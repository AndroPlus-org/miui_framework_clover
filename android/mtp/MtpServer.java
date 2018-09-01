// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import com.android.internal.util.Preconditions;

// Referenced classes of package android.mtp:
//            MtpDatabase, MtpStorage

public class MtpServer
    implements Runnable
{

    public MtpServer(MtpDatabase mtpdatabase, boolean flag, Runnable runnable, String s, String s1, String s2, String s3)
    {
        mDatabase = (MtpDatabase)Preconditions.checkNotNull(mtpdatabase);
        mOnTerminate = (Runnable)Preconditions.checkNotNull(runnable);
        native_setup(mtpdatabase, flag, s, s1, s2, s3);
        mtpdatabase.setServer(this);
    }

    public static void configure(boolean flag)
    {
        native_configure(flag);
    }

    private final native void native_add_storage(MtpStorage mtpstorage);

    private final native void native_cleanup();

    public static final native void native_configure(boolean flag);

    private final native void native_remove_storage(int i);

    private final native void native_run();

    private final native void native_send_device_property_changed(int i);

    private final native void native_send_object_added(int i);

    private final native void native_send_object_removed(int i);

    private final native void native_setup(MtpDatabase mtpdatabase, boolean flag, String s, String s1, String s2, String s3);

    public void addStorage(MtpStorage mtpstorage)
    {
        native_add_storage(mtpstorage);
    }

    public void removeStorage(MtpStorage mtpstorage)
    {
        native_remove_storage(mtpstorage.getStorageId());
    }

    public void run()
    {
        native_run();
        native_cleanup();
        mDatabase.close();
        mOnTerminate.run();
    }

    public void sendDevicePropertyChanged(int i)
    {
        native_send_device_property_changed(i);
    }

    public void sendObjectAdded(int i)
    {
        native_send_object_added(i);
    }

    public void sendObjectRemoved(int i)
    {
        native_send_object_removed(i);
    }

    public void start()
    {
        (new Thread(this, "MtpServer")).start();
    }

    private final MtpDatabase mDatabase;
    private long mNativeContext;
    private final Runnable mOnTerminate;

    static 
    {
        System.loadLibrary("media_jni");
    }
}
