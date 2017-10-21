package indi.sword.springmvc._04Converter;

import indi.sword.springmvc._03crud.dao.EmployeeDao;
import indi.sword.springmvc._03crud.entites.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 自定义ConversionService，使得前端能够传入指定格式的字符串，后端直接转换成JAVA对象
	 1.在input.jsp定义入口
	 2.编写EmployeeConverter，规定字符串输入格式
	 3.springmvc.xml配置
 		<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>
		<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		 	<property name="converters">
		 	<set>
		 		<ref bean="employeeConverter"></ref>
		 	</set>
		 	</property>
		 </bean>
 */
@Controller
@RequestMapping("/converter")
public class ConverterTest {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping("/testConversionServiceConverer")
	public String testConverter(@RequestParam("employee") Employee employee){
		System.out.println("save: " + employee);
		employeeDao.save(employee);
		return "redirect:/crud/emps";
	}

}
