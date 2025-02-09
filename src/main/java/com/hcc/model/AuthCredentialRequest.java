package com.hcc.model;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;

public class AuthCredentialRequest {

    private String username;
    private String password;
    private User user;

    public AuthCredentialRequest(String username, String password, User user) {

        this.username = username;
        this.password = password;
        this.user = user;
    }

    public AuthCredentialRequest() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthCredentialRequest(Builder builder) {

        this.username = builder.username;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {

        private String username;
        private String password;
        private User user;
        private Builder() {

        }


        public Builder withUser(User userToUse) {
            this.user = userToUse;
            return this;
        }
        public Builder withUsername(String userToUse) {
            this.username = userToUse;
            return this;
        }

        public Builder withPassword(String passwordToUse) {
            this.password = passwordToUse;
            return this;
        }



        public AuthCredentialRequest build() { return new AuthCredentialRequest(this); }
    }


}
