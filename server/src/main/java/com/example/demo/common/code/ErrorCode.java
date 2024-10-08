package com.example.demo.common.code;



import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeInterface {
    SUCCESS(0, "SUCCESS"),

    USER_ALREADY_EXISTS(-1, "이미 존재하는 유저 이름입니다."),
    USER_SAVE_FAILED (-2, "신규 유저 저장에 실패하였습니다."),
    LOGIN_PASSWORD_FAILED(-3, "패스워드가 일치하지 않습니다."),
    NOT_EXISTED_USER(-4, "존재하지 않은 유저 입니다."),

    REDIS_VALUE_NOT_FOUND(-100, "In Memory에 키가 없습니다."),
    REDIS_SAVE_FAILED(-102,"Save Failed"),

    ACCESS_TOKEN_IS_NOT_EXPIRED(-200, "Token IS Not EXPIRED"),
    TOKEN_IS_INVALID(-201, "Token is Not Valid"),
    TOKEN_IS_EXPIRED(-202,"Token is Expired");

    private final Integer code;
    private final String message;
}