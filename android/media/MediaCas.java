// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.hardware.cas.V1_0.*;
import android.os.*;
import android.util.Log;
import android.util.Singleton;
import java.util.ArrayList;

// Referenced classes of package android.media:
//            MediaCasException, MediaCasStateException

public final class MediaCas
    implements AutoCloseable
{
    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what == 0)
                MediaCas._2D_get2(MediaCas.this).onEvent(MediaCas.this, message.arg1, message.arg2, MediaCas._2D_wrap0(MediaCas.this, (ArrayList)message.obj));
        }

        private static final int MSG_CAS_EVENT = 0;
        final MediaCas this$0;

        public EventHandler(Looper looper)
        {
            this$0 = MediaCas.this;
            super(looper);
        }
    }

    public static interface EventListener
    {

        public abstract void onEvent(MediaCas mediacas, int i, int j, byte abyte0[]);
    }

    private class OpenSessionCallback
        implements android.hardware.cas.V1_0.ICas.openSessionCallback
    {

        public void onValues(int i, ArrayList arraylist)
        {
            mStatus = i;
            mSession = createFromSessionId(arraylist);
        }

        public Session mSession;
        public int mStatus;
        final MediaCas this$0;

        private OpenSessionCallback()
        {
            this$0 = MediaCas.this;
            super();
        }

        OpenSessionCallback(OpenSessionCallback opensessioncallback)
        {
            this();
        }
    }

    public static class PluginDescriptor
    {

        public String getName()
        {
            return mName;
        }

        public int getSystemId()
        {
            return mCASystemId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("PluginDescriptor {").append(mCASystemId).append(", ").append(mName).append("}").toString();
        }

        private final int mCASystemId;
        private final String mName;

        private PluginDescriptor()
        {
            mCASystemId = 65535;
            mName = null;
        }

        PluginDescriptor(HidlCasPluginDescriptor hidlcasplugindescriptor)
        {
            mCASystemId = hidlcasplugindescriptor.caSystemId;
            mName = hidlcasplugindescriptor.name;
        }
    }

    public final class Session
        implements AutoCloseable
    {

        public void close()
        {
            MediaCas._2D_wrap3(MediaCas.this);
            MediaCasStateException.throwExceptionIfNeeded(MediaCas._2D_get1(MediaCas.this).closeSession(mSessionId));
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            MediaCas._2D_wrap2(MediaCas.this);
              goto _L1
        }

        public void processEcm(byte abyte0[])
            throws MediaCasException
        {
            processEcm(abyte0, 0, abyte0.length);
        }

        public void processEcm(byte abyte0[], int i, int j)
            throws MediaCasException
        {
            MediaCas._2D_wrap3(MediaCas.this);
            MediaCasException.throwExceptionIfNeeded(MediaCas._2D_get1(MediaCas.this).processEcm(mSessionId, MediaCas._2D_wrap1(MediaCas.this, abyte0, i, j)));
_L1:
            return;
            abyte0;
            MediaCas._2D_wrap2(MediaCas.this);
              goto _L1
        }

        public void setPrivateData(byte abyte0[])
            throws MediaCasException
        {
            MediaCas._2D_wrap3(MediaCas.this);
            MediaCasException.throwExceptionIfNeeded(MediaCas._2D_get1(MediaCas.this).setSessionPrivateData(mSessionId, MediaCas._2D_wrap1(MediaCas.this, abyte0, 0, abyte0.length)));
_L1:
            return;
            abyte0;
            MediaCas._2D_wrap2(MediaCas.this);
              goto _L1
        }

        final ArrayList mSessionId;
        final MediaCas this$0;

        Session(ArrayList arraylist)
        {
            this$0 = MediaCas.this;
            super();
            mSessionId = arraylist;
        }
    }


    static EventHandler _2D_get0(MediaCas mediacas)
    {
        return mediacas.mEventHandler;
    }

    static ICas _2D_get1(MediaCas mediacas)
    {
        return mediacas.mICas;
    }

    static EventListener _2D_get2(MediaCas mediacas)
    {
        return mediacas.mListener;
    }

    static byte[] _2D_wrap0(MediaCas mediacas, ArrayList arraylist)
    {
        return mediacas.toBytes(arraylist);
    }

    static ArrayList _2D_wrap1(MediaCas mediacas, byte abyte0[], int i, int j)
    {
        return mediacas.toByteArray(abyte0, i, j);
    }

    static void _2D_wrap2(MediaCas mediacas)
    {
        mediacas.cleanupAndRethrowIllegalState();
    }

    static void _2D_wrap3(MediaCas mediacas)
    {
        mediacas.validateInternalStates();
    }

    public MediaCas(int i)
        throws MediaCasException.UnsupportedCasException
    {
        mBinder = new android.hardware.cas.V1_0.ICasListener.Stub() {

            public void onEvent(int j, int k, ArrayList arraylist)
                throws RemoteException
            {
                MediaCas._2D_get0(MediaCas.this).sendMessage(MediaCas._2D_get0(MediaCas.this).obtainMessage(0, j, k, arraylist));
            }

            final MediaCas this$0;

            
            {
                this$0 = MediaCas.this;
                super();
            }
        }
;
        mICas = getService().createPlugin(i, mBinder);
        if(mICas == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        break MISSING_BLOCK_LABEL_171;
        Exception exception;
        exception;
        StringBuilder stringbuilder = JVM INSTR new #105 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("MediaCas", stringbuilder.append("Failed to create plugin: ").append(exception).toString());
        mICas = null;
        if(mICas == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        break MISSING_BLOCK_LABEL_171;
        Exception exception1;
        exception1;
        if(mICas == null)
            throw new MediaCasException.UnsupportedCasException((new StringBuilder()).append("Unsupported CA_system_id ").append(i).toString());
        else
            throw exception1;
    }

    private void cleanupAndRethrowIllegalState()
    {
        mICas = null;
        throw new IllegalStateException();
    }

    public static PluginDescriptor[] enumeratePlugins()
    {
        Object obj;
        obj = getService();
        if(obj == null)
            break MISSING_BLOCK_LABEL_67;
        obj = ((IMediaCasService) (obj)).enumeratePlugins();
        if(((ArrayList) (obj)).size() == 0)
            return null;
        PluginDescriptor aplugindescriptor[] = new PluginDescriptor[((ArrayList) (obj)).size()];
        int i = 0;
_L2:
        if(i >= aplugindescriptor.length)
            break; /* Loop/switch isn't completed */
        aplugindescriptor[i] = new PluginDescriptor((HidlCasPluginDescriptor)((ArrayList) (obj)).get(i));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return aplugindescriptor;
        RemoteException remoteexception;
        remoteexception;
        return null;
    }

    static IMediaCasService getService()
    {
        return (IMediaCasService)gDefault.get();
    }

    public static boolean isSystemIdSupported(int i)
    {
        IMediaCasService imediacasservice;
        imediacasservice = getService();
        if(imediacasservice == null)
            break MISSING_BLOCK_LABEL_19;
        boolean flag = imediacasservice.isSystemIdSupported(i);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    private ArrayList toByteArray(byte abyte0[])
    {
        if(abyte0 == null)
            return new ArrayList();
        else
            return toByteArray(abyte0, 0, abyte0.length);
    }

    private ArrayList toByteArray(byte abyte0[], int i, int j)
    {
        ArrayList arraylist = new ArrayList(j);
        for(int k = 0; k < j; k++)
            arraylist.add(Byte.valueOf(abyte0[i + k]));

        return arraylist;
    }

    private byte[] toBytes(ArrayList arraylist)
    {
        byte abyte0[] = null;
        if(arraylist != null)
        {
            byte abyte1[] = new byte[arraylist.size()];
            int i = 0;
            do
            {
                abyte0 = abyte1;
                if(i >= abyte1.length)
                    break;
                abyte1[i] = ((Byte)arraylist.get(i)).byteValue();
                i++;
            } while(true);
        }
        return abyte0;
    }

    private void validateInternalStates()
    {
        if(mICas == null)
            throw new IllegalStateException();
        else
            return;
    }

    public void close()
    {
        if(mICas == null)
            break MISSING_BLOCK_LABEL_22;
        Exception exception;
        try
        {
            mICas.release();
        }
        catch(RemoteException remoteexception) { }
        mICas = null;
        return;
        exception;
        mICas = null;
        throw exception;
    }

    Session createFromSessionId(ArrayList arraylist)
    {
        if(arraylist == null || arraylist.size() == 0)
            return null;
        else
            return new Session(arraylist);
    }

    protected void finalize()
    {
        close();
    }

    IHwBinder getBinder()
    {
        validateInternalStates();
        return mICas.asBinder();
    }

    public Session openSession()
        throws MediaCasException
    {
        validateInternalStates();
        Object obj;
        try
        {
            obj = JVM INSTR new #18  <Class MediaCas$OpenSessionCallback>;
            ((OpenSessionCallback) (obj)).this. OpenSessionCallback(null);
            mICas.openSession(((android.hardware.cas.V1_0.ICas.openSessionCallback) (obj)));
            MediaCasException.throwExceptionIfNeeded(((OpenSessionCallback) (obj)).mStatus);
            obj = ((OpenSessionCallback) (obj)).mSession;
        }
        catch(RemoteException remoteexception)
        {
            cleanupAndRethrowIllegalState();
            return null;
        }
        return ((Session) (obj));
    }

    public void processEmm(byte abyte0[])
        throws MediaCasException
    {
        processEmm(abyte0, 0, abyte0.length);
    }

    public void processEmm(byte abyte0[], int i, int j)
        throws MediaCasException
    {
        validateInternalStates();
        MediaCasException.throwExceptionIfNeeded(mICas.processEmm(toByteArray(abyte0, i, j)));
_L1:
        return;
        abyte0;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    public void provision(String s)
        throws MediaCasException
    {
        validateInternalStates();
        MediaCasException.throwExceptionIfNeeded(mICas.provision(s));
_L1:
        return;
        s;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    public void refreshEntitlements(int i, byte abyte0[])
        throws MediaCasException
    {
        validateInternalStates();
        MediaCasException.throwExceptionIfNeeded(mICas.refreshEntitlements(i, toByteArray(abyte0)));
_L1:
        return;
        abyte0;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    public void sendEvent(int i, int j, byte abyte0[])
        throws MediaCasException
    {
        validateInternalStates();
        MediaCasException.throwExceptionIfNeeded(mICas.sendEvent(i, j, toByteArray(abyte0)));
_L1:
        return;
        abyte0;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    public void setEventListener(EventListener eventlistener, Handler handler)
    {
        mListener = eventlistener;
        if(mListener == null)
        {
            mEventHandler = null;
            return;
        }
        if(handler != null)
            handler = handler.getLooper();
        else
            handler = null;
        eventlistener = handler;
        if(handler == null)
        {
            handler = Looper.myLooper();
            eventlistener = handler;
            if(handler == null)
            {
                handler = Looper.getMainLooper();
                eventlistener = handler;
                if(handler == null)
                {
                    if(mHandlerThread == null || mHandlerThread.isAlive() ^ true)
                    {
                        mHandlerThread = new HandlerThread("MediaCasEventThread", -2);
                        mHandlerThread.start();
                    }
                    eventlistener = mHandlerThread.getLooper();
                }
            }
        }
        mEventHandler = new EventHandler(eventlistener);
    }

    public void setPrivateData(byte abyte0[])
        throws MediaCasException
    {
        validateInternalStates();
        MediaCasException.throwExceptionIfNeeded(mICas.setPrivateData(toByteArray(abyte0, 0, abyte0.length)));
_L1:
        return;
        abyte0;
        cleanupAndRethrowIllegalState();
          goto _L1
    }

    private static final String TAG = "MediaCas";
    private static final Singleton gDefault = new Singleton() {

        protected IMediaCasService create()
        {
            IMediaCasService imediacasservice;
            try
            {
                imediacasservice = IMediaCasService.getService();
            }
            catch(RemoteException remoteexception)
            {
                return null;
            }
            return imediacasservice;
        }

        protected volatile Object create()
        {
            return create();
        }

    }
;
    private final android.hardware.cas.V1_0.ICasListener.Stub mBinder;
    private EventHandler mEventHandler;
    private HandlerThread mHandlerThread;
    private ICas mICas;
    private EventListener mListener;

}
