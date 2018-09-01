// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import com.android.internal.os.IShellCallback;

// Referenced classes of package android.os:
//            Parcelable, Parcel, RemoteException, ParcelFileDescriptor

public class ShellCallback
    implements Parcelable
{
    class MyShellCallback extends com.android.internal.os.IShellCallback.Stub
    {

        public ParcelFileDescriptor openOutputFile(String s, String s1)
        {
            return onOpenOutputFile(s, s1);
        }

        final ShellCallback this$0;

        MyShellCallback()
        {
            this$0 = ShellCallback.this;
            super();
        }
    }


    public ShellCallback()
    {
        mLocal = true;
    }

    ShellCallback(Parcel parcel)
    {
        mLocal = false;
        mShellCallback = com.android.internal.os.IShellCallback.Stub.asInterface(parcel.readStrongBinder());
    }

    public static void writeToParcel(ShellCallback shellcallback, Parcel parcel)
    {
        if(shellcallback == null)
            parcel.writeStrongBinder(null);
        else
            shellcallback.writeToParcel(parcel, 0);
    }

    public int describeContents()
    {
        return 0;
    }

    public ParcelFileDescriptor onOpenOutputFile(String s, String s1)
    {
        return null;
    }

    public ParcelFileDescriptor openOutputFile(String s, String s1)
    {
        if(mLocal)
            return onOpenOutputFile(s, s1);
        if(mShellCallback == null)
            break MISSING_BLOCK_LABEL_62;
        s1 = mShellCallback.openOutputFile(s, s1);
        return s1;
        s1;
        Log.w("ShellCallback", (new StringBuilder()).append("Failure opening ").append(s).toString(), s1);
        return null;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mShellCallback == null)
        {
            MyShellCallback myshellcallback = JVM INSTR new #10  <Class ShellCallback$MyShellCallback>;
            myshellcallback.this. MyShellCallback();
            mShellCallback = myshellcallback;
        }
        parcel.writeStrongBinder(mShellCallback.asBinder());
        this;
        JVM INSTR monitorexit ;
        return;
        parcel;
        throw parcel;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ShellCallback createFromParcel(Parcel parcel)
        {
            return new ShellCallback(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ShellCallback[] newArray(int i)
        {
            return new ShellCallback[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DEBUG = false;
    static final String TAG = "ShellCallback";
    final boolean mLocal;
    IShellCallback mShellCallback;

}
