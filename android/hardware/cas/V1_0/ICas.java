// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.cas.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.*;
import java.util.*;

public interface ICas
    extends IBase
{
    public static final class Proxy
        implements ICas
    {

        public IHwBinder asBinder()
        {
            return mRemote;
        }

        public int closeSession(ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            int i;
            mRemote.transact(3, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            Exception exception;
            exception;
            arraylist.release();
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
            obj = JVM INSTR new #71  <Class DebugInfo>;
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
            obj = JVM INSTR new #81  <Class ArrayList>;
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

        public void openSession(openSessionCallback opensessioncallback)
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel1 = new HwParcel();
            mRemote.transact(2, hwparcel, hwparcel1, 0);
            hwparcel1.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            opensessioncallback.onValues(hwparcel1.readInt32(), hwparcel1.readInt8Vector());
            hwparcel1.release();
            return;
            opensessioncallback;
            hwparcel1.release();
            throw opensessioncallback;
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

        public int processEcm(ArrayList arraylist, ArrayList arraylist1)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt8Vector(arraylist);
            hwparcel.writeInt8Vector(arraylist1);
            arraylist = new HwParcel();
            int i;
            mRemote.transact(5, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            arraylist1;
            arraylist.release();
            throw arraylist1;
        }

        public int processEmm(ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            int i;
            mRemote.transact(6, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            Exception exception;
            exception;
            arraylist.release();
            throw exception;
        }

        public int provision(String s)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeString(s);
            s = new HwParcel();
            int i;
            mRemote.transact(8, hwparcel, s, 0);
            s.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = s.readInt32();
            s.release();
            return i;
            Exception exception;
            exception;
            s.release();
            throw exception;
        }

        public int refreshEntitlements(int i, ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt32(i);
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            mRemote.transact(9, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            Exception exception;
            exception;
            arraylist.release();
            throw exception;
        }

        public int release()
            throws RemoteException
        {
            HwParcel hwparcel;
            HwParcel hwparcel1;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel1 = new HwParcel();
            int i;
            mRemote.transact(10, hwparcel, hwparcel1, 0);
            hwparcel1.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = hwparcel1.readInt32();
            hwparcel1.release();
            return i;
            Exception exception;
            exception;
            hwparcel1.release();
            throw exception;
        }

        public int sendEvent(int i, int j, ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt32(i);
            hwparcel.writeInt32(j);
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            mRemote.transact(7, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            Exception exception;
            exception;
            arraylist.release();
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

        public int setPrivateData(ArrayList arraylist)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt8Vector(arraylist);
            arraylist = new HwParcel();
            int i;
            mRemote.transact(1, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            Exception exception;
            exception;
            arraylist.release();
            throw exception;
        }

        public int setSessionPrivateData(ArrayList arraylist, ArrayList arraylist1)
            throws RemoteException
        {
            HwParcel hwparcel;
            hwparcel = new HwParcel();
            hwparcel.writeInterfaceToken("android.hardware.cas@1.0::ICas");
            hwparcel.writeInt8Vector(arraylist);
            hwparcel.writeInt8Vector(arraylist1);
            arraylist = new HwParcel();
            int i;
            mRemote.transact(4, hwparcel, arraylist, 0);
            arraylist.verifySuccess();
            hwparcel.releaseTemporaryStorage();
            i = arraylist.readInt32();
            arraylist.release();
            return i;
            arraylist1;
            arraylist.release();
            throw arraylist1;
        }

        public String toString()
        {
            Object obj;
            try
            {
                obj = JVM INSTR new #173 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                obj = ((StringBuilder) (obj)).append(interfaceDescriptor()).append("@Proxy").toString();
            }
            catch(RemoteException remoteexception)
            {
                return "[class or subclass of android.hardware.cas@1.0::ICas]@Proxy";
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
        implements ICas
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
                    14, 101, 107, -95, -70, -63, 20, 97, -95, 112, 
                    -106, -17, 117, 43, 105, -46, 75, 0, 13, -126, 
                    14, -11, 101, 47, 1, 80, -96, -7, 115, 29, 
                    84, -62
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
                "android.hardware.cas@1.0::ICas", "android.hidl.base@1.0::IBase"
            }));
        }

        public final String interfaceDescriptor()
        {
            return "android.hardware.cas@1.0::ICas";
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
            JVM INSTR lookupswitch 20: default 172
        //                       1: 173
        //                       2: 205
        //                       3: 227
        //                       4: 259
        //                       5: 295
        //                       6: 331
        //                       7: 363
        //                       8: 403
        //                       9: 435
        //                       10: 471
        //                       256067662: 499
        //                       256131655: 527
        //                       256136003: 545
        //                       256398152: 573
        //                       256462420: 721
        //                       256660548: 172
        //                       256921159: 172
        //                       257049926: 734
        //                       257120595: 762
        //                       257250372: 172;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L1 _L1 _L17 _L18 _L1
_L1:
            return;
_L2:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = setPrivateData(hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L3:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            openSession(hwparcel1. new openSessionCallback() {

                public void onValues(int i, ArrayList arraylist)
                {
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(i);
                    _hidl_reply.writeInt8Vector(arraylist);
                    _hidl_reply.send();
                }

                final Stub this$1;
                final HwParcel val$_hidl_reply;

            
            {
                this$1 = final_stub;
                _hidl_reply = HwParcel.this;
                super();
            }
            }
);
            continue; /* Loop/switch isn't completed */
_L4:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = closeSession(hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L5:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = setSessionPrivateData(hwparcel.readInt8Vector(), hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L6:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = processEcm(hwparcel.readInt8Vector(), hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L7:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = processEmm(hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L8:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = sendEvent(hwparcel.readInt32(), hwparcel.readInt32(), hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L9:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = provision(hwparcel.readString());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L10:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = refreshEntitlements(hwparcel.readInt32(), hwparcel.readInt8Vector());
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L11:
            hwparcel.enforceInterface("android.hardware.cas@1.0::ICas");
            i = release();
            hwparcel1.writeStatus(0);
            hwparcel1.writeInt32(i);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L12:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceChain();
            hwparcel1.writeStatus(0);
            hwparcel1.writeStringVector(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L13:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel1.writeStatus(0);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L14:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = interfaceDescriptor();
            hwparcel1.writeStatus(0);
            hwparcel1.writeString(hwparcel);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L15:
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
_L16:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
            continue; /* Loop/switch isn't completed */
_L17:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            hwparcel = getDebugInfo();
            hwparcel1.writeStatus(0);
            hwparcel.writeToParcel(hwparcel1);
            hwparcel1.send();
            continue; /* Loop/switch isn't completed */
_L18:
            hwparcel.enforceInterface("android.hidl.base@1.0::IBase");
            notifySyspropsChanged();
            if(true) goto _L1; else goto _L19
_L19:
        }

        public final void ping()
        {
        }

        public IHwInterface queryLocalInterface(String s)
        {
            if("android.hardware.cas@1.0::ICas".equals(s))
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

    public static interface openSessionCallback
    {

        public abstract void onValues(int i, ArrayList arraylist);
    }


    public static ICas asInterface(IHwBinder ihwbinder)
    {
        if(ihwbinder == null)
            return null;
        IHwInterface ihwinterface = ihwbinder.queryLocalInterface("android.hardware.cas@1.0::ICas");
        if(ihwinterface != null && (ihwinterface instanceof ICas))
            return (ICas)ihwinterface;
        ihwbinder = new Proxy(ihwbinder);
        Iterator iterator = ihwbinder.interfaceChain().iterator();
        boolean flag;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_83;
            flag = ((String)iterator.next()).equals("android.hardware.cas@1.0::ICas");
        } while(!flag);
        return ihwbinder;
        ihwbinder;
        return null;
    }

    public static ICas castFrom(IHwInterface ihwinterface)
    {
        Object obj = null;
        if(ihwinterface == null)
            ihwinterface = obj;
        else
            ihwinterface = asInterface(ihwinterface.asBinder());
        return ihwinterface;
    }

    public static ICas getService()
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::ICas", "default"));
    }

    public static ICas getService(String s)
        throws RemoteException
    {
        return asInterface(HwBinder.getService("android.hardware.cas@1.0::ICas", s));
    }

    public abstract IHwBinder asBinder();

    public abstract int closeSession(ArrayList arraylist)
        throws RemoteException;

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

    public abstract void openSession(openSessionCallback opensessioncallback)
        throws RemoteException;

    public abstract void ping()
        throws RemoteException;

    public abstract int processEcm(ArrayList arraylist, ArrayList arraylist1)
        throws RemoteException;

    public abstract int processEmm(ArrayList arraylist)
        throws RemoteException;

    public abstract int provision(String s)
        throws RemoteException;

    public abstract int refreshEntitlements(int i, ArrayList arraylist)
        throws RemoteException;

    public abstract int release()
        throws RemoteException;

    public abstract int sendEvent(int i, int j, ArrayList arraylist)
        throws RemoteException;

    public abstract void setHALInstrumentation()
        throws RemoteException;

    public abstract int setPrivateData(ArrayList arraylist)
        throws RemoteException;

    public abstract int setSessionPrivateData(ArrayList arraylist, ArrayList arraylist1)
        throws RemoteException;

    public abstract boolean unlinkToDeath(android.os.IHwBinder.DeathRecipient deathrecipient)
        throws RemoteException;

    public static final String kInterfaceName = "android.hardware.cas@1.0::ICas";
}
