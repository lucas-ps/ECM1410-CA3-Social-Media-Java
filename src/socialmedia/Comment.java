package socialmedia;

public class Comment extends Post{
    private boolean isOrphan;
    private Post parent;
    private PostType type;

    public Comment(Account author, String contents, boolean isOrphan, Post parent)
            throws InvalidPostException {
        super(author, contents);
        this.isOrphan = isOrphan;
        this.type = PostType.COMMENT;
        this.parent = parent;

    }
}