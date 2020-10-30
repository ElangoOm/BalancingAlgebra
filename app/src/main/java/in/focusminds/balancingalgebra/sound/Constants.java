package in.focusminds.balancingalgebra.sound;

import android.content.Context;

import in.focusminds.balancingalgebra.R;


public class Constants {


    public static final int sCorrectAnser =R.raw.crowdsoundeffect;
    public static final int ikick =R.raw.ballkick;

    public static final int buttonClick = R.raw.button;

    public static void initSoundManager(Context context,SoundManager soundManager){

        soundManager.addSound(context,sCorrectAnser);
        soundManager.addSound(context,ikick);

        soundManager.addSound(context,buttonClick);
    }
}
