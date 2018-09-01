// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


// Referenced classes of package android.view:
//            Surface

public final class SurfaceSession
{

    public SurfaceSession()
    {
        mNativeClient = nativeCreate();
    }

    public SurfaceSession(Surface surface)
    {
        mNativeClient = nativeCreateScoped(surface.mNativeObject);
    }

    private static native long nativeCreate();

    private static native long nativeCreateScoped(long l);

    private static native void nativeDestroy(long l);

    private static native void nativeKill(long l);

    protected void finalize()
        throws Throwable
    {
        if(mNativeClient != 0L)
            nativeDestroy(mNativeClient);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void kill()
    {
        nativeKill(mNativeClient);
    }

    private long mNativeClient;
}
