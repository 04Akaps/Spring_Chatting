package com.example.demo.domain.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSearchRequest (
    @NotBlank
    String name
) {}
