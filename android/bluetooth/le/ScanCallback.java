// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import java.util.List;

// Referenced classes of package android.bluetooth.le:
//            ScanResult

public abstract class ScanCallback
{

    public ScanCallback()
    {
    }

    public void onBatchScanResults(List list)
    {
    }

    public void onScanFailed(int i)
    {
    }

    public void onScanResult(int i, ScanResult scanresult)
    {
    }

    static final int NO_ERROR = 0;
    public static final int SCAN_FAILED_ALREADY_STARTED = 1;
    public static final int SCAN_FAILED_APPLICATION_REGISTRATION_FAILED = 2;
    public static final int SCAN_FAILED_FEATURE_UNSUPPORTED = 4;
    public static final int SCAN_FAILED_INTERNAL_ERROR = 3;
    public static final int SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES = 5;
    public static final int SCAN_FAILED_SCANNING_TOO_FREQUENTLY = 6;
}
