package com.getHired.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String username;
	private String email;
	private String password;
	private int age;
    private String gender;
}
