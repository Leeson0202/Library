package cool.leeson.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("cool.leeson.library.dao")
@CrossOrigin
@EnableScheduling // 定时任务
public class LibraryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServerApplication.class, args);
    }

}
