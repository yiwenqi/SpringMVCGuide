package cn.gsdx.Springmvc.handler;

import cn.gsdx.Springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author JackWen
 */
@Controller
@RequestMapping("/springmvc")
public class SpringMVCTest {
    private final static String SUCCESS="success";

    /**
     * 使用 ModelAttribute 标注的方法 ， 会在每个目标方法执行之前被 SpringMVC 调用
     * @param username
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "username",required = false) String username,Map<String,Object> map){
        //模拟从数据库之中获取数据
        if(username!=null){
            User user = new User("tom", "18","0",null);
            System.out.println("从数据库之中获取值:" + user);
            map.put("user",user);
        }
    }

    @RequestMapping(value = "/testModelAttribute" ,method = RequestMethod.POST)
    public String testModelAttribute(User user){
        System.out.println("update 后的 user:"+user);
        return SUCCESS;
    }

    /**
     * SessionAttributes : 用来将 map 中的指定的值放入session 域之中
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Map<String,Object> map){
        User user = new User("tom","18岁","男" ,null);
        /**
         * 此时 map 中的 user 放入了 request 域 和 session 域之中
         */
        map.put("user",user);
        map.put("school" ,"String you konw");
        return SUCCESS;
    }

    /**
     * 目标方法可以添加 map 类型的参数  实际上还可以传入model 或 modelMap 类型
     * 将 map 类型放入 request 域之中 ，和modelAndView 一样
     * @param map
     * @return
     */
     @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
         map.put("name", Arrays.asList("Tom","Jack","Rose","Jani"));
        return SUCCESS;
    }

    /**
     * 目标方法的返回值可以时 ModelAndView 类型
     * 其中包含了 视图 view 和 模型信息（model）
     * SpringMVC 会把 modelAndView 中的 model 信息放入 request 域对象之中
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView mv = new ModelAndView(viewName);
        //添加模型信息
        mv.addObject("time" ,new Date());
        return mv;
    }


    /**
     * 可以使用 原生 Servlet Api作为目标参数 ，具体支持以下类型:
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * Write
     * Reader
     * OutputStream
     * InputStream
     * Principal
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/testServletApi")
    public String testServletApi(HttpServletRequest request , HttpServletResponse response){
        System.out.println("testServletApi request : "+ request +",response :" +response);
        return SUCCESS;
    }

    /**
     * 使用 pojo 作为参数 ，SprigMVC 会按照请求参数名自动的为 pojo 属性进行赋值 ，并且支持级联属性
     * @param user
     * @return
     */
    @RequestMapping(value = "/testPOJO")
    public String tesPOJO(User user){
        System.out.println("TestPOJO user :" +user);
        return SUCCESS;
    }

    /**
     * CookiesValue : 用来映射 cookievalue 值
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/testCookiesValue")
    public String testCookiesValue(@CookieValue("JSESSIONID") String sessionId){
        System.out.println("testCookiesValue sessionId :" +sessionId);
        return SUCCESS;
    }

    /**
     * RequestParam : 映射请求头参数到方法中去
     * @param al
     * @return
     */
    @RequestMapping(value = "/testRequestHeaders")
    public String testRequestHeaders(@RequestHeader("Accept-Lanuage") String al){
        System.out.println("testRequestParam al:" + al);
        return SUCCESS;
    }

    /**
     * RequestParam : 将请求参数传入方法中
     * @param username
     * @param age
     * @return
     */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam("username") String username ,
                                   @RequestParam(value = "age",required = false,defaultValue = "0") Integer age){
        System.out.println("testRequestParam username :"+username +",age:"+age);
        return SUCCESS;
    }

    /**
     * PathVariable : 用来映射 url 中的占位符
     * @param variable
     * @return
     */
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer variable){
        System.out.println("testPathVariable:"+variable);
        return SUCCESS;
    }

    /**
     * 用来测试 RequestMapping 注解
     * @return
     */
    @RequestMapping(value = "/testRequestMapping" ,method = RequestMethod.GET)
    public String testRequestMapping(){
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    /**
     * 用来测试 RequestMapping 注解下的 value params 和 headers 参数
     * @return
     */
    @RequestMapping(value = "testParamAndHeaders",params = {"username" ,"age!=10"},
                            headers = {"Accept-Language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"})
    public String testParamAndHeaders(){
        System.out.println("testParamAndHeaders");
        return SUCCESS;
    }

}
