package ru.javaops.topjava.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.dto.VoteDto;
import ru.javaops.topjava.error.IllegalRequestDataException;
import ru.javaops.topjava.mapper.RestaurantMapper;
import ru.javaops.topjava.mapper.VoteMapper;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.Vote;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.repository.VoteRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.javaops.topjava.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteDto create(@Valid @RequestBody VoteDto voteDto) {
        log.info("create Vote:");
        voteRepository.getByUserIdAndDateTime(voteDto.getUserId()
                        , LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                        , LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1))
                .ifPresent(vote -> {
                    throw new IllegalRequestDataException("User has already voted today!");
                });
        User user = userRepository.getWithRoles(voteDto.getUserId()).orElseThrow(() -> new EntityNotFoundException(String.format("User id:%d not found", voteDto.getUserId())));
        return VoteMapper.mapToDto(voteRepository.save(VoteMapper.map(voteDto, user)));
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteDto update(@Valid @RequestBody VoteDto voteDto, @PathVariable Long id) {
        log.info("update {} with userId={}", voteDto, id);
        assureIdConsistent(voteDto, id);

        Optional<Vote> optionalVote = voteRepository.getByUserIdAndDateTime(voteDto.getUserId()
                , LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                , LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1));
        if (LocalTime.now().isBefore(LocalTime.parse("11:00:00")) &&
                optionalVote.isPresent()) {
            Vote vote = optionalVote.get();
            vote.setDateTime(voteDto.getDateTime() != null ? voteDto.getDateTime() : LocalDateTime.now());
            vote.setRestaurant(RestaurantMapper.map(voteDto.getRestaurant()));
            return VoteMapper.mapToDto(voteRepository.save(vote));
        } else {
            throw new IllegalRequestDataException("User can't vote today yet!");
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete {}", id);
        voteRepository.deleteExisted(id);
    }

    @GetMapping(value = "/users/{userId}")
    public List<VoteDto> get(@PathVariable Long userId) {
        log.info("get by userId {}", userId);
        return voteRepository.get(userId).stream()
                .map(VoteMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping()
    public List<VoteDto> getAll() {
        log.info("getAll");
        return voteRepository.findAll(Sort.by("restaurant.name"))
                .stream()
                .map(VoteMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
