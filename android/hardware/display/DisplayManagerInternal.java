// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.hardware.SensorManager;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayInfo;

public abstract class DisplayManagerInternal
{
    public static interface DisplayPowerCallbacks
    {

        public abstract void acquireSuspendBlocker();

        public abstract void onDisplayStateChange(int i);

        public abstract void onProximityNegative();

        public abstract void onProximityPositive();

        public abstract void onStateChanged();

        public abstract void releaseSuspendBlocker();
    }

    public static final class DisplayPowerRequest
    {

        public static String policyToString(int i)
        {
            switch(i)
            {
            default:
                return Integer.toString(i);

            case 0: // '\0'
                return "OFF";

            case 1: // '\001'
                return "DOZE";

            case 2: // '\002'
                return "DIM";

            case 3: // '\003'
                return "BRIGHT";

            case 4: // '\004'
                return "VR";
            }
        }

        public void copyFrom(DisplayPowerRequest displaypowerrequest)
        {
            policy = displaypowerrequest.policy;
            useProximitySensor = displaypowerrequest.useProximitySensor;
            screenBrightness = displaypowerrequest.screenBrightness;
            screenAutoBrightnessAdjustment = displaypowerrequest.screenAutoBrightnessAdjustment;
            screenLowPowerBrightnessFactor = displaypowerrequest.screenLowPowerBrightnessFactor;
            brightnessSetByUser = displaypowerrequest.brightnessSetByUser;
            useAutoBrightness = displaypowerrequest.useAutoBrightness;
            blockScreenOn = displaypowerrequest.blockScreenOn;
            lowPowerMode = displaypowerrequest.lowPowerMode;
            boostScreenBrightness = displaypowerrequest.boostScreenBrightness;
            dozeScreenBrightness = displaypowerrequest.dozeScreenBrightness;
            dozeScreenState = displaypowerrequest.dozeScreenState;
        }

        public boolean equals(DisplayPowerRequest displaypowerrequest)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(displaypowerrequest != null)
            {
                flag1 = flag;
                if(policy == displaypowerrequest.policy)
                {
                    flag1 = flag;
                    if(useProximitySensor == displaypowerrequest.useProximitySensor)
                    {
                        flag1 = flag;
                        if(screenBrightness == displaypowerrequest.screenBrightness)
                        {
                            flag1 = flag;
                            if(screenAutoBrightnessAdjustment == displaypowerrequest.screenAutoBrightnessAdjustment)
                            {
                                flag1 = flag;
                                if(screenLowPowerBrightnessFactor == displaypowerrequest.screenLowPowerBrightnessFactor)
                                {
                                    flag1 = flag;
                                    if(brightnessSetByUser == displaypowerrequest.brightnessSetByUser)
                                    {
                                        flag1 = flag;
                                        if(useAutoBrightness == displaypowerrequest.useAutoBrightness)
                                        {
                                            flag1 = flag;
                                            if(blockScreenOn == displaypowerrequest.blockScreenOn)
                                            {
                                                flag1 = flag;
                                                if(lowPowerMode == displaypowerrequest.lowPowerMode)
                                                {
                                                    flag1 = flag;
                                                    if(boostScreenBrightness == displaypowerrequest.boostScreenBrightness)
                                                    {
                                                        flag1 = flag;
                                                        if(dozeScreenBrightness == displaypowerrequest.dozeScreenBrightness)
                                                        {
                                                            flag1 = flag;
                                                            if(dozeScreenState == displaypowerrequest.dozeScreenState)
                                                                flag1 = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public boolean equals(Object obj)
        {
            boolean flag;
            if(obj instanceof DisplayPowerRequest)
                flag = equals((DisplayPowerRequest)obj);
            else
                flag = false;
            return flag;
        }

        public int hashCode()
        {
            return 0;
        }

        public boolean isBrightOrDim()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(policy != 3)
                if(policy == 2)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public boolean isVr()
        {
            boolean flag;
            if(policy == 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return (new StringBuilder()).append("policy=").append(policyToString(policy)).append(", useProximitySensor=").append(useProximitySensor).append(", screenBrightness=").append(screenBrightness).append(", screenAutoBrightnessAdjustment=").append(screenAutoBrightnessAdjustment).append(", screenLowPowerBrightnessFactor=").append(screenLowPowerBrightnessFactor).append(", brightnessSetByUser=").append(brightnessSetByUser).append(", useAutoBrightness=").append(useAutoBrightness).append(", blockScreenOn=").append(blockScreenOn).append(", lowPowerMode=").append(lowPowerMode).append(", boostScreenBrightness=").append(boostScreenBrightness).append(", dozeScreenBrightness=").append(dozeScreenBrightness).append(", dozeScreenState=").append(Display.stateToString(dozeScreenState)).toString();
        }

        public static final int POLICY_BRIGHT = 3;
        public static final int POLICY_DIM = 2;
        public static final int POLICY_DOZE = 1;
        public static final int POLICY_OFF = 0;
        public static final int POLICY_VR = 4;
        public boolean blockScreenOn;
        public boolean boostScreenBrightness;
        public boolean brightnessSetByUser;
        public int dozeScreenBrightness;
        public int dozeScreenState;
        public boolean lowPowerMode;
        public int policy;
        public float screenAutoBrightnessAdjustment;
        public int screenBrightness;
        public float screenLowPowerBrightnessFactor;
        public boolean useAutoBrightness;
        public boolean useProximitySensor;

        public DisplayPowerRequest()
        {
            policy = 3;
            useProximitySensor = false;
            screenBrightness = 255;
            screenAutoBrightnessAdjustment = 0.0F;
            screenLowPowerBrightnessFactor = 0.5F;
            useAutoBrightness = false;
            blockScreenOn = false;
            dozeScreenBrightness = -1;
            dozeScreenState = 0;
        }

        public DisplayPowerRequest(DisplayPowerRequest displaypowerrequest)
        {
            copyFrom(displaypowerrequest);
        }
    }

    public static interface DisplayTransactionListener
    {

        public abstract void onDisplayTransaction();
    }


    public DisplayManagerInternal()
    {
    }

    public abstract DisplayInfo getDisplayInfo(int i);

    public abstract void getNonOverrideDisplayInfo(int i, DisplayInfo displayinfo);

    public abstract int getScreenBrightnessFromDisplayManager();

    public abstract void initPowerManagement(DisplayPowerCallbacks displaypowercallbacks, Handler handler, SensorManager sensormanager);

    public abstract boolean isProximitySensorAvailable();

    public abstract boolean isUidPresentOnDisplay(int i, int j);

    public abstract void performTraversalInTransactionFromWindowManager();

    public abstract void registerDisplayTransactionListener(DisplayTransactionListener displaytransactionlistener);

    public abstract boolean requestPowerState(DisplayPowerRequest displaypowerrequest, boolean flag);

    public abstract void setDisplayAccessUIDs(SparseArray sparsearray);

    public abstract void setDisplayInfoOverrideFromWindowManager(int i, DisplayInfo displayinfo);

    public abstract void setDisplayOffsets(int i, int j, int k);

    public abstract void setDisplayProperties(int i, boolean flag, float f, int j, boolean flag1);

    public abstract void unregisterDisplayTransactionListener(DisplayTransactionListener displaytransactionlistener);
}
