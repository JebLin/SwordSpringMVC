package indi.sword.springmvc._05Json;

import indi.sword.springmvc._03crud.dao.EmployeeDao;
import indi.sword.springmvc._03crud.entites.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/*
 * 使用 HttpMessageConverter<T>
 *   使用 HttpMessageConverter<T> 将请求信息转化并绑定到处理方法的入参中或将响应结果转为对应类型的响应信息，Spring 提供了两种途径：
 *      - 使用 @RequestBody / @ResponseBody  对处理方法进行标注
 *      - 使用 HttpEntity<T> / ResponseEntity<T> 作为处理方法的入参或返回值
 *
 *   当控制器处理方法使用到 @RequestBody/@ResponseBody 或HttpEntity<T>/ResponseEntity<T> 时, Spring 首先根据请求头或响应头的Accept 属性选择匹配的 HttpMessageConverter, 进而根据参数类型或泛型类型的过滤得到匹配的 HttpMessageConverter, 若找不到可用的HttpMessageConverter 将报错.
 *   @RequestBody 和 @ResponseBody 不需要成对出现
 */
@Controller
@RequestMapping("/json")
public class JsonTest {

    @Autowired
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }


    @RequestMapping("/jsonIndex")
    public String index(){
        return "jsp_json/index";
    }
}
