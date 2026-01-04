package com.finaya.wallete.infrastructure.dto.request;

import com.finaya.wallete.domain.enums.WebhookPixEventType;

import java.time.Instant;
import java.util.UUID;

public record WebhookPixRequest (String endToEndId,
                                 UUID eventId,
                                 WebhookPixEventType eventType,
                                 Instant occurredAt) {
}
