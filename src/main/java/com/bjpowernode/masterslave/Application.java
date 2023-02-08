package com.bjpowernode.masterslave;

import com.bjpowernode.masterslave.model.User;
import com.bjpowernode.masterslave.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ac= SpringApplication.run(Application.class, args);
        UserService service= (UserService) ac.getBean("userService");
        User user=new User();
        user.setName("张三");
        service.add(user);
        for(User u:service.selectAll()){
            System.out.println(u.getName());
        }

        System.out.println("left01分支初始化代码");
    }

}
