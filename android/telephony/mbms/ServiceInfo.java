// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.*;

public class ServiceInfo
{

    protected ServiceInfo(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i > 1000 || i < 0)
            throw new RuntimeException((new StringBuilder()).append("bad map length").append(i).toString());
        names = new HashMap(i);
        for(; i > 0; i--)
        {
            Locale locale = (Locale)parcel.readSerializable();
            String s = parcel.readString();
            names.put(locale, s);
        }

        className = parcel.readString();
        i = parcel.readInt();
        if(i > 1000 || i < 0)
            throw new RuntimeException((new StringBuilder()).append("bad locale length ").append(i).toString());
        locales = new ArrayList(i);
        for(; i > 0; i--)
        {
            Locale locale1 = (Locale)parcel.readSerializable();
            locales.add(locale1);
        }

        serviceId = parcel.readString();
        sessionStartTime = (Date)parcel.readSerializable();
        sessionEndTime = (Date)parcel.readSerializable();
    }

    public ServiceInfo(Map map, String s, List list, String s1, Date date, Date date1)
    {
        while(map == null || map.isEmpty() || TextUtils.isEmpty(s) || list == null || list.isEmpty() || TextUtils.isEmpty(s1) || date == null || date1 == null) 
            throw new IllegalArgumentException("Bad ServiceInfo construction");
        if(map.size() > 1000)
            throw new RuntimeException((new StringBuilder()).append("bad map length ").append(map.size()).toString());
        if(list.size() > 1000)
        {
            throw new RuntimeException((new StringBuilder()).append("bad locales length ").append(list.size()).toString());
        } else
        {
            names = new HashMap(map.size());
            names.putAll(map);
            className = s;
            locales = new ArrayList(list);
            serviceId = s1;
            sessionStartTime = (Date)date.clone();
            sessionEndTime = (Date)date1.clone();
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof ServiceInfo))
            return false;
        obj = (ServiceInfo)obj;
        boolean flag1 = flag;
        if(Objects.equals(names, ((ServiceInfo) (obj)).names))
        {
            flag1 = flag;
            if(Objects.equals(className, ((ServiceInfo) (obj)).className))
            {
                flag1 = flag;
                if(Objects.equals(locales, ((ServiceInfo) (obj)).locales))
                {
                    flag1 = flag;
                    if(Objects.equals(serviceId, ((ServiceInfo) (obj)).serviceId))
                    {
                        flag1 = flag;
                        if(Objects.equals(sessionStartTime, ((ServiceInfo) (obj)).sessionStartTime))
                            flag1 = Objects.equals(sessionEndTime, ((ServiceInfo) (obj)).sessionEndTime);
                    }
                }
            }
        }
        return flag1;
    }

    public List getLocales()
    {
        return locales;
    }

    public CharSequence getNameForLocale(Locale locale)
    {
        if(!names.containsKey(locale))
            throw new NoSuchElementException("Locale not supported");
        else
            return (CharSequence)names.get(locale);
    }

    public Set getNamedContentLocales()
    {
        return Collections.unmodifiableSet(names.keySet());
    }

    public String getServiceClassName()
    {
        return className;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public Date getSessionEndTime()
    {
        return sessionEndTime;
    }

    public Date getSessionStartTime()
    {
        return sessionStartTime;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            names, className, locales, serviceId, sessionStartTime, sessionEndTime
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj = names.keySet();
        parcel.writeInt(((Set) (obj)).size());
        for(Iterator iterator1 = ((Iterable) (obj)).iterator(); iterator1.hasNext(); parcel.writeString((String)names.get(obj)))
        {
            obj = (Locale)iterator1.next();
            parcel.writeSerializable(((java.io.Serializable) (obj)));
        }

        parcel.writeString(className);
        parcel.writeInt(locales.size());
        for(Iterator iterator = locales.iterator(); iterator.hasNext(); parcel.writeSerializable((Locale)iterator.next()));
        parcel.writeString(serviceId);
        parcel.writeSerializable(sessionStartTime);
        parcel.writeSerializable(sessionEndTime);
    }

    static final int MAP_LIMIT = 1000;
    private final String className;
    private final List locales;
    private final Map names;
    private final String serviceId;
    private final Date sessionEndTime;
    private final Date sessionStartTime;
}
