// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.*;

public interface ITaskStackListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITaskStackListener
    {

        public static ITaskStackListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.ITaskStackListener");
            if(iinterface != null && (iinterface instanceof ITaskStackListener))
                return (ITaskStackListener)iinterface;
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
                parcel1.writeString("android.app.ITaskStackListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onTaskStackChanged();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityPinned(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityUnpinned();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.ITaskStackListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onPinnedActivityRestartAttempt(flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onPinnedStackAnimationStarted();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onPinnedStackAnimationEnded();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityForcedResizable(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityDismissingDockedStack();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityLaunchOnSecondaryDisplayFailed();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.ITaskStackListener");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onTaskCreated(i, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onTaskRemoved(parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onTaskMovedToFront(parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.ITaskStackListener");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onTaskDescriptionChanged(i, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onActivityRequestedOrientationChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onTaskRemovalStarted(parcel.readInt());
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.ITaskStackListener");
                onTaskProfileLocked(parcel.readInt(), parcel.readInt());
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.ITaskStackListener");
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onTaskSnapshotChanged(i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.ITaskStackListener";
        static final int TRANSACTION_onActivityDismissingDockedStack = 8;
        static final int TRANSACTION_onActivityForcedResizable = 7;
        static final int TRANSACTION_onActivityLaunchOnSecondaryDisplayFailed = 9;
        static final int TRANSACTION_onActivityPinned = 2;
        static final int TRANSACTION_onActivityRequestedOrientationChanged = 14;
        static final int TRANSACTION_onActivityUnpinned = 3;
        static final int TRANSACTION_onPinnedActivityRestartAttempt = 4;
        static final int TRANSACTION_onPinnedStackAnimationEnded = 6;
        static final int TRANSACTION_onPinnedStackAnimationStarted = 5;
        static final int TRANSACTION_onTaskCreated = 10;
        static final int TRANSACTION_onTaskDescriptionChanged = 13;
        static final int TRANSACTION_onTaskMovedToFront = 12;
        static final int TRANSACTION_onTaskProfileLocked = 16;
        static final int TRANSACTION_onTaskRemovalStarted = 15;
        static final int TRANSACTION_onTaskRemoved = 11;
        static final int TRANSACTION_onTaskSnapshotChanged = 17;
        static final int TRANSACTION_onTaskStackChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.app.ITaskStackListener");
        }
    }

    private static class Stub.Proxy
        implements ITaskStackListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.ITaskStackListener";
        }

        public void onActivityDismissingDockedStack()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onActivityForcedResizable(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onActivityLaunchOnSecondaryDisplayFailed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onActivityPinned(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onActivityRequestedOrientationChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onActivityUnpinned()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPinnedActivityRestartAttempt(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPinnedStackAnimationEnded()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPinnedStackAnimationStarted()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTaskCreated(int i, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void onTaskDescriptionChanged(int i, ActivityManager.TaskDescription taskdescription)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            if(taskdescription == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            taskdescription.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            taskdescription;
            parcel.recycle();
            throw taskdescription;
        }

        public void onTaskMovedToFront(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTaskProfileLocked(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTaskRemovalStarted(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTaskRemoved(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTaskSnapshotChanged(int i, ActivityManager.TaskSnapshot tasksnapshot)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            parcel.writeInt(i);
            if(tasksnapshot == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            tasksnapshot.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tasksnapshot;
            parcel.recycle();
            throw tasksnapshot;
        }

        public void onTaskStackChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ITaskStackListener");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onActivityDismissingDockedStack()
        throws RemoteException;

    public abstract void onActivityForcedResizable(String s, int i, int j)
        throws RemoteException;

    public abstract void onActivityLaunchOnSecondaryDisplayFailed()
        throws RemoteException;

    public abstract void onActivityPinned(String s, int i, int j)
        throws RemoteException;

    public abstract void onActivityRequestedOrientationChanged(int i, int j)
        throws RemoteException;

    public abstract void onActivityUnpinned()
        throws RemoteException;

    public abstract void onPinnedActivityRestartAttempt(boolean flag)
        throws RemoteException;

    public abstract void onPinnedStackAnimationEnded()
        throws RemoteException;

    public abstract void onPinnedStackAnimationStarted()
        throws RemoteException;

    public abstract void onTaskCreated(int i, ComponentName componentname)
        throws RemoteException;

    public abstract void onTaskDescriptionChanged(int i, ActivityManager.TaskDescription taskdescription)
        throws RemoteException;

    public abstract void onTaskMovedToFront(int i)
        throws RemoteException;

    public abstract void onTaskProfileLocked(int i, int j)
        throws RemoteException;

    public abstract void onTaskRemovalStarted(int i)
        throws RemoteException;

    public abstract void onTaskRemoved(int i)
        throws RemoteException;

    public abstract void onTaskSnapshotChanged(int i, ActivityManager.TaskSnapshot tasksnapshot)
        throws RemoteException;

    public abstract void onTaskStackChanged()
        throws RemoteException;

    public static final int FORCED_RESIZEABLE_REASON_SECONDARY_DISPLAY = 2;
    public static final int FORCED_RESIZEABLE_REASON_SPLIT_SCREEN = 1;
}
