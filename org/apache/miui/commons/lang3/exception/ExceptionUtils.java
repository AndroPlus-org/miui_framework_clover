// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.exception;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.apache.miui.commons.lang3.*;

public class ExceptionUtils
{

    public ExceptionUtils()
    {
    }

    public static Throwable getCause(Throwable throwable)
    {
        return getCause(throwable, CAUSE_METHOD_NAMES);
    }

    public static Throwable getCause(Throwable throwable, String as[])
    {
        if(throwable == null)
            return null;
        String as1[] = as;
        if(as == null)
            as1 = CAUSE_METHOD_NAMES;
        int i = 0;
        for(int j = as1.length; i < j; i++)
        {
            as = as1[i];
            if(as == null)
                continue;
            as = getCauseUsingMethodName(throwable, as);
            if(as != null)
                return as;
        }

        return null;
    }

    private static Throwable getCauseUsingMethodName(Throwable throwable, String s)
    {
        Object obj = null;
        try
        {
            s = throwable.getClass().getMethod(s, new Class[0]);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = obj;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = obj;
        }
        if(s == null || !java/lang/Throwable.isAssignableFrom(s.getReturnType()))
            break MISSING_BLOCK_LABEL_47;
        throwable = (Throwable)s.invoke(throwable, new Object[0]);
        return throwable;
        throwable;
_L2:
        return null;
        throwable;
        continue; /* Loop/switch isn't completed */
        throwable;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String[] getDefaultCauseMethodNames()
    {
        return (String[])ArrayUtils.clone(CAUSE_METHOD_NAMES);
    }

    public static String getMessage(Throwable throwable)
    {
        if(throwable == null)
        {
            return "";
        } else
        {
            String s = ClassUtils.getShortClassName(throwable, null);
            throwable = throwable.getMessage();
            return (new StringBuilder()).append(s).append(": ").append(StringUtils.defaultString(throwable)).toString();
        }
    }

    public static Throwable getRootCause(Throwable throwable)
    {
        throwable = getThrowableList(throwable);
        if(throwable.size() < 2)
            throwable = null;
        else
            throwable = (Throwable)throwable.get(throwable.size() - 1);
        return throwable;
    }

    public static String getRootCauseMessage(Throwable throwable)
    {
        Throwable throwable1 = getRootCause(throwable);
        Throwable throwable2 = throwable1;
        if(throwable1 == null)
            throwable2 = throwable;
        return getMessage(throwable2);
    }

    public static String[] getRootCauseStackTrace(Throwable throwable)
    {
        ArrayList arraylist;
        if(throwable == null)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        Throwable athrowable[] = getThrowables(throwable);
        int i = athrowable.length;
        arraylist = new ArrayList();
        throwable = getStackFrameList(athrowable[i - 1]);
        int j = i;
        do
        {
label0:
            {
                Throwable throwable1 = throwable;
                int k = j - 1;
                if(k < 0)
                    break label0;
                throwable = throwable1;
                if(k != 0)
                {
                    throwable = getStackFrameList(athrowable[k - 1]);
                    removeCommonFrames(throwable1, throwable);
                }
                Throwable throwable2 = throwable;
                int l;
                if(k == i - 1)
                    arraylist.add(athrowable[k].toString());
                else
                    arraylist.add((new StringBuilder()).append(" [wrapped] ").append(athrowable[k].toString()).toString());
                l = 0;
                do
                {
                    j = k;
                    throwable = throwable2;
                    if(l >= throwable1.size())
                        break;
                    arraylist.add((String)throwable1.get(l));
                    l++;
                } while(true);
            }
        } while(true);
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    static List getStackFrameList(Throwable throwable)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(getStackTrace(throwable), SystemUtils.LINE_SEPARATOR);
        throwable = new ArrayList();
        boolean flag = false;
label0:
        do
            do
            {
                if(!stringtokenizer.hasMoreTokens())
                    break label0;
                String s = stringtokenizer.nextToken();
                int i = s.indexOf("at");
                if(i == -1 || s.substring(0, i).trim().length() != 0)
                    continue label0;
                flag = true;
                throwable.add(s);
            } while(true);
        while(!flag);
        return throwable;
    }

    static String[] getStackFrames(String s)
    {
        s = new StringTokenizer(s, SystemUtils.LINE_SEPARATOR);
        ArrayList arraylist = new ArrayList();
        for(; s.hasMoreTokens(); arraylist.add(s.nextToken()));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public static String[] getStackFrames(Throwable throwable)
    {
        if(throwable == null)
            return ArrayUtils.EMPTY_STRING_ARRAY;
        else
            return getStackFrames(getStackTrace(throwable));
    }

    public static String getStackTrace(Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringwriter, true));
        return stringwriter.getBuffer().toString();
    }

    public static int getThrowableCount(Throwable throwable)
    {
        return getThrowableList(throwable).size();
    }

    public static List getThrowableList(Throwable throwable)
    {
        ArrayList arraylist;
        for(arraylist = new ArrayList(); throwable != null && !arraylist.contains(throwable); throwable = getCause(throwable))
            arraylist.add(throwable);

        return arraylist;
    }

    public static Throwable[] getThrowables(Throwable throwable)
    {
        throwable = getThrowableList(throwable);
        return (Throwable[])throwable.toArray(new Throwable[throwable.size()]);
    }

    private static int indexOf(Throwable throwable, Class class1, int i, boolean flag)
    {
        if(throwable == null || class1 == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        throwable = getThrowables(throwable);
        if(j >= throwable.length)
            return -1;
        if(flag)
            for(; j < throwable.length; j++)
                if(class1.isAssignableFrom(throwable[j].getClass()))
                    return j;

        else
            for(; j < throwable.length; j++)
                if(class1.equals(throwable[j].getClass()))
                    return j;

        return -1;
    }

    public static int indexOfThrowable(Throwable throwable, Class class1)
    {
        return indexOf(throwable, class1, 0, false);
    }

    public static int indexOfThrowable(Throwable throwable, Class class1, int i)
    {
        return indexOf(throwable, class1, i, false);
    }

    public static int indexOfType(Throwable throwable, Class class1)
    {
        return indexOf(throwable, class1, 0, true);
    }

    public static int indexOfType(Throwable throwable, Class class1, int i)
    {
        return indexOf(throwable, class1, i, true);
    }

    public static void printRootCauseStackTrace(Throwable throwable)
    {
        printRootCauseStackTrace(throwable, System.err);
    }

    public static void printRootCauseStackTrace(Throwable throwable, PrintStream printstream)
    {
        if(throwable == null)
            return;
        if(printstream == null)
            throw new IllegalArgumentException("The PrintStream must not be null");
        throwable = getRootCauseStackTrace(throwable);
        int i = 0;
        for(int j = throwable.length; i < j; i++)
            printstream.println(throwable[i]);

        printstream.flush();
    }

    public static void printRootCauseStackTrace(Throwable throwable, PrintWriter printwriter)
    {
        if(throwable == null)
            return;
        if(printwriter == null)
            throw new IllegalArgumentException("The PrintWriter must not be null");
        throwable = getRootCauseStackTrace(throwable);
        int i = 0;
        for(int j = throwable.length; i < j; i++)
            printwriter.println(throwable[i]);

        printwriter.flush();
    }

    public static void removeCommonFrames(List list, List list1)
    {
        if(list == null || list1 == null)
            throw new IllegalArgumentException("The List must not be null");
        int i = list.size() - 1;
        for(int j = list1.size() - 1; i >= 0 && j >= 0; j--)
        {
            if(((String)list.get(i)).equals((String)list1.get(j)))
                list.remove(i);
            i--;
        }

    }

    private static final String CAUSE_METHOD_NAMES[] = {
        "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", 
        "getLinkedCause", "getThrowable"
    };
    static final String WRAPPED_MARKER = " [wrapped] ";

}
