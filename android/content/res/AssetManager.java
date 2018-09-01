// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.content.pm.ActivityInfo;
import android.miui.ResourcesManager;
import android.os.ParcelFileDescriptor;
import android.util.SparseArray;
import android.util.TypedValue;
import java.io.*;
import java.util.HashMap;

// Referenced classes of package android.content.res:
//            StringBlock, AssetFileDescriptor, XmlBlock, Configuration, 
//            XmlResourceParser

public final class AssetManager
    implements AutoCloseable
{
    public final class AssetInputStream extends InputStream
    {

        public final int available()
            throws IOException
        {
            long l = AssetManager._2D_wrap3(AssetManager.this, mAsset);
            int i;
            if(l > 0x7fffffffL)
                i = 0x7fffffff;
            else
                i = (int)l;
            return i;
        }

        public final void close()
            throws IOException
        {
            AssetManager assetmanager = AssetManager.this;
            assetmanager;
            JVM INSTR monitorenter ;
            if(mAsset != 0L)
            {
                AssetManager._2D_wrap6(AssetManager.this, mAsset);
                mAsset = 0L;
                AssetManager._2D_wrap5(AssetManager.this, hashCode());
            }
            assetmanager;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        protected void finalize()
            throws Throwable
        {
            close();
        }

        public final int getAssetInt()
        {
            throw new UnsupportedOperationException();
        }

        public final long getNativeAsset()
        {
            return mAsset;
        }

        public final void mark(int i)
        {
            mMarkPos = AssetManager._2D_wrap4(AssetManager.this, mAsset, 0L, 0);
        }

        public final boolean markSupported()
        {
            return true;
        }

        public final int read()
            throws IOException
        {
            return AssetManager._2D_wrap0(AssetManager.this, mAsset);
        }

        public final int read(byte abyte0[])
            throws IOException
        {
            return AssetManager._2D_wrap1(AssetManager.this, mAsset, abyte0, 0, abyte0.length);
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            return AssetManager._2D_wrap1(AssetManager.this, mAsset, abyte0, i, j);
        }

        public final void reset()
            throws IOException
        {
            AssetManager._2D_wrap4(AssetManager.this, mAsset, mMarkPos, -1);
        }

        public final long skip(long l)
            throws IOException
        {
            long l1 = AssetManager._2D_wrap4(AssetManager.this, mAsset, 0L, 0);
            long l2 = l;
            if(l1 + l > mLength)
                l2 = mLength - l1;
            if(l2 > 0L)
                AssetManager._2D_wrap4(AssetManager.this, mAsset, l2, 0);
            return l2;
        }

        private long mAsset;
        private long mLength;
        private long mMarkPos;
        final AssetManager this$0;

        private AssetInputStream(long l)
        {
            this$0 = AssetManager.this;
            super();
            mAsset = l;
            mLength = AssetManager._2D_wrap2(AssetManager.this, l);
        }

        AssetInputStream(long l, AssetInputStream assetinputstream)
        {
            this(l);
        }
    }


    static int _2D_wrap0(AssetManager assetmanager, long l)
    {
        return assetmanager.readAssetChar(l);
    }

    static int _2D_wrap1(AssetManager assetmanager, long l, byte abyte0[], int i, int j)
    {
        return assetmanager.readAsset(l, abyte0, i, j);
    }

    static long _2D_wrap2(AssetManager assetmanager, long l)
    {
        return assetmanager.getAssetLength(l);
    }

    static long _2D_wrap3(AssetManager assetmanager, long l)
    {
        return assetmanager.getAssetRemainingLength(l);
    }

    static long _2D_wrap4(AssetManager assetmanager, long l, long l1, int i)
    {
        return assetmanager.seekAsset(l, l1, i);
    }

    static void _2D_wrap5(AssetManager assetmanager, long l)
    {
        assetmanager.decRefsLocked(l);
    }

    static void _2D_wrap6(AssetManager assetmanager, long l)
    {
        assetmanager.destroyAsset(l);
    }

    public AssetManager()
    {
        mValue = new TypedValue();
        mOffsets = new long[2];
        mStringBlocks = null;
        mNumRefs = 1;
        mOpen = true;
        this;
        JVM INSTR monitorenter ;
        init(false);
        ensureSystemAssets();
        ResourcesManager.addSystemAssets(this);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private AssetManager(boolean flag)
    {
        mValue = new TypedValue();
        mOffsets = new long[2];
        mStringBlocks = null;
        mNumRefs = 1;
        mOpen = true;
        init(true);
        ResourcesManager.addSystemAssets(this);
    }

    private final int addAssetPathInternal(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = addAssetPathNative(s, flag);
        makeStringBlocks(mStringBlocks);
        this;
        JVM INSTR monitorexit ;
        return i;
        s;
        throw s;
    }

    private final native int addAssetPathNative(String s, boolean flag);

    static final native void applyStyle(long l, int i, int j, long l1, int ai[], int k, 
            long l2, long l3);

    static final native void applyThemeStyle(long l, int i, boolean flag);

    static final native void clearTheme(long l);

    static final native void copyTheme(long l, long l1);

    private final void decRefsLocked(long l)
    {
        mNumRefs = mNumRefs - 1;
        if(mNumRefs == 0)
            destroy();
    }

    private final native void deleteTheme(long l);

    private final native void destroy();

    private final native void destroyAsset(long l);

    static final native void dumpTheme(long l, int i, String s, String s1);

    private static void ensureSystemAssets()
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        if(sSystem == null)
        {
            AssetManager assetmanager = JVM INSTR new #2   <Class AssetManager>;
            assetmanager.AssetManager(true);
            assetmanager.makeStringBlocks(null);
            sSystem = assetmanager;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final native int[] getArrayStringInfo(int i);

    private final native String[] getArrayStringResource(int i);

    public static final native String getAssetAllocations();

    private final native long getAssetLength(long l);

    private final native long getAssetRemainingLength(long l);

    public static final native int getGlobalAssetCount();

    public static final native int getGlobalAssetManagerCount();

    private final native long getNativeStringBlock(int i);

    private final native int getStringBlockCount();

    public static AssetManager getSystem()
    {
        ensureSystemAssets();
        return sSystem;
    }

    static final native int getThemeChangingConfigurations(long l);

    private final void incRefsLocked(long l)
    {
        mNumRefs = mNumRefs + 1;
    }

    private final native void init(boolean flag);

    private final native int loadResourceBagValue(int i, int j, TypedValue typedvalue, boolean flag);

    private final native int loadResourceValue(int i, short word0, TypedValue typedvalue, boolean flag);

    static final native int loadThemeAttributeValue(long l, int i, TypedValue typedvalue, boolean flag);

    private final native long newTheme();

    private final native long openAsset(String s, int i);

    private final native ParcelFileDescriptor openAssetFd(String s, long al[])
        throws IOException;

    private native ParcelFileDescriptor openNonAssetFdNative(int i, String s, long al[])
        throws IOException;

    private final native long openNonAssetNative(int i, String s, int j);

    private final native long openXmlAssetNative(int i, String s);

    private final native int readAsset(long l, byte abyte0[], int i, int j);

    private final native int readAssetChar(long l);

    static final native boolean resolveAttrs(long l, int i, int j, int ai[], int ai1[], int ai2[], int ai3[]);

    private final native long seekAsset(long l, long l1, int i);

    public final int addAssetPath(String s)
    {
        return addAssetPathInternal(s, false);
    }

    public final int addAssetPathAsSharedLibrary(String s)
    {
        return addAssetPathInternal(s, true);
    }

    public final int[] addAssetPaths(String as[])
    {
        if(as == null)
            return null;
        int ai[] = new int[as.length];
        for(int i = 0; i < as.length; i++)
            ai[i] = addAssetPath(as[i]);

        return ai;
    }

    public final int addOverlayPath(String s)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = addOverlayPathNative(s);
        makeStringBlocks(mStringBlocks);
        this;
        JVM INSTR monitorexit ;
        return i;
        s;
        throw s;
    }

    public final native int addOverlayPathNative(String s);

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(mOpen)
        {
            mOpen = false;
            decRefsLocked(hashCode());
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final long createTheme()
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            RuntimeException runtimeexception = JVM INSTR new #208 <Class RuntimeException>;
            runtimeexception.RuntimeException("Assetmanager has been closed");
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        long l;
        l = newTheme();
        incRefsLocked(l);
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    final StringBlock[] ensureStringBlocks()
    {
        this;
        JVM INSTR monitorenter ;
        StringBlock astringblock[];
        if(mStringBlocks == null)
            makeStringBlocks(sSystem.mStringBlocks);
        astringblock = mStringBlocks;
        this;
        JVM INSTR monitorexit ;
        return astringblock;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        destroy();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    final native int[] getArrayIntResource(int i);

    final native int getArraySize(int i);

    public final native SparseArray getAssignedPackageIdentifiers();

    public final native String getCookieName(int i);

    public final native String[] getLocales();

    public final native String[] getNonSystemLocales();

    final CharSequence getPooledStringForCookie(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        CharSequence charsequence = mStringBlocks[i - 1].get(j);
        this;
        JVM INSTR monitorexit ;
        return charsequence;
        Exception exception;
        exception;
        throw exception;
    }

    final CharSequence getResourceBagText(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        obj = mValue;
        i = loadResourceBagValue(i, j, ((TypedValue) (obj)), true);
        if(i >= 0)
            break MISSING_BLOCK_LABEL_24;
        this;
        JVM INSTR monitorexit ;
        return null;
        obj.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(((TypedValue) (obj)).changingConfigurations);
        if(((TypedValue) (obj)).type != 3)
            break MISSING_BLOCK_LABEL_61;
        obj = mStringBlocks[i].get(((TypedValue) (obj)).data);
        this;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj));
        obj = ((TypedValue) (obj)).coerceToString();
        this;
        JVM INSTR monitorexit ;
        return ((CharSequence) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    final native String getResourceEntryName(int i);

    final native int getResourceIdentifier(String s, String s1, String s2);

    final native String getResourceName(int i);

    final native String getResourcePackageName(int i);

    final String[] getResourceStringArray(int i)
    {
        return getArrayStringResource(i);
    }

    final CharSequence getResourceText(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        obj = mValue;
        if(!getResourceValue(i, 0, ((TypedValue) (obj)), true))
            break MISSING_BLOCK_LABEL_27;
        obj = ((TypedValue) (obj)).coerceToString();
        return ((CharSequence) (obj));
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    final CharSequence[] getResourceTextArray(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int ai[] = getArrayStringInfo(i);
        if(ai != null)
            break MISSING_BLOCK_LABEL_16;
        this;
        JVM INSTR monitorexit ;
        return null;
        int j;
        CharSequence acharsequence[];
        j = ai.length;
        acharsequence = new CharSequence[j / 2];
        int k;
        k = 0;
        i = 0;
_L7:
        if(k >= j) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = ai[k];
        i1 = ai[k + 1];
        if(i1 < 0) goto _L4; else goto _L3
_L3:
        CharSequence charsequence = mStringBlocks[l].get(i1);
_L5:
        acharsequence[i] = charsequence;
        k += 2;
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        charsequence = null;
        if(true) goto _L5; else goto _L2
_L2:
        return acharsequence;
        if(true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    final native String getResourceTypeName(int i);

    final boolean getResourceValue(int i, int j, TypedValue typedvalue, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        short word0 = (short)j;
        i = loadResourceValue(i, word0, typedvalue, flag);
        if(i >= 0)
            break MISSING_BLOCK_LABEL_25;
        this;
        JVM INSTR monitorexit ;
        return false;
        typedvalue.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(typedvalue.changingConfigurations);
        if(typedvalue.type == 3)
            typedvalue.string = mStringBlocks[i].get(typedvalue.data);
        this;
        JVM INSTR monitorexit ;
        return true;
        typedvalue;
        throw typedvalue;
    }

    public final native Configuration[] getSizeConfigurations();

    final native int[] getStyleAttributes(int i);

    final boolean getThemeValue(long l, int i, TypedValue typedvalue, boolean flag)
    {
        i = loadThemeAttributeValue(l, i, typedvalue, flag);
        if(i < 0)
            return false;
        typedvalue.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(typedvalue.changingConfigurations);
        if(typedvalue.type == 3)
            typedvalue.string = ensureStringBlocks()[i].get(typedvalue.data);
        return true;
    }

    public final native boolean isUpToDate();

    public final native String[] list(String s)
        throws IOException;

    final void makeStringBlocks(StringBlock astringblock[])
    {
        int i;
        int j;
        int k;
        if(astringblock != null)
            i = astringblock.length;
        else
            i = 0;
        j = getStringBlockCount();
        mStringBlocks = new StringBlock[j];
        k = 0;
        while(k < j) 
        {
            if(k < i)
                mStringBlocks[k] = astringblock[k];
            else
                mStringBlocks[k] = new StringBlock(getNativeStringBlock(k), true);
            k++;
        }
    }

    public final InputStream open(String s)
        throws IOException
    {
        return open(s, 2);
    }

    public final InputStream open(String s, int i)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            s = JVM INSTR new #208 <Class RuntimeException>;
            s.RuntimeException("Assetmanager has been closed");
            throw s;
        }
        break MISSING_BLOCK_LABEL_26;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        long l = openAsset(s, i);
        if(l == 0L)
            break MISSING_BLOCK_LABEL_63;
        s = JVM INSTR new #8   <Class AssetManager$AssetInputStream>;
        s.this. AssetInputStream(l, null);
        incRefsLocked(s.hashCode());
        this;
        JVM INSTR monitorexit ;
        return s;
        this;
        JVM INSTR monitorexit ;
        throw new FileNotFoundException((new StringBuilder()).append("Asset file: ").append(s).toString());
    }

    public final AssetFileDescriptor openFd(String s)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            s = JVM INSTR new #208 <Class RuntimeException>;
            s.RuntimeException("Assetmanager has been closed");
            throw s;
        }
        break MISSING_BLOCK_LABEL_26;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        ParcelFileDescriptor parcelfiledescriptor = openAssetFd(s, mOffsets);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_65;
        s = new AssetFileDescriptor(parcelfiledescriptor, mOffsets[0], mOffsets[1]);
        this;
        JVM INSTR monitorexit ;
        return s;
        this;
        JVM INSTR monitorexit ;
        throw new FileNotFoundException((new StringBuilder()).append("Asset file: ").append(s).toString());
    }

    public final InputStream openNonAsset(int i, String s)
        throws IOException
    {
        return openNonAsset(i, s, 2);
    }

    public final InputStream openNonAsset(int i, String s, int j)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            s = JVM INSTR new #208 <Class RuntimeException>;
            s.RuntimeException("Assetmanager has been closed");
            throw s;
        }
        break MISSING_BLOCK_LABEL_26;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        long l = openNonAssetNative(i, s, j);
        if(l == 0L)
            break MISSING_BLOCK_LABEL_67;
        s = JVM INSTR new #8   <Class AssetManager$AssetInputStream>;
        s.this. AssetInputStream(l, null);
        incRefsLocked(s.hashCode());
        this;
        JVM INSTR monitorexit ;
        return s;
        this;
        JVM INSTR monitorexit ;
        throw new FileNotFoundException((new StringBuilder()).append("Asset absolute file: ").append(s).toString());
    }

    public final InputStream openNonAsset(String s)
        throws IOException
    {
        return openNonAsset(0, s, 2);
    }

    public final InputStream openNonAsset(String s, int i)
        throws IOException
    {
        return openNonAsset(0, s, i);
    }

    public final AssetFileDescriptor openNonAssetFd(int i, String s)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            s = JVM INSTR new #208 <Class RuntimeException>;
            s.RuntimeException("Assetmanager has been closed");
            throw s;
        }
        break MISSING_BLOCK_LABEL_26;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        ParcelFileDescriptor parcelfiledescriptor = openNonAssetFdNative(i, s, mOffsets);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_66;
        s = new AssetFileDescriptor(parcelfiledescriptor, mOffsets[0], mOffsets[1]);
        this;
        JVM INSTR monitorexit ;
        return s;
        this;
        JVM INSTR monitorexit ;
        throw new FileNotFoundException((new StringBuilder()).append("Asset absolute file: ").append(s).toString());
    }

    public final AssetFileDescriptor openNonAssetFd(String s)
        throws IOException
    {
        return openNonAssetFd(0, s);
    }

    final XmlBlock openXmlBlockAsset(int i, String s)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(!mOpen)
        {
            s = JVM INSTR new #208 <Class RuntimeException>;
            s.RuntimeException("Assetmanager has been closed");
            throw s;
        }
        break MISSING_BLOCK_LABEL_26;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        long l = openXmlAssetNative(i, s);
        if(l == 0L)
            break MISSING_BLOCK_LABEL_62;
        s = JVM INSTR new #366 <Class XmlBlock>;
        s.XmlBlock(this, l);
        incRefsLocked(s.hashCode());
        this;
        JVM INSTR monitorexit ;
        return s;
        this;
        JVM INSTR monitorexit ;
        throw new FileNotFoundException((new StringBuilder()).append("Asset XML file: ").append(s).toString());
    }

    final XmlBlock openXmlBlockAsset(String s)
        throws IOException
    {
        return openXmlBlockAsset(0, s);
    }

    public final XmlResourceParser openXmlResourceParser(int i, String s)
        throws IOException
    {
        s = openXmlBlockAsset(i, s);
        XmlResourceParser xmlresourceparser = s.newParser();
        s.close();
        return xmlresourceparser;
    }

    public final XmlResourceParser openXmlResourceParser(String s)
        throws IOException
    {
        return openXmlResourceParser(0, s);
    }

    final void releaseTheme(long l)
    {
        this;
        JVM INSTR monitorenter ;
        deleteTheme(l);
        decRefsLocked(l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final native int retrieveArray(int i, int ai[]);

    final native boolean retrieveAttributes(long l, int ai[], int ai1[], int ai2[]);

    public final native void setConfiguration(int i, int j, String s, int k, int l, int i1, int j1, 
            int k1, int l1, int i2, int j2, int k2, int l2, int i3, 
            int j3, int k3, int l3, int i4);

    void xmlBlockGone(int i)
    {
        this;
        JVM INSTR monitorenter ;
        long l = i;
        decRefsLocked(l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int ACCESS_BUFFER = 3;
    public static final int ACCESS_RANDOM = 1;
    public static final int ACCESS_STREAMING = 2;
    public static final int ACCESS_UNKNOWN = 0;
    private static final boolean DEBUG_REFS = false;
    static final int STYLE_ASSET_COOKIE = 2;
    static final int STYLE_CHANGING_CONFIGURATIONS = 4;
    static final int STYLE_DATA = 1;
    static final int STYLE_DENSITY = 5;
    static final int STYLE_NUM_ENTRIES = 6;
    static final int STYLE_RESOURCE_ID = 3;
    static final int STYLE_TYPE = 0;
    private static final String TAG = "AssetManager";
    private static final boolean localLOGV = false;
    private static final Object sSync = new Object();
    static AssetManager sSystem = null;
    private int mNumRefs;
    private long mObject;
    private final long mOffsets[];
    private boolean mOpen;
    private HashMap mRefStacks;
    private StringBlock mStringBlocks[];
    private final TypedValue mValue;

}
