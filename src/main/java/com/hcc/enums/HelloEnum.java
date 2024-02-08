package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
// this is an example enum feel free to delete this once you have created your own.
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HelloEnum {
    Greeting1(1, "Good Morning"),
    Greeting2(2, "Good Afternoon"),
    Greeting3(3, "Good Evening"),
    Greeting4(4, "Good Night");

    private int greetingNumber;

    private String greetingMessage;
    HelloEnum(int greetingNumber, String greetingMessage) {
        this.greetingNumber = greetingNumber;
        this.greetingMessage = greetingMessage;
    }

    int getGreetingNumber() { return greetingNumber; }
    String getGreetingMessage() { return greetingMessage; }


}
