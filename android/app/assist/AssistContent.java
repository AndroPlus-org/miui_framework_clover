// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.assist;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.*;

public class AssistContent
    implements Parcelable
{

    public AssistContent()
    {
        mIsAppProvidedIntent = false;
        mIsAppProvidedWebUri = false;
        mExtras = new Bundle();
    }

    AssistContent(Parcel parcel)
    {
        boolean flag = true;
        super();
        mIsAppProvidedIntent = false;
        mIsAppProvidedWebUri = false;
        if(parcel.readInt() != 0)
            mIntent = (Intent)Intent.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            mClipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            mUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            mStructuredData = parcel.readString();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsAppProvidedIntent = flag1;
        mExtras = parcel.readBundle();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsAppProvidedWebUri = flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public ClipData getClipData()
    {
        return mClipData;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public String getStructuredData()
    {
        return mStructuredData;
    }

    public Uri getWebUri()
    {
        return mUri;
    }

    public boolean isAppProvidedIntent()
    {
        return mIsAppProvidedIntent;
    }

    public boolean isAppProvidedWebUri()
    {
        return mIsAppProvidedWebUri;
    }

    public void setClipData(ClipData clipdata)
    {
        mClipData = clipdata;
    }

    public void setDefaultIntent(Intent intent)
    {
        mIntent = intent;
        mIsAppProvidedIntent = false;
        mIsAppProvidedWebUri = false;
        mUri = null;
        if(intent != null && "android.intent.action.VIEW".equals(intent.getAction()))
        {
            intent = intent.getData();
            if(intent != null && ("http".equals(intent.getScheme()) || "https".equals(intent.getScheme())))
                mUri = intent;
        }
    }

    public void setIntent(Intent intent)
    {
        mIsAppProvidedIntent = true;
        mIntent = intent;
    }

    public void setStructuredData(String s)
    {
        mStructuredData = s;
    }

    public void setWebUri(Uri uri)
    {
        mIsAppProvidedWebUri = true;
        mUri = uri;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    void writeToParcelInternal(Parcel parcel, int i)
    {
        boolean flag = true;
        if(mIntent != null)
        {
            parcel.writeInt(1);
            mIntent.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mClipData != null)
        {
            parcel.writeInt(1);
            mClipData.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mUri != null)
        {
            parcel.writeInt(1);
            mUri.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mStructuredData != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mStructuredData);
        } else
        {
            parcel.writeInt(0);
        }
        if(mIsAppProvidedIntent)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeBundle(mExtras);
        if(mIsAppProvidedWebUri)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AssistContent createFromParcel(Parcel parcel)
        {
            return new AssistContent(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AssistContent[] newArray(int i)
        {
            return new AssistContent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private ClipData mClipData;
    private final Bundle mExtras;
    private Intent mIntent;
    private boolean mIsAppProvidedIntent;
    private boolean mIsAppProvidedWebUri;
    private String mStructuredData;
    private Uri mUri;

}
