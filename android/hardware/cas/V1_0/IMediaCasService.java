// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.cas.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.*;
import java.util.*;

// Referenced classes of package android.hardware.cas.V1_0:
//            IDescramblerBase, ICasListener, ICas, HidlCasPluginDescriptor

public interface IMediaCasService
    extends IBase
{
    public static final class Proxy
        implements IMediaCasService
    {

        public IHwBinder asBinder()
        {
            return mRemote;
        }

        public IDescramblerBase createDescrambler(int i)
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
            ((HwParcel) (obj)).writeInt32(i);
            hwparcel = new HwParcel();
            mRemote.transact(5, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = IDescramblerBase.asInterface(hwparcel.readStrongBinder());
            hwparcel.release();
            return ((IDescramblerBase) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
        }

        public ICas createPlugin(int i, ICasListener icaslistener)
            throws RemoteException
        {
            ICas icas = null;
            HwParcel hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
            hwparcel.writeInt32(i);
            if(icaslistener == null)
                icaslistener = icas;
            else
                icaslistener = icaslistener.asBinder();
            hwparcel.writeStrongBinder(icaslistener);
            icaslistener = new HwParcel();
            mRemote.transact(3, hwparcel, icaslistener, 0);
            icaslistener.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            icas = ICas.asInterface(icaslistener.readStrongBinder());
            icaslistener.release();
            return icas;
            Exception exception;
            exception;
            icaslistener.release();
            throw exception;
        }

        public ArrayList enumeratePlugins()
            throws RemoteException
        {
            Object obj;
            HwParcel hwparcel;
            obj = new HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
            hwparcel = new HwParcel();
            mRemote.transact(1, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = HidlCasPluginDescriptor.readVectorFromParcel(hwparcel);
            hwparcel.release();
            return ((ArrayList) (obj));
            Exception exception;
            exception;
            hwparcel.release();
            throw exception;
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
            obj = JVM INSTR new #98  <Class DebugInfo>;
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
            obj = JVM INSTR new #107 <Class ArrayList>;
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

        public boolean isDescramblerSupported(int i)
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
            hwparcel.writeInt32(i);
            hwparcel1 = new HwParcel();
            boolean flag;
            mRemote.transact(4, hwparcel, hwparcel1, 0);
            hwparcel1.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            flag = hwparcel1.readBool();
            hwparcel1.release();
            return flag;
            Exception exception;
            exception;
            hwparcel1.release();
            throw exception;
        }

        public boolean isSystemIdSupported(int i)
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
            hwparcel.writeInt32(i);
            hwparcel1 = new HwParcel();
            boolean flag;
            mRemote.transact(2, hwparcel, hwparcel1, 0);
            hwparcel1.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            flag = hwparcel1.readBool();
            hwparcel1.release();
            return flag;
            Exception exception;
            exception;
            hwparcel1.release();
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
                obj = JVM INSTR new #174 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                obj = ((StringBuilder) (obj)).append(interfaceDescriptor()).append("@Proxy").toString();
            }
            catch(RemoteException remoteexception)
            {
                return "[class or subclass of android.hardware.cas@1.0::IMediaCasService]@Proxy";
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
        implements IMediaCasService
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
                    -122, -70, -100, 3, -105, -117, 121, -89, 66, -23, 
                    -112, 66, 11, -59, -50, -48, 103, 61, 37, -87, 
                    57, -8, 37, 114, -103, 107, -17, -110, 98, 30, 
                    32, 20
                }, new byte[] {
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
                "android.hardware.cas@1.0::IMediaCasService", "android.hidl.base@1.0::IBase"
            }));
        }

        public final String interfaceDescriptor()
        {
            return "android.hardware.cas@1.0::IMediaCasService";
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
            JVM INSTR lookupswitch 15: default 132
        //                       1: 133
        //                       2: 161
        //                       3: 195
        //                       4: 250
        //                       5: 284
        //                       256067662: 332
        //                       256131655: 360
        //                       256136003: 378
        //                       256398152: 406
        //                       256462420: 552
        //                       256660548: 132
        //                       256921159: 132
        //                       257049926: 565
        //                       257120595: 593
        //                       257250372: 132;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L1 _L1 _L12 _L13 _L1
_L1:
            return;
_L2:
            hwparcel.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            hwparcel = enumeratePlugins();
            hwparcel1.writeStatus(0);
            HidlCasPluginDescriptor.writeVectorToParcel(hwparcel1, hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L3:
            hwparcel.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            boolean flag = isSystemIdSupported(hwparcel.readInt32());
            hwparcel1.writeStatus(0);
            hwparcel1.writeBool(flag);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L4:
            hwparcel.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            hwparcel = createPlugin(hwparcel.readInt32(), ICasListener.asInterface(hwparcel.readStrongBinder()));
            hwparcel1.writeStatus(0);
            if(hwparcel == null)
                hwparcel = null;
            else
                hwparcel = hwparcel.asBinder();
            hwparcel1.writeStrongBinder(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L5:
            hwparcel.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            boolean flag1 = isDescramblerSupported(hwparcel.readInt32());
            hwparcel1.writeStatus(0);
            hwparcel1.writeBool(flag1);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L6:
            hwparcel.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            hwparcel = createDescrambler(hwparcel.readInt32());
            hwparcel1.writeStatus(0);
            if(hwparcel == null)
                hwparcel = null;
            else
                hwparcel = hwparcel.asBinder();
            hwparcel1.writeStrongBinder(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L7:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceChain();
            hwparcel1.writeStatus(0);
            hwparcel1.writeStringVector(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L8:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel1.writeStatus(0);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L9:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceDescriptor();
            hwparcel1.writeStatus(0);
            hwparcel1.writeString(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L10:
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
_L11:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
            continue; /* Loop/switch isn't completed */
_L12:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = getDebugInfo();
            hwparcel1.writeStatus(0);
            hwparcel.writeToParcel(hwparcel1);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L13:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            notifySyspropsChanged();
            if(true) goto _L1; else goto _L14
_L14:
        }

        public final void ping()
        {
        }

        public IHwInterface queryLocalInterface(String s)
        {
            if("android.hardware.cas@1.0::IMediaCasService".equals(s))
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


    public static IMediaCasService asInterface(IHwBinder ihwbinder)
    {
        Object obj;
        if(ihwbinder == null)
            return null;
        obj = ihwbinder.queryLocalInterface("android.hardware.cas@1.0::IMediaCasService");
        if(obj != null && (obj instanceof IMediaCasService))
            return (IMediaCasService)obj;
        obj = new Proxy(ihwbinder);
        ihwbinder = ((IMediaCasService) (obj)).interfaceChain().iterator();
        boolean flag;
        do
        {
            if(!ihwbinder.hasNext())
                break MISSING_BLOCK_LABEL_83;
            flag = ((String)ihwbinder.next()).equals("android.hardware.cas@1.0::IMediaCasService");
        } while(!flag);
        return ((IMediaCasService) (obj));
        ihwbinder;
        return null;
    }

    public static IMediaCasService castFrom(IHwInterface ihwinterface)
    {
        Object obj = null;
        if(ihwinterface == null)
            ihwinterface = obj;
        else
            ihwinterface = asInterface(ihwinterface.asBinder());
        return ihwinterface;
    }

    public static IMediaCasService getService()
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::IMediaCasService", "default"));
    }

    public static IMediaCasService getService(String s)
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::IMediaCasService", s));
    }

    public abstract IHwBinder asBinder();

    public abstract IDescramblerBase createDescrambler(int i)
        throws RemoteException;

    public abstract ICas createPlugin(int i, ICasListener icaslistener)
        throws RemoteException;

    public abstract ArrayList enumeratePlugins()
        throws RemoteException;

    public abstract DebugInfo getDebugInfo()
        throws RemoteException;

    public abstract ArrayList getHashChain()
        throws RemoteException;

    public abstract ArrayList interfaceChain()
        throws RemoteException;

    public abstract String interfaceDescriptor()
        throws RemoteException;

    public abstract boolean isDescramblerSupported(int i)
        throws RemoteException;

    public abstract boolean isSystemIdSupported(int i)
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

    public static final String kInterfaceName = "android.hardware.cas@1.0::IMediaCasService";
}
