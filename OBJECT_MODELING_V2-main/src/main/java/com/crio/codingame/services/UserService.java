package com.crio.codingame.services;

import java.util.List;


import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
      User user = new User(name, 0);
     return userRepository.save(user);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
        List<User> allUsers = userRepository.findAll();

        if (scoreOrder == ScoreOrder.ASC) {
            allUsers.sort((u1, u2) -> u1.getScore().compareTo(u2.getScore()));
        } else if (scoreOrder == ScoreOrder.DESC) {
            allUsers.sort((u1, u2) -> u2.getScore().compareTo(u1.getScore()));
        }
        return allUsers;
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    
    
                
            Contest contest = contestRepository.findById(contestId)
                    .orElseThrow(() -> new ContestNotFoundException(
                            "Cannot Withdraw Contest. Contest for given id:" + contestId + " not found!"));
            User user = userRepository.findByName(userName)
                    .orElseThrow(() -> new UserNotFoundException(
                            "Cannot Withdraw Contest. User for given name:" + userName + " not found!"));
        
            if (contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)) {
                throw new InvalidOperationException(
                        "Cannot Withdraw Contest. Contest for given id:" + contestId + " is in progress!");
            }
            if (contest.getContestStatus().equals(ContestStatus.ENDED)) {
                throw new InvalidOperationException(
                        "Cannot Withdraw Contest. Contest for given id:" + contestId + " is ended!");
            }
            if (!user.checkIfContestExists(contest)) {
                throw new InvalidOperationException(
                        "Cannot Withdraw Contest. Contest for given id:" + contestId + " is not registered!");
            }
        
            user.deleteContest(contest);
            userRepository.save(user);
            return new UserRegistrationDto(contest.getName(), user.getName(), RegisterationStatus.NOT_REGISTERED);
        }
    @Override
    public void withdrawContest(String contestId, UserRegistrationDto registrationDto) {
        // TODO Auto-generated method stub
    
        Contest contest = contestRepository.findById(contestId)
        .orElseThrow(() -> new ContestNotFoundException(
                "Cannot Withdraw Contest. Contest for given id:" + contestId + " not found!"));
User user = userRepository.findByName(registrationDto.getUserName())
        .orElseThrow(() -> new UserNotFoundException(
                "Cannot Withdraw Contest. User for given name:" + registrationDto.getUserName() + " not found!"));

if (contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)) {
    throw new InvalidOperationException(
            "Cannot Withdraw Contest. Contest for given id:" + contestId + " is in progress!");
}
if (contest.getContestStatus().equals(ContestStatus.ENDED)) {
    throw new InvalidOperationException(
            "Cannot Withdraw Contest. Contest for given id:" + contestId + " is ended!");
}
if (!user.checkIfContestExists(contest)) {
    throw new InvalidOperationException(
            "Cannot Withdraw Contest. Contest for given id:" + contestId + " is not registered!");
        
}}
}
        
    
    

