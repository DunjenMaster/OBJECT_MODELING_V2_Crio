
package com.crio.jukebox.services;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.User;

public class UserSercviceImpl implements IUserService{

    private final IUserRepository userRepository;

    public UserSercviceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String name) {

        if(name == null || name.isEmpty() || name.trim().isEmpty()) {
            // TODO: Handle the condition
            return null;
        }

        User user = new User(name);
        User createdUser = userRepository.save(user);
        return createdUser;
        
    }
    
}
