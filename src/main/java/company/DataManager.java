package company;

import com.google.gson.Gson;
import company.Entities.Student;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataManager {
    private static final String studentsIndexingFile = "src/main/resources/indexing.json";
    private static final String studentsDirectory = "src/main/resources/Students/";
    private Gson gson = new Gson();

    public List<Student> getStudents() {
        List<Integer> studentIds = getStudentIds();
        List<Student> students = new ArrayList<>();

        for (Integer studentId : studentIds)
           students.add(getStudent(studentId));


        return students;
    }

    public Student getStudent(int id) {
        List<Integer> studentIds = getStudentIds();
        if(!studentIds.contains(id))
            return null;
        try {
            return gson.fromJson(new FileReader(studentsDirectory + id + ".json"), Student.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO error : students data file missing
        }
        return null;
    }

    private List<Integer> getStudentIds() {
        List<Integer> studentIds = new ArrayList<>();
        try {
            Integer[] array =gson.fromJson(new FileReader(studentsIndexingFile), Integer[].class);
            studentIds = Arrays.asList(array);
            studentIds = new ArrayList<Integer>(studentIds);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO error : indexing file is missing

        }

        return studentIds;
    }

    public void addStudent(Student student) throws Exception {
        if (getStudents().contains(student.getId()))
            throw new Exception("student id already exists");

        List<Integer> studentIds = getStudentIds();
        studentIds.add(student.getId());

        FileWriter fw =  new FileWriter(studentsIndexingFile, false);
        gson.toJson(studentIds,fw);
        fw.flush();
        fw.close();

        File studentsFile = new File(studentsDirectory + student.getId() + ".json");
        studentsFile.createNewFile();
        fw = new FileWriter(studentsFile);
        gson.toJson(student, fw);
        fw.flush();
        fw.close();

    }
}
