// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.java.egretruntimelauncher;

import org.json.JSONObject;

public class Library
{

    public Library(JSONObject jsonobject, String s)
    {
        zipName = jsonobject.getString("name");
        libraryCheckSum = jsonobject.getString("md5");
        zipCheckSum = jsonobject.getString("zip");
        if(zipName == null)
        {
            libraryName = null;
            return;
        }
        int i;
        url = getUrlBy(s, zipName);
        i = zipName.lastIndexOf(".zip");
        if(i >= 0) goto _L2; else goto _L1
_L1:
        jsonobject = null;
_L3:
        libraryName = jsonobject;
_L4:
        return;
_L2:
        jsonobject = zipName.substring(0, i);
          goto _L3
        jsonobject;
        jsonobject.printStackTrace();
        zipName = null;
        libraryName = null;
        libraryCheckSum = null;
        zipCheckSum = null;
          goto _L4
    }

    private String getUrlBy(String s, String s1)
    {
        if(s == null)
            return null;
        if(s.endsWith("/"))
            return (new StringBuilder()).append(s).append(s1).toString();
        else
            return (new StringBuilder()).append(s).append("/").append(s1).toString();
    }

    public String getLibraryCheckSum()
    {
        return libraryCheckSum;
    }

    public String getLibraryName()
    {
        return libraryName;
    }

    public String getUrl()
    {
        return url;
    }

    public String getZipCheckSum()
    {
        return zipCheckSum;
    }

    public String getZipName()
    {
        return zipName;
    }

    private static final String JSON_LIBRARY_CHECKSUM = "md5";
    private static final String JSON_ZIP_CHECKSUM = "zip";
    private static final String JSON_ZIP_NAME = "name";
    private String libraryCheckSum;
    private String libraryName;
    private String url;
    private String zipCheckSum;
    private String zipName;
}
