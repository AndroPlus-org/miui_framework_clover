// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.util.Slog;
import java.util.ArrayList;

// Referenced classes of package android.content:
//            ContentResolver, IContentProvider

public class ContentResolverInjector
{

    public ContentResolverInjector()
    {
    }

    public static boolean isForceAcquireUnstableProvider(String s, Uri uri)
    {
        if(ENABLE && mUnstablePackagesWhiteList.contains(s))
        {
            Slog.d("ContentResolverInjector", (new StringBuilder()).append("force acquire UnstableProvider for pkg=").append(s).append(", uri=").append(uri).toString());
            return true;
        } else
        {
            return false;
        }
    }

    public static Cursor unstableQuery(ContentResolver contentresolver, Uri uri, String as[], Bundle bundle, ICancellationSignal icancellationsignal)
    {
        IContentProvider icontentprovider;
        Object obj;
        icontentprovider = contentresolver.acquireUnstableProvider(uri);
        if(icontentprovider == null)
            return null;
        obj = null;
        as = icontentprovider.query(contentresolver.getPackageName(), uri, as, bundle, icancellationsignal);
        uri = as;
        if(icontentprovider != null)
        {
            contentresolver.releaseUnstableProvider(icontentprovider);
            uri = as;
        }
_L2:
        return uri;
        as;
        as = JVM INSTR new #51  <Class StringBuilder>;
        as.StringBuilder();
        Slog.d("ContentResolverInjector", as.append("remote process has died again, unstableQuery pkg=").append(contentresolver.getPackageName()).append(", uri=").append(uri).toString());
        contentresolver.unstableProviderDied(icontentprovider);
        uri = obj;
        if(icontentprovider != null)
        {
            contentresolver.releaseUnstableProvider(icontentprovider);
            uri = obj;
        }
        if(true) goto _L2; else goto _L1
_L1:
        uri;
        if(icontentprovider != null)
            contentresolver.releaseUnstableProvider(icontentprovider);
        throw uri;
    }

    private static final boolean ENABLE = SystemProperties.getBoolean("persist.am.enable_crj", true);
    private static final String TAG = "ContentResolverInjector";
    private static ArrayList mUnstablePackagesWhiteList;

    static 
    {
        mUnstablePackagesWhiteList = new ArrayList();
        mUnstablePackagesWhiteList.add("com.android.contacts");
        mUnstablePackagesWhiteList.add("com.android.incallui");
        mUnstablePackagesWhiteList.add("com.android.mms");
    }
}
