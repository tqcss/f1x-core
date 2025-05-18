package com.app.f1x.util;

import com.app.f1x.payload.request.RegisterUserRequest;

public class AuthUtil {

    public static Boolean passwordsMatch(RegisterUserRequest registerRequest) {
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }

}
