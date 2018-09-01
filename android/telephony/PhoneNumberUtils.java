// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.location.Country;
import android.location.CountryDetector;
import android.net.Uri;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.text.*;
import android.text.style.TtsSpan;
import android.util.SparseIntArray;
import com.android.i18n.phonenumbers.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.telephony:
//            PhoneNumberUtilsInjector, Rlog, TelephonyManager, JapanesePhoneNumberFormatter, 
//            SubscriptionManager, CarrierConfigManager

public class PhoneNumberUtils
{
    private static class CountryCallingCodeAndNewIndex
    {

        public final int countryCallingCode;
        public final int newIndex;

        public CountryCallingCodeAndNewIndex(int i, int j)
        {
            countryCallingCode = i;
            newIndex = j;
        }
    }


    public PhoneNumberUtils()
    {
    }

    public static void addTtsSpan(Spannable spannable, int i, int j)
    {
        spannable.setSpan(createTtsSpan(spannable.subSequence(i, j).toString()), i, j, 33);
    }

    private static String appendPwCharBackToOrigDialStr(int i, String s, String s1)
    {
        if(i == 1)
            s = (new StringBuilder(s)).append(s1.charAt(0)).toString();
        else
            s = s.concat(s1.substring(0, i));
        return s;
    }

    private static char bcdToChar(byte byte0)
    {
        if(byte0 < 10)
            return (char)(byte0 + 48);
        switch(byte0)
        {
        default:
            return '\0';

        case 10: // '\n'
            return '*';

        case 11: // '\013'
            return '#';

        case 12: // '\f'
            return ',';

        case 13: // '\r'
            return 'N';

        case 14: // '\016'
            return ';';
        }
    }

    public static String calledPartyBCDFragmentToString(byte abyte0[], int i, int j)
    {
        StringBuilder stringbuilder = new StringBuilder(j * 2);
        internalCalledPartyBCDFragmentToString(stringbuilder, abyte0, i, j);
        return stringbuilder.toString();
    }

    public static String calledPartyBCDToString(byte abyte0[], int i, int j)
    {
        boolean flag = false;
        Object obj = new StringBuilder(j * 2 + 1);
        if(j < 2)
            return "";
        if((abyte0[i] & 0xf0) == 144)
            flag = true;
        internalCalledPartyBCDFragmentToString(((StringBuilder) (obj)), abyte0, i + 1, j - 1);
        if(flag && ((StringBuilder) (obj)).length() == 0)
            return "";
        abyte0 = ((byte []) (obj));
        if(flag)
        {
            String s = ((StringBuilder) (obj)).toString();
            obj = Pattern.compile("(^[#*])(.*)([#*])(.*)(#)$").matcher(s);
            if(((Matcher) (obj)).matches())
            {
                if("".equals(((Matcher) (obj)).group(2)))
                {
                    abyte0 = new StringBuilder();
                    abyte0.append(((Matcher) (obj)).group(1));
                    abyte0.append(((Matcher) (obj)).group(3));
                    abyte0.append(((Matcher) (obj)).group(4));
                    abyte0.append(((Matcher) (obj)).group(5));
                    abyte0.append("+");
                } else
                {
                    abyte0 = new StringBuilder();
                    abyte0.append(((Matcher) (obj)).group(1));
                    abyte0.append(((Matcher) (obj)).group(2));
                    abyte0.append(((Matcher) (obj)).group(3));
                    abyte0.append("+");
                    abyte0.append(((Matcher) (obj)).group(4));
                    abyte0.append(((Matcher) (obj)).group(5));
                }
            } else
            {
                Matcher matcher = Pattern.compile("(^[#*])(.*)([#*])(.*)").matcher(s);
                if(matcher.matches())
                {
                    abyte0 = new StringBuilder();
                    abyte0.append(matcher.group(1));
                    abyte0.append(matcher.group(2));
                    abyte0.append(matcher.group(3));
                    abyte0.append("+");
                    abyte0.append(matcher.group(4));
                } else
                {
                    abyte0 = new StringBuilder();
                    abyte0.append('+');
                    abyte0.append(s);
                }
            }
        }
        return abyte0.toString();
    }

    public static String cdmaCheckAndProcessPlusCode(String s)
    {
        if(!TextUtils.isEmpty(s) && isReallyDialable(s.charAt(0)) && isNonSeparator(s))
        {
            String s1 = PhoneNumberUtilsInjector.getCdmaTelephonyProperty("gsm.operator.iso-country", "");
            String s2 = PhoneNumberUtilsInjector.getCdmaTelephonyProperty("gsm.sim.operator.iso-country", "");
            if(!TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2) ^ true)
                return cdmaCheckAndProcessPlusCodeByNumberFormat(s, getFormatTypeFromCountryCode(s1), getFormatTypeFromCountryCode(s2));
        }
        return s;
    }

    public static String cdmaCheckAndProcessPlusCodeByNumberFormat(String s, int i, int j)
    {
label0:
        {
label1:
            {
                String s1 = s;
                boolean flag;
                String s3;
                String s4;
                if(i == j && i == 1)
                    flag = true;
                else
                    flag = false;
                s3 = s1;
                if(s == null)
                    break label1;
                s3 = s1;
                if(s.lastIndexOf("+") == -1)
                    break label1;
                s4 = s;
                s3 = null;
                do
                {
                    String s2;
                    String s5;
                    String s6;
                    String s7;
                    if(flag)
                        s2 = extractNetworkPortion(s4);
                    else
                        s2 = extractNetworkPortionAlt(s4);
                    s2 = processPlusCode(s2, flag);
                    if(TextUtils.isEmpty(s2))
                        break label0;
                    if(s3 == null)
                        s3 = s2;
                    else
                        s3 = s3.concat(s2);
                    s5 = extractPostDialPortion(s4);
                    s6 = s5;
                    s2 = s3;
                    s7 = s4;
                    if(!TextUtils.isEmpty(s5))
                    {
                        i = findDialableIndexFromPostDialStr(s5);
                        if(i >= 1)
                        {
                            s2 = appendPwCharBackToOrigDialStr(i, s3, s5);
                            s7 = s5.substring(i);
                            s6 = s5;
                        } else
                        {
                            s2 = s5;
                            if(i < 0)
                                s2 = "";
                            Rlog.e("wrong postDialStr=", s2);
                            s6 = s2;
                            s2 = s3;
                            s7 = s4;
                        }
                    }
                    s3 = s2;
                    if(TextUtils.isEmpty(s6))
                        break label1;
                    s3 = s2;
                    s4 = s7;
                } while(TextUtils.isEmpty(s7) ^ true);
                s3 = s2;
            }
            return s3;
        }
        Rlog.e("checkAndProcessPlusCode: null newDialStr", s2);
        return s;
    }

    public static String cdmaCheckAndProcessPlusCodeForSms(String s)
    {
        if(!TextUtils.isEmpty(s) && isReallyDialable(s.charAt(0)) && isNonSeparator(s))
        {
            String s1 = TelephonyManager.getDefault().getSimCountryIso();
            if(!TextUtils.isEmpty(s1))
            {
                int i = getFormatTypeFromCountryCode(s1);
                return cdmaCheckAndProcessPlusCodeByNumberFormat(s, i, i);
            }
        }
        return s;
    }

    private static int charToBCD(char c)
    {
        if(c >= '0' && c <= '9')
            return c - 48;
        if(c == '*')
            return 10;
        if(c == '#')
            return 11;
        if(c == ',')
            return 12;
        if(c == 'N')
            return 13;
        if(c == ';')
            return 14;
        else
            throw new RuntimeException((new StringBuilder()).append("invalid char for BCD ").append(c).toString());
    }

    private static boolean checkPrefixIsIgnorable(String s, int i, int j)
    {
        boolean flag = false;
_L2:
        if(j < i)
            break MISSING_BLOCK_LABEL_45;
        if(tryGetISODigit(s.charAt(j)) < 0)
            break; /* Loop/switch isn't completed */
        if(flag)
            return false;
        flag = true;
_L4:
        j--;
        if(true) goto _L2; else goto _L1
_L1:
        if(!isDialable(s.charAt(j))) goto _L4; else goto _L3
_L3:
        return false;
        return true;
    }

    public static boolean compare(Context context, String s, String s1)
    {
        return compare(s, s1, context.getResources().getBoolean(0x11200c8));
    }

    public static boolean compare(String s, String s1)
    {
        return compare(s, s1, false);
    }

    public static boolean compare(String s, String s1, boolean flag)
    {
        if(flag)
            flag = compareStrictly(s, s1);
        else
            flag = compareLoosely(s, s1);
        return flag;
    }

    public static boolean compareLoosely(String s, String s1)
    {
        boolean flag = true;
        int i = 0;
        int j = 0;
        if(s == null || s1 == null)
        {
            if(s != s1)
                flag = false;
            return flag;
        }
        if(s.length() == 0 || s1.length() == 0)
            return false;
        int k = indexOfLastNetworkChar(s);
        int l = indexOfLastNetworkChar(s1);
        int i1 = 0;
        int j1;
        int k1;
label0:
        do
        {
label1:
            {
                j1 = k;
                k1 = l;
                int l1 = i;
                int i2 = j;
                if(k >= 0)
                {
                    j1 = k;
                    k1 = l;
                    l1 = i;
                    i2 = j;
                    if(l >= 0)
                    {
                        boolean flag1 = false;
                        char c = s.charAt(k);
                        j1 = k;
                        l1 = i;
                        if(!isDialable(c))
                        {
                            j1 = k - 1;
                            flag1 = true;
                            l1 = i + 1;
                        }
                        char c1 = s1.charAt(l);
                        k1 = l;
                        i2 = j;
                        if(!isDialable(c1))
                        {
                            k1 = l - 1;
                            flag1 = true;
                            i2 = j + 1;
                        }
                        k = j1;
                        l = k1;
                        i = l1;
                        j = i2;
                        if(flag1)
                            continue;
                        if(c1 == c || c == 'N' || c1 == 'N')
                            break label1;
                    }
                }
                if(i1 < MIN_MATCH)
                {
                    l = s.length() - l1;
                    return l == s1.length() - i2 && l == i1;
                }
                break label0;
            }
            k = j1 - 1;
            l = k1 - 1;
            i1++;
            i = l1;
            j = i2;
        } while(true);
        if(i1 >= MIN_MATCH && (j1 < 0 || k1 < 0))
            return true;
        if(matchIntlPrefix(s, j1 + 1) && matchIntlPrefix(s1, k1 + 1))
            return true;
        if(matchTrunkPrefix(s, j1 + 1) && matchIntlPrefixAndCC(s1, k1 + 1))
            return true;
        return matchTrunkPrefix(s1, k1 + 1) && matchIntlPrefixAndCC(s, j1 + 1);
    }

    public static boolean compareStrictly(String s, String s1)
    {
        return compareStrictly(s, s1, true);
    }

    public static boolean compareStrictly(String s, String s1, boolean flag)
    {
        int i;
        int j;
        int k;
        CountryCallingCodeAndNewIndex countrycallingcodeandnewindex;
        CountryCallingCodeAndNewIndex countrycallingcodeandnewindex1;
        boolean flag2;
        int i1;
        int j1;
        int k1;
        boolean flag3;
        if(s == null || s1 == null)
        {
            if(s == s1)
                flag = true;
            else
                flag = false;
            return flag;
        }
        if(s.length() == 0 && s1.length() == 0)
            return false;
        i = 0;
        j = 0;
        k = 0;
        countrycallingcodeandnewindex = tryGetCountryCallingCodeAndNewIndex(s, flag);
        countrycallingcodeandnewindex1 = tryGetCountryCallingCodeAndNewIndex(s1, flag);
        flag2 = false;
        i1 = 1;
        j1 = 0;
        k1 = 0;
        flag3 = false;
        if(countrycallingcodeandnewindex == null || countrycallingcodeandnewindex1 == null) goto _L2; else goto _L1
_L1:
        int l1;
        boolean flag4;
        if(countrycallingcodeandnewindex.countryCallingCode != countrycallingcodeandnewindex1.countryCallingCode)
            return false;
        l1 = 0;
        flag4 = true;
        j = countrycallingcodeandnewindex.newIndex;
        k = countrycallingcodeandnewindex1.newIndex;
_L14:
        j1 = s.length() - 1;
        i = s1.length() - 1;
_L8:
        if(j1 < j || i < k)
            break; /* Loop/switch isn't completed */
        flag2 = false;
        char c = s.charAt(j1);
        char c3 = s1.charAt(i);
        i1 = j1;
        if(isSeparator(c))
        {
            i1 = j1 - 1;
            flag2 = true;
        }
        int j2 = i;
        if(isSeparator(c3))
        {
            j2 = i - 1;
            flag2 = true;
        }
        j1 = i1;
        i = j2;
        if(flag2)
            continue; /* Loop/switch isn't completed */
        if(c != c3)
            return false;
        j1 = i1 - 1;
        i = j2 - 1;
        continue; /* Loop/switch isn't completed */
_L2:
        if(countrycallingcodeandnewindex == null && countrycallingcodeandnewindex1 == null)
        {
            l1 = 0;
            flag4 = flag2;
            continue; /* Loop/switch isn't completed */
        }
        if(countrycallingcodeandnewindex == null) goto _L4; else goto _L3
_L3:
        i = countrycallingcodeandnewindex.newIndex;
_L6:
        if(countrycallingcodeandnewindex1 == null)
            break; /* Loop/switch isn't completed */
        k = countrycallingcodeandnewindex1.newIndex;
        flag4 = flag2;
        j = i;
        l1 = i1;
        k1 = j1;
        continue; /* Loop/switch isn't completed */
_L4:
        j = tryGetTrunkPrefixOmittedIndex(s, 0);
        if(j >= 0)
        {
            i = j;
            j1 = 1;
        }
        if(true) goto _L6; else goto _L5
_L5:
        j2 = tryGetTrunkPrefixOmittedIndex(s1, 0);
        flag4 = flag2;
        j = i;
        l1 = i1;
        k1 = j1;
        if(j2 >= 0)
        {
            k = j2;
            flag3 = true;
            flag4 = flag2;
            j = i;
            l1 = i1;
            k1 = j1;
        }
        continue; /* Loop/switch isn't completed */
        if(true) goto _L8; else goto _L7
_L7:
        if(l1 == 0) goto _L10; else goto _L9
_L9:
        while(k1 != 0 && j <= j1 || checkPrefixIsIgnorable(s, j, j1) ^ true) 
            if(flag)
                return compare(s, s1, false);
            else
                return false;
        while(flag3 && k <= i || checkPrefixIsIgnorable(s1, k, i) ^ true) 
            if(flag)
                return compare(s, s1, false);
            else
                return false;
_L12:
        return true;
_L10:
label0:
        {
            int i2 = flag4 ^ true;
            int l = j1;
            do
            {
                j1 = i;
                flag4 = i2;
                if(l < j)
                    break label0;
                char c1 = s.charAt(l);
                j1 = i2;
                if(isDialable(c1))
                {
                    if(i2 == 0 || tryGetISODigit(c1) != 1)
                        break;
                    j1 = 0;
                }
                l--;
                i2 = j1;
            } while(true);
            return false;
        }
        while(j1 >= k) 
        {
label1:
            {
                char c2 = s1.charAt(j1);
                boolean flag1 = flag4;
                if(isDialable(c2))
                {
                    if(!flag4 || tryGetISODigit(c2) != 1)
                        break label1;
                    flag1 = false;
                }
                j1--;
                flag4 = flag1;
            }
        }
        if(true) goto _L12; else goto _L11
_L11:
        return false;
        if(true) goto _L14; else goto _L13
_L13:
    }

    public static String convertAndStrip(String s)
    {
        return stripSeparators(convertKeypadLettersToDigits(s));
    }

    public static String convertKeypadLettersToDigits(String s)
    {
        if(s == null)
            return s;
        int i = s.length();
        if(i == 0)
            return s;
        s = s.toCharArray();
        for(int j = 0; j < i; j++)
        {
            char c = s[j];
            s[j] = (char)KEYPAD_MAP.get(c, c);
        }

        return new String(s);
    }

    public static String convertPreDial(String s)
    {
        if(s == null)
            return null;
        int i = s.length();
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            char c1;
            if(isPause(c))
            {
                byte byte0 = 44;
                c1 = byte0;
            } else
            {
                c1 = c;
                if(isToneWait(c))
                {
                    byte byte1 = 59;
                    c1 = byte1;
                }
            }
            stringbuilder.append(c1);
            j++;
        }
        return stringbuilder.toString();
    }

    public static Uri convertSipUriToTelUri(Uri uri)
    {
        if(!"sip".equals(uri.getScheme()))
            return uri;
        String as[] = uri.getSchemeSpecificPart().split("[@;:]");
        if(as.length == 0)
            return uri;
        else
            return Uri.fromParts("tel", as[0], null);
    }

    public static String convertToEmergencyNumber(Context context, String s)
    {
        if(context == null || TextUtils.isEmpty(s))
            return s;
        String s1 = normalizeNumber(s);
        if(isEmergencyNumber(s1))
            return s;
        if(sConvertToEmergencyMap == null)
            sConvertToEmergencyMap = context.getResources().getStringArray(0x107001c);
        if(sConvertToEmergencyMap == null || sConvertToEmergencyMap.length == 0)
            return s;
        String as[] = sConvertToEmergencyMap;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                break;
            String s2 = as[j];
            context = null;
            Object obj = null;
            Object obj1 = null;
            if(!TextUtils.isEmpty(s2))
                context = s2.split(":");
            CharSequence charsequence = obj1;
            String as1[] = obj;
            if(context != null)
            {
                charsequence = obj1;
                as1 = obj;
                if(context.length == 2)
                {
                    Object obj2 = context[1];
                    charsequence = obj2;
                    as1 = obj;
                    if(!TextUtils.isEmpty(context[0]))
                    {
                        as1 = context[0].split(",");
                        charsequence = obj2;
                    }
                }
            }
            if(!TextUtils.isEmpty(charsequence) && as1 != null && as1.length != 0)
            {
                int k = as1.length;
                int l = 0;
                while(l < k) 
                {
                    context = as1[l];
                    if(!TextUtils.isEmpty(context) && context.equals(s1))
                        return charsequence;
                    l++;
                }
            }
            j++;
        } while(true);
        return s;
    }

    public static TtsSpan createTtsSpan(String s)
    {
        Object obj;
        com.android.i18n.phonenumbers.Phonenumber.PhoneNumber phonenumber;
        if(s == null)
            return null;
        obj = PhoneNumberUtil.getInstance();
        phonenumber = null;
        obj = ((PhoneNumberUtil) (obj)).parse(s, null);
        phonenumber = ((com.android.i18n.phonenumbers.Phonenumber.PhoneNumber) (obj));
_L1:
        Object obj1 = new android.text.style.TtsSpan.TelephoneBuilder();
        if(phonenumber == null)
        {
            ((android.text.style.TtsSpan.TelephoneBuilder) (obj1)).setNumberParts(splitAtNonNumerics(s));
        } else
        {
            if(phonenumber.hasCountryCode())
                ((android.text.style.TtsSpan.TelephoneBuilder) (obj1)).setCountryCode(Integer.toString(phonenumber.getCountryCode()));
            ((android.text.style.TtsSpan.TelephoneBuilder) (obj1)).setNumberParts(Long.toString(phonenumber.getNationalNumber()));
        }
        return ((android.text.style.TtsSpan.TelephoneBuilder) (obj1)).build();
        obj1;
          goto _L1
    }

    public static CharSequence createTtsSpannable(CharSequence charsequence)
    {
        if(charsequence == null)
        {
            return null;
        } else
        {
            charsequence = android.text.Spannable.Factory.getInstance().newSpannable(charsequence);
            addTtsSpan(charsequence, 0, charsequence.length());
            return charsequence;
        }
    }

    public static String extractNetworkPortion(String s)
    {
        int i;
        StringBuilder stringbuilder;
        int j;
        if(s == null)
            return null;
        i = s.length();
        stringbuilder = new StringBuilder(i);
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        char c;
        c = s.charAt(j);
        int k = Character.digit(c, 10);
        if(k != -1)
            stringbuilder.append(k);
        else
        if(c == '+')
        {
            String s1 = stringbuilder.toString();
            if(s1.length() == 0 || s1.equals("*31#") || s1.equals("#31#"))
                stringbuilder.append(c);
        } else
        {
            if(!isDialable(c))
                continue; /* Loop/switch isn't completed */
            stringbuilder.append(c);
        }
_L4:
        j++;
          goto _L3
        if(!isStartsPostDial(c)) goto _L4; else goto _L2
_L2:
        return stringbuilder.toString();
    }

    public static String extractNetworkPortionAlt(String s)
    {
        int i;
        StringBuilder stringbuilder;
        boolean flag;
        int j;
        if(s == null)
            return null;
        i = s.length();
        stringbuilder = new StringBuilder(i);
        flag = false;
        j = 0;
_L7:
        if(j >= i) goto _L2; else goto _L1
_L1:
        char c;
        boolean flag1;
        c = s.charAt(j);
        flag1 = flag;
        if(c != '+') goto _L4; else goto _L3
_L3:
        if(!flag) goto _L6; else goto _L5
_L5:
        j++;
          goto _L7
_L6:
        flag1 = true;
_L4:
label0:
        {
            if(!isDialable(c))
                break label0;
            stringbuilder.append(c);
            flag = flag1;
        }
          goto _L5
        flag = flag1;
        if(!isStartsPostDial(c)) goto _L5; else goto _L2
_L2:
        return stringbuilder.toString();
    }

    public static String extractPostDialPortion(String s)
    {
        if(s == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        int i = indexOfLastNetworkChar(s) + 1;
        for(int j = s.length(); i < j; i++)
        {
            char c = s.charAt(i);
            if(isNonSeparator(c))
                stringbuilder.append(c);
        }

        return stringbuilder.toString();
    }

    private static int findDialableIndexFromPostDialStr(String s)
    {
        for(int i = 0; i < s.length(); i++)
            if(isReallyDialable(s.charAt(i)))
                return i;

        return -1;
    }

    public static void formatJapaneseNumber(Editable editable)
    {
        JapanesePhoneNumberFormatter.format(editable);
    }

    public static void formatNanpNumber(Editable editable)
    {
        int i;
        CharSequence charsequence;
        int k;
        int ai[];
        int l;
        int j1;
        int k1;
        i = editable.length();
        if(i > "+1-nnn-nnn-nnnn".length())
            return;
        if(i <= 5)
            return;
        charsequence = editable.subSequence(0, i);
        removeDashes(editable);
        k = editable.length();
        ai = new int[3];
        l = 1;
        j1 = 0;
        k1 = 0;
        i = 0;
_L11:
        if(k1 >= k)
            break MISSING_BLOCK_LABEL_281;
        editable.charAt(k1);
        JVM INSTR tableswitch 43 57: default 148
    //                   43 270
    //                   44 148
    //                   45 264
    //                   46 148
    //                   47 148
    //                   48 179
    //                   49 159
    //                   50 179
    //                   51 179
    //                   52 179
    //                   53 179
    //                   54 179
    //                   55 179
    //                   56 179
    //                   57 179;
           goto _L1 _L2 _L1 _L3 _L1 _L1 _L4 _L5 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4
_L1:
        editable.replace(0, k, charsequence);
        return;
_L5:
        if(j1 != 0 && l != 2) goto _L4; else goto _L6
_L6:
        l = 3;
_L8:
        k1++;
        continue; /* Loop/switch isn't completed */
_L4:
        if(l == 2)
        {
            editable.replace(0, k, charsequence);
            return;
        }
        if(l == 3)
        {
            l = i + 1;
            ai[i] = k1;
            i = l;
        } else
        if(l != 4 && (j1 == 3 || j1 == 6))
        {
            l = i + 1;
            ai[i] = k1;
            i = l;
        }
        l = 1;
        j1++;
        continue; /* Loop/switch isn't completed */
_L3:
        l = 4;
        continue; /* Loop/switch isn't completed */
_L2:
        if(k1 != 0)
            break; /* Loop/switch isn't completed */
        l = 2;
        if(true) goto _L8; else goto _L7
_L7:
        if(true) goto _L1; else goto _L9
_L9:
        if(j1 == 7)
            i--;
        for(int i1 = 0; i1 < i; i1++)
        {
            int l1 = ai[i1];
            editable.replace(l1 + i1, l1 + i1, "-");
        }

        for(int j = editable.length(); j > 0 && editable.charAt(j - 1) == '-'; j--)
            editable.delete(j - 1, j);

        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    public static String formatNumber(String s)
    {
        s = new SpannableStringBuilder(s);
        formatNumber(((Editable) (s)), getFormatTypeForLocale(Locale.getDefault()));
        return s.toString();
    }

    public static String formatNumber(String s, int i)
    {
        s = new SpannableStringBuilder(s);
        formatNumber(((Editable) (s)), i);
        return s.toString();
    }

    public static String formatNumber(String s, String s1)
    {
        PhoneNumberUtil phonenumberutil;
        Object obj;
        if(s.startsWith("#") || s.startsWith("*"))
            return s;
        phonenumberutil = PhoneNumberUtil.getInstance();
        obj = null;
        s = phonenumberutil.parseAndKeepRawInput(s, s1);
        if(!"KR".equalsIgnoreCase(s1) || s.getCountryCode() != phonenumberutil.getCountryCodeForRegion("KR") || s.getCountryCodeSource() != com.android.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN) goto _L2; else goto _L1
_L1:
        s = phonenumberutil.format(s, com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
_L4:
        return s;
_L2:
        if("JP".equalsIgnoreCase(s1) && s.getCountryCode() == phonenumberutil.getCountryCodeForRegion("JP") && s.getCountryCodeSource() == com.android.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN)
        {
            s = phonenumberutil.format(s, com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            s = phonenumberutil.formatInOriginalFormat(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("PhoneNumberUtils", "formatNumber exception: ", s);
            s = obj;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String formatNumber(String s, String s1, String s2)
    {
        Object obj;
        String s3;
        int i = s.length();
        for(int j = 0; j < i; j++)
            if(!isDialable(s.charAt(j)))
                return s;

        obj = PhoneNumberUtil.getInstance();
        s3 = s2;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_123;
        s3 = s2;
        if(s1.length() < 2)
            break MISSING_BLOCK_LABEL_123;
        s3 = s2;
        if(s1.charAt(0) != '+')
            break MISSING_BLOCK_LABEL_123;
        obj = ((PhoneNumberUtil) (obj)).getRegionCodeForNumber(((PhoneNumberUtil) (obj)).parse(s1, "ZZ"));
        s3 = s2;
        int k;
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            break MISSING_BLOCK_LABEL_123;
        k = normalizeNumber(s).indexOf(s1.substring(1));
        s3 = s2;
        if(k <= 0)
            s3 = ((String) (obj));
_L2:
        s1 = formatNumber(s, s3);
        if(s1 != null)
            s = s1;
        return s;
        s1;
        s3 = s2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void formatNumber(Editable editable, int i)
    {
        int j = i;
        i = j;
        if(editable.length() > 2)
        {
            i = j;
            if(editable.charAt(0) == '+')
                if(editable.charAt(1) == '1')
                    i = 1;
                else
                if(editable.length() >= 3 && editable.charAt(1) == '8' && editable.charAt(2) == '1')
                    i = 2;
                else
                    i = 0;
        }
        switch(i)
        {
        default:
            return;

        case 1: // '\001'
            formatNanpNumber(editable);
            return;

        case 2: // '\002'
            formatJapaneseNumber(editable);
            return;

        case 0: // '\0'
            removeDashes(editable);
            return;
        }
    }

    private static String formatNumberInternal(String s, String s1, com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat phonenumberformat)
    {
        PhoneNumberUtil phonenumberutil = PhoneNumberUtil.getInstance();
        s = phonenumberutil.parse(s, s1);
        if(!phonenumberutil.isValidNumber(s))
            break MISSING_BLOCK_LABEL_29;
        s = phonenumberutil.format(s, phonenumberformat);
        return s;
        s;
        return null;
    }

    public static String formatNumberToE164(String s, String s1)
    {
        return formatNumberInternal(s, s1, com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static String formatNumberToRFC3966(String s, String s1)
    {
        return formatNumberInternal(s, s1, com.android.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.RFC3966);
    }

    private static String getCountryIso(Context context)
    {
        Rlog.w("PhoneNumberUtils", (new StringBuilder()).append("getCountryIso ").append(sCountryDetector).toString());
        if(sCountryDetector == null)
        {
            context = (CountryDetector)context.getSystemService("country_detector");
            if(context != null)
                sCountryDetector = context.detectCountry();
        }
        if(sCountryDetector == null)
            return null;
        else
            return sCountryDetector.getCountryIso();
    }

    private static String getCurrentIdp(boolean flag)
    {
        String s;
        if(flag)
        {
            s = "011";
        } else
        {
            if(flag)
                s = "011";
            else
                s = "+";
            s = PhoneNumberUtilsInjector.getCdmaTelephonyProperty("gsm.operator.idpstring", s);
        }
        return s;
    }

    private static int getDefaultVoiceSubId()
    {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    public static int getFormatTypeForLocale(Locale locale)
    {
        return getFormatTypeFromCountryCode(locale.getCountry());
    }

    private static int getFormatTypeFromCountryCode(String s)
    {
        int i = NANP_COUNTRIES.length;
        for(int j = 0; j < i; j++)
            if(NANP_COUNTRIES[j].compareToIgnoreCase(s) == 0)
                return 1;

        return "jp".compareToIgnoreCase(s) != 0 ? 0 : 2;
    }

    public static String getNumberFromIntent(Intent intent, Context context)
    {
        Object obj;
        Object obj1;
        Object obj2;
        String s1;
        obj = null;
        obj1 = null;
        obj2 = intent.getData();
        if(obj2 == null)
            return null;
        String s = ((Uri) (obj2)).getScheme();
        if(s.equals("tel") || s.equals("sip"))
            return ((Uri) (obj2)).getSchemeSpecificPart();
        if(context == null)
            return null;
        intent.resolveType(context);
        s1 = null;
        intent = ((Uri) (obj2)).getAuthority();
        if(!"contacts".equals(intent)) goto _L2; else goto _L1
_L1:
        s1 = "number";
_L6:
        Object obj3;
        obj3 = null;
        intent = null;
        obj2 = context.getContentResolver().query(((Uri) (obj2)), new String[] {
            s1
        }, null, null, null);
        context = obj1;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_166;
        context = obj1;
        intent = ((Intent) (obj2));
        obj3 = obj2;
        if(!((Cursor) (obj2)).moveToFirst())
            break MISSING_BLOCK_LABEL_166;
        intent = ((Intent) (obj2));
        obj3 = obj2;
        context = ((Cursor) (obj2)).getString(((Cursor) (obj2)).getColumnIndex(s1));
        obj3 = context;
        if(obj2 != null)
        {
            ((Cursor) (obj2)).close();
            obj3 = context;
        }
_L4:
        return ((String) (obj3));
_L2:
        if("com.android.contacts".equals(intent))
            s1 = "data1";
        continue; /* Loop/switch isn't completed */
        context;
        obj3 = intent;
        Rlog.e("PhoneNumberUtils", "Error getting phone number.", context);
        obj3 = obj;
        if(intent != null)
        {
            intent.close();
            obj3 = obj;
        }
        if(true) goto _L4; else goto _L3
_L3:
        intent;
        if(obj3 != null)
            ((Cursor) (obj3)).close();
        throw intent;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static String getStrippedReversed(String s)
    {
        s = extractNetworkPortionAlt(s);
        if(s == null)
            return null;
        else
            return internalGetStrippedReversed(s, s.length());
    }

    public static String getUsernameFromUriNumber(String s)
    {
        int i = s.indexOf('@');
        int j = i;
        if(i < 0)
            j = s.indexOf("%40");
        i = j;
        if(j < 0)
        {
            Rlog.w("PhoneNumberUtils", (new StringBuilder()).append("getUsernameFromUriNumber: no delimiter found in SIP addr '").append(s).append("'").toString());
            i = s.length();
        }
        return s.substring(0, i);
    }

    private static int indexOfLastNetworkChar(String s)
    {
        int i = s.length();
        int j = minPositive(s.indexOf(','), s.indexOf(';'));
        if(j < 0)
            return i - 1;
        else
            return j - 1;
    }

    private static void internalCalledPartyBCDFragmentToString(StringBuilder stringbuilder, byte abyte0[], int i, int j)
    {
        int k = i;
        do
        {
            byte byte0;
label0:
            {
                if(k < j + i)
                {
                    char c = bcdToChar((byte)(abyte0[k] & 0xf));
                    if(c == 0)
                        return;
                    stringbuilder.append(c);
                    byte0 = (byte)(abyte0[k] >> 4 & 0xf);
                    if(byte0 != 15 || k + 1 != j + i)
                        break label0;
                }
                return;
            }
            char c1 = bcdToChar(byte0);
            if(c1 == 0)
                return;
            stringbuilder.append(c1);
            k++;
        } while(true);
    }

    private static String internalGetStrippedReversed(String s, int i)
    {
        if(s == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = s.length();
        for(int k = j - 1; k >= 0 && j - k <= i; k--)
            stringbuilder.append(s.charAt(k));

        return stringbuilder.toString();
    }

    public static final boolean is12Key(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c != '*')
        {
            flag1 = flag;
            if(c != '#')
                flag1 = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isCountryCallingCode(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i > 0)
        {
            flag1 = flag;
            if(i < CCC_LENGTH)
                flag1 = COUNTRY_CALLING_CALL[i];
        }
        return flag1;
    }

    public static final boolean isDialable(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c != '*')
        {
            flag1 = flag;
            if(c != '#')
            {
                flag1 = flag;
                if(c != '+')
                {
                    flag1 = flag;
                    if(c != 'N')
                        flag1 = false;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isDialable(String s)
    {
        int i = 0;
        for(int j = s.length(); i < j; i++)
            if(!isDialable(s.charAt(i)))
                return false;

        return true;
    }

    public static boolean isEmergencyNumber(int i, String s)
    {
        return isEmergencyNumberInternal(i, s, true);
    }

    public static boolean isEmergencyNumber(int i, String s, String s1)
    {
        return isEmergencyNumberInternal(i, s, s1, true);
    }

    public static boolean isEmergencyNumber(String s)
    {
        return isEmergencyNumber(getDefaultVoiceSubId(), s);
    }

    public static boolean isEmergencyNumber(String s, String s1)
    {
        return isEmergencyNumber(getDefaultVoiceSubId(), s, s1);
    }

    private static boolean isEmergencyNumberInternal(int i, String s, String s1, boolean flag)
    {
        if(s == null)
            return false;
        if(isUriNumber(s))
            return false;
        String s2 = extractNetworkPortionAlt(s);
        int j = SubscriptionManager.getSlotIndex(i);
        String s3;
        if(j <= 0)
            s = "ril.ecclist";
        else
            s = (new StringBuilder()).append("ril.ecclist").append(j).toString();
        s3 = SystemProperties.get(s, "");
        Rlog.d("PhoneNumberUtils", (new StringBuilder()).append("slotId:").append(j).append(" subId:").append(i).append(" country:").append(s1).append(" emergencyNumbers: ").append(s3).toString());
        s = s3;
        if(TextUtils.isEmpty(s3))
            s = SystemProperties.get("ro.ril.ecclist");
        if(!TextUtils.isEmpty(s))
        {
            s = s.split(",");
            j = s.length;
            for(i = 0; i < j; i++)
            {
                String s4 = s[i];
                if(flag || "BR".equalsIgnoreCase(s1))
                {
                    if(s2.equals(s4))
                        return true;
                    continue;
                }
                if(s2.startsWith(s4))
                    return true;
            }

            return false;
        }
        Rlog.d("PhoneNumberUtils", "System property doesn't provide any emergency numbers. Use embedded logic for determining ones.");
        String as[];
        if(j < 0)
            s = "112,911,000,08,110,118,119,999";
        else
            s = "112,911";
        as = s.split(",");
        j = as.length;
        for(i = 0; i < j; i++)
        {
            s = as[i];
            if(flag)
            {
                if(s2.equals(s))
                    return true;
                continue;
            }
            if(s2.startsWith(s))
                return true;
        }

        if(s1 != null)
        {
            s = ShortNumberInfo.getInstance();
            if(flag)
                return s.isEmergencyNumber(s2, s1);
            else
                return s.connectsToEmergencyNumber(s2, s1);
        } else
        {
            return false;
        }
    }

    private static boolean isEmergencyNumberInternal(int i, String s, boolean flag)
    {
        return isEmergencyNumberInternal(i, s, null, flag);
    }

    private static boolean isEmergencyNumberInternal(String s, String s1, boolean flag)
    {
        return isEmergencyNumberInternal(getDefaultVoiceSubId(), s, s1, flag);
    }

    private static boolean isEmergencyNumberInternal(String s, boolean flag)
    {
        return isEmergencyNumberInternal(getDefaultVoiceSubId(), s, flag);
    }

    public static boolean isGlobalPhoneNumber(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        else
            return GLOBAL_PHONE_NUMBER_PATTERN.matcher(s).matches();
    }

    public static boolean isISODigit(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isInternationalNumber(String s, String s1)
    {
        boolean flag = false;
        if(TextUtils.isEmpty(s))
            return false;
        if(s.startsWith("#") || s.startsWith("*"))
            return false;
        PhoneNumberUtil phonenumberutil = PhoneNumberUtil.getInstance();
        int i;
        int j;
        try
        {
            i = phonenumberutil.parseAndKeepRawInput(s, s1).getCountryCode();
            j = phonenumberutil.getCountryCodeForRegion(s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        if(i != j)
            flag = true;
        return flag;
    }

    public static boolean isLocalEmergencyNumber(Context context, int i, String s)
    {
        return isLocalEmergencyNumberInternal(i, s, context, true);
    }

    public static boolean isLocalEmergencyNumber(Context context, String s)
    {
        return isLocalEmergencyNumber(context, getDefaultVoiceSubId(), s);
    }

    private static boolean isLocalEmergencyNumberInternal(int i, String s, Context context, boolean flag)
    {
        String s1 = getCountryIso(context);
        Rlog.w("PhoneNumberUtils", (new StringBuilder()).append("isLocalEmergencyNumberInternal").append(s1).toString());
        String s2 = s1;
        if(s1 == null)
        {
            s2 = context.getResources().getConfiguration().locale.getCountry();
            Rlog.w("PhoneNumberUtils", (new StringBuilder()).append("No CountryDetector; falling back to countryIso based on locale: ").append(s2).toString());
        }
        return isEmergencyNumberInternal(i, s, s2, flag);
    }

    private static boolean isLocalEmergencyNumberInternal(String s, Context context, boolean flag)
    {
        return isLocalEmergencyNumberInternal(getDefaultVoiceSubId(), s, context, flag);
    }

    public static boolean isNanp(String s)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        if(s == null)
            break MISSING_BLOCK_LABEL_76;
        flag1 = flag;
        if(s.length() != 10) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(!isTwoToNine(s.charAt(0))) goto _L2; else goto _L3
_L3:
        flag1 = flag;
        if(!isTwoToNine(s.charAt(3))) goto _L2; else goto _L4
_L4:
        int i;
        flag = true;
        i = 1;
_L8:
        flag1 = flag;
        if(i >= 10) goto _L2; else goto _L5
_L5:
        if(isISODigit(s.charAt(i))) goto _L7; else goto _L6
_L6:
        flag1 = false;
_L2:
        return flag1;
_L7:
        i++;
          goto _L8
        Rlog.e("isNanp: null dialStr passed in", s);
        flag1 = flag;
          goto _L2
    }

    public static final boolean isNonSeparator(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c != '*')
        {
            flag1 = flag;
            if(c != '#')
            {
                flag1 = flag;
                if(c != '+')
                {
                    flag1 = flag;
                    if(c != 'N')
                    {
                        flag1 = flag;
                        if(c != ';')
                        {
                            flag1 = flag;
                            if(c != ',')
                                flag1 = false;
                        }
                    }
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isNonSeparator(String s)
    {
        int i = 0;
        for(int j = s.length(); i < j; i++)
            if(!isNonSeparator(s.charAt(i)))
                return false;

        return true;
    }

    private static boolean isOneNanp(String s)
    {
        boolean flag = false;
        boolean flag1;
        if(s != null)
        {
            String s1 = s.substring(1);
            flag1 = flag;
            if(s.charAt(0) == '1')
            {
                flag1 = flag;
                if(isNanp(s1))
                    flag1 = true;
            }
        } else
        {
            Rlog.e("isOneNanp: null dialStr passed in", s);
            flag1 = flag;
        }
        return flag1;
    }

    private static boolean isPause(char c)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(c != 'p')
            if(c == 'P')
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isPotentialEmergencyNumber(int i, String s)
    {
        return isEmergencyNumberInternal(i, s, false);
    }

    public static boolean isPotentialEmergencyNumber(int i, String s, String s1)
    {
        return isEmergencyNumberInternal(i, s, s1, false);
    }

    public static boolean isPotentialEmergencyNumber(String s)
    {
        return isPotentialEmergencyNumber(getDefaultVoiceSubId(), s);
    }

    public static boolean isPotentialEmergencyNumber(String s, String s1)
    {
        return isPotentialEmergencyNumber(getDefaultVoiceSubId(), s, s1);
    }

    public static boolean isPotentialLocalEmergencyNumber(Context context, int i, String s)
    {
        return isLocalEmergencyNumberInternal(i, s, context, false);
    }

    public static boolean isPotentialLocalEmergencyNumber(Context context, String s)
    {
        return isPotentialLocalEmergencyNumber(context, getDefaultVoiceSubId(), s);
    }

    public static final boolean isReallyDialable(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(c != '*')
        {
            flag1 = flag;
            if(c != '#')
            {
                flag1 = flag;
                if(c != '+')
                    flag1 = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isSeparator(char c)
    {
label0:
        {
            boolean flag = true;
            boolean flag1 = false;
            boolean flag2 = flag1;
            if(isDialable(c))
                break label0;
            if('a' <= c)
            {
                flag2 = flag1;
                if(c <= 'z')
                    break label0;
            }
            flag2 = flag;
            if('A' <= c)
                if(c > 'Z')
                    flag2 = flag;
                else
                    flag2 = false;
        }
        return flag2;
    }

    public static final boolean isStartsPostDial(char c)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(c != ',')
            if(c == ';')
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static boolean isToneWait(char c)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(c != 'w')
            if(c == 'W')
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static boolean isTwoToNine(char c)
    {
        return c >= '2' && c <= '9';
    }

    public static boolean isUriNumber(String s)
    {
        boolean flag;
        if(s != null)
        {
            if(!s.contains("@"))
                flag = s.contains("%40");
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public static boolean isVoiceMailNumber(int i, String s)
    {
        return isVoiceMailNumber(null, i, s);
    }

    public static boolean isVoiceMailNumber(Context context, int i, String s)
    {
        if(context != null)
            break MISSING_BLOCK_LABEL_35;
        Object obj = TelephonyManager.getDefault();
_L1:
        String s1;
        s1 = ((TelephonyManager) (obj)).getVoiceMailNumber(i);
        obj = ((TelephonyManager) (obj)).getLine1Number(i);
        s = extractNetworkPortionAlt(s);
        if(TextUtils.isEmpty(s))
            return false;
        break MISSING_BLOCK_LABEL_46;
        try
        {
            obj = TelephonyManager.from(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
          goto _L1
        boolean flag = false;
        boolean flag1 = flag;
        if(context != null)
        {
            context = (CarrierConfigManager)context.getSystemService("carrier_config");
            flag1 = flag;
            if(context != null)
            {
                context = context.getConfigForSubId(i);
                flag1 = flag;
                if(context != null)
                    flag1 = context.getBoolean("mdn_is_additional_voicemail_number_bool");
            }
        }
        if(flag1)
        {
            boolean flag2;
            if(!compare(s, s1))
                flag2 = compare(s, ((String) (obj)));
            else
                flag2 = true;
            return flag2;
        } else
        {
            return compare(s, s1);
        }
    }

    public static boolean isVoiceMailNumber(String s)
    {
        return isVoiceMailNumber(SubscriptionManager.getDefaultSubscriptionId(), s);
    }

    public static boolean isWellFormedSmsAddress(String s)
    {
        s = extractNetworkPortion(s);
        boolean flag;
        if(!s.equals("+"))
            flag = TextUtils.isEmpty(s);
        else
            flag = true;
        if(!flag)
            flag = isDialable(s);
        else
            flag = false;
        return flag;
    }

    private static void log(String s)
    {
        Rlog.d("PhoneNumberUtils", s);
    }

    private static boolean matchIntlPrefix(String s, int i)
    {
        boolean flag;
        int j;
        int k;
        flag = true;
        j = 0;
        k = 0;
_L6:
        char c;
        if(k >= i)
            break MISSING_BLOCK_LABEL_159;
        c = s.charAt(k);
        j;
        JVM INSTR tableswitch 0 4: default 56
    //                   0 66
    //                   1 56
    //                   2 103
    //                   3 56
    //                   4 137;
           goto _L1 _L2 _L1 _L3 _L1 _L4
_L1:
        if(isNonSeparator(c))
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        if(c != '+')
            break; /* Loop/switch isn't completed */
        j = 1;
_L9:
        k++;
        if(true) goto _L6; else goto _L5
_L5:
        if(c != '0') goto _L8; else goto _L7
_L7:
        j = 2;
          goto _L9
_L8:
        if(!isNonSeparator(c)) goto _L9; else goto _L10
_L10:
        return false;
_L3:
label0:
        {
            if(c != '0')
                break label0;
            j = 3;
        }
          goto _L9
        if(c != '1') goto _L12; else goto _L11
_L11:
        j = 4;
          goto _L9
_L12:
        if(!isNonSeparator(c)) goto _L9; else goto _L13
_L13:
        return false;
_L4:
        if(c != '1')
            continue; /* Loop/switch isn't completed */
        j = 5;
          goto _L9
        if(!isNonSeparator(c)) goto _L9; else goto _L14
_L14:
        return false;
        boolean flag1 = flag;
        if(j == 1) goto _L16; else goto _L15
_L15:
        if(j != 3) goto _L18; else goto _L17
_L17:
        flag1 = flag;
_L16:
        return flag1;
_L18:
        flag1 = flag;
        if(j != 5)
            flag1 = false;
        if(true) goto _L16; else goto _L19
_L19:
    }

    private static boolean matchIntlPrefixAndCC(String s, int i)
    {
        boolean flag;
        int j;
        int k;
        flag = true;
        j = 0;
        k = 0;
_L9:
        char c;
        if(k >= i)
            break MISSING_BLOCK_LABEL_219;
        c = s.charAt(k);
        j;
        JVM INSTR tableswitch 0 7: default 68
    //                   0 78
    //                   1 171
    //                   2 115
    //                   3 171
    //                   4 149
    //                   5 171
    //                   6 195
    //                   7 195;
           goto _L1 _L2 _L3 _L4 _L3 _L5 _L3 _L6 _L6
_L1:
        if(isNonSeparator(c))
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        if(c != '+') goto _L8; else goto _L7
_L7:
        j = 1;
_L12:
        k++;
          goto _L9
_L8:
        if(c != '0') goto _L11; else goto _L10
_L10:
        j = 2;
          goto _L12
_L11:
        if(!isNonSeparator(c)) goto _L12; else goto _L13
_L13:
        return false;
_L4:
label0:
        {
            if(c != '0')
                break label0;
            j = 3;
        }
          goto _L12
        if(c != '1') goto _L15; else goto _L14
_L14:
        j = 4;
          goto _L12
_L15:
        if(!isNonSeparator(c)) goto _L12; else goto _L16
_L16:
        return false;
_L5:
        if(c != '1') goto _L18; else goto _L17
_L17:
        j = 5;
          goto _L12
_L18:
        if(!isNonSeparator(c)) goto _L12; else goto _L19
_L19:
        return false;
_L3:
        if(!isISODigit(c)) goto _L21; else goto _L20
_L20:
        j = 6;
          goto _L12
_L21:
        if(!isNonSeparator(c)) goto _L12; else goto _L22
_L22:
        return false;
_L6:
        if(!isISODigit(c))
            continue; /* Loop/switch isn't completed */
        j++;
          goto _L12
        if(!isNonSeparator(c)) goto _L12; else goto _L23
_L23:
        return false;
        boolean flag1 = flag;
        if(j == 6) goto _L25; else goto _L24
_L24:
        if(j != 7) goto _L27; else goto _L26
_L26:
        flag1 = flag;
_L25:
        return flag1;
_L27:
        flag1 = flag;
        if(j != 8)
            flag1 = false;
        if(true) goto _L25; else goto _L28
_L28:
    }

    private static boolean matchTrunkPrefix(String s, int i)
    {
        boolean flag;
        int j;
        flag = false;
        j = 0;
_L2:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_47;
        c = s.charAt(j);
        if(c != '0' || !(flag ^ true))
            break; /* Loop/switch isn't completed */
        flag = true;
_L4:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(!isNonSeparator(c)) goto _L4; else goto _L3
_L3:
        return false;
        return flag;
    }

    private static int minPositive(int i, int j)
    {
        if(i >= 0 && j >= 0)
        {
            if(i >= j)
                i = j;
            return i;
        }
        if(i >= 0)
            return i;
        if(j >= 0)
            return j;
        else
            return -1;
    }

    public static byte[] networkPortionToCalledPartyBCD(String s)
    {
        return numberToCalledPartyBCDHelper(extractNetworkPortion(s), false);
    }

    public static byte[] networkPortionToCalledPartyBCDWithLength(String s)
    {
        return numberToCalledPartyBCDHelper(extractNetworkPortion(s), true);
    }

    public static String normalizeNumber(String s)
    {
        if(TextUtils.isEmpty(s))
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            PhoneNumberUtilsInjector.appendNonSeparator(stringbuilder, c, j);
            int k = Character.digit(c, 10);
            if(k != -1)
                stringbuilder.append(k);
            else
            if(stringbuilder.length() == 0 && c == '+')
                stringbuilder.append(c);
            else
                while(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') 
                    return normalizeNumber(convertKeypadLettersToDigits(s));
            j++;
        }
        return stringbuilder.toString();
    }

    public static byte[] numberToCalledPartyBCD(String s)
    {
        return numberToCalledPartyBCDHelper(s, false);
    }

    private static byte[] numberToCalledPartyBCDHelper(String s, boolean flag)
    {
        int i = s.length();
        int j = i;
        boolean flag1;
        if(s.indexOf('+') != -1)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            j = i - 1;
        if(j == 0)
            return null;
        int k = (j + 1) / 2;
        j = 1;
        if(flag)
            j = 2;
        int l = k + j;
        byte abyte0[] = new byte[l];
        int i1 = 0;
        k = 0;
        while(k < i) 
        {
            char c1 = s.charAt(k);
            if(c1 != '+')
            {
                byte byte0;
                int j1;
                if((i1 & 1) == 1)
                    byte0 = 4;
                else
                    byte0 = 0;
                j1 = (i1 >> 1) + j;
                abyte0[j1] = (byte)(abyte0[j1] | (byte)((charToBCD(c1) & 0xf) << byte0));
                i1++;
            }
            k++;
        }
        if((i1 & 1) == 1)
        {
            j = (i1 >> 1) + j;
            abyte0[j] = (byte)(abyte0[j] | 0xf0);
        }
        j = 0;
        if(flag)
        {
            j = 1;
            abyte0[0] = (byte)(l - 1);
        }
        char c;
        if(flag1)
            c = '\221';
        else
            c = '\201';
        abyte0[j] = (byte)c;
        return abyte0;
    }

    private static String processPlusCode(String s, boolean flag)
    {
        String s1 = s;
        String s2 = s1;
        if(s != null)
        {
            s2 = s1;
            if(s.charAt(0) == '+')
            {
                s2 = s1;
                if(s.length() > 1)
                {
                    s2 = s.substring(1);
                    if(!flag || !isOneNanp(s2))
                        s2 = s.replaceFirst("[+]", getCurrentIdp(flag));
                }
            }
        }
        return s2;
    }

    private static void removeDashes(Editable editable)
    {
        for(int i = 0; i < editable.length();)
            if(editable.charAt(i) == '-')
                editable.delete(i, i + 1);
            else
                i++;

    }

    public static String replaceUnicodeDigits(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(s.length());
        s = s.toCharArray();
        int i = 0;
        int j = s.length;
        while(i < j) 
        {
            char c = s[i];
            int k = Character.digit(c, 10);
            if(k != -1)
                stringbuilder.append(k);
            else
                stringbuilder.append(c);
            i++;
        }
        return stringbuilder.toString();
    }

    public static void resetCountryDetectorInfo()
    {
        sCountryDetector = null;
    }

    private static String splitAtNonNumerics(CharSequence charsequence)
    {
        StringBuilder stringbuilder = new StringBuilder(charsequence.length());
        int i = 0;
        while(i < charsequence.length()) 
        {
            Object obj;
            if(is12Key(charsequence.charAt(i)))
                obj = Character.valueOf(charsequence.charAt(i));
            else
                obj = " ";
            stringbuilder.append(obj);
            i++;
        }
        return stringbuilder.toString().replaceAll(" +", " ").trim();
    }

    public static String stringFromStringAndTOA(String s, int i)
    {
        if(s == null)
            return null;
        if(i == 145 && s.length() > 0 && s.charAt(0) != '+')
            return (new StringBuilder()).append("+").append(s).toString();
        else
            return s;
    }

    public static String stripSeparators(String s)
    {
        if(s == null)
            return null;
        int i = s.length();
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            int k = Character.digit(c, 10);
            if(k != -1)
                stringbuilder.append(k);
            else
            if(isNonSeparator(c))
                stringbuilder.append(c);
            j++;
        }
        return stringbuilder.toString();
    }

    public static String toCallerIDMinMatch(String s)
    {
        return internalGetStrippedReversed(extractNetworkPortionAlt(s), MIN_MATCH);
    }

    public static int toaFromString(String s)
    {
        return s == null || s.length() <= 0 || s.charAt(0) != '+' ? 129 : 145;
    }

    private static CountryCallingCodeAndNewIndex tryGetCountryCallingCodeAndNewIndex(String s, boolean flag)
    {
        int i;
        int j;
        int k;
        int l;
        i = 0;
        j = 0;
        k = s.length();
        l = 0;
_L8:
        char c;
        if(l >= k)
            break MISSING_BLOCK_LABEL_327;
        c = s.charAt(l);
        i;
        JVM INSTR tableswitch 0 9: default 84
    //                   0 86
    //                   1 198
    //                   2 142
    //                   3 198
    //                   4 176
    //                   5 198
    //                   6 198
    //                   7 198
    //                   8 281
    //                   9 304;
           goto _L1 _L2 _L3 _L4 _L3 _L5 _L3 _L3 _L3 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_304;
_L1:
        return null;
_L2:
        if(c == '+')
        {
            i = 1;
        } else
        {
label0:
            {
                if(c != '0')
                    break label0;
                i = 2;
            }
        }
_L11:
        l++;
          goto _L8
        if(c != '1') goto _L10; else goto _L9
_L9:
        if(flag)
            i = 8;
        else
            return null;
          goto _L11
_L10:
        if(!isDialable(c)) goto _L11; else goto _L12
_L12:
        return null;
_L4:
label1:
        {
            if(c != '0')
                break label1;
            i = 3;
        }
          goto _L11
        if(c != '1') goto _L14; else goto _L13
_L13:
        i = 4;
          goto _L11
_L14:
        if(!isDialable(c)) goto _L11; else goto _L15
_L15:
        return null;
_L5:
        if(c != '1') goto _L17; else goto _L16
_L16:
        i = 5;
          goto _L11
_L17:
        if(!isDialable(c)) goto _L11; else goto _L18
_L18:
        return null;
_L3:
        int i1 = tryGetISODigit(c);
        if(i1 <= 0) goto _L20; else goto _L19
_L19:
        j = j * 10 + i1;
        if(j >= 100 || isCountryCallingCode(j))
            return new CountryCallingCodeAndNewIndex(j, l + 1);
        if(i == 1 || i == 3 || i == 5)
            i = 6;
        else
            i++;
          goto _L11
_L20:
        if(!isDialable(c)) goto _L11; else goto _L21
_L21:
        return null;
_L6:
        if(c != '6')
            continue; /* Loop/switch isn't completed */
        i = 9;
          goto _L11
        if(!isDialable(c)) goto _L11; else goto _L22
_L22:
        return null;
        if(c == '6')
            return new CountryCallingCodeAndNewIndex(66, l + 1);
        else
            return null;
        return null;
    }

    private static int tryGetISODigit(char c)
    {
        if('0' <= c && c <= '9')
            return c - 48;
        else
            return -1;
    }

    private static int tryGetTrunkPrefixOmittedIndex(String s, int i)
    {
        for(int j = s.length(); i < j; i++)
        {
            char c = s.charAt(i);
            if(tryGetISODigit(c) >= 0)
                return i + 1;
            if(isDialable(c))
                return -1;
        }

        return -1;
    }

    public static CharSequence ttsSpanAsPhoneNumber(CharSequence charsequence)
    {
        return createTtsSpannable(charsequence);
    }

    public static void ttsSpanAsPhoneNumber(Spannable spannable, int i, int j)
    {
        addTtsSpan(spannable, i, j);
    }

    private static final int CCC_LENGTH = COUNTRY_CALLING_CALL.length;
    private static final String CLIR_OFF = "#31#";
    private static final String CLIR_ON = "*31#";
    private static final boolean COUNTRY_CALLING_CALL[] = {
        1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 
        1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 
        1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 
        0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 
        1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 
        1, 1, 1, 1, 1, 1, 0, 0, 1, 0
    };
    private static final boolean DBG = false;
    public static final int FORMAT_JAPAN = 2;
    public static final int FORMAT_NANP = 1;
    public static final int FORMAT_UNKNOWN = 0;
    private static final Pattern GLOBAL_PHONE_NUMBER_PATTERN = Pattern.compile("[\\+]?[0-9.-]+");
    private static final String JAPAN_ISO_COUNTRY_CODE = "JP";
    private static final SparseIntArray KEYPAD_MAP;
    private static final String KOREA_ISO_COUNTRY_CODE = "KR";
    static final String LOG_TAG = "PhoneNumberUtils";
    static final int MIN_MATCH = SystemProperties.getInt("persist.radio.phone.matchnum", 7);
    private static final String NANP_COUNTRIES[] = {
        "US", "CA", "AS", "AI", "AG", "BS", "BB", "BM", "VG", "KY", 
        "DM", "DO", "GD", "GU", "JM", "PR", "MS", "MP", "KN", "LC", 
        "VC", "TT", "TC", "VI"
    };
    private static final String NANP_IDP_STRING = "011";
    private static final int NANP_LENGTH = 10;
    private static final int NANP_STATE_DASH = 4;
    private static final int NANP_STATE_DIGIT = 1;
    private static final int NANP_STATE_ONE = 3;
    private static final int NANP_STATE_PLUS = 2;
    public static final char PAUSE = 44;
    private static final char PLUS_SIGN_CHAR = 43;
    private static final String PLUS_SIGN_STRING = "+";
    public static final int TOA_International = 145;
    public static final int TOA_Unknown = 129;
    public static final char WAIT = 59;
    public static final char WILD = 78;
    private static String sConvertToEmergencyMap[] = null;
    private static Country sCountryDetector = null;

    static 
    {
        KEYPAD_MAP = new SparseIntArray();
        KEYPAD_MAP.put(97, 50);
        KEYPAD_MAP.put(98, 50);
        KEYPAD_MAP.put(99, 50);
        KEYPAD_MAP.put(65, 50);
        KEYPAD_MAP.put(66, 50);
        KEYPAD_MAP.put(67, 50);
        KEYPAD_MAP.put(100, 51);
        KEYPAD_MAP.put(101, 51);
        KEYPAD_MAP.put(102, 51);
        KEYPAD_MAP.put(68, 51);
        KEYPAD_MAP.put(69, 51);
        KEYPAD_MAP.put(70, 51);
        KEYPAD_MAP.put(103, 52);
        KEYPAD_MAP.put(104, 52);
        KEYPAD_MAP.put(105, 52);
        KEYPAD_MAP.put(71, 52);
        KEYPAD_MAP.put(72, 52);
        KEYPAD_MAP.put(73, 52);
        KEYPAD_MAP.put(106, 53);
        KEYPAD_MAP.put(107, 53);
        KEYPAD_MAP.put(108, 53);
        KEYPAD_MAP.put(74, 53);
        KEYPAD_MAP.put(75, 53);
        KEYPAD_MAP.put(76, 53);
        KEYPAD_MAP.put(109, 54);
        KEYPAD_MAP.put(110, 54);
        KEYPAD_MAP.put(111, 54);
        KEYPAD_MAP.put(77, 54);
        KEYPAD_MAP.put(78, 54);
        KEYPAD_MAP.put(79, 54);
        KEYPAD_MAP.put(112, 55);
        KEYPAD_MAP.put(113, 55);
        KEYPAD_MAP.put(114, 55);
        KEYPAD_MAP.put(115, 55);
        KEYPAD_MAP.put(80, 55);
        KEYPAD_MAP.put(81, 55);
        KEYPAD_MAP.put(82, 55);
        KEYPAD_MAP.put(83, 55);
        KEYPAD_MAP.put(116, 56);
        KEYPAD_MAP.put(117, 56);
        KEYPAD_MAP.put(118, 56);
        KEYPAD_MAP.put(84, 56);
        KEYPAD_MAP.put(85, 56);
        KEYPAD_MAP.put(86, 56);
        KEYPAD_MAP.put(119, 57);
        KEYPAD_MAP.put(120, 57);
        KEYPAD_MAP.put(121, 57);
        KEYPAD_MAP.put(122, 57);
        KEYPAD_MAP.put(87, 57);
        KEYPAD_MAP.put(88, 57);
        KEYPAD_MAP.put(89, 57);
        KEYPAD_MAP.put(90, 57);
    }
}
