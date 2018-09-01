// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable, StateListDrawable, AnimatedStateListDrawable, LevelListDrawable, 
//            LayerDrawable, TransitionDrawable, RippleDrawable, AdaptiveIconDrawable, 
//            ColorDrawable, GradientDrawable, VectorDrawable, AnimatedVectorDrawable, 
//            ScaleDrawable, ClipDrawable, RotateDrawable, AnimatedRotateDrawable, 
//            AnimationDrawable, InsetDrawable, BitmapDrawable, NinePatchDrawable

public final class DrawableInflater
{

    public DrawableInflater(Resources resources, ClassLoader classloader)
    {
        mRes = resources;
        mClassLoader = classloader;
    }

    private Drawable inflateFromClass(String s)
    {
        HashMap hashmap = CONSTRUCTOR_MAP;
        hashmap;
        JVM INSTR monitorenter ;
        Constructor constructor = (Constructor)CONSTRUCTOR_MAP.get(s);
        Constructor constructor1;
        constructor1 = constructor;
        if(constructor != null)
            break MISSING_BLOCK_LABEL_56;
        constructor1 = mClassLoader.loadClass(s).asSubclass(android/graphics/drawable/Drawable).getConstructor(new Class[0]);
        CONSTRUCTOR_MAP.put(s, constructor1);
        hashmap;
        JVM INSTR monitorexit ;
        return (Drawable)constructor1.newInstance(new Object[0]);
        Object obj;
        obj;
        hashmap;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        s = new InflateException((new StringBuilder()).append("Error inflating class ").append(s).toString());
        s.initCause(((Throwable) (obj)));
        throw s;
        obj;
        s = new InflateException((new StringBuilder()).append("Error inflating class ").append(s).toString());
        s.initCause(((Throwable) (obj)));
        throw s;
        obj;
        s = new InflateException((new StringBuilder()).append("Class not found ").append(s).toString());
        s.initCause(((Throwable) (obj)));
        throw s;
        obj;
        s = new InflateException((new StringBuilder()).append("Class is not a Drawable ").append(s).toString());
        s.initCause(((Throwable) (obj)));
        throw s;
    }

    private Drawable inflateFromTag(String s)
    {
        if(s.equals("selector"))
            return new StateListDrawable();
        if(s.equals("animated-selector"))
            return new AnimatedStateListDrawable();
        if(s.equals("level-list"))
            return new LevelListDrawable();
        if(s.equals("layer-list"))
            return new LayerDrawable();
        if(s.equals("transition"))
            return new TransitionDrawable();
        if(s.equals("ripple"))
            return new RippleDrawable();
        if(s.equals("adaptive-icon"))
            return new AdaptiveIconDrawable();
        if(s.equals("color"))
            return new ColorDrawable();
        if(s.equals("shape"))
            return new GradientDrawable();
        if(s.equals("vector"))
            return new VectorDrawable();
        if(s.equals("animated-vector"))
            return new AnimatedVectorDrawable();
        if(s.equals("scale"))
            return new ScaleDrawable();
        if(s.equals("clip"))
            return new ClipDrawable();
        if(s.equals("rotate"))
            return new RotateDrawable();
        if(s.equals("animated-rotate"))
            return new AnimatedRotateDrawable();
        if(s.equals("animation-list"))
            return new AnimationDrawable();
        if(s.equals("inset"))
            return new InsetDrawable();
        if(s.equals("bitmap"))
            return new BitmapDrawable();
        if(s.equals("nine-patch"))
            return new NinePatchDrawable();
        else
            return null;
    }

    public static Drawable loadDrawable(Context context, int i)
    {
        return loadDrawable(context.getResources(), context.getTheme(), i);
    }

    public static Drawable loadDrawable(Resources resources, android.content.res.Resources.Theme theme, int i)
    {
        return resources.getDrawable(i, theme);
    }

    public Drawable inflateFromXml(String s, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        return inflateFromXmlForDensity(s, xmlpullparser, attributeset, 0, theme);
    }

    Drawable inflateFromXmlForDensity(String s, XmlPullParser xmlpullparser, AttributeSet attributeset, int i, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        String s1 = s;
        if(s.equals("drawable"))
        {
            s = attributeset.getAttributeValue(null, "class");
            s1 = s;
            if(s == null)
                throw new InflateException("<drawable> tag must specify class attribute");
        }
        Drawable drawable = inflateFromTag(s1);
        s = drawable;
        if(drawable == null)
            s = inflateFromClass(s1);
        s.setSrcDensityOverride(i);
        s.inflate(mRes, xmlpullparser, attributeset, theme);
        return s;
    }

    private static final HashMap CONSTRUCTOR_MAP = new HashMap();
    private final ClassLoader mClassLoader;
    private final Resources mRes;

}
