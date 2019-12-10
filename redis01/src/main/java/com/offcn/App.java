package com.offcn;

import com.alibaba.fastjson.JSON;
import com.offcn.pojo.Person;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
//存储String类型数据
    @Test
    public void show(){


        Jedis jedis = new Jedis("192.168.115.132",6379);
        jedis.auth("123456");
        jedis.set("name5","linxiao");
        String name5 = jedis.get("name5");
        System.out.println(name5);

        jedis.set("age","18");
        String age = jedis.get("age");
        System.out.println(age);

    }

//存储对象
    @Test
    public void show1(){
        Jedis jedis = new Jedis("192.168.115.132",6379);
        jedis.auth("123456");
        Person person = new Person(1,"xiaofei",new Date());
        //序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] bytes = null;
        try {
             oos = new ObjectOutputStream(bos);
             oos.writeObject(person);
             bytes = bos.toByteArray();
            jedis.set("person".getBytes(),bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //反序列化
        byte[] bytes1 = jedis.get("person".getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes1);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bis);
            Person person1 =(Person) ois.readObject();
            System.out.println(person1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//存储对象集合
    @Test
    public void show2(){
        Jedis jedis = new Jedis("192.168.115.132",6379);
        jedis.auth("123456");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person(101,"xiaobai",new Date()));
        personList.add(new Person(102,"xiaohei",new Date()));
        personList.add(new Person(103,"xiaohua",new Date()));
        personList.add(new Person(104,"xiaoxiao",new Date()));
        //System.out.println(personList);
        jedis.set("personList", JSON.toJSON(personList).toString());

        String personlist = jedis.get("personList");
        List<Person> personList1 = JSON.parseArray(personlist, Person.class);
        System.out.println(personList1);
    }
}
