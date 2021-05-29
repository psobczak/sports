package pl.sobczak.sports.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sobczak.sports.exceptions.PlayerNotFoundException;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.models.PlayerPosition;
import pl.sobczak.sports.services.PlayerService;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldReturnPlayer() throws Exception {
        when(playerService.findPlayerByFirstName("Paweł", "Sobczak"))
                .thenReturn(
                        new Player(
                                1L,
                                "Paweł",
                                "Sobczak",
                                LocalDate.of(1993, 6, 29),
                                "Dragon Dzierzbin",
                                "Poland",
                                PlayerPosition.DEFENDER_SWEEPER)
                );

        this.mockMvc.perform(get("/player?firstName=Paweł&lastName=Sobczak"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Paweł"))
                .andExpect(jsonPath("$.lastName").value("Sobczak"))
                .andExpect(jsonPath("$.birthday").value("1993-06-29"))
                .andExpect(jsonPath("$.team").value("Dragon Dzierzbin"))
                .andExpect(jsonPath("$.country").value("Poland"))
                .andExpect(jsonPath("$.position").value("DEFENDER_SWEEPER"));
    }

    @Test
    void shouldReturnAllPlayers() throws Exception {
        var players = List.of(
                new Player(1L, "Paweł", "Sobczak", LocalDate.of(1993, 6, 29), "Dragon Dzierzbin", "Poland", PlayerPosition.DEFENDER_SWEEPER),
                new Player(2L, "Martin", "Moran", LocalDate.of(1883, 1, 1), "Chelsea", "Scotish", PlayerPosition.DEFENDER_CENTRE_BACK),
                new Player(3L, "Mason", "Mount", LocalDate.of(1999, 1, 10), "Chelsea", "English", PlayerPosition.MIDFIELDER)
        );

        when(playerService.findAllPlayers())
                .thenReturn(players);

        this.mockMvc.perform(get("/players"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].firstName", is("Paweł")))
                .andExpect(jsonPath("$.[1].firstName", is("Martin")))
                .andExpect(jsonPath("$.[2].firstName", is("Mason")));
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() throws Exception {
        when(playerService.findPlayerByFirstName("Grzegorz", "Brzęczyszczykiewicz"))
                .thenThrow(new PlayerNotFoundException());

        this.mockMvc.perform(get("/player?firstName=Grzegorz&lastName=Brzęczyszczykiewicz"))
                .andExpect(status().isNotFound());
    }
}
