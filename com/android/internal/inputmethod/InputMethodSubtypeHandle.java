// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.inputmethod;

import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import java.util.Objects;

public class InputMethodSubtypeHandle
{

    public InputMethodSubtypeHandle(InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        mInputMethodId = inputmethodinfo.getId();
        if(inputmethodsubtype != null)
            mSubtypeId = inputmethodsubtype.hashCode();
        else
            mSubtypeId = -1;
    }

    public InputMethodSubtypeHandle(String s, int i)
    {
        mInputMethodId = s;
        mSubtypeId = i;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null || (obj instanceof InputMethodSubtypeHandle) ^ true)
            return false;
        obj = (InputMethodSubtypeHandle)obj;
        boolean flag1 = flag;
        if(TextUtils.equals(mInputMethodId, ((InputMethodSubtypeHandle) (obj)).getInputMethodId()))
        {
            flag1 = flag;
            if(mSubtypeId == ((InputMethodSubtypeHandle) (obj)).getSubtypeId())
                flag1 = true;
        }
        return flag1;
    }

    public String getInputMethodId()
    {
        return mInputMethodId;
    }

    public int getSubtypeId()
    {
        return mSubtypeId;
    }

    public int hashCode()
    {
        return Objects.hashCode(mInputMethodId) * 31 + mSubtypeId;
    }

    public String toString()
    {
        return (new StringBuilder()).append("InputMethodSubtypeHandle{mInputMethodId=").append(mInputMethodId).append(", mSubtypeId=").append(mSubtypeId).append("}").toString();
    }

    private final String mInputMethodId;
    private final int mSubtypeId;
}
