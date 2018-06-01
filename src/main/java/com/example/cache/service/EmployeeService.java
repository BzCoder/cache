package com.example.cache.service;

import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author BaoZhou
 * @date 2018/5/29
 */
@CacheConfig(cacheNames = "cacheNames")
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Cacheable(cacheNames = {"empCache"}, key = "#id")
    public Employee getEmp(Integer id) {
        System.out.println("查询");
        return mapper.getEmpById(id);
    }

    @CachePut(cacheNames = {"empCache"},key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("更新");
        mapper.updateEmp(employee);
        return employee;
    }

    @CacheEvict(cacheNames ={"empCache"},key = "#id")
    public  String deleteEmp(Integer id) {
        System.out.println("更新");
        mapper.deleteEmpById(id);
        return "删除"+id;
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "empCache",key = "#lastName")
            },
            put =
                    {
                            @CachePut(value = "empCache",key = "#lastName"),
                            @CachePut(value = "empCache",key = "#result.id"),
                            @CachePut(value = "empCache",key = "#result.email")
                    }
    )
    public  Employee getEmpByLastName(String lastName)
    {
        return mapper.getEmpByLastName(lastName);
    }

}
