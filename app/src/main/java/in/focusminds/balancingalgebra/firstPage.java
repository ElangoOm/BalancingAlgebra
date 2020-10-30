package in.focusminds.balancingalgebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import in.focusminds.balancingalgebra.sound.MyApplication;
import in.focusminds.balancingalgebra.sound.SoundManager;

public class firstPage extends AppCompatActivity {

    Button button;
    ImageView iAbout,imtathLoveAbout;
    int iButton;
    public static SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        iButton = R.raw.button;

        button = (Button) findViewById(R.id.button);

        soundManager = new SoundManager();
        soundManager = MyApplication.getSoundManager(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });



        imtathLoveAbout = findViewById(R.id.mathlove);
        imtathLoveAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundManager.playSound(iButton);
                Intent iAbout = new Intent(firstPage.this,mathlove.class);
                startActivity(iAbout);
            }
        });


        iAbout = findViewById(R.id.focusminds);
        iAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundManager.playSound(iButton);
                Intent iAbout = new Intent(firstPage.this, focusmindsabout.class);
                startActivity(iAbout);
            }
        });

    }

    public void openNewActivity(){

        Intent intent = new Intent(this, MainActivity.class);
        soundManager.playSound(iButton);
        startActivity(intent);

    }
}