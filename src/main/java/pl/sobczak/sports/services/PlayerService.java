package pl.sobczak.sports.services;

import pl.sobczak.sports.models.Player;

import java.util.List;

public interface PlayerService {

    Player findPlayerByFirstName(String firstName, String lastName);

    List<Player> findAllPlayers();
}
