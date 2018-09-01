// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import java.util.*;

public class Address
    implements Parcelable
{

    static HashMap _2D_get0(Address address)
    {
        return address.mAddressLines;
    }

    static boolean _2D_get1(Address address)
    {
        return address.mHasLatitude;
    }

    static boolean _2D_get2(Address address)
    {
        return address.mHasLongitude;
    }

    static int _2D_get3(Address address)
    {
        return address.mMaxAddressLineIndex;
    }

    static HashMap _2D_set0(Address address, HashMap hashmap)
    {
        address.mAddressLines = hashmap;
        return hashmap;
    }

    static String _2D_set1(Address address, String s)
    {
        address.mAdminArea = s;
        return s;
    }

    static double _2D_set10(Address address, double d)
    {
        address.mLongitude = d;
        return d;
    }

    static int _2D_set11(Address address, int i)
    {
        address.mMaxAddressLineIndex = i;
        return i;
    }

    static String _2D_set12(Address address, String s)
    {
        address.mPhone = s;
        return s;
    }

    static String _2D_set13(Address address, String s)
    {
        address.mPostalCode = s;
        return s;
    }

    static String _2D_set14(Address address, String s)
    {
        address.mPremises = s;
        return s;
    }

    static String _2D_set15(Address address, String s)
    {
        address.mSubAdminArea = s;
        return s;
    }

    static String _2D_set16(Address address, String s)
    {
        address.mSubLocality = s;
        return s;
    }

    static String _2D_set17(Address address, String s)
    {
        address.mSubThoroughfare = s;
        return s;
    }

    static String _2D_set18(Address address, String s)
    {
        address.mThoroughfare = s;
        return s;
    }

    static String _2D_set19(Address address, String s)
    {
        address.mUrl = s;
        return s;
    }

    static String _2D_set2(Address address, String s)
    {
        address.mCountryCode = s;
        return s;
    }

    static String _2D_set3(Address address, String s)
    {
        address.mCountryName = s;
        return s;
    }

    static Bundle _2D_set4(Address address, Bundle bundle)
    {
        address.mExtras = bundle;
        return bundle;
    }

    static String _2D_set5(Address address, String s)
    {
        address.mFeatureName = s;
        return s;
    }

    static boolean _2D_set6(Address address, boolean flag)
    {
        address.mHasLatitude = flag;
        return flag;
    }

    static boolean _2D_set7(Address address, boolean flag)
    {
        address.mHasLongitude = flag;
        return flag;
    }

    static double _2D_set8(Address address, double d)
    {
        address.mLatitude = d;
        return d;
    }

    static String _2D_set9(Address address, String s)
    {
        address.mLocality = s;
        return s;
    }

    public Address(Locale locale)
    {
        mMaxAddressLineIndex = -1;
        mHasLatitude = false;
        mHasLongitude = false;
        mExtras = null;
        mLocale = locale;
    }

    public void clearLatitude()
    {
        mHasLatitude = false;
    }

    public void clearLongitude()
    {
        mHasLongitude = false;
    }

    public int describeContents()
    {
        int i;
        if(mExtras != null)
            i = mExtras.describeContents();
        else
            i = 0;
        return i;
    }

    public String getAddressLine(int i)
    {
        String s = null;
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("index = ").append(i).append(" < 0").toString());
        if(mAddressLines != null)
            s = (String)mAddressLines.get(Integer.valueOf(i));
        return s;
    }

    public String getAdminArea()
    {
        return mAdminArea;
    }

    public String getCountryCode()
    {
        return mCountryCode;
    }

    public String getCountryName()
    {
        return mCountryName;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public String getFeatureName()
    {
        return mFeatureName;
    }

    public double getLatitude()
    {
        if(mHasLatitude)
            return mLatitude;
        else
            throw new IllegalStateException();
    }

    public Locale getLocale()
    {
        return mLocale;
    }

    public String getLocality()
    {
        return mLocality;
    }

    public double getLongitude()
    {
        if(mHasLongitude)
            return mLongitude;
        else
            throw new IllegalStateException();
    }

    public int getMaxAddressLineIndex()
    {
        return mMaxAddressLineIndex;
    }

    public String getPhone()
    {
        return mPhone;
    }

    public String getPostalCode()
    {
        return mPostalCode;
    }

    public String getPremises()
    {
        return mPremises;
    }

    public String getSubAdminArea()
    {
        return mSubAdminArea;
    }

    public String getSubLocality()
    {
        return mSubLocality;
    }

    public String getSubThoroughfare()
    {
        return mSubThoroughfare;
    }

    public String getThoroughfare()
    {
        return mThoroughfare;
    }

    public String getUrl()
    {
        return mUrl;
    }

    public boolean hasLatitude()
    {
        return mHasLatitude;
    }

    public boolean hasLongitude()
    {
        return mHasLongitude;
    }

    public void setAddressLine(int i, String s)
    {
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("index = ").append(i).append(" < 0").toString());
        if(mAddressLines == null)
            mAddressLines = new HashMap();
        mAddressLines.put(Integer.valueOf(i), s);
        if(s == null)
        {
            mMaxAddressLineIndex = -1;
            for(s = mAddressLines.keySet().iterator(); s.hasNext();)
            {
                Integer integer = (Integer)s.next();
                mMaxAddressLineIndex = Math.max(mMaxAddressLineIndex, integer.intValue());
            }

        } else
        {
            mMaxAddressLineIndex = Math.max(mMaxAddressLineIndex, i);
        }
    }

    public void setAdminArea(String s)
    {
        mAdminArea = s;
    }

    public void setCountryCode(String s)
    {
        mCountryCode = s;
    }

    public void setCountryName(String s)
    {
        mCountryName = s;
    }

    public void setExtras(Bundle bundle)
    {
        Object obj = null;
        if(bundle == null)
            bundle = obj;
        else
            bundle = new Bundle(bundle);
        mExtras = bundle;
    }

    public void setFeatureName(String s)
    {
        mFeatureName = s;
    }

    public void setLatitude(double d)
    {
        mLatitude = d;
        mHasLatitude = true;
    }

    public void setLocality(String s)
    {
        mLocality = s;
    }

    public void setLongitude(double d)
    {
        mLongitude = d;
        mHasLongitude = true;
    }

    public void setPhone(String s)
    {
        mPhone = s;
    }

    public void setPostalCode(String s)
    {
        mPostalCode = s;
    }

    public void setPremises(String s)
    {
        mPremises = s;
    }

    public void setSubAdminArea(String s)
    {
        mSubAdminArea = s;
    }

    public void setSubLocality(String s)
    {
        mSubLocality = s;
    }

    public void setSubThoroughfare(String s)
    {
        mSubThoroughfare = s;
    }

    public void setThoroughfare(String s)
    {
        mThoroughfare = s;
    }

    public void setUrl(String s)
    {
        mUrl = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Address[addressLines=[");
        int i = 0;
        while(i <= mMaxAddressLineIndex) 
        {
            if(i > 0)
                stringbuilder.append(',');
            stringbuilder.append(i);
            stringbuilder.append(':');
            String s = (String)mAddressLines.get(Integer.valueOf(i));
            if(s == null)
            {
                stringbuilder.append("null");
            } else
            {
                stringbuilder.append('"');
                stringbuilder.append(s);
                stringbuilder.append('"');
            }
            i++;
        }
        stringbuilder.append(']');
        stringbuilder.append(",feature=");
        stringbuilder.append(mFeatureName);
        stringbuilder.append(",admin=");
        stringbuilder.append(mAdminArea);
        stringbuilder.append(",sub-admin=");
        stringbuilder.append(mSubAdminArea);
        stringbuilder.append(",locality=");
        stringbuilder.append(mLocality);
        stringbuilder.append(",thoroughfare=");
        stringbuilder.append(mThoroughfare);
        stringbuilder.append(",postalCode=");
        stringbuilder.append(mPostalCode);
        stringbuilder.append(",countryCode=");
        stringbuilder.append(mCountryCode);
        stringbuilder.append(",countryName=");
        stringbuilder.append(mCountryName);
        stringbuilder.append(",hasLatitude=");
        stringbuilder.append(mHasLatitude);
        stringbuilder.append(",latitude=");
        stringbuilder.append(mLatitude);
        stringbuilder.append(",hasLongitude=");
        stringbuilder.append(mHasLongitude);
        stringbuilder.append(",longitude=");
        stringbuilder.append(mLongitude);
        stringbuilder.append(",phone=");
        stringbuilder.append(mPhone);
        stringbuilder.append(",url=");
        stringbuilder.append(mUrl);
        stringbuilder.append(",extras=");
        stringbuilder.append(mExtras);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mLocale.getLanguage());
        parcel.writeString(mLocale.getCountry());
        if(mAddressLines == null)
        {
            parcel.writeInt(0);
        } else
        {
            Object obj = mAddressLines.entrySet();
            parcel.writeInt(((Set) (obj)).size());
            obj = ((Iterable) (obj)).iterator();
            while(((Iterator) (obj)).hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                parcel.writeInt(((Integer)entry.getKey()).intValue());
                parcel.writeString((String)entry.getValue());
            }
        }
        parcel.writeString(mFeatureName);
        parcel.writeString(mAdminArea);
        parcel.writeString(mSubAdminArea);
        parcel.writeString(mLocality);
        parcel.writeString(mSubLocality);
        parcel.writeString(mThoroughfare);
        parcel.writeString(mSubThoroughfare);
        parcel.writeString(mPremises);
        parcel.writeString(mPostalCode);
        parcel.writeString(mCountryCode);
        parcel.writeString(mCountryName);
        if(mHasLatitude)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasLatitude)
            parcel.writeDouble(mLatitude);
        if(mHasLongitude)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasLongitude)
            parcel.writeDouble(mLongitude);
        parcel.writeString(mPhone);
        parcel.writeString(mUrl);
        parcel.writeBundle(mExtras);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Address createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            Object obj = parcel.readString();
            Object obj1 = parcel.readString();
            int i;
            if(((String) (obj1)).length() > 0)
                obj = new Locale(((String) (obj)), ((String) (obj1)));
            else
                obj = new Locale(((String) (obj)));
            obj1 = new Address(((Locale) (obj)));
            i = parcel.readInt();
            if(i > 0)
            {
                Address._2D_set0(((Address) (obj1)), new HashMap(i));
                for(int j = 0; j < i; j++)
                {
                    int k = parcel.readInt();
                    obj = parcel.readString();
                    Address._2D_get0(((Address) (obj1))).put(Integer.valueOf(k), obj);
                    Address._2D_set11(((Address) (obj1)), Math.max(Address._2D_get3(((Address) (obj1))), k));
                }

            } else
            {
                Address._2D_set0(((Address) (obj1)), null);
                Address._2D_set11(((Address) (obj1)), -1);
            }
            Address._2D_set5(((Address) (obj1)), parcel.readString());
            Address._2D_set1(((Address) (obj1)), parcel.readString());
            Address._2D_set15(((Address) (obj1)), parcel.readString());
            Address._2D_set9(((Address) (obj1)), parcel.readString());
            Address._2D_set16(((Address) (obj1)), parcel.readString());
            Address._2D_set18(((Address) (obj1)), parcel.readString());
            Address._2D_set17(((Address) (obj1)), parcel.readString());
            Address._2D_set14(((Address) (obj1)), parcel.readString());
            Address._2D_set13(((Address) (obj1)), parcel.readString());
            Address._2D_set2(((Address) (obj1)), parcel.readString());
            Address._2D_set3(((Address) (obj1)), parcel.readString());
            boolean flag1;
            if(parcel.readInt() == 0)
                flag1 = false;
            else
                flag1 = true;
            Address._2D_set6(((Address) (obj1)), flag1);
            if(Address._2D_get1(((Address) (obj1))))
                Address._2D_set8(((Address) (obj1)), parcel.readDouble());
            if(parcel.readInt() == 0)
                flag1 = flag;
            else
                flag1 = true;
            Address._2D_set7(((Address) (obj1)), flag1);
            if(Address._2D_get2(((Address) (obj1))))
                Address._2D_set10(((Address) (obj1)), parcel.readDouble());
            Address._2D_set12(((Address) (obj1)), parcel.readString());
            Address._2D_set19(((Address) (obj1)), parcel.readString());
            Address._2D_set4(((Address) (obj1)), parcel.readBundle());
            return ((Address) (obj1));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Address[] newArray(int i)
        {
            return new Address[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private HashMap mAddressLines;
    private String mAdminArea;
    private String mCountryCode;
    private String mCountryName;
    private Bundle mExtras;
    private String mFeatureName;
    private boolean mHasLatitude;
    private boolean mHasLongitude;
    private double mLatitude;
    private Locale mLocale;
    private String mLocality;
    private double mLongitude;
    private int mMaxAddressLineIndex;
    private String mPhone;
    private String mPostalCode;
    private String mPremises;
    private String mSubAdminArea;
    private String mSubLocality;
    private String mSubThoroughfare;
    private String mThoroughfare;
    private String mUrl;

}
