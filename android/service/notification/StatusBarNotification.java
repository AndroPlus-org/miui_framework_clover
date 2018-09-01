// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.*;

public class StatusBarNotification
    implements Parcelable
{

    public StatusBarNotification(Parcel parcel)
    {
        pkg = parcel.readString();
        opPkg = parcel.readString();
        id = parcel.readInt();
        if(parcel.readInt() != 0)
            tag = parcel.readString();
        else
            tag = null;
        uid = parcel.readInt();
        initialPid = parcel.readInt();
        notification = new Notification(parcel);
        user = UserHandle.readFromParcel(parcel);
        postTime = parcel.readLong();
        if(parcel.readInt() != 0)
            overrideGroupKey = parcel.readString();
        else
            overrideGroupKey = null;
        key = key();
        groupKey = groupKey();
    }

    public StatusBarNotification(String s, String s1, int i, String s2, int j, int k, int l, 
            Notification notification1, UserHandle userhandle, long l1)
    {
        if(s == null)
            throw new NullPointerException();
        if(notification1 == null)
        {
            throw new NullPointerException();
        } else
        {
            pkg = s;
            opPkg = s1;
            id = i;
            tag = s2;
            uid = j;
            initialPid = k;
            notification = notification1;
            user = userhandle;
            postTime = l1;
            key = key();
            groupKey = groupKey();
            return;
        }
    }

    public StatusBarNotification(String s, String s1, int i, String s2, int j, int k, Notification notification1, 
            UserHandle userhandle, String s3, long l)
    {
        if(s == null)
            throw new NullPointerException();
        if(notification1 == null)
        {
            throw new NullPointerException();
        } else
        {
            pkg = s;
            opPkg = s1;
            id = i;
            tag = s2;
            uid = j;
            initialPid = k;
            notification = notification1;
            user = userhandle;
            postTime = l;
            overrideGroupKey = s3;
            key = key();
            groupKey = groupKey();
            return;
        }
    }

    private String groupKey()
    {
        if(overrideGroupKey != null)
            return (new StringBuilder()).append(user.getIdentifier()).append("|").append(pkg).append("|").append("g:").append(overrideGroupKey).toString();
        String s = getNotification().getGroup();
        Object obj = getNotification().getSortKey();
        if(s == null && obj == null)
            return key;
        obj = (new StringBuilder()).append(user.getIdentifier()).append("|").append(pkg).append("|");
        if(s == null)
            s = (new StringBuilder()).append("c:").append(notification.getChannelId()).toString();
        else
            s = (new StringBuilder()).append("g:").append(s).toString();
        return ((StringBuilder) (obj)).append(s).toString();
    }

    private String key()
    {
        String s = (new StringBuilder()).append(user.getIdentifier()).append("|").append(pkg).append("|").append(id).append("|").append(tag).append("|").append(uid).toString();
        String s1 = s;
        if(overrideGroupKey != null)
        {
            s1 = s;
            if(getNotification().isGroupSummary())
                s1 = (new StringBuilder()).append(s).append("|").append(overrideGroupKey).toString();
        }
        return s1;
    }

    public StatusBarNotification clone()
    {
        return new StatusBarNotification(pkg, opPkg, id, tag, uid, initialPid, notification.clone(), user, overrideGroupKey, postTime);
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public StatusBarNotification cloneLight()
    {
        Notification notification1 = new Notification();
        notification.cloneInto(notification1, false);
        return new StatusBarNotification(pkg, opPkg, id, tag, uid, initialPid, notification1, user, overrideGroupKey, postTime);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getGroup()
    {
        if(overrideGroupKey != null)
            return overrideGroupKey;
        else
            return getNotification().getGroup();
    }

    public String getGroupKey()
    {
        return groupKey;
    }

    public int getId()
    {
        return id;
    }

    public int getInitialPid()
    {
        return initialPid;
    }

    public String getKey()
    {
        return key;
    }

    public Notification getNotification()
    {
        return notification;
    }

    public String getOpPkg()
    {
        return opPkg;
    }

    public String getOverrideGroupKey()
    {
        return overrideGroupKey;
    }

    public Context getPackageContext(Context context)
    {
        if(mContext == null)
            try
            {
                mContext = context.createApplicationContext(context.getPackageManager().getApplicationInfoAsUser(pkg, 8192, getUserId()), 4);
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                mContext = null;
            }
        if(mContext == null)
            mContext = context;
        return mContext;
    }

    public String getPackageName()
    {
        return pkg;
    }

    public long getPostTime()
    {
        return postTime;
    }

    public String getTag()
    {
        return tag;
    }

    public int getUid()
    {
        return uid;
    }

    public UserHandle getUser()
    {
        return user;
    }

    public int getUserId()
    {
        return user.getIdentifier();
    }

    public boolean isAppGroup()
    {
        return getNotification().getGroup() != null || getNotification().getSortKey() != null;
    }

    public boolean isClearable()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((notification.flags & 2) == 0)
        {
            flag1 = flag;
            if((notification.flags & 0x20) == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isGroup()
    {
        return overrideGroupKey != null || isAppGroup();
    }

    public boolean isOngoing()
    {
        boolean flag = false;
        if((notification.flags & 2) != 0)
            flag = true;
        return flag;
    }

    public void setOverrideGroupKey(String s)
    {
        overrideGroupKey = s;
        groupKey = groupKey();
    }

    public String toString()
    {
        return String.format("StatusBarNotification(pkg=%s user=%s id=%d tag=%s key=%s: %s)", new Object[] {
            pkg, user, Integer.valueOf(id), tag, key, notification
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(pkg);
        parcel.writeString(opPkg);
        parcel.writeInt(id);
        if(tag != null)
        {
            parcel.writeInt(1);
            parcel.writeString(tag);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(uid);
        parcel.writeInt(initialPid);
        notification.writeToParcel(parcel, i);
        user.writeToParcel(parcel, i);
        parcel.writeLong(postTime);
        if(overrideGroupKey != null)
        {
            parcel.writeInt(1);
            parcel.writeString(overrideGroupKey);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StatusBarNotification createFromParcel(Parcel parcel)
        {
            return new StatusBarNotification(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StatusBarNotification[] newArray(int i)
        {
            return new StatusBarNotification[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String groupKey;
    private final int id;
    private final int initialPid;
    private final String key;
    private Context mContext;
    private final Notification notification;
    private final String opPkg;
    private String overrideGroupKey;
    private final String pkg;
    private final long postTime;
    private final String tag;
    private final int uid;
    private final UserHandle user;

}
