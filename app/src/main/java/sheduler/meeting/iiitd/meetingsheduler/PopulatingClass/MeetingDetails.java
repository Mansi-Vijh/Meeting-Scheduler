package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

/**
 * Created by dell on 5/3/2015.
 */
public class MeetingDetails {

    private String title;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int hr;
    private int date;
    private int month;

    private String meetingId;
    private String name;



    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public MeetingDetails(String name, String title, int hr, int date, int month, String meetingId, String status) {
        this.name = name;
        this.meetingId=meetingId;
        this.title = title;
        this.hr = hr;
        this.date = date;
        this.month = month;
        this.status=status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int day) {
        this.date = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }



}
