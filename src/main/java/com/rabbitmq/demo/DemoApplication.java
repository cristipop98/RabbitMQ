package com.rabbitmq.demo;

import com.rabbitmq.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//p.afisare();
		SpringApplication.run(DemoApplication.class, args);
		//Producer p=new Producer();
		//producer.produceMsg();
	}

}
