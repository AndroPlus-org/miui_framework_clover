// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.util.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package android.view:
//            View, IWindowSession, ViewGroup, ViewOverlay, 
//            ViewHierarchyEncoder, ViewRootImpl

public class ViewDebug
{
    public static interface CapturedViewProperty
        extends Annotation
    {

        public abstract boolean retrieveReturn();
    }

    public static interface ExportedProperty
        extends Annotation
    {

        public abstract String category();

        public abstract boolean deepExport();

        public abstract FlagToString[] flagMapping();

        public abstract boolean formatToHexString();

        public abstract boolean hasAdjacentMapping();

        public abstract IntToString[] indexMapping();

        public abstract IntToString[] mapping();

        public abstract String prefix();

        public abstract boolean resolveId();
    }

    public static interface FlagToString
        extends Annotation
    {

        public abstract int equals();

        public abstract int mask();

        public abstract String name();

        public abstract boolean outputIf();
    }

    public static interface HierarchyHandler
    {

        public abstract void dumpViewHierarchyWithProperties(BufferedWriter bufferedwriter, int i);

        public abstract View findHierarchyView(String s, int i);
    }

    public static final class HierarchyTraceType extends Enum
    {

        public static HierarchyTraceType valueOf(String s)
        {
            return (HierarchyTraceType)Enum.valueOf(android/view/ViewDebug$HierarchyTraceType, s);
        }

        public static HierarchyTraceType[] values()
        {
            return $VALUES;
        }

        private static final HierarchyTraceType $VALUES[];
        public static final HierarchyTraceType BUILD_CACHE;
        public static final HierarchyTraceType DRAW;
        public static final HierarchyTraceType INVALIDATE;
        public static final HierarchyTraceType INVALIDATE_CHILD;
        public static final HierarchyTraceType INVALIDATE_CHILD_IN_PARENT;
        public static final HierarchyTraceType ON_LAYOUT;
        public static final HierarchyTraceType ON_MEASURE;
        public static final HierarchyTraceType REQUEST_LAYOUT;

        static 
        {
            INVALIDATE = new HierarchyTraceType("INVALIDATE", 0);
            INVALIDATE_CHILD = new HierarchyTraceType("INVALIDATE_CHILD", 1);
            INVALIDATE_CHILD_IN_PARENT = new HierarchyTraceType("INVALIDATE_CHILD_IN_PARENT", 2);
            REQUEST_LAYOUT = new HierarchyTraceType("REQUEST_LAYOUT", 3);
            ON_LAYOUT = new HierarchyTraceType("ON_LAYOUT", 4);
            ON_MEASURE = new HierarchyTraceType("ON_MEASURE", 5);
            DRAW = new HierarchyTraceType("DRAW", 6);
            BUILD_CACHE = new HierarchyTraceType("BUILD_CACHE", 7);
            $VALUES = (new HierarchyTraceType[] {
                INVALIDATE, INVALIDATE_CHILD, INVALIDATE_CHILD_IN_PARENT, REQUEST_LAYOUT, ON_LAYOUT, ON_MEASURE, DRAW, BUILD_CACHE
            });
        }

        private HierarchyTraceType(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface IntToString
        extends Annotation
    {

        public abstract int from();

        public abstract String to();
    }

    public static final class RecyclerTraceType extends Enum
    {

        public static RecyclerTraceType valueOf(String s)
        {
            return (RecyclerTraceType)Enum.valueOf(android/view/ViewDebug$RecyclerTraceType, s);
        }

        public static RecyclerTraceType[] values()
        {
            return $VALUES;
        }

        private static final RecyclerTraceType $VALUES[];
        public static final RecyclerTraceType BIND_VIEW;
        public static final RecyclerTraceType MOVE_FROM_ACTIVE_TO_SCRAP_HEAP;
        public static final RecyclerTraceType MOVE_TO_SCRAP_HEAP;
        public static final RecyclerTraceType NEW_VIEW;
        public static final RecyclerTraceType RECYCLE_FROM_ACTIVE_HEAP;
        public static final RecyclerTraceType RECYCLE_FROM_SCRAP_HEAP;

        static 
        {
            NEW_VIEW = new RecyclerTraceType("NEW_VIEW", 0);
            BIND_VIEW = new RecyclerTraceType("BIND_VIEW", 1);
            RECYCLE_FROM_ACTIVE_HEAP = new RecyclerTraceType("RECYCLE_FROM_ACTIVE_HEAP", 2);
            RECYCLE_FROM_SCRAP_HEAP = new RecyclerTraceType("RECYCLE_FROM_SCRAP_HEAP", 3);
            MOVE_TO_SCRAP_HEAP = new RecyclerTraceType("MOVE_TO_SCRAP_HEAP", 4);
            MOVE_FROM_ACTIVE_TO_SCRAP_HEAP = new RecyclerTraceType("MOVE_FROM_ACTIVE_TO_SCRAP_HEAP", 5);
            $VALUES = (new RecyclerTraceType[] {
                NEW_VIEW, BIND_VIEW, RECYCLE_FROM_ACTIVE_HEAP, RECYCLE_FROM_SCRAP_HEAP, MOVE_TO_SCRAP_HEAP, MOVE_FROM_ACTIVE_TO_SCRAP_HEAP
            });
        }

        private RecyclerTraceType(String s, int i)
        {
            super(s, i);
        }
    }

    static interface ViewOperation
    {

        public transient abstract void post(Object aobj[]);

        public abstract Object[] pre();

        public transient abstract void run(Object aobj[]);
    }


    public ViewDebug()
    {
    }

    private static Object callMethodOnAppropriateTheadBlocking(Method method, Object obj)
        throws IllegalAccessException, InvocationTargetException, TimeoutException
    {
        FutureTask futuretask;
        if(!(obj instanceof View))
            return method.invoke(obj, (Object[])null);
        obj = (View)obj;
        futuretask = new FutureTask(new Callable(method, ((View) (obj))) {

            public Object call()
                throws IllegalAccessException, InvocationTargetException
            {
                return method.invoke(view, (Object[])null);
            }

            final Method val$method;
            final View val$view;

            
            {
                method = method1;
                view = view1;
                super();
            }
        }
);
        obj = ((View) (obj)).getHandler();
        method = ((Method) (obj));
        if(obj == null)
            method = new Handler(Looper.getMainLooper());
        method.post(futuretask);
_L2:
        method = ((Method) (futuretask.get(4000L, TimeUnit.MILLISECONDS)));
        return method;
        method;
        throw new RuntimeException("Unexpected cancellation exception", method);
        method;
        method = method.getCause();
        if(method instanceof IllegalAccessException)
            throw (IllegalAccessException)method;
        if(method instanceof InvocationTargetException)
            throw (InvocationTargetException)method;
        else
            throw new RuntimeException("Unexpected exception", method);
        method;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void capture(View view, OutputStream outputstream, View view1)
        throws IOException
    {
        Bitmap bitmap;
        bitmap = performViewCapture(view1, false);
        view1 = bitmap;
        if(bitmap == null)
        {
            Log.w("View", "Failed to create capture bitmap!");
            view1 = Bitmap.createBitmap(view.getResources().getDisplayMetrics(), 1, 1, android.graphics.Bitmap.Config.ARGB_8888);
        }
        bitmap = null;
        BufferedOutputStream bufferedoutputstream;
        bufferedoutputstream = JVM INSTR new #221 <Class BufferedOutputStream>;
        bufferedoutputstream.BufferedOutputStream(outputstream, 32768);
        view1.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bufferedoutputstream);
        bufferedoutputstream.flush();
        if(bufferedoutputstream != null)
            bufferedoutputstream.close();
        view1.recycle();
        return;
        view;
        outputstream = bitmap;
_L2:
        if(outputstream != null)
            outputstream.close();
        view1.recycle();
        throw view;
        view;
        outputstream = bufferedoutputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void capture(View view, OutputStream outputstream, String s)
        throws IOException
    {
        capture(view, outputstream, findView(view, s));
    }

    public static void captureLayers(View view, DataOutputStream dataoutputstream)
        throws IOException
    {
        Rect rect;
        rect = JVM INSTR new #257 <Class Rect>;
        rect.Rect();
        try
        {
            view.mAttachInfo.mSession.getDisplayFrame(view.mAttachInfo.mWindow, rect);
        }
        catch(RemoteException remoteexception) { }
        dataoutputstream.writeInt(rect.width());
        dataoutputstream.writeInt(rect.height());
        captureViewLayer(view, dataoutputstream, true);
        dataoutputstream.write(2);
        dataoutputstream.close();
        return;
        view;
        dataoutputstream.close();
        throw view;
    }

    private static void captureViewLayer(View view, DataOutputStream dataoutputstream, boolean flag)
        throws IOException
    {
        if(view.getVisibility() != 0)
            flag = false;
        if((view.mPrivateFlags & 0x80) != 128)
        {
            int i = view.getId();
            Object obj = view.getClass().getSimpleName();
            if(i != -1)
                obj = resolveId(view.getContext(), i).toString();
            dataoutputstream.write(1);
            dataoutputstream.writeUTF(((String) (obj)));
            int ai[];
            int j;
            if(flag)
                i = 1;
            else
                i = 0;
            dataoutputstream.writeByte(i);
            ai = new int[2];
            view.getLocationInWindow(ai);
            dataoutputstream.writeInt(ai[0]);
            dataoutputstream.writeInt(ai[1]);
            dataoutputstream.flush();
            ai = performViewCapture(view, true);
            if(ai != null)
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(ai.getWidth() * ai.getHeight() * 2);
                ai.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
                dataoutputstream.writeInt(bytearrayoutputstream.size());
                bytearrayoutputstream.writeTo(dataoutputstream);
            }
            dataoutputstream.flush();
        }
        if(view instanceof ViewGroup)
        {
            ai = (ViewGroup)view;
            j = ai.getChildCount();
            for(i = 0; i < j; i++)
                captureViewLayer(ai.getChildAt(i), dataoutputstream, flag);

        }
        if(view.mOverlay != null)
            captureViewLayer(((View) (view.getOverlay().mOverlayViewGroup)), dataoutputstream, flag);
    }

    private static String capturedViewExportFields(Object obj, Class class1, String s)
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        if(obj == null)
            return "null";
        stringbuilder = new StringBuilder();
        class1 = capturedViewGetPropertyFields(class1);
        i = class1.length;
        j = 0;
_L6:
        Field field;
        if(j >= i)
            break MISSING_BLOCK_LABEL_126;
        field = class1[j];
        Object obj1;
        obj1 = field.get(obj);
        stringbuilder.append(s);
        stringbuilder.append(field.getName());
        stringbuilder.append("=");
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(obj1.toString().replace("\n", "\\n"));
_L3:
        stringbuilder.append(' ');
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append("null");
          goto _L3
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L4
        return stringbuilder.toString();
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static String capturedViewExportMethods(Object obj, Class class1, String s)
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        if(obj == null)
            return "null";
        stringbuilder = new StringBuilder();
        class1 = capturedViewGetPropertyMethods(class1);
        i = class1.length;
        j = 0;
_L2:
label0:
        {
            if(j >= i)
                break label0;
            Method method = class1[j];
            Object obj1;
            try
            {
                obj1 = method.invoke(obj, (Object[])null);
                Class class2 = method.getReturnType();
                if(!((CapturedViewProperty)method.getAnnotation(android/view/ViewDebug$CapturedViewProperty)).retrieveReturn())
                    break; /* Loop/switch isn't completed */
                StringBuilder stringbuilder1 = JVM INSTR new #387 <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                stringbuilder.append(capturedViewExportMethods(obj1, class2, stringbuilder1.append(method.getName()).append("#").toString()));
            }
            catch(IllegalAccessException illegalaccessexception) { }
            catch(InvocationTargetException invocationtargetexception) { }
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s);
        stringbuilder.append(method.getName());
        stringbuilder.append("()=");
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_189;
        stringbuilder.append(obj1.toString().replace("\n", "\\n"));
_L4:
        stringbuilder.append("; ");
          goto _L3
        stringbuilder.append("null");
          goto _L4
        return stringbuilder.toString();
          goto _L3
    }

    private static Field[] capturedViewGetPropertyFields(Class class1)
    {
        if(mCapturedViewFieldsForClasses == null)
            mCapturedViewFieldsForClasses = new HashMap();
        HashMap hashmap = mCapturedViewFieldsForClasses;
        Field afield[] = (Field[])hashmap.get(class1);
        if(afield != null)
            return afield;
        ArrayList arraylist = new ArrayList();
        Field afield1[] = class1.getFields();
        int i = afield1.length;
        for(int j = 0; j < i; j++)
        {
            Field field = afield1[j];
            if(field.isAnnotationPresent(android/view/ViewDebug$CapturedViewProperty))
            {
                field.setAccessible(true);
                arraylist.add(field);
            }
        }

        field = (Field[])arraylist.toArray(new Field[arraylist.size()]);
        hashmap.put(class1, field);
        return field;
    }

    private static Method[] capturedViewGetPropertyMethods(Class class1)
    {
        if(mCapturedViewMethodsForClasses == null)
            mCapturedViewMethodsForClasses = new HashMap();
        HashMap hashmap = mCapturedViewMethodsForClasses;
        Method amethod[] = (Method[])hashmap.get(class1);
        if(amethod != null)
            return amethod;
        ArrayList arraylist = new ArrayList();
        amethod = class1.getMethods();
        int i = amethod.length;
        for(int j = 0; j < i; j++)
        {
            Method method = amethod[j];
            if(method.getParameterTypes().length == 0 && method.isAnnotationPresent(android/view/ViewDebug$CapturedViewProperty) && method.getReturnType() != java/lang/Void)
            {
                method.setAccessible(true);
                arraylist.add(method);
            }
        }

        amethod = (Method[])arraylist.toArray(new Method[arraylist.size()]);
        hashmap.put(class1, amethod);
        return amethod;
    }

    static void dispatchCommand(View view, String s, String s1, OutputStream outputstream)
        throws IOException
    {
        view = view.getRootView();
        if(!"DUMP".equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        dump(view, false, true, outputstream);
_L4:
        return;
_L2:
        if("DUMP_THEME".equalsIgnoreCase(s))
            dumpTheme(view, outputstream);
        else
        if("CAPTURE_LAYERS".equalsIgnoreCase(s))
        {
            captureLayers(view, new DataOutputStream(outputstream));
        } else
        {
            s1 = s1.split(" ");
            if("CAPTURE".equalsIgnoreCase(s))
                capture(view, outputstream, s1[0]);
            else
            if("OUTPUT_DISPLAYLIST".equalsIgnoreCase(s))
                outputDisplayList(view, s1[0]);
            else
            if("INVALIDATE".equalsIgnoreCase(s))
                invalidate(view, s1[0]);
            else
            if("REQUEST_LAYOUT".equalsIgnoreCase(s))
                requestLayout(view, s1[0]);
            else
            if("PROFILE".equalsIgnoreCase(s))
                profile(view, outputstream, s1[0]);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void dump(View view, boolean flag, boolean flag1, OutputStream outputstream)
        throws IOException
    {
        Object obj;
        obj = JVM INSTR new #544 <Class BufferedWriter>;
        OutputStreamWriter outputstreamwriter = JVM INSTR new #546 <Class OutputStreamWriter>;
        outputstreamwriter.OutputStreamWriter(outputstream, "utf-8");
        ((BufferedWriter) (obj)).BufferedWriter(outputstreamwriter, 32768);
        outputstream = ((OutputStream) (obj));
        view = view.getRootView();
        outputstream = ((OutputStream) (obj));
        if(!(view instanceof ViewGroup))
            break MISSING_BLOCK_LABEL_70;
        outputstream = ((OutputStream) (obj));
        view = (ViewGroup)view;
        outputstream = ((OutputStream) (obj));
        dumpViewHierarchy(view.getContext(), view, ((BufferedWriter) (obj)), 0, flag, flag1);
        outputstream = ((OutputStream) (obj));
        ((BufferedWriter) (obj)).write("DONE.");
        outputstream = ((OutputStream) (obj));
        ((BufferedWriter) (obj)).newLine();
        if(obj != null)
            ((BufferedWriter) (obj)).close();
_L1:
        return;
        obj;
        view = null;
_L4:
        outputstream = view;
        Log.w("View", "Problem dumping the view:", ((Throwable) (obj)));
        if(view != null)
            view.close();
          goto _L1
        view;
        outputstream = null;
_L3:
        if(outputstream != null)
            outputstream.close();
        throw view;
        view;
        if(true) goto _L3; else goto _L2
_L2:
        outputstream;
        view = ((View) (obj));
        obj = outputstream;
          goto _L4
    }

    public static void dumpCapturedView(String s, Object obj)
    {
        Class class1 = obj.getClass();
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append(class1.getName()).append(": ").toString());
        stringbuilder.append(capturedViewExportFields(obj, class1, ""));
        stringbuilder.append(capturedViewExportMethods(obj, class1, ""));
        Log.d(s, stringbuilder.toString());
    }

    public static void dumpTheme(View view, OutputStream outputstream)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = obj;
        BufferedWriter bufferedwriter = JVM INSTR new #544 <Class BufferedWriter>;
        obj2 = obj;
        OutputStreamWriter outputstreamwriter = JVM INSTR new #546 <Class OutputStreamWriter>;
        obj2 = obj;
        outputstreamwriter.OutputStreamWriter(outputstream, "utf-8");
        obj2 = obj;
        bufferedwriter.BufferedWriter(outputstreamwriter, 32768);
        view = getStyleAttributesDump(view.getContext().getResources(), view.getContext().getTheme());
        if(view == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L3:
        if(i >= view.length)
            break; /* Loop/switch isn't completed */
        if(view[i] == null)
            break MISSING_BLOCK_LABEL_145;
        outputstream = JVM INSTR new #387 <Class StringBuilder>;
        outputstream.StringBuilder();
        bufferedwriter.write(outputstream.append(view[i]).append("\n").toString());
        outputstream = JVM INSTR new #387 <Class StringBuilder>;
        outputstream.StringBuilder();
        bufferedwriter.write(outputstream.append(view[i + 1]).append("\n").toString());
        i += 2;
        if(true) goto _L3; else goto _L2
_L2:
        bufferedwriter.write("DONE.");
        bufferedwriter.newLine();
        if(bufferedwriter != null)
            bufferedwriter.close();
_L4:
        return;
        outputstream;
        view = obj1;
_L7:
        obj2 = view;
        Log.w("View", "Problem dumping View Theme:", outputstream);
        if(view != null)
            view.close();
          goto _L4
        view;
_L6:
        if(obj2 != null)
            ((BufferedWriter) (obj2)).close();
        throw view;
        view;
        obj2 = bufferedwriter;
        if(true) goto _L6; else goto _L5
_L5:
        outputstream;
        view = bufferedwriter;
          goto _L7
    }

    private static boolean dumpView(Context context, View view, BufferedWriter bufferedwriter, int i, boolean flag)
    {
        int j = 0;
        do
        {
            if(j >= i)
                break;
            String s;
            String s1;
            try
            {
                bufferedwriter.write(32);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.w("View", "Error while dumping hierarchy tree");
                return false;
            }
            j++;
        } while(true);
        s = view.getClass().getName();
        s1 = s;
        if(s.equals("android.view.ViewOverlay$OverlayViewGroup"))
            s1 = "ViewOverlay";
        bufferedwriter.write(s1);
        bufferedwriter.write(64);
        bufferedwriter.write(Integer.toHexString(view.hashCode()));
        bufferedwriter.write(32);
        if(!flag)
            break MISSING_BLOCK_LABEL_90;
        dumpViewProperties(context, view, bufferedwriter);
        bufferedwriter.newLine();
        return true;
    }

    private static void dumpViewHierarchy(Context context, ViewGroup viewgroup, BufferedWriter bufferedwriter, int i, boolean flag, boolean flag1)
    {
        if(!dumpView(context, viewgroup, bufferedwriter, i, flag1))
            return;
        if(flag)
            return;
        int j = viewgroup.getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = viewgroup.getChildAt(k);
            if(view instanceof ViewGroup)
                dumpViewHierarchy(context, (ViewGroup)view, bufferedwriter, i + 1, flag, flag1);
            else
                dumpView(context, view, bufferedwriter, i + 1, flag1);
            if(view.mOverlay != null)
                dumpViewHierarchy(context, ((ViewGroup) (view.getOverlay().mOverlayViewGroup)), bufferedwriter, i + 2, flag, flag1);
            k++;
        }
        if(viewgroup instanceof HierarchyHandler)
            ((HierarchyHandler)viewgroup).dumpViewHierarchyWithProperties(bufferedwriter, i + 1);
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedwriter)
        throws IOException
    {
        dumpViewProperties(context, obj, bufferedwriter, "");
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedwriter, String s)
        throws IOException
    {
        if(obj == null)
        {
            bufferedwriter.write((new StringBuilder()).append(s).append("=4,null ").toString());
            return;
        }
        Class class1 = obj.getClass();
        Class class2;
        do
        {
            exportFields(context, obj, bufferedwriter, class1, s);
            exportMethods(context, obj, bufferedwriter, class1, s);
            class2 = class1.getSuperclass();
            class1 = class2;
        } while(class2 != java/lang/Object);
    }

    public static void dumpv2(View view, ByteArrayOutputStream bytearrayoutputstream)
        throws InterruptedException
    {
        ViewHierarchyEncoder viewhierarchyencoder = new ViewHierarchyEncoder(bytearrayoutputstream);
        bytearrayoutputstream = new CountDownLatch(1);
        view.post(new Runnable(viewhierarchyencoder, view, bytearrayoutputstream) {

            public void run()
            {
                encoder.addProperty("window:left", view.mAttachInfo.mWindowLeft);
                encoder.addProperty("window:top", view.mAttachInfo.mWindowTop);
                view.encode(encoder);
                latch.countDown();
            }

            final ViewHierarchyEncoder val$encoder;
            final CountDownLatch val$latch;
            final View val$view;

            
            {
                encoder = viewhierarchyencoder;
                view = view1;
                latch = countdownlatch;
                super();
            }
        }
);
        bytearrayoutputstream.await(2L, TimeUnit.SECONDS);
        viewhierarchyencoder.endStream();
    }

    private static void exportFields(Context context, Object obj, BufferedWriter bufferedwriter, Class class1, String s)
        throws IOException
    {
        Field afield[];
        int i;
        int j;
        afield = getExportedPropertyFields(class1);
        i = afield.length;
        j = 0;
_L21:
        Object obj1;
        Object obj2;
        Object aobj[];
        if(j >= i)
            break MISSING_BLOCK_LABEL_723;
        obj1 = afield[j];
        obj2 = null;
        class1 = null;
        aobj = null;
        Class class2;
        ExportedProperty exportedproperty;
        class2 = ((Field) (obj1)).getType();
        exportedproperty = (ExportedProperty)sAnnotations.get(obj1);
        if(exportedproperty.category().length() == 0) goto _L2; else goto _L1
_L1:
        Object obj4;
        obj4 = JVM INSTR new #387 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        obj4 = ((StringBuilder) (obj4)).append(exportedproperty.category()).append(":").toString();
_L7:
        if(class2 != Integer.TYPE && class2 != Byte.TYPE) goto _L4; else goto _L3
_L3:
        if(!exportedproperty.resolveId() || context == null) goto _L6; else goto _L5
_L5:
        class1 = ((Class) (resolveId(context, ((Field) (obj1)).getInt(obj))));
_L11:
        aobj = class1;
        if(class1 != null)
            break MISSING_BLOCK_LABEL_158;
        aobj = ((Object []) (((Field) (obj1)).get(obj)));
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        writeEntry(bufferedwriter, class1.append(((String) (obj4))).append(s).toString(), ((Field) (obj1)).getName(), "", ((Object) (aobj)));
_L14:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        obj4 = "";
          goto _L7
_L6:
        IntToString ainttostring[];
        aobj = exportedproperty.flagMapping();
        if(aobj.length > 0)
        {
            int k = ((Field) (obj1)).getInt(obj);
            StringBuilder stringbuilder1 = JVM INSTR new #387 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            exportUnrolledFlags(bufferedwriter, ((FlagToString []) (aobj)), k, stringbuilder1.append(((String) (obj4))).append(s).append(((Field) (obj1)).getName()).append('_').toString());
        }
        ainttostring = exportedproperty.mapping();
        if(ainttostring.length <= 0) goto _L9; else goto _L8
_L8:
        int i1;
        int j1;
        i1 = ((Field) (obj1)).getInt(obj);
        j1 = ainttostring.length;
        int l = 0;
_L12:
        aobj = obj2;
        if(l >= j1)
            break MISSING_BLOCK_LABEL_344;
        class1 = ainttostring[l];
        if(class1.from() != i1)
            break MISSING_BLOCK_LABEL_399;
        aobj = class1.to();
        class1 = ((Class) (aobj));
        if(aobj != null) goto _L9; else goto _L10
_L10:
        class1 = Integer.valueOf(i1);
_L9:
        if(exportedproperty.formatToHexString())
        {
            aobj = ((Object []) (((Field) (obj1)).get(obj)));
            if(class2 != Integer.TYPE)
                break MISSING_BLOCK_LABEL_405;
            class1 = formatIntToHexString(((Integer)aobj).intValue());
        }
          goto _L11
        l++;
          goto _L12
        class1 = ((Class) (aobj));
        if(class2 != Byte.TYPE) goto _L11; else goto _L13
_L13:
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        class1 = class1.append("0x").append(Byte.toHexString(((Byte)aobj).byteValue(), true)).toString();
          goto _L11
_L4:
label0:
        {
            if(class2 != [I)
                break label0;
            try
            {
                aobj = (int[])((Field) (obj1)).get(obj);
                class1 = JVM INSTR new #387 <Class StringBuilder>;
                class1.StringBuilder();
                exportUnrolledArray(context, bufferedwriter, exportedproperty, ((int []) (aobj)), class1.append(((String) (obj4))).append(s).append(((Field) (obj1)).getName()).append('_').toString(), "");
            }
            // Misplaced declaration of an exception variable
            catch(Class class1) { }
        }
          goto _L14
        if(class2 != [Ljava/lang/String;)
            break MISSING_BLOCK_LABEL_653;
        aobj = (String[])((Field) (obj1)).get(obj);
        if(!exportedproperty.hasAdjacentMapping() || aobj == null) goto _L14; else goto _L15
_L15:
        l = 0;
_L17:
        if(l >= aobj.length) goto _L14; else goto _L16
_L16:
        if(aobj[l] == null)
            break MISSING_BLOCK_LABEL_636;
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        obj1 = class1.append(((String) (obj4))).append(s).toString();
        Object obj3 = aobj[l];
        if(aobj[l + 1] == null)
            class1 = "null";
        else
            class1 = ((Class) (aobj[l + 1]));
        writeEntry(bufferedwriter, ((String) (obj1)), ((String) (obj3)), "", class1);
        l += 2;
          goto _L17
        class1 = ((Class) (aobj));
        if(class2.isPrimitive()) goto _L11; else goto _L18
_L18:
        class1 = ((Class) (aobj));
        if(!exportedproperty.deepExport()) goto _L11; else goto _L19
_L19:
        class1 = ((Class) (((Field) (obj1)).get(obj)));
        StringBuilder stringbuilder = JVM INSTR new #387 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        dumpViewProperties(context, class1, bufferedwriter, stringbuilder.append(s).append(exportedproperty.prefix()).toString());
          goto _L14
        return;
        if(true) goto _L21; else goto _L20
_L20:
    }

    private static void exportMethods(Context context, Object obj, BufferedWriter bufferedwriter, Class class1, String s)
        throws IOException
    {
        Method amethod[];
        int i;
        int j;
        amethod = getExportedPropertyMethods(class1);
        i = amethod.length;
        j = 0;
_L21:
        Object obj1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_611;
        obj1 = amethod[j];
        Object aobj[];
        FlagToString aflagtostring[];
        Object obj2;
        aobj = ((Object []) (callMethodOnAppropriateTheadBlocking(((Method) (obj1)), obj)));
        aflagtostring = ((Method) (obj1)).getReturnType();
        obj2 = (ExportedProperty)sAnnotations.get(obj1);
        if(((ExportedProperty) (obj2)).category().length() == 0) goto _L2; else goto _L1
_L1:
        String s1;
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        s1 = class1.append(((ExportedProperty) (obj2)).category()).append(":").toString();
_L7:
        if(aflagtostring != Integer.TYPE) goto _L4; else goto _L3
_L3:
        if(!((ExportedProperty) (obj2)).resolveId() || context == null) goto _L6; else goto _L5
_L5:
        class1 = ((Class) (resolveId(context, ((Integer)aobj).intValue())));
_L9:
        aobj = JVM INSTR new #387 <Class StringBuilder>;
        ((StringBuilder) (aobj)).StringBuilder();
        writeEntry(bufferedwriter, ((StringBuilder) (aobj)).append(s1).append(s).toString(), ((Method) (obj1)).getName(), "()", class1);
_L14:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = "";
          goto _L7
_L6:
        aflagtostring = ((ExportedProperty) (obj2)).flagMapping();
        if(aflagtostring.length > 0)
        {
            int k = ((Integer)aobj).intValue();
            class1 = JVM INSTR new #387 <Class StringBuilder>;
            class1.StringBuilder();
            exportUnrolledFlags(bufferedwriter, aflagtostring, k, class1.append(s1).append(s).append(((Method) (obj1)).getName()).append('_').toString());
        }
        obj2 = ((ExportedProperty) (obj2)).mapping();
        class1 = ((Class) (aobj));
        if(obj2.length <= 0) goto _L9; else goto _L8
_L8:
        int i1 = ((Integer)aobj).intValue();
        boolean flag1 = false;
        int j1 = obj2.length;
        int k1 = 0;
_L13:
        boolean flag;
        flag = flag1;
        class1 = ((Class) (aobj));
        if(k1 >= j1) goto _L11; else goto _L10
_L10:
        class1 = obj2[k1];
        if(class1.from() != i1)
            break MISSING_BLOCK_LABEL_348;
        class1 = class1.to();
        flag = true;
_L11:
        if(flag) goto _L9; else goto _L12
_L12:
        class1 = Integer.valueOf(i1);
          goto _L9
        k1++;
          goto _L13
_L4:
label0:
        {
            if(aflagtostring != [I)
                break label0;
            int l;
            try
            {
                aobj = (int[])aobj;
                class1 = JVM INSTR new #387 <Class StringBuilder>;
                class1.StringBuilder();
                exportUnrolledArray(context, bufferedwriter, ((ExportedProperty) (obj2)), ((int []) (aobj)), class1.append(s1).append(s).append(((Method) (obj1)).getName()).append('_').toString(), "()");
            }
            // Misplaced declaration of an exception variable
            catch(Class class1) { }
            // Misplaced declaration of an exception variable
            catch(Class class1) { }
            // Misplaced declaration of an exception variable
            catch(Class class1) { }
        }
          goto _L14
        if(aflagtostring != [Ljava/lang/String;)
            break MISSING_BLOCK_LABEL_546;
        aobj = (String[])aobj;
        if(!((ExportedProperty) (obj2)).hasAdjacentMapping() || aobj == null) goto _L14; else goto _L15
_L15:
        l = 0;
_L17:
        if(l >= aobj.length) goto _L14; else goto _L16
_L16:
        if(aobj[l] == null)
            break MISSING_BLOCK_LABEL_529;
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        obj2 = class1.append(s1).append(s).toString();
        obj1 = aobj[l];
        if(aobj[l + 1] == null)
            class1 = "null";
        else
            class1 = ((Class) (aobj[l + 1]));
        writeEntry(bufferedwriter, ((String) (obj2)), ((String) (obj1)), "()", class1);
        l += 2;
          goto _L17
        class1 = ((Class) (aobj));
        if(aflagtostring.isPrimitive()) goto _L9; else goto _L18
_L18:
        class1 = ((Class) (aobj));
        if(!((ExportedProperty) (obj2)).deepExport()) goto _L9; else goto _L19
_L19:
        class1 = JVM INSTR new #387 <Class StringBuilder>;
        class1.StringBuilder();
        dumpViewProperties(context, ((Object) (aobj)), bufferedwriter, class1.append(s).append(((ExportedProperty) (obj2)).prefix()).toString());
          goto _L14
        return;
        if(true) goto _L21; else goto _L20
_L20:
    }

    private static void exportUnrolledArray(Context context, BufferedWriter bufferedwriter, ExportedProperty exportedproperty, int ai[], String s, String s1)
        throws IOException
    {
        int k;
        int i1;
        IntToString ainttostring[] = exportedproperty.indexMapping();
        boolean flag;
        IntToString ainttostring1[];
        boolean flag1;
        boolean flag2;
        int i;
        int j;
        Object obj;
        Object obj2;
        int l;
        if(ainttostring.length > 0)
            flag = true;
        else
            flag = false;
        ainttostring1 = exportedproperty.mapping();
        if(ainttostring1.length > 0)
            flag1 = true;
        else
            flag1 = false;
        if(exportedproperty.resolveId() && context != null)
            flag2 = true;
        else
            flag2 = false;
        i = ai.length;
        j = 0;
_L11:
        if(j >= i)
            break MISSING_BLOCK_LABEL_264;
        obj = null;
        k = ai[j];
        exportedproperty = String.valueOf(j);
        obj2 = exportedproperty;
        if(!flag) goto _L2; else goto _L1
_L1:
        l = ainttostring.length;
        i1 = 0;
_L8:
        obj2 = exportedproperty;
        if(i1 >= l) goto _L2; else goto _L3
_L3:
        obj2 = ainttostring[i1];
        if(((IntToString) (obj2)).from() != j) goto _L5; else goto _L4
_L4:
        obj2 = ((IntToString) (obj2)).to();
_L2:
        exportedproperty = obj;
        if(!flag1) goto _L7; else goto _L6
_L6:
        l = ainttostring1.length;
        i1 = 0;
_L9:
        exportedproperty = obj;
        if(i1 < l)
        {
            exportedproperty = ainttostring1[i1];
            if(exportedproperty.from() != k)
                break MISSING_BLOCK_LABEL_248;
            exportedproperty = exportedproperty.to();
        }
_L7:
        Object obj1;
        if(flag2)
        {
            obj1 = exportedproperty;
            if(exportedproperty == null)
                obj1 = (String)resolveId(context, k);
        } else
        {
            obj1 = String.valueOf(k);
        }
        writeEntry(bufferedwriter, s, ((String) (obj2)), s1, obj1);
        j++;
        continue; /* Loop/switch isn't completed */
_L5:
        i1++;
          goto _L8
        i1++;
          goto _L9
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private static void exportUnrolledFlags(BufferedWriter bufferedwriter, FlagToString aflagtostring[], int i, String s)
        throws IOException
    {
        int j = aflagtostring.length;
        int k = 0;
        while(k < j) 
        {
            FlagToString flagtostring = aflagtostring[k];
            boolean flag = flagtostring.outputIf();
            int l = i & flagtostring.mask();
            boolean flag1;
            if(l == flagtostring.equals())
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && flag || !flag1 && flag ^ true)
                writeEntry(bufferedwriter, s, flagtostring.name(), "", formatIntToHexString(l));
            k++;
        }
    }

    public static View findView(View view, String s)
    {
        if(s.indexOf('@') != -1)
        {
            String as[] = s.split("@");
            s = as[0];
            int i = (int)Long.parseLong(as[1], 16);
            view = view.getRootView();
            if(view instanceof ViewGroup)
                return findView((ViewGroup)view, s, i);
            else
                return null;
        } else
        {
            i = view.getResources().getIdentifier(s, null, null);
            return view.getRootView().findViewById(i);
        }
    }

    private static View findView(ViewGroup viewgroup, String s, int i)
    {
        if(isRequestedView(viewgroup, s, i))
            return viewgroup;
        int j = viewgroup.getChildCount();
        for(int k = 0; k < j; k++)
        {
            View view = viewgroup.getChildAt(k);
            if(view instanceof ViewGroup)
            {
                View view1 = findView((ViewGroup)view, s, i);
                if(view1 != null)
                    return view1;
            } else
            if(isRequestedView(view, s, i))
                return view;
            if(view.mOverlay != null)
            {
                View view2 = findView(((ViewGroup) (view.mOverlay.mOverlayViewGroup)), s, i);
                if(view2 != null)
                    return view2;
            }
            if(!(view instanceof HierarchyHandler))
                continue;
            view = ((HierarchyHandler)view).findHierarchyView(s, i);
            if(view != null)
                return view;
        }

        return null;
    }

    private static String formatIntToHexString(int i)
    {
        return (new StringBuilder()).append("0x").append(Integer.toHexString(i).toUpperCase()).toString();
    }

    private static Field[] getExportedPropertyFields(Class class1)
    {
        if(sFieldsForClasses == null)
            sFieldsForClasses = new HashMap();
        if(sAnnotations == null)
            sAnnotations = new HashMap(512);
        HashMap hashmap = sFieldsForClasses;
        Field afield[] = (Field[])hashmap.get(class1);
        if(afield != null)
            return afield;
        ArrayList arraylist;
        Field afield1[];
        int i;
        int j;
        Field field;
        try
        {
            afield1 = class1.getDeclaredFieldsUnchecked(false);
            arraylist = JVM INSTR new #455 <Class ArrayList>;
            arraylist.ArrayList();
            i = afield1.length;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new AssertionError(class1);
        }
        j = 0;
        if(j >= i) goto _L2; else goto _L1
_L1:
        field = afield1[j];
        if(field.getType() != null && field.isAnnotationPresent(android/view/ViewDebug$ExportedProperty))
        {
            field.setAccessible(true);
            arraylist.add(field);
            sAnnotations.put(field, (ExportedProperty)field.getAnnotation(android/view/ViewDebug$ExportedProperty));
        }
        j++;
        break MISSING_BLOCK_LABEL_75;
_L2:
        arraylist = (Field[])arraylist.toArray(new Field[arraylist.size()]);
        hashmap.put(class1, arraylist);
        return arraylist;
    }

    private static Method[] getExportedPropertyMethods(Class class1)
    {
        HashMap hashmap;
        ArrayList arraylist;
        Method amethod2[];
        int i;
        int j;
        if(sMethodsForClasses == null)
            sMethodsForClasses = new HashMap(100);
        if(sAnnotations == null)
            sAnnotations = new HashMap(512);
        hashmap = sMethodsForClasses;
        Method amethod[] = (Method[])hashmap.get(class1);
        if(amethod != null)
            return amethod;
        amethod2 = class1.getDeclaredMethodsUnchecked(false);
        arraylist = new ArrayList();
        i = amethod2.length;
        j = 0;
_L2:
        Method method;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        method = amethod2[j];
        method.getReturnType();
        method.getParameterTypes();
        if(method.getParameterTypes().length == 0 && method.isAnnotationPresent(android/view/ViewDebug$ExportedProperty) && method.getReturnType() != java/lang/Void)
        {
            method.setAccessible(true);
            arraylist.add(method);
            sAnnotations.put(method, (ExportedProperty)method.getAnnotation(android/view/ViewDebug$ExportedProperty));
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        Method amethod1[] = (Method[])arraylist.toArray(new Method[arraylist.size()]);
        hashmap.put(class1, amethod1);
        return amethod1;
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
          goto _L3
    }

    private static String[] getStyleAttributesDump(Resources resources, android.content.res.Resources.Theme theme)
    {
        TypedValue typedvalue;
        int i;
        int ai[];
        String as[];
        int j;
        int k;
        typedvalue = new TypedValue();
        i = 0;
        ai = theme.getAllAttributes();
        as = new String[ai.length * 2];
        j = ai.length;
        k = 0;
_L5:
        if(k >= j) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = ai[k];
        i1 = i;
        as[i] = resources.getResourceName(l);
        i1 = i;
        if(!theme.resolveAttribute(l, typedvalue, true)) goto _L4; else goto _L3
_L3:
        i1 = i;
        String s = typedvalue.coerceToString().toString();
_L6:
        as[i + 1] = s;
        l = i + 2;
        i = l;
        i1 = l;
        if(typedvalue.type != 1)
            break MISSING_BLOCK_LABEL_139;
        i1 = l;
        as[l - 1] = resources.getResourceName(typedvalue.resourceId);
        i = l;
_L7:
        k++;
          goto _L5
_L4:
        s = "null";
          goto _L6
_L2:
        return as;
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
        i = i1;
          goto _L7
    }

    public static long getViewInstanceCount()
    {
        return Debug.countInstancesOfClass(android/view/View);
    }

    public static long getViewRootImplCount()
    {
        return Debug.countInstancesOfClass(android/view/ViewRootImpl);
    }

    private static void invalidate(View view, String s)
    {
        view = findView(view, s);
        if(view != null)
            view.postInvalidate();
    }

    public static Object invokeViewMethod(View view, Method method, Object aobj[])
    {
        CountDownLatch countdownlatch = new CountDownLatch(1);
        AtomicReference atomicreference = new AtomicReference();
        AtomicReference atomicreference1 = new AtomicReference();
        view.post(new Runnable(atomicreference, method, view, aobj, atomicreference1, countdownlatch) {

            public void run()
            {
                try
                {
                    result.set(method.invoke(view, args));
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                    exception.set(invocationtargetexception.getCause());
                }
                catch(Exception exception1)
                {
                    exception.set(exception1);
                }
                latch.countDown();
            }

            final Object val$args[];
            final AtomicReference val$exception;
            final CountDownLatch val$latch;
            final Method val$method;
            final AtomicReference val$result;
            final View val$view;

            
            {
                result = atomicreference;
                method = method1;
                view = view1;
                args = aobj;
                exception = atomicreference1;
                latch = countdownlatch;
                super();
            }
        }
);
        try
        {
            countdownlatch.await();
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            throw new RuntimeException(view);
        }
        if(atomicreference1.get() != null)
            throw new RuntimeException((Throwable)atomicreference1.get());
        else
            return atomicreference.get();
    }

    private static boolean isRequestedView(View view, String s, int i)
    {
        if(view.hashCode() == i)
        {
            view = view.getClass().getName();
            if(s.equals("ViewOverlay"))
                return view.equals("android.view.ViewOverlay$OverlayViewGroup");
            else
                return s.equals(view);
        } else
        {
            return false;
        }
    }

    public static void outputDisplayList(View view, View view1)
    {
        view.getViewRootImpl().outputDisplayList(view1);
    }

    private static void outputDisplayList(View view, String s)
        throws IOException
    {
        view = findView(view, s);
        view.getViewRootImpl().outputDisplayList(view);
    }

    private static Bitmap performViewCapture(View view, boolean flag)
    {
        CountDownLatch countdownlatch;
        Bitmap abitmap[];
        if(view == null)
            break MISSING_BLOCK_LABEL_84;
        countdownlatch = new CountDownLatch(1);
        abitmap = new Bitmap[1];
        view.post(new Runnable(countdownlatch, abitmap, view, flag) {

            public void run()
            {
                cache[0] = captureView.createSnapshot(android.graphics.Bitmap.Config.ARGB_8888, 0, skipChildren);
                latch.countDown();
_L2:
                return;
                Object obj;
                obj;
                Log.w("View", "Out of memory for bitmap");
                latch.countDown();
                if(true) goto _L2; else goto _L1
_L1:
                obj;
                latch.countDown();
                throw obj;
            }

            final Bitmap val$cache[];
            final View val$captureView;
            final CountDownLatch val$latch;
            final boolean val$skipChildren;

            
            {
                latch = countdownlatch;
                cache = abitmap;
                captureView = view;
                skipChildren = flag;
                super();
            }
        }
);
        countdownlatch.await(4000L, TimeUnit.MILLISECONDS);
        view = abitmap[0];
        return view;
        InterruptedException interruptedexception;
        interruptedexception;
        Log.w("View", (new StringBuilder()).append("Could not complete the capture of the view ").append(view).toString());
        Thread.currentThread().interrupt();
        return null;
    }

    private static void profile(View view, OutputStream outputstream, String s)
        throws IOException
    {
        View view1;
        Object obj;
        Object obj1;
        view1 = findView(view, s);
        obj = null;
        obj1 = null;
        view = obj;
        s = JVM INSTR new #544 <Class BufferedWriter>;
        view = obj;
        OutputStreamWriter outputstreamwriter = JVM INSTR new #546 <Class OutputStreamWriter>;
        view = obj;
        outputstreamwriter.OutputStreamWriter(outputstream);
        view = obj;
        s.BufferedWriter(outputstreamwriter, 32768);
        if(view1 == null) goto _L2; else goto _L1
_L1:
        profileViewAndChildren(view1, s);
_L3:
        s.write("DONE.");
        s.newLine();
        if(s != null)
            s.close();
_L4:
        return;
_L2:
        s.write("-1 -1 -1");
        s.newLine();
          goto _L3
        view;
        outputstream = s;
        s = view;
_L7:
        view = outputstream;
        Log.w("View", "Problem profiling the view:", s);
        if(outputstream != null)
            outputstream.close();
          goto _L4
        outputstream;
        s = view;
_L6:
        if(s != null)
            s.close();
        throw outputstream;
        view;
        outputstream = view;
        if(true) goto _L6; else goto _L5
_L5:
        s;
        outputstream = obj1;
          goto _L7
    }

    public static void profileViewAndChildren(View view, BufferedWriter bufferedwriter)
        throws IOException
    {
        profileViewAndChildren(view, bufferedwriter, true);
    }

    private static void profileViewAndChildren(View view, BufferedWriter bufferedwriter, boolean flag)
        throws IOException
    {
        long l;
        long l1;
        long l2;
        if(flag || (view.mPrivateFlags & 0x800) != 0)
            l = profileViewOperation(view, new ViewOperation(view) {

                private void forceLayout(View view1)
                {
                    view1.forceLayout();
                    if(view1 instanceof ViewGroup)
                    {
                        view1 = (ViewGroup)view1;
                        int k = view1.getChildCount();
                        for(int i1 = 0; i1 < k; i1++)
                            forceLayout(view1.getChildAt(i1));

                    }
                }

                public volatile void post(Object aobj[])
                {
                    post((Void[])aobj);
                }

                public transient void post(Void avoid[])
                {
                }

                public volatile Object[] pre()
                {
                    return pre();
                }

                public Void[] pre()
                {
                    forceLayout(view);
                    return null;
                }

                public volatile void run(Object aobj[])
                {
                    run((Void[])aobj);
                }

                public transient void run(Void avoid[])
                {
                    view.measure(view.mOldWidthMeasureSpec, view.mOldHeightMeasureSpec);
                }

                final View val$view;

            
            {
                view = view1;
                super();
            }
            }
);
        else
            l = 0L;
        if(flag || (view.mPrivateFlags & 0x2000) != 0)
            l1 = profileViewOperation(view, new ViewOperation(view) {

                public volatile void post(Object aobj[])
                {
                    post((Void[])aobj);
                }

                public transient void post(Void avoid[])
                {
                }

                public volatile Object[] pre()
                {
                    return pre();
                }

                public Void[] pre()
                {
                    return null;
                }

                public volatile void run(Object aobj[])
                {
                    run((Void[])aobj);
                }

                public transient void run(Void avoid[])
                {
                    view.layout(view.mLeft, view.mTop, view.mRight, view.mBottom);
                }

                final View val$view;

            
            {
                view = view1;
                super();
            }
            }
);
        else
            l1 = 0L;
        if(flag || view.willNotDraw() ^ true || (view.mPrivateFlags & 0x20) != 0)
            l2 = profileViewOperation(view, new ViewOperation(view) {

                public transient void post(Object aobj[])
                {
                    if(aobj[1] != null)
                        ((Canvas)aobj[1]).setBitmap(null);
                    if(aobj[0] != null)
                        ((Bitmap)aobj[0]).recycle();
                }

                public Object[] pre()
                {
                    Object obj;
                    Canvas canvas;
                    if(view != null && view.getResources() != null)
                        obj = view.getResources().getDisplayMetrics();
                    else
                        obj = null;
                    if(obj != null)
                        obj = Bitmap.createBitmap(((DisplayMetrics) (obj)), ((DisplayMetrics) (obj)).widthPixels, ((DisplayMetrics) (obj)).heightPixels, android.graphics.Bitmap.Config.RGB_565);
                    else
                        obj = null;
                    if(obj != null)
                        canvas = new Canvas(((Bitmap) (obj)));
                    else
                        canvas = null;
                    return (new Object[] {
                        obj, canvas
                    });
                }

                public transient void run(Object aobj[])
                {
                    if(aobj[1] != null)
                        view.draw((Canvas)aobj[1]);
                }

                final View val$view;

            
            {
                view = view1;
                super();
            }
            }
);
        else
            l2 = 0L;
        bufferedwriter.write(String.valueOf(l));
        bufferedwriter.write(32);
        bufferedwriter.write(String.valueOf(l1));
        bufferedwriter.write(32);
        bufferedwriter.write(String.valueOf(l2));
        bufferedwriter.newLine();
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int i = view.getChildCount();
            for(int j = 0; j < i; j++)
                profileViewAndChildren(view.getChildAt(j), bufferedwriter, false);

        }
    }

    private static long profileViewOperation(View view, ViewOperation viewoperation)
    {
        long al[];
label0:
        {
            CountDownLatch countdownlatch = new CountDownLatch(1);
            al = new long[1];
            view.post(new Runnable(countdownlatch, viewoperation, al) {

                public void run()
                {
                    Object aobj[] = operation.pre();
                    long l = Debug.threadCpuTimeNanos();
                    operation.run(aobj);
                    duration[0] = Debug.threadCpuTimeNanos() - l;
                    operation.post(aobj);
                    latch.countDown();
                    return;
                    Exception exception;
                    exception;
                    latch.countDown();
                    throw exception;
                }

                final long val$duration[];
                final CountDownLatch val$latch;
                final ViewOperation val$operation;

            
            {
                latch = countdownlatch;
                operation = viewoperation;
                duration = al;
                super();
            }
            }
);
            try
            {
                if(countdownlatch.await(4000L, TimeUnit.MILLISECONDS))
                    break label0;
                viewoperation = JVM INSTR new #387 <Class StringBuilder>;
                viewoperation.StringBuilder();
                Log.w("View", viewoperation.append("Could not complete the profiling of the view ").append(view).toString());
            }
            // Misplaced declaration of an exception variable
            catch(ViewOperation viewoperation)
            {
                Log.w("View", (new StringBuilder()).append("Could not complete the profiling of the view ").append(view).toString());
                Thread.currentThread().interrupt();
                return -1L;
            }
            return -1L;
        }
        return al[0];
    }

    private static void requestLayout(View view, String s)
    {
        s = findView(view, s);
        if(s != null)
            view.post(new Runnable(s) {

                public void run()
                {
                    view.requestLayout();
                }

                final View val$view;

            
            {
                view = view1;
                super();
            }
            }
);
    }

    static Object resolveId(Context context, int i)
    {
        context = context.getResources();
        if(i >= 0)
            try
            {
                StringBuilder stringbuilder = JVM INSTR new #387 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                context = stringbuilder.append(context.getResourceTypeName(i)).append('/').append(context.getResourceEntryName(i)).toString();
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context = (new StringBuilder()).append("id/").append(formatIntToHexString(i)).toString();
            }
        else
            context = "NO_ID";
        return context;
    }

    public static void setLayoutParameter(View view, String s, int i)
        throws NoSuchFieldException, IllegalAccessException
    {
        ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        Field field = layoutparams.getClass().getField(s);
        if(field.getType() != Integer.TYPE)
        {
            throw new RuntimeException((new StringBuilder()).append("Only integer layout parameters can be set. Field ").append(s).append(" is of type ").append(field.getType().getSimpleName()).toString());
        } else
        {
            field.set(layoutparams, Integer.valueOf(i));
            view.post(new Runnable(view, layoutparams) {

                public void run()
                {
                    view.setLayoutParams(p);
                }

                final ViewGroup.LayoutParams val$p;
                final View val$view;

            
            {
                view = view1;
                p = layoutparams;
                super();
            }
            }
);
            return;
        }
    }

    public static void startHierarchyTracing(String s, View view)
    {
    }

    public static void startRecyclerTracing(String s, View view)
    {
    }

    public static void stopHierarchyTracing()
    {
    }

    public static void stopRecyclerTracing()
    {
    }

    public static void trace(View view, HierarchyTraceType hierarchytracetype)
    {
    }

    public static transient void trace(View view, RecyclerTraceType recyclertracetype, int ai[])
    {
    }

    private static void writeEntry(BufferedWriter bufferedwriter, String s, String s1, String s2, Object obj)
        throws IOException
    {
        bufferedwriter.write(s);
        bufferedwriter.write(s1);
        bufferedwriter.write(s2);
        bufferedwriter.write("=");
        writeValue(bufferedwriter, obj);
        bufferedwriter.write(32);
    }

    private static void writeValue(BufferedWriter bufferedwriter, Object obj)
        throws IOException
    {
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = obj.toString().replace("\n", "\\n").replace("\r", "\\r");
        bufferedwriter.write(String.valueOf(((String) (obj)).length()));
        bufferedwriter.write(",");
        bufferedwriter.write(((String) (obj)));
_L4:
        return;
        obj;
        bufferedwriter.write(String.valueOf("[EXCEPTION]".length()));
        bufferedwriter.write(",");
        bufferedwriter.write("[EXCEPTION]");
        throw obj;
_L2:
        bufferedwriter.write("4,null");
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int CAPTURE_TIMEOUT = 4000;
    public static final boolean DEBUG_DRAG = false;
    public static final boolean DEBUG_POSITIONING = false;
    private static final String REMOTE_COMMAND_CAPTURE = "CAPTURE";
    private static final String REMOTE_COMMAND_CAPTURE_LAYERS = "CAPTURE_LAYERS";
    private static final String REMOTE_COMMAND_DUMP = "DUMP";
    private static final String REMOTE_COMMAND_DUMP_THEME = "DUMP_THEME";
    private static final String REMOTE_COMMAND_INVALIDATE = "INVALIDATE";
    private static final String REMOTE_COMMAND_OUTPUT_DISPLAYLIST = "OUTPUT_DISPLAYLIST";
    private static final String REMOTE_COMMAND_REQUEST_LAYOUT = "REQUEST_LAYOUT";
    private static final String REMOTE_PROFILE = "PROFILE";
    public static final boolean TRACE_HIERARCHY = false;
    public static final boolean TRACE_RECYCLER = false;
    private static HashMap mCapturedViewFieldsForClasses = null;
    private static HashMap mCapturedViewMethodsForClasses = null;
    private static HashMap sAnnotations;
    private static HashMap sFieldsForClasses;
    private static HashMap sMethodsForClasses;

}
