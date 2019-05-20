package meng.redis.service;

import lombok.extern.slf4j.Slf4j;
import meng.mybatis.bean.Student;
import meng.mybatis.bean.StudentMap;
import meng.mybatis.mapper.master.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "student")
@Slf4j
public class StudentRedisService {
//    @Autowired
//    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StudentDao studentDao;

    @CachePut(key = "#student.id")
    public Student update(Student student) {
        System.out.println("update");
        studentDao.update(student);
        return student;
    }

    @Cacheable(key = "#id")
    public Student findOne(int id) {
        System.out.println("findOne");
        return studentDao.findById(id);
    }

    @CacheEvict(key = "#id")
    public void delete(int id) {
        System.out.println("delete");
        studentDao.delete(id);
    }

    @Cacheable//不加key就是空字符串
    public List<Student> findAll() {
        return studentDao.findAll();
    }

//    public List<String> findByNameLike(){
//
//        List<StudentMap> studentMaps = studentDao.findByNameLike(name);
//        redisTemplate
//
//
//
//    }
}
