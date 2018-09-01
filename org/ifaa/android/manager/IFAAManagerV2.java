// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.ifaa.android.manager;

import android.content.Context;

// Referenced classes of package org.ifaa.android.manager:
//            IFAAManager

public abstract class IFAAManagerV2 extends IFAAManager
{

    public IFAAManagerV2()
    {
    }

    public abstract byte[] processCmdV2(Context context, byte abyte0[]);
}
