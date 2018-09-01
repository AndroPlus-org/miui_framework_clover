// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import java.io.IOException;
import java.util.*;

// Referenced classes of package android.drm:
//            DrmUtils, DrmInfoRequest

public class DrmInfo
{

    public DrmInfo(int i, String s, String s1)
    {
        mAttributes = new HashMap();
        mInfoType = i;
        mMimeType = s1;
        try
        {
            mData = DrmUtils.readBytes(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            mData = null;
        }
        if(!isValid())
        {
            (new StringBuilder()).append("infoType: ").append(i).append(",").append("mimeType: ").append(s1).append(",").append("data: ").append(Arrays.toString(mData)).toString();
            throw new IllegalArgumentException();
        } else
        {
            return;
        }
    }

    public DrmInfo(int i, byte abyte0[], String s)
    {
        mAttributes = new HashMap();
        mInfoType = i;
        mMimeType = s;
        mData = abyte0;
        if(!isValid())
            throw new IllegalArgumentException((new StringBuilder()).append("infoType: ").append(i).append(",").append("mimeType: ").append(s).append(",").append("data: ").append(Arrays.toString(abyte0)).toString());
        else
            return;
    }

    public Object get(String s)
    {
        return mAttributes.get(s);
    }

    public byte[] getData()
    {
        return mData;
    }

    public int getInfoType()
    {
        return mInfoType;
    }

    public String getMimeType()
    {
        return mMimeType;
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
                        flag1 = DrmInfoRequest.isValidType(mInfoType);
                }
            }
        }
        return flag1;
    }

    public Iterator iterator()
    {
        return mAttributes.values().iterator();
    }

    public Iterator keyIterator()
    {
        return mAttributes.keySet().iterator();
    }

    public void put(String s, Object obj)
    {
        mAttributes.put(s, obj);
    }

    private final HashMap mAttributes;
    private byte mData[];
    private final int mInfoType;
    private final String mMimeType;
}
