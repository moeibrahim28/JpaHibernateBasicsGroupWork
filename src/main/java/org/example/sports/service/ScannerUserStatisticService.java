package org.example.sports.service;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;
import org.example.sports.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ScannerUserStatisticService implements StatisticsService{
    UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
    UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService);
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    //add an athlete with a statistic
    public  Statistic addAthleteWithStatistic() {
        Statistic statistic = new Statistic();
        String response = userInputService.getUserInput("What's the athletes name?",
                new NonBlankInputValidationRule());
        int score = Integer.parseInt(userInputService.getUserInput("What's the athletes score in this game?",
                new NonBlankInputValidationRule()));
        statistic.setName(response);
        statistic.setScoreInGame(score);
        return statistic;
    }

    //ask user which statistic they would like to see
    public int getUserStatisticChoice() {
        return Integer.parseInt(userInputService.getUserInput("What statistic would you like to see?\n" +
                        "1. Print their average points per game\n" +
                        "2. Max number of points in a game",
                new NonBlankInputValidationRule()));
    }

    //ask user what action they would like to perform
    public int getUserChoice() {
        return Integer.parseInt(userInputService.getUserInput("What would you like to do?\n" +
                        "1. Add an athlete with a game statistic\n" +
                        "2. Add another game statistic for the same athlete\n" +
                        "3. Get statistics information on that athlete\n" +
                        "4. Exit program.",
                new NonBlankInputValidationRule()));

    }

    //get list of all distinct athletes
    //print list
    //ask user which one he wants to do stuff with
    public Statistic getDistinctPlayerForNewStatistic() {
        String hql = "SELECT S from Statistic S";

        List<Statistic> statisticNamesList = entityManager.createQuery(hql,
                Statistic.class).getResultList();
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < statisticNamesList.size(); i++) {
            if (!playerNames.contains(statisticNamesList.get(i).getName())) {
                playerNames.add(statisticNamesList.get(i).getName());
            }
        }
        String playerNamesString = "";
        int count = 0;
        for (String playerName : playerNames) {
            playerNamesString += "\n" + count + ". " + playerName;
            count++;

        }
        int chosenPlayer = Integer.parseInt(userInputService.getUserInput("Which athlete do you want to choose?" + playerNamesString,
                new NonBlankInputValidationRule()));
        Statistic statistic = new Statistic();
        statistic.setName(statisticNamesList.get(chosenPlayer).getName());


        return statistic;
    }

    //add statistic to existing player
    public Statistic addStatisticToPlayer(Statistic statistic) {

        int score = Integer.parseInt(userInputService.getUserInput("What's the athletes score in this game?",
                new NonBlankInputValidationRule()));
        statistic.setScoreInGame(score);
        return statistic;
    }


}
