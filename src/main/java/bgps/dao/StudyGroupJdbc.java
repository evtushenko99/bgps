package bgps.dao;

import bgps.model.StudyGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudyGroupJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudyGroupJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudyGroup getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDY_GROUP WHERE ID = ?", this::mapStudyGroup, id);
    }

    public List<StudyGroup> getAll() {
        return jdbcTemplate.query("SELECT * FROM STUDY_GROUP", this::mapStudyGroup);
    }

    private StudyGroup mapStudyGroup(ResultSet resultSet, int i) throws SQLException {
        return new StudyGroup(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }

    public StudyGroup add(StudyGroup studyGroup) {
        jdbcTemplate.update(
                "INSERT INTO STUDY_GROUP(id, name) VALUES (?, ?)",
                studyGroup.getId(),
                studyGroup.getName()
        );

        return getById(studyGroup.getId());
    }

    public StudyGroup delete(int id) {
        StudyGroup studyGroupToDelete = getById(id);

        if (studyGroupToDelete == null) {
            return null;
        }

        jdbcTemplate.update("DELETE FROM STUDY_GROUP WHERE ID = ?", id);
        return studyGroupToDelete;
    }

    public StudyGroup edit(int id, StudyGroup studyGroup) {
        StudyGroup studyGroupToEdit = getById(id);
        if (studyGroupToEdit == null) {
            return null;
        }

        delete(id);
        return add(studyGroup);
    }
}
