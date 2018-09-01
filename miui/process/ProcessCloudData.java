// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public class ProcessCloudData
    implements Parcelable
{

    public ProcessCloudData()
    {
    }

    protected ProcessCloudData(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public Map getAppProtectMap()
    {
        return mAppProtectMap;
    }

    public List getCloudWhiteList()
    {
        return mCloudWhiteList;
    }

    public List getFastBootList()
    {
        return mFastBootList;
    }

    public Map getFgProtectMap()
    {
        return mFgProtectMap;
    }

    public void readFromParcel(Parcel parcel)
    {
        if(parcel.readInt() != 0)
            mCloudWhiteList = parcel.readArrayList(java/util/List.getClassLoader());
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            String s = parcel.readString();
            int l = parcel.readInt();
            if(mAppProtectMap == null)
                mAppProtectMap = new HashMap();
            mAppProtectMap.put(s, Integer.valueOf(l));
        }

        i = parcel.readInt();
        for(int k = 0; k < i; k++)
        {
            String s1 = parcel.readString();
            int i1 = parcel.readInt();
            if(mFgProtectMap == null)
                mFgProtectMap = new HashMap();
            mFgProtectMap.put(s1, Integer.valueOf(i1));
        }

        if(parcel.readInt() != 0)
            mFastBootList = parcel.readArrayList(java/util/List.getClassLoader());
    }

    public void setAppProtectMap(Map map)
    {
        mAppProtectMap = map;
    }

    public void setCloudWhiteList(List list)
    {
        mCloudWhiteList = list;
    }

    public void setFastBootList(List list)
    {
        mFastBootList = list;
    }

    public void setFgProtectMap(Map map)
    {
        mFgProtectMap = map;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ProcessCloudData{");
        if(mCloudWhiteList != null)
            stringbuilder.append("mCloudWhiteList=").append(Arrays.toString(mCloudWhiteList.toArray()));
        if(mAppProtectMap != null)
        {
            stringbuilder.append(" mAppProtectMap=");
            Iterator iterator = mAppProtectMap.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj = (java.util.Map.Entry)iterator.next();
                String s1 = (String)((java.util.Map.Entry) (obj)).getKey();
                obj = (Integer)((java.util.Map.Entry) (obj)).getValue();
                if(s1 != null && obj != null)
                    stringbuilder.append("{packageName=").append(s1).append(",priorityLevel=").append(obj).append("} ");
            } while(true);
        }
        if(mFgProtectMap != null)
        {
            stringbuilder.append(" mFgProtectMap=");
            Iterator iterator1 = mFgProtectMap.entrySet().iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                Object obj1 = (java.util.Map.Entry)iterator1.next();
                String s = (String)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = (Integer)((java.util.Map.Entry) (obj1)).getValue();
                if(s != null && obj1 != null)
                    stringbuilder.append("{packageName=").append(s).append(",priorityLevel=").append(obj1).append("} ");
            } while(true);
        }
        if(mFastBootList != null)
            stringbuilder.append(" mFastBootList=").append(Arrays.toString(mFastBootList.toArray()));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mCloudWhiteList != null)
        {
            parcel.writeInt(1);
            parcel.writeList(mCloudWhiteList);
        } else
        {
            parcel.writeInt(0);
        }
        if(mAppProtectMap != null)
        {
            parcel.writeInt(mAppProtectMap.size());
            java.util.Map.Entry entry;
            for(Iterator iterator = mAppProtectMap.entrySet().iterator(); iterator.hasNext(); parcel.writeInt(((Integer)entry.getValue()).intValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                parcel.writeString((String)entry.getKey());
            }

        } else
        {
            parcel.writeInt(0);
        }
        if(mFgProtectMap != null)
        {
            parcel.writeInt(mFgProtectMap.size());
            java.util.Map.Entry entry1;
            for(Iterator iterator1 = mFgProtectMap.entrySet().iterator(); iterator1.hasNext(); parcel.writeInt(((Integer)entry1.getValue()).intValue()))
            {
                entry1 = (java.util.Map.Entry)iterator1.next();
                parcel.writeString((String)entry1.getKey());
            }

        } else
        {
            parcel.writeInt(0);
        }
        if(mFastBootList != null)
        {
            parcel.writeInt(1);
            parcel.writeList(mFastBootList);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProcessCloudData createFromParcel(Parcel parcel)
        {
            return new ProcessCloudData(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ProcessCloudData[] newArray(int i)
        {
            return new ProcessCloudData[i];
        }

    }
;
    private Map mAppProtectMap;
    private List mCloudWhiteList;
    private List mFastBootList;
    private Map mFgProtectMap;

}
