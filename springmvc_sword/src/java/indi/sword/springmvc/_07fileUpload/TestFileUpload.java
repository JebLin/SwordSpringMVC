package indi.sword.springmvc._07fileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/fileupload")
public class TestFileUpload {

    @RequestMapping("/index")
    public String testFileUpload(){
        return "jsp_fileUpload/index";
    }

    @RequestMapping("/testfileupload")
    public String testFileUpload(@RequestParam("file") MultipartFile file,
                                 @RequestParam("desc") String desc) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("originalFileName: " + file.getOriginalFilename());
        System.out.println("inputStream: " + file.getInputStream());
        System.out.println("contentType: " + file.getContentType());
        System.out.println("size: " + file.getSize());

        return "jsp_fileUpload/success";
    }
}
