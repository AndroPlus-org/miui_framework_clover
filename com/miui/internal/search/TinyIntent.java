// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Set;
import org.json.*;

public class TinyIntent
{

    public TinyIntent()
    {
    }

    public TinyIntent(Intent intent)
    {
        mAction = intent.getAction();
        mPackage = intent.getPackage();
        mComponent = intent.getComponent();
        mExtras = intent.getExtras();
    }

    public TinyIntent(String s)
    {
        setAction(s);
    }

    public TinyIntent(JSONObject jsonobject)
        throws JSONException
    {
        String s = jsonobject.optString("intent_action");
        String s1 = jsonobject.optString("intent_package");
        String s2 = jsonobject.optString("intent_class");
        jsonobject = jsonobject.optJSONArray("intent_extra");
        if(!TextUtils.isEmpty(s))
            setAction(s);
        if(!TextUtils.isEmpty(s1))
            if(!TextUtils.isEmpty(s2))
                setComponent(new ComponentName(s1, s2));
            else
                setPackage(s1);
        if(jsonobject != null)
        {
            for(int i = 0; i < jsonobject.length(); i++)
            {
                JSONObject jsonobject1 = jsonobject.getJSONObject(i);
                putExtra(jsonobject1.optString("name"), jsonobject1.optString("value"));
            }

        }
    }

    public String getAction()
    {
        return mAction;
    }

    public ComponentName getComponent()
    {
        return mComponent;
    }

    public CharSequence getExtra(String s)
    {
        return mExtras.getCharSequence(s);
    }

    public CharSequence getExtra(String s, CharSequence charsequence)
    {
        return mExtras.getCharSequence(s, charsequence);
    }

    public Bundle getExtras()
    {
        Bundle bundle = null;
        if(mExtras != null)
            bundle = new Bundle(mExtras);
        return bundle;
    }

    public String getPackage()
    {
        return mPackage;
    }

    public TinyIntent putExtra(String s, int i)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putInt(s, i);
        return this;
    }

    public TinyIntent putExtra(String s, Bundle bundle)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBundle(s, bundle);
        return this;
    }

    public TinyIntent putExtra(String s, CharSequence charsequence)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putCharSequence(s, charsequence);
        return this;
    }

    public TinyIntent putExtra(String s, boolean flag)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBoolean(s, flag);
        return this;
    }

    public TinyIntent putExtras(Bundle bundle)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putAll(bundle);
        return this;
    }

    public TinyIntent setAction(String s)
    {
        mAction = s;
        return this;
    }

    public TinyIntent setClassName(String s, String s1)
    {
        mComponent = new ComponentName(s, s1);
        return this;
    }

    public TinyIntent setComponent(ComponentName componentname)
    {
        mComponent = componentname;
        return this;
    }

    public TinyIntent setPackage(String s)
    {
        mPackage = s;
        return this;
    }

    public Intent toIntent()
    {
        Intent intent;
        intent = new Intent();
        if(!TextUtils.isEmpty(mAction))
            intent.setAction(mAction);
        if(mComponent == null) goto _L2; else goto _L1
_L1:
        intent.setComponent(mComponent);
_L4:
        if(mExtras != null)
            intent.putExtras(mExtras);
        return intent;
_L2:
        if(mPackage != null)
            intent.setPackage(mPackage);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public JSONObject toJSONObject()
        throws JSONException
    {
        JSONObject jsonobject = new JSONObject();
        if(!TextUtils.isEmpty(mAction))
            jsonobject.put("intent_action", mAction);
        if(mComponent != null)
        {
            jsonobject.put("intent_package", mComponent.getPackageName());
            jsonobject.put("intent_class", mComponent.getClassName());
        } else
        if(mPackage != null)
            jsonobject.put("intent_package", mPackage);
        if(mExtras != null && mExtras.keySet().isEmpty() ^ true)
        {
            JSONArray jsonarray = new JSONArray();
            String s;
            for(Iterator iterator = mExtras.keySet().iterator(); iterator.hasNext(); jsonarray.put((new JSONObject()).put("name", s).put("value", mExtras.get(s))))
                s = (String)iterator.next();

            jsonobject.put("intent_extra", jsonarray);
        }
        return jsonobject;
    }

    private String mAction;
    private ComponentName mComponent;
    private Bundle mExtras;
    private String mPackage;
}
