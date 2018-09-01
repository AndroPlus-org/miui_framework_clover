// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Parcelable, Parcel, RemoteException, Handler, 
//            IRemoteCallback, Bundle

public final class RemoteCallback
    implements Parcelable
{
    public static interface OnResultListener
    {

        public abstract void onResult(Bundle bundle);
    }


    static OnResultListener _2D_get0(RemoteCallback remotecallback)
    {
        return remotecallback.mListener;
    }

    RemoteCallback(Parcel parcel)
    {
        mListener = null;
        mHandler = null;
        mCallback = IRemoteCallback.Stub.asInterface(parcel.readStrongBinder());
    }

    public RemoteCallback(OnResultListener onresultlistener)
    {
        this(onresultlistener, null);
    }

    public RemoteCallback(OnResultListener onresultlistener, Handler handler)
    {
        if(onresultlistener == null)
        {
            throw new NullPointerException("listener cannot be null");
        } else
        {
            mListener = onresultlistener;
            mHandler = handler;
            mCallback = new IRemoteCallback.Stub() {

                public void sendResult(Bundle bundle)
                {
                    RemoteCallback.this.sendResult(bundle);
                }

                final RemoteCallback this$0;

            
            {
                this$0 = RemoteCallback.this;
                super();
            }
            }
;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void sendResult(final Bundle result)
    {
        if(mListener != null)
        {
            if(mHandler != null)
                mHandler.post(new Runnable() {

                    public void run()
                    {
                        RemoteCallback._2D_get0(RemoteCallback.this).onResult(result);
                    }

                    final RemoteCallback this$0;
                    final Bundle val$result;

            
            {
                this$0 = RemoteCallback.this;
                result = bundle;
                super();
            }
                }
);
            else
                mListener.onResult(result);
        } else
        {
            try
            {
                mCallback.sendResult(result);
            }
            // Misplaced declaration of an exception variable
            catch(final Bundle result) { }
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mCallback.asBinder());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public RemoteCallback createFromParcel(Parcel parcel)
        {
            return new RemoteCallback(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteCallback[] newArray(int i)
        {
            return new RemoteCallback[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IRemoteCallback mCallback;
    private final Handler mHandler;
    private final OnResultListener mListener;

}
