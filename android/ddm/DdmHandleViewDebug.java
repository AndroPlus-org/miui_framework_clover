// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.ddm;

import android.util.Log;
import android.view.*;
import java.io.*;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.*;

public class DdmHandleViewDebug extends ChunkHandler
{

    private DdmHandleViewDebug()
    {
    }

    private Chunk captureLayers(View view)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        Object obj;
        bytearrayoutputstream = new ByteArrayOutputStream(1024);
        obj = new DataOutputStream(bytearrayoutputstream);
        ViewDebug.captureLayers(view, ((DataOutputStream) (obj)));
        try
        {
            ((DataOutputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        view = bytearrayoutputstream.toByteArray();
        return new Chunk(CHUNK_VURT, view, 0, view.length);
        IOException ioexception;
        ioexception;
        view = JVM INSTR new #93  <Class StringBuilder>;
        view.StringBuilder();
        view = createFailChunk(1, view.append("Unexpected error while obtaining view hierarchy: ").append(ioexception.getMessage()).toString());
        try
        {
            ((DataOutputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        return view;
        view;
        try
        {
            ((DataOutputStream) (obj)).close();
        }
        catch(IOException ioexception1) { }
        throw view;
    }

    private Chunk captureView(View view, View view1)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1024);
        try
        {
            ViewDebug.capture(view, bytearrayoutputstream, view1);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return createFailChunk(1, (new StringBuilder()).append("Unexpected error while capturing view: ").append(view.getMessage()).toString());
        }
        view = bytearrayoutputstream.toByteArray();
        return new Chunk(CHUNK_VUOP, view, 0, view.length);
    }

    private Chunk dumpDisplayLists(final View rootView, final View targetView)
    {
        rootView.post(new Runnable() {

            public void run()
            {
                ViewDebug.outputDisplayList(rootView, targetView);
            }

            final DdmHandleViewDebug this$0;
            final View val$rootView;
            final View val$targetView;

            
            {
                this$0 = DdmHandleViewDebug.this;
                rootView = view;
                targetView = view1;
                super();
            }
        }
);
        return null;
    }

    private Chunk dumpHierarchy(View view, ByteBuffer bytebuffer)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        long l;
        long l1;
        if(bytebuffer.getInt() > 0)
            flag = true;
        else
            flag = false;
        if(bytebuffer.getInt() > 0)
            flag1 = true;
        else
            flag1 = false;
        if(bytebuffer.hasRemaining() && bytebuffer.getInt() > 0)
            flag2 = true;
        else
            flag2 = false;
        l = System.currentTimeMillis();
        bytebuffer = new ByteArrayOutputStream(0x200000);
        if(!flag2)
            break MISSING_BLOCK_LABEL_132;
        try
        {
            ViewDebug.dumpv2(view, bytebuffer);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return createFailChunk(1, (new StringBuilder()).append("Unexpected error while obtaining view hierarchy: ").append(view.getMessage()).toString());
        }
        l1 = System.currentTimeMillis();
        Log.d("DdmViewDebug", (new StringBuilder()).append("Time to obtain view hierarchy (ms): ").append(l1 - l).toString());
        view = bytebuffer.toByteArray();
        return new Chunk(CHUNK_VURT, view, 0, view.length);
        ViewDebug.dump(view, flag, flag1, bytebuffer);
        break MISSING_BLOCK_LABEL_61;
    }

    private Chunk dumpTheme(View view)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1024);
        try
        {
            ViewDebug.dumpTheme(view, bytearrayoutputstream);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return createFailChunk(1, (new StringBuilder()).append("Unexpected error while dumping the theme: ").append(view.getMessage()).toString());
        }
        view = bytearrayoutputstream.toByteArray();
        return new Chunk(CHUNK_VURT, view, 0, view.length);
    }

    private View getRootView(ByteBuffer bytebuffer)
    {
        try
        {
            bytebuffer = getString(bytebuffer, bytebuffer.getInt());
            bytebuffer = WindowManagerGlobal.getInstance().getRootView(bytebuffer);
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            return null;
        }
        return bytebuffer;
    }

    private View getTargetView(View view, ByteBuffer bytebuffer)
    {
        try
        {
            bytebuffer = getString(bytebuffer, bytebuffer.getInt());
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return null;
        }
        return ViewDebug.findView(view, bytebuffer);
    }

    private Chunk invokeViewMethod(View view, View view1, ByteBuffer bytebuffer)
    {
        String s = getString(bytebuffer, bytebuffer.getInt());
        if(bytebuffer.hasRemaining()) goto _L2; else goto _L1
_L1:
        Class aclass[];
        aclass = new Class[0];
        view = ((View) (new Object[0]));
_L4:
        int i;
        Class aclass1[];
        Object aobj[];
        int j;
        char c;
        boolean flag;
        try
        {
            bytebuffer = view1.getClass().getMethod(s, aclass);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            Log.e("DdmViewDebug", (new StringBuilder()).append("No such method: ").append(view.getMessage()).toString());
            return createFailChunk(-2, (new StringBuilder()).append("No such method: ").append(view.getMessage()).toString());
        }
        try
        {
            ViewDebug.invokeViewMethod(view1, bytebuffer, view);
        }
        // Misplaced declaration of an exception variable
        catch(ByteBuffer bytebuffer)
        {
            Log.e("DdmViewDebug", (new StringBuilder()).append("Exception while invoking method: ").append(bytebuffer.getCause().getMessage()).toString());
            view1 = bytebuffer.getCause().getMessage();
            view = view1;
            if(view1 == null)
                view = bytebuffer.getCause().toString();
            return createFailChunk(-3, view);
        }
        return null;
_L2:
        i = bytebuffer.getInt();
        aclass1 = new Class[i];
        aobj = new Object[i];
        j = 0;
_L14:
        aclass = aclass1;
        view = ((View) (aobj));
        if(j >= i) goto _L4; else goto _L3
_L3:
        c = bytebuffer.getChar();
        c;
        JVM INSTR lookupswitch 8: default 168
    //                   66: 275
    //                   67: 298
    //                   68: 413
    //                   70: 390
    //                   73: 344
    //                   74: 367
    //                   83: 321
    //                   90: 235;
           goto _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L8:
        break MISSING_BLOCK_LABEL_413;
_L5:
        Log.e("DdmViewDebug", (new StringBuilder()).append("arg ").append(j).append(", unrecognized type: ").append(c).toString());
        return createFailChunk(-2, (new StringBuilder()).append("Unsupported parameter type (").append(c).append(") to invoke view method.").toString());
_L13:
        aclass1[j] = Boolean.TYPE;
        if(bytebuffer.get() == 0)
            flag = false;
        else
            flag = true;
        aobj[j] = Boolean.valueOf(flag);
_L15:
        j++;
          goto _L14
_L6:
        aclass1[j] = Byte.TYPE;
        aobj[j] = Byte.valueOf(bytebuffer.get());
          goto _L15
_L7:
        aclass1[j] = Character.TYPE;
        aobj[j] = Character.valueOf(bytebuffer.getChar());
          goto _L15
_L12:
        aclass1[j] = Short.TYPE;
        aobj[j] = Short.valueOf(bytebuffer.getShort());
          goto _L15
_L10:
        aclass1[j] = Integer.TYPE;
        aobj[j] = Integer.valueOf(bytebuffer.getInt());
          goto _L15
_L11:
        aclass1[j] = Long.TYPE;
        aobj[j] = Long.valueOf(bytebuffer.getLong());
          goto _L15
_L9:
        aclass1[j] = Float.TYPE;
        aobj[j] = Float.valueOf(bytebuffer.getFloat());
          goto _L15
        aclass1[j] = Double.TYPE;
        aobj[j] = Double.valueOf(bytebuffer.getDouble());
          goto _L15
    }

    private Chunk listWindows()
    {
        boolean flag = false;
        String as[] = WindowManagerGlobal.getInstance().getViewRootNames();
        int i = 4;
        int j = as.length;
        for(int k = 0; k < j; k++)
            i = i + 4 + as[k].length() * 2;

        ByteBuffer bytebuffer = ByteBuffer.allocate(i);
        bytebuffer.order(ChunkHandler.CHUNK_ORDER);
        bytebuffer.putInt(as.length);
        i = as.length;
        for(int l = ((flag) ? 1 : 0); l < i; l++)
        {
            String s = as[l];
            bytebuffer.putInt(s.length());
            putString(bytebuffer, s);
        }

        return new Chunk(CHUNK_VULW, bytebuffer);
    }

    private Chunk profileView(View view, View view1)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        bytearrayoutputstream = new ByteArrayOutputStream(32768);
        view = new BufferedWriter(new OutputStreamWriter(bytearrayoutputstream), 32768);
        ViewDebug.profileViewAndChildren(view1, view);
        try
        {
            view.close();
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        view = bytearrayoutputstream.toByteArray();
        return new Chunk(CHUNK_VUOP, view, 0, view.length);
        IOException ioexception;
        ioexception;
        view1 = JVM INSTR new #93  <Class StringBuilder>;
        view1.StringBuilder();
        view1 = createFailChunk(1, view1.append("Unexpected error while profiling view: ").append(ioexception.getMessage()).toString());
        try
        {
            view.close();
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        return view1;
        view1;
        try
        {
            view.close();
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        throw view1;
    }

    public static void register()
    {
        DdmServer.registerHandler(CHUNK_VULW, sInstance);
        DdmServer.registerHandler(CHUNK_VURT, sInstance);
        DdmServer.registerHandler(CHUNK_VUOP, sInstance);
    }

    private Chunk setLayoutParameter(View view, View view1, ByteBuffer bytebuffer)
    {
        view = getString(bytebuffer, bytebuffer.getInt());
        int i = bytebuffer.getInt();
        try
        {
            ViewDebug.setLayoutParameter(view1, view, i);
        }
        // Misplaced declaration of an exception variable
        catch(View view1)
        {
            Log.e("DdmViewDebug", (new StringBuilder()).append("Exception setting layout parameter: ").append(view1).toString());
            return createFailChunk(-3, (new StringBuilder()).append("Error accessing field ").append(view).append(":").append(view1.getMessage()).toString());
        }
        return null;
    }

    public void connected()
    {
    }

    public void disconnected()
    {
    }

    public Chunk handleChunk(Chunk chunk)
    {
        int i = chunk.type;
        if(i == CHUNK_VULW)
            return listWindows();
        ByteBuffer bytebuffer = wrapChunk(chunk);
        int j = bytebuffer.getInt();
        chunk = getRootView(bytebuffer);
        if(chunk == null)
            return createFailChunk(-2, "Invalid View Root");
        if(i == CHUNK_VURT)
        {
            if(j == 1)
                return dumpHierarchy(chunk, bytebuffer);
            if(j == 2)
                return captureLayers(chunk);
            if(j == 3)
                return dumpTheme(chunk);
            else
                return createFailChunk(-1, (new StringBuilder()).append("Unknown view root operation: ").append(j).toString());
        }
        View view = getTargetView(chunk, bytebuffer);
        if(view == null)
            return createFailChunk(-2, "Invalid target view");
        if(i == CHUNK_VUOP)
            switch(j)
            {
            default:
                return createFailChunk(-1, (new StringBuilder()).append("Unknown view operation: ").append(j).toString());

            case 1: // '\001'
                return captureView(chunk, view);

            case 2: // '\002'
                return dumpDisplayLists(chunk, view);

            case 3: // '\003'
                return profileView(chunk, view);

            case 4: // '\004'
                return invokeViewMethod(chunk, view, bytebuffer);

            case 5: // '\005'
                return setLayoutParameter(chunk, view, bytebuffer);
            }
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown packet ").append(ChunkHandler.name(i)).toString());
    }

    private static final int CHUNK_VULW = type("VULW");
    private static final int CHUNK_VUOP = type("VUOP");
    private static final int CHUNK_VURT = type("VURT");
    private static final int ERR_EXCEPTION = -3;
    private static final int ERR_INVALID_OP = -1;
    private static final int ERR_INVALID_PARAM = -2;
    private static final String TAG = "DdmViewDebug";
    private static final int VUOP_CAPTURE_VIEW = 1;
    private static final int VUOP_DUMP_DISPLAYLIST = 2;
    private static final int VUOP_INVOKE_VIEW_METHOD = 4;
    private static final int VUOP_PROFILE_VIEW = 3;
    private static final int VUOP_SET_LAYOUT_PARAMETER = 5;
    private static final int VURT_CAPTURE_LAYERS = 2;
    private static final int VURT_DUMP_HIERARCHY = 1;
    private static final int VURT_DUMP_THEME = 3;
    private static final DdmHandleViewDebug sInstance = new DdmHandleViewDebug();

}
