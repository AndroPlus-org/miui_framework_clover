// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

abstract class GenericInflater
{
    public static interface Factory
    {

        public abstract Object onCreateItem(String s, Context context, AttributeSet attributeset);
    }

    private static class FactoryMerger
        implements Factory
    {

        public Object onCreateItem(String s, Context context, AttributeSet attributeset)
        {
            Object obj = mF1.onCreateItem(s, context, attributeset);
            if(obj != null)
                return obj;
            else
                return mF2.onCreateItem(s, context, attributeset);
        }

        private final Factory mF1;
        private final Factory mF2;

        FactoryMerger(Factory factory, Factory factory1)
        {
            mF1 = factory;
            mF2 = factory1;
        }
    }

    public static interface Parent
    {

        public abstract void addItemFromInflater(Object obj);
    }


    protected GenericInflater(Context context)
    {
        DEBUG = false;
        mConstructorArgs = new Object[2];
        mContext = context;
    }

    protected GenericInflater(GenericInflater genericinflater, Context context)
    {
        DEBUG = false;
        mConstructorArgs = new Object[2];
        mContext = context;
        mFactory = genericinflater.mFactory;
    }

    private final Object createItemFromTag(XmlPullParser xmlpullparser, String s, AttributeSet attributeset)
    {
        Object obj;
        if(mFactory == null)
            xmlpullparser = null;
        else
            try
            {
                xmlpullparser = ((XmlPullParser) (mFactory.onCreateItem(s, mContext, attributeset)));
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                throw xmlpullparser;
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                s = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s).toString());
                s.initCause(xmlpullparser);
                throw s;
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                s = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s).toString());
                s.initCause(xmlpullparser);
                throw s;
            }
        obj = xmlpullparser;
        if(xmlpullparser != null)
            break MISSING_BLOCK_LABEL_34;
        if(-1 != s.indexOf('.'))
            break MISSING_BLOCK_LABEL_56;
        obj = onCreateItem(s, attributeset);
_L1:
        return obj;
        obj = createItem(s, null, attributeset);
          goto _L1
    }

    private void rInflate(XmlPullParser xmlpullparser, Object obj, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break;
            if(j == 2 && !onCreateCustomFromTag(xmlpullparser, obj, attributeset))
            {
                Object obj1 = createItemFromTag(xmlpullparser, xmlpullparser.getName(), attributeset);
                ((Parent)obj).addItemFromInflater(obj1);
                rInflate(xmlpullparser, obj1, attributeset);
            }
        } while(true);
    }

    public abstract GenericInflater cloneInContext(Context context);

    public final Object createItem(String s, String s1, AttributeSet attributeset)
        throws ClassNotFoundException, InflateException
    {
        Constructor constructor;
        Object obj;
        constructor = (Constructor)sConstructorMap.get(s);
        obj = constructor;
        if(constructor != null) goto _L2; else goto _L1
_L1:
        Object obj1 = constructor;
        Object aobj[];
        ClassLoader classloader;
        try
        {
            classloader = mContext.getClassLoader();
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ");
            attributeset = s;
            if(s1 != null)
                attributeset = (new StringBuilder()).append(s1).append(s).toString();
            s = new InflateException(stringbuilder.append(attributeset).toString());
            s.initCause(nosuchmethodexception);
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s1 = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(((Constructor) (obj1)).getClass().getName()).toString());
            s1.initCause(s);
            throw s1;
        }
        if(s1 == null) goto _L4; else goto _L3
_L3:
        obj1 = constructor;
        obj = JVM INSTR new #88  <Class StringBuilder>;
        obj1 = constructor;
        ((StringBuilder) (obj)).StringBuilder();
        obj1 = constructor;
        obj = ((StringBuilder) (obj)).append(s1).append(s).toString();
_L6:
        obj1 = constructor;
        obj = classloader.loadClass(((String) (obj))).getConstructor(mConstructorSignature);
        obj1 = obj;
        ((Constructor) (obj)).setAccessible(true);
        obj1 = obj;
        sConstructorMap.put(s, obj);
_L2:
        obj1 = obj;
        aobj = mConstructorArgs;
        aobj[1] = attributeset;
        obj1 = obj;
        obj = ((Constructor) (obj)).newInstance(aobj);
        return obj;
_L4:
        obj = s;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public Context getContext()
    {
        return mContext;
    }

    public String getDefaultPackage()
    {
        return mDefaultPackage;
    }

    public final Factory getFactory()
    {
        return mFactory;
    }

    public Object inflate(int i, Parent parent)
    {
        boolean flag;
        if(parent != null)
            flag = true;
        else
            flag = false;
        return inflate(i, parent, flag);
    }

    public Object inflate(int i, Parent parent, boolean flag)
    {
        XmlResourceParser xmlresourceparser = getContext().getResources().getXml(i);
        parent = ((Parent) (inflate(((XmlPullParser) (xmlresourceparser)), parent, flag)));
        xmlresourceparser.close();
        return parent;
        parent;
        xmlresourceparser.close();
        throw parent;
    }

    public Object inflate(XmlPullParser xmlpullparser, Parent parent)
    {
        boolean flag;
        if(parent != null)
            flag = true;
        else
            flag = false;
        return inflate(xmlpullparser, parent, flag);
    }

    public Object inflate(XmlPullParser xmlpullparser, Parent parent, boolean flag)
    {
        Object aobj[] = mConstructorArgs;
        aobj;
        JVM INSTR monitorenter ;
        Object obj;
        obj = Xml.asAttributeSet(xmlpullparser);
        mConstructorArgs[0] = mContext;
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i == 2)
            break MISSING_BLOCK_LABEL_99;
        parent = JVM INSTR new #65  <Class InflateException>;
        obj = JVM INSTR new #88  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        parent.InflateException(((StringBuilder) (obj)).append(xmlpullparser.getPositionDescription()).append(": No start tag found!").toString());
        throw parent;
        xmlpullparser;
        throw xmlpullparser;
        xmlpullparser;
        aobj;
        JVM INSTR monitorexit ;
        throw xmlpullparser;
        parent = onMergeRoots(parent, flag, (Parent)createItemFromTag(xmlpullparser, xmlpullparser.getName(), ((AttributeSet) (obj))));
        rInflate(xmlpullparser, parent, ((AttributeSet) (obj)));
        aobj;
        JVM INSTR monitorexit ;
        return parent;
        parent;
        InflateException inflateexception = JVM INSTR new #65  <Class InflateException>;
        StringBuilder stringbuilder = JVM INSTR new #88  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        inflateexception.InflateException(stringbuilder.append(xmlpullparser.getPositionDescription()).append(": ").append(parent.getMessage()).toString());
        inflateexception.initCause(parent);
        throw inflateexception;
        xmlpullparser;
        parent = JVM INSTR new #65  <Class InflateException>;
        parent.InflateException(xmlpullparser.getMessage());
        parent.initCause(xmlpullparser);
        throw parent;
    }

    protected boolean onCreateCustomFromTag(XmlPullParser xmlpullparser, Object obj, AttributeSet attributeset)
        throws XmlPullParserException
    {
        return false;
    }

    protected Object onCreateItem(String s, AttributeSet attributeset)
        throws ClassNotFoundException
    {
        return createItem(s, mDefaultPackage, attributeset);
    }

    protected Parent onMergeRoots(Parent parent, boolean flag, Parent parent1)
    {
        return parent1;
    }

    public void setDefaultPackage(String s)
    {
        mDefaultPackage = s;
    }

    public void setFactory(Factory factory)
    {
        if(mFactorySet)
            throw new IllegalStateException("A factory has already been set on this inflater");
        if(factory == null)
            throw new NullPointerException("Given factory can not be null");
        mFactorySet = true;
        if(mFactory == null)
            mFactory = factory;
        else
            mFactory = new FactoryMerger(factory, mFactory);
    }

    private static final Class mConstructorSignature[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final HashMap sConstructorMap = new HashMap();
    private final boolean DEBUG;
    private final Object mConstructorArgs[];
    protected final Context mContext;
    private String mDefaultPackage;
    private Factory mFactory;
    private boolean mFactorySet;

}
