package company;

import company.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentsController {
    @Autowired
    private DataManager dataManager;

    @RequestMapping("/students")
    public List<Student> getStudents() {
        return dataManager.getStudents();
    }

    @RequestMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        return dataManager.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public void addStudent(@RequestBody Student student){
        try {
            dataManager.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO error : students id already exists
        }
    }
}
