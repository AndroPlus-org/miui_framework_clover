// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.PathParser;

// Referenced classes of package android.transition:
//            PathMotion

public class PatternPathMotion extends PathMotion
{

    public PatternPathMotion()
    {
        mPatternPath = new Path();
        mTempMatrix = new Matrix();
        mPatternPath.lineTo(1.0F, 0.0F);
        mOriginalPatternPath = mPatternPath;
    }

    public PatternPathMotion(Context context, AttributeSet attributeset)
    {
        mPatternPath = new Path();
        mTempMatrix = new Matrix();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PatternPathMotion);
        attributeset = context.getString(0);
        if(attributeset != null)
            break MISSING_BLOCK_LABEL_64;
        attributeset = JVM INSTR new #51  <Class RuntimeException>;
        attributeset.RuntimeException("pathData must be supplied for patternPathMotion");
        throw attributeset;
        attributeset;
        context.recycle();
        throw attributeset;
        setPatternPath(PathParser.createPathFromPathData(attributeset));
        context.recycle();
        return;
    }

    public PatternPathMotion(Path path)
    {
        mPatternPath = new Path();
        mTempMatrix = new Matrix();
        setPatternPath(path);
    }

    public Path getPath(float f, float f1, float f2, float f3)
    {
        double d = f2 - f;
        double d1 = f3 - f1;
        f2 = (float)Math.hypot(d, d1);
        d = Math.atan2(d1, d);
        mTempMatrix.setScale(f2, f2);
        mTempMatrix.postRotate((float)Math.toDegrees(d));
        mTempMatrix.postTranslate(f, f1);
        Path path = new Path();
        mPatternPath.transform(mTempMatrix, path);
        return path;
    }

    public Path getPatternPath()
    {
        return mOriginalPatternPath;
    }

    public void setPatternPath(Path path)
    {
        PathMeasure pathmeasure = new PathMeasure(path, false);
        float f = pathmeasure.getLength();
        float af[] = new float[2];
        pathmeasure.getPosTan(f, af, null);
        f = af[0];
        float f1 = af[1];
        pathmeasure.getPosTan(0.0F, af, null);
        float f2 = af[0];
        float f3 = af[1];
        if(f2 == f && f3 == f1)
        {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        } else
        {
            mTempMatrix.setTranslate(-f2, -f3);
            f -= f2;
            f1 -= f3;
            f3 = 1.0F / (float)Math.hypot(f, f1);
            mTempMatrix.postScale(f3, f3);
            double d = Math.atan2(f1, f);
            mTempMatrix.postRotate((float)Math.toDegrees(-d));
            path.transform(mTempMatrix, mPatternPath);
            mOriginalPatternPath = path;
            return;
        }
    }

    private Path mOriginalPatternPath;
    private final Path mPatternPath;
    private final Matrix mTempMatrix;
}
