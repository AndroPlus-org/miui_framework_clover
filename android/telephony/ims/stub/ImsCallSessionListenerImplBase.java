// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.stub;

import com.android.ims.*;
import com.android.ims.internal.IImsCallSession;

public class ImsCallSessionListenerImplBase extends com.android.ims.internal.IImsCallSessionListener.Stub
{

    public ImsCallSessionListenerImplBase()
    {
    }

    public void callSessionConferenceExtendFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionConferenceExtendReceived(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionConferenceExtended(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionConferenceStateUpdated(IImsCallSession iimscallsession, ImsConferenceState imsconferencestate)
    {
    }

    public void callSessionHandover(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionHandoverFailed(IImsCallSession iimscallsession, int i, int j, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionHeld(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionHoldFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionHoldReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionInviteParticipantsRequestDelivered(IImsCallSession iimscallsession)
    {
    }

    public void callSessionInviteParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionMayHandover(IImsCallSession iimscallsession, int i, int j)
    {
    }

    public void callSessionMergeComplete(IImsCallSession iimscallsession)
    {
    }

    public void callSessionMergeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionMergeStarted(IImsCallSession iimscallsession, IImsCallSession iimscallsession1, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionMultipartyStateChanged(IImsCallSession iimscallsession, boolean flag)
    {
    }

    public void callSessionProgressing(IImsCallSession iimscallsession, ImsStreamMediaProfile imsstreammediaprofile)
    {
    }

    public void callSessionRemoveParticipantsRequestDelivered(IImsCallSession iimscallsession)
    {
    }

    public void callSessionRemoveParticipantsRequestFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionResumeFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionResumeReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionResumed(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionRttMessageReceived(String s)
    {
    }

    public void callSessionRttModifyRequestReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionRttModifyResponseReceived(int i)
    {
    }

    public void callSessionStartFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionStarted(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionSuppServiceReceived(IImsCallSession iimscallsession, ImsSuppServiceNotification imssuppservicenotification)
    {
    }

    public void callSessionTerminated(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionTtyModeReceived(IImsCallSession iimscallsession, int i)
    {
    }

    public void callSessionUpdateFailed(IImsCallSession iimscallsession, ImsReasonInfo imsreasoninfo)
    {
    }

    public void callSessionUpdateReceived(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionUpdated(IImsCallSession iimscallsession, ImsCallProfile imscallprofile)
    {
    }

    public void callSessionUssdMessageReceived(IImsCallSession iimscallsession, int i, String s)
    {
    }
}
