package socialmedia;

import java.io.Serializable;

/**
 * Parent class of all posts.
 *
 * @author 700037512, 700074221
 */
public class Post implements Serializable {
    //TODO: Constraints (postId goes up in chronological order!! Just realised this may be tricky)
    // (100 character limit on contents)
    // Methods (constructor to create posts posts)
    // (remove from list using postId)
    // the show post children details method looks hard so that's going to be difficult, need a string builder
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
    public Post(Account author, String contents) throws InvalidPostException, HandleNotRecognisedException {
        postId = ++Post.newPostId;
        isAuthorValid(author);
        this.author = author;
        isContentsValid(contents);
        this.contents = contents;
        this.endorsementCount = 0;
        this.postType = null;
    }

    /**
     *Validation method checks if the contents of the message is valid.
     * @param contents What the author has said.
     * @throws InvalidPostException  if the message is empty or has more than
     * 	                             100 characters.
     */
    public static void isContentsValid(String contents) throws InvalidPostException{
        if (contents.equals("")) {
            throw new InvalidPostException("Contents is empty.");
        }
        if (contents.length() > 100) {
            throw new InvalidPostException("Contents is over 100 characters. It is too long.");
        }
    }

    // TODO: Check in main class if author handle is seen in hashmap
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

    //TODO: the documentation
    /**
     *
     * @param newPostId
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
        if((postType == PostType.ORIGINAL) && (postType == PostType.COMMENT)){
            return;
        } else{
            throw new NotActionablePostException("Attempted to act upon an not-actionable post");
        }
    }

    /**
     * @return
     */
    public int getEndorsementCount() {
        return endorsementCount;
    }

    /**
     *ToString method to show the details of the post.
     * @return
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

    //TODO: documetentation
    public int getId() {
        return this.postId;
    }

    public String getContents() {
        return contents;
    }
}