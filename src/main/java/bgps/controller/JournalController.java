package bgps.controller;

import bgps.dao.JournalJdbc;
import bgps.dao.StudentJdbc;
import bgps.model.JournalRecord;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JournalController {
    private final JournalJdbc journalRepository;
    private final StudentJdbc studentJdbc;

    public JournalController(JournalJdbc journalRepository, StudentJdbc studentJdbc) {
        this.journalRepository = journalRepository;
        this.studentJdbc = studentJdbc;
    }

    @GetMapping("/journal/{id}")
    public JournalRecord getById(@PathVariable int id) {
        return journalRepository.getById(id);
    }

    @GetMapping("/journal/student/{studentId}")
    public List<JournalRecord> getByStudentId(@PathVariable int studentId) {
        return journalRepository.getByStudentId(studentId);
    }

    @GetMapping("/journal/group/{groupId}")
    public List<JournalRecord> getByGroupId(@PathVariable int groupId) {
        return studentJdbc
                .getByGroupId(groupId)
                .stream()
                .map((student) -> journalRepository.getByStudentId(student.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @PostMapping("/journal")
    public JournalRecord add(@RequestBody JournalRecord journalRecord) {
        return journalRepository.add(journalRecord);
    }

    @PutMapping("/journal/{id}")
    public JournalRecord edit(@PathVariable int id, @RequestBody JournalRecord journalRecord) {
        return journalRepository.edit(id, journalRecord);
    }

    @DeleteMapping("/journal/{id}")
    public JournalRecord delete(@PathVariable int id) {
        return journalRepository.delete(id);
    }
}

