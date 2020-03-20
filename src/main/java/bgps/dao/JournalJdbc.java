package bgps.dao;

import bgps.model.JournalRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JournalJdbc {
    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JournalRecord getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM JOURNAL WHERE ID = ?", this::mapJournalRecord, id);
    }

    public List<JournalRecord> getByStudentId(int studentId) {
        return jdbcTemplate.query("SELECT * FROM JOURNAL WHERE STUDENT_ID = ?", this::mapJournalRecord, studentId);
    }

    public JournalRecord edit(int id, JournalRecord journalRecord) {
        JournalRecord journalRecordToEdit = getById(id);
        if (journalRecordToEdit == null) {
            return null;
        }

        delete(id);
        return add(journalRecord);
    }

    public JournalRecord mapJournalRecord(ResultSet resultSet, int i) throws SQLException {
        return new JournalRecord(
                resultSet.getInt("id"),
                resultSet.getBoolean("in_time"),
                resultSet.getInt("count"),
                resultSet.getInt("student_id"),
                resultSet.getInt("study_plan_id"),
                resultSet.getInt("mark_id")
        );
    }

    public JournalRecord add(JournalRecord journalRecord) {
        jdbcTemplate.update(
                "INSERT INTO JOURNAL(id, student_id, study_plan_id, in_time, count, mark_id) VALUES ( ?, ?, ?, ?, ?, ? )",
                journalRecord.getId(),
                journalRecord.getStudentId(),
                journalRecord.getStudyPlanId(),
                journalRecord.isInTime(),
                journalRecord.getCount(),
                journalRecord.getMarkId()
        );

        return getById(journalRecord.getId());
    }

    public JournalRecord delete(int id) {
        JournalRecord journalRecordToDelete = getById(id);

        if (journalRecordToDelete == null) {
            return null;
        }

        jdbcTemplate.update("DELETE FROM JOURNAL WHERE ID = ?", id);
        return journalRecordToDelete;
    }
}
