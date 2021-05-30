package pl.sobczak.sports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sobczak.sports.models.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT * FROM Player WHERE FIRST_NAME = :firstName and LAST_NAME = :lastName", nativeQuery = true)
    Optional<Player> findByFirstAndLastName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );

    @Query("SELECT p FROM Player p WHERE BIRTHDAY between ?1 and ?2")
    List<Player> findAllByBirthdayBetween(LocalDate from, LocalDate to);
}
