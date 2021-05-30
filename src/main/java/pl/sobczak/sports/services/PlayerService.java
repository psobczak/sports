package pl.sobczak.sports.services;

import pl.sobczak.sports.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto findPlayerByFirstName(String firstName, String lastName);

    List<PlayerDto> findAllPlayers();

    PlayerDto savePlayer(PlayerDto player);
}
