package com.impetus.userregistration.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.impetus.userregistration.process.UserProcess;
import com.impetus.userregistration.domain.UserInfo;
import com.impetus.userregistration.service.Response;

@RestController
@RequestMapping("/user")
public class UserService {

	@Autowired
	UserProcess userProcess;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response createUser(@RequestBody UserInfo userInfo) {

		return userProcess.registerUser(userInfo);
	}

}