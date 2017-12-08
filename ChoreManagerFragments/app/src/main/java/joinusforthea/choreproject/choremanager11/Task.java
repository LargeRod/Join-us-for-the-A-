package joinusforthea.choreproject.choremanager11;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 28/11/2017.
 */

public class Task  {
    private static DatabaseReference databasePeople  =  FirebaseDatabase.getInstance().getReference("users");
    private static User unassigned = new User("Unassigned","hollow_add",databasePeople.push().getKey());

    private String notes;
    private boolean completed;
    private User creator;
    private String footNote;
    private String duration;
    private User assignedTo;
    private String taskName;
    private String dueDate;
    private String id;

    //EV: Constructor
    public Task(String name, String idNumber, User c){
        id = idNumber;
        taskName = name;
        notes = "";
        setAssignedTo(unassigned);
        setCreator(c);
    }

    public Task(){
        //needs empty constructor
    }


//EV: getters and setters



    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCreator(User u){
        creator = u;
    }

    public User getCreator() {
        return creator;
    }

    public String getFootNote() {
        return footNote;
    }

    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String toString(){
        return getTaskName();
    }

    public String getId() {
        return id;
    }

    public void unassignTask(){
        setAssignedTo(unassigned);
    }

    public String getUserAvatar(){
        return getAssignedTo().getAvatar();
    }
}
