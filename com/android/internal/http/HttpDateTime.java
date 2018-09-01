// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.http;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpDateTime
{
    private static class TimeOfDay
    {

        int hour;
        int minute;
        int second;

        TimeOfDay(int i, int j, int k)
        {
            hour = i;
            minute = j;
            second = k;
        }
    }


    public HttpDateTime()
    {
    }

    private static int getDate(String s)
    {
        if(s.length() == 2)
            return (s.charAt(0) - 48) * 10 + (s.charAt(1) - 48);
        else
            return s.charAt(0) - 48;
    }

    private static int getMonth(String s)
    {
        switch((Character.toLowerCase(s.charAt(0)) + Character.toLowerCase(s.charAt(1)) + Character.toLowerCase(s.charAt(2))) - 291)
        {
        default:
            throw new IllegalArgumentException();

        case 22: // '\026'
            return 0;

        case 10: // '\n'
            return 1;

        case 29: // '\035'
            return 2;

        case 32: // ' '
            return 3;

        case 36: // '$'
            return 4;

        case 42: // '*'
            return 5;

        case 40: // '('
            return 6;

        case 26: // '\032'
            return 7;

        case 37: // '%'
            return 8;

        case 35: // '#'
            return 9;

        case 48: // '0'
            return 10;

        case 9: // '\t'
            return 11;
        }
    }

    private static TimeOfDay getTime(String s)
    {
        int i = 1;
        int j = s.charAt(0) - 48;
        int k = j;
        if(s.charAt(1) != ':')
        {
            k = j * 10 + (s.charAt(1) - 48);
            i = 1 + 1;
        }
        int l = ++i + 1;
        i = s.charAt(i);
        j = s.charAt(l);
        int i1 = l + 1 + 1;
        l = i1 + 1;
        return new TimeOfDay(k, (i - 48) * 10 + (j - 48), (s.charAt(i1) - 48) * 10 + (s.charAt(l) - 48));
    }

    private static int getYear(String s)
    {
        if(s.length() == 2)
        {
            int i = (s.charAt(0) - 48) * 10 + (s.charAt(1) - 48);
            if(i >= 70)
                return i + 1900;
            else
                return i + 2000;
        }
        if(s.length() == 3)
            return (s.charAt(0) - 48) * 100 + (s.charAt(1) - 48) * 10 + (s.charAt(2) - 48) + 1900;
        if(s.length() == 4)
            return (s.charAt(0) - 48) * 1000 + (s.charAt(1) - 48) * 100 + (s.charAt(2) - 48) * 10 + (s.charAt(3) - 48);
        else
            return 1970;
    }

    public static long parse(String s)
        throws IllegalArgumentException
    {
        Object obj = HTTP_DATE_RFC_PATTERN.matcher(s);
        int i;
        int j;
        int k;
        int l;
        if(((Matcher) (obj)).find())
        {
            i = getDate(((Matcher) (obj)).group(1));
            j = getMonth(((Matcher) (obj)).group(2));
            k = getYear(((Matcher) (obj)).group(3));
            s = getTime(((Matcher) (obj)).group(4));
        } else
        {
            Matcher matcher = HTTP_DATE_ANSIC_PATTERN.matcher(s);
            if(matcher.find())
            {
                j = getMonth(matcher.group(1));
                i = getDate(matcher.group(2));
                s = getTime(matcher.group(3));
                k = getYear(matcher.group(4));
            } else
            {
                throw new IllegalArgumentException();
            }
        }
        l = i;
        i = j;
        j = k;
        if(k >= 2038)
        {
            j = 2038;
            i = 0;
            l = 1;
        }
        obj = new Time("UTC");
        ((Time) (obj)).set(((TimeOfDay) (s)).second, ((TimeOfDay) (s)).minute, ((TimeOfDay) (s)).hour, l, i, j);
        return ((Time) (obj)).toMillis(false);
    }

    private static final Pattern HTTP_DATE_ANSIC_PATTERN = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");
    private static final String HTTP_DATE_ANSIC_REGEXP = "[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})";
    private static final Pattern HTTP_DATE_RFC_PATTERN = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    private static final String HTTP_DATE_RFC_REGEXP = "([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])";

}
