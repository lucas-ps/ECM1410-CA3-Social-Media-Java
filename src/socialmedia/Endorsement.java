package socialmedia;

public class Endorsement extends Post{
    private Original parent;
    private PostType type;

    public Endorsement(Account author, String contents, Original parent) throws InvalidPostException {
        super(author, contents);
        this.parent = parent;
        this.type = PostType.ENDORSEMENT;
    }
}