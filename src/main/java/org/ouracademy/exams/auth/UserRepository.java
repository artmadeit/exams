package org.ouracademy.exams.auth;

import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserAccount, String> {
    @Transactional(readOnly = true)
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    public Optional<UserAccount> findByName(String name);
    
}