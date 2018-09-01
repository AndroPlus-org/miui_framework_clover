// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;


public class MtpEvent
{
    private static class IllegalParameterAccess extends UnsupportedOperationException
    {

        public IllegalParameterAccess(String s, int i)
        {
            super((new StringBuilder()).append("Cannot obtain ").append(s).append(" for the event: ").append(i).append(".").toString());
        }
    }


    private MtpEvent()
    {
        mEventCode = 16384;
    }

    public int getDevicePropCode()
    {
        switch(mEventCode)
        {
        default:
            throw new IllegalParameterAccess("devicePropCode", mEventCode);

        case 16390: 
            return mParameter1;
        }
    }

    public int getEventCode()
    {
        return mEventCode;
    }

    public int getObjectFormatCode()
    {
        switch(mEventCode)
        {
        default:
            throw new IllegalParameterAccess("objectFormatCode", mEventCode);

        case 51202: 
            return mParameter2;
        }
    }

    public int getObjectHandle()
    {
        switch(mEventCode)
        {
        default:
            throw new IllegalParameterAccess("objectHandle", mEventCode);

        case 16386: 
            return mParameter1;

        case 16387: 
            return mParameter1;

        case 16391: 
            return mParameter1;

        case 16393: 
            return mParameter1;

        case 51201: 
            return mParameter1;

        case 51203: 
            return mParameter1;
        }
    }

    public int getObjectPropCode()
    {
        switch(mEventCode)
        {
        default:
            throw new IllegalParameterAccess("objectPropCode", mEventCode);

        case 51201: 
            return mParameter2;

        case 51202: 
            return mParameter1;
        }
    }

    public int getParameter1()
    {
        return mParameter1;
    }

    public int getParameter2()
    {
        return mParameter2;
    }

    public int getParameter3()
    {
        return mParameter3;
    }

    public int getStorageId()
    {
        switch(mEventCode)
        {
        case 16390: 
        case 16391: 
        case 16392: 
        case 16393: 
        case 16395: 
        default:
            throw new IllegalParameterAccess("storageID", mEventCode);

        case 16388: 
            return mParameter1;

        case 16389: 
            return mParameter1;

        case 16394: 
            return mParameter1;

        case 16396: 
            return mParameter1;
        }
    }

    public int getTransactionId()
    {
        switch(mEventCode)
        {
        default:
            throw new IllegalParameterAccess("transactionID", mEventCode);

        case 16397: 
            return mParameter1;
        }
    }

    public static final int EVENT_CANCEL_TRANSACTION = 16385;
    public static final int EVENT_CAPTURE_COMPLETE = 16397;
    public static final int EVENT_DEVICE_INFO_CHANGED = 16392;
    public static final int EVENT_DEVICE_PROP_CHANGED = 16390;
    public static final int EVENT_DEVICE_RESET = 16395;
    public static final int EVENT_OBJECT_ADDED = 16386;
    public static final int EVENT_OBJECT_INFO_CHANGED = 16391;
    public static final int EVENT_OBJECT_PROP_CHANGED = 51201;
    public static final int EVENT_OBJECT_PROP_DESC_CHANGED = 51202;
    public static final int EVENT_OBJECT_REFERENCES_CHANGED = 51203;
    public static final int EVENT_OBJECT_REMOVED = 16387;
    public static final int EVENT_REQUEST_OBJECT_TRANSFER = 16393;
    public static final int EVENT_STORAGE_INFO_CHANGED = 16396;
    public static final int EVENT_STORE_ADDED = 16388;
    public static final int EVENT_STORE_FULL = 16394;
    public static final int EVENT_STORE_REMOVED = 16389;
    public static final int EVENT_UNDEFINED = 16384;
    public static final int EVENT_UNREPORTED_STATUS = 16398;
    private int mEventCode;
    private int mParameter1;
    private int mParameter2;
    private int mParameter3;
}
