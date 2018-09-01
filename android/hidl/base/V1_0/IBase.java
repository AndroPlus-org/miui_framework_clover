// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hidl.base.V1_0;

import android.os.*;
import java.util.*;

// Referenced classes of package android.hidl.base.V1_0:
//            DebugInfo

public interface IBase
    extends IHwInterface
{
    public static final class Proxy
        implements IBase
    {

        public IHwBinder asBinder()
        {
            return mRemote;
        }

        public DebugInfo getDebugInfo()
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel = new HwParcel();
            mRemote.transact(0xf524546, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = JVM INSTR new #53  <Class DebugInfo>;
            ((DebugInfo) (obj)).DebugInfo();
            ((DebugInfo) (obj)).readFromParcel(hwparcel);
            hwparcel.release();
            return ((DebugInfo) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
        }

        public ArrayList getHashChain()
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel = new HwParcel();
            int i;
            HwBlob hwblob1;
            mRemote.transact(0xf485348, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = JVM INSTR new #67  <Class ArrayList>;
            ((ArrayList) (obj)).ArrayList();
            HwBlob hwblob = hwparcel.readBuffer(16L);
            i = hwblob.getInt32(8L);
            hwblob1 = hwparcel.readEmbeddedBuffer(i * 32, hwblob.handle(), 0L, true);
            ((ArrayList) (obj)).clear();
            int j = 0;
_L4:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            byte abyte0[] = new byte[32];
            long l;
            int k;
            l = j * 32;
            k = 0;
_L2:
            if(k >= 32)
                break; /* Loop/switch isn't completed */
            abyte0[k] = hwblob1.getInt8(l);
            l++;
            k++;
            if(true) goto _L2; else goto _L1
_L1:
            ((ArrayList) (obj)).add(abyte0);
            j++;
            if(true) goto _L4; else goto _L3
_L3:
            hwparcel.release();
            return ((ArrayList) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
        }

        public ArrayList interfaceChain()
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel = new HwParcel();
            mRemote.transact(0xf43484e, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = hwparcel.readStringVector();
            hwparcel.release();
            return ((ArrayList) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
        }

        public String interfaceDescriptor()
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel = new HwParcel();
            mRemote.transact(0xf445343, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = hwparcel.readString();
            hwparcel.release();
            return ((String) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
        }

        public boolean linkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient, long l)
            throws RemoteException
        {
            return mRemote.linkToDeath(deathrecipient, l);
        }

        public void notifySyspropsChanged()
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel1 = new HwParcel();
            mRemote.transact(0xf535953, hwparcel, hwparcel1, 1);
            hwparcel.releaseTemporaryStorage();
            hwparcel1.release();
            return;
            Exception exception;
            exception;
            hwparcel1.release();
            throw exception;
        }

        public void ping()
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel1 = new HwParcel();
            mRemote.transact(0xf504e47, hwparcel, hwparcel1, 0);
            hwparcel1.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            hwparcel1.release();
            return;
            Exception exception;
            exception;
            hwparcel1.release();
            throw exception;
        }

        public void setHALInstrumentation()
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            hwparcel1 = new HwParcel();
            mRemote.transact(0xf494e54, hwparcel, hwparcel1, 1);
            hwparcel.releaseTemporaryStorage();
            hwparcel1.release();
            return;
            Exception exception;
            exception;
            hwparcel1.release();
            throw exception;
        }

        public String toString()
        {
            Object obj;
            try
            {
                obj = JVM INSTR new #128 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                obj = ((StringBuilder) (obj)).append(interfaceDescriptor()).append("@Proxy").toString();
            }
            catch(RemoteException remoteexception)
            {
                return "[class or subclass of android.hidl.base@1.0::IBase]@Proxy";
            }
            return ((String) (obj));
        }

        public boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient)
            throws RemoteException
        {
            return mRemote.unlinkToDeath(deathrecipient);
        }

        private IHwBinder mRemote;

        public Proxy(IHwBinder ihwbinder)
        {
            mRemote = (IHwBinder)Objects.requireNonNull(ihwbinder);
        }
    }

    public static abstract class Stub extends HwBinder
        implements IBase
    {

        public IHwBinder asBinder()
        {
            return this;
        }

        public final DebugInfo getDebugInfo()
        {
            DebugInfo debuginfo = new DebugInfo();
            debuginfo.pid = -1;
            debuginfo.ptr = 0L;
            debuginfo.arch = 0;
            return debuginfo;
        }

        public final ArrayList getHashChain()
        {
            return new ArrayList(Arrays.asList(new byte[][] {
                new byte[] {
                    -67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 
                    125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 
                    54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 
                    15, -39
                }
            }));
        }

        public final ArrayList interfaceChain()
        {
            return new ArrayList(Arrays.asList(new String[] {
                "android.hidl.base@1.0::IBase"
            }));
        }

        public final String interfaceDescriptor()
        {
            return "android.hidl.base@1.0::IBase";
        }

        public final boolean linkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient, long l)
        {
            return true;
        }

        public final void notifySyspropsChanged()
        {
            SystemProperties.reportSyspropChanged();
        }

        public void onTransact(int i, HwParcel hwparcel, HwParcel hwparcel1, int j)
            throws RemoteException
        {
            i;
            JVM INSTR lookupswitch 10: default 92
        //                       256067662: 93
        //                       256131655: 121
        //                       256136003: 139
        //                       256398152: 167
        //                       256462420: 315
        //                       256660548: 92
        //                       256921159: 92
        //                       257049926: 328
        //                       257120595: 356
        //                       257250372: 92;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L1 _L7 _L8 _L1
_L1:
            return;
_L2:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceChain();
            hwparcel1.writeStatus(0);
            hwparcel1.writeStringVector(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L3:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel1.writeStatus(0);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L4:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceDescriptor();
            hwparcel1.writeStatus(0);
            hwparcel1.writeString(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L5:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = getHashChain();
            hwparcel1.writeStatus(0);
            HwBlob hwblob = new HwBlob(16);
            int k = hwparcel.size();
            hwblob.putInt32(8L, k);
            hwblob.putBool(12L, false);
            HwBlob hwblob1 = new HwBlob(k * 32);
            for(i = 0; i < k; i++)
            {
                long l = i * 32;
                for(j = 0; j < 32; j++)
                {
                    hwblob1.putInt8(l, ((byte[])hwparcel.get(i))[j]);
                    l++;
                }

            }

            hwblob.putBlob(0L, hwblob1);
            hwparcel1.writeBuffer(hwblob);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L6:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
            continue; /* Loop/switch isn't completed */
_L7:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = getDebugInfo();
            hwparcel1.writeStatus(0);
            hwparcel.writeToParcel(hwparcel1);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L8:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            notifySyspropsChanged();
            if(true) goto _L1; else goto _L9
_L9:
        }

        public final void ping()
        {
        }

        public IHwInterface queryLocalInterface(String s)
        {
            if("android.hidl.base@1.0::IBase".equals(s))
                return this;
            else
                return null;
        }

        public void registerAsService(String s)
            throws RemoteException
        {
            registerService(s);
        }

        public final void setHALInstrumentation()
        {
        }

        public String toString()
        {
            return (new StringBuilder()).append(interfaceDescriptor()).append("@Stub").toString();
        }

        public final boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient)
        {
            return true;
        }

        public Stub()
        {
        }
    }


    public static IBase asInterface(IHwBinder ihwbinder)
    {
        if(ihwbinder == null)
            return null;
        IHwInterface ihwinterface = ihwbinder.queryLocalInterface("android.hidl.base@1.0::IBase");
        if(ihwinterface != null && (ihwinterface instanceof IBase))
            return (IBase)ihwinterface;
        ihwbinder = new Proxy(ihwbinder);
        Iterator iterator = ihwbinder.interfaceChain().iterator();
        boolean flag;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_83;
            flag = ((String)iterator.next()).equals("android.hidl.base@1.0::IBase");
        } while(!flag);
        return ihwbinder;
        ihwbinder;
        return null;
    }

    public static IBase castFrom(IHwInterface ihwinterface)
    {
        Object obj = null;
        if(ihwinterface == null)
            ihwinterface = obj;
        else
            ihwinterface = asInterface(ihwinterface.asBinder());
        return ihwinterface;
    }

    public static IBase getService()
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hidl.base@1.0::IBase", "default"));
    }

    public static IBase getService(String s)
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hidl.base@1.0::IBase", s));
    }

    public abstract IHwBinder asBinder();

    public abstract DebugInfo getDebugInfo()
        throws RemoteException;

    public abstract ArrayList getHashChain()
        throws RemoteException;

    public abstract ArrayList interfaceChain()
        throws RemoteException;

    public abstract String interfaceDescriptor()
        throws RemoteException;

    public abstract boolean linkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient, long l)
        throws RemoteException;

    public abstract void notifySyspropsChanged()
        throws RemoteException;

    public abstract void ping()
        throws RemoteException;

    public abstract void setHALInstrumentation()
        throws RemoteException;

    public abstract boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient)
        throws RemoteException;

    public static final String kInterfaceName = "android.hidl.base@1.0::IBase";
}
