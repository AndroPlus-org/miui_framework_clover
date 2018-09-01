// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.os.Parcel;
import android.os.Parcelable;

public class Token
    implements Parcelable
{

    public Token()
    {
        mVersionCode = -1;
        mPkgName = "";
        mActivityName = "";
        mActivityHashCode = "";
    }

    public Token(Parcel parcel)
    {
        mVersionCode = -1;
        mPkgName = "";
        mActivityName = "";
        mActivityHashCode = "";
        mVersionCode = parcel.readInt();
        mPkgName = parcel.readString();
        mActivityName = parcel.readString();
        mActivityHashCode = parcel.readString();
    }

    public Token(String s, String s1, int i)
    {
        mVersionCode = -1;
        mPkgName = "";
        mActivityName = "";
        mActivityHashCode = "";
        mActivityName = s1;
        mPkgName = s;
        mVersionCode = i;
    }

    public Token(String s, String s1, String s2, int i)
    {
        mVersionCode = -1;
        mPkgName = "";
        mActivityName = "";
        mActivityHashCode = "";
        mActivityName = s1;
        mPkgName = s;
        mVersionCode = i;
        mActivityHashCode = s2;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getActivityHashCode()
    {
        return mActivityHashCode;
    }

    public String getActivityName()
    {
        return mActivityName;
    }

    public String getPkgName()
    {
        return mPkgName;
    }

    public int getVersionCode()
    {
        return mVersionCode;
    }

    public boolean isMatch(Token token)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(token.getPkgName().equals(mPkgName))
        {
            flag1 = flag;
            if(token.getActivityName().equals(mActivityName))
            {
                flag1 = flag;
                if(token.getVersionCode() == mVersionCode)
                {
                    flag1 = flag;
                    if(token.getActivityHashCode() == mActivityHashCode)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Token {pkg name:").append(mPkgName).append(", activity name:").append(mActivityName).append(", activityhashcode:").append(mActivityHashCode).append(", version:").append(mVersionCode).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mVersionCode);
        parcel.writeString(mPkgName);
        parcel.writeString(mActivityName);
        parcel.writeString(mActivityHashCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Token createFromParcel(Parcel parcel)
        {
            return new Token(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public Token[] newArray(int i)
        {
            return new Token[i];
        }

    }
;
    private String mActivityHashCode;
    private String mActivityName;
    private String mPkgName;
    private int mVersionCode;

}
