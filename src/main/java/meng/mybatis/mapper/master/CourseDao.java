package meng.mybatis.mapper.master;

import meng.mybatis.bean.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseDao {

    List<Course> findAll();
}
