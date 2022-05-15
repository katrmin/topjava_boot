package ru.javaops.topjava.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MenuRestaurantDishDto {
    private Long id;
    private DishDto dish;
    private RestaurantDto restaurant;
    private BigDecimal dishPrice;
    private Instant dateTime;
}
