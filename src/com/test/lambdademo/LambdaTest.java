package com.test.lambdademo;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author li li
 */
public class LambdaTest {

    public static void main(String[] args) {

        Runnable r = ()->{
            System.out.println("线程启动");
            List<Person> persons = personsList();
            //输出初始集合
            System.out.println("初始化:" + persons);
            //所有Age>6的Person的集合
            List<Person> personList = persons.stream().filter(p -> p.getAge() > 6).collect(Collectors.toList());
            System.out.println("personList:" + personList);
            //按年龄进行排序 升序
            List<Person> personList1 = persons.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
            System.out.println("年龄升序:" + personList1);
            //按年龄进行排序 降序
            List<Person> personList2 = persons.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
            System.out.println("年龄降序:" + personList2);
            //根据组别进行分组
            Map<Integer, List<Person>> collect = personList1.stream().collect(Collectors.groupingBy(Person::getGroup));
            System.out.println("分组:" + collect);
            //取age=1的对象
            Person person1 = persons.stream().filter(person-> person.getAge()==1).findAny().orElse(null);
            System.out.println("age=1的对象:" + person1);
            //所有Name包含儿子的Person的集合
            List<Person> personList3 = persons.stream().filter(person -> person.getName().contains("儿子")).collect(Collectors.toList());
            System.out.println("Name包含儿子的Person的集合:" + personList3);
        };
        r.run();

    }

    /**
     * @return 得到对象集合；
     */
    private static List<Person> personsList() {

        List<Person> persons = new ArrayList<>();
        //循环次数
        int count = 7;
        for (int i = 0; i <= count; i++) {

            String[] ss = {"儿子","女儿"};
            int index = new Random().nextInt(2);
            //姓名
            String name = i+ss[index];

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
            persons.add(p);
        }
        return persons;
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