package socialmedia;

import java.util.ArrayList;

public class Account{
    // constraints (handle must be unique) (ID must be created whilst the account is created and must be unique ==> think ive got this one)
    //methods (constructor to create account) (store accounts and be able to delete them given id)
    private String description;
    private int id;
    private int newId = 0;
    private String handle;
    private ArrayList<Post> userPosts = new ArrayList<>();

    public Account(String handle, String description) throws InvalidHandleException {
        isHandleValid(handle);
        this.handle = handle;
        this.description = description;
        id = newId;
        newId = newId++;
    }

    public int getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public static void isHandleValid(String handle) throws InvalidHandleException{
        if ("".equals(handle)) {
            throw new InvalidHandleException("Empty handle.");
        }
        if (handle.contains(" ")){
            throw new InvalidHandleException("Handle contains whitespace.");
        }
        if (handle.length()> 20){
            throw new InvalidHandleException("Handle is too long.");
        }
    }

    @Override
    //need to check this is the right format
    public String toString() {
        return "Account{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", newId=" + newId +
                ", handle='" + handle + '\'' +
                ", userPosts=" + userPosts +
                '}';
    }
}