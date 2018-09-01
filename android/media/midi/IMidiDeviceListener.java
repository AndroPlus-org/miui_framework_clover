// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;

// Referenced classes of package android.media.midi:
//            MidiDeviceInfo, MidiDeviceStatus

public interface IMidiDeviceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMidiDeviceListener
    {

        public static IMidiDeviceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.midi.IMidiDeviceListener");
            if(iinterface != null && (iinterface instanceof IMidiDeviceListener))
                return (IMidiDeviceListener)iinterface;
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
                parcel1.writeString("android.media.midi.IMidiDeviceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.midi.IMidiDeviceListener");
                if(parcel.readInt() != 0)
                    parcel = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onDeviceAdded(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.midi.IMidiDeviceListener");
                if(parcel.readInt() != 0)
                    parcel = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onDeviceRemoved(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.midi.IMidiDeviceListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (MidiDeviceStatus)MidiDeviceStatus.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onDeviceStatusChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.midi.IMidiDeviceListener";
        static final int TRANSACTION_onDeviceAdded = 1;
        static final int TRANSACTION_onDeviceRemoved = 2;
        static final int TRANSACTION_onDeviceStatusChanged = 3;

        public Stub()
        {
            attachInterface(this, "android.media.midi.IMidiDeviceListener");
        }
    }

    private static class Stub.Proxy
        implements IMidiDeviceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.midi.IMidiDeviceListener";
        }

        public void onDeviceAdded(MidiDeviceInfo midideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceListener");
            if(midideviceinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            midideviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            midideviceinfo;
            parcel.recycle();
            throw midideviceinfo;
        }

        public void onDeviceRemoved(MidiDeviceInfo midideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceListener");
            if(midideviceinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            midideviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            midideviceinfo;
            parcel.recycle();
            throw midideviceinfo;
        }

        public void onDeviceStatusChanged(MidiDeviceStatus mididevicestatus)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceListener");
            if(mididevicestatus == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            mididevicestatus.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mididevicestatus;
            parcel.recycle();
            throw mididevicestatus;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onDeviceAdded(MidiDeviceInfo midideviceinfo)
        throws RemoteException;

    public abstract void onDeviceRemoved(MidiDeviceInfo midideviceinfo)
        throws RemoteException;

    public abstract void onDeviceStatusChanged(MidiDeviceStatus mididevicestatus)
        throws RemoteException;
}
