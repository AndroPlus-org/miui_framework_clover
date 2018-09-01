// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            Layout, Spannable, Spanned, NoCopySpan

public class Selection
{
    private static final class END
        implements NoCopySpan
    {

        private END()
        {
        }

        END(END end)
        {
            this();
        }
    }

    public static interface PositionIterator
    {

        public abstract int following(int i);

        public abstract int preceding(int i);

        public static final int DONE = -1;
    }

    private static final class START
        implements NoCopySpan
    {

        private START()
        {
        }

        START(START start)
        {
            this();
        }
    }


    private Selection()
    {
    }

    private static int chooseHorizontal(Layout layout, int i, int j, int k)
    {
        if(layout.getLineForOffset(j) == layout.getLineForOffset(k))
        {
            float f = layout.getPrimaryHorizontal(j);
            float f1 = layout.getPrimaryHorizontal(k);
            if(i < 0)
                if(f < f1)
                    return j;
                else
                    return k;
            if(f > f1)
                return j;
            else
                return k;
        }
        if(layout.getParagraphDirection(layout.getLineForOffset(j)) == i)
            return Math.max(j, k);
        else
            return Math.min(j, k);
    }

    public static boolean extendDown(Spannable spannable, Layout layout)
    {
        int i = getSelectionEnd(spannable);
        int j = layout.getLineForOffset(i);
        if(j < layout.getLineCount() - 1)
        {
            if(layout.getParagraphDirection(j) == layout.getParagraphDirection(j + 1))
                i = layout.getOffsetForHorizontal(j + 1, layout.getPrimaryHorizontal(i));
            else
                i = layout.getLineStart(j + 1);
            extendSelection(spannable, i);
            return true;
        }
        if(i != spannable.length())
        {
            extendSelection(spannable, spannable.length());
            return true;
        } else
        {
            return true;
        }
    }

    public static boolean extendLeft(Spannable spannable, Layout layout)
    {
        int i = getSelectionEnd(spannable);
        int j = layout.getOffsetToLeftOf(i);
        if(j != i)
        {
            extendSelection(spannable, j);
            return true;
        } else
        {
            return true;
        }
    }

    public static boolean extendRight(Spannable spannable, Layout layout)
    {
        int i = getSelectionEnd(spannable);
        int j = layout.getOffsetToRightOf(i);
        if(j != i)
        {
            extendSelection(spannable, j);
            return true;
        } else
        {
            return true;
        }
    }

    public static final void extendSelection(Spannable spannable, int i)
    {
        if(spannable.getSpanStart(SELECTION_END) != i)
            spannable.setSpan(SELECTION_END, i, i, 34);
    }

    public static boolean extendToLeftEdge(Spannable spannable, Layout layout)
    {
        extendSelection(spannable, findEdge(spannable, layout, -1));
        return true;
    }

    public static boolean extendToRightEdge(Spannable spannable, Layout layout)
    {
        extendSelection(spannable, findEdge(spannable, layout, 1));
        return true;
    }

    public static boolean extendUp(Spannable spannable, Layout layout)
    {
        int i = getSelectionEnd(spannable);
        int j = layout.getLineForOffset(i);
        if(j > 0)
        {
            if(layout.getParagraphDirection(j) == layout.getParagraphDirection(j - 1))
                i = layout.getOffsetForHorizontal(j - 1, layout.getPrimaryHorizontal(i));
            else
                i = layout.getLineStart(j - 1);
            extendSelection(spannable, i);
            return true;
        }
        if(i != 0)
        {
            extendSelection(spannable, 0);
            return true;
        } else
        {
            return true;
        }
    }

    private static int findEdge(Spannable spannable, Layout layout, int i)
    {
        int j = layout.getLineForOffset(getSelectionEnd(spannable));
        if(i * layout.getParagraphDirection(j) < 0)
            return layout.getLineStart(j);
        i = layout.getLineEnd(j);
        if(j == layout.getLineCount() - 1)
            return i;
        else
            return i - 1;
    }

    public static final int getSelectionEnd(CharSequence charsequence)
    {
        if(charsequence instanceof Spanned)
            return ((Spanned)charsequence).getSpanStart(SELECTION_END);
        else
            return -1;
    }

    public static final int getSelectionStart(CharSequence charsequence)
    {
        if(charsequence instanceof Spanned)
            return ((Spanned)charsequence).getSpanStart(SELECTION_START);
        else
            return -1;
    }

    public static boolean moveDown(Spannable spannable, Layout layout)
    {
        int i = getSelectionStart(spannable);
        int j = getSelectionEnd(spannable);
        if(i != j)
        {
            int k = Math.min(i, j);
            j = Math.max(i, j);
            setSelection(spannable, j);
            return k != 0 || j != spannable.length();
        }
        int l = layout.getLineForOffset(j);
        if(l < layout.getLineCount() - 1)
        {
            if(layout.getParagraphDirection(l) == layout.getParagraphDirection(l + 1))
                l = layout.getOffsetForHorizontal(l + 1, layout.getPrimaryHorizontal(j));
            else
                l = layout.getLineStart(l + 1);
            setSelection(spannable, l);
            return true;
        }
        if(j != spannable.length())
        {
            setSelection(spannable, spannable.length());
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean moveLeft(Spannable spannable, Layout layout)
    {
        int i = getSelectionStart(spannable);
        int j = getSelectionEnd(spannable);
        if(i != j)
        {
            setSelection(spannable, chooseHorizontal(layout, -1, i, j));
            return true;
        }
        i = layout.getOffsetToLeftOf(j);
        if(i != j)
        {
            setSelection(spannable, i);
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean moveRight(Spannable spannable, Layout layout)
    {
        int i = getSelectionStart(spannable);
        int j = getSelectionEnd(spannable);
        if(i != j)
        {
            setSelection(spannable, chooseHorizontal(layout, 1, i, j));
            return true;
        }
        i = layout.getOffsetToRightOf(j);
        if(i != j)
        {
            setSelection(spannable, i);
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean moveToFollowing(Spannable spannable, PositionIterator positioniterator, boolean flag)
    {
        int i = positioniterator.following(getSelectionEnd(spannable));
        if(i != -1)
            if(flag)
                extendSelection(spannable, i);
            else
                setSelection(spannable, i);
        return true;
    }

    public static boolean moveToLeftEdge(Spannable spannable, Layout layout)
    {
        setSelection(spannable, findEdge(spannable, layout, -1));
        return true;
    }

    public static boolean moveToPreceding(Spannable spannable, PositionIterator positioniterator, boolean flag)
    {
        int i = positioniterator.preceding(getSelectionEnd(spannable));
        if(i != -1)
            if(flag)
                extendSelection(spannable, i);
            else
                setSelection(spannable, i);
        return true;
    }

    public static boolean moveToRightEdge(Spannable spannable, Layout layout)
    {
        setSelection(spannable, findEdge(spannable, layout, 1));
        return true;
    }

    public static boolean moveUp(Spannable spannable, Layout layout)
    {
        int i = getSelectionStart(spannable);
        int j = getSelectionEnd(spannable);
        if(i != j)
        {
            int k = Math.min(i, j);
            j = Math.max(i, j);
            setSelection(spannable, k);
            return k != 0 || j != spannable.length();
        }
        int l = layout.getLineForOffset(j);
        if(l > 0)
        {
            if(layout.getParagraphDirection(l) == layout.getParagraphDirection(l - 1))
                j = layout.getOffsetForHorizontal(l - 1, layout.getPrimaryHorizontal(j));
            else
                j = layout.getLineStart(l - 1);
            setSelection(spannable, j);
            return true;
        }
        if(j != 0)
        {
            setSelection(spannable, 0);
            return true;
        } else
        {
            return false;
        }
    }

    public static final void removeSelection(Spannable spannable)
    {
        spannable.removeSpan(SELECTION_START);
        spannable.removeSpan(SELECTION_END);
    }

    public static final void selectAll(Spannable spannable)
    {
        setSelection(spannable, 0, spannable.length());
    }

    public static final void setSelection(Spannable spannable, int i)
    {
        setSelection(spannable, i, i);
    }

    public static void setSelection(Spannable spannable, int i, int j)
    {
        int k = getSelectionStart(spannable);
        int l = getSelectionEnd(spannable);
        if(k != i || l != j)
        {
            spannable.setSpan(SELECTION_START, i, i, 546);
            spannable.setSpan(SELECTION_END, j, j, 34);
        }
    }

    public static final Object SELECTION_END = new END(null);
    public static final Object SELECTION_START = new START(null);

}
