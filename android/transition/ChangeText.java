// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, TransitionListenerAdapter

public class ChangeText extends Transition
{

    static int _2D_get0(ChangeText changetext)
    {
        return changetext.mChangeBehavior;
    }

    static void _2D_wrap0(ChangeText changetext, EditText edittext, int i, int j)
    {
        changetext.setSelection(edittext, i, j);
    }

    public ChangeText()
    {
        mChangeBehavior = 0;
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        if(transitionvalues.view instanceof TextView)
        {
            TextView textview = (TextView)transitionvalues.view;
            transitionvalues.values.put("android:textchange:text", textview.getText());
            if(textview instanceof EditText)
            {
                transitionvalues.values.put("android:textchange:textSelectionStart", Integer.valueOf(textview.getSelectionStart()));
                transitionvalues.values.put("android:textchange:textSelectionEnd", Integer.valueOf(textview.getSelectionEnd()));
            }
            if(mChangeBehavior > 0)
                transitionvalues.values.put("android:textchange:textColor", Integer.valueOf(textview.getCurrentTextColor()));
        }
    }

    private void setSelection(EditText edittext, int i, int j)
    {
        if(i >= 0 && j >= 0)
            edittext.setSelection(i, j);
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, final TransitionValues startText)
    {
        final TextView view;
        final Object endText;
        final int endSelectionStart;
        final int endSelectionEnd;
        final int endColor;
        while(transitionvalues == null || startText == null || (transitionvalues.view instanceof TextView) ^ true || (startText.view instanceof TextView) ^ true) 
            return null;
        view = (TextView)startText.view;
        viewgroup = transitionvalues.values;
        transitionvalues = startText.values;
        final int startSelectionStart;
        final int startSelectionEnd;
        if(viewgroup.get("android:textchange:text") != null)
            startText = (CharSequence)viewgroup.get("android:textchange:text");
        else
            startText = "";
        if(transitionvalues.get("android:textchange:text") != null)
            endText = (CharSequence)transitionvalues.get("android:textchange:text");
        else
            endText = "";
        if(view instanceof EditText)
        {
            if(viewgroup.get("android:textchange:textSelectionStart") != null)
                startSelectionStart = ((Integer)viewgroup.get("android:textchange:textSelectionStart")).intValue();
            else
                startSelectionStart = -1;
            if(viewgroup.get("android:textchange:textSelectionEnd") != null)
                startSelectionEnd = ((Integer)viewgroup.get("android:textchange:textSelectionEnd")).intValue();
            else
                startSelectionEnd = startSelectionStart;
            if(transitionvalues.get("android:textchange:textSelectionStart") != null)
                endSelectionStart = ((Integer)transitionvalues.get("android:textchange:textSelectionStart")).intValue();
            else
                endSelectionStart = -1;
            if(transitionvalues.get("android:textchange:textSelectionEnd") != null)
                endSelectionEnd = ((Integer)transitionvalues.get("android:textchange:textSelectionEnd")).intValue();
            else
                endSelectionEnd = endSelectionStart;
        } else
        {
            endSelectionEnd = -1;
            endSelectionStart = -1;
            startSelectionEnd = -1;
            startSelectionStart = -1;
        }
        if(startText.equals(endText)) goto _L2; else goto _L1
_L1:
        if(mChangeBehavior != 2)
        {
            view.setText(startText);
            if(view instanceof EditText)
                setSelection((EditText)view, startSelectionStart, startSelectionEnd);
        }
        if(mChangeBehavior != 0) goto _L4; else goto _L3
_L3:
        endColor = 0;
        viewgroup = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        viewgroup.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(startText.equals(view.getText()))
                {
                    view.setText(endText);
                    if(view instanceof EditText)
                        ChangeText._2D_wrap0(ChangeText.this, (EditText)view, endSelectionStart, endSelectionEnd);
                }
            }

            final ChangeText this$0;
            final int val$endSelectionEnd;
            final int val$endSelectionStart;
            final CharSequence val$endText;
            final CharSequence val$startText;
            final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                startText = charsequence;
                view = textview;
                endText = charsequence1;
                endSelectionStart = i;
                endSelectionEnd = j;
                super();
            }
        }
);
_L5:
        addListener(new TransitionListenerAdapter() {

            public void onTransitionEnd(Transition transition)
            {
                transition.removeListener(this);
            }

            public void onTransitionPause(Transition transition)
            {
                if(ChangeText._2D_get0(ChangeText.this) != 2)
                {
                    view.setText(endText);
                    if(view instanceof EditText)
                        ChangeText._2D_wrap0(ChangeText.this, (EditText)view, endSelectionStart, endSelectionEnd);
                }
                if(ChangeText._2D_get0(ChangeText.this) > 0)
                {
                    mPausedColor = view.getCurrentTextColor();
                    view.setTextColor(endColor);
                }
            }

            public void onTransitionResume(Transition transition)
            {
                if(ChangeText._2D_get0(ChangeText.this) != 2)
                {
                    view.setText(startText);
                    if(view instanceof EditText)
                        ChangeText._2D_wrap0(ChangeText.this, (EditText)view, startSelectionStart, startSelectionEnd);
                }
                if(ChangeText._2D_get0(ChangeText.this) > 0)
                    view.setTextColor(mPausedColor);
            }

            int mPausedColor;
            final ChangeText this$0;
            final int val$endColor;
            final int val$endSelectionEnd;
            final int val$endSelectionStart;
            final CharSequence val$endText;
            final int val$startSelectionEnd;
            final int val$startSelectionStart;
            final CharSequence val$startText;
            final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                view = textview;
                endText = charsequence;
                endSelectionStart = i;
                endSelectionEnd = j;
                endColor = k;
                startText = charsequence1;
                startSelectionStart = l;
                startSelectionEnd = i1;
                super();
                mPausedColor = 0;
            }
        }
);
        return viewgroup;
_L4:
        final int startColor = ((Integer)viewgroup.get("android:textchange:textColor")).intValue();
        endColor = ((Integer)transitionvalues.get("android:textchange:textColor")).intValue();
        viewgroup = null;
        transitionvalues = null;
        if(mChangeBehavior == 3 || mChangeBehavior == 1)
        {
            viewgroup = ValueAnimator.ofInt(new int[] {
                Color.alpha(startColor), 0
            });
            viewgroup.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

                public void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
                    view.setTextColor(i << 24 | startColor & 0xffffff);
                }

                final ChangeText this$0;
                final int val$startColor;
                final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                view = textview;
                startColor = i;
                super();
            }
            }
);
            viewgroup.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    if(startText.equals(view.getText()))
                    {
                        view.setText(endText);
                        if(view instanceof EditText)
                            ChangeText._2D_wrap0(ChangeText.this, (EditText)view, endSelectionStart, endSelectionEnd);
                    }
                    view.setTextColor(endColor);
                }

                final ChangeText this$0;
                final int val$endColor;
                final int val$endSelectionEnd;
                final int val$endSelectionStart;
                final CharSequence val$endText;
                final CharSequence val$startText;
                final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                startText = charsequence;
                view = textview;
                endText = charsequence1;
                endSelectionStart = i;
                endSelectionEnd = j;
                endColor = k;
                super();
            }
            }
);
        }
        if(mChangeBehavior == 3 || mChangeBehavior == 2)
        {
            transitionvalues = ValueAnimator.ofInt(new int[] {
                0, Color.alpha(endColor)
            });
            transitionvalues.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

                public void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
                    view.setTextColor(i << 24 | endColor & 0xffffff);
                }

                final ChangeText this$0;
                final int val$endColor;
                final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                view = textview;
                endColor = i;
                super();
            }
            }
);
            transitionvalues.addListener(new AnimatorListenerAdapter() {

                public void onAnimationCancel(Animator animator)
                {
                    view.setTextColor(endColor);
                }

                final ChangeText this$0;
                final int val$endColor;
                final TextView val$view;

            
            {
                this$0 = ChangeText.this;
                view = textview;
                endColor = i;
                super();
            }
            }
);
        }
        if(viewgroup != null && transitionvalues != null)
        {
            AnimatorSet animatorset = new AnimatorSet();
            ((AnimatorSet)animatorset).playSequentially(new Animator[] {
                viewgroup, transitionvalues
            });
            viewgroup = animatorset;
        } else
        if(viewgroup == null)
            viewgroup = transitionvalues;
        if(true) goto _L5; else goto _L2
_L2:
        return null;
    }

    public int getChangeBehavior()
    {
        return mChangeBehavior;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public ChangeText setChangeBehavior(int i)
    {
        if(i >= 0 && i <= 3)
            mChangeBehavior = i;
        return this;
    }

    public static final int CHANGE_BEHAVIOR_IN = 2;
    public static final int CHANGE_BEHAVIOR_KEEP = 0;
    public static final int CHANGE_BEHAVIOR_OUT = 1;
    public static final int CHANGE_BEHAVIOR_OUT_IN = 3;
    private static final String LOG_TAG = "TextChange";
    private static final String PROPNAME_TEXT = "android:textchange:text";
    private static final String PROPNAME_TEXT_COLOR = "android:textchange:textColor";
    private static final String PROPNAME_TEXT_SELECTION_END = "android:textchange:textSelectionEnd";
    private static final String PROPNAME_TEXT_SELECTION_START = "android:textchange:textSelectionStart";
    private static final String sTransitionProperties[] = {
        "android:textchange:text", "android:textchange:textSelectionStart", "android:textchange:textSelectionEnd"
    };
    private int mChangeBehavior;

}
