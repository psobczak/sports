package pl.sobczak.sports.config.converters;

import org.springframework.stereotype.Component;
import pl.sobczak.sports.dto.PlayerDto;
import pl.sobczak.sports.models.Player;
import pl.sobczak.sports.models.PlayerPosition;

@Component
public class PlayerConverterImpl implements PlayerConverter {

    @Override
    public Player createFrom(PlayerDto dto) {
        return Player.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthday(dto.getBirthday())
                .team(dto.getTeam())
                .country(dto.getCountry())
                .position(PlayerPosition.getPlayerPositionFromString(dto.getPosition()))
                .build();
    }

    @Override
    public PlayerDto createFrom(Player entity) {
        return PlayerDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthday(entity.getBirthday())
                .country(entity.getCountry())
                .position(entity.getPosition().toString())
                .team(entity.getTeam())
                .build();
    }
}
