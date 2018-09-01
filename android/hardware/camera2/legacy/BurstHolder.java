// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import java.util.*;

public class BurstHolder
{

    public BurstHolder(int i, boolean flag, CaptureRequest acapturerequest[], Collection collection)
    {
        int j = 0;
        int k = acapturerequest.length;
        for(int l = 0; l < k; l++)
        {
            CaptureRequest capturerequest = acapturerequest[l];
            mRequestBuilders.add(new RequestHolder.Builder(i, j, capturerequest, flag, collection));
            j++;
        }

        mRepeating = flag;
        mRequestId = i;
    }

    public int getNumberOfRequests()
    {
        return mRequestBuilders.size();
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public boolean isRepeating()
    {
        return mRepeating;
    }

    public List produceRequestHolders(long l)
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        for(Iterator iterator = mRequestBuilders.iterator(); iterator.hasNext();)
        {
            arraylist.add(((RequestHolder.Builder)iterator.next()).build((long)i + l));
            i++;
        }

        return arraylist;
    }

    private static final String TAG = "BurstHolder";
    private final boolean mRepeating;
    private final ArrayList mRequestBuilders = new ArrayList();
    private final int mRequestId;
}
