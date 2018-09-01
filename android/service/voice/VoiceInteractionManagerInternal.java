// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.os.Bundle;
import android.os.IBinder;

public abstract class VoiceInteractionManagerInternal
{

    public VoiceInteractionManagerInternal()
    {
    }

    public abstract void startLocalVoiceInteraction(IBinder ibinder, Bundle bundle);

    public abstract void stopLocalVoiceInteraction(IBinder ibinder);

    public abstract boolean supportsLocalVoiceInteraction();
}
