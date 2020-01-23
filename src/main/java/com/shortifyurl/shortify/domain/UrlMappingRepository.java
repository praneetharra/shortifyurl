package com.shortifyurl.shortify.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends CrudRepository<UrlMapping, Integer> {
	@Query("SELECT * FROM url_mapping WHERE short_url = :shortUrl")
	UrlMapping findByShortUrl(@Param("shortUrl")String shortUrl);
}
