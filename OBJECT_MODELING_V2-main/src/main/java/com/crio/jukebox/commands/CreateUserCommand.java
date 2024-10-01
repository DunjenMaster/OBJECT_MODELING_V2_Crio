package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;

    public CreateUserCommand(IUserService uService) {
        this.userService = uService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userName = "";

        if(tokens.size() == 2) {
            userName = tokens.get(1);

            User user = this.userService.create(userName);

            System.out.println(user);
        }
    }
    
}
