package com.store.database.dao.realization;

import com.store.database.dao.UserService;
import com.store.database.dao.WebStoreService;
import com.store.database.model.Role;
import com.store.database.model.User;
import com.store.database.repository.UserRepository;
import com.store.exception.UserDoesNotExist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;

//@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    @PostConstruct
    public void creation(){
       logger.debug("DEBUG");
       logger.info("INFO");
       logger.warn("WARN");
       logger.error("ERROR");
    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user)  {
        if (repository.exists(user.getUsername()))
            return null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Role role = new Role();
        role.setRole("ROLE_USER");

        user.setRoles(Collections.singletonList(role));
        user.setPassword(encoder.encode(user.getPassword()));
        
        return repository.save(user);
    }


    @Override
    public User update(User user) throws UserDoesNotExist {
        if (!repository.exists(user.getUsername()))
            throw new UserDoesNotExist(user.getUsername());

        return repository.save(user);
    }

    @Override
    public void delete(String s) {
        repository.delete(s);
    }

    @Override
    public User get(String username){
        return repository.findOne(username);
    }

    @Override
    public Iterable<User> getAll() {
        return null;
    }


    public UserRepository getRepository() {
        return repository;
    }
}
