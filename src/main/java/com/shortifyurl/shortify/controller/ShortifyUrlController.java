package com.shortifyurl.shortify.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortifyurl.shortify.domain.UrlMapping;
import com.shortifyurl.shortify.service.UrlMappingService;

@RestController
public class ShortifyUrlController {

	@Autowired
	UrlMappingService urlMappingService;

	@PostMapping("/create")
	@ResponseBody
	public UrlMapping shortifyUrl(@RequestBody String longUrl) {
		// handle edge cases
		return urlMappingService.saveUrlMapping(longUrl);
	}

	@GetMapping("/{shortUrl}")
	public void getLongUrl(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) throws IOException {
		// it doesn't redirect if url doesn't contain http://www. as prefix
		httpServletResponse.setHeader("Location", urlMappingService.getLongUrlByShortUrl(shortUrl));
		httpServletResponse.setStatus(302);
	}

}
