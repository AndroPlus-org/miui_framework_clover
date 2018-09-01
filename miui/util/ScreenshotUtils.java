// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.view.*;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import miui.graphics.BitmapFactory;

// Referenced classes of package miui.util:
//            MiuiFeatureUtils, FeatureParser, CompatibilityHelper, CustomizeUtil, 
//            Log

public class ScreenshotUtils
{

    static void _2D_wrap0(String s, int i, boolean flag, boolean flag1)
    {
        screenShotAndSave(s, i, flag, flag1);
    }

    public ScreenshotUtils()
    {
    }

    public static void captureActivityScreenshot(Context context, String s)
    {
        captureActivityScreenshot(context, s, Boolean.valueOf(true));
    }

    public static void captureActivityScreenshot(Context context, String s, Boolean boolean1)
    {
        int i;
        boolean flag;
        initializeIfNeed(context);
        if(sKeyguardManager.isKeyguardLocked())
            return;
        if(disallowTaskManagerScreenshotMode(context) || SystemProperties.getBoolean("persist.sys.screenshot_mode", false) ^ true)
        {
            if(getActivityScreenshotFile(s, true).exists())
                return;
            if(getActivityScreenshotFile(s, false).exists())
                return;
        }
        i = sDisplay.getRotation();
        if(i == 0 || i == 2)
            flag = true;
        else
            flag = false;
        if(sHandler != null) goto _L2; else goto _L1
_L1:
        miui/util/ScreenshotUtils;
        JVM INSTR monitorenter ;
        if(sHandler == null)
        {
            context = JVM INSTR new #153 <Class HandlerThread>;
            context.HandlerThread("ScreenshotUtils");
            sHandlerThread = context;
            sHandlerThread.start();
            context = JVM INSTR new #161 <Class Handler>;
            context.Handler(sHandlerThread.getLooper());
            sHandler = context;
        }
        miui/util/ScreenshotUtils;
        JVM INSTR monitorexit ;
_L2:
        boolean flag1 = false;
        context = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        boolean flag2;
        try
        {
            flag2 = context.hasNavigationBar();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            flag2 = flag1;
        }
        if(boolean1.booleanValue())
            sHandler.post(new Runnable(s, i, flag, flag2) {

                public void run()
                {
                    ScreenshotUtils._2D_wrap0(shortComponentName, rotation, isPort, finalHasNavigationBar);
                }

                final boolean val$finalHasNavigationBar;
                final boolean val$isPort;
                final int val$rotation;
                final String val$shortComponentName;

            
            {
                shortComponentName = s;
                rotation = i;
                isPort = flag;
                finalHasNavigationBar = flag1;
                super();
            }
            }
);
        else
            screenShotAndSave(s, i, flag, flag2);
        return;
        context;
        throw context;
    }

    public static boolean disallowTaskManagerScreenshotMode(Context context)
    {
        if(!MiuiFeatureUtils.isLiteMode())
            return false;
        else
            return FeatureParser.getBoolean("enable_miui_lite", false);
    }

    public static File getActivityScreenshotFile(String s, boolean flag)
    {
        File file = ACTIVITY_SCREENSHOT_FOLDER;
        String s1 = s.replace('/', '-');
        if(flag)
            s = "p";
        else
            s = "l";
        return new File(file, String.format("%s--%s", new Object[] {
            s1, s
        }));
    }

    public static void getActivityScreenshotSize(Context context, Point point)
    {
        initializeIfNeed(context);
        sDisplay.getRealSize(point);
        float f = Resources.getSystem().getFloat(0x110b0013);
        point.x = (int)((float)point.x * f + 0.5F);
        point.y = (int)((float)point.y * f + 0.5F);
    }

    public static Bitmap getBlurBackground(Context context, Bitmap bitmap)
    {
        context = getScreenshot(context, 0.3333333F, 0, 0, false);
        bitmap = getBlurBackground(((Bitmap) (context)), bitmap);
        context.recycle();
        return bitmap;
    }

    public static Bitmap getBlurBackground(Bitmap bitmap, Bitmap bitmap1)
    {
        Bitmap bitmap2 = bitmap1;
        if(bitmap != null)
            bitmap2 = BitmapFactory.fastBlur(bitmap, bitmap1, Resources.getSystem().getDimensionPixelSize(0x110b0012));
        if(bitmap2 != null)
            (new Canvas(bitmap2)).drawColor(Resources.getSystem().getColor(miui.system.R.color.blur_background_mask));
        return bitmap2;
    }

    private static SoftReference getCacheBitmap(boolean flag)
    {
        if(!flag)
            return sCacheBitmap;
        Object obj = new Point();
        sDisplay.getSize(((Point) (obj)));
        int i = sDisplay.getRotation();
        if(i == 0 || i == 2)
            i = 1;
        else
            i = 0;
        if(i != 0)
            i = ((Point) (obj)).y;
        else
            i = ((Point) (obj)).x;
        if(i == sScreenHeight)
            obj = sCacheBitmapWithNavigationBarHide;
        else
            obj = sCacheBitmapWithNavigationBarShow;
        return ((SoftReference) (obj));
    }

    public static Bitmap getScreenshot(Context context)
    {
        return getScreenshot(context, 1.0F, 0, 0, true);
    }

    public static Bitmap getScreenshot(Context context, float f, int i, int j, boolean flag)
    {
        int k;
        int l;
        Object obj;
        initializeIfNeed(context);
        k = (int)((float)sScreenWidth * f + 0.5F);
        l = (int)((float)sScreenHeight * f + 0.5F);
        int i1;
        int j1;
        Matrix matrix;
        if(i == 0 && j == 0)
            context = CompatibilityHelper.screenshot(k, l);
        else
            context = CompatibilityHelper.screenshot(k, l, i, j);
        obj = context;
        if(context == null) goto _L2; else goto _L1
_L1:
        j = sDisplay.getRotation();
        if(j == 0 || j == 2)
            i = 1;
        else
            i = 0;
        if(flag)
            sDisplay.getRealSize(sSizeBuf);
        else
            sDisplay.getSize(sSizeBuf);
        i1 = (int)((float)sSizeBuf.x * f + 0.5F);
        j1 = (int)((float)sSizeBuf.y * f + 0.5F);
        if(k == i1 && l == j1) goto _L4; else goto _L3
_L3:
        matrix = new Matrix();
        if(j != 0)
        {
            matrix.postTranslate((float)(-k) / 2.0F, (float)(-l) / 2.0F);
            matrix.postRotate(360 - j * 90);
            float f1;
            if(i != 0)
                f = (float)k / 2.0F;
            else
                f = (float)l / 2.0F;
            if(i != 0)
                f1 = (float)l / 2.0F;
            else
                f1 = (float)k / 2.0F;
            matrix.postTranslate(f, f1);
        }
        obj = Bitmap.createBitmap(i1, j1, android.graphics.Bitmap.Config.ARGB_8888);
        (new Canvas(((Bitmap) (obj)))).drawBitmap(context, matrix, new Paint());
        context.recycle();
_L2:
        return ((Bitmap) (obj));
_L4:
        obj = context;
        if(j == 0) goto _L2; else goto _L3
    }

    private static void initializeIfNeed(Context context)
    {
        if(!ACTIVITY_SCREENSHOT_FOLDER.exists() && ACTIVITY_SCREENSHOT_FOLDER.mkdir())
            FileUtils.setPermissions(ACTIVITY_SCREENSHOT_FOLDER.getAbsolutePath(), 509, -1, -1);
        if(sDisplay == null)
            sDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        if(sKeyguardManager == null)
            sKeyguardManager = (KeyguardManager)context.getSystemService("keyguard");
        if(sDisplay != null)
        {
            int i = sDisplay.getRotation();
            int j;
            if(i == 0 || i == 2)
                i = 1;
            else
                i = 0;
            CustomizeUtil.getRealSize(sDisplay, sSizeBuf);
            if(i != 0)
                j = sSizeBuf.x;
            else
                j = sSizeBuf.y;
            sScreenWidth = j;
            if(i != 0)
                i = sSizeBuf.y;
            else
                i = sSizeBuf.x;
            sScreenHeight = i;
        }
    }

    private static void screenShotAndSave(String s, int i, boolean flag, boolean flag1)
    {
        Bitmap bitmap;
        Object obj;
        bitmap = CompatibilityHelper.screenshot(sScreenWidth, sScreenHeight, 0, 30000);
        obj = JVM INSTR new #54  <Class Point>;
        ((Point) (obj)).Point();
        sDisplay.getSize(((Point) (obj)));
        if(!flag) goto _L2; else goto _L1
_L1:
        int j = ((Point) (obj)).x;
_L23:
        if(!flag) goto _L4; else goto _L3
_L3:
        int k = ((Point) (obj)).y;
_L24:
        float f = Resources.getSystem().getFloat(0x110b0013);
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(i != 3 && i != 2) goto _L8; else goto _L7
_L7:
        obj = JVM INSTR new #374 <Class Rect>;
        ((Rect) (obj)).Rect(0, sScreenHeight - k, j, sScreenHeight);
_L25:
        if(getCacheBitmap(flag1) != null) goto _L10; else goto _L9
_L9:
        Object obj1 = null;
_L26:
        Bitmap bitmap1 = ((Bitmap) (obj1));
        if(obj1 != null) goto _L12; else goto _L11
_L11:
        if(!flag1) goto _L14; else goto _L13
_L13:
        int l = j;
_L27:
        int i1 = (int)((float)l * f + 0.5F);
        if(!flag1) goto _L16; else goto _L15
_L15:
        l = k;
_L28:
        bitmap1 = Bitmap.createBitmap(i1, (int)((float)l * f + 0.5F), android.graphics.Bitmap.Config.ARGB_8888);
        obj1 = JVM INSTR new #381 <Class SoftReference>;
        ((SoftReference) (obj1)).SoftReference(bitmap1);
        setCacheBitmap(((SoftReference) (obj1)), flag1);
_L12:
        if(sPaint == null)
        {
            obj1 = JVM INSTR new #330 <Class Paint>;
            ((Paint) (obj1)).Paint(3);
            sPaint = ((Paint) (obj1));
        }
        obj1 = JVM INSTR new #268 <Class Canvas>;
        ((Canvas) (obj1)).Canvas(bitmap1);
        if(i != 1 && i != 2)
            break MISSING_BLOCK_LABEL_254;
        ((Canvas) (obj1)).rotate(180F, (float)bitmap1.getWidth() / 2.0F, (float)bitmap1.getHeight() / 2.0F);
        ((Canvas) (obj1)).scale(f, f);
        if(bitmap == null) goto _L18; else goto _L17
_L17:
        Rect rect = JVM INSTR new #374 <Class Rect>;
        if(!flag1) goto _L20; else goto _L19
_L19:
        if(!flag1) goto _L22; else goto _L21
_L21:
        rect.Rect(0, 0, j, k);
        ((Canvas) (obj1)).drawBitmap(bitmap, ((Rect) (obj)), rect, sPaint);
        bitmap.recycle();
_L29:
        obj = JVM INSTR new #412 <Class FileOutputStream>;
        ((FileOutputStream) (obj)).FileOutputStream(getActivityScreenshotFile(s, flag));
        bitmap1.compress(android.graphics.Bitmap.CompressFormat.JPEG, 90, ((java.io.OutputStream) (obj)));
        ((FileOutputStream) (obj)).close();
        s = getActivityScreenshotFile(s, flag ^ true);
        if(s.exists() && s.delete())
        {
            obj = JVM INSTR new #433 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("ScreenshotUtils", ((StringBuilder) (obj)).append(s.getAbsolutePath()).append("delete failed").toString());
        }
_L30:
        return;
_L2:
        j = ((Point) (obj)).y;
          goto _L23
_L4:
        k = ((Point) (obj)).x;
          goto _L24
_L8:
        obj = new Rect(0, 0, j, k);
          goto _L25
_L6:
        obj = new Rect(0, 0, sScreenWidth, sScreenHeight);
          goto _L25
_L10:
        obj1 = (Bitmap)getCacheBitmap(flag1).get();
          goto _L26
_L14:
        l = sScreenWidth;
          goto _L27
_L16:
        l = sScreenHeight;
          goto _L28
_L20:
        j = sScreenWidth;
          goto _L19
_L22:
        k = sScreenHeight;
          goto _L21
_L18:
        ((Canvas) (obj1)).drawColor(0xaaffffff, android.graphics.PorterDuff.Mode.SRC);
          goto _L29
        s;
        Log.d("ScreenshotUtils", "screenShotAndSave", s);
          goto _L30
    }

    private static void setCacheBitmap(SoftReference softreference, boolean flag)
    {
        if(!flag)
        {
            sCacheBitmap = softreference;
            return;
        }
        Point point = new Point();
        sDisplay.getSize(point);
        int i = sDisplay.getRotation();
        if(i == 0 || i == 2)
            i = 1;
        else
            i = 0;
        if(i != 0)
            i = point.y;
        else
            i = point.x;
        if(i == sScreenHeight)
            sCacheBitmapWithNavigationBarHide = softreference;
        else
            sCacheBitmapWithNavigationBarShow = softreference;
    }

    private static final File ACTIVITY_SCREENSHOT_FOLDER = new File("/data/system/app_screenshot");
    private static final String ACTIVITY_SCREENSHOT_FOLDER_PATH = "/data/system/app_screenshot";
    public static final float BLUR_SCALE_RATIO = 0.3333333F;
    public static final int DEFAULT_SCREENSHOT_COLOR = 0xaaffffff;
    public static final int DEFAULT_SCREEN_BLUR_RADIUS = Resources.getSystem().getDimensionPixelSize(0x110b0012);
    public static final float REAL_BLUR_BLACK = (float)SystemProperties.getInt("persist.sys.real_blur_black", 0) / 100F;
    public static final int REAL_BLUR_MINIFY = SystemProperties.getInt("persist.sys.real_blur_minify", 4);
    public static final int REAL_BLUR_RADIUS = SystemProperties.getInt("persist.sys.real_blur_radius", 8);
    private static final String TAG = "ScreenshotUtils";
    private static SoftReference sCacheBitmap;
    private static SoftReference sCacheBitmapWithNavigationBarHide;
    private static SoftReference sCacheBitmapWithNavigationBarShow;
    private static Display sDisplay;
    private static Handler sHandler;
    private static HandlerThread sHandlerThread;
    private static KeyguardManager sKeyguardManager;
    private static Paint sPaint;
    private static int sScreenHeight;
    private static int sScreenWidth;
    private static Point sSizeBuf = new Point();

}
