package meng.mybatis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Student implements Serializable {
    private String id;
    private String name;
    private int age;
    private Date birthday;
    private List<Course> courses;
}
