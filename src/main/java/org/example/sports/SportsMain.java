package org.example.sports;

import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
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
                        Athlete athlete= new Athlete();
                        Statistic statistic = statisticsService.addAthleteWithStatistic();
                        athlete.setName(statistic.getName());
                        statistic.setPlayerid(athlete.getId());
                        transaction.begin();
                        entityManager.persist(athlete);
                        transaction.commit();
                        transaction.begin();
                        entityManager.persist(statistic);
                        transaction.commit();
                        userChoice = statisticsService.getUserChoice();
                        break;
                    case 2:
                        //add a statistic to a pre-existing athlete
                        Statistic statistic1 = statisticsService.getDistinctPlayerForNewStatistic();
                        Statistic anotherStatistic = statisticsService.addStatisticToPlayer(statistic1);
                        anotherStatistic.setPlayerid(statistic1.getPlayerid());
                        transaction.begin();
                        entityManager.persist(anotherStatistic);
                        transaction.commit();
                        userChoice = statisticsService.getUserChoice();
                        break;
                    case 3:
                        int userStatisticChoice = statisticsService.getUserStatisticChoice();
                        //see either an average or max points
                        switch (userStatisticChoice) {
                            case 1:
                                //gets an athlete and prints their average points
                                Statistic statisticToGetAVG = statisticsService.getDistinctPlayerForNewStatistic();
                                String query = "SELECT S from Statistic S where S.name = '" + statisticToGetAVG.getName() + "'";
                                List<Statistic> statisticsToAverage = entityManager.createQuery(query,
                                        Statistic.class).getResultList();

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
                                String queryMax = "SELECT S from Statistic S where S.name = '" + statisticToGetMax.getName() + "'";
                                List<Statistic> statisticsToMax = entityManager.createQuery(queryMax,
                                        Statistic.class).getResultList();
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


