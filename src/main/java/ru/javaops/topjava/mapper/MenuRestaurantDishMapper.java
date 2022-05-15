package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.MenuRestaurantDishDto;
import ru.javaops.topjava.model.MenuRestaurantDish;

public class MenuRestaurantDishMapper {
    public static MenuRestaurantDishDto mapToDto(MenuRestaurantDish dish) {
        return MenuRestaurantDishDto.builder()
                .id(dish.getId())
                .restaurant(RestaurantMapper.mapToDto(dish.getRestaurant()))
                .dish(DishMapper.mapToDto(dish.getDish()))
                .dishPrice(dish.getDishPrice())
                .dateTime(dish.getDateTime())
                .build();
    }

    public static MenuRestaurantDish map(MenuRestaurantDishDto dishDto) {
        return MenuRestaurantDish.builder()
                .id(dishDto.getId())
                .restaurant(RestaurantMapper.map(dishDto.getRestaurant()))
                .dish(DishMapper.map(dishDto.getDish()))
                .dishPrice(dishDto.getDishPrice())
                .dateTime(dishDto.getDateTime())
                .build();
    }
}
