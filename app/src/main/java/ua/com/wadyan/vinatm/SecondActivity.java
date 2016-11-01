package ua.com.wadyan.vinatm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.wadyan.vinatm.Model.User;

public class SecondActivity extends Activity implements View.OnClickListener{

	@Bind(R.id.tv_balance) TextView mTvBalance;
	@Bind(R.id.btn_get_money) Button mBtnGetMoney;
	@Bind(R.id.btn_deposit_money) Button mBtnDepositMoney;
	@Bind(R.id.btn_exit_second_activity) Button mBtnExitSecondActivity;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		ButterKnife.bind(this);

		mBtnGetMoney.setOnClickListener(this);
		mBtnDepositMoney.setOnClickListener(this);
		mBtnExitSecondActivity.setOnClickListener(this);

		User user = GlobalConstVar.getCurrentUser();
		mTvBalance.setText(Integer.toString(user.getMoneyBalance()) + " грн.");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_get_money:
				startActivity(new Intent(this, GetMoneyActivity.class));
				break;
			case R.id.btn_deposit_money:
				startActivity(new Intent(this, DepositMonetActivity.class));
				break;
			case R.id.btn_exit_second_activity:
				finish();
				Toast.makeText(this, "By by!", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}

	}
}
