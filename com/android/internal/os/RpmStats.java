// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.util.ArrayMap;
import java.util.Map;

public final class RpmStats
{
    public static class PowerStateElement
    {

        public int mCount;
        public long mTimeMs;

        private PowerStateElement(long l, int i)
        {
            mTimeMs = l;
            mCount = i;
        }

        PowerStateElement(long l, int i, PowerStateElement powerstateelement)
        {
            this(l, i);
        }
    }

    public static class PowerStatePlatformSleepState
    {

        public void putVoter(String s, long l, int i)
        {
            PowerStateElement powerstateelement = (PowerStateElement)mVoters.get(s);
            if(powerstateelement == null)
            {
                mVoters.put(s, new PowerStateElement(l, i, null));
            } else
            {
                powerstateelement.mTimeMs = l;
                powerstateelement.mCount = i;
            }
        }

        public int mCount;
        public long mTimeMs;
        public Map mVoters;

        public PowerStatePlatformSleepState()
        {
            mVoters = new ArrayMap();
        }
    }

    public static class PowerStateSubsystem
    {

        public void putState(String s, long l, int i)
        {
            PowerStateElement powerstateelement = (PowerStateElement)mStates.get(s);
            if(powerstateelement == null)
            {
                mStates.put(s, new PowerStateElement(l, i, null));
            } else
            {
                powerstateelement.mTimeMs = l;
                powerstateelement.mCount = i;
            }
        }

        public Map mStates;

        public PowerStateSubsystem()
        {
            mStates = new ArrayMap();
        }
    }


    public RpmStats()
    {
        mPlatformLowPowerStats = new ArrayMap();
        mSubsystemLowPowerStats = new ArrayMap();
    }

    public PowerStatePlatformSleepState getAndUpdatePlatformState(String s, long l, int i)
    {
        PowerStatePlatformSleepState powerstateplatformsleepstate = (PowerStatePlatformSleepState)mPlatformLowPowerStats.get(s);
        PowerStatePlatformSleepState powerstateplatformsleepstate1 = powerstateplatformsleepstate;
        if(powerstateplatformsleepstate == null)
        {
            powerstateplatformsleepstate1 = new PowerStatePlatformSleepState();
            mPlatformLowPowerStats.put(s, powerstateplatformsleepstate1);
        }
        powerstateplatformsleepstate1.mTimeMs = l;
        powerstateplatformsleepstate1.mCount = i;
        return powerstateplatformsleepstate1;
    }

    public PowerStateSubsystem getSubsystem(String s)
    {
        PowerStateSubsystem powerstatesubsystem = (PowerStateSubsystem)mSubsystemLowPowerStats.get(s);
        PowerStateSubsystem powerstatesubsystem1 = powerstatesubsystem;
        if(powerstatesubsystem == null)
        {
            powerstatesubsystem1 = new PowerStateSubsystem();
            mSubsystemLowPowerStats.put(s, powerstatesubsystem1);
        }
        return powerstatesubsystem1;
    }

    public Map mPlatformLowPowerStats;
    public Map mSubsystemLowPowerStats;
}
