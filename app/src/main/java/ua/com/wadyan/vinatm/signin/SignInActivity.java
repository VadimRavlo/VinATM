package ua.com.wadyan.vinatm.signin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ua.com.wadyan.vinatm.R;
import ua.com.wadyan.vinatm.SecondActivity;

public class SignInActivity extends Activity implements SignInModel.Observer {
	private static final String LOG_TAG = "SignInActivity";
	private static final String TAG_WORKER = "TAG_WORKER";

	private EditText mUserName;
	private EditText mPassword;
	private View mSubmit;
	private View mProgress;

	private SignInModel mSignInModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		mUserName = (EditText) findViewById(R.id.view_username);
		mPassword = (EditText) findViewById(R.id.view_password);
		mSubmit = findViewById(R.id.view_submit);
		mProgress = findViewById(R.id.view_progress);

		setUsers();

		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final String userName = mUserName.getText().toString();
				final String password = mPassword.getText().toString();

				mSignInModel.signIn(userName, password);
			}
		});

		final SignInWorkerFragment retainedWorkerFragment =
				(SignInWorkerFragment) getFragmentManager().findFragmentByTag(TAG_WORKER);

		if (retainedWorkerFragment != null) {
			mSignInModel = retainedWorkerFragment.getSignInModel();
		} else {
			final SignInWorkerFragment workerFragment = new SignInWorkerFragment();

			getFragmentManager().beginTransaction()
					.add(workerFragment, TAG_WORKER)
					.commit();

			mSignInModel = workerFragment.getSignInModel();
		}

		mSignInModel.registerObserver(this);
	}

	private void setUsers(){

	}

	@Override
	protected void onDestroy() {
		Log.i(LOG_TAG, "onDestroy");
		super.onDestroy();
		mSignInModel.unregisterObserver(this);

		if (isFinishing()) {
			mSignInModel.stopSignIn();
		}
	}

	@Override
	public void onSignInStarted(final SignInModel signInModel) {
		Log.i(LOG_TAG, "onSignInStarted");
		showProgress(true);
	}

	@Override
	public void onSignInSucceeded(final SignInModel signInModel) {
		Log.i(LOG_TAG, "onSignInSucceeded");
		finish();
		startActivity(new Intent(this, SecondActivity.class));
	}

	@Override
	public void onSignInFailed(final SignInModel signInModel) {
		Log.i(LOG_TAG, "onSignInFailed");
		showProgress(false);
		Toast.makeText(this, R.string.sign_in_error, Toast.LENGTH_SHORT).show();
	}

	private void showProgress(final boolean show) {
		mUserName.setEnabled(!show);
		mPassword.setEnabled(!show);
		mSubmit.setEnabled(!show);
		mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}
