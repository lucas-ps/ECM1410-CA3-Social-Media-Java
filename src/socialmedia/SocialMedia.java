package socialmedia;

import java.io.*;
import java.util.*;

/**
 * Class for socialMedia objects.
 *
 * @author 700037512, 700074221
 */
public class SocialMedia implements SocialMediaPlatform, Serializable  {
    // TODO: (store accounts and be able to delete them given id)
    // TODO: delete an account
    // TODO: constraints (handle must be unique ==> put some constraints in but need to do the uniqueness)
    // TODO: Store accounts hashmap as <handle, account>
    private HashMap<String, Account> accounts;
    private HashMap<Integer, Post> posts;

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)){
                throw new IllegalHandleException("Handle has already been claimed.");
            }
        }
        Account newAccount = new Account(handle, description);
        int ID = newAccount.getId();
        return ID;
    }

    /**
     * The method removes the post from the platform. When a post is removed, all
     * its endorsements should be removed as well. All replies to this post should
     * be updated by replacing the reference to this post by a generic empty post.
     * <p>
     * The generic empty post message should be "The original content was removed
     * from the system and is no longer available.". This empty post is just a
     * replacement placeholder for the post which a reply refers to. Empty posts
     * should not be linked to any account and cannot be acted upon, i.e., it cannot
     * be available for endorsements or replies.
     * <p>
     * The state of this SocialMediaPlatform must be be unchanged if any exceptions
     * are thrown.
     *
     * @param handle handle of post to be removed.
     * @throws PostIDNotRecognisedException if the ID does not match to any post in
     *                                      the system.
     */
    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)) {
                account.clearAccount();
                accounts.remove(account.getId());
                for (Post post : posts.values()) {
                    if ((post.getAuthor().getHandle()).equals(account.getHandle())) {
                        if (post.getPostType().equals(PostType.COMMENT)){
                            ((Comment) post).makeOrphan();
                            posts.remove(post.getId());
                        }
                    }
                }
                return;
            }
        }
        throw new HandleNotRecognisedException("Handle not found");
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)) {
                account.setDescription(description);
                return;
            }
        }
        throw new HandleNotRecognisedException("Handle not found");
    }

    @Override
    public int getTotalOriginalPosts() {
        int originalPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.ORIGINAL)) {
                originalPostCount++;
            }
        }
        return originalPostCount;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        int endorsementPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.ENDORSEMENT)) {
                endorsementPostCount++;
            }
        }
        return endorsementPostCount;
    }

    @Override
    public int getTotalCommentPosts() {
        int commentPostCount = 0;
        for (Post post : posts.values()) {
            if (post.getPostType().equals(PostType.COMMENT)) {
                commentPostCount++;
            }
        }
        return commentPostCount;
    }

    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)){
                throw new IllegalHandleException("Handle has already been claimed.");
            }
        }
        Account newAccount = new Account(handle);
        int ID = newAccount.getId();
        return ID;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        for (Account account : accounts.values()) {
            if (account.getId() == id) {
                account.clearAccount();
                accounts.remove(account.getId());
                for (Post post : posts.values()) {
                    if ((post.getAuthor().getHandle()).equals(account.getHandle())) {
                        if (post.getPostType().equals(PostType.COMMENT)){
                            ((Comment) post).makeOrphan();
                            posts.remove(post.getId());
                        }
                    }
                }
                return;
            }
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle) throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)) {
             String formattedAccount = account.toString();
                return formattedAccount;
            }
        }
        throw new HandleNotRecognisedException("Handle not found");
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        for (Account account : accounts.values()) {
            if (account.getHandle().equals(handle)) {
                Original newOriginal = new Original(account, message);
                int ID = newOriginal.getId();
            }
        }
        throw new HandleNotRecognisedException("Handle not found");
    }

    @Override
    public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        return 0;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        return 0;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        return null;
    }

    @Override
    public int getMostEndorsedPost() {
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        Account mostPopularAccount = null;
        for (Account account : accounts.values()) {
            int popularity = 0;
            if (account.getEndorsementCount() > popularity) {
                popularity = account.getEndorsementCount();
                mostPopularAccount = account;
            }
        }
        return mostPopularAccount.getId();
    }

    @Override
    public int getNumberOfAccounts(){
        return accounts.size();
    }

    @Override
    public void savePlatform(String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        ObjectOutputStream outStream = new ObjectOutputStream(out);
        outStream.writeObject(this);
        outStream.close();
        out.close();
    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(filename);
        ObjectInputStream inStream = new ObjectInputStream(in);
        SocialMedia deserialized = (SocialMedia) inStream.readObject();
        this.accounts = deserialized.accounts;
        this.posts = deserialized.posts;
        inStream.close();
        in.close();
    }

    @Override
    public void erasePlatform(){
        accounts = new HashMap<Integer, Account>();
        posts = new HashMap<Integer, Post>();
        Account.setNewId(0);
        Post.setNewPostId(0);
    }
}