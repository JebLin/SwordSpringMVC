package indi.sword.springmvc._03crud.handlers;

import indi.sword.springmvc._03crud.dao.DepartmentDao;
import indi.sword.springmvc._03crud.dao.EmployeeDao;
import indi.sword.springmvc._03crud.entites.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/*

对属性对象的输入/输出进行格式化，从其本质上讲依然属于 “类型转换” 的范畴。
Spring 在格式化模块中定义了一个实现ConversionService 接口的
FormattingConversionService 实现类，该实现类扩展了 GenericConversionService，因此它既具有类型转换的功能，又具有格式化的功能。
FormattingConversionService 拥有一个FormattingConversionServiceFactroyBean 工厂类，后者用于在 Spring 上下文中构造前者

日期格式化:
	@DateTimeFormat 注解可对java.util.Date、java.util.Calendar、java.long.Long 时间类型进行标注：
	- pattern 属性：类型为字符串。指定解析/格式化字段数据的模式，如：”yyyy-MM-dd hh:mm:ss”
	- iso 属性：类型为 DateTimeFormat.ISO。指定解析/格式化字段数据的ISO模式，
	   包括四种：ISO.NONE（不使用） -- 默认、ISO.DATE(yyyy-MM-dd) 、ISO.TIME(hh:mm:ss.SSSZ)、ISO.DATE_TIME(yyyy-MM-dd hh:mm:ss.SSSZ)
	- style 属性：字符串类型。通过样式指定日期时间的格式，由两位字符组成，
	   第一位表示日期的格式，第二位表示时间的格式：S：短日期/时间格式、M：中日期/时间格式、L：长日期/时间格式、F：完整日期/时间格式、
	-：忽略日期或时间格式

数值格式化
	@NumberFormat 可对类似数字类型的属性进行标注，它拥有两个互斥的属性：
	- 样式类型，包括三种：Style.NUMBER（正常数字类型）、Style.CURRENCY（货币类型）、 Style.PERCENT（百分数类型）
	- pattern：类型为 String，自定义样式，如patter="#,###"；

 */
@Controller
@RequestMapping("/crud")
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@RequestMapping("/hello")
	public String hello(){
		System.out.println("hello world");
		return "jsp_helloworld/success";
	}

	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
							Map<String, Object> map){
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}

	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);

		return "redirect:/crud/emps";
	}

	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		System.out.println("input ...");
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "jsp_crud/input";
	}

	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		System.out.println("delete ...");
		employeeDao.delete(id);
		return "redirect:/crud/emps";
	}

	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String save(@Valid Employee employee, Errors result,
					   Map<String, Object> map){
		System.out.println("save: " + employee);

		if(result.getErrorCount() > 0){
			System.out.println("出错了!");

			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}

			//若验证出错, 则转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			return "jsp_crud/input";
		}

		employeeDao.save(employee);
		return "redirect:/crud/emps";
	}

	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "jsp_crud/input";
	}

	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		return "jsp_crud/list";
	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("lastName");
//	}

}
