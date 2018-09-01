// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.graphics.Point;
import android.graphics.Rect;

public final class Face
{

    public Face(Rect rect, int i)
    {
        this(rect, i, -1, null, null, null);
    }

    public Face(Rect rect, int i, int j, Point point, Point point1, Point point2)
    {
        checkNotNull("bounds", rect);
        if(i < 1 || i > 100)
            throw new IllegalArgumentException("Confidence out of range");
        if(j < 0 && j != -1)
            throw new IllegalArgumentException("Id out of range");
        if(j == -1)
        {
            checkNull("leftEyePosition", point);
            checkNull("rightEyePosition", point1);
            checkNull("mouthPosition", point2);
        }
        mBounds = rect;
        mScore = i;
        mId = j;
        mLeftEye = point;
        mRightEye = point1;
        mMouth = point2;
    }

    private static void checkNotNull(String s, Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" was required, but it was null").toString());
        else
            return;
    }

    private static void checkNull(String s, Object obj)
    {
        if(obj != null)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" was required to be null, but it wasn't").toString());
        else
            return;
    }

    public Rect getBounds()
    {
        return mBounds;
    }

    public int getId()
    {
        return mId;
    }

    public Point getLeftEyePosition()
    {
        return mLeftEye;
    }

    public Point getMouthPosition()
    {
        return mMouth;
    }

    public Point getRightEyePosition()
    {
        return mRightEye;
    }

    public int getScore()
    {
        return mScore;
    }

    public String toString()
    {
        return String.format("{ bounds: %s, score: %s, id: %d, leftEyePosition: %s, rightEyePosition: %s, mouthPosition: %s }", new Object[] {
            mBounds, Integer.valueOf(mScore), Integer.valueOf(mId), mLeftEye, mRightEye, mMouth
        });
    }

    public static final int ID_UNSUPPORTED = -1;
    public static final int SCORE_MAX = 100;
    public static final int SCORE_MIN = 1;
    private final Rect mBounds;
    private final int mId;
    private final Point mLeftEye;
    private final Point mMouth;
    private final Point mRightEye;
    private final int mScore;
}
