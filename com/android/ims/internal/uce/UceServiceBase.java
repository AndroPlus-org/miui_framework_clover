// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce;

import com.android.ims.internal.uce.common.UceLong;
import com.android.ims.internal.uce.options.IOptionsListener;
import com.android.ims.internal.uce.options.IOptionsService;
import com.android.ims.internal.uce.presence.IPresenceListener;
import com.android.ims.internal.uce.presence.IPresenceService;
import com.android.ims.internal.uce.uceservice.IUceListener;

public abstract class UceServiceBase
{
    private final class UceServiceBinder extends com.android.ims.internal.uce.uceservice.IUceService.Stub
    {

        public int createOptionsService(IOptionsListener ioptionslistener, UceLong ucelong)
        {
            return onCreateOptionsService(ioptionslistener, ucelong);
        }

        public int createOptionsServiceForSubscription(IOptionsListener ioptionslistener, UceLong ucelong, String s)
        {
            return onCreateOptionsService(ioptionslistener, ucelong, s);
        }

        public int createPresenceService(IPresenceListener ipresencelistener, UceLong ucelong)
        {
            return onCreatePresService(ipresencelistener, ucelong);
        }

        public int createPresenceServiceForSubscription(IPresenceListener ipresencelistener, UceLong ucelong, String s)
        {
            return onCreatePresService(ipresencelistener, ucelong, s);
        }

        public void destroyOptionsService(int i)
        {
            onDestroyOptionsService(i);
        }

        public void destroyPresenceService(int i)
        {
            onDestroyPresService(i);
        }

        public IOptionsService getOptionsService()
        {
            return onGetOptionsService();
        }

        public IOptionsService getOptionsServiceForSubscription(String s)
        {
            return onGetOptionsService(s);
        }

        public IPresenceService getPresenceService()
        {
            return onGetPresenceService();
        }

        public IPresenceService getPresenceServiceForSubscription(String s)
        {
            return onGetPresenceService(s);
        }

        public boolean getServiceStatus()
        {
            return onGetServiceStatus();
        }

        public boolean isServiceStarted()
        {
            return onIsServiceStarted();
        }

        public boolean startService(IUceListener iucelistener)
        {
            return onServiceStart(iucelistener);
        }

        public boolean stopService()
        {
            return onStopService();
        }

        final UceServiceBase this$0;

        private UceServiceBinder()
        {
            this$0 = UceServiceBase.this;
            super();
        }

        UceServiceBinder(UceServiceBinder uceservicebinder)
        {
            this();
        }
    }


    public UceServiceBase()
    {
    }

    public UceServiceBinder getBinder()
    {
        if(mBinder == null)
            mBinder = new UceServiceBinder(null);
        return mBinder;
    }

    protected int onCreateOptionsService(IOptionsListener ioptionslistener, UceLong ucelong)
    {
        return 0;
    }

    protected int onCreateOptionsService(IOptionsListener ioptionslistener, UceLong ucelong, String s)
    {
        return 0;
    }

    protected int onCreatePresService(IPresenceListener ipresencelistener, UceLong ucelong)
    {
        return 0;
    }

    protected int onCreatePresService(IPresenceListener ipresencelistener, UceLong ucelong, String s)
    {
        return 0;
    }

    protected void onDestroyOptionsService(int i)
    {
    }

    protected void onDestroyPresService(int i)
    {
    }

    protected IOptionsService onGetOptionsService()
    {
        return null;
    }

    protected IOptionsService onGetOptionsService(String s)
    {
        return null;
    }

    protected IPresenceService onGetPresenceService()
    {
        return null;
    }

    protected IPresenceService onGetPresenceService(String s)
    {
        return null;
    }

    protected boolean onGetServiceStatus()
    {
        return false;
    }

    protected boolean onIsServiceStarted()
    {
        return false;
    }

    protected boolean onServiceStart(IUceListener iucelistener)
    {
        return false;
    }

    protected boolean onStopService()
    {
        return false;
    }

    private UceServiceBinder mBinder;
}
