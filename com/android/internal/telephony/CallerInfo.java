// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Country;
import android.location.CountryDetector;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.telephony.Rlog;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SeempLog;
import com.android.i18n.phonenumbers.NumberParseException;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import com.android.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import java.util.Locale;

// Referenced classes of package com.android.internal.telephony:
//            CallerInfoAsyncQuery

public class CallerInfo
{

    public CallerInfo()
    {
        mIsEmergency = false;
        mIsVoiceMail = false;
        userType = 0L;
    }

    static CallerInfo doSecondaryLookupIfNecessary(Context context, String s, CallerInfo callerinfo)
    {
        CallerInfo callerinfo1 = callerinfo;
        if(!callerinfo.contactExists)
        {
            callerinfo1 = callerinfo;
            if(PhoneNumberUtils.isUriNumber(s))
            {
                s = PhoneNumberUtils.getUsernameFromUriNumber(s);
                callerinfo1 = callerinfo;
                if(PhoneNumberUtils.isGlobalPhoneNumber(s))
                    callerinfo1 = getCallerInfo(context, Uri.withAppendedPath(android.provider.ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(s)));
            }
        }
        return callerinfo1;
    }

    public static CallerInfo getCallerInfo(Context context, Uri uri)
    {
        Object obj = null;
        ContentResolver contentresolver = CallerInfoAsyncQuery.getCurrentProfileContentResolver(context);
        CallerInfo callerinfo = obj;
        if(contentresolver != null)
            try
            {
                callerinfo = getCallerInfo(context, uri, contentresolver.query(uri, null, null, null, null));
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Rlog.e("CallerInfo", "Error getting caller info.", context);
                callerinfo = obj;
            }
        return callerinfo;
    }

    public static CallerInfo getCallerInfo(Context context, Uri uri, Cursor cursor)
    {
        SeempLog.record_uri(12, uri);
        CallerInfo callerinfo = new CallerInfo();
        callerinfo.photoResource = 0;
        callerinfo.phoneLabel = null;
        callerinfo.numberType = 0;
        callerinfo.numberLabel = null;
        callerinfo.cachedPhoto = null;
        callerinfo.isCachedPhotoCurrent = false;
        callerinfo.contactExists = false;
        callerinfo.userType = 0L;
        if(VDBG)
            Rlog.v("CallerInfo", "getCallerInfo() based on cursor...");
        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                int i = cursor.getColumnIndex("display_name");
                if(i != -1)
                    callerinfo.name = cursor.getString(i);
                i = cursor.getColumnIndex("number");
                if(i != -1)
                    callerinfo.phoneNumber = cursor.getString(i);
                i = cursor.getColumnIndex("normalized_number");
                if(i != -1)
                    callerinfo.normalizedNumber = cursor.getString(i);
                i = cursor.getColumnIndex("label");
                if(i != -1)
                {
                    int j = cursor.getColumnIndex("type");
                    if(j != -1)
                    {
                        callerinfo.numberType = cursor.getInt(j);
                        callerinfo.numberLabel = cursor.getString(i);
                        callerinfo.phoneLabel = android.provider.ContactsContract.CommonDataKinds.Phone.getDisplayLabel(context, callerinfo.numberType, callerinfo.numberLabel).toString();
                    }
                }
                i = getColumnIndexForPersonId(uri, cursor);
                boolean flag;
                if(i != -1)
                {
                    long l = cursor.getLong(i);
                    if(l != 0L && android.provider.ContactsContract.Contacts.isEnterpriseContactId(l) ^ true)
                    {
                        callerinfo.contactIdOrZero = l;
                        if(VDBG)
                            Rlog.v("CallerInfo", (new StringBuilder()).append("==> got info.contactIdOrZero: ").append(callerinfo.contactIdOrZero).toString());
                    }
                    if(android.provider.ContactsContract.Contacts.isEnterpriseContactId(l))
                        callerinfo.userType = 1L;
                } else
                {
                    Rlog.w("CallerInfo", (new StringBuilder()).append("Couldn't find contact_id column for ").append(uri).toString());
                }
                i = cursor.getColumnIndex("lookup");
                if(i != -1)
                    callerinfo.lookupKey = cursor.getString(i);
                i = cursor.getColumnIndex("photo_uri");
                if(i != -1 && cursor.getString(i) != null)
                    callerinfo.contactDisplayPhotoUri = Uri.parse(cursor.getString(i));
                else
                    callerinfo.contactDisplayPhotoUri = null;
                i = cursor.getColumnIndex("custom_ringtone");
                if(i != -1 && cursor.getString(i) != null)
                {
                    if(TextUtils.isEmpty(cursor.getString(i)))
                        callerinfo.contactRingtoneUri = Uri.EMPTY;
                    else
                        callerinfo.contactRingtoneUri = Uri.parse(cursor.getString(i));
                } else
                {
                    callerinfo.contactRingtoneUri = null;
                }
                i = cursor.getColumnIndex("send_to_voicemail");
                if(i != -1)
                {
                    if(cursor.getInt(i) == 1)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = false;
                }
                callerinfo.shouldSendToVoicemail = flag;
                callerinfo.contactExists = true;
            }
            cursor.close();
        }
        callerinfo.needUpdate = false;
        callerinfo.name = normalize(callerinfo.name);
        callerinfo.contactRefUri = uri;
        return callerinfo;
    }

    public static CallerInfo getCallerInfo(Context context, String s)
    {
        if(VDBG)
            Rlog.v("CallerInfo", "getCallerInfo() based on number...");
        return getCallerInfo(context, s, SubscriptionManager.getDefaultSubscriptionId());
    }

    public static CallerInfo getCallerInfo(Context context, String s, int i)
    {
        SeempLog.record_str(12, (new StringBuilder()).append("number=").append(s).append(",subId=").append(i).toString());
        if(TextUtils.isEmpty(s))
            return null;
        if(PhoneNumberUtils.isLocalEmergencyNumber(context, s))
            return (new CallerInfo()).markAsEmergency(context);
        if(PhoneNumberUtils.isVoiceMailNumber(i, s))
            return (new CallerInfo()).markAsVoiceMail();
        context = doSecondaryLookupIfNecessary(context, s, getCallerInfo(context, Uri.withAppendedPath(android.provider.ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(s))));
        if(TextUtils.isEmpty(((CallerInfo) (context)).phoneNumber))
            context.phoneNumber = s;
        return context;
    }

    private static int getColumnIndexForPersonId(Uri uri, Cursor cursor)
    {
        if(VDBG)
            Rlog.v("CallerInfo", (new StringBuilder()).append("- getColumnIndexForPersonId: contactRef URI = '").append(uri).append("'...").toString());
        String s = uri.toString();
        uri = null;
        int i;
        if(s.startsWith("content://com.android.contacts/data/phones"))
        {
            if(VDBG)
                Rlog.v("CallerInfo", "'data/phones' URI; using RawContacts.CONTACT_ID");
            uri = "contact_id";
        } else
        if(s.startsWith("content://com.android.contacts/data"))
        {
            if(VDBG)
                Rlog.v("CallerInfo", "'data' URI; using Data.CONTACT_ID");
            uri = "contact_id";
        } else
        if(s.startsWith("content://com.android.contacts/phone_lookup"))
        {
            if(VDBG)
                Rlog.v("CallerInfo", "'phone_lookup' URI; using PhoneLookup._ID");
            uri = "_id";
        } else
        {
            Rlog.w("CallerInfo", (new StringBuilder()).append("Unexpected prefix for contactRef '").append(s).append("'").toString());
        }
        if(uri != null)
            i = cursor.getColumnIndex(uri);
        else
            i = -1;
        if(VDBG)
            Rlog.v("CallerInfo", (new StringBuilder()).append("==> Using column '").append(uri).append("' (columnIndex = ").append(i).append(") for person_id lookup...").toString());
        return i;
    }

    protected static String getCurrentCountryIso(Context context)
    {
        return getCurrentCountryIso(context, Locale.getDefault());
    }

    private static String getCurrentCountryIso(Context context, Locale locale)
    {
        Object obj = null;
        CountryDetector countrydetector = (CountryDetector)context.getSystemService("country_detector");
        context = ((Context) (obj));
        if(countrydetector != null)
        {
            context = countrydetector.detectCountry();
            if(context != null)
            {
                context = context.getCountryIso();
            } else
            {
                Rlog.e("CallerInfo", "CountryDetector.detectCountry() returned null.");
                context = ((Context) (obj));
            }
        }
        obj = context;
        if(context == null)
        {
            obj = locale.getCountry();
            Rlog.w("CallerInfo", (new StringBuilder()).append("No CountryDetector; falling back to countryIso based on locale: ").append(((String) (obj))).toString());
        }
        return ((String) (obj));
    }

    public static String getGeoDescription(Context context, String s)
    {
        Object obj;
        PhoneNumberOfflineGeocoder phonenumberofflinegeocoder;
        Locale locale;
        String s1;
        Object obj1;
        if(VDBG)
            Rlog.v("CallerInfo", (new StringBuilder()).append("getGeoDescription('").append(s).append("')...").toString());
        if(TextUtils.isEmpty(s))
            return null;
        obj = PhoneNumberUtil.getInstance();
        phonenumberofflinegeocoder = PhoneNumberOfflineGeocoder.getInstance();
        locale = context.getResources().getConfiguration().locale;
        s1 = getCurrentCountryIso(context, locale);
        obj1 = null;
        context = ((Context) (obj1));
        if(!VDBG)
            break MISSING_BLOCK_LABEL_144;
        context = ((Context) (obj1));
        StringBuilder stringbuilder;
        try
        {
            stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
        }
        catch(NumberParseException numberparseexception)
        {
            Rlog.w("CallerInfo", (new StringBuilder()).append("getGeoDescription: NumberParseException for incoming number '").append(miui.telephony.PhoneNumberUtils.toLogSafePhoneNumber(s)).append("'").toString());
            numberparseexception = context;
            continue; /* Loop/switch isn't completed */
        }
        context = ((Context) (obj1));
        stringbuilder.StringBuilder();
        context = ((Context) (obj1));
        Rlog.v("CallerInfo", stringbuilder.append("parsing '").append(s).append("' for countryIso '").append(s1).append("'...").toString());
        context = ((Context) (obj1));
        obj = ((PhoneNumberUtil) (obj)).parse(s, s1);
        obj1 = obj;
        context = ((Context) (obj));
        if(!VDBG)
            break MISSING_BLOCK_LABEL_206;
        context = ((Context) (obj));
        obj1 = JVM INSTR new #213 <Class StringBuilder>;
        context = ((Context) (obj));
        ((StringBuilder) (obj1)).StringBuilder();
        context = ((Context) (obj));
        Rlog.v("CallerInfo", ((StringBuilder) (obj1)).append("- parsed number: ").append(obj).toString());
        obj1 = obj;
_L5:
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        context = null;
        s = phonenumberofflinegeocoder.getDescriptionForNumber(((com.android.i18n.phonenumbers.Phonenumber.PhoneNumber) (obj1)), locale);
        context = s;
_L3:
        if(VDBG)
            Rlog.v("CallerInfo", (new StringBuilder()).append("- got description: '").append(context).append("'").toString());
        return context;
        s;
        Rlog.e("CallerInfo", (new StringBuilder()).append("getDescriptionForNumber exception ").append(s).toString());
        if(true) goto _L3; else goto _L2
_L2:
        return null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static String normalize(String s)
    {
        if(s == null || s.length() > 0)
            return s;
        else
            return null;
    }

    public boolean isEmergencyNumber()
    {
        return mIsEmergency;
    }

    public boolean isVoiceMailNumber()
    {
        return mIsVoiceMail;
    }

    CallerInfo markAsEmergency(Context context)
    {
        phoneNumber = context.getString(0x10401d5);
        photoResource = 0x108060c;
        mIsEmergency = true;
        return this;
    }

    CallerInfo markAsVoiceMail()
    {
        return markAsVoiceMail(SubscriptionManager.getDefaultSubscriptionId());
    }

    CallerInfo markAsVoiceMail(int i)
    {
        mIsVoiceMail = true;
        try
        {
            phoneNumber = TelephonyManager.getDefault().getVoiceMailAlphaTag(i);
        }
        catch(SecurityException securityexception)
        {
            Rlog.e("CallerInfo", "Cannot access VoiceMail.", securityexception);
        }
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder(128)).append(super.toString()).append(" { ").append("name ");
        String s;
        if(name == null)
            s = "null";
        else
            s = "non-null";
        stringbuilder = stringbuilder.append(s).append(", phoneNumber ");
        if(phoneNumber == null)
            s = "null";
        else
            s = "non-null";
        return stringbuilder.append(s).append(" }").toString();
    }

    public void updateGeoDescription(Context context, String s)
    {
        if(!TextUtils.isEmpty(phoneNumber))
            s = phoneNumber;
        geoDescription = getGeoDescription(context, s);
    }

    private static final String TAG = "CallerInfo";
    public static final long USER_TYPE_CURRENT = 0L;
    public static final long USER_TYPE_WORK = 1L;
    private static final boolean VDBG = Rlog.isLoggable("CallerInfo", 2);
    public Drawable cachedPhoto;
    public Bitmap cachedPhotoIcon;
    public String cnapName;
    public Uri contactDisplayPhotoUri;
    public boolean contactExists;
    public long contactIdOrZero;
    public Uri contactRefUri;
    public Uri contactRingtoneUri;
    public String geoDescription;
    public boolean isCachedPhotoCurrent;
    public String lookupKey;
    private boolean mIsEmergency;
    private boolean mIsVoiceMail;
    public String name;
    public int namePresentation;
    public boolean needUpdate;
    public String normalizedNumber;
    public String numberLabel;
    public int numberPresentation;
    public int numberType;
    public String phoneLabel;
    public String phoneNumber;
    public int photoResource;
    public boolean shouldSendToVoicemail;
    public long userType;

}
