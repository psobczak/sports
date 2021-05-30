package pl.sobczak.sports.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.models.PlayerPosition;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void prepareDatabase() {
        playerRepository.save(new Player(1L, "Paweł", "Sobczak", LocalDate.of(1993, 6, 29), "Dragon Dzierzbin", "Poland", PlayerPosition.DEFENDER_SWEEPER));
        playerRepository.save(new Player(2L, "Martin", "Moran", LocalDate.of(1883, 1, 1), "Chelsea", "Scotish", PlayerPosition.DEFENDER_CENTRE_BACK));
        playerRepository.save(new Player(3L, "Mason", "Mount", LocalDate.of(1999, 1, 10), "Chelsea", "English", PlayerPosition.MIDFIELDER));
    }

    @Test
    void shouldFindAllPlayersBornBetweenDates() {
        var players = playerRepository.findAllByBirthdayBetween(
                LocalDate.of(1993, 6, 29),
                LocalDate.now());

        Assertions.assertAll(() -> {
            Assertions.assertEquals(2, players.size());
            Assertions.assertEquals("Paweł", players.get(0).getFirstName());
            Assertions.assertEquals("Mason", players.get(1).getFirstName());
        });
    }
}
