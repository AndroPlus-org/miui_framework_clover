// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.pm.*;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.util.LruCache;
import com.android.internal.util.Preconditions;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.*;

// Referenced classes of package android.provider:
//            FontRequest, BaseColumns

public class FontsContract
{
    public static final class Columns
        implements BaseColumns
    {

        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

        private Columns()
        {
        }
    }

    public static class FontFamilyResult
    {

        public FontInfo[] getFonts()
        {
            return mFonts;
        }

        public int getStatusCode()
        {
            return mStatusCode;
        }

        public static final int STATUS_OK = 0;
        public static final int STATUS_REJECTED = 3;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo mFonts[];
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo afontinfo[])
        {
            mStatusCode = i;
            mFonts = afontinfo;
        }
    }

    public static class FontInfo
    {

        public FontVariationAxis[] getAxes()
        {
            return mAxes;
        }

        public int getResultCode()
        {
            return mResultCode;
        }

        public int getTtcIndex()
        {
            return mTtcIndex;
        }

        public Uri getUri()
        {
            return mUri;
        }

        public int getWeight()
        {
            return mWeight;
        }

        public boolean isItalic()
        {
            return mItalic;
        }

        private final FontVariationAxis mAxes[];
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, FontVariationAxis afontvariationaxis[], int j, boolean flag, int k)
        {
            mUri = (Uri)Preconditions.checkNotNull(uri);
            mTtcIndex = i;
            mAxes = afontvariationaxis;
            mWeight = j;
            mItalic = flag;
            mResultCode = k;
        }
    }

    public static class FontRequestCallback
    {

        public void onTypefaceRequestFailed(int i)
        {
        }

        public void onTypefaceRetrieved(Typeface typeface)
        {
        }

        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        public FontRequestCallback()
        {
        }
    }


    static Object _2D_get0()
    {
        return sLock;
    }

    static HandlerThread _2D_get1()
    {
        return sThread;
    }

    static Handler _2D_set0(Handler handler)
    {
        sHandler = handler;
        return handler;
    }

    static HandlerThread _2D_set1(HandlerThread handlerthread)
    {
        sThread = handlerthread;
        return handlerthread;
    }

    private FontsContract()
    {
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationsignal, FontInfo afontinfo[])
    {
        if(context.isRestricted())
            return null;
        context = prepareFontData(context, afontinfo, cancellationsignal);
        if(context.isEmpty())
            return null;
        else
            return (new android.graphics.Typeface.Builder(afontinfo, context)).build();
    }

    private static List convertToByteArrayList(Signature asignature[])
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < asignature.length; i++)
            arraylist.add(asignature[i].toByteArray());

        return arraylist;
    }

    private static boolean equalsByteArrayList(List list, List list1)
    {
        if(list.size() != list1.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(!Arrays.equals((byte[])list.get(i), (byte[])list1.get(i)))
                return false;

        return true;
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationsignal, FontRequest fontrequest)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        if(context.isRestricted())
            return new FontFamilyResult(3, null);
        ProviderInfo providerinfo = getProvider(context.getPackageManager(), fontrequest);
        if(providerinfo == null)
            return new FontFamilyResult(1, null);
        try
        {
            context = new FontFamilyResult(0, getFontFromProvider(context, fontrequest, providerinfo.authority, cancellationsignal));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return new FontFamilyResult(2, null);
        }
        return context;
    }

    public static FontInfo[] getFontFromProvider(Context context, FontRequest fontrequest, String s, CancellationSignal cancellationsignal)
    {
        ArrayList arraylist;
        Uri uri;
        Uri uri1;
        Object obj;
        Object obj1;
        Object obj2;
        FontVariationAxis afontvariationaxis[];
        arraylist = new ArrayList();
        uri = (new android.net.Uri.Builder()).scheme("content").authority(s).build();
        uri1 = (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("file").build();
        obj = null;
        obj1 = null;
        obj2 = null;
        s = obj2;
        afontvariationaxis = obj1;
        context = context.getContentResolver();
        s = obj2;
        afontvariationaxis = obj1;
        fontrequest = fontrequest.getQuery();
        s = obj2;
        afontvariationaxis = obj1;
        context = context.query(uri, new String[] {
            "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"
        }, "query = ?", new String[] {
            fontrequest
        }, null, cancellationsignal);
        fontrequest = arraylist;
        if(context == null) goto _L2; else goto _L1
_L1:
        fontrequest = arraylist;
        s = context;
        afontvariationaxis = context;
        if(context.getCount() <= 0) goto _L2; else goto _L3
_L3:
        s = context;
        afontvariationaxis = context;
        int i = context.getColumnIndex("result_code");
        s = context;
        afontvariationaxis = context;
        cancellationsignal = JVM INSTR new #118 <Class ArrayList>;
        s = context;
        afontvariationaxis = context;
        cancellationsignal.ArrayList();
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        j = context.getColumnIndexOrThrow("_id");
        k = context.getColumnIndex("file_id");
        l = context.getColumnIndex("font_ttc_index");
        i1 = context.getColumnIndex("font_variation_settings");
        j1 = context.getColumnIndex("font_weight");
        k1 = context.getColumnIndex("font_italic");
_L16:
        if(!context.moveToNext()) goto _L5; else goto _L4
_L4:
        if(i == -1) goto _L7; else goto _L6
_L6:
        int l1 = context.getInt(i);
_L17:
        if(l == -1) goto _L9; else goto _L8
_L8:
        int i2 = context.getInt(l);
_L18:
        if(i1 == -1) goto _L11; else goto _L10
_L10:
        fontrequest = context.getString(i1);
_L19:
        if(k != -1) goto _L13; else goto _L12
_L12:
        s = ContentUris.withAppendedId(uri, context.getLong(j));
_L20:
        if(j1 == -1 || k1 == -1) goto _L15; else goto _L14
_L14:
        int j2 = context.getInt(j1);
        boolean flag;
        if(context.getInt(k1) == 1)
            flag = true;
        else
            flag = false;
_L21:
        afontvariationaxis = FontVariationAxis.fromFontVariationSettings(fontrequest);
        fontrequest = JVM INSTR new #14  <Class FontsContract$FontInfo>;
        fontrequest.FontInfo(s, i2, afontvariationaxis, j2, flag, l1);
        cancellationsignal.add(fontrequest);
          goto _L16
        fontrequest;
_L25:
        throw fontrequest;
        cancellationsignal;
_L24:
        s = fontrequest;
        if(context == null)
            break MISSING_BLOCK_LABEL_438;
        context.close();
        s = fontrequest;
_L23:
        if(s != null)
            throw s;
        else
            throw cancellationsignal;
_L7:
        l1 = 0;
          goto _L17
_L9:
        i2 = 0;
          goto _L18
_L11:
        fontrequest = null;
          goto _L19
_L13:
        s = ContentUris.withAppendedId(uri1, context.getLong(k));
          goto _L20
_L15:
        j2 = 400;
        flag = false;
          goto _L21
_L5:
        fontrequest = cancellationsignal;
_L2:
        s = obj;
        if(context == null)
            break MISSING_BLOCK_LABEL_513;
        context.close();
        s = obj;
_L22:
        if(s != null)
            throw s;
        else
            return (FontInfo[])fontrequest.toArray(new FontInfo[0]);
        s;
          goto _L22
        context;
        if(fontrequest == null)
        {
            s = context;
        } else
        {
            s = fontrequest;
            if(fontrequest != context)
            {
                fontrequest.addSuppressed(context);
                s = fontrequest;
            }
        }
          goto _L23
        cancellationsignal;
        fontrequest = null;
        context = s;
          goto _L24
        cancellationsignal;
        fontrequest = null;
          goto _L24
        fontrequest;
        context = afontvariationaxis;
          goto _L25
    }

    public static Typeface getFontSync(FontRequest fontrequest)
    {
        String s;
        s = fontrequest.getIdentifier();
        Typeface typeface = (Typeface)sTypefaceCache.get(s);
        if(typeface != null)
            return typeface;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        ReentrantLock reentrantlock;
        Condition condition;
        AtomicReference atomicreference;
        AtomicBoolean atomicboolean;
        AtomicBoolean atomicboolean1;
        long l;
        if(sHandler == null)
        {
            Object obj1 = JVM INSTR new #304 <Class HandlerThread>;
            ((HandlerThread) (obj1)).HandlerThread("fonts", 10);
            sThread = ((HandlerThread) (obj1));
            sThread.start();
            obj1 = JVM INSTR new #314 <Class Handler>;
            ((Handler) (obj1)).Handler(sThread.getLooper());
            sHandler = ((Handler) (obj1));
        }
        reentrantlock = JVM INSTR new #323 <Class ReentrantLock>;
        reentrantlock.ReentrantLock();
        condition = reentrantlock.newCondition();
        atomicreference = JVM INSTR new #332 <Class AtomicReference>;
        atomicreference.AtomicReference();
        atomicboolean = JVM INSTR new #335 <Class AtomicBoolean>;
        atomicboolean.AtomicBoolean(true);
        atomicboolean1 = JVM INSTR new #335 <Class AtomicBoolean>;
        atomicboolean1.AtomicBoolean(false);
        Handler handler = sHandler;
        _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls3 _lcls3 = JVM INSTR new #340 <Class _$Lambda$a7Jyr6j_Mb70hHJ2ssL1AAhKh4c$3>;
        _lcls3._.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls3(fontrequest, s, atomicreference, reentrantlock, atomicboolean1, atomicboolean, condition);
        handler.post(_lcls3);
        sHandler.removeCallbacks(sReplaceDispatcherThreadRunnable);
        sHandler.postDelayed(sReplaceDispatcherThreadRunnable, 10000L);
        l = TimeUnit.MILLISECONDS.toNanos(500L);
        reentrantlock.lock();
        if(atomicboolean.get())
            break MISSING_BLOCK_LABEL_223;
        fontrequest = (Typeface)atomicreference.get();
        reentrantlock.unlock();
        obj;
        JVM INSTR monitorexit ;
        return fontrequest;
_L2:
        long l1;
        try
        {
            l1 = condition.awaitNanos(l);
        }
        catch(InterruptedException interruptedexception)
        {
            l1 = l;
        }
        if(atomicboolean.get())
            break MISSING_BLOCK_LABEL_269;
        fontrequest = (Typeface)atomicreference.get();
        reentrantlock.unlock();
        obj;
        JVM INSTR monitorexit ;
        return fontrequest;
        l = l1;
        if(l1 > 0L) goto _L2; else goto _L1
_L1:
        atomicboolean1.set(true);
        StringBuilder stringbuilder = JVM INSTR new #387 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("FontsContract", stringbuilder.append("Remote font fetch timed out: ").append(fontrequest.getProviderAuthority()).append("/").append(fontrequest.getQuery()).toString());
        reentrantlock.unlock();
        obj;
        JVM INSTR monitorexit ;
        return null;
        fontrequest;
        reentrantlock.unlock();
        throw fontrequest;
        fontrequest;
        obj;
        JVM INSTR monitorexit ;
        throw fontrequest;
    }

    public static ProviderInfo getProvider(PackageManager packagemanager, FontRequest fontrequest)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        String s = fontrequest.getProviderAuthority();
        ProviderInfo providerinfo = packagemanager.resolveContentProvider(s, 0);
        if(providerinfo == null)
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("No package found for authority: ").append(s).toString());
        if(!providerinfo.packageName.equals(fontrequest.getProviderPackage()))
            throw new android.content.pm.PackageManager.NameNotFoundException((new StringBuilder()).append("Found content provider ").append(s).append(", but package was not ").append(fontrequest.getProviderPackage()).toString());
        if(providerinfo.applicationInfo.isSystemApp())
            return providerinfo;
        packagemanager = convertToByteArrayList(packagemanager.getPackageInfo(providerinfo.packageName, 64).signatures);
        Collections.sort(packagemanager, sByteArrayComparator);
        fontrequest = fontrequest.getCertificates();
        for(int i = 0; i < fontrequest.size(); i++)
        {
            ArrayList arraylist = new ArrayList((Collection)fontrequest.get(i));
            Collections.sort(arraylist, sByteArrayComparator);
            if(equalsByteArrayList(packagemanager, arraylist))
                return providerinfo;
        }

        return null;
    }

    static void lambda$_2D_android_provider_FontsContract_13824(FontRequest fontrequest, String s, AtomicReference atomicreference, Lock lock, AtomicBoolean atomicboolean, AtomicBoolean atomicboolean1, Condition condition)
    {
        fontrequest = fetchFonts(sContext, null, fontrequest);
        if(fontrequest.getStatusCode() != 0)
            break MISSING_BLOCK_LABEL_46;
        fontrequest = buildTypeface(sContext, null, fontrequest.getFonts());
        if(fontrequest == null)
            break MISSING_BLOCK_LABEL_41;
        sTypefaceCache.put(s, fontrequest);
        atomicreference.set(fontrequest);
_L2:
        lock.lock();
        if(!atomicboolean.get())
        {
            atomicboolean1.set(false);
            condition.signal();
        }
        lock.unlock();
        return;
        fontrequest;
        lock.unlock();
        throw fontrequest;
        fontrequest;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static void lambda$_2D_android_provider_FontsContract_20860(FontRequestCallback fontrequestcallback, Typeface typeface)
    {
        fontrequestcallback.onTypefaceRetrieved(typeface);
    }

    static void lambda$_2D_android_provider_FontsContract_20965(Context context, CancellationSignal cancellationsignal, FontRequest fontrequest, Handler handler, FontRequestCallback fontrequestcallback)
    {
        int i = 0;
        FontFamilyResult fontfamilyresult;
        Typeface typeface;
        try
        {
            fontfamilyresult = fetchFonts(context, cancellationsignal, fontrequest);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)0, fontrequestcallback));
            return;
        }
        typeface = (Typeface)sTypefaceCache.get(fontrequest.getIdentifier());
        if(typeface != null)
        {
            handler.post(new _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls1((byte)0, fontrequestcallback, typeface));
            return;
        }
        if(fontfamilyresult.getStatusCode() != 0)
        {
            switch(fontfamilyresult.getStatusCode())
            {
            default:
                handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)3, fontrequestcallback));
                return;

            case 1: // '\001'
                handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)1, fontrequestcallback));
                return;

            case 2: // '\002'
                handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)2, fontrequestcallback));
                break;
            }
            return;
        }
        FontInfo afontinfo[] = fontfamilyresult.getFonts();
        if(afontinfo == null || afontinfo.length == 0)
        {
            handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)4, fontrequestcallback));
            return;
        }
        for(int j = afontinfo.length; i < j; i++)
        {
            FontInfo fontinfo = afontinfo[i];
            if(fontinfo.getResultCode() != 0)
            {
                i = fontinfo.getResultCode();
                if(i < 0)
                    handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)5, fontrequestcallback));
                else
                    handler.post(new _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls4(i, fontrequestcallback));
                return;
            }
        }

        context = buildTypeface(context, cancellationsignal, afontinfo);
        if(context == null)
        {
            handler.post(new _.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)6, fontrequestcallback));
            return;
        } else
        {
            sTypefaceCache.put(fontrequest.getIdentifier(), context);
            handler.post(new _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls1((byte)1, fontrequestcallback, context));
            return;
        }
    }

    static void lambda$_2D_android_provider_FontsContract_21192(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-1);
    }

    static void lambda$_2D_android_provider_FontsContract_21625(FontRequestCallback fontrequestcallback, Typeface typeface)
    {
        fontrequestcallback.onTypefaceRetrieved(typeface);
    }

    static void lambda$_2D_android_provider_FontsContract_21964(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-2);
    }

    static void lambda$_2D_android_provider_FontsContract_22246(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-3);
    }

    static void lambda$_2D_android_provider_FontsContract_22572(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-3);
    }

    static void lambda$_2D_android_provider_FontsContract_22911(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(1);
    }

    static void lambda$_2D_android_provider_FontsContract_23564(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-3);
    }

    static void lambda$_2D_android_provider_FontsContract_23765(FontRequestCallback fontrequestcallback, int i)
    {
        fontrequestcallback.onTypefaceRequestFailed(i);
    }

    static void lambda$_2D_android_provider_FontsContract_24251(FontRequestCallback fontrequestcallback)
    {
        fontrequestcallback.onTypefaceRequestFailed(-3);
    }

    static void lambda$_2D_android_provider_FontsContract_24509(FontRequestCallback fontrequestcallback, Typeface typeface)
    {
        fontrequestcallback.onTypefaceRetrieved(typeface);
    }

    static int lambda$_2D_android_provider_FontsContract_31229(byte abyte0[], byte abyte1[])
    {
        if(abyte0.length != abyte1.length)
            return abyte0.length - abyte1.length;
        for(int i = 0; i < abyte0.length; i++)
            if(abyte0[i] != abyte1[i])
                return abyte0[i] - abyte1[i];

        return 0;
    }

    private static Map prepareFontData(Context context, FontInfo afontinfo[], CancellationSignal cancellationsignal)
    {
        HashMap hashmap;
        ContentResolver contentresolver;
        int i;
        int j;
        hashmap = new HashMap();
        contentresolver = context.getContentResolver();
        i = afontinfo.length;
        j = 0;
_L5:
        if(j >= i) goto _L2; else goto _L1
_L1:
        context = afontinfo[j];
        if(context.getResultCode() == 0) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L4:
        Uri uri = context.getUri();
        if(hashmap.containsKey(uri)) goto _L3; else goto _L6
_L6:
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = obj;
        obj7 = obj1;
        Object obj8 = contentresolver.openFileDescriptor(uri, "r", cancellationsignal);
        obj7 = obj2;
        if(obj8 == null) goto _L8; else goto _L7
_L7:
        obj5 = null;
        obj7 = null;
        obj4 = null;
        obj6 = JVM INSTR new #556 <Class FileInputStream>;
        ((FileInputStream) (obj6)).FileInputStream(((ParcelFileDescriptor) (obj8)).getFileDescriptor());
        context = ((FileInputStream) (obj6)).getChannel();
        long l = context.size();
        context = context.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        Object obj9;
        obj9 = obj5;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_181;
        obj7 = context;
        obj4 = obj8;
        ((FileInputStream) (obj6)).close();
        obj9 = obj5;
_L10:
        obj7 = context;
        if(obj9 == null) goto _L8; else goto _L9
_L9:
        obj6 = context;
        obj5 = obj8;
        obj7 = context;
        obj4 = obj8;
        throw obj9;
        obj7;
        obj7 = context;
_L8:
        obj4 = obj3;
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_232;
        context = ((Context) (obj7));
        ((ParcelFileDescriptor) (obj8)).close();
        obj4 = obj3;
_L16:
        context = ((Context) (obj7));
        if(obj4 != null)
        {
            context = ((Context) (obj7));
            try
            {
                throw obj4;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj7) { }
        }
        hashmap.put(uri, context);
          goto _L3
        obj9;
          goto _L10
        context;
        obj6 = obj4;
_L20:
        throw context;
        Exception exception1;
        exception1;
_L19:
        Object obj10;
        obj10 = context;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_297;
        obj7 = obj1;
        obj4 = obj8;
        ((FileInputStream) (obj6)).close();
        obj10 = context;
_L13:
        if(obj10 == null) goto _L12; else goto _L11
_L11:
        obj6 = obj;
        obj5 = obj8;
        obj7 = obj1;
        obj4 = obj8;
        throw obj10;
        context;
        obj7 = obj2;
          goto _L8
        Throwable throwable1;
        throwable1;
label0:
        {
            if(context != null)
                break label0;
            obj10 = throwable1;
        }
          goto _L13
        obj10 = context;
        if(context == throwable1) goto _L13; else goto _L14
_L14:
        obj6 = obj;
        obj5 = obj8;
        obj7 = obj1;
        obj4 = obj8;
        context.addSuppressed(throwable1);
        obj10 = context;
          goto _L13
        obj8;
        throw obj8;
        context;
        obj4 = obj5;
        obj7 = obj6;
        obj6 = context;
_L15:
        obj5 = obj8;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_417;
        context = ((Context) (obj7));
        ((ParcelFileDescriptor) (obj4)).close();
        obj5 = obj8;
_L17:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_502;
        context = ((Context) (obj7));
        throw obj5;
_L12:
        obj6 = obj;
        obj5 = obj8;
        obj7 = obj1;
        obj4 = obj8;
        throw exception1;
        Exception exception;
        exception;
        obj8 = null;
          goto _L15
        obj4;
          goto _L16
        Throwable throwable;
        throwable;
label1:
        {
            if(obj8 != null)
                break label1;
            obj5 = throwable;
        }
          goto _L17
        obj5 = obj8;
        if(obj8 == throwable) goto _L17; else goto _L18
_L18:
        context = ((Context) (obj7));
        ((Throwable) (obj8)).addSuppressed(throwable);
        obj5 = obj8;
          goto _L17
        context = ((Context) (obj7));
        throw exception;
_L2:
        return Collections.unmodifiableMap(hashmap);
        exception1;
        context = null;
        exception = ((Exception) (obj7));
          goto _L19
        exception1;
        context = null;
          goto _L19
        context;
          goto _L20
    }

    public static void requestFonts(Context context, FontRequest fontrequest, Handler handler, CancellationSignal cancellationsignal, FontRequestCallback fontrequestcallback)
    {
        Handler handler1 = new Handler();
        Typeface typeface = (Typeface)sTypefaceCache.get(fontrequest.getIdentifier());
        if(typeface != null)
        {
            handler1.post(new _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls1((byte)2, fontrequestcallback, typeface));
            return;
        } else
        {
            handler.post(new _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c._cls2(context, cancellationsignal, fontrequest, handler1, fontrequestcallback));
            return;
        }
    }

    public static void setApplicationContextForResources(Context context)
    {
        sContext = context.getApplicationContext();
    }

    private static final long SYNC_FONT_FETCH_TIMEOUT_MS = 500L;
    private static final String TAG = "FontsContract";
    private static final int THREAD_RENEWAL_THRESHOLD_MS = 10000;
    private static final Comparator sByteArrayComparator;
    private static volatile Context sContext;
    private static Handler sHandler;
    private static Set sInQueueSet;
    private static final Object sLock = new Object();
    private static final Runnable sReplaceDispatcherThreadRunnable = new Runnable() {

        public void run()
        {
            Object obj = FontsContract._2D_get0();
            obj;
            JVM INSTR monitorenter ;
            if(FontsContract._2D_get1() != null)
            {
                FontsContract._2D_get1().quitSafely();
                FontsContract._2D_set1(null);
                FontsContract._2D_set0(null);
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

    }
;
    private static HandlerThread sThread;
    private static final LruCache sTypefaceCache = new LruCache(16);

    static 
    {
        sByteArrayComparator = _.Lambda.a7Jyr6j_Mb70hHJ2ssL1AAhKh4c.$INST$0;
    }
}
