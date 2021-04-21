import socialmedia.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Print helper method
	 * @param data the string to be printed to console
	 */
	public static void print(String data){System.out.println(data);  }

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id;

		/**
		 * Tests for account methods
		 * createAccount ✓, showAccount ✓, removeAccount ✓, updateAccountDescription ✓, changeAccountHandle ✓,
		 * getNumberOfAccounts, getMostEndorsedAccount
		 */
		try {
			// Creating an account
			id = platform.createAccount("my_handle");
			platform.createAccount("test_1", "testing");

			// Outputting account info
			String accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);
			String accountInfo1 = platform.showAccount("test_1");
			//print(accountInfo1);

			// Adding/changing account description
			platform.updateAccountDescription("my_handle", " test description");
			accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);

			// Changing an account's handle
			platform.changeAccountHandle("my_handle", "new_Handle");
			accountInfo = platform.showAccount("new_Handle");
			print(accountInfo);
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			// Removing an account
			platform.removeAccount(id);
			platform.createAccount("test_2");
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

			// TODO: getMostEndorsedAccount, getNumberOfAccounts
		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		}

		/**
		 * Tests for post methods
		 * createPost ✓, endorsePost ✓, commentPost ✓, deletePost ✓, showIndividualPost ✓,
		 * showPostChildrenDetails (+ postHelper), getTotalOriginalPosts, getTotalEndorsmentPosts, getTotalCommentPosts,
		 * getMostEndorsedPost
		 * // TODO: Endorsement count for posts not working
		 */
		try {
			// createPost, showIndividualPost
			platform.createPost("test_1", "hello world");
			print(platform.showIndividualPost(1));
			print(platform.showAccount("test_1"));

			// endorsePost
			platform.endorsePost("test_2", 1);
			print(platform.showIndividualPost(2));
			print(platform.showIndividualPost(1));
			print(platform.showAccount("test_2"));

			// commentPost
			platform.commentPost("test_2", 1, "hi");
			print(platform.showIndividualPost(3));
			print(platform.showIndividualPost(1));

			// showPostChildrenDetails
			platform.showPostChildrenDetails(1);

			// deletePost
			platform.deletePost(1);
			try {
				print(platform.showAccount("test_1"));
			} catch (Exception e) {
				print("Post successfully removed");
			}

		} catch(PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		}  catch(InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		}catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
			assert (true) : "NotActionablePostException";
		}

		/**
		 * Tests for platform methods
		 * savePlatform ✓, loadPlatform ✓, erasePlatform ✓
         */
		try {
			platform.savePlatform("testSave");
			// Saving hashmaps locally so that they can be used to see if loadPlatform() works
			HashMap<Integer, Post> localPosts = ((SocialMedia) platform).posts;
			HashMap<String, Account> localAccounts = ((SocialMedia) platform).accounts;

			platform.erasePlatform();

			platform.loadPlatform("testSave");
			assert ((SocialMedia) platform).accounts.equals(localAccounts) : "Accounts not loaded correctly";
			assert ((SocialMedia) platform).posts.equals(localPosts) : "Posts not loaded correctly";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
