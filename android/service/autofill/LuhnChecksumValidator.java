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

// Referenced classes of package android.service.autofill:
//            InternalValidator, Validator, ValueFinder

public final class LuhnChecksumValidator extends InternalValidator
    implements Validator, Parcelable
{

    public transient LuhnChecksumValidator(AutofillId aautofillid[])
    {
        mIds = (AutofillId[])Preconditions.checkArrayElementsNotNull(aautofillid, "ids");
    }

    private static boolean isLuhnChecksumValid(String s)
    {
        boolean flag = false;
        int i = 0;
        boolean flag1 = false;
        int j = s.length() - 1;
        while(j >= 0) 
        {
            int k = s.charAt(j) - 48;
            int l = ((flag1) ? 1 : 0);
            int i1 = i;
            if(k >= 0)
                if(k > 9)
                {
                    i1 = i;
                    l = ((flag1) ? 1 : 0);
                } else
                {
                    if(flag1)
                    {
                        i1 = k * 2;
                        l = i1;
                        if(i1 > 9)
                            l = i1 - 9;
                    } else
                    {
                        l = k;
                    }
                    i1 = i + l;
                    l = flag1 ^ true;
                }
            j--;
            flag1 = l;
            i = i1;
        }
        if(i % 10 == 0)
            flag = true;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid(ValueFinder valuefinder)
    {
        if(mIds == null || mIds.length == 0)
            return false;
        StringBuilder stringbuilder = new StringBuilder();
        AutofillId aautofillid[] = mIds;
        int i = aautofillid.length;
        for(int j = 0; j < i; j++)
        {
            AutofillId autofillid = aautofillid[j];
            String s = valuefinder.findByAutofillId(autofillid);
            if(s == null)
            {
                if(Helper.sDebug)
                    Log.d("LuhnChecksumValidator", (new StringBuilder()).append("No partial number for id ").append(autofillid).toString());
                return false;
            }
            stringbuilder.append(s);
        }

        return isLuhnChecksumValid(stringbuilder.toString());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelableArray(mIds, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LuhnChecksumValidator createFromParcel(Parcel parcel)
        {
            return new LuhnChecksumValidator((AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LuhnChecksumValidator[] newArray(int i)
        {
            return new LuhnChecksumValidator[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "LuhnChecksumValidator";
    private final AutofillId mIds[];

}
