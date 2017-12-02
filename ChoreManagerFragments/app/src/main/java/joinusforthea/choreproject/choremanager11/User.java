package joinusforthea.choreproject.choremanager11;

/**
 * Created by admin on 28/11/2017.
 */

class User {

    private String name;
    private String avatar;
    private Task[] currentTasks;
    private Task[] completedTasks;



    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }


    public void unassignTask(){
        //remove task from current tasks and "assign" it to user called unassigned
    }

    public void createTask(String name){
        //creates a new task and sets the creator as this user
        Task newTask = new Task(name, this);

    }

    //getters
    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}
