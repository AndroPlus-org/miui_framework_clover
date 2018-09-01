// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.ArrayMap;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.*;

// Referenced classes of package android.os:
//            BaseBundle, Parcelable, Bundle, Parcel

public final class PersistableBundle extends BaseBundle
    implements Cloneable, Parcelable, com.android.internal.util.XmlUtils.WriteMapCallback
{
    static class MyReadMapCallback
        implements com.android.internal.util.XmlUtils.ReadMapCallback
    {

        public Object readThisUnknownObjectXml(XmlPullParser xmlpullparser, String s)
            throws XmlPullParserException, IOException
        {
            if("pbundle_as_map".equals(s))
                return PersistableBundle.restoreFromXml(xmlpullparser);
            else
                throw new XmlPullParserException((new StringBuilder()).append("Unknown tag=").append(s).toString());
        }

        MyReadMapCallback()
        {
        }
    }


    public PersistableBundle()
    {
        mFlags = 1;
    }

    public PersistableBundle(int i)
    {
        super(i);
        mFlags = 1;
    }

    public PersistableBundle(Bundle bundle)
    {
        this(bundle.getMap());
    }

    PersistableBundle(Parcel parcel, int i)
    {
        super(parcel, i);
        mFlags = 1;
    }

    public PersistableBundle(PersistableBundle persistablebundle)
    {
        super(persistablebundle);
        mFlags = persistablebundle.mFlags;
    }

    private PersistableBundle(ArrayMap arraymap)
    {
        int i;
        int j;
        mFlags = 1;
        putAll(arraymap);
        i = mMap.size();
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_154;
        arraymap = ((ArrayMap) (mMap.valueAt(j)));
        if(arraymap instanceof ArrayMap)
        {
            mMap.setValueAt(j, new PersistableBundle((ArrayMap)arraymap));
        } else
        {
            if(!(arraymap instanceof Bundle))
                continue; /* Loop/switch isn't completed */
            mMap.setValueAt(j, new PersistableBundle((Bundle)arraymap));
        }
_L3:
        j++;
          goto _L1
        if(isValidType(arraymap)) goto _L3; else goto _L2
_L2:
        throw new IllegalArgumentException((new StringBuilder()).append("Bad value in PersistableBundle key=").append((String)mMap.keyAt(j)).append(" value=").append(arraymap).toString());
    }

    PersistableBundle(boolean flag)
    {
        super(flag);
    }

    public static PersistableBundle forPair(String s, String s1)
    {
        PersistableBundle persistablebundle = new PersistableBundle(1);
        persistablebundle.putString(s, s1);
        return persistablebundle;
    }

    public static boolean isValidType(Object obj)
    {
        boolean flag;
        if((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof int[]) || (obj instanceof long[]) || (obj instanceof double[]) || (obj instanceof String[]) || (obj instanceof PersistableBundle) || obj == null || (obj instanceof Boolean))
            flag = true;
        else
            flag = obj instanceof boolean[];
        return flag;
    }

    public static PersistableBundle restoreFromXml(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        int i = xmlpullparser.getDepth();
        String s = xmlpullparser.getName();
        String as[] = new String[1];
        do
        {
            int j = xmlpullparser.next();
            if(j != 1 && (j != 3 || xmlpullparser.getDepth() < i))
            {
                if(j == 2)
                    return new PersistableBundle(XmlUtils.readThisArrayMapXml(xmlpullparser, s, as, new MyReadMapCallback()));
            } else
            {
                return EMPTY;
            }
        } while(true);
    }

    public Object clone()
    {
        return new PersistableBundle(this);
    }

    public PersistableBundle deepCopy()
    {
        PersistableBundle persistablebundle = new PersistableBundle(false);
        persistablebundle.copyInternal(this, true);
        return persistablebundle;
    }

    public int describeContents()
    {
        return 0;
    }

    public PersistableBundle getPersistableBundle(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        PersistableBundle persistablebundle;
        try
        {
            persistablebundle = (PersistableBundle)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Bundle", classcastexception);
            return null;
        }
        return persistablebundle;
    }

    public void putPersistableBundle(String s, PersistableBundle persistablebundle)
    {
        unparcel();
        mMap.put(s, persistablebundle);
    }

    public void saveToXml(XmlSerializer xmlserializer)
        throws IOException, XmlPullParserException
    {
        unparcel();
        XmlUtils.writeMapXml(mMap, xmlserializer, this);
    }

    public String toShortString()
    {
        this;
        JVM INSTR monitorenter ;
        if(mParcelledData == null)
            break MISSING_BLOCK_LABEL_53;
        if(!isEmptyParcel())
            break MISSING_BLOCK_LABEL_21;
        this;
        JVM INSTR monitorexit ;
        return "EMPTY_PARCEL";
        Object obj;
        obj = JVM INSTR new #93  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("mParcelledData.dataSize=").append(mParcelledData.dataSize()).toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj = mMap.toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        if(mParcelledData == null)
            break MISSING_BLOCK_LABEL_58;
        if(!isEmptyParcel())
            break MISSING_BLOCK_LABEL_21;
        this;
        JVM INSTR monitorexit ;
        return "PersistableBundle[EMPTY_PARCEL]";
        Object obj;
        obj = JVM INSTR new #93  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("PersistableBundle[mParcelledData.dataSize=").append(mParcelledData.dataSize()).append("]").toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        obj = JVM INSTR new #93  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("PersistableBundle[").append(mMap.toString()).append("]").toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = parcel.pushAllowFds(false);
        writeToParcelInner(parcel, i);
        parcel.restoreAllowFds(flag);
        return;
        Exception exception;
        exception;
        parcel.restoreAllowFds(flag);
        throw exception;
    }

    public void writeUnknownObject(Object obj, String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(obj instanceof PersistableBundle)
        {
            xmlserializer.startTag(null, "pbundle_as_map");
            xmlserializer.attribute(null, "name", s);
            ((PersistableBundle)obj).saveToXml(xmlserializer);
            xmlserializer.endTag(null, "pbundle_as_map");
            return;
        } else
        {
            throw new XmlPullParserException((new StringBuilder()).append("Unknown Object o=").append(obj).toString());
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public PersistableBundle createFromParcel(Parcel parcel)
        {
            return parcel.readPersistableBundle();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PersistableBundle[] newArray(int i)
        {
            return new PersistableBundle[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final PersistableBundle EMPTY;
    private static final String TAG_PERSISTABLEMAP = "pbundle_as_map";

    static 
    {
        EMPTY = new PersistableBundle();
        EMPTY.mMap = ArrayMap.EMPTY;
    }
}
