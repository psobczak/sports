package pl.sobczak.sports.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sobczak.sports.models.Player;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query(value = "SELECT * FROM Player WHERE FIRST_NAME = :firstName and LAST_NAME = :lastName", nativeQuery = true)
    Optional<Player> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
