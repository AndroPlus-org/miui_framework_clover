// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.view.animation:
//            AnimationSet, AlphaAnimation, ScaleAnimation, RotateAnimation, 
//            TranslateAnimation, LinearInterpolator, AccelerateInterpolator, DecelerateInterpolator, 
//            AccelerateDecelerateInterpolator, CycleInterpolator, AnticipateInterpolator, OvershootInterpolator, 
//            AnticipateOvershootInterpolator, BounceInterpolator, PathInterpolator, LayoutAnimationController, 
//            GridLayoutAnimationController, Animation, Interpolator

public class AnimationUtils
{
    private static class AnimationState
    {

        boolean animationClockLocked;
        long currentVsyncTimeMillis;
        long lastReportedTimeMillis;

        private AnimationState()
        {
        }

        AnimationState(AnimationState animationstate)
        {
            this();
        }
    }


    public AnimationUtils()
    {
    }

    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return createAnimationFromXml(context, xmlpullparser, null, Xml.asAttributeSet(xmlpullparser));
    }

    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlpullparser, AnimationSet animationset, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if((j != 3 || xmlpullparser.getDepth() > i) && j != 1)
            {
                if(j == 2)
                {
                    Object obj1 = xmlpullparser.getName();
                    if(((String) (obj1)).equals("set"))
                    {
                        obj1 = new AnimationSet(context, attributeset);
                        createAnimationFromXml(context, xmlpullparser, (AnimationSet)obj1, attributeset);
                    } else
                    if(((String) (obj1)).equals("alpha"))
                        obj1 = new AlphaAnimation(context, attributeset);
                    else
                    if(((String) (obj1)).equals("scale"))
                        obj1 = new ScaleAnimation(context, attributeset);
                    else
                    if(((String) (obj1)).equals("rotate"))
                        obj1 = new RotateAnimation(context, attributeset);
                    else
                    if(((String) (obj1)).equals("translate"))
                        obj1 = new TranslateAnimation(context, attributeset);
                    else
                        throw new RuntimeException((new StringBuilder()).append("Unknown animation name: ").append(xmlpullparser.getName()).toString());
                    obj = obj1;
                    if(animationset != null)
                    {
                        animationset.addAnimation(((Animation) (obj1)));
                        obj = obj1;
                    }
                }
            } else
            {
                return ((Animation) (obj));
            }
        } while(true);
    }

    private static Interpolator createInterpolatorFromXml(Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break;
            if(j == 2)
            {
                obj = Xml.asAttributeSet(xmlpullparser);
                String s = xmlpullparser.getName();
                if(s.equals("linearInterpolator"))
                    obj = new LinearInterpolator();
                else
                if(s.equals("accelerateInterpolator"))
                    obj = new AccelerateInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("decelerateInterpolator"))
                    obj = new DecelerateInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("accelerateDecelerateInterpolator"))
                    obj = new AccelerateDecelerateInterpolator();
                else
                if(s.equals("cycleInterpolator"))
                    obj = new CycleInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("anticipateInterpolator"))
                    obj = new AnticipateInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("overshootInterpolator"))
                    obj = new OvershootInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("anticipateOvershootInterpolator"))
                    obj = new AnticipateOvershootInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                if(s.equals("bounceInterpolator"))
                    obj = new BounceInterpolator();
                else
                if(s.equals("pathInterpolator"))
                    obj = new PathInterpolator(resources, theme, ((AttributeSet) (obj)));
                else
                    throw new RuntimeException((new StringBuilder()).append("Unknown interpolator name: ").append(xmlpullparser.getName()).toString());
            }
        } while(true);
        return ((Interpolator) (obj));
    }

    private static LayoutAnimationController createLayoutAnimationFromXml(Context context, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        return createLayoutAnimationFromXml(context, xmlpullparser, Xml.asAttributeSet(xmlpullparser));
    }

    private static LayoutAnimationController createLayoutAnimationFromXml(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break;
            if(j == 2)
            {
                obj = xmlpullparser.getName();
                if("layoutAnimation".equals(obj))
                    obj = new LayoutAnimationController(context, attributeset);
                else
                if("gridLayoutAnimation".equals(obj))
                    obj = new GridLayoutAnimationController(context, attributeset);
                else
                    throw new RuntimeException((new StringBuilder()).append("Unknown layout animation name: ").append(((String) (obj))).toString());
            }
        } while(true);
        return ((LayoutAnimationController) (obj));
    }

    public static long currentAnimationTimeMillis()
    {
        AnimationState animationstate = (AnimationState)sAnimationState.get();
        if(animationstate.animationClockLocked)
        {
            return Math.max(animationstate.currentVsyncTimeMillis, animationstate.lastReportedTimeMillis);
        } else
        {
            animationstate.lastReportedTimeMillis = SystemClock.uptimeMillis();
            return animationstate.lastReportedTimeMillis;
        }
    }

    public static Animation loadAnimation(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        Object obj;
        Object obj1;
        XmlResourceParser xmlresourceparser;
        obj = null;
        obj1 = null;
        xmlresourceparser = null;
        Object obj2 = context.getResources().getAnimation(i);
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        context = createAnimationFromXml(context, ((XmlPullParser) (obj2)));
        if(obj2 != null)
            ((XmlResourceParser) (obj2)).close();
        return context;
        context;
        obj = xmlresourceparser;
        obj2 = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = xmlresourceparser;
        obj1 = JVM INSTR new #96  <Class StringBuilder>;
        obj = xmlresourceparser;
        ((StringBuilder) (obj1)).StringBuilder();
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj2)).android.content.res.Resources.NotFoundException(((StringBuilder) (obj1)).append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj2)).initCause(context);
        obj = xmlresourceparser;
        throw obj2;
        context;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw context;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        obj = obj1;
        obj2 = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        context = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj1;
        context.StringBuilder();
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).android.content.res.Resources.NotFoundException(context.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).initCause(xmlpullparserexception);
        obj = obj1;
        throw obj2;
    }

    public static Interpolator loadInterpolator(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        Object obj3 = context.getResources().getAnimation(i);
        obj2 = obj3;
        obj = obj3;
        obj1 = obj3;
        context = createInterpolatorFromXml(context.getResources(), context.getTheme(), ((XmlPullParser) (obj3)));
        if(obj3 != null)
            ((XmlResourceParser) (obj3)).close();
        return context;
        IOException ioexception;
        ioexception;
        obj = obj2;
        context = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj2;
        obj1 = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj2;
        ((StringBuilder) (obj1)).StringBuilder();
        obj = obj2;
        context.android.content.res.Resources.NotFoundException(((StringBuilder) (obj1)).append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj2;
        context.initCause(ioexception);
        obj = obj2;
        throw context;
        context;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw context;
        context;
        obj = obj1;
        obj2 = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        ioexception = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj1;
        ioexception.StringBuilder();
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).android.content.res.Resources.NotFoundException(ioexception.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).initCause(context);
        obj = obj1;
        throw obj2;
    }

    public static Interpolator loadInterpolator(Resources resources, android.content.res.Resources.Theme theme, int i)
        throws android.content.res.Resources.NotFoundException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        XmlResourceParser xmlresourceparser = resources.getAnimation(i);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        resources = createInterpolatorFromXml(resources, theme, xmlresourceparser);
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        return resources;
        obj1;
        obj = obj2;
        resources = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj2;
        theme = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj2;
        theme.StringBuilder();
        obj = obj2;
        resources.android.content.res.Resources.NotFoundException(theme.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj2;
        resources.initCause(((Throwable) (obj1)));
        obj = obj2;
        throw resources;
        resources;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw resources;
        theme;
        obj = obj1;
        obj2 = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        resources = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj1;
        resources.StringBuilder();
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).android.content.res.Resources.NotFoundException(resources.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        ((android.content.res.Resources.NotFoundException) (obj2)).initCause(theme);
        obj = obj1;
        throw obj2;
    }

    public static LayoutAnimationController loadLayoutAnimation(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        Object obj;
        Object obj1;
        XmlResourceParser xmlresourceparser;
        obj = null;
        obj1 = null;
        xmlresourceparser = null;
        Object obj2 = context.getResources().getAnimation(i);
        xmlresourceparser = ((XmlResourceParser) (obj2));
        obj = obj2;
        obj1 = obj2;
        context = createLayoutAnimationFromXml(context, ((XmlPullParser) (obj2)));
        if(obj2 != null)
            ((XmlResourceParser) (obj2)).close();
        return context;
        IOException ioexception;
        ioexception;
        obj = xmlresourceparser;
        obj1 = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = xmlresourceparser;
        context = JVM INSTR new #96  <Class StringBuilder>;
        obj = xmlresourceparser;
        context.StringBuilder();
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj1)).android.content.res.Resources.NotFoundException(context.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = xmlresourceparser;
        ((android.content.res.Resources.NotFoundException) (obj1)).initCause(ioexception);
        obj = xmlresourceparser;
        throw obj1;
        context;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw context;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        obj = obj1;
        context = JVM INSTR new #216 <Class android.content.res.Resources$NotFoundException>;
        obj = obj1;
        ioexception = JVM INSTR new #96  <Class StringBuilder>;
        obj = obj1;
        ioexception.StringBuilder();
        obj = obj1;
        context.android.content.res.Resources.NotFoundException(ioexception.append("Can't load animation resource ID #0x").append(Integer.toHexString(i)).toString());
        obj = obj1;
        context.initCause(xmlpullparserexception);
        obj = obj1;
        throw context;
    }

    public static void lockAnimationClock(long l)
    {
        AnimationState animationstate = (AnimationState)sAnimationState.get();
        animationstate.animationClockLocked = true;
        animationstate.currentVsyncTimeMillis = l;
    }

    public static Animation makeInAnimation(Context context, boolean flag)
    {
        if(flag)
            context = loadAnimation(context, 0x10a0002);
        else
            context = loadAnimation(context, 0x10a008d);
        context.setInterpolator(new DecelerateInterpolator());
        context.setStartTime(currentAnimationTimeMillis());
        return context;
    }

    public static Animation makeInChildBottomAnimation(Context context)
    {
        context = loadAnimation(context, 0x10a008a);
        context.setInterpolator(new AccelerateInterpolator());
        context.setStartTime(currentAnimationTimeMillis());
        return context;
    }

    public static Animation makeOutAnimation(Context context, boolean flag)
    {
        if(flag)
            context = loadAnimation(context, 0x10a0003);
        else
            context = loadAnimation(context, 0x10a0090);
        context.setInterpolator(new AccelerateInterpolator());
        context.setStartTime(currentAnimationTimeMillis());
        return context;
    }

    public static void unlockAnimationClock()
    {
        ((AnimationState)sAnimationState.get()).animationClockLocked = false;
    }

    private static final int SEQUENTIALLY = 1;
    private static final int TOGETHER = 0;
    private static ThreadLocal sAnimationState = new ThreadLocal() {

        protected AnimationState initialValue()
        {
            return new AnimationState(null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;

}
