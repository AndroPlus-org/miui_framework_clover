// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.*;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import java.util.*;

// Referenced classes of package android.service.autofill:
//            SaveInfo, Dataset

public final class FillResponse
    implements Parcelable
{
    public static final class Builder
    {

        static IntentSender _2D_get0(Builder builder)
        {
            return builder.mAuthentication;
        }

        static AutofillId[] _2D_get1(Builder builder)
        {
            return builder.mAuthenticationIds;
        }

        static Bundle _2D_get2(Builder builder)
        {
            return builder.mCLientState;
        }

        static ArrayList _2D_get3(Builder builder)
        {
            return builder.mDatasets;
        }

        static AutofillId[] _2D_get4(Builder builder)
        {
            return builder.mIgnoredIds;
        }

        static RemoteViews _2D_get5(Builder builder)
        {
            return builder.mPresentation;
        }

        static SaveInfo _2D_get6(Builder builder)
        {
            return builder.mSaveInfo;
        }

        private void throwIfDestroyed()
        {
            if(mDestroyed)
                throw new IllegalStateException("Already called #build()");
            else
                return;
        }

        public Builder addDataset(Dataset dataset)
        {
            throwIfDestroyed();
            if(dataset == null)
                return this;
            if(mDatasets == null)
                mDatasets = new ArrayList();
            if(!mDatasets.add(dataset))
                return this;
            else
                return this;
        }

        public FillResponse build()
        {
            throwIfDestroyed();
            if(mAuthentication == null && mDatasets == null && mSaveInfo == null)
            {
                throw new IllegalArgumentException("need to provide at least one DataSet or a SaveInfo or an authentication with a presentation");
            } else
            {
                mDestroyed = true;
                return new FillResponse(this, null);
            }
        }

        public Builder setAuthentication(AutofillId aautofillid[], IntentSender intentsender, RemoteViews remoteviews)
        {
            boolean flag = true;
            throwIfDestroyed();
            if(aautofillid == null || aautofillid.length == 0)
                throw new IllegalArgumentException("ids cannot be null or empry");
            boolean flag1;
            if(intentsender == null)
                flag1 = true;
            else
                flag1 = false;
            if(remoteviews != null)
                flag = false;
            if(flag ^ flag1)
            {
                throw new IllegalArgumentException("authentication and presentation must be both non-null or null");
            } else
            {
                mAuthentication = intentsender;
                mPresentation = remoteviews;
                mAuthenticationIds = aautofillid;
                return this;
            }
        }

        public Builder setClientState(Bundle bundle)
        {
            throwIfDestroyed();
            mCLientState = bundle;
            return this;
        }

        public transient Builder setIgnoredIds(AutofillId aautofillid[])
        {
            mIgnoredIds = aautofillid;
            return this;
        }

        public Builder setSaveInfo(SaveInfo saveinfo)
        {
            throwIfDestroyed();
            mSaveInfo = saveinfo;
            return this;
        }

        private IntentSender mAuthentication;
        private AutofillId mAuthenticationIds[];
        private Bundle mCLientState;
        private ArrayList mDatasets;
        private boolean mDestroyed;
        private AutofillId mIgnoredIds[];
        private RemoteViews mPresentation;
        private SaveInfo mSaveInfo;

        public Builder()
        {
        }
    }


    private FillResponse(Builder builder)
    {
        ParceledListSlice parceledlistslice = null;
        super();
        if(Builder._2D_get3(builder) != null)
            parceledlistslice = new ParceledListSlice(Builder._2D_get3(builder));
        mDatasets = parceledlistslice;
        mSaveInfo = Builder._2D_get6(builder);
        mClientState = Builder._2D_get2(builder);
        mPresentation = Builder._2D_get5(builder);
        mAuthentication = Builder._2D_get0(builder);
        mAuthenticationIds = Builder._2D_get1(builder);
        mIgnoredIds = Builder._2D_get4(builder);
        mRequestId = 0x80000000;
    }

    FillResponse(Builder builder, FillResponse fillresponse)
    {
        this(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public IntentSender getAuthentication()
    {
        return mAuthentication;
    }

    public AutofillId[] getAuthenticationIds()
    {
        return mAuthenticationIds;
    }

    public Bundle getClientState()
    {
        return mClientState;
    }

    public List getDatasets()
    {
        List list = null;
        if(mDatasets != null)
            list = mDatasets.getList();
        return list;
    }

    public AutofillId[] getIgnoredIds()
    {
        return mIgnoredIds;
    }

    public RemoteViews getPresentation()
    {
        return mPresentation;
    }

    public int getRequestId()
    {
        return mRequestId;
    }

    public SaveInfo getSaveInfo()
    {
        return mSaveInfo;
    }

    public void setRequestId(int i)
    {
        mRequestId = i;
    }

    public String toString()
    {
        boolean flag = true;
        if(!Helper.sDebug)
            return super.toString();
        StringBuilder stringbuilder = (new StringBuilder((new StringBuilder()).append("FillResponse : [mRequestId=").append(mRequestId).toString())).append(", datasets=");
        Object obj;
        boolean flag1;
        if(mDatasets == null)
            obj = "N/A";
        else
            obj = mDatasets.getList();
        obj = stringbuilder.append(obj).append(", saveInfo=").append(mSaveInfo).append(", clientState=");
        if(mClientState != null)
            flag1 = true;
        else
            flag1 = false;
        obj = ((StringBuilder) (obj)).append(flag1).append(", hasPresentation=");
        if(mPresentation != null)
            flag1 = true;
        else
            flag1 = false;
        obj = ((StringBuilder) (obj)).append(flag1).append(", hasAuthentication=");
        if(mAuthentication != null)
            flag1 = flag;
        else
            flag1 = false;
        return ((StringBuilder) (obj)).append(flag1).append(", authenticationIds=").append(Arrays.toString(mAuthenticationIds)).append(", ignoredIds=").append(Arrays.toString(mIgnoredIds)).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mDatasets, i);
        parcel.writeParcelable(mSaveInfo, i);
        parcel.writeParcelable(mClientState, i);
        parcel.writeParcelableArray(mAuthenticationIds, i);
        parcel.writeParcelable(mAuthentication, i);
        parcel.writeParcelable(mPresentation, i);
        parcel.writeParcelableArray(mIgnoredIds, i);
        parcel.writeInt(mRequestId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FillResponse createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            Object obj = (ParceledListSlice)parcel.readParcelable(null);
            int i;
            int j;
            if(obj != null)
                obj = ((ParceledListSlice) (obj)).getList();
            else
                obj = null;
            if(obj != null)
                i = ((List) (obj)).size();
            else
                i = 0;
            for(j = 0; j < i; j++)
                builder.addDataset((Dataset)((List) (obj)).get(j));

            builder.setSaveInfo((SaveInfo)parcel.readParcelable(null));
            builder.setClientState((Bundle)parcel.readParcelable(null));
            AutofillId aautofillid[] = (AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId);
            obj = (IntentSender)parcel.readParcelable(null);
            RemoteViews remoteviews = (RemoteViews)parcel.readParcelable(null);
            if(aautofillid != null)
                builder.setAuthentication(aautofillid, ((IntentSender) (obj)), remoteviews);
            builder.setIgnoredIds((AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId));
            obj = builder.build();
            ((FillResponse) (obj)).setRequestId(parcel.readInt());
            return ((FillResponse) (obj));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FillResponse[] newArray(int i)
        {
            return new FillResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IntentSender mAuthentication;
    private final AutofillId mAuthenticationIds[];
    private final Bundle mClientState;
    private final ParceledListSlice mDatasets;
    private final AutofillId mIgnoredIds[];
    private final RemoteViews mPresentation;
    private int mRequestId;
    private final SaveInfo mSaveInfo;

}
