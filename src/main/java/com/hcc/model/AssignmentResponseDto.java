package com.hcc.model;

import com.hcc.entities.Assignment;

public class AssignmentResponseDto {
    private Assignment assignment;

    public AssignmentResponseDto(Assignment assignment) {
        this.assignment = assignment;
    }

    public AssignmentResponseDto() {
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public AssignmentResponseDto(Builder builder) {
        this.assignment = builder.assignment;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Assignment assignment;

        private Builder() {

        }

        public Builder withAssignment(Assignment assignmentToUse) {
            this.assignment = assignmentToUse;
            return this;
        }

        public AssignmentResponseDto build() { return new AssignmentResponseDto(this); }
    }
}
