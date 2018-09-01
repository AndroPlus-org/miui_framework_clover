// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims;

import android.app.PendingIntent;
import android.os.*;
import android.telephony.ims.feature.IRcsFeature;
import android.util.Log;
import com.android.ims.ImsCallProfile;
import com.android.ims.internal.*;

// Referenced classes of package android.telephony.ims:
//            ImsServiceProxyCompat

public class ImsServiceProxy extends ImsServiceProxyCompat
    implements IRcsFeature
{
    public static interface INotifyStatusChanged
    {

        public abstract void notifyStatusChanged();
    }


    static boolean _2D_get0(ImsServiceProxy imsserviceproxy)
    {
        return imsserviceproxy.mIsAvailable;
    }

    static Object _2D_get1(ImsServiceProxy imsserviceproxy)
    {
        return imsserviceproxy.mLock;
    }

    static INotifyStatusChanged _2D_get2(ImsServiceProxy imsserviceproxy)
    {
        return imsserviceproxy.mStatusCallback;
    }

    static int _2D_get3(ImsServiceProxy imsserviceproxy)
    {
        return imsserviceproxy.mSupportedFeature;
    }

    static Integer _2D_set0(ImsServiceProxy imsserviceproxy, Integer integer)
    {
        imsserviceproxy.mFeatureStatusCached = integer;
        return integer;
    }

    static boolean _2D_set1(ImsServiceProxy imsserviceproxy, boolean flag)
    {
        imsserviceproxy.mIsAvailable = flag;
        return flag;
    }

    public ImsServiceProxy(int i, int j)
    {
        super(i, null);
        LOG_TAG = "ImsServiceProxy";
        mIsAvailable = true;
        mFeatureStatusCached = null;
        mLock = new Object();
        mListenerBinder = new com.android.ims.internal.IImsServiceFeatureListener.Stub() {

            public void imsFeatureCreated(int k, int l)
                throws RemoteException
            {
                Object obj = ImsServiceProxy._2D_get1(ImsServiceProxy.this);
                obj;
                JVM INSTR monitorenter ;
                if(!ImsServiceProxy._2D_get0(ImsServiceProxy.this) && mSlotId == k && l == ImsServiceProxy._2D_get3(ImsServiceProxy.this))
                {
                    String s = LOG_TAG;
                    StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Log.i(s, stringbuilder.append("Feature enabled on slotId: ").append(k).append(" for feature: ").append(l).toString());
                    ImsServiceProxy._2D_set1(ImsServiceProxy.this, true);
                }
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            public void imsFeatureRemoved(int k, int l)
                throws RemoteException
            {
                Object obj = ImsServiceProxy._2D_get1(ImsServiceProxy.this);
                obj;
                JVM INSTR monitorenter ;
                if(ImsServiceProxy._2D_get0(ImsServiceProxy.this) && mSlotId == k && l == ImsServiceProxy._2D_get3(ImsServiceProxy.this))
                {
                    String s = LOG_TAG;
                    StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Log.i(s, stringbuilder.append("Feature disabled on slotId: ").append(k).append(" for feature: ").append(l).toString());
                    ImsServiceProxy._2D_set1(ImsServiceProxy.this, false);
                }
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            public void imsStatusChanged(int k, int l, int i1)
                throws RemoteException
            {
                Object obj = ImsServiceProxy._2D_get1(ImsServiceProxy.this);
                obj;
                JVM INSTR monitorenter ;
                String s = LOG_TAG;
                StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i(s, stringbuilder.append("imsStatusChanged: slot: ").append(k).append(" feature: ").append(l).append(" status: ").append(i1).toString());
                if(mSlotId == k && l == ImsServiceProxy._2D_get3(ImsServiceProxy.this))
                {
                    ImsServiceProxy._2D_set0(ImsServiceProxy.this, Integer.valueOf(i1));
                    if(ImsServiceProxy._2D_get2(ImsServiceProxy.this) != null)
                        ImsServiceProxy._2D_get2(ImsServiceProxy.this).notifyStatusChanged();
                }
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            final ImsServiceProxy this$0;

            
            {
                this$0 = ImsServiceProxy.this;
                super();
            }
        }
;
        mSupportedFeature = j;
    }

    public ImsServiceProxy(int i, IBinder ibinder, int j)
    {
        super(i, ibinder);
        LOG_TAG = "ImsServiceProxy";
        mIsAvailable = true;
        mFeatureStatusCached = null;
        mLock = new Object();
        mListenerBinder = new _cls1();
        mSupportedFeature = j;
    }

    private IImsServiceController getServiceInterface(IBinder ibinder)
    {
        return com.android.ims.internal.IImsServiceController.Stub.asInterface(ibinder);
    }

    private Integer retrieveFeatureStatus()
    {
        if(mBinder == null)
            break MISSING_BLOCK_LABEL_35;
        int i = getServiceInterface(mBinder).getFeatureStatus(mSlotId, mSupportedFeature);
        return Integer.valueOf(i);
        RemoteException remoteexception;
        remoteexception;
        return null;
    }

    public void addRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        getServiceInterface(mBinder).addRegistrationListener(mSlotId, mSupportedFeature, iimsregistrationlistener);
        obj;
        JVM INSTR monitorexit ;
        return;
        iimsregistrationlistener;
        throw iimsregistrationlistener;
    }

    protected void checkServiceIsReady()
        throws RemoteException
    {
        if(!isBinderReady())
            throw new RemoteException("ImsServiceProxy is not ready to accept commands.");
        else
            return;
    }

    public ImsCallProfile createCallProfile(int i, int j, int k)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ImsCallProfile imscallprofile;
        checkServiceIsReady();
        imscallprofile = getServiceInterface(mBinder).createCallProfile(mSlotId, mSupportedFeature, i, j, k);
        obj;
        JVM INSTR monitorexit ;
        return imscallprofile;
        Exception exception;
        exception;
        throw exception;
    }

    public IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        imscallprofile = getServiceInterface(mBinder).createCallSession(mSlotId, mSupportedFeature, i, imscallprofile, iimscallsessionlistener);
        obj;
        JVM INSTR monitorexit ;
        return imscallprofile;
        imscallprofile;
        throw imscallprofile;
    }

    public void endSession(int i)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkBinderConnection();
        getServiceInterface(mBinder).endSession(mSlotId, mSupportedFeature, i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public IImsConfig getConfigInterface()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IImsConfig iimsconfig;
        checkServiceIsReady();
        iimsconfig = getServiceInterface(mBinder).getConfigInterface(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return iimsconfig;
        Exception exception;
        exception;
        throw exception;
    }

    public IImsEcbm getEcbmInterface()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IImsEcbm iimsecbm;
        checkServiceIsReady();
        iimsecbm = getServiceInterface(mBinder).getEcbmInterface(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return iimsecbm;
        Exception exception;
        exception;
        throw exception;
    }

    public int getFeatureStatus()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        if(!isBinderAlive() || mFeatureStatusCached == null)
            break MISSING_BLOCK_LABEL_69;
        String s = LOG_TAG;
        StringBuilder stringbuilder = JVM INSTR new #152 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i(s, stringbuilder.append("getFeatureStatus - returning cached: ").append(mFeatureStatusCached).toString());
        i = mFeatureStatusCached.intValue();
        obj;
        JVM INSTR monitorexit ;
        return i;
        obj;
        JVM INSTR monitorexit ;
        Object obj1 = retrieveFeatureStatus();
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(obj1 == null)
            return 0;
        break MISSING_BLOCK_LABEL_96;
        obj1;
        throw obj1;
        mFeatureStatusCached = ((Integer) (obj1));
        obj;
        JVM INSTR monitorexit ;
        Log.i(LOG_TAG, (new StringBuilder()).append("getFeatureStatus - returning ").append(obj1).toString());
        return ((Integer) (obj1)).intValue();
        Exception exception;
        exception;
        throw exception;
    }

    public IImsServiceFeatureListener getListener()
    {
        return mListenerBinder;
    }

    public IImsMultiEndpoint getMultiEndpointInterface()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IImsMultiEndpoint iimsmultiendpoint;
        checkServiceIsReady();
        iimsmultiendpoint = getServiceInterface(mBinder).getMultiEndpointInterface(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return iimsmultiendpoint;
        Exception exception;
        exception;
        throw exception;
    }

    public IImsCallSession getPendingCallSession(int i, String s)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        s = getServiceInterface(mBinder).getPendingCallSession(mSlotId, mSupportedFeature, i, s);
        obj;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public IImsUt getUtInterface()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IImsUt iimsut;
        checkServiceIsReady();
        iimsut = getServiceInterface(mBinder).getUtInterface(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return iimsut;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isBinderAlive()
    {
        boolean flag;
        if(mIsAvailable && mBinder != null)
            flag = mBinder.isBinderAlive();
        else
            flag = false;
        return flag;
    }

    public boolean isBinderReady()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isBinderAlive())
        {
            flag1 = flag;
            if(getFeatureStatus() == 2)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isConnected(int i, int j)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        checkServiceIsReady();
        flag = getServiceInterface(mBinder).isConnected(mSlotId, mSupportedFeature, i, j);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isOpened()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        checkServiceIsReady();
        flag = getServiceInterface(mBinder).isOpened(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void removeRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        getServiceInterface(mBinder).removeRegistrationListener(mSlotId, mSupportedFeature, iimsregistrationlistener);
        obj;
        JVM INSTR monitorexit ;
        return;
        iimsregistrationlistener;
        throw iimsregistrationlistener;
    }

    public void setBinder(IBinder ibinder)
    {
        mBinder = ibinder;
    }

    public void setStatusCallback(INotifyStatusChanged inotifystatuschanged)
    {
        mStatusCallback = inotifystatuschanged;
    }

    public void setUiTTYMode(int i, Message message)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        getServiceInterface(mBinder).setUiTTYMode(mSlotId, mSupportedFeature, i, message);
        obj;
        JVM INSTR monitorexit ;
        return;
        message;
        throw message;
    }

    public int startSession(PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkServiceIsReady();
        i = getServiceInterface(mBinder).startSession(mSlotId, mSupportedFeature, pendingintent, iimsregistrationlistener);
        obj;
        JVM INSTR monitorexit ;
        return i;
        pendingintent;
        throw pendingintent;
    }

    public void turnOffIms()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        getServiceInterface(mBinder).turnOffIms(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void turnOnIms()
        throws RemoteException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        checkServiceIsReady();
        getServiceInterface(mBinder).turnOnIms(mSlotId, mSupportedFeature);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected String LOG_TAG;
    private Integer mFeatureStatusCached;
    private boolean mIsAvailable;
    private final IImsServiceFeatureListener mListenerBinder;
    private final Object mLock;
    private INotifyStatusChanged mStatusCallback;
    private final int mSupportedFeature;
}
