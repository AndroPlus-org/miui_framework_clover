// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.graphics.Matrix;
import android.graphics.Rect;
import java.io.PrintWriter;

public class Transformation
{

    public Transformation()
    {
        mClipRect = new Rect();
        clear();
    }

    public void clear()
    {
        if(mMatrix == null)
            mMatrix = new Matrix();
        else
            mMatrix.reset();
        mClipRect.setEmpty();
        mHasClipRect = false;
        mAlpha = 1.0F;
        mTransformationType = 3;
    }

    public void compose(Transformation transformation)
    {
        mAlpha = mAlpha * transformation.getAlpha();
        mMatrix.preConcat(transformation.getMatrix());
        if(transformation.mHasClipRect)
        {
            transformation = transformation.getClipRect();
            if(mHasClipRect)
                setClipRect(mClipRect.left + ((Rect) (transformation)).left, mClipRect.top + ((Rect) (transformation)).top, mClipRect.right + ((Rect) (transformation)).right, mClipRect.bottom + ((Rect) (transformation)).bottom);
            else
                setClipRect(transformation);
        }
    }

    public float getAlpha()
    {
        return mAlpha;
    }

    public Rect getClipRect()
    {
        return mClipRect;
    }

    public Matrix getMatrix()
    {
        return mMatrix;
    }

    public int getTransformationType()
    {
        return mTransformationType;
    }

    public boolean hasClipRect()
    {
        return mHasClipRect;
    }

    public void postCompose(Transformation transformation)
    {
        mAlpha = mAlpha * transformation.getAlpha();
        mMatrix.postConcat(transformation.getMatrix());
        if(transformation.mHasClipRect)
        {
            transformation = transformation.getClipRect();
            if(mHasClipRect)
                setClipRect(mClipRect.left + ((Rect) (transformation)).left, mClipRect.top + ((Rect) (transformation)).top, mClipRect.right + ((Rect) (transformation)).right, mClipRect.bottom + ((Rect) (transformation)).bottom);
            else
                setClipRect(transformation);
        }
    }

    public void printShortString(PrintWriter printwriter)
    {
        printwriter.print("{alpha=");
        printwriter.print(mAlpha);
        printwriter.print(" matrix=");
        mMatrix.printShortString(printwriter);
        printwriter.print('}');
    }

    public void set(Transformation transformation)
    {
        mAlpha = transformation.getAlpha();
        mMatrix.set(transformation.getMatrix());
        if(transformation.mHasClipRect)
        {
            setClipRect(transformation.getClipRect());
        } else
        {
            mHasClipRect = false;
            mClipRect.setEmpty();
        }
        mTransformationType = transformation.getTransformationType();
    }

    public void setAlpha(float f)
    {
        mAlpha = f;
    }

    public void setClipRect(int i, int j, int k, int l)
    {
        mClipRect.set(i, j, k, l);
        mHasClipRect = true;
    }

    public void setClipRect(Rect rect)
    {
        setClipRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setTransformationType(int i)
    {
        mTransformationType = i;
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        toShortString(stringbuilder);
        return stringbuilder.toString();
    }

    public void toShortString(StringBuilder stringbuilder)
    {
        stringbuilder.append("{alpha=");
        stringbuilder.append(mAlpha);
        stringbuilder.append(" matrix=");
        mMatrix.toShortString(stringbuilder);
        stringbuilder.append('}');
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("Transformation");
        toShortString(stringbuilder);
        return stringbuilder.toString();
    }

    public static final int TYPE_ALPHA = 1;
    public static final int TYPE_BOTH = 3;
    public static final int TYPE_IDENTITY = 0;
    public static final int TYPE_MATRIX = 2;
    protected float mAlpha;
    private Rect mClipRect;
    private boolean mHasClipRect;
    protected Matrix mMatrix;
    protected int mTransformationType;
}
