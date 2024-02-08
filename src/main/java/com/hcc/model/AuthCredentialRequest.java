package com.hcc.model;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;

public class AuthCredentialRequest {
    private Long id;
    private User user;

    public AuthCredentialRequest(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public AuthCredentialRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthCredentialRequest(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Long id;
        private User user;

        private Builder() {

        }

        public Builder withId(Long idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withUser(User userToUse) {
            this.user = userToUse;
            return this;
        }

        public AuthCredentialRequest build() { return new AuthCredentialRequest(this); }
    }


}
