// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.transition:
//            VisibilityPropagation, Transition, TransitionValues

public class SidePropagation extends VisibilityPropagation
{

    public SidePropagation()
    {
        mPropagationSpeed = 3F;
        mSide = 80;
    }

    private int distance(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        int i2;
        if(mSide == 0x800003)
        {
            boolean flag;
            if(view.getLayoutDirection() == 1)
                i2 = 1;
            else
                i2 = 0;
            if(i2 != 0)
                i2 = 5;
            else
                i2 = 3;
        } else
        if(mSide == 0x800005)
        {
            if(view.getLayoutDirection() == 1)
                i2 = 1;
            else
                i2 = 0;
            if(i2 != 0)
                i2 = 3;
            else
                i2 = 5;
        } else
        {
            i2 = mSide;
        }
        flag = false;
        i2;
        JVM INSTR lookupswitch 4: default 76
    //                   3: 145
    //                   5: 177
    //                   48: 161
    //                   80: 193;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        i = ((flag) ? 1 : 0);
_L7:
        return i;
_L2:
        i = (k1 - i) + Math.abs(l - j);
        continue; /* Loop/switch isn't completed */
_L4:
        i = (l1 - j) + Math.abs(k - i);
        continue; /* Loop/switch isn't completed */
_L3:
        i = (i - i1) + Math.abs(l - j);
        continue; /* Loop/switch isn't completed */
_L5:
        i = (j - j1) + Math.abs(k - i);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private int getMaxDistance(ViewGroup viewgroup)
    {
        switch(mSide)
        {
        default:
            return viewgroup.getHeight();

        case 3: // '\003'
        case 5: // '\005'
        case 8388611: 
        case 8388613: 
            return viewgroup.getWidth();
        }
    }

    public long getStartDelay(ViewGroup viewgroup, Transition transition, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues == null && transitionvalues1 == null)
            return 0L;
        int i = 1;
        Rect rect = transition.getEpicenter();
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        float f;
        long l2;
        long l3;
        if(transitionvalues1 == null || getViewVisibility(transitionvalues) == 0)
            i = -1;
        else
            transitionvalues = transitionvalues1;
        j = getViewX(transitionvalues);
        k = getViewY(transitionvalues);
        transitionvalues = new int[2];
        viewgroup.getLocationOnScreen(transitionvalues);
        l = transitionvalues[0] + Math.round(viewgroup.getTranslationX());
        i1 = transitionvalues[1] + Math.round(viewgroup.getTranslationY());
        j1 = l + viewgroup.getWidth();
        k1 = i1 + viewgroup.getHeight();
        if(rect != null)
        {
            l1 = rect.centerX();
            i2 = rect.centerY();
        } else
        {
            l1 = (l + j1) / 2;
            i2 = (i1 + k1) / 2;
        }
        f = (float)distance(viewgroup, j, k, l1, i2, l, i1, j1, k1) / (float)getMaxDistance(viewgroup);
        l2 = transition.getDuration();
        l3 = l2;
        if(l2 < 0L)
            l3 = 300L;
        return (long)Math.round(((float)((long)i * l3) / mPropagationSpeed) * f);
    }

    public void setPropagationSpeed(float f)
    {
        if(f == 0.0F)
        {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        } else
        {
            mPropagationSpeed = f;
            return;
        }
    }

    public void setSide(int i)
    {
        mSide = i;
    }

    private static final String TAG = "SlidePropagation";
    private float mPropagationSpeed;
    private int mSide;
}
