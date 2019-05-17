package meng.mybatis.mapper.master;

import meng.mybatis.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherDao {
    @Select("select * from teacher where id=#{id}")
    Teacher findOne(int id);
}
