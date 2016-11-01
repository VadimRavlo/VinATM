package ua.com.wadyan.vinatm.communicator;

import android.util.Log;

import ua.com.wadyan.vinatm.GlobalConstVar;
import ua.com.wadyan.vinatm.Model.User;

class BackendCommunicatorStub implements BackendCommunicator {
	public User[] userList = new User[5];
	boolean mValidUsername, mValidPassword;

	public BackendCommunicatorStub() {
		for (int i = 0; i < 5; i++) {
			userList[i] = new User("user"+i, "qwerty"+i, i*150);
			Log.d("Wad", "User" + i + " created");
		}
	}

	@Override
	public boolean postSignIn(final String userName, final String password) throws InterruptedException {
		Thread.sleep(500);
		for (int i = 0; i < userList.length; i++) {
			User user = userList[i];
			for (int j = 0; j < 3; j++) {
				mValidUsername = user.getName().equals(userName);
				mValidPassword = user.getPassword().equals(password);
			}
			GlobalConstVar.setCurrentUser(user);
			if (mValidUsername && mValidPassword) break;
		}
		return mValidUsername && mValidPassword;
	}
}
