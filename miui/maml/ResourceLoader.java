// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.MemoryFile;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javax.xml.parsers.*;
import miui.maml.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class ResourceLoader
{

    public ResourceLoader()
    {
        mManifestName = "manifest.xml";
        mConfigName = "config.xml";
    }

    private String getPathForLanguage(String s, String s1)
    {
        if(!TextUtils.isEmpty(mLanguageCountrySuffix))
        {
            String s2 = (new StringBuilder()).append(s1).append("_").append(mLanguageCountrySuffix).append("/").append(s).toString();
            if(resourceExists(s2))
                return s2;
        }
        if(!TextUtils.isEmpty(mLanguageSuffix))
        {
            String s3 = (new StringBuilder()).append(s1).append("_").append(mLanguageSuffix).append("/").append(s).toString();
            if(resourceExists(s3))
                return s3;
        }
        if(!TextUtils.isEmpty(s1))
        {
            s1 = (new StringBuilder()).append(s1).append("/").append(s).toString();
            if(resourceExists(s1))
                return s1;
        }
        if(!resourceExists(s))
            s = null;
        return s;
    }

    private Element getXmlRoot(String s)
    {
        s = getInputStream(getPathForLanguage(s));
        if(s == null)
            return null;
        Element element = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(s).getDocumentElement();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return element;
        Object obj;
        obj;
        Log.e("ResourceLoader", ((Exception) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
_L2:
        return null;
        obj;
        Log.e("ResourceLoader", ((SAXException) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("ResourceLoader", ((ParserConfigurationException) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("ResourceLoader", ((OutOfMemoryError) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("ResourceLoader", ((IOException) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        throw obj;
    }

    public void finish()
    {
    }

    public ResourceManager.BitmapInfo getBitmapInfo(String s, android.graphics.BitmapFactory.Options options)
    {
        Object obj;
        obj = getPathForLanguage(s, "images");
        Object obj1 = obj;
        if(obj == null)
        {
            Log.d("ResourceLoader", (new StringBuilder()).append("TRY AGAIN to get getPathForLanguage: ").append(s).toString());
            obj = getPathForLanguage(s, "images");
            obj1 = obj;
            if(obj == null)
            {
                Log.e("ResourceLoader", (new StringBuilder()).append("fail to get getPathForLanguage: ").append(s).toString());
                return null;
            }
        }
        InputStream inputstream = getInputStream(((String) (obj1)));
        obj = inputstream;
        if(inputstream == null)
        {
            Log.d("ResourceLoader", (new StringBuilder()).append("TRY AGAIN to get InputStream: ").append(s).toString());
            obj1 = getInputStream(((String) (obj1)));
            obj = obj1;
            if(obj1 == null)
            {
                Log.e("ResourceLoader", (new StringBuilder()).append("fail to get InputStream: ").append(s).toString());
                return null;
            }
        }
        Rect rect;
        Object obj2;
        rect = JVM INSTR new #139 <Class Rect>;
        rect.Rect();
        obj2 = BitmapFactory.decodeStream(((InputStream) (obj)), rect, options);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_271;
        obj2 = JVM INSTR new #45  <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        Log.d("ResourceLoader", ((StringBuilder) (obj2)).append("TRY AGAIN to decode bitmap: ").append(s).toString());
        if(BitmapFactory.decodeStream(((InputStream) (obj)), rect, options) != null)
            break MISSING_BLOCK_LABEL_297;
        options = JVM INSTR new #45  <Class StringBuilder>;
        options.StringBuilder();
        Log.e("ResourceLoader", options.append("fail to decode bitmap: ").append(s).toString());
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return null;
        s = new ResourceManager.BitmapInfo(((android.graphics.Bitmap) (obj2)), rect);
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(android.graphics.BitmapFactory.Options options) { }
        return s;
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
_L2:
        return null;
        s;
        Log.e("ResourceLoader", s.toString());
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        if(true) goto _L2; else goto _L1
_L1:
        s;
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(android.graphics.BitmapFactory.Options options) { }
        throw s;
    }

    public Element getConfigRoot()
    {
        return getXmlRoot(mConfigName);
    }

    public MemoryFile getFile(String s)
    {
        long al[];
        al = new long[1];
        s = getInputStream(s, al);
        if(s == null)
            return null;
        byte abyte0[];
        MemoryFile memoryfile;
        abyte0 = new byte[0x10000];
        memoryfile = JVM INSTR new #166 <Class MemoryFile>;
        memoryfile.MemoryFile(null, (int)al[0]);
        int i = 0;
_L2:
        int j = s.read(abyte0, 0, 0x10000);
        if(j <= 0)
            break; /* Loop/switch isn't completed */
        memoryfile.writeBytes(abyte0, 0, i, j);
        i += j;
        if(true) goto _L2; else goto _L1
_L1:
        i = memoryfile.length();
        if(i > 0)
        {
            if(s != null)
                try
                {
                    s.close();
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
            return memoryfile;
        }
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
_L4:
        return null;
        Object obj;
        obj;
        Log.e("ResourceLoader", ((OutOfMemoryError) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e("ResourceLoader", ((IOException) (obj)).toString());
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        throw obj;
    }

    public final InputStream getInputStream(String s)
    {
        return getInputStream(s, null);
    }

    public abstract InputStream getInputStream(String s, long al[]);

    public Locale getLocale()
    {
        return mLocale;
    }

    public Element getManifestRoot()
    {
        return getXmlRoot(mManifestName);
    }

    public String getPathForLanguage(String s)
    {
        String s1 = null;
        if(!TextUtils.isEmpty(mLanguageCountrySuffix))
        {
            String s3 = Utils.addFileNameSuffix(s, mLanguageCountrySuffix);
            s1 = s3;
            if(!resourceExists(s3))
                s1 = null;
        }
        String s4 = s1;
        if(s1 == null)
        {
            s4 = s1;
            if(TextUtils.isEmpty(mLanguageSuffix) ^ true)
            {
                String s2 = Utils.addFileNameSuffix(s, mLanguageSuffix);
                s4 = s2;
                if(!resourceExists(s2))
                    s4 = null;
            }
        }
        if(s4 != null)
            s = s4;
        return s;
    }

    public void init()
    {
    }

    public abstract boolean resourceExists(String s);

    public ResourceLoader setLocal(Locale locale)
    {
        if(locale != null)
        {
            mLanguageSuffix = locale.getLanguage();
            mLanguageCountrySuffix = locale.toString();
            if(TextUtils.equals(mLanguageSuffix, mLanguageCountrySuffix))
                mLanguageSuffix = null;
        }
        mLocale = locale;
        return this;
    }

    private static final String CONFIG_FILE_NAME = "config.xml";
    private static final String IMAGES_FOLDER_NAME = "images";
    private static final String LOG_TAG = "ResourceLoader";
    private static final String MANIFEST_FILE_NAME = "manifest.xml";
    protected String mConfigName;
    protected String mLanguageCountrySuffix;
    protected String mLanguageSuffix;
    protected Locale mLocale;
    protected String mManifestName;
    private String mThemeName;
}
