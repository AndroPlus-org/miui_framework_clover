// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.feature;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.android.ims.internal.IImsFeatureStatusCallback;
import java.util.*;

public abstract class ImsFeature
{
    public static interface INotifyFeatureRemoved
    {

        public abstract void onFeatureRemoved(int i);
    }


    public ImsFeature()
    {
        mRemovedListeners = new ArrayList();
        mState = 0;
        mSlotId = -1;
    }

    static void lambda$_2D_android_telephony_ims_feature_ImsFeature_4367(int i, INotifyFeatureRemoved inotifyfeatureremoved)
    {
        inotifyfeatureremoved.onFeatureRemoved(i);
    }

    private void notifyFeatureState(int i)
    {
        Set set = mStatusCallbacks;
        set;
        JVM INSTR monitorenter ;
        Iterator iterator = mStatusCallbacks.iterator();
_L1:
        IImsFeatureStatusCallback iimsfeaturestatuscallback;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_128;
        iimsfeaturestatuscallback = (IImsFeatureStatusCallback)iterator.next();
        StringBuilder stringbuilder1 = JVM INSTR new #98  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.i("ImsFeature", stringbuilder1.append("notifying ImsFeatureState=").append(i).toString());
        iimsfeaturestatuscallback.notifyImsFeatureStatus(i);
          goto _L1
        RemoteException remoteexception;
        remoteexception;
        iterator.remove();
        StringBuilder stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("ImsFeature", stringbuilder.append("Couldn't notify feature state: ").append(remoteexception.getMessage()).toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
        set;
        JVM INSTR monitorexit ;
        sendImsServiceIntent(i);
        return;
    }

    private void sendImsServiceIntent(int i)
    {
        if(mContext == null || mSlotId == -1)
            return;
        i;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 74
    //                   1 74
    //                   2 87;
           goto _L1 _L2 _L2 _L3
_L1:
        Intent intent = new Intent("com.android.ims.IMS_SERVICE_DOWN");
_L5:
        intent.putExtra("android:phone_id", mSlotId);
        mContext.sendBroadcast(intent);
        return;
_L2:
        intent = new Intent("com.android.ims.IMS_SERVICE_DOWN");
        continue; /* Loop/switch isn't completed */
_L3:
        intent = new Intent("com.android.ims.IMS_SERVICE_UP");
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void addFeatureRemovedListener(INotifyFeatureRemoved inotifyfeatureremoved)
    {
        List list = mRemovedListeners;
        list;
        JVM INSTR monitorenter ;
        mRemovedListeners.add(inotifyfeatureremoved);
        list;
        JVM INSTR monitorexit ;
        return;
        inotifyfeatureremoved;
        throw inotifyfeatureremoved;
    }

    public void addImsFeatureStatusCallback(IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        if(iimsfeaturestatuscallback == null)
            return;
        iimsfeaturestatuscallback.notifyImsFeatureStatus(mState);
        Set set = mStatusCallbacks;
        set;
        JVM INSTR monitorenter ;
        mStatusCallbacks.add(iimsfeaturestatuscallback);
        set;
        JVM INSTR monitorexit ;
_L1:
        return;
        iimsfeaturestatuscallback;
        set;
        JVM INSTR monitorexit ;
        throw iimsfeaturestatuscallback;
        iimsfeaturestatuscallback;
        Log.w("ImsFeature", (new StringBuilder()).append("Couldn't notify feature state: ").append(iimsfeaturestatuscallback.getMessage()).toString());
          goto _L1
    }

    public int getFeatureState()
    {
        return mState;
    }

    public void notifyFeatureRemoved(int i)
    {
        List list = mRemovedListeners;
        list;
        JVM INSTR monitorenter ;
        List list1 = mRemovedListeners;
        _.Lambda.nHF_Dbrg4P2H381tqGPRuFD0u_w nhf_dbrg4p2h381tqgprufd0u_w = JVM INSTR new #168 <Class _$Lambda$nHF_Dbrg4P2H381tqGPRuFD0u_w>;
        nhf_dbrg4p2h381tqgprufd0u_w._.Lambda.nHF_Dbrg4P2H381tqGPRuFD0u_w(i);
        list1.forEach(nhf_dbrg4p2h381tqgprufd0u_w);
        onFeatureRemoved();
        list;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public abstract void onFeatureRemoved();

    public void removeFeatureRemovedListener(INotifyFeatureRemoved inotifyfeatureremoved)
    {
        List list = mRemovedListeners;
        list;
        JVM INSTR monitorenter ;
        mRemovedListeners.remove(inotifyfeatureremoved);
        list;
        JVM INSTR monitorexit ;
        return;
        inotifyfeatureremoved;
        throw inotifyfeatureremoved;
    }

    public void removeImsFeatureStatusCallback(IImsFeatureStatusCallback iimsfeaturestatuscallback)
    {
        if(iimsfeaturestatuscallback == null)
            return;
        Set set = mStatusCallbacks;
        set;
        JVM INSTR monitorenter ;
        mStatusCallbacks.remove(iimsfeaturestatuscallback);
        set;
        JVM INSTR monitorexit ;
        return;
        iimsfeaturestatuscallback;
        throw iimsfeaturestatuscallback;
    }

    public void setContext(Context context)
    {
        mContext = context;
    }

    protected final void setFeatureState(int i)
    {
        if(mState != i)
        {
            mState = i;
            notifyFeatureState(i);
        }
    }

    public void setSlotId(int i)
    {
        mSlotId = i;
    }

    public static final String ACTION_IMS_SERVICE_DOWN = "com.android.ims.IMS_SERVICE_DOWN";
    public static final String ACTION_IMS_SERVICE_UP = "com.android.ims.IMS_SERVICE_UP";
    public static final int EMERGENCY_MMTEL = 0;
    public static final String EXTRA_PHONE_ID = "android:phone_id";
    public static final int INVALID = -1;
    private static final String LOG_TAG = "ImsFeature";
    public static final int MAX = 3;
    public static final int MMTEL = 1;
    public static final int RCS = 2;
    public static final int STATE_INITIALIZING = 1;
    public static final int STATE_NOT_AVAILABLE = 0;
    public static final int STATE_READY = 2;
    private Context mContext;
    private List mRemovedListeners;
    private int mSlotId;
    private int mState;
    private final Set mStatusCallbacks = Collections.newSetFromMap(new WeakHashMap());
}
