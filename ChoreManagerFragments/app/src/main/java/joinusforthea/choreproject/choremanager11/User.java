package joinusforthea.choreproject.choremanager11;

import android.content.Context;

/**
 * Created by admin on 28/11/2017.
 */

class User {

    public User(){
        //needed empty constructor
    }

    private String name;
    private String avatar;
//    private List<Task> currentTasks = new ArrayList<>();
//    private List<Task> completedTasks = new ArrayList<>();
    private String id;
    private Context context;

    public User(String name, String avatar, String id) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;

    }

    //getters
    public String getName() {
        return name;
    }

    public String getAvatar() {

        return avatar;
    }

    public String getId() {
        return id;
    }

    public String toString(){
        return getName();
    }

//    public void addTask(Task task){
//        currentTasks.add(task);
//    }
//
//    public List<Task> getCurrentTasks(){
//        return currentTasks;
//    }
//
//    public void completeTask(Task task){
//        completedTasks.add(task);
//    }
//
//    public List<Task> getCompletedTasks(){
//        return completedTasks;
//    }

}
