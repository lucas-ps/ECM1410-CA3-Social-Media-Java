package socialmedia;

public class Post {
    //ToDo Constraints (postId goes up in chronological order!! Just realised this may be tricky)
    // (100 character limit on contents)
    //ToDo Methods (constructor to create posts posts)
    // (remove from list using postId)
    //ToDo just realisied im doing parent classes wrong whoopsie i need to move this stuff
    //ToDo the show post children details method looks hard so thats going to be difficult, need a stringbuilder

    private static int freePostId = 0;
    private int postId;
    private String contents;
    private Account author;


    public Post(){
        postId = freePostId;
        freePostId++;
    }

    @Override
    public String toString() {
        return "";
    }
}