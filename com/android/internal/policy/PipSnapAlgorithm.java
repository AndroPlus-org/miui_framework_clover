// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.util.Size;
import android.view.Gravity;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PipSnapAlgorithm
{

    public PipSnapAlgorithm(Context context)
    {
        mSnapMode = 3;
        mOrientation = 0;
        Resources resources = context.getResources();
        mContext = context;
        mMinimizedVisibleSize = resources.getDimensionPixelSize(0x1050146);
        mDefaultSizePercent = resources.getFloat(0x105003e);
        mMaxAspectRatioForMinSize = resources.getFloat(0x105003c);
        mMinAspectRatioForMinSize = 1.0F / mMaxAspectRatioForMinSize;
        mFlingDeceleration = mContext.getResources().getDimensionPixelSize(0x1050145);
        onConfigurationChanged();
    }

    private void calculateSnapTargets()
    {
        mSnapGravities.clear();
        mSnapMode;
        JVM INSTR tableswitch 0 4: default 44
    //                   0 79
    //                   1 45
    //                   2 44
    //                   3 79
    //                   4 79;
           goto _L1 _L2 _L3 _L1 _L2 _L2
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        return;
_L3:
        if(mOrientation == 2)
        {
            mSnapGravities.add(Integer.valueOf(49));
            mSnapGravities.add(Integer.valueOf(81));
        } else
        {
            mSnapGravities.add(Integer.valueOf(19));
            mSnapGravities.add(Integer.valueOf(21));
        }
        mSnapGravities.add(Integer.valueOf(51));
        mSnapGravities.add(Integer.valueOf(53));
        mSnapGravities.add(Integer.valueOf(83));
        mSnapGravities.add(Integer.valueOf(85));
        if(true) goto _L1; else goto _L4
_L4:
    }

    private float distanceToPoint(Point point, int i, int j)
    {
        return PointF.length(point.x - i, point.y - j);
    }

    private Point findClosestPoint(int i, int j, Point apoint[])
    {
        Point point = null;
        float f = 3.402823E+038F;
        int k = 0;
        for(int l = apoint.length; k < l;)
        {
            Point point1 = apoint[k];
            float f1 = distanceToPoint(point1, i, j);
            float f2 = f;
            if(f1 < f)
            {
                point = point1;
                f2 = f1;
            }
            k++;
            f = f2;
        }

        return point;
    }

    private int findX(float f, float f1, float f2)
    {
        return (int)((f2 - f1) / f);
    }

    private int findY(float f, float f1, float f2)
    {
        return (int)(f * f2 + f1);
    }

    private void snapRectToClosestEdge(Rect rect, Rect rect1, Rect rect2)
    {
        int i = Math.max(rect1.left, Math.min(rect1.right, rect.left));
        int j = Math.max(rect1.top, Math.min(rect1.bottom, rect.top));
        rect2.set(rect);
        if(mIsMinimized)
        {
            rect2.offsetTo(i, j);
            return;
        }
        int k = Math.abs(rect.left - rect1.left);
        int l = Math.abs(rect.top - rect1.top);
        int i1 = Math.abs(rect1.right - rect.left);
        int j1 = Math.abs(rect1.bottom - rect.top);
        if(mSnapMode == 4)
        {
            if(mOrientation == 2)
                j1 = Math.min(l, j1);
            else
                j1 = Math.min(k, i1);
        } else
        {
            j1 = Math.min(Math.min(k, i1), Math.min(l, j1));
        }
        if(j1 == k)
            rect2.offsetTo(rect1.left, j);
        else
        if(j1 == l)
            rect2.offsetTo(i, rect1.top);
        else
        if(j1 == i1)
            rect2.offsetTo(rect1.right, j);
        else
            rect2.offsetTo(i, rect1.bottom);
    }

    public void applyMinimizedOffset(Rect rect, Rect rect1, Point point, Rect rect2)
    {
        if(rect.left <= rect1.centerX())
            rect.offsetTo((rect2.left + mMinimizedVisibleSize) - rect.width(), rect.top);
        else
            rect.offsetTo(point.x - rect2.right - mMinimizedVisibleSize, rect.top);
    }

    public void applySnapFraction(Rect rect, Rect rect1, float f)
    {
        if(f < 1.0F)
            rect.offsetTo(rect1.left + (int)((float)rect1.width() * f), rect1.top);
        else
        if(f < 2.0F)
        {
            int i = rect1.top;
            int k = (int)((float)rect1.height() * (f - 1.0F));
            rect.offsetTo(rect1.right, i + k);
        } else
        if(f < 3F)
        {
            rect.offsetTo(rect1.left + (int)((1.0F - (f - 2.0F)) * (float)rect1.width()), rect1.bottom);
        } else
        {
            int j = rect1.top;
            int l = (int)((1.0F - (f - 3F)) * (float)rect1.height());
            rect.offsetTo(rect1.left, j + l);
        }
    }

    public void dump(PrintWriter printwriter, String s)
    {
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.println((new StringBuilder()).append(s).append(com/android/internal/policy/PipSnapAlgorithm.getSimpleName()).toString());
        printwriter.println((new StringBuilder()).append(s1).append("mSnapMode=").append(mSnapMode).toString());
        printwriter.println((new StringBuilder()).append(s1).append("mOrientation=").append(mOrientation).toString());
        printwriter.println((new StringBuilder()).append(s1).append("mMinimizedVisibleSize=").append(mMinimizedVisibleSize).toString());
        printwriter.println((new StringBuilder()).append(s1).append("mIsMinimized=").append(mIsMinimized).toString());
    }

    public Rect findClosestSnapBounds(Rect rect, Rect rect1)
    {
        Object obj = new Rect(rect.left, rect.top, rect.right + rect1.width(), rect.bottom + rect1.height());
        Rect rect2 = new Rect(rect1);
        if(mSnapMode == 4 || mSnapMode == 3)
        {
            Rect rect3 = new Rect();
            Point apoint1[] = new Point[mSnapGravities.size()];
            for(int i = 0; i < mSnapGravities.size(); i++)
            {
                Gravity.apply(((Integer)mSnapGravities.get(i)).intValue(), rect1.width(), rect1.height(), ((Rect) (obj)), 0, 0, rect3);
                apoint1[i] = new Point(rect3.left, rect3.top);
            }

            obj = findClosestPoint(rect1.left, rect1.top, apoint1);
            if(distanceToPoint(((Point) (obj)), rect1.left, rect1.top) < (float)Math.max(rect1.width(), rect1.height()) * 0.3F)
                rect2.offsetTo(((Point) (obj)).x, ((Point) (obj)).y);
            else
                snapRectToClosestEdge(rect1, rect, rect2);
        } else
        if(mSnapMode == 2)
        {
            snapRectToClosestEdge(rect1, rect, rect2);
        } else
        {
            rect = new Rect();
            Point apoint[] = new Point[mSnapGravities.size()];
            for(int j = 0; j < mSnapGravities.size(); j++)
            {
                Gravity.apply(((Integer)mSnapGravities.get(j)).intValue(), rect1.width(), rect1.height(), ((Rect) (obj)), 0, 0, rect);
                apoint[j] = new Point(rect.left, rect.top);
            }

            rect = findClosestPoint(rect1.left, rect1.top, apoint);
            rect2.offsetTo(((Point) (rect)).x, ((Point) (rect)).y);
        }
        return rect2;
    }

    public Rect findClosestSnapBounds(Rect rect, Rect rect1, float f, float f1, Point point)
    {
        Rect rect2 = new Rect(rect1);
        rect1 = getEdgeIntersect(rect1, rect, f, f1, point);
        rect2.offsetTo(((Point) (rect1)).x, ((Point) (rect1)).y);
        return findClosestSnapBounds(rect, rect2);
    }

    public Point getEdgeIntersect(Rect rect, Rect rect1, float f, float f1, Point point)
    {
        int i;
        int k;
        int l;
        float f2;
        float f4;
        Point point1;
        Point point2;
        int i1;
        if(mOrientation == 2)
            i = 1;
        else
            i = 0;
        k = rect.left;
        l = rect.top;
        f2 = f1 / f;
        f4 = (float)l - (float)k * f2;
        point1 = new Point();
        point2 = new Point();
        if(f > 0.0F)
            i1 = rect1.right;
        else
            i1 = rect1.left;
        point1.x = i1;
        point1.y = findY(f2, f4, point1.x);
        if(f1 > 0.0F)
            i1 = rect1.bottom;
        else
            i1 = rect1.top;
        point2.y = i1;
        point2.x = findX(f2, f4, point2.y);
        if(i != 0)
        {
            if(f > 0.0F)
                i1 = rect1.right - rect.left;
            else
                i1 = rect.left - rect1.left;
        } else
        if(f1 > 0.0F)
            i1 = rect1.bottom - rect.top;
        else
            i1 = rect.top - rect1.top;
        double d;
        double d1;
        if(i1 > 0)
        {
            int j1;
            int k1;
            int l1;
            if(i != 0)
                j1 = point.y;
            else
                j1 = point.x;
            if(i != 0)
                k1 = point2.y;
            else
                k1 = point2.x;
            for(l1 = rect1.centerX(); j1 < l1 && k1 < l1 || j1 > l1 && k1 > l1;)
            {
                float f3;
                if(i != 0)
                    f3 = f;
                else
                    f3 = f1;
                i1 = Math.min((int)(0.0D - Math.pow(f3, 2D)) / (mFlingDeceleration * 2), i1);
                if(i != 0)
                {
                    i = rect.left;
                    if(f <= 0.0F)
                        i1 = -i1;
                    point2.x = i + i1;
                } else
                {
                    int j = rect.top;
                    if(f1 <= 0.0F)
                        i1 = -i1;
                    point2.y = j + i1;
                }
                return point2;
            }

        }
        d = Math.hypot(point1.x - k, point1.y - l);
        d1 = Math.hypot(point2.x - k, point2.y - l);
        if(d == 0.0D)
            return point2;
        if(d1 == 0.0D)
            return point1;
        if(Math.abs(d) > Math.abs(d1))
            rect = point2;
        else
            rect = point1;
        return rect;
    }

    public void getMovementBounds(Rect rect, Rect rect1, Rect rect2, int i)
    {
        rect2.set(rect1);
        rect2.right = Math.max(rect1.left, rect1.right - rect.width());
        rect2.bottom = Math.max(rect1.top, rect1.bottom - rect.height());
        rect2.bottom = rect2.bottom - i;
    }

    public Size getSizeForAspectRatio(float f, float f1, int i, int j)
    {
        i = (int)Math.max(f1, (float)Math.min(i, j) * mDefaultSizePercent);
        if(f <= mMinAspectRatioForMinSize || f > mMaxAspectRatioForMinSize)
        {
            if(f <= 1.0F)
            {
                j = i;
                i = Math.round((float)i / f);
            } else
            {
                j = i;
                int k = Math.round((float)i * f);
                i = j;
                j = k;
            }
        } else
        {
            f1 = PointF.length(mMaxAspectRatioForMinSize * (float)i, i);
            i = (int)Math.round(Math.sqrt((f1 * f1) / (f * f + 1.0F)));
            j = Math.round((float)i * f);
        }
        return new Size(j, i);
    }

    public float getSnapFraction(Rect rect, Rect rect1)
    {
        Rect rect2 = new Rect();
        snapRectToClosestEdge(rect, rect1, rect2);
        float f = (float)(rect2.left - rect1.left) / (float)rect1.width();
        float f1 = (float)(rect2.top - rect1.top) / (float)rect1.height();
        if(rect2.top == rect1.top)
            return f;
        if(rect2.left == rect1.right)
            return 1.0F + f1;
        if(rect2.top == rect1.bottom)
            return (1.0F - f) + 2.0F;
        else
            return (1.0F - f1) + 3F;
    }

    public void onConfigurationChanged()
    {
        Resources resources = mContext.getResources();
        mOrientation = resources.getConfiguration().orientation;
        mSnapMode = resources.getInteger(0x10e008c);
        calculateSnapTargets();
    }

    public void setMinimized(boolean flag)
    {
        mIsMinimized = flag;
    }

    private static final float CORNER_MAGNET_THRESHOLD = 0.3F;
    private static final int SNAP_MODE_CORNERS_AND_SIDES = 1;
    private static final int SNAP_MODE_CORNERS_ONLY = 0;
    private static final int SNAP_MODE_EDGE = 2;
    private static final int SNAP_MODE_EDGE_MAGNET_CORNERS = 3;
    private static final int SNAP_MODE_LONG_EDGE_MAGNET_CORNERS = 4;
    private final Context mContext;
    private final float mDefaultSizePercent;
    private final int mDefaultSnapMode = 3;
    private final int mFlingDeceleration;
    private boolean mIsMinimized;
    private final float mMaxAspectRatioForMinSize;
    private final float mMinAspectRatioForMinSize;
    private final int mMinimizedVisibleSize;
    private int mOrientation;
    private final ArrayList mSnapGravities = new ArrayList();
    private int mSnapMode;
}
