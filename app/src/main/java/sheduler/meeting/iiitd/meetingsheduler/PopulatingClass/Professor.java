package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

/**
 * Created by Masood on 5/2/2015.
 */
public class Professor extends Person {
    String post;

    private static Professor instance ;

    public static synchronized Professor getInstance()
    {
        if (instance == null)
            instance = new Professor();

        return instance;
    }

    Professor(){
    }
    
    public Professor(String post, String name, String id, String type, String email, String courses) {
        super(name, id, type, email, courses);
        this.post = post;
    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
