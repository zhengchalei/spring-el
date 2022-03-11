package com.github.zhengchalei.spring.el.aop;

import com.github.zhengchalei.spring.el.annotations.Auth;
import com.github.zhengchalei.spring.el.expressions.RoleExpression;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * auth aop
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@Slf4j
@Aspect
@Configuration
public class AuthAop {

    private final StandardEvaluationContext standardEvaluationContext;

    private final RoleExpression roleExpression;

    public AuthAop(StandardEvaluationContext standardEvaluationContext, RoleExpression roleExpression) {
        this.standardEvaluationContext = standardEvaluationContext;
        this.roleExpression = roleExpression;
    }

    @Pointcut("@annotation(com.github.zhengchalei.spring.el.annotations.Auth)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法
        Method method = signature.getMethod();
        // 获取 auth func
        Auth annotation = method.getAnnotation(Auth.class);
        {
            // 获取注解内字符串
            String checkRole = annotation.checkRole();
            if (!checkRole.isBlank()) {
                Expression expression = new SpelExpressionParser().parseExpression(checkRole);
                // EvaluationContext context, @Nullable Object rootObject, @Nullable Class<T> desiredResultType
                // context 上下文, rootObject 设置后可以直接调用方法名, desiredResultType 返回值类型
                Boolean value = expression.getValue(this.standardEvaluationContext, this.roleExpression, Boolean.class);
                log.info("正在检查角色是否满足条件, 结果: {}", value);
                if (!Boolean.TRUE.equals(value)) {
                    throw new RuntimeException("权限不足!");
                }
            }
        }

        {
            boolean b = annotation.checkIsAdmin();
            if (b) {
                Expression expression = new SpelExpressionParser().parseExpression("checkIsAdmin()");
                // EvaluationContext context, @Nullable Object rootObject, @Nullable Class<T> desiredResultType
                // context 上下文, rootObject 设置后可以直接调用方法名, desiredResultType 返回值类型
                Boolean value = expression.getValue(this.standardEvaluationContext, this.roleExpression, Boolean.class);
                log.info("正在检查是否为管理员, 结果: {}", value);
                if (!Boolean.TRUE.equals(value)) {
                    throw new RuntimeException("不是Admin!");
                }
            }
        }
        return joinPoint.proceed();
    }

}
