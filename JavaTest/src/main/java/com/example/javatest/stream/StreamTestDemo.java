package com.example.javatest.stream;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String area; //地区

    // 构造方法
    public Person(String name, int salary, int age, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.area = area;
    }
}

class StreamTest {

    static List<Person> personList = new ArrayList<Person>();
    public static void main(String[] args) {
        personList.add(new Person("Tom", 8900, 12, "北京"));
        personList.add(new Person("Jack", 7000, 33, "上海"));
        personList.add(new Person("Lily", 7800, 66, "上海"));
        personList.add(new Person("Anni", 8200, 15, "杭州"));
        personList.add(new Person("Owen", 9500, 99, "深圳"));
        personList.add(new Person("Alisa", 7900, 34, "南京"));

        /**
         * stream：转换为Stream流
         * map：返回由将给定函数应用于此流的元素的结果组成的流
         * max、min、count：聚合
         * foreach遍历、filter过滤
         * collect：收集(toList/toSet/toMap)：
         *   统计:
         *      计数：count
         *      平均值：averagingInt、averagingLong、averagingDouble
         *      最值：maxBy、minBy
         *      求和：summingInt、summingLong、summingDouble
         *      统计以上所有：summarizingInt、summarizingLong、summarizingDouble
         *   分组(partitioningBy/groupingBy)
         *   接合(joining)
         *   归约(reducing)
         *
         * 排序(sorted)
         * 合并、去重、限制、跳过
         */
        //获取对象area值，收集成List
        List<String> collect = personList.stream()
                .map(Person::getArea)
                .collect(Collectors.toList());

        //获取对象Name值并用“,”分割
        String collect1 = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(","));

        System.out.println("collect：" + collect);
        System.out.println("collect：" + collect1);
        System.out.println("员工薪资最大值：" +
                personList.stream().max(Comparator.comparingInt(Person::getSalary)));

    }

    //foreach遍历、filter过滤
    public void testForEachAndfilter() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::print);
        System.out.println();

        System.out.println("匹配第一个值：" + list.stream().filter(x -> x > 6).findFirst());
        System.out.println("匹配任意一个值：" + list.parallelStream().filter(x -> x > 6).findAny());
        System.out.println("是否存在大于6的值：" + list.stream().anyMatch(x -> x > 6));
    }

    //排序
    public void testSort(){
        // 按工资升序排序（自然排序）
        List<String> salaryList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + salaryList);
    }

    //合并、去重、限制、跳过
    public void testStream(){
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }
}
