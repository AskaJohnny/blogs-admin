package org.johnny.blogsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author johnny
 * @create 2020-07-10 下午3:52
 **/

@SpringBootApplication


@EnableJpaAuditing //开启自动更改时间等
@ComponentScan("org.johnny.*")
@EntityScan(basePackages = {"org.johnny.*"})
@EnableJpaRepositories(basePackages = {"org.johnny.*"})
@EnableElasticsearchRepositories(basePackages = {"org.johnny.blogscommon.*"})
@EnableConfigurationProperties

@EnableScheduling //开起定时任务
public class BlogsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogsServerApplication.class);
    }
}