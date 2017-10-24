package indi.sword.springmvc._10UnionSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringUnionHelloWorld {

	@Autowired
	private SpringUnionUserService userService;
	
	public SpringUnionHelloWorld() {
		System.out.println("HelloWorld Constructor...");
	}
	
	@RequestMapping("/SpringUnionHelloWorld")
	public String hello(){
		System.out.println("success");
		System.out.println(userService);
		return "success";
	}
	
}
