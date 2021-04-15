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