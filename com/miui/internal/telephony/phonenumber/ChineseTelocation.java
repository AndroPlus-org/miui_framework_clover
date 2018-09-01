// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.telephony.phonenumber;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.location.Country;
import android.location.CountryDetector;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import com.android.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import java.util.Locale;
import miui.telephony.phonenumber.ChineseTelocationConverter;
import miui.telephony.phonenumber.CountryCode;

public class ChineseTelocation
{

    static void _2D_wrap0(ChineseTelocation chinesetelocation)
    {
        chinesetelocation.updateTelocationSetting();
    }

    private ChineseTelocation()
    {
    }

    public static String getCurrentCountryIso(Context context)
    {
        Object obj = null;
        CountryDetector countrydetector = (CountryDetector)context.getSystemService("country_detector");
        Object obj1 = obj;
        if(countrydetector != null)
        {
            obj1 = countrydetector.detectCountry();
            if(obj1 != null)
            {
                obj1 = ((Country) (obj1)).getCountryIso();
            } else
            {
                Log.e("ChineseTelocation", "getCurrentCountryIso CountryDetector.detectCountry() is null.");
                obj1 = obj;
            }
        }
        obj = obj1;
        if(obj1 == null)
            obj = context.getResources().getConfiguration().locale.getCountry();
        return ((String) (obj));
    }

    public static ChineseTelocation getInstance()
    {
        return sInstance;
    }

    private void initObserver(Context context)
    {
        mContext = context.getApplicationContext();
        if(mContext == null)
            mContext = context;
        updateTelocationSetting();
        mSettingObserver = new ContentObserver(new Handler(mContext.getMainLooper())) {

            public void onChange(boolean flag)
            {
                super.onChange(flag);
                Log.d("ChineseTelocation", "telocation setting changed, reloading ...");
                ChineseTelocation._2D_wrap0(ChineseTelocation.this);
            }

            final ChineseTelocation this$0;

            
            {
                this$0 = ChineseTelocation.this;
                super(handler);
            }
        }
;
        mContext.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor("enable_telocation"), false, mSettingObserver);
    }

    private void updateTelocationSetting()
    {
        mAllowTelocation = android.provider.MiuiSettings.Telephony.isTelocationEnable(mContext.getContentResolver());
    }

    protected void finalize()
        throws Throwable
    {
        if(mSettingObserver != null)
            mContext.getContentResolver().unregisterContentObserver(mSettingObserver);
        super.finalize();
    }

    public String getAreaCode(CharSequence charsequence, int i, int j)
    {
        return ChineseTelocationConverter.getInstance().getAreaCode(charsequence, i, j);
    }

    public String getExternalLocation(Context context, String s, CharSequence charsequence, Locale locale)
    {
        String s1;
        s1 = s;
        if(TextUtils.isEmpty(s))
        {
            s = CountryCode.getUserDefinedCountryCode();
            s1 = s;
            if(TextUtils.isEmpty(s))
                s1 = CountryCode.getIccCountryCode();
        }
        if(TextUtils.isEmpty(s1))
            break MISSING_BLOCK_LABEL_65;
        context = PhoneNumberOfflineGeocoder.getInstance().getDescriptionForNumber(PhoneNumberUtil.getInstance().parse(charsequence.toString(), getCurrentCountryIso(context)), locale);
        return context;
        context;
        return "";
    }

    public String getLocation(Context context, CharSequence charsequence, int i, int j, boolean flag)
    {
        if(mContext == null)
            initObserver(context);
        if(!mAllowTelocation)
            return "";
        context = "";
        int k = -1;
        if(flag)
            k = ChineseTelocationConverter.getInstance().getUniqId(charsequence, i, j, true);
        if(k > 0)
            context = ChineseTelocationConverter.getInstance().getLocation(charsequence, i, j, flag);
        return context;
    }

    public String getOperator(Context context, CharSequence charsequence, int i, int j, boolean flag)
    {
        if(mContext == null)
            initObserver(context);
        if(!mAllowTelocation)
            return "";
        if(!flag)
            return "";
        else
            return ChineseTelocationConverter.getInstance().getOperator(charsequence, i, j);
    }

    public String parseAreaCode(CharSequence charsequence, int i, int j)
    {
        return ChineseTelocationConverter.getInstance().parseAreaCode(charsequence, i, j);
    }

    private static final String EMPTY = "";
    private static final String TAG = "ChineseTelocation";
    private static ChineseTelocation sInstance = new ChineseTelocation();
    private boolean mAllowTelocation;
    private Context mContext;
    private ContentObserver mSettingObserver;

}
