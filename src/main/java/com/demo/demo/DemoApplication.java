package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@ComponentScan(basePackages={"controller","config"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
//        ReentrantLock lock = new ReentrantLock();
//        try{
//            lock.lock();
//            //to do something;
//        }catch (Exception e){
//
//        }finally {
//            lock.unlock();
//        }
    }
}
