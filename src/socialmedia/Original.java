package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for original objects.
 *
 * @author 700037512, 700074221
 */
public class Original extends Post implements Serializable {
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;
    private PostType type;

    /**
     * Constructor for Original post objects.
     * @param author The author object corresponding to who is creating the post.
     * @param contents The contents string of the post.
     * @throws InvalidPostException  if the message is empty or has more than
     * 	                             100 characters.
     * @throws HandleNotRecognisedException if the handle does not match to any
     * 	                                    account in the system.
     */
    public Original(Account author, String contents) throws InvalidPostException, HandleNotRecognisedException {
        super(author, contents);
        isContentsValid(contents);
        this.endorsements = new ArrayList<Endorsement>();
        this.comments = new ArrayList<Comment>();
        this.type = PostType.ORIGINAL;
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

    /**
     * Returns the ArrayList of Endorsement objects corresponding to the post.
     * @return all endorsements for the current post
     */
    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Returns the ArrayList of comments objects corresponding to the post.
     * @return all comments for the current post.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the current post's comment ArrayList.
     * @param comment the contents of the comment.
     */
    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    /**
     * Adds an endorsement to the current post's comment ArrayList.
     * @param endorsement the endorsement object to be added to the comment.
     */
    public void addEndorsement(Endorsement endorsement){
        this.endorsements.add(endorsement);
    }

   /**
    * The method generates a formatted string containing the details of a single
    * post. The format is as follows:
    *  ID: [post ID]
    *  Account: [account handle]
    *  No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
    *  [post message]
    */
    @Override
    public String toString() {
        return "\nId: " + postId + "\nAccount: " + author.getHandle() + "\nNo. endorsements: " +
                endorsements.size() + " | No. comments: " + comments.size() + "\n" + contents + "\n";
    }
}