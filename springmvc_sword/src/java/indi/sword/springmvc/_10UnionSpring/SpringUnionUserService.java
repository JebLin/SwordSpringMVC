package indi.sword.springmvc._10UnionSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringUnionUserService {

	/*
		SpringMVC 的 IOC 容器中的 bean 可以来引用 Spring IOC 容器中的 bean.
		返回来呢 ? 反之则不行. Spring IOC 容器中的 bean 却不能来引用 SpringMVC IOC 容器中的 bean!

		Spring 比 Spring MVC 先出来，向下兼容， Controller 可以引用 Service,但是Service不能调用Controller。
	*/
//	@Autowired
//	private SpringUnionHelloWorld helloWorld; // 这个地方乱引用，会出异常
	
	public SpringUnionUserService() {
		System.out.println("UserService Constructor...");
	}
	
}
