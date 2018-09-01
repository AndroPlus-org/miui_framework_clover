// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;
import java.util.HashMap;

// Referenced classes of package android.view:
//            IWindowId

public class WindowId
    implements Parcelable
{
    public static abstract class FocusObserver
    {

        public abstract void onFocusGained(WindowId windowid);

        public abstract void onFocusLost(WindowId windowid);

        final Handler mHandler = new H();
        final IWindowFocusObserver.Stub mIObserver = new _cls1();
        final HashMap mRegistrations = new HashMap();

        public FocusObserver()
        {
        }
    }

    class FocusObserver.H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 34
        //                       2 51;
               goto _L1 _L2 _L3
_L1:
            super.handleMessage(message);
_L5:
            return;
_L2:
            onFocusGained((WindowId)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            onFocusLost((WindowId)message.obj);
            if(true) goto _L5; else goto _L4
_L4:
        }

        final FocusObserver this$1;

        FocusObserver.H()
        {
            this$1 = FocusObserver.this;
            super();
        }
    }


    public WindowId(IBinder ibinder)
    {
        mToken = IWindowId.Stub.asInterface(ibinder);
    }

    public WindowId(IWindowId iwindowid)
    {
        mToken = iwindowid;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof WindowId)
            return mToken.asBinder().equals(((WindowId)obj).mToken.asBinder());
        else
            return false;
    }

    public IWindowId getTarget()
    {
        return mToken;
    }

    public int hashCode()
    {
        return mToken.asBinder().hashCode();
    }

    public boolean isFocused()
    {
        boolean flag;
        try
        {
            flag = mToken.isFocused();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void registerFocusObserver(FocusObserver focusobserver)
    {
        synchronized(focusobserver.mRegistrations)
        {
            if(focusobserver.mRegistrations.containsKey(mToken.asBinder()))
            {
                focusobserver = JVM INSTR new #76  <Class IllegalStateException>;
                focusobserver.IllegalStateException("Focus observer already registered with input token");
                throw focusobserver;
            }
            break MISSING_BLOCK_LABEL_43;
        }
        focusobserver.mRegistrations.put(mToken.asBinder(), this);
        try
        {
            mToken.registerFocusObserver(focusobserver.mIObserver);
        }
        // Misplaced declaration of an exception variable
        catch(FocusObserver focusobserver) { }
        hashmap;
        JVM INSTR monitorexit ;
    }

    public String toString()
    {
        IBinder ibinder = null;
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("IntentSender{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(": ");
        if(mToken != null)
            ibinder = mToken.asBinder();
        stringbuilder.append(ibinder);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void unregisterFocusObserver(FocusObserver focusobserver)
    {
        synchronized(focusobserver.mRegistrations)
        {
            if(focusobserver.mRegistrations.remove(mToken.asBinder()) == null)
            {
                focusobserver = JVM INSTR new #76  <Class IllegalStateException>;
                focusobserver.IllegalStateException("Focus observer not registered with input token");
                throw focusobserver;
            }
            break MISSING_BLOCK_LABEL_43;
        }
        try
        {
            mToken.unregisterFocusObserver(focusobserver.mIObserver);
        }
        // Misplaced declaration of an exception variable
        catch(FocusObserver focusobserver) { }
        hashmap;
        JVM INSTR monitorexit ;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mToken.asBinder());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WindowId createFromParcel(Parcel parcel)
        {
            Object obj = null;
            IBinder ibinder = parcel.readStrongBinder();
            parcel = obj;
            if(ibinder != null)
                parcel = new WindowId(ibinder);
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WindowId[] newArray(int i)
        {
            return new WindowId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IWindowId mToken;


    // Unreferenced inner class android/view/WindowId$FocusObserver$1

/* anonymous class */
    class FocusObserver._cls1 extends IWindowFocusObserver.Stub
    {

        public void focusGained(IBinder ibinder)
        {
            HashMap hashmap = mRegistrations;
            hashmap;
            JVM INSTR monitorenter ;
            ibinder = (WindowId)mRegistrations.get(ibinder);
            hashmap;
            JVM INSTR monitorexit ;
            if(mHandler != null)
                mHandler.sendMessage(mHandler.obtainMessage(1, ibinder));
            else
                onFocusGained(ibinder);
            return;
            ibinder;
            throw ibinder;
        }

        public void focusLost(IBinder ibinder)
        {
            HashMap hashmap = mRegistrations;
            hashmap;
            JVM INSTR monitorenter ;
            ibinder = (WindowId)mRegistrations.get(ibinder);
            hashmap;
            JVM INSTR monitorexit ;
            if(mHandler != null)
                mHandler.sendMessage(mHandler.obtainMessage(2, ibinder));
            else
                onFocusLost(ibinder);
            return;
            ibinder;
            throw ibinder;
        }

        final FocusObserver this$1;

            
            {
                this$1 = FocusObserver.this;
                super();
            }
    }

}
