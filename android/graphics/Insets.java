// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Rect

public class Insets
{

    private Insets(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public static Insets of(int i, int j, int k, int l)
    {
        if(i == 0 && j == 0 && k == 0 && l == 0)
            return NONE;
        else
            return new Insets(i, j, k, l);
    }

    public static Insets of(Rect rect)
    {
        if(rect == null)
            rect = NONE;
        else
            rect = of(rect.left, rect.top, rect.right, rect.bottom);
        return rect;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Insets)obj;
        if(bottom != ((Insets) (obj)).bottom)
            return false;
        if(left != ((Insets) (obj)).left)
            return false;
        if(right != ((Insets) (obj)).right)
            return false;
        return top == ((Insets) (obj)).top;
    }

    public int hashCode()
    {
        return ((left * 31 + top) * 31 + right) * 31 + bottom;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Insets{left=").append(left).append(", top=").append(top).append(", right=").append(right).append(", bottom=").append(bottom).append('}').toString();
    }

    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

}
