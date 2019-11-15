package com.test.lambdademo.map;

import java.lang.reflect.Field;
import java.util.*;
/**
 * @author li li
 */
public class LambdaMapTest {

    public static void main(String[] args) throws IllegalAccessException {
        //得到数据集合
        List<Map<String, Object>> persons = personsList();
        List<Map<String, Object>> newPersons = new ArrayList<>();
        persons.forEach(person -> {
            Map<String, Object> newPerson = new HashMap<>(10);
             for (Map.Entry<String, Object> entry : person.entrySet()) {
                newPerson.put(entry.getKey() + "1", entry.getValue());
            }
            newPersons.add(newPerson);
        });
        System.out.println("persons:" + persons);
        System.out.println("newPersons:" + newPersons);


    }

    /**
     * @return 得到对象集合；
     */
    private static List<Map<String, Object>> personsList() throws IllegalAccessException {

        List<Map<String, Object>> persons = new ArrayList<>();
        //循环次数
        int count = 7;
        for (int i = 0; i <= count; i++) {

            String[] ss = {"儿子", "女儿"};
            int index = new Random().nextInt(2);
            //姓名
            String name = i + ss[index];

            //年龄
            int age = 8 - i;
            //组别
            int group;
            if (age > 5) {
                group = 1;
            } else {
                group = 2;
            }

            Person p = new Person(name, age, group);

            Map<String, Object> map = objectToMap(p);

            persons.add(map);
        }
        return persons;

    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>(10);
        Class<?> aClass = obj.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            //开启暴力访问
            declaredField.setAccessible(true);
            map.put(declaredField.getName() , declaredField.get(obj));
        }
        return map;
    }

}


class Person {
    /*** 姓名*/
    private String name;
    /*** 年龄*/
    private Integer age;
    /*** 组别*/
    private Integer group;

    Person(String name, Integer age, Integer group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
}