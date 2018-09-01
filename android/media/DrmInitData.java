// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.Arrays;
import java.util.UUID;

public abstract class DrmInitData
{
    public static final class SchemeInitData
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof SchemeInitData))
                return false;
            if(obj == this)
                return true;
            obj = (SchemeInitData)obj;
            if(mimeType.equals(((SchemeInitData) (obj)).mimeType))
                flag = Arrays.equals(data, ((SchemeInitData) (obj)).data);
            return flag;
        }

        public int hashCode()
        {
            return mimeType.hashCode() + Arrays.hashCode(data) * 31;
        }

        public final byte data[];
        public final String mimeType;

        public SchemeInitData(String s, byte abyte0[])
        {
            mimeType = s;
            data = abyte0;
        }
    }


    DrmInitData()
    {
    }

    public abstract SchemeInitData get(UUID uuid);
}
