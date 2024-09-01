package com.example.demo.domain.auth.model.response;

import com.example.demo.common.code.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login User Response")
public record LoginResponse (

    @Schema(description = "ErrorCode")
    ErrorCode description,

    @Schema(description = "JWT Token")
    String token
) {}
