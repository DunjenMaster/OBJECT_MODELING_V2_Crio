package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<Integer, User> userMap;
    private Integer autoIncrementId = 0;

    public UserRepository() {
        this.userMap = new HashMap<>();
    }

    public UserRepository(Map<Integer, User> userMap) {
        this.userMap = userMap;
        autoIncrementId = userMap.size();
    }

    @Override
    public User save(User user) {

        int id = user.getId();

        if(id < 1) {
            autoIncrementId++;
            User newUser = new User(autoIncrementId, user.getName());
            this.userMap.put(autoIncrementId, newUser);
            return newUser;
        }

        this.userMap.put(id, user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return this.userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = Optional.ofNullable(this.userMap.get(id));
        return user;
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user = this.userMap.values()
                                            .stream()
                                            .filter(s -> s.getName().equals(name))
                                            .findAny();
        return user;
    }
    
}
