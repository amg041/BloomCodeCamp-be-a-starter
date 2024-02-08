package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {

    Assignment1(1, "abc"),
    Assignment2(2, "def"),
    Assignment3(3, "ghi");




    private int assignmentNumber;
    private String githubUrl;


    AssignmentEnum(int assignmentNumber, String githubUrl) {
        this.assignmentNumber = assignmentNumber;
        this.githubUrl = githubUrl;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public String getGithubUrl() {
        return githubUrl;
    }
}
