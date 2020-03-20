package bgps.controller;

import bgps.dao.StudentJdbc;
import bgps.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    @GetMapping("/student/{id}")
    public Student getById(@PathVariable int id) {
        return studentJdbc.get(id);
    }

    @GetMapping("/student")
    public List<Student> getAll() {
        return studentJdbc.getAll();
    }

    @GetMapping("/student/group/{id}")
    public List<Student> getByGroupId(@PathVariable int id) {
        return studentJdbc.getByGroupId(id);
    }

    @DeleteMapping("/student/{id}")
    public Student delete(@PathVariable int id) {
        return studentJdbc.delete(id);
    }

    @PostMapping("/student")
    public Student add(@RequestBody Student student) {
        return studentJdbc.add(student);
    }

    @PutMapping("/student/{id}")
    public Student edit(@PathVariable int id, @RequestBody Student student) {
        return studentJdbc.edit(id, student);
    }
}
