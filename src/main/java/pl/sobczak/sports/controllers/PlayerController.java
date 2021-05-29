package pl.sobczak.sports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.services.PlayerService;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerByFirstName(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        return playerService.findPlayerByFirstName(firstName, lastName);
    }

    @GetMapping("/players")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

}
