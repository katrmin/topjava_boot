package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Restaurant;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @EntityGraph(attributePaths = {"menuRestaurantDishes", "menuRestaurantDishes.dish", "menuRestaurantDishes.restaurant"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT u FROM Restaurant u WHERE u.id=?1")
    Optional<Restaurant> getWithDishes(Long id);

    @Query("SELECT u FROM Restaurant u WHERE u.id=?1")
    Optional<Restaurant> get(Long id);
}
