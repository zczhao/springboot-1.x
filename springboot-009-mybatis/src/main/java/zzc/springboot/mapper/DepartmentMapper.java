package zzc.springboot.mapper;

import org.apache.ibatis.annotations.*;
import zzc.springboot.bean.Department;

/**
 * @Mapper 指定这是一个操作数据库的mapper
 */
//@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDepartmentById(Integer id);

    @Delete("DELETE FROM department WHERE id=#{id}")
    int deleteDepartmentById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department(dept_name) VALUES(#{deptName})")
    int insertDepartment(Department department);

    @Update("UPDATE department dept_name=#{deptName} WHERE id=#{id}")
    int updateDepartment(Department department);

}
