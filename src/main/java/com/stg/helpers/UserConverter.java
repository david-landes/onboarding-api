package com.stg.helpers;

import com.stg.dtos.NewUser;
import com.stg.models.Position;
import com.stg.models.Practice;
import com.stg.models.User;

public class UserConverter {

    public static User convertToUser(NewUser newUser, Position position, Practice practice) {
	User user = new User();

	user.setEmail(newUser.getEmail());
	user.setFirstName(newUser.getFirstName());
	user.setLastName(newUser.getLastName());
	user.setPosition(position);
	user.setPractice(practice);

	return user;
    }
}
