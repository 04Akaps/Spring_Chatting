package com.example.demo.domain.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Token을 기반으로 인증합니다.")
public record VerifyTokenRequest (
    @Schema(description = "Token")
    @NotBlank
    @NotNull
    String token
) {}
