package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

/**
 * Created by dell on 5/3/2015.
 */
public class MeetingDetails {

   private String name;

    public MeetingDetails(String name, String title, String hr, String day, String month) {
        this.name = name;
        this.title = title;
        this.hr = hr;
        this.day = day;
        this.month = month;
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

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    private String title;

    private String hr;
    private String day;
    private String month;


}
