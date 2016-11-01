package ua.com.wadyan.vinatm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;

/**
 * Created by << Wad + >> on 01.11.2016.
 */

public class GetMoneyActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.et_sum) EditText mEtSum;
    @Bind(R.id.btn_get_money_finish) Button mBtnGetMoneyFinish;
    int userSum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_money);

        mBtnGetMoneyFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String message = "";
        CashInATM cashInATM = new CashInATM();

        userSum = Integer.parseInt(String.valueOf(mEtSum.getText()));

        switch (cashInATM.possibilityGetMoney(userSum)){
            case GlobalConstVar.ALLOWED:
                message = "Видачу коштів дозволено. Будь ласка, заберіть кошти з лотка протягом 30 секунд";
                break;
            case GlobalConstVar.EXCEEDED_DAILY_LIMIT:
                message = "Перевищений максимальний денний ліміт коштів. Зверніться в наступний день";
                break;
            case GlobalConstVar.EXCEEDED_ATM_CASH_REMINDER:
                message = "У банкоматі відсутня така кількість коштів. Введіть меншу суму";
                break;
            case GlobalConstVar.EXCEEDED_USER_MONEY_REMINDER:
                message = "На вашому рахунку недостатня кількість коштів";
                break;
            case GlobalConstVar.EXCEEDED_BANKNOTE:
                message = "Недостатня кількість банкнот належного номіналу";
                break;
            case GlobalConstVar.EXCEEDED_MAX_COUNT:
                message = "Кошти в банкоматі дозволяється отримувати один раз на день. Зверніться в наступний день";
                break;
            default:
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
