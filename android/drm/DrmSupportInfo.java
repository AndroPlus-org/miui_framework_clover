// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import java.util.ArrayList;
import java.util.Iterator;

public class DrmSupportInfo
{

    public DrmSupportInfo()
    {
        mDescription = "";
    }

    public void addFileSuffix(String s)
    {
        if(s == "")
        {
            throw new IllegalArgumentException("fileSuffix is an empty string");
        } else
        {
            mFileSuffixList.add(s);
            return;
        }
    }

    public void addMimeType(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("mimeType is null");
        if(s == "")
        {
            throw new IllegalArgumentException("mimeType is an empty string");
        } else
        {
            mMimeTypeList.add(s);
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof DrmSupportInfo)
        {
            obj = (DrmSupportInfo)obj;
            boolean flag1 = flag;
            if(mFileSuffixList.equals(((DrmSupportInfo) (obj)).mFileSuffixList))
            {
                flag1 = flag;
                if(mMimeTypeList.equals(((DrmSupportInfo) (obj)).mMimeTypeList))
                    flag1 = mDescription.equals(((DrmSupportInfo) (obj)).mDescription);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public String getDescriprition()
    {
        return mDescription;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public Iterator getFileSuffixIterator()
    {
        return mFileSuffixList.iterator();
    }

    public Iterator getMimeTypeIterator()
    {
        return mMimeTypeList.iterator();
    }

    public int hashCode()
    {
        return mFileSuffixList.hashCode() + mMimeTypeList.hashCode() + mDescription.hashCode();
    }

    boolean isSupportedFileSuffix(String s)
    {
        return mFileSuffixList.contains(s);
    }

    boolean isSupportedMimeType(String s)
    {
        if(s != null && s.equals("") ^ true)
        {
            for(int i = 0; i < mMimeTypeList.size(); i++)
                if(((String)mMimeTypeList.get(i)).startsWith(s))
                    return true;

        }
        return false;
    }

    public void setDescription(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("description is null");
        if(s == "")
        {
            throw new IllegalArgumentException("description is an empty string");
        } else
        {
            mDescription = s;
            return;
        }
    }

    private String mDescription;
    private final ArrayList mFileSuffixList = new ArrayList();
    private final ArrayList mMimeTypeList = new ArrayList();
}
