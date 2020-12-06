package com.iucse.passnet.recruitment.adapter.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
@Tag(name = "Home API")
public class HomeRestController {

	@GetMapping(value = "")
	public String getHomePage() {
		return "Hello";
	}
}
