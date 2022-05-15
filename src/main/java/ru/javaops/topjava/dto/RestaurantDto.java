package ru.javaops.topjava.dto;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RestaurantDto extends NamedDto {
    public RestaurantDto(Long id, String name) {
        super(id, name);
    }
}
