package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.redis.entity.Employee;
import com.example.redis.entity.User;
import com.example.redis.service.RedisService;
import com.example.redis.jwt.JwtUtils;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class RedisController {
    @Autowired
    private RedisService redisService;
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        redisService.saveEmployee(employee);
        return ResponseEntity.ok("Employee added successfully.");
    }

    @GetMapping
    public ResponseEntity<Map<String, Employee>> getAllEmployee() {
        Map<String, Employee> employees = redisService.findAllEmployee();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = redisService.findEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        Employee existingEmployee = redisService.findEmployeeById(id);
        if (existingEmployee != null) {
            employee.setId(id);
            redisService.updateEmployee(employee);
            return ResponseEntity.ok("Employee updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        redisService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}