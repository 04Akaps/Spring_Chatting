package com.example.demo.domain.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Login")
public record LoginRequest (
    @Schema(description = "이름")
    @NotBlank
    @NotNull
    String name,

    @Schema(description = "password")
    @NotNull
    @NotBlank
    String password
) {}
