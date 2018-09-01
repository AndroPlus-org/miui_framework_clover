// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.cas.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.*;
import java.util.*;

public interface ICasListener
    extends IBase
{
    public static final class Proxy
        implements ICasListener
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
            HwBlob hwblob;
            int i;
            mRemote.transact(0xf485348, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = JVM INSTR new #67  <Class ArrayList>;
            ((ArrayList) (obj)).ArrayList();
            hwblob = hwparcel.readBuffer(16L);
            i = hwblob.getInt32(8L);
            hwblob = hwparcel.readEmbeddedBuffer(i * 32, hwblob.handle(), 0L, true);
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
            abyte0[k] = hwblob.getInt8(l);
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

        public void onEvent(int i, int j, ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICasListener");
            hwparcel.writeInt32(i);
            hwparcel.writeInt32(j);
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            mRemote.transact(1, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            arraylist.release();
            return;
            Exception exception;
            exception;
            arraylist.release();
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
                obj = JVM INSTR new #141 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                obj = ((StringBuilder) (obj)).append(interfaceDescriptor()).append("@Proxy").toString();
            }
            catch(RemoteException remoteexception)
            {
                return "[class or subclass of android.hardware.cas@1.0::ICasListener]@Proxy";
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
        implements ICasListener
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
            byte abyte0[] = {
                -67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 
                125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 
                54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 
                15, -39
            };
            return new ArrayList(Arrays.asList(new byte[][] {
                new byte[] {
                    -72, 14, 20, 86, -72, 31, -128, 3, 45, 13, 
                    -25, -53, 69, 101, 42, -63, 90, -15, 30, 116, 
                    116, -43, 32, -41, 87, 72, 30, -54, -83, 121, 
                    109, -1
                }, abyte0
            }));
        }

        public final ArrayList interfaceChain()
        {
            return new ArrayList(Arrays.asList(new String[] {
                "android.hardware.cas@1.0::ICasListener", "android.hidl.base@1.0::IBase"
            }));
        }

        public final String interfaceDescriptor()
        {
            return "android.hardware.cas@1.0::ICasListener";
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
            JVM INSTR lookupswitch 11: default 100
        //                       1: 101
        //                       256067662: 135
        //                       256131655: 163
        //                       256136003: 181
        //                       256398152: 209
        //                       256462420: 355
        //                       256660548: 100
        //                       256921159: 100
        //                       257049926: 368
        //                       257120595: 396
        //                       257250372: 100;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L1 _L1 _L8 _L9 _L1
_L1:
            return;
_L2:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICasListener");
            onEvent(hwparcel.readInt32(), hwparcel.readInt32(), hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L3:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceChain();
            hwparcel1.writeStatus(0);
            hwparcel1.writeStringVector(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L4:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel1.writeStatus(0);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L5:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceDescriptor();
            hwparcel1.writeStatus(0);
            hwparcel1.writeString(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L6:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            ArrayList arraylist = getHashChain();
            hwparcel1.writeStatus(0);
            hwparcel = new HwBlob(16);
            int k = arraylist.size();
            hwparcel.putInt32(8L, k);
            hwparcel.putBool(12L, false);
            HwBlob hwblob = new HwBlob(k * 32);
            for(i = 0; i < k; i++)
            {
                long l = i * 32;
                for(j = 0; j < 32; j++)
                {
                    hwblob.putInt8(l, ((byte[])arraylist.get(i))[j]);
                    l++;
                }

            }

            hwparcel.putBlob(0L, hwblob);
            hwparcel1.writeBuffer(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L7:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
            continue; /* Loop/switch isn't completed */
_L8:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = getDebugInfo();
            hwparcel1.writeStatus(0);
            hwparcel.writeToParcel(hwparcel1);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L9:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            notifySyspropsChanged();
            if(true) goto _L1; else goto _L10
_L10:
        }

        public final void ping()
        {
        }

        public IHwInterface queryLocalInterface(String s)
        {
            if("android.hardware.cas@1.0::ICasListener".equals(s))
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


    public static ICasListener asInterface(IHwBinder ihwbinder)
    {
        Object obj;
        if(ihwbinder == null)
            return null;
        obj = ihwbinder.queryLocalInterface("android.hardware.cas@1.0::ICasListener");
        if(obj != null && (obj instanceof ICasListener))
            return (ICasListener)obj;
        obj = new Proxy(ihwbinder);
        ihwbinder = ((ICasListener) (obj)).interfaceChain().iterator();
        boolean flag;
        do
        {
            if(!ihwbinder.hasNext())
                break MISSING_BLOCK_LABEL_83;
            flag = ((String)ihwbinder.next()).equals("android.hardware.cas@1.0::ICasListener");
        } while(!flag);
        return ((ICasListener) (obj));
        ihwbinder;
        return null;
    }

    public static ICasListener castFrom(IHwInterface ihwinterface)
    {
        Object obj = null;
        if(ihwinterface == null)
            ihwinterface = obj;
        else
            ihwinterface = asInterface(ihwinterface.asBinder());
        return ihwinterface;
    }

    public static ICasListener getService()
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::ICasListener", "default"));
    }

    public static ICasListener getService(String s)
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::ICasListener", s));
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

    public abstract void onEvent(int i, int j, ArrayList arraylist)
        throws RemoteException;

    public abstract void ping()
        throws RemoteException;

    public abstract void setHALInstrumentation()
        throws RemoteException;

    public abstract boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient)
        throws RemoteException;

    public static final String kInterfaceName = "android.hardware.cas@1.0::ICasListener";
}
