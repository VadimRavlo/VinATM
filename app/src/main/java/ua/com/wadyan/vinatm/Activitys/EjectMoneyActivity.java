package ua.com.wadyan.vinatm.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.wadyan.vinatm.ATMValidation;
import ua.com.wadyan.vinatm.GlobalConstVar;
import ua.com.wadyan.vinatm.Model.FiftyUAH;
import ua.com.wadyan.vinatm.Model.FiveHundredUAH;
import ua.com.wadyan.vinatm.Model.OneHundredUAH;
import ua.com.wadyan.vinatm.Model.TwoHundredUAH;
import ua.com.wadyan.vinatm.R;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class EjectMoneyActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.et_sum) EditText mEtSum;
    @Bind(R.id.btn_get_money_finish) Button mBtnGetMoneyFinish;
    @Bind(R.id.tv_money_nominal_50) TextView mTvMoneyNominal50;
    @Bind(R.id.tv_money_nominal_100) TextView mTvMoneyNominal100;
    @Bind(R.id.tv_money_nominal_200) TextView mTvMoneyNominal200;
    @Bind(R.id.tv_money_nominal_500) TextView mTvMoneyNominal500;

    int userSum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eject_money);
        ButterKnife.bind(this);

        mBtnGetMoneyFinish.setOnClickListener(this);

        mTvMoneyNominal50.setText(FiftyUAH.getNominal() + " - " + FiftyUAH.getReminder() + " од.");
        mTvMoneyNominal100.setText(OneHundredUAH.getNominal() + " - " + OneHundredUAH.getReminder() + " од.");
        mTvMoneyNominal200.setText(TwoHundredUAH.getNominal() + " - " + TwoHundredUAH.getReminder() + " од.");
        mTvMoneyNominal500.setText(FiveHundredUAH.getNominal() + " - " + FiveHundredUAH.getReminder() + " од.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        String message = "", s50 = "", s100 = "", s200 = "", s500 = "";
        ATMValidation atmValidation = new ATMValidation();

        String s;
        if ("".equals(mEtSum.getText().toString()) || "0".equals(mEtSum.getText().toString())){
            Toast.makeText(this, R.string.not_enter_sum, Toast.LENGTH_SHORT).show();
        } else {
            s = mEtSum.getText().toString();
            userSum = Integer.parseInt(s);

            switch (atmValidation.possibilityGetMoney(userSum)){
                case GlobalConstVar.START_ID:
                    break;
                case GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER:
                    message = getString(R.string.account_not_enough_money);
                    break;
                case GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER:
                    message = getString(R.string.atm_not_enough_money);
                    break;
                case GlobalConstVar.EXCEEDED_DAILY_LIMIT:
                    message = getString(R.string.outdone_max_day_limit);
                    break;
                case GlobalConstVar.EXCEEDED_MAX_DAY_COUNT:
                    message = getString(R.string.outdone_max_day_count);
                    break;
                case GlobalConstVar.EXCEEDED_BANKNOTE:
                    message = getString(R.string.exceeded_banknote);
                    startActivity(new Intent(this, ChoiceBanknotesActivity.class));
                    break;
                case GlobalConstVar.ALLOWED:
                    if (FiftyUAH.getToEject() > 0) s50 = "50 грн. - " + FiftyUAH.getToEject();
                    if (OneHundredUAH.getToEject() > 0) s100 = "; 100 грн. - " + OneHundredUAH.getToEject();
                    if (TwoHundredUAH.getToEject() > 0) s200 = "; 200 грн. - " + TwoHundredUAH.getToEject();
                    if (FiveHundredUAH.getToEject() > 0) s500 = "; 500 грн. - " + FiveHundredUAH.getToEject();

                    message = getString(R.string.cash_eject_allowed);
                    message += s50 + s100 + s200 + s500;

                    startActivity(new Intent(this, SecondActivity.class));
                    finish();
                    break;
                default:
                    break;
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}
