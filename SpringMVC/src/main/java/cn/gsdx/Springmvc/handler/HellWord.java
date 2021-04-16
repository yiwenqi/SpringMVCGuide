package cn.gsdx.Springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JackWen
 * SpringMVC快速入门
 */
@Controller
public class HellWord {
    /**
     * 使用requestMapping 来映射请求的url
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "success" ;
    }
}
