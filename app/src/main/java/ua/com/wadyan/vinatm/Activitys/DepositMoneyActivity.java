package ua.com.wadyan.vinatm.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.wadyan.vinatm.R;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class DepositMoneyActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.btn_deposit_money_finish) Button mBtnDepositMoneyFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_money);
        ButterKnife.bind(this);

        mBtnDepositMoneyFinish.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SecondActivity.class));
        finish();
    }
}
