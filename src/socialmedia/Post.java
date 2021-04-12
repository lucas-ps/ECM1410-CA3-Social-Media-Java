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

    /**
     * Constructor for the SocialMediaPlatform posts.
     * @param author Account that created the post.
     * @param contents Content of the post.
     * @throws InvalidPostException  if the message is empty or has more than
     * 	                             100 characters.
     * @throws HandleNotRecognisedException if the handle does not match to any
     * 	                                    account in the system.
     */
    public Post(Account author, String contents) throws InvalidPostException, HandleNotRecognisedException {
        postId = ++Post.newPostId;
        this.author = author;
        isContentsValid(contents);
        this.contents = contents;
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


    @Override
    public String toString() {
        return "";
    }
}