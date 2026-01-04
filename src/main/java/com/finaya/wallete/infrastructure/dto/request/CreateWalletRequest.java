package com.finaya.wallete.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateWalletRequest(@NotNull @JsonProperty("id_user") Long idUser) {
}
