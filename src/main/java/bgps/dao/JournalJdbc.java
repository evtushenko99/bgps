package bgps.dao;

import bgps.model.JournalRecord;
import bgps.model.JournalRecordExpanded;
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

    public List<JournalRecordExpanded> getAll() {
        return jdbcTemplate.query("SELECT journal.id, in_time , count, student_id, study_plan_id, mark_id, " +
                "CONCAT(student.surname, ' ', student.name, ' ', student.second_name) AS student_full_name, " +
                "subject.name AS subject_name, subject.short_name AS subject_short_name, " +
                "exam_type.type AS exam_type, mark.name AS mark_name, mark.value AS mark_value " +
                "FROM journal " +
                "INNER JOIN student ON journal.student_id = student.id " +
                "INNER JOIN study_plan ON journal.study_plan_id = study_plan.id " +
                "INNER JOIN subject ON study_plan.subject_id = subject.id " +
                "INNER JOIN exam_type ON study_plan.exam_type_id = exam_type.id " +
                "INNER JOIN mark ON journal.mark_id = mark.id", this::mapJournalRecordExpanded);
    }

    public List<JournalRecordExpanded> getAllByStudyGroup(int studyGroupId) {
        return jdbcTemplate.query(
                "SELECT journal.id, student_id, study_plan_id, in_time, count, mark_id, " +
                        "CONCAT(student.surname, ' ', student.name, ' ', student.second_name) AS student_full_name, " +
                        "subject.name AS subject_name, subject.short_name AS subject_short_name, " +
                        "exam_type.type AS exam_type, mark.name AS mark_name, mark.value AS mark_value " +
                        "FROM journal " +
                        "INNER JOIN student ON journal.student_id = student.id " +
                        "INNER JOIN study_plan ON journal.study_plan_id = study_plan.id " +
                        "INNER JOIN subject ON study_plan.subject_id = subject.id " +
                        "INNER JOIN exam_type ON study_plan.exam_type_id = exam_type.id " +
                        "INNER JOIN mark ON journal.mark_id = mark.id " +
                        "WHERE study_group_id = ?",
                this::mapJournalRecordExpanded,
                studyGroupId
        );
    }

    public JournalRecord edit(int id, JournalRecord journalRecord) {
        JournalRecord journalRecordToEdit = getById(id);
        if (journalRecordToEdit == null) {
            return null;
        }

        delete(id);
        return add(journalRecord);
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

    private JournalRecord mapJournalRecord(ResultSet resultSet, int i) throws SQLException {
        return new JournalRecord(
                resultSet.getInt("id"),
                resultSet.getBoolean("in_time"),
                resultSet.getInt("count"),
                resultSet.getInt("student_id"),
                resultSet.getInt("study_plan_id"),
                resultSet.getInt("mark_id")
        );
    }

    private JournalRecordExpanded mapJournalRecordExpanded(ResultSet resultSet, int i) throws SQLException {
        return new JournalRecordExpanded(
                resultSet.getInt("id"),
                resultSet.getBoolean("in_time"),
                resultSet.getInt("count"),
                resultSet.getInt("student_id"),
                resultSet.getInt("study_plan_id"),
                resultSet.getInt("mark_id"),
                resultSet.getString("student_full_name"),
                resultSet.getString("subject_name"),
                resultSet.getString("subject_short_name"),
                resultSet.getString("exam_type"),
                resultSet.getString("mark_name"),
                resultSet.getString("mark_value"));
    }
}
