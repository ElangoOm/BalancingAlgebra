package in.focusminds.balancingalgebra.sound;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static  SoundManager soundManager;

    public static SoundManager getSoundManager(Context context) {
        soundManager = null;
        if (soundManager == null) {
            soundManager = new SoundManager();
            Constants.initSoundManager(context,soundManager);
        }
        return soundManager;
    }


}
