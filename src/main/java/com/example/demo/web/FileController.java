package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("document")
@EnableAsync
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);
    
    /**
     * 异步处理2：使用springBoot自带async注解
     */
    @RequestMapping(value = "test1",method = RequestMethod.GET)
    public String test1(){
        getTest1();
        logger.info("============>"+Thread.currentThread().getName());
        return "异步,正在解析......";
    }
    
    
    /**异步方法
     * 有@Async注解的方法，默认就是异步执行的，会在默认的线程池中执行，但是此方法不能在本类调用；启动类需添加直接开启异步执行@EnableAsync。
     * */
    @Async
    public String getTest1(){
        Object building = new Object();
        synchronized (building){
            try {
                for (int i = 1;i <= 100;i++){
                    logger.info(Thread.currentThread().getName()+"----------异步：>"+i);
                    building.wait(200);
                }
                return "执行异步任务完毕";
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return Thread.currentThread().getName()+"执行完毕";
    }
}
