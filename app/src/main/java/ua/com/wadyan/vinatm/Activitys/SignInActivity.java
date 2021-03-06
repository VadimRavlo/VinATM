package ua.com.wadyan.vinatm.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.wadyan.vinatm.GlobalConstVar;
import ua.com.wadyan.vinatm.R;
import ua.com.wadyan.vinatm.signin.SignInModel;
import ua.com.wadyan.vinatm.signin.SignInWorkerFragment;

import static ua.com.wadyan.vinatm.GlobalConstVar.LOG_TAG;

public class SignInActivity extends Activity implements SignInModel.Observer {
	private static final String TAG_WORKER = "TAG_WORKER";

	@Bind(R.id.view_username) EditText mUserName;
	@Bind(R.id.view_password) EditText mPassword;
	@Bind(R.id.view_submit) View mSubmit;
	@Bind(R.id.view_progress) View mProgress;

	private SignInModel mSignInModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		GlobalConstVar.createUsers();
		GlobalConstVar.fillingATM();

		ButterKnife.bind(this);

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

	@Override
	protected void onDestroy() {
		Log.i(LOG_TAG, "onDestroy");
		super.onDestroy();
		mSignInModel.unregisterObserver(this);
		ButterKnife.unbind(this);

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
