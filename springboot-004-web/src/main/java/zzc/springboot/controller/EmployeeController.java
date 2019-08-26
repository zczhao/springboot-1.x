package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import zzc.springboot.dao.DepartmentDao;
import zzc.springboot.dao.EmployeeDao;
import zzc.springboot.entity.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	/**
	 * 查询所有员工并返回列表页面
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/emps")
	public String list(Model model) {
		// 放在请求域中
		model.addAttribute("emps", employeeDao.getEmployee());
		return "emp/list";
	}

	/**
	 * 跳转到员工添加页
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		model.addAttribute("depts", departmentDao.getDepartments());
		return "emp/add";
	}

	/**
	 * 员工添加
	 *
	 * @param employee SpringMVC自动将请求参数和入参对象的属性进行一一绑定，要求请求参数的名字和JavaBean入参对象的属性名是一样的
	 * @return
	 */
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		System.out.println("employee = " + employee);
		employeeDao.save(employee);
		// 跳转到员工列表
		// redirect：表示重定向到一个地址 /：代表当前项目路径
		// forward：表示发到一个一址
		return "redirect:/emps";
	}


	/**
	 * 跳转到修改页面，查出当前员工，在页面回显
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("emp", employeeDao.get(id));
		model.addAttribute("depts", departmentDao.getDepartments());
		return "emp/add";
	}


	/**
	 * 员工修改
	 * @param employee
	 * @return
	 */
	@PutMapping("/emp")
	public String updateEmp(Employee employee) {
		System.out.println("employee = " + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@DeleteMapping("/emp/{id}")
	public String deleteEmp(@PathVariable("id") Integer id){
		employeeDao.deleteEmployee(id);
		return "redirect:/emps";
	}
}
