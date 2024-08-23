package com.example.demo.domain.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "User를 생성 합니다.")
public record CreateUserRequest (
    @Schema(description = "이름")
    @NotBlank
    @NotNull
    String name,

    @Schema(description = "password")
    @NotNull
    @NotBlank
    String password
) {}
