package meng.mongo.dao;

import meng.mongo.bean.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findStudentByAge(int age);
}
