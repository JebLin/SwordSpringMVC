## ʲôʱ��ʹ�� MVC:view-controller ��ǩ
ƽʱ���Ƿ���ÿһ��ҳ�涼��Ҫͨ��Controller������������ʱ����Ҫͨ��controller��ֻ��ֱ�ӵ���ת��Ŀ��ҳ�棬��ʱ��Ϳ���ʹ��mvc:view-controller��ǩ

## ��ôʹ��
��springmvc�����ļ�������:
```
<mvc:view-controller path="/hello" view-name="jsp_hello"></mvc:view-controller>
```
path="/hello" ��������ʵ�·�����൱��RequestMapping("/hello")�� 
view-name="jsp_hello"������Ҫ����ͼ����hello.jsp,�൱��return "jsp_hello"��

�൱�����´���
```
@RequestMapping(value="/hello") 
public String hello()
{ 
 System.out.println("hello"); 
 return "jsp_hello"; 
}
```

## ��������������
����������ϵ�����ʧ���ˣ�������������ļ������Ϸ��Ƿ���
```
xmlns:mvc="http://www.springframework.org/schema/mvc"
```
�Լ�
```
xsi:schemaLocation="http://www.springframework.org/schema/mvc 
http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd"
```
���óɹ�֮������ܻᷢ�������������ҳ���ʧ��
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
�����ϴ�����ʾ���������/helloʱ�����������ʣ�����/indexʱ���ܻᱨ����Ҫ�ţ���springmvc�����ļ��м���<mvc:annotation-driven/>����
