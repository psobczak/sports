package pl.sobczak.sports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.models.PlayerPosition;
import pl.sobczak.sports.repositories.PlayerRepository;

import java.time.LocalDate;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository) {
        return args -> {
            log.info("Preloading data into database");
            playerRepository.save(new Player(1L, "Paweł", "Sobczak", LocalDate.of(1993, 6, 29), "Dragon Dzierzbin", "Poland", PlayerPosition.DEFENDER_SWEEPER));
            playerRepository.save(new Player(2L, "Martin", "Moran", LocalDate.of(1883, 1, 1), "Chelsea", "Scotish", PlayerPosition.DEFENDER_CENTRE_BACK));
            playerRepository.save(new Player(3L, "Mason", "Mount", LocalDate.of(1999, 1, 10), "Chelsea", "English", PlayerPosition.MIDFIELDER));
        };
    }
}
