// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;

// Referenced classes of package miui.maml.data:
//            NotifierVariableUpdater, IndexedVariable, VariableUpdaterManager

public class VolumeVariableUpdater extends NotifierVariableUpdater
{

    static IndexedVariable _2D_get0(VolumeVariableUpdater volumevariableupdater)
    {
        return volumevariableupdater.mVolumeType;
    }

    public VolumeVariableUpdater(VariableUpdaterManager variableupdatermanager)
    {
        super(variableupdatermanager, "android.media.VOLUME_CHANGED_ACTION");
        mHandler = new Handler();
        mVolumeLevel = new IndexedVariable("volume_level", getContext().mVariables, true);
        mVolumeLevelOld = new IndexedVariable("volume_level_old", getContext().mVariables, true);
        mVolumeType = new IndexedVariable("volume_type", getContext().mVariables, true);
        mVolumeType.set(-1D);
    }

    public void onNotify(Context context, Intent intent, Object obj)
    {
        if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION"))
        {
            int i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
            mVolumeType.set(i);
            i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
            mVolumeLevel.set(i);
            int j = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
            if(j != i)
                mVolumeLevelOld.set(j);
            getRoot().requestUpdate();
            mHandler.removeCallbacks(mResetType);
            mHandler.postDelayed(mResetType, 1000L);
        }
    }

    private static final int SHOW_DELAY_TIME = 1000;
    public static final String VAR_VOLUME_LEVEL = "volume_level";
    public static final String VAR_VOLUME_LEVEL_OLD = "volume_level_old";
    public static final String VAR_VOLUME_TYPE = "volume_type";
    private Handler mHandler;
    private final Runnable mResetType = new Runnable() {

        public void run()
        {
            VolumeVariableUpdater._2D_get0(VolumeVariableUpdater.this).set(-1D);
        }

        final VolumeVariableUpdater this$0;

            
            {
                this$0 = VolumeVariableUpdater.this;
                super();
            }
    }
;
    private IndexedVariable mVolumeLevel;
    private IndexedVariable mVolumeLevelOld;
    private IndexedVariable mVolumeType;
}
