package org.ouracademy.exams.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    public Optional<UserAccount> findByName(String name);

}