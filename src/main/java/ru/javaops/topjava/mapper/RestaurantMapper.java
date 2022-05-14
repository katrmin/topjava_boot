package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.RestaurantDto;
import ru.javaops.topjava.model.Restaurant;

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
}
