// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            VolumeShaper

public interface VolumeAutomation
{

    public abstract VolumeShaper createVolumeShaper(VolumeShaper.Configuration configuration);
}
