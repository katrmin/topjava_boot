package ru.javaops.topjava.web.restaurant;

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
import ru.javaops.topjava.dto.RestaurantDto;
import ru.javaops.topjava.mapper.RestaurantMapper;
import ru.javaops.topjava.repository.RestaurantRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/api";
    private final RestaurantRepository restaurantRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto create(@Valid @RequestBody RestaurantDto restaurantDto) {
        return RestaurantMapper.mapToDto(restaurantRepository.save(RestaurantMapper.map(restaurantDto)));
    }

    @PutMapping(value = "/admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto update(@Valid @RequestBody RestaurantDto restaurantDto, @PathVariable Long id) {
        log.info("update {} with id={}", restaurantDto, id);
        return RestaurantMapper.mapToDto(restaurantRepository.save(RestaurantMapper.map(restaurantDto)));
    }

    @DeleteMapping(value = "/admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete {}", id);
        restaurantRepository.deleteExisted(id);
    }

    @GetMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto get(@PathVariable Long id) {
        log.info("get {}", id);
        return RestaurantMapper.mapToDto(restaurantRepository.getById(id));
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDto> getAll() {
        log.info("getAll");
        return restaurantRepository.findAll(Sort.by("name"))
                .stream()
                .map(RestaurantMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
