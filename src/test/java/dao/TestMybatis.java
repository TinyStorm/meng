package dao;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import meng.Application;
import meng.mybatis.bean.Student;
import meng.mybatis.mapper.master.StudentDao;
import meng.mybatis.mapper.slave.PersonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestMybatis {

    @Resource
    private StudentDao studentDao;
    @Resource
    private PersonDao personDao;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testFindAll() {
        PageHelper.startPage(1,1);
        PageInfo<Student> studentPageInfo = new PageInfo<>(studentDao.findAll());
        System.out.println(studentPageInfo);
    }

    @Test
    public void testFindOne() {
        System.out.println(studentDao.findById(2));
    }

    @Test
    public void testInsert() throws ParseException {
        Student student = new Student();
        student.setAge(25);
        student.setName("王蒙");
        student.setBirthday(simpleDateFormat.parse("1994-03-25"));
        studentDao.save(student);
    }

    @Test
    public void testOneToMany() {
        List<Student> students = studentDao.findAllStudentAndCourse();
        for (Student student : students) {
            System.out.println(student);
        }

    }

    @Test
    public void testSecondary() {
        System.out.println(personDao.findAll());
    }

    @Test
    public void testCondition(){
        System.out.println(studentDao.findByCondition("wm",0));
    }

    @Test
    public void testMap(){

        System.out.println(studentDao.findStudentMap());
    }

    @Test
    public void testLike(){
        System.out.println(studentDao.findByNameLike("%w%"));
    }


}
