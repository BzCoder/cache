package com.example.cache.controller;

import com.example.cache.bean.Employee;
import com.example.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BaoZhou
 * @date 2018/5/29
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return service.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee) {
        service.updateEmp(employee);
        return employee;
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        return service.deleteEmp(id);
    }
}
