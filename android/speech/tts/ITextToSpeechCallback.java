// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.os.*;

public interface ITextToSpeechCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITextToSpeechCallback
    {

        public static ITextToSpeechCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.speech.tts.ITextToSpeechCallback");
            if(iinterface != null && (iinterface instanceof ITextToSpeechCallback))
                return (ITextToSpeechCallback)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.speech.tts.ITextToSpeechCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onStart(parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onSuccess(parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                parcel1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onStop(parcel1, flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onError(parcel.readString(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onBeginSynthesis(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onAudioAvailable(parcel.readString(), parcel.createByteArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.speech.tts.ITextToSpeechCallback");
                onRangeStart(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.speech.tts.ITextToSpeechCallback";
        static final int TRANSACTION_onAudioAvailable = 6;
        static final int TRANSACTION_onBeginSynthesis = 5;
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onRangeStart = 7;
        static final int TRANSACTION_onStart = 1;
        static final int TRANSACTION_onStop = 3;
        static final int TRANSACTION_onSuccess = 2;

        public Stub()
        {
            attachInterface(this, "android.speech.tts.ITextToSpeechCallback");
        }
    }

    private static class Stub.Proxy
        implements ITextToSpeechCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.speech.tts.ITextToSpeechCallback";
        }

        public void onAudioAvailable(String s, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onBeginSynthesis(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onError(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRangeStart(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStart(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStop(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onSuccess(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.tts.ITextToSpeechCallback");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAudioAvailable(String s, byte abyte0[])
        throws RemoteException;

    public abstract void onBeginSynthesis(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onError(String s, int i)
        throws RemoteException;

    public abstract void onRangeStart(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onStart(String s)
        throws RemoteException;

    public abstract void onStop(String s, boolean flag)
        throws RemoteException;

    public abstract void onSuccess(String s)
        throws RemoteException;
}
