package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for original objects.
 *
 * @author 700037512, 700074221
 */
public class Original extends Post implements Serializable {
    private ArrayList<Comment> comments;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> deletedComments;

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
        this.comments = new ArrayList<>();
        this.endorsements = new ArrayList<>();
        this.deletedComments = new ArrayList<>();
        setPostType(PostType.ORIGINAL);
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
     * Adds comments to an arraylist of deleted posts.
     * @param comment the comment to be deleted.
     */
    @Override
    public void addDeletedComment(Comment comment){
        this.deletedComments.add(comment);
    }

    /**
     * Getter method for deleted comments.
     * @return An arraylist of deleted comments.
     */
    public ArrayList<Comment> getDeletedComments(){
        return deletedComments;
    }

    /**
     * Adds an endorsement to the current post's comment ArrayList.
     * @param endorsement the endorsement object to be added to the comment.
     */
    public void addEndorsementArrayList(Endorsement endorsement){
       endorsements.add(endorsement);
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
    * The method generates a formatted string containing the details of a single
    * post. The format is as follows:
    *  ID: [post ID]
    *  Account: [account handle]
    *  No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
    *  [post message]
    */
    @Override
    public String toString() {
        return "\nId: " + this.getId() + "\nAccount: " + this.getAuthor().getHandle() + "\nNo. endorsements: " +
                this.getEndorsementCount() + " | No. comments: " + comments.size() + "\n" + this.getContents() + "\n";
    }
}