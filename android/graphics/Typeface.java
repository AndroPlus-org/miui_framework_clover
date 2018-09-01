// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.content.res.AssetManager;
import android.graphics.fonts.FontVariationAxis;
import android.provider.FontRequest;
import android.provider.FontsContract;
import android.text.FontConfig;
import android.util.*;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics:
//            FontFamily, FontListParser, TypefaceInjector

public class Typeface
{
    public static final class Builder
    {

        static String _2D_wrap0(AssetManager assetmanager, String s, int i, FontVariationAxis afontvariationaxis[], int j, int k)
        {
            return createAssetUid(assetmanager, s, i, afontvariationaxis, j, k);
        }

        private static String createAssetUid(AssetManager assetmanager, String s, int i, FontVariationAxis afontvariationaxis[], int j, int k)
        {
            SparseArray sparsearray = assetmanager.getAssignedPackageIdentifiers();
            assetmanager = new StringBuilder();
            int l = sparsearray.size();
            for(int i1 = 0; i1 < l; i1++)
            {
                assetmanager.append((String)sparsearray.valueAt(i1));
                assetmanager.append("-");
            }

            assetmanager.append(s);
            assetmanager.append("-");
            assetmanager.append(Integer.toString(i));
            assetmanager.append("-");
            assetmanager.append(Integer.toString(j));
            assetmanager.append("-");
            assetmanager.append(Integer.toString(k));
            assetmanager.append("-");
            if(afontvariationaxis != null)
            {
                i = 0;
                for(j = afontvariationaxis.length; i < j; i++)
                {
                    s = afontvariationaxis[i];
                    assetmanager.append(s.getTag());
                    assetmanager.append("-");
                    assetmanager.append(Float.toString(s.getStyleValue()));
                }

            }
            return assetmanager.toString();
        }

        private Typeface resolveFallbackTypeface()
        {
            int i;
            Typeface typeface1;
            int j;
            boolean flag;
            Object obj;
            SparseArray sparsearray1;
            i = 1;
            if(mFallbackFamilyName == null)
                return null;
            Typeface typeface = (Typeface)Typeface.sSystemFontMap.get(mFallbackFamilyName);
            typeface1 = typeface;
            if(typeface == null)
                typeface1 = Typeface.sDefaultTypeface;
            if(mWeight == -1 && mItalic == -1)
                return typeface1;
            if(mWeight == -1)
                j = Typeface._2D_get1(typeface1);
            else
                j = mWeight;
            if(mItalic != -1 ? mItalic == 1 : (Typeface._2D_get0(typeface1) & 2) != 0)
                flag = true;
            else
                flag = false;
            if(!flag)
                i = 0;
            i = j << 1 | i;
            obj = sLock;
            obj;
            JVM INSTR monitorenter ;
            sparsearray1 = (SparseArray)sTypefaceCache.get(typeface1.native_instance);
            if(sparsearray1 == null)
                break MISSING_BLOCK_LABEL_179;
            typeface = (Typeface)sparsearray1.get(i);
            if(typeface == null)
                break MISSING_BLOCK_LABEL_179;
            obj;
            JVM INSTR monitorexit ;
            return typeface;
            Typeface typeface2;
            typeface2 = JVM INSTR new #6   <Class Typeface>;
            typeface2.Typeface(Typeface._2D_wrap1(typeface1.native_instance, j, flag), null);
            SparseArray sparsearray;
            sparsearray = sparsearray1;
            if(sparsearray1 != null)
                break MISSING_BLOCK_LABEL_229;
            sparsearray = JVM INSTR new #106 <Class SparseArray>;
            sparsearray.SparseArray(4);
            sTypefaceCache.put(typeface1.native_instance, sparsearray);
            sparsearray.put(i, typeface2);
            obj;
            JVM INSTR monitorexit ;
            return typeface2;
            Exception exception;
            exception;
            throw exception;
        }

        public Typeface build()
        {
            if(mFd == null) goto _L2; else goto _L1
_L1:
            Object obj;
            Object obj1;
            Object obj2;
            FontFamily fontfamily;
            Object obj3;
            obj = null;
            obj1 = null;
            obj2 = null;
            fontfamily = null;
            obj3 = null;
            Object obj4;
            obj4 = JVM INSTR new #196 <Class FileInputStream>;
            ((FileInputStream) (obj4)).FileInputStream(mFd);
            obj3 = ((FileInputStream) (obj4)).getChannel();
            long l = ((FileChannel) (obj3)).size();
            obj3 = ((FileChannel) (obj3)).map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
            fontfamily = JVM INSTR new #219 <Class FontFamily>;
            fontfamily.FontFamily();
            if(fontfamily.addFontFromBuffer(((ByteBuffer) (obj3)), mTtcIndex, mAxes, mWeight, mItalic)) goto _L4; else goto _L3
_L3:
            fontfamily.abortCreation();
            obj = resolveFallbackTypeface();
            obj3 = obj2;
            if(obj4 == null)
                break MISSING_BLOCK_LABEL_122;
            ((FileInputStream) (obj4)).close();
            obj3 = obj2;
_L7:
            if(obj3 == null) goto _L6; else goto _L5
_L5:
            try
            {
                throw obj3;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
_L12:
            return resolveFallbackTypeface();
            obj3;
              goto _L7
_L6:
            return ((Typeface) (obj));
_L4:
            if(fontfamily.freeze())
                break MISSING_BLOCK_LABEL_188;
            obj2 = resolveFallbackTypeface();
            obj3 = obj;
            if(obj4 == null)
                break MISSING_BLOCK_LABEL_173;
            ((FileInputStream) (obj4)).close();
            obj3 = obj;
_L9:
            if(obj3 == null)
                break MISSING_BLOCK_LABEL_186;
            throw obj3;
            obj3;
            if(true) goto _L9; else goto _L8
_L8:
            return ((Typeface) (obj2));
            int i = mWeight;
            int k1 = mItalic;
            obj2 = Typeface._2D_wrap0(new FontFamily[] {
                fontfamily
            }, i, k1);
            obj3 = obj1;
            if(obj4 == null)
                break MISSING_BLOCK_LABEL_233;
            ((FileInputStream) (obj4)).close();
            obj3 = obj1;
_L11:
            if(obj3 == null)
                break MISSING_BLOCK_LABEL_246;
            throw obj3;
            obj3;
            if(true) goto _L11; else goto _L10
_L10:
            return ((Typeface) (obj2));
            obj4;
            obj2 = obj3;
_L17:
            throw obj4;
            obj3;
_L16:
            obj = obj4;
            if(obj2 == null)
                break MISSING_BLOCK_LABEL_272;
            ((FileInputStream) (obj2)).close();
            obj = obj4;
_L14:
            if(obj == null)
                break MISSING_BLOCK_LABEL_315;
            try
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
              goto _L12
            obj2;
label0:
            {
                if(obj4 != null)
                    break label0;
                obj = obj2;
            }
            if(true) goto _L14; else goto _L13
_L13:
            obj = obj4;
            if(obj4 == obj2) goto _L14; else goto _L15
_L15:
            ((Throwable) (obj4)).addSuppressed(((Throwable) (obj2)));
            obj = obj4;
              goto _L14
            throw obj3;
_L2:
            if(mAssetManager == null)
                break MISSING_BLOCK_LABEL_513;
            obj3 = createAssetUid(mAssetManager, mPath, mTtcIndex, mAxes, mWeight, mItalic);
            obj4 = sLock;
            obj4;
            JVM INSTR monitorenter ;
            obj2 = (Typeface)Typeface._2D_get2().get(obj3);
            if(obj2 == null)
                break MISSING_BLOCK_LABEL_383;
            obj4;
            JVM INSTR monitorexit ;
            return ((Typeface) (obj2));
            obj2 = JVM INSTR new #219 <Class FontFamily>;
            ((FontFamily) (obj2)).FontFamily();
            if(((FontFamily) (obj2)).addFontFromAssetManager(mAssetManager, mPath, mTtcIndex, true, mTtcIndex, mWeight, mItalic, mAxes))
                break MISSING_BLOCK_LABEL_443;
            ((FontFamily) (obj2)).abortCreation();
            obj3 = resolveFallbackTypeface();
            obj4;
            JVM INSTR monitorexit ;
            return ((Typeface) (obj3));
            if(((FontFamily) (obj2)).freeze())
                break MISSING_BLOCK_LABEL_462;
            obj3 = resolveFallbackTypeface();
            obj4;
            JVM INSTR monitorexit ;
            return ((Typeface) (obj3));
            int j = mWeight;
            int l1 = mItalic;
            obj2 = Typeface._2D_wrap0(new FontFamily[] {
                obj2
            }, j, l1);
            Typeface._2D_get2().put(obj3, obj2);
            obj4;
            JVM INSTR monitorexit ;
            return ((Typeface) (obj2));
            obj3;
            throw obj3;
            if(mPath != null)
            {
                obj4 = new FontFamily();
                if(!((FontFamily) (obj4)).addFont(mPath, mTtcIndex, mAxes, mWeight, mItalic))
                {
                    ((FontFamily) (obj4)).abortCreation();
                    return resolveFallbackTypeface();
                }
                if(!((FontFamily) (obj4)).freeze())
                {
                    return resolveFallbackTypeface();
                } else
                {
                    int i2 = mWeight;
                    int k = mItalic;
                    return Typeface._2D_wrap0(new FontFamily[] {
                        obj4
                    }, i2, k);
                }
            }
            if(mFonts != null)
            {
                obj2 = new FontFamily();
                int i1 = 0;
                obj3 = mFonts;
                int j2 = 0;
                int l2 = obj3.length;
                while(j2 < l2) 
                {
                    android.provider.FontsContract.FontInfo fontinfo = obj3[j2];
                    obj4 = (ByteBuffer)mFontBuffers.get(fontinfo.getUri());
                    if(obj4 != null)
                    {
                        int i3 = fontinfo.getTtcIndex();
                        FontVariationAxis afontvariationaxis[] = fontinfo.getAxes();
                        int j3 = fontinfo.getWeight();
                        if(fontinfo.isItalic())
                            i1 = 1;
                        else
                            i1 = 0;
                        if(!((FontFamily) (obj2)).addFontFromBuffer(((ByteBuffer) (obj4)), i3, afontvariationaxis, j3, i1))
                        {
                            ((FontFamily) (obj2)).abortCreation();
                            return null;
                        }
                        i1 = 1;
                    }
                    j2++;
                }
                if(i1 == 0)
                {
                    ((FontFamily) (obj2)).abortCreation();
                    return null;
                } else
                {
                    ((FontFamily) (obj2)).freeze();
                    int k2 = mWeight;
                    int j1 = mItalic;
                    return Typeface._2D_wrap0(new FontFamily[] {
                        obj2
                    }, k2, j1);
                }
            } else
            {
                throw new IllegalArgumentException("No source was set.");
            }
            obj3;
            obj4 = null;
            obj2 = fontfamily;
              goto _L16
            obj3;
            obj2 = obj4;
            obj4 = null;
              goto _L16
            Throwable throwable;
            throwable;
            obj2 = obj4;
            obj4 = throwable;
              goto _L17
        }

        public Builder setFallback(String s)
        {
            mFallbackFamilyName = s;
            return this;
        }

        public Builder setFontVariationSettings(String s)
        {
            if(mFonts != null)
                throw new IllegalArgumentException("Font variation settings can not be specified for FontResult source.");
            if(mAxes != null)
            {
                throw new IllegalStateException("Font variation settings are already set.");
            } else
            {
                mAxes = FontVariationAxis.fromFontVariationSettings(s);
                return this;
            }
        }

        public Builder setFontVariationSettings(FontVariationAxis afontvariationaxis[])
        {
            if(mFonts != null)
                throw new IllegalArgumentException("Font variation settings can not be specified for FontResult source.");
            if(mAxes != null)
            {
                throw new IllegalStateException("Font variation settings are already set.");
            } else
            {
                mAxes = afontvariationaxis;
                return this;
            }
        }

        public Builder setItalic(boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            mItalic = i;
            return this;
        }

        public Builder setTtcIndex(int i)
        {
            if(mFonts != null)
            {
                throw new IllegalArgumentException("TTC index can not be specified for FontResult source.");
            } else
            {
                mTtcIndex = i;
                return this;
            }
        }

        public Builder setWeight(int i)
        {
            mWeight = i;
            return this;
        }

        public static final int BOLD_WEIGHT = 700;
        public static final int NORMAL_WEIGHT = 400;
        private static final Object sLock = new Object();
        private static final LongSparseArray sTypefaceCache = new LongSparseArray(3);
        private AssetManager mAssetManager;
        private FontVariationAxis mAxes[];
        private String mFallbackFamilyName;
        private FileDescriptor mFd;
        private Map mFontBuffers;
        private android.provider.FontsContract.FontInfo mFonts[];
        private int mItalic;
        private String mPath;
        private int mTtcIndex;
        private int mWeight;


        public Builder(AssetManager assetmanager, String s)
        {
            mWeight = -1;
            mItalic = -1;
            mAssetManager = (AssetManager)Preconditions.checkNotNull(assetmanager);
            mPath = (String)Preconditions.checkStringNotEmpty(s);
        }

        public Builder(File file)
        {
            mWeight = -1;
            mItalic = -1;
            mPath = file.getAbsolutePath();
        }

        public Builder(FileDescriptor filedescriptor)
        {
            mWeight = -1;
            mItalic = -1;
            mFd = filedescriptor;
        }

        public Builder(String s)
        {
            mWeight = -1;
            mItalic = -1;
            mPath = s;
        }

        public Builder(android.provider.FontsContract.FontInfo afontinfo[], Map map)
        {
            mWeight = -1;
            mItalic = -1;
            mFonts = afontinfo;
            mFontBuffers = map;
        }
    }


    static int _2D_get0(Typeface typeface)
    {
        return typeface.mStyle;
    }

    static int _2D_get1(Typeface typeface)
    {
        return typeface.mWeight;
    }

    static LruCache _2D_get2()
    {
        return sDynamicTypefaceCache;
    }

    static Typeface _2D_wrap0(FontFamily afontfamily[], int i, int j)
    {
        return createFromFamiliesWithDefault(afontfamily, i, j);
    }

    static long _2D_wrap1(long l, int i, boolean flag)
    {
        return nativeCreateFromTypefaceWithExactStyle(l, i, flag);
    }

    private Typeface(long l)
    {
        mStyle = 0;
        mWeight = 0;
        if(l == 0L)
        {
            throw new RuntimeException("native typeface cannot be made");
        } else
        {
            native_instance = l;
            mStyle = nativeGetStyle(l);
            mWeight = nativeGetWeight(l);
            return;
        }
    }

    Typeface(long l, Typeface typeface)
    {
        this(l);
    }

    public static Typeface create(Typeface typeface, int i)
    {
        int j;
label0:
        {
            if(i >= 0)
            {
                j = i;
                if(i <= 3)
                    break label0;
            }
            j = 0;
        }
        long l = 0L;
        if(typeface != null)
        {
            if(typeface.mStyle == j)
                return typeface;
            l = typeface.native_instance;
        }
        SparseArray sparsearray = (SparseArray)sTypefaceCache.get(l);
        if(sparsearray != null)
        {
            typeface = (Typeface)sparsearray.get(j);
            if(typeface != null)
                return typeface;
        }
        Typeface typeface1 = new Typeface(nativeCreateFromTypeface(l, j));
        typeface = sparsearray;
        if(sparsearray == null)
        {
            typeface = new SparseArray(4);
            sTypefaceCache.put(l, typeface);
        }
        typeface.put(j, typeface1);
        return typeface1;
    }

    public static Typeface create(String s, int i)
    {
        if(sSystemFontMap != null)
            return create((Typeface)sSystemFontMap.get(s), i);
        else
            return null;
    }

    public static Typeface createFromAsset(AssetManager assetmanager, String s)
    {
        if(s == null)
            throw new NullPointerException();
        if(sFallbackFonts == null) goto _L2; else goto _L1
_L1:
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = JVM INSTR new #6   <Class Typeface$Builder>;
        ((Builder) (obj1)).Builder(assetmanager, s);
        obj1 = ((Builder) (obj1)).build();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_47;
        obj;
        JVM INSTR monitorexit ;
        return ((Typeface) (obj1));
        Object obj2;
        obj1 = Builder._2D_wrap0(assetmanager, s, 0, null, -1, -1);
        obj2 = (Typeface)sDynamicTypefaceCache.get(obj1);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_79;
        obj;
        JVM INSTR monitorexit ;
        return ((Typeface) (obj2));
        obj2 = JVM INSTR new #199 <Class FontFamily>;
        ((FontFamily) (obj2)).FontFamily();
        if(!((FontFamily) (obj2)).addFontFromAssetManager(assetmanager, s, 0, true, 0, -1, -1, null))
            break MISSING_BLOCK_LABEL_144;
        ((FontFamily) (obj2)).allowUnsupportedFont();
        ((FontFamily) (obj2)).freeze();
        assetmanager = createFromFamiliesWithDefault(new FontFamily[] {
            obj2
        }, -1, -1);
        sDynamicTypefaceCache.put(obj1, assetmanager);
        obj;
        JVM INSTR monitorexit ;
        return assetmanager;
        ((FontFamily) (obj2)).abortCreation();
        obj;
        JVM INSTR monitorexit ;
_L2:
        throw new RuntimeException((new StringBuilder()).append("Font asset not found ").append(s).toString());
        assetmanager;
        throw assetmanager;
    }

    private static Typeface createFromFamilies(FontFamily afontfamily[])
    {
        long al[] = new long[afontfamily.length];
        for(int i = 0; i < afontfamily.length; i++)
            al[i] = afontfamily[i].mNativePtr;

        return new Typeface(nativeCreateFromArray(al, -1, -1));
    }

    private static Typeface createFromFamiliesWithDefault(FontFamily afontfamily[], int i, int j)
    {
        long al[] = new long[afontfamily.length + sFallbackFonts.length];
        for(int k = 0; k < afontfamily.length; k++)
            al[k] = afontfamily[k].mNativePtr;

        for(int l = 0; l < sFallbackFonts.length; l++)
            al[afontfamily.length + l] = sFallbackFonts[l].mNativePtr;

        return new Typeface(nativeCreateFromArray(al, i, j));
    }

    public static Typeface createFromFile(File file)
    {
        return createFromFile(file.getAbsolutePath());
    }

    public static Typeface createFromFile(String s)
    {
        if(sFallbackFonts != null)
        {
            FontFamily fontfamily = new FontFamily();
            if(fontfamily.addFont(s, 0, null, -1, -1))
            {
                fontfamily.allowUnsupportedFont();
                fontfamily.freeze();
                return createFromFamiliesWithDefault(new FontFamily[] {
                    fontfamily
                }, -1, -1);
            }
            fontfamily.abortCreation();
        }
        throw new RuntimeException((new StringBuilder()).append("Font not found ").append(s).toString());
    }

    public static Typeface createFromResources(AssetManager assetmanager, String s, int i)
    {
        if(sFallbackFonts == null) goto _L2; else goto _L1
_L1:
        LruCache lrucache = sDynamicTypefaceCache;
        lrucache;
        JVM INSTR monitorenter ;
        String s1;
        Object obj;
        s1 = Builder._2D_wrap0(assetmanager, s, 0, null, -1, -1);
        obj = (Typeface)sDynamicTypefaceCache.get(s1);
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        lrucache;
        JVM INSTR monitorexit ;
        return ((Typeface) (obj));
        boolean flag;
        obj = JVM INSTR new #199 <Class FontFamily>;
        ((FontFamily) (obj)).FontFamily();
        if(!((FontFamily) (obj)).addFontFromAssetManager(assetmanager, s, i, false, 0, -1, -1, null))
            break MISSING_BLOCK_LABEL_117;
        flag = ((FontFamily) (obj)).freeze();
        if(flag)
            break MISSING_BLOCK_LABEL_88;
        lrucache;
        JVM INSTR monitorexit ;
        return null;
        assetmanager = createFromFamiliesWithDefault(new FontFamily[] {
            obj
        }, -1, -1);
        sDynamicTypefaceCache.put(s1, assetmanager);
        lrucache;
        JVM INSTR monitorexit ;
        return assetmanager;
        lrucache;
        JVM INSTR monitorexit ;
_L2:
        return null;
        assetmanager;
        throw assetmanager;
    }

    public static Typeface createFromResources(android.content.res.FontResourcesParser.FamilyResourceEntry familyresourceentry, AssetManager assetmanager, String s)
    {
        Object obj;
        if(sFallbackFonts == null)
            break MISSING_BLOCK_LABEL_314;
        if(familyresourceentry instanceof android.content.res.FontResourcesParser.ProviderResourceEntry)
        {
            s = (android.content.res.FontResourcesParser.ProviderResourceEntry)familyresourceentry;
            familyresourceentry = s.getCerts();
            assetmanager = new ArrayList();
            if(familyresourceentry != null)
            {
                for(int i = 0; i < familyresourceentry.size(); i++)
                {
                    List list = (List)familyresourceentry.get(i);
                    ArrayList arraylist = new ArrayList();
                    for(int k = 0; k < list.size(); k++)
                        arraylist.add(Base64.decode((String)list.get(k), 0));

                    assetmanager.add(arraylist);
                }

            }
            assetmanager = FontsContract.getFontSync(new FontRequest(s.getAuthority(), s.getPackage(), s.getQuery(), assetmanager));
            familyresourceentry = assetmanager;
            if(assetmanager == null)
                familyresourceentry = DEFAULT;
            return familyresourceentry;
        }
        obj = findFromCache(assetmanager, s);
        if(obj != null)
            return ((Typeface) (obj));
        obj = (android.content.res.FontResourcesParser.FontFamilyFilesResourceEntry)familyresourceentry;
        familyresourceentry = new FontFamily();
        android.content.res.FontResourcesParser.FontFileResourceEntry afontfileresourceentry[] = ((android.content.res.FontResourcesParser.FontFamilyFilesResourceEntry) (obj)).getEntries();
        int l = afontfileresourceentry.length;
        for(int j = 0; j < l; j++)
        {
            android.content.res.FontResourcesParser.FontFileResourceEntry fontfileresourceentry = afontfileresourceentry[j];
            if(!familyresourceentry.addFontFromAssetManager(assetmanager, fontfileresourceentry.getFileName(), 0, false, 0, fontfileresourceentry.getWeight(), fontfileresourceentry.getItalic(), null))
                return null;
        }

        if(!familyresourceentry.freeze())
            return null;
        afontfileresourceentry = createFromFamiliesWithDefault(new FontFamily[] {
            familyresourceentry
        }, -1, -1);
        familyresourceentry = sDynamicTypefaceCache;
        familyresourceentry;
        JVM INSTR monitorenter ;
        assetmanager = Builder._2D_wrap0(assetmanager, s, 0, null, -1, -1);
        sDynamicTypefaceCache.put(assetmanager, afontfileresourceentry);
        familyresourceentry;
        JVM INSTR monitorexit ;
        return afontfileresourceentry;
        assetmanager;
        throw assetmanager;
        return null;
    }

    public static Typeface createFromTypefaceWithVariation(Typeface typeface, List list)
    {
        long l;
        if(typeface == null)
            l = 0L;
        else
            l = typeface.native_instance;
        return new Typeface(nativeCreateFromTypefaceWithVariation(l, list));
    }

    private static String createProviderUid(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("provider:");
        stringbuilder.append(s);
        stringbuilder.append("-");
        stringbuilder.append(s1);
        return stringbuilder.toString();
    }

    public static Typeface defaultFromStyle(int i)
    {
        return sDefaults[i];
    }

    public static Typeface findFromCache(AssetManager assetmanager, String s)
    {
        LruCache lrucache = sDynamicTypefaceCache;
        lrucache;
        JVM INSTR monitorenter ;
        assetmanager = Builder._2D_wrap0(assetmanager, s, 0, null, -1, -1);
        assetmanager = (Typeface)sDynamicTypefaceCache.get(assetmanager);
        if(assetmanager != null)
            return assetmanager;
        lrucache;
        JVM INSTR monitorexit ;
        return null;
        assetmanager;
        throw assetmanager;
    }

    private static File getSystemFontConfigLocation()
    {
        return new File("/system/etc/");
    }

    private static void init()
    {
        File file = new File(getSystemFontConfigLocation(), "fonts.xml");
        Object obj;
        Object obj2;
        Object obj3;
        obj = JVM INSTR new #358 <Class FileInputStream>;
        ((FileInputStream) (obj)).FileInputStream(file);
        obj2 = FontListParser.parse(((java.io.InputStream) (obj)));
        obj3 = JVM INSTR new #369 <Class HashMap>;
        ((HashMap) (obj3)).HashMap();
        obj = JVM INSTR new #266 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        int i = 0;
_L2:
        Object obj4;
        if(i >= ((FontConfig) (obj2)).getFamilies().length)
            break; /* Loop/switch isn't completed */
        obj4 = ((FontConfig) (obj2)).getFamilies()[i];
        if(i == 0)
            break MISSING_BLOCK_LABEL_78;
        if(((android.text.FontConfig.Family) (obj4)).getName() != null)
            break MISSING_BLOCK_LABEL_100;
        obj4 = makeFamilyFromParsed(((android.text.FontConfig.Family) (obj4)), ((Map) (obj3)));
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_100;
        ((List) (obj)).add(obj4);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        sFallbackFonts = (FontFamily[])((List) (obj)).toArray(new FontFamily[((List) (obj)).size()]);
        setDefault(createFromFamilies(sFallbackFonts));
        obj4 = JVM INSTR new #369 <Class HashMap>;
        ((HashMap) (obj4)).HashMap();
        i = 0;
_L7:
        android.text.FontConfig.Family family;
        if(i >= ((FontConfig) (obj2)).getFamilies().length)
            break MISSING_BLOCK_LABEL_233;
        family = ((FontConfig) (obj2)).getFamilies()[i];
        if(family.getName() == null) goto _L4; else goto _L3
_L3:
        if(i != 0) goto _L6; else goto _L5
_L5:
        obj = sDefaultTypeface;
_L9:
        ((Map) (obj4)).put(family.getName(), obj);
_L4:
        i++;
          goto _L7
_L6:
        obj = makeFamilyFromParsed(family, ((Map) (obj3)));
        if(obj == null) goto _L4; else goto _L8
_L8:
        obj = createFromFamiliesWithDefault(new FontFamily[] {
            obj
        }, -1, -1);
          goto _L9
        android.text.FontConfig.Alias aalias[] = ((FontConfig) (obj2)).getAliases();
        i = 0;
        int j = aalias.length;
_L11:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj2 = aalias[i];
        obj3 = (Typeface)((Map) (obj4)).get(((android.text.FontConfig.Alias) (obj2)).getToName());
        obj = obj3;
        int k = ((android.text.FontConfig.Alias) (obj2)).getWeight();
        if(k == 400)
            break MISSING_BLOCK_LABEL_308;
        obj = JVM INSTR new #2   <Class Typeface>;
        ((Typeface) (obj)).Typeface(nativeCreateWeightAlias(((Typeface) (obj3)).native_instance, k));
        ((Map) (obj4)).put(((android.text.FontConfig.Alias) (obj2)).getName(), obj);
        i++;
        if(true) goto _L11; else goto _L10
_L10:
        sSystemFontMap = ((Map) (obj4));
_L12:
        return;
        Object obj1;
        obj1;
        Log.e(TAG, (new StringBuilder()).append("XML parse exception for ").append(file).toString(), ((Throwable) (obj1)));
          goto _L12
        obj1;
        Log.e(TAG, (new StringBuilder()).append("Error reading ").append(file).toString(), ((Throwable) (obj1)));
          goto _L12
        obj1;
        Log.e(TAG, (new StringBuilder()).append("Error opening ").append(file).toString(), ((Throwable) (obj1)));
          goto _L12
        obj1;
        Log.w(TAG, "Didn't create default family (most likely, non-Minikin build)", ((Throwable) (obj1)));
          goto _L12
    }

    private static FontFamily makeFamilyFromParsed(android.text.FontConfig.Family family, Map map)
    {
        FontFamily fontfamily;
        android.text.FontConfig.Font afont[];
        int i;
        int j;
        fontfamily = new FontFamily(family.getLanguage(), family.getVariant());
        afont = family.getFonts();
        i = afont.length;
        j = 0;
_L5:
        if(j >= i) goto _L2; else goto _L1
_L1:
        android.text.FontConfig.Font font = afont[j];
        if(!TypefaceInjector.addFontToFamily(fontfamily, font, map)) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L4:
        String s;
        Object obj;
        Object obj2;
        s = (new StringBuilder()).append("/system/fonts/").append(font.getFontName()).toString();
        obj = (ByteBuffer)map.get(s);
        obj2 = obj;
        if(obj != null) goto _L7; else goto _L6
_L6:
        Object obj3;
        Object obj4;
        Object obj5;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj2 = JVM INSTR new #358 <Class FileInputStream>;
        ((FileInputStream) (obj2)).FileInputStream(s);
        obj = ((FileInputStream) (obj2)).getChannel();
        long l = ((FileChannel) (obj)).size();
        obj4 = ((FileChannel) (obj)).map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        map.put(s, obj4);
        obj = obj3;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_182;
        ((FileInputStream) (obj2)).close();
        obj = obj3;
_L9:
        obj2 = obj4;
        if(obj == null) goto _L7; else goto _L8
_L8:
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
_L10:
        Log.e(TAG, (new StringBuilder()).append("Error mapping font file ").append(s).toString());
          goto _L3
        obj;
          goto _L9
        Object obj1;
        obj1;
        obj2 = obj5;
_L14:
        throw obj1;
        Exception exception;
        exception;
        obj4 = obj2;
        obj2 = obj1;
        obj1 = exception;
_L13:
        exception = ((Exception) (obj2));
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_273;
        ((FileInputStream) (obj4)).close();
        exception = ((Exception) (obj2));
_L11:
        if(exception == null)
            break MISSING_BLOCK_LABEL_325;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
          goto _L10
        obj4;
label0:
        {
            if(obj2 != null)
                break label0;
            exception = ((Exception) (obj4));
        }
          goto _L11
        exception = ((Exception) (obj2));
        if(obj2 == obj4) goto _L11; else goto _L12
_L12:
        ((Throwable) (obj2)).addSuppressed(((Throwable) (obj4)));
        exception = ((Exception) (obj2));
          goto _L11
        throw obj1;
_L7:
        int k = font.getTtcIndex();
        obj1 = font.getAxes();
        int i1 = font.getWeight();
        int j1;
        if(font.isItalic())
            j1 = 1;
        else
            j1 = 0;
        if(!fontfamily.addFontFromBuffer(((ByteBuffer) (obj2)), k, ((FontVariationAxis []) (obj1)), i1, j1))
            Log.e(TAG, (new StringBuilder()).append("Error creating font ").append(s).append("#").append(font.getTtcIndex()).toString());
          goto _L3
_L2:
        if(!fontfamily.freeze())
        {
            Log.e(TAG, (new StringBuilder()).append("Unable to load Family: ").append(family.getName()).append(":").append(family.getLanguage()).toString());
            return null;
        } else
        {
            return fontfamily;
        }
        obj1;
        obj2 = null;
          goto _L13
        obj1;
        obj4 = obj2;
        obj2 = null;
          goto _L13
        obj1;
          goto _L14
    }

    private static native long nativeCreateFromArray(long al[], int i, int j);

    private static native long nativeCreateFromTypeface(long l, int i);

    private static native long nativeCreateFromTypefaceWithExactStyle(long l, int i, boolean flag);

    private static native long nativeCreateFromTypefaceWithVariation(long l, List list);

    private static native long nativeCreateWeightAlias(long l, int i);

    private static native int nativeGetStyle(long l);

    private static native int[] nativeGetSupportedAxes(long l);

    private static native int nativeGetWeight(long l);

    private static native void nativeSetDefault(long l);

    private static native void nativeUnref(long l);

    private static void setDefault(Typeface typeface)
    {
        sDefaultTypeface = typeface;
        nativeSetDefault(typeface.native_instance);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Typeface)obj;
        if(mStyle != ((Typeface) (obj)).mStyle || native_instance != ((Typeface) (obj)).native_instance)
            flag = false;
        return flag;
    }

    protected void finalize()
        throws Throwable
    {
        nativeUnref(native_instance);
        native_instance = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getStyle()
    {
        return mStyle;
    }

    public int hashCode()
    {
        return ((int)(native_instance ^ native_instance >>> 32) + 527) * 31 + mStyle;
    }

    public final boolean isBold()
    {
        boolean flag = false;
        if((mStyle & 1) != 0)
            flag = true;
        return flag;
    }

    public final boolean isItalic()
    {
        boolean flag = false;
        if((mStyle & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean isSupportedAxes(int i)
    {
        boolean flag = false;
        if(mSupportedAxes != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if(mSupportedAxes == null)
        {
            mSupportedAxes = nativeGetSupportedAxes(native_instance);
            if(mSupportedAxes == null)
                mSupportedAxes = EMPTY_AXES;
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        if(Arrays.binarySearch(mSupportedAxes, i) >= 0)
            flag = true;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int BOLD = 1;
    public static final int BOLD_ITALIC = 3;
    public static final Typeface DEFAULT;
    public static final Typeface DEFAULT_BOLD;
    private static final int EMPTY_AXES[] = new int[0];
    static final String FONTS_CONFIG = "fonts.xml";
    public static final int ITALIC = 2;
    public static final Typeface MONOSPACE = create("monospace", 0);
    public static final int NORMAL = 0;
    public static final int RESOLVE_BY_FONT_TABLE = -1;
    public static final Typeface SANS_SERIF = create("sans-serif", 0);
    public static final Typeface SERIF = create("serif", 0);
    private static final int STYLE_ITALIC = 1;
    private static final int STYLE_NORMAL = 0;
    private static String TAG = "Typeface";
    static Typeface sDefaultTypeface;
    static Typeface sDefaults[];
    private static final LruCache sDynamicTypefaceCache = new LruCache(16);
    static FontFamily sFallbackFonts[];
    private static final Object sLock = new Object();
    static Map sSystemFontMap;
    private static final LongSparseArray sTypefaceCache = new LongSparseArray(3);
    private int mStyle;
    private int mSupportedAxes[];
    private int mWeight;
    public long native_instance;

    static 
    {
        init();
        DEFAULT = create((String)null, 0);
        DEFAULT_BOLD = create((String)null, 1);
        sDefaults = (new Typeface[] {
            DEFAULT, DEFAULT_BOLD, create((String)null, 2), create((String)null, 3)
        });
    }
}
