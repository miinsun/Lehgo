package com.dalc.one.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
	private String id;
	private String password;
	private String nickname;
	
	public UserDTO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	
	/* JavaBeans Properties */
}
