package com.dalc.one.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dalc.one.domain.Place;
import com.dalc.one.domain.PlaceKeyword;
import com.dalc.one.domain.User;
import com.dalc.one.service.LehgoFacade;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class AIController{
	private LehgoFacade lehgo;

	@Autowired
	public void setFacade(LehgoFacade lehgo) {
		this.lehgo = lehgo;
	}
	
	/* AI 여행지 리스트*/
	@ResponseBody
	@PostMapping
	public ResponseEntity<List<PlaceKeyword>> getUserPlaceList(HttpServletRequest request, @Valid @RequestBody User user) throws Exception {
		String authorizationHeader = request.getHeader("authorization");
		int keyword = 1;
		
		if (authorizationHeader != null) { // 로그인을 한 상태면
			keyword = lehgo.getUserKeyword(user).getKeywordId();
		}
		return ResponseEntity.ok(lehgo.getAiPlaceList(keyword));
	}

	@ResponseBody
	@PostMapping("category")
	public ResponseEntity<List<Place>> getPlaceListbyCategory
		(HttpServletRequest request, @RequestParam("category") String category, @Valid @RequestBody User user ) throws Exception {
		String authorizationHeader = request.getHeader("authorization");
		int keyword = 1;
		
		if (authorizationHeader != null) { // 로그인을 한 상태면
			keyword = lehgo.getUserKeyword(user).getKeywordId();
		}
		System.out.println(keyword + " " + category);
		List<PlaceKeyword> AIList = lehgo.getAiPlaceList(keyword);
		List<Place> result = new ArrayList<Place>();
		for(int i = 0; i < AIList.size(); i++) {
			if((AIList.get(i).getPlace().getAttraction() != null && AIList.get(i).getPlace().getAttraction().getCategory().equals(category)) ||
					(AIList.get(i).getPlace().getRestaurant() != null && AIList.get(i).getPlace().getRestaurant().getCategory().equals(category))) {
				result.add(AIList.get(i).getPlace());
			}
		}
		return ResponseEntity.ok(result);
	}
}