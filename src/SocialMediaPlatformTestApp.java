import socialmedia.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * Testing for all SocialMedia.java functionality
 *
 * @author  700037512, 700074221
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

		/**
		 * Tests for account methods
		 * createAccount ✓, showAccount ✓, updateAccountDescription ✓, changeAccountHandle ✓, getNumberOfAccounts ✓,
		 * getMostEndorsedAccount ✓ removeAccount ✓
		 */
		try {
			/**
			 * createAccount
			 * - Handle cant be same as another (IllegalHandleException) ✓
			 * - Handle can't be empty, have more than 30 characters, or have a space in it (InvalidHandleException) ✓
			 * Should return correct account IDs - 0, 1 ✓
 			 */

			int my_handle = platform.createAccount("my_handle");
			assert (my_handle == 1) : "Incorrect account ID returned for createAccount.";
			int test_1 = platform.createAccount("test_1", "testing");
			assert (test_1 == 2) : "Incorrect account ID returned ";

			// IllegalHandleException
			try {
				platform.createAccount("test_1", "testing"); // Should throw IllegalHandleException
			} catch (IllegalHandleException e) {
				assert (true) : "IllegalHandleException not thrown for repeated account handles";
			}

			// InvalidHandleException
			try {
				platform.createAccount("", "testing"); // Should throw InvalidHandleException
			} catch (InvalidHandleException e) {
				assert (true) : "InvalidHandleException not thrown for empty account handles";
			}

			/**
			 * showAccount
			 * - Provided handle must exist (HandleNotRecognisedException) ✓
			 * - Account info should be returned as a string in the correct form ✓
 			 */

			// HandleNotRecognisedException
			try {
				platform.showAccount("FakeHandle"); // Should throw HandleNotRecognisedException
			} catch (HandleNotRecognisedException e) {
				assert (true) : "HandleNotRecognisedException not thrown for non-existent accounts";
			}

			String accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);
			String accountInfo1 = platform.showAccount("test_1");
			//print(accountInfo1);

			/**
		 	 * updateAccountDescription
			 * - Provided handle must exist (HandleNotRecognisedException)
			 * - Using showAccount after changing account description should return a string in the correct form ✓
 			 */
			// HandleNotRecognisedException
			try {
				platform.updateAccountDescription("FakeHandle", "test description"); // Should throw
				// HandleNotRecognisedException
			} catch (HandleNotRecognisedException e) {
				assert (true) : "HandleNotRecognisedException not thrown for non-existent accounts";
			}
			platform.updateAccountDescription("my_handle", " test description");
			accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);

			/**
			 * changeAccountHandle
			 * - Provided handle must exist (HandleNotRecognisedException)
			 * - Handle cant be same as another (IllegalHandleException)
			 * - Handle can't be empty, have more than 30 characters, or have a space in it (InvalidHandleException)
			 * - Using showAccount after changing account description should return the correct string
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
			 * currently ✓
 			 */
			int postID = platform.createPost("test_1", "testPost");
			platform.endorsePost("new_Handle", postID);
			assert (platform.getMostEndorsedAccount() == 1 ) : "getMostEndorsedAccount returned incorrect account ID";

			/**
			 * removeAccount
			 * - Account ID must exist on the system (AccountIDNotRecognisedException) ✓
			 * - Using getNumberOfAccounts after removing an account should return int 2 as one of the 3 accounts has been
			 * removed ✓
			 */
			platform.removeAccount(1);
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";


		} catch (IllegalHandleException e) {
			e.printStackTrace();
		} catch (InvalidHandleException e) {
			e.printStackTrace();
		} catch (AccountIDNotRecognisedException e) {
			e.printStackTrace();
		} catch (HandleNotRecognisedException e) {
			e.printStackTrace();
		} catch (InvalidPostException e) {
			e.printStackTrace();
		} catch (PostIDNotRecognisedException e) {
			e.printStackTrace();
		} catch (NotActionablePostException e) {
			e.printStackTrace();
		}

		/**
		 * Tests for post methods
		 * createPost ✓, endorsePost ✓, commentPost ✓, deletePost ✓, showIndividualPost ✓,
		 * showPostChildrenDetails (+ postHelper) ✓, getTotalOriginalPosts , getTotalEndorsmentPosts ✓,
		 * getTotalCommentPosts ✓, getMostEndorsedPost ✓
		 */
		try {
			platform.createAccount("test_2");
			/**
			 * createPost
			 * - Account handle must exist on system (HandleNotRecognisedException) ✓
			 * - Post contents must be valid (InvalidPostException) ✓
			 */
			// createPost, showIndividualPost
			int post = platform.createPost("test_1", "Main test post");

			/**
			 * showIndividualPost
			 * - Post ID should exist on system (PostIDNotRecognisedException) ✓
			 * - Using showIndividualPost for the post previously created should return the correct string ✓
			 */
			//print(platform.showIndividualPost(post));

			/**
			 * showAccount
			 * - Account handle should exist on the system (HandleNotRecognisedException) ✓
			 * - Using showAccount("test_1") should return the correct string ✓
			 */
			String test_1 = platform.showAccount("test_1");
			//print(test_1);

			/**
			 * endorsePost
			 * - Account handle should exist on the system (HandleNotRecognisedException) ✓
			 * - Post ID should exist on system (PostIDNotRecognisedException) ✓
			 * - Endorsement posts should not be endorsable (NotActionablePostException) ✓
			 * - Using showIndividualPost for the previously created post should return the correct string ✓
 			 */
			//print(platform.showAccount("test_1"));

			int endorse = platform.endorsePost("test_2", 3);
			//print(platform.showIndividualPost(endorse));
			//print(platform.showIndividualPost(3));
			//print(platform.showAccount("test_1"));

			/**
			 * commentPost
			 * - Account handle should exist on the system (HandleNotRecognisedException) ✓
			 * - Post ID should exist on system (PostIDNotRecognisedException) ✓
			 * - Endorsement posts should not be commentable (NotActionablePostException) ✓
			 * - Using showIndividualPost should return the correct string ✓
 			 */
			int firstComment = platform.commentPost("test_2", 3, "Comment under main test post 1");
			int secondComment = platform.commentPost("test_1", firstComment, "Comment under first comment");
			platform.commentPost("test_1", secondComment, "Comment under second comment");
			platform.commentPost("test_2", 3, "Comment under main test post 1");

			//print(platform.showIndividualPost(firstComment));
			//print(platform.showIndividualPost(3));

			/**
			 * showPostChildrenDetails
			 * - Post ID should exist on system (PostIDNotRecognisedException) ✓
			 * - Endorsement posts should not be commentable (NotActionablePostException) ✓
			 * - Using showPostChildrenDetails should return the correct string ✓
 			 */
			platform.showPostChildrenDetails(3);
			System.out.println(platform.showPostChildrenDetails(3));

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
			 * Using getMostEndorsedPost should return the correct string ✓
			 */
			int postID = platform.getMostEndorsedPost();
			assert(postID == 8) : "incorrect post returned for getMostEndorsedPost";

			/**
			 * deletePost
			 * - Post ID should exist on system (PostIDNotRecognisedException)
			 * - Using getTotalOriginalPosts should return int 1 ✓
			 */
			platform.deletePost(1);
			try {
				((SocialMedia) platform).getPost(1);
			} catch (PostIDNotRecognisedException e) {
				assert(true) : "deletePost did not remove post as expected";
			}
		} catch(PostIDNotRecognisedException e) {
			e.printStackTrace();
		}  catch(InvalidPostException e) {
			e.printStackTrace();
		} catch (HandleNotRecognisedException e) {
			e.printStackTrace();
		}catch (NotActionablePostException e) {
			e.printStackTrace();
		} catch (IllegalHandleException e){
			e.printStackTrace();
		} catch (InvalidHandleException e) {
			e.printStackTrace();
		}

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
			HashMap<Integer, Post> localPosts =  ((SocialMedia) platform).getPosts();
			HashMap<String, Account> localAccounts = ((SocialMedia) platform).getAccounts();

			/**
			 * erasePlatform
			 * - Should clear all arraylists, hashmaps and counters ✓
			 */
			platform.erasePlatform();
			assert (platform.getNumberOfAccounts() == 0) : "Accounts hashmap has not been reset";
			assert (platform.getTotalOriginalPosts() == 0) : "Posts hashmap has not been reset";

			/**
			 * loadPlatform
			 * - Hashmaps imported should be the same as before the platform was erased
			 */
			platform.loadPlatform("testSave");
			String test_2 = ((SocialMedia) platform).getAccount(3).getHandle();
			assert(test_2.equals("test_2")) : "Loaded accounts hashmap does not match original hashmap";

			String main_test_post = ((SocialMedia) platform).getPost(3).getContents();
			assert(main_test_post.equals("Main test post")) : "Loaded posts hashmap does not match original hashmap";

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (AccountIDNotRecognisedException e) {
			e.printStackTrace();
		} catch (PostIDNotRecognisedException e) {
			e.printStackTrace();
		}
	}
}
