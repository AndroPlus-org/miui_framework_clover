// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.content.ComponentName;
import android.media.IRemoteVolumeController;
import android.os.*;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.media.session:
//            IActiveSessionsListener, ISessionCallback, ISession, ICallback, 
//            IOnMediaKeyListener, IOnVolumeKeyLongPressListener

public interface ISessionManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISessionManager
    {

        public static ISessionManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.ISessionManager");
            if(iinterface != null && (iinterface instanceof ISessionManager))
                return (ISessionManager)iinterface;
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
                parcel1.writeString("android.media.session.ISessionManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.ISessionManager");
                parcel = createSession(parcel.readString(), ISessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.session.ISessionManager");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                parcel = getSessions(componentname, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeBinderList(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.session.ISessionManager");
                KeyEvent keyevent;
                boolean flag;
                if(parcel.readInt() != 0)
                    keyevent = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    keyevent = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                dispatchMediaKeyEvent(keyevent, flag);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.session.ISessionManager");
                KeyEvent keyevent1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    keyevent1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    keyevent1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                dispatchVolumeKeyEvent(keyevent1, i, flag1);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.session.ISessionManager");
                dispatchAdjustVolume(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.session.ISessionManager");
                IActiveSessionsListener iactivesessionslistener = IActiveSessionsListener.Stub.asInterface(parcel.readStrongBinder());
                ComponentName componentname1;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                addSessionsListener(iactivesessionslistener, componentname1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.session.ISessionManager");
                removeSessionsListener(IActiveSessionsListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.session.ISessionManager");
                setRemoteVolumeController(android.media.IRemoteVolumeController.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.session.ISessionManager");
                boolean flag2 = isGlobalPriorityActive();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.session.ISessionManager");
                setCallback(ICallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.session.ISessionManager");
                setOnVolumeKeyLongPressListener(IOnVolumeKeyLongPressListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.session.ISessionManager");
                setOnMediaKeyListener(IOnMediaKeyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.session.ISessionManager";
        static final int TRANSACTION_addSessionsListener = 6;
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_dispatchAdjustVolume = 5;
        static final int TRANSACTION_dispatchMediaKeyEvent = 3;
        static final int TRANSACTION_dispatchVolumeKeyEvent = 4;
        static final int TRANSACTION_getSessions = 2;
        static final int TRANSACTION_isGlobalPriorityActive = 9;
        static final int TRANSACTION_removeSessionsListener = 7;
        static final int TRANSACTION_setCallback = 10;
        static final int TRANSACTION_setOnMediaKeyListener = 12;
        static final int TRANSACTION_setOnVolumeKeyLongPressListener = 11;
        static final int TRANSACTION_setRemoteVolumeController = 8;

        public Stub()
        {
            attachInterface(this, "android.media.session.ISessionManager");
        }
    }

    private static class Stub.Proxy
        implements ISessionManager
    {

        public void addSessionsListener(IActiveSessionsListener iactivesessionslistener, ComponentName componentname, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(iactivesessionslistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iactivesessionslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iactivesessionslistener;
            parcel1.recycle();
            parcel.recycle();
            throw iactivesessionslistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ISession createSession(String s, ISessionCallback isessioncallback, String s1, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            parcel.writeString(s);
            s = obj;
            if(isessioncallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = isessioncallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = ISession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void dispatchAdjustVolume(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void dispatchMediaKeyEvent(KeyEvent keyevent, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel1.recycle();
            parcel.recycle();
            throw keyevent;
        }

        public void dispatchVolumeKeyEvent(KeyEvent keyevent, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel1.recycle();
            parcel.recycle();
            throw keyevent;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.ISessionManager";
        }

        public List getSessions(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createBinderArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isGlobalPriorityActive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeSessionsListener(IActiveSessionsListener iactivesessionslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(iactivesessionslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iactivesessionslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iactivesessionslistener;
            parcel1.recycle();
            parcel.recycle();
            throw iactivesessionslistener;
        }

        public void setCallback(ICallback icallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(icallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icallback;
            parcel1.recycle();
            parcel.recycle();
            throw icallback;
        }

        public void setOnMediaKeyListener(IOnMediaKeyListener ionmediakeylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(ionmediakeylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionmediakeylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionmediakeylistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionmediakeylistener;
        }

        public void setOnVolumeKeyLongPressListener(IOnVolumeKeyLongPressListener ionvolumekeylongpresslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(ionvolumekeylongpresslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionvolumekeylongpresslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionvolumekeylongpresslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionvolumekeylongpresslistener;
        }

        public void setRemoteVolumeController(IRemoteVolumeController iremotevolumecontroller)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionManager");
            if(iremotevolumecontroller == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iremotevolumecontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iremotevolumecontroller;
            parcel1.recycle();
            parcel.recycle();
            throw iremotevolumecontroller;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addSessionsListener(IActiveSessionsListener iactivesessionslistener, ComponentName componentname, int i)
        throws RemoteException;

    public abstract ISession createSession(String s, ISessionCallback isessioncallback, String s1, int i)
        throws RemoteException;

    public abstract void dispatchAdjustVolume(int i, int j, int k)
        throws RemoteException;

    public abstract void dispatchMediaKeyEvent(KeyEvent keyevent, boolean flag)
        throws RemoteException;

    public abstract void dispatchVolumeKeyEvent(KeyEvent keyevent, int i, boolean flag)
        throws RemoteException;

    public abstract List getSessions(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean isGlobalPriorityActive()
        throws RemoteException;

    public abstract void removeSessionsListener(IActiveSessionsListener iactivesessionslistener)
        throws RemoteException;

    public abstract void setCallback(ICallback icallback)
        throws RemoteException;

    public abstract void setOnMediaKeyListener(IOnMediaKeyListener ionmediakeylistener)
        throws RemoteException;

    public abstract void setOnVolumeKeyLongPressListener(IOnVolumeKeyLongPressListener ionvolumekeylongpresslistener)
        throws RemoteException;

    public abstract void setRemoteVolumeController(IRemoteVolumeController iremotevolumecontroller)
        throws RemoteException;
}
