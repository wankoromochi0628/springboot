package com.example.Sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleApplication {

// ***********************************************
//	@GetMapping アノテーション
//	「/」というパスでアクセスがあった場合、「homeメソッド」
//	が呼び出される。
// ***********************************************
    @RequestMapping(value="/", method={RequestMethod.GET})
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
    	SpringApplication.run(SampleApplication.class, args);
	}
}
