package com.example.redis.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.redis.entity.Employee;
import com.example.redis.entity.User;
import com.example.redis.jwt.JwtUtils;

import java.util.Map;

@Service
public class RedisService {
    private static final String HASH_KEY_EMPLOYEE = "Employee";
    private static final String HASH_KEY_USER = "User";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private HashOperations<Object, String, Employee> hashOperationsEmployee;
    private HashOperations<Object, String, User> hashOperationsUser;

    @Autowired
    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperationsEmployee = redisTemplate.opsForHash();
        this.hashOperationsUser = redisTemplate.opsForHash();
    }

    // Phương thức lưu Employee
    public void saveEmployee(Employee employee) {
        hashOperationsEmployee.put(HASH_KEY_EMPLOYEE, employee.getId(), employee);
    }

    // Phương thức lấy tất cả Employee
    public Map<String, Employee> findAllEmployee() {
        return hashOperationsEmployee.entries(HASH_KEY_EMPLOYEE);
    }

    // Phương thức lấy Employee theo id
    public Employee findEmployeeById(String id) {
        return hashOperationsEmployee.get(HASH_KEY_EMPLOYEE, id);
    }

    // Phương thức cập nhật Employee
    public void updateEmployee(Employee employee) {
        saveEmployee(employee);
    }

    // Phương thức xoá Employee
    public void deleteEmployee(String id) {
        hashOperationsEmployee.delete(HASH_KEY_EMPLOYEE, id);
    }

    // Phương thức lưu User
    public void saveUser(User user) {
        hashOperationsUser.put(HASH_KEY_USER, user.getIdUser(), user);
    }

    // Phương thức lấy tất cả User
    public Map<String, User> findAllUser() {
        return hashOperationsUser.entries(HASH_KEY_USER);
    }

    // Phương thức lấy User theo id
    public User findUserById(String idUser) {
        return hashOperationsUser.get(HASH_KEY_USER, idUser);
    }

    // Phương thức lấy User theo userName
    public User findByUsername(String userName) {
        Map<String, User> users = findAllUser();
        for (User user : users.values()) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    // Phương thức đăng ký người dùng mới
    public void register(User user) {
        saveUser(user);
    }

    // Phương thức xác thực thông tin đăng nhập và tạo/token token
    public String login(String userName, String password) {
        User user = findByUsername(userName);
        if (user != null && user.getPassword().equals(password)) {
            // Xác thực thành công, tạo và trả về token
            return JwtUtils.generateToken(user.getIdUser());
        } else {
            // Xác thực không thành công, trả về null hoặc throw exception
            return null;
        }
    }
}
