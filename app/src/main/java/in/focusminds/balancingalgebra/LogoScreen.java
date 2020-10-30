package in.focusminds.balancingalgebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iReturnMain = new Intent(LogoScreen.this,firstPage.class);
                startActivity(iReturnMain);
                overridePendingTransition(R.anim.zoom_in,R.anim.static_anim);
                finish();
            }
        },1500);
    }
}