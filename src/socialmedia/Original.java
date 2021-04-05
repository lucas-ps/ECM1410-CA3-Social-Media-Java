package socialmedia;

import java.util.ArrayList;

public class Original extends Post{
    private int postId;
    private Account author;
    private String contents;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;

    public Original(Account author, String contents) throws InvalidPostException{
       this.author = author;
       isContentsValid(contents);
       this.contents = contents;
    }

    public static void isContentsValid(String contents) throws InvalidPostException{
        if (contents.equals(null)) {
            throw new InvalidPostException("Contents is null");
        }
        if (contents.length() > 100) {
            throw new InvalidPostException("Contents is over 100 characters. It is too long.");
        }
    }

    @Override
    public String toString() {
        return "\nId: " + postId + "\nAccount: " + author.getHandle() + "\nNo. endorsements: " +
                endorsements.size() + " | No. comments: " + comments.size() + "\n" + contents + "\n";
    }
}