package com.shortifyurl.shortify.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortifyurl.shortify.domain.UrlMapping;
import com.shortifyurl.shortify.domain.UrlMappingRepository;

@RestController
public class ShortifyUrlController {

	UrlMappingRepository urlMappingRepository;

	public ShortifyUrlController(UrlMappingRepository urlMappingRepository) {
		super();
		this.urlMappingRepository = urlMappingRepository;
	}

	@PostMapping("/")
	@ResponseBody
	public String shortifyUrl(@RequestBody String longUrl) {
		String shortUrl = shortenMyUrl(longUrl);
		UrlMapping urlMapping = new UrlMapping();
		urlMapping.setShort_url(shortUrl);
		urlMapping.setLong_url(longUrl);
		urlMapping.setCreated_date(new Date());
		urlMapping.setUpdated_date(new Date());
		urlMappingRepository.save(urlMapping);
		return shortUrl;
	}

	@GetMapping("/{shortUrl}")
	public String getLongUrl(@PathVariable String shortUrl) {
		return urlMappingRepository.findByShortUrl(shortUrl).getLong_url();
	}

	private String shortenMyUrl(String longUrl) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);
		return generatedString;
	}
}
