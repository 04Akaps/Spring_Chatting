package com.example.demo.domain.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "User를 검증합니다.")
public record VerifyUserRequest (
    @Schema(description = "이메일")
    @NotBlank
    @NotNull
    String email
) {}
