package se.xmut.trahrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger3Config {

    @Bean
    public Docket restApiConfig(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("resApi")
                .apiInfo(restApiInfo())
                .enable(true)
                .select()
                .build();
    }

    private ApiInfo restApiInfo() {
        return new ApiInfoBuilder()
                .title("J2ee期末项目接口文档")
                .description("J2ee期末项目接口文档具体内容")
                .version("1.0")
                .contact(new Contact("Jinx","","1012140020@qq.com"))
                .build();
    }
}
