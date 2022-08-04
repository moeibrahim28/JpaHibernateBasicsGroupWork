package org.example.sports.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Entity
@Table(name="SPORTS_STATISTICS")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int scoreInGame;
    private String sport="Bowling";

    @ManyToOne
    private Athlete athlete;

}
