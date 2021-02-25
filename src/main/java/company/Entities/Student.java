package company.Entities;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    private int id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "major")
    private String major;


    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMajor() {
        return major;
    }
}
