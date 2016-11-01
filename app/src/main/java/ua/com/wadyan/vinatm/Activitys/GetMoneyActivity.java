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
import ua.com.wadyan.vinatm.ATMCash;
import ua.com.wadyan.vinatm.GlobalConstVar;
import ua.com.wadyan.vinatm.Model.FiftyUAH;
import ua.com.wadyan.vinatm.Model.FiveHundredUAH;
import ua.com.wadyan.vinatm.Model.OneHundredUAH;
import ua.com.wadyan.vinatm.Model.TwoHundredUAH;
import ua.com.wadyan.vinatm.R;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class GetMoneyActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.et_sum) EditText mEtSum;
    @Bind(R.id.btn_get_money_finish) Button mBtnGetMoneyFinish;
    @Bind(R.id.tv_money_nominal_50) TextView mTvMoneyNominal50;
    @Bind(R.id.tv_money_nominal_100) TextView mTvMoneyNominal100;
    @Bind(R.id.tv_money_nominal_200) TextView mTvMoneyNominal200;
    @Bind(R.id.tv_money_nominal_500) TextView mTvMoneyNominal500;

    int userSum;
    ATMCash atmCash = new ATMCash();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_money);
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
        String message = "";

        String s = mEtSum.getText().toString();
        userSum = Integer.parseInt(s);

        switch (atmCash.possibilityGetMoney(userSum)){
            case GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER:
                message = "На вашому рахунку недостатня кількість коштів";
                break;
            case GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER:
                message = "У банкоматі відсутня така кількість коштів. Введіть меншу суму";
                break;
            case GlobalConstVar.EXCEEDED_DAILY_LIMIT:
                message = "Перевищений максимальний денний ліміт коштів. Зверніться в наступний день";
                break;
            case GlobalConstVar.EXCEEDED_MAX_COUNT:
                message = "Кошти в банкоматі дозволяється отримувати не більше двох разів на день. Зверніться в наступний день";
                break;
            case GlobalConstVar.EXCEEDED_BANKNOTE:
                message = "Недостатня кількість банкнот належного номіналу. Введіть суму, кратну доступному номіналу банкнот";
                break;

            case GlobalConstVar.ALLOWED:
                message = "Видачу коштів дозволено. Будь ласка, заберіть кошти з лотка протягом 30 секунд";
                startActivity(new Intent(this, SecondActivity.class));
                break;
            default:
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
