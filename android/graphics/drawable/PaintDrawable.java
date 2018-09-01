// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package android.graphics.drawable:
//            ShapeDrawable

public class PaintDrawable extends ShapeDrawable
{

    public PaintDrawable()
    {
    }

    public PaintDrawable(int i)
    {
        getPaint().setColor(i);
    }

    protected boolean inflateTag(String s, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(!s.equals("corners"))
            break MISSING_BLOCK_LABEL_165;
        s = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.DrawableCorners);
        i = s.getDimensionPixelSize(0, 0);
        setCornerRadius(i);
        j = s.getDimensionPixelSize(1, i);
        k = s.getDimensionPixelSize(2, i);
        l = s.getDimensionPixelSize(3, i);
        i1 = s.getDimensionPixelSize(4, i);
        break MISSING_BLOCK_LABEL_70;
        if(j != i || k != i || l != i || i1 != i)
            setCornerRadii(new float[] {
                (float)j, (float)j, (float)k, (float)k, (float)l, (float)l, (float)i1, (float)i1
            });
        s.recycle();
        return true;
        return super.inflateTag(s, resources, xmlpullparser, attributeset);
    }

    public void setCornerRadii(float af[])
    {
        if(af == null)
        {
            if(getShape() != null)
                setShape(null);
        } else
        {
            setShape(new RoundRectShape(af, null, null));
        }
        invalidateSelf();
    }

    public void setCornerRadius(float f)
    {
        float af[] = null;
        if(f > 0.0F)
        {
            float af1[] = new float[8];
            int i = 0;
            do
            {
                af = af1;
                if(i >= 8)
                    break;
                af1[i] = f;
                i++;
            } while(true);
        }
        setCornerRadii(af);
    }
}
