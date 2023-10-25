package com.onlinestore.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //SQl = SELECT * FROM users WHERE uuid=${uuid}
    //JPQL = Jakarta or Java Persistent Query Language
    //JPQL is Spring Data feature for Dao implementation with SQl-concept
    //JPQL is based-on Entity

    //There are 2 ways for binding parameter
    // 1. `:` Named Binding Parameter
    // 2. `?` Positional Binding Parameter
    @Query("SELECT u FROM User AS u WHERE u.uuid=?1")//JPQL
    Optional<User> selectUserByUuid(String uuid);


    //Native Query
    @Query(value = "SELECT * FROM users WHERE uuid=?1 AND is_deleted=?2", nativeQuery = true)
    Optional<User> selectUserByUuidAndIsDeleted(String uuid,Boolean isDeleted);


    //Boolean existsByUuid(String uuid)//Derived Query method
    @Query("SELECT EXISTS (SELECT u from User as u WHERE u.uuid=?1)")
    boolean checkUserByUuid(String uuid);


    @Modifying
    @Query("UPDATE User AS u Set u.isDeleted = :isDeleted WHERE u.uuid = :uuid")
    void updateIsDeletedStatusByUuid(String uuid, Boolean isDeleted);

    //Derived query method
    Boolean existsByUsernameAndIsDeletedFalse(String username);

    Boolean existsByEmailAndIsDeletedFalse(String email);
}
