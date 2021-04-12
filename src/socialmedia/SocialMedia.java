package socialmedia;

import java.io.*;
import java.util.HashMap;

/**
 * Class for socialMedia objects.
 *
 * @author 700037512, 700074221
 */
public class SocialMedia implements Serializable {
    // TODO: (store accounts and be able to delete them given id)
    //TODO: delete an account
    //TODO: constraints (handle must be unique ==> put some constraints in but need to do the uniqueness)
    private HashMap<Integer, Account> accounts;
    private HashMap<Integer, Post> posts;


    public int getNumberOfAccounts(){
        return accounts.size();
    }

    public void savePlatform(String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        ObjectOutputStream outStream = new ObjectOutputStream(out);
        outStream.writeObject(this);
        outStream.close();
        out.close();
    }

    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(filename);
        ObjectInputStream inStream = new ObjectInputStream(in);
        SocialMedia deserialized = (SocialMedia) inStream.readObject();
       this.accounts = deserialized.accounts;
       this.posts = deserialized.posts;
        inStream.close();
        in.close();
    }
}