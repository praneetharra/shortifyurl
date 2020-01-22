package com.shortifyurl.shortify.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortifyUrlController {

	@PostMapping("/")
	@ResponseBody
	public String shortifyUrl(@RequestBody String longUrl) {
		String shortUrl = shortenMyUrl(longUrl);
		return shortUrl;
	}
	
	private String shortenMyUrl(String longUrl) {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    System.out.println(generatedString);
		return generatedString;
	}
}
