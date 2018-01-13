package com.store.database.dao;

import com.store.database.model.Role;
import com.store.database.model.User;
import com.store.database.repository.UserRepository;
import com.store.exception.UserDoesNotExist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;

public interface UserService extends WebStoreService<User,String> {

}
