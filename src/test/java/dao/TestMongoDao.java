package dao;

import meng.Application;
import meng.mongo.bean.Student;
import meng.mongo.dao.StudentRepository;
import meng.utils.GetSimpleDateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestMongoDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentRepository studentRepository;


    @Test
    public void testInsert() throws ParseException {

        Student student = new Student();
        student.setName("Jack");
        student.setAge(25);
        student.setBirthday(GetSimpleDateFormatUtils.getDateFormat().parse("1994-03-26"));
        List<String> hobby = new ArrayList<>();
        hobby.add("唱");
        hobby.add("跳");
        hobby.add("打篮球");
        student.setHobby(hobby);

        mongoTemplate.insert(student);

    }

    @Test
    public void testFind() {


        List<Student> students = mongoTemplate.findAll(Student.class);
        Query query = new Query();
        System.out.println(students);
        query.addCriteria(Criteria.where("name").is("Jack"));
        students = mongoTemplate.find(query, Student.class);
        System.out.println(students);

    }

    @Test
    public void repoFind() {
        System.out.println(studentRepository.findAll());
        System.out.println(studentRepository.findStudentByAge(25));
        Student student = new Student();
        student.setAge(25);
        System.out.println(studentRepository.findOne(Example.of(student)));
    }

    @Test
    public void update() {
        Update update = new Update();
        List hobby = new ArrayList();
        hobby.add("R6");
        hobby.add("编程");
        update.set("hobby", hobby);
        mongoTemplate.updateFirst(new Query(Criteria.where("name").is("王蒙")), update, Student.class);

    }
}
