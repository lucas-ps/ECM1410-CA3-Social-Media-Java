package socialmedia;

public class Post {
    //TODO: Constraints (postId goes up in chronological order!! Just realised this may be tricky)
    // (100 character limit on contents)
    // Methods (constructor to create posts posts)
    // (remove from list using postId)
    // just realised im doing parent classes wrong whoopsie i need to move this stuff
    // the show post children details method looks hard so that's going to be difficult, need a string builder
    /**
     * The method builds a StringBuilder showing the details of the current post and
     * all its children posts. The format is as follows:
     *
     * <pre>
     * {@link //#showIndividualPost(int) showIndividualPost(id)}
     * |
     * [for reply: replies to the post sorted by ID]
     * |  > {@link //#showIndividualPost(int) showIndividualPost(reply)}
     * </pre>
     */

    public int postId;
    private static int newPostId = 0;
    public Account author;
    public String contents;

    public Post(Account author, String contents) throws InvalidPostException {
        postId = ++Post.newPostId;
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
    public String toString() {
        return "";
    }
}