package meng.mybatis.mapper.slave;

import lombok.Data;
import meng.mybatis.bean.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonDao {

    @Select("select * from person")
    List<Person> findAll();
}
