// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.PathParser;
import android.view.InflateException;
import com.android.internal.view.animation.NativeInterpolatorFactory;
import com.android.internal.view.animation.NativeInterpolatorFactoryHelper;

// Referenced classes of package android.view.animation:
//            BaseInterpolator

public class PathInterpolator extends BaseInterpolator
    implements NativeInterpolatorFactory
{

    public PathInterpolator(float f, float f1)
    {
        initQuad(f, f1);
    }

    public PathInterpolator(float f, float f1, float f2, float f3)
    {
        initCubic(f, f1, f2, f3);
    }

    public PathInterpolator(Context context, AttributeSet attributeset)
    {
        this(context.getResources(), context.getTheme(), attributeset);
    }

    public PathInterpolator(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset)
    {
        if(theme != null)
            resources = theme.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PathInterpolator, 0, 0);
        else
            resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.PathInterpolator);
        parseInterpolatorFromTypeArray(resources);
        setChangingConfiguration(resources.getChangingConfigurations());
        resources.recycle();
    }

    public PathInterpolator(Path path)
    {
        initPath(path);
    }

    private void initCubic(float f, float f1, float f2, float f3)
    {
        Path path = new Path();
        path.moveTo(0.0F, 0.0F);
        path.cubicTo(f, f1, f2, f3, 1.0F, 1.0F);
        initPath(path);
    }

    private void initPath(Path path)
    {
        path = path.approximate(0.002F);
        int i;
        for(i = path.length / 3; path[1] != 0.0F || path[2] != 0.0F || path[path.length - 2] != 1.0F || path[path.length - 1] != 1.0F;)
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");

        mX = new float[i];
        mY = new float[i];
        float f = 0.0F;
        float f1 = 0.0F;
        int j = 0;
        for(int k = 0; j < i; k++)
        {
            int l = k + 1;
            float f2 = path[k];
            k = l + 1;
            float f3 = path[l];
            float f4 = path[k];
            if(f2 == f1 && f3 != f)
                throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis.");
            if(f3 < f)
                throw new IllegalArgumentException("The Path cannot loop back on itself.");
            mX[j] = f3;
            mY[j] = f4;
            f = f3;
            f1 = f2;
            j++;
        }

    }

    private void initQuad(float f, float f1)
    {
        Path path = new Path();
        path.moveTo(0.0F, 0.0F);
        path.quadTo(f, f1, 1.0F, 1.0F);
        initPath(path);
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedarray)
    {
        if(typedarray.hasValue(4))
        {
            String s = typedarray.getString(4);
            typedarray = PathParser.createPathFromPathData(s);
            if(typedarray == null)
                throw new InflateException((new StringBuilder()).append("The path is null, which is created from ").append(s).toString());
            initPath(typedarray);
        } else
        {
            if(!typedarray.hasValue(0))
                throw new InflateException("pathInterpolator requires the controlX1 attribute");
            if(!typedarray.hasValue(1))
                throw new InflateException("pathInterpolator requires the controlY1 attribute");
            float f = typedarray.getFloat(0, 0.0F);
            float f1 = typedarray.getFloat(1, 0.0F);
            boolean flag = typedarray.hasValue(2);
            if(flag != typedarray.hasValue(3))
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            if(!flag)
                initQuad(f, f1);
            else
                initCubic(f, f1, typedarray.getFloat(2, 0.0F), typedarray.getFloat(3, 0.0F));
        }
    }

    public long createNativeInterpolator()
    {
        return NativeInterpolatorFactoryHelper.createPathInterpolator(mX, mY);
    }

    public float getInterpolation(float f)
    {
        if(f <= 0.0F)
            return 0.0F;
        if(f >= 1.0F)
            return 1.0F;
        int i = 0;
        int j;
        for(j = mX.length - 1; j - i > 1;)
        {
            int k = (i + j) / 2;
            if(f < mX[k])
                j = k;
            else
                i = k;
        }

        float f1 = mX[j] - mX[i];
        if(f1 == 0.0F)
        {
            return mY[i];
        } else
        {
            f = (f - mX[i]) / f1;
            f1 = mY[i];
            return (mY[j] - f1) * f + f1;
        }
    }

    private static final float PRECISION = 0.002F;
    private float mX[];
    private float mY[];
}
