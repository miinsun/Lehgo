package com.dalc.one.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dalc.one.domain.PlaceKeyword;

public interface PlaceKeywordRepository extends CrudRepository<PlaceKeyword, Long>{
	
	@Query(value = "SELECT * FROM place_keyword pk "+ 
			"ORDER BY (CASE WHEN pk.keyword_id = :keyword THEN 1 ELSE 2 END)", nativeQuery = true)
	List<PlaceKeyword> findPlaceKeywordBykeyword(@Param(value = "keyword") int keyword);
	
	@Query(value = "SELECT * FROM PLACE_KEYWORD pk, RESTAURANT r " + 
	"WHERE pk.PLACE_ID = r.PLACE_ID and r.CATEGORY = :category and pk.KEYWORD_ID = :keyword", nativeQuery = true)
	List<PlaceKeyword> findPlaceKeywordList(@Param(value = "keyword") int keywordId, @Param(value = "category") String category);
	
}