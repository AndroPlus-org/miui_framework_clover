// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import java.util.Random;

// Referenced classes of package android.view.animation:
//            LayoutAnimationController, Animation, LinearInterpolator, Interpolator

public class GridLayoutAnimationController extends LayoutAnimationController
{
    public static class AnimationParameters extends LayoutAnimationController.AnimationParameters
    {

        public int column;
        public int columnsCount;
        public int row;
        public int rowsCount;

        public AnimationParameters()
        {
        }
    }


    public GridLayoutAnimationController(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.GridLayoutAnimation);
        mColumnDelay = Animation.Description.parseValue(context.peekValue(0)).value;
        mRowDelay = Animation.Description.parseValue(context.peekValue(1)).value;
        mDirection = context.getInt(2, 0);
        mDirectionPriority = context.getInt(3, 0);
        context.recycle();
    }

    public GridLayoutAnimationController(Animation animation)
    {
        this(animation, 0.5F, 0.5F);
    }

    public GridLayoutAnimationController(Animation animation, float f, float f1)
    {
        super(animation);
        mColumnDelay = f;
        mRowDelay = f1;
    }

    private int getTransformedColumnIndex(AnimationParameters animationparameters)
    {
        getOrder();
        JVM INSTR tableswitch 1 2: default 28
    //                   1 56
    //                   2 71;
           goto _L1 _L2 _L3
_L1:
        int i = animationparameters.column;
_L5:
        int j = i;
        if((mDirection & 1) == 1)
            j = animationparameters.columnsCount - 1 - i;
        return j;
_L2:
        i = animationparameters.columnsCount - 1 - animationparameters.column;
        continue; /* Loop/switch isn't completed */
_L3:
        if(mRandomizer == null)
            mRandomizer = new Random();
        i = (int)((float)animationparameters.columnsCount * mRandomizer.nextFloat());
        if(true) goto _L5; else goto _L4
_L4:
    }

    private int getTransformedRowIndex(AnimationParameters animationparameters)
    {
        getOrder();
        JVM INSTR tableswitch 1 2: default 28
    //                   1 56
    //                   2 71;
           goto _L1 _L2 _L3
_L1:
        int i = animationparameters.row;
_L5:
        int j = i;
        if((mDirection & 2) == 2)
            j = animationparameters.rowsCount - 1 - i;
        return j;
_L2:
        i = animationparameters.rowsCount - 1 - animationparameters.row;
        continue; /* Loop/switch isn't completed */
_L3:
        if(mRandomizer == null)
            mRandomizer = new Random();
        i = (int)((float)animationparameters.rowsCount * mRandomizer.nextFloat());
        if(true) goto _L5; else goto _L4
_L4:
    }

    public float getColumnDelay()
    {
        return mColumnDelay;
    }

    protected long getDelayForView(View view)
    {
        int i;
        int j;
        int k;
        int l;
        float f;
        float f1;
        view = (AnimationParameters)view.getLayoutParams().layoutAnimationParameters;
        if(view == null)
            return 0L;
        i = getTransformedColumnIndex(view);
        j = getTransformedRowIndex(view);
        k = ((AnimationParameters) (view)).rowsCount;
        l = ((AnimationParameters) (view)).columnsCount;
        long l1 = mAnimation.getDuration();
        f = mColumnDelay * (float)l1;
        f1 = mRowDelay * (float)l1;
        if(mInterpolator == null)
            mInterpolator = new LinearInterpolator();
        mDirectionPriority;
        JVM INSTR tableswitch 1 2: default 116
    //                   1 169
    //                   2 207;
           goto _L1 _L2 _L3
_L1:
        long l2;
        l2 = (long)((float)i * f + (float)j * f1);
        f = (float)l * f + (float)k * f1;
_L5:
        f1 = (float)l2 / f;
        return (long)(mInterpolator.getInterpolation(f1) * f);
_L2:
        l2 = (long)((float)j * f1 + (float)(i * k) * f1);
        f = (float)k * f1 + (float)(l * k) * f1;
        continue; /* Loop/switch isn't completed */
_L3:
        l2 = (long)((float)i * f + (float)(j * l) * f);
        f = (float)l * f + (float)(k * l) * f;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public int getDirection()
    {
        return mDirection;
    }

    public int getDirectionPriority()
    {
        return mDirectionPriority;
    }

    public float getRowDelay()
    {
        return mRowDelay;
    }

    public void setColumnDelay(float f)
    {
        mColumnDelay = f;
    }

    public void setDirection(int i)
    {
        mDirection = i;
    }

    public void setDirectionPriority(int i)
    {
        mDirectionPriority = i;
    }

    public void setRowDelay(float f)
    {
        mRowDelay = f;
    }

    public boolean willOverlap()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mColumnDelay >= 1.0F)
            if(mRowDelay < 1.0F)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static final int DIRECTION_BOTTOM_TO_TOP = 2;
    public static final int DIRECTION_HORIZONTAL_MASK = 1;
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int DIRECTION_TOP_TO_BOTTOM = 0;
    public static final int DIRECTION_VERTICAL_MASK = 2;
    public static final int PRIORITY_COLUMN = 1;
    public static final int PRIORITY_NONE = 0;
    public static final int PRIORITY_ROW = 2;
    private float mColumnDelay;
    private int mDirection;
    private int mDirectionPriority;
    private float mRowDelay;
}
