package bgps.controller;

import bgps.dao.StudyGroupJdbc;
import bgps.model.StudyGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyGroupController {
    private final StudyGroupJdbc repository;

    public StudyGroupController(StudyGroupJdbc repository) {
        this.repository = repository;
    }

    @GetMapping("/group/{id}")
    public StudyGroup getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @GetMapping("/group")
    public List<StudyGroup> getAll() {
        return repository.getAll();
    }

    @PostMapping("/group")
    public StudyGroup add(@RequestBody StudyGroup studyGroup) {
        return repository.add(studyGroup);
    }

    @DeleteMapping("/group/{id}")
    public StudyGroup delete(@PathVariable int id) {
        return repository.delete(id);
    }

    @PutMapping("/group/{id}")
    public StudyGroup edit(@PathVariable int id, @RequestBody StudyGroup studyGroup) {
        return repository.edit(id, studyGroup);
    }
}
