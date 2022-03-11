package com.github.zhengchalei.spring.el.controller;

import com.github.zhengchalei.spring.el.annotations.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * book api
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Auth(checkRole = "hasRole('admin') ")
    @GetMapping("/f1")
    public String fun1() {
        return LocalDateTime.now().toString();
    }

    @Auth(checkRole = "hasRole('admin') or hasRole('user')")
    @GetMapping("/f2")
    public String fun2() {
        return LocalDateTime.now().toString();
    }

    @Auth(checkRole = "hasRole('admin') and hasRole('user')")
    @GetMapping("/f3")
    public String fun3() {
        return LocalDateTime.now().toString();
    }

    @Auth(checkRole = "hasRole('admin') or hasRole('user')", checkIsAdmin = true)
    @GetMapping("/f4")
    public String fun4() {
        return LocalDateTime.now().toString();
    }
}
