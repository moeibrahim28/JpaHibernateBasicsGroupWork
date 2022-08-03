package org.example.sports.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Entity
@Table(name="ATHLETE_LIST")
public class Athlete {
    @Id
    private int id=counter.getAndIncrement();
    private String name;
    private static final AtomicInteger counter=new AtomicInteger(0);
}
