package com.onlinestore.api.auth;

import com.onlinestore.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long> {

    @Modifying
    @Query("UPDATE User as u SET u.verifiedCode=?2 WHERE u.username=?1")
    void updateVerifiedCode(String username,String verifiedCode);
    Optional<User> findByEmailAndVerifiedCodeAndIsDeletedFalse(String email, String verifiedCode);
}
