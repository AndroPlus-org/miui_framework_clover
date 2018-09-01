// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.miui.internal.telephony.phonenumber.ChineseTelocation;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import miui.telephony.phonenumber.CountryCode;
import miui.telephony.phonenumber.CountryCodeConverter;
import miui.telephony.phonenumber.Prefix;
import miui.util.AppConstants;

// Referenced classes of package miui.telephony:
//            SubscriptionManager, TelephonyManager, TelephonyManagerEx

public class PhoneNumberUtils extends android.telephony.PhoneNumberUtils
{
    public static interface OperatorQueryListener
    {

        public abstract void onComplete(Object obj, Object obj1, Object obj2, Object obj3, String s);
    }

    public static class PhoneNumber
    {

        public static String addCountryCode(String s)
        {
            PhoneNumber phonenumber = parse(s);
            boolean flag = TextUtils.isEmpty(phonenumber.getCountryCode());
            boolean flag1 = flag;
            String s2;
            if(flag)
            {
                flag1 = flag;
                String s1;
                if(phonenumber.isChineseNumber())
                    if(!TextUtils.isEmpty(phonenumber.getPrefix()))
                        flag1 = false;
                    else
                    if(phonenumber.isServiceNumber())
                    {
                        flag1 = false;
                    } else
                    {
                        flag1 = flag;
                        if(!phonenumber.isNormalMobileNumber())
                            flag1 = TextUtils.isEmpty(phonenumber.getAreaCode()) ^ true;
                    }
            }
            s1 = s;
            s2 = s1;
            if(flag1)
            {
                s2 = CountryCode.getUserDefinedCountryCode();
                String s3 = s2;
                if(TextUtils.isEmpty(s2))
                    s3 = CountryCode.getIccCountryCode();
                s2 = s1;
                if(!TextUtils.isEmpty(s3))
                    if("39".equals(s3) || s.charAt(0) != '0')
                        s2 = (new StringBuilder()).append("+").append(s3).append(s).toString();
                    else
                        s2 = (new StringBuilder()).append("+").append(s3).append(s.substring(1)).toString();
            }
            phonenumber.recycle();
            return s2;
        }

        private static boolean areEqual(CharSequence charsequence, int i, CharSequence charsequence1, int j, int k)
        {
            while(charsequence == null || charsequence1 == null || i < 0 || j < 0 || k < 0) 
                return false;
            if(charsequence.length() < i + k || charsequence1.length() < j + k)
                return false;
            for(int l = 0; l < k; l++)
                if(charsequence.charAt(i + l) != charsequence1.charAt(j + l))
                    return false;

            return true;
        }

        private void attach(CharSequence charsequence)
        {
            Object obj = charsequence;
            if(charsequence == null)
                obj = "";
            mOriginal = ((CharSequence) (obj));
            boolean flag = false;
            int i = 0;
            int j = ((CharSequence) (obj)).length();
            while(i < j) 
            {
                char c = ((CharSequence) (obj)).charAt(i);
                boolean flag1;
                if(flag && PhoneNumberUtils.isNonSeparator(c))
                {
                    mBuffer.append(c);
                    flag1 = flag;
                } else
                if(i == 0 && c == '+')
                {
                    mBuffer.append(c);
                    flag1 = flag;
                } else
                if(c >= '0' && c <= '9')
                {
                    mBuffer.append(c);
                    flag1 = flag;
                } else
                {
                    flag1 = flag;
                    if(!flag)
                    {
                        flag1 = flag;
                        if(PhoneNumberUtils.isStartsPostDial(c))
                        {
                            mPostDialStringStart = mBuffer.length();
                            flag1 = true;
                            mBuffer.append(c);
                        }
                    }
                }
                i++;
                flag = flag1;
            }
            if(!flag)
                mPostDialStringStart = mBuffer.length();
        }

        private void clear()
        {
            mBuffer.setLength(0);
            mPrefix = null;
            mCountryCode = null;
            mAreaCode = null;
            mEffectiveNumberStart = 0;
            mEffectiveNumber = null;
            mPostDialStringStart = 0;
            mPostDialString = null;
            mIsChinaEnvironment = false;
            mNetIddCode = null;
        }

        public static String getDefaultCountryCode()
        {
            return CountryCode.getIccCountryCode();
        }

        public static String getDialableNumber(String s)
        {
            if(TextUtils.isEmpty(s))
                return "";
            int i = s.indexOf('\002');
            String s1;
            if(i < 0)
                i = 1;
            else
                i++;
            s1 = s;
            if(s.charAt(0) == '\001')
                s1 = s.substring(i);
            return s1;
        }

        public static String getHashString(String s)
        {
            PhoneNumber phonenumber = parse(s);
            if(phonenumber.isSmsPrefix())
                s = (new StringBuilder()).append(phonenumber.getPrefix()).append(phonenumber.getEffectiveNumber()).toString();
            else
                s = phonenumber.getEffectiveNumber();
            if(!phonenumber.isChineseNumber())
                s = String.format("%c(00%s)%s%s", new Object[] {
                    Character.valueOf('\001'), phonenumber.getCountryCode(), s, phonenumber.getPostDialString()
                });
            else
            if(phonenumber.isNormalMobileNumber())
                s = String.format("%c(00%s)%s%s", new Object[] {
                    Character.valueOf('\001'), "86", s, phonenumber.getPostDialString()
                });
            else
            if(!TextUtils.isEmpty(phonenumber.getCountryCode()))
            {
                if(!TextUtils.isEmpty(phonenumber.getAreaCode()))
                    s = String.format("%c(00%s)%s-%s%s", new Object[] {
                        Character.valueOf('\001'), "86", phonenumber.getAreaCode(), s, phonenumber.getPostDialString()
                    });
                else
                    s = String.format("%c(00%s)%s%s", new Object[] {
                        Character.valueOf('\001'), "86", s, phonenumber.getPostDialString()
                    });
            } else
            if(!TextUtils.isEmpty(phonenumber.getAreaCode()))
                s = String.format("%c(00%s)%s-%s%s", new Object[] {
                    Character.valueOf('\001'), "86", phonenumber.getAreaCode(), s, phonenumber.getPostDialString()
                });
            else
                s = String.format("%c(00%s)%c%s%s", new Object[] {
                    Character.valueOf('\001'), "86", Character.valueOf('\002'), s, phonenumber.getPostDialString()
                });
            phonenumber.recycle();
            return s;
        }

        public static String getLocation(Context context, CharSequence charsequence)
        {
            charsequence = parse(charsequence);
            context = charsequence.getLocation(context);
            charsequence.recycle();
            return context;
        }

        public static String getLocationAreaCode(Context context, String s)
        {
            s = parse(s);
            context = s.getLocationAreaCode(context);
            s.recycle();
            return context;
        }

        public static String getOperator(Context context, CharSequence charsequence)
        {
            charsequence = parse(charsequence);
            context = charsequence.getOperator(context);
            charsequence.recycle();
            return context;
        }

        public static boolean isChineseOperator()
        {
            return CountryCode.isChinaEnvironment();
        }

        public static boolean isValidCountryCode(String s)
        {
            return CountryCodeConverter.isValidCountryCode(s);
        }

        public static PhoneNumber parse(CharSequence charsequence)
        {
            return parse(charsequence, CountryCode.isChinaEnvironment(), null);
        }

        public static PhoneNumber parse(CharSequence charsequence, boolean flag)
        {
            return parse(charsequence, flag, null);
        }

        public static PhoneNumber parse(CharSequence charsequence, boolean flag, String s)
        {
            PhoneNumber aphonenumber[] = sPool;
            aphonenumber;
            JVM INSTR monitorenter ;
            if(sPoolIndex != -1) goto _L2; else goto _L1
_L1:
            PhoneNumber phonenumber = new PhoneNumber();
_L3:
            aphonenumber;
            JVM INSTR monitorexit ;
            phonenumber.attach(charsequence);
            phonenumber.mIsChinaEnvironment = flag;
            phonenumber.mNetIddCode = s;
            return phonenumber;
_L2:
            PhoneNumber aphonenumber1[];
            int i;
            phonenumber = sPool[sPoolIndex];
            aphonenumber1 = sPool;
            i = sPoolIndex;
            sPoolIndex = i - 1;
            aphonenumber1[i] = null;
              goto _L3
            charsequence;
            throw charsequence;
        }

        public static String replaceCdmaInternationalAccessCode(String s)
        {
            if(s.startsWith("+86") && "86".equals(CountryCode.getNetworkCountryCode()))
            {
                s = s.substring(3);
                if(PhoneNumberUtils.isChinaMobileNumber(s))
                    return s;
                if(s.charAt(0) == '0')
                    return s;
                else
                    return (new StringBuilder()).append('0').append(s).toString();
            }
            Object obj = s;
            if(!TextUtils.isEmpty(s))
            {
                obj = s;
                if(s.charAt(0) == '+')
                {
                    obj = CountryCode.getIddCodes();
                    obj = (new StringBuilder()).append((String)((List) (obj)).get(0)).append(s.substring(1)).toString();
                }
            }
            return ((String) (obj));
        }

        public String getAreaCode()
        {
            if(mAreaCode == null)
            {
                mAreaCode = "";
                if(isChineseNumber() && Prefix.isSmsPrefix(getPrefix()) ^ true)
                {
                    boolean flag = true;
                    String s = getCountryCode();
                    if(TextUtils.isEmpty(s))
                    {
                        boolean flag1 = false;
                        flag = flag1;
                        if(mBuffer.length() - 1 > mEffectiveNumberStart)
                        {
                            flag = flag1;
                            if(mBuffer.charAt(mEffectiveNumberStart) == '0')
                            {
                                flag = true;
                                mEffectiveNumberStart = mEffectiveNumberStart + 1;
                            }
                        }
                    }
                    if(flag)
                    {
                        mAreaCode = ChineseTelocation.getInstance().parseAreaCode(mBuffer, mEffectiveNumberStart, mPostDialStringStart - mEffectiveNumberStart);
                        if(mAreaCode.length() == 0 && TextUtils.isEmpty(s))
                            mEffectiveNumberStart = mEffectiveNumberStart - 1;
                        else
                            mEffectiveNumberStart = mEffectiveNumberStart + mAreaCode.length();
                    }
                }
            }
            return mAreaCode;
        }

        public String getCountryCode()
        {
            if(mCountryCode != null) goto _L2; else goto _L1
_L1:
            Iterator iterator;
            getPrefix();
            iterator = CountryCode.getIddCodes().iterator();
_L9:
            if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
            String s1;
            String s = (String)iterator.next();
            s1 = "+";
            if(!areEqual(mBuffer, mEffectiveNumberStart, "+", 0, "+".length()))
            {
                if(!TextUtils.isEmpty(mNetIddCode))
                    s = mNetIddCode;
                s1 = s;
                if(!areEqual(mBuffer, mEffectiveNumberStart, s, 0, s.length()))
                    s1 = null;
            }
            if(s1 == null) goto _L5; else goto _L4
_L4:
            mEffectiveNumberStart = mEffectiveNumberStart + s1.length();
            mCountryCode = CountryCodeConverter.parse(mBuffer, mEffectiveNumberStart, mPostDialStringStart - mEffectiveNumberStart);
            if(mCountryCode.length() == 0) goto _L7; else goto _L6
_L6:
            mEffectiveNumberStart = mEffectiveNumberStart + mCountryCode.length();
_L2:
            return mCountryCode;
_L7:
            mEffectiveNumberStart = mEffectiveNumberStart - s1.length();
            continue; /* Loop/switch isn't completed */
_L5:
            mCountryCode = "";
            if(true) goto _L9; else goto _L8
_L8:
        }

        public String getEffectiveNumber()
        {
            String s;
            if(mEffectiveNumber == null)
            {
                getAreaCode();
                if(mBuffer.length() > mEffectiveNumberStart)
                    mEffectiveNumber = mBuffer.substring(mEffectiveNumberStart, mPostDialStringStart);
                else
                    mEffectiveNumber = "";
            }
            if(TextUtils.isEmpty(mEffectiveNumber))
            {
                s = mOriginal.toString();
                mOriginal = s;
                s = s.toString();
            } else
            {
                s = mEffectiveNumber;
            }
            return s;
        }

        public String getFakeNumberToQueryLocation()
        {
            Object obj = getEffectiveNumber();
            if(TextUtils.isEmpty(getAreaCode()) && ((String) (obj)).startsWith("1"))
            {
                int i = ((String) (obj)).length();
                byte byte0 = 7;
                byte byte1 = 11;
                if(((String) (obj)).startsWith("141") || ((String) (obj)).startsWith("1064"))
                {
                    byte0 = 9;
                    byte1 = 13;
                }
                if(i >= byte0 && i < byte1)
                {
                    obj = new StringBuilder(mOriginal);
                    for(int j = i; j < byte1; j++)
                        ((StringBuilder) (obj)).append('9');

                    obj = ((StringBuilder) (obj)).toString();
                } else
                {
                    obj = mOriginal.toString();
                }
            } else
            {
                obj = mOriginal.toString();
            }
            return ((String) (obj));
        }

        public String getLocation(Context context)
        {
            boolean flag = true;
            Locale locale = context.getResources().getConfiguration().locale;
            if(!locale.getLanguage().equals(Locale.CHINA.getLanguage()) || isChineseNumber() ^ true)
            {
                context = ChineseTelocation.getInstance().getExternalLocation(context, getCountryCode(), mOriginal, locale);
            } else
            {
                ChineseTelocation chinesetelocation = ChineseTelocation.getInstance();
                StringBuffer stringbuffer = mBuffer;
                int i = mEffectiveNumberStart;
                int j = mPostDialStringStart;
                int k = mEffectiveNumberStart;
                boolean flag1 = flag;
                if(!isNormalMobileNumber())
                    if(getAreaCode().length() > 0)
                        flag1 = flag;
                    else
                        flag1 = false;
                context = chinesetelocation.getLocation(context, stringbuffer, i, j - k, flag1);
            }
            return context;
        }

        public String getLocationAreaCode(Context context)
        {
            context = "";
            if(isChineseNumber())
                if(isNormalMobileNumber())
                    context = ChineseTelocation.getInstance().getAreaCode(mBuffer, mEffectiveNumberStart, mPostDialStringStart - mEffectiveNumberStart);
                else
                    context = getAreaCode();
            return context;
        }

        public String getNormalizedNumber(boolean flag, boolean flag1)
        {
            if(isChineseNumber()) goto _L2; else goto _L1
_L1:
            String s2;
            int i;
            int l;
            String s;
            if(flag)
                i = mEffectiveNumberStart - getCountryCode().length();
            else
                i = mEffectiveNumberStart;
            if(flag1)
                l = mBuffer.length();
            else
                l = mPostDialStringStart;
            s = mBuffer.substring(i, l);
            s2 = s;
            if(flag)
            {
                s2 = s;
                if(getCountryCode().length() > 0)
                    s2 = (new StringBuilder()).append("+").append(s).toString();
            }
_L4:
            return s2;
_L2:
            if(isNormalMobileNumber())
            {
                int j;
                String s1;
                if(flag1)
                    j = mBuffer.length();
                else
                    j = mPostDialStringStart;
                s1 = mBuffer.substring(mEffectiveNumberStart, j);
                s2 = s1;
                if(flag)
                    s2 = (new StringBuilder()).append("+86").append(s1).toString();
            } else
            {
                int k;
                if(flag1)
                    k = mBuffer.length();
                else
                    k = mPostDialStringStart;
                if(!TextUtils.isEmpty(getAreaCode()) && isServiceNumber() ^ true)
                {
                    s2 = mBuffer.substring(mEffectiveNumberStart - getAreaCode().length(), k);
                    if(flag)
                        s2 = (new StringBuilder()).append("+86").append(s2).toString();
                    else
                        s2 = (new StringBuilder()).append("0").append(s2).toString();
                } else
                {
                    s2 = mBuffer.substring(mEffectiveNumberStart, k);
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public String getNumberWithoutPrefix(boolean flag)
        {
            int i = 0;
            if(!TextUtils.isEmpty(getPrefix()))
                i = getPrefix().length();
            String s;
            if(flag)
                s = mBuffer.substring(i);
            else
                s = mBuffer.substring(i, mPostDialStringStart);
            return s;
        }

        public String getOperator(Context context)
        {
            if(!context.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage()) || isChineseNumber() ^ true)
                context = "";
            else
                context = ChineseTelocation.getInstance().getOperator(context, mBuffer.toString(), mEffectiveNumberStart, mPostDialStringStart - mEffectiveNumberStart, isNormalMobileNumber());
            return context;
        }

        public String getPostDialString()
        {
            if(mPostDialString == null)
                if(mBuffer.length() > mPostDialStringStart)
                    mPostDialString = mBuffer.substring(mPostDialStringStart);
                else
                    mPostDialString = "";
            return mPostDialString;
        }

        public String getPrefix()
        {
            if(mPrefix == null && mIsChinaEnvironment)
            {
                mPrefix = Prefix.parse(mBuffer, mEffectiveNumberStart, mPostDialStringStart - mEffectiveNumberStart);
                mEffectiveNumberStart = mEffectiveNumberStart + mPrefix.length();
            }
            return mPrefix;
        }

        public boolean isChineseNumber()
        {
            String s = getCountryCode();
            boolean flag;
            if(!TextUtils.isEmpty(s))
                flag = "86".equals(s);
            else
            if(!mIsChinaEnvironment)
                flag = "86".equals(mDefaultCountryCode);
            else
                flag = true;
            return flag;
        }

        public boolean isNormalMobileNumber()
        {
            boolean flag;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            flag = true;
            flag3 = true;
            flag4 = true;
            flag5 = false;
            getAreaCode();
            if(!isChineseNumber()) goto _L2; else goto _L1
_L1:
            int i = mPostDialStringStart - mEffectiveNumberStart;
            if(i != 11) goto _L4; else goto _L3
_L3:
            if(mBuffer.charAt(mEffectiveNumberStart) == '1')
                switch(mBuffer.charAt(mEffectiveNumberStart + 1))
                {
                default:
                    return false;

                case 51: // '3'
                    flag = flag4;
                    if(mBuffer.charAt(mEffectiveNumberStart + 2) == '8')
                        if(mBuffer.charAt(mEffectiveNumberStart + 3) != '0')
                        {
                            flag = flag4;
                        } else
                        {
                            flag = flag4;
                            if(mBuffer.charAt(mEffectiveNumberStart + 4) == '0')
                            {
                                flag = flag4;
                                if(mBuffer.charAt(mEffectiveNumberStart + 5) == '1')
                                {
                                    flag = flag4;
                                    if(mBuffer.charAt(mEffectiveNumberStart + 6) == '3')
                                    {
                                        flag = flag4;
                                        if(mBuffer.charAt(mEffectiveNumberStart + 7) == '8')
                                        {
                                            flag = flag4;
                                            if(mBuffer.charAt(mEffectiveNumberStart + 8) == '0')
                                            {
                                                flag = flag4;
                                                if(mBuffer.charAt(mEffectiveNumberStart + 9) == '0')
                                                {
                                                    flag = flag4;
                                                    if(mBuffer.charAt(mEffectiveNumberStart + 10) == '0')
                                                        flag = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    return flag;

                case 55: // '7'
                    if(mBuffer.charAt(mEffectiveNumberStart + 2) == '9')
                        flag = false;
                    return flag;

                case 52: // '4'
                case 53: // '5'
                case 54: // '6'
                case 56: // '8'
                case 57: // '9'
                    return true;
                }
              goto _L2
_L4:
            if(i != 13 || mBuffer.charAt(mEffectiveNumberStart) != '1') goto _L2; else goto _L5
_L5:
            mBuffer.charAt(mEffectiveNumberStart + 1);
            JVM INSTR lookupswitch 2: default 396
        //                       48: 398
        //                       52: 444;
               goto _L2 _L6 _L7
_L2:
            return false;
_L6:
            boolean flag1 = flag5;
            if(mBuffer.charAt(mEffectiveNumberStart + 2) == '6')
            {
                flag1 = flag5;
                if(mBuffer.charAt(mEffectiveNumberStart + 3) == '4')
                    flag1 = true;
            }
            return flag1;
_L7:
            boolean flag2;
            if(mBuffer.charAt(mEffectiveNumberStart + 2) == '1')
                flag2 = flag3;
            else
                flag2 = false;
            return flag2;
        }

        public boolean isServiceNumber()
        {
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;
            getAreaCode();
            if(isChineseNumber() && mPostDialStringStart - mEffectiveNumberStart > 2)
            {
                char c = mBuffer.charAt(mEffectiveNumberStart);
                if(c == '1')
                {
                    switch(mBuffer.charAt(mEffectiveNumberStart + 1))
                    {
                    case 52: // '4'
                    case 53: // '5'
                    default:
                        return false;

                    case 48: // '0'
                    case 49: // '1'
                    case 50: // '2'
                    case 54: // '6'
                        return true;

                    case 51: // '3'
                        if(mPostDialStringStart - mEffectiveNumberStart >= 11 && mBuffer.charAt(mEffectiveNumberStart + 2) == '8' && mBuffer.charAt(mEffectiveNumberStart + 3) == '0' && mBuffer.charAt(mEffectiveNumberStart + 4) == '0' && mBuffer.charAt(mEffectiveNumberStart + 5) == '1' && mBuffer.charAt(mEffectiveNumberStart + 6) == '3' && mBuffer.charAt(mEffectiveNumberStart + 7) == '8' && mBuffer.charAt(mEffectiveNumberStart + 8) == '0' && mBuffer.charAt(mEffectiveNumberStart + 9) == '0')
                        {
                            if(mBuffer.charAt(mEffectiveNumberStart + 10) != '0')
                                flag3 = false;
                        } else
                        {
                            flag3 = false;
                        }
                        return flag3;

                    case 55: // '7'
                        break;
                    }
                    if(mBuffer.charAt(mEffectiveNumberStart + 2) == '9')
                        flag3 = flag;
                    else
                        flag3 = false;
                    return flag3;
                }
                if(c == '9')
                    return true;
                if(c >= '2' && c <= '8')
                {
                    if(c == '4' || c == '8')
                    {
                        boolean flag4;
                        if(mEffectiveNumberStart == 0 && mBuffer.charAt(1) == '0' && mBuffer.charAt(2) == '0')
                        {
                            if(mBuffer.length() == 10)
                                flag4 = flag1;
                            else
                                flag4 = false;
                        } else
                        {
                            flag4 = false;
                        }
                        return flag4;
                    }
                    boolean flag5;
                    if(mBuffer.charAt(mEffectiveNumberStart + 1) == '0' && mBuffer.charAt(mEffectiveNumberStart + 2) == '0')
                    {
                        if(mBuffer.length() - mEffectiveNumberStart > 7)
                            flag5 = flag2;
                        else
                            flag5 = false;
                    } else
                    {
                        flag5 = false;
                    }
                    return flag5;
                }
            }
            return false;
        }

        public boolean isSmsPrefix()
        {
            return Prefix.isSmsPrefix(getPrefix());
        }

        public void recycle()
        {
            clear();
            PhoneNumber aphonenumber[] = sPool;
            aphonenumber;
            JVM INSTR monitorenter ;
            PhoneNumber aphonenumber1[];
            int i;
            if(sPoolIndex >= sPool.length)
                break MISSING_BLOCK_LABEL_38;
            aphonenumber1 = sPool;
            i = sPoolIndex + 1;
            sPoolIndex = i;
            aphonenumber1[i] = this;
            aphonenumber;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setDefaultCountryCode(String s)
        {
            mDefaultCountryCode = s;
        }

        private static final String EMPTY = "";
        private static final char HASH_STRING_INDICATOR = 1;
        private static final int MAX_NUMBER_LENGTH = 256;
        private static final char MISSING_AREA_CODE_INDICATOR = 2;
        private static final int POOL_SIZE = 10;
        private static final PhoneNumber sPool[] = new PhoneNumber[10];
        private static int sPoolIndex = -1;
        private String mAreaCode;
        private StringBuffer mBuffer;
        private String mCountryCode;
        private String mDefaultCountryCode;
        private String mEffectiveNumber;
        private int mEffectiveNumberStart;
        private boolean mIsChinaEnvironment;
        private String mNetIddCode;
        private CharSequence mOriginal;
        private String mPostDialString;
        private int mPostDialStringStart;
        private String mPrefix;


        private PhoneNumber()
        {
            mBuffer = new StringBuffer(256);
            clear();
        }
    }

    public static interface TelocationAndOperatorQueryListener
    {

        public abstract void onComplete(Object obj, Object obj1, Object obj2, Object obj3, String s, String s1);
    }

    private static class TelocationAsyncQueryHandler extends AsyncQueryHandler
    {

        public static TelocationAsyncQueryHandler getInstance()
        {
            return SingletonHolder._2D_get0();
        }

        public static String queryOperator(Context context, CharSequence charsequence)
        {
            return PhoneNumber.getOperator(context, charsequence);
        }

        public static String queryTelocation(Context context, CharSequence charsequence)
        {
            return PhoneNumber.getLocation(context, charsequence);
        }

        private void sendMsg(TelocationWorkerArgs telocationworkerargs, int i, int j, Object obj, Object obj1, Object obj2, Object obj3, 
                Context context, String s)
        {
            telocationworkerargs.handler = this;
            telocationworkerargs.context = context;
            telocationworkerargs.phoneNumber = s;
            telocationworkerargs.cookie1 = obj;
            telocationworkerargs.cookie2 = obj1;
            telocationworkerargs.cookie3 = obj2;
            telocationworkerargs.cookie4 = obj3;
            telocationworkerargs.location = null;
            obj = mWorkerHandler.obtainMessage(j);
            obj.arg1 = i;
            obj.obj = telocationworkerargs;
            ((Message) (obj)).sendToTarget();
        }

        protected Handler createHandler(Looper looper)
        {
            if(mWorkerHandler == null)
                mWorkerHandler = new TelocationWorkerHandler(looper);
            return mWorkerHandler;
        }

        public void handleMessage(Message message)
        {
            TelocationWorkerArgs telocationworkerargs = (TelocationWorkerArgs)message.obj;
            if(message.arg1 != 10 || telocationworkerargs.telocationQueryListener == null) goto _L2; else goto _L1
_L1:
            telocationworkerargs.telocationQueryListener.onComplete(telocationworkerargs.cookie1, telocationworkerargs.cookie2, telocationworkerargs.cookie3, telocationworkerargs.cookie4, telocationworkerargs.location);
_L4:
            return;
_L2:
            if(message.arg1 == 20 && telocationworkerargs.operatorQueryListener != null)
                telocationworkerargs.operatorQueryListener.onComplete(telocationworkerargs.cookie1, telocationworkerargs.cookie2, telocationworkerargs.cookie3, telocationworkerargs.cookie4, telocationworkerargs.operator);
            else
            if(message.arg1 == 30 && telocationworkerargs.telocationAndOperatorQueryListener != null)
                telocationworkerargs.telocationAndOperatorQueryListener.onComplete(telocationworkerargs.cookie1, telocationworkerargs.cookie2, telocationworkerargs.cookie3, telocationworkerargs.cookie4, telocationworkerargs.location, telocationworkerargs.operator);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void startQueryOperatorString(int i, Object obj, Object obj1, Object obj2, Object obj3, OperatorQueryListener operatorquerylistener, Context context, 
                String s)
        {
            TelocationWorkerArgs telocationworkerargs = new TelocationWorkerArgs();
            telocationworkerargs.operatorQueryListener = operatorquerylistener;
            sendMsg(telocationworkerargs, 20, i, obj, obj1, obj2, obj3, context, s);
        }

        public void startQueryTelocationAndOperatorString(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationAndOperatorQueryListener telocationandoperatorquerylistener, Context context, 
                String s)
        {
            TelocationWorkerArgs telocationworkerargs = new TelocationWorkerArgs();
            telocationworkerargs.telocationAndOperatorQueryListener = telocationandoperatorquerylistener;
            sendMsg(telocationworkerargs, 30, i, obj, obj1, obj2, obj3, context, s);
        }

        public void startQueryTelocationString(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationQueryListener telocationquerylistener, Context context, 
                String s)
        {
            TelocationWorkerArgs telocationworkerargs = new TelocationWorkerArgs();
            telocationworkerargs.telocationQueryListener = telocationquerylistener;
            sendMsg(telocationworkerargs, 10, i, obj, obj1, obj2, obj3, context, s);
        }

        private static final int EVENT_QUERY_OPERATOR = 20;
        private static final int EVENT_QUERY_TELOCATION = 10;
        private static final int EVENT_QUERY_TELOCATION_AND_OPERATOR = 30;
        private Handler mWorkerHandler;

        private TelocationAsyncQueryHandler()
        {
            super(null);
        }

        TelocationAsyncQueryHandler(TelocationAsyncQueryHandler telocationasyncqueryhandler)
        {
            this();
        }
    }

    private static class TelocationAsyncQueryHandler.SingletonHolder
    {

        static TelocationAsyncQueryHandler _2D_get0()
        {
            return INSTANCE;
        }

        private static final TelocationAsyncQueryHandler INSTANCE = new TelocationAsyncQueryHandler(null);


        private TelocationAsyncQueryHandler.SingletonHolder()
        {
        }
    }

    protected static final class TelocationAsyncQueryHandler.TelocationWorkerArgs
    {

        public Context context;
        public Object cookie1;
        public Object cookie2;
        public Object cookie3;
        public Object cookie4;
        public Handler handler;
        public String location;
        public String operator;
        public OperatorQueryListener operatorQueryListener;
        public String phoneNumber;
        public TelocationAndOperatorQueryListener telocationAndOperatorQueryListener;
        public TelocationQueryListener telocationQueryListener;

        protected TelocationAsyncQueryHandler.TelocationWorkerArgs()
        {
        }
    }

    protected class TelocationAsyncQueryHandler.TelocationWorkerHandler extends android.content.AsyncQueryHandler.WorkerHandler
    {

        public void handleMessage(Message message)
        {
            Object obj = (TelocationAsyncQueryHandler.TelocationWorkerArgs)message.obj;
            if(message.arg1 == 10 || message.arg1 == 30)
                obj.location = TelocationAsyncQueryHandler.queryTelocation(((TelocationAsyncQueryHandler.TelocationWorkerArgs) (obj)).context, ((TelocationAsyncQueryHandler.TelocationWorkerArgs) (obj)).phoneNumber);
            if(message.arg1 == 20 || message.arg1 == 30)
                obj.operator = TelocationAsyncQueryHandler.queryOperator(((TelocationAsyncQueryHandler.TelocationWorkerArgs) (obj)).context, ((TelocationAsyncQueryHandler.TelocationWorkerArgs) (obj)).phoneNumber);
            obj = ((TelocationAsyncQueryHandler.TelocationWorkerArgs) (obj)).handler.obtainMessage(message.what);
            obj.arg1 = message.arg1;
            obj.obj = message.obj;
            ((Message) (obj)).sendToTarget();
        }

        final TelocationAsyncQueryHandler this$1;

        public TelocationAsyncQueryHandler.TelocationWorkerHandler(Looper looper)
        {
            this$1 = TelocationAsyncQueryHandler.this;
            super(TelocationAsyncQueryHandler.this, looper);
        }
    }

    public static interface TelocationQueryListener
    {

        public abstract void onComplete(Object obj, Object obj1, Object obj2, Object obj3, String s);
    }


    public PhoneNumberUtils()
    {
    }

    public static void cancelAsyncTelocationQuery(int i)
    {
        TelocationAsyncQueryHandler.getInstance().cancelOperation(i);
    }

    public static String extractNetworkPortion(String s)
    {
        return extractNetworkPortion(s, 0);
    }

    public static String extractNetworkPortion(String s, int i)
    {
        if(s == null)
            return null;
        if(i == 3 || isUriNumber(s))
            return s.substring(0, indexOfLastNetworkChar(s) + 1).trim();
        else
            return android.telephony.PhoneNumberUtils.extractNetworkPortion(s);
    }

    public static String extractNetworkPortionAlt(String s)
    {
        return extractNetworkPortionAlt(s, 0);
    }

    public static String extractNetworkPortionAlt(String s, int i)
    {
        if(s == null)
            return null;
        if(i == 3 || isUriNumber(s))
            return s.substring(0, indexOfLastNetworkChar(s) + 1).trim();
        else
            return android.telephony.PhoneNumberUtils.extractNetworkPortionAlt(s);
    }

    public static String getDefaultIpBySim(Context context)
    {
        return getDefaultIpBySim(context, SubscriptionManager.getDefault().getDefaultSlotId());
    }

    public static String getDefaultIpBySim(Context context, int i)
    {
        context = TelephonyManager.getDefault();
        String s = context.getSimOperatorForSlot(i);
        if(context.isSameOperator(s, "46000"))
            return "17951";
        if(context.isSameOperator(s, "46001"))
            return "17911";
        if(context.isSameOperator(s, "46003"))
            return "17901";
        else
            return "";
    }

    public static int getPresentation(CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence) || TextUtils.equals(charsequence, "-1"))
            return 3;
        if(TextUtils.equals(charsequence, "-2"))
            return 2;
        return !TextUtils.equals(charsequence, "-3") ? 1 : 4;
    }

    public static String getPresentationString(int i)
    {
        String s = "";
        if(i != 2) goto _L2; else goto _L1
_L1:
        s = Resources.getSystem().getString(miui.system.R.string.presentation_private);
_L4:
        return s;
_L2:
        if(i == 4)
            s = Resources.getSystem().getString(miui.system.R.string.presentation_payphone);
        else
        if(i == 3)
            s = Resources.getSystem().getString(miui.system.R.string.presentation_unknown);
        if(true) goto _L4; else goto _L3
_L3:
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

    private static boolean isAlnum(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'z')
                continue; /* Loop/switch isn't completed */
        }
        if(c >= 'A')
        {
            flag1 = flag;
            if(c <= 'Z')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isChinaMobileNumber(String s)
    {
        if(TextUtils.isEmpty(s) || s.length() < 11)
            return false;
        s = stripSeparators(s);
        if(s.length() >= "86".length() + 11)
            return s.substring(s.length() - 11 - "86".length()).startsWith("861");
        if(s.length() >= 11)
            return s.substring(s.length() - 11).startsWith("1");
        else
            return false;
    }

    public static boolean isChineseOperator(String s)
    {
        boolean flag;
        if(!TextUtils.isEmpty(s))
            flag = s.startsWith("460");
        else
            flag = false;
        return flag;
    }

    public static boolean isDialable(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        int i = 0;
        for(int j = s.length(); i < j; i++)
            if(!isDialable(s.charAt(i)))
                return false;

        return true;
    }

    public static boolean isEmergencyNumber(String s)
    {
        boolean flag = true;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return TelephonyManagerEx.isLocalEmergencyNumber(AppConstants.getCurrentApplication(), s);
        if(!isMiuiEmergencyNumber(s, true))
            flag = android.telephony.PhoneNumberUtils.isEmergencyNumber(s);
        return flag;
    }

    public static boolean isMiuiEmergencyNumber(String s, boolean flag)
    {
        if(s == null)
            return false;
        String as[] = EMERGENCY_NUMBERS;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = as[j];
            if(flag)
            {
                if(s1.equals(s))
                    return true;
                continue;
            }
            if(s.startsWith(s1))
                return true;
        }

        return false;
    }

    public static boolean isServiceNumber(String s)
    {
        s = PhoneNumber.parse(s);
        boolean flag;
        if(s != null)
            flag = s.isServiceNumber();
        else
            flag = false;
        return flag;
    }

    public static String maskPhoneNumber(String s, int i)
    {
        int j;
        int l;
        if(s == null)
            return "";
        j = 0;
        for(int k = 0; k < s.length();)
        {
            int i1 = j;
            if(isAlnum(s.charAt(k)))
                i1 = j + 1;
            k++;
            j = i1;
        }

        if(j < 7)
            return new String(s);
        if(j < 11)
            l = 2;
        else
            l = 3;
        i;
        JVM INSTR tableswitch 0 2: default 96
    //                   0 112
    //                   1 177
    //                   2 184;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_184;
_L1:
        throw new IllegalArgumentException("Invalid cut mode");
_L2:
        i = 0;
_L5:
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        int j1 = 0;
        j = 0;
        while(j < s.length()) 
        {
            if(isAlnum(s.charAt(j)))
            {
                if(j1 < i || l <= 0)
                {
                    stringbuilder.append(s.charAt(j));
                } else
                {
                    stringbuilder.append('?');
                    l--;
                }
                j1++;
            } else
            {
                stringbuilder.append(s.charAt(j));
            }
            j++;
        }
        break MISSING_BLOCK_LABEL_221;
_L3:
        i = j - l;
          goto _L5
        i = (j - l) / 2;
          goto _L5
        return stringbuilder.toString();
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

    public static String miuiFormatNumber(String s, String s1, String s2)
    {
        if(Locale.getDefault().equals(Locale.SIMPLIFIED_CHINESE))
        {
            Object obj = PhoneNumber.parse(s);
            if(obj != null)
            {
                obj = ((PhoneNumber) (obj)).getPrefix();
                if(!TextUtils.isEmpty(((CharSequence) (obj))) && s.startsWith(((String) (obj))))
                {
                    s = android.telephony.PhoneNumberUtils.formatNumber(s.substring(((String) (obj)).length()), s1, s2);
                    return (new StringBuilder()).append(((String) (obj))).append(" ").append(s).toString();
                }
            }
        }
        return android.telephony.PhoneNumberUtils.formatNumber(s, s1, s2);
    }

    public static String parseNumber(String s)
    {
        String s1 = s;
        if(TelephonyManager.getDefault().getSimState() == 5)
        {
            PhoneNumber phonenumber = PhoneNumber.parse(s);
            s1 = s;
            if(phonenumber != null)
                s1 = phonenumber.getEffectiveNumber();
        }
        return s1;
    }

    public static String parseTelocationString(Context context, CharSequence charsequence)
    {
        return TelocationAsyncQueryHandler.queryTelocation(context, charsequence);
    }

    public static void queryOperatorStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, OperatorQueryListener operatorquerylistener, Context context, String s)
    {
        if(android.provider.MiuiSettings.Telephony.isTelocationEnable(context.getContentResolver()))
            TelocationAsyncQueryHandler.getInstance().startQueryOperatorString(i, obj, obj1, obj2, obj3, operatorquerylistener, context, s);
        else
            operatorquerylistener.onComplete(obj, obj1, obj2, obj3, null);
    }

    public static void queryOperatorStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, OperatorQueryListener operatorquerylistener, Context context, String s, 
            boolean flag)
    {
        if(flag)
            TelocationAsyncQueryHandler.getInstance().startQueryOperatorString(i, obj, obj1, obj2, obj3, operatorquerylistener, context, s);
        else
            operatorquerylistener.onComplete(obj, obj1, obj2, obj3, null);
    }

    public static void queryTelocationAndOperatorStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationAndOperatorQueryListener telocationandoperatorquerylistener, Context context, String s)
    {
        if(android.provider.MiuiSettings.Telephony.isTelocationEnable(context.getContentResolver()))
            TelocationAsyncQueryHandler.getInstance().startQueryTelocationAndOperatorString(i, obj, obj1, obj2, obj3, telocationandoperatorquerylistener, context, s);
        else
            telocationandoperatorquerylistener.onComplete(obj, obj1, obj2, obj3, null, null);
    }

    public static void queryTelocationAndOperatorStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationAndOperatorQueryListener telocationandoperatorquerylistener, Context context, String s, 
            boolean flag)
    {
        if(flag)
            TelocationAsyncQueryHandler.getInstance().startQueryTelocationAndOperatorString(i, obj, obj1, obj2, obj3, telocationandoperatorquerylistener, context, s);
        else
            telocationandoperatorquerylistener.onComplete(obj, obj1, obj2, obj3, null, null);
    }

    public static void queryTelocationStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationQueryListener telocationquerylistener, Context context, String s)
    {
        if(android.provider.MiuiSettings.Telephony.isTelocationEnable(context.getContentResolver()))
            TelocationAsyncQueryHandler.getInstance().startQueryTelocationString(i, obj, obj1, obj2, obj3, telocationquerylistener, context, s);
        else
            telocationquerylistener.onComplete(obj, obj1, obj2, obj3, null);
    }

    public static void queryTelocationStringAsync(int i, Object obj, Object obj1, Object obj2, Object obj3, TelocationQueryListener telocationquerylistener, Context context, String s, 
            boolean flag)
    {
        if(flag)
            TelocationAsyncQueryHandler.getInstance().startQueryTelocationString(i, obj, obj1, obj2, obj3, telocationquerylistener, context, s);
        else
            telocationquerylistener.onComplete(obj, obj1, obj2, obj3, null);
    }

    public static String removeDashesAndBlanks(String s)
    {
        if(TextUtils.isEmpty(s))
            return s;
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c != ' ' && c != '-')
                stringbuilder.append(c);
        }

        return stringbuilder.toString();
    }

    public static String[] splitNetworkAndPostDialPortion(String s)
    {
        if(s == null)
            return null;
        int i = indexOfLastNetworkChar(s) + 1;
        String s1 = s.substring(0, i);
        if(i == s.length())
            s = "";
        else
            s = s.substring(i);
        return (new String[] {
            s1, s
        });
    }

    public static String stripSeparatorsAndCountryCode(String s)
    {
        String s1;
        s1 = stripSeparators(s);
        s = s1;
        if(s1 == null) goto _L2; else goto _L1
_L1:
        if(!s1.startsWith("+86")) goto _L4; else goto _L3
_L3:
        s = s1.substring("+86".length());
_L2:
        return s;
_L4:
        s = s1;
        if(s1.startsWith("0086"))
            s = s1.substring("0086".length());
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static String toLogSafePhoneNumber(String s)
    {
        return toLogSafePhoneNumber(s, 0);
    }

    public static String toLogSafePhoneNumber(String s, int i)
    {
        StringBuilder stringbuilder;
        char c;
        int j;
        if(s == null)
            j = 0;
        else
            j = s.length();
        if(j == 0)
            return "";
        stringbuilder = new StringBuilder(j);
        int k;
        if(j > i)
            i = j - i;
        else
            i = j;
        k = 0;
_L2:
        if(k >= j)
            break MISSING_BLOCK_LABEL_111;
        c = s.charAt(k);
        break MISSING_BLOCK_LABEL_56;
        if(k >= i || c == '-' || c == '@' || c == '.')
            stringbuilder.append(c);
        else
            stringbuilder.append('x');
        k++;
        continue; /* Loop/switch isn't completed */
        return stringbuilder.toString();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String CHINA_COUNTRY_CODE = "86";
    private static final int CHINA_IOT_MOBILE_NUMBER_LENGTH = 13;
    public static final String CHINA_MCC = "460";
    private static final int CHINA_MOBILE_NUMBER_LENGTH = 11;
    private static final String CHINA_MOBILE_NUMBER_PREFIX = "1";
    private static final String CHINA_REGION_CODE1 = "+86";
    private static final String CHINA_REGION_CODE2 = "0086";
    private static final String EMERGENCY_NUMBERS[] = {
        "110", "112", "119", "120", "122", "911", "999", "995", "100", "101", 
        "102", "190"
    };
    static final String LOG_TAG = "PhoneNumberUtils";
    public static final int MASK_PHONE_NUMBER_MODE_HEAD = 0;
    public static final int MASK_PHONE_NUMBER_MODE_MIDDLE = 2;
    public static final int MASK_PHONE_NUMBER_MODE_TAIL = 1;
    private static final int MIN_QUERY_LOCATION_EFFECTIVE_IOT_NUMBER_LENGTH = 9;
    private static final int MIN_QUERY_LOCATION_EFFECTIVE_NUMBER_LENGTH = 7;
    public static final String PAYPHONE_NUMBER = "-3";
    public static final String PRIVATE_NUMBER = "-2";
    public static final String UNKNOWN_NUMBER = "-1";

}
