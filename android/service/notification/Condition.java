// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class Condition
    implements Parcelable
{

    public Condition(Uri uri, String s, int i)
    {
        this(uri, s, "", "", -1, i, 2);
    }

    public Condition(Uri uri, String s, String s1, String s2, int i, int j, int k)
    {
        if(uri == null)
            throw new IllegalArgumentException("id is required");
        if(s == null)
            throw new IllegalArgumentException("summary is required");
        if(!isValidState(j))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("state is invalid: ").append(j).toString());
        } else
        {
            id = uri;
            summary = s;
            line1 = s1;
            line2 = s2;
            icon = i;
            state = j;
            flags = k;
            return;
        }
    }

    public Condition(Parcel parcel)
    {
        this((Uri)parcel.readParcelable(android/service/notification/Condition.getClassLoader()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static boolean isValidId(Uri uri, String s)
    {
        boolean flag;
        if(uri != null && "condition".equals(uri.getScheme()))
            flag = s.equals(uri.getAuthority());
        else
            flag = false;
        return flag;
    }

    private static boolean isValidState(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 3)
                flag1 = true;
        }
        return flag1;
    }

    public static android.net.Uri.Builder newId(Context context)
    {
        return (new android.net.Uri.Builder()).scheme("condition").authority(context.getPackageName());
    }

    public static String relevanceToString(int i)
    {
        boolean flag;
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        if((i & 2) != 0)
            i = 1;
        else
            i = 0;
        if(!flag && (i ^ 1) != 0)
            return "NONE";
        if(flag && i != 0)
            return "NOW, ALWAYS";
        String s;
        if(flag)
            s = "NOW";
        else
            s = "ALWAYS";
        return s;
    }

    public static String stateToString(int i)
    {
        if(i == 0)
            return "STATE_FALSE";
        if(i == 1)
            return "STATE_TRUE";
        if(i == 2)
            return "STATE_UNKNOWN";
        if(i == 3)
            return "STATE_ERROR";
        else
            throw new IllegalArgumentException((new StringBuilder()).append("state is invalid: ").append(i).toString());
    }

    public Condition copy()
    {
        Parcel parcel = Parcel.obtain();
        Condition condition;
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        condition = new Condition(parcel);
        parcel.recycle();
        return condition;
        Exception exception;
        exception;
        parcel.recycle();
        throw exception;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(!(obj instanceof Condition))
            return false;
        if(obj == this)
            return true;
        obj = (Condition)obj;
        if(Objects.equals(((Condition) (obj)).id, id) && Objects.equals(((Condition) (obj)).summary, summary) && Objects.equals(((Condition) (obj)).line1, line1) && Objects.equals(((Condition) (obj)).line2, line2) && ((Condition) (obj)).icon == icon && ((Condition) (obj)).state == state)
        {
            if(((Condition) (obj)).flags != flags)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            id, summary, line1, line2, Integer.valueOf(icon), Integer.valueOf(state), Integer.valueOf(flags)
        });
    }

    public String toString()
    {
        return (new StringBuilder(android/service/notification/Condition.getSimpleName())).append('[').append("id=").append(id).append(",summary=").append(summary).append(",line1=").append(line1).append(",line2=").append(line2).append(",icon=").append(icon).append(",state=").append(stateToString(state)).append(",flags=").append(flags).append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(id, 0);
        parcel.writeString(summary);
        parcel.writeString(line1);
        parcel.writeString(line2);
        parcel.writeInt(icon);
        parcel.writeInt(state);
        parcel.writeInt(flags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Condition createFromParcel(Parcel parcel)
        {
            return new Condition(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Condition[] newArray(int i)
        {
            return new Condition[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_RELEVANT_ALWAYS = 2;
    public static final int FLAG_RELEVANT_NOW = 1;
    public static final String SCHEME = "condition";
    public static final int STATE_ERROR = 3;
    public static final int STATE_FALSE = 0;
    public static final int STATE_TRUE = 1;
    public static final int STATE_UNKNOWN = 2;
    public final int flags;
    public final int icon;
    public final Uri id;
    public final String line1;
    public final String line2;
    public final int state;
    public final String summary;

}
