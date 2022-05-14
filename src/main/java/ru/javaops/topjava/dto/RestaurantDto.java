package ru.javaops.topjava.dto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RestaurantDto extends NamedDto {
    public RestaurantDto(Long id, String name) {
        super(id, name);
    }
}
