package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
// import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() < 4) {
                System.out.println("Invalid number of tokens. Please provide all the required arguments.");
                return;
            }
    
            String contestName = tokens.get(1);
            String levelString = tokens.get(2);
            String participantName = tokens.get(3);
            
            // If the number of tokens is at least 5, fetch the numQuestions.
            Integer numQuestions = 0;
            if (tokens.size() >= 5) {
                numQuestions = Integer.parseInt(tokens.get(4));
            }
            else {
                numQuestions = null;
            }
    
            Level level;
            try {
                level = Level.valueOf(levelString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid level: " + levelString);
                return;
            }
            
    
            Contest contest = contestService.create(contestName, level, participantName, numQuestions);
            System.out.println(contest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
