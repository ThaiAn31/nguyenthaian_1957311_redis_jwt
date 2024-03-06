package com.example.redis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.redis.entity.Employee;



public interface RedisRepository extends CrudRepository<Employee, Long> {
	List<Employee> getAllStudents();

	Optional<Employee> getStudentById(String id);

}
