package com.example.demo.domain.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create User Response")
public record VerfiyTokenResponse (
    @Schema(description = "성공 유무")
    Boolean bool
) {}
