// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package org.apache.miui.commons.lang3:
//            CharSequenceUtils, ArrayUtils, CharUtils, ObjectUtils

public class StringUtils
{
    private static class InitStripAccents
    {

        static Throwable _2D_get0()
        {
            return java6Exception;
        }

        static Method _2D_get1()
        {
            return java6NormalizeMethod;
        }

        static Object _2D_get2()
        {
            return java6NormalizerFormNFD;
        }

        static Pattern _2D_get3()
        {
            return java6Pattern;
        }

        static Method _2D_get4()
        {
            return sunDecomposeMethod;
        }

        static Throwable _2D_get5()
        {
            return sunException;
        }

        static Pattern _2D_get6()
        {
            return sunPattern;
        }

        private static final Throwable java6Exception;
        private static final Method java6NormalizeMethod;
        private static final Object java6NormalizerFormNFD;
        private static final Pattern java6Pattern;
        private static final Method sunDecomposeMethod;
        private static final Throwable sunException;
        private static final Pattern sunPattern;

        static 
        {
            Object obj;
            Object obj1;
            Method method;
            Object obj2;
            Object obj3;
            Object obj4;
            sunPattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            java6Pattern = sunPattern;
            obj = null;
            obj1 = null;
            method = null;
            obj2 = null;
            obj3 = null;
            obj4 = obj;
            Object obj5 = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer$Form");
            obj4 = obj;
            obj = ((Class) (obj5)).getField("NFD").get(null);
            obj4 = obj;
            obj5 = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer").getMethod("normalize", new Class[] {
                java/lang/CharSequence, obj5
            });
            obj4 = obj;
            obj = obj5;
            obj5 = obj2;
_L2:
            java6Exception = ((Throwable) (obj5));
            java6NormalizerFormNFD = obj4;
            java6NormalizeMethod = ((Method) (obj));
            sunException = ((Throwable) (obj3));
            sunDecomposeMethod = method;
            return;
            obj5;
            obj = Thread.currentThread().getContextClassLoader().loadClass("sun.text.Normalizer").getMethod("decompose", new Class[] {
                java/lang/String, Boolean.TYPE, Integer.TYPE
            });
            method = ((Method) (obj));
            obj = obj1;
            continue; /* Loop/switch isn't completed */
            obj3;
            obj = obj1;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private InitStripAccents()
        {
        }
    }


    public StringUtils()
    {
    }

    public static String abbreviate(String s, int i)
    {
        return abbreviate(s, 0, i);
    }

    public static String abbreviate(String s, int i, int j)
    {
        if(s == null)
            return null;
        if(j < 4)
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        if(s.length() <= j)
            return s;
        int k = i;
        if(i > s.length())
            k = s.length();
        i = k;
        if(s.length() - k < j - 3)
            i = s.length() - (j - 3);
        if(i <= 4)
            return (new StringBuilder()).append(s.substring(0, j - 3)).append("...").toString();
        if(j < 7)
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        if((i + j) - 3 < s.length())
            return (new StringBuilder()).append("...").append(abbreviate(s.substring(i), j - 3)).toString();
        else
            return (new StringBuilder()).append("...").append(s.substring(s.length() - (j - 3))).toString();
    }

    public static String abbreviateMiddle(String s, String s1, int i)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        if(i >= s.length() || i < s1.length() + 2)
        {
            return s;
        } else
        {
            int j = i - s1.length();
            int k = j / 2;
            int l = s.length();
            int i1 = j / 2;
            StringBuilder stringbuilder = new StringBuilder(i);
            stringbuilder.append(s.substring(0, k + j % 2));
            stringbuilder.append(s1);
            stringbuilder.append(s.substring(l - i1));
            return stringbuilder.toString();
        }
    }

    public static String capitalize(String s)
    {
        int i;
label0:
        {
            if(s != null)
            {
                i = s.length();
                if(i != 0)
                    break label0;
            }
            return s;
        }
        return (new StringBuilder(i)).append(Character.toTitleCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String center(String s, int i)
    {
        return center(s, i, ' ');
    }

    public static String center(String s, int i, char c)
    {
        if(s == null || i <= 0)
            return s;
        int j = s.length();
        int k = i - j;
        if(k <= 0)
            return s;
        else
            return rightPad(leftPad(s, k / 2 + j, c), i, c);
    }

    public static String center(String s, int i, String s1)
    {
        if(s == null || i <= 0)
            return s;
        String s2 = s1;
        if(isEmpty(s1))
            s2 = " ";
        int j = s.length();
        int k = i - j;
        if(k <= 0)
            return s;
        else
            return rightPad(leftPad(s, k / 2 + j, s2), i, s2);
    }

    public static String chomp(String s)
    {
        int j;
        char c1;
        if(isEmpty(s))
            return s;
        if(s.length() == 1)
        {
            char c = s.charAt(0);
            if(c == '\r' || c == '\n')
                return "";
            else
                return s;
        }
        j = s.length() - 1;
        c1 = s.charAt(j);
        if(c1 != '\n') goto _L2; else goto _L1
_L1:
        int i;
        i = j;
        if(s.charAt(j - 1) == '\r')
            i = j - 1;
_L4:
        return s.substring(0, i);
_L2:
        i = j;
        if(c1 != '\r')
            i = j + 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String chomp(String s, String s1)
    {
        return removeEnd(s, s1);
    }

    public static String chop(String s)
    {
        if(s == null)
            return null;
        int i = s.length();
        if(i < 2)
            return "";
        i--;
        String s1 = s.substring(0, i);
        if(s.charAt(i) == '\n' && s1.charAt(i - 1) == '\r')
            return s1.substring(0, i - 1);
        else
            return s1;
    }

    public static boolean contains(CharSequence charsequence, int i)
    {
        boolean flag = false;
        if(isEmpty(charsequence))
            return false;
        if(CharSequenceUtils.indexOf(charsequence, i, 0) >= 0)
            flag = true;
        return flag;
    }

    public static boolean contains(CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag = false;
        if(charsequence == null || charsequence1 == null)
            return false;
        if(CharSequenceUtils.indexOf(charsequence, charsequence1, 0) >= 0)
            flag = true;
        return flag;
    }

    public static boolean containsAny(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence1 == null)
            return false;
        else
            return containsAny(charsequence, CharSequenceUtils.toCharArray(charsequence1));
    }

    public static transient boolean containsAny(CharSequence charsequence, char ac[])
    {
        if(isEmpty(charsequence) || ArrayUtils.isEmpty(ac))
            return false;
        int i = charsequence.length();
        int j = ac.length;
        for(int k = 0; k < i; k++)
        {
            char c = charsequence.charAt(k);
            for(int l = 0; l < j; l++)
            {
                if(ac[l] != c)
                    continue;
                if(Character.isHighSurrogate(c))
                {
                    if(l == j - 1)
                        return true;
                    if(k < i - 1 && ac[l + 1] == charsequence.charAt(k + 1))
                        return true;
                } else
                {
                    return true;
                }
            }

        }

        return false;
    }

    public static boolean containsIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null || charsequence1 == null)
            return false;
        int i = charsequence1.length();
        int j = charsequence.length();
        for(int k = 0; k <= j - i; k++)
            if(CharSequenceUtils.regionMatches(charsequence, true, k, charsequence1, 0, i))
                return true;

        return false;
    }

    public static boolean containsNone(CharSequence charsequence, String s)
    {
        if(charsequence == null || s == null)
            return true;
        else
            return containsNone(charsequence, s.toCharArray());
    }

    public static transient boolean containsNone(CharSequence charsequence, char ac[])
    {
        if(charsequence == null || ac == null)
            return true;
        int i = charsequence.length();
        int j = ac.length;
        for(int k = 0; k < i; k++)
        {
            char c = charsequence.charAt(k);
            for(int l = 0; l < j; l++)
            {
                if(ac[l] != c)
                    continue;
                if(Character.isHighSurrogate(c))
                {
                    if(l == j - 1)
                        return false;
                    if(k < i - 1 && ac[l + 1] == charsequence.charAt(k + 1))
                        return false;
                } else
                {
                    return false;
                }
            }

        }

        return true;
    }

    public static boolean containsOnly(CharSequence charsequence, String s)
    {
        if(charsequence == null || s == null)
            return false;
        else
            return containsOnly(charsequence, s.toCharArray());
    }

    public static transient boolean containsOnly(CharSequence charsequence, char ac[])
    {
        boolean flag = true;
        if(ac == null || charsequence == null)
            return false;
        if(charsequence.length() == 0)
            return true;
        if(ac.length == 0)
            return false;
        if(indexOfAnyBut(charsequence, ac) != -1)
            flag = false;
        return flag;
    }

    public static boolean containsWhitespace(CharSequence charsequence)
    {
        if(isEmpty(charsequence))
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(Character.isWhitespace(charsequence.charAt(j)))
                return true;

        return false;
    }

    public static int countMatches(CharSequence charsequence, CharSequence charsequence1)
    {
        if(isEmpty(charsequence) || isEmpty(charsequence1))
            return 0;
        int i = 0;
        int j = 0;
        do
        {
            j = CharSequenceUtils.indexOf(charsequence, charsequence1, j);
            if(j != -1)
            {
                i++;
                j += charsequence1.length();
            } else
            {
                return i;
            }
        } while(true);
    }

    public static CharSequence defaultIfBlank(CharSequence charsequence, CharSequence charsequence1)
    {
        if(!isBlank(charsequence))
            charsequence1 = charsequence;
        return charsequence1;
    }

    public static CharSequence defaultIfEmpty(CharSequence charsequence, CharSequence charsequence1)
    {
        if(!isEmpty(charsequence))
            charsequence1 = charsequence;
        return charsequence1;
    }

    public static String defaultString(String s)
    {
        String s1 = s;
        if(s == null)
            s1 = "";
        return s1;
    }

    public static String defaultString(String s, String s1)
    {
        if(s != null)
            s1 = s;
        return s1;
    }

    public static String deleteWhitespace(String s)
    {
        if(isEmpty(s))
            return s;
        int i = s.length();
        char ac[] = new char[i];
        int j = 0;
        int k = 0;
        for(; j < i; j++)
            if(!Character.isWhitespace(s.charAt(j)))
            {
                int l = k + 1;
                ac[k] = s.charAt(j);
                k = l;
            }

        if(k == i)
            return s;
        else
            return new String(ac, 0, k);
    }

    public static String difference(String s, String s1)
    {
        if(s == null)
            return s1;
        if(s1 == null)
            return s;
        int i = indexOfDifference(s, s1);
        if(i == -1)
            return "";
        else
            return s1.substring(i);
    }

    public static boolean endsWith(CharSequence charsequence, CharSequence charsequence1)
    {
        return endsWith(charsequence, charsequence1, false);
    }

    private static boolean endsWith(CharSequence charsequence, CharSequence charsequence1, boolean flag)
    {
        boolean flag1 = false;
        if(charsequence == null || charsequence1 == null)
        {
            flag = flag1;
            if(charsequence == null)
            {
                flag = flag1;
                if(charsequence1 == null)
                    flag = true;
            }
            return flag;
        }
        if(charsequence1.length() > charsequence.length())
            return false;
        else
            return CharSequenceUtils.regionMatches(charsequence, flag, charsequence.length() - charsequence1.length(), charsequence1, 0, charsequence1.length());
    }

    public static transient boolean endsWithAny(CharSequence charsequence, CharSequence acharsequence[])
    {
        if(isEmpty(charsequence) || ArrayUtils.isEmpty(acharsequence))
            return false;
        int i = acharsequence.length;
        for(int j = 0; j < i; j++)
            if(endsWith(charsequence, acharsequence[j]))
                return true;

        return false;
    }

    public static boolean endsWithIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        return endsWith(charsequence, charsequence1, true);
    }

    public static boolean equals(CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag;
        if(charsequence == null)
        {
            if(charsequence1 == null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = charsequence.equals(charsequence1);
        }
        return flag;
    }

    public static boolean equalsIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag = true;
        if(charsequence == null || charsequence1 == null)
        {
            if(charsequence != charsequence1)
                flag = false;
            return flag;
        } else
        {
            return CharSequenceUtils.regionMatches(charsequence, true, 0, charsequence1, 0, Math.max(charsequence.length(), charsequence1.length()));
        }
    }

    public static transient String getCommonPrefix(String as[])
    {
        if(as == null || as.length == 0)
            return "";
        int i = indexOfDifference(as);
        if(i == -1)
            if(as[0] == null)
                return "";
            else
                return as[0];
        if(i == 0)
            return "";
        else
            return as[0].substring(0, i);
    }

    public static int getLevenshteinDistance(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null || charsequence1 == null)
            throw new IllegalArgumentException("Strings must not be null");
        int i = charsequence.length();
        int k = charsequence1.length();
        if(i == 0)
            return k;
        if(k == 0)
            return i;
        int l = k;
        int i1 = i;
        CharSequence charsequence2 = charsequence;
        CharSequence charsequence3 = charsequence1;
        if(i > k)
        {
            charsequence3 = charsequence;
            i1 = k;
            l = charsequence.length();
            charsequence2 = charsequence1;
        }
        charsequence1 = new int[i1 + 1];
        charsequence = new int[i1 + 1];
        for(k = 0; k <= i1; k++)
            charsequence1[k] = k;

        k = 1;
        do
        {
            CharSequence charsequence4 = charsequence1;
            if(k <= l)
            {
                char c = charsequence3.charAt(k - 1);
                charsequence[0] = k;
                int j = 1;
                while(j <= i1) 
                {
                    int j1;
                    if(charsequence2.charAt(j - 1) == c)
                        j1 = 0;
                    else
                        j1 = 1;
                    charsequence[j] = Math.min(Math.min(charsequence[j - 1] + 1, charsequence4[j] + 1), charsequence4[j - 1] + j1);
                    j++;
                }
                charsequence1 = charsequence;
                charsequence = charsequence4;
                k++;
            } else
            {
                return charsequence4[i1];
            }
        } while(true);
    }

    public static int getLevenshteinDistance(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(charsequence == null || charsequence1 == null)
            throw new IllegalArgumentException("Strings must not be null");
        if(i < 0)
            throw new IllegalArgumentException("Threshold must not be negative");
        int j = charsequence.length();
        int l = charsequence1.length();
        if(j == 0)
        {
            if(l > i)
                l = -1;
            return l;
        }
        if(l == 0)
        {
            if(j > i)
                j = -1;
            return j;
        }
        int i1 = l;
        int j1 = j;
        CharSequence charsequence2 = charsequence;
        CharSequence charsequence3 = charsequence1;
        if(j > l)
        {
            charsequence3 = charsequence;
            i1 = charsequence.length();
            charsequence2 = charsequence1;
            j1 = l;
        }
        charsequence1 = new int[j1 + 1];
        charsequence = new int[j1 + 1];
        j = Math.min(j1, i) + 1;
        for(l = 0; l < j; l++)
            charsequence1[l] = l;

        Arrays.fill(charsequence1, j, charsequence1.length, 0x7fffffff);
        Arrays.fill(charsequence, 0x7fffffff);
        l = 1;
        CharSequence charsequence4;
        do
        {
            charsequence4 = charsequence1;
            if(l > i1)
                break;
            char c = charsequence3.charAt(l - 1);
            charsequence[0] = l;
            int k = Math.max(1, l - i);
            int k1 = Math.min(j1, l + i);
            if(k > k1)
                return -1;
            if(k > 1)
                charsequence[k - 1] = 0x7fffffff;
            while(k <= k1) 
            {
                if(charsequence2.charAt(k - 1) == c)
                    charsequence[k] = charsequence4[k - 1];
                else
                    charsequence[k] = Math.min(Math.min(charsequence[k - 1], charsequence4[k]), charsequence4[k - 1]) + 1;
                k++;
            }
            charsequence1 = charsequence;
            charsequence = charsequence4;
            l++;
        } while(true);
        if(charsequence4[j1] <= i)
            return charsequence4[j1];
        else
            return -1;
    }

    public static int indexOf(CharSequence charsequence, int i)
    {
        if(isEmpty(charsequence))
            return -1;
        else
            return CharSequenceUtils.indexOf(charsequence, i, 0);
    }

    public static int indexOf(CharSequence charsequence, int i, int j)
    {
        if(isEmpty(charsequence))
            return -1;
        else
            return CharSequenceUtils.indexOf(charsequence, i, j);
    }

    public static int indexOf(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        else
            return CharSequenceUtils.indexOf(charsequence, charsequence1, 0);
    }

    public static int indexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        else
            return CharSequenceUtils.indexOf(charsequence, charsequence1, i);
    }

    public static int indexOfAny(CharSequence charsequence, String s)
    {
        if(isEmpty(charsequence) || isEmpty(s))
            return -1;
        else
            return indexOfAny(charsequence, s.toCharArray());
    }

    public static transient int indexOfAny(CharSequence charsequence, char ac[])
    {
        if(isEmpty(charsequence) || ArrayUtils.isEmpty(ac))
            return -1;
        int i = charsequence.length();
        int j = ac.length;
        for(int k = 0; k < i; k++)
        {
            char c = charsequence.charAt(k);
            for(int l = 0; l < j; l++)
            {
                if(ac[l] != c)
                    continue;
                if(k < i - 1 && l < j - 1 && Character.isHighSurrogate(c))
                {
                    if(ac[l + 1] == charsequence.charAt(k + 1))
                        return k;
                } else
                {
                    return k;
                }
            }

        }

        return -1;
    }

    public static transient int indexOfAny(CharSequence charsequence, CharSequence acharsequence[])
    {
        if(charsequence == null || acharsequence == null)
            return -1;
        int i = acharsequence.length;
        int j = 0x7fffffff;
        int k = 0;
        while(k < i) 
        {
            CharSequence charsequence1 = acharsequence[k];
            int l;
            if(charsequence1 == null)
            {
                l = j;
            } else
            {
                int j1 = CharSequenceUtils.indexOf(charsequence, charsequence1, 0);
                l = j;
                if(j1 != -1)
                {
                    l = j;
                    if(j1 < j)
                        l = j1;
                }
            }
            k++;
            j = l;
        }
        int i1 = j;
        if(j == 0x7fffffff)
            i1 = -1;
        return i1;
    }

    public static int indexOfAnyBut(CharSequence charsequence, CharSequence charsequence1)
    {
        if(isEmpty(charsequence) || isEmpty(charsequence1))
            return -1;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
        {
            char c = charsequence.charAt(j);
            boolean flag;
            if(CharSequenceUtils.indexOf(charsequence1, c, 0) >= 0)
                flag = true;
            else
                flag = false;
            if(j + 1 < i && Character.isHighSurrogate(c))
            {
                char c1 = charsequence.charAt(j + 1);
                if(flag && CharSequenceUtils.indexOf(charsequence1, c1, 0) < 0)
                    return j;
                continue;
            }
            if(!flag)
                return j;
        }

        return -1;
    }

    public static transient int indexOfAnyBut(CharSequence charsequence, char ac[])
    {
        if(isEmpty(charsequence) || ArrayUtils.isEmpty(ac))
            return -1;
        int i = charsequence.length();
        int j = ac.length;
        int k = 0;
label0:
        do
        {
            if(k < i)
            {
                char c = charsequence.charAt(k);
                int l = 0;
                do
                {
                    if(l >= j)
                        break;
                    if(ac[l] == c && (k >= i - 1 || l >= j - 1 || !Character.isHighSurrogate(c) || ac[l + 1] == charsequence.charAt(k + 1)))
                    {
                        k++;
                        continue label0;
                    }
                    l++;
                } while(true);
                return k;
            }
            return -1;
        } while(true);
    }

    public static int indexOfDifference(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == charsequence1)
            return -1;
        if(charsequence == null || charsequence1 == null)
            return 0;
        int i = 0;
        do
        {
            if(i >= charsequence.length() || i >= charsequence1.length() || charsequence.charAt(i) != charsequence1.charAt(i))
                if(i < charsequence1.length() || i < charsequence.length())
                    return i;
                else
                    return -1;
            i++;
        } while(true);
    }

    public static transient int indexOfDifference(CharSequence acharsequence[])
    {
        int k;
        int l;
        int i1;
        int j1;
        int l1;
        if(acharsequence == null || acharsequence.length <= 1)
            return -1;
        boolean flag = false;
        boolean flag1 = true;
        k = acharsequence.length;
        l = 0x7fffffff;
        i1 = 0;
        j1 = 0;
        while(j1 < k) 
        {
            int k1;
            if(acharsequence[j1] == null)
            {
                flag = true;
                k1 = 0;
            } else
            {
                flag1 = false;
                k1 = Math.min(acharsequence[j1].length(), l);
                i1 = Math.max(acharsequence[j1].length(), i1);
            }
            j1++;
            l = k1;
        }
        if(flag1 || i1 == 0 && flag ^ true)
            return -1;
        if(l == 0)
            return 0;
        j1 = -1;
        l1 = 0;
_L10:
        int i = j1;
        if(l1 >= l) goto _L2; else goto _L1
_L1:
        int j;
        char c;
        c = acharsequence[0].charAt(l1);
        j = 1;
_L8:
        i = j1;
        if(j >= k) goto _L4; else goto _L3
_L3:
        if(acharsequence[j].charAt(l1) == c) goto _L6; else goto _L5
_L5:
        i = l1;
_L4:
        if(i == -1)
            break; /* Loop/switch isn't completed */
_L2:
        if(i == -1 && l != i1)
            return l;
        else
            return i;
_L6:
        j++;
        if(true) goto _L8; else goto _L7
_L7:
        l1++;
        j1 = i;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static int indexOfIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        return indexOfIgnoreCase(charsequence, charsequence1, 0);
    }

    public static int indexOfIgnoreCase(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        int k = (charsequence.length() - charsequence1.length()) + 1;
        if(j > k)
            return -1;
        if(charsequence1.length() == 0)
            return j;
        for(i = j; i < k; i++)
            if(CharSequenceUtils.regionMatches(charsequence, true, i, charsequence1, 0, charsequence1.length()))
                return i;

        return -1;
    }

    public static boolean isAllLowerCase(CharSequence charsequence)
    {
        if(charsequence == null || isEmpty(charsequence))
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isLowerCase(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isAllUpperCase(CharSequence charsequence)
    {
        if(charsequence == null || isEmpty(charsequence))
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isUpperCase(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isAlpha(CharSequence charsequence)
    {
        if(charsequence == null || charsequence.length() == 0)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isLetter(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isAlphaSpace(CharSequence charsequence)
    {
        if(charsequence == null)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isLetter(charsequence.charAt(j)) && charsequence.charAt(j) != ' ')
                return false;

        return true;
    }

    public static boolean isAlphanumeric(CharSequence charsequence)
    {
        if(charsequence == null || charsequence.length() == 0)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isLetterOrDigit(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isAlphanumericSpace(CharSequence charsequence)
    {
        if(charsequence == null)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isLetterOrDigit(charsequence.charAt(j)) && charsequence.charAt(j) != ' ')
                return false;

        return true;
    }

    public static boolean isAsciiPrintable(CharSequence charsequence)
    {
        if(charsequence == null)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!CharUtils.isAsciiPrintable(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isBlank(CharSequence charsequence)
    {
        int i;
label0:
        {
            if(charsequence != null)
            {
                i = charsequence.length();
                if(i != 0)
                    break label0;
            }
            return true;
        }
        for(int j = 0; j < i; j++)
            if(!Character.isWhitespace(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isEmpty(CharSequence charsequence)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(charsequence != null)
            if(charsequence.length() == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isNotBlank(CharSequence charsequence)
    {
        return isBlank(charsequence) ^ true;
    }

    public static boolean isNotEmpty(CharSequence charsequence)
    {
        return isEmpty(charsequence) ^ true;
    }

    public static boolean isNumeric(CharSequence charsequence)
    {
        if(charsequence == null || charsequence.length() == 0)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isDigit(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static boolean isNumericSpace(CharSequence charsequence)
    {
        if(charsequence == null)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isDigit(charsequence.charAt(j)) && charsequence.charAt(j) != ' ')
                return false;

        return true;
    }

    public static boolean isWhitespace(CharSequence charsequence)
    {
        if(charsequence == null)
            return false;
        int i = charsequence.length();
        for(int j = 0; j < i; j++)
            if(!Character.isWhitespace(charsequence.charAt(j)))
                return false;

        return true;
    }

    public static String join(Iterable iterable, char c)
    {
        if(iterable == null)
            return null;
        else
            return join(iterable.iterator(), c);
    }

    public static String join(Iterable iterable, String s)
    {
        if(iterable == null)
            return null;
        else
            return join(iterable.iterator(), s);
    }

    public static String join(Iterator iterator, char c)
    {
        if(iterator == null)
            return null;
        if(!iterator.hasNext())
            return "";
        Object obj = iterator.next();
        if(!iterator.hasNext())
            return ObjectUtils.toString(obj);
        StringBuilder stringbuilder = new StringBuilder(256);
        if(obj != null)
            stringbuilder.append(obj);
        do
        {
            if(!iterator.hasNext())
                break;
            stringbuilder.append(c);
            Object obj1 = iterator.next();
            if(obj1 != null)
                stringbuilder.append(obj1);
        } while(true);
        return stringbuilder.toString();
    }

    public static String join(Iterator iterator, String s)
    {
        if(iterator == null)
            return null;
        if(!iterator.hasNext())
            return "";
        Object obj = iterator.next();
        if(!iterator.hasNext())
            return ObjectUtils.toString(obj);
        StringBuilder stringbuilder = new StringBuilder(256);
        if(obj != null)
            stringbuilder.append(obj);
        do
        {
            if(!iterator.hasNext())
                break;
            if(s != null)
                stringbuilder.append(s);
            Object obj1 = iterator.next();
            if(obj1 != null)
                stringbuilder.append(obj1);
        } while(true);
        return stringbuilder.toString();
    }

    public static transient String join(Object aobj[])
    {
        return join(aobj, ((String) (null)));
    }

    public static String join(Object aobj[], char c)
    {
        if(aobj == null)
            return null;
        else
            return join(aobj, c, 0, aobj.length);
    }

    public static String join(Object aobj[], char c, int i, int j)
    {
        if(aobj == null)
            return null;
        int k = j - i;
        if(k <= 0)
            return "";
        StringBuilder stringbuilder = new StringBuilder(k * 16);
        for(int l = i; l < j; l++)
        {
            if(l > i)
                stringbuilder.append(c);
            if(aobj[l] != null)
                stringbuilder.append(aobj[l]);
        }

        return stringbuilder.toString();
    }

    public static String join(Object aobj[], String s)
    {
        if(aobj == null)
            return null;
        else
            return join(aobj, s, 0, aobj.length);
    }

    public static String join(Object aobj[], String s, int i, int j)
    {
        if(aobj == null)
            return null;
        String s1 = s;
        if(s == null)
            s1 = "";
        int k = j - i;
        if(k <= 0)
            return "";
        s = new StringBuilder(k * 16);
        for(int l = i; l < j; l++)
        {
            if(l > i)
                s.append(s1);
            if(aobj[l] != null)
                s.append(aobj[l]);
        }

        return s.toString();
    }

    public static int lastIndexOf(CharSequence charsequence, int i)
    {
        if(isEmpty(charsequence))
            return -1;
        else
            return CharSequenceUtils.lastIndexOf(charsequence, i, charsequence.length());
    }

    public static int lastIndexOf(CharSequence charsequence, int i, int j)
    {
        if(isEmpty(charsequence))
            return -1;
        else
            return CharSequenceUtils.lastIndexOf(charsequence, i, j);
    }

    public static int lastIndexOf(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        else
            return CharSequenceUtils.lastIndexOf(charsequence, charsequence1, charsequence.length());
    }

    public static int lastIndexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        else
            return CharSequenceUtils.lastIndexOf(charsequence, charsequence1, i);
    }

    public static transient int lastIndexOfAny(CharSequence charsequence, CharSequence acharsequence[])
    {
        if(charsequence == null || acharsequence == null)
            return -1;
        int i = acharsequence.length;
        byte byte0 = -1;
        int j = 0;
        while(j < i) 
        {
            CharSequence charsequence1 = acharsequence[j];
            int k;
            if(charsequence1 == null)
            {
                k = byte0;
            } else
            {
                int l = CharSequenceUtils.lastIndexOf(charsequence, charsequence1, charsequence.length());
                k = byte0;
                if(l > byte0)
                    k = l;
            }
            j++;
            byte0 = k;
        }
        return byte0;
    }

    public static int lastIndexOfIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        else
            return lastIndexOfIgnoreCase(charsequence, charsequence1, charsequence.length());
    }

    public static int lastIndexOfIgnoreCase(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        if(charsequence == null || charsequence1 == null)
            return -1;
        int j = i;
        if(i > charsequence.length() - charsequence1.length())
            j = charsequence.length() - charsequence1.length();
        if(j < 0)
            return -1;
        if(charsequence1.length() == 0)
            return j;
        for(; j >= 0; j--)
            if(CharSequenceUtils.regionMatches(charsequence, true, j, charsequence1, 0, charsequence1.length()))
                return j;

        return -1;
    }

    public static int lastOrdinalIndexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        return ordinalIndexOf(charsequence, charsequence1, i, true);
    }

    public static String left(String s, int i)
    {
        if(s == null)
            return null;
        if(i < 0)
            return "";
        if(s.length() <= i)
            return s;
        else
            return s.substring(0, i);
    }

    public static String leftPad(String s, int i)
    {
        return leftPad(s, i, ' ');
    }

    public static String leftPad(String s, int i, char c)
    {
        if(s == null)
            return null;
        int j = i - s.length();
        if(j <= 0)
            return s;
        if(j > 8192)
            return leftPad(s, i, String.valueOf(c));
        else
            return repeat(c, j).concat(s);
    }

    public static String leftPad(String s, int i, String s1)
    {
        if(s == null)
            return null;
        String s2 = s1;
        if(isEmpty(s1))
            s2 = " ";
        int j = s2.length();
        int k = i - s.length();
        if(k <= 0)
            return s;
        if(j == 1 && k <= 8192)
            return leftPad(s, i, s2.charAt(0));
        if(k == j)
            return s2.concat(s);
        if(k < j)
            return s2.substring(0, k).concat(s);
        s1 = new char[k];
        char ac[] = s2.toCharArray();
        for(i = 0; i < k; i++)
            s1[i] = ac[i % j];

        return (new String(s1)).concat(s);
    }

    public static int length(CharSequence charsequence)
    {
        int i;
        if(charsequence == null)
            i = 0;
        else
            i = charsequence.length();
        return i;
    }

    public static String lowerCase(String s)
    {
        if(s == null)
            return null;
        else
            return s.toLowerCase();
    }

    public static String lowerCase(String s, Locale locale)
    {
        if(s == null)
            return null;
        else
            return s.toLowerCase(locale);
    }

    public static String mid(String s, int i, int j)
    {
        if(s == null)
            return null;
        if(j < 0 || i > s.length())
            return "";
        int k = i;
        if(i < 0)
            k = 0;
        if(s.length() <= k + j)
            return s.substring(k);
        else
            return s.substring(k, k + j);
    }

    public static String normalizeSpace(String s)
    {
        if(s == null)
            return null;
        else
            return WHITESPACE_BLOCK.matcher(trim(s)).replaceAll(" ");
    }

    public static int ordinalIndexOf(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        return ordinalIndexOf(charsequence, charsequence1, i, false);
    }

    private static int ordinalIndexOf(CharSequence charsequence, CharSequence charsequence1, int i, boolean flag)
    {
        int j;
        for(j = 0; charsequence == null || charsequence1 == null || i <= 0;)
            return -1;

        if(charsequence1.length() == 0)
        {
            i = j;
            if(flag)
                i = charsequence.length();
            return i;
        }
        int k = 0;
        int l;
        if(flag)
            j = charsequence.length();
        else
            j = -1;
        int i1;
        do
        {
            if(flag)
                l = CharSequenceUtils.lastIndexOf(charsequence, charsequence1, j - 1);
            else
                l = CharSequenceUtils.indexOf(charsequence, charsequence1, j + 1);
            if(l < 0)
                return l;
            i1 = k + 1;
            k = i1;
            j = l;
        } while(i1 < i);
        return l;
    }

    public static String overlay(String s, String s1, int i, int j)
    {
        if(s == null)
            return null;
        String s2 = s1;
        if(s1 == null)
            s2 = "";
        int k = s.length();
        int l = i;
        if(i < 0)
            l = 0;
        i = l;
        if(l > k)
            i = k;
        l = j;
        if(j < 0)
            l = 0;
        j = l;
        if(l > k)
            j = k;
        int i1 = i;
        l = j;
        if(i > j)
        {
            l = i;
            i1 = j;
        }
        return (new StringBuilder(((k + i1) - l) + s2.length() + 1)).append(s.substring(0, i1)).append(s2).append(s.substring(l)).toString();
    }

    public static String remove(String s, char c)
    {
        if(isEmpty(s) || s.indexOf(c) == -1)
            return s;
        s = s.toCharArray();
        int i = 0;
        for(int j = 0; j < s.length;)
        {
            int k = i;
            if(s[j] != c)
            {
                s[i] = s[j];
                k = i + 1;
            }
            j++;
            i = k;
        }

        return new String(s, 0, i);
    }

    public static String remove(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        else
            return replace(s, s1, "", -1);
    }

    private static String removeAccentsJava6(CharSequence charsequence)
        throws IllegalAccessException, InvocationTargetException
    {
        if(InitStripAccents._2D_get1() == null || InitStripAccents._2D_get2() == null)
        {
            throw new IllegalStateException("java.text.Normalizer is not available", InitStripAccents._2D_get0());
        } else
        {
            charsequence = (String)InitStripAccents._2D_get1().invoke(null, new Object[] {
                charsequence, InitStripAccents._2D_get2()
            });
            return InitStripAccents._2D_get3().matcher(charsequence).replaceAll("");
        }
    }

    private static String removeAccentsSUN(CharSequence charsequence)
        throws IllegalAccessException, InvocationTargetException
    {
        if(InitStripAccents._2D_get4() == null)
        {
            throw new IllegalStateException("sun.text.Normalizer is not available", InitStripAccents._2D_get5());
        } else
        {
            charsequence = (String)InitStripAccents._2D_get4().invoke(null, new Object[] {
                charsequence, Boolean.FALSE, Integer.valueOf(0)
            });
            return InitStripAccents._2D_get6().matcher(charsequence).replaceAll("");
        }
    }

    public static String removeEnd(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        if(s.endsWith(s1))
            return s.substring(0, s.length() - s1.length());
        else
            return s;
    }

    public static String removeEndIgnoreCase(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        if(endsWithIgnoreCase(s, s1))
            return s.substring(0, s.length() - s1.length());
        else
            return s;
    }

    public static String removeStart(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        if(s.startsWith(s1))
            return s.substring(s1.length());
        else
            return s;
    }

    public static String removeStartIgnoreCase(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        if(startsWithIgnoreCase(s, s1))
            return s.substring(s1.length());
        else
            return s;
    }

    public static String repeat(char c, int i)
    {
        char ac[] = new char[i];
        for(i--; i >= 0; i--)
            ac[i] = c;

        return new String(ac);
    }

    public static String repeat(String s, int i)
    {
        if(s == null)
            return null;
        if(i <= 0)
            return "";
        int j = s.length();
        if(i == 1 || j == 0)
            return s;
        if(j == 1 && i <= 8192)
            return repeat(s.charAt(0), i);
        int i1 = j * i;
        StringBuilder stringbuilder;
        switch(j)
        {
        default:
            stringbuilder = new StringBuilder(i1);
            for(int k = 0; k < i; k++)
                stringbuilder.append(s);

            break;

        case 1: // '\001'
            return repeat(s.charAt(0), i);

        case 2: // '\002'
            int j1 = s.charAt(0);
            int l = s.charAt(1);
            s = new char[i1];
            for(i = i * 2 - 2; i >= 0; i = i - 1 - 1)
            {
                s[i] = (char)j1;
                s[i + 1] = (char)l;
            }

            return new String(s);
        }
        return stringbuilder.toString();
    }

    public static String repeat(String s, String s1, int i)
    {
        if(s == null || s1 == null)
            return repeat(s, i);
        else
            return removeEnd(repeat((new StringBuilder()).append(s).append(s1).toString(), i), s1);
    }

    public static String replace(String s, String s1, String s2)
    {
        return replace(s, s1, s2, -1);
    }

    public static String replace(String s, String s1, String s2, int i)
    {
        int j;
        for(j = 64; isEmpty(s) || isEmpty(s1) || s2 == null || i == 0;)
            return s;

        boolean flag = false;
        int k = s.indexOf(s1, 0);
        if(k == -1)
            return s;
        int i1 = s1.length();
        int j1 = s2.length() - i1;
        int k1 = j1;
        if(j1 < 0)
            k1 = 0;
        StringBuilder stringbuilder;
        if(i < 0)
            j = 16;
        else
        if(i <= 64)
            j = i;
        stringbuilder = new StringBuilder(s.length() + k1 * j);
        j = i;
        i = ((flag) ? 1 : 0);
        k1 = k;
        do
        {
label0:
            {
                int l = i;
                if(k1 != -1)
                {
                    stringbuilder.append(s.substring(i, k1)).append(s2);
                    i = k1 + i1;
                    if(--j != 0)
                        break label0;
                    l = i;
                }
                stringbuilder.append(s.substring(l));
                return stringbuilder.toString();
            }
            k1 = s.indexOf(s1, i);
        } while(true);
    }

    public static String replaceChars(String s, char c, char c1)
    {
        if(s == null)
            return null;
        else
            return s.replace(c, c1);
    }

    public static String replaceChars(String s, String s1, String s2)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        String s3 = s2;
        if(s2 == null)
            s3 = "";
        boolean flag = false;
        int i = s3.length();
        int j = s.length();
        s2 = new StringBuilder(j);
        int k = 0;
        while(k < j) 
        {
            char c = s.charAt(k);
            int l = s1.indexOf(c);
            if(l >= 0)
            {
                boolean flag1 = true;
                flag = flag1;
                if(l < i)
                {
                    s2.append(s3.charAt(l));
                    flag = flag1;
                }
            } else
            {
                s2.append(c);
            }
            k++;
        }
        if(flag)
            return s2.toString();
        else
            return s;
    }

    public static String replaceEach(String s, String as[], String as1[])
    {
        return replaceEach(s, as, as1, false, 0);
    }

    private static String replaceEach(String s, String as[], String as1[], boolean flag, int i)
    {
        int j;
        int k;
        boolean aflag[];
        byte byte0;
        int i1;
        while(s == null || s.length() == 0 || as == null || as.length == 0 || as1 == null || as1.length == 0) 
            return s;
        if(i < 0)
            throw new IllegalStateException("Aborting to protect against StackOverflowError - output of one loop is the input of another");
        j = as.length;
        k = as1.length;
        if(j != k)
            throw new IllegalArgumentException((new StringBuilder()).append("Search and Replace array lengths don't match: ").append(j).append(" vs ").append(k).toString());
        aflag = new boolean[j];
        k = -1;
        byte0 = -1;
        i1 = 0;
_L2:
        int j1;
        int k1;
        if(i1 >= j)
            break MISSING_BLOCK_LABEL_267;
        j1 = byte0;
        k1 = k;
        if(!aflag[i1])
        {
            if(as[i1] != null)
                break; /* Loop/switch isn't completed */
            k1 = k;
            j1 = byte0;
        }
_L3:
        i1++;
        byte0 = j1;
        k = k1;
        if(true) goto _L2; else goto _L1
_L1:
        int l1;
        j1 = byte0;
        k1 = k;
        if(as[i1].length() != 0)
        {
            j1 = byte0;
            k1 = k;
            if(as1[i1] != null)
            {
label0:
                {
                    l1 = s.indexOf(as[i1]);
                    if(l1 != -1)
                        break label0;
                    aflag[i1] = true;
                    j1 = byte0;
                    k1 = k;
                }
            }
        }
          goto _L3
        if(k == -1) goto _L5; else goto _L4
_L4:
        j1 = byte0;
        k1 = k;
        if(l1 >= k) goto _L3; else goto _L5
_L5:
        k1 = l1;
        j1 = i1;
          goto _L3
        StringBuilder stringbuilder;
        if(k == -1)
            return s;
        l1 = 0;
        k1 = 0;
        j1 = 0;
        while(j1 < as.length) 
        {
            i1 = k1;
            if(as[j1] != null)
                if(as1[j1] == null)
                {
                    i1 = k1;
                } else
                {
                    int i2 = as1[j1].length() - as[j1].length();
                    i1 = k1;
                    if(i2 > 0)
                        i1 = k1 + i2 * 3;
                }
            j1++;
            k1 = i1;
        }
        i1 = Math.min(k1, s.length() / 5);
        stringbuilder = new StringBuilder(s.length() + i1);
        k1 = k;
        k = l1;
        l1 = byte0;
_L7:
        int j2;
        if(k1 == -1)
            break MISSING_BLOCK_LABEL_628;
        for(; k < k1; k++)
            stringbuilder.append(s.charAt(k));

        stringbuilder.append(as1[l1]);
        j2 = k1 + as[l1].length();
        byte0 = -1;
        j1 = -1;
        i1 = 0;
_L8:
        l1 = j1;
        k = j2;
        k1 = byte0;
        if(i1 >= j) goto _L7; else goto _L6
_L6:
        k = j1;
        k1 = byte0;
        if(!aflag[i1])
            if(as[i1] == null)
            {
                k1 = byte0;
                k = j1;
            } else
            {
                k = j1;
                k1 = byte0;
                if(as[i1].length() != 0)
                {
                    k = j1;
                    k1 = byte0;
                    if(as1[i1] != null)
                    {
label1:
                        {
                            l1 = s.indexOf(as[i1], j2);
                            if(l1 != -1)
                                break label1;
                            aflag[i1] = true;
                            k = j1;
                            k1 = byte0;
                        }
                    }
                }
            }
_L11:
        i1++;
        j1 = k;
        byte0 = k1;
          goto _L8
        if(byte0 == -1) goto _L10; else goto _L9
_L9:
        k = j1;
        k1 = byte0;
        if(l1 >= byte0) goto _L11; else goto _L10
_L10:
        k1 = l1;
        k = i1;
          goto _L11
        for(int l = s.length(); k < l; k++)
            stringbuilder.append(s.charAt(k));

        s = stringbuilder.toString();
        if(!flag)
            return s;
        else
            return replaceEach(s, as, as1, flag, i - 1);
    }

    public static String replaceEachRepeatedly(String s, String as[], String as1[])
    {
        int i;
        if(as == null)
            i = 0;
        else
            i = as.length;
        return replaceEach(s, as, as1, true, i);
    }

    public static String replaceOnce(String s, String s1, String s2)
    {
        return replace(s, s1, s2, 1);
    }

    public static String reverse(String s)
    {
        if(s == null)
            return null;
        else
            return (new StringBuilder(s)).reverse().toString();
    }

    public static String reverseDelimited(String s, char c)
    {
        if(s == null)
        {
            return null;
        } else
        {
            s = split(s, c);
            ArrayUtils.reverse(s);
            return join(s, c);
        }
    }

    public static String right(String s, int i)
    {
        if(s == null)
            return null;
        if(i < 0)
            return "";
        if(s.length() <= i)
            return s;
        else
            return s.substring(s.length() - i);
    }

    public static String rightPad(String s, int i)
    {
        return rightPad(s, i, ' ');
    }

    public static String rightPad(String s, int i, char c)
    {
        if(s == null)
            return null;
        int j = i - s.length();
        if(j <= 0)
            return s;
        if(j > 8192)
            return rightPad(s, i, String.valueOf(c));
        else
            return s.concat(repeat(c, j));
    }

    public static String rightPad(String s, int i, String s1)
    {
        if(s == null)
            return null;
        String s2 = s1;
        if(isEmpty(s1))
            s2 = " ";
        int j = s2.length();
        int k = i - s.length();
        if(k <= 0)
            return s;
        if(j == 1 && k <= 8192)
            return rightPad(s, i, s2.charAt(0));
        if(k == j)
            return s.concat(s2);
        if(k < j)
            return s.concat(s2.substring(0, k));
        s1 = new char[k];
        char ac[] = s2.toCharArray();
        for(i = 0; i < k; i++)
            s1[i] = ac[i % j];

        return s.concat(new String(s1));
    }

    public static String[] split(String s)
    {
        return split(s, null, -1);
    }

    public static String[] split(String s, char c)
    {
        return splitWorker(s, c, false);
    }

    public static String[] split(String s, String s1)
    {
        return splitWorker(s, s1, -1, false);
    }

    public static String[] split(String s, String s1, int i)
    {
        return splitWorker(s, s1, i, false);
    }

    public static String[] splitByCharacterType(String s)
    {
        return splitByCharacterType(s, false);
    }

    private static String[] splitByCharacterType(String s, boolean flag)
    {
        if(s == null)
            return null;
        if(s.length() == 0)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        s = s.toCharArray();
        ArrayList arraylist = new ArrayList();
        int i = 0;
        int j = Character.getType(s[0]);
        int k = 1;
        while(k < s.length) 
        {
            int l = Character.getType(s[k]);
            if(l == j)
            {
                l = j;
            } else
            {
                if(flag && l == 2 && j == 1)
                {
                    int i1 = k - 1;
                    j = i;
                    if(i1 != i)
                    {
                        arraylist.add(new String(s, i, i1 - i));
                        j = i1;
                    }
                } else
                {
                    arraylist.add(new String(s, i, k - i));
                    j = k;
                }
                i = j;
            }
            k++;
            j = l;
        }
        arraylist.add(new String(s, i, s.length - i));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public static String[] splitByCharacterTypeCamelCase(String s)
    {
        return splitByCharacterType(s, true);
    }

    public static String[] splitByWholeSeparator(String s, String s1)
    {
        return splitByWholeSeparatorWorker(s, s1, -1, false);
    }

    public static String[] splitByWholeSeparator(String s, String s1, int i)
    {
        return splitByWholeSeparatorWorker(s, s1, i, false);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String s, String s1)
    {
        return splitByWholeSeparatorWorker(s, s1, -1, true);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String s, String s1, int i)
    {
        return splitByWholeSeparatorWorker(s, s1, i, true);
    }

    private static String[] splitByWholeSeparatorWorker(String s, String s1, int i, boolean flag)
    {
        if(s == null)
            return null;
        int j = s.length();
        if(j == 0)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        if(s1 == null || "".equals(s1))
            return splitWorker(s, null, i, flag);
        int k = s1.length();
        ArrayList arraylist = new ArrayList();
        int l = 0;
        int i1 = 0;
        for(int j1 = 0; j1 < j;)
        {
            int k1 = s.indexOf(s1, i1);
            if(k1 > -1)
            {
                if(k1 > i1)
                {
                    if(++l == i)
                    {
                        j1 = j;
                        arraylist.add(s.substring(i1));
                    } else
                    {
                        arraylist.add(s.substring(i1, k1));
                        i1 = k1 + k;
                        j1 = k1;
                    }
                } else
                {
                    j1 = k1;
                    int l1 = l;
                    if(flag)
                    {
                        l1 = l + 1;
                        if(l1 == i)
                        {
                            j1 = j;
                            arraylist.add(s.substring(i1));
                        } else
                        {
                            arraylist.add("");
                            j1 = k1;
                        }
                    }
                    i1 = j1 + k;
                    l = l1;
                }
            } else
            {
                arraylist.add(s.substring(i1));
                j1 = j;
            }
        }

        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public static String[] splitPreserveAllTokens(String s)
    {
        return splitWorker(s, null, -1, true);
    }

    public static String[] splitPreserveAllTokens(String s, char c)
    {
        return splitWorker(s, c, true);
    }

    public static String[] splitPreserveAllTokens(String s, String s1)
    {
        return splitWorker(s, s1, -1, true);
    }

    public static String[] splitPreserveAllTokens(String s, String s1, int i)
    {
        return splitWorker(s, s1, i, true);
    }

    private static String[] splitWorker(String s, char c, boolean flag)
    {
        if(s == null)
            return null;
        int i = s.length();
        if(i == 0)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        ArrayList arraylist = new ArrayList();
        int j = 0;
        int k = 0;
        boolean flag1 = false;
        boolean flag2 = false;
        while(j < i) 
            if(s.charAt(j) == c)
            {
                if(flag1 || flag)
                {
                    arraylist.add(s.substring(k, j));
                    flag1 = false;
                    flag2 = true;
                }
                k = ++j;
            } else
            {
                flag2 = false;
                flag1 = true;
                j++;
            }
        if(flag1 || flag && flag2)
            arraylist.add(s.substring(k, j));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private static String[] splitWorker(String s, String s1, int i, boolean flag)
    {
        int j;
        ArrayList arraylist;
        int k;
        boolean flag1;
        int l;
        int i1;
        int j1;
        boolean flag3;
        int k1;
        int l1;
        int i2;
        int j2;
        char c;
        int k2;
        if(s == null)
            return null;
        j = s.length();
        if(j == 0)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        arraylist = new ArrayList();
        k = 0;
        flag1 = false;
        l = 0;
        i1 = 0;
        j1 = 0;
        flag3 = false;
        k1 = 0;
        l1 = 0;
        i2 = 0;
        j2 = 0;
        c = '\0';
        k2 = 0;
        if(s1 != null) goto _L2; else goto _L1
_L1:
        l1 = 1;
        j1 = ((flag3) ? 1 : 0);
        j2 = i2;
        k = l;
        do
        {
            l = k;
            k1 = k2;
            i2 = j2;
            i1 = l1;
            i1 = j1;
            if(k >= j)
                break;
            if(Character.isWhitespace(s.charAt(k)))
            {
                if(j2 != 0 || flag)
                {
                    k2 = 1;
                    j2 = l1 + 1;
                    if(l1 == i)
                    {
                        k = j;
                        k2 = 0;
                    }
                    arraylist.add(s.substring(j1, k));
                    j1 = 0;
                    l1 = j2;
                    j2 = j1;
                }
                j1 = ++k;
            } else
            {
                k2 = 0;
                j2 = 1;
                k++;
            }
        } while(true);
          goto _L3
_L2:
        if(s1.length() != 1) goto _L5; else goto _L4
_L4:
        c = s1.charAt(0);
        l1 = 1;
        j1 = i1;
        k2 = k1;
_L7:
        l = k;
        k1 = j2;
        i2 = k2;
        i1 = l1;
        i1 = j1;
        if(k >= j) goto _L3; else goto _L6
_L6:
        if(s.charAt(k) == c)
        {
            if(k2 != 0 || flag)
            {
                k2 = 1;
                j2 = l1 + 1;
                if(l1 == i)
                {
                    k = j;
                    k2 = 0;
                }
                arraylist.add(s.substring(j1, k));
                j1 = 0;
                l1 = j2;
                j2 = k2;
            } else
            {
                j1 = k2;
            }
            l = ++k;
            k2 = j1;
            j1 = l;
        } else
        {
            j2 = 0;
            k2 = 1;
            k++;
        }
        if(true) goto _L7; else goto _L3
_L9:
        l = k;
        k1 = k2;
        i2 = j2;
        i1 = l1;
        i1 = j1;
        if(k < j)
        {
            if(s1.indexOf(s.charAt(k)) >= 0)
            {
                if(j2 != 0 || flag)
                {
                    k2 = 1;
                    j2 = l1 + 1;
                    if(l1 == i)
                    {
                        k = j;
                        k2 = 0;
                    }
                    arraylist.add(s.substring(j1, k));
                    j1 = 0;
                    l1 = j2;
                    j2 = j1;
                }
                j1 = ++k;
            } else
            {
                k2 = 0;
                j2 = 1;
                k++;
            }
            continue; /* Loop/switch isn't completed */
        }
_L3:
        if(i2 != 0 || flag && k1 != 0)
            arraylist.add(s.substring(i1, l));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
_L5:
        boolean flag2 = true;
        k = ((flag1) ? 1 : 0);
        k2 = c;
        j2 = l1;
        l1 = ((flag2) ? 1 : 0);
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static boolean startsWith(CharSequence charsequence, CharSequence charsequence1)
    {
        return startsWith(charsequence, charsequence1, false);
    }

    private static boolean startsWith(CharSequence charsequence, CharSequence charsequence1, boolean flag)
    {
        boolean flag1 = false;
        if(charsequence == null || charsequence1 == null)
        {
            flag = flag1;
            if(charsequence == null)
            {
                flag = flag1;
                if(charsequence1 == null)
                    flag = true;
            }
            return flag;
        }
        if(charsequence1.length() > charsequence.length())
            return false;
        else
            return CharSequenceUtils.regionMatches(charsequence, flag, 0, charsequence1, 0, charsequence1.length());
    }

    public static transient boolean startsWithAny(CharSequence charsequence, CharSequence acharsequence[])
    {
        if(isEmpty(charsequence) || ArrayUtils.isEmpty(acharsequence))
            return false;
        int i = acharsequence.length;
        for(int j = 0; j < i; j++)
            if(startsWith(charsequence, acharsequence[j]))
                return true;

        return false;
    }

    public static boolean startsWithIgnoreCase(CharSequence charsequence, CharSequence charsequence1)
    {
        return startsWith(charsequence, charsequence1, true);
    }

    public static String strip(String s)
    {
        return strip(s, null);
    }

    public static String strip(String s, String s1)
    {
        if(isEmpty(s))
            return s;
        else
            return stripEnd(stripStart(s, s1), s1);
    }

    public static String stripAccents(String s)
    {
        if(s == null)
            return null;
        if(InitStripAccents._2D_get1() == null) goto _L2; else goto _L1
_L1:
        s = removeAccentsJava6(s);
_L4:
        return s;
_L2:
        if(InitStripAccents._2D_get4() == null)
            break; /* Loop/switch isn't completed */
        s = removeAccentsSUN(s);
        if(true) goto _L4; else goto _L3
_L3:
        try
        {
            s = JVM INSTR new #590 <Class UnsupportedOperationException>;
            StringBuilder stringbuilder = JVM INSTR new #54  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s.UnsupportedOperationException(stringbuilder.append("The stripAccents(CharSequence) method requires at least Java6, but got: ").append(InitStripAccents._2D_get0()).append("; or a Sun JVM: ").append(InitStripAccents._2D_get5()).toString());
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("IllegalArgumentException occurred", s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("IllegalAccessException occurred", s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("InvocationTargetException occurred", s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("SecurityException occurred", s);
        }
    }

    public static transient String[] stripAll(String as[])
    {
        return stripAll(as, null);
    }

    public static String[] stripAll(String as[], String s)
    {
        int i;
label0:
        {
            if(as != null)
            {
                i = as.length;
                if(i != 0)
                    break label0;
            }
            return as;
        }
        String as1[] = new String[i];
        for(int j = 0; j < i; j++)
            as1[j] = strip(as[j], s);

        return as1;
    }

    public static String stripEnd(String s, String s1)
    {
        int i;
label0:
        {
            if(s != null)
            {
                i = s.length();
                if(i != 0)
                    break label0;
            }
            return s;
        }
        int j;
        if(s1 == null)
        {
            do
            {
                j = i;
                if(i == 0)
                    break;
                j = i;
                if(!Character.isWhitespace(s.charAt(i - 1)))
                    break;
                i--;
            } while(true);
        } else
        {
            if(s1.length() == 0)
                return s;
            do
            {
                j = i;
                if(i == 0)
                    break;
                j = i;
                if(s1.indexOf(s.charAt(i - 1)) == -1)
                    break;
                i--;
            } while(true);
        }
        return s.substring(0, j);
    }

    public static String stripStart(String s, String s1)
    {
        int i;
label0:
        {
            if(s != null)
            {
                i = s.length();
                if(i != 0)
                    break label0;
            }
            return s;
        }
        int j = 0;
        int k = 0;
        if(s1 == null)
        {
            do
            {
                j = k;
                if(k == i)
                    break;
                j = k;
                if(!Character.isWhitespace(s.charAt(k)))
                    break;
                k++;
            } while(true);
        } else
        {
            int l = j;
            if(s1.length() == 0)
                return s;
            do
            {
                j = l;
                if(l == i)
                    break;
                j = l;
                if(s1.indexOf(s.charAt(l)) == -1)
                    break;
                l++;
            } while(true);
        }
        return s.substring(j);
    }

    public static String stripToEmpty(String s)
    {
        if(s == null)
            s = "";
        else
            s = strip(s, null);
        return s;
    }

    public static String stripToNull(String s)
    {
        if(s == null)
            return null;
        String s1 = strip(s, null);
        s = s1;
        if(s1.length() == 0)
            s = null;
        return s;
    }

    public static String substring(String s, int i)
    {
        if(s == null)
            return null;
        int j = i;
        if(i < 0)
            j = i + s.length();
        i = j;
        if(j < 0)
            i = 0;
        if(i > s.length())
            return "";
        else
            return s.substring(i);
    }

    public static String substring(String s, int i, int j)
    {
        if(s == null)
            return null;
        int k = j;
        if(j < 0)
            k = j + s.length();
        j = i;
        if(i < 0)
            j = i + s.length();
        i = k;
        if(k > s.length())
            i = s.length();
        if(j > i)
            return "";
        k = j;
        if(j < 0)
            k = 0;
        j = i;
        if(i < 0)
            j = 0;
        return s.substring(k, j);
    }

    public static String substringAfter(String s, String s1)
    {
        if(isEmpty(s))
            return s;
        if(s1 == null)
            return "";
        int i = s.indexOf(s1);
        if(i == -1)
            return "";
        else
            return s.substring(s1.length() + i);
    }

    public static String substringAfterLast(String s, String s1)
    {
        if(isEmpty(s))
            return s;
        if(isEmpty(s1))
            return "";
        int i = s.lastIndexOf(s1);
        if(i == -1 || i == s.length() - s1.length())
            return "";
        else
            return s.substring(s1.length() + i);
    }

    public static String substringBefore(String s, String s1)
    {
        if(isEmpty(s) || s1 == null)
            return s;
        if(s1.length() == 0)
            return "";
        int i = s.indexOf(s1);
        if(i == -1)
            return s;
        else
            return s.substring(0, i);
    }

    public static String substringBeforeLast(String s, String s1)
    {
        if(isEmpty(s) || isEmpty(s1))
            return s;
        int i = s.lastIndexOf(s1);
        if(i == -1)
            return s;
        else
            return s.substring(0, i);
    }

    public static String substringBetween(String s, String s1)
    {
        return substringBetween(s, s1, s1);
    }

    public static String substringBetween(String s, String s1, String s2)
    {
        while(s == null || s1 == null || s2 == null) 
            return null;
        int i = s.indexOf(s1);
        if(i != -1)
        {
            int j = s.indexOf(s2, s1.length() + i);
            if(j != -1)
                return s.substring(s1.length() + i, j);
        }
        return null;
    }

    public static String[] substringsBetween(String s, String s1, String s2)
    {
        int i;
        int j;
        int k;
        ArrayList arraylist;
        int l;
        if(s == null || isEmpty(s1) || isEmpty(s2))
            return null;
        i = s.length();
        if(i == 0)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        j = s2.length();
        k = s1.length();
        arraylist = new ArrayList();
        l = 0;
_L6:
        if(l >= i - j) goto _L2; else goto _L1
_L1:
        l = s.indexOf(s1, l);
        if(l >= 0) goto _L3; else goto _L2
_L2:
        int i1;
        if(arraylist.isEmpty())
            return null;
        else
            return (String[])arraylist.toArray(new String[arraylist.size()]);
_L3:
        i1 = l + k;
        l = s.indexOf(s2, i1);
        if(l < 0) goto _L2; else goto _L4
_L4:
        arraylist.add(s.substring(i1, l));
        l += j;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static String swapCase(String s)
    {
        if(isEmpty(s))
            return s;
        s = s.toCharArray();
        int i = 0;
        while(i < s.length) 
        {
            char c = s[i];
            if(Character.isUpperCase(c))
                s[i] = Character.toLowerCase(c);
            else
            if(Character.isTitleCase(c))
                s[i] = Character.toLowerCase(c);
            else
            if(Character.isLowerCase(c))
                s[i] = Character.toUpperCase(c);
            i++;
        }
        return new String(s);
    }

    public static String toString(byte abyte0[], String s)
        throws UnsupportedEncodingException
    {
        if(s == null)
            abyte0 = new String(abyte0);
        else
            abyte0 = new String(abyte0, s);
        return abyte0;
    }

    public static String trim(String s)
    {
        Object obj = null;
        if(s == null)
            s = obj;
        else
            s = s.trim();
        return s;
    }

    public static String trimToEmpty(String s)
    {
        if(s == null)
            s = "";
        else
            s = s.trim();
        return s;
    }

    public static String trimToNull(String s)
    {
        String s1 = trim(s);
        s = s1;
        if(isEmpty(s1))
            s = null;
        return s;
    }

    public static String uncapitalize(String s)
    {
        int i;
label0:
        {
            if(s != null)
            {
                i = s.length();
                if(i != 0)
                    break label0;
            }
            return s;
        }
        return (new StringBuilder(i)).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String upperCase(String s)
    {
        if(s == null)
            return null;
        else
            return s.toUpperCase();
    }

    public static String upperCase(String s, Locale locale)
    {
        if(s == null)
            return null;
        else
            return s.toUpperCase(locale);
    }

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;
    private static final Pattern WHITESPACE_BLOCK = Pattern.compile("\\s+");

}
