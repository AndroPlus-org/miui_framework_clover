// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.service.autofill:
//            InternalTransformation, Transformation, ValueFinder

public final class ImageTransformation extends InternalTransformation
    implements Transformation, Parcelable
{
    public static class Builder
    {

        static AutofillId _2D_get0(Builder builder)
        {
            return builder.mId;
        }

        static ArrayList _2D_get1(Builder builder)
        {
            return builder.mOptions;
        }

        private void throwIfDestroyed()
        {
            Preconditions.checkState(mDestroyed ^ true, "Already called build()");
        }

        public Builder addOption(Pattern pattern, int i)
        {
            boolean flag = false;
            throwIfDestroyed();
            Preconditions.checkNotNull(pattern);
            if(i != 0)
                flag = true;
            Preconditions.checkArgument(flag);
            mOptions.add(new Pair(pattern, Integer.valueOf(i)));
            return this;
        }

        public ImageTransformation build()
        {
            throwIfDestroyed();
            mDestroyed = true;
            return new ImageTransformation(this, null);
        }

        private boolean mDestroyed;
        private final AutofillId mId;
        private final ArrayList mOptions = new ArrayList();

        public Builder(AutofillId autofillid, Pattern pattern, int i)
        {
            mId = (AutofillId)Preconditions.checkNotNull(autofillid);
            addOption(pattern, i);
        }
    }


    private ImageTransformation(Builder builder)
    {
        mId = Builder._2D_get0(builder);
        mOptions = Builder._2D_get1(builder);
    }

    ImageTransformation(Builder builder, ImageTransformation imagetransformation)
    {
        this(builder);
    }

    public void apply(ValueFinder valuefinder, RemoteViews remoteviews, int i)
        throws Exception
    {
        Object obj = valuefinder.findByAutofillId(mId);
        if(obj == null)
        {
            Log.w("ImageTransformation", (new StringBuilder()).append("No view for id ").append(mId).toString());
            return;
        }
        int j = mOptions.size();
        if(Helper.sDebug)
            Log.d("ImageTransformation", (new StringBuilder()).append(j).append(" multiple options on id ").append(i).append(" to compare against").toString());
        for(int k = 0; k < j; k++)
        {
            valuefinder = (Pair)mOptions.get(k);
            try
            {
                if(((Pattern)((Pair) (valuefinder)).first).matcher(((CharSequence) (obj))).matches())
                {
                    obj = JVM INSTR new #62  <Class StringBuilder>;
                    ((StringBuilder) (obj)).StringBuilder();
                    Log.d("ImageTransformation", ((StringBuilder) (obj)).append("Found match at ").append(k).append(": ").append(valuefinder).toString());
                    remoteviews.setImageViewResource(i, ((Integer)((Pair) (valuefinder)).second).intValue());
                    return;
                }
            }
            // Misplaced declaration of an exception variable
            catch(RemoteViews remoteviews)
            {
                Log.w("ImageTransformation", (new StringBuilder()).append("Error matching regex #").append(k).append("(").append(((Pattern)((Pair) (valuefinder)).first).pattern()).append(") on id ").append(((Pair) (valuefinder)).second).append(": ").append(remoteviews.getClass()).toString());
                throw remoteviews;
            }
        }

        if(Helper.sDebug)
            Log.d("ImageTransformation", (new StringBuilder()).append("No match for ").append(((String) (obj))).toString());
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
            return (new StringBuilder()).append("ImageTransformation: [id=").append(mId).append(", options=").append(mOptions).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mId, i);
        int j = mOptions.size();
        java.io.Serializable serializable = new Pattern[j];
        int ai[] = new int[j];
        for(i = 0; i < j; i++)
        {
            Pair pair = (Pair)mOptions.get(i);
            serializable[i] = (Pattern)pair.first;
            ai[i] = ((Integer)pair.second).intValue();
        }

        parcel.writeSerializable(serializable);
        parcel.writeIntArray(ai);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImageTransformation createFromParcel(Parcel parcel)
        {
            Object obj = (AutofillId)parcel.readParcelable(null);
            Pattern apattern[] = (Pattern[])parcel.readSerializable();
            parcel = parcel.createIntArray();
            obj = new Builder(((AutofillId) (obj)), apattern[0], parcel[0]);
            int i = apattern.length;
            for(int j = 1; j < i; j++)
                ((Builder) (obj)).addOption(apattern[j], parcel[j]);

            return ((Builder) (obj)).build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImageTransformation[] newArray(int i)
        {
            return new ImageTransformation[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ImageTransformation";
    private final AutofillId mId;
    private final ArrayList mOptions;

}
