// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package android.drm:
//            ProcessedData, DrmUtils

public class DrmRights
{

    public DrmRights(ProcessedData processeddata, String s)
    {
        if(processeddata == null)
            throw new IllegalArgumentException("data is null");
        mData = processeddata.getData();
        mAccountId = processeddata.getAccountId();
        mSubscriptionId = processeddata.getSubscriptionId();
        mMimeType = s;
        if(!isValid())
            throw new IllegalArgumentException((new StringBuilder()).append("mimeType: ").append(mMimeType).append(",").append("data: ").append(Arrays.toString(mData)).toString());
        else
            return;
    }

    public DrmRights(File file, String s)
    {
        instantiate(file, s);
    }

    public DrmRights(String s, String s1)
    {
        instantiate(new File(s), s1);
    }

    public DrmRights(String s, String s1, String s2)
    {
        this(s, s1);
        mAccountId = s2;
    }

    public DrmRights(String s, String s1, String s2, String s3)
    {
        this(s, s1);
        mAccountId = s2;
        mSubscriptionId = s3;
    }

    private void instantiate(File file, String s)
    {
        try
        {
            mData = DrmUtils.readBytes(file);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            file.printStackTrace();
        }
        mMimeType = s;
        if(!isValid())
            throw new IllegalArgumentException((new StringBuilder()).append("mimeType: ").append(mMimeType).append(",").append("data: ").append(Arrays.toString(mData)).toString());
        else
            return;
    }

    public String getAccountId()
    {
        return mAccountId;
    }

    public byte[] getData()
    {
        return mData;
    }

    public String getMimeType()
    {
        return mMimeType;
    }

    public String getSubscriptionId()
    {
        return mSubscriptionId;
    }

    boolean isValid()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mMimeType != null)
        {
            flag1 = flag;
            if(mMimeType.equals("") ^ true)
            {
                flag1 = flag;
                if(mData != null)
                {
                    flag1 = flag;
                    if(mData.length > 0)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private String mAccountId;
    private byte mData[];
    private String mMimeType;
    private String mSubscriptionId;
}
