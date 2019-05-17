package meng.mongo.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "student")
public class Student {

    private String name;
    private int age;
    private Date birthday;
    private List<String> hobby;

}
