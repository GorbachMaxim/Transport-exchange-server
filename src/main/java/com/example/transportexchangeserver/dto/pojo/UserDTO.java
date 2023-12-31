package com.example.transportexchangeserver.dto.pojo;

import com.example.transportexchangeserver.model.User;
import lombok.Data;
import org.mapstruct.Mapping;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private boolean isVerified;


    private Set<String> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(String username, String email, Set<String> set) {
        this.username = username;
        this.email = email;
        this.roles = set;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        user.getRoles().forEach(role -> {
            this.roles.add(role.toString());
        });
        this.isVerified = user.isVerified();
        this.id = user.getId();
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Set<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}

