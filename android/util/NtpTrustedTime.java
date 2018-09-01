// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.*;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;

// Referenced classes of package android.util:
//            TrustedTime, Log, NtpTrustedTimeInjector

public class NtpTrustedTime
    implements TrustedTime
{

    private NtpTrustedTime(String s, long l)
    {
        mBackupmode = false;
        Log.d("NtpTrustedTime", (new StringBuilder()).append("creating NtpTrustedTime using ").append(s).toString());
        mServer = s;
        mTimeout = l;
    }

    private void countInBackupmode()
    {
        if(isBackupSupported())
        {
            mNtpRetries++;
            if(mNtpRetries == mNtpRetriesMax)
            {
                mNtpRetries = 0;
                setBackupmode(true);
            }
        }
        Log.d("NtpTrustedTime", "countInBackupmode() func");
    }

    private boolean getBackupmode()
    {
        return mBackupmode;
    }

    public static NtpTrustedTime getInstance(Context context)
    {
        android/util/NtpTrustedTime;
        JVM INSTR monitorenter ;
        Resources resources;
        Object obj;
        long l;
        Object obj1;
        if(sSingleton != null)
            break MISSING_BLOCK_LABEL_145;
        resources = context.getResources();
        android.content.ContentResolver contentresolver = context.getContentResolver();
        obj = resources.getString(0x1040156);
        l = resources.getInteger(0x10e008a);
        obj1 = android.provider.Settings.Global.getString(contentresolver, "ntp_server");
        l = android.provider.Settings.Global.getLong(contentresolver, "ntp_timeout", l);
        if(obj1 == null)
            obj1 = obj;
        obj = JVM INSTR new #2   <Class NtpTrustedTime>;
        ((NtpTrustedTime) (obj)).NtpTrustedTime(((String) (obj1)), l);
        sSingleton = ((NtpTrustedTime) (obj));
        sContext = context;
        if(sSingleton == null)
            break MISSING_BLOCK_LABEL_145;
        context = SystemProperties.get("persist.backup.ntpServer");
        obj1 = sSingleton;
        mNtpRetriesMax = resources.getInteger(0x10e0088);
        obj1 = sSingleton;
        if(mNtpRetriesMax <= 0 || context == null)
            break MISSING_BLOCK_LABEL_160;
        if(context.length() == 0)
            break MISSING_BLOCK_LABEL_160;
        obj1 = sSingleton;
        mBackupServer = context.trim().replace("\"", "");
_L1:
        context = sSingleton;
        android/util/NtpTrustedTime;
        JVM INSTR monitorexit ;
        return context;
        context = sSingleton;
        mNtpRetriesMax = 0;
        context = sSingleton;
        mBackupServer = "";
          goto _L1
        context;
        throw context;
    }

    private boolean isBackupSupported()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mNtpRetriesMax > 0)
        {
            flag1 = flag;
            if(mBackupServer != null)
            {
                flag1 = flag;
                if(mBackupServer.length() != 0)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public long currentTimeMillis()
    {
        if(!mHasCache)
        {
            throw new IllegalStateException("Missing authoritative time source");
        } else
        {
            Log.d("NtpTrustedTime", "currentTimeMillis() cache hit");
            return mCachedNtpTime + getCacheAge();
        }
    }

    public boolean forceRefresh()
    {
        if(TextUtils.isEmpty(mServer))
            return false;
        this;
        JVM INSTR monitorenter ;
        if(mCM == null)
            mCM = (ConnectivityManager)sContext.getSystemService("connectivity");
        this;
        JVM INSTR monitorexit ;
        Exception exception;
        if(mCM == null)
            exception = null;
        else
            exception = mCM.getActiveNetworkInfo();
        if(exception == null || exception.isConnected() ^ true)
        {
            Log.d("NtpTrustedTime", "forceRefresh: no connectivity");
            return false;
        }
        break MISSING_BLOCK_LABEL_86;
        exception;
        throw exception;
        Log.d("NtpTrustedTime", "forceRefresh() from cache miss");
        SntpClient sntpclient = new SntpClient();
        String s = mServer;
        if(getBackupmode())
        {
            setBackupmode(false);
            s = mBackupServer;
        }
        Log.d("NtpTrustedTime", (new StringBuilder()).append("Ntp Server to access at:").append(s).toString());
        if(NtpTrustedTimeInjector.requestTime(sntpclient, s, (int)mTimeout))
        {
            mHasCache = true;
            mCachedNtpTime = sntpclient.getNtpTime();
            mCachedNtpElapsedRealtime = sntpclient.getNtpTimeReference();
            mCachedNtpCertainty = sntpclient.getRoundTripTime() / 2L;
            return true;
        } else
        {
            countInBackupmode();
            return false;
        }
    }

    public long getCacheAge()
    {
        if(mHasCache)
            return SystemClock.elapsedRealtime() - mCachedNtpElapsedRealtime;
        else
            return 0x7fffffffffffffffL;
    }

    public long getCacheCertainty()
    {
        if(mHasCache)
            return mCachedNtpCertainty;
        else
            return 0x7fffffffffffffffL;
    }

    public long getCachedNtpTime()
    {
        Log.d("NtpTrustedTime", "getCachedNtpTime() cache hit");
        return mCachedNtpTime;
    }

    public long getCachedNtpTimeReference()
    {
        return mCachedNtpElapsedRealtime;
    }

    public boolean hasCache()
    {
        return mHasCache;
    }

    public void setBackupmode(boolean flag)
    {
        if(isBackupSupported())
            mBackupmode = flag;
        Log.d("NtpTrustedTime", (new StringBuilder()).append("setBackupmode() set the backup mode to be:").append(mBackupmode).toString());
    }

    private static final boolean LOGD = true;
    private static final String TAG = "NtpTrustedTime";
    private static String mBackupServer = "";
    private static int mNtpRetries = 0;
    private static int mNtpRetriesMax = 0;
    private static Context sContext;
    private static NtpTrustedTime sSingleton;
    private boolean mBackupmode;
    private ConnectivityManager mCM;
    private long mCachedNtpCertainty;
    private long mCachedNtpElapsedRealtime;
    private long mCachedNtpTime;
    private boolean mHasCache;
    private final String mServer;
    private final long mTimeout;

}
