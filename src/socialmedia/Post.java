package socialmedia;

import java.io.Serializable;

/**
 * Parent class of all posts.
 *
 * @author 700037512, 700074221
 */
public class Post implements Serializable {

    private int postId;
    private static int newPostId = 0;
    private Account author;
    private String contents;
    private int endorsementCount;
    private PostType postType;

    /**
     * Constructor for the SocialMediaPlatform posts.
     * @param author Account that created the post.
     * @param contents Content of the post.
     *
     * @throws HandleNotRecognisedException if the handle does not match to any
     * 	                                    account in the system.
     */
    public Post(Account author, String contents) throws HandleNotRecognisedException {
        postId = ++Post.newPostId;
        isAuthorValid(author);
        this.author = author;
        this.contents = contents;
        this.endorsementCount = 0;
        postType = null;
    }

    /**
     * Post constructor that generates a dummy post, used so that it can be overridden in child objects.
     * @param id the id of the post.
     * @param contents the contents of the post.
     */
    public Post(int id, String contents) {
        postId = id;
        this.contents = contents;
        author = null;
    }

    /**
     * Overridden in Comment.java to remove a comment from a comment's ArrayList
     * @param comment the comment object to be removed
     */
    public void removeComment(Comment comment) {
    }

    /**
     * Constructor for the SocialMediaPlatform dummy post for deleted posts.
     * @param id ID assigned to a deleted post.
     */
    public Post(int id) {
        this.postId = id;
        this.author = null;
        this.contents = "The original content was removed from the system and is no longer available.";
        setPostType(PostType.DELETED);
    }

    /**
     * Method to add Comments to the parent post arraylist so they can be orphaned when the post is deleted.
     * @param comment The comment to be added to the arraylist.
     * @throws NotActionablePostException if the handle does not match to any
     *                                     account in the system.
     */
    public void addComment(Comment comment) throws NotActionablePostException {
        if (this.postType.equals(PostType.COMMENT) || this.postType.equals((PostType.ORIGINAL))){
            ((Comment) this).addComment(comment);
        } else {
            throw new NotActionablePostException("This post cannot be commented on.");
        }
    }

    /**
     * Setter method for the contents of the post.
     * @param contents String contents of a post.
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     *Validation method checks if the author of the message is not null.
     * @param author The account the comment has come from.
     * @throws HandleNotRecognisedException if the handle does not match to any
     *                                      account in the system.
     */
    public void isAuthorValid(Account author) throws HandleNotRecognisedException {
        if (author == null) {
            throw new HandleNotRecognisedException("Account is null.");
        }
    }

    /**
     * Adds an endorsement to the counter for a post.
     * @throws NotActionablePostException if the ID refers to a endorsement post.
     *                                      Endorsement posts are not endorsable.
     *                                      Endorsements are not transitive. For
     *                                      instance, if post A is endorsed by post
     *                                      B, and an account wants to endorse B, in
     *                                      fact, the endorsement must refers to A.
     */
    public void addEndorsement() throws NotActionablePostException{
       isPostEndorsable(postType);
       this.endorsementCount++;
    }

    /**
     * Adds an endorsement to the current post's comment ArrayList.
     * @param endorsement the endorsement object to be added to the comment.
     * @param post the post the endorsement needs to be added too.
     */
    public void addEndorsementList(Endorsement endorsement, Post post) {
        if (postType == postType.ORIGINAL) {
            ((Original) post).addEndorsementArrayList(endorsement);
        }else if (postType == postType.COMMENT){
            ((Comment) post).addEndorsementArrayList(endorsement);
        }
    }

    /**
     * Method to remove an endorsement from the endorsement count when the endorsement is deleted.
     */
    public void removeEndorsement() {
        this.endorsementCount--;
    }

    /**
     * Setter method for NewPostId.
     * @param newPostId Internal counter to set postIds in chronological order.
     */
    public static void setNewPostId(int newPostId) {
        Post.newPostId = newPostId;
    }

    /**
     * Returns the type of post.
     * @return The type of post.
     */
    public PostType getPostType(){
        return postType;
    }

    /**
     *Validation method to check if the post is endorsable.
     * @param postType The type of post.
     * @throws NotActionablePostException if the ID refers to a endorsement post.
     *                                     Endorsement posts are not endorsable.
     *                                     Endorsements are not transitive. For
     *                                     instance, if post A is endorsed by post
     *                                     B, and an account wants to endorse B, in
     *                                     fact, the endorsement must refers to A.
     */
    public static void isPostEndorsable(PostType postType) throws NotActionablePostException {
        if (postType.equals(PostType.ORIGINAL)){
            return;
        } else if  (postType.equals(PostType.COMMENT)){
            return;
        }else {
            throw new NotActionablePostException("Attempted to act upon an not-actionable post.");
        }
    }

    /**
     * Getter method for endorsementCount
     * @return Endorsement count
     */
    public int getEndorsementCount() {
        return endorsementCount;
    }

    /**
     * ToString method to show the details of the post, overridden in child objects.
     * @return Blank string
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * ToString method to show the details of the post, overridden in child objects.
     * @param indentationLevel the indentation required for the showPostChildrenDetails method.
     * @return Blank string
     */
    public String toString(int indentationLevel) {
        return "";
    }

    /**
     * Returns the post's author.
     * @return The Account object corresponding to the post's author
     */
    public Account getAuthor() {
        return author;
    }

    /**
     * Getter method for post id.
     * @return Id of the post.
     */
    public int getId() {
        return this.postId;
    }

    /**
     * Getter method for the Contents of the post.
     * @return Contents of the message.
     */
    public String getContents() {
        return contents;
    }

    /**
     * Set the post type for a post.
     * @param postType the postType to be set.
     */
    public void setPostType(PostType postType){
        this.postType = postType;
    }

    /**
     * Overridden in Original.java, adds comments to an arraylist of deleted posts.
     * @param comment the comment to be deleted.
     */
    public void addDeletedComment(Comment comment){
    }
}