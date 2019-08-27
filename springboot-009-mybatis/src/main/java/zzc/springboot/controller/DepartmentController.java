package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zzc.springboot.bean.Department;
import zzc.springboot.mapper.DepartmentMapper;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department) {
        departmentMapper.insertDepartment(department);
        return department;
    }

}
