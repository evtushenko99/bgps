package bgps.model;

public class JournalRecordExpanded {
    private int id;
    private boolean isInTime;
    private int count;
    private int studentId;
    private int studyPlanId;
    private int markId;
    private String studentFulName;
    private String subjectName;
    private String subjectShortName;
    private String examType;
    private String markName;
    private String markValue;

    public JournalRecordExpanded(int id, boolean isInTime, int count, int studentId, int studyPlanId, int markId, String studentFulName, String subjectName, String subjectShortName, String examType, String markName, String markValue) {
        this.id = id;
        this.isInTime = isInTime;
        this.count = count;
        this.studentId = studentId;
        this.studyPlanId = studyPlanId;
        this.markId = markId;
        this.studentFulName = studentFulName;
        this.subjectName = subjectName;
        this.subjectShortName = subjectShortName;
        this.examType = examType;
        this.markName = markName;
        this.markValue = markValue;
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

    public String getStudentFulName() {
        return studentFulName;
    }

    public void setStudentFulName(String studentFulName) {
        this.studentFulName = studentFulName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectShortName() {
        return subjectShortName;
    }

    public void setSubjectShortName(String subjectShortName) {
        this.subjectShortName = subjectShortName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getMarkValue() {
        return markValue;
    }

    public void setMarkValue(String markValue) {
        this.markValue = markValue;
    }
}
