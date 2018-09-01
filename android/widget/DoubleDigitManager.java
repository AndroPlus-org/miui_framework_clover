// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.os.Handler;

class DoubleDigitManager
{
    static interface CallBack
    {

        public abstract void singleDigitFinal(int i);

        public abstract boolean singleDigitIntermediate(int i);

        public abstract boolean twoDigitsFinal(int i, int j);
    }


    static Integer _2D_get0(DoubleDigitManager doubledigitmanager)
    {
        return doubledigitmanager.intermediateDigit;
    }

    static CallBack _2D_get1(DoubleDigitManager doubledigitmanager)
    {
        return doubledigitmanager.mCallBack;
    }

    static Integer _2D_set0(DoubleDigitManager doubledigitmanager, Integer integer)
    {
        doubledigitmanager.intermediateDigit = integer;
        return integer;
    }

    public DoubleDigitManager(long l, CallBack callback)
    {
        timeoutInMillis = l;
        mCallBack = callback;
    }

    public void reportDigit(int i)
    {
        if(intermediateDigit != null) goto _L2; else goto _L1
_L1:
        intermediateDigit = Integer.valueOf(i);
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                if(DoubleDigitManager._2D_get0(DoubleDigitManager.this) != null)
                {
                    DoubleDigitManager._2D_get1(DoubleDigitManager.this).singleDigitFinal(DoubleDigitManager._2D_get0(DoubleDigitManager.this).intValue());
                    DoubleDigitManager._2D_set0(DoubleDigitManager.this, null);
                }
            }

            final DoubleDigitManager this$0;

            
            {
                this$0 = DoubleDigitManager.this;
                super();
            }
        }
, timeoutInMillis);
        if(!mCallBack.singleDigitIntermediate(i))
        {
            intermediateDigit = null;
            mCallBack.singleDigitFinal(i);
        }
_L4:
        return;
_L2:
        if(mCallBack.twoDigitsFinal(intermediateDigit.intValue(), i))
            intermediateDigit = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Integer intermediateDigit;
    private final CallBack mCallBack;
    private final long timeoutInMillis;
}
