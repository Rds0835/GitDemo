package com.demo.demo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: java 8 stream demo
 *
 * @author: ruands
 * @date: 2018-07-15 下午9:26
 **/
public class StreamDemo {

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
//        for(int i=0; i<9; i++){
//            list.add(""+i);
//        }
//
        list.add("test demo");
        list.add("test demo1");
        list.add("demo2");
        list.add("test1, demo");
        Stream<String[]> stream = list.stream().map(l->l.split(" "));
        System.err.println("array of stream length: "+stream.collect(Collectors.toList()).size());
        Stream<String> stringStream = list.stream().map(l->l.split(" ")).flatMap(str->Arrays.stream(str));
        System.err.println("########float map########");
        stringStream.forEach(str->{System.err.println(str);});
        System.err.println("######distinct#####");
        list.stream().map(l->l.split(" ")).flatMap(str->Arrays.stream(str)).distinct().forEach(str->{System.err.println(str);});
    }

    @Test
    public void test1(){
        Student student1 = new Student("zhansan", 18, "男","清华");
        Student student2 = new Student("lisi", 19, "男","北大");
        Student student3 = new Student("xiaoli", 18, "女", "北大");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        Map<String, List<Student>> resultMap = list.stream().collect(Collectors.groupingBy(Student::getSchool));
        Map<String, Long> map =list.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.counting()));
        for(Map.Entry<String, Long> entry: map.entrySet()){

        }
        for(Map.Entry<String, List<Student>> entry : resultMap.entrySet()){

        }
        Iterator<String> iterator = resultMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.err.println(resultMap.get(key).stream().map(Student::getName).reduce(key+":",(a,b)->a+b));
        }
    }

    private class Student{
        private String name;
        private int age;
        private String sex;
        private String school;

        public Student(String name, int age, String sex, String school){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.school = school;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }
    }
}
