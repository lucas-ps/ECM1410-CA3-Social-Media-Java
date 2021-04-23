import socialmedia.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * // TODO: Documentation
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author  700037512, 700074221
 * @version 3.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Print helper method
	 * @param data the string to be printed to console
	 */
	public static void print(String data){System.out.println(data);  }

	/**
	 * Print helper method
	 * @param data the int to be printed to console
	 */
	public static void print(int data){System.out.println(data);  }

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
		 * Tests for platform methods
		 * savePlatform ✓, loadPlatform ✓, erasePlatform ✓
		 */
		try {
			/**
			 * savePlatform
			 * - IOException if file issues ✓
			 * - Should create a file called "testSave" ✓
			 */
			platform.savePlatform("testSave");
			// Saving hashmaps locally so that they can be used to see if loadPlatform() works
			HashMap<Integer, Post> localPosts = ((SocialMedia) platform).posts;
			HashMap<String, Account> localAccounts = ((SocialMedia) platform).accounts;

			/**
			 * erasePlatform
			 * - Should clear all arraylists, hashmaps and counters ✓
 			 */
			platform.erasePlatform();
			assert (platform.getNumberOfAccounts() == 0) : "Accounts hashmap has not been reset";
			assert (platform.getTotalOriginalPosts() == 0) : "Posts hashmap has not been reset";

			/**
			 * loadPlatform
			 * - Hashmaps imported should be the same as before the platform was erased ✓
 			 */
			platform.loadPlatform("testSave");
			assert ((SocialMedia) platform).accounts.equals(localAccounts) : "Accounts not loaded correctly";
			assert ((SocialMedia) platform).posts.equals(localPosts) : "Posts not loaded correctly";

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/**
		 * Tests for account methods
		 * createAccount ✓, showAccount ✓, updateAccountDescription ✓, changeAccountHandle ✓, getNumberOfAccounts ✓,
		 * getMostEndorsedAccount ✓ removeAccount ✓
		 */
		try {
			/**
			 * createAccount
			 * - Handle cant be same as another (IllegalHandleException)
			 * - Handle can't be empty, have more than 30 characters, or have a space in it (InvalidHandleException)
			 * Should return correct account IDs - 0, 1
 			 */

			assert (platform.createAccount("my_handle") == 1) : "Incorrect account ID returned for createAccount." +
					"";
			assert (platform.createAccount("test_1", "testing") == 2) : "Incorrect account ID returned ";
			platform.createAccount("test_1", "testing"); // Should throw IllegalHandleException

			/**
			 * showAccount
			 * - Provided handle must exist (HandleNotRecognisedException)
			 * - Account info should be returned as a string in the form :
			 * "TODO"
 			 */
			String accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);
			String accountInfo1 = platform.showAccount("test_1");
			//print(accountInfo1);

			/**
		 	 * updateAccountDescription
			 * - Provided handle must exist (HandleNotRecognisedException)
			 * - Using showAccount after changing account description should return :
			 * "TODO"
 			 */
			platform.updateAccountDescription("my_handle", " test description");
			accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);

			/**
			 * changeAccountHandle
			 * - Provided handle must exist (HandleNotRecognisedException)
			 * - Handle cant be same as another (IllegalHandleException)
			 * - Handle can't be empty, have more than 30 characters, or have a space in it (InvalidHandleException)
			 * - Using showAccount after changing account description should return :
			 * "TODO"
 			 */
			platform.changeAccountHandle("my_handle", "new_Handle");
			accountInfo = platform.showAccount("new_Handle");
			//print(accountInfo);

			/**
			 * getNumberOfAccounts
			 * - Using getNumberOfAccounts should return int 2 as two accounts currently exist on the system
			 */
			assert (platform.getNumberOfAccounts() == 2 ) : "getNumberOfAccounts calculated incorrect account count";

			/**
			 * getMostEndorsedAccount
			 * - Using getMostEndorsedAccount should return int 1 as it is the account ID with the most endorsements
			 * currently
 			 */
			int postID = platform.createPost("test_1", "testPost");
			platform.endorsePost("new_Handle", postID);
			assert (platform.getMostEndorsedAccount() == 1 ) : "getMostEndorsedAccount returned incorrect account ID";

			/**
			 * removeAccount
			 * - Account ID must exist on the system (AccountIDNotRecognisedException)
			 * - Using getNumberOfAccounts after removing an account should return int 2 as one of the 3 accounts has been
			 * removed
			 */
			platform.removeAccount(0);
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";
			platform.createAccount("test_2");

		} catch (IllegalHandleException e) {
			assert (true) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		} catch (InvalidPostException e) {
			e.printStackTrace();
		} catch (PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		} catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		}

		/**
		 * Tests for post methods
		 * createPost ✓, endorsePost ✓, commentPost ✓, deletePost ✓, showIndividualPost ✓,
		 * showPostChildrenDetails (+ postHelper) ✓, getTotalOriginalPosts , getTotalEndorsmentPosts ✓,
		 * getTotalCommentPosts ✓, getMostEndorsedPost
		 */
		try {
			/**
			 * createPost
			 * - Account handle must exist on system (HandleNotRecognisedException)
			 * - Post contents must be valid (InvalidPostException)
			 */
			// createPost, showIndividualPost
			platform.createPost("test_1", "Main test post");

			/**
			 * showIndividualPost
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Using showIndividualPost for the post previously created should return the string :
			 * "TODO"
			 */
			platform.showIndividualPost(1);

			/**
			 * showAccount
			 * - Account handle should exist on the system (HandleNotRecognisedException)
			 * - Using showAccount("test_1") should return :
			 * "TODO"
			 */
			platform.showAccount("test_1");

			/**
			 * endorsePost
			 * - Account handle should exist on the system (HandleNotRecognisedException)
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Endorsement posts should not be endorsable (NotActionablePostException)
			 * - Using showIndividualPost for the previously created post should return :
			 * "TODO"
 			 */
			platform.endorsePost("test_2", 1);
			//print(platform.showIndividualPost(2));
			//print(platform.showIndividualPost(1));
			//print(platform.showAccount("test_2"));

			/**
			 * commentPost
			 * - Account handle should exist on the system (HandleNotRecognisedException)
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Endorsement posts should not be commentable (NotActionablePostException)
			 * - Using showIndividualPost should return :
			 * "TODO"
 			 */
			int firstComment = platform.commentPost("test_2", 1, "Comment under main test post 1");
			platform.commentPost("test_1", firstComment, "Comment under first comment");
			platform.commentPost("test_1", 3, "Comment under id 3");
			firstComment = platform.commentPost("test_2", 1, "Comment under main test post 1");

			//print(platform.showIndividualPost(3));
			//print(platform.showIndividualPost(1));

			/**
			 * showPostChildrenDetails
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Endorsement posts should not be commentable (NotActionablePostException)
			 * - Using showPostChildrenDetails should return:
			 * "TODO"
 			 */
			platform.showPostChildrenDetails(1);
			//System.out.println(platform.showPostChildrenDetails(1));

			/**
			 * getTotalCommentPosts
			 * - Using getTotalCommentPosts should return int 4 ✓
 			 */
			int totalComment = platform.getTotalCommentPosts();
			assert (totalComment == 4) : "getTotalCommentPosts comment count not calculated correctly";

			/**
			 * getTotalEndorsmentPosts
			 * - Using getTotalEndorsmentPosts should return int 1 ✓
 			 */
			int totalEndorse = platform.getTotalEndorsmentPosts();
			assert (totalEndorse == 1) : "getTotalEndorsmentPosts endorsement count not calculated correctly";

			/**
			 * getTotalOriginalPosts
			 * - Using getTotalOriginalPosts should return int 2 ✓
 			 */
			int totalOriginal = platform.getTotalOriginalPosts();
			assert (totalOriginal == 2) : "getTotalOriginalPosts original count not calculated correctly";

			/**
			 * getMostEndorsedPost
			 * Using getMostEndorsedPost should return TODO
			 */
			int postID = platform.getMostEndorsedPost();

			/**
			 * deletePost
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Using getTotalOriginalPosts should return int 1 ✓
			 */
			platform.deletePost(1);
			try {
				platform.showAccount("test_1");
			} catch (Exception e) {
				print("Post successfully removed");
			}
		} catch(PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		}  catch(InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (true) : "HandleNotRecognisedException thrown incorrectly";
		}catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		}
	}
}
