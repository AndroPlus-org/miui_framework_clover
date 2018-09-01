// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.res.Resources;
import android.telephony.Rlog;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;

// Referenced classes of package com.android.internal.telephony:
//            EncodeException

public class GsmAlphabet
{
    private static class LanguagePairCount
    {

        final int languageCode;
        final int septetCounts[];
        final int unencodableCounts[];

        LanguagePairCount(int i)
        {
            int j;
            languageCode = i;
            j = GsmAlphabet._2D_get1();
            septetCounts = new int[j + 1];
            unencodableCounts = new int[j + 1];
            int k = 1;
            int l = 0;
            while(k <= j) 
            {
                if(GsmAlphabet._2D_get0()[l] == k)
                    l++;
                else
                    septetCounts[k] = -1;
                k++;
            }
            if(i != 1 || j < 1) goto _L2; else goto _L1
_L1:
            septetCounts[1] = -1;
_L4:
            return;
_L2:
            if(i == 3 && j >= 2)
                septetCounts[2] = -1;
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    public static class TextEncodingDetails
    {

        public String toString()
        {
            return (new StringBuilder()).append("TextEncodingDetails { msgCount=").append(msgCount).append(", codeUnitCount=").append(codeUnitCount).append(", codeUnitsRemaining=").append(codeUnitsRemaining).append(", codeUnitSize=").append(codeUnitSize).append(", languageTable=").append(languageTable).append(", languageShiftTable=").append(languageShiftTable).append(" }").toString();
        }

        public int codeUnitCount;
        public int codeUnitSize;
        public int codeUnitsRemaining;
        public int languageShiftTable;
        public int languageTable;
        public int msgCount;

        public TextEncodingDetails()
        {
        }
    }


    static int[] _2D_get0()
    {
        return sEnabledSingleShiftTables;
    }

    static int _2D_get1()
    {
        return sHighestEnabledSingleShiftCode;
    }

    private GsmAlphabet()
    {
    }

    public static int charToGsm(char c)
    {
        int i;
        try
        {
            i = charToGsm(c, false);
        }
        catch(EncodeException encodeexception)
        {
            return sCharsToGsmTables[0].get(32, 32);
        }
        return i;
    }

    public static int charToGsm(char c, boolean flag)
        throws EncodeException
    {
        int i = sCharsToGsmTables[0].get(c, -1);
        if(i == -1)
        {
            if(sCharsToShiftTables[0].get(c, -1) == -1)
            {
                if(flag)
                    throw new EncodeException(c);
                else
                    return sCharsToGsmTables[0].get(32, 32);
            } else
            {
                return 27;
            }
        } else
        {
            return i;
        }
    }

    public static int charToGsmExtended(char c)
    {
        c = sCharsToShiftTables[0].get(c, -1);
        if(c == '\uFFFF')
            return sCharsToGsmTables[0].get(32, 32);
        else
            return c;
    }

    public static int countGsmSeptets(char c)
    {
        int i;
        try
        {
            i = countGsmSeptets(c, false);
        }
        catch(EncodeException encodeexception)
        {
            return 0;
        }
        return i;
    }

    public static int countGsmSeptets(char c, boolean flag)
        throws EncodeException
    {
        if(sCharsToGsmTables[0].get(c, -1) != -1)
            return 1;
        if(sCharsToShiftTables[0].get(c, -1) != -1)
            return 2;
        if(flag)
            throw new EncodeException(c);
        else
            return 1;
    }

    public static TextEncodingDetails countGsmSeptets(CharSequence charsequence, boolean flag)
    {
        Object obj;
        int j;
        int k;
        if(!sDisableCountryEncodingCheck)
            enableCountrySpecificEncodings();
        if(sEnabledSingleShiftTables.length + sEnabledLockingShiftTables.length == 0)
        {
            TextEncodingDetails textencodingdetails = new TextEncodingDetails();
            int i = countGsmSeptetsUsingTables(charsequence, flag, 0, 0);
            if(i == -1)
                return null;
            textencodingdetails.codeUnitSize = 1;
            textencodingdetails.codeUnitCount = i;
            if(i > 160)
            {
                textencodingdetails.msgCount = (i + 152) / 153;
                textencodingdetails.codeUnitsRemaining = textencodingdetails.msgCount * 153 - i;
            } else
            {
                textencodingdetails.msgCount = 1;
                textencodingdetails.codeUnitsRemaining = 160 - i;
            }
            textencodingdetails.codeUnitSize = 1;
            return textencodingdetails;
        }
        k = sHighestEnabledSingleShiftCode;
        obj = new ArrayList(sEnabledLockingShiftTables.length + 1);
        ((List) (obj)).add(new LanguagePairCount(0));
        int ai[] = sEnabledLockingShiftTables;
        j = 0;
        for(int l = ai.length; j < l; j++)
        {
            int l1 = ai[j];
            if(l1 != 0 && sLanguageTables[l1].isEmpty() ^ true)
                ((List) (obj)).add(new LanguagePairCount(l1));
        }

        int i2 = charsequence.length();
        j = 0;
        while(j < i2 && ((List) (obj)).isEmpty() ^ true) 
        {
            char c = charsequence.charAt(j);
            if(c == '\033')
            {
                Rlog.w("GSM", "countGsmSeptets() string contains Escape character, ignoring!");
            } else
            {
                Iterator iterator = ((Iterable) (obj)).iterator();
                while(iterator.hasNext()) 
                {
                    LanguagePairCount languagepaircount1 = (LanguagePairCount)iterator.next();
                    if(sCharsToGsmTables[languagepaircount1.languageCode].get(c, -1) == -1)
                    {
                        int i1 = 0;
                        while(i1 <= k) 
                        {
                            if(languagepaircount1.septetCounts[i1] != -1)
                                if(sCharsToShiftTables[i1].get(c, -1) == -1)
                                {
                                    if(flag)
                                    {
                                        int ai1[] = languagepaircount1.septetCounts;
                                        ai1[i1] = ai1[i1] + 1;
                                        ai1 = languagepaircount1.unencodableCounts;
                                        ai1[i1] = ai1[i1] + 1;
                                    } else
                                    {
                                        languagepaircount1.septetCounts[i1] = -1;
                                    }
                                } else
                                {
                                    int ai2[] = languagepaircount1.septetCounts;
                                    ai2[i1] = ai2[i1] + 2;
                                }
                            i1++;
                        }
                    } else
                    {
                        int j1 = 0;
                        while(j1 <= k) 
                        {
                            if(languagepaircount1.septetCounts[j1] != -1)
                            {
                                int ai3[] = languagepaircount1.septetCounts;
                                ai3[j1] = ai3[j1] + 1;
                            }
                            j1++;
                        }
                    }
                }
            }
            j++;
        }
        charsequence = new TextEncodingDetails();
        charsequence.msgCount = 0x7fffffff;
        charsequence.codeUnitSize = 1;
        j = 0x7fffffff;
        obj = ((Iterable) (obj)).iterator();
_L4:
        if(!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        LanguagePairCount languagepaircount;
        int k1;
        int j2;
        languagepaircount = (LanguagePairCount)((Iterator) (obj)).next();
        j2 = 0;
        k1 = j;
_L7:
        j = k1;
        if(j2 > k) goto _L4; else goto _L3
_L3:
        int l2 = languagepaircount.septetCounts[j2];
        if(l2 != -1) goto _L6; else goto _L5
_L5:
        int i3 = k1;
_L9:
        j2++;
        k1 = i3;
          goto _L7
_L6:
        int k2;
        int j3;
        if(languagepaircount.languageCode != 0 && j2 != 0)
            j = 8;
        else
        if(languagepaircount.languageCode != 0 || j2 != 0)
            j = 5;
        else
            j = 0;
        if(l2 + j > 160)
        {
            k2 = j;
            if(j == 0)
                k2 = 1;
            k2 = 160 - (k2 + 6);
            j = ((l2 + k2) - 1) / k2;
            k2 = j * k2 - l2;
        } else
        {
            k2 = 1;
            i3 = 160 - j - l2;
            j = k2;
            k2 = i3;
        }
        j3 = languagepaircount.unencodableCounts[j2];
        if(!flag)
            break; /* Loop/switch isn't completed */
        i3 = k1;
        if(j3 > k1) goto _L9; else goto _L8
_L11:
        i3 = j3;
        charsequence.msgCount = j;
        charsequence.codeUnitCount = l2;
        charsequence.codeUnitsRemaining = k2;
        charsequence.languageTable = languagepaircount.languageCode;
        charsequence.languageShiftTable = j2;
        break; /* Loop/switch isn't completed */
_L8:
        if(flag && j3 < k1 || j < ((TextEncodingDetails) (charsequence)).msgCount) goto _L11; else goto _L10
_L10:
        i3 = k1;
        if(j != ((TextEncodingDetails) (charsequence)).msgCount)
            break; /* Loop/switch isn't completed */
        i3 = k1;
        if(k2 <= ((TextEncodingDetails) (charsequence)).codeUnitsRemaining) goto _L9; else goto _L11
_L2:
        if(((TextEncodingDetails) (charsequence)).msgCount == 0x7fffffff)
            return null;
        else
            return charsequence;
    }

    public static int countGsmSeptetsUsingTables(CharSequence charsequence, boolean flag, int i, int j)
    {
        boolean flag1 = false;
        int k = charsequence.length();
        SparseIntArray sparseintarray = sCharsToGsmTables[i];
        SparseIntArray sparseintarray1 = sCharsToShiftTables[j];
        j = 0;
        i = ((flag1) ? 1 : 0);
        while(j < k) 
        {
            char c = charsequence.charAt(j);
            if(c == '\033')
                Rlog.w("GSM", "countGsmSeptets() string contains Escape character, skipping.");
            else
            if(sparseintarray.get(c, -1) != -1)
                i++;
            else
            if(sparseintarray1.get(c, -1) != -1)
                i += 2;
            else
            if(flag)
                i++;
            else
                return -1;
            j++;
        }
        return i;
    }

    private static void enableCountrySpecificEncodings()
    {
        Resources resources = Resources.getSystem();
        sEnabledSingleShiftTables = resources.getIntArray(0x1070042);
        sEnabledLockingShiftTables = resources.getIntArray(0x1070041);
        if(sEnabledSingleShiftTables.length > 0)
            sHighestEnabledSingleShiftCode = sEnabledSingleShiftTables[sEnabledSingleShiftTables.length - 1];
        else
            sHighestEnabledSingleShiftCode = 0;
    }

    public static int findGsmSeptetLimitIndex(String s, int i, int j, int k, int l)
    {
        boolean flag = false;
        int i1 = s.length();
        SparseIntArray sparseintarray = sCharsToGsmTables[k];
        SparseIntArray sparseintarray1 = sCharsToShiftTables[l];
        k = i;
        i = ((flag) ? 1 : 0);
        for(; k < i1; k++)
        {
            if(sparseintarray.get(s.charAt(k), -1) == -1)
            {
                if(sparseintarray1.get(s.charAt(k), -1) == -1)
                    i++;
                else
                    i += 2;
            } else
            {
                i++;
            }
            if(i > j)
                return k;
        }

        return i1;
    }

    public static int[] getEnabledLockingShiftTables()
    {
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorenter ;
        int ai[] = sEnabledLockingShiftTables;
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorexit ;
        return ai;
        Exception exception;
        exception;
        throw exception;
    }

    public static int[] getEnabledSingleShiftTables()
    {
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorenter ;
        int ai[] = sEnabledSingleShiftTables;
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorexit ;
        return ai;
        Exception exception;
        exception;
        throw exception;
    }

    public static String gsm7BitPackedToString(byte abyte0[], int i, int j)
    {
        return gsm7BitPackedToString(abyte0, i, j, 0, 0, 0);
    }

    public static String gsm7BitPackedToString(byte abyte0[], int i, int j, int k, int l, int i1)
    {
        StringBuilder stringbuilder;
        int j1;
        Object obj1;
        char c;
label0:
        {
            stringbuilder = new StringBuilder(j);
            if(l >= 0)
            {
                j1 = l;
                if(l <= sLanguageTables.length)
                    break label0;
            }
            Rlog.w("GSM", (new StringBuilder()).append("unknown language table ").append(l).append(", using default").toString());
            j1 = 0;
        }
label1:
        {
            if(i1 >= 0)
            {
                l = i1;
                if(i1 <= sLanguageShiftTables.length)
                    break label1;
            }
            Rlog.w("GSM", (new StringBuilder()).append("unknown single shift table ").append(i1).append(", using default").toString());
            l = 0;
        }
        i1 = 0;
        Object obj;
        String s;
        int k1;
        int l1;
        int i2;
        try
        {
            obj = sLanguageTables[j1];
            s = sLanguageShiftTables[l];
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("GSM", "Error GSM 7 bit packed: ", abyte0);
            return null;
        }
        obj1 = obj;
        if(((String) (obj)).isEmpty())
        {
            obj1 = JVM INSTR new #118 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Rlog.w("GSM", ((StringBuilder) (obj1)).append("no language table for code ").append(j1).append(", using default").toString());
            obj1 = sLanguageTables[0];
        }
        obj = s;
        if(s.isEmpty())
        {
            obj = JVM INSTR new #118 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Rlog.w("GSM", ((StringBuilder) (obj)).append("no single shift table for code ").append(l).append(", using default").toString());
            obj = sLanguageShiftTables[0];
        }
        j1 = 0;
        l = i1;
        i1 = j1;
_L8:
        if(i1 >= j)
            break MISSING_BLOCK_LABEL_459;
        j1 = i1 * 7 + k;
        k1 = j1 / 8;
        l1 = j1 % 8;
        i2 = abyte0[i + k1] >> l1 & 0x7f;
        j1 = i2;
        if(l1 > 1)
            j1 = i2 & 127 >> l1 - 1 | abyte0[i + k1 + 1] << 8 - l1 & 0x7f;
        if(l == 0) goto _L2; else goto _L1
_L1:
        if(j1 != 27) goto _L4; else goto _L3
_L3:
        stringbuilder.append(' ');
_L5:
        l = 0;
_L6:
        i1++;
        continue; /* Loop/switch isn't completed */
_L4:
        c = ((String) (obj)).charAt(j1);
        if(c != ' ')
            break MISSING_BLOCK_LABEL_419;
        stringbuilder.append(((String) (obj1)).charAt(j1));
          goto _L5
        stringbuilder.append(c);
          goto _L5
_L2:
label2:
        {
            if(j1 != 27)
                break label2;
            l = 1;
        }
          goto _L6
        stringbuilder.append(((String) (obj1)).charAt(j1));
          goto _L6
        return stringbuilder.toString();
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static String gsm8BitUnpackedToString(byte abyte0[], int i, int j)
    {
        return gsm8BitUnpackedToString(abyte0, i, j, "");
    }

    public static String gsm8BitUnpackedToString(byte abyte0[], int i, int j, String s)
    {
        int k;
        String s1;
        StringBuilder stringbuilder;
        Charset charset;
        boolean flag;
        ByteBuffer bytebuffer;
        char c;
        k = 0;
        s1 = null;
        stringbuilder = null;
        charset = s1;
        flag = k;
        bytebuffer = stringbuilder;
        if(!TextUtils.isEmpty(s))
        {
            charset = s1;
            flag = k;
            bytebuffer = stringbuilder;
            if(s.equalsIgnoreCase("us-ascii") ^ true)
            {
                charset = s1;
                flag = k;
                bytebuffer = stringbuilder;
                if(Charset.isSupported(s))
                {
                    flag = true;
                    charset = Charset.forName(s);
                    bytebuffer = ByteBuffer.allocate(2);
                }
            }
        }
        s1 = sLanguageTables[0];
        s = sLanguageShiftTables[0];
        stringbuilder = new StringBuilder(j);
        c = '\0';
        k = i;
_L2:
        int i1;
label0:
        {
            if(k < i + j)
            {
                i1 = abyte0[k] & 0xff;
                if(i1 != 255)
                    break label0;
            }
            return stringbuilder.toString();
        }
        if(i1 != 27)
            break; /* Loop/switch isn't completed */
        if(c != 0)
        {
            stringbuilder.append(' ');
            c = '\0';
        } else
        {
            c = '\001';
        }
_L3:
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        if(c != 0)
        {
            char c1;
            if(i1 < s.length())
            {
                c = s.charAt(i1);
                c1 = c;
            } else
            {
                byte byte0 = 32;
                c1 = byte0;
            }
            if(c1 == ' ')
            {
                if(i1 < s1.length())
                    stringbuilder.append(s1.charAt(i1));
                else
                    stringbuilder.append(' ');
            } else
            {
                stringbuilder.append(c1);
            }
        } else
        if(!flag || i1 < 128 || k + 1 >= i + j)
        {
            if(i1 < s1.length())
                stringbuilder.append(s1.charAt(i1));
            else
                stringbuilder.append(' ');
        } else
        {
            bytebuffer.clear();
            int l = k + 1;
            bytebuffer.put(abyte0, k, 2);
            bytebuffer.flip();
            stringbuilder.append(charset.decode(bytebuffer).toString());
            k = l;
        }
        c = '\0';
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public static char gsmExtendedToChar(int i)
    {
        if(i == 27)
            return ' ';
        if(i >= 0 && i < 128)
        {
            char c = sLanguageShiftTables[0].charAt(i);
            if(c == ' ')
                return sLanguageTables[0].charAt(i);
            else
                return c;
        } else
        {
            return ' ';
        }
    }

    public static char gsmToChar(int i)
    {
        if(i >= 0 && i < 128)
            return sLanguageTables[0].charAt(i);
        else
            return ' ';
    }

    public static boolean isGsmSeptets(char c)
    {
        if(sCharsToGsmTables[0].get(c, -1) != -1)
            return true;
        return sCharsToShiftTables[0].get(c, -1) != -1;
    }

    private static void packSmsChar(byte abyte0[], int i, int j)
    {
        int k = i / 8;
        i %= 8;
        k++;
        abyte0[k] = (byte)(abyte0[k] | j << i);
        if(i > 1)
            abyte0[k + 1] = (byte)(j >> 8 - i);
    }

    public static void setEnabledLockingShiftTables(int ai[])
    {
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorenter ;
        sEnabledLockingShiftTables = ai;
        sDisableCountryEncodingCheck = true;
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorexit ;
        return;
        ai;
        throw ai;
    }

    public static void setEnabledSingleShiftTables(int ai[])
    {
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorenter ;
        sEnabledSingleShiftTables = ai;
        sDisableCountryEncodingCheck = true;
        if(ai.length <= 0) goto _L2; else goto _L1
_L1:
        sHighestEnabledSingleShiftCode = ai[ai.length - 1];
_L4:
        com/android/internal/telephony/GsmAlphabet;
        JVM INSTR monitorexit ;
        return;
_L2:
        sHighestEnabledSingleShiftCode = 0;
        if(true) goto _L4; else goto _L3
_L3:
        ai;
        throw ai;
    }

    public static byte[] stringToGsm7BitPacked(String s)
        throws EncodeException
    {
        return stringToGsm7BitPacked(s, 0, true, 0, 0);
    }

    public static byte[] stringToGsm7BitPacked(String s, int i, int j)
        throws EncodeException
    {
        return stringToGsm7BitPacked(s, 0, true, i, j);
    }

    public static byte[] stringToGsm7BitPacked(String s, int i, boolean flag, int j, int k)
        throws EncodeException
    {
        int l = s.length();
        int i1 = countGsmSeptetsUsingTables(s, flag ^ true, j, k);
        if(i1 == -1)
            throw new EncodeException("countGsmSeptetsUsingTables(): unencodable char");
        int j1 = i1 + i;
        if(j1 > 255)
            throw new EncodeException("Payload cannot exceed 255 septets");
        byte abyte0[] = new byte[(j1 * 7 + 7) / 8 + 1];
        SparseIntArray sparseintarray = sCharsToGsmTables[j];
        SparseIntArray sparseintarray1 = sCharsToShiftTables[k];
        i1 = 0;
        j = i;
        k = i * 7;
        i = j;
        j = k;
        while(i1 < l && i < j1) 
        {
            char c = s.charAt(i1);
            int k1 = sparseintarray.get(c, -1);
            int l1 = j;
            int i2 = i;
            k = k1;
            if(k1 == -1)
            {
                k = sparseintarray1.get(c, -1);
                if(k == -1)
                {
                    if(flag)
                        throw new EncodeException("stringToGsm7BitPacked(): unencodable char");
                    k = sparseintarray.get(32, 32);
                    i2 = i;
                    l1 = j;
                } else
                {
                    packSmsChar(abyte0, j, 27);
                    l1 = j + 7;
                    i2 = i + 1;
                }
            }
            packSmsChar(abyte0, l1, k);
            i = i2 + 1;
            i1++;
            j = l1 + 7;
        }
        abyte0[0] = (byte)j1;
        return abyte0;
    }

    public static byte[] stringToGsm7BitPackedWithHeader(String s, byte abyte0[])
        throws EncodeException
    {
        return stringToGsm7BitPackedWithHeader(s, abyte0, 0, 0);
    }

    public static byte[] stringToGsm7BitPackedWithHeader(String s, byte abyte0[], int i, int j)
        throws EncodeException
    {
        if(abyte0 == null || abyte0.length == 0)
        {
            return stringToGsm7BitPacked(s, i, j);
        } else
        {
            s = stringToGsm7BitPacked(s, ((abyte0.length + 1) * 8 + 6) / 7, true, i, j);
            s[1] = (byte)abyte0.length;
            System.arraycopy(abyte0, 0, s, 2, abyte0.length);
            return s;
        }
    }

    public static byte[] stringToGsm8BitPacked(String s)
    {
        byte abyte0[] = new byte[countGsmSeptetsUsingTables(s, true, 0, 0)];
        stringToGsm8BitUnpackedField(s, abyte0, 0, abyte0.length);
        return abyte0;
    }

    public static void stringToGsm8BitUnpackedField(String s, byte abyte0[], int i, int j)
    {
        SparseIntArray sparseintarray;
        SparseIntArray sparseintarray1;
        int k;
        int l;
        int i1;
        sparseintarray = sCharsToGsmTables[0];
        sparseintarray1 = sCharsToShiftTables[0];
        k = 0;
        l = s.length();
        i1 = i;
_L8:
        int j1 = i1;
        if(k >= l) goto _L2; else goto _L1
_L1:
        j1 = i1;
        if(i1 - i >= j) goto _L2; else goto _L3
_L3:
        char c;
        c = s.charAt(k);
        j1 = sparseintarray.get(c, -1);
        if(j1 != -1) goto _L5; else goto _L4
_L4:
        j1 = sparseintarray1.get(c, -1);
        if(j1 != -1) goto _L7; else goto _L6
_L6:
        j1 = sparseintarray.get(32, 32);
_L5:
        int k1 = i1 + 1;
        abyte0[i1] = (byte)j1;
        k++;
        i1 = k1;
          goto _L8
_L7:
        if((i1 + 1) - i < j)
            break MISSING_BLOCK_LABEL_158;
_L2:
        for(j1 = i1; j1 - i < j; j1++)
            abyte0[j1] = (byte)-1;

        break MISSING_BLOCK_LABEL_178;
        int l1 = i1 + 1;
        abyte0[i1] = (byte)27;
        i1 = l1;
          goto _L5
    }

    public static final byte GSM_EXTENDED_ESCAPE = 27;
    private static final String TAG = "GSM";
    public static final int UDH_SEPTET_COST_CONCATENATED_MESSAGE = 6;
    public static final int UDH_SEPTET_COST_LENGTH = 1;
    public static final int UDH_SEPTET_COST_ONE_SHIFT_TABLE = 4;
    public static final int UDH_SEPTET_COST_TWO_SHIFT_TABLES = 7;
    private static final SparseIntArray sCharsToGsmTables[];
    private static final SparseIntArray sCharsToShiftTables[];
    private static boolean sDisableCountryEncodingCheck = false;
    private static int sEnabledLockingShiftTables[];
    private static int sEnabledSingleShiftTables[];
    private static int sHighestEnabledSingleShiftCode;
    private static final String sLanguageShiftTables[] = {
        "          \f         ^                   {}     \\            [~] |                                    \u20AC                          ", "          \f         ^                   {}     \\            [~] |      \u011E \u0130         \u015E               \347 \u20AC \u011F \u0131         \u015F            ", "         \347\f         ^                   {}     \\            [~] |\301       \315     \323     \332           \341   \u20AC   \355     \363     \372          ", "     \352   \347\f\324\364 \301\341  \u03A6\u0393^\u03A9\u03A0\u03A8\u03A3\u0398     \312        {}     \\            [~] |\300       \315     \323     \332     \303\325    \302   \u20AC   \355     \363     \372     \343\365  \342", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u09E6\u09E7 \u09E8\u09E9\u09EA\u09EB\u09EC\u09ED\u09EE\u09EF\u09DF\u09E0\u09E1\u09E2{}\u09E3\u09F2\u09F3\u09F4\u09F5\\\u09F6\u09F7\u09F8\u09F9\u09FA       [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0AE6\u0AE7\u0AE8\u0AE9\u0AEA\u0AEB\u0AEC\u0AED\u0AEE\u0AEF  {}     \\            [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0966\u0967\u0968\u0969\u096A\u096B\u096C\u096D\u096E\u096F\u0951\u0952{}\u0953\u0954\u0958\u0959\u095A\\\u095B\u095C\u095D\u095E\u095F\u0960\u0961\u0962\u0963\u0970\u0971 [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0CE6\u0CE7\u0CE8\u0CE9\u0CEA\u0CEB\u0CEC\u0CED\u0CEE\u0CEF\u0CDE\u0CF1{}\u0CF2    \\            [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0D66\u0D67\u0D68\u0D69\u0D6A\u0D6B\u0D6C\u0D6D\u0D6E\u0D6F\u0D70\u0D71{}\u0D72\u0D73\u0D74\u0D75\u0D7A\\\u0D7B\u0D7C\u0D7D\u0D7E\u0D7F       [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0B66\u0B67\u0B68\u0B69\u0B6A\u0B6B\u0B6C\u0B6D\u0B6E\u0B6F\u0B5C\u0B5D{}\u0B5F\u0B70\u0B71  \\            [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", 
        "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0A66\u0A67\u0A68\u0A69\u0A6A\u0A6B\u0A6C\u0A6D\u0A6E\u0A6F\u0A59\u0A5A{}\u0A5B\u0A5C\u0A5E\u0A75 \\            [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0964\u0965 \u0BE6\u0BE7\u0BE8\u0BE9\u0BEA\u0BEB\u0BEC\u0BED\u0BEE\u0BEF\u0BF3\u0BF4{}\u0BF5\u0BF6\u0BF7\u0BF8\u0BFA\\            [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*   \u0C66\u0C67\u0C68\u0C69\u0C6A\u0C6B\u0C6C\u0C6D\u0C6E\u0C6F\u0C58\u0C59{}\u0C78\u0C79\u0C7A\u0C7B\u0C7C\\\u0C7D\u0C7E\u0C7F         [~] |ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          ", "@\243$\245\277\"\244%&'\f*+ -/<=>\241^\241_#*\u0600\u0601 \u06F0\u06F1\u06F2\u06F3\u06F4\u06F5\u06F6\u06F7\u06F8\u06F9\u060C\u060D{}\u060E\u060F\u0610\u0611\u0612\\\u0613\u0614\u061B\u061F\u0640\u0652\u0658\u066B\u066C\u0672\u0673\u06CD[~]\u06D4|ABCDEFGHIJKLMNOPQRSTUVWXYZ          \u20AC                          "
    };
    private static final String sLanguageTables[] = {
        "@\243$\245\350\351\371\354\362\307\n\330\370\r\305\345\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039E\uFFFF\306\346\337\311 !\"#\244%&'()*+,-./0123456789:;<=>?\241ABCDEFGHIJKLMNOPQRSTUVWXYZ\304\326\321\334\247\277abcdefghijklmnopqrstuvwxyz\344\366\361\374\340", "@\243$\245\u20AC\351\371\u0131\362\307\n\u011E\u011F\r\305\345\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039E\uFFFF\u015E\u015F\337\311 !\"#\244%&'()*+,-./0123456789:;<=>?\u0130ABCDEFGHIJKLMNOPQRSTUVWXYZ\304\326\321\334\247\347abcdefghijklmnopqrstuvwxyz\344\366\361\374\340", "", "@\243$\245\352\351\372\355\363\347\n\324\364\r\301\341\u0394_\252\307\300\u221E^\\\u20AC\323|\uFFFF\302\342\312\311 !\"#\272%&'()*+,-./0123456789:;<=>?\315ABCDEFGHIJKLMNOPQRSTUVWXYZ\303\325\332\334\247~abcdefghijklmnopqrstuvwxyz\343\365`\374\340", "\u0981\u0982\u0983\u0985\u0986\u0987\u0988\u0989\u098A\u098B\n\u098C \r \u098F\u0990  \u0993\u0994\u0995\u0996\u0997\u0998\u0999\u099A\uFFFF\u099B\u099C\u099D\u099E !\u099F\u09A0\u09A1\u09A2\u09A3\u09A4)(\u09A5\u09A6,\u09A7.\u09A80123456789:; \u09AA\u09AB?\u09AC\u09AD\u09AE\u09AF\u09B0 \u09B2   \u09B6\u09B7\u09B8\u09B9\u09BC\u09BD\u09BE\u09BF\u09C0\u09C1\u09C2\u09C3\u09C4  \u09C7\u09C8  \u09CB\u09CC\u09CD\u09CEabcdefghijklmnopqrstuvwxyz\u09D7\u09DC\u09DD\u09F0\u09F1", "\u0A81\u0A82\u0A83\u0A85\u0A86\u0A87\u0A88\u0A89\u0A8A\u0A8B\n\u0A8C\u0A8D\r \u0A8F\u0A90\u0A91 \u0A93\u0A94\u0A95\u0A96\u0A97\u0A98\u0A99\u0A9A\uFFFF\u0A9B\u0A9C\u0A9D\u0A9E !\u0A9F\u0AA0\u0AA1\u0AA2\u0AA3\u0AA4)(\u0AA5\u0AA6,\u0AA7.\u0AA80123456789:; \u0AAA\u0AAB?\u0AAC\u0AAD\u0AAE\u0AAF\u0AB0 \u0AB2\u0AB3 \u0AB5\u0AB6\u0AB7\u0AB8\u0AB9\u0ABC\u0ABD\u0ABE\u0ABF\u0AC0\u0AC1\u0AC2\u0AC3\u0AC4\u0AC5 \u0AC7\u0AC8\u0AC9 \u0ACB\u0ACC\u0ACD\u0AD0abcdefghijklmnopqrstuvwxyz\u0AE0\u0AE1\u0AE2\u0AE3\u0AF1", "\u0901\u0902\u0903\u0905\u0906\u0907\u0908\u0909\u090A\u090B\n\u090C\u090D\r\u090E\u090F\u0910\u0911\u0912\u0913\u0914\u0915\u0916\u0917\u0918\u0919\u091A\uFFFF\u091B\u091C\u091D\u091E !\u091F\u0920\u0921\u0922\u0923\u0924)(\u0925\u0926,\u0927.\u09280123456789:;\u0929\u092A\u092B?\u092C\u092D\u092E\u092F\u0930\u0931\u0932\u0933\u0934\u0935\u0936\u0937\u0938\u0939\u093C\u093D\u093E\u093F\u0940\u0941\u0942\u0943\u0944\u0945\u0946\u0947\u0948\u0949\u094A\u094B\u094C\u094D\u0950abcdefghijklmnopqrstuvwxyz\u0972\u097B\u097C\u097E\u097F", " \u0C82\u0C83\u0C85\u0C86\u0C87\u0C88\u0C89\u0C8A\u0C8B\n\u0C8C \r\u0C8E\u0C8F\u0C90 \u0C92\u0C93\u0C94\u0C95\u0C96\u0C97\u0C98\u0C99\u0C9A\uFFFF\u0C9B\u0C9C\u0C9D\u0C9E !\u0C9F\u0CA0\u0CA1\u0CA2\u0CA3\u0CA4)(\u0CA5\u0CA6,\u0CA7.\u0CA80123456789:; \u0CAA\u0CAB?\u0CAC\u0CAD\u0CAE\u0CAF\u0CB0\u0CB1\u0CB2\u0CB3 \u0CB5\u0CB6\u0CB7\u0CB8\u0CB9\u0CBC\u0CBD\u0CBE\u0CBF\u0CC0\u0CC1\u0CC2\u0CC3\u0CC4 \u0CC6\u0CC7\u0CC8 \u0CCA\u0CCB\u0CCC\u0CCD\u0CD5abcdefghijklmnopqrstuvwxyz\u0CD6\u0CE0\u0CE1\u0CE2\u0CE3", " \u0D02\u0D03\u0D05\u0D06\u0D07\u0D08\u0D09\u0D0A\u0D0B\n\u0D0C \r\u0D0E\u0D0F\u0D10 \u0D12\u0D13\u0D14\u0D15\u0D16\u0D17\u0D18\u0D19\u0D1A\uFFFF\u0D1B\u0D1C\u0D1D\u0D1E !\u0D1F\u0D20\u0D21\u0D22\u0D23\u0D24)(\u0D25\u0D26,\u0D27.\u0D280123456789:; \u0D2A\u0D2B?\u0D2C\u0D2D\u0D2E\u0D2F\u0D30\u0D31\u0D32\u0D33\u0D34\u0D35\u0D36\u0D37\u0D38\u0D39 \u0D3D\u0D3E\u0D3F\u0D40\u0D41\u0D42\u0D43\u0D44 \u0D46\u0D47\u0D48 \u0D4A\u0D4B\u0D4C\u0D4D\u0D57abcdefghijklmnopqrstuvwxyz\u0D60\u0D61\u0D62\u0D63\u0D79", "\u0B01\u0B02\u0B03\u0B05\u0B06\u0B07\u0B08\u0B09\u0B0A\u0B0B\n\u0B0C \r \u0B0F\u0B10  \u0B13\u0B14\u0B15\u0B16\u0B17\u0B18\u0B19\u0B1A\uFFFF\u0B1B\u0B1C\u0B1D\u0B1E !\u0B1F\u0B20\u0B21\u0B22\u0B23\u0B24)(\u0B25\u0B26,\u0B27.\u0B280123456789:; \u0B2A\u0B2B?\u0B2C\u0B2D\u0B2E\u0B2F\u0B30 \u0B32\u0B33 \u0B35\u0B36\u0B37\u0B38\u0B39\u0B3C\u0B3D\u0B3E\u0B3F\u0B40\u0B41\u0B42\u0B43\u0B44  \u0B47\u0B48  \u0B4B\u0B4C\u0B4D\u0B56abcdefghijklmnopqrstuvwxyz\u0B57\u0B60\u0B61\u0B62\u0B63", 
        "\u0A01\u0A02\u0A03\u0A05\u0A06\u0A07\u0A08\u0A09\u0A0A \n  \r \u0A0F\u0A10  \u0A13\u0A14\u0A15\u0A16\u0A17\u0A18\u0A19\u0A1A\uFFFF\u0A1B\u0A1C\u0A1D\u0A1E !\u0A1F\u0A20\u0A21\u0A22\u0A23\u0A24)(\u0A25\u0A26,\u0A27.\u0A280123456789:; \u0A2A\u0A2B?\u0A2C\u0A2D\u0A2E\u0A2F\u0A30 \u0A32\u0A33 \u0A35\u0A36 \u0A38\u0A39\u0A3C \u0A3E\u0A3F\u0A40\u0A41\u0A42    \u0A47\u0A48  \u0A4B\u0A4C\u0A4D\u0A51abcdefghijklmnopqrstuvwxyz\u0A70\u0A71\u0A72\u0A73\u0A74", " \u0B82\u0B83\u0B85\u0B86\u0B87\u0B88\u0B89\u0B8A \n  \r\u0B8E\u0B8F\u0B90 \u0B92\u0B93\u0B94\u0B95   \u0B99\u0B9A\uFFFF \u0B9C \u0B9E !\u0B9F   \u0BA3\u0BA4)(  , .\u0BA80123456789:;\u0BA9\u0BAA ?  \u0BAE\u0BAF\u0BB0\u0BB1\u0BB2\u0BB3\u0BB4\u0BB5\u0BB6\u0BB7\u0BB8\u0BB9  \u0BBE\u0BBF\u0BC0\u0BC1\u0BC2   \u0BC6\u0BC7\u0BC8 \u0BCA\u0BCB\u0BCC\u0BCD\u0BD0abcdefghijklmnopqrstuvwxyz\u0BD7\u0BF0\u0BF1\u0BF2\u0BF9", "\u0C01\u0C02\u0C03\u0C05\u0C06\u0C07\u0C08\u0C09\u0C0A\u0C0B\n\u0C0C \r\u0C0E\u0C0F\u0C10 \u0C12\u0C13\u0C14\u0C15\u0C16\u0C17\u0C18\u0C19\u0C1A\uFFFF\u0C1B\u0C1C\u0C1D\u0C1E !\u0C1F\u0C20\u0C21\u0C22\u0C23\u0C24)(\u0C25\u0C26,\u0C27.\u0C280123456789:; \u0C2A\u0C2B?\u0C2C\u0C2D\u0C2E\u0C2F\u0C30\u0C31\u0C32\u0C33 \u0C35\u0C36\u0C37\u0C38\u0C39 \u0C3D\u0C3E\u0C3F\u0C40\u0C41\u0C42\u0C43\u0C44 \u0C46\u0C47\u0C48 \u0C4A\u0C4B\u0C4C\u0C4D\u0C55abcdefghijklmnopqrstuvwxyz\u0C56\u0C60\u0C61\u0C62\u0C63", "\u0627\u0622\u0628\u067B\u0680\u067E\u06A6\u062A\u06C2\u067F\n\u0679\u067D\r\u067A\u067C\u062B\u062C\u0681\u0684\u0683\u0685\u0686\u0687\u062D\u062E\u062F\uFFFF\u068C\u0688\u0689\u068A !\u068F\u068D\u0630\u0631\u0691\u0693)(\u0699\u0632,\u0696.\u06980123456789:;\u069A\u0633\u0634?\u0635\u0636\u0637\u0638\u0639\u0641\u0642\u06A9\u06AA\u06AB\u06AF\u06B3\u06B1\u0644\u0645\u0646\u06BA\u06BB\u06BC\u0648\u06C4\u06D5\u06C1\u06BE\u0621\u06CC\u06D0\u06D2\u064D\u0650\u064F\u0657\u0654abcdefghijklmnopqrstuvwxyz\u0655\u0651\u0653\u0656\u0670"
    };

    static 
    {
        enableCountrySpecificEncodings();
        int i = sLanguageTables.length;
        int k = sLanguageShiftTables.length;
        if(i != k)
            Rlog.e("GSM", (new StringBuilder()).append("Error: language tables array length ").append(i).append(" != shift tables array length ").append(k).toString());
        sCharsToGsmTables = new SparseIntArray[i];
        for(int l = 0; l < i; l++)
        {
            String s = sLanguageTables[l];
            int j1 = s.length();
            if(j1 != 0 && j1 != 128)
                Rlog.e("GSM", (new StringBuilder()).append("Error: language tables index ").append(l).append(" length ").append(j1).append(" (expected 128 or 0)").toString());
            SparseIntArray sparseintarray = new SparseIntArray(j1);
            sCharsToGsmTables[l] = sparseintarray;
            for(int k1 = 0; k1 < j1; k1++)
                sparseintarray.put(s.charAt(k1), k1);

        }

        sCharsToShiftTables = new SparseIntArray[i];
        for(int i1 = 0; i1 < k; i1++)
        {
            String s1 = sLanguageShiftTables[i1];
            int j = s1.length();
            if(j != 0 && j != 128)
                Rlog.e("GSM", (new StringBuilder()).append("Error: language shift tables index ").append(i1).append(" length ").append(j).append(" (expected 128 or 0)").toString());
            SparseIntArray sparseintarray1 = new SparseIntArray(j);
            sCharsToShiftTables[i1] = sparseintarray1;
            for(int l1 = 0; l1 < j; l1++)
            {
                char c = s1.charAt(l1);
                if(c != ' ')
                    sparseintarray1.put(c, l1);
            }

        }

    }
}
