package com.example.redis.entity;

public class User {
	private String idUser;
	private String userName;
	private String password;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String idUser, String userName, String password) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", userName=" + userName + ", password=" + password + "]";
	}

}
