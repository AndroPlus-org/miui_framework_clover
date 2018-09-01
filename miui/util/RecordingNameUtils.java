// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class RecordingNameUtils
{

    public RecordingNameUtils()
    {
    }

    public static String generatCallRecordingName(Context context, List list, List list1, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getString(0x11080070));
        int i = stringbuilder.length();
        s = (new StringBuilder()).append("_").append(sDateFormat.format(Calendar.getInstance().getTime())).append(s).toString();
        int j = 50 - i - s.length();
        StringBuilder stringbuilder1 = new StringBuilder();
        i = 64;
        list1 = list1.iterator();
        for(char c = i; list1.hasNext(); c = i)
        {
            context = (String)list1.next();
            stringbuilder1.append(c);
            i = 95;
            stringbuilder1.append(context);
        }

        i = j - stringbuilder1.length();
        int k = 1;
        int l = 0;
        while(l < list.size() && i > 2) 
        {
            list1 = (String)list.get(l);
            context = list1;
            if(list1.length() > i - 2)
                context = list1.substring(0, i - 2);
            int i1;
            if(TextUtils.isEmpty(context))
            {
                k = stringbuilder1.indexOf("_", k + 1) + 1;
                i1 = i;
                i = k;
            } else
            {
                stringbuilder1.insert(k, (new StringBuilder()).append(context).append("(").toString());
                k = stringbuilder1.indexOf("_", k + 1);
                i1 = k;
                if(k < 0)
                    i1 = stringbuilder1.length();
                stringbuilder1.insert(i1, ')');
                i1 += 2;
                k = i - context.length() - 2;
                i = i1;
                i1 = k;
            }
            l++;
            k = i;
            i = i1;
        }
        if(j < stringbuilder1.length())
            i = j;
        else
            i = stringbuilder1.length();
        stringbuilder.append(stringbuilder1.substring(0, i));
        stringbuilder.append(s);
        return stringbuilder.toString().replace(',', 'p').replace("+", "00").replace('*', 's');
    }

    public static String generatFMRecordName(Context context, String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getString(0x11080076)).append('@').append(s).append('_').append(sDateFormat.format(Calendar.getInstance().getTime())).append(s1);
        return stringbuilder.toString();
    }

    public static String[] getCallRecordingCallerNumbers(Context context, String s)
    {
        s = getRecordingFileTitle(s);
        if(s != null)
            return getPhoneNumbers(context, s);
        else
            return null;
    }

    public static String getCallRecordingTitle(Context context, String s)
    {
        String s1 = getRecordingFileTitle(s);
        int i = s.indexOf('@');
        if(i != -1 && i != s.length() - 1 && s1 != null)
        {
            context = getCallers(context, s1);
            if(context == null)
                context = s1;
            return context;
        } else
        {
            return s1;
        }
    }

    private static String getCallerString(Context context, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(s == null || TextUtils.equals(s, ""))
        {
            stringbuilder.append(context.getString(0x11080095));
        } else
        {
            Cursor cursor = context.getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI, s), null, null, null, null);
            if(cursor == null)
                return s;
            HashSet hashset = new HashSet();
            int i = cursor.getColumnIndex("display_name");
            boolean flag = true;
            do
            {
                if(!cursor.moveToNext())
                    break;
                String s1 = cursor.getString(i);
                if(!hashset.contains(s1))
                {
                    if(!flag)
                    {
                        stringbuilder.append(' ');
                        stringbuilder.append(context.getString(0x11080071));
                        stringbuilder.append(' ');
                    }
                    stringbuilder.append(s1);
                    flag = false;
                    hashset.add(s1);
                }
            } while(true);
            cursor.close();
        }
        if(stringbuilder.length() == 0)
            context = s;
        else
            context = stringbuilder.toString();
        return context;
    }

    private static String getCallers(Context context, String s)
    {
        s = getPhoneNumbers(context, s);
        if(s.length == 0)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < s.length; i++)
        {
            stringbuilder.append(getCallerString(context, s[i]));
            if(i != s.length - 1)
                stringbuilder.append(", ");
        }

        return stringbuilder.toString();
    }

    public static String getFMRecordingTitle(String s)
    {
        return getRecordingFileTitle(s);
    }

    private static String getFileNameWithoutExtension(String s)
    {
        int i = s.lastIndexOf(File.separatorChar);
        if(i != -1)
            s = s.substring(i + 1);
        i = s.lastIndexOf('.');
        String s1 = s;
        if(i != -1)
            s1 = s.substring(0, i);
        return s1;
    }

    private static String[] getPhoneNumbers(Context context, String s)
    {
        context = s.replace('p', ',').replace('s', '*').split("_");
        for(int i = 0; i < context.length; i++)
        {
            int j = context[i].indexOf('(');
            int k = context[i].indexOf(')');
            if(j > 0 && k > j)
                context[i] = context[i].substring(j + 1, k);
        }

        return context;
    }

    public static long getRecordingCreationTime(Context context, String s)
    {
        long l = 0L;
        s = getFileNameWithoutExtension(s);
        int i = s.lastIndexOf('_');
        context = s;
        if(i != -1)
            context = s.substring(i + 1);
        long l1;
        try
        {
            l1 = sDateFormat.parse(context).getTime();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            l1 = l;
        }
        return l1;
    }

    public static String getRecordingFileTitle(String s)
    {
        int i = s.indexOf('@');
        if(i != -1 && i != s.length() - 1 && !s.substring(0, i).contains("_"))
        {
            int j = s.lastIndexOf('_');
            String s1 = s;
            if(j > i)
                s1 = s.substring(0, j);
            return s1.substring(i + 1);
        } else
        {
            return s;
        }
    }

    private static final int MAX_FILE_NAME_LENGTH = 50;
    private static final SimpleDateFormat sDateFormat;

    static 
    {
        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        sDateFormat.setTimeZone(Calendar.getInstance().getTimeZone());
    }
}
