package com.shortifyurl.shortify.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.shortifyurl.shortify.domain.UrlMapping;
import com.shortifyurl.shortify.domain.UrlMappingRepository;

@Service
public class UrlMappingService {

	UrlMappingRepository urlMappingRepository;

	public UrlMappingService(UrlMappingRepository urlMappingRepository) {
		super();
		this.urlMappingRepository = urlMappingRepository;
	}

	public UrlMapping saveUrlMapping(String longUrl) {
		String shortUrl = shortenMyUrl(longUrl);
		UrlMapping urlMapping = new UrlMapping();
		urlMapping.setShort_url(shortUrl);
		urlMapping.setLong_url(longUrl);
		urlMapping.setCreated_date(new Date());
		urlMapping.setUpdated_date(new Date());
		return urlMappingRepository.save(urlMapping);
	}

	public String getLongUrlByShortUrl(String shortUrl) {
		if (shortUrl == null || shortUrl.isEmpty()) {
			return null;
		}

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
