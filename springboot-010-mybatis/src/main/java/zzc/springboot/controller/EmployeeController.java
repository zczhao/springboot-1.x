package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zzc.springboot.bean.Employee;
import zzc.springboot.mapper.EmployeeMapper;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeMapper.getEmployeeById(id);
    }


}
