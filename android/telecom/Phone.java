// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Bundle;
import android.util.ArrayMap;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.telecom:
//            ParcelableCall, Log, AudioState, Call, 
//            InCallAdapter, CallAudioState

public final class Phone
{
    public static abstract class Listener
    {

        public void onAudioStateChanged(Phone phone, AudioState audiostate)
        {
        }

        public void onBringToForeground(Phone phone, boolean flag)
        {
        }

        public void onCallAdded(Phone phone, Call call)
        {
        }

        public void onCallAudioStateChanged(Phone phone, CallAudioState callaudiostate)
        {
        }

        public void onCallRemoved(Phone phone, Call call)
        {
        }

        public void onCanAddCallChanged(Phone phone, boolean flag)
        {
        }

        public void onSilenceRinger(Phone phone)
        {
        }

        public Listener()
        {
        }
    }


    Phone(InCallAdapter incalladapter, String s, int i)
    {
        mUnmodifiableCalls = Collections.unmodifiableList(mCalls);
        mCanAddCall = true;
        mInCallAdapter = incalladapter;
        mCallingPackage = s;
        mTargetSdkVersion = i;
    }

    private void checkCallTree(ParcelableCall parcelablecall)
    {
        if(parcelablecall.getChildCallIds() != null)
        {
            for(int i = 0; i < parcelablecall.getChildCallIds().size(); i++)
                if(!mCallByTelecomCallId.containsKey(parcelablecall.getChildCallIds().get(i)))
                    Log.wtf(this, "ParcelableCall %s has nonexistent child %s", new Object[] {
                        parcelablecall.getId(), parcelablecall.getChildCallIds().get(i)
                    });

        }
    }

    private void fireBringToForeground(boolean flag)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onBringToForeground(this, flag));
    }

    private void fireCallAdded(Call call)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onCallAdded(this, call));
    }

    private void fireCallAudioStateChanged(CallAudioState callaudiostate)
    {
        Listener listener;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); listener.onAudioStateChanged(this, new AudioState(callaudiostate)))
        {
            listener = (Listener)iterator.next();
            listener.onCallAudioStateChanged(this, callaudiostate);
        }

    }

    private void fireCallRemoved(Call call)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onCallRemoved(this, call));
    }

    private void fireCanAddCallChanged(boolean flag)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onCanAddCallChanged(this, flag));
    }

    private void fireSilenceRinger()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onSilenceRinger(this));
    }

    public final void addListener(Listener listener)
    {
        mListeners.add(listener);
    }

    public final boolean canAddCall()
    {
        return mCanAddCall;
    }

    final void destroy()
    {
        Iterator iterator = mCalls.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Call call = (Call)iterator.next();
            InCallService.VideoCall videocall = call.getVideoCall();
            if(videocall != null)
                videocall.destroy();
            if(call.getState() != 7)
                call.internalSetDisconnected();
        } while(true);
    }

    public final AudioState getAudioState()
    {
        return new AudioState(mCallAudioState);
    }

    public final CallAudioState getCallAudioState()
    {
        return mCallAudioState;
    }

    public final List getCalls()
    {
        return mUnmodifiableCalls;
    }

    final void internalAddCall(ParcelableCall parcelablecall)
    {
        Call call = new Call(this, parcelablecall.getId(), mInCallAdapter, parcelablecall.getState(), mCallingPackage, mTargetSdkVersion);
        mCallByTelecomCallId.put(parcelablecall.getId(), call);
        mCalls.add(call);
        checkCallTree(parcelablecall);
        call.internalUpdate(parcelablecall, mCallByTelecomCallId);
        fireCallAdded(call);
    }

    final void internalBringToForeground(boolean flag)
    {
        fireBringToForeground(flag);
    }

    final void internalCallAudioStateChanged(CallAudioState callaudiostate)
    {
        if(!Objects.equals(mCallAudioState, callaudiostate))
        {
            mCallAudioState = callaudiostate;
            fireCallAudioStateChanged(callaudiostate);
        }
    }

    final Call internalGetCallByTelecomId(String s)
    {
        return (Call)mCallByTelecomCallId.get(s);
    }

    final void internalOnConnectionEvent(String s, String s1, Bundle bundle)
    {
        s = (Call)mCallByTelecomCallId.get(s);
        if(s != null)
            s.internalOnConnectionEvent(s1, bundle);
    }

    final void internalOnRttInitiationFailure(String s, int i)
    {
        s = (Call)mCallByTelecomCallId.get(s);
        if(s != null)
            s.internalOnRttInitiationFailure(i);
    }

    final void internalOnRttUpgradeRequest(String s, int i)
    {
        s = (Call)mCallByTelecomCallId.get(s);
        if(s != null)
            s.internalOnRttUpgradeRequest(i);
    }

    final void internalRemoveCall(Call call)
    {
        mCallByTelecomCallId.remove(call.internalGetCallId());
        mCalls.remove(call);
        InCallService.VideoCall videocall = call.getVideoCall();
        if(videocall != null)
            videocall.destroy();
        fireCallRemoved(call);
    }

    final void internalSetCanAddCall(boolean flag)
    {
        if(mCanAddCall != flag)
        {
            mCanAddCall = flag;
            fireCanAddCallChanged(flag);
        }
    }

    final void internalSetPostDialWait(String s, String s1)
    {
        s = (Call)mCallByTelecomCallId.get(s);
        if(s != null)
            s.internalSetPostDialWait(s1);
    }

    final void internalSilenceRinger()
    {
        fireSilenceRinger();
    }

    final void internalUpdateCall(ParcelableCall parcelablecall)
    {
        Call call = (Call)mCallByTelecomCallId.get(parcelablecall.getId());
        if(call != null)
        {
            checkCallTree(parcelablecall);
            call.internalUpdate(parcelablecall, mCallByTelecomCallId);
        }
    }

    public final void removeListener(Listener listener)
    {
        if(listener != null)
            mListeners.remove(listener);
    }

    public final void setAudioRoute(int i)
    {
        mInCallAdapter.setAudioRoute(i);
    }

    public final void setMuted(boolean flag)
    {
        mInCallAdapter.mute(flag);
    }

    public final void setProximitySensorOff(boolean flag)
    {
        mInCallAdapter.turnProximitySensorOff(flag);
    }

    public final void setProximitySensorOn()
    {
        mInCallAdapter.turnProximitySensorOn();
    }

    private CallAudioState mCallAudioState;
    private final Map mCallByTelecomCallId = new ArrayMap();
    private final String mCallingPackage;
    private final List mCalls = new CopyOnWriteArrayList();
    private boolean mCanAddCall;
    private final InCallAdapter mInCallAdapter;
    private final List mListeners = new CopyOnWriteArrayList();
    private final int mTargetSdkVersion;
    private final List mUnmodifiableCalls;
}
