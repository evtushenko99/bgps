package bgps.dao;

import bgps.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = ?", this::mapStudent, id);
    }

    public List<Student> getByGroupId(int groupId) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE STUDY_GROUP_ID = ?", this::mapStudent, groupId);
    }

    private Student mapStudent(ResultSet resultSet, int i) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("second_name"),
                resultSet.getInt("study_group_id")
        );
    }

    public List<Student> getAll() {
        return jdbcTemplate.query("SELECT * FROM STUDENT", this::mapStudent);
    }

    public Student delete(int id) {
        Student studentToDelete = get(id);

        if (studentToDelete == null) {
            return null;
        }

        jdbcTemplate.update("DELETE FROM STUDENT WHERE ID = ?", id);
        return studentToDelete;
    }

    public Student add(Student student) {
        jdbcTemplate.update(
                "INSERT INTO STUDENT(id, surname, name, second_name, study_group_id) VALUES (?, ?, ?, ?, ?)",
                student.getId(),
                student.getSurname(),
                student.getName(),
                student.getSecondName(),
                student.getStudyGroupId()
        );

        return get(student.getId());
    }

    public Student edit(int id, Student student) {
        Student studentToEdit = get(id);
        if (studentToEdit == null) {
            return null;
        }
        delete(id);
        return add(student);
    }
}
