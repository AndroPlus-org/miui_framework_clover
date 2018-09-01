// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;


// Referenced classes of package miui.telephony:
//            PhoneDebug, TelephonyUtils

public abstract class SubscriptionInfo
{

    public SubscriptionInfo()
    {
    }

    public abstract CharSequence getDisplayName();

    public abstract String getDisplayNumber();

    public abstract String getIccId();

    public int getMcc()
    {
        return 0;
    }

    public int getMnc()
    {
        return 0;
    }

    public abstract int getPhoneId();

    public abstract int getSlotId();

    public abstract int getSubscriptionId();

    public abstract boolean isActivated();

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder(128)).append("{id=").append(getSubscriptionId()).append(", iccId=");
        String s;
        if(PhoneDebug.VDBG)
            s = getIccId();
        else
            s = TelephonyUtils.pii(getIccId());
        return stringbuilder.append(s).append(", slotId=").append(getSlotId()).append(", displayName=").append(getDisplayName()).append(", displayNumber=").append(TelephonyUtils.pii(getDisplayNumber())).append(", isActivated=").append(isActivated()).append('}').toString();
    }
}
