// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.graphics.Rect;
import android.text.Layout;
import android.text.Spannable;

// Referenced classes of package android.widget:
//            TextView

final class AccessibilityIterators
{
    static class LineTextSegmentIterator extends android.view.AbstractTextSegmentIterator
    {

        public static LineTextSegmentIterator getInstance()
        {
            if(sLineInstance == null)
                sLineInstance = new LineTextSegmentIterator();
            return sLineInstance;
        }

        public int[] following(int i)
        {
            if(mText.length() <= 0)
                return null;
            if(i >= mText.length())
                return null;
            if(i < 0)
            {
                i = mLayout.getLineForOffset(0);
            } else
            {
                int j = mLayout.getLineForOffset(i);
                if(getLineEdgeIndex(j, -1) == i)
                    i = j;
                else
                    i = j + 1;
            }
            if(i >= mLayout.getLineCount())
                return null;
            else
                return getRange(getLineEdgeIndex(i, -1), getLineEdgeIndex(i, 1) + 1);
        }

        protected int getLineEdgeIndex(int i, int j)
        {
            if(j * mLayout.getParagraphDirection(i) < 0)
                return mLayout.getLineStart(i);
            else
                return mLayout.getLineEnd(i) - 1;
        }

        public void initialize(Spannable spannable, Layout layout)
        {
            mText = spannable.toString();
            mLayout = layout;
        }

        public int[] preceding(int i)
        {
            if(mText.length() <= 0)
                return null;
            if(i <= 0)
                return null;
            if(i > mText.length())
            {
                i = mLayout.getLineForOffset(mText.length());
            } else
            {
                int j = mLayout.getLineForOffset(i);
                if(getLineEdgeIndex(j, 1) + 1 == i)
                    i = j;
                else
                    i = j - 1;
            }
            if(i < 0)
                return null;
            else
                return getRange(getLineEdgeIndex(i, -1), getLineEdgeIndex(i, 1) + 1);
        }

        protected static final int DIRECTION_END = 1;
        protected static final int DIRECTION_START = -1;
        private static LineTextSegmentIterator sLineInstance;
        protected Layout mLayout;

        LineTextSegmentIterator()
        {
        }
    }

    static class PageTextSegmentIterator extends LineTextSegmentIterator
    {

        public static PageTextSegmentIterator getInstance()
        {
            if(sPageInstance == null)
                sPageInstance = new PageTextSegmentIterator();
            return sPageInstance;
        }

        public int[] following(int i)
        {
            if(mText.length() <= 0)
                return null;
            if(i >= mText.length())
                return null;
            if(!mView.getGlobalVisibleRect(mTempRect))
                return null;
            int j = Math.max(0, i);
            i = mLayout.getLineForOffset(j);
            i = mLayout.getLineTop(i) + (mTempRect.height() - mView.getTotalPaddingTop() - mView.getTotalPaddingBottom());
            if(i < mLayout.getLineTop(mLayout.getLineCount() - 1))
                i = mLayout.getLineForVertical(i);
            else
                i = mLayout.getLineCount();
            return getRange(j, getLineEdgeIndex(i - 1, 1) + 1);
        }

        public void initialize(TextView textview)
        {
            super.initialize((Spannable)textview.getIterableTextForAccessibility(), textview.getLayout());
            mView = textview;
        }

        public int[] preceding(int i)
        {
            if(mText.length() <= 0)
                return null;
            if(i <= 0)
                return null;
            if(!mView.getGlobalVisibleRect(mTempRect))
                return null;
            int j = Math.min(mText.length(), i);
            int k = mLayout.getLineForOffset(j);
            i = mLayout.getLineTop(k) - (mTempRect.height() - mView.getTotalPaddingTop() - mView.getTotalPaddingBottom());
            int l;
            if(i > 0)
                i = mLayout.getLineForVertical(i);
            else
                i = 0;
            l = i;
            if(j == mText.length())
            {
                l = i;
                if(i < k)
                    l = i + 1;
            }
            return getRange(getLineEdgeIndex(l, -1), j);
        }

        private static PageTextSegmentIterator sPageInstance;
        private final Rect mTempRect = new Rect();
        private TextView mView;

        PageTextSegmentIterator()
        {
        }
    }


    AccessibilityIterators()
    {
    }
}
