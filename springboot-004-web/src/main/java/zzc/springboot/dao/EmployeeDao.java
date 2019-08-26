package zzc.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zzc.springboot.entity.Department;
import zzc.springboot.entity.Employee;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
	private static Map<Integer, Employee> empolyees = null;

	@Autowired
	private DepartmentDao departmentDao;

	static {
		empolyees = new HashMap<Integer, Employee>();
		empolyees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA"), new Date()));
		empolyees.put(1002, new Employee(1002, "E-BB", "aa@163.com", 0, new Department(102, "D-BB"), new Date()));
		empolyees.put(1003, new Employee(1003, "E-CC", "aa@163.com", 1, new Department(103, "D-CC"), new Date()));
		empolyees.put(1004, new Employee(1004, "E-DD", "aa@163.com", 0, new Department(104, "D-DD"), new Date()));
		empolyees.put(1005, new Employee(1005, "E-EE", "aa@163.com", 1, new Department(105, "D-EE"), new Date()));
	}

	private static Integer initId = 1006;

	public void save(Employee empolyee) {
		if (empolyee.getId() == null) {
			empolyee.setId(initId++);
		}
		empolyee.setDepartment(departmentDao.getDepartment(empolyee.getDepartment().getId()));
		empolyees.put(empolyee.getId(), empolyee);
	}

	public Collection<Employee> getEmployee() {
		return empolyees.values();
	}

	public Employee get(Integer id){
		return empolyees.get(id);
	}

	public void deleteEmployee(Integer id){
		empolyees.remove(id);
	}
}
