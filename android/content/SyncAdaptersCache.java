// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.pm.RegisteredServicesCache;
import android.content.pm.XmlSerializerAndParser;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.*;

// Referenced classes of package android.content:
//            SyncAdapterType, ComponentName, Context

public class SyncAdaptersCache extends RegisteredServicesCache
{
    static class MySerializer
        implements XmlSerializerAndParser
    {

        public SyncAdapterType createFromXml(XmlPullParser xmlpullparser)
            throws IOException, XmlPullParserException
        {
            return SyncAdapterType.newKey(xmlpullparser.getAttributeValue(null, "authority"), xmlpullparser.getAttributeValue(null, "accountType"));
        }

        public volatile Object createFromXml(XmlPullParser xmlpullparser)
            throws IOException, XmlPullParserException
        {
            return createFromXml(xmlpullparser);
        }

        public void writeAsXml(SyncAdapterType syncadaptertype, XmlSerializer xmlserializer)
            throws IOException
        {
            xmlserializer.attribute(null, "authority", syncadaptertype.authority);
            xmlserializer.attribute(null, "accountType", syncadaptertype.accountType);
        }

        public volatile void writeAsXml(Object obj, XmlSerializer xmlserializer)
            throws IOException
        {
            writeAsXml((SyncAdapterType)obj, xmlserializer);
        }

        MySerializer()
        {
        }
    }


    public SyncAdaptersCache(Context context)
    {
        super(context, "android.content.SyncAdapter", "android.content.SyncAdapter", "sync-adapter", sSerializer);
        mAuthorityToSyncAdapters = new SparseArray();
    }

    public String[] getSyncAdapterPackagesForAuthority(String s, int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (ArrayMap)mAuthorityToSyncAdapters.get(i);
        ArrayMap arraymap;
        arraymap = ((ArrayMap) (obj1));
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_49;
        arraymap = JVM INSTR new #55  <Class ArrayMap>;
        arraymap.ArrayMap();
        mAuthorityToSyncAdapters.put(i, arraymap);
        if(!arraymap.containsKey(s))
            break MISSING_BLOCK_LABEL_72;
        s = (String[])arraymap.get(s);
        obj;
        JVM INSTR monitorexit ;
        return s;
        java.util.Collection collection = getAllServices(i);
        obj1 = JVM INSTR new #75  <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        Iterator iterator = collection.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            android.content.pm.RegisteredServicesCache.ServiceInfo serviceinfo = (android.content.pm.RegisteredServicesCache.ServiceInfo)iterator.next();
            if(s.equals(((SyncAdapterType)serviceinfo.type).authority) && serviceinfo.componentName != null)
                ((ArrayList) (obj1)).add(serviceinfo.componentName.getPackageName());
        } while(true);
        break MISSING_BLOCK_LABEL_168;
        s;
        throw s;
        String as[];
        as = new String[((ArrayList) (obj1)).size()];
        ((ArrayList) (obj1)).toArray(as);
        arraymap.put(s, as);
        obj;
        JVM INSTR monitorexit ;
        return as;
    }

    protected void onServicesChangedLocked(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = (ArrayMap)mAuthorityToSyncAdapters.get(i);
        if(arraymap == null)
            break MISSING_BLOCK_LABEL_27;
        arraymap.clear();
        obj;
        JVM INSTR monitorexit ;
        super.onServicesChangedLocked(i);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void onUserRemoved(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        mAuthorityToSyncAdapters.remove(i);
        obj;
        JVM INSTR monitorexit ;
        super.onUserRemoved(i);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public SyncAdapterType parseServiceAttributes(Resources resources, String s, AttributeSet attributeset)
    {
        resources = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.SyncAdapter);
        String s1;
        boolean flag;
        attributeset = resources.getString(2);
        s1 = resources.getString(1);
        if(TextUtils.isEmpty(attributeset))
            break MISSING_BLOCK_LABEL_41;
        flag = TextUtils.isEmpty(s1);
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        resources.recycle();
        return null;
        s = new SyncAdapterType(attributeset, s1, resources.getBoolean(3, true), resources.getBoolean(4, true), resources.getBoolean(6, false), resources.getBoolean(5, false), resources.getString(0), s);
        resources.recycle();
        return s;
        s;
        resources.recycle();
        throw s;
    }

    public volatile Object parseServiceAttributes(Resources resources, String s, AttributeSet attributeset)
    {
        return parseServiceAttributes(resources, s, attributeset);
    }

    private static final String ATTRIBUTES_NAME = "sync-adapter";
    private static final String SERVICE_INTERFACE = "android.content.SyncAdapter";
    private static final String SERVICE_META_DATA = "android.content.SyncAdapter";
    private static final String TAG = "Account";
    private static final MySerializer sSerializer = new MySerializer();
    private SparseArray mAuthorityToSyncAdapters;

}
