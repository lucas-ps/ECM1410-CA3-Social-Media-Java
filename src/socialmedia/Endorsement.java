package socialmedia;

import java.io.Serializable;

/**
 * Class for endorsement objects.
 *
 * @author 700037512, 700074221
 */
public class Endorsement extends Post implements Serializable {
    private Post parent;
    /**
     * Constructor for endorsement objects, adds an endorsement to the parent's endorsement counter.
     * @param author The author object corresponding to who is creating the comment.
     * @param contents The contents string of the comment.
     * @param parent The parent (Original/Comment) object that the comment is commenting on.
     * @throws HandleNotRecognisedException if the handle does not match to any
     * 	                                    account in the system.
     * @throws NotActionablePostException if the ID refers to a endorsement post.
     *                                    Endorsement posts are not endorsable.
     *                                    Endorsements are not transitive. For
     *                                    instance, if post A is endorsed by post
     *                                    B, and an account wants to endorse B, in
     *                                    fact, the endorsement must refers to A.
     */
    public Endorsement(Account author, String contents, Post parent) throws
            HandleNotRecognisedException, NotActionablePostException {
        super(author, contents);
        this.setContents(parent.getContents());
        this.parent = parent;
        parent.getAuthor().addEndorsement();
        setPostType(PostType.ENDORSEMENT);
        author.addEndorsement();
        parent.addEndorsement();
    }

    /**
     * Returns the parent (original/comment) post of the current endorsement post.
     * @return The parent of the current endorsement object.
     */
    public Post getParent() {
        return parent;
    }

    /**
     * Remove Endorsement from parent comment.
     */
    public void removeEndorsementFromParent(){
        this.getParent().removeEndorsement();
    }


    /**
     * Returns a string for the endorsement in the form "EP@" + [endorsed account handle] + ": " + [endorsed message]
     * @return The endorsement as a string
     */
    @Override
    public String toString(){
        String outputStr = "EP@" + this.getAuthor().getHandle() + ": " + this.getContents();
        return outputStr;
    }
}