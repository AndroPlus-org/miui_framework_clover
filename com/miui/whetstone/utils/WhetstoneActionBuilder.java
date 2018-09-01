// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.utils;

import com.miui.whetstone.WhetstoneAction;
import org.json.JSONException;
import org.json.JSONObject;

public class WhetstoneActionBuilder
{

    public WhetstoneActionBuilder(String s)
    {
        department = s;
        content = new JSONObject();
    }

    public WhetstoneActionBuilder add(String s, int i)
    {
        try
        {
            content.put(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
        return this;
    }

    public WhetstoneActionBuilder add(String s, long l)
    {
        try
        {
            content.put(s, l);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
        return this;
    }

    public WhetstoneActionBuilder add(String s, String s1)
    {
        try
        {
            content.put(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
        return this;
    }

    public WhetstoneActionBuilder add(String s, JSONObject jsonobject)
    {
        try
        {
            content.put(s, jsonobject);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
        return this;
    }

    public WhetstoneAction getAction()
    {
        return new WhetstoneAction(department, content);
    }

    private JSONObject content;
    private String department;
}
