// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Bundle;

// Referenced classes of package android.location:
//            Location

public interface LocationListener
{

    public abstract void onLocationChanged(Location location);

    public abstract void onProviderDisabled(String s);

    public abstract void onProviderEnabled(String s);

    public abstract void onStatusChanged(String s, int i, Bundle bundle);
}
