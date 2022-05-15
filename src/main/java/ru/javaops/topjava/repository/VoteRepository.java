package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT u FROM Vote u WHERE u.user.id=?1")
    List<Vote> get(Long userId);

    @Query("SELECT u FROM Vote u WHERE u.user.id=?1 and u.dateTime>=?2 and u.dateTime<?3")
    Optional<Vote> getByUserIdAndDateTime(Long userId, LocalDateTime startDt, LocalDateTime endDt);
}
