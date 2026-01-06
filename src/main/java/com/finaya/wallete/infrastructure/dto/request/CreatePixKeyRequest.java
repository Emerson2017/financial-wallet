package com.finaya.wallete.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finaya.wallete.domain.enums.PixKeyType;
import jakarta.validation.constraints.NotNull;

public record CreatePixKeyRequest (
        @NotNull @JsonProperty("keyType") PixKeyType pixKeyType,
        String key) {
}
