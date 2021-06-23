package com.example.springboot2proguard;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot2proguardApplication {

    // 防止bean名称重复, 采用这个方法绕过
    public static class CustomGenerator implements BeanNameGenerator {
        @Override
        public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
            System.out.println(definition.getBeanClassName());
            return definition.getBeanClassName();
        }
    }

    public static void main(String[] args) {
        SpringApplication springApplication =new SpringApplication(Springboot2proguardApplication.class);
        // 某些网站声明可以这么解决部分配置的问题, 实测下来反而会造成部分包找不到
//        springApplication.setBeanNameGenerator(new CustomGenerator());
        springApplication.run(args);
    }

}
