package socialmedia;

public class Post {
    //Constraints (postId goes up in chronological order) (100 character limit on contents)
    //Methods (constructor to create posts posts) (remove from list using postId)

    private int postId;
    private String contents;
    private Account author;


    public Post(Account author, String contents){
        this.author = author;
        this.contents = contents;

    }

    public int removePost(int postId){
        return 0;
    }


}