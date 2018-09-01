// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiopolicy;

import android.media.AudioFocusInfo;
import android.os.*;

public interface IAudioPolicyCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAudioPolicyCallback
    {

        public static IAudioPolicyCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.audiopolicy.IAudioPolicyCallback");
            if(iinterface != null && (iinterface instanceof IAudioPolicyCallback))
                return (IAudioPolicyCallback)iinterface;
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
                parcel1.writeString("android.media.audiopolicy.IAudioPolicyCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.audiopolicy.IAudioPolicyCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (AudioFocusInfo)AudioFocusInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAudioFocusGrant(parcel1, parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.audiopolicy.IAudioPolicyCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (AudioFocusInfo)AudioFocusInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                notifyAudioFocusLoss(parcel1, flag);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.audiopolicy.IAudioPolicyCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (AudioFocusInfo)AudioFocusInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAudioFocusRequest(parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.audiopolicy.IAudioPolicyCallback");
                if(parcel.readInt() != 0)
                    parcel = (AudioFocusInfo)AudioFocusInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyAudioFocusAbandon(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.audiopolicy.IAudioPolicyCallback");
                notifyMixStateUpdate(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.audiopolicy.IAudioPolicyCallback";
        static final int TRANSACTION_notifyAudioFocusAbandon = 4;
        static final int TRANSACTION_notifyAudioFocusGrant = 1;
        static final int TRANSACTION_notifyAudioFocusLoss = 2;
        static final int TRANSACTION_notifyAudioFocusRequest = 3;
        static final int TRANSACTION_notifyMixStateUpdate = 5;

        public Stub()
        {
            attachInterface(this, "android.media.audiopolicy.IAudioPolicyCallback");
        }
    }

    private static class Stub.Proxy
        implements IAudioPolicyCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.audiopolicy.IAudioPolicyCallback";
        }

        public void notifyAudioFocusAbandon(AudioFocusInfo audiofocusinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.audiopolicy.IAudioPolicyCallback");
            if(audiofocusinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            audiofocusinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audiofocusinfo;
            parcel.recycle();
            throw audiofocusinfo;
        }

        public void notifyAudioFocusGrant(AudioFocusInfo audiofocusinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.audiopolicy.IAudioPolicyCallback");
            if(audiofocusinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            audiofocusinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audiofocusinfo;
            parcel.recycle();
            throw audiofocusinfo;
        }

        public void notifyAudioFocusLoss(AudioFocusInfo audiofocusinfo, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.audiopolicy.IAudioPolicyCallback");
            if(audiofocusinfo == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            audiofocusinfo.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audiofocusinfo;
            parcel.recycle();
            throw audiofocusinfo;
        }

        public void notifyAudioFocusRequest(AudioFocusInfo audiofocusinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.audiopolicy.IAudioPolicyCallback");
            if(audiofocusinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            audiofocusinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audiofocusinfo;
            parcel.recycle();
            throw audiofocusinfo;
        }

        public void notifyMixStateUpdate(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.audiopolicy.IAudioPolicyCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
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


    public abstract void notifyAudioFocusAbandon(AudioFocusInfo audiofocusinfo)
        throws RemoteException;

    public abstract void notifyAudioFocusGrant(AudioFocusInfo audiofocusinfo, int i)
        throws RemoteException;

    public abstract void notifyAudioFocusLoss(AudioFocusInfo audiofocusinfo, boolean flag)
        throws RemoteException;

    public abstract void notifyAudioFocusRequest(AudioFocusInfo audiofocusinfo, int i)
        throws RemoteException;

    public abstract void notifyMixStateUpdate(String s, int i)
        throws RemoteException;
}
