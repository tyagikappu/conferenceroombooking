package com.impetus.userregistration.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.impetus.DBManager;
import com.impetus.userregistration.domain.User;
import com.impetus.userregistration.domain.UserInfo;
import com.impetus.userregistration.repositories.UserRepository;
import com.impetus.userregistration.service.Response;

@Component
public class UserProcess {

	@Autowired
	UserRepository userRepository;
	@Autowired
	DBManager dBManager;

	public Response registerUser(UserInfo userInfo) {
		Response response = new Response();

		List<User> userList = userRepository.findUserByEmail(userInfo
				.getEmail());

		if (userList != null && userList.size() > 0) {
			response.setMessage("User Already exists with Email : "
					+ userInfo.getEmail());
		} else {
			User user = new User(null, userInfo.getFirstName(),
					userInfo.getLastName(), userInfo.getEmail());
			userRepository.save(user);
			response.setMessage("User Created");
		}

		response.setStatus("OK");

		return response;

	}
}
