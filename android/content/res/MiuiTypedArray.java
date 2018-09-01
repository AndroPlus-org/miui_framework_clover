// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;


// Referenced classes of package android.content.res:
//            TypedArray, MiuiResources, Resources

public class MiuiTypedArray extends TypedArray
{

    MiuiTypedArray(Resources resources)
    {
        super(resources);
        mIsMiuiResources = getResources() instanceof MiuiResources;
    }

    private CharSequence loadStringValueAt(int i)
    {
        if(!mIsMiuiResources)
            return null;
        if(mData[i + 0] == 3)
        {
            i = mData[i + 3];
            CharSequence charsequence = ((MiuiResources)getResources()).getThemeString(i);
            if(charsequence != null)
                return charsequence;
        }
        return null;
    }

    public String getString(int i)
    {
        CharSequence charsequence = loadStringValueAt(i * 6);
        if(charsequence != null)
            return charsequence.toString();
        else
            return super.getString(i);
    }

    public CharSequence getText(int i)
    {
        CharSequence charsequence = loadStringValueAt(i * 6);
        if(charsequence != null)
            return charsequence;
        else
            return super.getText(i);
    }

    private boolean mIsMiuiResources;
}
