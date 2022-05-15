package ru.javaops.topjava.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Builder
@Data
public class VoteDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private RestaurantDto restaurant;
    @NotNull
    public Instant dateTime;
}
