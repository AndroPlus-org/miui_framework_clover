// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.graphics.*;
import java.io.*;
import java.util.ArrayList;

// Referenced classes of package android.gesture:
//            GesturePoint, GestureUtils, OrientedBoundingBox

public class GestureStroke
{

    private GestureStroke(RectF rectf, float f, float af[], long al[])
    {
        boundingBox = new RectF(rectf.left, rectf.top, rectf.right, rectf.bottom);
        length = f;
        points = (float[])af.clone();
        timestamps = (long[])al.clone();
    }

    public GestureStroke(ArrayList arraylist)
    {
        int i = arraylist.size();
        float af[] = new float[i * 2];
        long al[] = new long[i];
        RectF rectf = null;
        float f = 0.0F;
        int j = 0;
        int k = 0;
        while(k < i) 
        {
            GesturePoint gesturepoint = (GesturePoint)arraylist.get(k);
            af[k * 2] = gesturepoint.x;
            af[k * 2 + 1] = gesturepoint.y;
            al[j] = gesturepoint.timestamp;
            if(rectf == null)
            {
                rectf = new RectF();
                rectf.top = gesturepoint.y;
                rectf.left = gesturepoint.x;
                rectf.right = gesturepoint.x;
                rectf.bottom = gesturepoint.y;
                f = 0.0F;
            } else
            {
                f = (float)((double)f + Math.hypot(gesturepoint.x - af[(k - 1) * 2], gesturepoint.y - af[(k - 1) * 2 + 1]));
                rectf.union(gesturepoint.x, gesturepoint.y);
            }
            j++;
            k++;
        }
        timestamps = al;
        points = af;
        boundingBox = rectf;
        length = f;
    }

    static GestureStroke deserialize(DataInputStream datainputstream)
        throws IOException
    {
        int i = datainputstream.readInt();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(GesturePoint.deserialize(datainputstream));

        return new GestureStroke(arraylist);
    }

    private void makePath()
    {
        float af[];
        int i;
        Path path;
        float f;
        float f1;
        int j;
        af = points;
        i = af.length;
        path = null;
        f = 0.0F;
        f1 = 0.0F;
        j = 0;
_L2:
        float f2;
        float f3;
        Path path1;
        float f4;
        float f5;
        if(j >= i)
            break MISSING_BLOCK_LABEL_169;
        f2 = af[j];
        f3 = af[j + 1];
        if(path != null)
            break; /* Loop/switch isn't completed */
        path1 = new Path();
        path1.moveTo(f2, f3);
        f4 = f2;
        f5 = f3;
_L5:
        j += 2;
        f = f4;
        f1 = f5;
        path = path1;
        if(true) goto _L2; else goto _L1
_L1:
        float f6;
        f5 = Math.abs(f2 - f);
        f6 = Math.abs(f3 - f1);
        if(f5 >= 3F) goto _L4; else goto _L3
_L3:
        f4 = f;
        f5 = f1;
        path1 = path;
        if(f6 < 3F) goto _L5; else goto _L4
_L4:
        path.quadTo(f, f1, (f2 + f) / 2.0F, (f3 + f1) / 2.0F);
        f4 = f2;
        f5 = f3;
        path1 = path;
          goto _L5
        mCachedPath = path;
        return;
    }

    public void clearPath()
    {
        if(mCachedPath != null)
            mCachedPath.rewind();
    }

    public Object clone()
    {
        return new GestureStroke(boundingBox, length, points, timestamps);
    }

    public OrientedBoundingBox computeOrientedBoundingBox()
    {
        return GestureUtils.computeOrientedBoundingBox(points);
    }

    void draw(Canvas canvas, Paint paint)
    {
        if(mCachedPath == null)
            makePath();
        canvas.drawPath(mCachedPath, paint);
    }

    public Path getPath()
    {
        if(mCachedPath == null)
            makePath();
        return mCachedPath;
    }

    void serialize(DataOutputStream dataoutputstream)
        throws IOException
    {
        float af[] = points;
        long al[] = timestamps;
        int i = points.length;
        dataoutputstream.writeInt(i / 2);
        for(int j = 0; j < i; j += 2)
        {
            dataoutputstream.writeFloat(af[j]);
            dataoutputstream.writeFloat(af[j + 1]);
            dataoutputstream.writeLong(al[j / 2]);
        }

    }

    public Path toPath(float f, float f1, int i)
    {
        float af[];
        float f2;
        float f3;
        Path path1;
        int j;
        af = GestureUtils.temporalSampling(this, i);
        RectF rectf = boundingBox;
        GestureUtils.translate(af, -rectf.left, -rectf.top);
        f /= rectf.width();
        f1 /= rectf.height();
        if(f > f1)
            f = f1;
        GestureUtils.scale(af, f, f);
        f2 = 0.0F;
        f3 = 0.0F;
        path1 = null;
        j = af.length;
        i = 0;
_L2:
        Path path;
        float f4;
        float f5;
        if(i >= j)
            break MISSING_BLOCK_LABEL_227;
        f4 = af[i];
        f5 = af[i + 1];
        if(path1 != null)
            break; /* Loop/switch isn't completed */
        path = new Path();
        path.moveTo(f4, f5);
        f1 = f4;
        f = f5;
_L5:
        i += 2;
        f2 = f1;
        f3 = f;
        path1 = path;
        if(true) goto _L2; else goto _L1
_L1:
        float f6;
        f = Math.abs(f4 - f2);
        f6 = Math.abs(f5 - f3);
        if(f >= 3F) goto _L4; else goto _L3
_L3:
        f1 = f2;
        f = f3;
        path = path1;
        if(f6 < 3F) goto _L5; else goto _L4
_L4:
        path1.quadTo(f2, f3, (f4 + f2) / 2.0F, (f5 + f3) / 2.0F);
        f1 = f4;
        f = f5;
        path = path1;
          goto _L5
        return path1;
    }

    static final float TOUCH_TOLERANCE = 3F;
    public final RectF boundingBox;
    public final float length;
    private Path mCachedPath;
    public final float points[];
    private final long timestamps[];
}
