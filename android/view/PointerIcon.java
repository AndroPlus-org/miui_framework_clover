// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.*;
import android.graphics.Bitmap;
import android.graphics.drawable.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.XmlUtils;

public final class PointerIcon
    implements Parcelable
{

    static int _2D_set0(PointerIcon pointericon, int i)
    {
        pointericon.mSystemIconResourceId = i;
        return i;
    }

    private PointerIcon(int i)
    {
        mType = i;
    }

    PointerIcon(int i, PointerIcon pointericon)
    {
        this(i);
    }

    public static PointerIcon create(Bitmap bitmap, float f, float f1)
    {
        if(bitmap == null)
        {
            throw new IllegalArgumentException("bitmap must not be null");
        } else
        {
            validateHotSpot(bitmap, f, f1);
            PointerIcon pointericon = new PointerIcon(-1);
            pointericon.mBitmap = bitmap;
            pointericon.mHotSpotX = f;
            pointericon.mHotSpotY = f1;
            return pointericon;
        }
    }

    public static PointerIcon getDefaultIcon(Context context)
    {
        return getSystemIcon(context, 1000);
    }

    public static PointerIcon getNullIcon()
    {
        return gNullIcon;
    }

    public static PointerIcon getSystemIcon(Context context, int i)
    {
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(i == 0)
            return gNullIcon;
        Object obj = (PointerIcon)gSystemIcons.get(i);
        if(obj != null)
            return ((PointerIcon) (obj));
        int j = getSystemIconTypeIndex(i);
        int k = j;
        if(j == 0)
            k = getSystemIconTypeIndex(1000);
        if(sUseLargeIcons)
            j = 0x1030318;
        else
            j = 0x103031d;
        obj = context.obtainStyledAttributes(null, com.android.internal.R.styleable.Pointer, 0, j);
        k = ((TypedArray) (obj)).getResourceId(k, -1);
        ((TypedArray) (obj)).recycle();
        if(k == -1)
        {
            Log.w("PointerIcon", (new StringBuilder()).append("Missing theme resources for pointer icon type ").append(i).toString());
            if(i == 1000)
                context = gNullIcon;
            else
                context = getSystemIcon(context, 1000);
            return context;
        }
        obj = new PointerIcon(i);
        if((0xff000000 & k) == 0x1000000)
            obj.mSystemIconResourceId = k;
        else
            ((PointerIcon) (obj)).loadResource(context, context.getResources(), k);
        gSystemIcons.append(i, obj);
        return ((PointerIcon) (obj));
    }

    private static int getSystemIconTypeIndex(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 1000: 
            return 2;

        case 2000: 
            return 14;

        case 2001: 
            return 15;

        case 2002: 
            return 13;

        case 1002: 
            return 9;

        case 1001: 
            return 4;

        case 1003: 
            return 10;

        case 1004: 
            return 21;

        case 1006: 
            return 3;

        case 1007: 
            return 6;

        case 1008: 
            return 16;

        case 1009: 
            return 20;

        case 1010: 
            return 0;

        case 1011: 
            return 5;

        case 1013: 
            return 1;

        case 1012: 
            return 12;

        case 1014: 
            return 11;

        case 1015: 
            return 19;

        case 1016: 
            return 18;

        case 1017: 
            return 17;

        case 1018: 
            return 22;

        case 1019: 
            return 23;

        case 1020: 
            return 7;

        case 1021: 
            return 8;
        }
    }

    public static PointerIcon load(Resources resources, int i)
    {
        if(resources == null)
        {
            throw new IllegalArgumentException("resources must not be null");
        } else
        {
            PointerIcon pointericon = new PointerIcon(-1);
            pointericon.loadResource(null, resources, i);
            return pointericon;
        }
    }

    private void loadResource(Context context, Resources resources, int i)
    {
        Object obj = resources.getXml(i);
        float f;
        float f1;
        XmlUtils.beginDocument(((org.xmlpull.v1.XmlPullParser) (obj)), "pointer-icon");
        TypedArray typedarray = resources.obtainAttributes(((android.util.AttributeSet) (obj)), com.android.internal.R.styleable.PointerIcon);
        i = typedarray.getResourceId(0, 0);
        f = typedarray.getDimension(1, 0.0F);
        f1 = typedarray.getDimension(2, 0.0F);
        typedarray.recycle();
        ((XmlResourceParser) (obj)).close();
        if(i == 0)
            throw new IllegalArgumentException("<pointer-icon> is missing bitmap attribute.");
        break MISSING_BLOCK_LABEL_101;
        context;
        resources = JVM INSTR new #122 <Class IllegalArgumentException>;
        resources.IllegalArgumentException("Exception parsing pointer icon resource.", context);
        throw resources;
        context;
        ((XmlResourceParser) (obj)).close();
        throw context;
        int j;
        if(context == null)
            context = resources.getDrawable(i);
        else
            context = context.getDrawable(i);
        resources = context;
        if(!(context instanceof AnimationDrawable)) goto _L2; else goto _L1
_L1:
        obj = (AnimationDrawable)context;
        j = ((AnimationDrawable) (obj)).getNumberOfFrames();
        context = ((AnimationDrawable) (obj)).getFrame(0);
        if(j != 1) goto _L4; else goto _L3
_L3:
        Log.w("PointerIcon", "Animation icon with single frame -- simply treating the first frame as a normal bitmap icon.");
        resources = context;
_L2:
        int k;
        int l;
        if(!(resources instanceof BitmapDrawable))
        {
            throw new IllegalArgumentException("<pointer-icon> bitmap attribute must refer to a bitmap drawable.");
        } else
        {
            mBitmap = ((BitmapDrawable)resources).getBitmap();
            mHotSpotX = f;
            mHotSpotY = f1;
            return;
        }
_L4:
        mDurationPerFrame = ((AnimationDrawable) (obj)).getDuration(0);
        mBitmapFrames = new Bitmap[j - 1];
        k = context.getIntrinsicWidth();
        l = context.getIntrinsicHeight();
        i = 1;
_L6:
        resources = context;
        if(i >= j) goto _L2; else goto _L5
_L5:
        resources = ((AnimationDrawable) (obj)).getFrame(i);
        if(!(resources instanceof BitmapDrawable))
            throw new IllegalArgumentException("Frame of an animated pointer icon must refer to a bitmap drawable.");
        if(resources.getIntrinsicWidth() != k || resources.getIntrinsicHeight() != l)
            throw new IllegalArgumentException((new StringBuilder()).append("The bitmap size of ").append(i).append("-th frame ").append("is different. All frames should have the exact same size and ").append("share the same hotspot.").toString());
        mBitmapFrames[i - 1] = ((BitmapDrawable)resources).getBitmap();
        i++;
          goto _L6
    }

    public static void setUseLargeIcons(boolean flag)
    {
        sUseLargeIcons = flag;
        gSystemIcons.clear();
    }

    private static void validateHotSpot(Bitmap bitmap, float f, float f1)
    {
        if(f < 0.0F || f >= (float)bitmap.getWidth())
            throw new IllegalArgumentException("x hotspot lies outside of the bitmap area");
        if(f1 < 0.0F || f1 >= (float)bitmap.getHeight())
            throw new IllegalArgumentException("y hotspot lies outside of the bitmap area");
        else
            return;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || (obj instanceof PointerIcon) ^ true)
            return false;
        obj = (PointerIcon)obj;
        if(mType != ((PointerIcon) (obj)).mType || mSystemIconResourceId != ((PointerIcon) (obj)).mSystemIconResourceId)
            return false;
        while(mSystemIconResourceId == 0 && (mBitmap != ((PointerIcon) (obj)).mBitmap || mHotSpotX != ((PointerIcon) (obj)).mHotSpotX || mHotSpotY != ((PointerIcon) (obj)).mHotSpotY)) 
            return false;
        return true;
    }

    public int getType()
    {
        return mType;
    }

    public PointerIcon load(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(mSystemIconResourceId == 0 || mBitmap != null)
        {
            return this;
        } else
        {
            PointerIcon pointericon = new PointerIcon(mType);
            pointericon.mSystemIconResourceId = mSystemIconResourceId;
            pointericon.loadResource(context, context.getResources(), mSystemIconResourceId);
            return pointericon;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        if(mType != 0)
        {
            parcel.writeInt(mSystemIconResourceId);
            if(mSystemIconResourceId == 0)
            {
                mBitmap.writeToParcel(parcel, i);
                parcel.writeFloat(mHotSpotX);
                parcel.writeFloat(mHotSpotY);
            }
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PointerIcon createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 0)
                return PointerIcon.getNullIcon();
            int j = parcel.readInt();
            if(j != 0)
            {
                parcel = new PointerIcon(i, null);
                PointerIcon._2D_set0(parcel, j);
                return parcel;
            } else
            {
                return PointerIcon.create((Bitmap)Bitmap.CREATOR.createFromParcel(parcel), parcel.readFloat(), parcel.readFloat());
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PointerIcon[] newArray(int i)
        {
            return new PointerIcon[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "PointerIcon";
    public static final int TYPE_ALIAS = 1010;
    public static final int TYPE_ALL_SCROLL = 1013;
    public static final int TYPE_ARROW = 1000;
    public static final int TYPE_CELL = 1006;
    public static final int TYPE_CONTEXT_MENU = 1001;
    public static final int TYPE_COPY = 1011;
    public static final int TYPE_CROSSHAIR = 1007;
    public static final int TYPE_CUSTOM = -1;
    public static final int TYPE_DEFAULT = 1000;
    public static final int TYPE_GRAB = 1020;
    public static final int TYPE_GRABBING = 1021;
    public static final int TYPE_HAND = 1002;
    public static final int TYPE_HELP = 1003;
    public static final int TYPE_HORIZONTAL_DOUBLE_ARROW = 1014;
    public static final int TYPE_NOT_SPECIFIED = 1;
    public static final int TYPE_NO_DROP = 1012;
    public static final int TYPE_NULL = 0;
    private static final int TYPE_OEM_FIRST = 10000;
    public static final int TYPE_SPOT_ANCHOR = 2002;
    public static final int TYPE_SPOT_HOVER = 2000;
    public static final int TYPE_SPOT_TOUCH = 2001;
    public static final int TYPE_TEXT = 1008;
    public static final int TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW = 1017;
    public static final int TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW = 1016;
    public static final int TYPE_VERTICAL_DOUBLE_ARROW = 1015;
    public static final int TYPE_VERTICAL_TEXT = 1009;
    public static final int TYPE_WAIT = 1004;
    public static final int TYPE_ZOOM_IN = 1018;
    public static final int TYPE_ZOOM_OUT = 1019;
    private static final PointerIcon gNullIcon = new PointerIcon(0);
    private static final SparseArray gSystemIcons = new SparseArray();
    private static boolean sUseLargeIcons = false;
    private Bitmap mBitmap;
    private Bitmap mBitmapFrames[];
    private int mDurationPerFrame;
    private float mHotSpotX;
    private float mHotSpotY;
    private int mSystemIconResourceId;
    private final int mType;

}
