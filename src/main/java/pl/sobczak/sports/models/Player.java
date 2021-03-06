package pl.sobczak.sports.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@javax.persistence.Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String team;
    private String country;
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

}
