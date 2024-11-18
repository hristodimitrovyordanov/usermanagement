package com.hristo.usermanagement.user.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
