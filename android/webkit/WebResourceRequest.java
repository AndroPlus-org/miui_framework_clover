// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.net.Uri;
import java.util.Map;

public interface WebResourceRequest
{

    public abstract String getMethod();

    public abstract Map getRequestHeaders();

    public abstract Uri getUrl();

    public abstract boolean hasGesture();

    public abstract boolean isForMainFrame();

    public abstract boolean isRedirect();
}
