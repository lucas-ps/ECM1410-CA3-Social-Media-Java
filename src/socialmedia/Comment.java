package socialmedia;

import java.util.ArrayList;

/**
 * Class for comment objects.
 *
 * @author 700037512, 700074221
 */
public class Comment extends Post{
    // TODO: comments arent being added to the array list either
    private boolean isOrphan;
    private Post parent;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;
    private Post originalParent;

    /**
     *
     * @param author The account that created the post.
     * @param contents The contents of the comment message.
     * @param parent The post that the comment refers too.
     * @param originalParent The post's original parent (eg. if a comment, not the comment parent but the original post
     *                       being commented on)
     * @throws InvalidPostException if the message is empty or has more than
     * 	                             100 characters.
     * @throws HandleNotRecognisedException if the handle does not match to any
     *                                      account in the system.
     * @throws NotActionablePostException if the ID refers to a endorsement post.
     *                                     Endorsement posts are not endorsable.
     *                                     Endorsements are not transitive. For
     *                                     instance, if post A is endorsed by post
     *                                     B, and an account wants to endorse B, in
     *                                     fact, the endorsement must refers to A.
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

    /**
     * Constructor for a deleted comment taking the ID as a parameter.
     * @param id id of the post.
     */
    public Comment(int id) {
        super(id, "The original content was removed from the system and is no longer available.");
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
     * Getter method for the Parent of the Parent of the post after the immediate post has been deleted.
     * @return the Original Grand-Parent of the post.
     */
    public Post getOriginalParent() {
        return originalParent;
    }

    /**
     * Makes a comment an orphan when the post it refers to is deleted.
     */
    public void makeOrphan(){
        setPostType(PostType.DELETED);
        this.isOrphan = true;
        originalParent.addDeletedComment(this);
    }

    /**
     * Adds an endorsement to the current post's comment ArrayList.
     * @param endorsement the endorsement object to be added to the comment.
     */
    public void addEndorsementArrayList(Endorsement endorsement){
        endorsements.add(endorsement);
    }

    /**
     * Removes comment from an arraylist when a comment is deleted.
     * @param comment Comment to be removed.
     */
    @Override
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
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
     * Getter method for the parent of the post.
     * @return The parent of the comment.
     */
    public Post getParent() {
        return parent;
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
        return "\nId: " + this.getId() + "\nAccount: " + this.getAuthor().getHandle() + "\nNo. endorsements: " +
               getEndorsementCount() + " | No. comments: " + comments.size() + "\n" + this.getContents() + "\n";
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
                babyIndent + "| > "+ "Id: " + this.getId() + "\n" +
                indent +"Account: " + this.getAuthor().getHandle() + "\n" +
                indent + "No. endorsements: " + getEndorsementCount() + " | No. comments: " + this.getComments().size() + "\n" +
                indent + this.getContents() + "\n";

        return toString;
    }
}