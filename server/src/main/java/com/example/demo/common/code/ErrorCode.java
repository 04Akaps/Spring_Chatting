package com.example.demo.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeInterface {
    REDIS_VALUE_NOT_FOUND(-100, "In Memory에 키가 없습니다."),

    MAIL_RECEIVER_REQUIRED(-200, "Mail Receiver Required!!");

    private final Integer code;
    private final String message;
}