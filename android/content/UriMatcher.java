// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class UriMatcher
{

    private UriMatcher()
    {
        mCode = -1;
        mWhich = -1;
        mChildren = new ArrayList();
        mText = null;
    }

    public UriMatcher(int i)
    {
        mCode = i;
        mWhich = -1;
        mChildren = new ArrayList();
        mText = null;
    }

    public void addURI(String s, String s1, int i)
    {
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("code ").append(i).append(" is invalid: it must be positive").toString());
        Object obj = null;
        if(s1 != null)
        {
            obj = s1;
            Object obj1 = obj;
            if(s1.length() > 1)
            {
                obj1 = obj;
                if(s1.charAt(0) == '/')
                    obj1 = s1.substring(1);
            }
            obj = ((String) (obj1)).split("/");
        }
        int j;
        int k;
        if(obj != null)
            j = obj.length;
        else
            j = 0;
        s1 = this;
        k = -1;
label0:
        do
        {
            if(k < j)
            {
                String s2;
                ArrayList arraylist;
                int l;
                int i1;
                if(k < 0)
                    s2 = s;
                else
                    s2 = obj[k];
                arraylist = ((UriMatcher) (s1)).mChildren;
                l = arraylist.size();
                i1 = 0;
                do
                {
label1:
                    {
                        Object obj2 = s1;
                        if(i1 < l)
                        {
                            obj2 = (UriMatcher)arraylist.get(i1);
                            if(!s2.equals(((UriMatcher) (obj2)).mText))
                                break label1;
                        }
                        s1 = ((String) (obj2));
                        if(i1 == l)
                        {
                            s1 = new UriMatcher();
                            if(s2.equals("#"))
                                s1.mWhich = 1;
                            else
                            if(s2.equals("*"))
                                s1.mWhich = 2;
                            else
                                s1.mWhich = 0;
                            s1.mText = s2;
                            ((UriMatcher) (obj2)).mChildren.add(s1);
                        }
                        k++;
                        continue label0;
                    }
                    i1++;
                } while(true);
            }
            s1.mCode = i;
            return;
        } while(true);
    }

    public int match(Uri uri)
    {
        List list;
        int i;
        UriMatcher urimatcher;
        int j;
        list = uri.getPathSegments();
        i = list.size();
        urimatcher = this;
        if(i == 0 && uri.getAuthority() == null)
            return mCode;
        j = -1;
_L12:
        String s;
        ArrayList arraylist;
        UriMatcher urimatcher1;
        int k;
        int l;
label0:
        {
            if(j < i)
            {
                if(j < 0)
                    s = uri.getAuthority();
                else
                    s = (String)list.get(j);
                arraylist = urimatcher.mChildren;
                if(arraylist != null)
                    break label0;
            }
            return urimatcher.mCode;
        }
        urimatcher1 = null;
        k = arraylist.size();
        l = 0;
_L10:
        urimatcher = urimatcher1;
        if(l >= k) goto _L2; else goto _L1
_L1:
        UriMatcher urimatcher2 = (UriMatcher)arraylist.get(l);
        urimatcher2.mWhich;
        JVM INSTR tableswitch 0 2: default 152
    //                   0 168
    //                   1 192
    //                   2 253;
           goto _L3 _L4 _L5 _L6
_L3:
        urimatcher = urimatcher1;
_L8:
        if(urimatcher == null)
            break; /* Loop/switch isn't completed */
_L2:
        if(urimatcher == null)
            return -1;
        break; /* Loop/switch isn't completed */
_L4:
        urimatcher = urimatcher1;
        if(urimatcher2.mText.equals(s))
            urimatcher = urimatcher2;
        continue; /* Loop/switch isn't completed */
_L5:
        int i1 = s.length();
        for(int j1 = 0; j1 < i1; j1++)
        {
            char c = s.charAt(j1);
            urimatcher = urimatcher1;
            if(c < '0')
                continue; /* Loop/switch isn't completed */
            urimatcher = urimatcher1;
            if(c > '9')
                continue; /* Loop/switch isn't completed */
        }

        urimatcher = urimatcher2;
        continue; /* Loop/switch isn't completed */
_L6:
        urimatcher = urimatcher2;
        if(true) goto _L8; else goto _L7
_L7:
        l++;
        urimatcher1 = urimatcher;
        if(true) goto _L10; else goto _L9
_L9:
        j++;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private static final int EXACT = 0;
    public static final int NO_MATCH = -1;
    private static final int NUMBER = 1;
    private static final int TEXT = 2;
    private ArrayList mChildren;
    private int mCode;
    private String mText;
    private int mWhich;
}
