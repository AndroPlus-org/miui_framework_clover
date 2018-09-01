// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.IBinder;

// Referenced classes of package android.content:
//            ComponentName

public interface ServiceConnection
{

    public void onBindingDied(ComponentName componentname)
    {
    }

    public abstract void onServiceConnected(ComponentName componentname, IBinder ibinder);

    public abstract void onServiceDisconnected(ComponentName componentname);
}
