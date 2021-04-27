package org.johnny.blogsfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan("org.johnny.*")
@EntityScan(basePackages = {"org.johnny.blogscommon.*"})
@EnableJpaRepositories(basePackages = {"org.johnny.blogscommon.*"})
@EnableElasticsearchRepositories(basePackages = {"org.johnny.blogscommon.*"})
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = {"org.johnny.blogscommon.*"})
@EnableJpaAuditing //开启自动更改时间等
public class BlogsFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogsFrontApplication.class, args);
    }

}
