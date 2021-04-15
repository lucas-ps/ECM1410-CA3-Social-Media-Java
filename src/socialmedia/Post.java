package socialmedia;

import java.io.Serializable;

/**
 * Parent class of all posts.
 *
 * @author 700037512, 700074221
 */
public class Post implements Serializable {
    // TODO: the show post children details method looks hard so that's going to be difficult, need a string builder
    /*
     * The method builds a StringBuilder showing the details of the current post and
     * all its children posts. The format is as follows:
     *
     * <pre>
     * {@link //#showIndividualPost(int) showIndividualPost(id)}
     * |
     * [for reply: replies to the post sorted by ID]
     * |  > {@link //#showIndividualPost(int) showIndividualPost(reply)}
     * </pre>
     */

    public int postId;
    private static int newPostId = 0;
    public Account author;
    public String contents;
    public int endorsementCount;
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
        this.postType = null;
    }

    /**
     * Constructor for the SocialMediaPlatform dummy post for deleted posts.
     * @param id ID assigned to a deleted post.
     *
     */
    public Post(int id) {
        this.postId = id;
        this.author = null;
        this.contents = "The original content was removed from the system and is no longer available.";
        this.postType = PostType.DELETED;
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
    public void addEndorsement() throws NotActionablePostException {
        isPostEndorsable(this.getPostType());
        this.endorsementCount++;
    }
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
     *Used in addEndorsement method.
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
        if((postType.equals(PostType.ORIGINAL)) || (postType.equals(PostType.COMMENT))){
            return;
        } else{
            throw new NotActionablePostException("Attempted to act upon an not-actionable post");
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
     *ToString method to show the details of the post.
     * @return Blank string
     */
    @Override
    public String toString() {
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
     * Getter method for the Contents of the post
     * @return Contents of the message.
     */
    public String getContents() {
        return contents;
    }
}