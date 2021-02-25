package company.Repositories;

import company.Entities.Student;
import org.springframework.data.repository.CrudRepository;



public interface StudentsRepository extends CrudRepository<Student, Integer> {

}
