// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import android.util.Log;
import java.util.HashMap;

// Referenced classes of package android.location:
//            ICountryDetector, CountryListener, Country

public class CountryDetector
{
    private static final class ListenerTransport extends ICountryListener.Stub
    {

        static CountryListener _2D_get0(ListenerTransport listenertransport)
        {
            return listenertransport.mListener;
        }

        public void onCountryDetected(Country country)
        {
            mHandler.post(country. new Runnable() {

                public void run()
                {
                    ListenerTransport._2D_get0(ListenerTransport.this).onCountryDetected(country);
                }

                final ListenerTransport this$1;
                final Country val$country;

            
            {
                this$1 = final_listenertransport;
                country = Country.this;
                super();
            }
            }
);
        }

        private final Handler mHandler;
        private final CountryListener mListener;

        public ListenerTransport(CountryListener countrylistener, Looper looper)
        {
            mListener = countrylistener;
            if(looper != null)
                mHandler = new Handler(looper);
            else
                mHandler = new Handler();
        }
    }


    public CountryDetector(ICountryDetector icountrydetector)
    {
        mService = icountrydetector;
    }

    public void addCountryListener(CountryListener countrylistener, Looper looper)
    {
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ListenerTransport listenertransport;
        if(mListeners.containsKey(countrylistener))
            break MISSING_BLOCK_LABEL_52;
        listenertransport = JVM INSTR new #6   <Class CountryDetector$ListenerTransport>;
        listenertransport.ListenerTransport(countrylistener, looper);
        mService.addCountryListener(listenertransport);
        mListeners.put(countrylistener, listenertransport);
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        countrylistener;
        Log.e("CountryDetector", "addCountryListener: RemoteException", countrylistener);
          goto _L1
        countrylistener;
        throw countrylistener;
    }

    public Country detectCountry()
    {
        Country country;
        try
        {
            country = mService.detectCountry();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("CountryDetector", "detectCountry: RemoteException", remoteexception);
            return null;
        }
        return country;
    }

    public void removeCountryListener(CountryListener countrylistener)
    {
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ListenerTransport listenertransport = (ListenerTransport)mListeners.get(countrylistener);
        if(listenertransport == null)
            break MISSING_BLOCK_LABEL_42;
        mListeners.remove(countrylistener);
        mService.removeCountryListener(listenertransport);
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        countrylistener;
        Log.e("CountryDetector", "removeCountryListener: RemoteException", countrylistener);
          goto _L1
        countrylistener;
        throw countrylistener;
    }

    private static final String TAG = "CountryDetector";
    private final HashMap mListeners = new HashMap();
    private final ICountryDetector mService;
}
