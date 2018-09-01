// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package miui.contentcatcher.sdk:
//            Token

public class ClientCatcherResult
    implements Parcelable
{

    public ClientCatcherResult()
    {
        mResults = new HashMap();
        mPropertyMap = new HashMap();
    }

    private ClientCatcherResult(Parcel parcel)
    {
        mResults = new HashMap();
        mPropertyMap = new HashMap();
        mInjectorToken = (Token)parcel.readParcelable(ClassLoader.getSystemClassLoader());
        mResults = parcel.readHashMap(ClassLoader.getSystemClassLoader());
        mPropertyMap = parcel.readHashMap(ClassLoader.getSystemClassLoader());
    }

    ClientCatcherResult(Parcel parcel, ClientCatcherResult clientcatcherresult)
    {
        this(parcel);
    }

    public void addContent(String s, String s1, Object obj)
    {
        if(!mResults.containsKey(s))
        {
            HashMap hashmap = new HashMap();
            mResults.put(s, hashmap);
        }
        ((Map)mResults.get(s)).put(s1, obj);
    }

    public void addProperty(String s, Object obj)
    {
        mPropertyMap.put(s, obj);
    }

    public int describeContents()
    {
        return 0;
    }

    public Token getInjectorToken()
    {
        return mInjectorToken;
    }

    public Map getParams(String s)
    {
        return (Map)mResults.get(s);
    }

    public Map getPropertyMap()
    {
        return mPropertyMap;
    }

    public Map getResults()
    {
        return mResults;
    }

    public void setInjectorToken(Token token)
    {
        mInjectorToken = token;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("result {");
        stringbuffer.append((new StringBuilder()).append("injector token : ").append(mInjectorToken.toString()).append("\n").toString());
        for(Iterator iterator = mResults.entrySet().iterator(); iterator.hasNext(); stringbuffer.append("}"))
        {
            Object obj1 = (java.util.Map.Entry)iterator.next();
            String s1 = (String)((java.util.Map.Entry) (obj1)).getKey();
            Object obj2 = (HashMap)((java.util.Map.Entry) (obj1)).getValue();
            StringBuilder stringbuilder = (new StringBuilder()).append("jobTag : ");
            obj1 = s1;
            if(s1 == null)
                obj1 = "null";
            stringbuffer.append(stringbuilder.append(((String) (obj1))).append("\n").toString());
            stringbuffer.append("params is : {");
            String s2;
            for(Iterator iterator1 = ((HashMap) (obj2)).entrySet().iterator(); iterator1.hasNext(); stringbuffer.append((new StringBuilder()).append("key :").append(s2).append(" value : ").append(obj2).toString()))
            {
                obj2 = (java.util.Map.Entry)iterator1.next();
                s2 = (String)((java.util.Map.Entry) (obj2)).getKey();
                obj2 = ((java.util.Map.Entry) (obj2)).getValue();
            }

        }

        stringbuffer.append("}");
        stringbuffer.append("; property{");
        for(Iterator iterator2 = mPropertyMap.entrySet().iterator(); iterator2.hasNext(); stringbuffer.append("; "))
        {
            Object obj = (java.util.Map.Entry)iterator2.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = ((java.util.Map.Entry) (obj)).getValue();
            stringbuffer.append((new StringBuilder()).append("propertykey:").append(s).append(",propertyValue:").append(obj).toString());
        }

        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mInjectorToken, i);
        parcel.writeMap(mResults);
        parcel.writeMap(mPropertyMap);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ClientCatcherResult createFromParcel(Parcel parcel)
        {
            return new ClientCatcherResult(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ClientCatcherResult[] newArray(int i)
        {
            return new ClientCatcherResult[i];
        }

    }
;
    private Token mInjectorToken;
    private Map mPropertyMap;
    private Map mResults;

}
