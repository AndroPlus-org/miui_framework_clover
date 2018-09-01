// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Handler, Parcel, IMessenger, 
//            RemoteException, IBinder, Message

public final class Messenger
    implements Parcelable
{

    public Messenger(Handler handler)
    {
        mTarget = handler.getIMessenger();
    }

    public Messenger(IBinder ibinder)
    {
        mTarget = IMessenger.Stub.asInterface(ibinder);
    }

    public static Messenger readMessengerOrNullFromParcel(Parcel parcel)
    {
        Object obj = null;
        IBinder ibinder = parcel.readStrongBinder();
        parcel = obj;
        if(ibinder != null)
            parcel = new Messenger(ibinder);
        return parcel;
    }

    public static void writeMessengerOrNullToParcel(Messenger messenger, Parcel parcel)
    {
        IBinder ibinder = null;
        if(messenger != null)
            ibinder = messenger.mTarget.asBinder();
        parcel.writeStrongBinder(ibinder);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        boolean flag;
        try
        {
            flag = mTarget.asBinder().equals(((Messenger)obj).mTarget.asBinder());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public IBinder getBinder()
    {
        return mTarget.asBinder();
    }

    public int hashCode()
    {
        return mTarget.asBinder().hashCode();
    }

    public void send(Message message)
        throws RemoteException
    {
        mTarget.send(message);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mTarget.asBinder());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Messenger createFromParcel(Parcel parcel)
        {
            Object obj = null;
            IBinder ibinder = parcel.readStrongBinder();
            parcel = obj;
            if(ibinder != null)
                parcel = new Messenger(ibinder);
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Messenger[] newArray(int i)
        {
            return new Messenger[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IMessenger mTarget;

}
