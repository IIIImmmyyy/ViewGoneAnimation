package imy.viewgoneanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button first_Btn;
    Button hideBtn;
    Button testBtn;
    TextView nidaye;
    Button showBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBtn = (Button) findViewById(R.id.show_all_btn);
        nidaye = (TextView) findViewById(R.id.ni_daye);
        first_Btn = (Button) findViewById(R.id.first_btn);
        hideBtn = (Button) findViewById(R.id.sec_btn);
        testBtn = (Button) findViewById(R.id.test_btn);
        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAnimationHelper.getInstance().
                        attach(nidaye).setVisibility(View.GONE);
                ViewAnimationHelper.getInstance().attach(testBtn).setVisibility(View.GONE);
            }
        });
        showBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAnimationHelper.getInstance().attach(nidaye).setVisibility(View.VISIBLE);
                ViewAnimationHelper.getInstance().attach(testBtn).setVisibility(View.VISIBLE);
            }
        });
    }
}
