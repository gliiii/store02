package cn.tedu.store02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.store02.mapper")
public class Store02Application {

	public static void main(String[] args) {
		SpringApplication.run(Store02Application.class, args);
	}

}
