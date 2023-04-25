package cool.leeson.lib;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cool.leeson.lib.dao")
public class Account {
    public static void main(String[] args) {
        SpringApplication.run(Account.class, args);
    }
}