package socialmedia;

import java.io.IOException;

/**
 * BadSocialMedia is a minimally compiling, but non-functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class BadSocialMedia implements SocialMediaPlatform {

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		//  Auto-generated method stub

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		//  Auto-generated method stub

	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		//  Auto-generated method stub

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		//  Auto-generated method stub

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		//  Auto-generated method stub
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		//  Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		//  Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		//  Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalOriginalPosts() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		//  Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
		// Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		//  Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		//  Auto-generated method stub

	}

}
