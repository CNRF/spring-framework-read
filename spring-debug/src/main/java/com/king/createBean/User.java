package com.king.createBean;

/**
 * @author wmx
 * @version 1.0
 * @ClassName User
 * @Description
 * @date 2021/9/28 9:39
 */
public class User {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				'}';
	}
}
