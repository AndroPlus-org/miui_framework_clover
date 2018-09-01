// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DebugUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

// Referenced classes of package android.service.autofill:
//            CustomDescription, InternalValidator, Validator

public final class SaveInfo
    implements Parcelable
{
    public static final class Builder
    {

        static CustomDescription _2D_get0(Builder builder)
        {
            return builder.mCustomDescription;
        }

        static CharSequence _2D_get1(Builder builder)
        {
            return builder.mDescription;
        }

        static int _2D_get2(Builder builder)
        {
            return builder.mFlags;
        }

        static IntentSender _2D_get3(Builder builder)
        {
            return builder.mNegativeActionListener;
        }

        static int _2D_get4(Builder builder)
        {
            return builder.mNegativeButtonStyle;
        }

        static AutofillId[] _2D_get5(Builder builder)
        {
            return builder.mOptionalIds;
        }

        static AutofillId[] _2D_get6(Builder builder)
        {
            return builder.mRequiredIds;
        }

        static int _2D_get7(Builder builder)
        {
            return builder.mType;
        }

        static InternalValidator _2D_get8(Builder builder)
        {
            return builder.mValidator;
        }

        private AutofillId[] assertValid(AutofillId aautofillid[])
        {
            boolean flag;
            int i;
            if(aautofillid != null && aautofillid.length > 0)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, (new StringBuilder()).append("must have at least one id: ").append(Arrays.toString(aautofillid)).toString());
            i = 0;
            while(i < aautofillid.length) 
            {
                if(aautofillid[i] != null)
                    flag = true;
                else
                    flag = false;
                Preconditions.checkArgument(flag, (new StringBuilder()).append("cannot have null id: ").append(Arrays.toString(aautofillid)).toString());
                i++;
            }
            return aautofillid;
        }

        private void throwIfDestroyed()
        {
            if(mDestroyed)
                throw new IllegalStateException("Already called #build()");
            else
                return;
        }

        public SaveInfo build()
        {
            throwIfDestroyed();
            boolean flag;
            if(ArrayUtils.isEmpty(mRequiredIds))
                flag = ArrayUtils.isEmpty(mOptionalIds) ^ true;
            else
                flag = true;
            Preconditions.checkState(flag, "must have at least one required or optional id");
            mDestroyed = true;
            return new SaveInfo(this, null);
        }

        public Builder setCustomDescription(CustomDescription customdescription)
        {
            throwIfDestroyed();
            boolean flag;
            if(mDescription == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "Can call setDescription() or setCustomDescription(), but not both");
            mCustomDescription = customdescription;
            return this;
        }

        public Builder setDescription(CharSequence charsequence)
        {
            throwIfDestroyed();
            boolean flag;
            if(mCustomDescription == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "Can call setDescription() or setCustomDescription(), but not both");
            mDescription = charsequence;
            return this;
        }

        public Builder setFlags(int i)
        {
            throwIfDestroyed();
            mFlags = Preconditions.checkFlagsArgument(i, 1);
            return this;
        }

        public Builder setNegativeAction(int i, IntentSender intentsender)
        {
            throwIfDestroyed();
            if(i != 0 && i != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid style: ").append(i).toString());
            } else
            {
                mNegativeButtonStyle = i;
                mNegativeActionListener = intentsender;
                return this;
            }
        }

        public Builder setOptionalIds(AutofillId aautofillid[])
        {
            throwIfDestroyed();
            mOptionalIds = assertValid(aautofillid);
            return this;
        }

        public Builder setValidator(Validator validator)
        {
            throwIfDestroyed();
            Preconditions.checkArgument(validator instanceof InternalValidator, (new StringBuilder()).append("not provided by Android System: ").append(validator).toString());
            mValidator = (InternalValidator)validator;
            return this;
        }

        private CustomDescription mCustomDescription;
        private CharSequence mDescription;
        private boolean mDestroyed;
        private int mFlags;
        private IntentSender mNegativeActionListener;
        private int mNegativeButtonStyle;
        private AutofillId mOptionalIds[];
        private final AutofillId mRequiredIds[];
        private final int mType;
        private InternalValidator mValidator;

        public Builder(int i)
        {
            mNegativeButtonStyle = 0;
            mType = i;
            mRequiredIds = null;
        }

        public Builder(int i, AutofillId aautofillid[])
        {
            mNegativeButtonStyle = 0;
            mType = i;
            mRequiredIds = assertValid(aautofillid);
        }
    }


    private SaveInfo(Builder builder)
    {
        mType = Builder._2D_get7(builder);
        mNegativeButtonStyle = Builder._2D_get4(builder);
        mNegativeActionListener = Builder._2D_get3(builder);
        mRequiredIds = Builder._2D_get6(builder);
        mOptionalIds = Builder._2D_get5(builder);
        mDescription = Builder._2D_get1(builder);
        mFlags = Builder._2D_get2(builder);
        mCustomDescription = Builder._2D_get0(builder);
        mValidator = Builder._2D_get8(builder);
    }

    SaveInfo(Builder builder, SaveInfo saveinfo)
    {
        this(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public CustomDescription getCustomDescription()
    {
        return mCustomDescription;
    }

    public CharSequence getDescription()
    {
        return mDescription;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public IntentSender getNegativeActionListener()
    {
        return mNegativeActionListener;
    }

    public int getNegativeActionStyle()
    {
        return mNegativeButtonStyle;
    }

    public AutofillId[] getOptionalIds()
    {
        return mOptionalIds;
    }

    public AutofillId[] getRequiredIds()
    {
        return mRequiredIds;
    }

    public int getType()
    {
        return mType;
    }

    public InternalValidator getValidator()
    {
        return mValidator;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        else
            return (new StringBuilder("SaveInfo: [type=")).append(DebugUtils.flagsToString(android/service/autofill/SaveInfo, "SAVE_DATA_TYPE_", mType)).append(", requiredIds=").append(Arrays.toString(mRequiredIds)).append(", optionalIds=").append(Arrays.toString(mOptionalIds)).append(", description=").append(mDescription).append(DebugUtils.flagsToString(android/service/autofill/SaveInfo, "NEGATIVE_BUTTON_STYLE_", mNegativeButtonStyle)).append(", mFlags=").append(mFlags).append(", mCustomDescription=").append(mCustomDescription).append(", validation=").append(mValidator).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeParcelableArray(mRequiredIds, i);
        parcel.writeParcelableArray(mOptionalIds, i);
        parcel.writeInt(mNegativeButtonStyle);
        parcel.writeParcelable(mNegativeActionListener, i);
        parcel.writeCharSequence(mDescription);
        parcel.writeParcelable(mCustomDescription, i);
        parcel.writeParcelable(mValidator, i);
        parcel.writeInt(mFlags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SaveInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            AutofillId aautofillid[] = (AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId);
            Builder builder;
            AutofillId aautofillid1[];
            Object obj;
            if(aautofillid != null)
                builder = new Builder(i, aautofillid);
            else
                builder = new Builder(i);
            aautofillid1 = (AutofillId[])parcel.readParcelableArray(null, android/view/autofill/AutofillId);
            if(aautofillid1 != null)
                builder.setOptionalIds(aautofillid1);
            builder.setNegativeAction(parcel.readInt(), (IntentSender)parcel.readParcelable(null));
            builder.setDescription(parcel.readCharSequence());
            obj = (CustomDescription)parcel.readParcelable(null);
            if(obj != null)
                builder.setCustomDescription(((CustomDescription) (obj)));
            obj = (InternalValidator)parcel.readParcelable(null);
            if(obj != null)
                builder.setValidator(((Validator) (obj)));
            builder.setFlags(parcel.readInt());
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SaveInfo[] newArray(int i)
        {
            return new SaveInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_SAVE_ON_ALL_VIEWS_INVISIBLE = 1;
    public static final int NEGATIVE_BUTTON_STYLE_CANCEL = 0;
    public static final int NEGATIVE_BUTTON_STYLE_REJECT = 1;
    public static final int SAVE_DATA_TYPE_ADDRESS = 2;
    public static final int SAVE_DATA_TYPE_CREDIT_CARD = 4;
    public static final int SAVE_DATA_TYPE_EMAIL_ADDRESS = 16;
    public static final int SAVE_DATA_TYPE_GENERIC = 0;
    public static final int SAVE_DATA_TYPE_PASSWORD = 1;
    public static final int SAVE_DATA_TYPE_USERNAME = 8;
    private final CustomDescription mCustomDescription;
    private final CharSequence mDescription;
    private final int mFlags;
    private final IntentSender mNegativeActionListener;
    private final int mNegativeButtonStyle;
    private final AutofillId mOptionalIds[];
    private final AutofillId mRequiredIds[];
    private final int mType;
    private final InternalValidator mValidator;

}
