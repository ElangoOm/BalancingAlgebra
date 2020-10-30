package in.focusminds.balancingalgebra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import in.focusminds.balancingalgebra.sound.MyApplication;
import in.focusminds.balancingalgebra.sound.SoundManager;

public class MainActivity extends AppCompatActivity {

    Button bPlusx, bMinusx, bPlusone, bMinusone, bStart;
    TextView tLeftEq, tRightEq, tResult, tPreveq, tPoints;
    int iLeftx,iLeftone,iRightone,iRightx, iRandom ,iPoints=0;
    private  static final String TAG = "iMainActivity";
    String sPreveq = "";

    int displayWidth, displayHeight, dialogWindowWidth, dialogWindowHeight;
    Dialog hintdialog;
    DisplayMetrics displayMetrics;
    WindowManager.LayoutParams layoutParams;

    public static SoundManager soundManager;
    int iButton,iBallKick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Log.i(TAG, "********Oncreate*********");
        bStart = findViewById(R.id.start);
        bPlusx = findViewById(R.id.plusx);
        bMinusx = findViewById(R.id.minusx);
        bPlusone = findViewById(R.id.plus1);
        bMinusone = findViewById(R.id.minus1);
        tLeftEq = findViewById(R.id.leftequation);
        tRightEq = findViewById(R.id.rightequation);
        tResult = findViewById(R.id.result);
        tPreveq = findViewById(R.id.preveq);
        tPoints = findViewById(R.id.points);

        displayMetrics = new DisplayMetrics();
        setDefaultMetric();
        hintdialog = new Dialog(this);
        layoutParams = new WindowManager.LayoutParams();

        soundManager = new SoundManager();
        soundManager = MyApplication.getSoundManager(getApplicationContext());

        iButton = R.raw.crowdsoundeffect;
        iBallKick = R.raw.ballkick;

        bPlusone.setEnabled(false);
        bMinusone.setEnabled(false);
        bPlusx.setEnabled(false);
        bMinusx.setEnabled(false);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Log.i(TAG, "********starts*********");
                soundManager.autoPause();
                bPlusone.setEnabled(true);
                bMinusone.setEnabled(true);
                bPlusx.setEnabled(true);
                bMinusx.setEnabled(true);

                if (iPoints < 10) {
                    iRandom = (int) (Math.random() * 3 + 1);
                    tResult.setText("");
                    tPreveq.setText("");
                    int range = 5 - (-5) + 1;

                    switch (iRandom) {
                        case 1:
                            // iLeftx = (int) (Math.random() * 3 + 1);
                            iLeftx = 1;
                            iRightx = 0;
                            iLeftone = (int) (Math.random() * range + -5);
                            iRightone = (int) (Math.random() * range + -5);
                            if (iLeftone == 0 || iRightone ==0) {
                                iLeftone++;
                                iRightone++;
                            }

                            //tLeftEq.setText("x + "+iLeftone);
                            //tRightEq.setText(String.valueOf(iRightone));
                            setValues();

                            break;

                        case 2:
                            // iRightx = (int) (Math.random() * 3 + 1);
                            iRightx = -1;
                            iLeftx = 0;
                            iLeftone = (int) (Math.random() * range + -5);
                            iRightone = (int) (Math.random() * range + -5);
                            if (iLeftone == 0 || iRightone == 0) {
                                iLeftone++;
                                iRightone++;
                            }
                            setValues();
                            //tLeftEq.setText(String.valueOf(iLeftone));
                            //tRightEq.setText("-x + "+iRightone);
                            break;

                        default:

                            iLeftx = (int) (Math.random() * range + -5);

                            iRightx = iLeftx - 1;

                            iLeftone = (int) (Math.random() * range + -5);
                            iRightone = (int) (Math.random() * range + -5);
                            if (iLeftone == 0 || iRightone ==0) {
                                iLeftone++;
                                iRightone++;
                            }
                            setValues();
                            //tLeftEq.setText(iLeftx+"x + "+iLeftone);
                            //tRightEq.setText(iRightx+"x + "+iRightone);
                            break;
                    }

                    bStart.setVisibility(View.INVISIBLE);
                } else {
                    endgame();
                }
            }
        });

        bPlusx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.autoPause();
                soundManager.playSound(iBallKick);
                Log.i(TAG, "tLeftEq:"+tLeftEq.getText().toString());
                Log.i(TAG, "tRightEq:"+tRightEq.getText().toString());

                 iLeftx = iLeftx+1;
                iRightx = iRightx+1;


                setValues();
               // tLeftEq.setText(iLeftx+"x + "+iLeftone);
                //tRightEq.setText(iRightx+"x + "+iRightone);
                checkFinal();
            }


        });

        bMinusx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.autoPause();
                soundManager.playSound(iBallKick);
                iLeftx = iLeftx-1;
                iRightx = iRightx-1;

                setValues();
                checkFinal();
                //tLeftEq.setText(iLeftx+sLeftx+" + "+iRightone);
               // tRightEq.setText(iRightx+sRightx+" + "+iRightone);
            }
        });

        bPlusone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.autoPause();
                soundManager.playSound(iBallKick);
                iLeftone = iLeftone+1;
                iRightone = iRightone+1;

                setValues();
                checkFinal();
            }
        });

        bMinusone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.autoPause();
                soundManager.playSound(iBallKick);
                iLeftone = iLeftone-1;
                iRightone = iRightone-1;

                setValues();
                checkFinal();
            }
        });


    }

    private void setValues() {

        sPreveq = tLeftEq.getText().toString() +" = " +tRightEq.getText().toString();
        if(iLeftone == 0 && iLeftx == 1) {
            tLeftEq.setText("x   ");
        } else if(iLeftone == 0 && iLeftx != 0) {
            tLeftEq.setText(iLeftx+"x   ");
        } else if(iLeftx == 0) {
            tLeftEq.setText(String.valueOf(iLeftone));
        } else if(iLeftx == 1) {
            tLeftEq.setText("x + ("+iLeftone+")");
        } else if(iLeftx == -1) {
            tLeftEq.setText("-x + ("+iLeftone+")");
        } else {
            tLeftEq.setText("("+iLeftx+"x) + ("+iLeftone+")");
        }
        if(iRightone == 0 && iRightx == 1) {
            tRightEq.setText("x   ");
        } else if(iRightone == 0 && iRightx != 0) {
            tRightEq.setText(iRightx+"x   ");
        } else if(iRightx == 0) {
            tRightEq.setText(String.valueOf(iRightone));
        } else if(iRightx == 1) {
            tRightEq.setText("x + ("+iRightone+")");
        } else if(iRightx == -1) {
            tRightEq.setText("-x + ("+iRightone+")");
        } else {
            tRightEq.setText("("+iRightx+"x) + ("+iRightone+")");
        }

    }

    private void checkFinal() {
      //  Log.i(TAG, "**checkFinalt*****LeftEq:"+tLeftEq.getText().toString());
       // Log.i(TAG, "**checkFinalt*****tRightEq:"+tRightEq.getText().toString());
        if(tLeftEq.getText().toString().trim().equalsIgnoreCase("X")) {
          //  Log.i(TAG, "**checkFinalt**237***:");
          if( tRightEq.getText().toString().trim().matches("-?\\d+(\\.\\d+)?")){
              //Log.i(TAG, "**checkFinalt**239***:");
              soundManager.playSound(iButton);
              iPoints++;
              tResult.setText("ACHIEVED");
              bStart.setVisibility(View.VISIBLE);
              tPoints.setText(String.valueOf(iPoints));
              bPlusone.setEnabled(false);
              bMinusone.setEnabled(false);
              bPlusx.setEnabled(false);
              bMinusx.setEnabled(false);
          }
        }
        tPreveq.setText(sPreveq);
        //Log.i(TAG, "**checkFinalt**244***:");
    }


    public void setDefaultMetric() {
        displayMetrics = getResources().getDisplayMetrics();
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
        dialogWindowWidth = (int) (displayWidth * 0.9f);
        dialogWindowHeight = (int) (displayHeight * 0.9f);
    }

    public void endgame() {
        //hintdialog = new Dialog(this);
        soundManager.autoPause();
        soundManager.playSound(iButton);
        hintdialog.setContentView(R.layout.endgame);
        TextView tResult,  tClose;

        tResult = hintdialog.findViewById(R.id.result);

        tClose = hintdialog.findViewById(R.id.dialogClose);

        tResult.setText("WELL DONE");
        tClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintdialog.dismiss();
                finish();
            }
        });

        hintdialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                    hintdialog.dismiss();
                }
                return true;
            }
        });

        hintdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        hintdialog.show();
        layoutParams.copyFrom(hintdialog.getWindow().getAttributes());
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        hintdialog.getWindow().setAttributes(layoutParams);
    }

    @Override
    protected void onStop() {
        soundManager.autoPause();
        super.onStop();
    }

    @Override
    protected void onResume() {
        // Log.i(TAG, "**OnReusme******");
        soundManager = new SoundManager();
        soundManager = MyApplication.getSoundManager(getApplicationContext());
        super.onResume();
    }

    @Override
    protected void onPause() {
        soundManager.release();
        //  Log.i(TAG, "**onPause******");
        super.onPause();
    }

}