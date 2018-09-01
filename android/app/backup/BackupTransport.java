// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.*;
import com.android.internal.backup.IBackupTransport;

// Referenced classes of package android.app.backup:
//            RestoreSet, RestoreDescription

public class BackupTransport
{
    class TransportImpl extends com.android.internal.backup.IBackupTransport.Stub
    {

        public int abortFullRestore()
        {
            return BackupTransport.this.abortFullRestore();
        }

        public void cancelFullBackup()
            throws RemoteException
        {
            BackupTransport.this.cancelFullBackup();
        }

        public int checkFullBackupSize(long l)
        {
            return BackupTransport.this.checkFullBackupSize(l);
        }

        public int clearBackupData(PackageInfo packageinfo)
            throws RemoteException
        {
            return BackupTransport.this.clearBackupData(packageinfo);
        }

        public Intent configurationIntent()
            throws RemoteException
        {
            return BackupTransport.this.configurationIntent();
        }

        public String currentDestinationString()
            throws RemoteException
        {
            return BackupTransport.this.currentDestinationString();
        }

        public Intent dataManagementIntent()
        {
            return BackupTransport.this.dataManagementIntent();
        }

        public String dataManagementLabel()
        {
            return BackupTransport.this.dataManagementLabel();
        }

        public int finishBackup()
            throws RemoteException
        {
            return BackupTransport.this.finishBackup();
        }

        public void finishRestore()
            throws RemoteException
        {
            BackupTransport.this.finishRestore();
        }

        public RestoreSet[] getAvailableRestoreSets()
            throws RemoteException
        {
            return BackupTransport.this.getAvailableRestoreSets();
        }

        public long getBackupQuota(String s, boolean flag)
        {
            return BackupTransport.this.getBackupQuota(s, flag);
        }

        public long getCurrentRestoreSet()
            throws RemoteException
        {
            return BackupTransport.this.getCurrentRestoreSet();
        }

        public int getNextFullRestoreDataChunk(ParcelFileDescriptor parcelfiledescriptor)
        {
            return BackupTransport.this.getNextFullRestoreDataChunk(parcelfiledescriptor);
        }

        public int getRestoreData(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            return BackupTransport.this.getRestoreData(parcelfiledescriptor);
        }

        public int initializeDevice()
            throws RemoteException
        {
            return BackupTransport.this.initializeDevice();
        }

        public boolean isAppEligibleForBackup(PackageInfo packageinfo, boolean flag)
            throws RemoteException
        {
            return BackupTransport.this.isAppEligibleForBackup(packageinfo, flag);
        }

        public String name()
            throws RemoteException
        {
            return BackupTransport.this.name();
        }

        public RestoreDescription nextRestorePackage()
            throws RemoteException
        {
            return BackupTransport.this.nextRestorePackage();
        }

        public int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            return BackupTransport.this.performBackup(packageinfo, parcelfiledescriptor, i);
        }

        public int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            return BackupTransport.this.performFullBackup(packageinfo, parcelfiledescriptor, i);
        }

        public long requestBackupTime()
            throws RemoteException
        {
            return BackupTransport.this.requestBackupTime();
        }

        public long requestFullBackupTime()
            throws RemoteException
        {
            return BackupTransport.this.requestFullBackupTime();
        }

        public int sendBackupData(int i)
            throws RemoteException
        {
            return BackupTransport.this.sendBackupData(i);
        }

        public int startRestore(long l, PackageInfo apackageinfo[])
            throws RemoteException
        {
            return BackupTransport.this.startRestore(l, apackageinfo);
        }

        public String transportDirName()
            throws RemoteException
        {
            return BackupTransport.this.transportDirName();
        }

        final BackupTransport this$0;

        TransportImpl()
        {
            this$0 = BackupTransport.this;
            super();
        }
    }


    public BackupTransport()
    {
        mBinderImpl = new TransportImpl();
    }

    public int abortFullRestore()
    {
        return 0;
    }

    public void cancelFullBackup()
    {
        throw new UnsupportedOperationException("Transport cancelFullBackup() not implemented");
    }

    public int checkFullBackupSize(long l)
    {
        return 0;
    }

    public int clearBackupData(PackageInfo packageinfo)
    {
        return -1000;
    }

    public Intent configurationIntent()
    {
        return null;
    }

    public String currentDestinationString()
    {
        throw new UnsupportedOperationException("Transport currentDestinationString() not implemented");
    }

    public Intent dataManagementIntent()
    {
        return null;
    }

    public String dataManagementLabel()
    {
        throw new UnsupportedOperationException("Transport dataManagementLabel() not implemented");
    }

    public int finishBackup()
    {
        return -1000;
    }

    public void finishRestore()
    {
        throw new UnsupportedOperationException("Transport finishRestore() not implemented");
    }

    public RestoreSet[] getAvailableRestoreSets()
    {
        return null;
    }

    public long getBackupQuota(String s, boolean flag)
    {
        return 0x7fffffffffffffffL;
    }

    public IBinder getBinder()
    {
        return mBinderImpl.asBinder();
    }

    public long getCurrentRestoreSet()
    {
        return 0L;
    }

    public int getNextFullRestoreDataChunk(ParcelFileDescriptor parcelfiledescriptor)
    {
        return 0;
    }

    public int getRestoreData(ParcelFileDescriptor parcelfiledescriptor)
    {
        return -1000;
    }

    public int initializeDevice()
    {
        return -1000;
    }

    public boolean isAppEligibleForBackup(PackageInfo packageinfo, boolean flag)
    {
        return true;
    }

    public String name()
    {
        throw new UnsupportedOperationException("Transport name() not implemented");
    }

    public RestoreDescription nextRestorePackage()
    {
        return null;
    }

    public int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor)
    {
        return -1000;
    }

    public int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
    {
        return performBackup(packageinfo, parcelfiledescriptor);
    }

    public int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor)
    {
        return -1002;
    }

    public int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
    {
        return performFullBackup(packageinfo, parcelfiledescriptor);
    }

    public long requestBackupTime()
    {
        return 0L;
    }

    public long requestFullBackupTime()
    {
        return 0L;
    }

    public int sendBackupData(int i)
    {
        return -1000;
    }

    public int startRestore(long l, PackageInfo apackageinfo[])
    {
        return -1000;
    }

    public String transportDirName()
    {
        throw new UnsupportedOperationException("Transport transportDirName() not implemented");
    }

    public static final int AGENT_ERROR = -1003;
    public static final int AGENT_UNKNOWN = -1004;
    public static final int FLAG_USER_INITIATED = 1;
    public static final int NO_MORE_DATA = -1;
    public static final int TRANSPORT_ERROR = -1000;
    public static final int TRANSPORT_NOT_INITIALIZED = -1001;
    public static final int TRANSPORT_OK = 0;
    public static final int TRANSPORT_PACKAGE_REJECTED = -1002;
    public static final int TRANSPORT_QUOTA_EXCEEDED = -1005;
    IBackupTransport mBinderImpl;
}
