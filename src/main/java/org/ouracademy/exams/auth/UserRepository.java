package org.ouracademy.exams.auth;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserAccount, String> {
    @Transactional(readOnly = true)
    @Cacheable("users")
    public Optional<UserAccount> findByName(String name);
    
}