package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

import java.util.ArrayList;

/**
 * Created by Masood on 5/2/2015.
 */
public class Person {

    String name ;
    String id ;
    String type ;
    String email;
    String courses ;

    Person(){

    }

    public Person(String name, String id, String type, String email, String courses) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.email = email;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }






}
