// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.ActivityThread;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.os.Process;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import com.android.internal.util.FastPrintWriter;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import libcore.io.IoUtils;

// Referenced classes of package miui.util:
//            MiuiFeatureUtils

public class DumpBitmapInfoUtils
{

    public DumpBitmapInfoUtils()
    {
    }

    public static void dumpBitmapInfo(FileDescriptor filedescriptor, String as[])
    {
        boolean flag1;
        boolean flag3;
        int j;
        int k;
        Object obj;
        long l1;
        Object obj1;
        if(!ENABLE)
            return;
        boolean flag = false;
        flag1 = false;
        boolean flag2 = false;
        flag3 = false;
        j = 0;
        k = 0;
        int l = as.length;
        while(k < l) 
        {
            String s = as[k];
            if("--bitmap".equalsIgnoreCase(s) || "-b".equalsIgnoreCase(s))
                flag = true;
            if("--exportbitmap".equalsIgnoreCase(s) || "-e".equalsIgnoreCase(s))
                flag1 = true;
            if("--nogc".equalsIgnoreCase(s))
                flag2 = true;
            if("--includepreload".equalsIgnoreCase(s))
                flag3 = true;
            if(s.startsWith("--recycle:"))
                if(s.startsWith("--recycle:0x"))
                    j = Integer.parseInt(s.substring("--recycle:0x".length()), 16);
                else
                    j = Integer.parseInt(s.substring("--recycle:".length()), 16);
            k++;
        }
        if(!flag && flag1 ^ true)
            return;
        if(!flag2)
            System.gc();
        Iterator iterator;
        java.util.Map.Entry entry;
        java.util.AbstractMap.SimpleEntry simpleentry;
        if(ActivityThread.currentApplication() == null || TextUtils.isEmpty(ActivityThread.currentPackageName()) || "system".equals(ActivityThread.currentPackageName()))
            as = new File("/data/system/_exportbitmap/");
        else
            as = new File(ActivityThread.currentApplication().getCacheDir(), (new StringBuilder()).append("_exportbitmap/").append(ActivityThread.currentProcessName()).toString());
        if(flag1)
            if(!as.exists())
                as.mkdirs();
            else
                try
                {
                    IoUtils.deleteContents(as);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    ((Exception) (obj)).printStackTrace();
                }
        filedescriptor = new FastPrintWriter(new FileOutputStream(filedescriptor));
        l1 = 0L;
        k = 0;
        obj = sBitmapTitles;
        obj;
        JVM INSTR monitorenter ;
        obj1 = JVM INSTR new #131 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        iterator = sBitmapTitles.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            entry = (java.util.Map.Entry)iterator.next();
            simpleentry = JVM INSTR new #155 <Class java.util.AbstractMap$SimpleEntry>;
            simpleentry.java.util.AbstractMap.SimpleEntry(entry);
            if(simpleentry.getKey() != null)
                ((ArrayList) (obj1)).add(simpleentry);
        } while(true);
        break MISSING_BLOCK_LABEL_418;
        as;
        obj;
        JVM INSTR monitorexit ;
        throw as;
        as;
        filedescriptor.flush();
        throw as;
        obj;
        JVM INSTR monitorexit ;
        obj = JVM INSTR new #6   <Class DumpBitmapInfoUtils$1>;
        ((_cls1) (obj))._cls1();
        Collections.sort(((java.util.List) (obj1)), ((Comparator) (obj)));
        filedescriptor.printf("All big bitmaps (debug.bitmap_threshold_size = %d k):\n", new Object[] {
            Integer.valueOf(sBitmapThresholdSize)
        });
        obj = Resources.getSystem().getPreloadedDrawables().clone();
        obj1 = ((Iterable) (obj1)).iterator();
_L4:
        if(!((Iterator) (obj1)).hasNext()) goto _L2; else goto _L1
_L1:
        Bitmap bitmap;
        Object obj2;
        obj2 = (java.util.Map.Entry)((Iterator) (obj1)).next();
        bitmap = (Bitmap)((java.util.Map.Entry) (obj2)).getKey();
        if(bitmap.isRecycled()) goto _L4; else goto _L3
_L3:
        int i;
        boolean flag4;
        flag4 = false;
        i = 0;
_L10:
        boolean flag5 = flag4;
        if(i >= ((LongSparseArray) (obj)).size()) goto _L6; else goto _L5
_L5:
        if(getBitmapFromDrawableState((android.graphics.drawable.Drawable.ConstantState)((LongSparseArray) (obj)).valueAt(i)) != bitmap) goto _L8; else goto _L7
_L7:
        flag5 = true;
_L6:
        if(flag5 && flag3 ^ true) goto _L4; else goto _L9
_L9:
        l1 += bitmap.getByteCount();
        k++;
        String s1 = getBitmapMsg(bitmap, (CharSequence)((java.util.Map.Entry) (obj2)).getValue(), false, flag5);
        StringBuilder stringbuilder1 = JVM INSTR new #177 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        filedescriptor.print(stringbuilder1.append("  ").append(s1).toString());
        if(!flag1)
            break MISSING_BLOCK_LABEL_730;
        Object obj3 = JVM INSTR new #177 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        String s2 = ((StringBuilder) (obj3)).append(k).append("_").append(getBitmapMsg(bitmap, (CharSequence)((java.util.Map.Entry) (obj2)).getValue(), true, flag5)).toString();
        obj3 = JVM INSTR new #123 <Class FileOutputStream>;
        obj2 = JVM INSTR new #107 <Class File>;
        ((File) (obj2)).File(as, s2);
        ((FileOutputStream) (obj3)).FileOutputStream(((File) (obj2)));
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, ((java.io.OutputStream) (obj3)));
        ((FileOutputStream) (obj3)).close();
_L11:
        if(j == 0)
            break MISSING_BLOCK_LABEL_757;
        if(bitmap.hashCode() == j)
        {
            bitmap.recycle();
            filedescriptor.print("  now recycled.");
        }
        filedescriptor.print('\n');
          goto _L4
_L8:
        i++;
          goto _L10
        Exception exception;
        exception;
        exception.printStackTrace(filedescriptor);
        exception.printStackTrace();
          goto _L11
_L2:
        filedescriptor.printf("Total count: %d, size: %dM\n", new Object[] {
            Integer.valueOf(k), Long.valueOf(l1 / 1024L / 1024L)
        });
        if(!flag1)
            break MISSING_BLOCK_LABEL_900;
        StringBuilder stringbuilder = JVM INSTR new #177 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        filedescriptor.print(stringbuilder.append("Export bitmap. Path: ").append(as.getAbsolutePath()).append("\n").toString());
        stringbuilder = JVM INSTR new #177 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("DumpBitmapInfo", stringbuilder.append("Export bitmaps finished. Path: ").append(as.getAbsolutePath()).toString());
        filedescriptor.printf("\n", new Object[0]);
        filedescriptor.flush();
        return;
          goto _L4
    }

    private static String formatMsg(Bitmap bitmap, CharSequence charsequence, boolean flag)
    {
        int i = bitmap.hashCode();
        int j = bitmap.getByteCount() / 1024;
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        if(flag)
            bitmap = "preload";
        else
            bitmap = "";
        if(charsequence == null)
            charsequence = "";
        else
            charsequence = charsequence.toString();
        return String.format("0x%8x %,6dk %dx%d %s %s", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(l), bitmap, charsequence
        });
    }

    private static Bitmap getBitmapFromDrawableState(android.graphics.drawable.Drawable.ConstantState constantstate)
    {
        Object obj = constantstate.getClass().getSimpleName();
        if(((String) (obj)).equals("BitmapState"))
        {
            obj = constantstate.getClass().getDeclaredField("mBitmap");
            ((Field) (obj)).setAccessible(true);
            return (Bitmap)((Field) (obj)).get(constantstate);
        }
        if(!((String) (obj)).equals("NinePatchState"))
            break MISSING_BLOCK_LABEL_91;
        obj = constantstate.getClass().getDeclaredField("mNinePatch");
        ((Field) (obj)).setAccessible(true);
        constantstate = (NinePatch)((Field) (obj)).get(constantstate);
        if(constantstate == null)
            return null;
        try
        {
            constantstate = constantstate.getBitmap();
        }
        // Misplaced declaration of an exception variable
        catch(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            constantstate.printStackTrace();
            return null;
        }
        return constantstate;
        return null;
    }

    private static String getBitmapMsg(Bitmap bitmap, CharSequence charsequence, boolean flag, boolean flag1)
    {
        String s = formatMsg(bitmap, charsequence, flag1);
        if(!flag)
            return s;
        int i = s.length() - 240;
        String s1 = s;
        if(i > 0)
        {
            s1 = s;
            if(charsequence != null)
            {
                s1 = s;
                if(charsequence.length() > i)
                    s1 = formatMsg(bitmap, charsequence.toString().substring(i), flag1);
            }
        }
        return (new StringBuilder()).append(s1.replace(' ', '_').replace('\\', '-').replace('/', '-')).append(".png").toString();
    }

    private static boolean isTrackingNeeded(Bitmap bitmap)
    {
        if(sCurrProcess != Process.myPid())
        {
            sBitmapThresholdSize = SystemProperties.getInt("debug.bitmap_threshold_size", 100);
            sCurrProcess = Process.myPid();
        }
        boolean flag;
        if((bitmap.getWidth() * bitmap.getHeight()) / 256 >= sBitmapThresholdSize)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void putBitmap(Bitmap bitmap, CharSequence charsequence)
    {
        if(!ENABLE)
            return;
        if(bitmap == null)
            return;
        if(!isTrackingNeeded(bitmap))
            return;
        WeakHashMap weakhashmap = sBitmapTitles;
        weakhashmap;
        JVM INSTR monitorenter ;
        sBitmapTitles.put(bitmap, charsequence);
        weakhashmap;
        JVM INSTR monitorexit ;
_L1:
        return;
        bitmap;
        weakhashmap;
        JVM INSTR monitorexit ;
        throw bitmap;
        bitmap;
        bitmap.printStackTrace();
          goto _L1
    }

    static final boolean ENABLE;
    static int sBitmapThresholdSize;
    static WeakHashMap sBitmapTitles;
    static int sCurrProcess;

    static 
    {
        ENABLE = MiuiFeatureUtils.isSystemFeatureSupported("DumpBitmapInfo", true);
        if(ENABLE)
            sBitmapTitles = new WeakHashMap();
    }

    // Unreferenced inner class miui/util/DumpBitmapInfoUtils$1

/* anonymous class */
    static final class _cls1
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((java.util.Map.Entry)obj, (java.util.Map.Entry)obj1);
        }

        public int compare(java.util.Map.Entry entry, java.util.Map.Entry entry1)
        {
            return ((Bitmap)entry.getKey()).getByteCount() - ((Bitmap)entry1.getKey()).getByteCount();
        }

    }

}
