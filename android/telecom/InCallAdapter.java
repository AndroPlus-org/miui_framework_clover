// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.internal.telecom.IInCallAdapter;
import java.util.List;

// Referenced classes of package android.telecom:
//            PhoneAccountHandle

public final class InCallAdapter
{

    public InCallAdapter(IInCallAdapter iincalladapter)
    {
        mAdapter = iincalladapter;
    }

    public void answerCall(String s, int i)
    {
        mAdapter.answerCall(s, i);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void conference(String s, String s1)
    {
        mAdapter.conference(s, s1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void disconnectCall(String s)
    {
        mAdapter.disconnectCall(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void holdCall(String s)
    {
        mAdapter.holdCall(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void mergeConference(String s)
    {
        mAdapter.mergeConference(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void mute(boolean flag)
    {
        mAdapter.mute(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void phoneAccountSelected(String s, PhoneAccountHandle phoneaccounthandle, boolean flag)
    {
        mAdapter.phoneAccountSelected(s, phoneaccounthandle, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void playDtmfTone(String s, char c)
    {
        mAdapter.playDtmfTone(s, c);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void postDialContinue(String s, boolean flag)
    {
        mAdapter.postDialContinue(s, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void pullExternalCall(String s)
    {
        mAdapter.pullExternalCall(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void putExtra(String s, String s1, int i)
    {
        Bundle bundle = JVM INSTR new #59  <Class Bundle>;
        bundle.Bundle();
        bundle.putInt(s1, i);
        mAdapter.putExtras(s, bundle);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void putExtra(String s, String s1, String s2)
    {
        Bundle bundle = JVM INSTR new #59  <Class Bundle>;
        bundle.Bundle();
        bundle.putString(s1, s2);
        mAdapter.putExtras(s, bundle);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void putExtra(String s, String s1, boolean flag)
    {
        Bundle bundle = JVM INSTR new #59  <Class Bundle>;
        bundle.Bundle();
        bundle.putBoolean(s1, flag);
        mAdapter.putExtras(s, bundle);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void putExtras(String s, Bundle bundle)
    {
        mAdapter.putExtras(s, bundle);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void rejectCall(String s, boolean flag, String s1)
    {
        mAdapter.rejectCall(s, flag, s1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void removeExtras(String s, List list)
    {
        mAdapter.removeExtras(s, list);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void respondToRttRequest(String s, int i, boolean flag)
    {
        mAdapter.respondToRttRequest(s, i, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendCallEvent(String s, String s1, Bundle bundle)
    {
        mAdapter.sendCallEvent(s, s1, bundle);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendRttRequest(String s)
    {
        mAdapter.sendRttRequest(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setAudioRoute(int i)
    {
        mAdapter.setAudioRoute(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setRttMode(String s, int i)
    {
        mAdapter.setRttMode(s, i);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void splitFromConference(String s)
    {
        mAdapter.splitFromConference(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopDtmfTone(String s)
    {
        mAdapter.stopDtmfTone(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopRtt(String s)
    {
        mAdapter.stopRtt(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void swapConference(String s)
    {
        mAdapter.swapConference(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void turnProximitySensorOff(boolean flag)
    {
        mAdapter.turnOffProximitySensor(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void turnProximitySensorOn()
    {
        mAdapter.turnOnProximitySensor();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void unholdCall(String s)
    {
        mAdapter.unholdCall(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final IInCallAdapter mAdapter;
}
