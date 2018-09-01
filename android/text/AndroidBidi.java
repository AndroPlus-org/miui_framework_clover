// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            Layout

public class AndroidBidi
{

    public AndroidBidi()
    {
    }

    public static int bidi(int i, char ac[], byte abyte0[], int j, boolean flag)
    {
        if(ac == null || abyte0 == null)
            throw new NullPointerException();
        while(j < 0 || ac.length < j || abyte0.length < j) 
            throw new IndexOutOfBoundsException();
        i;
        JVM INSTR tableswitch -2 2: default 76
    //                   -2 112
    //                   -1 101
    //                   0 76
    //                   1 96
    //                   2 106;
           goto _L1 _L2 _L3 _L1 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_112;
_L1:
        i = 0;
_L6:
        if((runBidi(i, ac, abyte0, j, flag) & 1) == 0)
            i = 1;
        else
            i = -1;
        return i;
_L4:
        i = 0;
          goto _L6
_L3:
        i = 1;
          goto _L6
_L5:
        i = -2;
          goto _L6
        i = -1;
          goto _L6
    }

    public static Layout.Directions directions(int i, byte abyte0[], int j, char ac[], int k, int l)
    {
        int i1;
        int j1;
        int l1;
        int k2;
        int i3;
        int l3;
        if(l == 0)
            return Layout.DIRS_ALL_LEFT_TO_RIGHT;
        if(i == 1)
            i1 = 0;
        else
            i1 = 1;
        j1 = abyte0[j];
        i = j1;
        l1 = 1;
        for(int j2 = j + 1; j2 < j + l;)
        {
            byte byte0 = abyte0[j2];
            byte byte1 = j1;
            int j3 = l1;
            if(byte0 != j1)
            {
                byte1 = byte0;
                j3 = l1 + 1;
            }
            j2++;
            j1 = byte1;
            l1 = j3;
        }

        k2 = l;
        l3 = l1;
        i3 = k2;
        if((j1 & 1) == (i1 & 1)) goto _L2; else goto _L1
_L1:
        j1 = k2;
_L8:
        k2 = j1 - 1;
        j1 = k2;
        if(k2 < 0) goto _L4; else goto _L3
_L3:
        char c = ac[k + k2];
        if(c != '\n') goto _L6; else goto _L5
_L5:
        j1 = k2 - 1;
_L4:
        k = j1 + 1;
        l3 = l1;
        i3 = k;
        if(k != l)
        {
            l3 = l1 + 1;
            i3 = k;
        }
_L2:
        if(l3 == 1 && i == i1)
            if((i & 1) != 0)
                return Layout.DIRS_ALL_RIGHT_TO_LEFT;
            else
                return Layout.DIRS_ALL_LEFT_TO_RIGHT;
        break MISSING_BLOCK_LABEL_243;
_L6:
        j1 = k2;
        if(c == ' ') goto _L8; else goto _L7
_L7:
        j1 = k2;
        if(c == '\t') goto _L8; else goto _L9
_L9:
        j1 = k2;
          goto _L4
        ac = new int[l3 * 2];
        k = i;
        int i4 = i << 26;
        int j4 = j;
        int k1 = i;
        int i2 = j;
        int l2 = 1;
        while(i2 < j + i3) 
        {
            byte byte2 = abyte0[i2];
            int k3;
            if(byte2 != k1)
            {
                byte byte3 = byte2;
                if(byte2 > k)
                {
                    k1 = byte2;
                    k3 = i;
                } else
                {
                    k1 = k;
                    k3 = i;
                    if(byte2 < i)
                    {
                        k3 = byte2;
                        k1 = k;
                    }
                }
                i = l2 + 1;
                ac[l2] = i2 - j4 | i4;
                ac[i] = i2 - j;
                i4 = byte2 << 26;
                j4 = i2;
                i++;
                k = k1;
                k1 = byte3;
            } else
            {
                k3 = i;
                i = l2;
            }
            i2++;
            l2 = i;
            i = k3;
        }
        ac[l2] = (j + i3) - j4 | i4;
        if(i3 < l)
        {
            j = l2 + 1;
            ac[j] = i3;
            ac[j + 1] = l - i3 | i1 << 26;
        }
        if((i & 1) == i1)
        {
            j = i + 1;
            if(k > j)
                i = 1;
            else
                i = 0;
        } else
        if(l3 > 1)
        {
            l = 1;
            j = i;
            i = l;
        } else
        {
            l = 0;
            j = i;
            i = l;
        }
        if(i != 0)
            for(k--; k >= j; k--)
                for(i = 0; i < ac.length; i = l + 2)
                {
                    l = i;
                    if(abyte0[ac[i]] < k)
                        continue;
                    for(l = i + 2; l < ac.length && abyte0[ac[l]] >= k; l += 2);
                    for(i2 = l - 2; i < i2; i2 -= 2)
                    {
                        l2 = ac[i];
                        ac[i] = ac[i2];
                        ac[i2] = l2;
                        l2 = ac[i + 1];
                        ac[i + 1] = ac[i2 + 1];
                        ac[i2 + 1] = l2;
                        i += 2;
                    }

                    l += 2;
                }


        return new Layout.Directions(ac);
    }

    private static native int runBidi(int i, char ac[], byte abyte0[], int j, boolean flag);
}
