package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for account objects.
 *
 * @author 700037512, 700074221
 */
public class Account implements Serializable {

    private String description;
    private static int newId = 0;
    private int id;
    private String handle;
    private int endorsementCount;
    private ArrayList<Post> userPosts;

    /**
     * Constructor for SocialMediaPlatform accounts.
     * @param handle the handle provided for the new account object.
     * @param description the description provided for the new account object.
     * @throws InvalidHandleException thrown if the new handle is empty,
     *                                has more than 30 characters,
     *                                or has white spaces.
     * @throws IllegalHandleException if the handle already exists in the platform.
     */
    public Account(String handle, String description) throws InvalidHandleException, IllegalHandleException {
        isHandleValid(handle);
        this.handle = handle;
        this.description = description;
        id = ++Account.newId;
        endorsementCount = 0;
        this.userPosts = new ArrayList<>();
    }

    /**
     * Constructor for miniSocialMediaPlatform accounts.
     * @param handle the handle provided for the new account object.
     * @throws InvalidHandleException thrown if the new handle is empty,
     *                                has more than 30 characters,
     *                                or has white spaces.
     * @throws IllegalHandleException if the handle already exists in the platform.
     */
    public Account(String handle) throws InvalidHandleException, IllegalHandleException {
        isHandleValid(handle);
        this.handle = handle;
        description = "";
        id = ++Account.newId;
        userPosts = new ArrayList<>();
    }

    /**
     * Returns an account's ID
     * @return an account object's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns an account's handle
     * @return an account object's handle.
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Sets new handle for an account object.
     * @param handle the new handle.
     * @throws InvalidHandleException thrown if the new handle is empty,
     *                                has more than 30 characters,
     *                                or has white spaces.
     * @throws IllegalHandleException if the handle already exists in the platform.
     */
    public void setHandle(String handle) throws InvalidHandleException, IllegalHandleException{
        isHandleValid(handle);
        this.handle = handle;
    }

    /**
     * Validation method to check if provided handle can be used (not empty, no spaces, less than 30 chars).
     * @param handle the unique non-empty name of the account holder.
     * @throws InvalidHandleException thrown if the new handle is empty,
     *                                has more than 30 characters,
     *                                or has white spaces.
     */
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

    /**
     * Sets a new description for an account.
     * @param description the new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description for a specified account.
     * @return the description for the account.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Increments endorsementCount, called when an account's post is endorsed.
     */
    public void addEndorsement(){
        this.endorsementCount++;
    }

    /**
     * Returns an account's endorsementCount
     * @return an account object's endorsementCount.
     */
    public int getEndorsementCount(){
        return endorsementCount;
    }

    /**
     * Sets attributes for removed account.
     */
    public void clearAccount(){
        id = -1;
        handle = null;
        description = null;
        userPosts = new ArrayList<>();
    }

    /**
     * Adds a post object to the user's post ArrayList.
     * @param post The new post object to be added
     */
    public void addPost(Post post) {
        userPosts.add(post);
    }

    /**
     * Sets new ID for an account object.
     * @param newId the new ID.
     */
    public static void setNewId(int newId) {
        Account.newId = newId;
    }

    /**
     * Returns an account overview
     * @return a string version of an account's overview
     */
    @Override
    public String toString() {
       return "\nID: " + id + "\nHandle: " + handle + "\nDescription: " + description +
               "\nPost count: " + userPosts.size() + "\nEndorse count: " + endorsementCount + "\n";
    }
}