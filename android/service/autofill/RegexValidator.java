// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import com.android.internal.util.Preconditions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.service.autofill:
//            InternalValidator, Validator, ValueFinder

public final class RegexValidator extends InternalValidator
    implements Validator, Parcelable
{

    public RegexValidator(AutofillId autofillid, Pattern pattern)
    {
        mId = (AutofillId)Preconditions.checkNotNull(autofillid);
        mRegex = (Pattern)Preconditions.checkNotNull(pattern);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid(ValueFinder valuefinder)
    {
        valuefinder = valuefinder.findByAutofillId(mId);
        if(valuefinder == null)
        {
            Log.w("RegexValidator", (new StringBuilder()).append("No view for id ").append(mId).toString());
            return false;
        }
        boolean flag = mRegex.matcher(valuefinder).matches();
        if(Helper.sDebug)
            Log.d("RegexValidator", (new StringBuilder()).append("isValid(): ").append(flag).toString());
        return flag;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder()).append("RegexValidator: [id=").append(mId).append(", regex=").append(mRegex).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mId, i);
        parcel.writeSerializable(mRegex);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RegexValidator createFromParcel(Parcel parcel)
        {
            return new RegexValidator((AutofillId)parcel.readParcelable(null), (Pattern)parcel.readSerializable());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RegexValidator[] newArray(int i)
        {
            return new RegexValidator[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "RegexValidator";
    private final AutofillId mId;
    private final Pattern mRegex;

}
