// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.io.PrintWriter;

// Referenced classes of package android.os:
//            Parcelable, Parcel, Binder, Process

public final class UserHandle
    implements Parcelable
{

    public UserHandle(int i)
    {
        mHandle = i;
    }

    public UserHandle(Parcel parcel)
    {
        mHandle = parcel.readInt();
    }

    public static String formatUid(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        formatUid(stringbuilder, i);
        return stringbuilder.toString();
    }

    public static void formatUid(PrintWriter printwriter, int i)
    {
        if(i < 10000)
        {
            printwriter.print(i);
        } else
        {
            printwriter.print('u');
            printwriter.print(getUserId(i));
            i = getAppId(i);
            if(i >= 0x182b8 && i <= 0x1869f)
            {
                printwriter.print('i');
                printwriter.print(i - 0x182b8);
            } else
            if(i >= 10000)
            {
                printwriter.print('a');
                printwriter.print(i - 10000);
            } else
            {
                printwriter.print('s');
                printwriter.print(i);
            }
        }
    }

    public static void formatUid(StringBuilder stringbuilder, int i)
    {
        if(i < 10000)
        {
            stringbuilder.append(i);
        } else
        {
            stringbuilder.append('u');
            stringbuilder.append(getUserId(i));
            i = getAppId(i);
            if(i >= 0x182b8 && i <= 0x1869f)
            {
                stringbuilder.append('i');
                stringbuilder.append(i - 0x182b8);
            } else
            if(i >= 10000)
            {
                stringbuilder.append('a');
                stringbuilder.append(i - 10000);
            } else
            {
                stringbuilder.append('s');
                stringbuilder.append(i);
            }
        }
    }

    public static int getAppId(int i)
    {
        return i % 0x186a0;
    }

    public static int getAppIdFromSharedAppGid(int i)
    {
        i = (getAppId(i) + 10000) - 50000;
        if(i < 0 || i >= 50000)
            return -1;
        else
            return i;
    }

    public static int getCacheAppGid(int i)
    {
        return getCacheAppGid(getUserId(i), getAppId(i));
    }

    public static int getCacheAppGid(int i, int j)
    {
        if(j >= 10000 && j <= 19999)
            return getUid(i, (j - 10000) + 20000);
        else
            return -1;
    }

    public static int getCallingAppId()
    {
        return getAppId(Binder.getCallingUid());
    }

    public static int getCallingUserId()
    {
        return getUserId(Binder.getCallingUid());
    }

    public static int getSharedAppGid(int i)
    {
        return getSharedAppGid(getUserId(i), getAppId(i));
    }

    public static int getSharedAppGid(int i, int j)
    {
        if(j >= 10000 && j <= 19999)
            return (j - 10000) + 50000;
        if(j >= 0 && j <= 10000)
            return j;
        else
            return -1;
    }

    public static int getUid(int i, int j)
    {
        return i * 0x186a0 + j % 0x186a0;
    }

    public static int getUserGid(int i)
    {
        return getUid(i, 9997);
    }

    public static UserHandle getUserHandleForUid(int i)
    {
        return of(getUserId(i));
    }

    public static int getUserId(int i)
    {
        return i / 0x186a0;
    }

    public static boolean isApp(int i)
    {
        boolean flag = false;
        if(i > 0)
        {
            i = getAppId(i);
            boolean flag1 = flag;
            if(i >= 10000)
            {
                flag1 = flag;
                if(i <= 19999)
                    flag1 = true;
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public static boolean isIsolated(int i)
    {
        boolean flag = false;
        if(i > 0)
        {
            i = getAppId(i);
            boolean flag1 = flag;
            if(i >= 0x182b8)
            {
                flag1 = flag;
                if(i <= 0x1869f)
                    flag1 = true;
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public static boolean isSameApp(int i, int j)
    {
        boolean flag;
        if(getAppId(i) == getAppId(j))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isSameUser(int i, int j)
    {
        boolean flag;
        if(getUserId(i) == getUserId(j))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int myUserId()
    {
        return getUserId(Process.myUid());
    }

    public static UserHandle of(int i)
    {
        UserHandle userhandle;
        if(i == 0)
            userhandle = SYSTEM;
        else
            userhandle = new UserHandle(i);
        return userhandle;
    }

    public static int parseUserArg(String s)
    {
        int i;
        if("all".equals(s))
            i = -1;
        else
        if("current".equals(s) || "cur".equals(s))
            i = -2;
        else
            try
            {
                i = Integer.parseInt(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Bad user number: ").append(s).toString());
            }
        return i;
    }

    public static UserHandle readFromParcel(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i != -10000)
            parcel = new UserHandle(i);
        else
            parcel = null;
        return parcel;
    }

    public static void writeToParcel(UserHandle userhandle, Parcel parcel)
    {
        if(userhandle != null)
            userhandle.writeToParcel(parcel, 0);
        else
            parcel.writeInt(-10000);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = false;
        if(obj == null)
            break MISSING_BLOCK_LABEL_33;
        int i;
        int j;
        obj = (UserHandle)obj;
        i = mHandle;
        j = ((UserHandle) (obj)).mHandle;
        if(i == j)
            flag = true;
        return flag;
        obj;
        return false;
    }

    public int getIdentifier()
    {
        return mHandle;
    }

    public int hashCode()
    {
        return mHandle;
    }

    public boolean isOwner()
    {
        return equals(OWNER);
    }

    public boolean isSystem()
    {
        return equals(SYSTEM);
    }

    public String toString()
    {
        return (new StringBuilder()).append("UserHandle{").append(mHandle).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mHandle);
    }

    public static final int AID_APP_END = 19999;
    public static final int AID_APP_START = 10000;
    public static final int AID_CACHE_GID_START = 20000;
    public static final int AID_ROOT = 0;
    public static final int AID_SHARED_GID_START = 50000;
    public static final UserHandle ALL = new UserHandle(-1);
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public UserHandle createFromParcel(Parcel parcel)
        {
            return new UserHandle(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UserHandle[] newArray(int i)
        {
            return new UserHandle[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final UserHandle CURRENT = new UserHandle(-2);
    public static final UserHandle CURRENT_OR_SELF = new UserHandle(-3);
    public static final int ERR_GID = -1;
    public static final boolean MU_ENABLED = true;
    public static final UserHandle OWNER = new UserHandle(0);
    public static final int PER_USER_RANGE = 0x186a0;
    public static final UserHandle SYSTEM = new UserHandle(0);
    public static final int USER_ALL = -1;
    public static final int USER_CURRENT = -2;
    public static final int USER_CURRENT_OR_SELF = -3;
    public static final int USER_NULL = -10000;
    public static final int USER_OWNER = 0;
    public static final int USER_SERIAL_SYSTEM = 0;
    public static final int USER_SYSTEM = 0;
    final int mHandle;

}
