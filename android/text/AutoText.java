// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.content.Context;
import android.content.res.*;
import android.view.View;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

public class AutoText
{

    private AutoText(Resources resources)
    {
        mLocale = resources.getConfiguration().locale;
        init(resources);
    }

    private void add(String s, char c)
    {
        int i = s.length();
        int j = 0;
        mSize = mSize + 1;
label0:
        for(int k = 0; k < i; k++)
        {
            int l = s.charAt(k);
            boolean flag = false;
            int i1;
label1:
            do
            {
label2:
                {
                    boolean flag1 = flag;
                    i1 = j;
                    if(mTrie[j] != '\uFFFF')
                    {
                        if(l != mTrie[mTrie[j] + 0])
                            break label2;
                        if(k == i - 1)
                        {
                            mTrie[mTrie[j] + 1] = c;
                            return;
                        }
                        i1 = mTrie[j] + 2;
                        flag1 = true;
                    }
                    j = i1;
                    if(flag1)
                        continue label0;
                    j = newTrieNode();
                    mTrie[i1] = (char)j;
                    mTrie[mTrie[i1] + 0] = (char)l;
                    mTrie[mTrie[i1] + 1] = (char)65535;
                    mTrie[mTrie[i1] + 3] = (char)65535;
                    mTrie[mTrie[i1] + 2] = (char)65535;
                    if(k == i - 1)
                    {
                        mTrie[mTrie[i1] + 1] = c;
                        return;
                    }
                    break label1;
                }
                j = mTrie[j] + 3;
            } while(true);
            j = mTrie[i1] + 2;
        }

    }

    public static String get(CharSequence charsequence, int i, int j, View view)
    {
        return getInstance(view).lookup(charsequence, i, j);
    }

    private static AutoText getInstance(View view)
    {
        Resources resources;
        Locale locale;
        resources = view.getContext().getResources();
        locale = resources.getConfiguration().locale;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        AutoText autotext = sInstance;
        view = autotext;
        if(!locale.equals(autotext.mLocale))
        {
            view = JVM INSTR new #2   <Class AutoText>;
            view.AutoText(resources);
            sInstance = view;
        }
        obj;
        JVM INSTR monitorexit ;
        return view;
        view;
        throw view;
    }

    private int getSize()
    {
        return mSize;
    }

    public static int getSize(View view)
    {
        return getInstance(view).getSize();
    }

    private void init(Resources resources)
    {
        XmlResourceParser xmlresourceparser;
        Object obj;
        xmlresourceparser = resources.getXml(0x1170002);
        obj = new StringBuilder(9300);
        mTrie = new char[14337];
        mTrie[0] = (char)65535;
        mTrieUsed = (char)1;
        XmlUtils.beginDocument(xmlresourceparser, "words");
_L2:
        String s;
        XmlUtils.nextElement(xmlresourceparser);
        s = xmlresourceparser.getName();
        if(s == null)
            break MISSING_BLOCK_LABEL_77;
        if(!(s.equals("word") ^ true))
            break MISSING_BLOCK_LABEL_96;
        resources.flushLayoutCache();
        xmlresourceparser.close();
        mText = ((StringBuilder) (obj)).toString();
        return;
        String s1 = xmlresourceparser.getAttributeValue(null, "src");
        if(xmlresourceparser.next() != 4) goto _L2; else goto _L1
_L1:
        s = xmlresourceparser.getText();
        if(!s.equals("")) goto _L4; else goto _L3
_L3:
        char c1;
        boolean flag = false;
        c1 = flag;
_L5:
        add(s1, c1);
          goto _L2
        resources;
        obj = JVM INSTR new #187 <Class RuntimeException>;
        ((RuntimeException) (obj)).RuntimeException(resources);
        throw obj;
        resources;
        xmlresourceparser.close();
        throw resources;
_L4:
        char c;
        c = (char)((StringBuilder) (obj)).length();
        ((StringBuilder) (obj)).append((char)s.length());
        ((StringBuilder) (obj)).append(s);
        c1 = c;
          goto _L5
        resources;
        RuntimeException runtimeexception = JVM INSTR new #187 <Class RuntimeException>;
        runtimeexception.RuntimeException(resources);
        throw runtimeexception;
    }

    private String lookup(CharSequence charsequence, int i, int j)
    {
        char c = mTrie[0];
        int l = i;
        i = c;
label0:
        for(; l < j; l++)
        {
            char c1 = charsequence.charAt(l);
            int k = i;
            do
            {
label1:
                {
                    i = k;
                    if(k != 65535)
                    {
                        if(c1 != mTrie[k + 0])
                            break label1;
                        if(l == j - 1 && mTrie[k + 1] != '\uFFFF')
                        {
                            i = mTrie[k + 1];
                            j = mText.charAt(i);
                            return mText.substring(i + 1, i + 1 + j);
                        }
                        i = mTrie[k + 2];
                    }
                    if(i == 65535)
                        return null;
                    continue label0;
                }
                k = mTrie[k + 3];
            } while(true);
        }

        return null;
    }

    private char newTrieNode()
    {
        if(mTrieUsed + 4 > mTrie.length)
        {
            char ac[] = new char[mTrie.length + 1024];
            System.arraycopy(mTrie, 0, ac, 0, mTrie.length);
            mTrie = ac;
        }
        char c = mTrieUsed;
        mTrieUsed = (char)(mTrieUsed + 4);
        return c;
    }

    private static final int DEFAULT = 14337;
    private static final int INCREMENT = 1024;
    private static final int RIGHT = 9300;
    private static final int TRIE_C = 0;
    private static final int TRIE_CHILD = 2;
    private static final int TRIE_NEXT = 3;
    private static final char TRIE_NULL = 65535;
    private static final int TRIE_OFF = 1;
    private static final int TRIE_ROOT = 0;
    private static final int TRIE_SIZEOF = 4;
    private static AutoText sInstance = new AutoText(Resources.getSystem());
    private static Object sLock = new Object();
    private Locale mLocale;
    private int mSize;
    private String mText;
    private char mTrie[];
    private char mTrieUsed;

}
