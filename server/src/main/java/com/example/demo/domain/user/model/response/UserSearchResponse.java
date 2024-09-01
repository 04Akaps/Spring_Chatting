package com.example.demo.domain.user.model.response;


import com.example.demo.common.code.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Schema(description = "User Name List.")
public record UserSearchResponse (

    @Schema(description = "ErrorCode")
    ErrorCode description,

    @Schema(description = "이름")
    @NotBlank
    @NotNull
    List<String> name
) {}
