// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcel;
import android.view.autofill.Helper;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.service.autofill:
//            InternalValidator, ValueFinder

final class RequiredValidators extends InternalValidator
{

    RequiredValidators(InternalValidator ainternalvalidator[])
    {
        mValidators = (InternalValidator[])Preconditions.checkArrayElementsNotNull(ainternalvalidator, "validators");
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid(ValueFinder valuefinder)
    {
        InternalValidator ainternalvalidator[] = mValidators;
        int i = ainternalvalidator.length;
        for(int j = 0; j < i; j++)
            if(!ainternalvalidator[j].isValid(valuefinder))
                return false;

        return true;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder("RequiredValidators: [validators=")).append(mValidators).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelableArray(mValidators, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RequiredValidators createFromParcel(Parcel parcel)
        {
            return new RequiredValidators((InternalValidator[])parcel.readParcelableArray(null, android/service/autofill/InternalValidator));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RequiredValidators[] newArray(int i)
        {
            return new RequiredValidators[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final InternalValidator mValidators[];

}
