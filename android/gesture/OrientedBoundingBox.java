// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.graphics.Matrix;
import android.graphics.Path;

public class OrientedBoundingBox
{

    OrientedBoundingBox(float f, float f1, float f2, float f3, float f4)
    {
        orientation = f;
        width = f3;
        height = f4;
        centerX = f1;
        centerY = f2;
        f = f3 / f4;
        if(f > 1.0F)
            squareness = 1.0F / f;
        else
            squareness = f;
    }

    public Path toPath()
    {
        Path path = new Path();
        float af[] = new float[2];
        af[0] = -width / 2.0F;
        af[1] = height / 2.0F;
        Matrix matrix = new Matrix();
        matrix.setRotate(orientation);
        matrix.postTranslate(centerX, centerY);
        matrix.mapPoints(af);
        path.moveTo(af[0], af[1]);
        af[0] = -width / 2.0F;
        af[1] = -height / 2.0F;
        matrix.mapPoints(af);
        path.lineTo(af[0], af[1]);
        af[0] = width / 2.0F;
        af[1] = -height / 2.0F;
        matrix.mapPoints(af);
        path.lineTo(af[0], af[1]);
        af[0] = width / 2.0F;
        af[1] = height / 2.0F;
        matrix.mapPoints(af);
        path.lineTo(af[0], af[1]);
        path.close();
        return path;
    }

    public final float centerX;
    public final float centerY;
    public final float height;
    public final float orientation;
    public final float squareness;
    public final float width;
}
