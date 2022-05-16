package ru.javaops.topjava.mapper;

import ru.javaops.topjava.dto.VoteDto;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.Vote;

import java.time.LocalDateTime;

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
                .restaurant(RestaurantMapper.idMap(voteDto.getRestaurant()))
                .dateTime(voteDto.getDateTime() != null ? voteDto.getDateTime() : LocalDateTime.now())
                .build();
    }
}
