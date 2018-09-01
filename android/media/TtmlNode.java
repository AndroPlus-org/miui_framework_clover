// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.ArrayList;
import java.util.List;

class TtmlNode
{

    public TtmlNode(String s, String s1, String s2, long l, long l1, 
            TtmlNode ttmlnode, long l2)
    {
        mName = s;
        mAttributes = s1;
        mText = s2;
        mStartTimeMs = l;
        mEndTimeMs = l1;
        mParent = ttmlnode;
        mRunId = l2;
    }

    public boolean isActive(long l, long l1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mEndTimeMs > l)
        {
            flag1 = flag;
            if(mStartTimeMs < l1)
                flag1 = true;
        }
        return flag1;
    }

    public final String mAttributes;
    public final List mChildren = new ArrayList();
    public final long mEndTimeMs;
    public final String mName;
    public final TtmlNode mParent;
    public final long mRunId;
    public final long mStartTimeMs;
    public final String mText;
}
