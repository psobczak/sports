package pl.sobczak.sports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczak.sports.config.converters.PlayerConverter;
import pl.sobczak.sports.dto.PlayerDto;
import pl.sobczak.sports.exceptions.PlayerNotFoundException;
import pl.sobczak.sports.repositories.PlayerRepository;

import java.util.List;

@Service
public class PlayerServicesImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerConverter playerConverter;

    @Override
    public PlayerDto findPlayerByFirstName(String firstName, String lastName) {
        var player = playerRepository.findByFirstAndLastName(firstName, lastName)
                .orElseThrow(PlayerNotFoundException::new);
        return playerConverter.createFrom(player);
    }

    @Override
    public List<PlayerDto> findAllPlayers() {
        return playerConverter.createFromEntities(playerRepository.findAll());
    }

    @Override
    public PlayerDto savePlayer(PlayerDto playerDto) {
        var player = playerConverter.createFrom(playerDto);
        playerRepository.save(player);
        return playerDto;
    }
}
