package com.crud.minerals.repository;

import com.crud.minerals.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findFirstByLogin(String login);
}
