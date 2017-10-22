package indi.sword.springmvc._06i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@RequestMapping("/international")
@Controller
public class TestI18n {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/testI18n")
    public String testI18n(Locale locale){
        String val1 = messageSource.getMessage("i18n.user",null,locale);
        String val2 = messageSource.getMessage("i18n.password",null,locale);
        System.out.println(val1 + " -> " + val2);
        return "jsp_international/index";
    }
}
