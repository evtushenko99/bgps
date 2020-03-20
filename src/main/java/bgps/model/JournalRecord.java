package bgps.model;

import java.util.Objects;

public class JournalRecord {
    private int id;
    private boolean isInTime;
    private int count;
    private int studentId;
    private int studyPlanId;
    private int markId;

    public JournalRecord(int id, boolean isInTime, int count, int studentId, int studyPlanId, int markId) {
        this.id = id;
        this.isInTime = isInTime;
        this.count = count;
        this.studentId = studentId;
        this.studyPlanId = studyPlanId;
        this.markId = markId;
    }

    public JournalRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInTime() {
        return isInTime;
    }

    public void setInTime(boolean inTime) {
        isInTime = inTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudyPlanId() {
        return studyPlanId;
    }

    public void setStudyPlanId(int studyPlanId) {
        this.studyPlanId = studyPlanId;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalRecord journalRecord = (JournalRecord) o;
        return id == journalRecord.id &&
                isInTime == journalRecord.isInTime &&
                count == journalRecord.count &&
                studentId == journalRecord.studentId &&
                studyPlanId == journalRecord.studyPlanId &&
                markId == journalRecord.markId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isInTime, count, studentId, studyPlanId, markId);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", isInTime=" + isInTime +
                ", count=" + count +
                ", studentId=" + studentId +
                ", studyPlanId=" + studyPlanId +
                ", markId=" + markId +
                '}';
    }
}
