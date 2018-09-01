// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package miui.contentcatcher.sdk:
//            Token

public class Content
    implements Parcelable
{

    public Content(int i, String s, Token token, Map map)
    {
        mCatchType = 0;
        mCatcherToken = null;
        mInjectorToken = null;
        mResultMap = null;
        mFavIntent = null;
        mCatchType = i;
        mCatcherToken = s;
        mInjectorToken = token;
        mResultMap = map;
    }

    public Content(Intent intent)
    {
        mCatchType = 0;
        mCatcherToken = null;
        mInjectorToken = null;
        mResultMap = null;
        mFavIntent = null;
        mFavIntent = intent;
    }

    private Content(Parcel parcel)
    {
        mCatchType = 0;
        mCatcherToken = null;
        mInjectorToken = null;
        mResultMap = null;
        mFavIntent = null;
        mCatchType = parcel.readInt();
        mCatcherToken = parcel.readString();
        mInjectorToken = (Token)parcel.readParcelable(null);
        mResultMap = parcel.readHashMap(ClassLoader.getSystemClassLoader());
        mFavIntent = (Intent)parcel.readParcelable(null);
    }

    Content(Parcel parcel, Content content)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public Object getInjectorToken()
    {
        return mInjectorToken;
    }

    public List getTargetTokens()
    {
        return null;
    }

    public String toString()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        stringbuffer.append("Content{ ");
        if(mFavIntent == null) goto _L2; else goto _L1
_L1:
        stringbuffer.append((new StringBuilder()).append("mFavIntent=").append(mFavIntent).append("; ").toString());
_L4:
        stringbuffer.append("}");
        return stringbuffer.toString();
_L2:
        stringbuffer.append((new StringBuilder()).append("mCatchType=").append(mCatchType).append("; ").toString());
        stringbuffer.append((new StringBuilder()).append("mCatcherToken=").append(mCatcherToken).append("; ").toString());
        if(mInjectorToken != null)
            stringbuffer.append((new StringBuilder()).append("mInjectorToken=").append(mInjectorToken.getPkgName()).append(",").append(mInjectorToken.getActivityName()).append(",").append(mInjectorToken.getVersionCode()).toString());
        if(mResultMap == null) goto _L4; else goto _L3
_L3:
        Iterator iterator;
        stringbuffer.append("mResultMap[ ");
        iterator = mResultMap.keySet().iterator();
_L6:
        String s;
        String s1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s = (String)iterator.next();
        s1 = "";
        String s2 = mResultMap.get(s).toString();
        s1 = s2;
_L7:
        stringbuffer.append((new StringBuilder()).append(s).append("=").append(s1).append("; ").toString());
        if(true) goto _L6; else goto _L5
_L5:
        stringbuffer.append(" ]");
          goto _L4
        Exception exception;
        exception;
          goto _L7
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCatchType);
        parcel.writeString(mCatcherToken);
        parcel.writeParcelable(mInjectorToken, i);
        parcel.writeMap(mResultMap);
        parcel.writeParcelable(mFavIntent, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Content createFromParcel(Parcel parcel)
        {
            return new Content(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public Content[] newArray(int i)
        {
            return new Content[i];
        }

    }
;
    public int mCatchType;
    public String mCatcherToken;
    public Intent mFavIntent;
    public Token mInjectorToken;
    public Map mResultMap;

}
