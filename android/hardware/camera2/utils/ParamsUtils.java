// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.graphics.*;
import android.hardware.camera2.CaptureRequest;
import android.util.Rational;
import android.util.Size;
import com.android.internal.util.Preconditions;

public class ParamsUtils
{

    private ParamsUtils()
    {
        throw new AssertionError();
    }

    public static void convertRectF(Rect rect, RectF rectf)
    {
        Preconditions.checkNotNull(rect, "source must not be null");
        Preconditions.checkNotNull(rectf, "destination must not be null");
        rectf.left = rect.left;
        rectf.right = rect.right;
        rectf.bottom = rect.bottom;
        rectf.top = rect.top;
    }

    public static Rational createRational(float f)
    {
        if(Float.isNaN(f))
            return Rational.NaN;
        if(f == (1.0F / 0.0F))
            return Rational.POSITIVE_INFINITY;
        if(f == (-1.0F / 0.0F))
            return Rational.NEGATIVE_INFINITY;
        if(f == 0.0F)
            return Rational.ZERO;
        int i = 0xf4240;
        do
        {
            for(float f1 = f * (float)i; f1 > -2.147484E+009F && f1 < 2.147484E+009F || i == 1;)
                return new Rational((int)f1, i);

            i /= 10;
        } while(true);
    }

    public static Rect createRect(RectF rectf)
    {
        Preconditions.checkNotNull(rectf, "rect must not be null");
        Rect rect = new Rect();
        rectf.roundOut(rect);
        return rect;
    }

    public static Rect createRect(Size size)
    {
        Preconditions.checkNotNull(size, "size must not be null");
        return new Rect(0, 0, size.getWidth(), size.getHeight());
    }

    public static Size createSize(Rect rect)
    {
        Preconditions.checkNotNull(rect, "rect must not be null");
        return new Size(rect.width(), rect.height());
    }

    public static Object getOrDefault(CaptureRequest capturerequest, android.hardware.camera2.CaptureRequest.Key key, Object obj)
    {
        Preconditions.checkNotNull(capturerequest, "r must not be null");
        Preconditions.checkNotNull(key, "key must not be null");
        Preconditions.checkNotNull(obj, "defaultValue must not be null");
        capturerequest = ((CaptureRequest) (capturerequest.get(key)));
        if(capturerequest == null)
            return obj;
        else
            return capturerequest;
    }

    public static Rect mapRect(Matrix matrix, Rect rect)
    {
        Preconditions.checkNotNull(matrix, "transform must not be null");
        Preconditions.checkNotNull(rect, "rect must not be null");
        rect = new RectF(rect);
        matrix.mapRect(rect);
        return createRect(rect);
    }

    private static final int RATIONAL_DENOMINATOR = 0xf4240;
}
