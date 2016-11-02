package ua.com.wadyan.vinatm.communicator;

import android.util.Log;

import ua.com.wadyan.vinatm.GlobalConstVar;
import ua.com.wadyan.vinatm.Model.User;

class BackendCommunicatorStub implements BackendCommunicator {
	private boolean mValidUsername, mValidPassword;

	@Override
	public boolean postSignIn(final String userName, final String password) throws InterruptedException {
		Thread.sleep(500);
		for (int i = 0; i < GlobalConstVar.getUserList().length; i++) {
			User user = GlobalConstVar.getUserList()[i];
			mValidUsername = user.getName().equals(userName);
			mValidPassword = user.getPassword().equals(password);
			GlobalConstVar.setCurrentUser(user);
			if (mValidUsername && mValidPassword) break;
		}
		return mValidUsername && mValidPassword;
	}
}
