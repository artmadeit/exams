package org.ouracademy.exams;

import org.ouracademy.exams.auth.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
