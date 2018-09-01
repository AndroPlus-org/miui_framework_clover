// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.autofill.*;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;

public final class Dataset
    implements Parcelable
{
    public static final class Builder
    {

        static IntentSender _2D_get0(Builder builder)
        {
            return builder.mAuthentication;
        }

        static ArrayList _2D_get1(Builder builder)
        {
            return builder.mFieldIds;
        }

        static ArrayList _2D_get2(Builder builder)
        {
            return builder.mFieldPresentations;
        }

        static ArrayList _2D_get3(Builder builder)
        {
            return builder.mFieldValues;
        }

        static String _2D_get4(Builder builder)
        {
            return builder.mId;
        }

        static RemoteViews _2D_get5(Builder builder)
        {
            return builder.mPresentation;
        }

        static void _2D_wrap0(Builder builder, AutofillId autofillid, AutofillValue autofillvalue, RemoteViews remoteviews)
        {
            builder.setValueAndPresentation(autofillid, autofillvalue, remoteviews);
        }

        private void setValueAndPresentation(AutofillId autofillid, AutofillValue autofillvalue, RemoteViews remoteviews)
        {
            Preconditions.checkNotNull(autofillid, "id cannot be null");
            if(mFieldIds != null)
            {
                int i = mFieldIds.indexOf(autofillid);
                if(i >= 0)
                {
                    mFieldValues.set(i, autofillvalue);
                    mFieldPresentations.set(i, remoteviews);
                    return;
                }
            } else
            {
                mFieldIds = new ArrayList();
                mFieldValues = new ArrayList();
                mFieldPresentations = new ArrayList();
            }
            mFieldIds.add(autofillid);
            mFieldValues.add(autofillvalue);
            mFieldPresentations.add(remoteviews);
        }

        private void throwIfDestroyed()
        {
            if(mDestroyed)
                throw new IllegalStateException("Already called #build()");
            else
                return;
        }

        public Dataset build()
        {
            throwIfDestroyed();
            mDestroyed = true;
            if(mFieldIds == null)
                throw new IllegalArgumentException("at least one value must be set");
            else
                return new Dataset(this, null);
        }

        public Builder setAuthentication(IntentSender intentsender)
        {
            throwIfDestroyed();
            mAuthentication = intentsender;
            return this;
        }

        public Builder setId(String s)
        {
            throwIfDestroyed();
            mId = s;
            return this;
        }

        public Builder setValue(AutofillId autofillid, AutofillValue autofillvalue)
        {
            throwIfDestroyed();
            if(mPresentation == null)
            {
                throw new IllegalStateException("Dataset presentation not set on constructor");
            } else
            {
                setValueAndPresentation(autofillid, autofillvalue, null);
                return this;
            }
        }

        public Builder setValue(AutofillId autofillid, AutofillValue autofillvalue, RemoteViews remoteviews)
        {
            throwIfDestroyed();
            Preconditions.checkNotNull(remoteviews, "presentation cannot be null");
            setValueAndPresentation(autofillid, autofillvalue, remoteviews);
            return this;
        }

        private IntentSender mAuthentication;
        private boolean mDestroyed;
        private ArrayList mFieldIds;
        private ArrayList mFieldPresentations;
        private ArrayList mFieldValues;
        private String mId;
        private RemoteViews mPresentation;

        public Builder()
        {
        }

        public Builder(RemoteViews remoteviews)
        {
            Preconditions.checkNotNull(remoteviews, "presentation must be non-null");
            mPresentation = remoteviews;
        }
    }


    private Dataset(Builder builder)
    {
        mFieldIds = Builder._2D_get1(builder);
        mFieldValues = Builder._2D_get3(builder);
        mFieldPresentations = Builder._2D_get2(builder);
        mPresentation = Builder._2D_get5(builder);
        mAuthentication = Builder._2D_get0(builder);
        mId = Builder._2D_get4(builder);
    }

    Dataset(Builder builder, Dataset dataset)
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

    public ArrayList getFieldIds()
    {
        return mFieldIds;
    }

    public RemoteViews getFieldPresentation(int i)
    {
        RemoteViews remoteviews = (RemoteViews)mFieldPresentations.get(i);
        if(remoteviews == null)
            remoteviews = mPresentation;
        return remoteviews;
    }

    public ArrayList getFieldValues()
    {
        return mFieldValues;
    }

    public String getId()
    {
        return mId;
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(mFieldIds != null)
            flag = mFieldIds.isEmpty();
        else
            flag = true;
        return flag;
    }

    public String toString()
    {
        boolean flag = true;
        if(!Helper.sDebug)
            return super.toString();
        StringBuilder stringbuilder = (new StringBuilder((new StringBuilder()).append("Dataset ").append(mId).append(" [").toString())).append("fieldIds=").append(mFieldIds).append(", fieldValues=").append(mFieldValues).append(", fieldPresentations=");
        int i;
        boolean flag1;
        if(mFieldPresentations == null)
            i = 0;
        else
            i = mFieldPresentations.size();
        stringbuilder = stringbuilder.append(i).append(", hasPresentation=");
        if(mPresentation != null)
            flag1 = true;
        else
            flag1 = false;
        stringbuilder = stringbuilder.append(flag1).append(", hasAuthentication=");
        if(mAuthentication != null)
            flag1 = flag;
        else
            flag1 = false;
        return stringbuilder.append(flag1).append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mPresentation, i);
        parcel.writeTypedList(mFieldIds, i);
        parcel.writeTypedList(mFieldValues, i);
        parcel.writeParcelableList(mFieldPresentations, i);
        parcel.writeParcelable(mAuthentication, i);
        parcel.writeString(mId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Dataset createFromParcel(Parcel parcel)
        {
            Object obj = (RemoteViews)parcel.readParcelable(null);
            ArrayList arraylist;
            ArrayList arraylist1;
            ArrayList arraylist2;
            int i;
            int j;
            int k;
            if(obj == null)
                obj = new Builder();
            else
                obj = new Builder(((RemoteViews) (obj)));
            arraylist = parcel.createTypedArrayList(AutofillId.CREATOR);
            arraylist1 = parcel.createTypedArrayList(AutofillValue.CREATOR);
            arraylist2 = new ArrayList();
            parcel.readParcelableList(arraylist2, null);
            if(arraylist != null)
                i = arraylist.size();
            else
                i = 0;
            if(arraylist1 != null)
                j = arraylist1.size();
            else
                j = 0;
            k = 0;
            while(k < i) 
            {
                AutofillId autofillid = (AutofillId)arraylist.get(k);
                AutofillValue autofillvalue;
                RemoteViews remoteviews;
                if(j > k)
                    autofillvalue = (AutofillValue)arraylist1.get(k);
                else
                    autofillvalue = null;
                if(arraylist2.isEmpty())
                    remoteviews = null;
                else
                    remoteviews = (RemoteViews)arraylist2.get(k);
                Builder._2D_wrap0(((Builder) (obj)), autofillid, autofillvalue, remoteviews);
                k++;
            }
            ((Builder) (obj)).setAuthentication((IntentSender)parcel.readParcelable(null));
            ((Builder) (obj)).setId(parcel.readString());
            return ((Builder) (obj)).build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Dataset[] newArray(int i)
        {
            return new Dataset[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IntentSender mAuthentication;
    private final ArrayList mFieldIds;
    private final ArrayList mFieldPresentations;
    private final ArrayList mFieldValues;
    String mId;
    private final RemoteViews mPresentation;

}
