package zzc.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import zzc.springboot.bean.Employee;

@Mapper
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    int insertEmployeeById(Employee employee);
}
