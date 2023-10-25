package com.onlinestore.api.user;

import com.onlinestore.api.user.web.NewUserDto;
import com.onlinestore.api.user.web.UpdateUserDto;
import com.onlinestore.api.user.web.UserDto;

import java.util.List;

public interface UserService {

    /**
     * This method is used to create a new user resource into a database
     *
     * @param newUserDto is request data from a client
     */
    void createNewUser(NewUserDto newUserDto);

    /**
     * This method is used to update old a user resource in the database based on its UUID.
     *
     * @param uuid          the UUID of the user to be updated
     * @param updateUserDto the data containing the updated user information
     */
    void updateByUuid(String uuid, UpdateUserDto updateUserDto);

    /**
     * This method is used to retrieve a user resource in the database base on its UUID
     *
     * @param uuid the UUID of the user to be retrieved
     * @return a UserDto object representing the user
     */
    UserDto findByUuid(String uuid);

    /**
     * This method is used to delete a user resource in the database based on its UUID (Permanently)
     *
     * @param uuid the UUID of the user to be deleted
     */
    void deleteByUuid(String uuid);

    /**
     * Updates the "isDeleted" status of a user resource in the database based on its UUID.
     *
     * @param uuid      the UUID of the user to be updated
     * @param isDeleted the new "isDeleted" status for the user
     */
    void updateIsDeletedByUuid(String uuid, Boolean isDeleted);

    /**
     * This method is used to retrieve a list of users in the database
     *
     * @return a list of UserDto object representing the user
     */
    List<UserDto> findAll();
}
