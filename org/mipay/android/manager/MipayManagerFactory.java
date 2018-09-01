// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mipay.android.manager;

import android.content.Context;

// Referenced classes of package org.mipay.android.manager:
//            MipayManagerImpl, IMipayManager

public class MipayManagerFactory
{

    public MipayManagerFactory()
    {
    }

    public static IMipayManager getMipayManager(Context context, int i)
    {
        return MipayManagerImpl.getInstance();
    }
}
