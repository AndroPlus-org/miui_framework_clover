// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.autofill.Helper;
import android.widget.RemoteViews;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;

// Referenced classes of package android.service.autofill:
//            InternalTransformation, ValueFinder, Transformation

public final class CustomDescription
    implements Parcelable
{
    public static class Builder
    {

        static RemoteViews _2D_get0(Builder builder)
        {
            return builder.mPresentation;
        }

        static ArrayList _2D_get1(Builder builder)
        {
            return builder.mTransformations;
        }

        public Builder addChild(int i, Transformation transformation)
        {
            Preconditions.checkArgument(transformation instanceof InternalTransformation, (new StringBuilder()).append("not provided by Android System: ").append(transformation).toString());
            if(mTransformations == null)
                mTransformations = new ArrayList();
            mTransformations.add(new Pair(Integer.valueOf(i), (InternalTransformation)transformation));
            return this;
        }

        public CustomDescription build()
        {
            return new CustomDescription(this, null);
        }

        private final RemoteViews mPresentation;
        private ArrayList mTransformations;

        public Builder(RemoteViews remoteviews)
        {
            mPresentation = remoteviews;
        }
    }


    private CustomDescription(Builder builder)
    {
        mPresentation = Builder._2D_get0(builder);
        mTransformations = Builder._2D_get1(builder);
    }

    CustomDescription(Builder builder, CustomDescription customdescription)
    {
        this(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public RemoteViews getPresentation(ValueFinder valuefinder)
    {
        if(mTransformations != null)
        {
            int i = mTransformations.size();
            if(Helper.sDebug)
                Log.d("CustomDescription", (new StringBuilder()).append("getPresentation(): applying ").append(i).append(" transformations").toString());
            int j = 0;
            do
            {
                if(j >= i)
                    break;
                Object obj = (Pair)mTransformations.get(j);
                int k = ((Integer)((Pair) (obj)).first).intValue();
                obj = (InternalTransformation)((Pair) (obj)).second;
                if(Helper.sDebug)
                    Log.d("CustomDescription", (new StringBuilder()).append("#").append(j).append(": ").append(obj).toString());
                try
                {
                    ((InternalTransformation) (obj)).apply(valuefinder, mPresentation, k);
                }
                // Misplaced declaration of an exception variable
                catch(ValueFinder valuefinder)
                {
                    Log.e("CustomDescription", (new StringBuilder()).append("Could not apply transformation ").append(obj).append(": ").append(valuefinder.getClass()).toString());
                    return null;
                }
                j++;
            } while(true);
        }
        return mPresentation;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder("CustomDescription: [presentation=")).append(mPresentation).append(", transformations=").append(mTransformations).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mPresentation, i);
        if(mTransformations == null)
        {
            parcel.writeIntArray(null);
        } else
        {
            int j = mTransformations.size();
            int ai[] = new int[j];
            InternalTransformation ainternaltransformation[] = new InternalTransformation[j];
            for(int k = 0; k < j; k++)
            {
                Pair pair = (Pair)mTransformations.get(k);
                ai[k] = ((Integer)pair.first).intValue();
                ainternaltransformation[k] = (InternalTransformation)pair.second;
            }

            parcel.writeIntArray(ai);
            parcel.writeParcelableArray(ainternaltransformation, i);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CustomDescription createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder((RemoteViews)parcel.readParcelable(null));
            int ai[] = parcel.createIntArray();
            if(ai != null)
            {
                parcel = (InternalTransformation[])parcel.readParcelableArray(null, android/service/autofill/InternalTransformation);
                int i = ai.length;
                for(int j = 0; j < i; j++)
                    builder.addChild(ai[j], parcel[j]);

            }
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CustomDescription[] newArray(int i)
        {
            return new CustomDescription[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "CustomDescription";
    private final RemoteViews mPresentation;
    private final ArrayList mTransformations;

}
