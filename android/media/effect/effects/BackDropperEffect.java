// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect.effects;

import android.filterfw.core.*;
import android.filterpacks.videoproc.BackDropperFilter;
import android.media.effect.*;

public class BackDropperEffect extends FilterGraphEffect
{

    static EffectUpdateListener _2D_get0(BackDropperEffect backdroppereffect)
    {
        return backdroppereffect.mEffectListener;
    }

    public BackDropperEffect(EffectContext effectcontext, String s)
    {
        super(effectcontext, s, "@import android.filterpacks.base;\n@import android.filterpacks.videoproc;\n@import android.filterpacks.videosrc;\n\n@filter GLTextureSource foreground {\n  texId = 0;\n  width = 0;\n  height = 0;\n  repeatFrame = true;\n}\n\n@filter MediaSource background {\n  sourceUrl = \"no_file_specified\";\n  waitForNewFrame = false;\n  sourceIsUrl = true;\n}\n\n@filter BackDropperFilter replacer {\n  autowbToggle = 1;\n}\n\n@filter GLTextureTarget output {\n  texId = 0;\n}\n\n@connect foreground[frame]  => replacer[video];\n@connect background[video]  => replacer[background];\n@connect replacer[video]    => output[frame];\n", "foreground", "output", android/filterfw/core/OneShotScheduler);
        mEffectListener = null;
        mLearningListener = new android.filterpacks.videoproc.BackDropperFilter.LearningDoneListener() {

            public void onLearningDone(BackDropperFilter backdropperfilter)
            {
                if(BackDropperEffect._2D_get0(BackDropperEffect.this) != null)
                    BackDropperEffect._2D_get0(BackDropperEffect.this).onEffectUpdated(BackDropperEffect.this, null);
            }

            final BackDropperEffect this$0;

            
            {
                this$0 = BackDropperEffect.this;
                super();
            }
        }
;
        mGraph.getFilter("replacer").setInputValue("learningDoneListener", mLearningListener);
    }

    public void setParameter(String s, Object obj)
    {
        if(!s.equals("source")) goto _L2; else goto _L1
_L1:
        mGraph.getFilter("background").setInputValue("sourceUrl", obj);
_L4:
        return;
_L2:
        if(s.equals("context"))
            mGraph.getFilter("background").setInputValue("context", obj);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setUpdateListener(EffectUpdateListener effectupdatelistener)
    {
        mEffectListener = effectupdatelistener;
    }

    private static final String mGraphDefinition = "@import android.filterpacks.base;\n@import android.filterpacks.videoproc;\n@import android.filterpacks.videosrc;\n\n@filter GLTextureSource foreground {\n  texId = 0;\n  width = 0;\n  height = 0;\n  repeatFrame = true;\n}\n\n@filter MediaSource background {\n  sourceUrl = \"no_file_specified\";\n  waitForNewFrame = false;\n  sourceIsUrl = true;\n}\n\n@filter BackDropperFilter replacer {\n  autowbToggle = 1;\n}\n\n@filter GLTextureTarget output {\n  texId = 0;\n}\n\n@connect foreground[frame]  => replacer[video];\n@connect background[video]  => replacer[background];\n@connect replacer[video]    => output[frame];\n";
    private EffectUpdateListener mEffectListener;
    private android.filterpacks.videoproc.BackDropperFilter.LearningDoneListener mLearningListener;
}
