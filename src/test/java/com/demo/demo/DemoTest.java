package com.demo.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author: ruands
 * @date: 2018-07-14 下午4:02
 **/
public class DemoTest {

    @Test
    public void test(){
//        Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        map.put("e","5");

        Thread updateThread = new Thread(new UpdateTask(map, 0));
        updateThread.setName("update thread");
        updateThread.start();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        for(int i=0; i<4; i++) {
            service.execute(new GetTask(map));
        }
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private static class UpdateTask implements Runnable{
       private Map<String, String> map ;
       private int index;

        public UpdateTask( Map<String, String> map, int index){
            this.map = map;
            this.index =index;
        }
        @Override
        public void run(){
            while(true){
                try{
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", a: "+map.get("a"));
                    map.put("a",String.valueOf(index++));
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", a: "+map.get("a"));
//                    Thread.sleep(200);
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", b: "+map.get("b"));
                    map.put("b",String.valueOf(index++));
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", b: "+map.get("b"));
//                    Thread.sleep(200);
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", c: "+map.get("c"));
                    map.put("c",String.valueOf(index++));
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", c: "+map.get("c"));
//                    Thread.sleep(200);
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", d: "+map.get("d"));
                    map.put("d",String.valueOf(index++));
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", d: "+map.get("d"));
//                    Thread.sleep(200);
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", e: "+map.get("e"));
                    map.put("e",String.valueOf(index++));
                    System.err.println("ThreadName: "+ Thread.currentThread().getName()+", e: "+map.get("e"));
                    System.err.println("***************************************************");
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
            }
        }
    }

    private static class GetTask implements Runnable{

        private Map<String, String> map;

        public GetTask(Map<String, String> map){
            this.map = map;
        }

        @Override
        public void run(){
            while(true){
                try {
                    System.err.println("ThreadName: " + Thread.currentThread().getName() + ", a: " + map.get("a"));
                    System.err.println("ThreadName: " + Thread.currentThread().getName() + ", b: " + map.get("b"));
                    System.err.println("ThreadName: " + Thread.currentThread().getName() + ", c: " + map.get("c"));
                    System.err.println("ThreadName: " + Thread.currentThread().getName() + ", d: " + map.get("d"));
                    System.err.println("ThreadName: " + Thread.currentThread().getName() + ", e: " + map.get("e"));
                    Thread.sleep(20);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
