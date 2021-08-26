package com.example.springsecurityjwt;

import com.example.springsecurityjwt.model.User;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class SpringsecurityJwtApplication {


private UserRepository repository;
    SpringsecurityJwtApplication(UserRepository repository){
        this.repository = repository;
    }

    @PostConstruct
    public void initUsers(){
        List<User> users = Stream.of(
                new User(101, "jason", "password","jason@gmail.com"),
                new User(102, "franklin", "pass1","joker@gmail.com"),
                new User(103, "jeff", "pass2","jeff@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityJwtApplication.class, args);
    }

}


