// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


// Referenced classes of package android.webkit:
//            WebViewFactory, WebViewFactoryProvider, ValueCallback

public class WebStorage
{
    public static class Origin
    {

        public String getOrigin()
        {
            return mOrigin;
        }

        public long getQuota()
        {
            return mQuota;
        }

        public long getUsage()
        {
            return mUsage;
        }

        private String mOrigin;
        private long mQuota;
        private long mUsage;

        protected Origin(String s, long l, long l1)
        {
            mOrigin = null;
            mQuota = 0L;
            mUsage = 0L;
            mOrigin = s;
            mQuota = l;
            mUsage = l1;
        }
    }

    public static interface QuotaUpdater
    {

        public abstract void updateQuota(long l);
    }


    public WebStorage()
    {
    }

    public static WebStorage getInstance()
    {
        return WebViewFactory.getProvider().getWebStorage();
    }

    public void deleteAllData()
    {
    }

    public void deleteOrigin(String s)
    {
    }

    public void getOrigins(ValueCallback valuecallback)
    {
    }

    public void getQuotaForOrigin(String s, ValueCallback valuecallback)
    {
    }

    public void getUsageForOrigin(String s, ValueCallback valuecallback)
    {
    }

    public void setQuotaForOrigin(String s, long l)
    {
    }
}
