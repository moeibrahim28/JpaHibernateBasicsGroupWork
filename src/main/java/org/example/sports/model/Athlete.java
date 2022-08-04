package org.example.sports.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Entity
@Table(name="ATHLETE_LIST")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String name;

    @OneToMany(mappedBy = "athlete")
    private List<Statistic> statisticList = new ArrayList<>();
}
