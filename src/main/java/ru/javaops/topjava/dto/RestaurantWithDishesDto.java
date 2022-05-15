package ru.javaops.topjava.dto;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RestaurantWithDishesDto extends NamedDto {
    private List<MenuRestaurantDishDto> menuRestaurantDishes;
}
