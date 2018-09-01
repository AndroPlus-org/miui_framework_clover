// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.*;
import android.graphics.Canvas;
import android.os.*;
import android.util.*;
import android.widget.FrameLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.view:
//            InflateException, ViewGroup, ContextThemeWrapper, View, 
//            ViewStub, LayoutInflaterMap

public abstract class LayoutInflater
{
    private static class BlinkLayout extends FrameLayout
    {

        static boolean _2D_get0(BlinkLayout blinklayout)
        {
            return blinklayout.mBlink;
        }

        static boolean _2D_get1(BlinkLayout blinklayout)
        {
            return blinklayout.mBlinkState;
        }

        static boolean _2D_set0(BlinkLayout blinklayout, boolean flag)
        {
            blinklayout.mBlinkState = flag;
            return flag;
        }

        static void _2D_wrap0(BlinkLayout blinklayout)
        {
            blinklayout.makeBlink();
        }

        private void makeBlink()
        {
            Message message = mHandler.obtainMessage(66);
            mHandler.sendMessageDelayed(message, 500L);
        }

        protected void dispatchDraw(Canvas canvas)
        {
            if(mBlinkState)
                super.dispatchDraw(canvas);
        }

        protected void onAttachedToWindow()
        {
            super.onAttachedToWindow();
            mBlink = true;
            mBlinkState = true;
            makeBlink();
        }

        protected void onDetachedFromWindow()
        {
            super.onDetachedFromWindow();
            mBlink = false;
            mBlinkState = true;
            mHandler.removeMessages(66);
        }

        private static final int BLINK_DELAY = 500;
        private static final int MESSAGE_BLINK = 66;
        private boolean mBlink;
        private boolean mBlinkState;
        private final Handler mHandler = new Handler(new _cls1());

        public BlinkLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static interface Factory
    {

        public abstract View onCreateView(String s, Context context, AttributeSet attributeset);
    }

    public static interface Factory2
        extends Factory
    {

        public abstract View onCreateView(View view, String s, Context context, AttributeSet attributeset);
    }

    private static class FactoryMerger
        implements Factory2
    {

        public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
        {
            View view1;
            if(mF12 != null)
                view1 = mF12.onCreateView(view, s, context, attributeset);
            else
                view1 = mF1.onCreateView(s, context, attributeset);
            if(view1 != null)
                return view1;
            if(mF22 != null)
                view = mF22.onCreateView(view, s, context, attributeset);
            else
                view = mF2.onCreateView(s, context, attributeset);
            return view;
        }

        public View onCreateView(String s, Context context, AttributeSet attributeset)
        {
            View view = mF1.onCreateView(s, context, attributeset);
            if(view != null)
                return view;
            else
                return mF2.onCreateView(s, context, attributeset);
        }

        private final Factory mF1;
        private final Factory2 mF12;
        private final Factory mF2;
        private final Factory2 mF22;

        FactoryMerger(Factory factory, Factory2 factory2, Factory factory1, Factory2 factory2_1)
        {
            mF1 = factory;
            mF2 = factory1;
            mF12 = factory2;
            mF22 = factory2_1;
        }
    }

    public static interface Filter
    {

        public abstract boolean onLoadClass(Class class1);
    }


    protected LayoutInflater(Context context)
    {
        mConstructorArgs = new Object[2];
        mContext = context;
    }

    protected LayoutInflater(LayoutInflater layoutinflater, Context context)
    {
        mConstructorArgs = new Object[2];
        mContext = context;
        mFactory = layoutinflater.mFactory;
        mFactory2 = layoutinflater.mFactory2;
        mPrivateFactory = layoutinflater.mPrivateFactory;
        setFilter(layoutinflater.mFilter);
    }

    static final void consumeChildElements(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        int j;
        do
            j = xmlpullparser.next();
        while((j != 3 || xmlpullparser.getDepth() > i) && j != 1);
    }

    private View createViewFromTag(View view, String s, Context context, AttributeSet attributeset)
    {
        return createViewFromTag(view, s, context, attributeset, false);
    }

    private void failNotAllowed(String s, String s1, AttributeSet attributeset)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(attributeset.getPositionDescription()).append(": Class not allowed to be inflated ");
        attributeset = s;
        if(s1 != null)
            attributeset = (new StringBuilder()).append(s1).append(s).toString();
        throw new InflateException(stringbuilder.append(attributeset).toString());
    }

    public static LayoutInflater from(Context context)
    {
        context = (LayoutInflater)context.getSystemService("layout_inflater");
        if(context == null)
            throw new AssertionError("LayoutInflater not found.");
        else
            return context;
    }

    private void parseInclude(XmlPullParser xmlpullparser, Context context, View view, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        if(!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag;
        Object obj1;
        obj = context.obtainStyledAttributes(attributeset, ATTRS_THEME);
        int i = ((TypedArray) (obj)).getResourceId(0, 0);
        int l;
        if(i != 0)
            flag = true;
        else
            flag = false;
        obj1 = context;
        if(flag)
            obj1 = new ContextThemeWrapper(context, i);
        ((TypedArray) (obj)).recycle();
        l = attributeset.getAttributeResourceValue(null, "layout", 0);
        i = l;
        if(l == 0)
        {
            context = attributeset.getAttributeValue(null, "layout");
            if(context == null || context.length() <= 0)
                throw new InflateException("You must specify a layout in the include tag: <include layout=\"@layout/layoutID\" />");
            i = ((Context) (obj1)).getResources().getIdentifier(context.substring(1), "attr", ((Context) (obj1)).getPackageName());
        }
        if(mTempValue == null)
            mTempValue = new TypedValue();
        l = i;
        if(i != 0)
        {
            l = i;
            if(((Context) (obj1)).getTheme().resolveAttribute(i, mTempValue, true))
                l = mTempValue.resourceId;
        }
        if(l == 0)
        {
            xmlpullparser = attributeset.getAttributeValue(null, "layout");
            throw new InflateException((new StringBuilder()).append("You must specify a valid layout reference. The layout ID ").append(xmlpullparser).append(" is not valid.").toString());
        }
        obj = ((Context) (obj1)).getResources().getLayout(l);
        AttributeSet attributeset1 = Xml.asAttributeSet(((XmlPullParser) (obj)));
        int j;
        do
            j = ((XmlResourceParser) (obj)).next();
        while(j != 2 && j != 1);
        if(j == 2)
            break MISSING_BLOCK_LABEL_344;
        xmlpullparser = JVM INSTR new #168 <Class InflateException>;
        context = JVM INSTR new #153 <Class StringBuilder>;
        context.StringBuilder();
        xmlpullparser.InflateException(context.append(((XmlResourceParser) (obj)).getPositionDescription()).append(": No start tag found!").toString());
        throw xmlpullparser;
        xmlpullparser;
        ((XmlResourceParser) (obj)).close();
        throw xmlpullparser;
        context = ((XmlResourceParser) (obj)).getName();
        if(!"merge".equals(context)) goto _L4; else goto _L3
_L3:
        rInflate(((XmlPullParser) (obj)), view, ((Context) (obj1)), attributeset1, false);
_L12:
        ((XmlResourceParser) (obj)).close();
        consumeChildElements(xmlpullparser);
        return;
_L4:
        int k;
        int i1;
        View view1;
        ViewGroup viewgroup;
        view1 = createViewFromTag(view, context, ((Context) (obj1)), attributeset1, flag);
        viewgroup = (ViewGroup)view;
        context = ((Context) (obj1)).obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Include);
        k = context.getResourceId(0, -1);
        i1 = context.getInt(1, -1);
        context.recycle();
        context = null;
        view = viewgroup.generateLayoutParams(attributeset);
        context = view;
_L9:
        view = context;
        if(context != null)
            break MISSING_BLOCK_LABEL_462;
        view = viewgroup.generateLayoutParams(attributeset1);
        view1.setLayoutParams(view);
        rInflateChildren(((XmlPullParser) (obj)), view1, attributeset1, true);
        if(k == -1)
            break MISSING_BLOCK_LABEL_492;
        view1.setId(k);
        i1;
        JVM INSTR tableswitch 0 2: default 520
    //                   0 534
    //                   1 543
    //                   2 552;
           goto _L5 _L6 _L7 _L8
_L5:
        break; /* Loop/switch isn't completed */
_L8:
        break MISSING_BLOCK_LABEL_552;
_L10:
        viewgroup.addView(view1);
        continue; /* Loop/switch isn't completed */
        view;
          goto _L9
_L6:
        view1.setVisibility(0);
          goto _L10
_L7:
        view1.setVisibility(4);
          goto _L10
        view1.setVisibility(8);
          goto _L10
_L2:
        throw new InflateException("<include /> can only be used inside of a ViewGroup");
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void parseViewTag(XmlPullParser xmlpullparser, View view, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        attributeset = view.getContext().obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewTag);
        view.setTag(attributeset.getResourceId(1, 0), attributeset.getText(0));
        attributeset.recycle();
        consumeChildElements(xmlpullparser);
    }

    private final boolean verifyClassLoader(Constructor constructor)
    {
        ClassLoader classloader = constructor.getDeclaringClass().getClassLoader();
        if(classloader == BOOT_CLASS_LOADER)
            return true;
        constructor = mContext.getClassLoader();
        ClassLoader classloader1;
        do
        {
            if(classloader == constructor)
                return true;
            classloader1 = constructor.getParent();
            constructor = classloader1;
        } while(classloader1 != null);
        return false;
    }

    public abstract LayoutInflater cloneInContext(Context context);

    public final View createView(String s, String s1, AttributeSet attributeset)
        throws ClassNotFoundException, InflateException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = (Constructor)sConstructorMap.get(s);
        obj1 = obj;
        if(obj != null)
        {
            obj1 = obj;
            if(verifyClassLoader(((Constructor) (obj))) ^ true)
            {
                obj1 = null;
                sConstructorMap.remove(s);
            }
        }
        obj2 = null;
        obj3 = null;
        obj = obj2;
        Trace.traceBegin(8L, s);
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        obj = obj2;
        obj1 = mContext.getClassLoader();
        if(s1 == null) goto _L4; else goto _L3
_L3:
        obj = obj2;
        Object obj4 = JVM INSTR new #153 <Class StringBuilder>;
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        obj = obj2;
        obj4 = ((StringBuilder) (obj4)).append(s1).append(s).toString();
_L5:
        obj = obj2;
        obj4 = ((ClassLoader) (obj1)).loadClass(((String) (obj4))).asSubclass(android/view/View);
        obj = obj4;
        if(mFilter == null || obj4 == null)
            break MISSING_BLOCK_LABEL_187;
        obj = obj4;
        if(mFilter.onLoadClass(((Class) (obj4))))
            break MISSING_BLOCK_LABEL_187;
        obj = obj4;
        failNotAllowed(s, s1, attributeset);
        obj = obj4;
        Object obj5 = ((Class) (obj4)).getConstructor(mConstructorSignature);
        obj = obj4;
        ((Constructor) (obj5)).setAccessible(true);
        obj = obj4;
        sConstructorMap.put(s, obj5);
_L7:
        obj = obj4;
        obj2 = mConstructorArgs[0];
        obj = obj4;
        if(mConstructorArgs[0] != null)
            break MISSING_BLOCK_LABEL_264;
        obj = obj4;
        mConstructorArgs[0] = mContext;
        obj = obj4;
        obj1 = ((Object) (mConstructorArgs));
        obj1[1] = attributeset;
        obj = obj4;
        obj5 = (View)((Constructor) (obj5)).newInstance(((Object []) (obj1)));
        obj = obj4;
        if(!(obj5 instanceof ViewStub))
            break MISSING_BLOCK_LABEL_330;
        obj = obj4;
        ((ViewStub)obj5).setLayoutInflater(cloneInContext((Context)obj1[0]));
        obj = obj4;
        mConstructorArgs[0] = obj2;
        Trace.traceEnd(8L);
        return ((View) (obj5));
_L4:
        obj4 = s;
          goto _L5
_L2:
        obj4 = obj3;
        obj5 = obj1;
        obj = obj2;
        if(mFilter == null) goto _L7; else goto _L6
_L6:
        obj = obj2;
        Boolean boolean1 = (Boolean)mFilterMap.get(s);
        if(boolean1 != null)
            break MISSING_BLOCK_LABEL_657;
        obj = obj2;
        obj5 = mContext.getClassLoader();
        if(s1 == null) goto _L9; else goto _L8
_L8:
        obj = obj2;
        obj4 = JVM INSTR new #153 <Class StringBuilder>;
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        obj = obj2;
        obj4 = ((StringBuilder) (obj4)).append(s1).append(s).toString();
_L11:
        obj = obj2;
        obj2 = ((ClassLoader) (obj5)).loadClass(((String) (obj4))).asSubclass(android/view/View);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_651;
        obj = obj2;
        boolean flag = mFilter.onLoadClass(((Class) (obj2)));
_L12:
        obj = obj2;
        mFilterMap.put(s, Boolean.valueOf(flag));
        obj4 = obj2;
        obj5 = obj1;
        if(flag) goto _L7; else goto _L10
_L10:
        obj = obj2;
        failNotAllowed(s, s1, attributeset);
        obj4 = obj2;
        obj5 = obj1;
          goto _L7
        obj4;
        obj = JVM INSTR new #168 <Class InflateException>;
        obj1 = JVM INSTR new #153 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append(attributeset.getPositionDescription()).append(": Error inflating class ");
        attributeset = s;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_609;
        attributeset = JVM INSTR new #153 <Class StringBuilder>;
        attributeset.StringBuilder();
        attributeset = attributeset.append(s1).append(s).toString();
        ((InflateException) (obj)).InflateException(((StringBuilder) (obj1)).append(attributeset).toString(), ((Throwable) (obj4)));
        ((InflateException) (obj)).setStackTrace(EMPTY_STACK_TRACE);
        throw obj;
        s;
        Trace.traceEnd(8L);
        throw s;
_L9:
        obj4 = s;
          goto _L11
        flag = false;
          goto _L12
        obj4 = obj3;
        obj5 = obj1;
        obj = obj2;
        if(!boolean1.equals(Boolean.FALSE)) goto _L7; else goto _L13
_L13:
        obj = obj2;
        failNotAllowed(s, s1, attributeset);
        obj4 = obj3;
        obj5 = obj1;
          goto _L7
        obj;
        obj4 = JVM INSTR new #168 <Class InflateException>;
        obj1 = JVM INSTR new #153 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append(attributeset.getPositionDescription()).append(": Class is not a View ");
        attributeset = s;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_765;
        attributeset = JVM INSTR new #153 <Class StringBuilder>;
        attributeset.StringBuilder();
        attributeset = attributeset.append(s1).append(s).toString();
        ((InflateException) (obj4)).InflateException(((StringBuilder) (obj1)).append(attributeset).toString(), ((Throwable) (obj)));
        ((InflateException) (obj4)).setStackTrace(EMPTY_STACK_TRACE);
        throw obj4;
        Exception exception;
        exception;
        s1 = JVM INSTR new #168 <Class InflateException>;
        s = JVM INSTR new #153 <Class StringBuilder>;
        s.StringBuilder();
        attributeset = s.append(attributeset.getPositionDescription()).append(": Error inflating class ");
        if(obj != null) goto _L15; else goto _L14
_L14:
        s = "<unknown>";
_L17:
        s1.InflateException(attributeset.append(s).toString(), exception);
        s1.setStackTrace(EMPTY_STACK_TRACE);
        throw s1;
_L15:
        s = ((Class) (obj)).getName();
        if(true) goto _L17; else goto _L16
_L16:
        s;
        throw s;
          goto _L5
    }

    View createViewFromTag(View view, String s, Context context, AttributeSet attributeset, boolean flag)
    {
        String s1;
        Object obj;
        s1 = s;
        if(s.equals("view"))
            s1 = attributeset.getAttributeValue(null, "class");
        obj = context;
        if(!flag)
        {
            obj = context.obtainStyledAttributes(attributeset, ATTRS_THEME);
            int i = ((TypedArray) (obj)).getResourceId(0, 0);
            s = context;
            if(i != 0)
                s = new ContextThemeWrapper(context, i);
            ((TypedArray) (obj)).recycle();
            obj = s;
        }
        if(s1.equals("blink"))
            return new BlinkLayout(((Context) (obj)), attributeset);
        if(mFactory2 == null) goto _L2; else goto _L1
_L1:
        s = mFactory2.onCreateView(view, s1, ((Context) (obj)), attributeset);
_L9:
        context = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_158;
        context = s;
        if(mPrivateFactory != null)
            context = mPrivateFactory.onCreateView(view, s1, ((Context) (obj)), attributeset);
        s = context;
        if(context != null) goto _L4; else goto _L3
_L3:
        s = ((String) (mConstructorArgs[0]));
        mConstructorArgs[0] = obj;
        if(-1 != s1.indexOf('.')) goto _L6; else goto _L5
_L5:
        view = onCreateView(view, s1, attributeset);
_L7:
        mConstructorArgs[0] = s;
        s = view;
_L4:
        return s;
_L2:
        if(mFactory != null)
        {
            s = mFactory.onCreateView(s1, ((Context) (obj)), attributeset);
            continue; /* Loop/switch isn't completed */
        }
        s = null;
        continue; /* Loop/switch isn't completed */
_L6:
        view = createView(s1, null, attributeset);
          goto _L7
        view;
        try
        {
            mConstructorArgs[0] = s;
            throw view;
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            throw view;
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            view = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s1).toString(), view);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            view = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s1).toString(), view);
            view.setStackTrace(EMPTY_STACK_TRACE);
            throw view;
        }
        view.setStackTrace(EMPTY_STACK_TRACE);
        throw view;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public Context getContext()
    {
        return mContext;
    }

    public final Factory getFactory()
    {
        return mFactory;
    }

    public final Factory2 getFactory2()
    {
        return mFactory2;
    }

    public Filter getFilter()
    {
        return mFilter;
    }

    public View inflate(int i, ViewGroup viewgroup)
    {
        boolean flag;
        if(viewgroup != null)
            flag = true;
        else
            flag = false;
        return inflate(i, viewgroup, flag);
    }

    public View inflate(int i, ViewGroup viewgroup, boolean flag)
    {
        XmlResourceParser xmlresourceparser = getContext().getResources().getLayout(LayoutInflaterMap.getResourceId(getContext(), i));
        viewgroup = inflate(((XmlPullParser) (xmlresourceparser)), viewgroup, flag);
        xmlresourceparser.close();
        return viewgroup;
        viewgroup;
        xmlresourceparser.close();
        throw viewgroup;
    }

    public View inflate(XmlPullParser xmlpullparser, ViewGroup viewgroup)
    {
        boolean flag;
        if(viewgroup != null)
            flag = true;
        else
            flag = false;
        return inflate(xmlpullparser, viewgroup, flag);
    }

    public View inflate(XmlPullParser xmlpullparser, ViewGroup viewgroup, boolean flag)
    {
        Object aobj[] = mConstructorArgs;
        aobj;
        JVM INSTR monitorenter ;
        Object obj;
        AttributeSet attributeset;
        Context context;
        Trace.traceBegin(8L, "inflate");
        obj = mContext;
        attributeset = Xml.asAttributeSet(xmlpullparser);
        context = (Context)mConstructorArgs[0];
        mConstructorArgs[0] = obj;
        Object obj1 = viewgroup;
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i == 2)
            break MISSING_BLOCK_LABEL_171;
        viewgroup = JVM INSTR new #168 <Class InflateException>;
        obj1 = JVM INSTR new #153 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        viewgroup.InflateException(((StringBuilder) (obj1)).append(xmlpullparser.getPositionDescription()).append(": No start tag found!").toString());
        throw viewgroup;
        viewgroup;
        xmlpullparser = JVM INSTR new #168 <Class InflateException>;
        xmlpullparser.InflateException(viewgroup.getMessage(), viewgroup);
        xmlpullparser.setStackTrace(EMPTY_STACK_TRACE);
        throw xmlpullparser;
        xmlpullparser;
        mConstructorArgs[0] = context;
        mConstructorArgs[1] = null;
        Trace.traceEnd(8L);
        throw xmlpullparser;
        xmlpullparser;
        aobj;
        JVM INSTR monitorexit ;
        throw xmlpullparser;
        Object obj2 = xmlpullparser.getName();
        if(!"merge".equals(obj2)) goto _L2; else goto _L1
_L1:
        if(viewgroup != null && !(flag ^ true))
            break MISSING_BLOCK_LABEL_271;
        viewgroup = JVM INSTR new #168 <Class InflateException>;
        viewgroup.InflateException("<merge /> can be used only with a valid ViewGroup root and attachToRoot=true");
        throw viewgroup;
        obj1;
        viewgroup = JVM INSTR new #168 <Class InflateException>;
        obj = JVM INSTR new #153 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        viewgroup.InflateException(((StringBuilder) (obj)).append(xmlpullparser.getPositionDescription()).append(": ").append(((Exception) (obj1)).getMessage()).toString(), ((Throwable) (obj1)));
        viewgroup.setStackTrace(EMPTY_STACK_TRACE);
        throw viewgroup;
        rInflate(xmlpullparser, viewgroup, ((Context) (obj)), attributeset, false);
_L4:
        mConstructorArgs[0] = context;
        mConstructorArgs[1] = null;
        Trace.traceEnd(8L);
        aobj;
        JVM INSTR monitorexit ;
        return ((View) (obj1));
_L2:
        obj2 = createViewFromTag(viewgroup, ((String) (obj2)), ((Context) (obj)), attributeset);
        obj = null;
        if(viewgroup == null)
            break MISSING_BLOCK_LABEL_356;
        ViewGroup.LayoutParams layoutparams = viewgroup.generateLayoutParams(attributeset);
        obj = layoutparams;
        if(flag)
            break MISSING_BLOCK_LABEL_356;
        ((View) (obj2)).setLayoutParams(layoutparams);
        obj = layoutparams;
        rInflateChildren(xmlpullparser, ((View) (obj2)), attributeset, true);
        if(viewgroup == null || !flag)
            break MISSING_BLOCK_LABEL_382;
        viewgroup.addView(((View) (obj2)), ((ViewGroup.LayoutParams) (obj)));
        if(viewgroup == null || flag ^ true)
            obj1 = obj2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected View onCreateView(View view, String s, AttributeSet attributeset)
        throws ClassNotFoundException
    {
        return onCreateView(s, attributeset);
    }

    protected View onCreateView(String s, AttributeSet attributeset)
        throws ClassNotFoundException
    {
        return createView(s, "android.view.", attributeset);
    }

    void rInflate(XmlPullParser xmlpullparser, View view, Context context, AttributeSet attributeset, boolean flag)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        boolean flag1 = false;
        do
        {
            int j = xmlpullparser.next();
            if(j == 3 && xmlpullparser.getDepth() <= i || j == 1)
                break;
            if(j == 2)
            {
                Object obj = xmlpullparser.getName();
                if("requestFocus".equals(obj))
                {
                    flag1 = true;
                    consumeChildElements(xmlpullparser);
                } else
                if("tag".equals(obj))
                    parseViewTag(xmlpullparser, view, attributeset);
                else
                if("include".equals(obj))
                {
                    if(xmlpullparser.getDepth() == 0)
                        throw new InflateException("<include /> cannot be the root element");
                    parseInclude(xmlpullparser, context, view, attributeset);
                } else
                {
                    if("merge".equals(obj))
                        throw new InflateException("<merge /> must be the root element");
                    obj = createViewFromTag(view, ((String) (obj)), context, attributeset);
                    ViewGroup viewgroup = (ViewGroup)view;
                    ViewGroup.LayoutParams layoutparams = viewgroup.generateLayoutParams(attributeset);
                    rInflateChildren(xmlpullparser, ((View) (obj)), attributeset, true);
                    viewgroup.addView(((View) (obj)), layoutparams);
                }
            }
        } while(true);
        if(flag1)
            view.restoreDefaultFocus();
        if(flag)
            view.onFinishInflate();
    }

    final void rInflateChildren(XmlPullParser xmlpullparser, View view, AttributeSet attributeset, boolean flag)
        throws XmlPullParserException, IOException
    {
        rInflate(xmlpullparser, view, view.getContext(), attributeset, flag);
    }

    public void setFactory(Factory factory)
    {
        if(mFactorySet)
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        if(factory == null)
            throw new NullPointerException("Given factory can not be null");
        mFactorySet = true;
        if(mFactory == null)
            mFactory = factory;
        else
            mFactory = new FactoryMerger(factory, null, mFactory, mFactory2);
    }

    public void setFactory2(Factory2 factory2)
    {
        if(mFactorySet)
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        if(factory2 == null)
            throw new NullPointerException("Given factory can not be null");
        mFactorySet = true;
        if(mFactory == null)
        {
            mFactory2 = factory2;
            mFactory = factory2;
        } else
        {
            factory2 = new FactoryMerger(factory2, factory2, mFactory, mFactory2);
            mFactory2 = factory2;
            mFactory = factory2;
        }
    }

    public void setFilter(Filter filter)
    {
        mFilter = filter;
        if(filter != null)
            mFilterMap = new HashMap();
    }

    public void setPrivateFactory(Factory2 factory2)
    {
        if(mPrivateFactory == null)
            mPrivateFactory = factory2;
        else
            mPrivateFactory = new FactoryMerger(factory2, factory2, mPrivateFactory, mPrivateFactory);
    }

    private static final int ATTRS_THEME[] = {
        0x1010000
    };
    private static final String ATTR_LAYOUT = "layout";
    private static final ClassLoader BOOT_CLASS_LOADER = android/view/LayoutInflater.getClassLoader();
    private static final boolean DEBUG = false;
    private static final StackTraceElement EMPTY_STACK_TRACE[] = new StackTraceElement[0];
    private static final String TAG = android/view/LayoutInflater.getSimpleName();
    private static final String TAG_1995 = "blink";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_MERGE = "merge";
    private static final String TAG_REQUEST_FOCUS = "requestFocus";
    private static final String TAG_TAG = "tag";
    static final Class mConstructorSignature[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final HashMap sConstructorMap = new HashMap();
    final Object mConstructorArgs[];
    protected final Context mContext;
    private Factory mFactory;
    private Factory2 mFactory2;
    private boolean mFactorySet;
    private Filter mFilter;
    private HashMap mFilterMap;
    private Factory2 mPrivateFactory;
    private TypedValue mTempValue;


    // Unreferenced inner class android/view/LayoutInflater$BlinkLayout$1

/* anonymous class */
    class BlinkLayout._cls1
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            if(message.what == 66)
            {
                if(BlinkLayout._2D_get0(BlinkLayout.this))
                {
                    BlinkLayout._2D_set0(BlinkLayout.this, BlinkLayout._2D_get1(BlinkLayout.this) ^ true);
                    BlinkLayout._2D_wrap0(BlinkLayout.this);
                }
                invalidate();
                return true;
            } else
            {
                return false;
            }
        }

        final BlinkLayout this$1;

            
            {
                this$1 = BlinkLayout.this;
                super();
            }
    }

}
