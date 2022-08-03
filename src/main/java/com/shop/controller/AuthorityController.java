package com.shop.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.shop.config.JwtUtil;
import com.shop.dto.JwtResponse;
import com.shop.dto.ResponseMessage;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.enumEntity.AuthenticationProvider;
import com.shop.enumEntity.Expired;
import com.shop.enumEntity.RoleName;
import com.shop.enumEntity.StatusMessage;
import com.shop.helper.UserNotFoundException;
import com.shop.services.Impl.RoleServiceImpl;
import com.shop.services.Impl.UserDetailServiceImp;
import com.shop.services.Impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class AuthorityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Autowired
    private UserServiceImpl userService;
    @Value("${google.clientId}")
    private String CLIENT_ID;

    @Autowired
    private RoleServiceImpl roleService;

    @SneakyThrows
    @PostMapping("/generate-token")
    public ResponseEntity<ResponseMessage> loginSecurity(@RequestBody JwtResponse jwtResponse) {
        try {
            this.authenticate(jwtResponse.getUserDto().getEmail(), jwtResponse.getUserDto().getPassword());
        } catch (UserNotFoundException e) {
            e.getStackTrace();
            throw new Exception("User not found " + e.getMessage());
        }
        UserDetails userDetails = this.userDetailServiceImp.loadUserByUsername(jwtResponse.getUserDto().getEmail());
        if (this.passwordEncoder.matches(jwtResponse.getUserDto().getPassword(), userDetails.getPassword())) {
            String tokens
                    = this.jwtUtil.generateToken(userDetails, jwtResponse.getRememberMe() ? Expired.DAYS : Expired.HOURS);
            User users = (User) this.userDetailServiceImp.loadUserByUsername(this.getNamePrincipal());
            JwtResponse jwtResponse1 = new JwtResponse();
            BeanUtils.copyProperties(users, jwtResponse1);
            jwtResponse1.setToken(tokens);
            jwtResponse1.setRememberMe(jwtResponse.getRememberMe());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(StatusMessage.OK, "Login is successfully", jwtResponse1));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessage(StatusMessage.ERROR, "Login is not successfully", null));
        }

    }

    @SneakyThrows
    @PostMapping("/google")
    public ResponseEntity<?> signInWithGoogleToken(@RequestBody String token) {
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        GoogleIdToken.Payload payload = idToken.getPayload();

        // Print user identifier
        String userId = payload.getSubject();
        User user;
        if (this.userService.findByEmail(payload.getEmail()) == null) {
            user = new User();
            user.setEmail(payload.getEmail());
            user.setPassword(this.passwordEncoder.encode(userId));
            user.setFullName((String) payload.get("name"));
            user.setAuthProvider(AuthenticationProvider.GOOGLE_PROVIDER);
            user.setImageUrl((String) payload.get("picture"));
            Role role = this.roleService.findByRoleName(RoleName.CLIENT);
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            user.setRoleSet(roleSet);
            this.userService.createUser(user);
        } else {
            user = this.userService.findByEmail(payload.getEmail());
        }

        try {
            this.authenticate(user.getUsername(), user.getPassword());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        UserDetails userDetails = this.userDetailServiceImp.loadUserByUsername(user.getUsername());
        String tokens = this.jwtUtil.generateToken(userDetails, payload.getExpirationTimeSeconds());
        System.out.println(tokens);
        User users = (User) this.userDetailServiceImp.loadUserByUsername(this.getNamePrincipal());
        JwtResponse jwtResponse1 = new JwtResponse();
        BeanUtils.copyProperties(users, jwtResponse1);
        jwtResponse1.setToken(tokens);
        jwtResponse1.setRememberMe(true);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage(StatusMessage.OK, "Login is successfully", jwtResponse1));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            System.out.println("User disable " + e.getMessage());
        } catch (BadCredentialsException e) {
            System.out.println("User bad credentials " + e.getMessage());
        }
    }

    private String getNamePrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}