package com.iucse.passnet.recruitment.adapter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home API")
@CrossOrigin(value = "*")
@RequestMapping(value = "/")
public class HomeRestController {

	@GetMapping(value = "")
	public String getHomePage() {
		return "Welcome to Passnet Recruitment service";
	}
}
