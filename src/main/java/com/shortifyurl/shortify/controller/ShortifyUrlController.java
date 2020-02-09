package com.shortifyurl.shortify.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortifyurl.shortify.domain.UrlMapping;
import com.shortifyurl.shortify.service.UrlMappingService;

@Controller
public class ShortifyUrlController {

	private static final Logger LOG = LoggerFactory.getLogger(ShortifyUrlController.class);

	@Autowired
	UrlMappingService urlMappingService;
	
	@GetMapping("/index")
	public String defectDetails() {
	    return "index";
	}

	@PostMapping("/create")
	public String createShortUrl(@RequestParam String longUrl, Model model) {
		LOG.info("Request recieved for createShortUrl with param: " + longUrl);
		// handle edge cases
		model.addAttribute("urlMapping", urlMappingService.saveUrlMapping(longUrl));
		return "response";
	}

	@GetMapping("/{shortUrl}")
	public void getLongUrl(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) throws IOException {
		LOG.info("Request recieved for getLongUrl for short url: " + shortUrl);

		String longUrl = urlMappingService.getLongUrlByShortUrl(shortUrl);
		// it doesn't redirect if url doesn't contain http://www. as prefix
		if (longUrl == null) {
			LOG.debug("Long URL cannot be found for short URL: " + shortUrl);
		}
		httpServletResponse.setHeader("Location", longUrl);
		httpServletResponse.setStatus(302);
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}

}
