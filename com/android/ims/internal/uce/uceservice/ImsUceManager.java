// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.uceservice;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import java.util.HashMap;

// Referenced classes of package com.android.ims.internal.uce.uceservice:
//            IUceService

public class ImsUceManager
{
    private class UceServiceDeathRecipient
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            ImsUceManager._2D_set0(ImsUceManager.this, null);
            if(ImsUceManager._2D_get0(ImsUceManager.this) != null)
            {
                Intent intent = new Intent("com.android.ims.internal.uce.UCE_SERVICE_DOWN");
                intent.putExtra("android:phone_id", ImsUceManager._2D_get1(ImsUceManager.this));
                ImsUceManager._2D_get0(ImsUceManager.this).sendBroadcast(new Intent(intent));
            }
        }

        final ImsUceManager this$0;

        private UceServiceDeathRecipient()
        {
            this$0 = ImsUceManager.this;
            super();
        }

        UceServiceDeathRecipient(UceServiceDeathRecipient uceservicedeathrecipient)
        {
            this();
        }
    }


    static Context _2D_get0(ImsUceManager imsucemanager)
    {
        return imsucemanager.mContext;
    }

    static int _2D_get1(ImsUceManager imsucemanager)
    {
        return imsucemanager.mPhoneId;
    }

    static IUceService _2D_set0(ImsUceManager imsucemanager, IUceService iuceservice)
    {
        imsucemanager.mUceService = iuceservice;
        return iuceservice;
    }

    private ImsUceManager(Context context, int i)
    {
        mUceService = null;
        mDeathReceipient = new UceServiceDeathRecipient(null);
        mContext = context;
        mPhoneId = i;
        createUceService(true);
    }

    public static ImsUceManager getInstance(Context context, int i)
    {
        HashMap hashmap = sUceManagerInstances;
        hashmap;
        JVM INSTR monitorenter ;
        if(!sUceManagerInstances.containsKey(Integer.valueOf(i)))
            break MISSING_BLOCK_LABEL_37;
        context = (ImsUceManager)sUceManagerInstances.get(Integer.valueOf(i));
        hashmap;
        JVM INSTR monitorexit ;
        return context;
        ImsUceManager imsucemanager;
        imsucemanager = JVM INSTR new #2   <Class ImsUceManager>;
        imsucemanager.ImsUceManager(context, i);
        sUceManagerInstances.put(Integer.valueOf(i), imsucemanager);
        hashmap;
        JVM INSTR monitorexit ;
        return imsucemanager;
        context;
        throw context;
    }

    private String getUceServiceName(int i)
    {
        return "uce";
    }

    public void createUceService(boolean flag)
    {
        if(flag && ServiceManager.checkService(getUceServiceName(mPhoneId)) == null)
            return;
        IBinder ibinder = ServiceManager.getService(getUceServiceName(mPhoneId));
        if(ibinder != null)
            try
            {
                ibinder.linkToDeath(mDeathReceipient, 0);
            }
            catch(RemoteException remoteexception) { }
        mUceService = IUceService.Stub.asInterface(ibinder);
    }

    public IUceService getUceServiceInstance()
    {
        return mUceService;
    }

    public static final String ACTION_UCE_SERVICE_DOWN = "com.android.ims.internal.uce.UCE_SERVICE_DOWN";
    public static final String ACTION_UCE_SERVICE_UP = "com.android.ims.internal.uce.UCE_SERVICE_UP";
    public static final String EXTRA_PHONE_ID = "android:phone_id";
    private static final String LOG_TAG = "ImsUceManager";
    private static final String UCE_SERVICE = "uce";
    public static final int UCE_SERVICE_STATUS_CLOSED = 2;
    public static final int UCE_SERVICE_STATUS_FAILURE = 0;
    public static final int UCE_SERVICE_STATUS_ON = 1;
    public static final int UCE_SERVICE_STATUS_READY = 3;
    private static HashMap sUceManagerInstances = new HashMap();
    private Context mContext;
    private UceServiceDeathRecipient mDeathReceipient;
    private int mPhoneId;
    private IUceService mUceService;

}
