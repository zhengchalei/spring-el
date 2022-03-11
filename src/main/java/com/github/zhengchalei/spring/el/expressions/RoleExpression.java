package com.github.zhengchalei.spring.el.expressions;

import com.github.zhengchalei.spring.el.config.UserContext;
import org.springframework.stereotype.Component;

/**
 * 角色Expression
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@Component
public class RoleExpression {

    private final UserContext userContext;

    public RoleExpression(UserContext userContext) {
        this.userContext = userContext;
    }

    /**
     * 判断是否包含角色
     *
     * @param role 角色
     * @return boolean
     */
    public boolean hasRole(String role) {
        return this.userContext.getRoles().contains(role);
    }

    /**
     * 检查是否为管理员
     *
     * @return boolean
     */
    public boolean checkIsAdmin() {
        return this.userContext.getRoles().contains("admin");
    }
}
