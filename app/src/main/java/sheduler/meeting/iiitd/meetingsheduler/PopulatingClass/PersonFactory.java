package sheduler.meeting.iiitd.meetingsheduler.PopulatingClass;

import android.graphics.drawable.shapes.Shape;

import com.google.android.gms.maps.model.Circle;

/**
 * Created by Masood on 5/2/2015.
 */
public class PersonFactory {
    //use getShape method to get object of type shape
    public Person getPerson(String personType){
        if(personType == null){
            return null;
        }
        if(personType.equalsIgnoreCase("STUDENT")){
            return new Student();

        } else if(personType.equalsIgnoreCase("PROFESSOR")){
            return new Professor();

        }
        return null;
    }
}
