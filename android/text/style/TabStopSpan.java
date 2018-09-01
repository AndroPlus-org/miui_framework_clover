// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;


// Referenced classes of package android.text.style:
//            ParagraphStyle

public interface TabStopSpan
    extends ParagraphStyle
{
    public static class Standard
        implements TabStopSpan
    {

        public int getTabStop()
        {
            return mTab;
        }

        private int mTab;

        public Standard(int i)
        {
            mTab = i;
        }
    }


    public abstract int getTabStop();
}
