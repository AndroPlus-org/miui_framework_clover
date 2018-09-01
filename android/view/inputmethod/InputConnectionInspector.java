// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.os.Bundle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

// Referenced classes of package android.view.inputmethod:
//            BaseInputConnection, InputConnectionWrapper, InputContentInfo, CorrectionInfo, 
//            InputConnection

public final class InputConnectionInspector
{

    public InputConnectionInspector()
    {
    }

    public static int getMissingMethodFlags(InputConnection inputconnection)
    {
        if(inputconnection == null)
            return 0;
        if(inputconnection instanceof BaseInputConnection)
            return 0;
        if(inputconnection instanceof InputConnectionWrapper)
            return ((InputConnectionWrapper)inputconnection).getMissingMethodFlags();
        else
            return getMissingMethodFlagsInternal(inputconnection.getClass());
    }

    public static String getMissingMethodFlagsAsString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = true;
        if((i & 1) != 0)
        {
            stringbuilder.append("getSelectedText(int)");
            flag = false;
        }
        boolean flag1 = flag;
        if((i & 2) != 0)
        {
            if(!flag)
                stringbuilder.append(",");
            stringbuilder.append("setComposingRegion(int, int)");
            flag1 = false;
        }
        flag = flag1;
        if((i & 4) != 0)
        {
            if(!flag1)
                stringbuilder.append(",");
            stringbuilder.append("commitCorrection(CorrectionInfo)");
            flag = false;
        }
        flag1 = flag;
        if((i & 8) != 0)
        {
            if(!flag)
                stringbuilder.append(",");
            stringbuilder.append("requestCursorUpdate(int)");
            flag1 = false;
        }
        flag = flag1;
        if((i & 0x10) != 0)
        {
            if(!flag1)
                stringbuilder.append(",");
            stringbuilder.append("deleteSurroundingTextInCodePoints(int, int)");
            flag = false;
        }
        if((i & 0x20) != 0)
        {
            if(!flag)
                stringbuilder.append(",");
            stringbuilder.append("getHandler()");
        }
        if((i & 0x40) != 0)
        {
            if(!flag)
                stringbuilder.append(",");
            stringbuilder.append("closeConnection()");
        }
        if((i & 0x80) != 0)
        {
            if(!flag)
                stringbuilder.append(",");
            stringbuilder.append("commitContent(InputContentInfo, Bundle)");
        }
        return stringbuilder.toString();
    }

    public static int getMissingMethodFlagsInternal(Class class1)
    {
        Integer integer = (Integer)sMissingMethodsMap.get(class1);
        if(integer != null)
            return integer.intValue();
        int i = 0;
        if(!hasGetSelectedText(class1))
            i = 1;
        int j = i;
        if(!hasSetComposingRegion(class1))
            j = i | 2;
        int k = j;
        if(!hasCommitCorrection(class1))
            k = j | 4;
        i = k;
        if(!hasRequestCursorUpdate(class1))
            i = k | 8;
        j = i;
        if(!hasDeleteSurroundingTextInCodePoints(class1))
            j = i | 0x10;
        i = j;
        if(!hasGetHandler(class1))
            i = j | 0x20;
        j = i;
        if(!hasCloseConnection(class1))
            j = i | 0x40;
        i = j;
        if(!hasCommitContent(class1))
            i = j | 0x80;
        sMissingMethodsMap.put(class1, Integer.valueOf(i));
        return i;
    }

    private static boolean hasCloseConnection(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("closeConnection", new Class[0]).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasCommitContent(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("commitContent", new Class[] {
                android/view/inputmethod/InputContentInfo, Integer.TYPE, android/os/Bundle
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasCommitCorrection(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("commitCorrection", new Class[] {
                android/view/inputmethod/CorrectionInfo
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasDeleteSurroundingTextInCodePoints(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("deleteSurroundingTextInCodePoints", new Class[] {
                Integer.TYPE, Integer.TYPE
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasGetHandler(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("getHandler", new Class[0]).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasGetSelectedText(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("getSelectedText", new Class[] {
                Integer.TYPE
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasRequestCursorUpdate(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("requestCursorUpdates", new Class[] {
                Integer.TYPE
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static boolean hasSetComposingRegion(Class class1)
    {
        boolean flag;
        try
        {
            flag = Modifier.isAbstract(class1.getMethod("setComposingRegion", new Class[] {
                Integer.TYPE, Integer.TYPE
            }).getModifiers());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return flag ^ true;
    }

    private static final Map sMissingMethodsMap = Collections.synchronizedMap(new WeakHashMap());

}
