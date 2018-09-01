// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.OsConstants;
import java.net.*;
import java.util.Locale;

// Referenced classes of package android.os:
//            RemoteException, Parcel, IBinder

class CommonTimeUtils
{

    public CommonTimeUtils(IBinder ibinder, String s)
    {
        mRemote = ibinder;
        mInterfaceDesc = s;
    }

    public int transactGetInt(int i, int j)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken(mInterfaceDesc);
        mRemote.transact(i, parcel, parcel1, 0);
        if(parcel1.readInt() != 0) goto _L2; else goto _L1
_L1:
        i = parcel1.readInt();
_L4:
        parcel1.recycle();
        parcel.recycle();
        return i;
_L2:
        i = j;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public long transactGetLong(int i, long l)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken(mInterfaceDesc);
        mRemote.transact(i, parcel, parcel1, 0);
        if(parcel1.readInt() == 0)
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

    public InetSocketAddress transactGetSockaddr(int i)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        Object obj;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        obj = null;
        parcel.writeInterfaceToken(mInterfaceDesc);
        mRemote.transact(i, parcel, parcel1, 0);
        InetSocketAddress inetsocketaddress = obj;
        if(parcel1.readInt() != 0) goto _L2; else goto _L1
_L1:
        String s;
        i = 0;
        s = null;
        int j = parcel1.readInt();
        if(OsConstants.AF_INET != j) goto _L4; else goto _L3
_L3:
        j = parcel1.readInt();
        i = parcel1.readInt();
        s = String.format(Locale.US, "%d.%d.%d.%d", new Object[] {
            Integer.valueOf(j >> 24 & 0xff), Integer.valueOf(j >> 16 & 0xff), Integer.valueOf(j >> 8 & 0xff), Integer.valueOf(j & 0xff)
        });
_L7:
        inetsocketaddress = obj;
        if(s == null) goto _L2; else goto _L5
_L5:
        inetsocketaddress = new InetSocketAddress(s, i);
_L2:
        parcel1.recycle();
        parcel.recycle();
        return inetsocketaddress;
_L4:
        if(OsConstants.AF_INET6 != j) goto _L7; else goto _L6
_L6:
        int k = parcel1.readInt();
        int l = parcel1.readInt();
        int i1 = parcel1.readInt();
        int j1 = parcel1.readInt();
        i = parcel1.readInt();
        parcel1.readInt();
        parcel1.readInt();
        s = String.format(Locale.US, "[%04X:%04X:%04X:%04X:%04X:%04X:%04X:%04X]", new Object[] {
            Integer.valueOf(k >> 16 & 0xffff), Integer.valueOf(0xffff & k), Integer.valueOf(l >> 16 & 0xffff), Integer.valueOf(0xffff & l), Integer.valueOf(i1 >> 16 & 0xffff), Integer.valueOf(0xffff & i1), Integer.valueOf(j1 >> 16 & 0xffff), Integer.valueOf(0xffff & j1)
        });
          goto _L7
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public String transactGetString(int i, String s)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken(mInterfaceDesc);
        mRemote.transact(i, parcel, parcel1, 0);
        if(parcel1.readInt() == 0)
            s = parcel1.readString();
        parcel1.recycle();
        parcel.recycle();
        return s;
        s;
        parcel1.recycle();
        parcel.recycle();
        throw s;
    }

    public int transactSetInt(int i, int j)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            parcel.writeInterfaceToken(mInterfaceDesc);
            parcel.writeInt(j);
            mRemote.transact(i, parcel, parcel1, 0);
            i = parcel1.readInt();
        }
        catch(RemoteException remoteexception)
        {
            parcel1.recycle();
            parcel.recycle();
            return -7;
        }
        parcel1.recycle();
        parcel.recycle();
        return i;
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public int transactSetLong(int i, long l)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            parcel.writeInterfaceToken(mInterfaceDesc);
            parcel.writeLong(l);
            mRemote.transact(i, parcel, parcel1, 0);
            i = parcel1.readInt();
        }
        catch(RemoteException remoteexception)
        {
            parcel1.recycle();
            parcel.recycle();
            return -7;
        }
        parcel1.recycle();
        parcel.recycle();
        return i;
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public int transactSetSockaddr(int i, InetSocketAddress inetsocketaddress)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken(mInterfaceDesc);
        if(inetsocketaddress != null) goto _L2; else goto _L1
_L1:
        parcel.writeInt(0);
_L3:
        mRemote.transact(i, parcel, parcel1, 0);
        i = parcel1.readInt();
        parcel1.recycle();
        parcel.recycle();
_L4:
        return i;
_L2:
        InetAddress inetaddress;
        byte abyte0[];
        int j;
        parcel.writeInt(1);
        inetaddress = inetsocketaddress.getAddress();
        abyte0 = inetaddress.getAddress();
        j = inetsocketaddress.getPort();
        if(!(inetaddress instanceof Inet4Address))
            break MISSING_BLOCK_LABEL_186;
        byte byte0;
        int k;
        byte byte1;
        byte byte2;
        byte0 = abyte0[0];
        k = abyte0[1];
        byte1 = abyte0[2];
        byte2 = abyte0[3];
        parcel.writeInt(OsConstants.AF_INET);
        parcel.writeInt((byte0 & 0xff) << 24 | (k & 0xff) << 16 | (byte1 & 0xff) << 8 | byte2 & 0xff);
        parcel.writeInt(j);
          goto _L3
        inetsocketaddress;
        i = -7;
        parcel1.recycle();
        parcel.recycle();
          goto _L4
        if(!(inetaddress instanceof Inet6Address))
            break MISSING_BLOCK_LABEL_324;
        inetsocketaddress = (Inet6Address)inetaddress;
        parcel.writeInt(OsConstants.AF_INET6);
        k = 0;
_L6:
        if(k >= 4)
            break; /* Loop/switch isn't completed */
        parcel.writeInt((abyte0[k * 4 + 0] & 0xff) << 24 | (abyte0[k * 4 + 1] & 0xff) << 16 | (abyte0[k * 4 + 2] & 0xff) << 8 | abyte0[k * 4 + 3] & 0xff);
        k++;
        if(true) goto _L6; else goto _L5
_L5:
        parcel.writeInt(j);
        parcel.writeInt(0);
        parcel.writeInt(inetsocketaddress.getScopeId());
          goto _L3
        inetsocketaddress;
        parcel1.recycle();
        parcel.recycle();
        throw inetsocketaddress;
        parcel1.recycle();
        parcel.recycle();
        return -4;
    }

    public int transactSetString(int i, String s)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            parcel.writeInterfaceToken(mInterfaceDesc);
            parcel.writeString(s);
            mRemote.transact(i, parcel, parcel1, 0);
            i = parcel1.readInt();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            parcel1.recycle();
            parcel.recycle();
            return -7;
        }
        parcel1.recycle();
        parcel.recycle();
        return i;
        s;
        parcel1.recycle();
        parcel.recycle();
        throw s;
    }

    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -4;
    public static final int ERROR_DEAD_OBJECT = -7;
    public static final int SUCCESS = 0;
    private String mInterfaceDesc;
    private IBinder mRemote;
}
