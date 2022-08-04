package org.example.sports;

import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.db.Repository;
import org.example.sports.db.AthleteRepository;
import org.example.sports.db.StatisticRepository;
import org.example.sports.model.Athlete;
import org.example.sports.model.Statistic;
import org.example.sports.service.ScannerUserStatisticService;
import org.example.sports.service.StatisticsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SportsMain {
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        StatisticsService statisticsService = new ScannerUserStatisticService();
        StatisticRepository statisticRepository = new StatisticRepository();
        AthleteRepository athleteRepository = new AthleteRepository();
        try {
            userOutputService.print("WELCOME");

            int userChoice = statisticsService.getUserChoice();
            boolean runValue = true;
            while (runValue) {
                //create entity manager
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                // access transaction object
                EntityTransaction transaction = entityManager.getTransaction();
                switch (userChoice) {
                    case 1:
                        //add a new athlete and get a statistic for them
                        Statistic statistic = statisticsService.addAthleteWithStatistic();
                        athleteRepository.save(statistic.getAthlete());
                        statisticRepository.save(statistic);
                        userChoice = statisticsService.getUserChoice();
                        break;
                    case 2:
                        //add a statistic to a pre-existing athlete
                        Statistic statistic1 = statisticsService.getDistinctPlayerForNewStatistic();
                        Statistic anotherStatistic = statisticsService.addStatisticToPlayer(statistic1);
                        anotherStatistic.setAthlete(statistic1.getAthlete());
                        statisticRepository.save(anotherStatistic);
                        userChoice = statisticsService.getUserChoice();
                        break;
                    case 3:
                        int userStatisticChoice = statisticsService.getUserStatisticChoice();
                        //see either an average or max points
                        switch (userStatisticChoice) {
                            case 1:
                                //gets an athlete and prints their average points
                                Statistic statisticToGetAVG = statisticsService.getDistinctPlayerForNewStatistic();
                                List<Statistic> statisticsToAverage = statisticRepository.getListOfStatistics(statisticToGetAVG);

                                float average = 0;
                                double count = 0;
                                double total = 0;
                                for (Statistic stat : statisticsToAverage) {
                                    total += stat.getScoreInGame();
                                    count++;
                                }
                                average = (float) (total / count);
                                System.out.println("Average score= " + average);
                                break;
                            case 2:
                                //gets an athlete and prints their max points
                                Statistic statisticToGetMax = statisticsService.getDistinctPlayerForNewStatistic();
                                List<Statistic> statisticsToMax = statisticRepository.getListOfStatistics(statisticToGetMax);
                                int max = 0;
                                for (Statistic stat : statisticsToMax) {
                                    if (max < stat.getScoreInGame()) {
                                        max = stat.getScoreInGame();
                                    }
                                }
                                System.out.println("Max points= " + max);
                                break;
                            default:
                                break;
                        }
                        userChoice = statisticsService.getUserChoice();
                        break;
                    case 4:
                        runValue = false;
                        break;
                    default:
                        System.out.println("Please choose a number from the selection given");
                        break;
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


