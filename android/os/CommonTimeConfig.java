// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.net.InetSocketAddress;
import java.util.NoSuchElementException;

// Referenced classes of package android.os:
//            RemoteException, ServiceManager, IBinder, CommonTimeUtils, 
//            Parcel

public class CommonTimeConfig
{
    public static interface OnServerDiedListener
    {

        public abstract void onServerDied();
    }


    static Object _2D_get0(CommonTimeConfig commontimeconfig)
    {
        return commontimeconfig.mListenerLock;
    }

    static OnServerDiedListener _2D_get1(CommonTimeConfig commontimeconfig)
    {
        return commontimeconfig.mServerDiedListener;
    }

    public CommonTimeConfig()
        throws RemoteException
    {
        mServerDiedListener = null;
        mRemote = null;
        mInterfaceDesc = "";
        mDeathHandler = new IBinder.DeathRecipient() {

            public void binderDied()
            {
                Object obj = CommonTimeConfig._2D_get0(CommonTimeConfig.this);
                obj;
                JVM INSTR monitorenter ;
                if(CommonTimeConfig._2D_get1(CommonTimeConfig.this) != null)
                    CommonTimeConfig._2D_get1(CommonTimeConfig.this).onServerDied();
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            final CommonTimeConfig this$0;

            
            {
                this$0 = CommonTimeConfig.this;
                super();
            }
        }
;
        mRemote = ServiceManager.getService("common_time.config");
        if(mRemote == null)
        {
            throw new RemoteException();
        } else
        {
            mInterfaceDesc = mRemote.getInterfaceDescriptor();
            mUtils = new CommonTimeUtils(mRemote, mInterfaceDesc);
            mRemote.linkToDeath(mDeathHandler, 0);
            return;
        }
    }

    private boolean checkDeadServer()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mRemote != null)
            if(mUtils == null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static CommonTimeConfig create()
    {
        CommonTimeConfig commontimeconfig;
        try
        {
            commontimeconfig = JVM INSTR new #2   <Class CommonTimeConfig>;
            commontimeconfig.CommonTimeConfig();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception = null;
        }
        return commontimeconfig;
    }

    private void throwOnDeadServer()
        throws RemoteException
    {
        if(checkDeadServer())
            throw new RemoteException();
        else
            return;
    }

    protected void finalize()
        throws Throwable
    {
        release();
    }

    public int forceNetworklessMasterMode()
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        int i;
        try
        {
            parcel.writeInterfaceToken(mInterfaceDesc);
            mRemote.transact(17, parcel, parcel1, 0);
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

    public boolean getAutoDisable()
        throws RemoteException
    {
        boolean flag = true;
        throwOnDeadServer();
        if(1 != mUtils.transactGetInt(15, 1))
            flag = false;
        return flag;
    }

    public int getClientSyncInterval()
        throws RemoteException
    {
        throwOnDeadServer();
        return mUtils.transactGetInt(11, -1);
    }

    public String getInterfaceBinding()
        throws RemoteException
    {
        throwOnDeadServer();
        String s = mUtils.transactGetString(7, null);
        if(s != null && s.length() == 0)
            return null;
        else
            return s;
    }

    public int getMasterAnnounceInterval()
        throws RemoteException
    {
        throwOnDeadServer();
        return mUtils.transactGetInt(9, -1);
    }

    public InetSocketAddress getMasterElectionEndpoint()
        throws RemoteException
    {
        throwOnDeadServer();
        return mUtils.transactGetSockaddr(3);
    }

    public long getMasterElectionGroupId()
        throws RemoteException
    {
        throwOnDeadServer();
        return mUtils.transactGetLong(5, -1L);
    }

    public byte getMasterElectionPriority()
        throws RemoteException
    {
        throwOnDeadServer();
        return (byte)mUtils.transactGetInt(1, -1);
    }

    public int getPanicThreshold()
        throws RemoteException
    {
        throwOnDeadServer();
        return mUtils.transactGetInt(13, -1);
    }

    public void release()
    {
        if(mRemote != null)
        {
            try
            {
                mRemote.unlinkToDeath(mDeathHandler, 0);
            }
            catch(NoSuchElementException nosuchelementexception) { }
            mRemote = null;
        }
        mUtils = null;
    }

    public int setAutoDisable(boolean flag)
    {
        if(checkDeadServer())
            return -7;
        CommonTimeUtils commontimeutils = mUtils;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        return commontimeutils.transactSetInt(16, i);
    }

    public int setClientSyncInterval(int i)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetInt(12, i);
    }

    public int setMasterAnnounceInterval(int i)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetInt(10, i);
    }

    public int setMasterElectionEndpoint(InetSocketAddress inetsocketaddress)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetSockaddr(4, inetsocketaddress);
    }

    public int setMasterElectionGroupId(long l)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetLong(6, l);
    }

    public int setMasterElectionPriority(byte byte0)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetInt(2, byte0);
    }

    public int setNetworkBinding(String s)
    {
        if(checkDeadServer())
            return -7;
        CommonTimeUtils commontimeutils = mUtils;
        String s1 = s;
        if(s == null)
            s1 = "";
        return commontimeutils.transactSetString(8, s1);
    }

    public int setPanicThreshold(int i)
    {
        if(checkDeadServer())
            return -7;
        else
            return mUtils.transactSetInt(14, i);
    }

    public void setServerDiedListener(OnServerDiedListener onserverdiedlistener)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mServerDiedListener = onserverdiedlistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onserverdiedlistener;
        throw onserverdiedlistener;
    }

    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -4;
    public static final int ERROR_DEAD_OBJECT = -7;
    public static final long INVALID_GROUP_ID = -1L;
    private static final int METHOD_FORCE_NETWORKLESS_MASTER_MODE = 17;
    private static final int METHOD_GET_AUTO_DISABLE = 15;
    private static final int METHOD_GET_CLIENT_SYNC_INTERVAL = 11;
    private static final int METHOD_GET_INTERFACE_BINDING = 7;
    private static final int METHOD_GET_MASTER_ANNOUNCE_INTERVAL = 9;
    private static final int METHOD_GET_MASTER_ELECTION_ENDPOINT = 3;
    private static final int METHOD_GET_MASTER_ELECTION_GROUP_ID = 5;
    private static final int METHOD_GET_MASTER_ELECTION_PRIORITY = 1;
    private static final int METHOD_GET_PANIC_THRESHOLD = 13;
    private static final int METHOD_SET_AUTO_DISABLE = 16;
    private static final int METHOD_SET_CLIENT_SYNC_INTERVAL = 12;
    private static final int METHOD_SET_INTERFACE_BINDING = 8;
    private static final int METHOD_SET_MASTER_ANNOUNCE_INTERVAL = 10;
    private static final int METHOD_SET_MASTER_ELECTION_ENDPOINT = 4;
    private static final int METHOD_SET_MASTER_ELECTION_GROUP_ID = 6;
    private static final int METHOD_SET_MASTER_ELECTION_PRIORITY = 2;
    private static final int METHOD_SET_PANIC_THRESHOLD = 14;
    public static final String SERVICE_NAME = "common_time.config";
    public static final int SUCCESS = 0;
    private IBinder.DeathRecipient mDeathHandler;
    private String mInterfaceDesc;
    private final Object mListenerLock = new Object();
    private IBinder mRemote;
    private OnServerDiedListener mServerDiedListener;
    private CommonTimeUtils mUtils;
}
