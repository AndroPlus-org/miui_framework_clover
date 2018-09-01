// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Surface;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package android.renderscript:
//            RSRuntimeException, RenderScriptCacheDir, RSIllegalArgumentException, RSDriverException, 
//            BaseObj, RSInvalidStateException, Element, ProgramRaster, 
//            ProgramStore, Sampler, Allocation

public class RenderScript
{
    public static final class ContextType extends Enum
    {

        public static ContextType valueOf(String s)
        {
            return (ContextType)Enum.valueOf(android/renderscript/RenderScript$ContextType, s);
        }

        public static ContextType[] values()
        {
            return $VALUES;
        }

        private static final ContextType $VALUES[];
        public static final ContextType DEBUG;
        public static final ContextType NORMAL;
        public static final ContextType PROFILE;
        int mID;

        static 
        {
            NORMAL = new ContextType("NORMAL", 0, 0);
            DEBUG = new ContextType("DEBUG", 1, 1);
            PROFILE = new ContextType("PROFILE", 2, 2);
            $VALUES = (new ContextType[] {
                NORMAL, DEBUG, PROFILE
            });
        }

        private ContextType(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    static class MessageThread extends Thread
    {

        public void run()
        {
            int ai[] = new int[16];
            mRS.nContextInitToClient(mRS.mContext);
            while(mRun) 
            {
                ai[0] = 0;
                int i = mRS.nContextPeekMessage(mRS.mContext, mAuxData);
                int j = mAuxData[1];
                int k = mAuxData[0];
                if(i == 4)
                {
                    int ai1[] = ai;
                    if(j >> 2 >= ai.length)
                        ai1 = new int[j + 3 >> 2];
                    if(mRS.nContextGetUserMessage(mRS.mContext, ai1) != 4)
                        throw new RSDriverException("Error processing message from RenderScript.");
                    if(mRS.mMessageCallback != null)
                    {
                        mRS.mMessageCallback.mData = ai1;
                        mRS.mMessageCallback.mID = k;
                        mRS.mMessageCallback.mLength = j;
                        mRS.mMessageCallback.run();
                        ai = ai1;
                    } else
                    {
                        throw new RSInvalidStateException("Received a message from the script with no message handler installed.");
                    }
                } else
                if(i == 3)
                {
                    String s = mRS.nContextGetErrorMessage(mRS.mContext);
                    if(k >= 4096 || k >= 2048 && (mRS.mContextType != ContextType.DEBUG || mRS.mErrorCallback == null))
                        throw new RSRuntimeException((new StringBuilder()).append("Fatal error ").append(k).append(", details: ").append(s).toString());
                    if(mRS.mErrorCallback != null)
                    {
                        mRS.mErrorCallback.mErrorMessage = s;
                        mRS.mErrorCallback.mErrorNum = k;
                        mRS.mErrorCallback.run();
                    } else
                    {
                        Log.e("RenderScript_jni", (new StringBuilder()).append("non fatal RS error, ").append(s).toString());
                    }
                } else
                if(i == 5)
                {
                    if(mRS.nContextGetUserMessage(mRS.mContext, ai) != 5)
                        throw new RSDriverException("Error processing message from RenderScript.");
                    Allocation.sendBufferNotification(((long)ai[1] << 32) + ((long)ai[0] & 0xffffffffL));
                } else
                {
                    try
                    {
                        sleep(1L, 0);
                    }
                    catch(InterruptedException interruptedexception) { }
                }
            }
        }

        static final int RS_ERROR_FATAL_DEBUG = 2048;
        static final int RS_ERROR_FATAL_UNKNOWN = 4096;
        static final int RS_MESSAGE_TO_CLIENT_ERROR = 3;
        static final int RS_MESSAGE_TO_CLIENT_EXCEPTION = 1;
        static final int RS_MESSAGE_TO_CLIENT_NEW_BUFFER = 5;
        static final int RS_MESSAGE_TO_CLIENT_NONE = 0;
        static final int RS_MESSAGE_TO_CLIENT_RESIZE = 2;
        static final int RS_MESSAGE_TO_CLIENT_USER = 4;
        int mAuxData[];
        RenderScript mRS;
        boolean mRun;

        MessageThread(RenderScript renderscript)
        {
            super("RSMessageThread");
            mRun = true;
            mAuxData = new int[2];
            mRS = renderscript;
        }
    }

    public static final class Priority extends Enum
    {

        public static Priority valueOf(String s)
        {
            return (Priority)Enum.valueOf(android/renderscript/RenderScript$Priority, s);
        }

        public static Priority[] values()
        {
            return $VALUES;
        }

        private static final Priority $VALUES[];
        public static final Priority LOW;
        public static final Priority NORMAL;
        int mID;

        static 
        {
            LOW = new Priority("LOW", 0, 15);
            NORMAL = new Priority("NORMAL", 1, -8);
            $VALUES = (new Priority[] {
                LOW, NORMAL
            });
        }

        private Priority(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static class RSErrorHandler
        implements Runnable
    {

        public void run()
        {
        }

        protected String mErrorMessage;
        protected int mErrorNum;

        public RSErrorHandler()
        {
        }
    }

    public static class RSMessageHandler
        implements Runnable
    {

        public void run()
        {
        }

        protected int mData[];
        protected int mID;
        protected int mLength;

        public RSMessageHandler()
        {
        }
    }


    RenderScript(Context context)
    {
        mIsProcessContext = false;
        mContextFlags = 0;
        mContextSdkVersion = 0;
        mDestroyed = false;
        mMessageCallback = null;
        mErrorCallback = null;
        mContextType = ContextType.NORMAL;
        if(context != null)
        {
            mApplicationContext = context.getApplicationContext();
            mIsSystemPackage = "android".equals(context.getPackageName());
        }
        mRWLock = new ReentrantReadWriteLock();
        try
        {
            registerNativeAllocation.invoke(sRuntime, new Object[] {
                Integer.valueOf(0x400000)
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("RenderScript_jni", (new StringBuilder()).append("Couldn't invoke registerNativeAllocation:").append(context).toString());
        }
        throw new RSRuntimeException((new StringBuilder()).append("Couldn't invoke registerNativeAllocation:").append(context).toString());
    }

    static native void _nInit();

    public static RenderScript create(Context context)
    {
        return create(context, ContextType.NORMAL);
    }

    public static RenderScript create(Context context, int i)
    {
        return create(context, i, ContextType.NORMAL, 0);
    }

    private static RenderScript create(Context context, int i, ContextType contexttype, int j)
    {
        if(i < 23)
            return internalCreate(context, i, contexttype, j);
        ArrayList arraylist = mProcessContextList;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mProcessContextList.iterator();
        RenderScript renderscript;
        int k;
        do
        {
            do
            {
                if(!iterator.hasNext())
                    break MISSING_BLOCK_LABEL_91;
                renderscript = (RenderScript)iterator.next();
            } while(renderscript.mContextType != contexttype || renderscript.mContextFlags != j);
            k = renderscript.mContextSdkVersion;
        } while(k != i);
        arraylist;
        JVM INSTR monitorexit ;
        return renderscript;
        context = internalCreate(context, i, contexttype, j);
        context.mIsProcessContext = true;
        mProcessContextList.add(context);
        arraylist;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public static RenderScript create(Context context, ContextType contexttype)
    {
        return create(context, contexttype, 0);
    }

    public static RenderScript create(Context context, ContextType contexttype, int i)
    {
        return create(context, context.getApplicationInfo().targetSdkVersion, contexttype, i);
    }

    public static RenderScript createMultiContext(Context context, ContextType contexttype, int i, int j)
    {
        return internalCreate(context, j, contexttype, i);
    }

    static String getCachePath()
    {
        android/renderscript/RenderScript;
        JVM INSTR monitorenter ;
        if(mCachePath != null)
            break MISSING_BLOCK_LABEL_68;
        if(RenderScriptCacheDir.mCacheDir == null && mIsSystemPackage ^ true)
        {
            RSRuntimeException rsruntimeexception = JVM INSTR new #255 <Class RSRuntimeException>;
            rsruntimeexception.RSRuntimeException("RenderScript code cache directory uninitialized.");
            throw rsruntimeexception;
        }
        break MISSING_BLOCK_LABEL_42;
        Exception exception;
        exception;
        android/renderscript/RenderScript;
        JVM INSTR monitorexit ;
        throw exception;
        File file = JVM INSTR new #370 <Class File>;
        file.File(RenderScriptCacheDir.mCacheDir, "com.android.renderscript.cache");
        mCachePath = file.getAbsolutePath();
        file.mkdirs();
        String s = mCachePath;
        android/renderscript/RenderScript;
        JVM INSTR monitorexit ;
        return s;
    }

    public static long getMinorID()
    {
        return 1L;
    }

    public static long getMinorVersion()
    {
        return 1L;
    }

    private void helpDestroy()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(mDestroyed)
            break MISSING_BLOCK_LABEL_18;
        flag = true;
        mDestroyed = true;
        this;
        JVM INSTR monitorexit ;
        boolean flag1;
        if(!flag)
            break MISSING_BLOCK_LABEL_105;
        nContextFinish();
        nContextDeinitToClient(mContext);
        mMessageThread.mRun = false;
        mMessageThread.interrupt();
        flag1 = false;
        flag = false;
_L2:
        if(flag1)
            break; /* Loop/switch isn't completed */
        mMessageThread.join();
        flag1 = true;
        continue; /* Loop/switch isn't completed */
        Object obj;
        obj;
        throw obj;
        obj;
        flag = true;
        if(true) goto _L2; else goto _L1
_L1:
        if(flag)
        {
            Log.v("RenderScript_jni", "Interrupted during wait for MessageThread to join");
            Thread.currentThread().interrupt();
        }
        nContextDestroy();
    }

    private static RenderScript internalCreate(Context context, int i, ContextType contexttype, int j)
    {
        if(!sInitialized)
        {
            Log.e("RenderScript_jni", "RenderScript.create() called when disabled; someone is likely to crash");
            return null;
        }
        if((j & 0xfffffff1) != 0)
            throw new RSIllegalArgumentException("Invalid flags passed.");
        context = new RenderScript(context);
        context.mContext = context.nContextCreate(context.nDeviceCreate(), j, i, contexttype.mID);
        context.mContextType = contexttype;
        context.mContextFlags = j;
        context.mContextSdkVersion = i;
        if(((RenderScript) (context)).mContext == 0L)
        {
            throw new RSDriverException("Failed to create RS context.");
        } else
        {
            context.nContextSetCacheDir(getCachePath());
            context.mMessageThread = new MessageThread(context);
            ((RenderScript) (context)).mMessageThread.start();
            return context;
        }
    }

    public static void releaseAllContexts()
    {
        Object obj = mProcessContextList;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = mProcessContextList;
        ArrayList arraylist = JVM INSTR new #165 <Class ArrayList>;
        arraylist.ArrayList();
        mProcessContextList = arraylist;
        obj;
        JVM INSTR monitorexit ;
        for(Iterator iterator = ((Iterable) (obj1)).iterator(); iterator.hasNext(); ((RenderScript) (obj)).destroy())
        {
            obj = (RenderScript)iterator.next();
            obj.mIsProcessContext = false;
        }

        break MISSING_BLOCK_LABEL_67;
        obj1;
        throw obj1;
        ((ArrayList) (obj1)).clear();
        return;
    }

    static native int rsnSystemGetPointerSize();

    public void contextDump()
    {
        validate();
        nContextDump(0);
    }

    public void destroy()
    {
        if(mIsProcessContext)
        {
            return;
        } else
        {
            validate();
            helpDestroy();
            return;
        }
    }

    protected void finalize()
        throws Throwable
    {
        helpDestroy();
        super.finalize();
    }

    public void finish()
    {
        nContextFinish();
    }

    public final Context getApplicationContext()
    {
        return mApplicationContext;
    }

    public RSErrorHandler getErrorHandler()
    {
        return mErrorCallback;
    }

    public RSMessageHandler getMessageHandler()
    {
        return mMessageCallback;
    }

    boolean isAlive()
    {
        boolean flag;
        if(mContext != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    long nAllocationAdapterCreate(long l, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationAdapterCreate(mContext, l, l1);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationAdapterOffset(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationAdapterOffset(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationCopyFromBitmap(long l, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationCopyFromBitmap(mContext, l, bitmap);
        this;
        JVM INSTR monitorexit ;
        return;
        bitmap;
        throw bitmap;
    }

    void nAllocationCopyToBitmap(long l, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationCopyToBitmap(mContext, l, bitmap);
        this;
        JVM INSTR monitorexit ;
        return;
        bitmap;
        throw bitmap;
    }

    long nAllocationCreateBitmapBackedAllocation(long l, int i, Bitmap bitmap, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationCreateBitmapBackedAllocation(mContext, l, i, bitmap, j);
        this;
        JVM INSTR monitorexit ;
        return l;
        bitmap;
        throw bitmap;
    }

    long nAllocationCreateBitmapRef(long l, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationCreateBitmapRef(mContext, l, bitmap);
        this;
        JVM INSTR monitorexit ;
        return l;
        bitmap;
        throw bitmap;
    }

    long nAllocationCreateFromAssetStream(int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnAllocationCreateFromAssetStream(mContext, i, j, k);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nAllocationCreateFromBitmap(long l, int i, Bitmap bitmap, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationCreateFromBitmap(mContext, l, i, bitmap, j);
        this;
        JVM INSTR monitorexit ;
        return l;
        bitmap;
        throw bitmap;
    }

    long nAllocationCreateTyped(long l, int i, int j, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationCreateTyped(mContext, l, i, j, l1);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nAllocationCubeCreateFromBitmap(long l, int i, Bitmap bitmap, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationCubeCreateFromBitmap(mContext, l, i, bitmap, j);
        this;
        JVM INSTR monitorexit ;
        return l;
        bitmap;
        throw bitmap;
    }

    void nAllocationData1D(long l, int i, int j, int k, Object obj, int i1, 
            Element.DataType datatype, int j1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData1D(mContext, l, i, j, k, obj, i1, datatype.mID, j1, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationData2D(long l, int i, int j, int k, int i1, int j1, 
            int k1, long l1, int i2, int j2, int k2, int l2)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData2D(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2, k2, l2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationData2D(long l, int i, int j, int k, int i1, int j1, 
            int k1, Object obj, int l1, Element.DataType datatype, int i2, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData2D(mContext, l, i, j, k, i1, j1, k1, obj, l1, datatype.mID, i2, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationData2D(long l, int i, int j, int k, int i1, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData2D(mContext, l, i, j, k, i1, bitmap);
        this;
        JVM INSTR monitorexit ;
        return;
        bitmap;
        throw bitmap;
    }

    void nAllocationData3D(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, long l2, int i2, int j2, int k2, 
            int i3)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData3D(mContext, l, i, j, k, i1, j1, k1, l1, l2, i2, j2, k2, i3);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationData3D(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, Object obj, int i2, Element.DataType datatype, int j2, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationData3D(mContext, l, i, j, k, i1, j1, k1, l1, obj, i2, datatype.mID, j2, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationElementData(long l, int i, int j, int k, int i1, int j1, 
            byte abyte0[], int k1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationElementData(mContext, l, i, j, k, i1, j1, abyte0, k1);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    void nAllocationElementRead(long l, int i, int j, int k, int i1, int j1, 
            byte abyte0[], int k1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationElementRead(mContext, l, i, j, k, i1, j1, abyte0, k1);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    void nAllocationGenerateMipmaps(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationGenerateMipmaps(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    ByteBuffer nAllocationGetByteBuffer(long l, long al[], int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        al = rsnAllocationGetByteBuffer(mContext, l, al, i, j, k);
        this;
        JVM INSTR monitorexit ;
        return al;
        al;
        throw al;
    }

    Surface nAllocationGetSurface(long l)
    {
        this;
        JVM INSTR monitorenter ;
        Surface surface;
        validate();
        surface = rsnAllocationGetSurface(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return surface;
        Exception exception;
        exception;
        throw exception;
    }

    long nAllocationGetType(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationGetType(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nAllocationIoReceive(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnAllocationIoReceive(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationIoSend(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationIoSend(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationRead(long l, Object obj, Element.DataType datatype, int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationRead(mContext, l, obj, datatype.mID, i, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationRead1D(long l, int i, int j, int k, Object obj, int i1, 
            Element.DataType datatype, int j1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationRead1D(mContext, l, i, j, k, obj, i1, datatype.mID, j1, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationRead2D(long l, int i, int j, int k, int i1, int j1, 
            int k1, Object obj, int l1, Element.DataType datatype, int i2, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationRead2D(mContext, l, i, j, k, i1, j1, k1, obj, l1, datatype.mID, i2, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationRead3D(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, Object obj, int i2, Element.DataType datatype, int j2, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationRead3D(mContext, l, i, j, k, i1, j1, k1, l1, obj, i2, datatype.mID, j2, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    void nAllocationResize1D(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationResize1D(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationSetSurface(long l, Surface surface)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationSetSurface(mContext, l, surface);
        this;
        JVM INSTR monitorexit ;
        return;
        surface;
        throw surface;
    }

    void nAllocationSetupBufferQueue(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationSetupBufferQueue(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationShareBufferQueue(long l, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationShareBufferQueue(mContext, l, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAllocationSyncAll(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAllocationSyncAll(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nAssignName(long l, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnAssignName(mContext, l, abyte0);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    long nClosureCreate(long l, long l1, long al[], long al1[], int ai[], 
            long al2[], long al3[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnClosureCreate(mContext, l, l1, al, al1, ai, al2, al3);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_56;
        al = JVM INSTR new #255 <Class RSRuntimeException>;
        al.RSRuntimeException("Failed creating closure.");
        throw al;
        al;
        this;
        JVM INSTR monitorexit ;
        throw al;
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    void nClosureSetArg(long l, int i, long l1, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnClosureSetArg(mContext, l, i, l1, j);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nClosureSetGlobal(long l, long l1, long l2, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnClosureSetGlobal(mContext, l, l1, l2, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindProgramFragment(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindProgramFragment(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindProgramRaster(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindProgramRaster(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindProgramStore(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindProgramStore(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindProgramVertex(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindProgramVertex(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindRootScript(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindRootScript(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextBindSampler(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextBindSampler(mContext, i, j);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nContextCreate(long l, int i, int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        l = rsnContextCreate(l, i, j, k);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nContextCreateGL(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, int k2, int l2, int i3, 
            float f, int j3)
    {
        this;
        JVM INSTR monitorenter ;
        l = rsnContextCreateGL(l, i, j, k, i1, j1, k1, l1, i2, j2, k2, l2, i3, f, j3);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    native void nContextDeinitToClient(long l);

    void nContextDestroy()
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock writelock = mRWLock.writeLock();
        writelock.lock();
        long l = mContext;
        mContext = 0L;
        writelock.unlock();
        rsnContextDestroy(l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextDump(int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextDump(mContext, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextFinish()
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextFinish(mContext);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    native String nContextGetErrorMessage(long l);

    native int nContextGetUserMessage(long l, int ai[]);

    native void nContextInitToClient(long l);

    void nContextPause()
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextPause(mContext);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    native int nContextPeekMessage(long l, int ai[]);

    void nContextResume()
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextResume(mContext);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextSendMessage(int i, int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextSendMessage(mContext, i, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        ai;
        throw ai;
    }

    void nContextSetCacheDir(String s)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextSetCacheDir(mContext, s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    void nContextSetPriority(int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextSetPriority(mContext, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nContextSetSurface(int i, int j, Surface surface)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextSetSurface(mContext, i, j, surface);
        this;
        JVM INSTR monitorexit ;
        return;
        surface;
        throw surface;
    }

    void nContextSetSurfaceTexture(int i, int j, SurfaceTexture surfacetexture)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnContextSetSurfaceTexture(mContext, i, j, surfacetexture);
        this;
        JVM INSTR monitorexit ;
        return;
        surfacetexture;
        throw surfacetexture;
    }

    native long nDeviceCreate();

    native void nDeviceDestroy(long l);

    native void nDeviceSetConfig(long l, int i, int j);

    long nElementCreate(long l, int i, boolean flag, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnElementCreate(mContext, l, i, flag, j);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nElementCreate2(long al[], String as[], int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnElementCreate2(mContext, al, as, ai);
        this;
        JVM INSTR monitorexit ;
        return l;
        al;
        throw al;
    }

    void nElementGetNativeData(long l, int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnElementGetNativeData(mContext, l, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        ai;
        throw ai;
    }

    void nElementGetSubElements(long l, long al[], String as[], int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnElementGetSubElements(mContext, l, al, as, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    long nFileA3DCreateFromAsset(AssetManager assetmanager, String s)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnFileA3DCreateFromAsset(mContext, assetmanager, s);
        this;
        JVM INSTR monitorexit ;
        return l;
        assetmanager;
        throw assetmanager;
    }

    long nFileA3DCreateFromAssetStream(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnFileA3DCreateFromAssetStream(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nFileA3DCreateFromFile(String s)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnFileA3DCreateFromFile(mContext, s);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    long nFileA3DGetEntryByIndex(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnFileA3DGetEntryByIndex(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nFileA3DGetIndexEntries(long l, int i, int ai[], String as[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnFileA3DGetIndexEntries(mContext, l, i, ai, as);
        this;
        JVM INSTR monitorexit ;
        return;
        ai;
        throw ai;
    }

    int nFileA3DGetNumIndexEntries(long l)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        validate();
        i = rsnFileA3DGetNumIndexEntries(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    long nFontCreateFromAsset(AssetManager assetmanager, String s, float f, int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnFontCreateFromAsset(mContext, assetmanager, s, f, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        assetmanager;
        throw assetmanager;
    }

    long nFontCreateFromAssetStream(String s, float f, int i, long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnFontCreateFromAssetStream(mContext, s, f, i, l);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    long nFontCreateFromFile(String s, float f, int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnFontCreateFromFile(mContext, s, f, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    String nGetName(long l)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        validate();
        s = rsnGetName(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    long nInvokeClosureCreate(long l, byte abyte0[], long al[], long al1[], int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnInvokeClosureCreate(mContext, l, abyte0, al, al1, ai);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_47;
        abyte0 = JVM INSTR new #255 <Class RSRuntimeException>;
        abyte0.RSRuntimeException("Failed creating closure.");
        throw abyte0;
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    long nMeshCreate(long al[], long al1[], int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnMeshCreate(mContext, al, al1, ai);
        this;
        JVM INSTR monitorexit ;
        return l;
        al;
        throw al;
    }

    int nMeshGetIndexCount(long l)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        validate();
        i = rsnMeshGetIndexCount(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    void nMeshGetIndices(long l, long al[], int ai[], int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnMeshGetIndices(mContext, l, al, ai, i);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    int nMeshGetVertexBufferCount(long l)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        validate();
        i = rsnMeshGetVertexBufferCount(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    void nMeshGetVertices(long l, long al[], int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnMeshGetVertices(mContext, l, al, i);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    void nObjDestroy(long l)
    {
        if(mContext != 0L)
            rsnObjDestroy(mContext, l);
    }

    void nProgramBindConstants(long l, int i, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnProgramBindConstants(mContext, l, i, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nProgramBindSampler(long l, int i, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnProgramBindSampler(mContext, l, i, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nProgramBindTexture(long l, int i, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnProgramBindTexture(mContext, l, i, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nProgramFragmentCreate(String s, String as[], long al[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnProgramFragmentCreate(mContext, s, as, al);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    long nProgramRasterCreate(boolean flag, int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnProgramRasterCreate(mContext, flag, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nProgramStoreCreate(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, int i, 
            int j, int k)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnProgramStoreCreate(mContext, flag, flag1, flag2, flag3, flag4, flag5, i, j, k);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    long nProgramVertexCreate(String s, String as[], long al[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnProgramVertexCreate(mContext, s, as, al);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    long nSamplerCreate(int i, int j, int k, int l, int i1, float f)
    {
        this;
        JVM INSTR monitorenter ;
        long l1;
        validate();
        l1 = rsnSamplerCreate(mContext, i, j, k, l, i1, f);
        this;
        JVM INSTR monitorexit ;
        return l1;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptBindAllocation(long l, long l1, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptBindAllocation(mContext, l, l1, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nScriptCCreate(String s, String s1, byte abyte0[], int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnScriptCCreate(mContext, s, s1, abyte0, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    long nScriptFieldIDCreate(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnScriptFieldIDCreate(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptForEach(long l, int i, long al[], long l1, byte abyte0[], 
            int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptForEach(mContext, l, i, al, l1, abyte0, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    double nScriptGetVarD(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        double d;
        validate();
        d = rsnScriptGetVarD(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return d;
        Exception exception;
        exception;
        throw exception;
    }

    float nScriptGetVarF(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        float f;
        validate();
        f = rsnScriptGetVarF(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return f;
        Exception exception;
        exception;
        throw exception;
    }

    int nScriptGetVarI(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        i = rsnScriptGetVarI(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    long nScriptGetVarJ(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnScriptGetVarJ(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptGetVarV(long l, int i, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptGetVarV(mContext, l, i, abyte0);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    long nScriptGroup2Create(String s, String s1, long al[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnScriptGroup2Create(mContext, s, s1, al);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_44;
        s = JVM INSTR new #255 <Class RSRuntimeException>;
        s.RSRuntimeException("Failed creating script group.");
        throw s;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    void nScriptGroup2Execute(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptGroup2Execute(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nScriptGroupCreate(long al[], long al1[], long al2[], long al3[], long al4[])
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        validate();
        l = rsnScriptGroupCreate(mContext, al, al1, al2, al3, al4);
        this;
        JVM INSTR monitorexit ;
        return l;
        al;
        throw al;
    }

    void nScriptGroupExecute(long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptGroupExecute(mContext, l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptGroupSetInput(long l, long l1, long l2)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptGroupSetInput(mContext, l, l1, l2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptGroupSetOutput(long l, long l1, long l2)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptGroupSetOutput(mContext, l, l1, l2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptIntrinsicBLAS_BNNM(long l, int i, int j, int k, long l1, 
            int i1, long l2, int j1, long l3, int k1, 
            int i2)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptIntrinsicBLAS_BNNM(mContext, l, i, j, k, l1, i1, l2, j1, l3, k1, i2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptIntrinsicBLAS_Complex(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, float f, float f1, long l2, long l3, float f2, float f3, long l4, 
            int k2, int i3, int j3, int k3)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptIntrinsicBLAS_Complex(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2, f, f1, l2, l3, f2, f3, l4, k2, i3, j3, k3);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptIntrinsicBLAS_Double(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, double d, long l2, long l3, double d1, long l4, 
            int k2, int i3, int j3, int k3)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptIntrinsicBLAS_Double(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2, d, l2, l3, d1, l4, k2, i3, j3, k3);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptIntrinsicBLAS_Single(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, float f, long l2, 
            long l3, float f1, long l4, int k2, int i3, 
            int j3, int k3)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptIntrinsicBLAS_Single(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2, f, l2, l3, f1, l4, k2, i3, j3, k3);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptIntrinsicBLAS_Z(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, double d, double d1, long l2, long l3, double d2, 
            double d3, long l4, int k2, int i3, int j3, 
            int k3)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptIntrinsicBLAS_Z(mContext, l, i, j, k, i1, j1, k1, l1, i2, j2, d, d1, l2, l3, d2, d3, l4, k2, i3, j3, k3);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nScriptIntrinsicCreate(int i, long l)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnScriptIntrinsicCreate(mContext, i, l);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptInvoke(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptInvoke(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    long nScriptInvokeIDCreate(long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnScriptInvokeIDCreate(mContext, l, i);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptInvokeV(long l, int i, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptInvokeV(mContext, l, i, abyte0);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    long nScriptKernelIDCreate(long l, int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnScriptKernelIDCreate(mContext, l, i, j);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptReduce(long l, int i, long al[], long l1, int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptReduce(mContext, l, i, al, l1, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    void nScriptSetTimeZone(long l, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetTimeZone(mContext, l, abyte0);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    void nScriptSetVarD(long l, int i, double d)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarD(mContext, l, i, d);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptSetVarF(long l, int i, float f)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarF(mContext, l, i, f);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptSetVarI(long l, int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarI(mContext, l, i, j);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptSetVarJ(long l, int i, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarJ(mContext, l, i, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptSetVarObj(long l, int i, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarObj(mContext, l, i, l1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void nScriptSetVarV(long l, int i, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarV(mContext, l, i, abyte0);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    void nScriptSetVarVE(long l, int i, byte abyte0[], long l1, int ai[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnScriptSetVarVE(mContext, l, i, abyte0, l1, ai);
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    long nTypeCreate(long l, int i, int j, int k, boolean flag, boolean flag1, 
            int i1)
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        l = rsnTypeCreate(mContext, l, i, j, k, flag, flag1, i1);
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    void nTypeGetNativeData(long l, long al[])
    {
        this;
        JVM INSTR monitorenter ;
        validate();
        rsnTypeGetNativeData(mContext, l, al);
        this;
        JVM INSTR monitorexit ;
        return;
        al;
        throw al;
    }

    native long rsnAllocationAdapterCreate(long l, long l1, long l2);

    native void rsnAllocationAdapterOffset(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2, int k2);

    native void rsnAllocationCopyFromBitmap(long l, long l1, Bitmap bitmap);

    native void rsnAllocationCopyToBitmap(long l, long l1, Bitmap bitmap);

    native long rsnAllocationCreateBitmapBackedAllocation(long l, long l1, int i, Bitmap bitmap, int j);

    native long rsnAllocationCreateBitmapRef(long l, long l1, Bitmap bitmap);

    native long rsnAllocationCreateFromAssetStream(long l, int i, int j, int k);

    native long rsnAllocationCreateFromBitmap(long l, long l1, int i, Bitmap bitmap, int j);

    native long rsnAllocationCreateTyped(long l, long l1, int i, int j, long l2);

    native long rsnAllocationCubeCreateFromBitmap(long l, long l1, int i, Bitmap bitmap, int j);

    native void rsnAllocationData1D(long l, long l1, int i, int j, int k, 
            Object obj, int i1, int j1, int k1, boolean flag);

    native void rsnAllocationData2D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, long l2, int i2, int j2, 
            int k2, int i3);

    native void rsnAllocationData2D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, Object obj, int i2, int j2, int k2, 
            boolean flag);

    native void rsnAllocationData2D(long l, long l1, int i, int j, int k, 
            int i1, Bitmap bitmap);

    native void rsnAllocationData3D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, long l2, int j2, 
            int k2, int i3, int j3);

    native void rsnAllocationData3D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, Object obj, int j2, int k2, 
            int l2, boolean flag);

    native void rsnAllocationElementData(long l, long l1, int i, int j, int k, 
            int i1, int j1, byte abyte0[], int k1);

    native void rsnAllocationElementRead(long l, long l1, int i, int j, int k, 
            int i1, int j1, byte abyte0[], int k1);

    native void rsnAllocationGenerateMipmaps(long l, long l1);

    native ByteBuffer rsnAllocationGetByteBuffer(long l, long l1, long al[], int i, int j, 
            int k);

    native Surface rsnAllocationGetSurface(long l, long l1);

    native long rsnAllocationGetType(long l, long l1);

    native long rsnAllocationIoReceive(long l, long l1);

    native void rsnAllocationIoSend(long l, long l1);

    native void rsnAllocationRead(long l, long l1, Object obj, int i, int j, 
            boolean flag);

    native void rsnAllocationRead1D(long l, long l1, int i, int j, int k, 
            Object obj, int i1, int j1, int k1, boolean flag);

    native void rsnAllocationRead2D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, Object obj, int i2, int j2, int k2, 
            boolean flag);

    native void rsnAllocationRead3D(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, Object obj, int j2, int k2, 
            int l2, boolean flag);

    native void rsnAllocationResize1D(long l, long l1, int i);

    native void rsnAllocationSetSurface(long l, long l1, Surface surface);

    native void rsnAllocationSetupBufferQueue(long l, long l1, int i);

    native void rsnAllocationShareBufferQueue(long l, long l1, long l2);

    native void rsnAllocationSyncAll(long l, long l1, int i);

    native void rsnAssignName(long l, long l1, byte abyte0[]);

    native long rsnClosureCreate(long l, long l1, long l2, long al[], 
            long al1[], int ai[], long al2[], long al3[]);

    native void rsnClosureSetArg(long l, long l1, int i, long l2, 
            int j);

    native void rsnClosureSetGlobal(long l, long l1, long l2, long l3, int i);

    native void rsnContextBindProgramFragment(long l, long l1);

    native void rsnContextBindProgramRaster(long l, long l1);

    native void rsnContextBindProgramStore(long l, long l1);

    native void rsnContextBindProgramVertex(long l, long l1);

    native void rsnContextBindRootScript(long l, long l1);

    native void rsnContextBindSampler(long l, int i, int j);

    native long rsnContextCreate(long l, int i, int j, int k);

    native long rsnContextCreateGL(long l, int i, int j, int k, int i1, int j1, 
            int k1, int l1, int i2, int j2, int k2, int l2, int i3, 
            float f, int j3);

    native void rsnContextDestroy(long l);

    native void rsnContextDump(long l, int i);

    native void rsnContextFinish(long l);

    native void rsnContextPause(long l);

    native void rsnContextResume(long l);

    native void rsnContextSendMessage(long l, int i, int ai[]);

    native void rsnContextSetCacheDir(long l, String s);

    native void rsnContextSetPriority(long l, int i);

    native void rsnContextSetSurface(long l, int i, int j, Surface surface);

    native void rsnContextSetSurfaceTexture(long l, int i, int j, SurfaceTexture surfacetexture);

    native long rsnElementCreate(long l, long l1, int i, boolean flag, int j);

    native long rsnElementCreate2(long l, long al[], String as[], int ai[]);

    native void rsnElementGetNativeData(long l, long l1, int ai[]);

    native void rsnElementGetSubElements(long l, long l1, long al[], String as[], int ai[]);

    native long rsnFileA3DCreateFromAsset(long l, AssetManager assetmanager, String s);

    native long rsnFileA3DCreateFromAssetStream(long l, long l1);

    native long rsnFileA3DCreateFromFile(long l, String s);

    native long rsnFileA3DGetEntryByIndex(long l, long l1, int i);

    native void rsnFileA3DGetIndexEntries(long l, long l1, int i, int ai[], String as[]);

    native int rsnFileA3DGetNumIndexEntries(long l, long l1);

    native long rsnFontCreateFromAsset(long l, AssetManager assetmanager, String s, float f, int i);

    native long rsnFontCreateFromAssetStream(long l, String s, float f, int i, long l1);

    native long rsnFontCreateFromFile(long l, String s, float f, int i);

    native String rsnGetName(long l, long l1);

    native long rsnInvokeClosureCreate(long l, long l1, byte abyte0[], long al[], long al1[], 
            int ai[]);

    native long rsnMeshCreate(long l, long al[], long al1[], int ai[]);

    native int rsnMeshGetIndexCount(long l, long l1);

    native void rsnMeshGetIndices(long l, long l1, long al[], int ai[], int i);

    native int rsnMeshGetVertexBufferCount(long l, long l1);

    native void rsnMeshGetVertices(long l, long l1, long al[], int i);

    native void rsnObjDestroy(long l, long l1);

    native void rsnProgramBindConstants(long l, long l1, int i, long l2);

    native void rsnProgramBindSampler(long l, long l1, int i, long l2);

    native void rsnProgramBindTexture(long l, long l1, int i, long l2);

    native long rsnProgramFragmentCreate(long l, String s, String as[], long al[]);

    native long rsnProgramRasterCreate(long l, boolean flag, int i);

    native long rsnProgramStoreCreate(long l, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, 
            boolean flag5, int i, int j, int k);

    native long rsnProgramVertexCreate(long l, String s, String as[], long al[]);

    native long rsnSamplerCreate(long l, int i, int j, int k, int i1, int j1, 
            float f);

    native void rsnScriptBindAllocation(long l, long l1, long l2, int i);

    native long rsnScriptCCreate(long l, String s, String s1, byte abyte0[], int i);

    native long rsnScriptFieldIDCreate(long l, long l1, int i);

    native void rsnScriptForEach(long l, long l1, int i, long al[], long l2, byte abyte0[], int ai[]);

    native double rsnScriptGetVarD(long l, long l1, int i);

    native float rsnScriptGetVarF(long l, long l1, int i);

    native int rsnScriptGetVarI(long l, long l1, int i);

    native long rsnScriptGetVarJ(long l, long l1, int i);

    native void rsnScriptGetVarV(long l, long l1, int i, byte abyte0[]);

    native long rsnScriptGroup2Create(long l, String s, String s1, long al[]);

    native void rsnScriptGroup2Execute(long l, long l1);

    native long rsnScriptGroupCreate(long l, long al[], long al1[], long al2[], long al3[], long al4[]);

    native void rsnScriptGroupExecute(long l, long l1);

    native void rsnScriptGroupSetInput(long l, long l1, long l2, long l3);

    native void rsnScriptGroupSetOutput(long l, long l1, long l2, long l3);

    native void rsnScriptIntrinsicBLAS_BNNM(long l, long l1, int i, int j, int k, 
            long l2, int i1, long l3, int j1, long l4, int k1, int i2);

    native void rsnScriptIntrinsicBLAS_Complex(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2, int k2, float f, 
            float f1, long l2, long l3, float f2, float f3, 
            long l4, int i3, int j3, int k3, int i4);

    native void rsnScriptIntrinsicBLAS_Double(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2, int k2, double d, long l2, long l3, double d1, 
            long l4, int i3, int j3, int k3, int i4);

    native void rsnScriptIntrinsicBLAS_Single(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2, int k2, float f, 
            long l2, long l3, float f1, long l4, 
            int i3, int j3, int k3, int i4);

    native void rsnScriptIntrinsicBLAS_Z(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2, int k2, double d, double d1, long l2, long l3, 
            double d2, double d3, long l4, int i3, 
            int j3, int k3, int i4);

    native long rsnScriptIntrinsicCreate(long l, int i, long l1);

    native void rsnScriptInvoke(long l, long l1, int i);

    native long rsnScriptInvokeIDCreate(long l, long l1, int i);

    native void rsnScriptInvokeV(long l, long l1, int i, byte abyte0[]);

    native long rsnScriptKernelIDCreate(long l, long l1, int i, int j);

    native void rsnScriptReduce(long l, long l1, int i, long al[], long l2, int ai[]);

    native void rsnScriptSetTimeZone(long l, long l1, byte abyte0[]);

    native void rsnScriptSetVarD(long l, long l1, int i, double d);

    native void rsnScriptSetVarF(long l, long l1, int i, float f);

    native void rsnScriptSetVarI(long l, long l1, int i, int j);

    native void rsnScriptSetVarJ(long l, long l1, int i, long l2);

    native void rsnScriptSetVarObj(long l, long l1, int i, long l2);

    native void rsnScriptSetVarV(long l, long l1, int i, byte abyte0[]);

    native void rsnScriptSetVarVE(long l, long l1, int i, byte abyte0[], long l2, int ai[]);

    native long rsnTypeCreate(long l, long l1, int i, int j, int k, 
            boolean flag, boolean flag1, int i1);

    native void rsnTypeGetNativeData(long l, long l1, long al[]);

    long safeID(BaseObj baseobj)
    {
        if(baseobj != null)
            return baseobj.getID(this);
        else
            return 0L;
    }

    public void sendMessage(int i, int ai[])
    {
        nContextSendMessage(i, ai);
    }

    public void setErrorHandler(RSErrorHandler rserrorhandler)
    {
        mErrorCallback = rserrorhandler;
    }

    public void setMessageHandler(RSMessageHandler rsmessagehandler)
    {
        mMessageCallback = rsmessagehandler;
    }

    public void setPriority(Priority priority)
    {
        validate();
        nContextSetPriority(priority.mID);
    }

    void validate()
    {
        if(mContext == 0L)
            throw new RSInvalidStateException("Calling RS with no Context active.");
        else
            return;
    }

    void validateObject(BaseObj baseobj)
    {
        if(baseobj != null && baseobj.mRS != this)
            throw new RSIllegalArgumentException("Attempting to use an object across contexts.");
        else
            return;
    }

    public static final int CREATE_FLAG_LOW_LATENCY = 2;
    public static final int CREATE_FLAG_LOW_POWER = 4;
    public static final int CREATE_FLAG_NONE = 0;
    public static final int CREATE_FLAG_WAIT_FOR_ATTACH = 8;
    static final boolean DEBUG = false;
    static final boolean LOG_ENABLED = false;
    static final String LOG_TAG = "RenderScript_jni";
    static final long TRACE_TAG = 32768L;
    private static String mCachePath;
    static boolean mIsSystemPackage = false;
    private static ArrayList mProcessContextList;
    static Method registerNativeAllocation;
    static Method registerNativeFree;
    static boolean sInitialized = false;
    static final long sMinorVersion = 1L;
    static int sPointerSize;
    static Object sRuntime;
    private Context mApplicationContext;
    long mContext;
    private int mContextFlags;
    private int mContextSdkVersion;
    ContextType mContextType;
    private boolean mDestroyed;
    volatile Element mElement_ALLOCATION;
    volatile Element mElement_A_8;
    volatile Element mElement_BOOLEAN;
    volatile Element mElement_CHAR_2;
    volatile Element mElement_CHAR_3;
    volatile Element mElement_CHAR_4;
    volatile Element mElement_DOUBLE_2;
    volatile Element mElement_DOUBLE_3;
    volatile Element mElement_DOUBLE_4;
    volatile Element mElement_ELEMENT;
    volatile Element mElement_F16;
    volatile Element mElement_F32;
    volatile Element mElement_F64;
    volatile Element mElement_FLOAT_2;
    volatile Element mElement_FLOAT_3;
    volatile Element mElement_FLOAT_4;
    volatile Element mElement_FONT;
    volatile Element mElement_HALF_2;
    volatile Element mElement_HALF_3;
    volatile Element mElement_HALF_4;
    volatile Element mElement_I16;
    volatile Element mElement_I32;
    volatile Element mElement_I64;
    volatile Element mElement_I8;
    volatile Element mElement_INT_2;
    volatile Element mElement_INT_3;
    volatile Element mElement_INT_4;
    volatile Element mElement_LONG_2;
    volatile Element mElement_LONG_3;
    volatile Element mElement_LONG_4;
    volatile Element mElement_MATRIX_2X2;
    volatile Element mElement_MATRIX_3X3;
    volatile Element mElement_MATRIX_4X4;
    volatile Element mElement_MESH;
    volatile Element mElement_PROGRAM_FRAGMENT;
    volatile Element mElement_PROGRAM_RASTER;
    volatile Element mElement_PROGRAM_STORE;
    volatile Element mElement_PROGRAM_VERTEX;
    volatile Element mElement_RGBA_4444;
    volatile Element mElement_RGBA_5551;
    volatile Element mElement_RGBA_8888;
    volatile Element mElement_RGB_565;
    volatile Element mElement_RGB_888;
    volatile Element mElement_SAMPLER;
    volatile Element mElement_SCRIPT;
    volatile Element mElement_SHORT_2;
    volatile Element mElement_SHORT_3;
    volatile Element mElement_SHORT_4;
    volatile Element mElement_TYPE;
    volatile Element mElement_U16;
    volatile Element mElement_U32;
    volatile Element mElement_U64;
    volatile Element mElement_U8;
    volatile Element mElement_UCHAR_2;
    volatile Element mElement_UCHAR_3;
    volatile Element mElement_UCHAR_4;
    volatile Element mElement_UINT_2;
    volatile Element mElement_UINT_3;
    volatile Element mElement_UINT_4;
    volatile Element mElement_ULONG_2;
    volatile Element mElement_ULONG_3;
    volatile Element mElement_ULONG_4;
    volatile Element mElement_USHORT_2;
    volatile Element mElement_USHORT_3;
    volatile Element mElement_USHORT_4;
    volatile Element mElement_YUV;
    RSErrorHandler mErrorCallback;
    private boolean mIsProcessContext;
    RSMessageHandler mMessageCallback;
    MessageThread mMessageThread;
    ProgramRaster mProgramRaster_CULL_BACK;
    ProgramRaster mProgramRaster_CULL_FRONT;
    ProgramRaster mProgramRaster_CULL_NONE;
    ProgramStore mProgramStore_BLEND_ALPHA_DEPTH_NO_DEPTH;
    ProgramStore mProgramStore_BLEND_ALPHA_DEPTH_TEST;
    ProgramStore mProgramStore_BLEND_NONE_DEPTH_NO_DEPTH;
    ProgramStore mProgramStore_BLEND_NONE_DEPTH_TEST;
    ReentrantReadWriteLock mRWLock;
    volatile Sampler mSampler_CLAMP_LINEAR;
    volatile Sampler mSampler_CLAMP_LINEAR_MIP_LINEAR;
    volatile Sampler mSampler_CLAMP_NEAREST;
    volatile Sampler mSampler_MIRRORED_REPEAT_LINEAR;
    volatile Sampler mSampler_MIRRORED_REPEAT_LINEAR_MIP_LINEAR;
    volatile Sampler mSampler_MIRRORED_REPEAT_NEAREST;
    volatile Sampler mSampler_WRAP_LINEAR;
    volatile Sampler mSampler_WRAP_LINEAR_MIP_LINEAR;
    volatile Sampler mSampler_WRAP_NEAREST;

    static 
    {
        mProcessContextList = new ArrayList();
        sInitialized = false;
        if(SystemProperties.getBoolean("config.disable_renderscript", false))
            break MISSING_BLOCK_LABEL_106;
        try
        {
            Class class1 = Class.forName("dalvik.system.VMRuntime");
            sRuntime = class1.getDeclaredMethod("getRuntime", new Class[0]).invoke(null, new Object[0]);
            registerNativeAllocation = class1.getDeclaredMethod("registerNativeAllocation", new Class[] {
                Integer.TYPE
            });
            registerNativeFree = class1.getDeclaredMethod("registerNativeFree", new Class[] {
                Integer.TYPE
            });
        }
        catch(Exception exception)
        {
            Log.e("RenderScript_jni", (new StringBuilder()).append("Error loading GC methods: ").append(exception).toString());
            throw new RSRuntimeException((new StringBuilder()).append("Error loading GC methods: ").append(exception).toString());
        }
        System.loadLibrary("rs_jni");
        _nInit();
        sInitialized = true;
        sPointerSize = rsnSystemGetPointerSize();
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        Log.e("RenderScript_jni", (new StringBuilder()).append("Error loading RS jni library: ").append(unsatisfiedlinkerror).toString());
        throw new RSRuntimeException((new StringBuilder()).append("Error loading RS jni library: ").append(unsatisfiedlinkerror).toString());
    }
}
