package ua.com.wadyan.vinatm.communicator;


public interface BackendCommunicator {
	boolean postSignIn(String userName, String password) throws InterruptedException;
}
