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
import ru.javaops.topjava.mapper.VoteMapper;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.repository.VoteRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = userRepository.getWithRoles(voteDto.getUserId()).orElseThrow(() -> new EntityNotFoundException(String.format("User id:%d not found", voteDto.getUserId())));
        return VoteMapper.mapToDto(voteRepository.save(VoteMapper.map(voteDto, user)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteDto update(@Valid @RequestBody VoteDto voteDto, @PathVariable Long id) {
        log.info("update {} with id={}", voteDto, id);
        User user = userRepository.getWithRoles(voteDto.getUserId()).orElseThrow(() -> new EntityNotFoundException(String.format("User id:%d not found", voteDto.getUserId())));
        return VoteMapper.mapToDto(voteRepository.save(VoteMapper.map(voteDto, user)));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete {}", id);
        voteRepository.deleteExisted(id);
    }

    @GetMapping(value = "/{id}/users/{userId}")
    public VoteDto get(@PathVariable Long id, @PathVariable Long userId) {
        log.info("get {}", id);
        return VoteMapper.mapToDto(voteRepository.get(id, userId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Vote (id:%d) of user (userId:%d) not found", id))));
    }

    @GetMapping()
    public List<VoteDto> getAll() {
        log.info("getAll");
        return voteRepository.findAll(Sort.by("menuRestaurantDish.restaurant.name"))
                .stream()
                .map(VoteMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
