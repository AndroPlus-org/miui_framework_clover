// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.*;

// Referenced classes of package android.nfc.cardemulation:
//            CardEmulation

public class AidGroup
    implements Parcelable
{

    AidGroup(String s, String s1)
    {
        aids = new ArrayList();
        category = s;
        description = s1;
    }

    public AidGroup(List list, String s)
    {
        if(list == null || list.size() == 0)
            throw new IllegalArgumentException("No AIDS in AID group.");
        if(list.size() > 256)
            throw new IllegalArgumentException("Too many AIDs in AID group.");
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            if(!CardEmulation.isValidAid(s1))
                throw new IllegalArgumentException((new StringBuilder()).append("AID ").append(s1).append(" is not a valid AID.").toString());
        }

        if(isValidCategory(s))
            category = s;
        else
            category = "other";
        aids = new ArrayList(list.size());
        for(s = list.iterator(); s.hasNext(); aids.add(list.toUpperCase()))
            list = (String)s.next();

        description = null;
    }

    public static AidGroup createFromXml(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj;
        ArrayList arraylist;
        Object obj1;
        boolean flag;
        int i;
        int j;
        obj = null;
        arraylist = new ArrayList();
        obj1 = null;
        flag = false;
        i = xmlpullparser.getEventType();
        j = xmlpullparser.getDepth();
_L2:
        Object obj2;
        String s;
        boolean flag1;
        obj2 = obj1;
        if(i == 1)
            break MISSING_BLOCK_LABEL_304;
        obj2 = obj1;
        if(xmlpullparser.getDepth() < j)
            break MISSING_BLOCK_LABEL_304;
        s = xmlpullparser.getName();
        if(i != 2)
            break; /* Loop/switch isn't completed */
        if(s.equals("aid"))
        {
            if(flag)
            {
                s = xmlpullparser.getAttributeValue(null, "value");
                obj2 = obj;
                flag1 = flag;
                if(s != null)
                {
                    arraylist.add(s.toUpperCase());
                    flag1 = flag;
                    obj2 = obj;
                }
            } else
            {
                Log.d("AidGroup", "Ignoring <aid> tag while not in group");
                obj2 = obj;
                flag1 = flag;
            }
        } else
        if(s.equals("aid-group"))
        {
            obj2 = xmlpullparser.getAttributeValue(null, "category");
            if(obj2 == null)
            {
                Log.e("AidGroup", "<aid-group> tag without valid category");
                return null;
            }
            flag1 = true;
        } else
        {
            Log.d("AidGroup", (new StringBuilder()).append("Ignoring unexpected tag: ").append(s).toString());
            obj2 = obj;
            flag1 = flag;
        }
_L4:
        i = xmlpullparser.next();
        obj = obj2;
        flag = flag1;
        if(true) goto _L2; else goto _L1
_L1:
        obj2 = obj;
        flag1 = flag;
        if(i != 3) goto _L4; else goto _L3
_L3:
        obj2 = obj;
        flag1 = flag;
        if(!s.equals("aid-group")) goto _L4; else goto _L5
_L5:
        obj2 = obj;
        flag1 = flag;
        if(!flag) goto _L4; else goto _L6
_L6:
        obj2 = obj;
        flag1 = flag;
        if(arraylist.size() <= 0) goto _L4; else goto _L7
_L7:
        obj2 = new AidGroup(arraylist, ((String) (obj)));
        return ((AidGroup) (obj2));
    }

    static boolean isValidCategory(String s)
    {
        boolean flag;
        if(!"payment".equals(s))
            flag = "other".equals(s);
        else
            flag = true;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public List getAids()
    {
        return aids;
    }

    public String getCategory()
    {
        return category;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("Category: ").append(category).append(", AIDs:").toString());
        for(Iterator iterator = aids.iterator(); iterator.hasNext(); stringbuilder.append(", "))
            stringbuilder.append((String)iterator.next());

        return stringbuilder.toString();
    }

    public void writeAsXml(XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.startTag(null, "aid-group");
        xmlserializer.attribute(null, "category", category);
        for(Iterator iterator = aids.iterator(); iterator.hasNext(); xmlserializer.endTag(null, "aid"))
        {
            String s = (String)iterator.next();
            xmlserializer.startTag(null, "aid");
            xmlserializer.attribute(null, "value", s);
        }

        xmlserializer.endTag(null, "aid-group");
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(category);
        parcel.writeInt(aids.size());
        if(aids.size() > 0)
            parcel.writeStringList(aids);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AidGroup createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            int i = parcel.readInt();
            ArrayList arraylist = new ArrayList();
            if(i > 0)
                parcel.readStringList(arraylist);
            return new AidGroup(arraylist, s);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AidGroup[] newArray(int i)
        {
            return new AidGroup[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAX_NUM_AIDS = 256;
    static final String TAG = "AidGroup";
    protected List aids;
    protected String category;
    protected String description;

}
