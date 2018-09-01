// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.ifaa.android.manager;

import android.content.Context;

// Referenced classes of package org.ifaa.android.manager:
//            IFAAManagerImpl, IFAAManager

public class IFAAManagerFactory
{

    public IFAAManagerFactory()
    {
    }

    public static IFAAManager getIFAAManager(Context context, int i)
    {
        return IFAAManagerImpl.getInstance();
    }
}
