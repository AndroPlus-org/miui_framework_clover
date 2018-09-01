// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MessageUtils
{
    public static class DuplicateConstantError extends Error
    {

        private DuplicateConstantError()
        {
        }

        public DuplicateConstantError(String s, String s1, int i)
        {
            super(String.format("Duplicate constant value: both %s and %s = %d", new Object[] {
                s, s1, Integer.valueOf(i)
            }));
        }
    }


    public MessageUtils()
    {
    }

    public static SparseArray findMessageNames(Class aclass[])
    {
        return findMessageNames(aclass, DEFAULT_PREFIXES);
    }

    public static SparseArray findMessageNames(Class aclass[], String as[])
    {
        SparseArray sparsearray;
        int i;
        int j;
        sparsearray = new SparseArray();
        i = aclass.length;
        j = 0;
_L11:
        Class class1;
        Object obj;
        if(j >= i)
            break MISSING_BLOCK_LABEL_249;
        class1 = aclass[j];
        obj = class1.getName();
        Field afield[] = class1.getDeclaredFields();
        int k;
        int l;
        k = afield.length;
        l = 0;
_L5:
        if(l >= k) goto _L2; else goto _L1
_L1:
        int i1;
        obj = afield[l];
        i1 = ((Field) (obj)).getModifiers();
        if(!(Modifier.isStatic(i1) ^ true | Modifier.isFinal(i1) ^ true)) goto _L4; else goto _L3
_L3:
        l++;
          goto _L5
        SecurityException securityexception;
        securityexception;
        Log.e(TAG, (new StringBuilder()).append("Can't list fields of class ").append(((String) (obj))).toString());
_L2:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        String s;
        int j1;
        s = ((Field) (obj)).getName();
        i1 = 0;
        j1 = as.length;
_L9:
        if(i1 >= j1) goto _L3; else goto _L6
_L6:
        if(s.startsWith(as[i1])) goto _L8; else goto _L7
_L7:
        i1++;
          goto _L9
_L8:
        ((Field) (obj)).setAccessible(true);
        int k1 = ((Field) (obj)).getInt(null);
        String s1 = (String)sparsearray.get(k1);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_238;
        Object obj2;
        if(s1.equals(s) ^ true)
        {
            DuplicateConstantError duplicateconstanterror = JVM INSTR new #6   <Class MessageUtils$DuplicateConstantError>;
            duplicateconstanterror.DuplicateConstantError(s, s1, k1);
            throw duplicateconstanterror;
        }
        break MISSING_BLOCK_LABEL_238;
        Object obj1;
        obj1;
          goto _L3
        try
        {
            sparsearray.put(k1, s);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
          goto _L7
        return sparsearray;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private static final boolean DBG = false;
    public static final String DEFAULT_PREFIXES[] = {
        "CMD_", "EVENT_"
    };
    private static final String TAG = com/android/internal/util/MessageUtils.getSimpleName();

}
