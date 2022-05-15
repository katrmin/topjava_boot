package ru.javaops.topjava.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.javaops.topjava.HasId;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class VoteDto implements HasId {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private RestaurantDto restaurant;
    public LocalDateTime dateTime;
}
