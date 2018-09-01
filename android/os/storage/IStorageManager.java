// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.content.pm.IPackageMoveObserver;
import android.os.*;
import com.android.internal.os.AppFuseMount;

// Referenced classes of package android.os.storage:
//            DiskInfo, StorageVolume, VolumeRecord, VolumeInfo, 
//            IObbActionListener, IStorageEventListener, IStorageShutdownObserver

public interface IStorageManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStorageManager
    {

        public static IStorageManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.storage.IStorageManager");
            if(iinterface != null && (iinterface instanceof IStorageManager))
                return (IStorageManager)iinterface;
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
                parcel1.writeString("android.os.storage.IStorageManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                registerListener(IStorageEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                unregisterListener(IStorageEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag = isUsbMassStorageConnected();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setUsbMassStorageEnabled(flag1);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag2 = isUsbMassStorageEnabled();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = mountVolume(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s = parcel.readString();
                boolean flag3;
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                unmountVolume(s, flag3, flag15);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = formatVolume(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getStorageUsers(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getVolumeState(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s6 = parcel.readString();
                i = parcel.readInt();
                String s7 = parcel.readString();
                String s1 = parcel.readString();
                j = parcel.readInt();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                i = createSecureContainer(s6, i, s7, s1, j, flag4);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = finalizeSecureContainer(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s2 = parcel.readString();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                i = destroySecureContainer(s2, flag5);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s3 = parcel.readString();
                String s8 = parcel.readString();
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                i = mountSecureContainer(s3, s8, i, flag6);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s4 = parcel.readString();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                i = unmountSecureContainer(s4, flag7);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag8 = isSecureContainerMounted(parcel.readString());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = renameSecureContainer(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getSecureContainerPath(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getSecureContainerList();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                shutdown(IStorageShutdownObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                finishMediaUpdate();
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                mountObb(parcel.readString(), parcel.readString(), parcel.readString(), IObbActionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                String s5 = parcel.readString();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                unmountObb(s5, flag9, IObbActionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag10 = isObbMounted(parcel.readString());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getMountedObbPath(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag11 = isExternalStorageEmulated();
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = decryptStorage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = encryptStorage(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = changeEncryptionPassword(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getVolumeList(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getSecureContainerFilesystemPath(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = getEncryptionState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = verifyEncryptionPassword(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = fixPermissionsSecureContainer(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = mkdirs(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = getPasswordType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getPassword();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                clearPassword();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.os.storage.IStorageManager");
                setField(parcel.readString(), parcel.readString());
                return true;

            case 40: // '('
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getField(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                i = resizeSecureContainer(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                long l = lastMaintenance();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                runMaintenance();
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.os.storage.IStorageManager");
                waitForAsecScan();
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getDisks();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getVolumes(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getVolumeRecords(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                mount(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                unmount(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                format(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                partitionPublic(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                partitionPrivate(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                partitionMixed(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                setVolumeNickname(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                setVolumeUserFlags(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                forgetVolume(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                forgetAllVolumes();
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = getPrimaryStorageUuid();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                setPrimaryStorageUuid(parcel.readString(), android.content.pm.IPackageMoveObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                long l1 = benchmark(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.os.storage.IStorageManager");
                setDebugFlags(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                createUserKey(j, i, flag12);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                destroyUserKey(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                unlockUserKey(parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                lockUserKey(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag13 = isUserKeyUnlocked(parcel.readInt());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                prepareUserStorage(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                destroyUserStorage(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                boolean flag14 = isConvertibleToFBE();
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                addUserKeyAuth(parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                fixateNewestUserKeyAuth(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                fstrim(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = mountProxyFileDescriptorBridge();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                parcel = openProxyFileDescriptor(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                long l2 = getCacheQuotaBytes(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                long l3 = getCacheSizeBytes(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l3);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                long l4 = getAllocatableBytes(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l4);
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                allocateBytes(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.os.storage.IStorageManager");
                secdiscard(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.storage.IStorageManager";
        static final int TRANSACTION_addUserKeyAuth = 71;
        static final int TRANSACTION_allocateBytes = 79;
        static final int TRANSACTION_benchmark = 60;
        static final int TRANSACTION_changeEncryptionPassword = 29;
        static final int TRANSACTION_clearPassword = 38;
        static final int TRANSACTION_createSecureContainer = 11;
        static final int TRANSACTION_createUserKey = 62;
        static final int TRANSACTION_decryptStorage = 27;
        static final int TRANSACTION_destroySecureContainer = 13;
        static final int TRANSACTION_destroyUserKey = 63;
        static final int TRANSACTION_destroyUserStorage = 68;
        static final int TRANSACTION_encryptStorage = 28;
        static final int TRANSACTION_finalizeSecureContainer = 12;
        static final int TRANSACTION_finishMediaUpdate = 21;
        static final int TRANSACTION_fixPermissionsSecureContainer = 34;
        static final int TRANSACTION_fixateNewestUserKeyAuth = 72;
        static final int TRANSACTION_forgetAllVolumes = 57;
        static final int TRANSACTION_forgetVolume = 56;
        static final int TRANSACTION_format = 50;
        static final int TRANSACTION_formatVolume = 8;
        static final int TRANSACTION_fstrim = 73;
        static final int TRANSACTION_getAllocatableBytes = 78;
        static final int TRANSACTION_getCacheQuotaBytes = 76;
        static final int TRANSACTION_getCacheSizeBytes = 77;
        static final int TRANSACTION_getDisks = 45;
        static final int TRANSACTION_getEncryptionState = 32;
        static final int TRANSACTION_getField = 40;
        static final int TRANSACTION_getMountedObbPath = 25;
        static final int TRANSACTION_getPassword = 37;
        static final int TRANSACTION_getPasswordType = 36;
        static final int TRANSACTION_getPrimaryStorageUuid = 58;
        static final int TRANSACTION_getSecureContainerFilesystemPath = 31;
        static final int TRANSACTION_getSecureContainerList = 19;
        static final int TRANSACTION_getSecureContainerPath = 18;
        static final int TRANSACTION_getStorageUsers = 9;
        static final int TRANSACTION_getVolumeList = 30;
        static final int TRANSACTION_getVolumeRecords = 47;
        static final int TRANSACTION_getVolumeState = 10;
        static final int TRANSACTION_getVolumes = 46;
        static final int TRANSACTION_isConvertibleToFBE = 69;
        static final int TRANSACTION_isExternalStorageEmulated = 26;
        static final int TRANSACTION_isObbMounted = 24;
        static final int TRANSACTION_isSecureContainerMounted = 16;
        static final int TRANSACTION_isUsbMassStorageConnected = 3;
        static final int TRANSACTION_isUsbMassStorageEnabled = 5;
        static final int TRANSACTION_isUserKeyUnlocked = 66;
        static final int TRANSACTION_lastMaintenance = 42;
        static final int TRANSACTION_lockUserKey = 65;
        static final int TRANSACTION_mkdirs = 35;
        static final int TRANSACTION_mount = 48;
        static final int TRANSACTION_mountObb = 22;
        static final int TRANSACTION_mountProxyFileDescriptorBridge = 74;
        static final int TRANSACTION_mountSecureContainer = 14;
        static final int TRANSACTION_mountVolume = 6;
        static final int TRANSACTION_openProxyFileDescriptor = 75;
        static final int TRANSACTION_partitionMixed = 53;
        static final int TRANSACTION_partitionPrivate = 52;
        static final int TRANSACTION_partitionPublic = 51;
        static final int TRANSACTION_prepareUserStorage = 67;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_renameSecureContainer = 17;
        static final int TRANSACTION_resizeSecureContainer = 41;
        static final int TRANSACTION_runMaintenance = 43;
        static final int TRANSACTION_secdiscard = 80;
        static final int TRANSACTION_setDebugFlags = 61;
        static final int TRANSACTION_setField = 39;
        static final int TRANSACTION_setPrimaryStorageUuid = 59;
        static final int TRANSACTION_setUsbMassStorageEnabled = 4;
        static final int TRANSACTION_setVolumeNickname = 54;
        static final int TRANSACTION_setVolumeUserFlags = 55;
        static final int TRANSACTION_shutdown = 20;
        static final int TRANSACTION_unlockUserKey = 64;
        static final int TRANSACTION_unmount = 49;
        static final int TRANSACTION_unmountObb = 23;
        static final int TRANSACTION_unmountSecureContainer = 15;
        static final int TRANSACTION_unmountVolume = 7;
        static final int TRANSACTION_unregisterListener = 2;
        static final int TRANSACTION_verifyEncryptionPassword = 33;
        static final int TRANSACTION_waitForAsecScan = 44;

        public Stub()
        {
            attachInterface(this, "android.os.storage.IStorageManager");
        }
    }

    private static class Stub.Proxy
        implements IStorageManager
    {

        public void addUserKeyAuth(int i, int j, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(71, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void allocateBytes(String s, long l, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(79, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public long benchmark(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int changeEncryptionPassword(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearPassword()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(38, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public int createSecureContainer(String s, int i, String s1, String s2, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createUserKey(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
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

        public int decryptStorage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int destroySecureContainer(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void destroyUserKey(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public void destroyUserStorage(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int encryptStorage(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int finalizeSecureContainer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void finishMediaUpdate()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public int fixPermissionsSecureContainer(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void fixateNewestUserKeyAuth(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(72, parcel, parcel1, 0);
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

        public void forgetAllVolumes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(57, parcel, parcel1, 0);
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

        public void forgetVolume(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void format(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int formatVolume(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void fstrim(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(73, parcel, parcel1, 0);
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

        public long getAllocatableBytes(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public long getCacheQuotaBytes(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public long getCacheSizeBytes(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(77, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public DiskInfo[] getDisks()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            DiskInfo adiskinfo[];
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            adiskinfo = (DiskInfo[])parcel1.createTypedArray(DiskInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return adiskinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getEncryptionState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getField(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.storage.IStorageManager";
        }

        public String getMountedObbPath(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getPassword()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getPasswordType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getPrimaryStorageUuid()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getSecureContainerFilesystemPath(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] getSecureContainerList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getSecureContainerPath(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int[] getStorageUsers(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public StorageVolume[] getVolumeList(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            s = (StorageVolume[])parcel1.createTypedArray(StorageVolume.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public VolumeRecord[] getVolumeRecords(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            VolumeRecord avolumerecord[];
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            avolumerecord = (VolumeRecord[])parcel1.createTypedArray(VolumeRecord.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return avolumerecord;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getVolumeState(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public VolumeInfo[] getVolumes(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            VolumeInfo avolumeinfo[];
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            avolumeinfo = (VolumeInfo[])parcel1.createTypedArray(VolumeInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return avolumeinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isConvertibleToFBE()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(69, parcel, parcel1, 0);
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

        public boolean isExternalStorageEmulated()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(26, parcel, parcel1, 0);
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

        public boolean isObbMounted(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isSecureContainerMounted(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isUsbMassStorageConnected()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public boolean isUsbMassStorageEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean isUserKeyUnlocked(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public long lastMaintenance()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void lockUserKey(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public int mkdirs(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void mount(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void mountObb(String s, String s1, String s2, IObbActionListener iobbactionlistener, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            s = obj;
            if(iobbactionlistener == null)
                break MISSING_BLOCK_LABEL_54;
            s = iobbactionlistener.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public AppFuseMount mountProxyFileDescriptorBridge()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            AppFuseMount appfusemount = (AppFuseMount)AppFuseMount.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return appfusemount;
_L2:
            appfusemount = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int mountSecureContainer(String s, String s1, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int mountVolume(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParcelFileDescriptor openProxyFileDescriptor(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void partitionMixed(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void partitionPrivate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void partitionPublic(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void prepareUserStorage(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerListener(IStorageEventListener istorageeventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            if(istorageeventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = istorageeventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            istorageeventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw istorageeventlistener;
        }

        public int renameSecureContainer(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int resizeSecureContainer(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void runMaintenance()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(43, parcel, parcel1, 0);
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

        public void secdiscard(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setDebugFlags(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public void setField(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(39, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setPrimaryStorageUuid(String s, IPackageMoveObserver ipackagemoveobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            s = obj;
            if(ipackagemoveobserver == null)
                break MISSING_BLOCK_LABEL_38;
            s = ipackagemoveobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setUsbMassStorageEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void setVolumeNickname(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setVolumeUserFlags(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void shutdown(IStorageShutdownObserver istorageshutdownobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            if(istorageshutdownobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = istorageshutdownobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            istorageshutdownobserver;
            parcel1.recycle();
            parcel.recycle();
            throw istorageshutdownobserver;
        }

        public void unlockUserKey(int i, int j, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void unmount(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unmountObb(String s, boolean flag, IObbActionListener iobbactionlistener, int i)
            throws RemoteException
        {
            Object obj;
            int j;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            s = obj;
            if(iobbactionlistener == null)
                break MISSING_BLOCK_LABEL_57;
            s = iobbactionlistener.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int unmountSecureContainer(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unmountVolume(String s, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unregisterListener(IStorageEventListener istorageeventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            if(istorageeventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = istorageeventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            istorageeventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw istorageeventlistener;
        }

        public int verifyEncryptionPassword(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void waitForAsecScan()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageManager");
            mRemote.transact(44, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addUserKeyAuth(int i, int j, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract void allocateBytes(String s, long l, int i, String s1)
        throws RemoteException;

    public abstract long benchmark(String s)
        throws RemoteException;

    public abstract int changeEncryptionPassword(int i, String s)
        throws RemoteException;

    public abstract void clearPassword()
        throws RemoteException;

    public abstract int createSecureContainer(String s, int i, String s1, String s2, int j, boolean flag)
        throws RemoteException;

    public abstract void createUserKey(int i, int j, boolean flag)
        throws RemoteException;

    public abstract int decryptStorage(String s)
        throws RemoteException;

    public abstract int destroySecureContainer(String s, boolean flag)
        throws RemoteException;

    public abstract void destroyUserKey(int i)
        throws RemoteException;

    public abstract void destroyUserStorage(String s, int i, int j)
        throws RemoteException;

    public abstract int encryptStorage(int i, String s)
        throws RemoteException;

    public abstract int finalizeSecureContainer(String s)
        throws RemoteException;

    public abstract void finishMediaUpdate()
        throws RemoteException;

    public abstract int fixPermissionsSecureContainer(String s, int i, String s1)
        throws RemoteException;

    public abstract void fixateNewestUserKeyAuth(int i)
        throws RemoteException;

    public abstract void forgetAllVolumes()
        throws RemoteException;

    public abstract void forgetVolume(String s)
        throws RemoteException;

    public abstract void format(String s)
        throws RemoteException;

    public abstract int formatVolume(String s)
        throws RemoteException;

    public abstract void fstrim(int i)
        throws RemoteException;

    public abstract long getAllocatableBytes(String s, int i, String s1)
        throws RemoteException;

    public abstract long getCacheQuotaBytes(String s, int i)
        throws RemoteException;

    public abstract long getCacheSizeBytes(String s, int i)
        throws RemoteException;

    public abstract DiskInfo[] getDisks()
        throws RemoteException;

    public abstract int getEncryptionState()
        throws RemoteException;

    public abstract String getField(String s)
        throws RemoteException;

    public abstract String getMountedObbPath(String s)
        throws RemoteException;

    public abstract String getPassword()
        throws RemoteException;

    public abstract int getPasswordType()
        throws RemoteException;

    public abstract String getPrimaryStorageUuid()
        throws RemoteException;

    public abstract String getSecureContainerFilesystemPath(String s)
        throws RemoteException;

    public abstract String[] getSecureContainerList()
        throws RemoteException;

    public abstract String getSecureContainerPath(String s)
        throws RemoteException;

    public abstract int[] getStorageUsers(String s)
        throws RemoteException;

    public abstract StorageVolume[] getVolumeList(int i, String s, int j)
        throws RemoteException;

    public abstract VolumeRecord[] getVolumeRecords(int i)
        throws RemoteException;

    public abstract String getVolumeState(String s)
        throws RemoteException;

    public abstract VolumeInfo[] getVolumes(int i)
        throws RemoteException;

    public abstract boolean isConvertibleToFBE()
        throws RemoteException;

    public abstract boolean isExternalStorageEmulated()
        throws RemoteException;

    public abstract boolean isObbMounted(String s)
        throws RemoteException;

    public abstract boolean isSecureContainerMounted(String s)
        throws RemoteException;

    public abstract boolean isUsbMassStorageConnected()
        throws RemoteException;

    public abstract boolean isUsbMassStorageEnabled()
        throws RemoteException;

    public abstract boolean isUserKeyUnlocked(int i)
        throws RemoteException;

    public abstract long lastMaintenance()
        throws RemoteException;

    public abstract void lockUserKey(int i)
        throws RemoteException;

    public abstract int mkdirs(String s, String s1)
        throws RemoteException;

    public abstract void mount(String s)
        throws RemoteException;

    public abstract void mountObb(String s, String s1, String s2, IObbActionListener iobbactionlistener, int i)
        throws RemoteException;

    public abstract AppFuseMount mountProxyFileDescriptorBridge()
        throws RemoteException;

    public abstract int mountSecureContainer(String s, String s1, int i, boolean flag)
        throws RemoteException;

    public abstract int mountVolume(String s)
        throws RemoteException;

    public abstract ParcelFileDescriptor openProxyFileDescriptor(int i, int j, int k)
        throws RemoteException;

    public abstract void partitionMixed(String s, int i)
        throws RemoteException;

    public abstract void partitionPrivate(String s)
        throws RemoteException;

    public abstract void partitionPublic(String s)
        throws RemoteException;

    public abstract void prepareUserStorage(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void registerListener(IStorageEventListener istorageeventlistener)
        throws RemoteException;

    public abstract int renameSecureContainer(String s, String s1)
        throws RemoteException;

    public abstract int resizeSecureContainer(String s, int i, String s1)
        throws RemoteException;

    public abstract void runMaintenance()
        throws RemoteException;

    public abstract void secdiscard(String s)
        throws RemoteException;

    public abstract void setDebugFlags(int i, int j)
        throws RemoteException;

    public abstract void setField(String s, String s1)
        throws RemoteException;

    public abstract void setPrimaryStorageUuid(String s, IPackageMoveObserver ipackagemoveobserver)
        throws RemoteException;

    public abstract void setUsbMassStorageEnabled(boolean flag)
        throws RemoteException;

    public abstract void setVolumeNickname(String s, String s1)
        throws RemoteException;

    public abstract void setVolumeUserFlags(String s, int i, int j)
        throws RemoteException;

    public abstract void shutdown(IStorageShutdownObserver istorageshutdownobserver)
        throws RemoteException;

    public abstract void unlockUserKey(int i, int j, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract void unmount(String s)
        throws RemoteException;

    public abstract void unmountObb(String s, boolean flag, IObbActionListener iobbactionlistener, int i)
        throws RemoteException;

    public abstract int unmountSecureContainer(String s, boolean flag)
        throws RemoteException;

    public abstract void unmountVolume(String s, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void unregisterListener(IStorageEventListener istorageeventlistener)
        throws RemoteException;

    public abstract int verifyEncryptionPassword(String s)
        throws RemoteException;

    public abstract void waitForAsecScan()
        throws RemoteException;
}
