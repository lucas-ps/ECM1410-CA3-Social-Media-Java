package socialmedia;

import java.util.ArrayList;

public class Account{
    //TODO: constraints (handle must be unique ==> put some constraints in but need to do the uniqueness)
    // (ID must be created whilst the account is created and must be unique ==> think ive got this one)
    // (counting the endorsements an account has)
    // is an array list the best data type for the posts??

    private String description;
    private int newId = 0;
    private int id;
    private String handle;
    private ArrayList<Post> userPosts = new ArrayList<>();

    public Account(String handle, String description) throws InvalidHandleException {
        isHandleValid(handle);
        this.handle = handle;
        this.description = description;
        id = newId;
        newId = newId++;
    }

    public Account(String handle) throws InvalidHandleException{
        isHandleValid(handle);
        this.handle = handle;
        description = "";
        id = newId;
        newId = newId++;
    }

    public int getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) throws InvalidHandleException{
        isHandleValid(handle);
        this.handle = handle;
    }

    public static void isHandleValid(String handle) throws InvalidHandleException{
        if ("".equals(handle)) {
            throw new InvalidHandleException("Empty handle.");
        }
        if (handle.contains(" ")){
            throw new InvalidHandleException("Handle contains whitespace.");
        }
        if (handle.length()> 30){
            throw new InvalidHandleException("Handle is too long.");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    @Override
    //ToDo need to check this is the right format ==> it is but we need to sort the endorsement out
    public String toString() {
       return "\nID: " + id + "\nHandle: " + handle + "\nDescription: " + description +
               "Post count" + userPosts.size() + "\n Endorse count:" + "DUCK" + "\n";
    }
}