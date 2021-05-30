package pl.sobczak.sports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sobczak.sports.dto.MultiplePlayersResponse;
import pl.sobczak.sports.dto.PlayerDto;
import pl.sobczak.sports.dto.PlayerResponse;
import pl.sobczak.sports.services.PlayerService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponse getPlayerByFirstName(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        PlayerDto player = playerService.findPlayerByFirstName(firstName, lastName);
        return PlayerResponse.builder()
                .player(player)
                .build();
    }

    @GetMapping("/players")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MultiplePlayersResponse getAllPlayers(
            @RequestParam(required = false) LocalDate birthdayFrom,
            @RequestParam(required = false) LocalDate birthdayTo) {
        List<PlayerDto> allPlayers = playerService.findAllPlayers();
        return MultiplePlayersResponse.builder()
                .players(allPlayers)
                .build();
    }

    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDto postPlayer(@RequestBody PlayerDto playerDTO) {
        playerService.savePlayer(playerDTO);
        return playerDTO;
    }

//    @DeleteMapping("/player")
//    public PlayerDto deletePlayer
}
