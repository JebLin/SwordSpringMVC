package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._02ResponseStatusExceptionResolver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException{


}
