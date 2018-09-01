// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.*;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.service.autofill:
//            FillContext

public final class SaveRequest
    implements Parcelable
{

    private SaveRequest(Parcel parcel)
    {
        this(parcel.createTypedArrayList(FillContext.CREATOR), parcel.readBundle());
    }

    SaveRequest(Parcel parcel, SaveRequest saverequest)
    {
        this(parcel);
    }

    public SaveRequest(ArrayList arraylist, Bundle bundle)
    {
        mFillContexts = (ArrayList)Preconditions.checkNotNull(arraylist, "fillContexts");
        mClientState = bundle;
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
        return mFillContexts;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(mFillContexts, i);
        parcel.writeBundle(mClientState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SaveRequest createFromParcel(Parcel parcel)
        {
            return new SaveRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SaveRequest[] newArray(int i)
        {
            return new SaveRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Bundle mClientState;
    private final ArrayList mFillContexts;

}
