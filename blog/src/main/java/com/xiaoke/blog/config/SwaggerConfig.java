package com.xiaoke.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 开启swagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(Environment environment) {

        // 设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");

        // 通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaoke.blog.controller"))
//                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("小科","http://blog.xiaoke6.cn", "702164551@qq.com");
        return new ApiInfoBuilder()
                .title("网行侠博客项目API文档")
                .description("更多Spring Boot相关文章请关注微信公众号：网行侠")
                .termsOfServiceUrl("http://blog.xiaoke6.cn")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
