package com.onlinestore.api.auth.web;

public record VerifyCodeDto(String email,
                            Integer digit1,
                            Integer digit2,
                            Integer digit3,
                            Integer digit4,
                            Integer digit5,
                            Integer digit6) {
    public String sexDigits() {
        return "" + digit1 + digit2 + digit3 + digit4 + digit5 + digit6;
    }
}
