## 什么时候使用 MVC:view-controller 标签
平时我们访问每一个页面都需要通过Controller，但是我们有时候不想要通过controller，只想直接地跳转到目标页面，这时候就可以使用mvc:view-controller标签

## 怎么使用
在springmvc配置文件中配置:
```
<mvc:view-controller path="/hello" view-name="jsp_hello"></mvc:view-controller>
```
path="/hello" 就是你访问的路径（相当于RequestMapping("/hello")） 
view-name="jsp_hello"是你所要的视图（如hello.jsp,相当于return "jsp_hello"）

相当于以下代码
```
@RequestMapping(value="/hello") 
public String hello()
{ 
 System.out.println("hello"); 
 return "jsp_hello"; 
}
```

## 可能遇到的问题
如果按照以上的配置失败了，请检查你的配置文件中最上方是否有
```
xmlns:mvc="http://www.springframework.org/schema/mvc"
```
以及
```
xsi:schemaLocation="http://www.springframework.org/schema/mvc 
http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd"
```
配置成功之后，你可能会发现你访问其他的页面会失败
```
@RequestMapping(value="/hello") 
public String hello(){ 
 System.out.println("hello");
 return "hello";
} 

@RequestMapping(value="/index") 
public String index(){
 return "index";
}
```
如以上代码所示，当你访问/hello时可以正常访问，访问/index时可能会报错，不要慌，在springmvc配置文件中加上<mvc:annotation-driven/>即可
