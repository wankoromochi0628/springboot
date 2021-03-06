package com.example.demo.common.Config;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swaggerについて参考になるページ
 * https://qiita.com/nvtomo1029/items/03f263423848a955f2bc
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> paths() {

        // 仕様書生成対象のURLパスを指定する
        return Predicates.and(
                // 公開したくない場合に記載する
                // Predicates.not(Predicates.containsPattern("/api*")),
                Predicates.or(Predicates.containsPattern("/api*")));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "sample2_restapi Web API",              // title
                "sample2_restapi の Web API 仕様書",    // description
                "0.0.1",                                // version
                "",                                     // terms of service url
                "ySu0628",                             // created by
                "ySu0628 Co. Ltd",                     // license
                "");                                    // license url
        return apiInfo;
    }
}
