package indi.sword.springmvc._04Converter;

import indi.sword.springmvc._03crud.entites.Department;
import indi.sword.springmvc._03crud.entites.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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
@Component
public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		System.out.println("convert ...");
		if(source != null){
			String [] vals = source.split("-");
			//GG-gg@atguigu.com-0-105
			if(vals != null && vals.length == 4){
				String lastName = vals[0];
				String email = vals[1];
				Integer gender = Integer.parseInt(vals[2]);
				Department department = new Department();
				department.setId(Integer.parseInt(vals[3]));
				
				Employee employee = new Employee(null, lastName, email, gender, department);
				System.out.println(source + "--convert--" + employee);
				return employee;
			}
		}
		return null;
	}

}
