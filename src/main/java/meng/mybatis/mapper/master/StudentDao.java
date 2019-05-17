package meng.mybatis.mapper.master;


import meng.mybatis.bean.Student;
import meng.mybatis.bean.StudentMap;
import meng.mybatis.mapper.ConditionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {
    @Select("select * from student")
    List<Student> findAll();

    @Select("select * from student where id = #{id}")
    Student findById(int id);

    @Insert({"insert into student(name,age,birthday) values(#{name},#{age},#{birthday})"})
    void save(Student student);

    List<Student> findAllStudentAndCourse();

    List<Student> findAllSct();

    List<Student> findByCondition(@Param("name") String name, @Param("age") Integer age);

    @SelectProvider(type = ConditionProvider.class, method = "condition")
    List<Student> findByCondition2(@Param("username") String name, @Param("age") Integer age);

    @Results({
            @Result(property = "username", column = "name"),
            @Result(property = "currentAge", column = "age"),
    })
    @Select("select * from student")
    List<StudentMap> findStudentMap();
    @Results({
            @Result(property = "username", column = "name"),
            @Result(property = "currentAge", column = "age")
    })
    @Select("select * from student where name like #{like}")
    List<StudentMap> findByNameLike(String like);



}

