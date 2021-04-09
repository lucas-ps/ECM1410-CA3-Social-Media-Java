package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

public class Original extends Post implements Serializable {
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;

    public Original(Account author, String contents) throws InvalidPostException{
       this.author = author;
       isContentsValid(contents);
       this.contents = contents;
    }

    /**
     * @return
     */
    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * @return the
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * @param comment
     */
    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    /**
     * @param endorsement
     */
    public void addEndorsement(Endorsement endorsement){
        this.endorsements.add(endorsement);
    }

    @Override
   /** Show individual post
    *  ID: [post ID]
    * Account: [account handle]
    * No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
    * [post message]
    */
    public String toString() {
        return "\nId: " + postId + "\nAccount: " + author.getHandle() + "\nNo. endorsements: " +
                endorsements.size() + " | No. comments: " + comments.size() + "\n" + contents + "\n";
    }
}