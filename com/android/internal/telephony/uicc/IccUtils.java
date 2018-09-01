// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.uicc;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.telephony.Rlog;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class IccUtils
{

    public IccUtils()
    {
    }

    public static String adnStringFieldToString(byte abyte0[], int i, int j)
    {
        int k;
        Object obj;
        if(j == 0)
            return "";
        if(j < 1 || abyte0[i] != -128)
            break MISSING_BLOCK_LABEL_109;
        k = (j - 1) / 2;
        obj = null;
        Object obj1;
        obj1 = JVM INSTR new #23  <Class String>;
        ((String) (obj1)).String(abyte0, i + 1, k * 2, "utf-16be");
        obj = obj1;
_L2:
        if(obj == null)
            break MISSING_BLOCK_LABEL_109;
        for(i = ((String) (obj)).length(); i > 0 && ((String) (obj)).charAt(i - 1) == '\uFFFF'; i--);
        break; /* Loop/switch isn't completed */
        Object obj2;
        obj2;
        Rlog.e("IccUtils", "implausible UnsupportedEncodingException", ((Throwable) (obj2)));
        if(true) goto _L2; else goto _L1
_L1:
        return ((String) (obj)).substring(0, i);
        int i1;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        int l;
        char c;
        boolean flag3;
        if(j >= 3 && abyte0[i] == -127)
        {
            i1 = abyte0[i + 1] & 0xff;
            l = i1;
            if(i1 > j - 3)
                l = j - 3;
            c = (char)((abyte0[i + 2] & 0xff) << 7);
            i1 = i + 3;
            flag3 = true;
        } else
        {
            c = flag1;
            flag3 = flag;
            l = ((flag2) ? 1 : 0);
            i1 = i;
            if(j >= 4)
            {
                c = flag1;
                flag3 = flag;
                l = ((flag2) ? 1 : 0);
                i1 = i;
                if(abyte0[i] == -126)
                {
                    i1 = abyte0[i + 1] & 0xff;
                    l = i1;
                    if(i1 > j - 4)
                        l = j - 4;
                    c = (char)((abyte0[i + 2] & 0xff) << 8 | abyte0[i + 3] & 0xff);
                    i1 = i + 4;
                    flag3 = true;
                }
            }
        }
        if(flag3)
        {
            obj = new StringBuilder();
            for(; l > 0; l = j - l)
            {
                j = l;
                i = i1;
                if(abyte0[i1] < 0)
                {
                    ((StringBuilder) (obj)).append((char)((abyte0[i1] & 0x7f) + c));
                    i = i1 + 1;
                    j = l - 1;
                }
                for(l = 0; l < j && abyte0[i + l] >= 0; l++);
                ((StringBuilder) (obj)).append(GsmAlphabet.gsm8BitUnpackedToString(abyte0, i, l));
                i1 = i + l;
            }

            return ((StringBuilder) (obj)).toString();
        }
        obj2 = Resources.getSystem();
        obj = "";
        obj2 = ((Resources) (obj2)).getString(0x1040262);
        obj = obj2;
_L4:
        return GsmAlphabet.gsm8BitUnpackedToString(abyte0, i1, j, ((String) (obj)).trim());
        obj2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String bcdPlmnToString(byte abyte0[], int i)
    {
        if(i + 3 > abyte0.length)
            return null;
        String s = bytesToHexString(new byte[] {
            (byte)(abyte0[i + 0] << 4 | abyte0[i + 0] >> 4 & 0xf), (byte)(abyte0[i + 1] << 4 | abyte0[i + 2] & 0xf), (byte)(abyte0[i + 2] & 0xf0 | abyte0[i + 1] >> 4 & 0xf)
        });
        abyte0 = s;
        if(s.endsWith("f"))
            abyte0 = s.substring(0, s.length() - 1);
        return abyte0;
    }

    public static String bcdToString(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder;
        int k;
        stringbuilder = new StringBuilder(j * 2);
        k = i;
_L4:
        if(k >= i + j) goto _L2; else goto _L1
_L1:
        int l = abyte0[k] & 0xf;
        if(l <= 9) goto _L3; else goto _L2
_L2:
        return stringbuilder.toString();
_L3:
        stringbuilder.append((char)(l + 48));
        l = abyte0[k] >> 4 & 0xf;
        if(l != 15)
            continue; /* Loop/switch isn't completed */
_L6:
        k++;
          goto _L4
        if(l > 9) goto _L2; else goto _L5
_L5:
        stringbuilder.append((char)(l + 48));
          goto _L6
    }

    public static String bchToString(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder = new StringBuilder(j * 2);
        for(int k = i; k < i + j; k++)
        {
            stringbuilder.append("0123456789abcdef".charAt(abyte0[k] & 0xf));
            stringbuilder.append("0123456789abcdef".charAt(abyte0[k] >> 4 & 0xf));
        }

        return stringbuilder.toString();
    }

    private static int bitToRGB(int i)
    {
        return i != 1 ? 0xff000000 : -1;
    }

    public static String bytesToHexString(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder(abyte0.length * 2);
        for(int i = 0; i < abyte0.length; i++)
        {
            stringbuilder.append("0123456789abcdef".charAt(abyte0[i] >> 4 & 0xf));
            stringbuilder.append("0123456789abcdef".charAt(abyte0[i] & 0xf));
        }

        return stringbuilder.toString();
    }

    public static int cdmaBcdByteToInt(byte byte0)
    {
        int i = 0;
        if((byte0 & 0xf0) <= 144)
            i = (byte0 >> 4 & 0xf) * 10;
        int j = i;
        if((byte0 & 0xf) <= 9)
            j = i + (byte0 & 0xf);
        return j;
    }

    public static String cdmaBcdToString(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder = new StringBuilder(j);
        int k = 0;
        do
        {
            int l;
label0:
            {
                if(k < j)
                {
                    l = abyte0[i] & 0xf;
                    int i1 = l;
                    if(l > 9)
                        i1 = 0;
                    stringbuilder.append((char)(i1 + 48));
                    l = k + 1;
                    if(l != j)
                        break label0;
                }
                return stringbuilder.toString();
            }
            int j1 = abyte0[i] >> 4 & 0xf;
            k = j1;
            if(j1 > 9)
                k = 0;
            stringbuilder.append((char)(k + 48));
            k = l + 1;
            i++;
        } while(true);
    }

    private static int[] getCLUT(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            return null;
        int ai[] = new int[j];
        int k = i;
        int l = 0;
        do
        {
            int i1 = k + 1;
            byte byte0 = abyte0[k];
            int j1 = i1 + 1;
            i1 = abyte0[i1];
            k = j1 + 1;
            ai[l] = (byte0 & 0xff) << 16 | 0xff000000 | (i1 & 0xff) << 8 | abyte0[j1] & 0xff;
            if(k < i + j * 3)
                l++;
            else
                return ai;
        } while(true);
    }

    public static int gsmBcdByteToInt(byte byte0)
    {
        int i = 0;
        if((byte0 & 0xf0) <= 144)
            i = byte0 >> 4 & 0xf;
        int j = i;
        if((byte0 & 0xf) <= 9)
            j = i + (byte0 & 0xf) * 10;
        return j;
    }

    static int hexCharToInt(char c)
    {
        if(c >= '0' && c <= '9')
            return c - 48;
        if(c >= 'A' && c <= 'F')
            return (c - 65) + 10;
        if(c >= 'a' && c <= 'f')
            return (c - 97) + 10;
        else
            throw new RuntimeException((new StringBuilder()).append("invalid hex char '").append(c).append("'").toString());
    }

    public static byte[] hexStringToBytes(String s)
    {
        if(s == null)
            return null;
        int i = s.length();
        byte abyte0[] = new byte[i / 2];
        for(int j = 0; j < i; j += 2)
            abyte0[j / 2] = (byte)(hexCharToInt(s.charAt(j)) << 4 | hexCharToInt(s.charAt(j + 1)));

        return abyte0;
    }

    private static int[] mapTo2OrderBitColor(byte abyte0[], int i, int j, int ai[], int k)
    {
        int l;
        char c;
        if(8 % k != 0)
        {
            Rlog.e("IccUtils", "not event number of color");
            return mapToNon2OrderBitColor(abyte0, i, j, ai, k);
        }
        l = 1;
        c = l;
        k;
        JVM INSTR tableswitch 1 8: default 80
    //                   1 157
    //                   2 163
    //                   3 84
    //                   4 169
    //                   5 84
    //                   6 84
    //                   7 84
    //                   8 176;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L4 _L4 _L6
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        c = l;
_L10:
        int ai1[];
        int j1;
        ai1 = new int[j];
        boolean flag = false;
        j1 = 8 / k;
        l = i;
        i = ((flag) ? 1 : 0);
_L8:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        byte byte0 = abyte0[l];
        for(int i1 = 0; i1 < j1;)
        {
            ai1[i] = ai[byte0 >> (j1 - i1 - 1) * k & c];
            i1++;
            i++;
        }

        l++;
        continue; /* Loop/switch isn't completed */
_L2:
        c = '\001';
        continue; /* Loop/switch isn't completed */
_L3:
        c = '\003';
        continue; /* Loop/switch isn't completed */
_L5:
        c = '\017';
        continue; /* Loop/switch isn't completed */
_L6:
        c = '\377';
        continue; /* Loop/switch isn't completed */
        if(true) goto _L8; else goto _L7
_L7:
        return ai1;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static int[] mapToNon2OrderBitColor(byte abyte0[], int i, int j, int ai[], int k)
    {
        if(8 % k == 0)
        {
            Rlog.e("IccUtils", "not odd number of color");
            return mapTo2OrderBitColor(abyte0, i, j, ai, k);
        } else
        {
            return new int[j];
        }
    }

    public static String networkNameToString(byte abyte0[], int i, int j)
    {
        if((abyte0[i] & 0x80) != 128 || j < 1)
            return "";
        abyte0[i] >>> 4 & 7;
        JVM INSTR tableswitch 0 1: default 52
    //                   0 61
    //                   1 88;
           goto _L1 _L2 _L3
_L1:
        String s = "";
_L5:
        i = abyte0[i];
        return s;
_L2:
        s = GsmAlphabet.gsm7BitPackedToString(abyte0, i + 1, ((j - 1) * 8 - (abyte0[i] & 7)) / 7);
        continue; /* Loop/switch isn't completed */
_L3:
        try
        {
            s = JVM INSTR new #23  <Class String>;
            s.String(abyte0, i + 1, j - 1, "utf-16");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            s = "";
            Rlog.e("IccUtils", "implausible UnsupportedEncodingException", unsupportedencodingexception);
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static Bitmap parseToBnW(byte abyte0[], int i)
    {
        int j = abyte0[0] & 0xff;
        int k = 1 + 1;
        int l = abyte0[1] & 0xff;
        int i1 = j * l;
        int ai[] = new int[i1];
        i = 7;
        int j1 = 0;
        int k1 = 0;
        while(k1 < i1) 
        {
            int l1;
            int i2;
            if(k1 % 8 == 0)
            {
                i = k + 1;
                k = abyte0[k];
                l1 = 7;
            } else
            {
                int j2 = k;
                l1 = i;
                k = j1;
                i = j2;
            }
            ai[k1] = bitToRGB(k >> l1 & 1);
            j1 = l1 - 1;
            k1++;
            i2 = i;
            i = j1;
            j1 = k;
            k = i2;
        }
        if(k1 != i1)
            Rlog.e("IccUtils", "parse end and size error");
        return Bitmap.createBitmap(ai, j, l, android.graphics.Bitmap.Config.ARGB_8888);
    }

    public static Bitmap parseToRGB(byte abyte0[], int i, boolean flag)
    {
        i = abyte0[0] & 0xff;
        int j = 1 + 1;
        int k = abyte0[1] & 0xff;
        int l = j + 1;
        j = abyte0[j] & 0xff;
        int i1 = l + 1;
        l = abyte0[l] & 0xff;
        int j1 = i1 + 1;
        byte byte0 = abyte0[i1];
        i1 = j1 + 1;
        int ai[] = getCLUT(abyte0, (byte0 & 0xff) << 8 | abyte0[j1] & 0xff, l);
        if(flag)
            ai[l - 1] = 0;
        if(8 % j == 0)
            abyte0 = mapTo2OrderBitColor(abyte0, i1, i * k, ai, j);
        else
            abyte0 = mapToNon2OrderBitColor(abyte0, i1, i * k, ai, j);
        return Bitmap.createBitmap(abyte0, i, k, android.graphics.Bitmap.Config.RGB_565);
    }

    static byte[] stringToAdnStringField(String s)
    {
        boolean flag;
        int i;
        flag = false;
        i = 0;
_L2:
        boolean flag1 = flag;
        if(i >= s.length())
            break; /* Loop/switch isn't completed */
        GsmAlphabet.countGsmSeptets(s.charAt(i), true);
        i++;
        if(true) goto _L2; else goto _L1
        EncodeException encodeexception;
        encodeexception;
        flag1 = true;
_L1:
        return stringToAdnStringField(s, flag1);
    }

    static byte[] stringToAdnStringField(String s, boolean flag)
    {
        if(!flag)
        {
            return GsmAlphabet.stringToGsm8BitPacked(s);
        } else
        {
            s = s.getBytes(Charset.forName("UTF-16BE"));
            byte abyte0[] = new byte[s.length + 1];
            abyte0[0] = (byte)-128;
            System.arraycopy(s, 0, abyte0, 1, s.length);
            return abyte0;
        }
    }

    static final String LOG_TAG = "IccUtils";
}
