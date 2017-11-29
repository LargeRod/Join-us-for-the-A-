package joinusforthea.choreproject.choremanager11;

import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by admin on 28/11/2017.
 */

public class Task {


    private Item[] requiredEquipment;
    private String note;
    private boolean completed;
    private User creator;
    private String footNote;
    private Duration duration;
    private User assignedTo;
    private String taskName;
    private Date deadline;

    //EV: Constructor


    public Task(String name, User user) {
        taskName = name;
        creator = user;
        //by default, the class should be assigned to the user called "unassigned"
        //assignedTo = unassigned;
    }





//EV: getters and setters

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Item[] getRequiredEquipment() {
        return requiredEquipment;
    }

    public void setRequiredEquipment(Item[] requiredEquipment) {
        this.requiredEquipment = requiredEquipment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getFootNote() {
        return footNote;
    }

    public void setFootNote(String footNote) {
        this.footNote = footNote;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }





}
