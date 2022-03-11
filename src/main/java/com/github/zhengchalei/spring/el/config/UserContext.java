package com.github.zhengchalei.spring.el.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户上下文
 * <p>
 * 假设这里存储的是登录了用户的信息, 依据实际情况做判断即可
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@Data
@Component
public class UserContext {

    private String username = "admin";

    private List<String> roles = List.of("admin", "it manager");

}
