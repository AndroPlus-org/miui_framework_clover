// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.*;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public final class FillRequest
    implements Parcelable
{

    public FillRequest(int i, ArrayList arraylist, Bundle bundle, int j)
    {
        mId = i;
        mFlags = Preconditions.checkFlagsArgument(j, 1);
        mContexts = (ArrayList)Preconditions.checkCollectionElementsNotNull(arraylist, "contexts");
        mClientState = bundle;
    }

    private FillRequest(Parcel parcel)
    {
        mId = parcel.readInt();
        mContexts = new ArrayList();
        parcel.readParcelableList(mContexts, null);
        mClientState = parcel.readBundle();
        mFlags = parcel.readInt();
    }

    FillRequest(Parcel parcel, FillRequest fillrequest)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public Bundle getClientState()
    {
        return mClientState;
    }

    public List getFillContexts()
    {
        return mContexts;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public int getId()
    {
        return mId;
    }

    public String toString()
    {
        return (new StringBuilder()).append("FillRequest: [id=").append(mId).append(", flags=").append(mFlags).append(", ctxts= ").append(mContexts).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeParcelableList(mContexts, i);
        parcel.writeBundle(mClientState);
        parcel.writeInt(mFlags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FillRequest createFromParcel(Parcel parcel)
        {
            return new FillRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FillRequest[] newArray(int i)
        {
            return new FillRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_MANUAL_REQUEST = 1;
    public static final int INVALID_REQUEST_ID = 0x80000000;
    private final Bundle mClientState;
    private final ArrayList mContexts;
    private final int mFlags;
    private final int mId;

}
