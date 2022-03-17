package com.dalc.one.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class UserDTO{
	private String id;
	private String password;
	
	/* JavaBeans Properties */
}
