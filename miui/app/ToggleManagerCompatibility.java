// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import miui.os.Build;

// Referenced classes of package miui.app:
//            ToggleManager

public class ToggleManagerCompatibility
{
    public final class CustomToggleQueryRunnable
        implements Runnable
    {

        public void run()
        {
            Object obj = ToggleManagerCompatibility._2D_get9();
            obj;
            JVM INSTR monitorenter ;
            PackageManager packagemanager;
            Object obj2;
            int i;
            ToggleManagerCompatibility._2D_get3().clear();
            ToggleManagerCompatibility._2D_get5().clear();
            ToggleManagerCompatibility._2D_get4().clear();
            ToggleManagerCompatibility._2D_get1().clear();
            packagemanager = ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this).getPackageManager();
            obj2 = JVM INSTR new #64  <Class Intent>;
            ((Intent) (obj2)).Intent("android.service.quicksettings.action.QS_TILE");
            obj2 = packagemanager.queryIntentServicesAsUser(((Intent) (obj2)), 0, ToggleManagerCompatibility._2D_wrap2(ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this)));
            i = ((List) (obj2)).size();
            if(i != 0)
                break MISSING_BLOCK_LABEL_84;
            obj;
            JVM INSTR monitorexit ;
            return;
            obj2 = ((Iterable) (obj2)).iterator();
_L5:
            if(!((Iterator) (obj2)).hasNext()) goto _L2; else goto _L1
_L1:
            ResolveInfo resolveinfo;
            Object obj3;
            Object obj4;
            String s;
            boolean flag;
            resolveinfo = (ResolveInfo)((Iterator) (obj2)).next();
            obj3 = resolveinfo.serviceInfo.packageName;
            obj4 = JVM INSTR new #114 <Class ComponentName>;
            ((ComponentName) (obj4)).ComponentName(((String) (obj3)), resolveinfo.serviceInfo.name);
            resolveinfo.serviceInfo.applicationInfo.loadLabel(packagemanager);
            s = ToggleManagerCompatibility.toSpec(((ComponentName) (obj4)));
            if(resolveinfo.serviceInfo.icon == 0 && resolveinfo.serviceInfo.applicationInfo.icon == 0)
                continue; /* Loop/switch isn't completed */
            flag = "android.permission.BIND_QUICK_SETTINGS_TILE".equals(resolveinfo.serviceInfo.permission);
            if(!flag)
                continue; /* Loop/switch isn't completed */
            i = 0xc0000;
            if(ToggleManagerCompatibility._2D_wrap0(packagemanager, ((ComponentName) (obj4))))
                i = 0xc0200;
            obj4 = AppGlobals.getPackageManager().getServiceInfo(((ComponentName) (obj4)), i, ToggleManagerCompatibility._2D_wrap2(ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this)));
            if(((ServiceInfo) (obj4)).icon == 0)
                break MISSING_BLOCK_LABEL_394;
            i = ((ServiceInfo) (obj4)).icon;
_L3:
            obj3 = Icon.createWithResource(((String) (obj3)), i);
            obj4 = resolveinfo.serviceInfo.loadLabel(packagemanager);
            if(obj3 == null || obj4 == null)
                continue; /* Loop/switch isn't completed */
            Integer integer1 = Integer.valueOf(ToggleManagerCompatibility._2D_wrap1(s) + 10000);
            ToggleManagerCompatibility._2D_get3().put(integer1, s);
            ToggleManagerCompatibility._2D_get5().put(integer1, ((CharSequence) (obj4)).toString());
            ToggleManagerCompatibility._2D_get1().add(integer1);
            ToggleManagerCompatibility._2D_get6().put(integer1, Boolean.valueOf(false));
            ToggleManagerCompatibility._2D_get4().put(integer1, ((Icon) (obj3)).loadDrawable(ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this)));
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            exception1.printStackTrace();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            throw exception;
            i = ((ServiceInfo) (obj4)).applicationInfo.icon;
              goto _L3
_L2:
            obj2 = ToggleManagerCompatibility.getUserSelectedToggleOrder(ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this));
            Iterator iterator = ToggleManagerCompatibility._2D_get1().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Integer integer = (Integer)iterator.next();
                if(!((ArrayList) (obj2)).contains(integer))
                    ((ArrayList) (obj2)).add(integer);
            } while(true);
            setUserSelectedToggleOrder(((ArrayList) (obj2)));
            obj2 = ToggleManagerCompatibility._2D_get7(ToggleManagerCompatibility.this);
            Object obj1 = JVM INSTR new #11  <Class ToggleManagerCompatibility$CustomToggleQueryRunnable$1>;
            ((_cls1) (obj1)).this. _cls1();
            ((Handler) (obj2)).post(((Runnable) (obj1)));
            obj1 = ToggleManagerCompatibility._2D_get7(ToggleManagerCompatibility.this);
            obj2 = JVM INSTR new #13  <Class ToggleManagerCompatibility$CustomToggleQueryRunnable$2>;
            ((_cls2) (obj2)).this. _cls2();
            ((Handler) (obj1)).postDelayed(((Runnable) (obj2)), 300L);
            obj;
            JVM INSTR monitorexit ;
            return;
            if(true) goto _L5; else goto _L4
_L4:
        }

        final ToggleManagerCompatibility this$0;

        public CustomToggleQueryRunnable()
        {
            this$0 = ToggleManagerCompatibility.this;
            super();
        }
    }

    public static interface OnCustomToggleChangedListener
    {

        public abstract void onCustomToggleChanged();
    }

    public static class Point
    {

        public int mHeight;
        public int mWidth;

        public Point()
        {
        }
    }


    static Context _2D_get0(ToggleManagerCompatibility togglemanagercompatibility)
    {
        return togglemanagercompatibility.mContext;
    }

    static ArrayList _2D_get1()
    {
        return mCustomIds;
    }

    static List _2D_get10(ToggleManagerCompatibility togglemanagercompatibility)
    {
        return togglemanagercompatibility.mToggleOrderChangedListener;
    }

    static List _2D_get2(ToggleManagerCompatibility togglemanagercompatibility)
    {
        return togglemanagercompatibility.mCustomToggleChangedListeners;
    }

    static HashMap _2D_get3()
    {
        return mCustomToggleComponentNames;
    }

    static HashMap _2D_get4()
    {
        return mCustomToggleImages;
    }

    static HashMap _2D_get5()
    {
        return mCustomToggleLabelNames;
    }

    static HashMap _2D_get6()
    {
        return mCustomToggleStatus;
    }

    static Handler _2D_get7(ToggleManagerCompatibility togglemanagercompatibility)
    {
        return togglemanagercompatibility.mHandler;
    }

    static boolean _2D_get8()
    {
        return mIsSystemUI;
    }

    static Object _2D_get9()
    {
        return mObjectLock;
    }

    static boolean _2D_wrap0(PackageManager packagemanager, ComponentName componentname)
    {
        return isSystemApp(packagemanager, componentname);
    }

    static int _2D_wrap1(String s)
    {
        return getMd5Num(s);
    }

    static int _2D_wrap2(Context context)
    {
        return getUserId(context);
    }

    private ToggleManagerCompatibility(Context context)
    {
        mCustomToggleQueryRunnable = new CustomToggleQueryRunnable();
        mPackageChangeReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if("android.intent.action.PACKAGE_ADDED".equals(context1))
                {
                    if(ToggleManagerCompatibility.mEnableCustom)
                        queryCustomToggles();
                } else
                {
                    "android.intent.action.PACKAGE_REMOVED".equals(context1);
                }
            }

            final ToggleManagerCompatibility this$0;

            
            {
                this$0 = ToggleManagerCompatibility.this;
                super();
            }
        }
;
        mDevelopmentObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag)
            {
                if(ToggleManagerCompatibility.mEnableCustom)
                    queryCustomToggles();
            }

            final ToggleManagerCompatibility this$0;

            
            {
                this$0 = ToggleManagerCompatibility.this;
                super(handler);
            }
        }
;
        mTogglOrderObserver = new ContentObserver(mHandler) {

            public void onChange(boolean flag)
            {
                Object obj = ToggleManagerCompatibility._2D_get9();
                obj;
                JVM INSTR monitorenter ;
                ArrayList arraylist = ToggleManagerCompatibility.getCustomToggleIds(ToggleManagerCompatibility._2D_get0(ToggleManagerCompatibility.this));
                int i = 0;
_L2:
                if(i >= ToggleManagerCompatibility._2D_get1().size())
                    break; /* Loop/switch isn't completed */
                if(!arraylist.contains(ToggleManagerCompatibility._2D_get1().get(i)))
                {
                    ToggleManagerCompatibility._2D_get3().remove(ToggleManagerCompatibility._2D_get1().get(i));
                    ToggleManagerCompatibility._2D_get5().remove(ToggleManagerCompatibility._2D_get1().get(i));
                    ToggleManagerCompatibility._2D_get6().remove(ToggleManagerCompatibility._2D_get1().get(i));
                    ToggleManagerCompatibility._2D_get4().remove(ToggleManagerCompatibility._2D_get1().get(i));
                    ToggleManagerCompatibility._2D_get1().remove(ToggleManagerCompatibility._2D_get1().get(i));
                }
                i++;
                if(true) goto _L2; else goto _L1
_L1:
                for(obj = ToggleManagerCompatibility._2D_get10(ToggleManagerCompatibility.this).iterator(); ((Iterator) (obj)).hasNext(); ((ToggleManager.OnToggleOrderChangedListener)((Iterator) (obj)).next()).OnToggleOrderChanged());
                break MISSING_BLOCK_LABEL_173;
                Exception exception;
                exception;
                throw exception;
                if(!ToggleManagerCompatibility._2D_get8())
                {
                    for(Iterator iterator = ToggleManagerCompatibility._2D_get2(ToggleManagerCompatibility.this).iterator(); iterator.hasNext(); ((OnCustomToggleChangedListener)iterator.next()).onCustomToggleChanged());
                }
                return;
            }

            final ToggleManagerCompatibility this$0;

            
            {
                this$0 = ToggleManagerCompatibility.this;
                super(handler);
            }
        }
;
        mContext = context;
        if(mToggleManager == null)
            mToggleManager = ToggleManager.createInstance(context);
        mBgThread = new HandlerThread("ToggleManagerCompatibility", 10);
        mBgThread.start();
        mBgHandler = new Handler(mBgThread.getLooper());
        mResolver = context.getContentResolver();
        mIsSystemUI = "com.android.systemui".equals(context.getApplicationInfo().packageName);
        mToggleChangedListener = new ArrayList();
        mToggleOrderChangedListener = new ArrayList();
        mCustomToggleChangedListeners = new ArrayList();
        mCustomToggleOffColor = context.getResources().getColor(0x11060001);
        mCustomToggleOnColor = context.getResources().getColor(0x11060000);
        registerListener(mIsSystemUI);
    }

    public static ToggleManagerCompatibility createInstance(Context context)
    {
        if(mToggleManagerCompatibility == null)
            mToggleManagerCompatibility = new ToggleManagerCompatibility(context.getApplicationContext());
        return mToggleManagerCompatibility;
    }

    private static Bitmap drawableToBitmap(Drawable drawable)
    {
        if(drawable instanceof BitmapDrawable)
            return ((BitmapDrawable)drawable).getBitmap();
        int i = drawable.getIntrinsicWidth();
        int j = drawable.getIntrinsicHeight();
        if(i == 0 || j == 0)
            return null;
        Object obj;
        Canvas canvas;
        if(drawable.getOpacity() != -1)
            obj = android.graphics.Bitmap.Config.ARGB_8888;
        else
            obj = android.graphics.Bitmap.Config.RGB_565;
        obj = Bitmap.createBitmap(i, j, ((android.graphics.Bitmap.Config) (obj)));
        canvas = new Canvas(((Bitmap) (obj)));
        drawable.setBounds(0, 0, i, j);
        drawable.draw(canvas);
        return ((Bitmap) (obj));
    }

    public static ArrayList getCustomToggleIds(Context context)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        context = getToggleList(context);
        if(TextUtils.isEmpty(context)) goto _L2; else goto _L1
_L1:
        int i;
        context = context.split(" ");
        i = 0;
_L3:
        if(i >= context.length)
            break; /* Loop/switch isn't completed */
        int j = Integer.valueOf(context[i]).intValue();
        if(j < 10000)
            break MISSING_BLOCK_LABEL_62;
        arraylist.add(Integer.valueOf(j));
        i++;
        if(true) goto _L3; else goto _L2
        context;
        context.printStackTrace();
        arraylist.clear();
_L2:
        return arraylist;
    }

    public static String getCustomToggleTileSpecById(Integer integer)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        integer = (String)mCustomToggleComponentNames.get(integer);
        obj;
        JVM INSTR monitorexit ;
        return integer;
        integer;
        throw integer;
    }

    public static ArrayList getCustomToggleTileSpecs(Context context)
    {
        context = ((Context) (mObjectLock));
        context;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = JVM INSTR new #144 <Class ArrayList>;
        arraylist.ArrayList();
        Integer integer;
        for(Iterator iterator = mCustomIds.iterator(); iterator.hasNext(); arraylist.add((String)mCustomToggleComponentNames.get(integer)))
            integer = (Integer)iterator.next();

        break MISSING_BLOCK_LABEL_65;
        Exception exception;
        exception;
        throw exception;
        context;
        JVM INSTR monitorexit ;
        return arraylist;
    }

    public static Drawable getImageDrawable(int i, Context context)
    {
        return getImageDrawable(i, context, 0);
    }

    public static Drawable getImageDrawable(int i, Context context, int j)
    {
        return getImageDrawable(i, getStatus(i), context, j, mCustomToggleOnColor, mCustomToggleOffColor);
    }

    public static Drawable getImageDrawable(int i, boolean flag, Context context)
    {
        return getImageDrawable(i, flag, context, 0, mCustomToggleOnColor, mCustomToggleOffColor);
    }

    public static Drawable getImageDrawable(int i, boolean flag, Context context, int j, int k, int l)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        Drawable drawable = null;
        if(i < 10000) goto _L2; else goto _L1
_L1:
        if(mCustomToggleImages.get(Integer.valueOf(i)) == null) goto _L4; else goto _L3
_L3:
        drawable = (Drawable)mCustomToggleImages.get(Integer.valueOf(i));
_L11:
        if(flag) goto _L6; else goto _L5
_L5:
        Object obj1;
        obj1 = drawable;
        if(i < 10000)
            break MISSING_BLOCK_LABEL_261;
        Drawable drawable1;
        Bitmap bitmap;
        Canvas canvas;
        drawable1 = context.getResources().getDrawable(0x110200b1);
        bitmap = Bitmap.createBitmap(drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        canvas = JVM INSTR new #282 <Class Canvas>;
        canvas.Canvas(bitmap);
        drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        drawable1.draw(canvas);
        obj1 = JVM INSTR new #22  <Class ToggleManagerCompatibility$Point>;
        ((Point) (obj1)).Point();
        getTargetDrawableSize(context, ((Point) (obj1)));
        drawable = zoomDrawable(context, drawable, (int)((double)((Point) (obj1)).mWidth / 2.5D), (int)((double)((Point) (obj1)).mHeight / 2.5D));
        obj1 = drawable;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_261;
        i = (drawable1.getIntrinsicWidth() - drawable.getIntrinsicWidth()) / 2;
        drawable.setBounds(i, i, drawable1.getIntrinsicWidth() - i, drawable1.getIntrinsicHeight() - i);
        if(drawable == null)
            break MISSING_BLOCK_LABEL_239;
        if(Color.alpha(j) == 0)
            drawable.setColorFilter(l, android.graphics.PorterDuff.Mode.SRC_IN);
        drawable.draw(canvas);
        obj1 = new BitmapDrawable(context.getResources(), bitmap);
_L8:
        obj;
        JVM INSTR monitorexit ;
        return ((Drawable) (obj1));
_L4:
        obj;
        JVM INSTR monitorexit ;
        return null;
_L2:
        if(mToggleManager != null)
        {
            obj1 = mToggleManager;
            drawable = ToggleManager.getImageDrawable(i, flag, context);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        obj1 = drawable;
        if(i < 10000) goto _L8; else goto _L7
_L7:
        drawable1 = context.getResources().getDrawable(0x110200b2);
        bitmap = Bitmap.createBitmap(drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        canvas = JVM INSTR new #282 <Class Canvas>;
        canvas.Canvas(bitmap);
        drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        if(drawable1 == null)
            break MISSING_BLOCK_LABEL_385;
        if(Color.alpha(j) != 0)
            drawable1.setColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN);
        drawable1.draw(canvas);
        obj1 = JVM INSTR new #22  <Class ToggleManagerCompatibility$Point>;
        ((Point) (obj1)).Point();
        getTargetDrawableSize(context, ((Point) (obj1)));
        drawable = zoomDrawable(context, drawable, (int)((double)((Point) (obj1)).mWidth / 2.5D), (int)((double)((Point) (obj1)).mHeight / 2.5D));
        obj1 = drawable;
        if(drawable == null) goto _L8; else goto _L9
_L9:
        i = (drawable1.getIntrinsicWidth() - drawable.getIntrinsicWidth()) / 2;
        drawable.setBounds(i, i, drawable1.getIntrinsicWidth() - i, drawable1.getIntrinsicHeight() - i);
        drawable.setColorFilter(k, android.graphics.PorterDuff.Mode.SRC_IN);
        drawable.draw(canvas);
        obj1 = new BitmapDrawable(context.getResources(), bitmap);
          goto _L8
        context;
        throw context;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private static int getMd5Num(String s)
    {
        int i;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            s = JVM INSTR new #437 <Class BigInteger>;
            s.BigInteger(1, messagedigest.digest());
            i = Math.abs(s.intValue());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
        return i;
    }

    public static boolean getStatus(int i)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        if(i < 10000)
            break MISSING_BLOCK_LABEL_39;
        boolean flag;
        try
        {
            flag = ((Boolean)mCustomToggleStatus.get(Integer.valueOf(i))).booleanValue();
        }
        catch(Exception exception)
        {
            return false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        if(mToggleManager == null)
            break MISSING_BLOCK_LABEL_58;
        ToggleManager togglemanager = mToggleManager;
        flag = ToggleManager.getStatus(i);
        return flag;
        obj;
        JVM INSTR monitorexit ;
        return false;
        Exception exception1;
        exception1;
        throw exception1;
    }

    public static String getStatusName(int i, Resources resources)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = "";
        if(i < 10000) goto _L2; else goto _L1
_L1:
        obj1 = (String)mCustomToggleLabelNames.get(Integer.valueOf(i));
_L4:
        obj;
        JVM INSTR monitorexit ;
        return ((String) (obj1));
_L2:
        if(mToggleManager == null) goto _L4; else goto _L3
_L3:
        obj1 = mToggleManager;
        obj1 = ToggleManager.getStatusName(i, resources);
          goto _L4
        resources;
        throw resources;
    }

    public static void getTargetDrawableSize(Context context, Point point)
    {
        context.getResources().getDisplayMetrics().densityDpi;
        JVM INSTR lookupswitch 3: default 44
    //                   320: 59
    //                   480: 74
    //                   640: 91;
           goto _L1 _L2 _L3 _L4
_L1:
        point.mHeight = 130;
        point.mWidth = 130;
_L6:
        return;
_L2:
        point.mHeight = 94;
        point.mWidth = 94;
        continue; /* Loop/switch isn't completed */
_L3:
        point.mHeight = 130;
        point.mWidth = 130;
        continue; /* Loop/switch isn't completed */
_L4:
        point.mHeight = 150;
        point.mWidth = 150;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static String getToggleList(Context context)
    {
        int i = getUserId(context);
        String s;
        if(isListStyle(context, i))
            s = "status_bar_toggle_list_order_new";
        else
            s = "status_bar_toggle_page_order";
        return android.provider.Settings.System.getStringForUser(context.getContentResolver(), s, i);
    }

    private static int getUserId(Context context)
    {
        int i;
        if("com.android.systemui".equals(context.getApplicationInfo().packageName))
            i = ActivityManager.getCurrentUser();
        else
            i = UserHandle.myUserId();
        return i;
    }

    public static ArrayList getUserSelectedToggleOrder(Context context)
    {
        return getUserSelectedToggleOrder(context, getUserId(context));
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, int i)
    {
        return getUserSelectedToggleOrder(context, isListStyle(context, i), i);
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, boolean flag)
    {
        return getUserSelectedToggleOrder(context, flag, getUserId(context));
    }

    public static ArrayList getUserSelectedToggleOrder(Context context, boolean flag, int i)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = JVM INSTR new #144 <Class ArrayList>;
        arraylist.ArrayList();
        Object obj1;
        int j;
        if(flag)
            obj1 = "status_bar_toggle_list_order_new";
        else
            obj1 = "status_bar_toggle_page_order";
        obj1 = android.provider.Settings.System.getStringForUser(context.getContentResolver(), ((String) (obj1)), i);
        if(TextUtils.isEmpty(((CharSequence) (obj1))))
            break MISSING_BLOCK_LABEL_161;
        obj1 = ((String) (obj1)).split(" ");
        i = 0;
_L2:
        j = obj1.length;
        if(i >= j)
            break MISSING_BLOCK_LABEL_161;
        j = Integer.valueOf(obj1[i]).intValue();
        if(j < 10000)
            break; /* Loop/switch isn't completed */
        if(mCustomIds.contains(Integer.valueOf(j)))
            arraylist.add(Integer.valueOf(j));
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(ToggleManager.getName(j) == 0) goto _L4; else goto _L3
_L3:
        arraylist.add(Integer.valueOf(j));
          goto _L4
        Exception exception;
        exception;
        exception.printStackTrace();
        arraylist.clear();
        if(mToggleManager != null)
        {
            ToggleManager togglemanager = mToggleManager;
            ToggleManager.validateToggleOrder(context, arraylist, isListStyle(context, getUserId(context)), getUserId(context));
        }
        obj;
        JVM INSTR monitorexit ;
        return arraylist;
        context;
        throw context;
    }

    public static void initDrawable(int i, Drawable drawable)
    {
    }

    public static boolean isDisabled(int i)
    {
        if(i >= 10000)
            return false;
        if(mToggleManager != null)
        {
            ToggleManager togglemanager = mToggleManager;
            return ToggleManager.isDisabled(i);
        } else
        {
            return false;
        }
    }

    public static boolean isInternationalBuilder()
    {
        return Build.IS_INTERNATIONAL_BUILD;
    }

    public static boolean isListStyle(Context context, int i)
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(context.getContentResolver(), "status_bar_style_type", 0, i) == 0)
            flag = true;
        return flag;
    }

    private static boolean isSystemApp(PackageManager packagemanager, ComponentName componentname)
    {
        boolean flag;
        try
        {
            flag = packagemanager.getApplicationInfo(componentname.getPackageName(), 0).isSystemApp();
        }
        // Misplaced declaration of an exception variable
        catch(PackageManager packagemanager)
        {
            return false;
        }
        return flag;
    }

    public static boolean isValid(Context context, int i)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        if(i < 10000)
            break MISSING_BLOCK_LABEL_28;
        boolean flag = mCustomIds.contains(Integer.valueOf(i));
        obj;
        JVM INSTR monitorexit ;
        return flag;
        if(mToggleManager == null)
            break MISSING_BLOCK_LABEL_49;
        ToggleManager togglemanager = mToggleManager;
        flag = ToggleManager.isValid(context, i);
        return flag;
        obj;
        JVM INSTR monitorexit ;
        return false;
        context;
        throw context;
    }

    public static void onCustomTileChanged(String s, boolean flag, Context context)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        if(!flag) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(!mCustomToggleComponentNames.containsValue(s))
            continue; /* Loop/switch isn't completed */
        Iterator iterator = mCustomToggleComponentNames.keySet().iterator();
        Integer integer;
        do
        {
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            integer = (Integer)iterator.next();
        } while(!((String)mCustomToggleComponentNames.get(integer)).equals(s));
        mCustomIds.remove(integer);
        mCustomToggleImages.remove(integer);
        mCustomToggleComponentNames.remove(integer);
        mCustomToggleLabelNames.remove(integer);
        updateUserSelectedToggleOrder(context);
        if(true) goto _L1; else goto _L3
_L3:
        s;
        throw s;
    }

    private void queryCustomToggles(boolean flag)
    {
        mBgHandler.removeCallbacks(mCustomToggleQueryRunnable);
        if(flag)
            mBgHandler.postDelayed(mCustomToggleQueryRunnable, 300L);
        else
            mBgHandler.postDelayed(mCustomToggleQueryRunnable, 200L);
    }

    private void registerListener(boolean flag)
    {
        int i;
        UserHandle userhandle;
        IntentFilter intentfilter;
        if(flag)
            i = -1;
        else
            i = UserHandle.myUserId();
        if(mIsSystemUI)
            userhandle = UserHandle.ALL;
        else
            userhandle = new UserHandle(getUserId(mContext));
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentfilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentfilter.addDataScheme("package");
        mContext.registerReceiverAsUser(mPackageChangeReceiver, userhandle, intentfilter, null, null);
        mResolver.registerContentObserver(android.provider.Settings.Global.getUriFor("development_settings_enabled"), false, mDevelopmentObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("status_bar_toggle_page_order"), false, mTogglOrderObserver, i);
        mResolver.registerContentObserver(android.provider.Settings.System.getUriFor("status_bar_toggle_list_order_new"), false, mTogglOrderObserver, i);
    }

    public static void resetInstance()
    {
        mToggleManagerCompatibility = null;
    }

    public static String toSpec(ComponentName componentname)
    {
        return (new StringBuilder()).append("custom(").append(componentname.flattenToShortString()).append(")").toString();
    }

    public static void updateImageView(int i, ImageView imageview)
    {
        updateImageView(i, imageview, 0);
    }

    public static void updateImageView(int i, ImageView imageview, int j)
    {
        if(imageview == null) goto _L2; else goto _L1
_L1:
        Drawable drawable = getImageDrawable(i, imageview.getContext(), j);
        if(i >= 10000 || drawable == null || Color.alpha(j) == 0) goto _L4; else goto _L3
_L3:
        drawable.setColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN);
_L6:
        imageview.setImageDrawable(drawable);
_L2:
        return;
_L4:
        if(i >= 10000 && getStatus(i) ^ true && drawable != null && Color.alpha(j) != 0)
            drawable.setColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void updateTextView(int i, TextView textview)
    {
        if(textview != null)
            textview.setText(getStatusName(i, textview.getResources()));
    }

    protected static void updateToggleStatus(int i, boolean flag)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        if(i < 10000) goto _L2; else goto _L1
_L1:
        mCustomToggleStatus.put(Integer.valueOf(i), Boolean.valueOf(flag));
_L4:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mToggleManager == null) goto _L4; else goto _L3
_L3:
        mToggleManager.updateToggleStatus(i, flag);
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public static void updateUserSelectedToggleOrder(Context context)
    {
        boolean flag;
        String s;
        String s1;
        Object obj;
        flag = false;
        String as[];
        int i;
        int j;
        if(isListStyle(context, getUserId(context)))
            s = "status_bar_toggle_list_order_new";
        else
            s = "status_bar_toggle_page_order";
        s1 = getToggleList(context);
        obj = new ArrayList();
        if(TextUtils.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        as = s1.split(" ");
        i = 0;
_L3:
        if(i >= as.length)
            break; /* Loop/switch isn't completed */
        j = Integer.valueOf(as[i]).intValue();
        if(j < 10000)
            break MISSING_BLOCK_LABEL_90;
        ((ArrayList) (obj)).add(Integer.valueOf(j));
        i++;
        if(true) goto _L3; else goto _L2
        Exception exception;
        exception;
        exception.printStackTrace();
        ((ArrayList) (obj)).clear();
_L2:
        obj = ((Iterable) (obj)).iterator();
        boolean flag1 = flag;
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            Integer integer = (Integer)((Iterator) (obj)).next();
            if(!mCustomIds.contains(integer))
            {
                s1 = s1.replace((new StringBuilder()).append(integer).append(" ").toString(), "");
                flag1 = true;
            }
        } while(true);
        if(!flag1)
        {
            return;
        } else
        {
            android.provider.Settings.System.putStringForUser(context.getContentResolver(), s, s1, getUserId(context));
            return;
        }
    }

    public static Drawable zoomDrawable(Context context, Drawable drawable, int i, int j)
    {
        int k = drawable.getIntrinsicWidth();
        int l = drawable.getIntrinsicHeight();
        Bitmap bitmap = drawableToBitmap(drawable);
        if(bitmap == null)
        {
            return null;
        } else
        {
            drawable = new Matrix();
            drawable.postScale((float)i / (float)k, (float)j / (float)l);
            drawable = Bitmap.createBitmap(bitmap, 0, 0, k, l, drawable, true);
            return new BitmapDrawable(context.getResources(), drawable);
        }
    }

    public void onDestroy()
    {
        mContext.unregisterReceiver(mPackageChangeReceiver);
        mResolver.unregisterContentObserver(mDevelopmentObserver);
        mResolver.unregisterContentObserver(mTogglOrderObserver);
        if(mToggleManager == null)
            break MISSING_BLOCK_LABEL_71;
        mToggleManager.onDestroy();
        Field field = mToggleManager.getClass().getDeclaredField("sToggleManager");
        field.setAccessible(true);
        field.set(mToggleManager, null);
_L1:
        if(mToggleOrderChangedListener != null)
            mToggleOrderChangedListener.clear();
        if(mToggleChangedListener != null)
            mToggleChangedListener.clear();
        if(mCustomToggleChangedListeners != null)
            mCustomToggleChangedListeners.clear();
        if(mCustomToggleLabelNames != null)
            mCustomToggleLabelNames.clear();
        if(mCustomToggleComponentNames != null)
            mCustomToggleComponentNames.clear();
        if(mCustomToggleImages != null)
            mCustomToggleImages.clear();
        if(mCustomToggleStatus != null)
            mCustomToggleStatus.clear();
        if(mBgThread != null)
            mBgThread.quit();
        if(mBgHandler != null)
            mBgHandler.removeCallbacksAndMessages(null);
        if(mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
        mToggleManager = null;
        resetInstance();
_L2:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
        exception;
          goto _L2
    }

    public boolean performToggle(int i)
    {
        if(i >= 10000)
            return true;
        if(mToggleManager != null)
            return mToggleManager.performToggle(i);
        else
            return true;
    }

    public void queryCustomToggles()
    {
        if(mEnableCustom)
            if(mIsSystemUI)
                queryCustomToggles(true);
            else
                queryCustomToggles(false);
    }

    public void removeCustomToggleChangeListener(OnCustomToggleChangedListener oncustomtogglechangedlistener)
    {
        if(mCustomToggleChangedListeners.contains(oncustomtogglechangedlistener))
            mCustomToggleChangedListeners.remove(oncustomtogglechangedlistener);
    }

    public void removeToggleChangedListener(ToggleManager.OnToggleChangedListener ontogglechangedlistener)
    {
        if(mToggleChangedListener.contains(ontogglechangedlistener))
            mCustomToggleChangedListeners.remove(ontogglechangedlistener);
        if(mToggleManager != null)
            mToggleManager.removeToggleChangedListener(ontogglechangedlistener);
    }

    public void removeToggleOrderChangeListener(ToggleManager.OnToggleOrderChangedListener ontoggleorderchangedlistener)
    {
        if(mToggleOrderChangedListener.contains(ontoggleorderchangedlistener))
            mToggleOrderChangedListener.remove(ontoggleorderchangedlistener);
        if(mToggleManager != null)
            mToggleManager.removeToggleOrderChangeListener(ontoggleorderchangedlistener);
    }

    public void setOnCustomToggleChangeListener(OnCustomToggleChangedListener oncustomtogglechangedlistener)
    {
        if(!mCustomToggleChangedListeners.contains(oncustomtogglechangedlistener))
            mCustomToggleChangedListeners.add(oncustomtogglechangedlistener);
    }

    public void setOnToggleChangedListener(ToggleManager.OnToggleChangedListener ontogglechangedlistener)
    {
        mToggleChangedListener.add(new WeakReference(ontogglechangedlistener));
        if(mToggleManager != null)
            mToggleManager.setOnToggleChangedListener(ontogglechangedlistener);
    }

    public void setOnToggleOrderChangeListener(ToggleManager.OnToggleOrderChangedListener ontoggleorderchangedlistener)
    {
        if(!mToggleOrderChangedListener.contains(ontoggleorderchangedlistener))
            mToggleOrderChangedListener.add(ontoggleorderchangedlistener);
        if(mToggleManager != null)
            mToggleManager.setOnToggleOrderChangeListener(ontoggleorderchangedlistener);
    }

    public void setUserSelectedToggleOrder(ArrayList arraylist)
    {
        if(mToggleManager != null)
            mToggleManager.setUserSelectedToggleOrder(arraylist);
    }

    public void updateCustomToggleImageAndText(ComponentName componentname, Drawable drawable, String s, List list, boolean flag)
    {
        Object obj = mObjectLock;
        obj;
        JVM INSTR monitorenter ;
        String s1 = toSpec(componentname);
        if(!mCustomToggleComponentNames.containsValue(s1)) goto _L2; else goto _L1
_L1:
        Iterator iterator = mCustomIds.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        componentname = (Integer)iterator.next();
        if(!((String)mCustomToggleComponentNames.get(componentname)).equals(s1)) goto _L5; else goto _L4
_L4:
        if(list == null) goto _L2; else goto _L6
_L6:
        boolean flag1 = list.contains(componentname);
        if(!(flag1 ^ true)) goto _L7; else goto _L2
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
_L7:
        int i;
        mCustomToggleImages.put(componentname, drawable);
        mCustomToggleLabelNames.put(componentname, s);
        updateToggleStatus(componentname.intValue(), flag);
        if(mToggleChangedListener.size() <= 0)
            continue; /* Loop/switch isn't completed */
        i = mToggleChangedListener.size() - 1;
_L9:
        if(i < 0)
            continue; /* Loop/switch isn't completed */
        drawable = (ToggleManager.OnToggleChangedListener)((WeakReference)mToggleChangedListener.get(i)).get();
        if(drawable != null)
            break MISSING_BLOCK_LABEL_200;
        mToggleChangedListener.remove(i);
_L10:
        i--;
        if(true) goto _L9; else goto _L8
_L8:
        continue; /* Loop/switch isn't completed */
        drawable.OnToggleChanged(componentname.intValue());
          goto _L10
        componentname;
        throw componentname;
        if(true) goto _L2; else goto _L11
_L11:
    }

    protected void updateToggleDisabled(int i, boolean flag)
    {
        if(i >= 10000)
            return;
        if(mToggleManager != null)
            mToggleManager.updateToggleDisabled(i, flag);
    }

    public static final int CUSTOM_BASE_ID = 10000;
    private static final String PROCESS_NAME_SYSTEM_UI = "com.android.systemui";
    public static final String TAG = "ToggleManagerCompatibility";
    private static ArrayList mCustomIds = new ArrayList();
    private static HashMap mCustomToggleComponentNames = new HashMap();
    private static HashMap mCustomToggleImages = new HashMap();
    private static HashMap mCustomToggleLabelNames = new HashMap();
    private static int mCustomToggleOffColor;
    private static int mCustomToggleOnColor;
    private static HashMap mCustomToggleStatus = new HashMap();
    public static boolean mEnableCustom = true;
    private static boolean mIsSystemUI = false;
    private static Object mObjectLock = new Object();
    private static ToggleManager mToggleManager = null;
    private static ToggleManagerCompatibility mToggleManagerCompatibility = null;
    private Handler mBgHandler;
    private HandlerThread mBgThread;
    private Context mContext;
    private List mCustomToggleChangedListeners;
    private CustomToggleQueryRunnable mCustomToggleQueryRunnable;
    private final ContentObserver mDevelopmentObserver;
    private final Handler mHandler = new Handler();
    private BroadcastReceiver mPackageChangeReceiver;
    private final ContentResolver mResolver;
    private final ContentObserver mTogglOrderObserver;
    private List mToggleChangedListener;
    private List mToggleOrderChangedListener;


    // Unreferenced inner class miui/app/ToggleManagerCompatibility$CustomToggleQueryRunnable$1

/* anonymous class */
    class CustomToggleQueryRunnable._cls1
        implements Runnable
    {

        public void run()
        {
            for(Iterator iterator = ToggleManagerCompatibility._2D_get2(_fld0).iterator(); iterator.hasNext(); ((OnCustomToggleChangedListener)iterator.next()).onCustomToggleChanged());
        }

        final CustomToggleQueryRunnable this$1;

            
            {
                this$1 = CustomToggleQueryRunnable.this;
                super();
            }
    }


    // Unreferenced inner class miui/app/ToggleManagerCompatibility$CustomToggleQueryRunnable$2

/* anonymous class */
    class CustomToggleQueryRunnable._cls2
        implements Runnable
    {

        public void run()
        {
            for(Iterator iterator = ToggleManagerCompatibility._2D_get10(_fld0).iterator(); iterator.hasNext(); ((ToggleManager.OnToggleOrderChangedListener)iterator.next()).OnToggleOrderChanged());
        }

        final CustomToggleQueryRunnable this$1;

            
            {
                this$1 = CustomToggleQueryRunnable.this;
                super();
            }
    }

}
