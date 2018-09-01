// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.cas.V1_0;

import java.util.ArrayList;

public final class Status
{

    public Status()
    {
    }

    public static final String dumpBitfield(int i)
    {
        ArrayList arraylist = new ArrayList();
        int j = 0;
        arraylist.add("OK");
        if((i & 1) == 1)
        {
            arraylist.add("ERROR_CAS_NO_LICENSE");
            j = 1;
        }
        int k = j;
        if((i & 2) == 2)
        {
            arraylist.add("ERROR_CAS_LICENSE_EXPIRED");
            k = j | 2;
        }
        j = k;
        if((i & 3) == 3)
        {
            arraylist.add("ERROR_CAS_SESSION_NOT_OPENED");
            j = k | 3;
        }
        k = j;
        if((i & 4) == 4)
        {
            arraylist.add("ERROR_CAS_CANNOT_HANDLE");
            k = j | 4;
        }
        j = k;
        if((i & 5) == 5)
        {
            arraylist.add("ERROR_CAS_INVALID_STATE");
            j = k | 5;
        }
        int l = j;
        if((i & 6) == 6)
        {
            arraylist.add("BAD_VALUE");
            l = j | 6;
        }
        k = l;
        if((i & 7) == 7)
        {
            arraylist.add("ERROR_CAS_NOT_PROVISIONED");
            k = l | 7;
        }
        j = k;
        if((i & 8) == 8)
        {
            arraylist.add("ERROR_CAS_RESOURCE_BUSY");
            j = k | 8;
        }
        k = j;
        if((i & 9) == 9)
        {
            arraylist.add("ERROR_CAS_INSUFFICIENT_OUTPUT_PROTECTION");
            k = j | 9;
        }
        j = k;
        if((i & 0xa) == 10)
        {
            arraylist.add("ERROR_CAS_TAMPER_DETECTED");
            j = k | 0xa;
        }
        k = j;
        if((i & 0xb) == 11)
        {
            arraylist.add("ERROR_CAS_DEVICE_REVOKED");
            k = j | 0xb;
        }
        j = k;
        if((i & 0xc) == 12)
        {
            arraylist.add("ERROR_CAS_DECRYPT_UNIT_NOT_INITIALIZED");
            j = k | 0xc;
        }
        k = j;
        if((i & 0xd) == 13)
        {
            arraylist.add("ERROR_CAS_DECRYPT");
            k = j | 0xd;
        }
        j = k;
        if((i & 0xe) == 14)
        {
            arraylist.add("ERROR_CAS_UNKNOWN");
            j = k | 0xe;
        }
        if(i != j)
            arraylist.add((new StringBuilder()).append("0x").append(Integer.toHexString(j & i)).toString());
        return String.join(" | ", arraylist);
    }

    public static final String toString(int i)
    {
        if(i == 0)
            return "OK";
        if(i == 1)
            return "ERROR_CAS_NO_LICENSE";
        if(i == 2)
            return "ERROR_CAS_LICENSE_EXPIRED";
        if(i == 3)
            return "ERROR_CAS_SESSION_NOT_OPENED";
        if(i == 4)
            return "ERROR_CAS_CANNOT_HANDLE";
        if(i == 5)
            return "ERROR_CAS_INVALID_STATE";
        if(i == 6)
            return "BAD_VALUE";
        if(i == 7)
            return "ERROR_CAS_NOT_PROVISIONED";
        if(i == 8)
            return "ERROR_CAS_RESOURCE_BUSY";
        if(i == 9)
            return "ERROR_CAS_INSUFFICIENT_OUTPUT_PROTECTION";
        if(i == 10)
            return "ERROR_CAS_TAMPER_DETECTED";
        if(i == 11)
            return "ERROR_CAS_DEVICE_REVOKED";
        if(i == 12)
            return "ERROR_CAS_DECRYPT_UNIT_NOT_INITIALIZED";
        if(i == 13)
            return "ERROR_CAS_DECRYPT";
        if(i == 14)
            return "ERROR_CAS_UNKNOWN";
        else
            return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();
    }

    public static final int BAD_VALUE = 6;
    public static final int ERROR_CAS_CANNOT_HANDLE = 4;
    public static final int ERROR_CAS_DECRYPT = 13;
    public static final int ERROR_CAS_DECRYPT_UNIT_NOT_INITIALIZED = 12;
    public static final int ERROR_CAS_DEVICE_REVOKED = 11;
    public static final int ERROR_CAS_INSUFFICIENT_OUTPUT_PROTECTION = 9;
    public static final int ERROR_CAS_INVALID_STATE = 5;
    public static final int ERROR_CAS_LICENSE_EXPIRED = 2;
    public static final int ERROR_CAS_NOT_PROVISIONED = 7;
    public static final int ERROR_CAS_NO_LICENSE = 1;
    public static final int ERROR_CAS_RESOURCE_BUSY = 8;
    public static final int ERROR_CAS_SESSION_NOT_OPENED = 3;
    public static final int ERROR_CAS_TAMPER_DETECTED = 10;
    public static final int ERROR_CAS_UNKNOWN = 14;
    public static final int OK = 0;
}
