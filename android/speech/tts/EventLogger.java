// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.text.TextUtils;

// Referenced classes of package android.speech.tts:
//            AbstractEventLogger, SynthesisRequest, EventLogTags

class EventLogger extends AbstractEventLogger
{

    EventLogger(SynthesisRequest synthesisrequest, int i, int j, String s)
    {
        super(i, j, s);
        mRequest = synthesisrequest;
    }

    private String getLocaleString()
    {
        StringBuilder stringbuilder = new StringBuilder(mRequest.getLanguage());
        if(!TextUtils.isEmpty(mRequest.getCountry()))
        {
            stringbuilder.append('-');
            stringbuilder.append(mRequest.getCountry());
            if(!TextUtils.isEmpty(mRequest.getVariant()))
            {
                stringbuilder.append('-');
                stringbuilder.append(mRequest.getVariant());
            }
        }
        return stringbuilder.toString();
    }

    private int getUtteranceLength()
    {
        String s = mRequest.getText();
        int i;
        if(s == null)
            i = 0;
        else
            i = s.length();
        return i;
    }

    protected void logFailure(int i)
    {
        if(i != -2)
            EventLogTags.writeTtsSpeakFailure(mServiceApp, mCallerUid, mCallerPid, getUtteranceLength(), getLocaleString(), mRequest.getSpeechRate(), mRequest.getPitch());
    }

    protected void logSuccess(long l, long l1, long l2)
    {
        EventLogTags.writeTtsSpeakSuccess(mServiceApp, mCallerUid, mCallerPid, getUtteranceLength(), getLocaleString(), mRequest.getSpeechRate(), mRequest.getPitch(), l1, l2, l);
    }

    private final SynthesisRequest mRequest;
}
