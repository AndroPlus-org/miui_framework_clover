// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;


public class DrmConvertedStatus
{

    public DrmConvertedStatus(int i, byte abyte0[], int j)
    {
        if(!isValidStatusCode(i))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported status code: ").append(i).toString());
        } else
        {
            statusCode = i;
            convertedData = abyte0;
            offset = j;
            return;
        }
    }

    private boolean isValidStatusCode(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 1) goto _L2; else goto _L1
_L1:
        if(i != 2) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 3)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static final int STATUS_ERROR = 3;
    public static final int STATUS_INPUTDATA_ERROR = 2;
    public static final int STATUS_OK = 1;
    public final byte convertedData[];
    public final int offset;
    public final int statusCode;
}
