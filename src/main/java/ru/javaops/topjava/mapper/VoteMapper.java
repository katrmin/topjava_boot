package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.VoteDto;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.Vote;

public class VoteMapper {
    public static VoteDto mapToDto(Vote vote) {
        return VoteDto.builder()
                .id(vote.getId())
                .userId(vote.getUser().getId())
                .restaurant(RestaurantMapper.mapToDto(vote.getRestaurant()))
                .dateTime(vote.getDateTime())
                .build();
    }

    public static Vote map(VoteDto voteDto, User user) {
        return Vote.builder()
                .id(voteDto.getId())
                .user(user)
                .restaurant(RestaurantMapper.map(voteDto.getRestaurant()))
                .dateTime(voteDto.getDateTime())
                .build();
    }
}
