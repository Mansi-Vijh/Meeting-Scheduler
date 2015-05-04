package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

/**
 * Created by dell on 4/19/2015.
 */
public class ProfessorDetails {

    private String profName;
    private String about;
    private String time;
    private String date;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private String objectId;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    private String post;

    public String getCourse() {
        return course;
    }

    public ProfessorDetails(String profName, String post, String course, String objectId) {
        this.objectId=objectId;
        this.profName = profName;
        this.course = course;
        this.post=post;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    private String course;

    public ProfessorDetails(String profName, String about, String time, String date, String meetingId, String status, String month) {
        this.profName = profName;
        this.about = about;
        this.time = time;
        this.date = date;
        this.meetingId = meetingId;
//        this.meetingId = "123";
        this.status = status;
        this.month = month;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    private String meetingId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public ProfessorDetails(String profName, String about, String time, String date, String month, String status) {
        this.profName = profName;
        this.status=status;
        this.about = about;
        this.time = time;
        this.meetingId = "123";
        this.date = date;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    private String month;



}
