// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            MaskFilter

public class TableMaskFilter extends MaskFilter
{

    private TableMaskFilter(long l)
    {
        native_instance = l;
    }

    public TableMaskFilter(byte abyte0[])
    {
        if(abyte0.length < 256)
        {
            throw new RuntimeException("table.length must be >= 256");
        } else
        {
            native_instance = nativeNewTable(abyte0);
            return;
        }
    }

    public static TableMaskFilter CreateClipTable(int i, int j)
    {
        return new TableMaskFilter(nativeNewClip(i, j));
    }

    public static TableMaskFilter CreateGammaTable(float f)
    {
        return new TableMaskFilter(nativeNewGamma(f));
    }

    private static native long nativeNewClip(int i, int j);

    private static native long nativeNewGamma(float f);

    private static native long nativeNewTable(byte abyte0[]);
}
