package com.zhongdianwanwei.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author :金鑫
 * 使用方法：启动后访问http://localhost:8080/zdww/swagger-ui.html 即可模拟前端向后端发起请求测试后端接口是否正常
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    //ss
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhongdianwanwei.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Springboot-api文档")
                .description("").termsOfServiceUrl("")
                .version("1.0").build();
    }

}


