package socialmedia;

import java.util.ArrayList;

/**
 * Class for account objects.
 */
public class Account{
    //TODO: constraints (handle must be unique ==> put some constraints in but need to do the uniqueness)
    // (ID must be created whilst the account is created and must be unique ==> think ive got this one)
    // (counting the endorsements an account has)
    // is an array list the best data type for the posts??

    private String description;
    private int newId = 0;
    private int id;
    private String handle;
    private ArrayList<Post> userPosts;
    private int endorsementCount;

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
        userPosts = new ArrayList<>();
        endorsementCount = 0;
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
        id = newId;
        newId = newId++;
    }

    /**
     * @return an account object's id.
     */
    public int getId() {
        return id;
    }

    /**
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
     * @return an account object's endorsementCount.
     */
    public int getEndorsementCount(){
        return endorsementCount;
    }

    @Override
    //ToDo need to check this is the right format ==> it is but we need to sort the endorsement out
    public String toString() {
       return "\nID: " + id + "\nHandle: " + handle + "\nDescription: " + description +
               "Post count" + userPosts.size() + "\n Endorse count:" + "DUCK" + "\n";
    }
}