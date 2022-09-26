package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shop.config.CustomAuthorityDeserializer;
import com.shop.dto.Authority;
import com.shop.enumEntity.AuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authProvider;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();

    @OneToMany(mappedBy = "users_orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(mappedBy = "user_shippers", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Shipper> shippers;

    @OneToMany(mappedBy = "user_comments", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user_commentDetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CommentDetail> commentDetails;

    @OneToMany(mappedBy = "userReview", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reviews> reviewsSet = new LinkedHashSet<>();


    public User() {
    }

    public User(Long userId, String email, String password, String fullName,
                String phoneNumber, String address, String imageUrl,
                AuthenticationProvider authProvider) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.imageUrl = imageUrl;
        this.authProvider = authProvider;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        this.roleSet.forEach(e -> {
            authorities.add(new Authority(e.getRoleName()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(Set<Shipper> shippers) {
        this.shippers = shippers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<CommentDetail> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(Set<CommentDetail> commentDetails) {
        this.commentDetails = commentDetails;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    public Set<Reviews> getReviewsSet() {
        return reviewsSet;
    }

    public void setReviewsSet(Set<Reviews> reviewsSet) {
        this.reviewsSet = reviewsSet;
    }
}
