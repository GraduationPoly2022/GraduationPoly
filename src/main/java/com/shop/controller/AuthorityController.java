package com.shop.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.shop.config.JwtUtil;
import com.shop.dto.JwtResponse;
import com.shop.dto.ResponseMessage;
import com.shop.dto.UserDto;
import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.enumEntity.*;
import com.shop.helper.handleCode.HandleTimeCode;
import com.shop.helper.handleCode.TimeCode;
import com.shop.services.IMailService;
import com.shop.services.IRoleService;
import com.shop.services.IUserService;
import com.shop.services.Impl.UserDetailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.shop.helper.CheckMail.emailExists;

@RestController
@RequestMapping("/auth")
public class AuthorityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private IUserService userService;
    @Value("${google.clientId}")
    private String CLIENT_ID;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private HandleTimeCode handleTimeCode;

    private TimeCode timeCode;
    @Autowired
    private IMailService mailService;

    @PostMapping("/generate-token")
    public ResponseEntity<ResponseMessage> loginSecurity(@RequestBody JwtResponse jwtResponse) {
        try {
            this.authenticate(jwtResponse.getUserDto().getEmail(), jwtResponse.getUserDto().getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMessage(StatusMessage.ERROR, "Account does not exist yet", null));
        }
        UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(jwtResponse.getUserDto().getEmail());
        if (this.passwordEncoder.matches(jwtResponse.getUserDto().getPassword(), userDetails.getPassword())) {
            JwtResponse jwtResponse1;
            if (jwtResponse.getMobile()) {
                jwtResponse1 = this.hanldeToken(userDetails, Expired.TIME_MOBILE,
                        jwtResponse.getRememberMe(), jwtResponse.getMobile());
            } else {
                jwtResponse1 = this.hanldeToken(userDetails, jwtResponse.getRememberMe() ? Expired.DAYS : Expired.HOURS,
                        jwtResponse.getRememberMe(), jwtResponse.getMobile());
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(StatusMessage.OK, "Logged in successful", jwtResponse1));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessage(StatusMessage.ERROR, "Login unsuccessful", null));
        }

    }

    @PostMapping("/google")
    public ResponseEntity<ResponseMessage> signInWithGoogleToken(@RequestBody String token) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        GoogleIdToken.Payload payload = idToken.getPayload();

        String userId = payload.getSubject();
        User user;
        if (this.userService.findByEmail(payload.getEmail()) == null) {
            user = this.handleCreateUserPayload(payload, userId);
        } else {
            user = this.userService.findByEmail(payload.getEmail());
        }

        try {
            this.authenticate(user.getUsername(), userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Lỗi " + e.getMessage(), null));
        }
        UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(user.getUsername());
        JwtResponse jwtResponse1 = this.hanldeToken(userDetails, payload.getExpirationTimeSeconds(), true, false);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage(StatusMessage.OK, "Login is successfully", jwtResponse1));
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseMessage> createUser(@RequestParam("code") String code, @RequestBody UserDto userDto) {
        ResponseEntity<ResponseMessage> message = null;
        User user = new User();
//        if (this.handleTimeCode.fileNotFound() && this.timeCode == null) {
//            this.timeCode = this.handleTimeCode.timeCodeExCode;
//            this.timeCode.setEmail(userDto.getEmail());
//        }
//        if (!this.timeCode.getCode().equals(code) && !this.timeCode.getEmail().equals(userDto.getEmail())) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseMessage(StatusMessage.FAILED, "Invalid authentication code", null)
//            );
//        }
        try {
            UserController.CreateUser(userDto, user, this.roleService, this.passwordEncoder.encode(userDto.getPassword()));
            if (Objects.equals(this.timeCode.getCode(), code) && this.timeCode.getEmail().equals(userDto.getEmail())) {
                User u = this.userService.createUser(user);
                if (u != null) {
                    message = ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMessage(StatusMessage.OK, "Successful account registration", u));
                }
            }
        } catch (Exception e) {
            message = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, e.getMessage(), null));
        }
        return message;
    }

    @GetMapping("/check-mail")
    public ResponseEntity<ResponseMessage> ckeckEmailExists(@RequestParam("email") String email) {
        if (!emailExists(email)) {
            UserDto userError = new UserDto();
            userError.setEmail("Email address does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.FAILED, "Email address does not exist", userError));
        } else if (this.userService.findByEmail(email) != null) {
            UserDto userError = new UserDto();
            userError.setEmail("Email already exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.ERROR, "Email already exists", userError));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(StatusMessage.OK, "Email ok", true));
        }
    }

    @PatchMapping("/user")
    public ResponseEntity<ResponseMessage> handleForgotPassword(@RequestParam("email") String email,
                                                                @RequestParam("password") String password) {
        User user = this.userService.findByEmail(email);
        if (user != null) {
            user.setPassword(this.passwordEncoder.encode(password));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(StatusMessage.OK, "Change password successfully",
                            this.userService.createUser(user))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseMessage(StatusMessage.OK, "Password change failed",
                        null)
        );
    }

    @GetMapping("/send-mail/{toForm}/{name}/{status}")
    public ResponseEntity<ResponseMessage> sendCode(@PathVariable("toForm") String toForm,
                                                    @PathVariable("name") String name,
                                                    @PathVariable("status") TypeUsers status) {
        ResponseEntity<ResponseMessage> message;

        if (!emailExists(toForm)) {
            UserDto userError = new UserDto();
            userError.setEmail("Email address does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(StatusMessage.FAILED, "Email address does not exist", userError));
        } else {

            if (this.userService.findByEmail(toForm) != null && status == TypeUsers.CREATE) {
                UserDto userError = new UserDto();
                userError.setEmail("Email already exists");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseMessage(StatusMessage.ERROR, "Email already exists", userError));
            }
            try {
                this.timeCode = this.handleTimeCode.timeCode();
                this.mailService.sendCodeConfirm(toForm, name, this.timeCode.getCode());
                this.timeCode.setEmail(toForm);
                message = ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Account verification code", this.timeCode));
            } catch (Exception e) {
                message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseMessage(StatusMessage.FAILED, "Error " + e.getMessage(), null)
                );
            }
        }
        return message;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    private <T> JwtResponse hanldeToken(UserDetails userDetails, T expired, Boolean rememberMe, Boolean moblie) {
        String tokens = this.jwtUtil.generateToken(userDetails, expired);
        User users = (User) this.userDetailServiceImpl.loadUserByUsername(userDetails.getUsername());
        JwtResponse jwtResponse1 = new JwtResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(users, userDto, "password");
        userDto.setEmail(users.getUsername());
        userDto.setAuthority(RoleName.valueOf(users.getAuthorities().stream().iterator().next().getAuthority()));
        jwtResponse1.setToken(tokens);
        jwtResponse1.setUserDto(userDto);
        jwtResponse1.setMobile(moblie);
        jwtResponse1.setRememberMe(rememberMe);
        return jwtResponse1;
    }

    private User handleCreateUserPayload(GoogleIdToken.Payload payload, String userId) {
        User user = new User();
        user.setEmail(payload.getEmail());
        user.setPassword(this.passwordEncoder.encode(userId));
        user.setFullName((String) payload.get("name"));
        user.setAuthProvider(AuthenticationProvider.GOOGLE_PROVIDER);
        user.setImageUrl((String) payload.get("picture"));
        Role role = this.roleService.findByRoleName(RoleName.CLIENT);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoleSet(roleSet);
        return this.userService.createUser(user);
    }
}