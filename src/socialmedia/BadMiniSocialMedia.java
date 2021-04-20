package socialmedia;

import java.io.IOException;

/**
 * BadMiniSocialMedia is a minimally compiling, but non-functioning implementor
 * of the MiniSocialMediaPlatform interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class BadMiniSocialMedia implements MiniSocialMediaPlatform {

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// Auto-generated method stub
		return 0;
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		//  Auto-generated method stub

	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
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
		//  Auto-generated method stub

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
