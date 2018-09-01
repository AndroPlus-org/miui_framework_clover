// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.ComponentName;
import android.os.RemoteException;
import com.android.internal.telecom.IConnectionService;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package android.telecom:
//            RemoteConnectionService, RemoteConnection, Log, ConnectionRequest, 
//            PhoneAccountHandle, ConnectionService

public class RemoteConnectionManager
{

    public RemoteConnectionManager(ConnectionService connectionservice)
    {
        mOurConnectionServiceImpl = connectionservice;
    }

    void addConnectionService(ComponentName componentname, IConnectionService iconnectionservice)
    {
        if(mRemoteConnectionServices.containsKey(componentname))
            break MISSING_BLOCK_LABEL_38;
        RemoteConnectionService remoteconnectionservice = JVM INSTR new #34  <Class RemoteConnectionService>;
        remoteconnectionservice.RemoteConnectionService(iconnectionservice, mOurConnectionServiceImpl);
        mRemoteConnectionServices.put(componentname, remoteconnectionservice);
_L2:
        return;
        componentname;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void conferenceRemoteConnections(RemoteConnection remoteconnection, RemoteConnection remoteconnection1)
    {
        if(remoteconnection.getConnectionService() != remoteconnection1.getConnectionService()) goto _L2; else goto _L1
_L1:
        remoteconnection.getConnectionService().conference(remoteconnection.getId(), remoteconnection1.getId(), null);
_L4:
        return;
_L2:
        Log.w(this, "Request to conference incompatible remote connections (%s,%s) (%s,%s)", new Object[] {
            remoteconnection.getConnectionService(), remoteconnection.getId(), remoteconnection1.getConnectionService(), remoteconnection1.getId()
        });
        continue; /* Loop/switch isn't completed */
        remoteconnection;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public RemoteConnection createRemoteConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest, boolean flag)
    {
        if(connectionrequest.getAccountHandle() == null)
            throw new IllegalArgumentException("accountHandle must be specified.");
        Object obj = connectionrequest.getAccountHandle().getComponentName();
        if(!mRemoteConnectionServices.containsKey(obj))
            throw new UnsupportedOperationException((new StringBuilder()).append("accountHandle not supported: ").append(obj).toString());
        obj = (RemoteConnectionService)mRemoteConnectionServices.get(obj);
        if(obj != null)
            return ((RemoteConnectionService) (obj)).createRemoteConnection(phoneaccounthandle, connectionrequest, flag);
        else
            return null;
    }

    private final ConnectionService mOurConnectionServiceImpl;
    private final Map mRemoteConnectionServices = new HashMap();
}
