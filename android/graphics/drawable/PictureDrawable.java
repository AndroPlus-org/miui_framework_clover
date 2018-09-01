// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.graphics.*;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class PictureDrawable extends Drawable
{

    public PictureDrawable(Picture picture)
    {
        mPicture = picture;
    }

    public void draw(Canvas canvas)
    {
        if(mPicture != null)
        {
            Rect rect = getBounds();
            canvas.save();
            canvas.clipRect(rect);
            canvas.translate(rect.left, rect.top);
            canvas.drawPicture(mPicture);
            canvas.restore();
        }
    }

    public int getIntrinsicHeight()
    {
        int i;
        if(mPicture != null)
            i = mPicture.getHeight();
        else
            i = -1;
        return i;
    }

    public int getIntrinsicWidth()
    {
        int i;
        if(mPicture != null)
            i = mPicture.getWidth();
        else
            i = -1;
        return i;
    }

    public int getOpacity()
    {
        return -3;
    }

    public Picture getPicture()
    {
        return mPicture;
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setPicture(Picture picture)
    {
        mPicture = picture;
    }

    private Picture mPicture;
}
