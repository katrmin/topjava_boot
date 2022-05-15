package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.VoteDto;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.Vote;

public class VoteMapper {
    public static VoteDto mapToDto(Vote vote) {
        return VoteDto.builder()
                .id(vote.getId())
                .userId(vote.getUser().getId())
                .menuRestaurantDish(MenuRestaurantDishMapper.mapToDto(vote.getMenuRestaurantDish()))
                .dateTime(vote.getDateTime())
                .build();
    }

    public static Vote map(VoteDto voteDto, User user) {
        return Vote.builder()
                .id(voteDto.getId())
                .user(user)
                .menuRestaurantDish(MenuRestaurantDishMapper.map(voteDto.getMenuRestaurantDish()))
                .dateTime(voteDto.getDateTime())
                .build();

    }
}
