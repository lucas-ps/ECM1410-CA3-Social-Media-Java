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

    public static void isContentsValid(String contents) throws InvalidPostException{
        if (contents.equals("")) {
            throw new InvalidPostException("Contents is empty.");
        }
        if (contents.length() > 100) {
            throw new InvalidPostException("Contents is over 100 characters. It is too long.");
        }
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