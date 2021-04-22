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
			// savePlatform
			platform.savePlatform("testSave");
			// Saving hashmaps locally so that they can be used to see if loadPlatform() works
			HashMap<Integer, Post> localPosts = ((SocialMedia) platform).posts;
			HashMap<String, Account> localAccounts = ((SocialMedia) platform).accounts;

			// erasePlatform
			platform.erasePlatform();

			// loadPlatform
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
			// createAccount
			id = platform.createAccount("my_handle");
			platform.createAccount("test_1", "testing");

			// showAccount
			String accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);
			String accountInfo1 = platform.showAccount("test_1");
			//print(accountInfo1);

			// updateAccountDescription
			platform.updateAccountDescription("my_handle", " test description");
			accountInfo = platform.showAccount("my_handle");
			//print(accountInfo);

			// changeAccountHandle
			platform.changeAccountHandle("my_handle", "new_Handle");
			accountInfo = platform.showAccount("new_Handle");
			//print(accountInfo);

			// getNumberOfAccounts
			assert (platform.getNumberOfAccounts() == 2 ) : "getNumberOfAccounts calculated incorrect account count";

			// getMostEndorsedAccount
			int postID = platform.createPost("test_1", "testPost");
			platform.endorsePost("new_Handle", postID);
			assert (platform.getMostEndorsedAccount() == 1 ) : "getMostEndorsedAccount returned incorrect account ID";

			// removeAccount
			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";

			platform.createAccount("test_2");

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
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
			// createPost, showIndividualPost
			platform.createPost("test_1", "Main test post");
			//print(platform.showIndividualPost(1));
			//print(platform.showAccount("test_1"));

			// endorsePost
			platform.endorsePost("test_2", 1);
			//print(platform.showIndividualPost(2));
			//print(platform.showIndividualPost(1));
			//print(platform.showAccount("test_2"));

			// commentPost
			int firstComment = platform.commentPost("test_2", 1, "Comment under main test post 1");
			platform.commentPost("test_1", firstComment, "Comment under first comment");
			platform.commentPost("test_1", 3, "Comment under id 3");
			firstComment = platform.commentPost("test_2", 1, "Comment under main test post 1");

			//print(platform.showIndividualPost(3));
			//print(platform.showIndividualPost(1));

			// showPostChildrenDetails
			platform.showPostChildrenDetails(1);
			//System.out.println(platform.showPostChildrenDetails(1));

			// deletePost
			platform.deletePost(1);
			try {
				platform.showAccount("test_1");
			} catch (Exception e) {
				print("Post successfully removed");
			}

			// getTotalCommentPosts
			int totalComment = platform.getTotalCommentPosts();
			assert (totalComment == 4) : "getTotalCommentPosts comment count not calculated correctly";

			// getTotalEndorsmentPosts
			int totalEndorse = platform.getTotalEndorsmentPosts();
			assert (totalEndorse == 1) : "getTotalEndorsmentPosts endorsement count not calculated correctly";

			// getTotalOriginalPosts
			int totalOriginal = platform.getTotalOriginalPosts();
			assert (totalOriginal == 1) : "getTotalOriginalPosts original count not calculated correctly";

			int postID = platform.getMostEndorsedPost();
			//print(platform.showIndividualPost(postID));


		} catch(PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		}  catch(InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		}catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		}
	}
}
