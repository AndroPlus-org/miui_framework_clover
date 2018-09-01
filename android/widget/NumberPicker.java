// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            LinearLayout, ImageButton, EditText, Scroller, 
//            TextView, Button

public class NumberPicker extends LinearLayout
{
    class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider
    {

        private AccessibilityNodeInfo createAccessibilityNodeInfoForNumberPicker(int i, int j, int k, int l)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain();
            accessibilitynodeinfo.setClassName(android/widget/NumberPicker.getName());
            accessibilitynodeinfo.setPackageName(NumberPicker._2D_get3(NumberPicker.this).getPackageName());
            accessibilitynodeinfo.setSource(NumberPicker.this);
            if(hasVirtualDecrementButton())
                accessibilitynodeinfo.addChild(NumberPicker.this, 3);
            accessibilitynodeinfo.addChild(NumberPicker.this, 2);
            if(hasVirtualIncrementButton())
                accessibilitynodeinfo.addChild(NumberPicker.this, 1);
            accessibilitynodeinfo.setParent((View)getParentForAccessibility());
            accessibilitynodeinfo.setEnabled(isEnabled());
            accessibilitynodeinfo.setScrollable(true);
            float f = getContext().getResources().getCompatibilityInfo().applicationScale;
            Rect rect = mTempRect;
            rect.set(i, j, k, l);
            rect.scale(f);
            accessibilitynodeinfo.setBoundsInParent(rect);
            accessibilitynodeinfo.setVisibleToUser(NumberPicker._2D_wrap0(NumberPicker.this));
            int ai[] = mTempArray;
            getLocationOnScreen(ai);
            rect.offset(ai[0], ai[1]);
            rect.scale(f);
            accessibilitynodeinfo.setBoundsInScreen(rect);
            if(mAccessibilityFocusedView != -1)
                accessibilitynodeinfo.addAction(64);
            if(mAccessibilityFocusedView == -1)
                accessibilitynodeinfo.addAction(128);
            if(isEnabled())
            {
                if(getWrapSelectorWheel() || getValue() < getMaxValue())
                    accessibilitynodeinfo.addAction(4096);
                if(getWrapSelectorWheel() || getValue() > getMinValue())
                    accessibilitynodeinfo.addAction(8192);
            }
            return accessibilitynodeinfo;
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForVirtualButton(int i, String s, int j, int k, int l, int i1)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain();
            accessibilitynodeinfo.setClassName(android/widget/Button.getName());
            accessibilitynodeinfo.setPackageName(NumberPicker._2D_get3(NumberPicker.this).getPackageName());
            accessibilitynodeinfo.setSource(NumberPicker.this, i);
            accessibilitynodeinfo.setParent(NumberPicker.this);
            accessibilitynodeinfo.setText(s);
            accessibilitynodeinfo.setClickable(true);
            accessibilitynodeinfo.setLongClickable(true);
            accessibilitynodeinfo.setEnabled(isEnabled());
            Rect rect = mTempRect;
            rect.set(j, k, l, i1);
            accessibilitynodeinfo.setVisibleToUser(NumberPicker._2D_wrap1(NumberPicker.this, rect));
            accessibilitynodeinfo.setBoundsInParent(rect);
            s = mTempArray;
            getLocationOnScreen(s);
            rect.offset(s[0], s[1]);
            accessibilitynodeinfo.setBoundsInScreen(rect);
            if(mAccessibilityFocusedView != i)
                accessibilitynodeinfo.addAction(64);
            if(mAccessibilityFocusedView == i)
                accessibilitynodeinfo.addAction(128);
            if(isEnabled())
                accessibilitynodeinfo.addAction(16);
            return accessibilitynodeinfo;
        }

        private AccessibilityNodeInfo createAccessibiltyNodeInfoForInputText(int i, int j, int k, int l)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = NumberPicker._2D_get7(NumberPicker.this).createAccessibilityNodeInfo();
            accessibilitynodeinfo.setSource(NumberPicker.this, 2);
            if(mAccessibilityFocusedView != 2)
                accessibilitynodeinfo.addAction(64);
            if(mAccessibilityFocusedView == 2)
                accessibilitynodeinfo.addAction(128);
            Rect rect = mTempRect;
            rect.set(i, j, k, l);
            accessibilitynodeinfo.setVisibleToUser(NumberPicker._2D_wrap1(NumberPicker.this, rect));
            accessibilitynodeinfo.setBoundsInParent(rect);
            int ai[] = mTempArray;
            getLocationOnScreen(ai);
            rect.offset(ai[0], ai[1]);
            accessibilitynodeinfo.setBoundsInScreen(rect);
            return accessibilitynodeinfo;
        }

        private void findAccessibilityNodeInfosByTextInChild(String s, int i, List list)
        {
            i;
            JVM INSTR tableswitch 1 3: default 28
        //                       1 171
        //                       2 71
        //                       3 29;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L4:
            String s1 = getVirtualDecrementButtonText();
            if(!TextUtils.isEmpty(s1) && s1.toString().toLowerCase().contains(s))
                list.add(createAccessibilityNodeInfo(3));
            return;
_L3:
            android.text.Editable editable = NumberPicker._2D_get7(NumberPicker.this).getText();
            if(!TextUtils.isEmpty(editable) && editable.toString().toLowerCase().contains(s))
            {
                list.add(createAccessibilityNodeInfo(2));
                return;
            }
            editable = NumberPicker._2D_get7(NumberPicker.this).getText();
            if(!TextUtils.isEmpty(editable) && editable.toString().toLowerCase().contains(s))
            {
                list.add(createAccessibilityNodeInfo(2));
                return;
            }
              goto _L1
_L2:
            String s2 = getVirtualIncrementButtonText();
            if(!TextUtils.isEmpty(s2) && s2.toString().toLowerCase().contains(s))
                list.add(createAccessibilityNodeInfo(1));
            return;
        }

        private String getVirtualDecrementButtonText()
        {
            int i = NumberPicker._2D_get19(NumberPicker.this) - 1;
            int j = i;
            if(NumberPicker._2D_get20(NumberPicker.this))
                j = NumberPicker._2D_wrap3(NumberPicker.this, i);
            if(j >= NumberPicker._2D_get11(NumberPicker.this))
            {
                String s;
                if(NumberPicker._2D_get5(NumberPicker.this) == null)
                    s = NumberPicker._2D_wrap4(NumberPicker.this, j);
                else
                    s = NumberPicker._2D_get5(NumberPicker.this)[j - NumberPicker._2D_get11(NumberPicker.this)];
                return s;
            } else
            {
                return null;
            }
        }

        private String getVirtualIncrementButtonText()
        {
            int i = NumberPicker._2D_get19(NumberPicker.this) + 1;
            int j = i;
            if(NumberPicker._2D_get20(NumberPicker.this))
                j = NumberPicker._2D_wrap3(NumberPicker.this, i);
            if(j <= NumberPicker._2D_get10(NumberPicker.this))
            {
                String s;
                if(NumberPicker._2D_get5(NumberPicker.this) == null)
                    s = NumberPicker._2D_wrap4(NumberPicker.this, j);
                else
                    s = NumberPicker._2D_get5(NumberPicker.this)[j - NumberPicker._2D_get11(NumberPicker.this)];
                return s;
            } else
            {
                return null;
            }
        }

        private boolean hasVirtualDecrementButton()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(!getWrapSelectorWheel())
                if(getValue() > getMinValue())
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        private boolean hasVirtualIncrementButton()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(!getWrapSelectorWheel())
                if(getValue() < getMaxValue())
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        private void sendAccessibilityEventForVirtualButton(int i, int j, String s)
        {
            if(AccessibilityManager.getInstance(NumberPicker._2D_get3(NumberPicker.this)).isEnabled())
            {
                AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
                accessibilityevent.setClassName(android/widget/Button.getName());
                accessibilityevent.setPackageName(NumberPicker._2D_get3(NumberPicker.this).getPackageName());
                accessibilityevent.getText().add(s);
                accessibilityevent.setEnabled(isEnabled());
                accessibilityevent.setSource(NumberPicker.this, i);
                requestSendAccessibilityEvent(NumberPicker.this, accessibilityevent);
            }
        }

        private void sendAccessibilityEventForVirtualText(int i)
        {
            if(AccessibilityManager.getInstance(NumberPicker._2D_get3(NumberPicker.this)).isEnabled())
            {
                AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(i);
                NumberPicker._2D_get7(NumberPicker.this).onInitializeAccessibilityEvent(accessibilityevent);
                NumberPicker._2D_get7(NumberPicker.this).onPopulateAccessibilityEvent(accessibilityevent);
                accessibilityevent.setSource(NumberPicker.this, 2);
                requestSendAccessibilityEvent(NumberPicker.this, accessibilityevent);
            }
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i)
        {
            switch(i)
            {
            case 0: // '\0'
            default:
                return super.createAccessibilityNodeInfo(i);

            case -1: 
                return createAccessibilityNodeInfoForNumberPicker(NumberPicker._2D_get13(NumberPicker.this), NumberPicker._2D_get14(NumberPicker.this), NumberPicker._2D_get13(NumberPicker.this) + (NumberPicker._2D_get12(NumberPicker.this) - NumberPicker._2D_get8(NumberPicker.this)), NumberPicker._2D_get14(NumberPicker.this) + (NumberPicker._2D_get1(NumberPicker.this) - NumberPicker._2D_get17(NumberPicker.this)));

            case 3: // '\003'
                return createAccessibilityNodeInfoForVirtualButton(3, getVirtualDecrementButtonText(), NumberPicker._2D_get13(NumberPicker.this), NumberPicker._2D_get14(NumberPicker.this), NumberPicker._2D_get13(NumberPicker.this) + (NumberPicker._2D_get12(NumberPicker.this) - NumberPicker._2D_get8(NumberPicker.this)), NumberPicker._2D_get18(NumberPicker.this) + NumberPicker._2D_get15(NumberPicker.this));

            case 2: // '\002'
                return createAccessibiltyNodeInfoForInputText(NumberPicker._2D_get13(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this) + NumberPicker._2D_get15(NumberPicker.this), NumberPicker._2D_get13(NumberPicker.this) + (NumberPicker._2D_get12(NumberPicker.this) - NumberPicker._2D_get8(NumberPicker.this)), NumberPicker._2D_get2(NumberPicker.this) - NumberPicker._2D_get15(NumberPicker.this));

            case 1: // '\001'
                return createAccessibilityNodeInfoForVirtualButton(1, getVirtualIncrementButtonText(), NumberPicker._2D_get13(NumberPicker.this), NumberPicker._2D_get2(NumberPicker.this) - NumberPicker._2D_get15(NumberPicker.this), NumberPicker._2D_get13(NumberPicker.this) + (NumberPicker._2D_get12(NumberPicker.this) - NumberPicker._2D_get8(NumberPicker.this)), NumberPicker._2D_get14(NumberPicker.this) + (NumberPicker._2D_get1(NumberPicker.this) - NumberPicker._2D_get17(NumberPicker.this)));
            }
        }

        public List findAccessibilityNodeInfosByText(String s, int i)
        {
            if(TextUtils.isEmpty(s))
                return Collections.emptyList();
            String s1 = s.toLowerCase();
            ArrayList arraylist = new ArrayList();
            switch(i)
            {
            case 0: // '\0'
            default:
                return super.findAccessibilityNodeInfosByText(s, i);

            case -1: 
                findAccessibilityNodeInfosByTextInChild(s1, 3, arraylist);
                findAccessibilityNodeInfosByTextInChild(s1, 2, arraylist);
                findAccessibilityNodeInfosByTextInChild(s1, 1, arraylist);
                return arraylist;

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                findAccessibilityNodeInfosByTextInChild(s1, i, arraylist);
                break;
            }
            return arraylist;
        }

        public boolean performAction(int i, int j, Bundle bundle)
        {
            i;
            JVM INSTR tableswitch -1 3: default 36
        //                       -1 44
        //                       0 36
        //                       1 503
        //                       2 239
        //                       3 679;
               goto _L1 _L2 _L1 _L3 _L4 _L5
_L7:
            return super.performAction(i, j, bundle);
_L2:
            switch(j)
            {
            default:
                break;

            case 8192: 
                break; /* Loop/switch isn't completed */

            case 64: // '@'
                if(mAccessibilityFocusedView != i)
                {
                    mAccessibilityFocusedView = i;
                    requestAccessibilityFocus();
                    return true;
                } else
                {
                    return false;
                }

            case 128: 
                if(mAccessibilityFocusedView == i)
                {
                    mAccessibilityFocusedView = 0x80000000;
                    clearAccessibilityFocus();
                    return true;
                } else
                {
                    return false;
                }

            case 4096: 
                if(isEnabled() && (getWrapSelectorWheel() || getValue() < getMaxValue()))
                {
                    NumberPicker._2D_wrap5(NumberPicker.this, true);
                    return true;
                } else
                {
                    return false;
                }
            }
_L1:
            if(true) goto _L7; else goto _L6
_L6:
            if(isEnabled() && (getWrapSelectorWheel() || getValue() > getMinValue()))
            {
                NumberPicker._2D_wrap5(NumberPicker.this, false);
                return true;
            } else
            {
                return false;
            }
_L4:
            switch(j)
            {
            default:
                return NumberPicker._2D_get7(NumberPicker.this).performAccessibilityAction(j, bundle);

            case 1: // '\001'
                if(isEnabled() && NumberPicker._2D_get7(NumberPicker.this).isFocused() ^ true)
                    return NumberPicker._2D_get7(NumberPicker.this).requestFocus();
                else
                    return false;

            case 2: // '\002'
                if(isEnabled() && NumberPicker._2D_get7(NumberPicker.this).isFocused())
                {
                    NumberPicker._2D_get7(NumberPicker.this).clearFocus();
                    return true;
                } else
                {
                    return false;
                }

            case 16: // '\020'
                if(isEnabled())
                {
                    performClick();
                    return true;
                } else
                {
                    return false;
                }

            case 32: // ' '
                if(isEnabled())
                {
                    performLongClick();
                    return true;
                } else
                {
                    return false;
                }

            case 64: // '@'
                if(mAccessibilityFocusedView != i)
                {
                    mAccessibilityFocusedView = i;
                    sendAccessibilityEventForVirtualView(i, 32768);
                    NumberPicker._2D_get7(NumberPicker.this).invalidate();
                    return true;
                } else
                {
                    return false;
                }

            case 128: 
                break;
            }
            if(mAccessibilityFocusedView == i)
            {
                mAccessibilityFocusedView = 0x80000000;
                sendAccessibilityEventForVirtualView(i, 0x10000);
                NumberPicker._2D_get7(NumberPicker.this).invalidate();
                return true;
            } else
            {
                return false;
            }
_L3:
            switch(j)
            {
            default:
                return false;

            case 16: // '\020'
                if(isEnabled())
                {
                    NumberPicker._2D_wrap5(NumberPicker.this, true);
                    sendAccessibilityEventForVirtualView(i, 1);
                    return true;
                } else
                {
                    return false;
                }

            case 64: // '@'
                if(mAccessibilityFocusedView != i)
                {
                    mAccessibilityFocusedView = i;
                    sendAccessibilityEventForVirtualView(i, 32768);
                    invalidate(0, NumberPicker._2D_get2(NumberPicker.this), NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get1(NumberPicker.this));
                    return true;
                } else
                {
                    return false;
                }

            case 128: 
                break;
            }
            if(mAccessibilityFocusedView == i)
            {
                mAccessibilityFocusedView = 0x80000000;
                sendAccessibilityEventForVirtualView(i, 0x10000);
                invalidate(0, NumberPicker._2D_get2(NumberPicker.this), NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get1(NumberPicker.this));
                return true;
            } else
            {
                return false;
            }
_L5:
            switch(j)
            {
            default:
                return false;

            case 16: // '\020'
                if(isEnabled())
                {
                    boolean flag;
                    if(i == 1)
                        flag = true;
                    else
                        flag = false;
                    NumberPicker._2D_wrap5(NumberPicker.this, flag);
                    sendAccessibilityEventForVirtualView(i, 1);
                    return true;
                } else
                {
                    return false;
                }

            case 64: // '@'
                if(mAccessibilityFocusedView != i)
                {
                    mAccessibilityFocusedView = i;
                    sendAccessibilityEventForVirtualView(i, 32768);
                    invalidate(0, 0, NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this));
                    return true;
                } else
                {
                    return false;
                }

            case 128: 
                break;
            }
            if(mAccessibilityFocusedView == i)
            {
                mAccessibilityFocusedView = 0x80000000;
                sendAccessibilityEventForVirtualView(i, 0x10000);
                invalidate(0, 0, NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this));
                return true;
            } else
            {
                return false;
            }
        }

        public void sendAccessibilityEventForVirtualView(int i, int j)
        {
            i;
            JVM INSTR tableswitch 1 3: default 28
        //                       1 57
        //                       2 49
        //                       3 29;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L4:
            if(hasVirtualDecrementButton())
                sendAccessibilityEventForVirtualButton(i, j, getVirtualDecrementButtonText());
            continue; /* Loop/switch isn't completed */
_L3:
            sendAccessibilityEventForVirtualText(j);
            continue; /* Loop/switch isn't completed */
_L2:
            if(hasVirtualIncrementButton())
                sendAccessibilityEventForVirtualButton(i, j, getVirtualIncrementButtonText());
            if(true) goto _L1; else goto _L5
_L5:
        }

        private static final int UNDEFINED = 0x80000000;
        private static final int VIRTUAL_VIEW_ID_DECREMENT = 3;
        private static final int VIRTUAL_VIEW_ID_INCREMENT = 1;
        private static final int VIRTUAL_VIEW_ID_INPUT = 2;
        private int mAccessibilityFocusedView;
        private final int mTempArray[] = new int[2];
        private final Rect mTempRect = new Rect();
        final NumberPicker this$0;

        AccessibilityNodeProviderImpl()
        {
            this$0 = NumberPicker.this;
            super();
            mAccessibilityFocusedView = 0x80000000;
        }
    }

    class BeginSoftInputOnLongPressCommand
        implements Runnable
    {

        public void run()
        {
            performLongClick();
        }

        final NumberPicker this$0;

        BeginSoftInputOnLongPressCommand()
        {
            this$0 = NumberPicker.this;
            super();
        }
    }

    class ChangeCurrentByOneFromLongPressCommand
        implements Runnable
    {

        static void _2D_wrap0(ChangeCurrentByOneFromLongPressCommand changecurrentbyonefromlongpresscommand, boolean flag)
        {
            changecurrentbyonefromlongpresscommand.setStep(flag);
        }

        private void setStep(boolean flag)
        {
            mIncrement = flag;
        }

        public void run()
        {
            NumberPicker._2D_wrap5(NumberPicker.this, mIncrement);
            postDelayed(this, NumberPicker._2D_get9(NumberPicker.this));
        }

        private boolean mIncrement;
        final NumberPicker this$0;

        ChangeCurrentByOneFromLongPressCommand()
        {
            this$0 = NumberPicker.this;
            super();
        }
    }

    public static class CustomEditText extends EditText
    {

        public void onEditorAction(int i)
        {
            super.onEditorAction(i);
            if(i == 6)
                clearFocus();
        }

        public CustomEditText(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static interface Formatter
    {

        public abstract String format(int i);
    }

    class InputTextFilter extends NumberKeyListener
    {

        public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
        {
            if(NumberPicker._2D_get16(NumberPicker.this) != null)
                NumberPicker._2D_get16(NumberPicker.this).cancel();
            if(NumberPicker._2D_get5(NumberPicker.this) == null)
            {
                CharSequence charsequence1 = super.filter(charsequence, i, j, spanned, k, l);
                CharSequence charsequence2 = charsequence1;
                if(charsequence1 == null)
                    charsequence2 = charsequence.subSequence(i, j);
                charsequence = (new StringBuilder()).append(String.valueOf(spanned.subSequence(0, k))).append(charsequence2).append(spanned.subSequence(l, spanned.length())).toString();
                if("".equals(charsequence))
                    return charsequence;
                if(NumberPicker._2D_wrap2(NumberPicker.this, charsequence) > NumberPicker._2D_get10(NumberPicker.this) || charsequence.length() > String.valueOf(NumberPicker._2D_get10(NumberPicker.this)).length())
                    return "";
                else
                    return charsequence2;
            }
            charsequence = String.valueOf(charsequence.subSequence(i, j));
            if(TextUtils.isEmpty(charsequence))
                return "";
            spanned = (new StringBuilder()).append(String.valueOf(spanned.subSequence(0, k))).append(charsequence).append(spanned.subSequence(l, spanned.length())).toString();
            String s = String.valueOf(spanned).toLowerCase();
            String as[] = NumberPicker._2D_get5(NumberPicker.this);
            i = 0;
            for(j = as.length; i < j; i++)
            {
                charsequence = as[i];
                if(charsequence.toLowerCase().startsWith(s))
                {
                    NumberPicker._2D_wrap8(NumberPicker.this, spanned.length(), charsequence.length());
                    return charsequence.subSequence(k, charsequence.length());
                }
            }

            return "";
        }

        protected char[] getAcceptedChars()
        {
            return NumberPicker._2D_get0();
        }

        public int getInputType()
        {
            return 1;
        }

        final NumberPicker this$0;

        InputTextFilter()
        {
            this$0 = NumberPicker.this;
            super();
        }
    }

    public static interface OnScrollListener
    {

        public abstract void onScrollStateChange(NumberPicker numberpicker, int i);

        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;
    }

    public static interface OnValueChangeListener
    {

        public abstract void onValueChange(NumberPicker numberpicker, int i, int j);
    }

    class PressedStateHelper
        implements Runnable
    {

        public void buttonPressDelayed(int i)
        {
            cancel();
            mMode = 1;
            mManagedButton = i;
            postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int i)
        {
            cancel();
            mMode = 2;
            mManagedButton = i;
            post(this);
        }

        public void cancel()
        {
            mMode = 0;
            mManagedButton = 0;
            removeCallbacks(this);
            if(NumberPicker._2D_get6(NumberPicker.this))
            {
                NumberPicker._2D_set1(NumberPicker.this, false);
                invalidate(0, NumberPicker._2D_get2(NumberPicker.this), NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get1(NumberPicker.this));
            }
            NumberPicker._2D_set0(NumberPicker.this, false);
            if(NumberPicker._2D_get4(NumberPicker.this))
                invalidate(0, 0, NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this));
        }

        public void run()
        {
            mMode;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 135;
               goto _L1 _L2 _L3
_L5:
            return;
_L2:
            switch(mManagedButton)
            {
            case 1: // '\001'
                NumberPicker._2D_set1(NumberPicker.this, true);
                invalidate(0, NumberPicker._2D_get2(NumberPicker.this), NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get1(NumberPicker.this));
                break;

            case 2: // '\002'
                NumberPicker._2D_set0(NumberPicker.this, true);
                invalidate(0, 0, NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this));
                break;
            }
_L1:
            if(true) goto _L5; else goto _L4
_L4:
_L3:
            switch(mManagedButton)
            {
            case 1: // '\001'
                if(!NumberPicker._2D_get6(NumberPicker.this))
                    postDelayed(this, ViewConfiguration.getPressedStateDuration());
                NumberPicker numberpicker = NumberPicker.this;
                NumberPicker._2D_set1(numberpicker, NumberPicker._2D_get6(numberpicker) ^ true);
                invalidate(0, NumberPicker._2D_get2(NumberPicker.this), NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get1(NumberPicker.this));
                break;

            case 2: // '\002'
                if(!NumberPicker._2D_get4(NumberPicker.this))
                    postDelayed(this, ViewConfiguration.getPressedStateDuration());
                NumberPicker numberpicker1 = NumberPicker.this;
                NumberPicker._2D_set0(numberpicker1, NumberPicker._2D_get4(numberpicker1) ^ true);
                invalidate(0, 0, NumberPicker._2D_get12(NumberPicker.this), NumberPicker._2D_get18(NumberPicker.this));
                break;
            }
            if(true) goto _L5; else goto _L6
_L6:
        }

        public static final int BUTTON_DECREMENT = 2;
        public static final int BUTTON_INCREMENT = 1;
        private final int MODE_PRESS = 1;
        private final int MODE_TAPPED = 2;
        private int mManagedButton;
        private int mMode;
        final NumberPicker this$0;

        PressedStateHelper()
        {
            this$0 = NumberPicker.this;
            super();
        }
    }

    private static class SetSelectionCommand
        implements Runnable
    {

        public void cancel()
        {
            if(mPosted)
            {
                mInputText.removeCallbacks(this);
                mPosted = false;
            }
        }

        public void post(int i, int j)
        {
            mSelectionStart = i;
            mSelectionEnd = j;
            if(!mPosted)
            {
                mInputText.post(this);
                mPosted = true;
            }
        }

        public void run()
        {
            mPosted = false;
            mInputText.setSelection(mSelectionStart, mSelectionEnd);
        }

        private final EditText mInputText;
        private boolean mPosted;
        private int mSelectionEnd;
        private int mSelectionStart;

        public SetSelectionCommand(EditText edittext)
        {
            mInputText = edittext;
        }
    }

    private static class TwoDigitFormatter
        implements Formatter
    {

        private java.util.Formatter createFormatter(Locale locale)
        {
            return new java.util.Formatter(mBuilder, locale);
        }

        private static char getZeroDigit(Locale locale)
        {
            return LocaleData.get(locale).zeroDigit;
        }

        private void init(Locale locale)
        {
            mFmt = createFormatter(locale);
            mZeroDigit = getZeroDigit(locale);
        }

        public String format(int i)
        {
            Locale locale = Locale.getDefault();
            if(mZeroDigit != getZeroDigit(locale))
                init(locale);
            mArgs[0] = Integer.valueOf(i);
            mBuilder.delete(0, mBuilder.length());
            mFmt.format("%02d", mArgs);
            return mFmt.toString();
        }

        final Object mArgs[] = new Object[1];
        final StringBuilder mBuilder = new StringBuilder();
        java.util.Formatter mFmt;
        char mZeroDigit;

        TwoDigitFormatter()
        {
            init(Locale.getDefault());
        }
    }


    static char[] _2D_get0()
    {
        return DIGIT_CHARACTERS;
    }

    static int _2D_get1(NumberPicker numberpicker)
    {
        return numberpicker.mBottom;
    }

    static int _2D_get10(NumberPicker numberpicker)
    {
        return numberpicker.mMaxValue;
    }

    static int _2D_get11(NumberPicker numberpicker)
    {
        return numberpicker.mMinValue;
    }

    static int _2D_get12(NumberPicker numberpicker)
    {
        return numberpicker.mRight;
    }

    static int _2D_get13(NumberPicker numberpicker)
    {
        return numberpicker.mScrollX;
    }

    static int _2D_get14(NumberPicker numberpicker)
    {
        return numberpicker.mScrollY;
    }

    static int _2D_get15(NumberPicker numberpicker)
    {
        return numberpicker.mSelectionDividerHeight;
    }

    static SetSelectionCommand _2D_get16(NumberPicker numberpicker)
    {
        return numberpicker.mSetSelectionCommand;
    }

    static int _2D_get17(NumberPicker numberpicker)
    {
        return numberpicker.mTop;
    }

    static int _2D_get18(NumberPicker numberpicker)
    {
        return numberpicker.mTopSelectionDividerTop;
    }

    static int _2D_get19(NumberPicker numberpicker)
    {
        return numberpicker.mValue;
    }

    static int _2D_get2(NumberPicker numberpicker)
    {
        return numberpicker.mBottomSelectionDividerBottom;
    }

    static boolean _2D_get20(NumberPicker numberpicker)
    {
        return numberpicker.mWrapSelectorWheel;
    }

    static Context _2D_get3(NumberPicker numberpicker)
    {
        return numberpicker.mContext;
    }

    static boolean _2D_get4(NumberPicker numberpicker)
    {
        return numberpicker.mDecrementVirtualButtonPressed;
    }

    static String[] _2D_get5(NumberPicker numberpicker)
    {
        return numberpicker.mDisplayedValues;
    }

    static boolean _2D_get6(NumberPicker numberpicker)
    {
        return numberpicker.mIncrementVirtualButtonPressed;
    }

    static EditText _2D_get7(NumberPicker numberpicker)
    {
        return numberpicker.mInputText;
    }

    static int _2D_get8(NumberPicker numberpicker)
    {
        return numberpicker.mLeft;
    }

    static long _2D_get9(NumberPicker numberpicker)
    {
        return numberpicker.mLongPressUpdateInterval;
    }

    static boolean _2D_set0(NumberPicker numberpicker, boolean flag)
    {
        numberpicker.mDecrementVirtualButtonPressed = flag;
        return flag;
    }

    static boolean _2D_set1(NumberPicker numberpicker, boolean flag)
    {
        numberpicker.mIncrementVirtualButtonPressed = flag;
        return flag;
    }

    static boolean _2D_wrap0(NumberPicker numberpicker)
    {
        return numberpicker.isVisibleToUser();
    }

    static boolean _2D_wrap1(NumberPicker numberpicker, Rect rect)
    {
        return numberpicker.isVisibleToUser(rect);
    }

    static int _2D_wrap2(NumberPicker numberpicker, String s)
    {
        return numberpicker.getSelectedPos(s);
    }

    static int _2D_wrap3(NumberPicker numberpicker, int i)
    {
        return numberpicker.getWrappedSelectorIndex(i);
    }

    static String _2D_wrap4(NumberPicker numberpicker, int i)
    {
        return numberpicker.formatNumber(i);
    }

    static void _2D_wrap5(NumberPicker numberpicker, boolean flag)
    {
        numberpicker.changeValueByOne(flag);
    }

    static void _2D_wrap6(NumberPicker numberpicker)
    {
        numberpicker.hideSoftInput();
    }

    static void _2D_wrap7(NumberPicker numberpicker, boolean flag, long l)
    {
        numberpicker.postChangeCurrentByOneFromLongPress(flag, l);
    }

    static void _2D_wrap8(NumberPicker numberpicker, int i, int j)
    {
        numberpicker.postSetSelectionCommand(i, j);
    }

    static void _2D_wrap9(NumberPicker numberpicker, View view)
    {
        numberpicker.validateInputTextView(view);
    }

    public NumberPicker(Context context)
    {
        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010524);
    }

    public NumberPicker(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public NumberPicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mWrapSelectorWheelPreferred = true;
        mLongPressUpdateInterval = 300L;
        mSelectorIndexToStringCache = new SparseArray();
        mSelectorIndices = new int[3];
        mInitialScrollOffset = 0x80000000;
        mScrollState = 0;
        mLastHandledDownDpadKeyCode = -1;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.NumberPicker, i, j);
        i = attributeset.getResourceId(2, 0x10900ad);
        boolean flag;
        Object obj;
        if(i != 0x10900ad)
            flag = true;
        else
            flag = false;
        mHasSelectorWheel = flag;
        mHideWheelUntilFocused = attributeset.getBoolean(1, false);
        mSolidColor = attributeset.getColor(0, 0);
        obj = attributeset.getDrawable(7);
        if(obj != null)
        {
            ((Drawable) (obj)).setCallback(this);
            ((Drawable) (obj)).setLayoutDirection(getLayoutDirection());
            if(((Drawable) (obj)).isStateful())
                ((Drawable) (obj)).setState(getDrawableState());
        }
        mSelectionDivider = ((Drawable) (obj));
        mSelectionDividerHeight = attributeset.getDimensionPixelSize(8, (int)TypedValue.applyDimension(1, 2.0F, getResources().getDisplayMetrics()));
        mSelectionDividersDistance = attributeset.getDimensionPixelSize(9, (int)TypedValue.applyDimension(1, 48F, getResources().getDisplayMetrics()));
        mMinHeight = attributeset.getDimensionPixelSize(5, -1);
        mMaxHeight = attributeset.getDimensionPixelSize(3, -1);
        if(mMinHeight != -1 && mMaxHeight != -1 && mMinHeight > mMaxHeight)
            throw new IllegalArgumentException("minHeight > maxHeight");
        mMinWidth = attributeset.getDimensionPixelSize(6, -1);
        mMaxWidth = attributeset.getDimensionPixelSize(4, -1);
        if(mMinWidth != -1 && mMaxWidth != -1 && mMinWidth > mMaxWidth)
            throw new IllegalArgumentException("minWidth > maxWidth");
        if(mMaxWidth == -1)
            flag = true;
        else
            flag = false;
        mComputeMaxWidth = flag;
        mVirtualButtonPressedDrawable = attributeset.getDrawable(10);
        attributeset.recycle();
        mPressedStateHelper = new PressedStateHelper();
        setWillNotDraw(mHasSelectorWheel ^ true);
        ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(i, this, true);
        attributeset = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                NumberPicker._2D_wrap6(NumberPicker.this);
                NumberPicker._2D_get7(NumberPicker.this).clearFocus();
                if(view.getId() == 0x10202a7)
                    NumberPicker._2D_wrap5(NumberPicker.this, true);
                else
                    NumberPicker._2D_wrap5(NumberPicker.this, false);
            }

            final NumberPicker this$0;

            
            {
                this$0 = NumberPicker.this;
                super();
            }
        }
;
        obj = new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                NumberPicker._2D_wrap6(NumberPicker.this);
                NumberPicker._2D_get7(NumberPicker.this).clearFocus();
                if(view.getId() == 0x10202a7)
                    NumberPicker._2D_wrap7(NumberPicker.this, true, 0L);
                else
                    NumberPicker._2D_wrap7(NumberPicker.this, false, 0L);
                return true;
            }

            final NumberPicker this$0;

            
            {
                this$0 = NumberPicker.this;
                super();
            }
        }
;
        if(!mHasSelectorWheel)
        {
            mIncrementButton = (ImageButton)findViewById(0x10202a7);
            mIncrementButton.setOnClickListener(attributeset);
            mIncrementButton.setOnLongClickListener(((android.view.View.OnLongClickListener) (obj)));
        } else
        {
            mIncrementButton = null;
        }
        if(!mHasSelectorWheel)
        {
            mDecrementButton = (ImageButton)findViewById(0x102021e);
            mDecrementButton.setOnClickListener(attributeset);
            mDecrementButton.setOnLongClickListener(((android.view.View.OnLongClickListener) (obj)));
        } else
        {
            mDecrementButton = null;
        }
        mInputText = (EditText)findViewById(0x1020344);
        mInputText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag1)
            {
                if(flag1)
                {
                    NumberPicker._2D_get7(NumberPicker.this).selectAll();
                } else
                {
                    NumberPicker._2D_get7(NumberPicker.this).setSelection(0, 0);
                    NumberPicker._2D_wrap9(NumberPicker.this, view);
                }
            }

            final NumberPicker this$0;

            
            {
                this$0 = NumberPicker.this;
                super();
            }
        }
);
        mInputText.setFilters(new InputFilter[] {
            new InputTextFilter()
        });
        mInputText.setAccessibilityLiveRegion(1);
        mInputText.setRawInputType(2);
        mInputText.setImeOptions(6);
        context = ViewConfiguration.get(context);
        mTouchSlop = context.getScaledTouchSlop();
        mMinimumFlingVelocity = context.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = context.getScaledMaximumFlingVelocity() / 8;
        mTextSize = (int)mInputText.getTextSize();
        context = new Paint();
        context.setAntiAlias(true);
        context.setTextAlign(android.graphics.Paint.Align.CENTER);
        context.setTextSize(mTextSize);
        context.setTypeface(mInputText.getTypeface());
        context.setColor(mInputText.getTextColors().getColorForState(ENABLED_STATE_SET, -1));
        mSelectorWheelPaint = context;
        mFlingScroller = new Scroller(getContext(), null, true);
        mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(2.5F));
        updateInputTextView();
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
        if(getFocusable() == 16)
        {
            setFocusable(1);
            setFocusableInTouchMode(true);
        }
    }

    private void changeValueByOne(boolean flag)
    {
        if(mHasSelectorWheel)
        {
            hideSoftInput();
            if(!moveToFinalScrollerPosition(mFlingScroller))
                moveToFinalScrollerPosition(mAdjustScroller);
            mPreviousScrollerY = 0;
            if(flag)
                mFlingScroller.startScroll(0, 0, 0, -mSelectorElementHeight, 300);
            else
                mFlingScroller.startScroll(0, 0, 0, mSelectorElementHeight, 300);
            invalidate();
        } else
        if(flag)
            setValueInternal(mValue + 1, true);
        else
            setValueInternal(mValue - 1, true);
    }

    private void decrementSelectorIndices(int ai[])
    {
        for(int i = ai.length - 1; i > 0; i--)
            ai[i] = ai[i - 1];

        int k = ai[1] - 1;
        int j = k;
        if(mWrapSelectorWheel)
        {
            j = k;
            if(k < mMinValue)
                j = mMaxValue;
        }
        ai[0] = j;
        ensureCachedScrollSelectorValue(j);
    }

    private void ensureCachedScrollSelectorValue(int i)
    {
        SparseArray sparsearray = mSelectorIndexToStringCache;
        if((String)sparsearray.get(i) != null)
            return;
        String s;
        if(i < mMinValue || i > mMaxValue)
            s = "";
        else
        if(mDisplayedValues != null)
        {
            int j = mMinValue;
            s = mDisplayedValues[i - j];
        } else
        {
            s = formatNumber(i);
        }
        sparsearray.put(i, s);
    }

    private boolean ensureScrollWheelAdjusted()
    {
        int i = mInitialScrollOffset - mCurrentScrollOffset;
        if(i != 0)
        {
            mPreviousScrollerY = 0;
            int j = i;
            if(Math.abs(i) > mSelectorElementHeight / 2)
            {
                if(i > 0)
                    j = -mSelectorElementHeight;
                else
                    j = mSelectorElementHeight;
                j = i + j;
            }
            mAdjustScroller.startScroll(0, 0, 0, j, 800);
            invalidate();
            return true;
        } else
        {
            return false;
        }
    }

    private void fling(int i)
    {
        mPreviousScrollerY = 0;
        if(i > 0)
            mFlingScroller.fling(0, 0, 0, i, 0, 0, 0, 0x7fffffff);
        else
            mFlingScroller.fling(0, 0x7fffffff, 0, i, 0, 0, 0, 0x7fffffff);
        invalidate();
    }

    private String formatNumber(int i)
    {
        String s;
        if(mFormatter != null)
            s = mFormatter.format(i);
        else
            s = formatNumberWithLocale(i);
        return s;
    }

    private static String formatNumberWithLocale(int i)
    {
        return String.format(Locale.getDefault(), "%d", new Object[] {
            Integer.valueOf(i)
        });
    }

    private int getSelectedPos(String s)
    {
        if(mDisplayedValues != null) goto _L2; else goto _L1
_L1:
        int i = Integer.parseInt(s);
        return i;
        s;
_L4:
        return mMinValue;
_L2:
        for(i = 0; i < mDisplayedValues.length; i++)
        {
            s = s.toLowerCase();
            if(mDisplayedValues[i].toLowerCase().startsWith(s))
                return mMinValue + i;
        }

        i = Integer.parseInt(s);
        return i;
        s;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final Formatter getTwoDigitFormatter()
    {
        return sTwoDigitFormatter;
    }

    private int getWrappedSelectorIndex(int i)
    {
        if(i > mMaxValue)
            return (mMinValue + (i - mMaxValue) % (mMaxValue - mMinValue)) - 1;
        if(i < mMinValue)
            return (mMaxValue - (mMinValue - i) % (mMaxValue - mMinValue)) + 1;
        else
            return i;
    }

    private void hideSoftInput()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager != null && inputmethodmanager.isActive(mInputText))
            inputmethodmanager.hideSoftInputFromWindow(getWindowToken(), 0);
        if(mHasSelectorWheel)
            mInputText.setVisibility(4);
    }

    private void incrementSelectorIndices(int ai[])
    {
        for(int i = 0; i < ai.length - 1; i++)
            ai[i] = ai[i + 1];

        int k = ai[ai.length - 2] + 1;
        int j = k;
        if(mWrapSelectorWheel)
        {
            j = k;
            if(k > mMaxValue)
                j = mMinValue;
        }
        ai[ai.length - 1] = j;
        ensureCachedScrollSelectorValue(j);
    }

    private void initializeFadingEdges()
    {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength((mBottom - mTop - mTextSize) / 2);
    }

    private void initializeSelectorWheel()
    {
        initializeSelectorWheelIndices();
        int ai[] = mSelectorIndices;
        int i = ai.length;
        int j = mTextSize;
        mSelectorTextGapHeight = (int)((float)(mBottom - mTop - i * j) / (float)ai.length + 0.5F);
        mSelectorElementHeight = mTextSize + mSelectorTextGapHeight;
        mInitialScrollOffset = (mInputText.getBaseline() + mInputText.getTop()) - mSelectorElementHeight * 1;
        mCurrentScrollOffset = mInitialScrollOffset;
        updateInputTextView();
    }

    private void initializeSelectorWheelIndices()
    {
        mSelectorIndexToStringCache.clear();
        int ai[] = mSelectorIndices;
        int i = getValue();
        for(int j = 0; j < mSelectorIndices.length; j++)
        {
            int k = i + (j - 1);
            int l = k;
            if(mWrapSelectorWheel)
                l = getWrappedSelectorIndex(k);
            ai[j] = l;
            ensureCachedScrollSelectorValue(ai[j]);
        }

    }

    private int makeMeasureSpec(int i, int j)
    {
        if(j == -1)
            return i;
        int k = android.view.View.MeasureSpec.getSize(i);
        int l = android.view.View.MeasureSpec.getMode(i);
        switch(l)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown measure mode: ").append(l).toString());

        case 1073741824: 
            return i;

        case -2147483648: 
            return android.view.View.MeasureSpec.makeMeasureSpec(Math.min(k, j), 0x40000000);

        case 0: // '\0'
            return android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
        }
    }

    private boolean moveToFinalScrollerPosition(Scroller scroller)
    {
        scroller.forceFinished(true);
        int i = scroller.getFinalY() - scroller.getCurrY();
        int j = mCurrentScrollOffset;
        int l = mSelectorElementHeight;
        l = mInitialScrollOffset - (j + i) % l;
        if(l != 0)
        {
            int k = l;
            if(Math.abs(l) > mSelectorElementHeight / 2)
                if(l > 0)
                    k = l - mSelectorElementHeight;
                else
                    k = l + mSelectorElementHeight;
            scrollBy(0, i + k);
            return true;
        } else
        {
            return false;
        }
    }

    private void notifyChange(int i, int j)
    {
        if(mOnValueChangeListener != null)
            mOnValueChangeListener.onValueChange(this, i, mValue);
    }

    private void onScrollStateChange(int i)
    {
        if(mScrollState == i)
            return;
        mScrollState = i;
        if(mOnScrollListener != null)
            mOnScrollListener.onScrollStateChange(this, i);
    }

    private void onScrollerFinished(Scroller scroller)
    {
        if(scroller != mFlingScroller) goto _L2; else goto _L1
_L1:
        ensureScrollWheelAdjusted();
        updateInputTextView();
        onScrollStateChange(0);
_L4:
        return;
_L2:
        if(mScrollState != 1)
            updateInputTextView();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void postBeginSoftInputOnLongPressCommand()
    {
        if(mBeginSoftInputOnLongPressCommand == null)
            mBeginSoftInputOnLongPressCommand = new BeginSoftInputOnLongPressCommand();
        else
            removeCallbacks(mBeginSoftInputOnLongPressCommand);
        postDelayed(mBeginSoftInputOnLongPressCommand, ViewConfiguration.getLongPressTimeout());
    }

    private void postChangeCurrentByOneFromLongPress(boolean flag, long l)
    {
        if(mChangeCurrentByOneFromLongPressCommand == null)
            mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        else
            removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
        ChangeCurrentByOneFromLongPressCommand._2D_wrap0(mChangeCurrentByOneFromLongPressCommand, flag);
        postDelayed(mChangeCurrentByOneFromLongPressCommand, l);
    }

    private void postSetSelectionCommand(int i, int j)
    {
        if(mSetSelectionCommand == null)
            mSetSelectionCommand = new SetSelectionCommand(mInputText);
        mSetSelectionCommand.post(i, j);
    }

    private void removeAllCallbacks()
    {
        if(mChangeCurrentByOneFromLongPressCommand != null)
            removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
        if(mSetSelectionCommand != null)
            mSetSelectionCommand.cancel();
        if(mBeginSoftInputOnLongPressCommand != null)
            removeCallbacks(mBeginSoftInputOnLongPressCommand);
        mPressedStateHelper.cancel();
    }

    private void removeBeginSoftInputCommand()
    {
        if(mBeginSoftInputOnLongPressCommand != null)
            removeCallbacks(mBeginSoftInputOnLongPressCommand);
    }

    private void removeChangeCurrentByOneFromLongPress()
    {
        if(mChangeCurrentByOneFromLongPressCommand != null)
            removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
    }

    private int resolveSizeAndStateRespectingMinSize(int i, int j, int k)
    {
        if(i != -1)
            return resolveSizeAndState(Math.max(i, j), k, 0);
        else
            return j;
    }

    private void setValueInternal(int i, boolean flag)
    {
        if(mValue == i)
            return;
        int j;
        if(mWrapSelectorWheel)
            i = getWrappedSelectorIndex(i);
        else
            i = Math.min(Math.max(i, mMinValue), mMaxValue);
        j = mValue;
        mValue = i;
        if(mScrollState != 2)
            updateInputTextView();
        if(flag)
            notifyChange(j, i);
        initializeSelectorWheelIndices();
        invalidate();
    }

    private void showSoftInput()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager != null)
        {
            if(mHasSelectorWheel)
                mInputText.setVisibility(0);
            mInputText.requestFocus();
            inputmethodmanager.showSoftInput(mInputText, 0);
        }
    }

    private void tryComputeMaxWidth()
    {
        int i;
        if(!mComputeMaxWidth)
            return;
        i = 0;
        if(mDisplayedValues != null) goto _L2; else goto _L1
_L1:
        int l;
        float f = 0.0F;
        for(i = 0; i <= 9;)
        {
            float f2 = mSelectorWheelPaint.measureText(formatNumberWithLocale(i));
            float f3 = f;
            if(f2 > f)
                f3 = f2;
            i++;
            f = f3;
        }

        i = 0;
        for(int j = mMaxValue; j > 0; j /= 10)
            i++;

        l = (int)((float)i * f);
_L4:
        i = l + (mInputText.getPaddingLeft() + mInputText.getPaddingRight());
        if(mMaxWidth != i)
        {
            float f1;
            int k;
            int i1;
            if(i > mMinWidth)
                mMaxWidth = i;
            else
                mMaxWidth = mMinWidth;
            invalidate();
        }
        return;
_L2:
        i1 = mDisplayedValues.length;
        k = 0;
_L5:
        l = i;
        if(k >= i1) goto _L4; else goto _L3
_L3:
        f1 = mSelectorWheelPaint.measureText(mDisplayedValues[k]);
        l = i;
        if(f1 > (float)i)
            l = (int)f1;
        k++;
        i = l;
          goto _L5
    }

    private boolean updateInputTextView()
    {
        String s;
        if(mDisplayedValues == null)
            s = formatNumber(mValue);
        else
            s = mDisplayedValues[mValue - mMinValue];
        if(!TextUtils.isEmpty(s))
        {
            android.text.Editable editable = mInputText.getText();
            if(!s.equals(editable.toString()))
            {
                mInputText.setText(s);
                if(AccessibilityManager.getInstance(mContext).isEnabled())
                {
                    AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(16);
                    mInputText.onInitializeAccessibilityEvent(accessibilityevent);
                    mInputText.onPopulateAccessibilityEvent(accessibilityevent);
                    accessibilityevent.setFromIndex(0);
                    accessibilityevent.setRemovedCount(editable.length());
                    accessibilityevent.setAddedCount(s.length());
                    accessibilityevent.setBeforeText(editable);
                    accessibilityevent.setSource(this, 2);
                    requestSendAccessibilityEvent(this, accessibilityevent);
                }
                return true;
            }
        }
        return false;
    }

    private void updateWrapSelectorWheel()
    {
        boolean flag;
        boolean flag1;
        if(mMaxValue - mMinValue >= mSelectorIndices.length)
            flag = true;
        else
            flag = false;
        if(flag)
            flag1 = mWrapSelectorWheelPreferred;
        else
            flag1 = false;
        mWrapSelectorWheel = flag1;
    }

    private void validateInputTextView(View view)
    {
        view = String.valueOf(((TextView)view).getText());
        if(TextUtils.isEmpty(view))
            updateInputTextView();
        else
            setValueInternal(getSelectedPos(view.toString()), true);
    }

    public void computeScroll()
    {
        Scroller scroller = mFlingScroller;
        Scroller scroller2 = scroller;
        if(scroller.isFinished())
        {
            Scroller scroller1 = mAdjustScroller;
            scroller2 = scroller1;
            if(scroller1.isFinished())
                return;
        }
        scroller2.computeScrollOffset();
        int i = scroller2.getCurrY();
        if(mPreviousScrollerY == 0)
            mPreviousScrollerY = scroller2.getStartY();
        scrollBy(0, i - mPreviousScrollerY);
        mPreviousScrollerY = i;
        if(scroller2.isFinished())
            onScrollerFinished(scroller2);
        else
            invalidate();
    }

    protected int computeVerticalScrollExtent()
    {
        return getHeight();
    }

    protected int computeVerticalScrollOffset()
    {
        return mCurrentScrollOffset;
    }

    protected int computeVerticalScrollRange()
    {
        return ((mMaxValue - mMinValue) + 1) * mSelectorElementHeight;
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        if(!mHasSelectorWheel)
            return super.dispatchHoverEvent(motionevent);
        if(!AccessibilityManager.getInstance(mContext).isEnabled()) goto _L2; else goto _L1
_L1:
        int i;
        i = (int)motionevent.getY();
        int j;
        if(i < mTopSelectionDividerTop)
            i = 3;
        else
        if(i > mBottomSelectionDividerBottom)
            i = 1;
        else
            i = 2;
        j = motionevent.getActionMasked();
        motionevent = (AccessibilityNodeProviderImpl)getAccessibilityNodeProvider();
        j;
        JVM INSTR tableswitch 7 10: default 88
    //                   7 133
    //                   8 88
    //                   9 108
    //                   10 185;
           goto _L2 _L3 _L2 _L4 _L5
_L2:
        return false;
_L4:
        motionevent.sendAccessibilityEventForVirtualView(i, 128);
        mLastHoveredChildVirtualViewId = i;
        motionevent.performAction(i, 64, null);
        continue; /* Loop/switch isn't completed */
_L3:
        if(mLastHoveredChildVirtualViewId != i && mLastHoveredChildVirtualViewId != -1)
        {
            motionevent.sendAccessibilityEventForVirtualView(mLastHoveredChildVirtualViewId, 256);
            motionevent.sendAccessibilityEventForVirtualView(i, 128);
            mLastHoveredChildVirtualViewId = i;
            motionevent.performAction(i, 64, null);
        }
        continue; /* Loop/switch isn't completed */
_L5:
        motionevent.sendAccessibilityEventForVirtualView(i, 256);
        mLastHoveredChildVirtualViewId = -1;
        if(true) goto _L2; else goto _L6
_L6:
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        switch(i)
        {
        case 23: // '\027'
        case 66: // 'B'
            removeAllCallbacks();
            break;

        case 19: // '\023'
        case 20: // '\024'
            if(mHasSelectorWheel)
                switch(keyevent.getAction())
                {
                case 1: // '\001'
                    continue;

                case 0: // '\0'
                    if(mWrapSelectorWheel || (i != 20 ? getValue() > getMinValue() : getValue() < getMaxValue()))
                    {
                        requestFocus();
                        mLastHandledDownDpadKeyCode = i;
                        removeAllCallbacks();
                        if(mFlingScroller.isFinished())
                        {
                            boolean flag;
                            if(i == 20)
                                flag = true;
                            else
                                flag = false;
                            changeValueByOne(flag);
                        }
                        return true;
                    }
                    break;
                }
            break;
        }
        while(true) 
        {
            do
                return super.dispatchKeyEvent(keyevent);
            while(mLastHandledDownDpadKeyCode != i);
            mLastHandledDownDpadKeyCode = -1;
            return true;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 1 3: default 32
    //                   1 38
    //                   2 32
    //                   3 38;
           goto _L1 _L2 _L1 _L2
_L1:
        return super.dispatchTouchEvent(motionevent);
_L2:
        removeAllCallbacks();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 1 3: default 32
    //                   1 38
    //                   2 32
    //                   3 38;
           goto _L1 _L2 _L1 _L2
_L1:
        return super.dispatchTrackballEvent(motionevent);
_L2:
        removeAllCallbacks();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mSelectionDivider;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider()
    {
        if(!mHasSelectorWheel)
            return super.getAccessibilityNodeProvider();
        if(mAccessibilityNodeProvider == null)
            mAccessibilityNodeProvider = new AccessibilityNodeProviderImpl();
        return mAccessibilityNodeProvider;
    }

    protected float getBottomFadingEdgeStrength()
    {
        return 0.9F;
    }

    public CharSequence getDisplayedValueForCurrentSelection()
    {
        return (CharSequence)mSelectorIndexToStringCache.get(getValue());
    }

    public String[] getDisplayedValues()
    {
        return mDisplayedValues;
    }

    public int getMaxValue()
    {
        return mMaxValue;
    }

    public int getMinValue()
    {
        return mMinValue;
    }

    public int getSolidColor()
    {
        return mSolidColor;
    }

    protected float getTopFadingEdgeStrength()
    {
        return 0.9F;
    }

    public int getValue()
    {
        return mValue;
    }

    public boolean getWrapSelectorWheel()
    {
        return mWrapSelectorWheel;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mSelectionDivider != null)
            mSelectionDivider.jumpToCurrentState();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeAllCallbacks();
    }

    protected void onDraw(Canvas canvas)
    {
        boolean flag;
        int i;
        if(!mHasSelectorWheel)
        {
            super.onDraw(canvas);
            return;
        }
        float f;
        float f1;
        int ai[];
        int k;
        String s;
        if(mHideWheelUntilFocused)
            flag = hasFocus();
        else
            flag = true;
        f = (mRight - mLeft) / 2;
        f1 = mCurrentScrollOffset;
        if(flag && mVirtualButtonPressedDrawable != null && mScrollState == 0)
        {
            if(mDecrementVirtualButtonPressed)
            {
                mVirtualButtonPressedDrawable.setState(PRESSED_STATE_SET);
                mVirtualButtonPressedDrawable.setBounds(0, 0, mRight, mTopSelectionDividerTop);
                mVirtualButtonPressedDrawable.draw(canvas);
            }
            if(mIncrementVirtualButtonPressed)
            {
                mVirtualButtonPressedDrawable.setState(PRESSED_STATE_SET);
                mVirtualButtonPressedDrawable.setBounds(0, mBottomSelectionDividerBottom, mRight, mBottom);
                mVirtualButtonPressedDrawable.draw(canvas);
            }
        }
        ai = mSelectorIndices;
        i = 0;
_L2:
        if(i >= ai.length)
            break MISSING_BLOCK_LABEL_253;
        k = ai[i];
        s = (String)mSelectorIndexToStringCache.get(k);
        break MISSING_BLOCK_LABEL_190;
        if(flag && i != 1 || i == 1 && mInputText.getVisibility() != 0)
            canvas.drawText(s, f, f1, mSelectorWheelPaint);
        f1 += mSelectorElementHeight;
        i++;
        continue; /* Loop/switch isn't completed */
        if(flag && mSelectionDivider != null)
        {
            int j = mTopSelectionDividerTop;
            int l = mSelectionDividerHeight;
            mSelectionDivider.setBounds(0, j, mRight, j + l);
            mSelectionDivider.draw(canvas);
            j = mBottomSelectionDividerBottom;
            l = mSelectionDividerHeight;
            mSelectionDivider.setBounds(0, j - l, mRight, j);
            mSelectionDivider.draw(canvas);
        }
        return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setClassName(android/widget/NumberPicker.getName());
        accessibilityevent.setScrollable(true);
        accessibilityevent.setScrollY((mMinValue + mValue) * mSelectorElementHeight);
        accessibilityevent.setMaxScrollY((mMaxValue - mMinValue) * mSelectorElementHeight);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(!mHasSelectorWheel || isEnabled() ^ true)
            return false;
        switch(motionevent.getActionMasked())
        {
        default:
            return false;

        case 0: // '\0'
            removeAllCallbacks();
            break;
        }
        hideSoftInput();
        float f = motionevent.getY();
        mLastDownEventY = f;
        mLastDownOrMoveEventY = f;
        mLastDownEventTime = motionevent.getEventTime();
        mIgnoreMoveEvents = false;
        mPerformClickOnTap = false;
        if(mLastDownEventY < (float)mTopSelectionDividerTop)
        {
            if(mScrollState == 0)
                mPressedStateHelper.buttonPressDelayed(2);
        } else
        if(mLastDownEventY > (float)mBottomSelectionDividerBottom && mScrollState == 0)
            mPressedStateHelper.buttonPressDelayed(1);
        getParent().requestDisallowInterceptTouchEvent(true);
        if(!mFlingScroller.isFinished())
        {
            mFlingScroller.forceFinished(true);
            mAdjustScroller.forceFinished(true);
            onScrollStateChange(0);
        } else
        if(!mAdjustScroller.isFinished())
        {
            mFlingScroller.forceFinished(true);
            mAdjustScroller.forceFinished(true);
        } else
        if(mLastDownEventY < (float)mTopSelectionDividerTop)
            postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
        else
        if(mLastDownEventY > (float)mBottomSelectionDividerBottom)
        {
            postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
        } else
        {
            mPerformClickOnTap = true;
            postBeginSoftInputOnLongPressCommand();
        }
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(!mHasSelectorWheel)
        {
            super.onLayout(flag, i, j, k, l);
            return;
        }
        l = getMeasuredWidth();
        k = getMeasuredHeight();
        i = mInputText.getMeasuredWidth();
        j = mInputText.getMeasuredHeight();
        l = (l - i) / 2;
        k = (k - j) / 2;
        mInputText.layout(l, k, l + i, k + j);
        if(flag)
        {
            initializeSelectorWheel();
            initializeFadingEdges();
            mTopSelectionDividerTop = (getHeight() - mSelectionDividersDistance) / 2 - mSelectionDividerHeight;
            mBottomSelectionDividerBottom = mTopSelectionDividerTop + mSelectionDividerHeight * 2 + mSelectionDividersDistance;
        }
    }

    protected void onMeasure(int i, int j)
    {
        if(!mHasSelectorWheel)
        {
            super.onMeasure(i, j);
            return;
        } else
        {
            super.onMeasure(makeMeasureSpec(i, mMaxWidth), makeMeasureSpec(j, mMaxHeight));
            setMeasuredDimension(resolveSizeAndStateRespectingMinSize(mMinWidth, getMeasuredWidth(), i), resolveSizeAndStateRespectingMinSize(mMinHeight, getMeasuredHeight(), j));
            return;
        }
    }

    public void onResolveDrawables(int i)
    {
        super.onResolveDrawables(i);
        if(mSelectionDivider != null)
            mSelectionDivider.setLayoutDirection(i);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!isEnabled() || mHasSelectorWheel ^ true)
            return false;
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(motionevent);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 1 2: default 68
    //                   1 143
    //                   2 70;
           goto _L1 _L2 _L3
_L1:
        return true;
_L3:
        if(!mIgnoreMoveEvents)
        {
            float f = motionevent.getY();
            if(mScrollState != 1)
            {
                if((int)Math.abs(f - mLastDownEventY) > mTouchSlop)
                {
                    removeAllCallbacks();
                    onScrollStateChange(1);
                }
            } else
            {
                scrollBy(0, (int)(f - mLastDownOrMoveEventY));
                invalidate();
            }
            mLastDownOrMoveEventY = f;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        removeBeginSoftInputCommand();
        removeChangeCurrentByOneFromLongPress();
        mPressedStateHelper.cancel();
        VelocityTracker velocitytracker = mVelocityTracker;
        velocitytracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        int i = (int)velocitytracker.getYVelocity();
        if(Math.abs(i) <= mMinimumFlingVelocity)
            break; /* Loop/switch isn't completed */
        fling(i);
        onScrollStateChange(2);
_L5:
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        if(true) goto _L1; else goto _L4
_L4:
        int l = (int)motionevent.getY();
        int j = (int)Math.abs((float)l - mLastDownEventY);
        long l1 = motionevent.getEventTime();
        long l2 = mLastDownEventTime;
        if(j <= mTouchSlop && l1 - l2 < (long)ViewConfiguration.getTapTimeout())
        {
            if(mPerformClickOnTap)
            {
                mPerformClickOnTap = false;
                performClick();
            } else
            {
                int k = l / mSelectorElementHeight - 1;
                if(k > 0)
                {
                    changeValueByOne(true);
                    mPressedStateHelper.buttonTapped(1);
                } else
                if(k < 0)
                {
                    changeValueByOne(false);
                    mPressedStateHelper.buttonTapped(2);
                }
            }
        } else
        {
            ensureScrollWheelAdjusted();
        }
        onScrollStateChange(0);
          goto _L5
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean performClick()
    {
        if(!mHasSelectorWheel)
            return super.performClick();
        if(!super.performClick())
            showSoftInput();
        return true;
    }

    public boolean performLongClick()
    {
        if(!mHasSelectorWheel)
            return super.performLongClick();
        if(!super.performLongClick())
        {
            showSoftInput();
            mIgnoreMoveEvents = true;
        }
        return true;
    }

    public void scrollBy(int i, int j)
    {
        int ai[] = mSelectorIndices;
        i = mCurrentScrollOffset;
        if(!mWrapSelectorWheel && j > 0 && ai[1] <= mMinValue)
        {
            mCurrentScrollOffset = mInitialScrollOffset;
            return;
        }
        if(!mWrapSelectorWheel && j < 0 && ai[1] >= mMaxValue)
        {
            mCurrentScrollOffset = mInitialScrollOffset;
            return;
        }
        mCurrentScrollOffset = mCurrentScrollOffset + j;
        do
        {
            if(mCurrentScrollOffset - mInitialScrollOffset <= mSelectorTextGapHeight)
                break;
            mCurrentScrollOffset = mCurrentScrollOffset - mSelectorElementHeight;
            decrementSelectorIndices(ai);
            setValueInternal(ai[1], true);
            if(!mWrapSelectorWheel && ai[1] <= mMinValue)
                mCurrentScrollOffset = mInitialScrollOffset;
        } while(true);
        do
        {
            if(mCurrentScrollOffset - mInitialScrollOffset >= -mSelectorTextGapHeight)
                break;
            mCurrentScrollOffset = mCurrentScrollOffset + mSelectorElementHeight;
            incrementSelectorIndices(ai);
            setValueInternal(ai[1], true);
            if(!mWrapSelectorWheel && ai[1] >= mMaxValue)
                mCurrentScrollOffset = mInitialScrollOffset;
        } while(true);
        if(i != mCurrentScrollOffset)
            onScrollChanged(0, mCurrentScrollOffset, 0, i);
    }

    public void setDisplayedValues(String as[])
    {
        if(mDisplayedValues == as)
            return;
        mDisplayedValues = as;
        if(mDisplayedValues != null)
            mInputText.setRawInputType(0x80001);
        else
            mInputText.setRawInputType(2);
        updateInputTextView();
        initializeSelectorWheelIndices();
        tryComputeMaxWidth();
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        if(!mHasSelectorWheel)
            mIncrementButton.setEnabled(flag);
        if(!mHasSelectorWheel)
            mDecrementButton.setEnabled(flag);
        mInputText.setEnabled(flag);
    }

    public void setFormatter(Formatter formatter)
    {
        if(formatter == mFormatter)
        {
            return;
        } else
        {
            mFormatter = formatter;
            initializeSelectorWheelIndices();
            updateInputTextView();
            return;
        }
    }

    public void setMaxValue(int i)
    {
        if(mMaxValue == i)
            return;
        if(i < 0)
            throw new IllegalArgumentException("maxValue must be >= 0");
        mMaxValue = i;
        if(mMaxValue < mValue)
            mValue = mMaxValue;
        updateWrapSelectorWheel();
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public void setMinValue(int i)
    {
        if(mMinValue == i)
            return;
        if(i < 0)
            throw new IllegalArgumentException("minValue must be >= 0");
        mMinValue = i;
        if(mMinValue > mValue)
            mValue = mMinValue;
        updateWrapSelectorWheel();
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
    }

    public void setOnLongPressUpdateInterval(long l)
    {
        mLongPressUpdateInterval = l;
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        mOnScrollListener = onscrolllistener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onvaluechangelistener)
    {
        mOnValueChangeListener = onvaluechangelistener;
    }

    public void setValue(int i)
    {
        setValueInternal(i, false);
    }

    public void setWrapSelectorWheel(boolean flag)
    {
        mWrapSelectorWheelPreferred = flag;
        updateWrapSelectorWheel();
    }

    private static final int DEFAULT_LAYOUT_RESOURCE_ID = 0x10900ad;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300L;
    private static final char DIGIT_CHARACTERS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '\u0660', '\u0661', '\u0662', '\u0663', '\u0664', '\u0665', '\u0666', '\u0667', '\u0668', '\u0669', 
        '\u06F0', '\u06F1', '\u06F2', '\u06F3', '\u06F4', '\u06F5', '\u06F6', '\u06F7', '\u06F8', '\u06F9', 
        '\u0966', '\u0967', '\u0968', '\u0969', '\u096A', '\u096B', '\u096C', '\u096D', '\u096E', '\u096F', 
        '\u09E6', '\u09E7', '\u09E8', '\u09E9', '\u09EA', '\u09EB', '\u09EC', '\u09ED', '\u09EE', '\u09EF', 
        '\u0CE6', '\u0CE7', '\u0CE8', '\u0CE9', '\u0CEA', '\u0CEB', '\u0CEC', '\u0CED', '\u0CEE', '\u0CEF'
    };
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 8;
    private static final int SELECTOR_MIDDLE_ITEM_INDEX = 1;
    private static final int SELECTOR_WHEEL_ITEM_COUNT = 3;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final float TOP_AND_BOTTOM_FADING_EDGE_STRENGTH = 0.9F;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDERS_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDER_HEIGHT = 2;
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    private AccessibilityNodeProviderImpl mAccessibilityNodeProvider;
    private final Scroller mAdjustScroller;
    private BeginSoftInputOnLongPressCommand mBeginSoftInputOnLongPressCommand;
    private int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private final boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private final ImageButton mDecrementButton;
    private boolean mDecrementVirtualButtonPressed;
    private String mDisplayedValues[];
    private final Scroller mFlingScroller;
    private Formatter mFormatter;
    private final boolean mHasSelectorWheel;
    private boolean mHideWheelUntilFocused;
    private boolean mIgnoreMoveEvents;
    private final ImageButton mIncrementButton;
    private boolean mIncrementVirtualButtonPressed;
    private int mInitialScrollOffset;
    private final EditText mInputText;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private int mLastHoveredChildVirtualViewId;
    private long mLongPressUpdateInterval;
    private final int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private final int mMinHeight;
    private int mMinValue;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private boolean mPerformClickOnTap;
    private final PressedStateHelper mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private final Drawable mSelectionDivider;
    private final int mSelectionDividerHeight;
    private final int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private final SparseArray mSelectorIndexToStringCache;
    private final int mSelectorIndices[];
    private int mSelectorTextGapHeight;
    private final Paint mSelectorWheelPaint;
    private SetSelectionCommand mSetSelectionCommand;
    private final int mSolidColor;
    private final int mTextSize;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private final Drawable mVirtualButtonPressedDrawable;
    private boolean mWrapSelectorWheel;
    private boolean mWrapSelectorWheelPreferred;

}
