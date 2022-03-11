package com.github.zhengchalei.spring.el.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * el上下文配置
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@Configuration
public class ElContextConfiguration {

    /**
     * 表达式解析器
     *
     * @return {@link SpelExpressionParser}
     */
    @Bean
    public SpelExpressionParser spelExpressionParser() {
        return new SpelExpressionParser();
    }

    /**
     * Standard 上下文
     *
     * @param applicationContext 应用程序上下文
     * @return {@link StandardEvaluationContext}
     */
    @Bean
    public StandardEvaluationContext standardEvaluationContext(ApplicationContext applicationContext) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        // 如果不设置这一步 只能使用基础解析, 设置后 可使用 @beanName.func(参数) 调用
        standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
        return standardEvaluationContext;
    }
}
