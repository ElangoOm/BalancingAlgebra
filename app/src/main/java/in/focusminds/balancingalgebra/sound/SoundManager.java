package in.focusminds.balancingalgebra.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.SparseIntArray;


public class SoundManager{
   // private  static final String TAG = "SoundManager";
    public static  SoundPool mSoundPool;
    private static final int MAX_STREAMS = 5;
    private SparseIntArray mSoundPoolMap = new SparseIntArray();
    float voulme;
    public SoundManager() {

        mSoundPoolMap = new SparseIntArray();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_STREAMS)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,0);
        }
    }

    public void setVolume(float sound) {

    this.voulme = sound;
    }

    public void addSound(Context context,int soundID) {

        mSoundPoolMap.put(soundID, mSoundPool.load(context,soundID, 1));
    }

    public void playSound(int soundID) {

        boolean hasSound = mSoundPoolMap.indexOfKey(soundID) >= 0;
        if(!hasSound){
            return;
        }

        mSoundPool.play(mSoundPoolMap.get(soundID), 1, 1, 0, 0, 1);

    }


    public void autoPause() {

        mSoundPool.autoPause();

    }

    public void release() {
       // Log.i(TAG,"**mSoundPoolrelease******"+mSoundPool);
        if(mSoundPool !=null) {
            mSoundPool.release();

        }
    }
}
