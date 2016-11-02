package ua.com.wadyan.vinatm.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.wadyan.vinatm.Model.FiftyUAH;
import ua.com.wadyan.vinatm.Model.FiveHundredUAH;
import ua.com.wadyan.vinatm.Model.OneHundredUAH;
import ua.com.wadyan.vinatm.Model.TwoHundredUAH;
import ua.com.wadyan.vinatm.R;

/**
 * Created by << Wad + >> on 02.11.2016.
 */

public class ChoiceBanknotesActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.btn_edit_banknotes_count) Button mBtnEditBanknotesCount;
    @Bind(R.id.btn_finish_edit_banknotes_count) Button mBtnFinishEditBanknotesCoutn;
    @Bind(R.id.tv_money_nominal_50) TextView mTvMoneyNominal50;
    @Bind(R.id.tv_money_nominal_100) TextView mTvMoneyNominal100;
    @Bind(R.id.tv_money_nominal_200) TextView mTvMoneyNominal200;
    @Bind(R.id.tv_money_nominal_500) TextView mTvMoneyNominal500;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_banknote);
        ButterKnife.bind(this);

        mBtnEditBanknotesCount.setOnClickListener(this);
        mBtnFinishEditBanknotesCoutn.setOnClickListener(this);

        mTvMoneyNominal50.setText("Доступно - " + FiftyUAH.getReminder() + " од.");
        mTvMoneyNominal100.setText("Доступно - " + OneHundredUAH.getReminder() + " од.");
        mTvMoneyNominal200.setText("Доступно - " + TwoHundredUAH.getReminder() + " од.");
        mTvMoneyNominal500.setText("Доступно - " + FiveHundredUAH.getReminder() + " од.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_banknotes_count:

                break;
            case R.id.btn_finish_edit_banknotes_count:
                startActivity(new Intent(this, SecondActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
