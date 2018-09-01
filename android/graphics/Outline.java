// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Rect, Path

public final class Outline
{

    public Outline()
    {
        mMode = 0;
        mRect = new Rect();
        mRadius = (-1.0F / 0.0F);
    }

    public Outline(Outline outline)
    {
        mMode = 0;
        mRect = new Rect();
        mRadius = (-1.0F / 0.0F);
        set(outline);
    }

    public boolean canClip()
    {
        boolean flag;
        if(mMode != 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public float getAlpha()
    {
        return mAlpha;
    }

    public float getRadius()
    {
        return mRadius;
    }

    public boolean getRect(Rect rect)
    {
        if(mMode != 1)
        {
            return false;
        } else
        {
            rect.set(mRect);
            return true;
        }
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(mMode == 0)
            flag = true;
        return flag;
    }

    public void offset(int i, int j)
    {
        if(mMode != 1) goto _L2; else goto _L1
_L1:
        mRect.offset(i, j);
_L4:
        return;
_L2:
        if(mMode == 2)
            mPath.offset(i, j);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void set(Outline outline)
    {
        mMode = outline.mMode;
        if(outline.mMode == 2)
        {
            if(mPath == null)
                mPath = new Path();
            mPath.set(outline.mPath);
        }
        mRect.set(outline.mRect);
        mRadius = outline.mRadius;
        mAlpha = outline.mAlpha;
    }

    public void setAlpha(float f)
    {
        mAlpha = f;
    }

    public void setConvexPath(Path path)
    {
        if(path.isEmpty())
        {
            setEmpty();
            return;
        }
        if(!path.isConvex())
            throw new IllegalArgumentException("path must be convex");
        if(mPath == null)
            mPath = new Path();
        mMode = 2;
        mPath.set(path);
        mRect.setEmpty();
        mRadius = (-1.0F / 0.0F);
    }

    public void setEmpty()
    {
        if(mPath != null)
            mPath.rewind();
        mMode = 0;
        mRect.setEmpty();
        mRadius = (-1.0F / 0.0F);
    }

    public void setOval(int i, int j, int k, int l)
    {
        if(i >= k || j >= l)
        {
            setEmpty();
            return;
        }
        if(l - j == k - i)
        {
            setRoundRect(i, j, k, l, (float)(l - j) / 2.0F);
            return;
        }
        if(mPath == null)
            mPath = new Path();
        else
            mPath.rewind();
        mMode = 2;
        mPath.addOval(i, j, k, l, Path.Direction.CW);
        mRect.setEmpty();
        mRadius = (-1.0F / 0.0F);
    }

    public void setOval(Rect rect)
    {
        setOval(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setRect(int i, int j, int k, int l)
    {
        setRoundRect(i, j, k, l, 0.0F);
    }

    public void setRect(Rect rect)
    {
        setRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setRoundRect(int i, int j, int k, int l, float f)
    {
        if(i >= k || j >= l)
        {
            setEmpty();
            return;
        }
        if(mMode == 2)
            mPath.rewind();
        mMode = 1;
        mRect.set(i, j, k, l);
        mRadius = f;
    }

    public void setRoundRect(Rect rect, float f)
    {
        setRoundRect(rect.left, rect.top, rect.right, rect.bottom, f);
    }

    public static final int MODE_CONVEX_PATH = 2;
    public static final int MODE_EMPTY = 0;
    public static final int MODE_ROUND_RECT = 1;
    private static final float RADIUS_UNDEFINED = (-1.0F / 0.0F);
    public float mAlpha;
    public int mMode;
    public Path mPath;
    public float mRadius;
    public final Rect mRect;
}
