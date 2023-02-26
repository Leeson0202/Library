package cool.leeson.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cool.leeson.library.dao")
public class LibraryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServerApplication.class, args);
    }

}
