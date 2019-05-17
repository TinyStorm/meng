package meng.mybatis.bean;

import lombok.Data;

@Data
public class Course {
    private int id;
    private String name;
    private Teacher teacher;
}
