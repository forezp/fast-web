package io.github.forezp.fastwebadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication

@MapperScan("io.github.forezp.fastwebadmin.mapper")
public class FastWebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastWebAdminApplication.class, args);
    }

}
