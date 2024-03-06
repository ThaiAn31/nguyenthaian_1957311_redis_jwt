package com.example.redis.entity;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private int age;
	private String position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Employee(String id, String name, int age, String position) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.position = position;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
