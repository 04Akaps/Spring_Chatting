package com.example.demo.domain.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Verify User Response")
public record VerifyUserResponse (
    @Schema(description = "성공 유무")
    String code
) {}
