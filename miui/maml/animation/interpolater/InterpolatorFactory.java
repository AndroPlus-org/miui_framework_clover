// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Interpolator;

// Referenced classes of package miui.maml.animation.interpolater:
//            BackEaseInInterpolater, BackEaseOutInterpolater, BackEaseInOutInterpolater, BounceEaseInInterpolater, 
//            BounceEaseOutInterpolater, BounceEaseInOutInterpolater, CircEaseInInterpolater, CircEaseOutInterpolater, 
//            CircEaseInOutInterpolater, CubicEaseInInterpolater, CubicEaseOutInterpolater, CubicEaseInOutInterpolater, 
//            ElasticEaseInInterpolater, ElasticEaseOutInterpolater, ElasticEaseInOutInterpolater, ExpoEaseInInterpolater, 
//            ExpoEaseOutInterpolater, ExpoEaseInOutInterpolater, QuadEaseInInterpolater, QuadEaseOutInterpolater, 
//            QuadEaseInOutInterpolater, QuartEaseInInterpolater, QuartEaseOutInterpolater, QuartEaseInOutInterpolater, 
//            QuintEaseInInterpolater, QuintEaseOutInterpolater, QuintEaseInOutInterpolater, SineEaseInInterpolater, 
//            SineEaseOutInterpolater, SineEaseInOutInterpolater, LinearInterpolater

public class InterpolatorFactory
{

    public InterpolatorFactory()
    {
    }

    public static Interpolator create(String s)
    {
        float f3;
        boolean flag;
        boolean flag2;
        float f4;
        boolean flag3;
        int k;
        float f5;
        String s1;
        String s2;
        String s3;
        if(TextUtils.isEmpty(s))
            return null;
        int i = s.indexOf('(');
        int j = s.indexOf(')');
        float f = 0.0F;
        float f1 = 0.0F;
        f3 = 0.0F;
        flag = false;
        boolean flag1 = false;
        flag2 = false;
        f4 = f1;
        flag3 = flag;
        k = ((flag1) ? 1 : 0);
        f5 = f3;
        if(i == -1)
            break MISSING_BLOCK_LABEL_196;
        f4 = f1;
        flag3 = flag;
        k = ((flag1) ? 1 : 0);
        f5 = f3;
        if(j == -1)
            break MISSING_BLOCK_LABEL_196;
        flag = true;
        s1 = s.substring(i + 1, j);
        s2 = s1;
        s3 = "";
        k = s1.indexOf(",");
        if(k != -1)
        {
            flag2 = true;
            s2 = s1.substring(0, k);
            s3 = s1.substring(k + 1);
        }
        f4 = f;
        float f2 = Float.parseFloat(s2);
        f4 = f2;
        flag3 = flag;
        k = ((flag2) ? 1 : 0);
        f5 = f3;
        if(!flag2)
            break MISSING_BLOCK_LABEL_196;
        f4 = f2;
        f5 = Float.parseFloat(s3);
        k = ((flag2) ? 1 : 0);
        flag3 = flag;
        f4 = f2;
_L2:
        if("BackEaseIn".equalsIgnoreCase(s))
            return new BackEaseInInterpolater();
        break; /* Loop/switch isn't completed */
        NumberFormatException numberformatexception;
        numberformatexception;
        Log.d("InterpolatorFactory", (new StringBuilder()).append("parse error:").append(s1).toString());
        flag3 = flag;
        k = ((flag2) ? 1 : 0);
        f5 = f3;
        if(true) goto _L2; else goto _L1
_L1:
        if("BackEaseOut".equalsIgnoreCase(s))
            return new BackEaseOutInterpolater();
        if("BackEaseInOut".equalsIgnoreCase(s))
            return new BackEaseInOutInterpolater();
        if(s.startsWith("BackEaseIn") && flag3)
            return new BackEaseInInterpolater(f4);
        if(s.startsWith("BackEaseOut") && flag3)
            return new BackEaseOutInterpolater(f4);
        if(s.startsWith("BackEaseInOut") && flag3)
            return new BackEaseInOutInterpolater(f4);
        if("BounceEaseIn".equalsIgnoreCase(s))
            return new BounceEaseInInterpolater();
        if("BounceEaseOut".equalsIgnoreCase(s))
            return new BounceEaseOutInterpolater();
        if("BounceEaseInOut".equalsIgnoreCase(s))
            return new BounceEaseInOutInterpolater();
        if("CircEaseIn".equalsIgnoreCase(s))
            return new CircEaseInInterpolater();
        if("CircEaseOut".equalsIgnoreCase(s))
            return new CircEaseOutInterpolater();
        if("CircEaseInOut".equalsIgnoreCase(s))
            return new CircEaseInOutInterpolater();
        if("CubicEaseIn".equalsIgnoreCase(s))
            return new CubicEaseInInterpolater();
        if("CubicEaseOut".equalsIgnoreCase(s))
            return new CubicEaseOutInterpolater();
        if("CubicEaseInOut".equalsIgnoreCase(s))
            return new CubicEaseInOutInterpolater();
        if("ElasticEaseIn".equalsIgnoreCase(s))
            return new ElasticEaseInInterpolater();
        if("ElasticEaseOut".equalsIgnoreCase(s))
            return new ElasticEaseOutInterpolater();
        if("ElasticEaseInOut".equalsIgnoreCase(s))
            return new ElasticEaseInOutInterpolater();
        if(s.startsWith("ElasticEaseIn") && k != 0)
            return new ElasticEaseInInterpolater(f4, f5);
        if(s.startsWith("ElasticEaseOut") && k != 0)
            return new ElasticEaseOutInterpolater(f4, f5);
        if(s.startsWith("ElasticEaseInOut") && k != 0)
            return new ElasticEaseInOutInterpolater(f4, f5);
        if("ExpoEaseIn".equalsIgnoreCase(s))
            return new ExpoEaseInInterpolater();
        if("ExpoEaseOut".equalsIgnoreCase(s))
            return new ExpoEaseOutInterpolater();
        if("ExpoEaseInOut".equalsIgnoreCase(s))
            return new ExpoEaseInOutInterpolater();
        if("QuadEaseIn".equalsIgnoreCase(s))
            return new QuadEaseInInterpolater();
        if("QuadEaseOut".equalsIgnoreCase(s))
            return new QuadEaseOutInterpolater();
        if("QuadEaseInOut".equalsIgnoreCase(s))
            return new QuadEaseInOutInterpolater();
        if("QuartEaseIn".equalsIgnoreCase(s))
            return new QuartEaseInInterpolater();
        if("QuartEaseOut".equalsIgnoreCase(s))
            return new QuartEaseOutInterpolater();
        if("QuartEaseInOut".equalsIgnoreCase(s))
            return new QuartEaseInOutInterpolater();
        if("QuintEaseIn".equalsIgnoreCase(s))
            return new QuintEaseInInterpolater();
        if("QuintEaseOut".equalsIgnoreCase(s))
            return new QuintEaseOutInterpolater();
        if("QuintEaseInOut".equalsIgnoreCase(s))
            return new QuintEaseInOutInterpolater();
        if("SineEaseIn".equalsIgnoreCase(s))
            return new SineEaseInInterpolater();
        if("SineEaseOut".equalsIgnoreCase(s))
            return new SineEaseOutInterpolater();
        if("SineEaseInOut".equalsIgnoreCase(s))
            return new SineEaseInOutInterpolater();
        if("Linear".equalsIgnoreCase(s))
            return new LinearInterpolater();
        else
            return null;
    }

    public static final String LOG_TAG = "InterpolatorFactory";
}
