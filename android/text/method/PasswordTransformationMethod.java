// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.text.*;
import android.text.style.UpdateLayout;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.text.method:
//            TransformationMethod, TextKeyListener

public class PasswordTransformationMethod
    implements TransformationMethod, TextWatcher
{
    private static class PasswordCharSequence
        implements CharSequence, GetChars
    {

        public char charAt(int i)
        {
            if(mSource instanceof Spanned)
            {
                Spanned spanned = (Spanned)mSource;
                int j = spanned.getSpanStart(TextKeyListener.ACTIVE);
                int l = spanned.getSpanEnd(TextKeyListener.ACTIVE);
                if(i >= j && i < l)
                    return mSource.charAt(i);
                Visible avisible[] = (Visible[])spanned.getSpans(0, spanned.length(), android/text/method/PasswordTransformationMethod$Visible);
                for(int k = 0; k < avisible.length; k++)
                {
                    if(spanned.getSpanStart(Visible._2D_get0(avisible[k])) < 0)
                        continue;
                    int i1 = spanned.getSpanStart(avisible[k]);
                    int j1 = spanned.getSpanEnd(avisible[k]);
                    if(i >= i1 && i < j1)
                        return mSource.charAt(i);
                }

            }
            return PasswordTransformationMethod._2D_get0();
        }

        public void getChars(int i, int j, char ac[], int k)
        {
            int l;
            int i1;
            int j1;
            int ai[];
            int ai1[];
            int l2;
            TextUtils.getChars(mSource, i, j, ac, k);
            l = -1;
            i1 = -1;
            j1 = 0;
            ai = null;
            ai1 = null;
            if(mSource instanceof Spanned)
            {
                Spanned spanned = (Spanned)mSource;
                int k1 = spanned.getSpanStart(TextKeyListener.ACTIVE);
                int l1 = spanned.getSpanEnd(TextKeyListener.ACTIVE);
                Visible avisible[] = (Visible[])spanned.getSpans(0, spanned.length(), android/text/method/PasswordTransformationMethod$Visible);
                int j2 = avisible.length;
                int ai2[] = new int[j2];
                int ai3[] = new int[j2];
                int k2 = 0;
                do
                {
                    i1 = l1;
                    ai1 = ai3;
                    j1 = j2;
                    l = k1;
                    ai = ai2;
                    if(k2 >= j2)
                        break;
                    if(spanned.getSpanStart(Visible._2D_get0(avisible[k2])) >= 0)
                    {
                        ai2[k2] = spanned.getSpanStart(avisible[k2]);
                        ai3[k2] = spanned.getSpanEnd(avisible[k2]);
                    }
                    k2++;
                } while(true);
            }
            l2 = i;
_L11:
            if(l2 >= j) goto _L2; else goto _L1
_L1:
            if(l2 >= l && l2 < i1) goto _L4; else goto _L3
_L3:
            boolean flag;
            int i2;
            flag = false;
            i2 = 0;
_L9:
            boolean flag1 = flag;
            if(i2 >= j1) goto _L6; else goto _L5
_L5:
            if(l2 < ai[i2] || l2 >= ai1[i2]) goto _L8; else goto _L7
_L7:
            flag1 = true;
_L6:
            if(!flag1)
                ac[(l2 - i) + k] = PasswordTransformationMethod._2D_get0();
_L4:
            l2++;
            continue; /* Loop/switch isn't completed */
_L8:
            i2++;
            if(true) goto _L9; else goto _L2
_L2:
            return;
            if(true) goto _L11; else goto _L10
_L10:
        }

        public int length()
        {
            return mSource.length();
        }

        public CharSequence subSequence(int i, int j)
        {
            char ac[] = new char[j - i];
            getChars(i, j, ac, 0);
            return new String(ac);
        }

        public String toString()
        {
            return subSequence(0, length()).toString();
        }

        private CharSequence mSource;

        public PasswordCharSequence(CharSequence charsequence)
        {
            mSource = charsequence;
        }
    }

    private static class ViewReference extends WeakReference
        implements NoCopySpan
    {

        public ViewReference(View view)
        {
            super(view);
        }
    }

    private static class Visible extends Handler
        implements UpdateLayout, Runnable
    {

        static PasswordTransformationMethod _2D_get0(Visible visible)
        {
            return visible.mTransformer;
        }

        public void run()
        {
            mText.removeSpan(this);
        }

        private Spannable mText;
        private PasswordTransformationMethod mTransformer;

        public Visible(Spannable spannable, PasswordTransformationMethod passwordtransformationmethod)
        {
            mText = spannable;
            mTransformer = passwordtransformationmethod;
            postAtTime(this, SystemClock.uptimeMillis() + 1500L);
        }
    }


    static char _2D_get0()
    {
        return DOT;
    }

    public PasswordTransformationMethod()
    {
    }

    public static PasswordTransformationMethod getInstance()
    {
        if(sInstance != null)
        {
            return sInstance;
        } else
        {
            sInstance = new PasswordTransformationMethod();
            return sInstance;
        }
    }

    private static void removeVisibleSpans(Spannable spannable)
    {
        Visible avisible[] = (Visible[])spannable.getSpans(0, spannable.length(), android/text/method/PasswordTransformationMethod$Visible);
        for(int i = 0; i < avisible.length; i++)
            spannable.removeSpan(avisible[i]);

    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public CharSequence getTransformation(CharSequence charsequence, View view)
    {
        if(charsequence instanceof Spannable)
        {
            Spannable spannable = (Spannable)charsequence;
            ViewReference aviewreference[] = (ViewReference[])spannable.getSpans(0, spannable.length(), android/text/method/PasswordTransformationMethod$ViewReference);
            for(int i = 0; i < aviewreference.length; i++)
                spannable.removeSpan(aviewreference[i]);

            removeVisibleSpans(spannable);
            spannable.setSpan(new ViewReference(view), 0, 0, 34);
        }
        return new PasswordCharSequence(charsequence);
    }

    public void onFocusChanged(View view, CharSequence charsequence, boolean flag, int i, Rect rect)
    {
        if(!flag && (charsequence instanceof Spannable))
            removeVisibleSpans((Spannable)charsequence);
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(charsequence instanceof Spannable)
        {
            Spannable spannable = (Spannable)charsequence;
            ViewReference aviewreference[] = (ViewReference[])spannable.getSpans(0, charsequence.length(), android/text/method/PasswordTransformationMethod$ViewReference);
            if(aviewreference.length == 0)
                return;
            charsequence = null;
            for(j = 0; charsequence == null && j < aviewreference.length; j++)
                charsequence = (View)aviewreference[j].get();

            if(charsequence == null)
                return;
            if((TextKeyListener.getInstance().getPrefs(charsequence.getContext()) & 8) != 0 && k > 0)
            {
                removeVisibleSpans(spannable);
                if(k == 1)
                    spannable.setSpan(new Visible(spannable, this), i, i + k, 33);
            }
        }
    }

    private static char DOT = (char)8226;
    private static PasswordTransformationMethod sInstance;

}
