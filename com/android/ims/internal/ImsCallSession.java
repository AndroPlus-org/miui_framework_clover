// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.Message;
import android.os.RemoteException;
import android.telephony.ims.stub.ImsCallSessionListenerImplBase;
import android.util.Log;
import com.android.ims.*;
import java.util.Objects;

// Referenced classes of package com.android.ims.internal:
//            IImsCallSession, IImsVideoCallProvider

public class ImsCallSession
{
    private class IImsCallSessionListenerProxy extends ImsCallSessionListenerImplBase
    {

        public void callSessionConferenceExtendFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionConferenceExtendFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionConferenceExtendReceived(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionConferenceExtendReceived(ImsCallSession.this, new ImsCallSession(iimscallsession1), imscallprofile);
        }

        public void callSessionConferenceExtended(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionConferenceExtended(ImsCallSession.this, new ImsCallSession(iimscallsession1), imscallprofile);
        }

        public void callSessionConferenceStateUpdated(IImsCallSession iimscallsession, ImsConferenceState imsconferencestate)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionConferenceStateUpdated(ImsCallSession.this, imsconferencestate);
        }

        public void callSessionHandover(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionHandover(ImsCallSession.this, i, j, imsreasoninfo);
        }

        public void callSessionHandoverFailed(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionHandoverFailed(ImsCallSession.this, i, j, imsreasoninfo);
        }

        public void callSessionHeld(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionHeld(ImsCallSession.this, imscallprofile);
        }

        public void callSessionHoldFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionHoldFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionHoldReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionHoldReceived(ImsCallSession.this, imscallprofile);
        }

        public void callSessionInviteParticipantsRequestDelivered(IImsCallSession iimscallsession)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionInviteParticipantsRequestDelivered(ImsCallSession.this);
        }

        public void callSessionInviteParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionInviteParticipantsRequestFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionMayHandover(IImsCallSession iimscallsession, int i, int j)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionMayHandover(ImsCallSession.this, i, j);
        }

        public void callSessionMergeComplete(IImsCallSession iimscallsession)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                if(iimscallsession != null)
                {
                    ImsCallSession imscallsession = ImsCallSession.this;
                    ImsCallSession imscallsession1 = imscallsession;
                    try
                    {
                        if(!Objects.equals(ImsCallSession._2D_get1(ImsCallSession.this).getCallId(), iimscallsession.getCallId()))
                        {
                            imscallsession1 = JVM INSTR new #6   <Class ImsCallSession>;
                            imscallsession1.ImsCallSession(iimscallsession);
                        }
                    }
                    // Misplaced declaration of an exception variable
                    catch(IImsCallSession iimscallsession)
                    {
                        Log.e("ImsCallSession", "callSessionMergeComplete: exception for getCallId!");
                        imscallsession1 = imscallsession;
                    }
                    ImsCallSession._2D_get0(ImsCallSession.this).callSessionMergeComplete(imscallsession1);
                } else
                {
                    ImsCallSession._2D_get0(ImsCallSession.this).callSessionMergeComplete(null);
                }
        }

        public void callSessionMergeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionMergeFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionMergeStarted(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
        {
            Log.d("ImsCallSession", "callSessionMergeStarted");
        }

        public void callSessionMultipartyStateChanged(IImsCallSession iimscallsession, boolean flag)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionMultipartyStateChanged(ImsCallSession.this, flag);
        }

        public void callSessionProgressing(IImsCallSession iimscallsession, ImsStreamMediaProfile imsstreammediaprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionProgressing(ImsCallSession.this, imsstreammediaprofile);
        }

        public void callSessionRemoveParticipantsRequestDelivered(IImsCallSession iimscallsession)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionRemoveParticipantsRequestDelivered(ImsCallSession.this);
        }

        public void callSessionRemoveParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionRemoveParticipantsRequestFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionResumeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionResumeFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionResumeReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionResumeReceived(ImsCallSession.this, imscallprofile);
        }

        public void callSessionResumed(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionResumed(ImsCallSession.this, imscallprofile);
        }

        public void callSessionRttMessageReceived(String s)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionRttMessageReceived(s);
        }

        public void callSessionRttModifyRequestReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionRttModifyRequestReceived(ImsCallSession.this, imscallprofile);
        }

        public void callSessionRttModifyResponseReceived(int i)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionRttModifyResponseReceived(i);
        }

        public void callSessionStartFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionStartFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionStarted(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionStarted(ImsCallSession.this, imscallprofile);
        }

        public void callSessionSuppServiceReceived(IImsCallSession iimscallsession, ImsSuppServiceNotification imssuppservicenotification)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionSuppServiceReceived(ImsCallSession.this, imssuppservicenotification);
        }

        public void callSessionTerminated(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionTerminated(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionTtyModeReceived(IImsCallSession iimscallsession, int i)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionTtyModeReceived(ImsCallSession.this, i);
        }

        public void callSessionUpdateFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionUpdateFailed(ImsCallSession.this, imsreasoninfo);
        }

        public void callSessionUpdateReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionUpdateReceived(ImsCallSession.this, imscallprofile);
        }

        public void callSessionUpdated(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionUpdated(ImsCallSession.this, imscallprofile);
        }

        public void callSessionUssdMessageReceived(IImsCallSession iimscallsession, int i, String s)
        {
            if(ImsCallSession._2D_get0(ImsCallSession.this) != null)
                ImsCallSession._2D_get0(ImsCallSession.this).callSessionUssdMessageReceived(ImsCallSession.this, i, s);
        }

        final ImsCallSession this$0;

        private IImsCallSessionListenerProxy()
        {
            this$0 = ImsCallSession.this;
            super();
        }

        IImsCallSessionListenerProxy(IImsCallSessionListenerProxy iimscallsessionlistenerproxy)
        {
            this();
        }
    }

    public static class Listener
    {

        public void callSessionConferenceExtendFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionConferenceExtendReceived(ImsCallSession imscallsession, ImsCallSession imscallsession1, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionConferenceExtended(ImsCallSession imscallsession, ImsCallSession imscallsession1, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionConferenceStateUpdated(ImsCallSession imscallsession, ImsConferenceState imsconferencestate)
        {
        }

        public void callSessionHandover(ImsCallSession imscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionHandoverFailed(ImsCallSession imscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionHeld(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionHoldFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionHoldReceived(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionInviteParticipantsRequestDelivered(ImsCallSession imscallsession)
        {
        }

        public void callSessionInviteParticipantsRequestFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionMayHandover(ImsCallSession imscallsession, int i, int j)
        {
        }

        public void callSessionMergeComplete(ImsCallSession imscallsession)
        {
        }

        public void callSessionMergeFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionMergeStarted(ImsCallSession imscallsession, ImsCallSession imscallsession1, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionMultipartyStateChanged(ImsCallSession imscallsession, boolean flag)
        {
        }

        public void callSessionProgressing(ImsCallSession imscallsession, ImsStreamMediaProfile imsstreammediaprofile)
        {
        }

        public void callSessionRemoveParticipantsRequestDelivered(ImsCallSession imscallsession)
        {
        }

        public void callSessionRemoveParticipantsRequestFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionResumeFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionResumeReceived(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionResumed(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionRttMessageReceived(String s)
        {
        }

        public void callSessionRttModifyRequestReceived(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionRttModifyResponseReceived(int i)
        {
        }

        public void callSessionStartFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionStarted(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionSuppServiceReceived(ImsCallSession imscallsession, ImsSuppServiceNotification imssuppservicenotification)
        {
        }

        public void callSessionTerminated(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionTtyModeReceived(ImsCallSession imscallsession, int i)
        {
        }

        public void callSessionUpdateFailed(ImsCallSession imscallsession, ImsReasonInfo imsreasoninfo)
        {
        }

        public void callSessionUpdateReceived(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionUpdated(ImsCallSession imscallsession, ImsCallProfile imscallprofile)
        {
        }

        public void callSessionUssdMessageReceived(ImsCallSession imscallsession, int i, String s)
        {
        }

        public Listener()
        {
        }
    }

    public static class State
    {

        public static String toString(int i)
        {
            switch(i)
            {
            default:
                return "UNKNOWN";

            case 0: // '\0'
                return "IDLE";

            case 1: // '\001'
                return "INITIATED";

            case 2: // '\002'
                return "NEGOTIATING";

            case 3: // '\003'
                return "ESTABLISHING";

            case 4: // '\004'
                return "ESTABLISHED";

            case 5: // '\005'
                return "RENEGOTIATING";

            case 6: // '\006'
                return "REESTABLISHING";

            case 7: // '\007'
                return "TERMINATING";

            case 8: // '\b'
                return "TERMINATED";
            }
        }

        public static final int ESTABLISHED = 4;
        public static final int ESTABLISHING = 3;
        public static final int IDLE = 0;
        public static final int INITIATED = 1;
        public static final int INVALID = -1;
        public static final int NEGOTIATING = 2;
        public static final int REESTABLISHING = 6;
        public static final int RENEGOTIATING = 5;
        public static final int TERMINATED = 8;
        public static final int TERMINATING = 7;

        private State()
        {
        }
    }


    static Listener _2D_get0(ImsCallSession imscallsession)
    {
        return imscallsession.mListener;
    }

    static IImsCallSession _2D_get1(ImsCallSession imscallsession)
    {
        return imscallsession.miSession;
    }

    public ImsCallSession(IImsCallSession iimscallsession)
    {
        mClosed = false;
        miSession = iimscallsession;
        if(iimscallsession == null) goto _L2; else goto _L1
_L1:
        IImsCallSessionListenerProxy iimscallsessionlistenerproxy = JVM INSTR new #6   <Class ImsCallSession$IImsCallSessionListenerProxy>;
        iimscallsessionlistenerproxy.this. IImsCallSessionListenerProxy(null);
        iimscallsession.setListener(iimscallsessionlistenerproxy);
_L4:
        return;
_L2:
        mClosed = true;
        continue; /* Loop/switch isn't completed */
        iimscallsession;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public ImsCallSession(IImsCallSession iimscallsession, Listener listener)
    {
        this(iimscallsession);
        setListener(listener);
    }

    public void accept(int i, ImsStreamMediaProfile imsstreammediaprofile)
    {
        if(mClosed)
            return;
        miSession.accept(i, imsstreammediaprofile);
_L2:
        return;
        imsstreammediaprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mClosed;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        try
        {
            miSession.close();
            mClosed = true;
        }
        catch(RemoteException remoteexception) { }
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public void extendToConference(String as[])
    {
        if(mClosed)
            return;
        miSession.extendToConference(as);
_L2:
        return;
        as;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getCallId()
    {
        if(mClosed)
            return null;
        String s;
        try
        {
            s = miSession.getCallId();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return s;
    }

    public ImsCallProfile getCallProfile()
    {
        if(mClosed)
            return null;
        ImsCallProfile imscallprofile;
        try
        {
            imscallprofile = miSession.getCallProfile();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return imscallprofile;
    }

    public ImsCallProfile getLocalCallProfile()
    {
        if(mClosed)
            return null;
        ImsCallProfile imscallprofile;
        try
        {
            imscallprofile = miSession.getLocalCallProfile();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return imscallprofile;
    }

    public String getProperty(String s)
    {
        if(mClosed)
            return null;
        try
        {
            s = miSession.getProperty(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    public ImsCallProfile getRemoteCallProfile()
    {
        if(mClosed)
            return null;
        ImsCallProfile imscallprofile;
        try
        {
            imscallprofile = miSession.getRemoteCallProfile();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return imscallprofile;
    }

    public IImsCallSession getSession()
    {
        return miSession;
    }

    public int getState()
    {
        if(mClosed)
            return -1;
        int i;
        try
        {
            i = miSession.getState();
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        return i;
    }

    public IImsVideoCallProvider getVideoCallProvider()
    {
        if(mClosed)
            return null;
        IImsVideoCallProvider iimsvideocallprovider;
        try
        {
            iimsvideocallprovider = miSession.getVideoCallProvider();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return iimsvideocallprovider;
    }

    public void hold(ImsStreamMediaProfile imsstreammediaprofile)
    {
        if(mClosed)
            return;
        miSession.hold(imsstreammediaprofile);
_L2:
        return;
        imsstreammediaprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void inviteParticipants(String as[])
    {
        if(mClosed)
            return;
        miSession.inviteParticipants(as);
_L2:
        return;
        as;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isAlive()
    {
        if(mClosed)
            return false;
        switch(getState())
        {
        default:
            return false;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            return true;
        }
    }

    public boolean isInCall()
    {
        if(mClosed)
            return false;
        boolean flag;
        try
        {
            flag = miSession.isInCall();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isMultiparty()
    {
        if(mClosed)
            return false;
        boolean flag;
        try
        {
            flag = miSession.isMultiparty();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void merge()
    {
        if(mClosed)
            return;
        miSession.merge();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void reject(int i)
    {
        if(mClosed)
            return;
        miSession.reject(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void removeParticipants(String as[])
    {
        if(mClosed)
            return;
        miSession.removeParticipants(as);
_L2:
        return;
        as;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void resume(ImsStreamMediaProfile imsstreammediaprofile)
    {
        if(mClosed)
            return;
        miSession.resume(imsstreammediaprofile);
_L2:
        return;
        imsstreammediaprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendDtmf(char c, Message message)
    {
        if(mClosed)
            return;
        miSession.sendDtmf(c, message);
_L2:
        return;
        message;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendRttMessage(String s)
    {
        if(mClosed)
            return;
        miSession.sendRttMessage(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendRttModifyRequest(ImsCallProfile imscallprofile)
    {
        if(mClosed)
            return;
        miSession.sendRttModifyRequest(imscallprofile);
_L2:
        return;
        imscallprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendRttModifyResponse(boolean flag)
    {
        if(mClosed)
            return;
        miSession.sendRttModifyResponse(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendUssd(String s)
    {
        if(mClosed)
            return;
        miSession.sendUssd(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setListener(Listener listener)
    {
        mListener = listener;
    }

    public void setMute(boolean flag)
    {
        if(mClosed)
            return;
        miSession.setMute(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void start(String s, ImsCallProfile imscallprofile)
    {
        if(mClosed)
            return;
        miSession.start(s, imscallprofile);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void start(String as[], ImsCallProfile imscallprofile)
    {
        if(mClosed)
            return;
        miSession.startConference(as, imscallprofile);
_L2:
        return;
        as;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void startDtmf(char c)
    {
        if(mClosed)
            return;
        miSession.startDtmf(c);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopDtmf()
    {
        if(mClosed)
            return;
        miSession.stopDtmf();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void terminate(int i)
    {
        if(mClosed)
            return;
        miSession.terminate(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[ImsCallSession objId:");
        stringbuilder.append(System.identityHashCode(this));
        stringbuilder.append(" state:");
        stringbuilder.append(State.toString(getState()));
        stringbuilder.append(" callId:");
        stringbuilder.append(getCallId());
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void update(int i, ImsStreamMediaProfile imsstreammediaprofile)
    {
        if(mClosed)
            return;
        miSession.update(i, imsstreammediaprofile);
_L2:
        return;
        imsstreammediaprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "ImsCallSession";
    private boolean mClosed;
    private Listener mListener;
    private final IImsCallSession miSession;
}
