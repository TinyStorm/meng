package dao;

import meng.Application;
import meng.mybatis.bean.Student;
import meng.redis.service.StudentRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    private StudentRedisService redisService;

    @Test
    public void testFind(){
        Student student = redisService.findOne(1);
        System.out.println(student);
    }

    @Test
    public void testUpdate(){
        Student student = redisService.findOne(1);
        student.setName("王大蒙");
        redisService.update(student);

    }

    @Test
    public void testFindAll(){
        List<Student> students = redisService.findAll();
        System.out.println(students);
    }

    @Test
    public void testDelete(){
        redisService.delete(1);
    }

}
