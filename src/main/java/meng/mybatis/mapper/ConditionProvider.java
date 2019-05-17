package meng.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public class ConditionProvider {
    public String condition(@Param("username") String username,@Param("age") Integer age) {
        String sql = "select * from student where 1=1 ";
        if (username != null) {
            sql += "and name=#{username} ";
        }
        if (age != null && age != 0) {
            sql += "and age=#{age} ";
        }
        return sql;
    }
}
