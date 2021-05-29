package pl.sobczak.sports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczak.sports.exceptions.PlayerNotFoundException;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.repositories.PlayerRepository;

import java.util.List;

@Service
public class PlayerServicesImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player findPlayerByFirstName(String firstName, String lastName) {
        return playerRepository.findByFirstAndLastName(firstName, lastName)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public List<Player> findAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }
}
