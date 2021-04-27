package socialmedia;

import java.util.ArrayList;

/**
 * Class for comment objects.
 *
 * @author 700037512, 700074221
 */
public class Comment extends Post{
    private boolean isOrphan;
    private Post parent;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;

    /**
     *
     * @param author The account that created the post.
     * @param contents The contents of the comment message.
     * @param parent The post that the comment refers too.
     * @throws InvalidPostException if the message is empty or has more than
     * 	                             100 characters.
     * @throws HandleNotRecognisedException if the handle does not match to any
     *                                      account in the system.
     * @throws NotActionablePostException //TODO: this
     */

    public Comment(Account author, String contents, Post parent, Post originalParent)
            throws InvalidPostException, HandleNotRecognisedException, NotActionablePostException {
        super(author, contents);
        isContentsValid(contents);
        isOrphan = false;
        setPostType(PostType.COMMENT);
        this.parent = parent;
        endorsements = new ArrayList<Endorsement>();
        comments = new ArrayList<Comment>();
        this.originalParent = originalParent;
        parent.addComment(this);
    }

    //TODO: Documentation
    public Comment(int id) {
        this.postId = id;
        this.author = null;
        this.contents = "The original content was removed from the system and is no longer available.";
        comments = new ArrayList<>();
        setPostType(PostType.DELETED);
    }

    /**
     * Adds a comment to the current post's comment ArrayList.
     * @param comment the contents of the comment.
     */
    public void addComment(Comment comment){
        this.comments.add(comment);
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
     * Makes a comment an orphan when the post it refers to is deleted.
     */
    public void makeOrphan(){
       this.isOrphan = true;
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
     * Getter method for Comments.
     * @return an array list of comments.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Setter method for Parent post so it can be changed if the parent post is deleted.
     * @param parent Post the comment refers to.
     */
    public void setParent(Post parent) {
        this.parent = parent;
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
    public String toString(){
        return "\nId: " + postId + "\nAccount: " + author.getHandle() + "\nNo. endorsements: " +
               getEndorsementCount() + " | No. comments: " + comments.size() + "\n" + contents + "\n";
    }

    /**
     * toString which takes into account indentation
     * The method generates a formatted string containing the details of a single
     * post. The format is as follows:
     *  ID: [post ID]
     *  Account: [account handle]
     *  No. endorsements: [number of endorsements received by the post] | No. comments: [number of comments received by the post]
     *  [post message]
     */
    @Override
    public String toString(int indentationLevel){
        // Indent for all other lines
        String indent = "";
        for (int i = 0; i < indentationLevel; i++) {
            indent += "    ";
        }

        // Indent for first line
        String babyIndent = "";
        for (int i = 0; i < indentationLevel-1 ; i++) {
            babyIndent += "    ";
        }

        // Building the string
        String toString = babyIndent +"|\n" +
                babyIndent + "| > "+ "Id: " + postId + "\n" +
                indent +"Account: " + author.getHandle() + "\n" +
                indent + "No. endorsements: " + getEndorsementCount() + " | No. comments: " + comments.size() + "\n" +
                indent + contents + "\n";

        return toString;
    }
}