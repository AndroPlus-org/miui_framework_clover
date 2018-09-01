// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.service.autofill:
//            InternalTransformation, Transformation, ValueFinder

public final class CharSequenceTransformation extends InternalTransformation
    implements Transformation, Parcelable
{
    public static class Builder
    {

        static ArrayMap _2D_get0(Builder builder)
        {
            return builder.mFields;
        }

        private void throwIfDestroyed()
        {
            Preconditions.checkState(mDestroyed ^ true, "Already called build()");
        }

        public Builder addField(AutofillId autofillid, Pattern pattern, String s)
        {
            throwIfDestroyed();
            Preconditions.checkNotNull(autofillid);
            Preconditions.checkNotNull(pattern);
            Preconditions.checkNotNull(s);
            mFields.put(autofillid, new Pair(pattern, s));
            return this;
        }

        public CharSequenceTransformation build()
        {
            throwIfDestroyed();
            mDestroyed = true;
            return new CharSequenceTransformation(this, null);
        }

        private boolean mDestroyed;
        private final ArrayMap mFields = new ArrayMap();

        public Builder(AutofillId autofillid, Pattern pattern, String s)
        {
            addField(autofillid, pattern, s);
        }
    }


    private CharSequenceTransformation(Builder builder)
    {
        mFields = Builder._2D_get0(builder);
    }

    CharSequenceTransformation(Builder builder, CharSequenceTransformation charsequencetransformation)
    {
        this(builder);
    }

    public void apply(ValueFinder valuefinder, RemoteViews remoteviews, int i)
        throws Exception
    {
        StringBuilder stringbuilder;
        int j;
        int k;
        stringbuilder = new StringBuilder();
        j = mFields.size();
        if(Helper.sDebug)
            Log.d("CharSequenceTransformation", (new StringBuilder()).append(j).append(" multiple fields on id ").append(i).toString());
        k = 0;
_L2:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        AutofillId autofillid = (AutofillId)mFields.keyAt(k);
        Pair pair = (Pair)mFields.valueAt(k);
        Object obj = valuefinder.findByAutofillId(autofillid);
        if(obj == null)
        {
            Log.w("CharSequenceTransformation", (new StringBuilder()).append("No value for id ").append(autofillid).toString());
            return;
        }
        try
        {
            obj = ((Pattern)pair.first).matcher(((CharSequence) (obj)));
            if(!((Matcher) (obj)).find())
            {
                if(Helper.sDebug)
                {
                    valuefinder = JVM INSTR new #48  <Class StringBuilder>;
                    valuefinder.StringBuilder();
                    Log.d("CharSequenceTransformation", valuefinder.append("match for ").append(pair.first).append(" failed on id ").append(autofillid).toString());
                }
                return;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ValueFinder valuefinder)
        {
            Log.w("CharSequenceTransformation", (new StringBuilder()).append("Cannot apply ").append(((Pattern)pair.first).pattern()).append("->").append((String)pair.second).append(" to ").append("field with autofill id").append(autofillid).append(": ").append(valuefinder.getClass()).toString());
            throw valuefinder;
        }
        stringbuilder.append(((Matcher) (obj)).replaceAll((String)pair.second));
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        remoteviews.setCharSequence(i, "setText", stringbuilder);
        return;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder()).append("MultipleViewsCharSequenceTransformation: [fields=").append(mFields).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = mFields.size();
        AutofillId aautofillid[] = new AutofillId[j];
        java.io.Serializable serializable = new Pattern[j];
        String as[] = new String[j];
        for(int k = 0; k < j; k++)
        {
            aautofillid[k] = (AutofillId)mFields.keyAt(k);
            Pair pair = (Pair)mFields.valueAt(k);
            serializable[k] = (Pattern)pair.first;
            as[k] = (String)pair.second;
        }

        parcel.writeParcelableArray(aautofillid, i);
        parcel.writeSerializable(serializable);
        parcel.writeStringArray(as);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CharSequenceTransformation createFromParcel(Parcel parcel)
        {
            AutofillId aautofillid[] = (AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId);
            Pattern apattern[] = (Pattern[])parcel.readSerializable();
            parcel = parcel.createStringArray();
            Builder builder = new Builder(aautofillid[0], apattern[0], parcel[0]);
            int i = aautofillid.length;
            for(int j = 1; j < i; j++)
                builder.addField(aautofillid[j], apattern[j], parcel[j]);

            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CharSequenceTransformation[] newArray(int i)
        {
            return new CharSequenceTransformation[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "CharSequenceTransformation";
    private final ArrayMap mFields;

}
