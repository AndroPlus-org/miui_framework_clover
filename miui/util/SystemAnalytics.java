// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Slog;
import java.util.*;
import org.json.JSONObject;

public class SystemAnalytics
{
    public static class Action
    {

        private void ensureKey(String s)
        {
            if(!TextUtils.isEmpty(s) && sKeywords.contains(s))
                throw new IllegalArgumentException((new StringBuilder()).append("this key ").append(s).append(" is built-in, please pick another key.").toString());
            else
                return;
        }

        void addContent(String s, int i)
        {
            if(TextUtils.isEmpty(s))
                break MISSING_BLOCK_LABEL_17;
            mContent.put(s, i);
_L1:
            return;
            s;
            s.printStackTrace();
              goto _L1
        }

        void addContent(String s, long l)
        {
            if(TextUtils.isEmpty(s))
                break MISSING_BLOCK_LABEL_17;
            mContent.put(s, l);
_L1:
            return;
            s;
            s.printStackTrace();
              goto _L1
        }

        void addContent(String s, Object obj)
        {
            if(TextUtils.isEmpty(s))
                break MISSING_BLOCK_LABEL_17;
            mContent.put(s, obj);
_L1:
            return;
            s;
            s.printStackTrace();
              goto _L1
        }

        void addContent(JSONObject jsonobject)
        {
            if(jsonobject != null)
            {
                for(Iterator iterator = jsonobject.keys(); iterator.hasNext();)
                {
                    String s = iterator.next().toString();
                    ensureKey(s);
                    try
                    {
                        mContent.put(s, jsonobject.get(s));
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }

            }
        }

        protected Action addEventId(String s)
        {
            addContent("_event_id_", s);
            return this;
        }

        void addExtra(String s, String s1)
        {
            mExtra.put(s, s1);
_L1:
            return;
            s;
            s.printStackTrace();
              goto _L1
        }

        public Action addParam(String s, int i)
        {
            ensureKey(s);
            addContent(s, i);
            return this;
        }

        public Action addParam(String s, long l)
        {
            ensureKey(s);
            addContent(s, l);
            return this;
        }

        public Action addParam(String s, String s1)
        {
            ensureKey(s);
            addContent(s, s1);
            return this;
        }

        public Action addParam(String s, JSONObject jsonobject)
        {
            ensureKey(s);
            addContent(s, jsonobject);
            return this;
        }

        final JSONObject getContent()
        {
            return mContent;
        }

        final JSONObject getExtra()
        {
            return mExtra;
        }

        protected static final String ACTION = "_action_";
        protected static final String CATEGORY = "_category_";
        protected static final String EVENT_ID = "_event_id_";
        protected static final String LABEL = "_label_";
        protected static final String VALUE = "_value_";
        private JSONObject mContent;
        private JSONObject mExtra;
        private Set sKeywords;

        public Action()
        {
            mContent = new JSONObject();
            mExtra = new JSONObject();
            sKeywords = new HashSet();
            sKeywords.add("_event_id_");
            sKeywords.add("_category_");
            sKeywords.add("_action_");
            sKeywords.add("_label_");
            sKeywords.add("_value_");
        }
    }


    public SystemAnalytics()
    {
    }

    public static void trackSystem(Context context, String s, Action action)
    {
        Intent intent;
        intent = JVM INSTR new #31  <Class Intent>;
        intent.Intent();
        intent.setClassName("com.miui.analytics", "com.miui.analytics.EventService");
        if(s == null)
            s = "";
        intent.putExtra("key", s);
        intent.putExtra("content", action.getContent().toString());
        Slog.i("SystemAnalytics", action.getContent().toString());
        intent.putExtra("extra", action.getExtra().toString());
        intent.putExtra("appid", "systemserver");
        intent.putExtra("type", 0);
        context.startService(intent);
_L1:
        return;
        context;
        Slog.e("SystemAnalytics", "track system error!", context);
          goto _L1
    }

    public static final String CONFIGKEY_BOOT_SHUT = "systemserver_bootshuttime";
    private static final int LOGTYPE_EVENT = 0;
    private static final String SYSTEM_APP_ID = "systemserver";
    private static final String TAG = "SystemAnalytics";
}
