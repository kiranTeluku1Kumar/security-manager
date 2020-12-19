package com.food.securitymanager.ctrls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthControllers {

	@GetMapping
	public String welcomeMyMessage() {
		return "welcome to kiran practice session";
	}
}
