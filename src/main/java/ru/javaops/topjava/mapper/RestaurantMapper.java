package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.RestaurantDto;
import ru.javaops.topjava.dto.RestaurantWithDishesDto;
import ru.javaops.topjava.model.Restaurant;

import java.util.stream.Collectors;

public class RestaurantMapper {
    public static Restaurant map(RestaurantDto restaurantDto) {
        return Restaurant.builder()
                .name(restaurantDto.getName())
                .id(restaurantDto.getId())
                .build();
    }

    public static RestaurantDto mapToDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .name(restaurant.getName())
                .id(restaurant.getId())
                .build();
    }

    public static RestaurantWithDishesDto mapToDtoWithDishes(Restaurant restaurant) {
        return RestaurantWithDishesDto.builder()
                .name(restaurant.getName())
                .id(restaurant.getId())
                .menuRestaurantDishes(restaurant.getMenuRestaurantDishes()
                        .stream()
                        .map(MenuRestaurantDishMapper::mapToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
