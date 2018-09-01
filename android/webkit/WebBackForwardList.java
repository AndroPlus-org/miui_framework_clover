// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import java.io.Serializable;

// Referenced classes of package android.webkit:
//            WebHistoryItem

public abstract class WebBackForwardList
    implements Cloneable, Serializable
{

    public WebBackForwardList()
    {
    }

    protected abstract WebBackForwardList clone();

    protected volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public abstract int getCurrentIndex();

    public abstract WebHistoryItem getCurrentItem();

    public abstract WebHistoryItem getItemAtIndex(int i);

    public abstract int getSize();
}
