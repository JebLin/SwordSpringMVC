# SwordSpringMVC


### 内容概要
>1.SpringMVC 概述
2.SpringMVC 的 HelloWorld
3.使用 @RequestMapping 映射请求
4.映射请求参数 & 请求头
5.处理模型数据
6.视图和视图解析器
7.RESTful CRUD
8.SpringMVC 表单标签 &处理静态资源
9.数据转换 & 数据格式化 & 数据校验
10.处理 JSON：使用 HttpMessageConverter
11.国际化
12.文件的上传
13.使用拦截器
14.异常处理
15.SpringMVC 运行流程
16.在 Spring 的环境下使用SpringMVC
17.SpringMVC 对比 Struts2

#### 1.SpringMVC 概述
```
Spring 为展现层提供的基于 MVC 设计理念的优秀的Web 框架，是目前最主流的 MVC 框架之一
Spring3.0 后全面超越 Struts2，成为最优秀的 MVC 框架
Spring MVC 通过一套 MVC 注解，让 POJO 成为处理请求的控制器，而无须实现任何接口。
支持 REST 风格的 URL 请求
采用了松散耦合可插拔组件结构，比其他 MVC 框架更具扩展性和灵活性
```
#### 2.SpringMVC 的 HelloWorld
```
package indi.sword.springmvc.helloworld.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
   /**
    * 1. 使用 @RequestMapping 注解来映射请求的 URL
    * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于 InternalResourceViewResolver 视图解析器, 会做如下的解析:
    * 通过 prefix + returnVal + 后缀 这样的方式得到实际的物理视图, 然会做转发操作
    *
    * /WEB-INF/views/success.jsp
    *
    * @return
    */
   @RequestMapping("/helloworld")
   public String hello(){
      System.out.println("hello world");
      return "helloworld/success";
   }
}
```
web.xml
```
<!-- 配置 DispatcherServlet,也即Spring MVC配置 -->
<servlet>
   <servlet-name>springmvc</servlet-name>
   <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   <!-- 配置 DispatcherServlet 的一个初始化参数: 配置 SpringMVC 配置文件的位置和名称 -->
   <!--
      实际上也可以不通过 contextConfigLocation 来配置 SpringMVC 的配置文件, 而使用默认的.
      默认的配置文件为: /WEB-INF/<servlet-name>-servlet.xml
   -->
   <!--
   <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
   </init-param>
   -->
   <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
   <servlet-name>springmvc</servlet-name>
   <url-pattern>/</url-pattern>
</servlet-mapping>
```
WEB-INF/views/helloworld/success.jsp
```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
    Success
</body>
</html>
```
springmvc-servlet.xml
```
<!-- 配置自定扫描的包 -->
<context:component-scan base-package="indi.sword.springmvc"></context:component-scan>

<!-- 配置视图解析器: 如何把 handler 方法返回值解析为实际的物理视图 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
   <property name="prefix" value="/WEB-INF/views/"></property>
   <property name="suffix" value=".jsp"></property>
</bean>

<!-- 在实际开发中通常都需配置 mvc:annotation-driven 标签 -->
<mvc:annotation-driven></mvc:annotation-driven>
```

#### 3.使用 @RequestMapping 映射请求
```
Spring MVC 使用 @RequestMapping 注解为控制器指定可以处理哪些 URL 请求
在控制器的类定义及方法定义处都可标注
@RequestMapping
- 类定义处：提供初步的请求映射信息。相对于 WEB 应用的根目录
- 方法处：提供进一步的细分映射信息。相对于类定义处的 URL。若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于WEB 应用的根目录
DispatcherServlet 截获请求后，就通过控制器上
@RequestMapping 提供的映射信息确定请求所对应的处理方法。

```

#### 4.映射请求参数、请求方法或请求头
```
@RequestMapping 除了可以使用请求 URL 映射请求外，还可以使用请求方法、请求参数及请求头映射请求
@RequestMapping 的 value、method、params 及 heads分别表示请求 URL、请求方法、请求参数及请求头的映射条件，他们之间是与的关系，联合使用多个条件可让请求映射
更加精确化。
params 和 headers支持简单的表达式：
- param1: 表示请求必须包含名为 param1 的请求参数
- !param1: 表示请求不能包含名为 param1 的请求参数
- param1 != value1: 表示请求包含名为 param1 的请求参数，但其值不能为 value1
- {“param1=value1”, “param2”}: 请求必须包含名为 param1 和param2 的两个请求参数，且 param1 参数的值必须为 value1
使用 @RequestMapping 映射请求
   Ant 风格资源地址支持 3 种匹配符：
   - ?：匹配文件名中的一个字符
   - *：匹配文件名中的任意字符
   - **：** 匹配多层路径
@RequestMapping 还支持 Ant 风格的 URL：
- /user/aaa/createUser、/user/bbb/createUser 等 URL
- /user/**/createUser: 匹配 /user/createUser、/user/aaa/bbb/createUser 等 URL
- /user/createUser??: 匹配  /user/createUseraa、/user/createUserbb 等 URL


```

 



####  REST
```
REST：即 Representational State Transfer。（资源）表现层状态转化。是目前最流行的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用
资源（Resources）：网络上的一个实体，或者说是网络上的一个具体信息。它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。可以用一个URI（统一资源定位符）指向它，每种资源对应一个特定的 URI 。要获取这个资源，访问它的URI就可以，因此 URI 即为每一个资源的独一无二的识别符。
表现层（Representation）：把资源具体呈现出来的形式，叫做它的表现层（Representation）。比如，文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现，甚至可以采用二进制格式。
状态转化（State Transfer）：每发出一个请求，就代表了客户端和服务器的一次交互过程。HTTP协议，是一个无状态协议，即所有的状态都保存在服务器端。因此，如果客户端想要操作服务器，必须通过某种手段，让服务器端发生“状态转化”（State Transfer）。而这种转化是建立在表现层之上的，所以就是 “表现层状态转化”。具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。

示例：
- /order/1 HTTP GET ：得到 id = 1 的 order 
- /order/1 HTTP DELETE：删除 id = 1的 order 
- /order/1 HTTP PUT：更新id = 1的 order 
- /order HTTP POST：新增 order 
HiddenHttpMethodFilter：浏览器 form 表单只支持 GET与 POST 请求，而DELETE、PUT 等 method 并不支持，Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的 http 方法，使得支持 GET、POST、PUT 与DELETE 请求。
 

```

#### 请求处理方法签名
```
Spring MVC 通过分析处理方法的签名，将 HTTP 请求信息绑定到处理方法的相应入参中。
Spring MVC 对控制器处理方法签名的限制是很宽松的，几乎可以按喜欢的任何方式对方法进行签名。
必要时可以对方法及方法入参标注相应的注解（@PathVariable、@RequestParam、@RequestHeader 等）、Spring MVC 框架会将 HTTP 请求的信息绑定到相应的方法入参中，并根据方法的返回值类型做出相应的后续处理。

   通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：
   URL 中的 {xxx} 占位符可以通过@PathVariable("xxx") 绑定到操作方法的入参中。
   带占位符的 URL 是 Spring3.0 新增的功能，该功能在SpringMVC 向 REST 目标挺进发展过程中具有里程碑的意义.
      @RequestMapping("/delete/{id}")
      public String delete(@PathVariable("id") Integer id){
         UserDao.delete(id);
         return "redirect:/user/list.action";
      }

   使用 @RequestParam 绑定请求参数值
      @RequestMapping("handle5")
      public String handle5(@RequestParam(value="userName",required=true)String userName,@RequestParam("age")int age){
         return "success";
      }

   使用 @RequestHeader 绑定请求报头的属性值
   请求头包含了若干个属性，服务器可据此获知客户端的信息，通过 @RequestHeader 即可将请求头中的属性值绑定到处理方法的入参中
      @RequestMapping("handle7")
      public String handle7(@RequestHeader("Accept-Encoding")String encoding,@RequestHeader("Keep-Alive")long keepAlive){
         return "success";
      }


   使用 @CookieValue 绑定请求中的 Cookie 值
      @RequestMapping("/handle6")
      public String handle6(@CookieValue(value="sessionId",required=false)String sessionId,@RequestParam("age")int age){
         return "success";
      }


```

####  处理模型数据
```
处理模型数据
Spring MVC 提供了以下几种途径输出模型数据：
- ModelAndView: 处理方法返回值类型为 ModelAndView 时, 方法体即可通过该对象添加模型数据
- Map 及 Model: 入参为org.springframework.ui.Model、org.springframework.ui.ModelMap 或 java.uti.Map 时，处理方法返回时，Map 中的数据会自动添加到模型中。
- @SessionAttributes: 将模型中的某个属性暂存到HttpSession 中，以便多个请求之间可以共享这个属性
- @ModelAttribute: 方法入参标注该注解后, 入参的对象就会放到数据模型中

ModelAndView：
      控制器处理方法的返回值如果为 ModelAndView, 则其既包含视图信息，也包含模型数据信息。
      添加模型数据 :
      - MoelAndView addObject(String attributeName, Object attributeValue)
      - ModelAndView addAllObject(Map<String, ?> modelMap)

      设置视图 :
      - void setView(View view)
      - void setViewName(String viewName)

Map 及 Model
      Spring MVC 在内部使用了一个org.springframework.ui.Model 接口存储模型数据
      具体步骤
      - Spring MVC 在调用方法前会创建一个隐含的模型对象作为模型数据的存储容器。
      - 如果方法的入参为 Map 或 Model类型，Spring MVC 会将隐含模型的引用传递给这些入参。在方法体内，开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据。

@SessionAttributes
      若希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注一个 @SessionAttributes, Spring MVC 将在模型中对应的属性暂存到 HttpSession 中。
      @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外，还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中。
      - @SessionAttributes(types=User.class) 会将隐含模型中所有类型为 User.class 的属性添加到会话中。
      - @SessionAttributes(value={“user1”, “user2”})
      - @SessionAttributes(types={User.class, Dept.class})
      - @SessionAttributes(value={“user1”, “user2”},types={Dept.class})

@ModelAttribute
      在方法定义上使用 @ModelAttribute 注解：Spring MVC在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute 的方法。
      在方法的入参前使用@ModelAttribute 注解：
      - 可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数绑定到对象中，再传入入参
      - 将方法入参对象添加到模型中

   由@SessionAttributes引发的异常 org.springframework.web.HttpSessionRequiredException:Session attribute 'user' required - not found in session
   如果在处理类定义处标注了@SessionAttributes(“xxx”)，则尝试从会话中获取该属性，并将其赋给该入参，然后再用请求消息填充该入参对象。如果在会话中找不到对应的属性，则抛出 HttpSessionRequiredException 异常


```

#### 视图和视图解析器
```
请求处理方法执行完成后，最终返回一个 ModelAndView对象。对于那些返回 String，View 或 ModeMap 等类型的处理方法，Spring MVC 也会在内部将它们装配成一个ModelAndView 对象，它包含了逻辑名和模型对象的视图
Spring MVC 借助视图解析器（ViewResolver）得到最终的视图对象（View），最终的视图可以是 JSP ，也可能是Excel、JFreeChart 等各种表现形式的视图。
对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生产模型数据的工作上，从而实现 MVC 的充分解耦。

视图：
    视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。
    为了实现视图模型和具体实现技术的解耦，Spring在org.springframework.web.servlet 包中定义了一个高度抽象的 View接口。
    视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题。

视图解析器：
    SpringMVC 为逻辑视图名的解析提供了不同的策略，可以在 Spring WEB 上下文中配置一种或多种解析策略，并指定他们之间的先后顺序。每一种映射策略对应一个具体的视图解析器实现类。
    视图解析器的作用比较单一：将逻辑视图解析为一个具体的视图对象。所有的视图解析器都必须实现 ViewResolver 接口。

常用的视图解析器实现类：
    BeanNameViewResolver（解析为Bean的名字）：将逻辑视图名解析为一个Bean，Bean的id等于逻辑视图名。
    InternalResourceViewResolver(解析为URL文件）：
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
        将视图名解析为一个URL文件，一般使用该视图解析器将视图名映射为一个保存在WEB-INF目录下的程序文件（如JSP）。
        若项目中使用了 JSTL，则 SpringMVC 会自动把视图由InternalResourceView 转为 JstlView。
        若使用 JSTL 的 fmt 标签则需要在 SpringMVC 的配置文件中配置国际化资源文件。
        若希望直接响应通过 SpringMVC 渲染的页面，可以使用 mvc:view-controller 标签实现。

    程序员可以选择一种视图解析器或混用多种视图解析器。
    每个视图解析器都实现了 Ordered 接口并开放出一个 order 属性，可以通过 order 属性指定解析器的优先顺序，order 越小优先级越高。
    SpringMVC 会按视图解析器顺序的优先顺序对逻辑视图名进行解析，直到解析成功并返回视图对象，否则将抛出 ServletException 异常。




```
#### 关于重定向
```
一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理。
如果返回的字符串中带 forward: 或 redirect:前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和 redirect: 当成指示符，其后的字符串作为 URL 来处理。
redirect:success.jsp：会完成一个到 success.jsp 的重定向的操作。
forward:success.jsp：会完成一个到 success.jsp 的转发操作。

```
#### SpringMVC 表单标签 
```
使用 Spring 的表单标签：
   通过 SpringMVC 的表单标签可以实现将模型数据中的属性和 HTML 表单元素相绑定，以实现表单数据更便捷编辑和表单值的回显。

form 标签：
   一般情况下，通过 GET 请求获取表单页面，而通过POST 请求提交表单页面，因此获取表单页面和提交表单页面的 URL 是相同的。只要满足该最佳条件的契约，<form:form> 标签就无需通过 action 属性指定表单提交的 URL
   可以通过 modelAttribute 属性指定绑定的模型属性，若没有指定该属性，则默认从 request 域对象中读取command 的表单 bean，如果该属性值也不存在，则会发生错误。

   SpringMVC 提供了多个表单组件标签，如<form:input/>、<form:select/> 等，用以绑定表单字段的属性值，它们的共有属性如下：
      - path：表单字段，对应 html 元素的 name 属性，支持级联属性
      - htmlEscape：是否对表单值的 HTML 特殊字符进行转换，默认值为 true
      - cssClass：表单组件对应的 CSS 样式类名
      - cssErrorClass：表单组件的数据存在错误时，采取的 CSS样式

表单标签
   form:input、form:password、form:hidden、form:textarea：对应 HTML 表单的 text、password、hidden、textarea标签
   form:radiobutton：单选框组件标签，当表单 bean 对应的属性值和 value 值相等时，单选框被选中.
   form:radiobuttons：单选框组标签，用于构造多个单选框
      - items：可以是一个 List、String[] 或 Map
      - itemValue：指定 radio 的 value 值。可以是集合中 bean 的一个属性值
      - itemLabel：指定 radio 的 label值
      - delimiter：多个单选框可以通过 delimiter 指定分隔符

   form:checkbox：复选框组件。用于构造单个复选框
   form:checkboxs：用于构造多个复选框。使用方式同 form:radiobuttons 标签
   form:select：用于构造下拉框组件。使用方式同 form:radiobuttons 标签
   form:option：下拉框选项组件标签。使用方式同 form:radiobuttons 标签
   form:errors：显示表单组件或数据校验所对应的错误
      - <form:errors path= “ *” /> ：显示表单所有的错误
      - <form:errors path= “ user*” /> ：显示所有以 user 为前缀的属性对应的错误
      - <form:errors path= “ username” /> ：显示特定表单对象属性的错误

```

#### SpringMVC 处理静态资源
```
1. 为什么会有这样的问题:
优雅的 REST 风格的资源URL 不希望带 .html 或 .do 等后缀。
若将 DispatcherServlet 请求映射配置为 /,
则 Spring MVC 将捕获 WEB 容器的所有请求, 包括静态资源的请求, SpringMVC 会将他们当成一个普通请求处理,因找不到对应处理器将导致错误。
2. 解决: 在 SpringMVC 的配置文件中配置 <mvc:default-servlet-handler/>
   - default-servlet-handler 将在 SpringMVC 上下文中定义一个 DefaultServletHttpRequestHandler,它会对进入 DispatcherServlet 的请求进行筛查, 如果发现是没有经过映射的请求, 就将该请求交由 WEB 应用服务器默认的Servlet 处理. 如果不是静态资源的请求，才由 DispatcherServlet 继续处理。
   - 一般 WEB 应用服务器默认的 Servlet 的名称都是 default.若所使用的 WEB 服务器的默认 Servlet 名称不是 default，则需要通过 default-servlet-name 属性显式指定

```

#### 数据绑定流程
```
1. Spring MVC 主框架将 ServletRequest 对象及目标方法的入参实例传递给 WebDataBinderFactory 实例，以创建 DataBinder 实例对象。
2. DataBinder 调用装配在 Spring MVC 上下文中的ConversionService 组件进行数据类型转换、数据格式化工作。将 Servlet 中的请求信息填充到入参对象中。
3. 调用 Validator 组件对已经绑定了请求消息的入参对象进行数据合法性校验，并最终生成数据绑定结果BindingData 对象。
4. Spring MVC 抽取 BindingResult 中的入参对象和校验错误对象，将它们赋给处理方法的响应入参。

```

 


 
 
 
 

#### 关于 mvc:annotation-driven
```
<mvc:annotation-driven /> 会自动注册RequestMappingHandlerMapping、RequestMappingHandlerAdapter 与ExceptionHandlerExceptionResolver 三个bean。
还将提供以下支持：
支持使用 ConversionService 实例对表单参数进行类型转换
支持使用 @NumberFormat annotation、@DateTimeFormat注解完成数据类型的格式化
支持使用 @Valid 注解对 JavaBean 实例进行 JSR 303 验证
支持使用 @RequestBody 和 @ResponseBody 注解
``` 

 
 
### 数据格式化
```

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


```


###  数据校验：


 
#### Spring MVC 数据校验
```
Spring MVC 数据校验：
   - Spring 4.0 拥有自己独立的数据校验框架，同时支持 JSR 303 标准的校验框架。
   - Spring 在进行数据绑定时，可同时调用校验框架完成数据校验工作。在 Spring MVC 中，可直接通过注解驱动的方式进行数据校验。
   - Spring 的 LocalValidatorFactroyBean 既实现了 Spring 的Validator 接口，也实现了 JSR 303 的 Validator 接口。只要在 Spring 容器中定义了一个LocalValidatorFactoryBean，即可将其注入到需要数据校验的 Bean 中。
   - Spring 本身并没有提供 JSR303 的实现，所以必须将JSR303 的实现者的 jar 包放到类路径下。
   - <mvc:annotation-driven/> 会默认装配好一个 LocalValidatorFactoryBean，通过在处理方法的入参上标注 @valid 注解即可让 Spring MVC 在完成数据绑定后执行数据校验的工作。
   - 在已经标注了 JSR303 注解的表单/命令对象前标注一个@Valid，Spring MVC 框架在将请求参数绑定到该入参对象后，就会调用校验框架根据注解声明的校验规则实施校验。
   - Spring MVC 是通过对处理方法签名的规约来保存校验结果的：前一个表单/命令对象的校验结果保存到随后的入参中，这个保存校验结果的入参必须是 BindingResult 或 Errors 类型，这两个类都位于org.springframework.validation 包中。
   - 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
   - Errors 接口提供了获取错误信息的方法，如 getErrorCount()或getFieldErrors(String field)
   - BindingResult 扩展了 Errors 接口

```

 

 
#### 在目标方法中获取校验结果
```
在目标方法中获取校验结果

   在表单/命令对象类的属性中标注校验注解，在处理方法对应的入参前添加 @Valid，Spring MVC 就会实施校验并将校验结果保存在被校验入参对象之后的 BindingResult 或 Errors 入参中。
   常用方法：
   - FieldError getFieldError(String field)
   - List<FieldError> getFieldErrors()
   - Object getFieldValue(String field)
   - Int getErrorCount()


```

#### 在页面上显示错误
```
在页面上显示错误
   - Spring MVC 除了会将表单/命令对象的校验结果保存到对应的 BindingResult 或 Errors 对象中外，还会将所有校验结果保存到 “隐含模型”
   - 即使处理方法的签名中没有对应于表单/命令对象的结果入参，校验结果也会保存在 “隐含对象” 中。
   - 隐含模型中的所有数据最终将通过 HttpServletRequest 的属性列表暴露给 JSP 视图对象，因此在 JSP 中可以获取错误信息
   - 在 JSP 页面上可通过 <form:errors path=“userName”> 显示错误消息

```

 
 

 
#### 提示消息的国际化
```
提示消息的国际化
   每个属性在数据绑定和数据校验发生错误时，都会生成一个对应的 FieldError 对象。
   当一个属性校验失败后，校验框架会为该属性生成 4 个消息代码，这些代码以校验注解类名为前缀，结合modleAttribute、属性名及属性类型名生成多个对应的消息代码：例如 User 类中的 password 属性标准了一个 @Pattern 注解，当该属性值不满足 @Pattern 所定义的规则时, 就会产生以下 4个错误代码：
      - Pattern.user.password
      - Pattern.password
      - Pattern.java.lang.String
      - Pattern
   当使用 Spring MVC 标签显示错误消息时， Spring MVC 会查看WEB 上下文是否装配了对应的国际化消息，如果没有，则显示默认的错误消息，否则使用国际化消息。

   若数据类型转换或数据格式转换时发生错误，或该有的参数不存在，或调用处理方法时发生错误，都会在隐含模型中创建错误消息。其错误代码前缀说明如下：
   - required：必要的参数不存在。如 @RequiredParam(“param1”)标注了一个入参，但是该参数不存在
   - typeMismatch：在数据绑定时，发生数据类型不匹配的问题
   - methodInvocation：Spring MVC 在调用处理方法时发生了错误注册国际化资源文件.
   <bean id = "messageSource" class = "org.springframework.context.support.ResourceBundleMessageSource">
      <property name="basename" value="i18n" />
   </bean>

```
### 处理JSON : 使用HttpMessageConverter
 

 