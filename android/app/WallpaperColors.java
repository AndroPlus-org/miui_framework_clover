// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.graphics.palette.Palette;
import com.android.internal.graphics.palette.VariationalKMeansQuantizer;
import java.util.*;

public final class WallpaperColors
    implements Parcelable
{

    public WallpaperColors(Color color, Color color1, Color color2)
    {
        this(color, color1, color2, 0);
    }

    public WallpaperColors(Color color, Color color1, Color color2, int i)
    {
        if(color == null)
            throw new IllegalArgumentException("Primary color should never be null.");
        mMainColors = new ArrayList(3);
        mMainColors.add(color);
        if(color1 != null)
            mMainColors.add(color1);
        if(color2 != null)
        {
            if(color1 == null)
                throw new IllegalArgumentException("tertiaryColor can't be specified when secondaryColor is null");
            mMainColors.add(color2);
        }
        mColorHints = i;
    }

    public WallpaperColors(Parcel parcel)
    {
        mMainColors = new ArrayList();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            Color color = Color.valueOf(parcel.readInt());
            mMainColors.add(color);
        }

        mColorHints = parcel.readInt();
    }

    private static int calculateDarkHints(Bitmap bitmap)
    {
        if(bitmap == null)
            return 0;
        int ai[] = new int[bitmap.getWidth() * bitmap.getHeight()];
        double d = 0.0D;
        int i = (int)((float)ai.length * 0.05F);
        int j = 0;
        bitmap.getPixels(ai, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        bitmap = new float[3];
        for(int k = 0; k < ai.length;)
        {
            ColorUtils.colorToHSL(ai[k], bitmap);
            float f = bitmap[2];
            int i1 = Color.alpha(ai[k]);
            int j1 = j;
            if(f < 0.45F)
            {
                j1 = j;
                if(i1 != 0)
                    j1 = j + 1;
            }
            d += f;
            k++;
            j = j1;
        }

        boolean flag = false;
        d /= ai.length;
        int l = ((flag) ? 1 : 0);
        if(d > 0.75D)
        {
            l = ((flag) ? 1 : 0);
            if(j < i)
                l = 1;
        }
        j = l;
        if(d < 0.25D)
            j = l | 2;
        return j;
    }

    private static Size calculateOptimalSize(int i, int j)
    {
        int k = i * j;
        double d = 1.0D;
        if(k > 12544)
            d = Math.sqrt(12544D / (double)k);
        int l = (int)((double)i * d);
        k = (int)((double)j * d);
        i = l;
        if(l == 0)
            i = 1;
        j = k;
        if(k == 0)
            j = 1;
        return new Size(i, j);
    }

    public static WallpaperColors fromBitmap(Bitmap bitmap)
    {
        int i;
        int j;
        boolean flag;
        Object obj;
        ArrayList arraylist;
        Object obj1;
        Object obj2;
        Object obj3;
        if(bitmap == null)
            throw new IllegalArgumentException("Bitmap can't be null");
        i = bitmap.getWidth();
        j = bitmap.getHeight();
        flag = false;
        obj = bitmap;
        if(i * j > 12544)
        {
            flag = true;
            obj = calculateOptimalSize(bitmap.getWidth(), bitmap.getHeight());
            obj = Bitmap.createScaledBitmap(bitmap, ((Size) (obj)).getWidth(), ((Size) (obj)).getHeight(), true);
        }
        arraylist = new ArrayList(Palette.from(((Bitmap) (obj))).setQuantizer(new VariationalKMeansQuantizer()).maximumColorCount(5).clearFilters().resizeBitmapArea(12544).generate().getSwatches());
        arraylist.removeIf(new _.Lambda.xNlQtks0cIOkqsInCE_AAmZWgcY._cls1((float)(((Bitmap) (obj)).getWidth() * ((Bitmap) (obj)).getHeight()) * 0.05F));
        arraylist.sort(_.Lambda.xNlQtks0cIOkqsInCE_AAmZWgcY.$INST$0);
        i = arraylist.size();
        obj1 = null;
        obj2 = null;
        obj3 = null;
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        bitmap = Color.valueOf(((com.android.internal.graphics.palette.Palette.Swatch)arraylist.get(j)).getRgb());
        j;
        JVM INSTR tableswitch 0 2: default 216
    //                   0 248
    //                   1 257
    //                   2 263;
           goto _L2 _L3 _L4 _L5
_L2:
        j = calculateDarkHints(((Bitmap) (obj)));
        if(flag)
            ((Bitmap) (obj)).recycle();
        return new WallpaperColors(((Color) (obj1)), ((Color) (obj2)), ((Color) (obj3)), j | 4);
_L3:
        obj1 = bitmap;
_L7:
        j++;
          goto _L6
_L4:
        obj2 = bitmap;
          goto _L7
_L5:
        obj3 = bitmap;
          goto _L7
    }

    public static WallpaperColors fromDrawable(Drawable drawable)
    {
        int i;
        int k;
label0:
        {
            i = drawable.getIntrinsicWidth();
            int j = drawable.getIntrinsicHeight();
            if(i > 0)
            {
                k = j;
                if(j > 0)
                    break label0;
            }
            i = 112;
            k = 112;
        }
        Object obj = calculateOptimalSize(i, k);
        obj = Bitmap.createBitmap(((Size) (obj)).getWidth(), ((Size) (obj)).getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(((Bitmap) (obj)));
        drawable.setBounds(0, 0, ((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
        drawable.draw(canvas);
        drawable = fromBitmap(((Bitmap) (obj)));
        ((Bitmap) (obj)).recycle();
        return drawable;
    }

    static boolean lambda$_2D_android_app_WallpaperColors_6256(float f, com.android.internal.graphics.palette.Palette.Swatch swatch)
    {
        boolean flag;
        if((float)swatch.getPopulation() < f)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static int lambda$_2D_android_app_WallpaperColors_6318(com.android.internal.graphics.palette.Palette.Swatch swatch, com.android.internal.graphics.palette.Palette.Swatch swatch1)
    {
        return swatch1.getPopulation() - swatch.getPopulation();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (WallpaperColors)obj;
        boolean flag1 = flag;
        if(mMainColors.equals(((WallpaperColors) (obj)).mMainColors))
        {
            flag1 = flag;
            if(mColorHints == ((WallpaperColors) (obj)).mColorHints)
                flag1 = true;
        }
        return flag1;
    }

    public int getColorHints()
    {
        return mColorHints;
    }

    public List getMainColors()
    {
        return Collections.unmodifiableList(mMainColors);
    }

    public Color getPrimaryColor()
    {
        return (Color)mMainColors.get(0);
    }

    public Color getSecondaryColor()
    {
        Color color;
        if(mMainColors.size() < 2)
            color = null;
        else
            color = (Color)mMainColors.get(1);
        return color;
    }

    public Color getTertiaryColor()
    {
        Color color;
        if(mMainColors.size() < 3)
            color = null;
        else
            color = (Color)mMainColors.get(2);
        return color;
    }

    public int hashCode()
    {
        return mMainColors.hashCode() * 31 + mColorHints;
    }

    public void setColorHints(int i)
    {
        mColorHints = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < mMainColors.size(); i++)
            stringbuilder.append(Integer.toHexString(((Color)mMainColors.get(i)).toArgb())).append(" ");

        return (new StringBuilder()).append("[WallpaperColors: ").append(stringbuilder.toString()).append("h: ").append(mColorHints).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        List list = getMainColors();
        int j = list.size();
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
            parcel.writeInt(((Color)list.get(i)).toArgb());

        parcel.writeInt(mColorHints);
    }

    private static final float BRIGHT_IMAGE_MEAN_LUMINANCE = 0.75F;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WallpaperColors createFromParcel(Parcel parcel)
        {
            return new WallpaperColors(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WallpaperColors[] newArray(int i)
        {
            return new WallpaperColors[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final float DARK_PIXEL_LUMINANCE = 0.45F;
    private static final float DARK_THEME_MEAN_LUMINANCE = 0.25F;
    public static final int HINT_FROM_BITMAP = 4;
    public static final int HINT_SUPPORTS_DARK_TEXT = 1;
    public static final int HINT_SUPPORTS_DARK_THEME = 2;
    private static final int MAX_BITMAP_SIZE = 112;
    private static final float MAX_DARK_AREA = 0.05F;
    private static final int MAX_WALLPAPER_EXTRACTION_AREA = 12544;
    private static final float MIN_COLOR_OCCURRENCE = 0.05F;
    private int mColorHints;
    private final ArrayList mMainColors;

}
