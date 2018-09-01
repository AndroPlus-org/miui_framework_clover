// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import java.util.List;

// Referenced classes of package android.bluetooth.le:
//            ScanFilter

public final class TruncatedFilter
{

    public TruncatedFilter(ScanFilter scanfilter, List list)
    {
        mFilter = scanfilter;
        mStorageDescriptors = list;
    }

    public ScanFilter getFilter()
    {
        return mFilter;
    }

    public List getStorageDescriptors()
    {
        return mStorageDescriptors;
    }

    private final ScanFilter mFilter;
    private final List mStorageDescriptors;
}
