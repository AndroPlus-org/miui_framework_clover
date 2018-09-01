// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util.net;

import java.util.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

public class ObjectUtils
{

    public ObjectUtils()
    {
    }

    private static Object convertObj(Object obj)
    {
        if(obj instanceof JSONObject)
            return jsonToMap((JSONObject)obj);
        if(obj instanceof JSONArray)
        {
            JSONArray jsonarray = (JSONArray)obj;
            int i = jsonarray.length();
            obj = new ArrayList();
            for(int j = 0; j < i; j++)
                ((List) (obj)).add(convertObj(jsonarray.opt(j)));

            return obj;
        }
        if(obj == JSONObject.NULL)
            return null;
        else
            return obj;
    }

    public static Object convertObjectToJson(Object obj)
    {
        if(obj instanceof List)
        {
            Object obj1 = (List)obj;
            obj = new JSONArray();
            for(obj1 = ((Iterable) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); ((JSONArray) (obj)).put(convertObjectToJson(((Iterator) (obj1)).next())));
            return obj;
        }
        if(obj instanceof Map)
        {
            JSONObject jsonobject = new JSONObject();
            Map map = (Map)obj;
            for(obj = map.keySet().iterator(); ((Iterator) (obj)).hasNext();)
            {
                Object obj2 = ((Iterator) (obj)).next();
                try
                {
                    jsonobject.put((String)obj2, convertObjectToJson(map.get(obj2)));
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
            }

            return jsonobject;
        } else
        {
            return obj;
        }
    }

    public static String flattenMap(Map map)
    {
        if(map == null)
            return "null";
        java.util.Set set = map.entrySet();
        map = new StringBuilder();
        map.append("{");
        for(Iterator iterator = set.iterator(); iterator.hasNext(); map.append("),"))
        {
            Object obj1 = (java.util.Map.Entry)iterator.next();
            Object obj = ((java.util.Map.Entry) (obj1)).getKey();
            obj1 = ((java.util.Map.Entry) (obj1)).getValue();
            map.append("(");
            map.append(obj);
            map.append(",");
            map.append(obj1);
        }

        map.append("}");
        return map.toString();
    }

    public static Map jsonToMap(JSONObject jsonobject)
    {
        if(jsonobject == null)
            return null;
        HashMap hashmap = new HashMap();
        String s;
        for(Iterator iterator = jsonobject.keys(); iterator.hasNext(); hashmap.put(s, convertObj(jsonobject.opt(s))))
            s = (String)iterator.next();

        return hashmap;
    }

    public static Map listToMap(Map map)
    {
        HashMap hashmap = new HashMap();
        if(map != null)
        {
            Iterator iterator = map.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj = (java.util.Map.Entry)iterator.next();
                map = (String)((java.util.Map.Entry) (obj)).getKey();
                obj = (List)((java.util.Map.Entry) (obj)).getValue();
                if(map != null && obj != null && ((List) (obj)).size() > 0)
                    hashmap.put(map, (String)((List) (obj)).get(0));
            } while(true);
        }
        return hashmap;
    }

    public static List mapToPairs(Map map)
    {
        if(map == null)
            return null;
        ArrayList arraylist = new ArrayList();
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) 
        {
            map = (java.util.Map.Entry)iterator.next();
            String s = (String)map.getKey();
            map = (String)map.getValue();
            if(map == null)
                map = "";
            arraylist.add(new BasicNameValuePair(s, map));
        }
        return arraylist;
    }
}
