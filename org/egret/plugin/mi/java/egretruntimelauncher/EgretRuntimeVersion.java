// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.java.egretruntimelauncher;

import java.util.ArrayList;
import org.json.*;

// Referenced classes of package org.egret.plugin.mi.java.egretruntimelauncher:
//            Library

public class EgretRuntimeVersion
{

    public EgretRuntimeVersion()
    {
        libraryList = new ArrayList();
    }

    public void fromString(String s)
    {
        ArrayList arraylist = new ArrayList();
        Object obj;
        JSONObject jsonobject = JVM INSTR new #33  <Class JSONObject>;
        obj = JVM INSTR new #35  <Class JSONTokener>;
        ((JSONTokener) (obj)).JSONTokener(s);
        jsonobject.JSONObject(((JSONTokener) (obj)));
        jsonobject = jsonobject.getJSONObject("runtime");
        s = jsonobject.getString("url");
        obj = jsonobject.getJSONArray("library");
        int i = 0;
_L2:
        if(i >= ((JSONArray) (obj)).length())
            break; /* Loop/switch isn't completed */
        Library library = JVM INSTR new #60  <Class Library>;
        library.Library((JSONObject)((JSONArray) (obj)).get(i), s);
        arraylist.add(library);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        libraryList = arraylist;
_L3:
        return;
        s;
        s.printStackTrace();
          goto _L3
    }

    public ArrayList getLibraryList()
    {
        return libraryList;
    }

    private static final String JSON_LIBRARY = "library";
    private static final String JSON_RUNTIME = "runtime";
    private static final String JSON_URL = "url";
    private ArrayList libraryList;
}
