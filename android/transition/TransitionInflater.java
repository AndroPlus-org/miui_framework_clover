// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.content.res.*;
import android.util.*;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.transition:
//            TransitionSet, Fade, ChangeBounds, Slide, 
//            Explode, ChangeImageTransform, ChangeTransform, ChangeClipBounds, 
//            AutoTransition, Recolor, ChangeScroll, Transition, 
//            ArcMotion, PathMotion, PatternPathMotion, TransitionManager, 
//            Scene

public class TransitionInflater
{

    private TransitionInflater(Context context)
    {
        mContext = context;
    }

    private Object createCustom(AttributeSet attributeset, Class class1, String s)
    {
        String s1;
        s1 = attributeset.getAttributeValue(null, "class");
        if(s1 == null)
            throw new InflateException((new StringBuilder()).append(s).append(" tag must have a 'class' attribute").toString());
        ArrayMap arraymap = sConstructors;
        arraymap;
        JVM INSTR monitorenter ;
        Constructor constructor = (Constructor)sConstructors.get(s1);
        s = constructor;
        if(constructor != null)
            break MISSING_BLOCK_LABEL_122;
        Class class2 = mContext.getClassLoader().loadClass(s1).asSubclass(class1);
        s = constructor;
        if(class2 == null)
            break MISSING_BLOCK_LABEL_122;
        s = class2.getConstructor(sConstructorSignature);
        s.setAccessible(true);
        sConstructors.put(s1, s);
        attributeset = ((AttributeSet) (s.newInstance(new Object[] {
            mContext, attributeset
        })));
        arraymap;
        JVM INSTR monitorexit ;
        return attributeset;
        attributeset;
        arraymap;
        JVM INSTR monitorexit ;
        throw attributeset;
        attributeset;
        throw new InflateException((new StringBuilder()).append("Could not instantiate ").append(class1).append(" class ").append(s1).toString(), attributeset);
        attributeset;
        throw new InflateException((new StringBuilder()).append("Could not instantiate ").append(class1).append(" class ").append(s1).toString(), attributeset);
        attributeset;
        throw new InflateException((new StringBuilder()).append("Could not instantiate ").append(class1).append(" class ").append(s1).toString(), attributeset);
        attributeset;
        throw new InflateException((new StringBuilder()).append("Could not instantiate ").append(class1).append(" class ").append(s1).toString(), attributeset);
        attributeset;
        throw new InflateException((new StringBuilder()).append("Could not instantiate ").append(class1).append(" class ").append(s1).toString(), attributeset);
    }

    private Transition createTransitionFromXml(XmlPullParser xmlpullparser, AttributeSet attributeset, Transition transition)
        throws XmlPullParserException, IOException
    {
        Object obj = null;
        int i = xmlpullparser.getDepth();
        TransitionSet transitionset;
        if(transition instanceof TransitionSet)
            transitionset = (TransitionSet)transition;
        else
            transitionset = null;
        do
        {
            int j = xmlpullparser.next();
            if((j != 3 || xmlpullparser.getDepth() > i) && j != 1)
            {
                if(j == 2)
                {
                    Object obj1 = xmlpullparser.getName();
                    if("fade".equals(obj1))
                        obj1 = new Fade(mContext, attributeset);
                    else
                    if("changeBounds".equals(obj1))
                        obj1 = new ChangeBounds(mContext, attributeset);
                    else
                    if("slide".equals(obj1))
                        obj1 = new Slide(mContext, attributeset);
                    else
                    if("explode".equals(obj1))
                        obj1 = new Explode(mContext, attributeset);
                    else
                    if("changeImageTransform".equals(obj1))
                        obj1 = new ChangeImageTransform(mContext, attributeset);
                    else
                    if("changeTransform".equals(obj1))
                        obj1 = new ChangeTransform(mContext, attributeset);
                    else
                    if("changeClipBounds".equals(obj1))
                        obj1 = new ChangeClipBounds(mContext, attributeset);
                    else
                    if("autoTransition".equals(obj1))
                        obj1 = new AutoTransition(mContext, attributeset);
                    else
                    if("recolor".equals(obj1))
                        obj1 = new Recolor(mContext, attributeset);
                    else
                    if("changeScroll".equals(obj1))
                        obj1 = new ChangeScroll(mContext, attributeset);
                    else
                    if("transitionSet".equals(obj1))
                        obj1 = new TransitionSet(mContext, attributeset);
                    else
                    if("transition".equals(obj1))
                        obj1 = (Transition)createCustom(attributeset, android/transition/Transition, "transition");
                    else
                    if("targets".equals(obj1))
                    {
                        getTargetIds(xmlpullparser, attributeset, transition);
                        obj1 = obj;
                    } else
                    if("arcMotion".equals(obj1))
                    {
                        transition.setPathMotion(new ArcMotion(mContext, attributeset));
                        obj1 = obj;
                    } else
                    if("pathMotion".equals(obj1))
                    {
                        transition.setPathMotion((PathMotion)createCustom(attributeset, android/transition/PathMotion, "pathMotion"));
                        obj1 = obj;
                    } else
                    if("patternPathMotion".equals(obj1))
                    {
                        transition.setPathMotion(new PatternPathMotion(mContext, attributeset));
                        obj1 = obj;
                    } else
                    {
                        throw new RuntimeException((new StringBuilder()).append("Unknown scene name: ").append(xmlpullparser.getName()).toString());
                    }
                    obj = obj1;
                    if(obj1 != null)
                    {
                        if(!xmlpullparser.isEmptyElementTag())
                            createTransitionFromXml(xmlpullparser, attributeset, ((Transition) (obj1)));
                        if(transitionset != null)
                        {
                            transitionset.addTransition(((Transition) (obj1)));
                            obj = null;
                        } else
                        {
                            obj = obj1;
                            if(transition != null)
                                throw new InflateException("Could not add transition to another transition.");
                        }
                    }
                }
            } else
            {
                return ((Transition) (obj));
            }
        } while(true);
    }

    private TransitionManager createTransitionManagerFromXml(XmlPullParser xmlpullparser, AttributeSet attributeset, ViewGroup viewgroup)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        TransitionManager transitionmanager = null;
        do
        {
            int j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break;
            if(j == 2)
            {
                String s = xmlpullparser.getName();
                if(s.equals("transitionManager"))
                    transitionmanager = new TransitionManager();
                else
                if(s.equals("transition") && transitionmanager != null)
                    loadTransition(attributeset, viewgroup, transitionmanager);
                else
                    throw new RuntimeException((new StringBuilder()).append("Unknown scene name: ").append(xmlpullparser.getName()).toString());
            }
        } while(true);
        return transitionmanager;
    }

    public static TransitionInflater from(Context context)
    {
        return new TransitionInflater(context);
    }

    private void getTargetIds(XmlPullParser xmlpullparser, AttributeSet attributeset, Transition transition)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L2:
        TypedArray typedarray;
        int j;
        do
        {
            j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break MISSING_BLOCK_LABEL_307;
        } while(j != 2);
        if(!xmlpullparser.getName().equals("target"))
            break MISSING_BLOCK_LABEL_275;
        typedarray = mContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TransitionTarget);
        j = typedarray.getResourceId(1, 0);
        if(j == 0)
            break; /* Loop/switch isn't completed */
        transition.addTarget(j);
_L3:
        typedarray.recycle();
        if(true) goto _L2; else goto _L1
_L1:
        String s;
        String s1;
        int k = typedarray.getResourceId(2, 0);
        if(k != 0)
        {
            transition.excludeTarget(k, true);
        } else
        {
            s = typedarray.getString(4);
            if(s != null)
            {
                transition.addTarget(s);
            } else
            {
                s = typedarray.getString(5);
                if(s != null)
                {
                    transition.excludeTarget(s, true);
                } else
                {
label0:
                    {
                        s1 = typedarray.getString(3);
                        if(s1 == null)
                            break label0;
                        s = s1;
                        try
                        {
                            transition.excludeTarget(Class.forName(s1), true);
                        }
                        // Misplaced declaration of an exception variable
                        catch(XmlPullParser xmlpullparser)
                        {
                            typedarray.recycle();
                            throw new RuntimeException((new StringBuilder()).append("Could not create ").append(s).toString(), xmlpullparser);
                        }
                    }
                }
            }
        }
          goto _L3
        s = s1;
        s1 = typedarray.getString(0);
        if(s1 == null) goto _L3; else goto _L4
_L4:
        s = s1;
        transition.addTarget(Class.forName(s1));
          goto _L3
        throw new RuntimeException((new StringBuilder()).append("Unknown scene name: ").append(xmlpullparser.getName()).toString());
    }

    private void loadTransition(AttributeSet attributeset, ViewGroup viewgroup, TransitionManager transitionmanager)
        throws android.content.res.Resources.NotFoundException
    {
        TypedArray typedarray = mContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TransitionManager);
        int i = typedarray.getResourceId(2, -1);
        int j = typedarray.getResourceId(0, -1);
        if(j < 0)
            attributeset = null;
        else
            attributeset = Scene.getSceneForLayout(viewgroup, j, mContext);
        j = typedarray.getResourceId(1, -1);
        if(j < 0)
            viewgroup = null;
        else
            viewgroup = Scene.getSceneForLayout(viewgroup, j, mContext);
        if(i >= 0)
        {
            Transition transition = inflateTransition(i);
            if(transition != null)
            {
                if(viewgroup == null)
                    throw new RuntimeException((new StringBuilder()).append("No toScene for transition ID ").append(i).toString());
                if(attributeset == null)
                    transitionmanager.setTransition(viewgroup, transition);
                else
                    transitionmanager.setTransition(attributeset, viewgroup, transition);
            }
        }
        typedarray.recycle();
    }

    public Transition inflateTransition(int i)
    {
        XmlResourceParser xmlresourceparser = mContext.getResources().getXml(i);
        Transition transition = createTransitionFromXml(xmlresourceparser, Xml.asAttributeSet(xmlresourceparser), null);
        xmlresourceparser.close();
        return transition;
        Object obj;
        obj;
        InflateException inflateexception1 = JVM INSTR new #54  <Class InflateException>;
        StringBuilder stringbuilder = JVM INSTR new #56  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        inflateexception1.InflateException(stringbuilder.append(xmlresourceparser.getPositionDescription()).append(": ").append(((IOException) (obj)).getMessage()).toString());
        inflateexception1.initCause(((Throwable) (obj)));
        throw inflateexception1;
        obj;
        xmlresourceparser.close();
        throw obj;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        InflateException inflateexception = JVM INSTR new #54  <Class InflateException>;
        inflateexception.InflateException(xmlpullparserexception.getMessage());
        inflateexception.initCause(xmlpullparserexception);
        throw inflateexception;
    }

    public TransitionManager inflateTransitionManager(int i, ViewGroup viewgroup)
    {
        XmlResourceParser xmlresourceparser = mContext.getResources().getXml(i);
        viewgroup = createTransitionManagerFromXml(xmlresourceparser, Xml.asAttributeSet(xmlresourceparser), viewgroup);
        xmlresourceparser.close();
        return viewgroup;
        IOException ioexception;
        ioexception;
        InflateException inflateexception1 = JVM INSTR new #54  <Class InflateException>;
        viewgroup = JVM INSTR new #56  <Class StringBuilder>;
        viewgroup.StringBuilder();
        inflateexception1.InflateException(viewgroup.append(xmlresourceparser.getPositionDescription()).append(": ").append(ioexception.getMessage()).toString());
        inflateexception1.initCause(ioexception);
        throw inflateexception1;
        viewgroup;
        xmlresourceparser.close();
        throw viewgroup;
        viewgroup;
        InflateException inflateexception = JVM INSTR new #54  <Class InflateException>;
        inflateexception.InflateException(viewgroup.getMessage());
        inflateexception.initCause(viewgroup);
        throw inflateexception;
    }

    private static final Class sConstructorSignature[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final ArrayMap sConstructors = new ArrayMap();
    private Context mContext;

}
