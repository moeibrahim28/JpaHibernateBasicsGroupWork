package org.example.sports.service;

import org.example.sports.model.Statistic;

public interface StatisticsService {
    int getUserStatisticChoice();

    int getUserChoice();

    Statistic getDistinctPlayerForNewStatistic();

    Statistic addStatisticToPlayer(Statistic statistic);

    Statistic addAthleteWithStatistic();
}
