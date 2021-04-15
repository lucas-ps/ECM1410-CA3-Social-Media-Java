package socialmedia;

import java.util.ArrayList;

public class Comment extends Post{
    private boolean isOrphan;
    private Post parent;
    private Original originalPost;
    private PostType type;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;

    public Comment(Account author, String contents, Post parent)
            throws InvalidPostException, HandleNotRecognisedException {
        super(author, contents);
        isContentsValid(contents);
        this.isOrphan = false;
        this.type = PostType.COMMENT;
        this.parent = parent;
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

    public void makeOrphan(){
        this.isOrphan = true;
    }

    // TODO: Add in indent to comments
    /**
     * The method generates a formated string containing the details of a single
     * post. The format is as follows:
     *  ID: [post ID]
     *  Account: [account handle]
     *  No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
     *  [post message]
     */
    @Override
    public String toString(){
        return "\nId: " + postId + "\nAccount: " + author.getHandle() + "\nNo. endorsements: " +
                endorsements.size() + " | No. comments: " + comments.size() + "\n" + contents + "\n";
    }
}