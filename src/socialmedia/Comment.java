package socialmedia;

import java.util.ArrayList;

public class Comment extends Post{
    private boolean isOrphan;
    private Post parent;
    private Original originalPost;
    private PostType type;
    private ArrayList<Endorsement> endorsements;
    private ArrayList<Comment> comments;

    public Comment(Account author, String contents, boolean isOrphan, Post parent)
            throws InvalidPostException, HandleNotRecognisedException {
        super(author, contents);
        this.isOrphan = isOrphan;
        this.type = PostType.COMMENT;
        this.parent = parent;

    }
}