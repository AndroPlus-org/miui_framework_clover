// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.util.DisplayMetrics;
import java.util.Arrays;

// Referenced classes of package android.telephony:
//            Rlog, UiccAccessRule

public class SubscriptionInfo
    implements Parcelable
{

    public SubscriptionInfo(int i, String s, int j, CharSequence charsequence, CharSequence charsequence1, int k, int l, 
            String s1, int i1, Bitmap bitmap, int j1, int k1, String s2)
    {
        this(i, s, j, charsequence, charsequence1, k, l, s1, i1, bitmap, j1, k1, s2, false, null);
    }

    public SubscriptionInfo(int i, String s, int j, CharSequence charsequence, CharSequence charsequence1, int k, int l, 
            String s1, int i1, Bitmap bitmap, int j1, int k1, String s2, boolean flag, 
            UiccAccessRule auiccaccessrule[])
    {
        mId = i;
        mIccId = s;
        mSimSlotIndex = j;
        mDisplayName = charsequence;
        mCarrierName = charsequence1;
        mNameSource = k;
        mIconTint = l;
        mNumber = s1;
        mDataRoaming = i1;
        mIconBitmap = bitmap;
        mMcc = j1;
        mMnc = k1;
        mCountryIso = s2;
        mIsEmbedded = flag;
        mAccessRules = auiccaccessrule;
    }

    public static String givePrintableIccid(String s)
    {
        String s1 = null;
        if(s != null)
            if(s.length() > 9 && Build.IS_DEBUGGABLE ^ true)
                s1 = (new StringBuilder()).append(s.substring(0, 9)).append(Rlog.pii(false, s.substring(9))).toString();
            else
                s1 = s;
        return s1;
    }

    public boolean canManageSubscription(Context context)
    {
        return canManageSubscription(context, context.getPackageName());
    }

    public boolean canManageSubscription(Context context, String s)
    {
        if(!isEmbedded())
            throw new UnsupportedOperationException("Not an embedded subscription");
        if(mAccessRules == null)
            return false;
        context = context.getPackageManager();
        int i;
        try
        {
            context = context.getPackageInfo(s, 64);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown package: ").append(s).toString(), context);
        }
        s = mAccessRules;
        i = s.length;
        for(int j = 0; j < i; j++)
            if(s[j].getCarrierPrivilegeStatus(context) == 1)
                return true;

        return false;
    }

    public Bitmap createIconBitmap(Context context)
    {
        int i = mIconBitmap.getWidth();
        int j = mIconBitmap.getHeight();
        Object obj = context.getResources().getDisplayMetrics();
        Bitmap bitmap = Bitmap.createBitmap(((DisplayMetrics) (obj)), i, j, mIconBitmap.getConfig());
        context = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(mIconTint, android.graphics.PorterDuff.Mode.SRC_ATOP));
        context.drawBitmap(mIconBitmap, 0.0F, 0.0F, paint);
        paint.setColorFilter(null);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create("sans-serif", 0));
        paint.setColor(-1);
        paint.setTextSize(((DisplayMetrics) (obj)).density * 16F);
        obj = String.format("%d", new Object[] {
            Integer.valueOf(mSimSlotIndex + 1)
        });
        Rect rect = new Rect();
        paint.getTextBounds(((String) (obj)), 0, 1, rect);
        context.drawText(((String) (obj)), (float)i / 2.0F - (float)rect.centerX(), (float)j / 2.0F - (float)rect.centerY(), paint);
        return bitmap;
    }

    public int describeContents()
    {
        return 0;
    }

    public UiccAccessRule[] getAccessRules()
    {
        if(!isEmbedded())
            throw new UnsupportedOperationException("Not an embedded subscription");
        else
            return mAccessRules;
    }

    public CharSequence getCarrierName()
    {
        return mCarrierName;
    }

    public String getCountryIso()
    {
        return mCountryIso;
    }

    public int getDataRoaming()
    {
        return mDataRoaming;
    }

    public CharSequence getDisplayName()
    {
        return mDisplayName;
    }

    public String getIccId()
    {
        return mIccId;
    }

    public int getIconTint()
    {
        return mIconTint;
    }

    public int getMcc()
    {
        return mMcc;
    }

    public int getMnc()
    {
        return mMnc;
    }

    public int getNameSource()
    {
        return mNameSource;
    }

    public String getNumber()
    {
        return mNumber;
    }

    public int getSimSlotIndex()
    {
        return mSimSlotIndex;
    }

    public int getSubscriptionId()
    {
        return mId;
    }

    public boolean isEmbedded()
    {
        return mIsEmbedded;
    }

    public void setCarrierName(CharSequence charsequence)
    {
        mCarrierName = charsequence;
    }

    public void setDisplayName(CharSequence charsequence)
    {
        mDisplayName = charsequence;
    }

    public void setIconTint(int i)
    {
        mIconTint = i;
    }

    public String toString()
    {
        String s = givePrintableIccid(mIccId);
        return (new StringBuilder()).append("{id=").append(mId).append(", iccId=").append(s).append(" simSlotIndex=").append(mSimSlotIndex).append(" displayName=").append(mDisplayName).append(" carrierName=").append(mCarrierName).append(" nameSource=").append(mNameSource).append(" iconTint=").append(mIconTint).append(" dataRoaming=").append(mDataRoaming).append(" iconBitmap=").append(mIconBitmap).append(" mcc ").append(mMcc).append(" mnc ").append(mMnc).append(" isEmbedded ").append(mIsEmbedded).append(" accessRules ").append(Arrays.toString(mAccessRules)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeString(mIccId);
        parcel.writeInt(mSimSlotIndex);
        parcel.writeCharSequence(mDisplayName);
        parcel.writeCharSequence(mCarrierName);
        parcel.writeInt(mNameSource);
        parcel.writeInt(mIconTint);
        parcel.writeString(mNumber);
        parcel.writeInt(mDataRoaming);
        parcel.writeInt(mMcc);
        parcel.writeInt(mMnc);
        parcel.writeString(mCountryIso);
        mIconBitmap.writeToParcel(parcel, i);
        parcel.writeBoolean(mIsEmbedded);
        parcel.writeTypedArray(mAccessRules, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SubscriptionInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            String s = parcel.readString();
            int j = parcel.readInt();
            CharSequence charsequence = parcel.readCharSequence();
            CharSequence charsequence1 = parcel.readCharSequence();
            int k = parcel.readInt();
            int l = parcel.readInt();
            String s1 = parcel.readString();
            int i1 = parcel.readInt();
            int j1 = parcel.readInt();
            int k1 = parcel.readInt();
            String s2 = parcel.readString();
            return new SubscriptionInfo(i, s, j, charsequence, charsequence1, k, l, s1, i1, (Bitmap)Bitmap.CREATOR.createFromParcel(parcel), j1, k1, s2, parcel.readBoolean(), (UiccAccessRule[])parcel.createTypedArray(UiccAccessRule.CREATOR));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SubscriptionInfo[] newArray(int i)
        {
            return new SubscriptionInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int TEXT_SIZE = 16;
    private UiccAccessRule mAccessRules[];
    private CharSequence mCarrierName;
    private String mCountryIso;
    public int mDataRoaming;
    public CharSequence mDisplayName;
    public String mIccId;
    private Bitmap mIconBitmap;
    private int mIconTint;
    public int mId;
    private boolean mIsEmbedded;
    private int mMcc;
    private int mMnc;
    private int mNameSource;
    public String mNumber;
    public int mSimSlotIndex;

}
