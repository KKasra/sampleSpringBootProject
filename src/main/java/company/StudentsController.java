package company;

import company.Entities.Student;
import company.Repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class StudentsController {
    @Autowired
    private StudentsRepository studentsRepository;

    @RequestMapping("/students")
    public List<Student> getStudents() {
        List<Student> res = new ArrayList<>();
        studentsRepository.findAll().forEach(res::add);
        return res;
    }

    @RequestMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        Optional<Student> student = studentsRepository.findById(id);
        if (!student.isPresent()) {
            //TODO error : student does not exists
        }
        return student.get();

    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public void addStudent(@RequestBody Student student){
        if (studentsRepository.findById(student.getId()).isPresent()) {
            //TODO error : student with this id number already exists
        }
        studentsRepository.save(student);
    }
}
