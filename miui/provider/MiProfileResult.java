// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import miui.graphics.BitmapFactory;

// Referenced classes of package miui.provider:
//            MiProfile

public class MiProfileResult
{

    public MiProfileResult()
    {
    }

    public static String getMiProfileFileName(String s, String s1)
    {
        return String.format("%s-%s", new Object[] {
            Integer.valueOf(s.hashCode()), s1
        });
    }

    public static String normalizeNumber(String s)
    {
        if(TextUtils.isEmpty(s))
            return "";
        miui.telephony.PhoneNumberUtils.PhoneNumber phonenumber = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(PhoneNumberUtils.normalizeNumber(s));
        if(phonenumber != null)
            s = phonenumber.getNormalizedNumber(true, false);
        if(!TextUtils.isEmpty(s))
        {
            if(s.startsWith("+86"))
                return s.substring(3);
            if(s.startsWith("0086"))
                return s.substring(4);
        }
        return s;
    }

    public static MiProfileResult queryContactMiProfile(Context context, String s)
    {
        String s1;
        MiProfileResult miprofileresult;
        Cursor cursor;
        Cursor cursor1;
        s1 = normalizeNumber(s);
        miprofileresult = null;
        cursor = null;
        cursor1 = cursor;
        s = miprofileresult;
        ContentResolver contentresolver = context.getContentResolver();
        cursor1 = cursor;
        s = miprofileresult;
        Uri uri = MiProfile.MIPROFILE_NUMBERS_URI;
        cursor1 = cursor;
        s = miprofileresult;
        long l = System.currentTimeMillis();
        cursor1 = cursor;
        s = miprofileresult;
        cursor = contentresolver.query(uri, new String[] {
            "sid", "type", "name", "json"
        }, "number=? AND type=? AND (expireTime>=? OR expireTime<0)", new String[] {
            s1, "SendCard", String.valueOf(l)
        }, null);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_314;
        cursor1 = cursor;
        s = cursor;
        if(!cursor.moveToFirst())
            break MISSING_BLOCK_LABEL_314;
        cursor1 = cursor;
        s = cursor;
        miprofileresult = JVM INSTR new #2   <Class MiProfileResult>;
        cursor1 = cursor;
        s = cursor;
        miprofileresult.MiProfileResult();
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mSid = cursor.getString(0);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mType = cursor.getString(1);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mName = cursor.getString(2);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mJson = cursor.getString(3);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mThumbnail = queryMiProfilePhoto(context, miprofileresult.mSid, "thumbnail", true);
        cursor1 = cursor;
        s = cursor;
        context = JVM INSTR new #151 <Class StringBuilder>;
        cursor1 = cursor;
        s = cursor;
        context.StringBuilder();
        cursor1 = cursor;
        s = cursor;
        Log.d("MiProfileResult", context.append("queryContactMiProfile(): sid =").append(miprofileresult.mSid).toString());
        if(cursor != null)
            cursor.close();
        return miprofileresult;
        if(cursor != null)
            cursor.close();
_L2:
        return null;
        context;
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L2; else goto _L1
_L1:
        context;
        if(s != null)
            s.close();
        throw context;
    }

    private static Bitmap queryMiProfilePhoto(Context context, String s, String s1, boolean flag)
    {
        s = new File((new StringBuilder()).append("/data/data/com.miui.cloudservice/files/").append(getMiProfileFileName(s, s1)).toString());
        if(!s.exists())
            break MISSING_BLOCK_LABEL_67;
        s1 = BitmapFactory.decodeBitmap(context, Uri.fromFile(s), true);
        if(s1 == null)
            break MISSING_BLOCK_LABEL_67;
        s = s1;
        if(!flag)
            break MISSING_BLOCK_LABEL_64;
        s = BitmapFactory.createPhoto(context, s1);
        return s;
        context;
        return null;
    }

    public static MiProfileResult queryPhoneMiProfile(Context context, String s)
    {
        String s1;
        MiProfileResult miprofileresult;
        Cursor cursor;
        Cursor cursor1;
        if(context == null || TextUtils.isEmpty(s))
            return null;
        s1 = normalizeNumber(s);
        miprofileresult = null;
        cursor = null;
        cursor1 = cursor;
        s = miprofileresult;
        ContentResolver contentresolver = context.getContentResolver();
        cursor1 = cursor;
        s = miprofileresult;
        Uri uri = MiProfile.MIPROFILE_NUMBERS_URI;
        cursor1 = cursor;
        s = miprofileresult;
        long l = System.currentTimeMillis();
        cursor1 = cursor;
        s = miprofileresult;
        cursor = contentresolver.query(uri, new String[] {
            "sid", "name"
        }, "number=? AND type=? AND (expireTime>=? OR expireTime<0)", new String[] {
            s1, "SendCard", String.valueOf(l)
        }, null);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_279;
        cursor1 = cursor;
        s = cursor;
        if(!cursor.moveToFirst())
            break MISSING_BLOCK_LABEL_279;
        cursor1 = cursor;
        s = cursor;
        miprofileresult = JVM INSTR new #2   <Class MiProfileResult>;
        cursor1 = cursor;
        s = cursor;
        miprofileresult.MiProfileResult();
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mSid = cursor.getString(0);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mName = cursor.getString(1);
        cursor1 = cursor;
        s = cursor;
        miprofileresult.mThumbnail = queryMiProfilePhoto(context, miprofileresult.mSid, "thumbnail", false);
        cursor1 = cursor;
        s = cursor;
        context = JVM INSTR new #151 <Class StringBuilder>;
        cursor1 = cursor;
        s = cursor;
        context.StringBuilder();
        cursor1 = cursor;
        s = cursor;
        Log.d("MiProfileResult", context.append("queryPhoneMiProfile(): sid =").append(miprofileresult.mSid).toString());
        if(cursor != null)
            cursor.close();
        return miprofileresult;
        if(cursor != null)
            cursor.close();
_L2:
        return null;
        context;
        if(cursor1 != null)
            cursor1.close();
        if(true) goto _L2; else goto _L1
_L1:
        context;
        if(s != null)
            s.close();
        throw context;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("mSid = ").append(mSid).append(";");
        stringbuilder.append("mType = ").append(mType).append("; ");
        if(!TextUtils.isEmpty(mName))
            stringbuilder.append("mName = ").append(mName).append("; ");
        if(mPhoto != null)
            stringbuilder.append("mPhoto exists; ");
        if(mThumbnail != null)
            stringbuilder.append("mThumbnail exists; ");
        if(!TextUtils.isEmpty(mJson))
            stringbuilder.append("mJson = ").append(mJson).append("; ");
        return stringbuilder.toString();
    }

    private static final String MIPROFILE_DIR = "/data/data/com.miui.cloudservice/files/";
    private static final String TAG = "MiProfileResult";
    public String mJson;
    public String mName;
    public Bitmap mPhoto;
    public String mSid;
    public Bitmap mThumbnail;
    public String mType;
}
