// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.PrintWriter;

// Referenced classes of package android.content:
//            Context

public final class ComponentName
    implements Parcelable, Cloneable, Comparable
{

    public ComponentName(Context context, Class class1)
    {
        mPackage = context.getPackageName();
        mClass = class1.getName();
    }

    public ComponentName(Context context, String s)
    {
        if(s == null)
        {
            throw new NullPointerException("class name is null");
        } else
        {
            mPackage = context.getPackageName();
            mClass = s;
            return;
        }
    }

    public ComponentName(Parcel parcel)
    {
        mPackage = parcel.readString();
        if(mPackage == null)
            throw new NullPointerException("package name is null");
        mClass = parcel.readString();
        if(mClass == null)
            throw new NullPointerException("class name is null");
        else
            return;
    }

    private ComponentName(String s, Parcel parcel)
    {
        mPackage = s;
        mClass = parcel.readString();
    }

    public ComponentName(String s, String s1)
    {
        if(s == null)
            throw new NullPointerException("package name is null");
        if(s1 == null)
        {
            throw new NullPointerException("class name is null");
        } else
        {
            mPackage = s;
            mClass = s1;
            return;
        }
    }

    private static void appendShortClassName(StringBuilder stringbuilder, String s, String s1)
    {
        if(s1.startsWith(s))
        {
            int i = s.length();
            int j = s1.length();
            if(j > i && s1.charAt(i) == '.')
            {
                stringbuilder.append(s1, i, j);
                return;
            }
        }
        stringbuilder.append(s1);
    }

    public static void appendShortString(StringBuilder stringbuilder, String s, String s1)
    {
        stringbuilder.append(s).append('/');
        appendShortClassName(stringbuilder, s, s1);
    }

    public static ComponentName createRelative(Context context, String s)
    {
        return createRelative(context.getPackageName(), s);
    }

    public static ComponentName createRelative(String s, String s1)
    {
        if(TextUtils.isEmpty(s1))
            throw new IllegalArgumentException("class name cannot be empty");
        if(s1.charAt(0) == '.')
            s1 = (new StringBuilder()).append(s).append(s1).toString();
        return new ComponentName(s, s1);
    }

    private static void printShortClassName(PrintWriter printwriter, String s, String s1)
    {
        if(s1.startsWith(s))
        {
            int i = s.length();
            int j = s1.length();
            if(j > i && s1.charAt(i) == '.')
            {
                printwriter.write(s1, i, j - i);
                return;
            }
        }
        printwriter.print(s1);
    }

    public static void printShortString(PrintWriter printwriter, String s, String s1)
    {
        printwriter.print(s);
        printwriter.print('/');
        printShortClassName(printwriter, s, s1);
    }

    public static ComponentName readFromParcel(Parcel parcel)
    {
        ComponentName componentname = null;
        String s = parcel.readString();
        if(s != null)
            componentname = new ComponentName(s, parcel);
        return componentname;
    }

    public static ComponentName unflattenFromString(String s)
    {
        int i = s.indexOf('/');
        if(i < 0 || i + 1 >= s.length())
            return null;
        String s1 = s.substring(0, i);
        String s2 = s.substring(i + 1);
        s = s2;
        if(s2.length() > 0)
        {
            s = s2;
            if(s2.charAt(0) == '.')
                s = (new StringBuilder()).append(s1).append(s2).toString();
        }
        return new ComponentName(s1, s);
    }

    public static void writeToParcel(ComponentName componentname, Parcel parcel)
    {
        if(componentname != null)
            componentname.writeToParcel(parcel, 0);
        else
            parcel.writeString(null);
    }

    public void appendShortString(StringBuilder stringbuilder)
    {
        appendShortString(stringbuilder, mPackage, mClass);
    }

    public ComponentName clone()
    {
        return new ComponentName(mPackage, mClass);
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int compareTo(ComponentName componentname)
    {
        int i = mPackage.compareTo(componentname.mPackage);
        if(i != 0)
            return i;
        else
            return mClass.compareTo(componentname.mClass);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((ComponentName)obj);
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
            break MISSING_BLOCK_LABEL_40;
        obj = (ComponentName)obj;
        if(mPackage.equals(((ComponentName) (obj)).mPackage))
            flag = mClass.equals(((ComponentName) (obj)).mClass);
        return flag;
        obj;
        return false;
    }

    public String flattenToShortString()
    {
        StringBuilder stringbuilder = new StringBuilder(mPackage.length() + mClass.length());
        appendShortString(stringbuilder, mPackage, mClass);
        return stringbuilder.toString();
    }

    public String flattenToString()
    {
        return (new StringBuilder()).append(mPackage).append("/").append(mClass).toString();
    }

    public String getClassName()
    {
        return mClass;
    }

    public String getPackageName()
    {
        return mPackage;
    }

    public String getShortClassName()
    {
        if(mClass.startsWith(mPackage))
        {
            int i = mPackage.length();
            int j = mClass.length();
            if(j > i && mClass.charAt(i) == '.')
                return mClass.substring(i, j);
        }
        return mClass;
    }

    public int hashCode()
    {
        return mPackage.hashCode() + mClass.hashCode();
    }

    public String toShortString()
    {
        return (new StringBuilder()).append("{").append(mPackage).append("/").append(mClass).append("}").toString();
    }

    public String toString()
    {
        return (new StringBuilder()).append("ComponentInfo{").append(mPackage).append("/").append(mClass).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackage);
        parcel.writeString(mClass);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ComponentName createFromParcel(Parcel parcel)
        {
            return new ComponentName(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ComponentName[] newArray(int i)
        {
            return new ComponentName[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mClass;
    private final String mPackage;

}
