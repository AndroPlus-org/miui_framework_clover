// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.graphics.Bitmap;

public abstract class WebHistoryItem
    implements Cloneable
{

    public WebHistoryItem()
    {
    }

    protected abstract WebHistoryItem clone();

    protected volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public abstract Bitmap getFavicon();

    public abstract int getId();

    public abstract String getOriginalUrl();

    public abstract String getTitle();

    public abstract String getUrl();
}
