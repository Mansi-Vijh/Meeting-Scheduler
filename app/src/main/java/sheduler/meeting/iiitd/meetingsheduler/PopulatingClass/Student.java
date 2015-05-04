package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

/**
 * Created by Masood on 5/2/2015.
 */
public class Student extends Person {
    String year;
    String stream;
    String program;

    private static Student instance ;

    public static synchronized Student getInstance()
    {
        if (instance == null)
            instance = new Student();

        return instance;
    }

    public Student() {
        super();
    }

    public String getStream() {
        return stream;
    }

    public Student(String year, String stream, String program, String name ,String id, String type, String email, String courses ) {
        super(name, id, type, email, courses);
        this.year = year;
        this.stream = stream;
        this.program = program;

    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
