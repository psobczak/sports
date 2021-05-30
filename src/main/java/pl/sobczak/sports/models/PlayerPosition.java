package pl.sobczak.sports.models;

import java.util.Arrays;

public enum PlayerPosition {

    GOALKEEPER,
    DEFENDER,
    DEFENDER_CENTRE_BACK,
    DEFENDER_SWEEPER,
    DEFENDER_FULL_BACK,
    DEFENDER_WING_BACK,
    MIDFIELDER,
    MIDFIELDER_CENTRE_MIDFIELD,
    MIDFIELDER_DEFENSIVE_MIDFIELD,
    MIDFIELDER_ATTACKING_MIDFIELD,
    MIDFIELDER_WIDE_MIDFIELD,
    FORWARD,
    FORWARD_CENTRE_FORWARD,
    FORWARD_SECOND_STRIKER,
    FORWARD_WINGER;

    public static PlayerPosition getPlayerPositionFromString(String playerPosition) {
        return Arrays.stream(values())
                .filter(position -> position.name().equals(playerPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Position " + playerPosition + " was not found"));
    }
}
