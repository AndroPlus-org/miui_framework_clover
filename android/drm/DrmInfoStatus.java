// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;


// Referenced classes of package android.drm:
//            DrmInfoRequest, ProcessedData

public class DrmInfoStatus
{

    public DrmInfoStatus(int i, int j, ProcessedData processeddata, String s)
    {
        if(!DrmInfoRequest.isValidType(j))
            throw new IllegalArgumentException((new StringBuilder()).append("infoType: ").append(j).toString());
        if(!isValidStatusCode(i))
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported status code: ").append(i).toString());
        if(s == null || s == "")
        {
            throw new IllegalArgumentException("mimeType is null or an empty string");
        } else
        {
            statusCode = i;
            infoType = j;
            data = processeddata;
            mimeType = s;
            return;
        }
    }

    private boolean isValidStatusCode(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 1)
            if(i == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static final int STATUS_ERROR = 2;
    public static final int STATUS_OK = 1;
    public final ProcessedData data;
    public final int infoType;
    public final String mimeType;
    public final int statusCode;
}
