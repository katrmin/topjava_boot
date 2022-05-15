package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.DishDto;
import ru.javaops.topjava.model.Dish;

public class DishMapper {
    public static Dish map(DishDto dishDto) {
        return Dish.builder()
                .name(dishDto.getName())
                .id(dishDto.getId())
                .build();
    }

    public static DishDto mapToDto(Dish dish) {
        return DishDto.builder()
                .name(dish.getName())
                .id(dish.getId())
                .build();
    }
}
