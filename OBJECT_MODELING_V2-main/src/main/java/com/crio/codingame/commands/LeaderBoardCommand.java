package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class LeaderBoardCommand implements ICommand{

    private final IUserService userService;
    
    public LeaderBoardCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllUserScoreOrderWise method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LEADERBOARD","ASC"]
    // or
    // ["LEADERBOARD","DESC"]

    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() != 2) {
                System.out.println("Invalid number of tokens. Please provide the sorting order (ASC/DESC).");
                return;
            }

            String orderString = tokens.get(1);

            ScoreOrder scoreOrder;
            try {
                scoreOrder = ScoreOrder.valueOf(orderString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid sorting order: " + orderString + ". Please use ASC or DESC.");
                return;
            }

            List<User> users = userService.getAllUserScoreOrderWise(scoreOrder);
            System.out.println(users);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
    }
    
}
}
