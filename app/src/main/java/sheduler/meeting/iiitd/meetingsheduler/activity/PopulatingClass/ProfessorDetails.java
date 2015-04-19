package sheduler.meeting.iiitd.meetingsheduler.activity.PopulatingClass;

/**
 * Created by dell on 4/19/2015.
 */
public class ProfessorDetails {

    private String profName;
    private String about;
    private String time;
    private String date;

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
