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

    private int postId;
    private String contents;
    private Account author;


    public Post(){
    }

    @Override
    public String toString() {
        return "";
    }
}