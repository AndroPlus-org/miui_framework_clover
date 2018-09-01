// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class MamlDrawable extends Drawable
{
    public static class MamlDrawableState extends android.graphics.drawable.Drawable.ConstantState
    {

        protected MamlDrawable createDrawable()
        {
            return null;
        }

        public int getChangingConfigurations()
        {
            return 0;
        }

        public Drawable newDrawable()
        {
            MamlDrawable mamldrawable = createDrawable();
            if(mamldrawable == null)
                return null;
            Drawable drawable = null;
            Rect rect = null;
            if(mStateBadgeDrawable != null)
                drawable = mStateBadgeDrawable.mutate();
            if(mStateBadgeLocation != null)
                rect = new Rect(mStateBadgeLocation.left, mStateBadgeLocation.top, mStateBadgeLocation.right, mStateBadgeLocation.bottom);
            mamldrawable.setBadgeInfo(drawable, rect);
            return mamldrawable;
        }

        protected Drawable mStateBadgeDrawable;
        protected Rect mStateBadgeLocation;

        public MamlDrawableState()
        {
        }
    }


    public MamlDrawable()
    {
        mInvalidateSelf = new Runnable() {

            public void run()
            {
                invalidateSelf();
            }

            final MamlDrawable this$0;

            
            {
                this$0 = MamlDrawable.this;
                super();
            }
        }
;
    }

    public void cleanUp()
    {
    }

    public void draw(Canvas canvas)
    {
        drawIcon(canvas);
        if(mBadgeDrawable != null)
        {
            if(mBadgeLocation == null)
                break MISSING_BLOCK_LABEL_80;
            mBadgeDrawable.setBounds(0, 0, mBadgeLocation.width(), mBadgeLocation.height());
            canvas.save();
            canvas.translate(mBadgeLocation.left, mBadgeLocation.top);
            mBadgeDrawable.draw(canvas);
            canvas.restore();
        }
_L1:
        return;
        try
        {
            mBadgeDrawable.setBounds(0, 0, mIntrinsicWidth, mIntrinsicHeight);
            mBadgeDrawable.draw(canvas);
        }
        // Misplaced declaration of an exception variable
        catch(Canvas canvas)
        {
            canvas.printStackTrace();
        }
        // Misplaced declaration of an exception variable
        catch(Canvas canvas)
        {
            canvas.printStackTrace();
        }
          goto _L1
    }

    protected void drawIcon(Canvas canvas)
    {
    }

    protected void finalize()
        throws Throwable
    {
        cleanUp();
        super.finalize();
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        return mState;
    }

    public int getIntrinsicHeight()
    {
        return mIntrinsicHeight;
    }

    public int getIntrinsicWidth()
    {
        return mIntrinsicWidth;
    }

    public int getOpacity()
    {
        return -3;
    }

    public void setAlpha(int i)
    {
    }

    public void setBadgeInfo(Drawable drawable, Rect rect)
    {
        while(rect != null && (rect.left < 0 || rect.top < 0 || rect.width() > mIntrinsicWidth || rect.height() > mIntrinsicHeight)) 
            throw new IllegalArgumentException((new StringBuilder()).append("Badge location ").append(rect).append(" not in badged drawable bounds ").append(new Rect(0, 0, mIntrinsicWidth, mIntrinsicHeight)).toString());
        mBadgeDrawable = drawable;
        mBadgeLocation = rect;
        mState.mStateBadgeDrawable = drawable;
        mState.mStateBadgeLocation = rect;
    }

    public void setBounds(int i, int j, int k, int l)
    {
        super.setBounds(i, j, k, l);
        mWidth = k - i;
        mHeight = l - j;
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setIntrinsicSize(int i, int j)
    {
        mIntrinsicWidth = i;
        mIntrinsicHeight = j;
    }

    protected Drawable mBadgeDrawable;
    protected Rect mBadgeLocation;
    protected int mHeight;
    protected int mIntrinsicHeight;
    protected int mIntrinsicWidth;
    protected Runnable mInvalidateSelf;
    protected MamlDrawableState mState;
    protected int mWidth;
}
