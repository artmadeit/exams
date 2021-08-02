package org.ouracademy.exams.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    @Transactional(readOnly = true)
    public Optional<UserAccount> findByName(String name);

}