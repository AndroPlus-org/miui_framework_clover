// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import android.util.Log;
import org.json.*;

public class JSONPath
{

    public JSONPath(JSONArray jsonarray)
    {
        mRootArray = jsonarray;
    }

    public JSONPath(JSONObject jsonobject)
    {
        mRoot = jsonobject;
    }

    public Object get(String s)
    {
        String as[];
        int i;
        if(TextUtils.isEmpty(s))
            return null;
        as = s.split("/");
        if(mRoot != null)
            s = mRoot;
        else
            s = mRootArray;
        if(s == null)
            return null;
        i = 0;
_L5:
        Object obj = s;
        if(i >= as.length) goto _L2; else goto _L1
_L1:
        obj = as[i];
        if(!TextUtils.isEmpty(((CharSequence) (obj)))) goto _L4; else goto _L3
_L3:
        obj = s;
_L10:
        i++;
        s = ((String) (obj));
          goto _L5
_L4:
        int j = -1;
        int k = ((String) (obj)).indexOf("[");
        Object obj1;
        obj1 = obj;
        if(k == -1)
            break MISSING_BLOCK_LABEL_131;
        j = Integer.parseInt(((String) (obj)).substring(k + 1, ((String) (obj)).length() - 1));
        obj1 = ((String) (obj)).substring(0, k);
        obj = s;
        if(!(s instanceof JSONObject))
            break MISSING_BLOCK_LABEL_165;
        obj = s;
        if(TextUtils.isEmpty(((CharSequence) (obj1))) ^ true)
            obj = ((JSONObject)s).get(((String) (obj1)));
        s = ((String) (obj));
        if(!(obj instanceof JSONArray)) goto _L7; else goto _L6
_L6:
        s = (JSONArray)obj;
        if(j != -1) goto _L8; else goto _L2
_L2:
        return obj;
_L8:
        s = ((String) (s.get(j)));
_L7:
        if(s == null)
            break; /* Loop/switch isn't completed */
        obj1 = JSONObject.NULL;
        obj = s;
        if(s != obj1) goto _L10; else goto _L9
_L9:
        return null;
        s;
        Log.d("JSONPath", s.toString());
_L12:
        return null;
        s;
        Log.d("JSONPath", s.toString());
        if(true) goto _L12; else goto _L11
_L11:
    }

    private static final String LOG_TAG = "JSONPath";
    private JSONObject mRoot;
    private JSONArray mRootArray;
}
