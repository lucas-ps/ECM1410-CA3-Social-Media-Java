package socialmedia;

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Class for socialMedia objects.
 *
 * @author 700037512, 700074221
 */
public class SocialMedia implements SocialMediaPlatform, Serializable  {
    public HashMap<String, Account> accounts;
    public HashMap<Integer, Post> posts;
    // TODO: Change back to private once tested

    /**
     * Constructor method for the social media platform to implement the interfaces.
     */
    public SocialMedia(){
        accounts = new HashMap<String, Account> ();
        posts = new HashMap<Integer, Post> ();
    }
    // Account/Post getter methods

    /**
     * Creates a dummy post to set as a parent for comments who's parent has been deleted.
     * @return the dummy post found/newly created.
     */
    public Post dummyPost() {
        if (posts.containsKey(-1)) {
            return posts.get(-1);
        } else {
            Post dummyPost = new Post(-1);
            return dummyPost;
        }
    }

    /**
     * Finds an account object from accounts hashmap using the account's ID.
     * @param  ID the ID of the account being searched for.
     * @return the matching account object found if one is found.
     * @throws AccountIDNotRecognisedException if the ID does not match to any
     *                                         account in the system.
     */
    public Account getAccount(int ID) throws AccountIDNotRecognisedException {
        for (Account account : accounts.values()) {
            if (account.getId() == ID) {
                return account;
                }
            }
        throw new AccountIDNotRecognisedException("Account ID '" + ID +" not recognised.");
    }

    /**
     * Finds an account object from accounts hashmap using the account's handle.
     * @param  handle the string handle of the account being searched for.
     * @return the matching account object found if one is found.
     * @throws HandleNotRecognisedException if the handle does not match to any
     *                                       account in the system.
     */
    public Account getAccount(String handle) throws HandleNotRecognisedException {
        if (accounts.containsKey(handle)){
             return accounts.get(handle);
        } else{
            throw new HandleNotRecognisedException("Handle '"+ handle +"'not recognised.");
        }
    }

    /**
     * Finds a post object from the posts hashmap using the post's ID.
     * @param  ID The int ID of the post being searched for
     * @return the matching post object if one is found
     * @throws PostIDNotRecognisedException if the ID does not match to any
     *                                      post on the system
     */
    public Post getPost(int ID) throws PostIDNotRecognisedException {
        if (posts.containsKey(ID)){
            return posts.get(ID);
        } else{
            throw new PostIDNotRecognisedException("Post ID '"+ ID +"' not recognised.");
        }
    }

    /**
     * Gets all posts by a specified author
     * @param  author the author of the posts that are being searched for
     * @return an ArrayList of posts that were found
     */
    public ArrayList<Post> getPostsByAuthor(Account author) {
        ArrayList<Post> postsByAuthor = new ArrayList();
        for (Post post : posts.values()) {
            if ((post.getAuthor().getHandle()).equals(author.getHandle())) {
                postsByAuthor.add(post);
            }
        }
        return postsByAuthor;
    }

    /**
     * Remove comments and endorsements that were children of a removed post.
     * @param comments the ArrayList of comments to be removed.
     * @param endorsements the ArrayList of endorsements to be removed.
     */
    public void removeCommentsAndEndorsements(ArrayList<Comment> comments, ArrayList<Endorsement> endorsements) {
        // Remove comments that were children the removed post
        for (Comment comment : comments){
            comment.makeOrphan();
            comment.setParent(dummyPost());
        }
        // Remove endorsements that were children the removed post
        for (Endorsement endorsement : endorsements){
            this.posts.remove(endorsement.getId());
        }
    }


    /**
     * Removes posts in a provided ArrayList (helper method).
     * @param posts the ArrayList of posts to be removed.
     */
    public void removePosts(ArrayList<Post> posts){
        for (Post post : posts) {
            if (post.getPostType().equals(PostType.COMMENT)) {
                ArrayList<Endorsement> endorsements = ((Comment) post).getEndorsements();
                ArrayList<Comment> comments = ((Comment) post).getComments();
                removeCommentsAndEndorsements(comments, endorsements);
                this.posts.remove(post.getId());
            }
            else if (post.getPostType().equals(PostType.ORIGINAL)) {
                ArrayList<Comment> comments = ((Original) post).getComments();
                ArrayList<Endorsement> endorsements = ((Original) post).getEndorsements();
                removeCommentsAndEndorsements(comments, endorsements);
                this.posts.remove(post.getId());
            }
            else if (post.getPostType().equals(PostType.ENDORSEMENT)) {
                this.posts.remove(post.getId());
                ((Endorsement) post).removeEndorsementFromParent();
            }
        }
    }

    // Main methods

    /**
     * The method creates an account in the platform with the given handle.
     * <p>
     * The state of this SocialMediaPlatform must be be unchanged if any exceptions
     * are thrown.
     *
     * @param handle account's handle.
     * @throws IllegalHandleException if the handle already exists in the platform.
     * @throws InvalidHandleException if the new handle is empty, has more than 30
     *                                characters, or has white spaces.
     * @return the ID of the created account.
     *
     */
    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        if (accounts.containsKey(handle)){
            throw new IllegalHandleException("Handle '"+ handle +"' has already been claimed.");
        } else{
            Account newAccount = new Account(handle, description);
            int ID = newAccount.getId();
            accounts.put(handle, newAccount);
            return ID;
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        Account accountToBeRemoved = getAccount(handle);
        ArrayList<Post> postsByAuthor = getPostsByAuthor(accountToBeRemoved);
        accountToBeRemoved.clearAccount();
        accounts.remove(handle);
        removePosts(postsByAuthor);
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        Account accountToUpdate = getAccount(handle);
        accountToUpdate.setDescription(description);
    }

    @Override
    public int getTotalOriginalPosts() {
        int originalPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.ORIGINAL)) {
                originalPostCount++;
            }
        }
        return originalPostCount;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        int endorsementPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.ENDORSEMENT)) {
                endorsementPostCount++;
            }
        }
        return endorsementPostCount;
    }

    @Override
    public int getTotalCommentPosts() {
        int commentPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.COMMENT)) {
                commentPostCount++;
            }
        }
        return commentPostCount;
    }

    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        if (accounts.containsKey(handle)){
            throw new IllegalHandleException("Handle '"+ handle +"' has already been claimed.");
        } else{
            Account newAccount = new Account(handle);
            int ID = newAccount.getId();
            accounts.put(handle, newAccount);
            return ID;
        }
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        Account accountToBeRemoved = getAccount(id);

        ArrayList<Post> postsByAuthor = getPostsByAuthor(accountToBeRemoved);
        accountToBeRemoved.clearAccount();
        accounts.remove(accountToBeRemoved.getHandle());

        // Dealing with posts made by this account
        removePosts(postsByAuthor);
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException,
            IllegalHandleException, InvalidHandleException {
        Account accountToUpdate = getAccount(oldHandle);
        accountToUpdate.setHandle(newHandle);
        accounts.remove(oldHandle);
        accounts.put(newHandle, accountToUpdate);
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        Account accountToShow = getAccount(handle);
        return accountToShow.toString();
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        Account author = getAccount(handle);
        Original newPost = new Original(author, message);
        author.addPost(newPost);
        posts.put(newPost.getId(), newPost);
        return newPost.getId();
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException,
            NotActionablePostException {
        Account endorsingAccount = getAccount(handle);
        Post post = posts.get(id);
        Account postAuthorAccount = post.getAuthor();
        Endorsement newEndorsement = new Endorsement(endorsingAccount, post.getContents(), post);
        posts.put(newEndorsement.getId(), newEndorsement);
        return newEndorsement.getId();
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        Account commentingAccount = getAccount(handle);
        Comment newComment = new Comment(commentingAccount, message, getPost(id));
        posts.put(newComment.getId(), newComment);
        return newComment.getId();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        Post postToRemove = getPost(id);
        ArrayList<Post> posts = new ArrayList();
        posts.add(postToRemove);
        removePosts(posts);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        Post post = getPost(id);
        String stringyPost = post.toString();
        return stringyPost;
    }

    /**
     * The method builds a StringBuilder showing the details of the current post and
     * all its children posts. The format is as follows:
     *
     * <pre>
     * {@link #showIndividualPost(int) showIndividualPost(id)}
     * |
     * [for reply: replies to the post sorted by ID]
     * |  > {@link #showIndividualPost(int) showIndividualPost(reply)}
     * </pre>
     *
     * See an example:
     *
     * <pre>
     * ID: 1
     * Account: user1
     * No. endorsements: 2 | No. comments: 3
     * I like examples.
     * |
     * | > ID: 3
     *     Account: user2
     *     No. endorsements: 0 | No. comments: 1
     *     No more than me...
     *     |
     *     | > ID: 5
     *         Account: user1
     *         No. endorsements: 0 | No. comments: 1
     *         I can prove!
     *         |
     *         | > ID: 6
     *             Account: user2
     *             No. endorsements: 0 | No. comments: 0
     *             prove it
     * | > ID: 4
     *     Account: user3
     *     No. endorsements: 4 | No. comments: 0
     *     Can't you do better than this?
     *
     * | > ID: 7
     *     Account: user5
     *     No. endorsements: 0 | No. comments: 1
     *     where is the example?
     *     |
     *     | > ID: 10
     *         Account: user1
     *         No. endorsements: 0 | No. comments: 0
     *         This is the example!
     * </pre>
     *
     * Continuing with the example, if the method is called for post ID=5
     * ({@code showIndividualPost(5)}), the return would be:
     *
     * <pre>
     * ID: 5
     * Account: user1
     * No. endorsements: 0 | No. comments: 1
     * I can prove!
     * |
     * | > ID: 6
     *     Account: user2
     *     No. endorsements: 0 | No. comments: 0
     *     prove it
     * </pre>
     *
     * @param id of the post to be shown.
     * @return a formatted StringBuilder containing the details of the post and its
     *         children.
     * @throws PostIDNotRecognisedException if the ID does not match to any post in
     *                                      the system.
     * @throws NotActionablePostException   if the ID refers to an endorsement post.
     *                                      Endorsement posts do not have children
     *                                      since they are not endorsable nor
     *                                      commented.
     */
    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException,
            NotActionablePostException {
        StringBuilder showPostChildren = new StringBuilder();
        int indentationLevel = 1;
        Post post = getPost(id);
        showPostChildren.append(post.toString());
        if (post.getPostType().equals(PostType.COMMENT)) {
            postHelper(((Comment)post).getComments(), indentationLevel, showPostChildren);
        } else {
            postHelper(((Original)post).getComments(), indentationLevel, showPostChildren);
        }
        return showPostChildren;
    }


    /**
     * Helper method for showPostChildrenDetails, iterates trough provided comments ArrayList, sorts out indentation,
     * recursively calls showPostChildrenDetails.
     * @param comments The comment ArrayList provided for the post.
     * @param indentationLevel The level of indentation on the post.
     * @param showPostChildren Stringbuilder for the ShowPostChildrenDetails method.
     * @return
     * https://www.logicbig.com/how-to/java-string/java-indent-string.html
     * https://www.logicbig.com/tutorials/core-java-tutorial/java-12-changes/string-changes.html
     */
    public static StringBuilder postHelper(ArrayList<Comment> comments, int indentationLevel,
                                           StringBuilder showPostChildren ) {
        for (Comment comment: comments){
            if (comment.getPostType().equals(PostType.DELETED)){
                continue;
            }
            String commentString = "";
            commentString += comment.toString(indentationLevel);
            showPostChildren.append(commentString);
            if(!(comment.getComments().isEmpty())){
                postHelper(comment.getComments(),indentationLevel+1, showPostChildren);
            }
        }
        return showPostChildren;
    }

    @Override
    public int getMostEndorsedPost() {
        Post mostPopularPost = null;
        for(Post post : posts.values()){
            int popularity = -1;
            if (post.getEndorsementCount() > popularity) {
                popularity = post.getEndorsementCount();
                mostPopularPost = post;
            }
        }
        return mostPopularPost.getId();
    }

    @Override
    public int getMostEndorsedAccount() {
        Account mostPopularAccount = null;
        for (Account account : accounts.values()) {
            int popularity = -1;
            if (account.getEndorsementCount() > popularity) {
                popularity = account.getEndorsementCount();
                mostPopularAccount = account;
            }
        }
        return mostPopularAccount.getId();
    }

    @Override
    public int getNumberOfAccounts(){
        return accounts.size();
    }

    @Override
    public void savePlatform(String filename) throws IOException {
        try{
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream outStream = new ObjectOutputStream(out);
            outStream.writeObject(this);
            outStream.close();
            out.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(in);
            SocialMedia deserialized = (SocialMedia) inStream.readObject();
            this.accounts = deserialized.accounts;
            this.posts = deserialized.posts;
            inStream.close();
            in.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void erasePlatform(){
        accounts = new HashMap<String, Account>();
        posts = new HashMap<Integer, Post>();
        Account.setNewId(0);
        Post.setNewPostId(0);
    }
}