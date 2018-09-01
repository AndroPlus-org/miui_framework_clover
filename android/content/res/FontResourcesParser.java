// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            Resources, TypedArray

public class FontResourcesParser
{
    public static interface FamilyResourceEntry
    {
    }

    public static final class FontFamilyFilesResourceEntry
        implements FamilyResourceEntry
    {

        public FontFileResourceEntry[] getEntries()
        {
            return mEntries;
        }

        private final FontFileResourceEntry mEntries[];

        public FontFamilyFilesResourceEntry(FontFileResourceEntry afontfileresourceentry[])
        {
            mEntries = afontfileresourceentry;
        }
    }

    public static final class FontFileResourceEntry
    {

        public String getFileName()
        {
            return mFileName;
        }

        public int getItalic()
        {
            return mItalic;
        }

        public int getWeight()
        {
            return mWeight;
        }

        private final String mFileName;
        private int mItalic;
        private int mResourceId;
        private int mWeight;

        public FontFileResourceEntry(String s, int i, int j)
        {
            mFileName = s;
            mWeight = i;
            mItalic = j;
        }
    }

    public static final class ProviderResourceEntry
        implements FamilyResourceEntry
    {

        public String getAuthority()
        {
            return mProviderAuthority;
        }

        public List getCerts()
        {
            return mCerts;
        }

        public String getPackage()
        {
            return mProviderPackage;
        }

        public String getQuery()
        {
            return mQuery;
        }

        private final List mCerts;
        private final String mProviderAuthority;
        private final String mProviderPackage;
        private final String mQuery;

        public ProviderResourceEntry(String s, String s1, String s2, List list)
        {
            mProviderAuthority = s;
            mProviderPackage = s1;
            mQuery = s2;
            mCerts = list;
        }
    }


    public FontResourcesParser()
    {
    }

    public static FamilyResourceEntry parse(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        else
            return readFamilies(xmlpullparser, resources);
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        xmlpullparser.require(2, null, "font-family");
        if(xmlpullparser.getName().equals("font-family"))
        {
            return readFamily(xmlpullparser, resources);
        } else
        {
            skip(xmlpullparser);
            Log.e("FontResourcesParser", "Failed to find font-family tag");
            return null;
        }
    }

    private static FamilyResourceEntry readFamily(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = resources.obtainAttributes(Xml.asAttributeSet(xmlpullparser), com.android.internal.R.styleable.FontFamily);
        String s = typedarray.getString(0);
        String s1 = typedarray.getString(2);
        Object obj1 = typedarray.getString(1);
        int i = typedarray.getResourceId(3, 0);
        typedarray.recycle();
        if(s != null && s1 != null && obj1 != null)
        {
            for(; xmlpullparser.next() != 3; skip(xmlpullparser));
            Object obj = null;
            xmlpullparser = obj;
            if(i != 0)
            {
                TypedArray typedarray1 = resources.obtainTypedArray(i);
                xmlpullparser = obj;
                if(typedarray1.length() > 0)
                {
                    ArrayList arraylist = new ArrayList();
                    int j;
                    if(typedarray1.getResourceId(0, 0) != 0)
                        j = 1;
                    else
                        j = 0;
                    if(j != 0)
                    {
                        j = 0;
                        do
                        {
                            xmlpullparser = arraylist;
                            if(j >= typedarray1.length())
                                break;
                            arraylist.add(Arrays.asList(resources.getStringArray(typedarray1.getResourceId(j, 0))));
                            j++;
                        } while(true);
                    } else
                    {
                        arraylist.add(Arrays.asList(resources.getStringArray(i)));
                        xmlpullparser = arraylist;
                    }
                }
            }
            return new ProviderResourceEntry(s, s1, ((String) (obj1)), xmlpullparser);
        }
        obj1 = new ArrayList();
        do
        {
            if(xmlpullparser.next() == 3)
                break;
            if(xmlpullparser.getEventType() == 2)
                if(xmlpullparser.getName().equals("font"))
                {
                    FontFileResourceEntry fontfileresourceentry = readFont(xmlpullparser, resources);
                    if(fontfileresourceentry != null)
                        ((List) (obj1)).add(fontfileresourceentry);
                } else
                {
                    skip(xmlpullparser);
                }
        } while(true);
        if(((List) (obj1)).isEmpty())
            return null;
        else
            return new FontFamilyFilesResourceEntry((FontFileResourceEntry[])((List) (obj1)).toArray(new FontFileResourceEntry[((List) (obj1)).size()]));
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        resources = resources.obtainAttributes(Xml.asAttributeSet(xmlpullparser), com.android.internal.R.styleable.FontFamilyFont);
        int i = resources.getInt(1, -1);
        int j = resources.getInt(2, -1);
        String s = resources.getString(0);
        resources.recycle();
        for(; xmlpullparser.next() != 3; skip(xmlpullparser));
        if(s == null)
            return null;
        else
            return new FontFileResourceEntry(s, i, j);
    }

    private static void skip(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = 1;
        do
        {
            if(i <= 0)
                break;
            switch(xmlpullparser.next())
            {
            case 2: // '\002'
                i++;
                break;

            case 3: // '\003'
                i--;
                break;
            }
        } while(true);
    }

    private static final String TAG = "FontResourcesParser";
}
