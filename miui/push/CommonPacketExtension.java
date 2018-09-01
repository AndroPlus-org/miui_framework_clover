// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package miui.push:
//            PacketExtension, StringUtils

public class CommonPacketExtension
    implements PacketExtension
{

    public CommonPacketExtension(String s, String s1, String s2, String s3)
    {
        mAttributeNames = null;
        mAttributeValues = null;
        mChildrenEles = null;
        mExtensionElementName = s;
        mNamespace = s1;
        mAttributeNames = (new String[] {
            s2
        });
        mAttributeValues = (new String[] {
            s3
        });
    }

    public CommonPacketExtension(String s, String s1, List list, List list1)
    {
        mAttributeNames = null;
        mAttributeValues = null;
        mChildrenEles = null;
        mExtensionElementName = s;
        mNamespace = s1;
        mAttributeNames = (String[])list.toArray(new String[list.size()]);
        mAttributeValues = (String[])list1.toArray(new String[list1.size()]);
    }

    public CommonPacketExtension(String s, String s1, List list, List list1, String s2, List list2)
    {
        mAttributeNames = null;
        mAttributeValues = null;
        mChildrenEles = null;
        mExtensionElementName = s;
        mNamespace = s1;
        mAttributeNames = (String[])list.toArray(new String[list.size()]);
        mAttributeValues = (String[])list1.toArray(new String[list1.size()]);
        mText = s2;
        mChildrenEles = list2;
    }

    public CommonPacketExtension(String s, String s1, String as[], String as1[])
    {
        mAttributeNames = null;
        mAttributeValues = null;
        mChildrenEles = null;
        mExtensionElementName = s;
        mNamespace = s1;
        mAttributeNames = as;
        mAttributeValues = as1;
    }

    public CommonPacketExtension(String s, String s1, String as[], String as1[], String s2, List list)
    {
        mAttributeNames = null;
        mAttributeValues = null;
        mChildrenEles = null;
        mExtensionElementName = s;
        mNamespace = s1;
        mAttributeNames = as;
        mAttributeValues = as1;
        mText = s2;
        mChildrenEles = list;
    }

    public static CommonPacketExtension[] getArray(Parcelable aparcelable[])
    {
        int i;
        CommonPacketExtension acommonpacketextension[];
        if(aparcelable == null)
            i = 0;
        else
            i = aparcelable.length;
        acommonpacketextension = new CommonPacketExtension[i];
        if(aparcelable != null)
            for(i = 0; i < aparcelable.length; i++)
                acommonpacketextension[i] = parseFromBundle((Bundle)aparcelable[i]);

        return acommonpacketextension;
    }

    public static CommonPacketExtension parseFromBundle(Bundle bundle)
    {
        String s = bundle.getString("ext_ele_name");
        String s1 = bundle.getString("ext_ns");
        String s2 = bundle.getString("ext_text");
        Bundle bundle1 = bundle.getBundle("attributes");
        Set set = bundle1.keySet();
        String as[] = new String[set.size()];
        String as1[] = new String[set.size()];
        Bundle bundle2 = null;
        int i = 0;
        for(Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            String s3 = (String)iterator.next();
            as[i] = s3;
            as1[i] = bundle1.getString(s3);
            i++;
        }

        if(bundle.containsKey("children"))
        {
            Parcelable aparcelable[] = bundle.getParcelableArray("children");
            bundle = new ArrayList(aparcelable.length);
            int j = 0;
            int k = aparcelable.length;
            do
            {
                bundle2 = bundle;
                if(j >= k)
                    break;
                bundle.add(parseFromBundle((Bundle)aparcelable[j]));
                j++;
            } while(true);
        }
        return new CommonPacketExtension(s, s1, as, as1, s2, bundle2);
    }

    public static Parcelable[] toParcelableArray(List list)
    {
        return toParcelableArray((CommonPacketExtension[])list.toArray(new CommonPacketExtension[list.size()]));
    }

    public static Parcelable[] toParcelableArray(CommonPacketExtension acommonpacketextension[])
    {
        if(acommonpacketextension == null)
            return null;
        Parcelable aparcelable[] = new Parcelable[acommonpacketextension.length];
        for(int i = 0; i < acommonpacketextension.length; i++)
            aparcelable[i] = acommonpacketextension[i].toParcelable();

        return aparcelable;
    }

    public void appendChild(CommonPacketExtension commonpacketextension)
    {
        if(mChildrenEles == null)
            mChildrenEles = new ArrayList();
        if(!mChildrenEles.contains(commonpacketextension))
            mChildrenEles.add(commonpacketextension);
    }

    public String getAttributeValue(String s)
    {
        if(s == null)
            throw new IllegalArgumentException();
        if(mAttributeNames != null)
        {
            for(int i = 0; i < mAttributeNames.length; i++)
                if(s.equals(mAttributeNames[i]))
                    return mAttributeValues[i];

        }
        return null;
    }

    public CommonPacketExtension getChildByName(String s)
    {
        if(TextUtils.isEmpty(s) || mChildrenEles == null)
            return null;
        for(Iterator iterator = mChildrenEles.iterator(); iterator.hasNext();)
        {
            CommonPacketExtension commonpacketextension = (CommonPacketExtension)iterator.next();
            if(commonpacketextension.mExtensionElementName.equals(s))
                return commonpacketextension;
        }

        return null;
    }

    public List getChildrenByName(String s)
    {
        if(TextUtils.isEmpty(s) || mChildrenEles == null)
            return null;
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mChildrenEles.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            CommonPacketExtension commonpacketextension = (CommonPacketExtension)iterator.next();
            if(commonpacketextension.mExtensionElementName.equals(s))
                arraylist.add(commonpacketextension);
        } while(true);
        return arraylist;
    }

    public List getChildrenExt()
    {
        return mChildrenEles;
    }

    public String getElementName()
    {
        return mExtensionElementName;
    }

    public String getNamespace()
    {
        return mNamespace;
    }

    public String getText()
    {
        if(!TextUtils.isEmpty(mText))
            return StringUtils.unescapeFromXML(mText);
        else
            return mText;
    }

    public void setText(String s)
    {
        if(!TextUtils.isEmpty(s))
            mText = StringUtils.escapeForXML(s);
        else
            mText = s;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", mExtensionElementName);
        bundle.putString("ext_ns", mNamespace);
        bundle.putString("ext_text", mText);
        Bundle bundle1 = new Bundle();
        if(mAttributeNames != null && mAttributeNames.length > 0)
        {
            for(int i = 0; i < mAttributeNames.length; i++)
                bundle1.putString(mAttributeNames[i], mAttributeValues[i]);

        }
        bundle.putBundle("attributes", bundle1);
        if(mChildrenEles != null && mChildrenEles.size() > 0)
            bundle.putParcelableArray("children", toParcelableArray(mChildrenEles));
        return bundle;
    }

    public Parcelable toParcelable()
    {
        return toBundle();
    }

    public String toString()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(mExtensionElementName);
        if(!TextUtils.isEmpty(mNamespace))
            stringbuilder.append(" ").append("xmlns=").append("\"").append(mNamespace).append("\"");
        if(mAttributeNames != null && mAttributeNames.length > 0)
        {
            for(int i = 0; i < mAttributeNames.length; i++)
                if(!TextUtils.isEmpty(mAttributeValues[i]))
                    stringbuilder.append(" ").append(mAttributeNames[i]).append("=\"").append(StringUtils.escapeForXML(mAttributeValues[i])).append("\"");

        }
        if(!TextUtils.isEmpty(mText))
            stringbuilder.append(">").append(mText).append("</").append(mExtensionElementName).append(">");
        else
        if(mChildrenEles != null && mChildrenEles.size() > 0)
        {
            stringbuilder.append(">");
            for(Iterator iterator = mChildrenEles.iterator(); iterator.hasNext(); stringbuilder.append(((CommonPacketExtension)iterator.next()).toXML()));
            stringbuilder.append("</").append(mExtensionElementName).append(">");
        } else
        {
            stringbuilder.append("/>");
        }
        return stringbuilder.toString();
    }

    public static final String ATTRIBUTE_NAME = "attributes";
    public static final String CHILDREN_NAME = "children";
    private String mAttributeNames[];
    private String mAttributeValues[];
    private List mChildrenEles;
    private String mExtensionElementName;
    private String mNamespace;
    private String mText;
}
