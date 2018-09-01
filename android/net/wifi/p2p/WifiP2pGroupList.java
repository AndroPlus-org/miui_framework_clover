// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.LruCache;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p:
//            WifiP2pGroup, WifiP2pDevice

public class WifiP2pGroupList
    implements Parcelable
{
    public static interface GroupDeleteListener
    {

        public abstract void onDeleteGroup(int i);
    }


    static boolean _2D_get0(WifiP2pGroupList wifip2pgrouplist)
    {
        return wifip2pgrouplist.isClearCalled;
    }

    static GroupDeleteListener _2D_get1(WifiP2pGroupList wifip2pgrouplist)
    {
        return wifip2pgrouplist.mListener;
    }

    public WifiP2pGroupList()
    {
        this(null, null);
    }

    public WifiP2pGroupList(WifiP2pGroupList wifip2pgrouplist, GroupDeleteListener groupdeletelistener)
    {
        isClearCalled = false;
        mListener = groupdeletelistener;
        mGroups = new LruCache(32) {

            protected void entryRemoved(boolean flag, Integer integer, WifiP2pGroup wifip2pgroup, WifiP2pGroup wifip2pgroup1)
            {
                if(WifiP2pGroupList._2D_get1(WifiP2pGroupList.this) != null && WifiP2pGroupList._2D_get0(WifiP2pGroupList.this) ^ true)
                    WifiP2pGroupList._2D_get1(WifiP2pGroupList.this).onDeleteGroup(wifip2pgroup.getNetworkId());
            }

            protected volatile void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
            {
                entryRemoved(flag, (Integer)obj, (WifiP2pGroup)obj1, (WifiP2pGroup)obj2);
            }

            final WifiP2pGroupList this$0;

            
            {
                this$0 = WifiP2pGroupList.this;
                super(i);
            }
        }
;
        if(wifip2pgrouplist != null)
            for(wifip2pgrouplist = wifip2pgrouplist.mGroups.snapshot().entrySet().iterator(); wifip2pgrouplist.hasNext(); mGroups.put((Integer)groupdeletelistener.getKey(), (WifiP2pGroup)groupdeletelistener.getValue()))
                groupdeletelistener = (java.util.Map.Entry)wifip2pgrouplist.next();

    }

    public void add(WifiP2pGroup wifip2pgroup)
    {
        mGroups.put(Integer.valueOf(wifip2pgroup.getNetworkId()), wifip2pgroup);
    }

    public boolean clear()
    {
        if(mGroups.size() == 0)
        {
            return false;
        } else
        {
            isClearCalled = true;
            mGroups.evictAll();
            isClearCalled = false;
            return true;
        }
    }

    public boolean contains(int i)
    {
        for(Iterator iterator = mGroups.snapshot().values().iterator(); iterator.hasNext();)
            if(i == ((WifiP2pGroup)iterator.next()).getNetworkId())
                return true;

        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public Collection getGroupList()
    {
        return mGroups.snapshot().values();
    }

    public int getNetworkId(String s)
    {
        if(s == null)
            return -1;
        for(Iterator iterator = mGroups.snapshot().values().iterator(); iterator.hasNext();)
        {
            WifiP2pGroup wifip2pgroup = (WifiP2pGroup)iterator.next();
            if(s.equalsIgnoreCase(wifip2pgroup.getOwner().deviceAddress))
            {
                mGroups.get(Integer.valueOf(wifip2pgroup.getNetworkId()));
                return wifip2pgroup.getNetworkId();
            }
        }

        return -1;
    }

    public int getNetworkId(String s, String s1)
    {
        if(s == null || s1 == null)
            return -1;
        for(Iterator iterator = mGroups.snapshot().values().iterator(); iterator.hasNext();)
        {
            WifiP2pGroup wifip2pgroup = (WifiP2pGroup)iterator.next();
            if(s.equalsIgnoreCase(wifip2pgroup.getOwner().deviceAddress) && s1.equals(wifip2pgroup.getNetworkName()))
            {
                mGroups.get(Integer.valueOf(wifip2pgroup.getNetworkId()));
                return wifip2pgroup.getNetworkId();
            }
        }

        return -1;
    }

    public String getOwnerAddr(int i)
    {
        WifiP2pGroup wifip2pgroup = (WifiP2pGroup)mGroups.get(Integer.valueOf(i));
        if(wifip2pgroup != null)
            return wifip2pgroup.getOwner().deviceAddress;
        else
            return null;
    }

    public void remove(int i)
    {
        mGroups.remove(Integer.valueOf(i));
    }

    void remove(String s)
    {
        remove(getNetworkId(s));
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(Iterator iterator = mGroups.snapshot().values().iterator(); iterator.hasNext(); stringbuffer.append((WifiP2pGroup)iterator.next()).append("\n"));
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj = mGroups.snapshot().values();
        parcel.writeInt(((Collection) (obj)).size());
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); parcel.writeParcelable((WifiP2pGroup)((Iterator) (obj)).next(), i));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pGroupList createFromParcel(Parcel parcel)
        {
            WifiP2pGroupList wifip2pgrouplist = new WifiP2pGroupList();
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
                wifip2pgrouplist.add((WifiP2pGroup)parcel.readParcelable(null));

            return wifip2pgrouplist;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pGroupList[] newArray(int i)
        {
            return new WifiP2pGroupList[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int CREDENTIAL_MAX_NUM = 32;
    private boolean isClearCalled;
    private final LruCache mGroups;
    private final GroupDeleteListener mListener;

}
