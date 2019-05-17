package meng.mybatis.mapper.master;

import meng.mybatis.bean.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseDao {

    List<Course> findAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "teacher", column = "teacher_id", one = @One(select = "meng.mybatis.mapper.master.TeacherDao.findOne"))
    })
    @Select({"select id,name,teacher_id from course"})
    List<Course> findAllByAnnotation();
}
