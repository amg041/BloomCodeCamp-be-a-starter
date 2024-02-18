package com.hcc.model;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;

public class AssignmentResponseDto {

    private Long id;
    private Integer number;
    private User user;
    private String githubUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public AssignmentResponseDto(Builder builder) {
        this.id = builder.id;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Long id;
        private Integer number;
        private User user;
        private String githubUrl;

        private Builder() {

        }

        public Builder withId(Long idToUse) {
            this.id = idToUse;
            return this;
        }
        public Builder withNumber(Integer numberToUse) {
            this.number = numberToUse;
            return this;
        }
        public Builder withUser(User userToUse) {
            this.user = userToUse;
            return this;
        }

        public Builder withGithubUrl(String githubUrlToUse) {
            this.githubUrl = githubUrlToUse;
            return this;
        }

        public AssignmentResponseDto build() { return new AssignmentResponseDto(this); }
    }

}
