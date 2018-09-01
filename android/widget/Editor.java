// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.*;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.method.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.*;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import com.android.internal.view.menu.ActionMenu;
import com.android.internal.widget.EditableInputConnection;
import com.miui.translationservice.provider.TranslationResult;
import java.util.*;
import miui.content.ExtraIntent;
import miui.os.Build;
import miui.phrase.AddPhraseActivity;
import miui.view.animation.BackEaseOutInterpolator;
import miui.view.animation.CubicEaseOutInterpolator;

// Referenced classes of package android.widget:
//            TextView, PopupWindow, SelectionActionModeHelper, SpellChecker, 
//            TouchPanelLayout, LinearLayout, ImageView, FrameLayout, 
//            LinkSpec, ImageButton, ListView, TranslationPresenter, 
//            TranslationManager, Linkify, AdapterView, Adapter, 
//            BaseAdapter

public class Editor
{
    private abstract class ActionPinnedPopupWindow extends PinnedPopupWindow
        implements Fader
    {

        static HandleView _2D_get0(ActionPinnedPopupWindow actionpinnedpopupwindow)
        {
            return actionpinnedpopupwindow.mHandleView;
        }

        static Region _2D_get1(ActionPinnedPopupWindow actionpinnedpopupwindow)
        {
            return actionpinnedpopupwindow.mTouchableRegion;
        }

        static boolean _2D_wrap0(ActionPinnedPopupWindow actionpinnedpopupwindow)
        {
            return actionpinnedpopupwindow.isMiddleOffsetInSelection();
        }

        private boolean isMainPanelContent()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mMainPanel != null)
            {
                flag1 = flag;
                if(mContentView.getChildAt(0) == mMainPanel)
                    flag1 = true;
            }
            return flag1;
        }

        private boolean isMiddleOffsetInSelection()
        {
            boolean flag = false;
            int i = Editor._2D_get6(Editor.this).getOffsetForPosition(Editor._2D_get6(Editor.this).getWidth() / 2, Editor._2D_get6(Editor.this).getHeight() / 2);
            boolean flag1 = flag;
            if(i <= Editor._2D_get6(Editor.this).getSelectionEnd())
            {
                flag1 = flag;
                if(i >= Editor._2D_get6(Editor.this).getSelectionStart())
                    flag1 = true;
            }
            return flag1;
        }

        private void setTouchableSurfaceInsetsComputer()
        {
            ViewTreeObserver viewtreeobserver = mPopupWindow.getContentView().getRootView().getViewTreeObserver();
            viewtreeobserver.removeOnComputeInternalInsetsListener(mInsetsComputer);
            viewtreeobserver.addOnComputeInternalInsetsListener(mInsetsComputer);
        }

        public void cancelAnimations()
        {
            mAnimationFadeIn.cancel();
            mContentView.setScaleX(1.0F);
            mContentView.setScaleY(1.0F);
        }

        protected int clipVertically(int i)
        {
            Layout layout;
            int j;
            int k;
            int l;
            int i1;
            int j1;
            int k1;
            DisplayMetrics displaymetrics;
            layout = Editor._2D_get6(Editor.this).getLayout();
            j = Editor._2D_get6(Editor.this).getSelectionStart();
            k = Editor._2D_get6(Editor.this).getSelectionEnd();
            l = layout.getLineForOffset(j);
            i1 = layout.getLineForOffset(k);
            j1 = layout.getLineTop(i1) - layout.getLineBottom(l);
            k1 = Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200e8).getIntrinsicHeight();
            displaymetrics = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            if(i >= 0 || !Editor._2D_wrap4(Editor.this, j)) goto _L2; else goto _L1
_L1:
            if(j1 > mContentView.getMeasuredHeight())
                i += layout.getLineBottom(l) - layout.getLineTop(l);
            else
                i += layout.getLineBottom(i1) - layout.getLineTop(l);
            j = i + mContentView.getMeasuredHeight() + k1 / 2;
            mAboveHandle = false;
_L4:
            return j;
_L2:
            if(i < 0 && Editor._2D_get6(Editor.this).isSingleLine())
            {
                j = layout.getLineBottom(l) + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset() + k1 / 2;
                mAboveHandle = false;
            } else
            {
                j = i;
                if(mContentView.getMeasuredHeight() + i > displaymetrics.heightPixels)
                {
                    j = i;
                    if(Editor._2D_wrap4(Editor.this, k))
                    {
                        if(j1 > mContentView.getMeasuredHeight())
                            i -= layout.getLineBottom(i1) - layout.getLineTop(i1);
                        else
                            i -= layout.getLineBottom(i1) - layout.getLineTop(l);
                        j = i - mContentView.getMeasuredHeight() - k1 / 2;
                    }
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected void computeLocalPosition()
        {
            int i = Editor._2D_get6(Editor.this).getSelectionStart();
            int j = Editor._2D_get6(Editor.this).getSelectionEnd();
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            mAboveHandle = true;
            measureContent();
            int k;
            float f;
            if(Editor._2D_wrap4(Editor.this, i))
            {
                mPositionY = layout.getLineTop(layout.getLineForOffset(i)) - mContentView.getMeasuredHeight();
                mPositionY = mPositionY + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset();
            } else
            if(Editor._2D_wrap4(Editor.this, j))
            {
                mPositionY = layout.getLineBottom(layout.getLineForOffset(j));
                mPositionY = mPositionY + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset();
                if(mHandleView.getVisibility() == 0)
                {
                    int l = Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200e8).getIntrinsicHeight();
                    mPositionY = mPositionY + l / 2;
                }
                mAboveHandle = false;
            } else
            if(Editor._2D_get6(Editor.this).isSingleLine())
            {
                mPositionY = layout.getLineTop(layout.getLineForOffset(getTextOffset())) - mContentView.getMeasuredHeight();
                mPositionY = mPositionY + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset();
            } else
            {
                if(Editor._2D_get5(Editor.this) == null)
                    Editor._2D_set4(Editor.this, new Rect());
                Editor._2D_get6(Editor.this).getLocalVisibleRect(Editor._2D_get5(Editor.this));
                mPositionY = ((Editor._2D_get5(Editor.this).bottom - Editor._2D_get5(Editor.this).top) / 2 + Editor._2D_get5(Editor.this).top) - mContentView.getMeasuredHeight() / 2;
            }
            k = mContentView.getMeasuredWidth();
            f = mHandleView.mHorizontalScale;
            mPositionX = (int)((((layout.getPrimaryHorizontal(i) + layout.getPrimaryHorizontal(j)) - (float)k) / 2.0F + (float)Editor._2D_get6(Editor.this).viewportToContentHorizontalOffset()) * f);
        }

        protected abstract void createAnimations();

        public void dismiss()
        {
            super.dismiss();
            setZeroTouchableSurface();
        }

        public void fadeOut()
        {
            mPopupWindow.dismiss();
        }

        protected int getTextOffset()
        {
            return (Editor._2D_get6(Editor.this).getSelectionStart() + Editor._2D_get6(Editor.this).getSelectionEnd()) / 2;
        }

        protected int getVerticalLocalPosition(int i)
        {
            return 0;
        }

        public void hide()
        {
            Editor._2D_get6(Editor.this).removeCallbacks(mShower);
            super.hide();
        }

        protected void measureContent()
        {
            DisplayMetrics displaymetrics;
            int i;
            int j;
            displaymetrics = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            i = displaymetrics.widthPixels;
            j = mSpaceOffScreen;
            if(Build.IS_TABLET) goto _L2; else goto _L1
_L1:
            int l;
            int k = mVisibleChildren.size();
            if(Build.IS_INTERNATIONAL_BUILD)
                l = 5;
            else
                l = 6;
            if(k >= l) goto _L3; else goto _L2
_L2:
            l = 0x80000000;
_L5:
            mContentView.measure(android.view.View.MeasureSpec.makeMeasureSpec(i + j * 2, l), android.view.View.MeasureSpec.makeMeasureSpec(displaymetrics.heightPixels, 0x80000000));
            return;
_L3:
            l = 0x40000000;
            if(true) goto _L5; else goto _L4
_L4:
        }

        protected void setContentAreaAsTouchableSurface(boolean flag)
        {
            View view = mContentView.findViewById(0x110c0011);
            int i = view.getPaddingLeft();
            int j = view.getPaddingTop();
            int k = view.getPaddingRight();
            int l = view.getPaddingBottom();
            if(flag || mPopupWindow.isShowing() ^ true)
            {
                DisplayMetrics displaymetrics = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
                int i1 = displaymetrics.widthPixels;
                int j1 = mSpaceOffScreen;
                int k1 = mVisibleChildren.size();
                int l1;
                if(Build.IS_INTERNATIONAL_BUILD)
                    l1 = 5;
                else
                    l1 = 6;
                if(k1 < l1)
                    l1 = 0x80000000;
                else
                    l1 = 0x40000000;
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i1 + j1 * 2, l1), android.view.View.MeasureSpec.makeMeasureSpec(displaymetrics.heightPixels, 0x80000000));
            }
            l1 = view.getMeasuredWidth();
            i1 = view.getMeasuredHeight();
            mTouchableRegion.set(view.getLeft() + i, view.getTop() + j, (view.getLeft() + l1) - k, (view.getTop() + i1) - l);
        }

        protected void setMainPanelAsContent()
        {
            if(mMainPanel.getParent() != null)
            {
                setContentAreaAsTouchableSurface(false);
                return;
            } else
            {
                mContentView.removeAllViews();
                mContentView.addView(mMainPanel, new android.view.ViewGroup.LayoutParams(-1, -1));
                setContentAreaAsTouchableSurface(false);
                return;
            }
        }

        protected void setSubPanelAsContent(View view, Drawable drawable)
        {
            dismiss();
            if(mHandleView instanceof InsertionHandleView)
                InsertionHandleView._2D_wrap1((InsertionHandleView)mHandleView);
            LinearLayout linearlayout = new LinearLayout(Editor._2D_get6(Editor.this).getContext());
            if(drawable != null)
            {
                linearlayout.setBackground(drawable);
                linearlayout.setId(0x110c0011);
            }
            linearlayout.addView(view);
            mContentView.removeAllViews();
            mContentView.addView(linearlayout, new android.view.ViewGroup.LayoutParams(-1, -1));
            mVisibleChildren.clear();
            computeLocalPosition();
            view = Editor._2D_wrap0(Editor.this);
            updatePosition(view.getPositionX(), view.getPositionY());
            setContentAreaAsTouchableSurface(false);
        }

        public void setY(int i)
        {
        }

        protected void setZeroTouchableSurface()
        {
            mTouchableRegion.setEmpty();
        }

        public void show()
        {
            setMainPanelAsContent();
            super.show();
        }

        void show(int i)
        {
            Editor._2D_get6(Editor.this).removeCallbacks(mShower);
            Editor._2D_get6(Editor.this).postDelayed(mShower, i);
        }

        protected void updatePosition(int i, int j)
        {
            int k = mPositionX;
            j = clipVertically(j + mPositionY);
            int l = mContentView.getMeasuredWidth();
            mPopupWindow.setWidth(l);
            i = Math.min((Editor._2D_get6(Editor.this).getResources().getDisplayMetrics().widthPixels + mSpaceOffScreen) - l, i + k);
            i = Math.max(-mSpaceOffScreen, i);
            if(isShowing())
            {
                mPopupWindow.update(i, j, l, -1);
            } else
            {
                mPopupWindow.showAtLocation(Editor._2D_get6(Editor.this), 0, i, j);
                setTouchableSurfaceInsetsComputer();
            }
        }

        public void updatePosition(int i, int j, boolean flag, boolean flag1)
        {
            if(flag1 || flag)
            {
                j = 0;
                i = j;
                if(mHandleView.isShowing())
                {
                    i = j;
                    if(mHandleView instanceof InsertionHandleView)
                    {
                        InsertionHandleView insertionhandleview = (InsertionHandleView)mHandleView;
                        InsertionHandleView._2D_wrap1(insertionhandleview);
                        i = 1;
                        InsertionHandleView._2D_set0(insertionhandleview, true);
                    }
                }
                if(isMainPanelContent())
                {
                    hide();
                    show(500);
                } else
                if(i != 0)
                    hide();
                else
                    stopSelectionActionMode();
            }
        }

        protected static final int LONG_ACTION_MENU_COUNT = 6;
        protected boolean mAboveHandle;
        protected AnimatorSet mAnimationFadeIn;
        protected AnimatorSet mAnimationFadeOut;
        protected AnimatorListenerAdapter mAnimationFadeOutListener;
        private HandleView mHandleView;
        protected LayoutInflater mInflater;
        private final android.view.ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new _cls2();
        protected TouchPanelLayout mMainPanel;
        private Runnable mShower;
        private int mSpaceOffScreen;
        private final Region mTouchableRegion = new Region();
        protected List mVisibleChildren;
        final Editor this$0;

        public ActionPinnedPopupWindow(HandleView handleview)
        {
            this$0 = Editor.this;
            super();
            mVisibleChildren = new ArrayList();
            mShower = new _cls1();
            mHandleView = handleview;
            mSpaceOffScreen = Editor._2D_get6(Editor.this).getResources().getDimensionPixelSize(0x110b002d);
            createAnimations();
            ((AnimatePopupWindow)mPopupWindow).setFader(this);
            mInflater = (LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater");
        }
    }

    private class ActionPopupWindow extends ActionPinnedPopupWindow
        implements android.view.View.OnClickListener
    {

        static TranslationPresenter _2D_get0(ActionPopupWindow actionpopupwindow)
        {
            return actionpopupwindow.mTranslationPresenter;
        }

        private int getMaxPhraseListHeight()
        {
            Resources resources = Editor._2D_get6(Editor.this).getResources();
            Object obj = Editor._2D_get6(Editor.this).getContext().getTheme();
            int i = resources.getDrawable(0x11020082, ((android.content.res.Resources.Theme) (obj))).getIntrinsicHeight();
            obj = resources.getDrawable(0x11020083, ((android.content.res.Resources.Theme) (obj)));
            ((Drawable) (obj)).setLevel(0);
            int j = ((Drawable) (obj)).getIntrinsicHeight();
            ((Drawable) (obj)).setLevel(1);
            int k = ((Drawable) (obj)).getIntrinsicHeight();
            ((Drawable) (obj)).setLevel(2);
            return i + j + k + ((Drawable) (obj)).getIntrinsicHeight();
        }

        private boolean isPasswordInputType()
        {
            boolean flag;
            int i;
            boolean flag1;
            flag = true;
            i = mInputType & 0xfff;
            flag1 = flag;
            if(i == 129) goto _L2; else goto _L1
_L1:
            if(i != 225) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 18)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        private ImageView newImageView()
        {
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
            ImageView imageview = new ImageView(Editor._2D_get6(Editor.this).getContext());
            imageview.setLayoutParams(layoutparams);
            imageview.setBackgroundResource(0x110200db);
            imageview.setScaleType(ImageView.ScaleType.CENTER);
            imageview.setOnClickListener(this);
            return imageview;
        }

        private TextView newTextView()
        {
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
            TextView textview = (TextView)((LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater")).inflate(0x1103001c, null);
            textview.setLayoutParams(layoutparams);
            textview.setSingleLine();
            textview.setGravity(17);
            textview.setOnClickListener(this);
            return textview;
        }

        protected void createAnimations()
        {
            mAnimationFadeIn = new AnimatorSet();
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(mContentView, View.ALPHA, new float[] {
                0.0F, 1.0F
            });
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(mContentView, View.SCALE_X, new float[] {
                0.6F, 1.0F
            });
            ObjectAnimator objectanimator2 = ObjectAnimator.ofFloat(mContentView, View.SCALE_Y, new float[] {
                0.6F, 1.0F
            });
            mAnimationFadeIn.setInterpolator(new CubicEaseOutInterpolator());
            mAnimationFadeIn.setDuration(150L);
            mAnimationFadeIn.playTogether(new Animator[] {
                objectanimator, objectanimator1, objectanimator2
            });
            mAnimationFadeOut = new AnimatorSet();
            objectanimator = ObjectAnimator.ofFloat(mContentView, View.ALPHA, new float[] {
                1.0F, 0.0F
            });
            objectanimator1 = ObjectAnimator.ofFloat(mContentView, View.SCALE_X, new float[] {
                1.0F, 0.6F
            });
            objectanimator2 = ObjectAnimator.ofFloat(mContentView, View.SCALE_Y, new float[] {
                1.0F, 0.6F
            });
            mAnimationFadeOut.setInterpolator(new CubicEaseOutInterpolator());
            mAnimationFadeOut.setDuration(100L);
            mAnimationFadeOut.playTogether(new Animator[] {
                objectanimator, objectanimator1, objectanimator2
            });
            mAnimationFadeOutListener = new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    ((AnimatePopupWindow)mPopupWindow).dismiss(false);
                }

                final ActionPopupWindow this$1;

            
            {
                this$1 = ActionPopupWindow.this;
                super();
            }
            }
;
        }

        protected void createPopupWindow()
        {
            mPopupWindow = new AnimatePopupWindow(Editor._2D_get6(Editor.this).getContext(), null);
            mPopupWindow.setClippingEnabled(false);
        }

        public void dismiss()
        {
            ((AnimatePopupWindow)mPopupWindow).dismiss(true);
            setZeroTouchableSurface();
        }

        public void fadeIn(int i, int j)
        {
            mContentView.setPivotX(mContentView.getMeasuredWidth() / 2);
            ViewGroup viewgroup = mContentView;
            if(mAboveHandle)
                i = mContentView.getMeasuredHeight();
            else
                i = 0;
            viewgroup.setPivotY(i);
            mAnimationFadeIn.start();
        }

        protected void initContentView()
        {
            mMainPanel = new TouchPanelLayout(Editor._2D_get6(Editor.this).getContext());
            mMainPanel.setOrientation(0);
            mMainPanel.setBackgroundResource(0x110200da);
            mMainPanel.setId(0x110c0011);
            mContentView = new FrameLayout(Editor._2D_get6(Editor.this).getContext());
            mContentView.addView(mMainPanel, new android.view.ViewGroup.LayoutParams(-1, -1));
            mSearchImageView = newImageView();
            mMainPanel.addView(mSearchImageView);
            mSearchImageView.setImageResource(0x110200cc);
            mWebImageView = newImageView();
            mMainPanel.addView(mWebImageView);
            mWebImageView.setImageResource(0x110200d5);
            mTelImageView = newImageView();
            mMainPanel.addView(mTelImageView);
            mTelImageView.setImageResource(0x110200d2);
            mSelectTextView = newTextView();
            mMainPanel.addView(mSelectTextView);
            mSelectTextView.setText(0x11080060);
            mSelectAllTextView = newTextView();
            mMainPanel.addView(mSelectAllTextView);
            mSelectAllTextView.setText(0x11080061);
            mCutTextView = newTextView();
            mMainPanel.addView(mCutTextView);
            mCutTextView.setText(0x11080062);
            mCopyTextView = newTextView();
            mMainPanel.addView(mCopyTextView);
            mCopyTextView.setText(0x11080063);
            mPasteTextView = newTextView();
            mMainPanel.addView(mPasteTextView);
            mPasteTextView.setText(0x11080064);
            mReplaceTextView = newTextView();
            mMainPanel.addView(mReplaceTextView);
            mReplaceTextView.setText(0x11080065);
            mAutoFillTextView = newTextView();
            mMainPanel.addView(mAutoFillTextView);
            mAutoFillTextView.setText(0x1108006c);
            mAutoFillTextView.setId(0x102026e);
            mTranslationImageView = newImageView();
            mMainPanel.addView(mTranslationImageView);
            mTranslationImageView.setImageResource(0x110200c9);
            mShareImageView = newImageView();
            mMainPanel.addView(mShareImageView);
            mShareImageView.setImageResource(0x110200cf);
            mPhraseTextView = newTextView();
            mMainPanel.addView(mPhraseTextView);
            mPhraseTextView.setText(0x11080066);
        }

        public void onClick(View view)
        {
            byte byte0;
            int i;
            final int maximum;
            byte0 = -1;
            i = 0;
            maximum = Editor._2D_get6(Editor.this).getText().length();
            if(Editor._2D_get6(Editor.this).isFocused())
            {
                int j = Editor._2D_get6(Editor.this).getSelectionStart();
                maximum = Editor._2D_get6(Editor.this).getSelectionEnd();
                i = Math.max(0, Math.min(j, maximum));
                maximum = Math.max(0, Math.max(j, maximum));
            }
            if(view != mSelectTextView) goto _L2; else goto _L1
_L1:
            int k;
            if(hasSelectionController())
            {
                getSelectionController().setMinTouchOffset(i);
                getSelectionController().setMaxTouchOffset(maximum);
            }
            startSelectionActionMode();
            k = byte0;
_L9:
            Editor._2D_wrap1(Editor.this).onSelectionAction(k);
            return;
_L2:
            if(view == mSelectAllTextView)
            {
                Editor._2D_get6(Editor.this).onTextContextMenuItem(0x102001f);
                startSelectionActionMode();
                k = 0x102001f;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mPasteTextView && Editor._2D_get6(Editor.this).canPaste())
            {
                Editor._2D_get6(Editor.this).onTextContextMenuItem(0x1020022);
                hide();
                k = 0x1020022;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mReplaceTextView)
            {
                replace();
                k = 0x1020034;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mCopyTextView)
            {
                Editor._2D_get6(Editor.this).onTextContextMenuItem(0x1020021);
                k = 0x1020021;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mCutTextView)
            {
                Editor._2D_get6(Editor.this).onTextContextMenuItem(0x1020020);
                k = 0x1020020;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mShareImageView)
            {
                view = new Intent("android.intent.action.SEND");
                view.setType("text/plain");
                view.putExtra("android.intent.extra.TEXT", Editor._2D_get6(Editor.this).getText().subSequence(i, maximum).toString());
                Context context = Editor._2D_get6(Editor.this).getContext();
                Editor._2D_wrap19(Editor.this, context, Intent.createChooser(view, context.getString(0x10405c7)));
                Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), (i + maximum) / 2);
                k = 0x1020035;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mSearchImageView)
            {
                if(Build.IS_INTERNATIONAL_BUILD)
                {
                    view = new Intent("android.intent.action.WEB_SEARCH");
                    view.putExtra("query", Editor._2D_get6(Editor.this).getText().subSequence(i, maximum).toString());
                    Editor._2D_wrap19(Editor.this, Editor._2D_get6(Editor.this).getContext(), view);
                } else
                {
                    view = new Intent("android.intent.action.SEARCH");
                    view.setData(Uri.parse((new StringBuilder()).append("qsb://query?words=").append(Editor._2D_get6(Editor.this).getText().subSequence(i, maximum).toString()).append("&ref=miuiEditor&web_search=true").toString()));
                    Editor._2D_wrap19(Editor.this, Editor._2D_get6(Editor.this).getContext(), view);
                }
                Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), (i + maximum) / 2);
                k = 0x1020035;
                continue; /* Loop/switch isn't completed */
            }
            if(view == mTelImageView)
            {
                view = (LinkSpec)mTelImageView.getTag();
                k = byte0;
                if(view != null)
                {
                    view = new Intent("android.intent.action.DIAL", Uri.parse(((LinkSpec) (view)).url));
                    Editor._2D_wrap19(Editor.this, Editor._2D_get6(Editor.this).getContext(), view);
                    k = byte0;
                    if(i != maximum)
                    {
                        Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), (i + maximum) / 2);
                        k = byte0;
                    }
                }
                continue; /* Loop/switch isn't completed */
            }
            if(view == mWebImageView)
            {
                view = (LinkSpec)mWebImageView.getTag();
                k = byte0;
                if(view != null)
                {
                    view = new Intent("android.intent.action.VIEW", Uri.parse(((LinkSpec) (view)).url));
                    Editor._2D_wrap19(Editor.this, Editor._2D_get6(Editor.this).getContext(), view);
                    k = byte0;
                    if(i != maximum)
                    {
                        Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), (i + maximum) / 2);
                        k = byte0;
                    }
                }
                continue; /* Loop/switch isn't completed */
            }
            if(view == mPhraseTextView)
            {
                ArrayList arraylist = android.provider.MiuiSettings.FrequentPhrases.getFrequentPhrases(Editor._2D_get6(Editor.this).getContext());
                if(arraylist == null || arraylist.size() == 0)
                {
                    view = newTextView();
                    view.setText(0x11080067);
                    view.getBackground().setLevel(3);
                    view.setPadding(mTextActionPadding, view.getPaddingTop(), mTextActionPadding, view.getPaddingBottom());
                    view.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            view = ExtraIntent.getAddPhraseIntent();
                            view.addFlags(0x10000000);
                            Editor._2D_get6(_fld0).getContext().startActivity(view.putStringArrayListExtra("phrase_list", new ArrayList()));
                        }

                        final ActionPopupWindow this$1;

            
            {
                this$1 = ActionPopupWindow.this;
                super();
            }
                    }
);
                    setSubPanelAsContent(view, Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200da, Editor._2D_get6(Editor.this).getContext().getTheme()));
                    k = byte0;
                } else
                {
                    view = ((LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater")).inflate(0x1103000e, null);
                    Object obj = (ImageButton)view.findViewById(0x110c002a);
                    if(isPasswordInputType())
                    {
                        ((ImageButton) (obj)).setVisibility(8);
                    } else
                    {
                        ((ImageButton) (obj)).setVisibility(0);
                        ((ImageButton) (obj)).setOnClickListener(new android.view.View.OnClickListener() {

                            public void onClick(View view)
                            {
                                view = ExtraIntent.getPhraseEditIntent();
                                view.addFlags(0x10000000);
                                Editor._2D_get6(_fld0).getContext().startActivity(view);
                            }

                            final ActionPopupWindow this$1;

            
            {
                this$1 = ActionPopupWindow.this;
                super();
            }
                        }
);
                    }
                    obj = (ListView)view.findViewById(0x102000a);
                    ((ListView) (obj)).setOverScrollMode(2);
                    ((ListView) (obj)).setAdapter(new PhraseAdapter(arraylist));
                    ((ListView) (obj)).setOnItemClickListener(i. new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            adapterview = (String)adapterview.getAdapter().getItem(i);
                            view = Editor._2D_get6(_fld0).getText();
                            Selection.setSelection((Spannable)view, maximum);
                            ((Editable)view).replace(minimum, maximum, adapterview);
                        }

                        final ActionPopupWindow this$1;
                        final int val$maximum;
                        final int val$minimum;

            
            {
                this$1 = final_actionpopupwindow;
                maximum = i;
                minimum = I.this;
                super();
            }
                    }
);
                    i = Editor._2D_get6(Editor.this).getResources().getDimensionPixelSize(0x110b002f);
                    k = -2;
                    if(arraylist.size() > 2)
                    {
                        if(mMaxPhraseListHeight == 0)
                            mMaxPhraseListHeight = getMaxPhraseListHeight();
                        k = mMaxPhraseListHeight;
                    }
                    view.setLayoutParams(new android.view.ViewGroup.LayoutParams(i, k));
                    setSubPanelAsContent(view, Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200da, Editor._2D_get6(Editor.this).getContext().getTheme()));
                    k = byte0;
                }
                continue; /* Loop/switch isn't completed */
            }
            if(view != mTranslationImageView) goto _L4; else goto _L3
_L3:
            if(mTranslationPanel != null) goto _L6; else goto _L5
_L5:
            mTranslationPanel = ((LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater")).inflate(0x1103001d, null);
_L7:
            if(mTranslationPresenter == null)
                mTranslationPresenter = new TranslationPresenter(Editor._2D_get6(Editor.this).getContext(), mTranslationPanel);
            view = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            Drawable drawable = Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200da, Editor._2D_get6(Editor.this).getContext().getTheme());
            Rect rect = new Rect();
            drawable.getPadding(rect);
            int l = ((DisplayMetrics) (view)).widthPixels;
            int i1 = rect.left;
            int j1 = rect.right;
            int k1 = Editor._2D_get6(Editor.this).getResources().getDimensionPixelSize(0x110b0032);
            int l1 = rect.top;
            k = rect.bottom;
            mTranslationPanel.setLayoutParams(new android.view.ViewGroup.LayoutParams(l - i1 - j1, k1 + l1 + k));
            setSubPanelAsContent(mTranslationPanel, null);
            mTranslationPresenter.setAboveHandle(mAboveHandle);
            mTranslationPresenter.setInProgress();
            mTranslationManager.translate(null, null, Editor._2D_get6(Editor.this).getTransformedText(i, maximum).toString());
            k = byte0;
            continue; /* Loop/switch isn't completed */
_L6:
            if(mTranslationPanel.getParent() != null)
                ((ViewGroup)mTranslationPanel.getParent()).removeView(mTranslationPanel);
            if(true) goto _L7; else goto _L4
_L4:
            k = byte0;
            if(view == mAutoFillTextView)
            {
                Editor._2D_get6(Editor.this).onTextContextMenuItem(0x1020043);
                k = byte0;
            }
            if(true) goto _L9; else goto _L8
_L8:
        }

        public void show()
        {
            int i1;
            int j1;
            Object obj = Editor._2D_get6(Editor.this).getText();
            int i = Editor._2D_get6(Editor.this).getSelectionStart();
            int j = Editor._2D_get6(Editor.this).getSelectionEnd();
            boolean flag2 = isPasswordInputType();
            boolean flag;
            boolean flag1;
            int k;
            boolean flag3;
            boolean flag4;
            int l;
            boolean flag5;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            int k1;
            if(((CharSequence) (obj)).length() > 0 && j - i > 0)
                k = flag2 ^ true;
            else
                k = 0;
            flag3 = false;
            mTelImageView.setTag(null);
            flag4 = flag3;
            if(mFeatureTel)
            {
                flag4 = flag3;
                if(((CharSequence) (obj)).length() > 0)
                {
                    ArrayList arraylist = Linkify.getLinks(((CharSequence) (obj)), i, j, 4);
                    flag4 = flag3;
                    if(arraylist != null)
                    {
                        flag4 = flag3;
                        if(arraylist.size() == 1)
                        {
                            mTelImageView.setTag(arraylist.get(0));
                            flag4 = true;
                        }
                    }
                }
            }
            flag3 = false;
            mWebImageView.setTag(null);
            flag5 = flag3;
            if(!flag4)
            {
                flag5 = flag3;
                if(((CharSequence) (obj)).length() > 0)
                {
                    ArrayList arraylist1 = Linkify.getLinks(((CharSequence) (obj)), i, j, 1);
                    flag5 = flag3;
                    if(arraylist1 != null)
                    {
                        flag5 = flag3;
                        if(arraylist1.size() > 0)
                        {
                            mWebImageView.setTag(arraylist1.get(0));
                            flag5 = true;
                        }
                    }
                }
            }
            flag6 = Editor._2D_get6(Editor.this).canCopy();
            flag7 = Editor._2D_get6(Editor.this).canCut();
            flag8 = Editor._2D_get6(Editor.this).canPaste();
            if(Editor._2D_get6(Editor.this).isSuggestionsEnabled() && Editor._2D_wrap11(Editor.this))
            {
                if(Editor._2D_get6(Editor.this).isInExtractedMode())
                    flag9 = Editor._2D_get6(Editor.this).hasSelection();
                else
                    flag9 = false;
                flag3 = flag9 ^ true;
            } else
            {
                flag3 = false;
            }
            if(((CharSequence) (obj)).length() > 0)
                flag10 = Editor._2D_get6(Editor.this).hasSelection() ^ true;
            else
                flag10 = false;
            if(((CharSequence) (obj)).length() > 0 && (i != 0 || j != ((CharSequence) (obj)).length()))
                flag = true;
            else
                flag = false;
            if(((CharSequence) (obj)).length() > 0 && Editor._2D_get6(Editor.this).hasSelection())
                j1 = flag2 ^ true;
            else
                j1 = 0;
            if(!Editor._2D_get6(Editor.this).hasSelection() && (Editor._2D_get6(Editor.this).getContext() instanceof AddPhraseActivity) ^ true && (!flag2 || android.provider.MiuiSettings.FrequentPhrases.getFrequentPhrases(Editor._2D_get6(Editor.this).getContext()) != null))
                flag1 = Editor._2D_get6(Editor.this).isInExtractedMode() ^ true;
            else
                flag1 = false;
            if(mTranslationManager == null)
                mTranslationManager = new TranslationManager(Editor._2D_get6(Editor.this).getContext(), mTranslationHandler);
            if(((CharSequence) (obj)).length() > 0 && Editor._2D_get6(Editor.this).hasSelection() && mTranslationManager.isAvailable())
                k1 = flag2 ^ true;
            else
                k1 = 0;
            if(Editor._2D_get6(Editor.this).canRequestAutofill())
            {
                if(Editor._2D_get6(Editor.this).getSelectedText() != null)
                    flag9 = Editor._2D_get6(Editor.this).getSelectedText().isEmpty();
                else
                    flag9 = true;
            } else
            {
                flag9 = false;
            }
            obj = mSearchImageView;
            if(k != 0)
                k = 0;
            else
                k = 8;
            ((ImageView) (obj)).setVisibility(k);
            obj = mTelImageView;
            if(flag4)
                l = 0;
            else
                l = 8;
            ((ImageView) (obj)).setVisibility(l);
            obj = mWebImageView;
            if(flag5)
                i1 = 0;
            else
                i1 = 8;
            ((ImageView) (obj)).setVisibility(i1);
            obj = mCopyTextView;
            if(flag6)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mCutTextView;
            if(flag7)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mPasteTextView;
            if(flag8)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mReplaceTextView;
            if(flag3)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mSelectTextView;
            if(flag10)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mSelectAllTextView;
            if(flag)
                i1 = 0;
            else
                i1 = 8;
            ((TextView) (obj)).setVisibility(i1);
            obj = mTranslationImageView;
            if(k1 != 0)
                k1 = 0;
            else
                k1 = 8;
            ((ImageView) (obj)).setVisibility(k1);
            obj = mShareImageView;
            if(j1 != 0)
                j1 = 0;
            else
                j1 = 8;
            ((ImageView) (obj)).setVisibility(j1);
            obj = mPhraseTextView;
            if(flag1)
                j1 = 0;
            else
                j1 = 8;
            ((TextView) (obj)).setVisibility(j1);
            obj = mAutoFillTextView;
            if(flag9)
                j1 = 0;
            else
                j1 = 8;
            ((TextView) (obj)).setVisibility(j1);
            k1 = mMainPanel.getChildCount();
            mVisibleChildren.clear();
            for(j1 = 0; j1 < k1; j1++)
            {
                obj = mMainPanel.getChildAt(j1);
                if(((View) (obj)).getVisibility() == 0)
                    mVisibleChildren.add(obj);
            }

            i1 = mVisibleChildren.size();
            if(Build.IS_TABLET)
                break MISSING_BLOCK_LABEL_1153;
            if(Build.IS_INTERNATIONAL_BUILD)
                j1 = 5;
            else
                j1 = 6;
            if(i1 < j1)
                break MISSING_BLOCK_LABEL_1153;
            j1 = 0;
_L1:
            if(i1 == 1)
            {
                obj = (View)mVisibleChildren.get(0);
                ((View) (obj)).getBackground().setLevel(3);
                ((View) (obj)).setPadding(j1, ((View) (obj)).getPaddingTop(), j1, ((View) (obj)).getPaddingBottom());
            } else
            {
                int l1 = 0;
                while(l1 < i1) 
                {
                    View view = (View)mVisibleChildren.get(l1);
                    Drawable drawable = view.getBackground();
                    if(l1 == 0)
                        drawable.setLevel(0);
                    else
                    if(l1 == i1 - 1)
                        drawable.setLevel(2);
                    else
                        drawable.setLevel(1);
                    view.setPadding(j1, view.getPaddingTop(), j1, view.getPaddingBottom());
                    l1++;
                }
            }
            if(!flag6 && flag7 ^ true && flag8 ^ true && flag3 ^ true && flag10 ^ true && flag ^ true && flag1 ^ true)
            {
                return;
            } else
            {
                super.show();
                return;
            }
            j1 = mTextActionPadding;
              goto _L1
        }

        protected void updatePosition(int i, int j)
        {
            AnimatePopupWindow animatepopupwindow = (AnimatePopupWindow)mPopupWindow;
            if(animatepopupwindow.isDismissing())
                animatepopupwindow.dismiss(false);
            super.updatePosition(i, j);
        }

        private final int POPUP_TEXT_LAYOUT = 0x1103001c;
        private TextView mAutoFillTextView;
        private TextView mCopyTextView;
        private TextView mCutTextView;
        private boolean mFeatureTel;
        private int mMaxPhraseListHeight;
        private TextView mPasteTextView;
        private TextView mPhraseTextView;
        private TextView mReplaceTextView;
        private ImageView mSearchImageView;
        private TextView mSelectAllTextView;
        private TextView mSelectTextView;
        private ImageView mShareImageView;
        private ImageView mTelImageView;
        private int mTextActionPadding;
        private Handler mTranslationHandler;
        private ImageView mTranslationImageView;
        private TranslationManager mTranslationManager;
        private View mTranslationPanel;
        private TranslationPresenter mTranslationPresenter;
        private ImageView mWebImageView;
        final Editor this$0;

        public ActionPopupWindow(HandleView handleview)
        {
            this$0 = Editor.this;
            super(handleview);
            mTranslationHandler = new _cls1();
            handleview = Editor._2D_get6(Editor.this).getResources();
            int i;
            if(Editor._2D_wrap2())
                i = 0x110b002c;
            else
                i = 0x110b002b;
            mTextActionPadding = handleview.getDimensionPixelSize(i);
            if((new Intent("android.intent.action.DIAL")).resolveActivity(Editor._2D_get6(Editor.this).getContext().getPackageManager()) != null)
                mFeatureTel = true;
        }
    }

    private class AnimatePopupWindow extends PopupWindow
    {

        public void dismiss(boolean flag)
        {
            if(flag && mDismissing)
                return;
            if(mFader != null)
                mFader.cancelAnimations();
            if(flag && mFader != null)
            {
                mDismissing = true;
                mFader.fadeOut();
            } else
            {
                mDismissing = false;
                dismiss();
            }
        }

        public boolean isDismissing()
        {
            return mDismissing;
        }

        public void setFader(Fader fader)
        {
            mFader = fader;
        }

        public void showAtLocation(View view, int i, int j, int k)
        {
            mDismissing = false;
            if(mFader != null)
                mFader.cancelAnimations();
            super.showAtLocation(view, i, j, k);
            if(mFader != null)
                mFader.fadeIn(j, k);
        }

        public void update(int i, int j, int k, int l)
        {
            mDismissing = false;
            super.update(i, j, k, l);
            if(mFader != null)
                mFader.setY(j);
        }

        private boolean mDismissing;
        private Fader mFader;
        final Editor this$0;

        private AnimatePopupWindow(Context context)
        {
            this$0 = Editor.this;
            super(context, null, 0);
            mDismissing = false;
            if(mFader == null)
                setAnimationStyle(0x110d0004);
        }

        public AnimatePopupWindow(Context context, AttributeSet attributeset, int i)
        {
            this$0 = Editor.this;
            super(context, attributeset, i);
            mDismissing = false;
        }

        AnimatePopupWindow(Context context, AnimatePopupWindow animatepopupwindow)
        {
            this(context);
        }
    }

    private class Blink
        implements Runnable
    {

        void cancel()
        {
            if(!mCancelled)
            {
                Editor._2D_get6(Editor.this).removeCallbacks(this);
                mCancelled = true;
            }
        }

        public void run()
        {
            if(mCancelled)
                return;
            Editor._2D_get6(Editor.this).removeCallbacks(this);
            if(Editor._2D_wrap10(Editor.this))
            {
                if(Editor._2D_get6(Editor.this).getLayout() != null)
                    Editor._2D_get6(Editor.this).invalidateCursorPath();
                Editor._2D_get6(Editor.this).postDelayed(this, 500L);
            }
        }

        void uncancel()
        {
            mCancelled = false;
        }

        private boolean mCancelled;
        final Editor this$0;

        private Blink()
        {
            this$0 = Editor.this;
            super();
        }

        Blink(Blink blink)
        {
            this();
        }
    }

    private class CorrectionHighlighter
    {

        static void _2D_wrap0(CorrectionHighlighter correctionhighlighter, boolean flag)
        {
            correctionhighlighter.invalidate(flag);
        }

        private void invalidate(boolean flag)
        {
            if(Editor._2D_get6(Editor.this).getLayout() == null)
                return;
            if(mTempRectF == null)
                mTempRectF = new RectF();
            mPath.computeBounds(mTempRectF, false);
            int i = Editor._2D_get6(Editor.this).getCompoundPaddingLeft();
            int j = Editor._2D_get6(Editor.this).getExtendedPaddingTop() + Editor._2D_get6(Editor.this).getVerticalOffset(true);
            if(flag)
                Editor._2D_get6(Editor.this).postInvalidateOnAnimation((int)mTempRectF.left + i, (int)mTempRectF.top + j, (int)mTempRectF.right + i, (int)mTempRectF.bottom + j);
            else
                Editor._2D_get6(Editor.this).postInvalidate((int)mTempRectF.left, (int)mTempRectF.top, (int)mTempRectF.right, (int)mTempRectF.bottom);
        }

        private void stopAnimation()
        {
            mCorrectionHighlighter = null;
        }

        private boolean updatePaint()
        {
            long l = SystemClock.uptimeMillis() - mFadingStartTime;
            if(l > 400L)
            {
                return false;
            } else
            {
                float f = (float)l / 400F;
                int i = Color.alpha(Editor._2D_get6(Editor.this).mHighlightColor);
                int j = Editor._2D_get6(Editor.this).mHighlightColor;
                i = (int)((float)i * (1.0F - f));
                mPaint.setColor((j & 0xffffff) + (i << 24));
                return true;
            }
        }

        private boolean updatePath()
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            if(layout == null)
            {
                return false;
            } else
            {
                int i = Editor._2D_get6(Editor.this).getText().length();
                int j = Math.min(i, mStart);
                i = Math.min(i, mEnd);
                mPath.reset();
                layout.getSelectionPath(j, i, mPath);
                return true;
            }
        }

        public void draw(Canvas canvas, int i)
        {
            if(updatePath() && updatePaint())
            {
                if(i != 0)
                    canvas.translate(0.0F, i);
                canvas.drawPath(mPath, mPaint);
                if(i != 0)
                    canvas.translate(0.0F, -i);
                invalidate(true);
            } else
            {
                stopAnimation();
                invalidate(false);
            }
        }

        public void highlight(CorrectionInfo correctioninfo)
        {
            mStart = correctioninfo.getOffset();
            mEnd = mStart + correctioninfo.getNewText().length();
            mFadingStartTime = SystemClock.uptimeMillis();
            if(mStart < 0 || mEnd < 0)
                stopAnimation();
        }

        private static final int FADE_OUT_DURATION = 400;
        private int mEnd;
        private long mFadingStartTime;
        private final Paint mPaint = new Paint(1);
        private final Path mPath = new Path();
        private int mStart;
        private RectF mTempRectF;
        final Editor this$0;

        public CorrectionHighlighter()
        {
            this$0 = Editor.this;
            super();
            mPaint.setCompatibilityScaling(Editor._2D_get6(Editor.this).getResources().getCompatibilityInfo().applicationScale);
            mPaint.setStyle(android.graphics.Paint.Style.FILL);
        }
    }

    private static interface CursorController
        extends android.view.ViewTreeObserver.OnTouchModeChangeListener
    {

        public abstract void hide();

        public abstract boolean isActive();

        public abstract boolean isCursorBeingModified();

        public abstract void onDetached();

        public abstract void show();
    }

    private static class DragLocalState
    {

        public int end;
        public TextView sourceTextView;
        public int start;

        public DragLocalState(TextView textview, int i, int j)
        {
            sourceTextView = textview;
            start = i;
            end = j;
        }
    }

    private static interface EasyEditDeleteListener
    {

        public abstract void onDeleteClick(EasyEditSpan easyeditspan);
    }

    private class EasyEditPopupWindow extends PinnedPopupWindow
        implements android.view.View.OnClickListener
    {

        static EasyEditSpan _2D_get0(EasyEditPopupWindow easyeditpopupwindow)
        {
            return easyeditpopupwindow.mEasyEditSpan;
        }

        static void _2D_wrap0(EasyEditPopupWindow easyeditpopupwindow, EasyEditDeleteListener easyeditdeletelistener)
        {
            easyeditpopupwindow.setOnDeleteListener(easyeditdeletelistener);
        }

        private void setOnDeleteListener(EasyEditDeleteListener easyeditdeletelistener)
        {
            mOnDeleteListener = easyeditdeletelistener;
        }

        protected int clipVertically(int i)
        {
            return i;
        }

        protected void createPopupWindow()
        {
            mPopupWindow = new PopupWindow(Editor._2D_get6(Editor.this).getContext(), null, 0x10102c8);
            mPopupWindow.setInputMethodMode(2);
            mPopupWindow.setClippingEnabled(true);
        }

        protected int getTextOffset()
        {
            return ((Editable)Editor._2D_get6(Editor.this).getText()).getSpanEnd(mEasyEditSpan);
        }

        protected int getVerticalLocalPosition(int i)
        {
            return Editor._2D_get6(Editor.this).getLayout().getLineBottom(i);
        }

        public void hide()
        {
            if(mEasyEditSpan != null)
                mEasyEditSpan.setDeleteEnabled(false);
            mOnDeleteListener = null;
            super.hide();
        }

        protected void initContentView()
        {
            Object obj = new LinearLayout(Editor._2D_get6(Editor.this).getContext());
            ((LinearLayout) (obj)).setOrientation(0);
            mContentView = ((ViewGroup) (obj));
            mContentView.setBackgroundResource(0x1080847);
            obj = (LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater");
            android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-2, -2);
            mDeleteTextView = (TextView)((LayoutInflater) (obj)).inflate(0x1090104, null);
            mDeleteTextView.setLayoutParams(layoutparams);
            mDeleteTextView.setText(0x10401a3);
            mDeleteTextView.setOnClickListener(this);
            mContentView.addView(mDeleteTextView);
        }

        public void onClick(View view)
        {
            if(view == mDeleteTextView && mEasyEditSpan != null && mEasyEditSpan.isDeleteEnabled() && mOnDeleteListener != null)
                mOnDeleteListener.onDeleteClick(mEasyEditSpan);
        }

        public void setEasyEditSpan(EasyEditSpan easyeditspan)
        {
            mEasyEditSpan = easyeditspan;
        }

        private static final int POPUP_TEXT_LAYOUT = 0x1090104;
        private TextView mDeleteTextView;
        private EasyEditSpan mEasyEditSpan;
        private EasyEditDeleteListener mOnDeleteListener;
        final Editor this$0;

        private EasyEditPopupWindow()
        {
            this$0 = Editor.this;
            super();
        }

        EasyEditPopupWindow(EasyEditPopupWindow easyeditpopupwindow)
        {
            this();
        }
    }

    public static class EditOperation extends UndoOperation
    {

        static String _2D_get0(EditOperation editoperation)
        {
            return editoperation.mNewText;
        }

        static String _2D_get1(EditOperation editoperation)
        {
            return editoperation.mOldText;
        }

        static boolean _2D_set0(EditOperation editoperation, boolean flag)
        {
            editoperation.mFrozen = flag;
            return flag;
        }

        static boolean _2D_wrap0(EditOperation editoperation, EditOperation editoperation1)
        {
            return editoperation.mergeWith(editoperation1);
        }

        private int getNewTextEnd()
        {
            return mStart + mNewText.length();
        }

        private int getOldTextEnd()
        {
            return mStart + mOldText.length();
        }

        private String getTypeString()
        {
            switch(mType)
            {
            default:
                return "";

            case 0: // '\0'
                return "insert";

            case 1: // '\001'
                return "delete";

            case 2: // '\002'
                return "replace";
            }
        }

        private boolean mergeDeleteWith(EditOperation editoperation)
        {
            if(editoperation.mType != 1)
                return false;
            if(mStart != editoperation.getOldTextEnd())
            {
                return false;
            } else
            {
                mStart = editoperation.mStart;
                mOldText = (new StringBuilder()).append(editoperation.mOldText).append(mOldText).toString();
                mNewCursorPos = editoperation.mNewCursorPos;
                mIsComposition = editoperation.mIsComposition;
                return true;
            }
        }

        private boolean mergeInsertWith(EditOperation editoperation)
        {
            if(editoperation.mType == 0)
                if(getNewTextEnd() != editoperation.mStart)
                {
                    return false;
                } else
                {
                    mNewText = (new StringBuilder()).append(mNewText).append(editoperation.mNewText).toString();
                    mNewCursorPos = editoperation.mNewCursorPos;
                    mFrozen = editoperation.mFrozen;
                    mIsComposition = editoperation.mIsComposition;
                    return true;
                }
            if(mIsComposition && editoperation.mType == 2 && mStart <= editoperation.mStart && getNewTextEnd() >= editoperation.getOldTextEnd())
            {
                mNewText = (new StringBuilder()).append(mNewText.substring(0, editoperation.mStart - mStart)).append(editoperation.mNewText).append(mNewText.substring(editoperation.getOldTextEnd() - mStart, mNewText.length())).toString();
                mNewCursorPos = editoperation.mNewCursorPos;
                mIsComposition = editoperation.mIsComposition;
                return true;
            } else
            {
                return false;
            }
        }

        private boolean mergeReplaceWith(EditOperation editoperation)
        {
            if(editoperation.mType == 0 && getNewTextEnd() == editoperation.mStart)
            {
                mNewText = (new StringBuilder()).append(mNewText).append(editoperation.mNewText).toString();
                mNewCursorPos = editoperation.mNewCursorPos;
                return true;
            }
            if(!mIsComposition)
                return false;
            if(editoperation.mType == 1 && mStart <= editoperation.mStart && getNewTextEnd() >= editoperation.getOldTextEnd())
            {
                mNewText = (new StringBuilder()).append(mNewText.substring(0, editoperation.mStart - mStart)).append(mNewText.substring(editoperation.getOldTextEnd() - mStart, mNewText.length())).toString();
                if(mNewText.isEmpty())
                    mType = 1;
                mNewCursorPos = editoperation.mNewCursorPos;
                mIsComposition = editoperation.mIsComposition;
                return true;
            }
            if(editoperation.mType == 2 && mStart == editoperation.mStart && TextUtils.equals(mNewText, editoperation.mOldText))
            {
                mNewText = editoperation.mNewText;
                mNewCursorPos = editoperation.mNewCursorPos;
                mIsComposition = editoperation.mIsComposition;
                return true;
            } else
            {
                return false;
            }
        }

        private boolean mergeWith(EditOperation editoperation)
        {
            if(mFrozen)
                return false;
            switch(mType)
            {
            default:
                return false;

            case 0: // '\0'
                return mergeInsertWith(editoperation);

            case 1: // '\001'
                return mergeDeleteWith(editoperation);

            case 2: // '\002'
                return mergeReplaceWith(editoperation);
            }
        }

        private static void modifyText(Editable editable, int i, int j, CharSequence charsequence, int k, int l)
        {
            if(Editor._2D_wrap7(editable, i, j) && k <= editable.length() - (j - i))
            {
                if(i != j)
                    editable.delete(i, j);
                if(charsequence.length() != 0)
                    editable.insert(k, charsequence);
            }
            if(l >= 0 && l <= editable.length())
                Selection.setSelection(editable, l);
        }

        public void commit()
        {
        }

        public void forceMergeWith(EditOperation editoperation)
        {
            if(mergeWith(editoperation))
            {
                return;
            } else
            {
                Object obj = (Editable)Editor._2D_get6((Editor)getOwnerData()).getText();
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(((Editable) (obj)).toString());
                modifyText(spannablestringbuilder, mStart, getNewTextEnd(), mOldText, mStart, mOldCursorPos);
                obj = new SpannableStringBuilder(((Editable) (obj)).toString());
                modifyText(((Editable) (obj)), editoperation.mStart, editoperation.getOldTextEnd(), editoperation.mNewText, editoperation.mStart, editoperation.mNewCursorPos);
                mType = 2;
                mNewText = ((Editable) (obj)).toString();
                mOldText = spannablestringbuilder.toString();
                mStart = 0;
                mNewCursorPos = editoperation.mNewCursorPos;
                mIsComposition = editoperation.mIsComposition;
                return;
            }
        }

        public void redo()
        {
            modifyText((Editable)Editor._2D_get6((Editor)getOwnerData()).getText(), mStart, getOldTextEnd(), mNewText, mStart, mNewCursorPos);
        }

        public String toString()
        {
            return (new StringBuilder()).append("[mType=").append(getTypeString()).append(", ").append("mOldText=").append(mOldText).append(", ").append("mNewText=").append(mNewText).append(", ").append("mStart=").append(mStart).append(", ").append("mOldCursorPos=").append(mOldCursorPos).append(", ").append("mNewCursorPos=").append(mNewCursorPos).append(", ").append("mFrozen=").append(mFrozen).append(", ").append("mIsComposition=").append(mIsComposition).append("]").toString();
        }

        public void undo()
        {
            modifyText((Editable)Editor._2D_get6((Editor)getOwnerData()).getText(), mStart, getNewTextEnd(), mOldText, mStart, mOldCursorPos);
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(mType);
            parcel.writeString(mOldText);
            parcel.writeString(mNewText);
            parcel.writeInt(mStart);
            parcel.writeInt(mOldCursorPos);
            parcel.writeInt(mNewCursorPos);
            if(mFrozen)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mIsComposition)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
        }

        public static final android.os.Parcelable.ClassLoaderCreator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

            public EditOperation createFromParcel(Parcel parcel)
            {
                return new EditOperation(parcel, null);
            }

            public EditOperation createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new EditOperation(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public EditOperation[] newArray(int i)
            {
                return new EditOperation[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int TYPE_DELETE = 1;
        private static final int TYPE_INSERT = 0;
        private static final int TYPE_REPLACE = 2;
        private boolean mFrozen;
        private boolean mIsComposition;
        private int mNewCursorPos;
        private String mNewText;
        private int mOldCursorPos;
        private String mOldText;
        private int mStart;
        private int mType;


        public EditOperation(Parcel parcel, ClassLoader classloader)
        {
            boolean flag = true;
            super(parcel, classloader);
            mType = parcel.readInt();
            mOldText = parcel.readString();
            mNewText = parcel.readString();
            mStart = parcel.readInt();
            mOldCursorPos = parcel.readInt();
            mNewCursorPos = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            mFrozen = flag1;
            if(parcel.readInt() == 1)
                flag1 = flag;
            else
                flag1 = false;
            mIsComposition = flag1;
        }

        public EditOperation(Editor editor, String s, int i, String s1, boolean flag)
        {
            super(Editor._2D_get8(editor));
            mOldText = s;
            mNewText = s1;
            if(mNewText.length() > 0 && mOldText.length() == 0)
                mType = 0;
            else
            if(mNewText.length() == 0 && mOldText.length() > 0)
                mType = 1;
            else
                mType = 2;
            mStart = i;
            mOldCursorPos = Editor._2D_get6(editor).getSelectionStart();
            mNewCursorPos = mNewText.length() + i;
            mIsComposition = flag;
        }
    }

    private static class ErrorPopup extends PopupWindow
    {

        private int getResourceId(int i, int j)
        {
            int k = i;
            if(i == 0)
            {
                TypedArray typedarray = mView.getContext().obtainStyledAttributes(android.R.styleable.Theme);
                k = typedarray.getResourceId(j, 0);
                typedarray.recycle();
            }
            return k;
        }

        void fixDirection(boolean flag)
        {
            mAbove = flag;
            TextView textview;
            int i;
            if(flag)
                mPopupInlineErrorAboveBackgroundId = getResourceId(mPopupInlineErrorAboveBackgroundId, 289);
            else
                mPopupInlineErrorBackgroundId = getResourceId(mPopupInlineErrorBackgroundId, 290);
            textview = mView;
            if(flag)
                i = mPopupInlineErrorAboveBackgroundId;
            else
                i = mPopupInlineErrorBackgroundId;
            textview.setBackgroundResource(i);
        }

        public void update(int i, int j, int k, int l, boolean flag)
        {
            super.update(i, j, k, l, flag);
            flag = isAboveAnchor();
            if(flag != mAbove)
                fixDirection(flag);
        }

        private boolean mAbove;
        private int mPopupInlineErrorAboveBackgroundId;
        private int mPopupInlineErrorBackgroundId;
        private final TextView mView;

        ErrorPopup(TextView textview, int i, int j)
        {
            super(textview, i, j);
            mAbove = false;
            mPopupInlineErrorBackgroundId = 0;
            mPopupInlineErrorAboveBackgroundId = 0;
            mView = textview;
            mPopupInlineErrorBackgroundId = getResourceId(mPopupInlineErrorBackgroundId, 290);
            mView.setBackgroundResource(mPopupInlineErrorBackgroundId);
        }
    }

    private static interface Fader
    {

        public abstract void cancelAnimations();

        public abstract void fadeIn(int i, int j);

        public abstract void fadeOut();

        public abstract void setY(int i);
    }

    private abstract class HandleView extends View
        implements TextViewPositionListener
    {

        private void addPositionToTouchUpFilter(int i)
        {
            mPreviousOffsetIndex = (mPreviousOffsetIndex + 1) % 5;
            mPreviousOffsets[mPreviousOffsetIndex] = i;
            mPreviousOffsetsTimes[mPreviousOffsetIndex] = SystemClock.uptimeMillis();
            mNumberPreviousOffsets = mNumberPreviousOffsets + 1;
        }

        private int clipVertically(int i)
        {
            int j = i;
            if(i >= getResources().getDisplayMetrics().heightPixels)
            {
                j = getCurrentCursorOffset();
                Layout layout = Editor._2D_get6(Editor.this).getLayout();
                j = layout.getLineForOffset(j);
                j = i - (layout.getLineBottom(j) - layout.getLineTop(j)) - getMeasuredHeight();
            }
            return j;
        }

        private void filterOnTouchUp()
        {
            long l = SystemClock.uptimeMillis();
            int i = 0;
            int j = mPreviousOffsetIndex;
            int k;
            for(k = Math.min(mNumberPreviousOffsets, 5); i < k && l - mPreviousOffsetsTimes[j] < 150L; j = ((mPreviousOffsetIndex - i) + 5) % 5)
                i++;

            if(i > 0 && i < k && l - mPreviousOffsetsTimes[j] > 350L)
                positionAtCursorOffset(mPreviousOffsets[j], false);
        }

        private int getHandleExtension(int i)
        {
            return Math.min(i, Editor._2D_get6(Editor.this).getLineHeight() * 2);
        }

        private boolean isVisible()
        {
            if(mIsDragging)
                return true;
            if(Editor._2D_get6(Editor.this).isInBatchEditMode())
                return false;
            else
                return Editor._2D_wrap6(Editor.this, mPositionX + mHotspotX + getHorizontalOffset(), mPositionY);
        }

        private void startTouchUpFilter(int i)
        {
            mNumberPreviousOffsets = 0;
            addPositionToTouchUpFilter(i);
        }

        protected void dismiss()
        {
            mIsDragging = false;
            mContainer.dismiss();
            onDetached();
        }

        protected ActionPopupWindow getActionPopupWindow()
        {
            if(mActionPopupWindow == null)
                mActionPopupWindow = new ActionPopupWindow(this);
            return mActionPopupWindow;
        }

        public abstract int getCurrentCursorOffset();

        protected int getCursorOffset()
        {
            return 0;
        }

        protected abstract int getHorizontalGravity(boolean flag);

        protected int getHorizontalOffset()
        {
            int i;
            int j;
            i = getPreferredWidth();
            j = mDrawable.getIntrinsicWidth();
            mHorizontalGravity;
            JVM INSTR lookupswitch 3: default 52
        //                       3: 60
        //                       5: 65
        //                       17: 52;
               goto _L1 _L2 _L3 _L1
_L1:
            i = (i - j) / 2;
_L5:
            return i;
_L2:
            i = 0;
            continue; /* Loop/switch isn't completed */
_L3:
            i -= j;
            if(true) goto _L5; else goto _L4
_L4:
        }

        protected abstract int getHotspotX(Drawable drawable, boolean flag);

        public float getIdealVerticalOffset()
        {
            return mIdealVerticalOffset;
        }

        int getPreferredHeight()
        {
            return Math.max(mDrawable.getIntrinsicHeight(), mMinSize);
        }

        int getPreferredWidth()
        {
            return Math.max(mDrawable.getIntrinsicWidth(), mMinSize);
        }

        public void hide()
        {
            dismiss();
            Editor._2D_wrap0(Editor.this).removeSubscriber(this);
        }

        protected void hideActionPopupWindow()
        {
            if(mActionPopupShower != null)
                Editor._2D_get6(Editor.this).removeCallbacks(mActionPopupShower);
            if(mActionPopupWindow != null)
                mActionPopupWindow.hide();
        }

        public boolean isDragging()
        {
            return mIsDragging;
        }

        public boolean isPopshowing()
        {
            boolean flag;
            if(mActionPopupWindow != null)
                flag = mActionPopupWindow.isShowing();
            else
                flag = false;
            return flag;
        }

        public boolean isShowing()
        {
            return mContainer.isShowing();
        }

        public boolean offsetHasBeenChanged()
        {
            boolean flag = true;
            if(mNumberPreviousOffsets <= 1)
                flag = false;
            return flag;
        }

        public void onAttached()
        {
        }

        public void onDetached()
        {
            hideActionPopupWindow();
        }

        protected void onDraw(Canvas canvas)
        {
            int i = mDrawable.getIntrinsicWidth();
            int j = mDrawable.getIntrinsicHeight();
            int k = mHandleExtension;
            int l = getHorizontalOffset();
            mDrawable.setBounds(l, 0, l + i, (j + k) - 1);
            mDrawable.draw(canvas);
        }

        void onHandleMoved()
        {
            hideActionPopupWindow();
        }

        protected void onMeasure(int i, int j)
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            i = 0;
            if(layout != null)
            {
                i = layout.getLineForOffset(getCurrentCursorOffset());
                i = layout.getLineBottom(i) - layout.getLineTop(i);
            }
            mHandleExtension = getHandleExtension(i);
            i = getPreferredHeight();
            j = mHandleExtension;
            setMeasuredDimension(getPreferredWidth(), i + j);
            mTouchOffsetY = (float)mHandleExtension * 0.5F;
            mIdealVerticalOffset = (float)i * 0.5F + (float)mHandleExtension;
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            motionevent.getActionMasked();
            JVM INSTR tableswitch 0 3: default 36
        //                       0 38
        //                       1 282
        //                       2 134
        //                       3 286;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return true;
_L2:
            startTouchUpFilter(getCurrentCursorOffset());
            mTouchToWindowOffsetX = motionevent.getRawX() - (float)mPositionX;
            mTouchToWindowOffsetY = motionevent.getRawY() - (float)mPositionY;
            mLastParentX = Editor._2D_wrap0(Editor.this).getPositionX();
            mLastParentY = Editor._2D_wrap0(Editor.this).getPositionY();
            mLastWindowY = Editor._2D_get6(Editor.this).getRootView().getLocationOnScreen()[1];
            mIsDragging = true;
            mPreviousLineTouched = -1;
            continue; /* Loop/switch isn't completed */
_L4:
            float f = motionevent.getRawX();
            float f1 = motionevent.getRawY();
            float f2 = mTouchToWindowOffsetY - (float)mLastParentY - (float)mLastWindowY;
            float f3 = f1 - (float)mPositionY - (float)mLastParentY - (float)mLastWindowY;
            if(f2 < mIdealVerticalOffset)
                f2 = Math.max(Math.min(f3, mIdealVerticalOffset), f2);
            else
                f2 = Math.min(Math.max(f3, mIdealVerticalOffset), f2);
            mTouchToWindowOffsetY = (float)mLastParentY + f2 + (float)mLastWindowY;
            updatePosition((f - mTouchToWindowOffsetX) + (float)mHotspotX + (float)getHorizontalOffset(), (f1 - mTouchToWindowOffsetY) + mTouchOffsetY);
            continue; /* Loop/switch isn't completed */
_L3:
            filterOnTouchUp();
_L5:
            mIsDragging = false;
            if(true) goto _L1; else goto _L6
_L6:
        }

        protected void positionAtCursorOffset(int i, boolean flag)
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            if(layout == null)
            {
                prepareCursorControllers();
                return;
            }
            int j;
            if(i != mPreviousOffset)
                j = 1;
            else
                j = 0;
            if(j != 0 || flag)
            {
                if(j != 0)
                {
                    updateSelection(i);
                    addPositionToTouchUpFilter(i);
                }
                j = layout.getLineForOffset(i);
                mPositionX = (int)((layout.getPrimaryHorizontal(i) * mHorizontalScale - (float)mHotspotX - (float)getHorizontalOffset()) + (float)getCursorOffset());
                int k = getHandleExtension(layout.getLineBottom(j) - layout.getLineTop(j));
                mPositionY = layout.getLineBottom(j) - k;
                if(k != mHandleExtension)
                    onMeasure(0, 0);
                mPositionX = (int)((float)mPositionX + (float)Editor._2D_get6(Editor.this).viewportToContentHorizontalOffset() * mHorizontalScale);
                mPositionY = mPositionY + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset();
                mPreviousOffset = i;
                mPositionHasChanged = true;
            }
        }

        public void show()
        {
            if(mContainer.isDismissing())
                mContainer.dismiss(false);
            if(isShowing())
            {
                return;
            } else
            {
                Editor._2D_wrap0(Editor.this).addSubscriber(this, true);
                mPreviousOffset = -1;
                mHorizontalScale = Editor._2D_wrap12(Editor._2D_get6(Editor.this));
                positionAtCursorOffset(getCurrentCursorOffset(), false);
                return;
            }
        }

        void showActionPopupWindow(int i)
        {
            mActionPopupWindow = getActionPopupWindow();
            if(mActionPopupShower == null)
                mActionPopupShower = new Runnable() {

                    public void run()
                    {
                        mActionPopupWindow.show();
                    }

                    final HandleView this$1;

            
            {
                this$1 = HandleView.this;
                super();
            }
                }
;
            Editor._2D_get6(Editor.this).removeCallbacks(mActionPopupShower);
            Editor._2D_get6(Editor.this).postDelayed(mActionPopupShower, i);
        }

        protected void updateDrawable()
        {
            int i = getCurrentCursorOffset();
            boolean flag = Editor._2D_get6(Editor.this).getLayout().isRtlCharAt(i);
            Drawable drawable;
            if(flag)
                drawable = mDrawableRtl;
            else
                drawable = mDrawableLtr;
            mDrawable = drawable;
            mHotspotX = getHotspotX(mDrawable, flag);
            mHorizontalGravity = getHorizontalGravity(flag);
        }

        public abstract void updatePosition(float f, float f1);

        public void updatePosition(int i, int j, boolean flag, boolean flag1)
        {
            positionAtCursorOffset(getCurrentCursorOffset(), flag1);
            if(!flag && !mPositionHasChanged) goto _L2; else goto _L1
_L1:
            if(mIsDragging)
            {
                if(i != mLastParentX || j != mLastParentY)
                {
                    mTouchToWindowOffsetX = mTouchToWindowOffsetX + (float)(i - mLastParentX);
                    mTouchToWindowOffsetY = mTouchToWindowOffsetY + (float)(j - mLastParentY);
                    mLastParentX = i;
                    mLastParentY = j;
                }
                onHandleMoved();
            }
            if(!isVisible()) goto _L4; else goto _L3
_L3:
            i += mPositionX;
            j = clipVertically(mPositionY + j);
            if(isShowing())
            {
                mContainer.update(i, j, -1, -1);
            } else
            {
                onAttached();
                mContainer.showAtLocation(Editor._2D_get6(Editor.this), 0, i, j);
            }
_L6:
            mPositionHasChanged = false;
_L2:
            return;
_L4:
            if(isShowing())
                dismiss();
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected abstract void updateSelection(int i);

        private static final int HISTORY_SIZE = 5;
        private static final int TOUCH_UP_FILTER_DELAY_AFTER = 150;
        private static final int TOUCH_UP_FILTER_DELAY_BEFORE = 350;
        private Runnable mActionPopupShower;
        protected ActionPopupWindow mActionPopupWindow;
        protected AnimatorSet mAnimationFadeIn;
        protected AnimatorSet mAnimationFadeOut;
        protected AnimatorListenerAdapter mAnimationFadeOutListener;
        protected final AnimatePopupWindow mContainer;
        protected Drawable mDrawable;
        protected Drawable mDrawableLtr;
        protected Drawable mDrawableRtl;
        private int mHandleExtension;
        protected int mHorizontalGravity;
        protected float mHorizontalScale;
        protected int mHotspotX;
        private float mIdealVerticalOffset;
        private boolean mIsDragging;
        private int mLastParentX;
        private int mLastParentY;
        private int mLastWindowY;
        private int mMinSize;
        private int mNumberPreviousOffsets;
        private boolean mPositionHasChanged;
        protected int mPositionX;
        protected int mPositionY;
        protected int mPrevLine;
        protected int mPreviousLineTouched;
        private int mPreviousOffset;
        private int mPreviousOffsetIndex;
        private final int mPreviousOffsets[] = new int[5];
        private final long mPreviousOffsetsTimes[] = new long[5];
        private float mTouchOffsetY;
        private float mTouchToWindowOffsetX;
        private float mTouchToWindowOffsetY;
        final Editor this$0;

        public HandleView(Drawable drawable, Drawable drawable1)
        {
            this$0 = Editor.this;
            super(Editor._2D_get6(Editor.this).getContext());
            mHorizontalScale = 1.0F;
            mPreviousOffset = -1;
            mPositionHasChanged = true;
            mPrevLine = -1;
            mPreviousLineTouched = -1;
            mPreviousOffsetIndex = 0;
            mNumberPreviousOffsets = 0;
            mContainer = new AnimatePopupWindow(Editor._2D_get6(Editor.this).getContext(), null);
            mContainer.setSplitTouchEnabled(true);
            mContainer.setClippingEnabled(false);
            mContainer.setWindowLayoutType(1002);
            mContainer.setContentView(this);
            mContainer.setOnDismissListener(new _cls1());
            mDrawableLtr = drawable;
            mDrawableRtl = drawable1;
            mMinSize = Editor._2D_get6(Editor.this).getContext().getResources().getDimensionPixelSize(0x110b002e);
            updateDrawable();
        }
    }

    static class InputContentType
    {

        boolean enterDown;
        Bundle extras;
        int imeActionId;
        CharSequence imeActionLabel;
        LocaleList imeHintLocales;
        int imeOptions;
        TextView.OnEditorActionListener onEditorActionListener;
        String privateImeOptions;

        InputContentType()
        {
            imeOptions = 0;
        }
    }

    static class InputMethodState
    {

        int mBatchEditNesting;
        int mChangedDelta;
        int mChangedEnd;
        int mChangedStart;
        boolean mContentChanged;
        boolean mCursorChanged;
        Rect mCursorRectInWindow;
        final ExtractedText mExtractedText = new ExtractedText();
        ExtractedTextRequest mExtractedTextRequest;
        boolean mSelectionModeChanged;
        float mTmpOffset[];
        RectF mTmpRectF;

        InputMethodState()
        {
            mCursorRectInWindow = new Rect();
            mTmpRectF = new RectF();
            mTmpOffset = new float[2];
        }
    }

    private class InsertionHandleView extends HandleView
        implements Fader
    {

        static boolean _2D_set0(InsertionHandleView insertionhandleview, boolean flag)
        {
            insertionhandleview.mReShowPopup = flag;
            return flag;
        }

        static void _2D_wrap0(InsertionHandleView insertionhandleview)
        {
            insertionhandleview.hideAfterDelay();
        }

        static void _2D_wrap1(InsertionHandleView insertionhandleview)
        {
            insertionhandleview.removeHiderCallback();
        }

        private void createAnimations()
        {
            mAnimationFadeIn = new AnimatorSet();
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, View.SCALE_X, new float[] {
                0.0F, 1.0F
            });
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[] {
                0.0F, 1.0F
            });
            mAnimationFadeIn.setInterpolator(new BackEaseOutInterpolator());
            mAnimationFadeIn.setDuration(300L);
            mAnimationFadeIn.playTogether(new Animator[] {
                objectanimator, objectanimator1
            });
            mAnimationFadeOut = new AnimatorSet();
            objectanimator1 = ObjectAnimator.ofFloat(this, View.SCALE_X, new float[] {
                1.0F, 0.0F
            });
            objectanimator = ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[] {
                1.0F, 0.0F
            });
            mAnimationFadeOut.setInterpolator(new CubicEaseOutInterpolator());
            mAnimationFadeOut.setDuration(150L);
            mAnimationFadeOut.playTogether(new Animator[] {
                objectanimator1, objectanimator
            });
            mAnimationFadeOutListener = new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    mContainer.dismiss(false);
                }

                final InsertionHandleView this$1;

            
            {
                this$1 = InsertionHandleView.this;
                super();
            }
            }
;
        }

        private void hideAfterDelay()
        {
            if(mHider == null)
                mHider = new Runnable() {

                    public void run()
                    {
                        hide();
                    }

                    final InsertionHandleView this$1;

            
            {
                this$1 = InsertionHandleView.this;
                super();
            }
                }
;
            else
                removeHiderCallback();
            Editor._2D_get6(Editor.this).postDelayed(mHider, 3000L);
        }

        private void removeHiderCallback()
        {
            if(mHider != null)
                Editor._2D_get6(Editor.this).removeCallbacks(mHider);
        }

        public void cancelAnimations()
        {
            mAnimationFadeIn.cancel();
            mAnimationFadeOut.removeAllListeners();
            mAnimationFadeOut.cancel();
            setScaleX(1.0F);
            setScaleY(1.0F);
        }

        public void fadeIn(int i, int j)
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            if(layout != null)
            {
                i = layout.getLineForOffset(getCurrentCursorOffset());
                setPivotY(layout.getLineBottom(i) - layout.getLineTop(i));
            }
            mAnimationFadeIn.start();
        }

        public void fadeOut()
        {
            mAnimationFadeOut.removeAllListeners();
            mAnimationFadeOut.addListener(mAnimationFadeOutListener);
            mAnimationFadeOut.start();
        }

        public int getCurrentCursorOffset()
        {
            return Editor._2D_get6(Editor.this).getSelectionStart();
        }

        protected int getCursorOffset()
        {
            int i = super.getCursorOffset();
            Drawable drawable;
            int j;
            if(mCursorCount > 0)
                drawable = mCursorDrawable[0];
            else
                drawable = null;
            j = i;
            if(drawable != null)
            {
                drawable.getPadding(Editor._2D_get5(Editor.this));
                j = i + (drawable.getIntrinsicWidth() - Editor._2D_get5(Editor.this).left - Editor._2D_get5(Editor.this).right) / 2;
            }
            return j;
        }

        protected int getHorizontalGravity(boolean flag)
        {
            return 1;
        }

        protected int getHotspotX(Drawable drawable, boolean flag)
        {
            return drawable.getIntrinsicWidth() / 2;
        }

        public void hide()
        {
            mReShowPopup = false;
            super.hide();
        }

        public void onAttached()
        {
            if(mReShowPopup)
            {
                showActionPopupWindow(0);
                mReShowPopup = false;
            }
            super.onAttached();
            hideAfterDelay();
        }

        public void onDetached()
        {
            super.onDetached();
            removeHiderCallback();
        }

        void onHandleMoved()
        {
            if(isPopshowing())
                mReShowPopup = true;
            super.onHandleMoved();
            removeHiderCallback();
        }

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            setPivotX(getMeasuredWidth() / 2);
            setPivotY(getMeasuredHeight() - getPreferredHeight());
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = super.onTouchEvent(motionevent);
            motionevent.getActionMasked();
            JVM INSTR tableswitch 0 3: default 40
        //                       0 42
        //                       1 61
        //                       2 40
        //                       3 224;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            return flag;
_L2:
            mDownPositionX = motionevent.getRawX();
            mDownPositionY = motionevent.getRawY();
            continue; /* Loop/switch isn't completed */
_L3:
            if(offsetHasBeenChanged()) goto _L6; else goto _L5
_L5:
            float f = mDownPositionX - motionevent.getRawX();
            float f1 = mDownPositionY - motionevent.getRawY();
            int i = ViewConfiguration.get(Editor._2D_get6(Editor.this).getContext()).getScaledTouchSlop();
            if(f * f + f1 * f1 < (float)(i * i))
                if(mActionPopupWindow != null && mActionPopupWindow.isShowing())
                {
                    mReShowPopup = false;
                    hideActionPopupWindow();
                } else
                {
                    showWithActionPopup();
                }
_L7:
            hideAfterDelay();
            continue; /* Loop/switch isn't completed */
_L6:
            if(mReShowPopup)
            {
                showActionPopupWindow(0);
                mReShowPopup = false;
            }
            if(hasSelectionController())
            {
                getSelectionController().setMinTouchOffset(getCurrentCursorOffset());
                getSelectionController().setMaxTouchOffset(getCurrentCursorOffset());
            }
            if(true) goto _L7; else goto _L4
_L4:
            hideAfterDelay();
            if(true) goto _L1; else goto _L8
_L8:
        }

        public void setY(int i)
        {
        }

        public void show()
        {
            long l;
            long l1;
            super.show();
            l = SystemClock.uptimeMillis();
            l1 = TextView.sLastCutCopyOrTextChangedTime;
            break MISSING_BLOCK_LABEL_12;
            if(Editor._2D_get0(Editor.this) != null && (Editor._2D_get4(Editor.this) == 2 || Editor._2D_get4(Editor.this) == 3 || Editor._2D_wrap3(Editor.this)))
                Editor._2D_get6(Editor.this).removeCallbacks(Editor._2D_get0(Editor.this));
            if(Editor._2D_get4(Editor.this) != 2 && Editor._2D_get4(Editor.this) != 3 && Editor._2D_wrap3(Editor.this) ^ true && l - l1 < 15000L && mTextActionMode == null)
            {
                if(Editor._2D_get0(Editor.this) == null)
                    Editor._2D_set0(Editor.this, new Runnable() {

                        public void run()
                        {
                            startInsertionActionMode();
                        }

                        final InsertionHandleView this$1;

            
            {
                this$1 = InsertionHandleView.this;
                super();
            }
                    }
);
                Editor._2D_get6(Editor.this).postDelayed(Editor._2D_get0(Editor.this), ViewConfiguration.getDoubleTapTimeout() + 1);
            }
            hideAfterDelay();
            return;
        }

        public void showWithActionPopup()
        {
            show();
            showActionPopupWindow(0);
        }

        public void updatePosition(float f, float f1)
        {
            float f2 = f;
            if(mHorizontalScale != 1.0F)
                f2 = f / mHorizontalScale;
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            int j;
            if(layout != null)
            {
                if(mPreviousLineTouched == -1)
                    mPreviousLineTouched = Editor._2D_get6(Editor.this).getLineAtCoordinate(f1);
                int i = Editor._2D_wrap13(Editor.this, layout, mPreviousLineTouched, f1);
                j = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(i, f2);
                mPreviousLineTouched = i;
            } else
            {
                j = Editor._2D_get6(Editor.this).getOffsetForPosition(f2, f1);
            }
            positionAtCursorOffset(j, false);
        }

        public void updateSelection(int i)
        {
            int j = i;
            if(i < 0)
                j = 0;
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), j);
        }

        private static final int DELAY_BEFORE_HANDLE_FADES_OUT = 3000;
        private static final int RECENT_CUT_COPY_DURATION = 15000;
        private float mDownPositionX;
        private float mDownPositionY;
        private Runnable mHider;
        private boolean mReShowPopup;
        final Editor this$0;

        public InsertionHandleView(Drawable drawable)
        {
            this$0 = Editor.this;
            super(drawable, drawable);
            createAnimations();
            mContainer.setFader(this);
        }
    }

    private class InsertionPointCursorController
        implements CursorController
    {

        static InsertionHandleView _2D_wrap0(InsertionPointCursorController insertionpointcursorcontroller)
        {
            return insertionpointcursorcontroller.getHandle();
        }

        private InsertionHandleView getHandle()
        {
            if(Editor._2D_get1(Editor.this) == null)
                Editor._2D_set1(Editor.this, Editor._2D_get6(Editor.this).getResources().getDrawable(0x110200e9));
            if(mHandle == null)
                mHandle = new InsertionHandleView(Editor._2D_get1(Editor.this));
            return mHandle;
        }

        public void hide()
        {
            if(mHandle != null)
                mHandle.hide();
        }

        public void invalidateHandle()
        {
            if(mHandle != null)
                mHandle.invalidate();
        }

        public boolean isActive()
        {
            boolean flag;
            if(mHandle != null)
                flag = mHandle.isShowing();
            else
                flag = false;
            return flag;
        }

        public boolean isCursorBeingModified()
        {
            boolean flag;
            if(mHandle != null)
                flag = mHandle.isDragging();
            else
                flag = false;
            return flag;
        }

        public void onDetached()
        {
            Editor._2D_get6(Editor.this).getViewTreeObserver().removeOnTouchModeChangeListener(this);
            if(mHandle != null)
                mHandle.onDetached();
        }

        public void onTouchModeChanged(boolean flag)
        {
            if(!flag)
                hide();
        }

        public void show()
        {
            getHandle().show();
            getHandle().setVisibility(0);
        }

        public void showWithActionPopup()
        {
            getHandle().showWithActionPopup();
            if(Editor._2D_get6(Editor.this).getText().length() == 0)
                getHandle().setVisibility(8);
            else
                getHandle().setVisibility(0);
        }

        private InsertionHandleView mHandle;
        final Editor this$0;

        private InsertionPointCursorController()
        {
            this$0 = Editor.this;
            super();
        }

        InsertionPointCursorController(InsertionPointCursorController insertionpointcursorcontroller)
        {
            this();
        }
    }

    private class PhraseAdapter extends BaseAdapter
    {

        public int getCount()
        {
            int i;
            if(mPhraseList == null)
                i = 0;
            else
                i = mPhraseList.size();
            return i;
        }

        public Object getItem(int i)
        {
            if(mPhraseList == null || i >= mPhraseList.size())
                return null;
            else
                return mPhraseList.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
                view = (TextView)mInflater.inflate(0x1103000f, viewgroup, false);
            else
                view = (TextView)view;
            view.setText((String)getItem(i));
            if(getCount() == 1)
                view.getBackground().setLevel(3);
            else
            if(i == 0)
                view.getBackground().setLevel(0);
            else
            if(i == getCount() - 1)
                view.getBackground().setLevel(2);
            else
                view.getBackground().setLevel(1);
            return view;
        }

        private LayoutInflater mInflater;
        private ArrayList mPhraseList;
        final Editor this$0;

        public PhraseAdapter(ArrayList arraylist)
        {
            this$0 = Editor.this;
            super();
            mInflater = null;
            mPhraseList = null;
            mInflater = (LayoutInflater)Editor._2D_get6(Editor.this).getContext().getSystemService("layout_inflater");
            mPhraseList = arraylist;
        }
    }

    private abstract class PinnedPopupWindow
        implements TextViewPositionListener
    {

        protected abstract int clipVertically(int i);

        protected void computeLocalPosition()
        {
            measureContent();
            int i = mContentView.getMeasuredWidth();
            int j = getTextOffset();
            mPositionX = (int)(Editor._2D_get6(Editor.this).getLayout().getPrimaryHorizontal(j) - (float)i / 2.0F);
            mPositionX = mPositionX + Editor._2D_get6(Editor.this).viewportToContentHorizontalOffset();
            mPositionY = getVerticalLocalPosition(Editor._2D_get6(Editor.this).getLayout().getLineForOffset(j));
            mPositionY = mPositionY + Editor._2D_get6(Editor.this).viewportToContentVerticalOffset();
        }

        protected abstract void createPopupWindow();

        public void dismiss()
        {
            mPopupWindow.dismiss();
        }

        protected abstract int getTextOffset();

        protected abstract int getVerticalLocalPosition(int i);

        public void hide()
        {
            dismiss();
            Editor._2D_wrap0(Editor.this).removeSubscriber(this);
        }

        protected abstract void initContentView();

        public boolean isShowing()
        {
            return mPopupWindow.isShowing();
        }

        protected void measureContent()
        {
            DisplayMetrics displaymetrics = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            mContentView.measure(android.view.View.MeasureSpec.makeMeasureSpec(displaymetrics.widthPixels, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(displaymetrics.heightPixels, 0x80000000));
        }

        public void show()
        {
            Editor._2D_wrap0(Editor.this).addSubscriber(this, false);
            computeLocalPosition();
            PositionListener positionlistener = Editor._2D_wrap0(Editor.this);
            updatePosition(positionlistener.getPositionX(), positionlistener.getPositionY());
        }

        protected void updatePosition(int i, int j)
        {
            int k = mPositionX;
            j = clipVertically(j + mPositionY);
            DisplayMetrics displaymetrics = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            int l = mContentView.getMeasuredWidth();
            i = Math.max(0, Math.min(displaymetrics.widthPixels - l, i + k));
            if(isShowing())
                mPopupWindow.update(i, j, -1, -1);
            else
                mPopupWindow.showAtLocation(Editor._2D_get6(Editor.this), 0, i, j);
        }

        public void updatePosition(int i, int j, boolean flag, boolean flag1)
        {
            if(isShowing() && Editor._2D_wrap4(Editor.this, getTextOffset()))
            {
                if(flag1)
                    computeLocalPosition();
                updatePosition(i, j);
            } else
            {
                hide();
            }
        }

        protected ViewGroup mContentView;
        protected PopupWindow mPopupWindow;
        int mPositionX;
        int mPositionY;
        final Editor this$0;

        public PinnedPopupWindow()
        {
            this$0 = Editor.this;
            super();
            createPopupWindow();
            mPopupWindow.setWindowLayoutType(1002);
            mPopupWindow.setWidth(-2);
            mPopupWindow.setHeight(-2);
            initContentView();
            editor = new android.view.ViewGroup.LayoutParams(-2, -2);
            mContentView.setLayoutParams(Editor.this);
            mPopupWindow.setContentView(mContentView);
        }
    }

    private class PositionListener
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private void updatePosition()
        {
            Editor._2D_get6(Editor.this).getLocationInWindow(mTempCoords);
            boolean flag;
            if(mTempCoords[0] != mPositionX || mTempCoords[1] != mPositionY)
                flag = true;
            else
                flag = false;
            mPositionHasChanged = flag;
            mPositionX = mTempCoords[0];
            mPositionY = mTempCoords[1];
        }

        public void addSubscriber(TextViewPositionListener textviewpositionlistener, boolean flag)
        {
            if(mNumberOfListeners == 0)
            {
                updatePosition();
                Editor._2D_get6(Editor.this).getViewTreeObserver().addOnPreDrawListener(this);
            }
            int i = -1;
            for(int j = 0; j < 6;)
            {
                TextViewPositionListener textviewpositionlistener1 = mPositionListeners[j];
                if(textviewpositionlistener1 == textviewpositionlistener)
                    return;
                int k = i;
                if(i < 0)
                {
                    k = i;
                    if(textviewpositionlistener1 == null)
                        k = j;
                }
                j++;
                i = k;
            }

            mPositionListeners[i] = textviewpositionlistener;
            mCanMove[i] = flag;
            mNumberOfListeners = mNumberOfListeners + 1;
        }

        public int getPositionX()
        {
            return mPositionX;
        }

        public int getPositionY()
        {
            return mPositionY;
        }

        public boolean onPreDraw()
        {
            updatePosition();
            for(int i = 0; i < 6; i++)
            {
                if(!mPositionHasChanged && !mScrollHasChanged && !mCanMove[i])
                    continue;
                TextViewPositionListener textviewpositionlistener = mPositionListeners[i];
                if(textviewpositionlistener != null)
                    textviewpositionlistener.updatePosition(mPositionX, mPositionY, mPositionHasChanged, mScrollHasChanged);
            }

            mScrollHasChanged = false;
            return true;
        }

        public void onScrollChanged()
        {
            mScrollHasChanged = true;
        }

        public void removeSubscriber(TextViewPositionListener textviewpositionlistener)
        {
            int i = 0;
            do
            {
label0:
                {
                    if(i < 6)
                    {
                        if(mPositionListeners[i] != textviewpositionlistener)
                            break label0;
                        mPositionListeners[i] = null;
                        mNumberOfListeners = mNumberOfListeners - 1;
                    }
                    if(mNumberOfListeners == 0)
                        Editor._2D_get6(Editor.this).getViewTreeObserver().removeOnPreDrawListener(this);
                    return;
                }
                i++;
            } while(true);
        }

        private final int MAXIMUM_NUMBER_OF_LISTENERS;
        private boolean mCanMove[];
        private int mNumberOfListeners;
        private boolean mPositionHasChanged;
        private TextViewPositionListener mPositionListeners[];
        private int mPositionX;
        private int mPositionY;
        private boolean mScrollHasChanged;
        final int mTempCoords[];
        final Editor this$0;

        private PositionListener()
        {
            this$0 = Editor.this;
            super();
            MAXIMUM_NUMBER_OF_LISTENERS = 6;
            mPositionListeners = new TextViewPositionListener[6];
            mCanMove = new boolean[6];
            mPositionHasChanged = true;
            mTempCoords = new int[2];
        }

        PositionListener(PositionListener positionlistener)
        {
            this();
        }
    }

    static final class ProcessTextIntentActionsHandler
    {

        public void initializeAccessibilityActions()
        {
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
        }

        public boolean performAccessibilityAction(int i)
        {
            return false;
        }

        private ProcessTextIntentActionsHandler(Editor editor)
        {
        }

        ProcessTextIntentActionsHandler(Editor editor, ProcessTextIntentActionsHandler processtextintentactionshandler)
        {
            this(editor);
        }
    }

    private class SelectionEndHandleView extends SelectionHandleView
    {

        private void positionAndAdjustForCrossingHandles(int i)
        {
            int j = Editor._2D_get6(Editor.this).getSelectionStart();
            int k = i;
            if(i <= j)
                k = Math.min(j + 1, Editor._2D_get6(Editor.this).getText().length());
            positionAtCursorOffset(k, false);
        }

        public int getCurrentCursorOffset()
        {
            return Editor._2D_get6(Editor.this).getSelectionEnd();
        }

        protected int getHorizontalGravity(boolean flag)
        {
            byte byte0;
            if(flag)
                byte0 = 5;
            else
                byte0 = 3;
            return byte0;
        }

        protected int getHotspotX(Drawable drawable, boolean flag)
        {
            if(flag)
                return (drawable.getIntrinsicWidth() * 3) / 4;
            else
                return drawable.getIntrinsicWidth() / 4;
        }

        public void updatePosition(float f, float f1)
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            if(layout == null)
            {
                positionAndAdjustForCrossingHandles(Editor._2D_get6(Editor.this).getOffsetForPosition(f, f1));
                return;
            }
            if(mPreviousLineTouched == -1)
                mPreviousLineTouched = Editor._2D_get6(Editor.this).getLineAtCoordinate(f1);
            int i = Editor._2D_get6(Editor.this).getSelectionStart();
            int j = Editor._2D_wrap13(Editor.this, layout, mPreviousLineTouched, f1);
            int k = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(j, f);
            int l = k;
            if(k <= i)
            {
                j = layout.getLineForOffset(i);
                l = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(j, f);
            }
            mPreviousLineTouched = j;
            positionAndAdjustForCrossingHandles(l);
        }

        public void updateSelection(int i)
        {
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), Editor._2D_get6(Editor.this).getSelectionStart(), i);
            updateDrawable();
        }

        final Editor this$0;

        public SelectionEndHandleView(Drawable drawable, Drawable drawable1)
        {
            this$0 = Editor.this;
            super(drawable, drawable1);
        }
    }

    private abstract class SelectionHandleView extends HandleView
        implements Fader
    {

        static int _2D_get0(SelectionHandleView selectionhandleview)
        {
            return selectionhandleview.mY;
        }

        static int _2D_set0(SelectionHandleView selectionhandleview, int i)
        {
            selectionhandleview.mY = i;
            return i;
        }

        public void cancelAnimations()
        {
            if(mAnimationFadeIn != null)
                mAnimationFadeIn.cancel();
            setScaleX(1.0F);
            setTranslationX(0.0F);
        }

        public void fadeIn(int i, int j)
        {
            float f = i;
            float f1 = mTranslation;
            float f2 = mHorizontalScale;
            mY = -1;
            mAnimationFadeIn = new AnimatorSet();
            mAnimationFadeIn.setInterpolator(new CubicEaseOutInterpolator());
            mAnimationFadeIn.setDuration(300L);
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, View.SCALE_X, new float[] {
                0.0F, 1.0F
            });
            if(mTranslation != 0.0F)
            {
                ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                    f + f1 * f2, (float)i
                });
                valueanimator.addUpdateListener(j. new android.animation.ValueAnimator.AnimatorUpdateListener() {

                    public void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
                        if(SelectionHandleView._2D_get0(SelectionHandleView.this) == -1)
                            SelectionHandleView._2D_set0(SelectionHandleView.this, locationY);
                        mContainer.update((int)f, SelectionHandleView._2D_get0(SelectionHandleView.this), -1, -1);
                    }

                    final SelectionHandleView this$1;
                    final int val$locationY;

            
            {
                this$1 = final_selectionhandleview;
                locationY = I.this;
                super();
            }
                }
);
                mAnimationFadeIn.playTogether(new Animator[] {
                    objectanimator, valueanimator
                });
                mTranslation = 0.0F;
            } else
            {
                mAnimationFadeIn.playTogether(new Animator[] {
                    objectanimator
                });
            }
            mAnimationFadeIn.start();
        }

        public void fadeOut()
        {
            mContainer.dismiss();
        }

        public ActionPopupWindow getActionPopupWindow()
        {
            if(mActionPopupWindow == null)
                mActionPopupWindow = new SelectionPopupWindow(this);
            return mActionPopupWindow;
        }

        public void onAttached()
        {
            showActionPopupWindow(0);
        }

        public void onDetached()
        {
            if(hasSelectionController() && SelectionModifierCursorController._2D_get1(getSelectionController()) ^ true)
                hideActionPopupWindow();
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            boolean flag = super.onTouchEvent(motionevent);
            motionevent.getActionMasked();
            JVM INSTR tableswitch 1 1: default 28
        //                       1 30;
               goto _L1 _L2
_L1:
            return flag;
_L2:
            showActionPopupWindow(300);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void setActionPopupWindow(ActionPopupWindow actionpopupwindow)
        {
            mActionPopupWindow = actionpopupwindow;
        }

        public void setTranslation(float f)
        {
            mTranslation = f;
        }

        public void setY(int i)
        {
            mY = i;
        }

        private float mTranslation;
        private int mY;
        final Editor this$0;

        public SelectionHandleView(Drawable drawable, Drawable drawable1)
        {
            this$0 = Editor.this;
            super(drawable, drawable1);
            mY = -1;
            setPivotX(mHotspotX + getHorizontalOffset());
            mContainer.setFader(this);
        }
    }

    public class SelectionModifierCursorController
        implements CursorController
    {

        static boolean _2D_get0(SelectionModifierCursorController selectionmodifiercursorcontroller)
        {
            return selectionmodifiercursorcontroller.mInSwipeSelectionMode;
        }

        static boolean _2D_get1(SelectionModifierCursorController selectionmodifiercursorcontroller)
        {
            return selectionmodifiercursorcontroller.mTextSelectionModeEnable;
        }

        private void initDrawables()
        {
            if(Editor._2D_get2(Editor.this) == null)
                Editor._2D_set2(Editor.this, Editor._2D_get6(Editor.this).getContext().getResources().getDrawable(0x110200e8));
            if(Editor._2D_get3(Editor.this) == null)
                Editor._2D_set3(Editor.this, Editor._2D_get6(Editor.this).getContext().getResources().getDrawable(0x110200ea));
        }

        private void initHandles()
        {
            if(mStartHandle == null)
                mStartHandle = new SelectionStartHandleView(Editor._2D_get2(Editor.this), Editor._2D_get3(Editor.this));
            if(mEndHandle == null)
                mEndHandle = new SelectionEndHandleView(Editor._2D_get3(Editor.this), Editor._2D_get2(Editor.this));
            mStartHandle.setTranslation(mTranslationCache[0]);
            mEndHandle.setTranslation(mTranslationCache[1]);
            mStartHandle.show();
            mEndHandle.show();
            mStartHandle.showActionPopupWindow(200);
            mEndHandle.setActionPopupWindow(mStartHandle.getActionPopupWindow());
            hideInsertionPointCursorController();
        }

        private void requestDisallowInterceptTouchEvent(boolean flag)
        {
            Editor._2D_get6(Editor.this).getParent().requestDisallowInterceptTouchEvent(flag);
        }

        private void resetDragAcceleratorState()
        {
            mStartOffset = -1;
            mDragAcceleratorMode = 0;
            mSwitchedLines = false;
            int i = Editor._2D_get6(Editor.this).getSelectionStart();
            int j = Editor._2D_get6(Editor.this).getSelectionEnd();
            if(i > j)
                Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), j, i);
        }

        private boolean selectCurrentParagraphAndStartDrag()
        {
            if(Editor._2D_get0(Editor.this) != null)
                Editor._2D_get6(Editor.this).removeCallbacks(Editor._2D_get0(Editor.this));
            stopTextActionMode();
            if(!Editor._2D_wrap8(Editor.this))
            {
                return false;
            } else
            {
                enterDrag(3);
                return true;
            }
        }

        private void updateCharacterBasedSelection(MotionEvent motionevent)
        {
            int i = Editor._2D_get6(Editor.this).getOffsetForPosition(motionevent.getX(), motionevent.getY());
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), mStartOffset, i);
        }

        private void updateMinAndMaxOffsets(MotionEvent motionevent)
        {
            int i = motionevent.getPointerCount();
            for(int j = 0; j < i; j++)
            {
                int k = Editor._2D_get6(Editor.this).getOffsetForPosition(motionevent.getX(j), motionevent.getY(j));
                if(k < mMinTouchOffset)
                    mMinTouchOffset = k;
                if(k > mMaxTouchOffset)
                    mMaxTouchOffset = k;
            }

        }

        private void updateParagraphBasedSelection(MotionEvent motionevent)
        {
            int i = Editor._2D_get6(Editor.this).getOffsetForPosition(motionevent.getX(), motionevent.getY());
            int j = Math.min(i, mStartOffset);
            i = Math.max(i, mStartOffset);
            long l = Editor._2D_wrap17(Editor.this, j, i);
            i = TextUtils.unpackRangeStartFromLong(l);
            j = TextUtils.unpackRangeEndFromLong(l);
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), i, j);
        }

        private void updateSelection(MotionEvent motionevent)
        {
            if(Editor._2D_get6(Editor.this).getLayout() == null) goto _L2; else goto _L1
_L1:
            mDragAcceleratorMode;
            JVM INSTR tableswitch 1 3: default 44
        //                       1 45
        //                       2 53
        //                       3 61;
               goto _L2 _L3 _L4 _L5
_L2:
            return;
_L3:
            updateCharacterBasedSelection(motionevent);
            continue; /* Loop/switch isn't completed */
_L4:
            updateWordBasedSelection(motionevent);
            continue; /* Loop/switch isn't completed */
_L5:
            updateParagraphBasedSelection(motionevent);
            if(true) goto _L2; else goto _L6
_L6:
        }

        private void updateWordBasedSelection(MotionEvent motionevent)
        {
            if(mHaventMovedEnoughToStartDrag)
                return;
            boolean flag = motionevent.isFromSource(8194);
            ViewConfiguration viewconfiguration = ViewConfiguration.get(Editor._2D_get6(Editor.this).getContext());
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            int i;
            int j;
            int k;
            if(flag)
            {
                i = Editor._2D_get6(Editor.this).getLineAtCoordinate(f1);
            } else
            {
                float f2 = f1;
                if(mSwitchedLines)
                {
                    j = viewconfiguration.getScaledTouchSlop();
                    if(mStartHandle != null)
                        f2 = mStartHandle.getIdealVerticalOffset();
                    else
                        f2 = j;
                    f2 = f1 - f2;
                }
                j = Editor._2D_wrap13(Editor.this, Editor._2D_get6(Editor.this).getLayout(), mLineSelectionIsOn, f2);
                i = j;
                if(!mSwitchedLines)
                {
                    i = j;
                    if(j != mLineSelectionIsOn)
                    {
                        mSwitchedLines = true;
                        return;
                    }
                }
            }
            j = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(i, f);
            if(mStartOffset < j)
            {
                j = Editor._2D_wrap15(Editor.this, j);
                k = Editor._2D_wrap16(Editor.this, mStartOffset);
            } else
            {
                int l = Editor._2D_wrap16(Editor.this, j);
                int i1 = Editor._2D_wrap15(Editor.this, mStartOffset);
                j = l;
                k = i1;
                if(i1 == l)
                {
                    j = Editor._2D_wrap14(Editor.this, l, false);
                    k = i1;
                }
            }
            mLineSelectionIsOn = i;
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), k, j);
        }

        public void enterDrag(int i)
        {
            show();
            mDragAcceleratorMode = i;
            mStartOffset = Editor._2D_get6(Editor.this).getOffsetForPosition(mLastDownPositionX, mLastDownPositionY);
            mLineSelectionIsOn = Editor._2D_get6(Editor.this).getLineAtCoordinate(mLastDownPositionY);
            hide();
            Editor._2D_get6(Editor.this).getParent().requestDisallowInterceptTouchEvent(true);
            Editor._2D_get6(Editor.this).cancelLongPress();
        }

        public int getMaxTouchOffset()
        {
            return mMaxTouchOffset;
        }

        public int getMinTouchOffset()
        {
            return mMinTouchOffset;
        }

        public void hide()
        {
            mTextSelectionModeEnable = false;
            if(mStartHandle != null)
                mStartHandle.hide();
            if(mEndHandle != null)
                mEndHandle.hide();
        }

        public void invalidateHandles()
        {
            if(mStartHandle != null)
                mStartHandle.invalidate();
            if(mEndHandle != null)
                mEndHandle.invalidate();
        }

        public boolean isActive()
        {
            boolean flag;
            if(mStartHandle != null)
                flag = mStartHandle.isShowing();
            else
                flag = false;
            return flag;
        }

        public boolean isCursorBeingModified()
        {
            boolean flag;
            if(!isDragAcceleratorActive() && !isSelectionStartDragged())
            {
                if(mEndHandle != null)
                    flag = mEndHandle.isDragging();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public boolean isDragAcceleratorActive()
        {
            boolean flag = false;
            if(mDragAcceleratorMode != 0)
                flag = true;
            return flag;
        }

        public boolean isSelectionStartDragged()
        {
            boolean flag;
            if(mStartHandle != null)
                flag = mStartHandle.isDragging();
            else
                flag = false;
            return flag;
        }

        public void onDetached()
        {
            Editor._2D_get6(Editor.this).getViewTreeObserver().removeOnTouchModeChangeListener(this);
            if(mStartHandle != null)
                mStartHandle.hideActionPopupWindow();
            if(mEndHandle != null)
                mEndHandle.hideActionPopupWindow();
        }

        public void onTouchEvent(MotionEvent motionevent)
        {
            boolean flag;
            float f;
            float f1;
            flag = motionevent.isFromSource(8194);
            f = motionevent.getX();
            f1 = motionevent.getY();
            motionevent.getActionMasked();
            JVM INSTR tableswitch 0 6: default 64
        //                       0 70
        //                       1 659
        //                       2 362
        //                       3 64
        //                       4 64
        //                       5 332
        //                       6 332;
               goto _L1 _L2 _L3 _L4 _L1 _L1 _L5 _L5
_L1:
            requestDisallowInterceptTouchEvent(false);
_L6:
            return;
_L2:
            mInSwipeSelectionMode = false;
            if(extractedTextModeWillBeStarted())
            {
                hide();
            } else
            {
                int i = Editor._2D_get6(Editor.this).getOffsetForPosition(f, f1);
                mMaxTouchOffset = i;
                mMinTouchOffset = i;
                if(mGestureStayedInTapRegion && (Editor._2D_get4(Editor.this) == 2 || Editor._2D_get4(Editor.this) == 3))
                {
                    float f2 = f - mDownPositionX;
                    float f4 = f1 - mDownPositionY;
                    int j = ViewConfiguration.get(Editor._2D_get6(Editor.this).getContext()).getScaledDoubleTapSlop();
                    if(f2 * f2 + f4 * f4 < (float)(j * j))
                        j = 1;
                    else
                        j = 0;
                    if(j && (flag || Editor._2D_wrap5(Editor.this, f, f1)))
                    {
                        if(Editor._2D_get4(Editor.this) == 2)
                            Editor._2D_wrap9(Editor.this);
                        else
                        if(Editor._2D_get4(Editor.this) == 3)
                            selectCurrentParagraphAndStartDrag();
                        mSwipeSelectionStart = Editor._2D_get6(Editor.this).getOffsetForPosition(f, f1);
                        mInSwipeSelectionMode = true;
                        mDoubleTabed = true;
                        mDiscardNextActionUp = true;
                    }
                }
                mDownPositionX = f;
                mDownPositionY = f1;
                mGestureStayedInTapRegion = true;
                mHaventMovedEnoughToStartDrag = true;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if(Editor._2D_get6(Editor.this).getContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct"))
                updateMinAndMaxOffsets(motionevent);
            continue; /* Loop/switch isn't completed */
_L4:
            if(mGestureStayedInTapRegion || mHaventMovedEnoughToStartDrag)
            {
                float f3 = f - mDownPositionX;
                float f5 = f1 - mDownPositionY;
                f3 = f3 * f3 + f5 * f5;
                ViewConfiguration viewconfiguration = ViewConfiguration.get(Editor._2D_get6(Editor.this).getContext());
                int k = viewconfiguration.getScaledDoubleTapTouchSlop();
                int i1 = viewconfiguration.getScaledTouchSlop();
                boolean flag1;
                if(mGestureStayedInTapRegion)
                {
                    if(f3 <= (float)(k * k))
                        flag1 = true;
                    else
                        flag1 = false;
                    mGestureStayedInTapRegion = flag1;
                }
                if(mHaventMovedEnoughToStartDrag)
                {
                    if(f3 <= (float)(i1 * i1))
                        flag1 = true;
                    else
                        flag1 = false;
                    mHaventMovedEnoughToStartDrag = flag1;
                }
            }
            if(flag && isDragAcceleratorActive() ^ true)
            {
                int l = Editor._2D_get6(Editor.this).getOffsetForPosition(f, f1);
                if(Editor._2D_get6(Editor.this).hasSelection() && (!mHaventMovedEnoughToStartDrag || mStartOffset != l) && l >= Editor._2D_get6(Editor.this).getSelectionStart() && l <= Editor._2D_get6(Editor.this).getSelectionEnd())
                {
                    Editor._2D_wrap20(Editor.this);
                    continue; /* Loop/switch isn't completed */
                }
                if(mStartOffset != l)
                {
                    stopTextActionMode();
                    enterDrag(1);
                    mDiscardNextActionUp = true;
                    mHaventMovedEnoughToStartDrag = false;
                }
            }
            if(mStartHandle == null || !mStartHandle.isShowing())
                updateSelection(motionevent);
            if(true) goto _L6; else goto _L3
_L3:
            mPreviousTapUpTime = SystemClock.uptimeMillis();
            if(!isDragAcceleratorActive()) goto _L6; else goto _L7
_L7:
            updateSelection(motionevent);
            Editor._2D_get6(Editor.this).getParent().requestDisallowInterceptTouchEvent(false);
            resetDragAcceleratorState();
            if(Editor._2D_get6(Editor.this).hasSelection() || Editor._2D_get4(Editor.this) == 2)
                startSelectionActionModeAsync(mHaventMovedEnoughToStartDrag);
            if(true) goto _L1; else goto _L8
_L8:
        }

        public void onTouchModeChanged(boolean flag)
        {
            if(!flag)
                hide();
        }

        public void resetTouchOffsets()
        {
            mMaxTouchOffset = -1;
            mMinTouchOffset = -1;
            resetDragAcceleratorState();
        }

        public void setMaxTouchOffset(int i)
        {
            mMaxTouchOffset = i;
        }

        public void setMinTouchOffset(int i)
        {
            mMinTouchOffset = i;
        }

        public void setTranslationCache(float af[])
        {
            mTranslationCache = af;
        }

        public void show()
        {
            if(Editor._2D_get6(Editor.this).isInBatchEditMode())
            {
                return;
            } else
            {
                initDrawables();
                initHandles();
                mTextSelectionModeEnable = true;
                return;
            }
        }

        private static final int DELAY_BEFORE_REPLACE_ACTION = 200;
        private static final int DRAG_ACCELERATOR_MODE_CHARACTER = 1;
        private static final int DRAG_ACCELERATOR_MODE_INACTIVE = 0;
        private static final int DRAG_ACCELERATOR_MODE_PARAGRAPH = 3;
        private static final int DRAG_ACCELERATOR_MODE_WORD = 2;
        private boolean mDoubleTabed;
        private float mDownPositionX;
        private float mDownPositionY;
        private int mDragAcceleratorMode;
        private SelectionEndHandleView mEndHandle;
        private boolean mGestureStayedInTapRegion;
        private boolean mHaventMovedEnoughToStartDrag;
        private boolean mInSwipeSelectionMode;
        private int mLineSelectionIsOn;
        private int mMaxTouchOffset;
        private int mMinTouchOffset;
        private long mPreviousTapUpTime;
        private SelectionStartHandleView mStartHandle;
        private int mStartOffset;
        private int mSwipeSelectionStart;
        private boolean mSwitchedLines;
        private boolean mTextSelectionModeEnable;
        private float mTranslationCache[] = {
            0.0F, 0.0F
        };
        final Editor this$0;

        SelectionModifierCursorController()
        {
            this$0 = Editor.this;
            super();
            mPreviousTapUpTime = 0L;
            mStartOffset = -1;
            mLineSelectionIsOn = -1;
            mSwitchedLines = false;
            mDragAcceleratorMode = 0;
            resetTouchOffsets();
        }
    }

    private class SelectionPopupWindow extends ActionPopupWindow
    {

        protected void createAnimations()
        {
            mAnimationFadeIn = new AnimatorSet();
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(mContentView, View.SCALE_X, new float[] {
                0.5F, 1.0F
            });
            mAnimationFadeIn.setInterpolator(new CubicEaseOutInterpolator());
            mAnimationFadeIn.setDuration(200L);
            mAnimationFadeIn.playTogether(new Animator[] {
                objectanimator
            });
            mLayoutAnimationController = new LayoutAnimationController(AnimationUtils.loadAnimation(Editor._2D_get6(Editor.this).getContext(), 0x11040007));
            mLayoutAnimationController.setInterpolator(new CubicEaseOutInterpolator());
            mLayoutAnimationController.setDelay(0.05F);
        }

        public void fadeIn(int i, int j)
        {
            mContentView.setPivotX(mContentView.getMeasuredWidth() / 2);
            mContentView.setPivotY(mContentView.getMeasuredHeight() / 2);
            mAnimationFadeIn.start();
            mMainPanel.setLayoutAnimation(mLayoutAnimationController);
        }

        private LayoutAnimationController mLayoutAnimationController;
        final Editor this$0;

        public SelectionPopupWindow(HandleView handleview)
        {
            this$0 = Editor.this;
            super(handleview);
        }
    }

    private class SelectionStartHandleView extends SelectionHandleView
    {

        private void positionAndAdjustForCrossingHandles(int i)
        {
            int j = Editor._2D_get6(Editor.this).getSelectionEnd();
            int k = i;
            if(i >= j)
                k = Math.max(0, j - 1);
            positionAtCursorOffset(k, false);
        }

        public int getCurrentCursorOffset()
        {
            return Editor._2D_get6(Editor.this).getSelectionStart();
        }

        protected int getHorizontalGravity(boolean flag)
        {
            byte byte0;
            if(flag)
                byte0 = 3;
            else
                byte0 = 5;
            return byte0;
        }

        protected int getHotspotX(Drawable drawable, boolean flag)
        {
            if(flag)
                return drawable.getIntrinsicWidth() / 4;
            else
                return (drawable.getIntrinsicWidth() * 3) / 4;
        }

        public void updatePosition(float f, float f1)
        {
            Layout layout = Editor._2D_get6(Editor.this).getLayout();
            if(layout == null)
            {
                positionAndAdjustForCrossingHandles(Editor._2D_get6(Editor.this).getOffsetForPosition(f, f1));
                return;
            }
            if(mPreviousLineTouched == -1)
                mPreviousLineTouched = Editor._2D_get6(Editor.this).getLineAtCoordinate(f1);
            int i = Editor._2D_get6(Editor.this).getSelectionEnd();
            int j = Editor._2D_wrap13(Editor.this, layout, mPreviousLineTouched, f1);
            int k = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(j, f);
            int l = k;
            if(k >= i)
            {
                j = layout.getLineForOffset(i);
                l = Editor._2D_get6(Editor.this).getOffsetAtCoordinate(j, f);
            }
            mPreviousLineTouched = j;
            positionAndAdjustForCrossingHandles(l);
        }

        public void updateSelection(int i)
        {
            int j = i;
            if(i < 0)
                j = 0;
            Selection.setSelection((Spannable)Editor._2D_get6(Editor.this).getText(), j, Editor._2D_get6(Editor.this).getSelectionEnd());
            updateDrawable();
        }

        final Editor this$0;

        public SelectionStartHandleView(Drawable drawable, Drawable drawable1)
        {
            this$0 = Editor.this;
            super(drawable, drawable1);
        }
    }

    class SpanController
        implements SpanWatcher
    {

        static void _2D_wrap0(SpanController spancontroller, int i, EasyEditSpan easyeditspan)
        {
            spancontroller.sendEasySpanNotification(i, easyeditspan);
        }

        private boolean isNonIntermediateSelectionSpan(Spannable spannable, Object obj)
        {
            boolean flag1;
label0:
            {
                boolean flag = false;
                if(Selection.SELECTION_START != obj)
                {
                    flag1 = flag;
                    if(Selection.SELECTION_END != obj)
                        break label0;
                }
                flag1 = flag;
                if((spannable.getSpanFlags(obj) & 0x200) == 0)
                    flag1 = true;
            }
            return flag1;
        }

        private void sendEasySpanNotification(int i, EasyEditSpan easyeditspan)
        {
            easyeditspan = easyeditspan.getPendingIntent();
            if(easyeditspan == null)
                break MISSING_BLOCK_LABEL_41;
            Intent intent = JVM INSTR new #63  <Class Intent>;
            intent.Intent();
            intent.putExtra("android.text.style.EXTRA_TEXT_CHANGED_TYPE", i);
            easyeditspan.send(Editor._2D_get6(Editor.this).getContext(), 0, intent);
_L1:
            return;
            easyeditspan;
            Log.w("Editor", "PendingIntent for notification cannot be sent", easyeditspan);
              goto _L1
        }

        public void hide()
        {
            if(mPopupWindow != null)
            {
                mPopupWindow.hide();
                Editor._2D_get6(Editor.this).removeCallbacks(mHidePopup);
            }
        }

        public void onSpanAdded(Spannable spannable, Object obj, int i, int j)
        {
            if(!isNonIntermediateSelectionSpan(spannable, obj)) goto _L2; else goto _L1
_L1:
            Editor._2D_wrap18(Editor.this);
_L4:
            return;
_L2:
            if(obj instanceof EasyEditSpan)
            {
                if(mPopupWindow == null)
                {
                    mPopupWindow = new EasyEditPopupWindow(null);
                    mHidePopup = new Runnable() {

                        public void run()
                        {
                            hide();
                        }

                        final SpanController this$1;

            
            {
                this$1 = SpanController.this;
                super();
            }
                    }
;
                }
                if(EasyEditPopupWindow._2D_get0(mPopupWindow) != null)
                    EasyEditPopupWindow._2D_get0(mPopupWindow).setDeleteEnabled(false);
                mPopupWindow.setEasyEditSpan((EasyEditSpan)obj);
                EasyEditPopupWindow._2D_wrap0(mPopupWindow, new EasyEditDeleteListener() {

                    public void onDeleteClick(EasyEditSpan easyeditspan)
                    {
                        Editable editable = (Editable)Editor._2D_get6(_fld0).getText();
                        int i = editable.getSpanStart(easyeditspan);
                        int j = editable.getSpanEnd(easyeditspan);
                        if(i >= 0 && j >= 0)
                        {
                            SpanController._2D_wrap0(SpanController.this, 1, easyeditspan);
                            Editor._2D_get6(_fld0).deleteText_internal(i, j);
                        }
                        editable.removeSpan(easyeditspan);
                    }

                    final SpanController this$1;

            
            {
                this$1 = SpanController.this;
                super();
            }
                }
);
                if(Editor._2D_get6(Editor.this).getWindowVisibility() != 0)
                    return;
                if(Editor._2D_get6(Editor.this).getLayout() == null)
                    return;
                if(extractedTextModeWillBeStarted())
                    return;
                mPopupWindow.show();
                Editor._2D_get6(Editor.this).removeCallbacks(mHidePopup);
                Editor._2D_get6(Editor.this).postDelayed(mHidePopup, 3000L);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
        {
            if(!isNonIntermediateSelectionSpan(spannable, obj)) goto _L2; else goto _L1
_L1:
            Editor._2D_wrap18(Editor.this);
_L4:
            return;
_L2:
            if(mPopupWindow != null && (obj instanceof EasyEditSpan))
            {
                obj = (EasyEditSpan)obj;
                sendEasySpanNotification(2, ((EasyEditSpan) (obj)));
                spannable.removeSpan(obj);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
        {
            if(!isNonIntermediateSelectionSpan(spannable, obj)) goto _L2; else goto _L1
_L1:
            Editor._2D_wrap18(Editor.this);
_L4:
            return;
_L2:
            if(mPopupWindow != null && obj == EasyEditPopupWindow._2D_get0(mPopupWindow))
                hide();
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final int DISPLAY_TIMEOUT_MS = 3000;
        private Runnable mHidePopup;
        private EasyEditPopupWindow mPopupWindow;
        final Editor this$0;

        SpanController()
        {
            this$0 = Editor.this;
            super();
        }
    }

    private class SuggestionsPopupWindow extends PinnedPopupWindow
        implements AdapterView.OnItemClickListener
    {

        static boolean _2D_get0(SuggestionsPopupWindow suggestionspopupwindow)
        {
            return suggestionspopupwindow.mCursorWasVisibleBeforeSuggestions;
        }

        static int _2D_get1(SuggestionsPopupWindow suggestionspopupwindow)
        {
            return suggestionspopupwindow.mNumberOfSuggestions;
        }

        static HashMap _2D_get2(SuggestionsPopupWindow suggestionspopupwindow)
        {
            return suggestionspopupwindow.mSpansLengths;
        }

        static SuggestionInfo[] _2D_get3(SuggestionsPopupWindow suggestionspopupwindow)
        {
            return suggestionspopupwindow.mSuggestionInfos;
        }

        private SuggestionSpan[] getSuggestionSpans()
        {
            int i = Editor._2D_get6(Editor.this).getSelectionStart();
            Spannable spannable = (Spannable)Editor._2D_get6(Editor.this).getText();
            SuggestionSpan asuggestionspan[] = (SuggestionSpan[])spannable.getSpans(i, i, android/text/style/SuggestionSpan);
            mSpansLengths.clear();
            i = 0;
            for(int j = asuggestionspan.length; i < j; i++)
            {
                SuggestionSpan suggestionspan = asuggestionspan[i];
                int k = spannable.getSpanStart(suggestionspan);
                int l = spannable.getSpanEnd(suggestionspan);
                mSpansLengths.put(suggestionspan, Integer.valueOf(l - k));
            }

            Arrays.sort(asuggestionspan, mSuggestionSpanComparator);
            return asuggestionspan;
        }

        private void highlightTextDifferences(SuggestionInfo suggestioninfo, int i, int j)
        {
            Object obj = (Spannable)Editor._2D_get6(Editor.this).getText();
            int k = ((Spannable) (obj)).getSpanStart(suggestioninfo.suggestionSpan);
            int l = ((Spannable) (obj)).getSpanEnd(suggestioninfo.suggestionSpan);
            suggestioninfo.suggestionStart = k - i;
            suggestioninfo.suggestionEnd = suggestioninfo.suggestionStart + suggestioninfo.text.length();
            suggestioninfo.text.setSpan(suggestioninfo.highlightSpan, 0, suggestioninfo.text.length(), 33);
            obj = ((Spannable) (obj)).toString();
            suggestioninfo.text.insert(0, ((String) (obj)).substring(i, k));
            suggestioninfo.text.append(((String) (obj)).substring(l, j));
        }

        private boolean updateSuggestions()
        {
            Spannable spannable;
            SuggestionSpan asuggestionspan[];
            int i;
            int j;
            int k;
            Object obj;
            int l;
            int i1;
            spannable = (Spannable)Editor._2D_get6(Editor.this).getText();
            asuggestionspan = getSuggestionSpans();
            i = asuggestionspan.length;
            if(i == 0)
                return false;
            mNumberOfSuggestions = 0;
            j = Editor._2D_get6(Editor.this).getText().length();
            k = 0;
            obj = null;
            l = 0;
            i1 = 0;
_L14:
            if(i1 >= i) goto _L2; else goto _L1
_L1:
            SuggestionSpan suggestionspan;
            int i2;
            int j2;
            int k2;
            int l2;
            String as[];
            int i3;
            suggestionspan = asuggestionspan[i1];
            i2 = spannable.getSpanStart(suggestionspan);
            j2 = spannable.getSpanEnd(suggestionspan);
            k2 = Math.min(i2, j);
            l2 = Math.max(j2, k);
            if((suggestionspan.getFlags() & 2) != 0)
                obj = suggestionspan;
            if(i1 == 0)
                l = suggestionspan.getUnderlineColor();
            as = suggestionspan.getSuggestions();
            i3 = as.length;
            j = 0;
_L12:
            k = i1;
            if(j >= i3) goto _L4; else goto _L3
_L3:
            String s;
            boolean flag;
            s = as[j];
            flag = false;
            k = 0;
_L11:
            int j3 = ((flag) ? 1 : 0);
            if(k >= mNumberOfSuggestions) goto _L6; else goto _L5
_L5:
            if(!mSuggestionInfos[k].text.toString().equals(s)) goto _L8; else goto _L7
_L7:
            int l3;
            SuggestionSpan suggestionspan1 = mSuggestionInfos[k].suggestionSpan;
            l3 = spannable.getSpanStart(suggestionspan1);
            j3 = spannable.getSpanEnd(suggestionspan1);
            if(i2 != l3 || j2 != j3) goto _L8; else goto _L9
_L9:
            j3 = 1;
_L6:
            if(j3 != 0)
                break; /* Loop/switch isn't completed */
            SuggestionInfo suggestioninfo1 = mSuggestionInfos[mNumberOfSuggestions];
            suggestioninfo1.suggestionSpan = suggestionspan;
            suggestioninfo1.suggestionIndex = j;
            suggestioninfo1.text.replace(0, suggestioninfo1.text.length(), s);
            mNumberOfSuggestions = mNumberOfSuggestions + 1;
            if(mNumberOfSuggestions != 5)
                break; /* Loop/switch isn't completed */
            k = i;
_L4:
            i1 = k + 1;
            k = l2;
            j = k2;
            continue; /* Loop/switch isn't completed */
_L8:
            k++;
            if(true) goto _L11; else goto _L10
_L10:
            j++;
            if(true) goto _L12; else goto _L2
_L2:
            for(int j1 = 0; j1 < mNumberOfSuggestions; j1++)
                highlightTextDifferences(mSuggestionInfos[j1], j, k);

            if(obj != null)
            {
                int k3 = spannable.getSpanStart(obj);
                int k1 = spannable.getSpanEnd(obj);
                if(k3 >= 0 && k1 > k3)
                {
                    SuggestionInfo suggestioninfo = mSuggestionInfos[mNumberOfSuggestions];
                    suggestioninfo.suggestionSpan = ((SuggestionSpan) (obj));
                    suggestioninfo.suggestionIndex = -1;
                    suggestioninfo.text.replace(0, suggestioninfo.text.length(), Editor._2D_get6(Editor.this).getContext().getString(0x1040061));
                    suggestioninfo.text.setSpan(suggestioninfo.highlightSpan, 0, 0, 33);
                    mNumberOfSuggestions = mNumberOfSuggestions + 1;
                }
            }
            obj = mSuggestionInfos[mNumberOfSuggestions];
            obj.suggestionSpan = null;
            obj.suggestionIndex = -2;
            ((SuggestionInfo) (obj)).text.replace(0, ((SuggestionInfo) (obj)).text.length(), Editor._2D_get6(Editor.this).getContext().getString(0x10401a4));
            ((SuggestionInfo) (obj)).text.setSpan(((SuggestionInfo) (obj)).highlightSpan, 0, 0, 33);
            mNumberOfSuggestions = mNumberOfSuggestions + 1;
            if(mSuggestionRangeSpan == null)
                mSuggestionRangeSpan = new SuggestionRangeSpan();
            if(l == 0)
            {
                mSuggestionRangeSpan.setBackgroundColor(Editor._2D_get6(Editor.this).mHighlightColor);
            } else
            {
                int l1 = (int)((float)Color.alpha(l) * 0.4F);
                mSuggestionRangeSpan.setBackgroundColor((0xffffff & l) + (l1 << 24));
            }
            spannable.setSpan(mSuggestionRangeSpan, j, k, 33);
            mSuggestionsAdapter.notifyDataSetChanged();
            return true;
            if(true) goto _L14; else goto _L13
_L13:
        }

        protected int clipVertically(int i)
        {
            int j = mContentView.getMeasuredHeight();
            return Math.min(i, Editor._2D_get6(Editor.this).getResources().getDisplayMetrics().heightPixels - j);
        }

        protected void createPopupWindow()
        {
            mPopupWindow = new CustomPopupWindow(Editor._2D_get6(Editor.this).getContext(), 0x1010373);
            mPopupWindow.setInputMethodMode(2);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setClippingEnabled(false);
        }

        protected int getTextOffset()
        {
            return Editor._2D_get6(Editor.this).getSelectionStart();
        }

        protected int getVerticalLocalPosition(int i)
        {
            return Editor._2D_get6(Editor.this).getLayout().getLineBottom(i);
        }

        public void hide()
        {
            super.hide();
        }

        protected void initContentView()
        {
            ListView listview = new ListView(Editor._2D_get6(Editor.this).getContext());
            mSuggestionsAdapter = new SuggestionAdapter(null);
            listview.setAdapter(mSuggestionsAdapter);
            listview.setOnItemClickListener(this);
            mContentView = listview;
            mSuggestionInfos = new SuggestionInfo[7];
            for(int i = 0; i < mSuggestionInfos.length; i++)
                mSuggestionInfos[i] = new SuggestionInfo(null);

        }

        public boolean isShowingUp()
        {
            return mIsShowingUp;
        }

        protected void measureContent()
        {
            Object obj = Editor._2D_get6(Editor.this).getResources().getDisplayMetrics();
            int i = android.view.View.MeasureSpec.makeMeasureSpec(((DisplayMetrics) (obj)).widthPixels, 0x80000000);
            int j = android.view.View.MeasureSpec.makeMeasureSpec(((DisplayMetrics) (obj)).heightPixels, 0x80000000);
            int k = 0;
            obj = null;
            for(int l = 0; l < mNumberOfSuggestions; l++)
            {
                obj = mSuggestionsAdapter.getView(l, ((View) (obj)), mContentView);
                ((View) (obj)).getLayoutParams().width = -2;
                ((View) (obj)).measure(i, j);
                k = Math.max(k, ((View) (obj)).getMeasuredWidth());
            }

            mContentView.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), j);
            obj = mPopupWindow.getBackground();
            int i1 = k;
            if(obj != null)
            {
                if(Editor._2D_get5(Editor.this) == null)
                    Editor._2D_set4(Editor.this, new Rect());
                ((Drawable) (obj)).getPadding(Editor._2D_get5(Editor.this));
                i1 = k + (Editor._2D_get5(Editor.this).left + Editor._2D_get5(Editor.this).right);
            }
            mPopupWindow.setWidth(i1);
        }

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            Object obj;
label0:
            {
label1:
                {
                    int j;
label2:
                    {
                        obj = (Editable)Editor._2D_get6(Editor.this).getText();
                        adapterview = mSuggestionInfos[i];
                        if(((SuggestionInfo) (adapterview)).suggestionIndex != -2)
                            break label0;
                        j = ((Editable) (obj)).getSpanStart(mSuggestionRangeSpan);
                        int i1 = ((Editable) (obj)).getSpanEnd(mSuggestionRangeSpan);
                        if(j < 0 || i1 <= j)
                            break label1;
                        i = i1;
                        if(i1 >= ((Editable) (obj)).length())
                            break label2;
                        i = i1;
                        if(!Character.isSpaceChar(((Editable) (obj)).charAt(i1)))
                            break label2;
                        if(j != 0)
                        {
                            i = i1;
                            if(!Character.isSpaceChar(((Editable) (obj)).charAt(j - 1)))
                                break label2;
                        }
                        i = i1 + 1;
                    }
                    Editor._2D_get6(Editor.this).deleteText_internal(j, i);
                }
                hide();
                return;
            }
            int j1 = ((Editable) (obj)).getSpanStart(((SuggestionInfo) (adapterview)).suggestionSpan);
            int k = ((Editable) (obj)).getSpanEnd(((SuggestionInfo) (adapterview)).suggestionSpan);
            if(j1 < 0 || k <= j1)
            {
                hide();
                return;
            }
            view = ((Editable) (obj)).toString().substring(j1, k);
            if(((SuggestionInfo) (adapterview)).suggestionIndex == -1)
            {
                Intent intent = new Intent("com.android.settings.USER_DICTIONARY_INSERT");
                intent.putExtra("word", view);
                intent.putExtra("locale", Editor._2D_get6(Editor.this).getTextServicesLocale().toString());
                intent.setFlags(intent.getFlags() | 0x10000000);
                Editor._2D_get6(Editor.this).getContext().startActivity(intent);
                ((Editable) (obj)).removeSpan(((SuggestionInfo) (adapterview)).suggestionSpan);
                Selection.setSelection(((Spannable) (obj)), k);
                Editor._2D_wrap21(Editor.this, j1, k, false);
            } else
            {
                SuggestionSpan asuggestionspan[] = (SuggestionSpan[])((Editable) (obj)).getSpans(j1, k, android/text/style/SuggestionSpan);
                int k1 = asuggestionspan.length;
                int ai[] = new int[k1];
                int ai1[] = new int[k1];
                int ai2[] = new int[k1];
                for(i = 0; i < k1; i++)
                {
                    SuggestionSpan suggestionspan = asuggestionspan[i];
                    ai[i] = ((Editable) (obj)).getSpanStart(suggestionspan);
                    ai1[i] = ((Editable) (obj)).getSpanEnd(suggestionspan);
                    ai2[i] = ((Editable) (obj)).getSpanFlags(suggestionspan);
                    int l1 = suggestionspan.getFlags();
                    if((l1 & 2) > 0)
                        suggestionspan.setFlags(l1 & -3 & -2);
                }

                int i2 = ((SuggestionInfo) (adapterview)).suggestionStart;
                i = ((SuggestionInfo) (adapterview)).suggestionEnd;
                obj = ((SuggestionInfo) (adapterview)).text.subSequence(i2, i).toString();
                Editor._2D_get6(Editor.this).replaceText_internal(j1, k, ((CharSequence) (obj)));
                ((SuggestionInfo) (adapterview)).suggestionSpan.notifySelection(Editor._2D_get6(Editor.this).getContext(), view, ((SuggestionInfo) (adapterview)).suggestionIndex);
                ((SuggestionInfo) (adapterview)).suggestionSpan.getSuggestions()[((SuggestionInfo) (adapterview)).suggestionIndex] = view;
                i2 = ((String) (obj)).length() - (k - j1);
                for(i = 0; i < k1; i++)
                    if(ai[i] <= j1 && ai1[i] >= k)
                        Editor._2D_get6(Editor.this).setSpan_internal(asuggestionspan[i], ai[i], ai1[i] + i2, ai2[i]);

                i = k + i2;
                Editor._2D_get6(Editor.this).setCursorPosition_internal(i, i);
            }
            hide();
        }

        public void onParentLostFocus()
        {
            mIsShowingUp = false;
        }

        public void show()
        {
            if(!(Editor._2D_get6(Editor.this).getText() instanceof Editable))
                return;
            if(updateSuggestions())
            {
                mCursorWasVisibleBeforeSuggestions = mCursorVisible;
                Editor._2D_get6(Editor.this).setCursorVisible(false);
                mIsShowingUp = true;
                super.show();
            }
        }

        private static final int ADD_TO_DICTIONARY = -1;
        private static final int DELETE_TEXT = -2;
        private static final int MAX_NUMBER_SUGGESTIONS = 5;
        private boolean mCursorWasVisibleBeforeSuggestions;
        private boolean mIsShowingUp;
        private int mNumberOfSuggestions;
        private final HashMap mSpansLengths = new HashMap();
        private SuggestionInfo mSuggestionInfos[];
        private final Comparator mSuggestionSpanComparator = new SuggestionSpanComparator(null);
        private SuggestionAdapter mSuggestionsAdapter;
        final Editor this$0;

        public SuggestionsPopupWindow()
        {
            this$0 = Editor.this;
            super();
            mIsShowingUp = false;
            mCursorWasVisibleBeforeSuggestions = mCursorVisible;
        }
    }

    private class SuggestionsPopupWindow.CustomPopupWindow extends AnimatePopupWindow
    {

        public void dismiss()
        {
            super.dismiss();
            Editor._2D_wrap0(_fld0).removeSubscriber(SuggestionsPopupWindow.this);
            ((Spannable)Editor._2D_get6(_fld0).getText()).removeSpan(mSuggestionRangeSpan);
            Editor._2D_get6(_fld0).setCursorVisible(SuggestionsPopupWindow._2D_get0(SuggestionsPopupWindow.this));
            if(hasInsertionController())
                getInsertionController().show();
        }

        final SuggestionsPopupWindow this$1;

        public SuggestionsPopupWindow.CustomPopupWindow(Context context, int i)
        {
            this$1 = SuggestionsPopupWindow.this;
            super(context, null, i);
        }
    }

    private class SuggestionsPopupWindow.SuggestionAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return SuggestionsPopupWindow._2D_get1(SuggestionsPopupWindow.this);
        }

        public Object getItem(int i)
        {
            return SuggestionsPopupWindow._2D_get3(SuggestionsPopupWindow.this)[i];
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            TextView textview = (TextView)view;
            view = textview;
            if(textview == null)
                view = (TextView)mInflater.inflate(Editor._2D_get6(_fld0).mTextEditSuggestionItemLayout, viewgroup, false);
            viewgroup = SuggestionsPopupWindow._2D_get3(SuggestionsPopupWindow.this)[i];
            view.setText(((SuggestionsPopupWindow.SuggestionInfo) (viewgroup)).text);
            if(((SuggestionsPopupWindow.SuggestionInfo) (viewgroup)).suggestionIndex == -1 || ((SuggestionsPopupWindow.SuggestionInfo) (viewgroup)).suggestionIndex == -2)
                view.setBackgroundColor(0);
            else
                view.setBackgroundColor(-1);
            return view;
        }

        private LayoutInflater mInflater;
        final SuggestionsPopupWindow this$1;

        private SuggestionsPopupWindow.SuggestionAdapter()
        {
            this$1 = SuggestionsPopupWindow.this;
            super();
            mInflater = (LayoutInflater)Editor._2D_get6(_fld0).getContext().getSystemService("layout_inflater");
        }

        SuggestionsPopupWindow.SuggestionAdapter(SuggestionsPopupWindow.SuggestionAdapter suggestionadapter)
        {
            this();
        }
    }

    private class SuggestionsPopupWindow.SuggestionInfo
    {

        TextAppearanceSpan highlightSpan;
        int suggestionEnd;
        int suggestionIndex;
        SuggestionSpan suggestionSpan;
        int suggestionStart;
        SpannableStringBuilder text;
        final SuggestionsPopupWindow this$1;

        private SuggestionsPopupWindow.SuggestionInfo()
        {
            this$1 = SuggestionsPopupWindow.this;
            super();
            text = new SpannableStringBuilder();
            highlightSpan = new TextAppearanceSpan(Editor._2D_get6(_fld0).getContext(), 0x1030118);
        }

        SuggestionsPopupWindow.SuggestionInfo(SuggestionsPopupWindow.SuggestionInfo suggestioninfo)
        {
            this();
        }
    }

    private class SuggestionsPopupWindow.SuggestionSpanComparator
        implements Comparator
    {

        public int compare(SuggestionSpan suggestionspan, SuggestionSpan suggestionspan1)
        {
            int i = suggestionspan.getFlags();
            int j = suggestionspan1.getFlags();
            if(i != j)
            {
                boolean flag;
                boolean flag1;
                boolean flag2;
                boolean flag3;
                if((i & 1) != 0)
                    flag2 = true;
                else
                    flag2 = false;
                if((j & 1) != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if((i & 2) != 0)
                    flag = true;
                else
                    flag = false;
                if((j & 2) != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag2 && flag ^ true)
                    return -1;
                if(flag3 && flag1 ^ true)
                    return 1;
                if(flag)
                    return -1;
                if(flag1)
                    return 1;
            }
            return ((Integer)SuggestionsPopupWindow._2D_get2(SuggestionsPopupWindow.this).get(suggestionspan)).intValue() - ((Integer)SuggestionsPopupWindow._2D_get2(SuggestionsPopupWindow.this).get(suggestionspan1)).intValue();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((SuggestionSpan)obj, (SuggestionSpan)obj1);
        }

        final SuggestionsPopupWindow this$1;

        private SuggestionsPopupWindow.SuggestionSpanComparator()
        {
            this$1 = SuggestionsPopupWindow.this;
            super();
        }

        SuggestionsPopupWindow.SuggestionSpanComparator(SuggestionsPopupWindow.SuggestionSpanComparator suggestionspancomparator)
        {
            this();
        }
    }

    private static class TextRenderNode
    {

        boolean needsRecord()
        {
            boolean flag;
            if(!isDirty)
                flag = renderNode.isValid() ^ true;
            else
                flag = true;
            return flag;
        }

        boolean isDirty;
        RenderNode renderNode;

        public TextRenderNode(String s)
        {
            isDirty = true;
            renderNode = RenderNode.create(s, null);
        }
    }

    private static interface TextViewPositionListener
    {

        public abstract void updatePosition(int i, int j, boolean flag, boolean flag1);
    }

    public static class UndoInputFilter
        implements InputFilter
    {

        private boolean canUndoEdit(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
        {
            if(!mEditor.mAllowUndo)
                return false;
            if(Editor._2D_get7(mEditor).isInUndo())
                return false;
            if(!Editor._2D_wrap7(charsequence, i, j) || Editor._2D_wrap7(spanned, k, l) ^ true)
                return false;
            return i != j || k != l;
        }

        private EditOperation getLastEdit()
        {
            return (EditOperation)Editor._2D_get7(mEditor).getLastOperation(android/widget/Editor$EditOperation, Editor._2D_get8(mEditor), 1);
        }

        private void handleEdit(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l, boolean flag)
        {
            byte byte0;
            if(isInTextWatcher() || mPreviousOperationWasInSameBatchEdit)
                byte0 = 0;
            else
            if(flag)
                byte0 = 1;
            else
                byte0 = 2;
            charsequence = TextUtils.substring(charsequence, i, j);
            spanned = TextUtils.substring(spanned, k, l);
            charsequence = new EditOperation(mEditor, spanned, k, charsequence, mHasComposition);
            if(mHasComposition && TextUtils.equals(EditOperation._2D_get0(charsequence), EditOperation._2D_get1(charsequence)))
            {
                return;
            } else
            {
                recordEdit(charsequence, byte0);
                return;
            }
        }

        private static boolean isComposition(CharSequence charsequence)
        {
            boolean flag = false;
            if(!(charsequence instanceof Spannable))
                return false;
            charsequence = (Spannable)charsequence;
            if(EditableInputConnection.getComposingSpanStart(charsequence) < EditableInputConnection.getComposingSpanEnd(charsequence))
                flag = true;
            return flag;
        }

        private boolean isInTextWatcher()
        {
            boolean flag = false;
            CharSequence charsequence = Editor._2D_get6(mEditor).getText();
            boolean flag1 = flag;
            if(charsequence instanceof SpannableStringBuilder)
            {
                flag1 = flag;
                if(((SpannableStringBuilder)charsequence).getTextWatcherDepth() > 0)
                    flag1 = true;
            }
            return flag1;
        }

        private void recordEdit(EditOperation editoperation, int i)
        {
            UndoManager undomanager;
            EditOperation editoperation1;
            undomanager = Editor._2D_get7(mEditor);
            undomanager.beginUpdate("Edit text");
            editoperation1 = getLastEdit();
            if(editoperation1 != null) goto _L2; else goto _L1
_L1:
            undomanager.addOperation(editoperation, 0);
_L4:
            mPreviousOperationWasInSameBatchEdit = mIsUserEdit;
            undomanager.endUpdate();
            return;
_L2:
            if(i == 0)
                editoperation1.forceMergeWith(editoperation);
            else
            if(!mIsUserEdit)
            {
                undomanager.commitState(Editor._2D_get8(mEditor));
                undomanager.addOperation(editoperation, 0);
            } else
            if(i != 2 || !EditOperation._2D_wrap0(editoperation1, editoperation))
            {
                undomanager.commitState(Editor._2D_get8(mEditor));
                undomanager.addOperation(editoperation, 0);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void beginBatchEdit()
        {
            mIsUserEdit = true;
        }

        public void endBatchEdit()
        {
            mIsUserEdit = false;
            mPreviousOperationWasInSameBatchEdit = false;
        }

        public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
        {
            if(!canUndoEdit(charsequence, i, j, spanned, k, l))
                return null;
            boolean flag = mHasComposition;
            mHasComposition = isComposition(charsequence);
            boolean flag1 = mExpanding;
            boolean flag2 = false;
            boolean flag3 = flag2;
            if(j - i != l - k)
            {
                if(j - i > l - k)
                    flag3 = true;
                else
                    flag3 = false;
                mExpanding = flag3;
                flag3 = flag2;
                if(flag)
                {
                    flag3 = flag2;
                    if(mExpanding != flag1)
                        flag3 = true;
                }
            }
            handleEdit(charsequence, i, j, spanned, k, l, flag3);
            return null;
        }

        void freezeLastEdit()
        {
            Editor._2D_get7(mEditor).beginUpdate("Edit text");
            EditOperation editoperation = getLastEdit();
            if(editoperation != null)
                EditOperation._2D_set0(editoperation, true);
            Editor._2D_get7(mEditor).endUpdate();
        }

        public void restoreInstanceState(Parcel parcel)
        {
            boolean flag = true;
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            mIsUserEdit = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            mHasComposition = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            mExpanding = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            mPreviousOperationWasInSameBatchEdit = flag1;
        }

        public void saveInstanceState(Parcel parcel)
        {
            boolean flag = true;
            int i;
            if(mIsUserEdit)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mHasComposition)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mExpanding)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(mPreviousOperationWasInSameBatchEdit)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
        }

        private static final int MERGE_EDIT_MODE_FORCE_MERGE = 0;
        private static final int MERGE_EDIT_MODE_NEVER_MERGE = 1;
        private static final int MERGE_EDIT_MODE_NORMAL = 2;
        private final Editor mEditor;
        private boolean mExpanding;
        private boolean mHasComposition;
        private boolean mIsUserEdit;
        private boolean mPreviousOperationWasInSameBatchEdit;

        public UndoInputFilter(Editor editor)
        {
            mEditor = editor;
        }
    }


    static Runnable _2D_get0(Editor editor)
    {
        return editor.mInsertionActionModeRunnable;
    }

    static Drawable _2D_get1(Editor editor)
    {
        return editor.mSelectHandleCenter;
    }

    static Drawable _2D_get2(Editor editor)
    {
        return editor.mSelectHandleLeft;
    }

    static Drawable _2D_get3(Editor editor)
    {
        return editor.mSelectHandleRight;
    }

    static int _2D_get4(Editor editor)
    {
        return editor.mTapState;
    }

    static Rect _2D_get5(Editor editor)
    {
        return editor.mTempRect;
    }

    static TextView _2D_get6(Editor editor)
    {
        return editor.mTextView;
    }

    static UndoManager _2D_get7(Editor editor)
    {
        return editor.mUndoManager;
    }

    static UndoOwner _2D_get8(Editor editor)
    {
        return editor.mUndoOwner;
    }

    static Runnable _2D_set0(Editor editor, Runnable runnable)
    {
        editor.mInsertionActionModeRunnable = runnable;
        return runnable;
    }

    static Drawable _2D_set1(Editor editor, Drawable drawable)
    {
        editor.mSelectHandleCenter = drawable;
        return drawable;
    }

    static Drawable _2D_set2(Editor editor, Drawable drawable)
    {
        editor.mSelectHandleLeft = drawable;
        return drawable;
    }

    static Drawable _2D_set3(Editor editor, Drawable drawable)
    {
        editor.mSelectHandleRight = drawable;
        return drawable;
    }

    static Rect _2D_set4(Editor editor, Rect rect)
    {
        editor.mTempRect = rect;
        return rect;
    }

    static PositionListener _2D_wrap0(Editor editor)
    {
        return editor.getPositionListener();
    }

    static SelectionActionModeHelper _2D_wrap1(Editor editor)
    {
        return editor.getSelectionActionModeHelper();
    }

    static boolean _2D_wrap10(Editor editor)
    {
        return editor.shouldBlink();
    }

    static boolean _2D_wrap11(Editor editor)
    {
        return editor.shouldOfferToShowSuggestions();
    }

    static float _2D_wrap12(View view)
    {
        return getDescendantViewScale(view);
    }

    static int _2D_wrap13(Editor editor, Layout layout, int i, float f)
    {
        return editor.getCurrentLineAdjustedForSlop(layout, i, f);
    }

    static int _2D_wrap14(Editor editor, int i, boolean flag)
    {
        return editor.getNextCursorOffset(i, flag);
    }

    static int _2D_wrap15(Editor editor, int i)
    {
        return editor.getWordEnd(i);
    }

    static int _2D_wrap16(Editor editor, int i)
    {
        return editor.getWordStart(i);
    }

    static long _2D_wrap17(Editor editor, int i, int j)
    {
        return editor.getParagraphsRange(i, j);
    }

    static void _2D_wrap18(Editor editor)
    {
        editor.sendUpdateSelection();
    }

    static void _2D_wrap19(Editor editor, Context context, Intent intent)
    {
        editor.startActivityFromContext(context, intent);
    }

    static boolean _2D_wrap2()
    {
        return isBigFontMode();
    }

    static void _2D_wrap20(Editor editor)
    {
        editor.startDragAndDrop();
    }

    static void _2D_wrap21(Editor editor, int i, int j, boolean flag)
    {
        editor.updateSpellCheckSpans(i, j, flag);
    }

    static boolean _2D_wrap3(Editor editor)
    {
        return editor.isCursorInsideEasyCorrectionSpan();
    }

    static boolean _2D_wrap4(Editor editor, int i)
    {
        return editor.isOffsetVisible(i);
    }

    static boolean _2D_wrap5(Editor editor, float f, float f1)
    {
        return editor.isPositionOnText(f, f1);
    }

    static boolean _2D_wrap6(Editor editor, int i, int j)
    {
        return editor.isPositionVisible(i, j);
    }

    static boolean _2D_wrap7(CharSequence charsequence, int i, int j)
    {
        return isValidRange(charsequence, i, j);
    }

    static boolean _2D_wrap8(Editor editor)
    {
        return editor.selectCurrentParagraph();
    }

    static boolean _2D_wrap9(Editor editor)
    {
        return editor.selectCurrentWordAndStartDrag();
    }

    Editor(TextView textview)
    {
        mUndoOwner = mUndoManager.getOwner("Editor", this);
        mAllowUndo = true;
        mFirstTouchUp = true;
        mInputType = 0;
        mCursorVisible = true;
        mShowSoftInputOnFocus = true;
        mTapState = 0;
        mLastTouchUpTime = 0L;
        mIsInsertionActionModeStartPending = false;
        mCustomSelectionActionModeCallbackDestroyed = false;
        mAllowToStartActionMode = true;
        mTextView = textview;
        mTextView.setFilters(mTextView.getFilters());
    }

    private boolean canSelectText()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(hasSelectionController())
        {
            flag1 = flag;
            if(mTextView.getText().length() != 0)
                flag1 = true;
        }
        return flag1;
    }

    private void chooseSize(PopupWindow popupwindow, CharSequence charsequence, TextView textview)
    {
        int i = textview.getPaddingLeft();
        int j = textview.getPaddingRight();
        int k = textview.getPaddingTop();
        int l = textview.getPaddingBottom();
        int i1 = mTextView.getResources().getDimensionPixelSize(0x1050195);
        charsequence = new StaticLayout(charsequence, textview.getPaint(), i1, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        float f = 0.0F;
        for(int j1 = 0; j1 < charsequence.getLineCount(); j1++)
            f = Math.max(f, charsequence.getLineWidth(j1));

        popupwindow.setWidth((int)Math.ceil(f) + (i + j));
        popupwindow.setHeight(charsequence.getHeight() + (k + l));
    }

    private void discardTextDisplayLists()
    {
        if(mTextRenderNodes != null)
        {
            int i = 0;
            while(i < mTextRenderNodes.length) 
            {
                RenderNode rendernode;
                if(mTextRenderNodes[i] != null)
                    rendernode = mTextRenderNodes[i].renderNode;
                else
                    rendernode = null;
                if(rendernode != null && rendernode.isValid())
                    rendernode.discardDisplayList();
                i++;
            }
        }
    }

    private void downgradeEasyCorrectionSpans()
    {
        Object obj = mTextView.getText();
        if(obj instanceof Spannable)
        {
            obj = (Spannable)obj;
            SuggestionSpan asuggestionspan[] = (SuggestionSpan[])((Spannable) (obj)).getSpans(0, ((Spannable) (obj)).length(), android/text/style/SuggestionSpan);
            for(int i = 0; i < asuggestionspan.length; i++)
            {
                int j = asuggestionspan[i].getFlags();
                if((j & 1) != 0 && (j & 2) == 0)
                    asuggestionspan[i].setFlags(j & -2);
            }

        }
    }

    private void drawCursor(Canvas canvas, int i)
    {
        boolean flag;
        if(i != 0)
            flag = true;
        else
            flag = false;
        if(flag)
            canvas.translate(0.0F, i);
        for(int j = 0; j < mCursorCount; j++)
            mCursorDrawable[j].draw(canvas);

        if(flag)
            canvas.translate(0.0F, -i);
    }

    private void drawHardwareAccelerated(Canvas canvas, Layout layout, Path path, Paint paint, int i)
    {
        int j;
        int k;
        long l = layout.getLineRangeForDraw(canvas);
        j = TextUtils.unpackRangeStartFromLong(l);
        k = TextUtils.unpackRangeEndFromLong(l);
        if(k < 0)
            return;
        layout.drawBackground(canvas, path, paint, i, j, k);
        if(!(layout instanceof DynamicLayout)) goto _L2; else goto _L1
_L1:
        DynamicLayout dynamiclayout;
        int ai[];
        int ai1[];
        int i1;
        int j1;
        int k1;
        int l1;
        if(mTextRenderNodes == null)
            mTextRenderNodes = (TextRenderNode[])ArrayUtils.emptyArray(android/widget/Editor$TextRenderNode);
        dynamiclayout = (DynamicLayout)layout;
        ai = dynamiclayout.getBlockEndLines();
        ai1 = dynamiclayout.getBlockIndices();
        i1 = dynamiclayout.getNumberOfBlocks();
        j1 = dynamiclayout.getIndexFirstChangedBlock();
        k1 = -1;
        l1 = 0;
        i = 0;
_L4:
        int i2;
        int j2;
        int k2;
        int l2;
        float f1;
        float f3;
        if(i >= i1)
            break; /* Loop/switch isn't completed */
        i2 = ai[i];
        j = ai1[i];
        boolean flag;
        if(j == -1)
            j2 = 1;
        else
            j2 = 0;
        k = l1;
        if(j2 != 0)
        {
            j = getAvailableDisplayListIndex(ai1, i1, l1);
            ai1[i] = j;
            if(mTextRenderNodes[j] != null)
                mTextRenderNodes[j].isDirty = true;
            k = j + 1;
        }
        if(mTextRenderNodes[j] == null)
            mTextRenderNodes[j] = new TextRenderNode((new StringBuilder()).append("Text ").append(j).toString());
        flag = mTextRenderNodes[j].needsRecord();
        path = mTextRenderNodes[j].renderNode;
        if(i < j1 && !flag)
            break MISSING_BLOCK_LABEL_479;
        j2 = k1 + 1;
        k2 = layout.getLineTop(j2);
        l2 = layout.getLineBottom(i2);
        k1 = 0;
        l1 = mTextView.getWidth();
        if(mTextView.getHorizontallyScrolling())
        {
            float f = 3.402823E+038F;
            float f2 = 1.401298E-045F;
            for(k1 = j2; k1 <= i2; k1++)
            {
                f = Math.min(f, layout.getLineLeft(k1));
                f2 = Math.max(f2, layout.getLineRight(k1));
            }

            k1 = (int)f;
            l1 = (int)(0.5F + f2);
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_466;
        paint = path.start(l1 - k1, l2 - k2);
        f3 = -k1;
        f1 = -k2;
        paint.translate(f3, f1);
        layout.drawText(paint, j2, i2);
        mTextRenderNodes[j].isDirty = false;
        path.end(paint);
        path.setClipToBounds(false);
        path.setLeftTopRightBottom(k1, k2, l1, l2);
        ((DisplayListCanvas)canvas).drawRenderNode(path);
        k1 = i2;
        i++;
        l1 = k;
        if(true) goto _L4; else goto _L3
        canvas;
        path.end(paint);
        path.setClipToBounds(false);
        throw canvas;
_L3:
        dynamiclayout.setIndexFirstChangedBlock(i1);
_L6:
        return;
_L2:
        layout.drawText(canvas, j, k);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean extractTextInternal(ExtractedTextRequest extractedtextrequest, int i, int j, int k, ExtractedText extractedtext)
    {
        if(extractedtextrequest == null || extractedtext == null)
            return false;
        CharSequence charsequence = mTextView.getText();
        if(charsequence == null)
            return false;
        if(i != -2)
        {
            int l = charsequence.length();
            if(i < 0)
            {
                extractedtext.partialEndOffset = -1;
                extractedtext.partialStartOffset = -1;
                k = 0;
                j = l;
            } else
            {
                j += k;
                int i1 = i;
                int j1 = j;
                if(charsequence instanceof Spanned)
                {
                    Spanned spanned = (Spanned)charsequence;
                    Object aobj[] = spanned.getSpans(i, j, android/text/ParcelableSpan);
                    int k1 = aobj.length;
                    do
                    {
                        i1 = i;
                        j1 = j;
                        if(k1 <= 0)
                            break;
                        i1 = k1 - 1;
                        k1 = spanned.getSpanStart(aobj[i1]);
                        j1 = i;
                        if(k1 < i)
                            j1 = k1;
                        int l1 = spanned.getSpanEnd(aobj[i1]);
                        k1 = i1;
                        i = j1;
                        if(l1 > j)
                        {
                            j = l1;
                            k1 = i1;
                            i = j1;
                        }
                    } while(true);
                }
                extractedtext.partialStartOffset = i1;
                extractedtext.partialEndOffset = j1 - k;
                if(i1 > l)
                {
                    i = l;
                } else
                {
                    i = i1;
                    if(i1 < 0)
                        i = 0;
                }
                if(j1 > l)
                {
                    j = l;
                    k = i;
                } else
                {
                    k = i;
                    j = j1;
                    if(j1 < 0)
                    {
                        j = 0;
                        k = i;
                    }
                }
            }
            if((extractedtextrequest.flags & 1) != 0)
                extractedtext.text = charsequence.subSequence(k, j);
            else
                extractedtext.text = TextUtils.substring(charsequence, k, j);
        } else
        {
            extractedtext.partialStartOffset = 0;
            extractedtext.partialEndOffset = 0;
            extractedtext.text = "";
        }
        extractedtext.flags = 0;
        if(MetaKeyKeyListener.getMetaState(charsequence, 2048) != 0)
            extractedtext.flags = extractedtext.flags | 2;
        if(mTextView.isSingleLine())
            extractedtext.flags = extractedtext.flags | 1;
        extractedtext.startOffset = 0;
        extractedtext.selectionStart = mTextView.getSelectionStart();
        extractedtext.selectionEnd = mTextView.getSelectionEnd();
        return true;
    }

    private Layout getActiveLayout()
    {
        Layout layout = mTextView.getLayout();
        Layout layout1 = mTextView.getHintLayout();
        Layout layout2 = layout;
        if(TextUtils.isEmpty(layout.getText()))
        {
            layout2 = layout;
            if(layout1 != null)
            {
                layout2 = layout;
                if(TextUtils.isEmpty(layout1.getText()) ^ true)
                    layout2 = layout1;
            }
        }
        return layout2;
    }

    private int getAvailableDisplayListIndex(int ai[], int i, int j)
    {
        int k = mTextRenderNodes.length;
label0:
        do
        {
            if(j < k)
            {
                boolean flag = false;
                int l = 0;
                do
                {
label1:
                    {
                        boolean flag1 = flag;
                        if(l < i)
                        {
                            if(ai[l] != j)
                                break label1;
                            flag1 = true;
                        }
                        if(flag1)
                            j++;
                        else
                            return j;
                        continue label0;
                    }
                    l++;
                } while(true);
            }
            mTextRenderNodes = (TextRenderNode[])GrowingArrayUtils.append(mTextRenderNodes, k, null);
            return k;
        } while(true);
    }

    private long getCharClusterRange(int i)
    {
        if(i < mTextView.getText().length())
        {
            i = getNextCursorOffset(i, true);
            return TextUtils.packRangeInLong(getNextCursorOffset(i, false), i);
        }
        if(i - 1 >= 0)
        {
            i = getNextCursorOffset(i, false);
            return TextUtils.packRangeInLong(i, getNextCursorOffset(i, true));
        } else
        {
            return TextUtils.packRangeInLong(i, i);
        }
    }

    private long getCharRange(int i)
    {
        int j = mTextView.getText().length();
        if(i + 1 < j && Character.isSurrogatePair(mTextView.getText().charAt(i), mTextView.getText().charAt(i + 1)))
            return TextUtils.packRangeInLong(i, i + 2);
        if(i < j)
            return TextUtils.packRangeInLong(i, i + 1);
        if(i - 2 >= 0)
        {
            char c = mTextView.getText().charAt(i - 1);
            if(Character.isSurrogatePair(mTextView.getText().charAt(i - 2), c))
                return TextUtils.packRangeInLong(i - 2, i);
        }
        if(i - 1 >= 0)
            return TextUtils.packRangeInLong(i - 1, i);
        else
            return TextUtils.packRangeInLong(i, i);
    }

    private int getCurrentLineAdjustedForSlop(Layout layout, int i, float f)
    {
        int j;
        float f2;
        float f3;
        for(j = mTextView.getLineAtCoordinate(f); layout == null || i > layout.getLineCount() || layout.getLineCount() <= 0 || i < 0;)
            return j;

        if(Math.abs(j - i) >= 2)
            return j;
        float f1 = mTextView.viewportToContentVerticalOffset();
        j = layout.getLineCount();
        f2 = (float)mTextView.getLineHeight() * 0.5F;
        f3 = layout.getLineTop(0);
        f3 = Math.max(((float)layout.getLineTop(i) + f1) - f2, f3 + f1 + f2);
        float f4 = layout.getLineBottom(j - 1);
        f2 = Math.min((float)layout.getLineBottom(i) + f1 + f2, (f4 + f1) - f2);
        if(f > f3) goto _L2; else goto _L1
_L1:
        i = Math.max(i - 1, 0);
_L4:
        return i;
_L2:
        if(f >= f2)
            i = Math.min(i + 1, j - 1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static float getDescendantViewScale(View view)
    {
        float f = 1.0F * view.getScaleX();
        view = view.getParent();
        do
        {
label0:
            {
                if(view instanceof View)
                {
                    view = (View)view;
                    if(view.getId() != 0x1020002)
                        break label0;
                }
                return f;
            }
            f *= view.getScaleX();
            view = view.getParent();
        } while(true);
    }

    private int getErrorX()
    {
        int i;
        int j;
        float f;
        TextView.Drawables drawables;
        i = 0;
        j = 0;
        f = mTextView.getResources().getDisplayMetrics().density;
        drawables = mTextView.mDrawables;
        mTextView.getLayoutDirection();
        JVM INSTR tableswitch 0 1: default 56
    //                   0 56
    //                   1 112;
           goto _L1 _L1 _L2
_L1:
        if(drawables != null)
            j = drawables.mDrawableSizeRight;
        j = -j / 2;
        i = (int)(25F * f + 0.5F);
        j = (mTextView.getWidth() - mErrorPopup.getWidth() - mTextView.getPaddingRight()) + (j + i);
_L4:
        return j;
_L2:
        j = i;
        if(drawables != null)
            j = drawables.mDrawableSizeLeft;
        j /= 2;
        i = (int)(25F * f + 0.5F);
        j = mTextView.getPaddingLeft() + (j - i);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getErrorY()
    {
        int i;
        int j;
        int k;
        int l;
        TextView.Drawables drawables;
        i = mTextView.getCompoundPaddingTop();
        j = mTextView.getBottom();
        k = mTextView.getTop();
        l = mTextView.getCompoundPaddingBottom();
        drawables = mTextView.mDrawables;
        mTextView.getLayoutDirection();
        JVM INSTR tableswitch 0 1: default 72
    //                   0 72
    //                   1 145;
           goto _L1 _L1 _L2
_L1:
        int i1;
        float f;
        if(drawables != null)
            i1 = drawables.mDrawableHeightRight;
        else
            i1 = 0;
_L4:
        l = (j - k - l - i - i1) / 2;
        f = mTextView.getResources().getDisplayMetrics().density;
        return (i + l + i1) - mTextView.getHeight() - (int)(2.0F * f + 0.5F);
_L2:
        if(drawables != null)
            i1 = drawables.mDrawableHeightLeft;
        else
            i1 = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getLastTapPosition()
    {
        if(mSelectionModifierCursorController != null)
        {
            int i = mSelectionModifierCursorController.getMinTouchOffset();
            if(i >= 0)
            {
                int j = i;
                if(i > mTextView.getText().length())
                    j = mTextView.getText().length();
                return j;
            }
        }
        return -1;
    }

    private long getLastTouchOffsets()
    {
        SelectionModifierCursorController selectionmodifiercursorcontroller = getSelectionController();
        return TextUtils.packRangeInLong(selectionmodifiercursorcontroller.getMinTouchOffset(), selectionmodifiercursorcontroller.getMaxTouchOffset());
    }

    private int getNextCursorOffset(int i, boolean flag)
    {
        Layout layout = mTextView.getLayout();
        if(layout == null)
            return i;
        if(flag == layout.isRtlCharAt(i))
            i = layout.getOffsetToLeftOf(i);
        else
            i = layout.getOffsetToRightOf(i);
        return i;
    }

    private long getParagraphsRange(int i, int j)
    {
        Layout layout;
        CharSequence charsequence;
        layout = mTextView.getLayout();
        if(layout == null)
            return TextUtils.packRangeInLong(-1, -1);
        charsequence = mTextView.getText();
        i = layout.getLineForOffset(i);
_L3:
        if(i > 0 && charsequence.charAt(layout.getLineEnd(i - 1) - 1) != '\n') goto _L2; else goto _L1
_L1:
        j = layout.getLineForOffset(j);
_L4:
        if(j >= layout.getLineCount() - 1 || charsequence.charAt(layout.getLineEnd(j) - 1) == '\n')
            return TextUtils.packRangeInLong(layout.getLineStart(i), layout.getLineEnd(j));
        break MISSING_BLOCK_LABEL_113;
_L2:
        i--;
          goto _L3
        j++;
          goto _L4
    }

    private PositionListener getPositionListener()
    {
        if(mPositionListener == null)
            mPositionListener = new PositionListener(null);
        return mPositionListener;
    }

    private float getPrimaryHorizontal(Layout layout, Layout layout1, int i, boolean flag)
    {
        if(TextUtils.isEmpty(layout.getText()) && layout1 != null && TextUtils.isEmpty(layout1.getText()) ^ true)
            return layout1.getPrimaryHorizontal(i, flag);
        else
            return layout.getPrimaryHorizontal(i, flag);
    }

    private SelectionActionModeHelper getSelectionActionModeHelper()
    {
        if(mSelectionActionModeHelper == null)
            mSelectionActionModeHelper = new SelectionActionModeHelper(this);
        return mSelectionActionModeHelper;
    }

    private android.view.View.DragShadowBuilder getTextThumbnailBuilder(int i, int j)
    {
        TextView textview = (TextView)View.inflate(mTextView.getContext(), 0x1090103, null);
        if(textview == null)
            throw new IllegalArgumentException("Unable to inflate text drag thumbnail");
        int k = j;
        if(j - i > DRAG_SHADOW_MAX_TEXT_LENGTH)
            k = TextUtils.unpackRangeEndFromLong(getCharClusterRange(DRAG_SHADOW_MAX_TEXT_LENGTH + i));
        textview.setText(mTextView.getTransformedText(i, k));
        textview.setTextColor(mTextView.getTextColors());
        textview.setTextAppearance(16);
        textview.setGravity(17);
        textview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        textview.measure(i, i);
        textview.layout(0, 0, textview.getMeasuredWidth(), textview.getMeasuredHeight());
        textview.invalidate();
        return new android.view.View.DragShadowBuilder(textview);
    }

    private int getWordEnd(int i)
    {
        int j = getWordIteratorWithText().nextBoundary(i);
        if(getWordIteratorWithText().isAfterPunctuation(j))
            j = getWordIteratorWithText().getPunctuationEnd(i);
        else
            j = getWordIteratorWithText().getNextWordEndOnTwoWordBoundary(i);
        if(j == -1)
            return i;
        else
            return j;
    }

    private WordIterator getWordIteratorWithText()
    {
        if(mWordIteratorWithText == null)
        {
            mWordIteratorWithText = new WordIterator(mTextView.getTextServicesLocale());
            mUpdateWordIteratorText = true;
        }
        if(mUpdateWordIteratorText)
        {
            CharSequence charsequence = mTextView.getText();
            mWordIteratorWithText.setCharSequence(charsequence, 0, charsequence.length());
            mUpdateWordIteratorText = false;
        }
        return mWordIteratorWithText;
    }

    private int getWordStart(int i)
    {
        int j = getWordIteratorWithText().prevBoundary(i);
        if(getWordIteratorWithText().isOnPunctuation(j))
            j = getWordIteratorWithText().getPunctuationBeginning(i);
        else
            j = getWordIteratorWithText().getPrevWordBeginningOnTwoWordsBoundary(i);
        if(j == -1)
            return i;
        else
            return j;
    }

    private boolean hasPasswordTransformationMethod()
    {
        return mTextView.getTransformationMethod() instanceof PasswordTransformationMethod;
    }

    private void hideCursorControllers()
    {
        if(mSuggestionsPopupWindow != null && mSuggestionsPopupWindow.isShowingUp() ^ true)
            mSuggestionsPopupWindow.hide();
        hideInsertionPointCursorController();
        stopSelectionActionMode();
    }

    private void hideError()
    {
        if(mErrorPopup != null && mErrorPopup.isShowing())
            mErrorPopup.dismiss();
        mShowErrorAfterAttach = false;
    }

    private void hideSpanControllers()
    {
        if(mSpanController != null)
            mSpanController.hide();
    }

    private static boolean isBigFontMode()
    {
        switch(MiuiConfiguration.getScaleMode())
        {
        default:
            return false;

        case 11: // '\013'
        case 15: // '\017'
            return true;
        }
    }

    private boolean isCursorInsideEasyCorrectionSpan()
    {
        SuggestionSpan asuggestionspan[] = (SuggestionSpan[])((Spannable)mTextView.getText()).getSpans(mTextView.getSelectionStart(), mTextView.getSelectionEnd(), android/text/style/SuggestionSpan);
        for(int i = 0; i < asuggestionspan.length; i++)
            if((asuggestionspan[i].getFlags() & 1) != 0)
                return true;

        return false;
    }

    private boolean isOffsetVisible(int i)
    {
        Layout layout = mTextView.getLayout();
        if(layout == null)
        {
            return false;
        } else
        {
            int j = layout.getLineForOffset(i);
            int k = layout.getLineBottom(j);
            layout.getLineTop(j);
            i = (int)layout.getPrimaryHorizontal(i);
            return mTextView.isPositionVisible(mTextView.viewportToContentHorizontalOffset() + i, mTextView.viewportToContentVerticalOffset() + k);
        }
    }

    private boolean isPositionOnText(float f, float f1)
    {
        Layout layout = mTextView.getLayout();
        if(layout == null)
            return false;
        int i = mTextView.getLineAtCoordinate(f1);
        f = mTextView.convertToLocalHorizontalCoordinate(f);
        if(f < layout.getLineLeft(i))
            return false;
        return f <= layout.getLineRight(i);
    }

    private boolean isPositionVisible(int i, int j)
    {
        float af[] = TEMP_POSITION;
        af;
        JVM INSTR monitorenter ;
        float af1[] = TEMP_POSITION;
        af1[0] = i;
        af1[1] = j;
        Object obj = mTextView;
_L6:
        if(obj == null)
            break; /* Loop/switch isn't completed */
        if(obj != mTextView)
        {
            af1[0] = af1[0] - (float)((View) (obj)).getScrollX();
            af1[1] = af1[1] - (float)((View) (obj)).getScrollY();
        }
        if(af1[0] < 0.0F) goto _L2; else goto _L1
_L1:
        float f = af1[1];
        if(f >= 0.0F) goto _L3; else goto _L2
_L2:
        af;
        JVM INSTR monitorexit ;
        return false;
_L3:
        if(af1[0] > (float)((View) (obj)).getWidth() || af1[1] > (float)((View) (obj)).getHeight()) goto _L2; else goto _L4
_L4:
        if(!((View) (obj)).getMatrix().isIdentity())
            ((View) (obj)).getMatrix().mapPoints(af1);
        af1[0] = af1[0] + (float)((View) (obj)).getLeft();
        af1[1] = af1[1] + (float)((View) (obj)).getTop();
        obj = ((View) (obj)).getParent();
        if(obj instanceof View)
        {
            obj = (View)obj;
            continue; /* Loop/switch isn't completed */
        }
        obj = null;
        if(true) goto _L6; else goto _L5
_L5:
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    private static boolean isValidRange(CharSequence charsequence, int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= j)
            {
                flag1 = flag;
                if(j <= charsequence.length())
                    flag1 = true;
            }
        }
        return flag1;
    }

    private boolean needsToSelectAllToSelectWordOrParagraph()
    {
        if(mTextView.hasPasswordTransformationMethod())
            return true;
        int i = mTextView.getInputType();
        int j = i & 0xf;
        for(i &= 0xff0; j == 2 || j == 3 || j == 4 || i == 16 || i == 32 || i == 208 || i == 176;)
            return true;

        return false;
    }

    private void resumeBlink()
    {
        if(mBlink != null)
        {
            mBlink.uncancel();
            makeBlink();
        }
    }

    private boolean selectCurrentParagraph()
    {
        if(!mTextView.canSelectText())
            return false;
        if(needsToSelectAllToSelectWordOrParagraph())
            return mTextView.selectAllText();
        long l = getLastTouchOffsets();
        l = getParagraphsRange(TextUtils.unpackRangeStartFromLong(l), TextUtils.unpackRangeEndFromLong(l));
        int i = TextUtils.unpackRangeStartFromLong(l);
        int j = TextUtils.unpackRangeEndFromLong(l);
        if(i < j)
        {
            Selection.setSelection((Spannable)mTextView.getText(), i, j);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean selectCurrentWordAndStartDrag()
    {
        if(mInsertionActionModeRunnable != null)
            mTextView.removeCallbacks(mInsertionActionModeRunnable);
        if(extractedTextModeWillBeStarted())
            return false;
        if(!checkField())
            return false;
        if(!mTextView.hasSelection() && selectCurrentWord() ^ true)
        {
            return false;
        } else
        {
            stopTextActionModeWithPreservingSelection();
            getSelectionController().enterDrag(2);
            return true;
        }
    }

    private void sendUpdateSelection()
    {
        if(mInputMethodState != null && mInputMethodState.mBatchEditNesting <= 0)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
            {
                int i = mTextView.getSelectionStart();
                int j = mTextView.getSelectionEnd();
                int k = -1;
                int l = -1;
                if(mTextView.getText() instanceof Spannable)
                {
                    Spannable spannable = (Spannable)mTextView.getText();
                    k = EditableInputConnection.getComposingSpanStart(spannable);
                    l = EditableInputConnection.getComposingSpanEnd(spannable);
                }
                inputmethodmanager.updateSelection(mTextView, i, j, k, l);
            }
        }
    }

    private void setErrorIcon(Drawable drawable)
    {
        TextView.Drawables drawables = mTextView.mDrawables;
        TextView.Drawables drawables1 = drawables;
        if(drawables == null)
        {
            TextView textview = mTextView;
            drawables1 = new TextView.Drawables(mTextView.getContext());
            textview.mDrawables = drawables1;
        }
        drawables1.setErrorDrawable(drawable, mTextView);
        mTextView.resetResolvedDrawables();
        mTextView.invalidate();
        mTextView.requestLayout();
    }

    private void setSelectionTranslation(int i, int j)
    {
        if(!hasSelectionController())
            return;
        float af[] = new float[2];
        float[] _tmp = af;
        af[0] = 0.0F;
        af[1] = 0.0F;
        Layout layout = mTextView.getLayout();
        if(layout != null && j > i)
        {
            float f = layout.getPrimaryHorizontal(i);
            float f1 = layout.getPrimaryHorizontal(j);
            int k = Selection.getSelectionStart(mTextView.getText());
            if(k < i || k > j)
            {
                af[0] = (f1 - f) / 2.0F;
                af[1] = (f - f1) / 2.0F;
            } else
            {
                float f2 = layout.getPrimaryHorizontal(k);
                if(k == i)
                    af[1] = f2 - f1;
                else
                if(k == j)
                {
                    af[0] = f2 - f;
                } else
                {
                    af[0] = f2 - f;
                    af[1] = f2 - f1;
                }
            }
        }
        getSelectionController().setTranslationCache(af);
    }

    private boolean shouldBlink()
    {
        boolean flag = false;
        if(!isCursorVisible() || mTextView.isFocused() ^ true)
            return false;
        int i = mTextView.getSelectionStart();
        if(i < 0)
            return false;
        int j = mTextView.getSelectionEnd();
        if(j < 0)
            return false;
        if(i == j)
            flag = true;
        return flag;
    }

    private boolean shouldFilterOutTouchEvent(MotionEvent motionevent)
    {
        if(!motionevent.isFromSource(8194))
            return false;
        boolean flag;
        int i;
        if(((mLastButtonState ^ motionevent.getButtonState()) & 1) != 0)
            flag = true;
        else
            flag = false;
        i = motionevent.getActionMasked();
        if((i == 0 || i == 1) && flag ^ true)
            return true;
        return i == 2 && motionevent.isButtonPressed(1) ^ true;
    }

    private boolean shouldOfferToShowSuggestions()
    {
        Object obj = mTextView.getText();
        if(!(obj instanceof Spannable))
            return false;
        obj = (Spannable)obj;
        int i = mTextView.getSelectionStart();
        int j = mTextView.getSelectionEnd();
        SuggestionSpan asuggestionspan[] = (SuggestionSpan[])((Spannable) (obj)).getSpans(i, j, android/text/style/SuggestionSpan);
        if(asuggestionspan.length == 0)
            return false;
        if(i == j)
        {
            for(int k = 0; k < asuggestionspan.length; k++)
                if(asuggestionspan[k].getSuggestions().length > 0)
                    return true;

            return false;
        }
        int i1 = mTextView.getText().length();
        int j1 = 0;
        int k1 = mTextView.getText().length();
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        while(j2 < asuggestionspan.length) 
        {
            int k2 = ((Spannable) (obj)).getSpanStart(asuggestionspan[j2]);
            int l2 = ((Spannable) (obj)).getSpanEnd(asuggestionspan[j2]);
            int i3 = Math.min(i1, k2);
            j1 = Math.max(j1, l2);
            i1 = i2;
            int l = l1;
            int j3 = k1;
            if(i >= k2)
                if(i > l2)
                {
                    j3 = k1;
                    l = l1;
                    i1 = i2;
                } else
                {
                    if(i2 != 0 || asuggestionspan[j2].getSuggestions().length > 0)
                        l = 1;
                    else
                        l = 0;
                    j3 = Math.min(k1, k2);
                    l1 = Math.max(l1, l2);
                    i1 = l;
                    l = l1;
                }
            j2++;
            i2 = i1;
            i1 = i3;
            l1 = l;
            k1 = j3;
        }
        if(i2 == 0)
            return false;
        if(k1 >= l1)
            return false;
        return i1 >= k1 && j1 <= l1;
    }

    private void showError()
    {
        if(mTextView.getWindowToken() == null)
        {
            mShowErrorAfterAttach = true;
            return;
        }
        if(mErrorPopup == null)
        {
            TextView textview = (TextView)LayoutInflater.from(mTextView.getContext()).inflate(0x109010e, null);
            float f = mTextView.getResources().getDisplayMetrics().density;
            mErrorPopup = new ErrorPopup(textview, (int)(200F * f + 0.5F), (int)(50F * f + 0.5F));
            mErrorPopup.setFocusable(false);
            mErrorPopup.setInputMethodMode(1);
        }
        TextView textview1 = (TextView)mErrorPopup.getContentView();
        chooseSize(mErrorPopup, mError, textview1);
        textview1.setText(mError);
        mErrorPopup.showAsDropDown(mTextView, getErrorX(), getErrorY());
        mErrorPopup.fixDirection(mErrorPopup.isAboveAnchor());
    }

    private void startActivityFromContext(Context context, Intent intent)
    {
        if(context instanceof Activity)
        {
            context.startActivity(intent);
        } else
        {
            intent.addFlags(0x10000000);
            context.startActivity(intent);
        }
    }

    private void startDragAndDrop()
    {
        if(mTextView.isInExtractedMode())
            return;
        int i = mTextView.getSelectionStart();
        int j = mTextView.getSelectionEnd();
        ClipData clipdata = ClipData.newPlainText(null, mTextView.getTransformedText(i, j));
        DragLocalState draglocalstate = new DragLocalState(mTextView, i, j);
        mTextView.startDragAndDrop(clipdata, getTextThumbnailBuilder(i, j), draglocalstate, 256);
        stopTextActionMode();
        if(hasSelectionController())
            getSelectionController().resetTouchOffsets();
    }

    private void stopTextActionModeWithPreservingSelection()
    {
        if(mTextActionMode != null)
            mRestartActionModeOnNextRefresh = true;
        mPreserveSelection = true;
        stopTextActionMode();
        mPreserveSelection = false;
    }

    private void suspendBlink()
    {
        if(mBlink != null)
            mBlink.cancel();
    }

    private boolean touchPositionIsInSelection()
    {
        int i = mTextView.getSelectionStart();
        int j = mTextView.getSelectionEnd();
        if(i == j)
            return false;
        int k = j;
        int l = i;
        if(i > j)
        {
            l = j;
            k = i;
            Selection.setSelection((Spannable)mTextView.getText(), l, i);
        }
        SelectionModifierCursorController selectionmodifiercursorcontroller = getSelectionController();
        i = selectionmodifiercursorcontroller.getMinTouchOffset();
        j = selectionmodifiercursorcontroller.getMaxTouchOffset();
        boolean flag;
        if(i >= l && j < k)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void updateCursorPosition(int i, int j, int k, float f)
    {
        if(mCursorDrawable[i] == null)
            mCursorDrawable[i] = mTextView.getResources().getDrawable(mTextView.mCursorDrawableRes, mTextView.getContext().getTheme());
        if(mTempRect == null)
            mTempRect = new Rect();
        mCursorDrawable[i].getPadding(mTempRect);
        int l = mCursorDrawable[i].getIntrinsicWidth();
        int i1 = (int)Math.max(0.5F, f - 0.5F) - mTempRect.left;
        mCursorDrawable[i].setBounds(i1, j - mTempRect.top, i1 + l, mTempRect.bottom + k);
    }

    private void updateFloatingToolbarVisibility(MotionEvent motionevent)
    {
    }

    private void updateSpellCheckSpans(int i, int j, boolean flag)
    {
        mTextView.removeAdjacentSuggestionSpans(i);
        mTextView.removeAdjacentSuggestionSpans(j);
        if(mTextView.isTextEditable() && mTextView.isSuggestionsEnabled() && mTextView.isInExtractedMode() ^ true)
        {
            if(mSpellChecker == null && flag)
                mSpellChecker = new SpellChecker(mTextView);
            if(mSpellChecker != null)
                mSpellChecker.spellCheck(i, j);
        }
    }

    private void updateTapState(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if(i == 0)
        {
            boolean flag = motionevent.isFromSource(8194);
            if((mTapState == 1 || mTapState == 2 && flag) && SystemClock.uptimeMillis() - mLastTouchUpTime <= (long)ViewConfiguration.getDoubleTapTimeout())
            {
                if(mTapState == 1)
                    mTapState = 2;
                else
                    mTapState = 3;
            } else
            {
                mTapState = 1;
            }
        }
        if(i == 1)
            mLastTouchUpTime = SystemClock.uptimeMillis();
    }

    public void addSpanWatchers(Spannable spannable)
    {
        int i = spannable.length();
        if(mKeyListener != null)
            spannable.setSpan(mKeyListener, 0, i, 18);
        if(mSpanController == null)
            mSpanController = new SpanController();
        spannable.setSpan(mSpanController, 0, i, 18);
    }

    void adjustInputType(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        if((mInputType & 0xf) != 1) goto _L2; else goto _L1
_L1:
        if(flag || flag1)
            mInputType = mInputType & 0xfffff00f | 0x80;
        if(flag2)
            mInputType = mInputType & 0xfffff00f | 0xe0;
_L4:
        return;
_L2:
        if((mInputType & 0xf) == 2 && flag3)
            mInputType = mInputType & 0xfffff00f | 0x10;
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean areSuggestionsShown()
    {
        boolean flag;
        if(mSuggestionsPopupWindow != null)
            flag = mSuggestionsPopupWindow.isShowing();
        else
            flag = false;
        return flag;
    }

    public void beginBatchEdit()
    {
        mInBatchEditControllers = true;
        InputMethodState inputmethodstate = mInputMethodState;
        if(inputmethodstate != null)
        {
            int i = inputmethodstate.mBatchEditNesting + 1;
            inputmethodstate.mBatchEditNesting = i;
            if(i == 1)
            {
                inputmethodstate.mCursorChanged = false;
                inputmethodstate.mChangedDelta = 0;
                if(inputmethodstate.mContentChanged)
                {
                    inputmethodstate.mChangedStart = 0;
                    inputmethodstate.mChangedEnd = mTextView.getText().length();
                } else
                {
                    inputmethodstate.mChangedStart = -1;
                    inputmethodstate.mChangedEnd = -1;
                    inputmethodstate.mContentChanged = false;
                }
                mUndoInputFilter.beginBatchEdit();
                mTextView.onBeginBatchEdit();
            }
        }
    }

    boolean canRedo()
    {
        boolean flag = true;
        UndoOwner undoowner = mUndoOwner;
        if(!mAllowUndo || mUndoManager.countRedos(new UndoOwner[] {
    undoowner
}) <= 0)
            flag = false;
        return flag;
    }

    boolean canUndo()
    {
        boolean flag = true;
        UndoOwner undoowner = mUndoOwner;
        if(!mAllowUndo || mUndoManager.countUndos(new UndoOwner[] {
    undoowner
}) <= 0)
            flag = false;
        return flag;
    }

    boolean checkField()
    {
        if(!mTextView.canSelectText() || mTextView.requestFocus() ^ true)
        {
            Log.w("TextView", "TextView does not support text selection. Selection cancelled.");
            return false;
        } else
        {
            return true;
        }
    }

    boolean checkFieldAndSelectCurrentWord()
    {
        if(!mTextView.canSelectText() || mTextView.requestFocus() ^ true)
        {
            Log.w("TextView", "TextView does not support text selection. Selection cancelled.");
            return false;
        }
        if(!mTextView.hasSelection())
            return selectCurrentWord();
        else
            return true;
    }

    void createInputContentTypeIfNeeded()
    {
        if(mInputContentType == null)
            mInputContentType = new InputContentType();
    }

    void createInputMethodStateIfNeeded()
    {
        if(mInputMethodState == null)
            mInputMethodState = new InputMethodState();
    }

    public void endBatchEdit()
    {
        mInBatchEditControllers = false;
        InputMethodState inputmethodstate = mInputMethodState;
        if(inputmethodstate != null)
        {
            int i = inputmethodstate.mBatchEditNesting - 1;
            inputmethodstate.mBatchEditNesting = i;
            if(i == 0)
                finishBatchEdit(inputmethodstate);
        }
    }

    void ensureEndedBatchEdit()
    {
        InputMethodState inputmethodstate = mInputMethodState;
        if(inputmethodstate != null && inputmethodstate.mBatchEditNesting != 0)
        {
            inputmethodstate.mBatchEditNesting = 0;
            finishBatchEdit(inputmethodstate);
        }
    }

    boolean extractText(ExtractedTextRequest extractedtextrequest, ExtractedText extractedtext)
    {
        return extractTextInternal(extractedtextrequest, -1, -1, -1, extractedtext);
    }

    boolean extractedTextModeWillBeStarted()
    {
        boolean flag = false;
        if(!mTextView.isInExtractedMode())
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
                flag = inputmethodmanager.isFullscreenMode();
            return flag;
        } else
        {
            return false;
        }
    }

    void finishBatchEdit(InputMethodState inputmethodstate)
    {
        mTextView.onEndBatchEdit();
        mUndoInputFilter.endBatchEdit();
        if(!inputmethodstate.mContentChanged && !inputmethodstate.mSelectionModeChanged) goto _L2; else goto _L1
_L1:
        mTextView.updateAfterEdit();
        reportExtractedText();
_L4:
        sendUpdateSelection();
        return;
_L2:
        if(inputmethodstate.mCursorChanged)
            mTextView.invalidateCursor();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void forgetUndoRedo()
    {
        UndoOwner aundoowner[] = new UndoOwner[1];
        aundoowner[0] = mUndoOwner;
        mUndoManager.forgetUndos(aundoowner, -1);
        mUndoManager.forgetRedos(aundoowner, -1);
    }

    public Drawable[] getCursorDrawable()
    {
        return mCursorDrawable;
    }

    InsertionPointCursorController getInsertionController()
    {
        if(!mInsertionControllerEnabled)
            return null;
        if(mInsertionPointCursorController == null)
        {
            mInsertionPointCursorController = new InsertionPointCursorController(null);
            mTextView.getViewTreeObserver().addOnTouchModeChangeListener(mInsertionPointCursorController);
        }
        return mInsertionPointCursorController;
    }

    SelectionModifierCursorController getSelectionController()
    {
        if(!mSelectionControllerEnabled)
            return null;
        if(mSelectionModifierCursorController == null)
        {
            mSelectionModifierCursorController = new SelectionModifierCursorController();
            mTextView.getViewTreeObserver().addOnTouchModeChangeListener(mSelectionModifierCursorController);
        }
        return mSelectionModifierCursorController;
    }

    ActionMode getTextActionMode()
    {
        return mTextActionMode;
    }

    TextView getTextView()
    {
        return mTextView;
    }

    public WordIterator getWordIterator()
    {
        if(mWordIterator == null)
            mWordIterator = new WordIterator(mTextView.getTextServicesLocale());
        return mWordIterator;
    }

    boolean hasInsertionController()
    {
        return mInsertionControllerEnabled;
    }

    boolean hasSelectionController()
    {
        return mSelectionControllerEnabled;
    }

    void hideCursorAndSpanControllers()
    {
        hideCursorControllers();
        hideSpanControllers();
    }

    void hideFloatingToolbar(int i)
    {
    }

    void hideInsertionPointCursorController()
    {
        if(mInsertionPointCursorController != null)
            mInsertionPointCursorController.hide();
    }

    void invalidateActionModeAsync()
    {
        getSelectionActionModeHelper().invalidateActionModeAsync();
    }

    void invalidateHandlesAndActionMode()
    {
        if(mSelectionModifierCursorController != null)
            mSelectionModifierCursorController.invalidateHandles();
        if(mInsertionPointCursorController != null)
            mInsertionPointCursorController.invalidateHandle();
        if(mTextActionMode != null)
            mTextActionMode.invalidate();
    }

    void invalidateTextDisplayList()
    {
        if(mTextRenderNodes != null)
        {
            for(int i = 0; i < mTextRenderNodes.length; i++)
                if(mTextRenderNodes[i] != null)
                    mTextRenderNodes[i].isDirty = true;

        }
    }

    void invalidateTextDisplayList(Layout layout, int i, int j)
    {
        if(mTextRenderNodes == null || !(layout instanceof DynamicLayout)) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int ai[];
        int i1;
        k = layout.getLineForOffset(i);
        l = layout.getLineForOffset(j);
        DynamicLayout dynamiclayout = (DynamicLayout)layout;
        layout = dynamiclayout.getBlockEndLines();
        ai = dynamiclayout.getBlockIndices();
        i1 = dynamiclayout.getNumberOfBlocks();
        i = 0;
_L7:
        j = i;
        if(i >= i1) goto _L4; else goto _L3
_L3:
        if(layout[i] < k) goto _L6; else goto _L5
_L5:
        j = i;
_L4:
        if(j < i1)
        {
            i = ai[j];
            if(i != -1)
                mTextRenderNodes[i].isDirty = true;
            if(layout[j] < l)
                break MISSING_BLOCK_LABEL_115;
        }
_L2:
        return;
_L6:
        i++;
          goto _L7
        j++;
          goto _L4
    }

    boolean isCursorVisible()
    {
        boolean flag;
        if(mCursorVisible)
            flag = mTextView.isTextEditable();
        else
            flag = false;
        return flag;
    }

    public boolean isInSwipeSelectionMode()
    {
        if(getSelectionController() != null)
            return SelectionModifierCursorController._2D_get0(getSelectionController());
        else
            return false;
    }

    void makeBlink()
    {
        if(!shouldBlink()) goto _L2; else goto _L1
_L1:
        mShowCursor = SystemClock.uptimeMillis();
        if(mBlink == null)
            mBlink = new Blink(null);
        mTextView.removeCallbacks(mBlink);
        mTextView.postDelayed(mBlink, 500L);
_L4:
        return;
_L2:
        if(mBlink != null)
            mTextView.removeCallbacks(mBlink);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void onAttachedToWindow()
    {
        if(mShowErrorAfterAttach)
        {
            showError();
            mShowErrorAfterAttach = false;
        }
        ViewTreeObserver viewtreeobserver = mTextView.getViewTreeObserver();
        if(mInsertionPointCursorController != null)
            viewtreeobserver.addOnTouchModeChangeListener(mInsertionPointCursorController);
        if(mSelectionModifierCursorController != null)
        {
            mSelectionModifierCursorController.resetTouchOffsets();
            viewtreeobserver.addOnTouchModeChangeListener(mSelectionModifierCursorController);
        }
        updateSpellCheckSpans(0, mTextView.getText().length(), true);
        if(mTextView.hasTransientState() && mTextView.getSelectionStart() != mTextView.getSelectionEnd())
        {
            mTextView.setHasTransientState(false);
            startSelectionActionMode();
        }
    }

    public void onCommitCorrection(CorrectionInfo correctioninfo)
    {
        if(mCorrectionHighlighter == null)
            mCorrectionHighlighter = new CorrectionHighlighter();
        else
            CorrectionHighlighter._2D_wrap0(mCorrectionHighlighter, false);
        mCorrectionHighlighter.highlight(correctioninfo);
        mUndoInputFilter.freezeLastEdit();
    }

    void onCreateContextMenu(ContextMenu contextmenu)
    {
    }

    void onDetachedFromWindow()
    {
        if(mError != null)
            hideError();
        if(mBlink != null)
            mTextView.removeCallbacks(mBlink);
        if(mInsertionPointCursorController != null)
            mInsertionPointCursorController.onDetached();
        if(mSelectionModifierCursorController != null)
            mSelectionModifierCursorController.onDetached();
        if(mShowSuggestionRunnable != null)
            mTextView.removeCallbacks(mShowSuggestionRunnable);
        discardTextDisplayLists();
        if(mSpellChecker != null)
        {
            mSpellChecker.closeSession();
            mSpellChecker = null;
        }
        hideCursorAndSpanControllers();
        stopTextActionModeWithPreservingSelection();
    }

    void onDraw(Canvas canvas, Layout layout, Path path, Paint paint, int i)
    {
        int j = mTextView.getSelectionStart();
        int k = mTextView.getSelectionEnd();
        Object obj = mInputMethodState;
        if(obj != null && ((InputMethodState) (obj)).mBatchEditNesting == 0)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
            {
                if(inputmethodmanager.isActive(mTextView) && (((InputMethodState) (obj)).mContentChanged || ((InputMethodState) (obj)).mSelectionModeChanged))
                    reportExtractedText();
                if(inputmethodmanager.isWatchingCursor(mTextView) && path != null)
                {
                    path.computeBounds(((InputMethodState) (obj)).mTmpRectF, true);
                    float af[] = ((InputMethodState) (obj)).mTmpOffset;
                    ((InputMethodState) (obj)).mTmpOffset[1] = 0.0F;
                    af[0] = 0.0F;
                    canvas.getMatrix().mapPoints(((InputMethodState) (obj)).mTmpOffset);
                    ((InputMethodState) (obj)).mTmpRectF.offset(((InputMethodState) (obj)).mTmpOffset[0], ((InputMethodState) (obj)).mTmpOffset[1]);
                    ((InputMethodState) (obj)).mTmpRectF.offset(0.0F, i);
                    ((InputMethodState) (obj)).mCursorRectInWindow.set((int)((double)((InputMethodState) (obj)).mTmpRectF.left + 0.5D), (int)((double)((InputMethodState) (obj)).mTmpRectF.top + 0.5D), (int)((double)((InputMethodState) (obj)).mTmpRectF.right + 0.5D), (int)((double)((InputMethodState) (obj)).mTmpRectF.bottom + 0.5D));
                    inputmethodmanager.updateCursor(mTextView, ((InputMethodState) (obj)).mCursorRectInWindow.left, ((InputMethodState) (obj)).mCursorRectInWindow.top, ((InputMethodState) (obj)).mCursorRectInWindow.right, ((InputMethodState) (obj)).mCursorRectInWindow.bottom);
                }
            }
        }
        if(mCorrectionHighlighter != null)
            mCorrectionHighlighter.draw(canvas, i);
        obj = path;
        if(path != null)
        {
            obj = path;
            if(j == k)
            {
                obj = path;
                if(mCursorCount > 0)
                {
                    drawCursor(canvas, i);
                    obj = null;
                }
            }
        }
        if(mTextView.canHaveDisplayList() && canvas.isHardwareAccelerated())
            drawHardwareAccelerated(canvas, layout, ((Path) (obj)), paint, i);
        else
            layout.draw(canvas, ((Path) (obj)), paint, i);
    }

    void onDrop(DragEvent dragevent)
    {
        SpannableStringBuilder spannablestringbuilder;
        Object obj;
        spannablestringbuilder = new SpannableStringBuilder();
        obj = DragAndDropPermissions.obtain(dragevent);
        if(obj != null)
            ((DragAndDropPermissions) (obj)).takeTransient();
        ClipData clipdata;
        int i;
        clipdata = dragevent.getClipData();
        i = clipdata.getItemCount();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        spannablestringbuilder.append(clipdata.getItemAt(j).coerceToStyledText(mTextView.getContext()));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(obj != null)
            ((DragAndDropPermissions) (obj)).release();
        mTextView.beginBatchEdit();
        mUndoInputFilter.freezeLastEdit();
        int k;
        k = mTextView.getOffsetForPosition(dragevent.getX(), dragevent.getY());
        obj = dragevent.getLocalState();
        dragevent = null;
        if(obj instanceof DragLocalState)
            dragevent = (DragLocalState)obj;
        if(dragevent == null) goto _L4; else goto _L3
_L3:
        if(((DragLocalState) (dragevent)).sourceTextView == mTextView)
            j = 1;
        else
            j = 0;
_L6:
        if(!j)
            break; /* Loop/switch isn't completed */
        if(k < ((DragLocalState) (dragevent)).start)
            break; /* Loop/switch isn't completed */
        i = ((DragLocalState) (dragevent)).end;
        if(k < i)
        {
            mTextView.endBatchEdit();
            mUndoInputFilter.freezeLastEdit();
            return;
        }
        break; /* Loop/switch isn't completed */
        dragevent;
        if(obj != null)
            ((DragAndDropPermissions) (obj)).release();
        throw dragevent;
_L4:
        j = 0;
        if(true) goto _L6; else goto _L5
_L5:
        int l;
        l = mTextView.getText().length();
        Selection.setSelection((Spannable)mTextView.getText(), k);
        mTextView.replaceText_internal(k, k, spannablestringbuilder);
        if(!j)
            break MISSING_BLOCK_LABEL_420;
        int i1;
        int j1;
        i1 = ((DragLocalState) (dragevent)).start;
        j1 = ((DragLocalState) (dragevent)).end;
        i = j1;
        j = i1;
        if(k > i1)
            break MISSING_BLOCK_LABEL_318;
        i = mTextView.getText().length() - l;
        j = i1 + i;
        i = j1 + i;
        mTextView.deleteText_internal(j, i);
        i = Math.max(0, j - 1);
        j = Math.min(mTextView.getText().length(), j + 1);
        if(j <= i + 1)
            break MISSING_BLOCK_LABEL_420;
        dragevent = mTextView.getTransformedText(i, j);
        if(Character.isSpaceChar(dragevent.charAt(0)) && Character.isSpaceChar(dragevent.charAt(1)))
            mTextView.deleteText_internal(i, i + 1);
        mTextView.endBatchEdit();
        mUndoInputFilter.freezeLastEdit();
        return;
        dragevent;
        mTextView.endBatchEdit();
        mUndoInputFilter.freezeLastEdit();
        throw dragevent;
    }

    void onFocusChanged(boolean flag, int i)
    {
        mShowCursor = SystemClock.uptimeMillis();
        ensureEndedBatchEdit();
        if(!flag) goto _L2; else goto _L1
_L1:
        int j = mTextView.getSelectionStart();
        int k = mTextView.getSelectionEnd();
        int l;
        if(mSelectAllOnFocus && j == 0)
        {
            if(k == mTextView.getText().length())
                l = 1;
            else
                l = 0;
        } else
        {
            l = 0;
        }
        if(mFrozenWithFocus && mTextView.hasSelection())
            flag = l ^ true;
        else
            flag = false;
        mCreatedWithASelection = flag;
        if(!mFrozenWithFocus || j < 0 || k < 0)
        {
            l = getLastTapPosition();
            if(l >= 0)
                Selection.setSelection((Spannable)mTextView.getText(), l);
            MovementMethod movementmethod = mTextView.getMovementMethod();
            if(movementmethod != null)
                movementmethod.onTakeFocus(mTextView, (Spannable)mTextView.getText(), i);
            if((mTextView.isInExtractedMode() || mSelectionMoved) && j >= 0 && k >= 0)
                Selection.setSelection((Spannable)mTextView.getText(), j, k);
            if(mSelectAllOnFocus)
                mTextView.selectAllText();
            mTouchFocusSelected = true;
        }
        mFrozenWithFocus = false;
        mSelectionMoved = false;
        if(mError != null)
            showError();
        makeBlink();
_L4:
        return;
_L2:
        if(mError != null)
            hideError();
        mTextView.onEndBatchEdit();
        if(!mTextView.isInExtractedMode())
            break; /* Loop/switch isn't completed */
        hideCursorAndSpanControllers();
        stopTextActionModeWithPreservingSelection();
_L5:
        if(mSelectionModifierCursorController != null)
            mSelectionModifierCursorController.resetTouchOffsets();
        mFirstTouchUp = true;
        if(true) goto _L4; else goto _L3
_L3:
        hideCursorAndSpanControllers();
        if(mTextView.isTemporarilyDetached())
            stopTextActionModeWithPreservingSelection();
        else
            stopTextActionMode();
        downgradeEasyCorrectionSpans();
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    void onLocaleChanged()
    {
        mWordIterator = null;
    }

    void onScreenStateChanged(int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 32
    //                   1 25;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        resumeBlink();
        continue; /* Loop/switch isn't completed */
_L2:
        suspendBlink();
        if(true) goto _L1; else goto _L4
_L4:
    }

    void onScrollChanged()
    {
        if(mPositionListener != null)
            mPositionListener.onScrollChanged();
    }

    void onTouchEvent(MotionEvent motionevent)
    {
        boolean flag = shouldFilterOutTouchEvent(motionevent);
        mLastButtonState = motionevent.getButtonState();
        if(flag)
        {
            if(motionevent.getActionMasked() == 1)
                mDiscardNextActionUp = true;
            return;
        }
        updateTapState(motionevent);
        updateFloatingToolbarVisibility(motionevent);
        if(hasSelectionController())
            getSelectionController().onTouchEvent(motionevent);
        if(mShowSuggestionRunnable != null)
        {
            mTextView.removeCallbacks(mShowSuggestionRunnable);
            mShowSuggestionRunnable = null;
        }
        if(motionevent.getActionMasked() == 0)
        {
            mLastDownPositionX = motionevent.getX();
            mLastDownPositionY = motionevent.getY();
            mTouchFocusSelected = false;
            mIgnoreActionUpEvent = false;
        }
    }

    void onTouchUpEvent(MotionEvent motionevent)
    {
        if(getSelectionActionModeHelper().resetSelection(getTextView().getOffsetForPosition(motionevent.getX(), motionevent.getY())))
            return;
        if(hasInsertionController())
            InsertionPointCursorController._2D_wrap0(getInsertionController()).isPopshowing();
        boolean flag;
        CharSequence charsequence;
        int i;
        if(mSelectAllOnFocus)
            flag = mTextView.didTouchFocusSelect();
        else
            flag = false;
        hideCursorAndSpanControllers();
        stopTextActionMode();
        charsequence = mTextView.getText();
        if(flag || charsequence.length() <= 0) goto _L2; else goto _L1
_L1:
        i = mTextView.getOffsetForPosition(motionevent.getX(), motionevent.getY());
        Selection.setSelection((Spannable)charsequence, i);
        if(mSpellChecker != null)
            mSpellChecker.onSelectionChanged();
        if(extractedTextModeWillBeStarted()) goto _L2; else goto _L3
_L3:
        if(!isCursorInsideEasyCorrectionSpan()) goto _L5; else goto _L4
_L4:
        mShowSuggestionRunnable = new Runnable() {

            public void run()
            {
                showSuggestions();
            }

            final Editor this$0;

            
            {
                this$0 = Editor.this;
                super();
            }
        }
;
        mTextView.postDelayed(mShowSuggestionRunnable, ViewConfiguration.getDoubleTapTimeout());
_L2:
        return;
_L5:
        if(hasInsertionController())
            getInsertionController().show();
        if(true) goto _L2; else goto _L6
_L6:
    }

    void onWindowFocusChanged(boolean flag)
    {
        if(flag)
        {
            if(mBlink != null)
            {
                mBlink.uncancel();
                makeBlink();
            }
        } else
        {
            if(mBlink != null)
                mBlink.cancel();
            if(mInputContentType != null)
                mInputContentType.enterDown = false;
            hideCursorAndSpanControllers();
            stopTextActionModeWithPreservingSelection();
            if(mSuggestionsPopupWindow != null)
                mSuggestionsPopupWindow.onParentLostFocus();
            ensureEndedBatchEdit();
        }
    }

    public boolean performLongClick(boolean flag)
    {
        boolean flag1 = flag;
        if(!flag)
        {
            flag1 = flag;
            if(isPositionOnText(mLastDownPositionX, mLastDownPositionY) ^ true)
            {
                flag1 = flag;
                if(mInsertionControllerEnabled)
                {
                    int i = mTextView.getOffsetForPosition(mLastDownPositionX, mLastDownPositionY);
                    Selection.setSelection((Spannable)mTextView.getText(), i);
                    getInsertionController().showWithActionPopup();
                    mIsInsertionActionModeStartPending = true;
                    flag1 = true;
                    MetricsLogger.action(mTextView.getContext(), 629, 0);
                }
            }
        }
        flag = flag1;
        if(!flag1)
        {
            flag = flag1;
            if(mTextView.hasSelection())
            {
                boolean flag2;
                if(touchPositionIsInSelection())
                {
                    startDragAndDrop();
                    MetricsLogger.action(mTextView.getContext(), 629, 2);
                } else
                {
                    getSelectionController().hide();
                    selectCurrentWord();
                    getSelectionController().show();
                    MetricsLogger.action(mTextView.getContext(), 629, 1);
                }
                flag = true;
            }
        }
        flag2 = flag;
        if(!flag)
        {
            flag = selectCurrentWordAndStartDrag();
            flag2 = flag;
            if(flag)
            {
                MetricsLogger.action(mTextView.getContext(), 629, 1);
                flag2 = flag;
            }
        }
        return flag2;
    }

    void prepareCursorControllers()
    {
        boolean flag = false;
        boolean flag1 = false;
        Object obj = mTextView.getRootView().getLayoutParams();
        boolean flag2;
        if(obj instanceof android.view.WindowManager.LayoutParams)
        {
            obj = (android.view.WindowManager.LayoutParams)obj;
            if(((android.view.WindowManager.LayoutParams) (obj)).type >= 1000)
            {
                if(((android.view.WindowManager.LayoutParams) (obj)).type > 1999)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = true;
            }
        }
        if(flag1 && mTextView.getLayout() != null)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            flag2 = isCursorVisible();
        else
            flag2 = false;
        mInsertionControllerEnabled = flag2;
        flag2 = flag;
        if(flag1)
            flag2 = mTextView.textCanBeSelected();
        mSelectionControllerEnabled = flag2;
        if(!mInsertionControllerEnabled)
        {
            hideInsertionPointCursorController();
            if(mInsertionPointCursorController != null)
            {
                mInsertionPointCursorController.onDetached();
                mInsertionPointCursorController = null;
            }
        }
        if(!mSelectionControllerEnabled)
        {
            stopSelectionActionMode();
            if(mSelectionModifierCursorController != null)
            {
                mSelectionModifierCursorController.onDetached();
                mSelectionModifierCursorController = null;
            }
        }
    }

    void redo()
    {
        if(!mAllowUndo)
        {
            return;
        } else
        {
            UndoOwner undoowner = mUndoOwner;
            mUndoManager.redo(new UndoOwner[] {
                undoowner
            }, 1);
            return;
        }
    }

    void refreshTextActionMode()
    {
        boolean flag;
        SelectionModifierCursorController selectionmodifiercursorcontroller;
        InsertionPointCursorController insertionpointcursorcontroller;
        if(extractedTextModeWillBeStarted())
        {
            mRestartActionModeOnNextRefresh = false;
            return;
        }
        flag = mTextView.hasSelection();
        selectionmodifiercursorcontroller = getSelectionController();
        insertionpointcursorcontroller = getInsertionController();
        if(selectionmodifiercursorcontroller != null && selectionmodifiercursorcontroller.isCursorBeingModified() || insertionpointcursorcontroller != null && insertionpointcursorcontroller.isCursorBeingModified())
        {
            mRestartActionModeOnNextRefresh = false;
            return;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        hideInsertionPointCursorController();
        if(mTextActionMode == null)
        {
            if(mRestartActionModeOnNextRefresh)
                startSelectionActionModeAsync(false);
        } else
        if(selectionmodifiercursorcontroller == null || selectionmodifiercursorcontroller.isActive() ^ true)
        {
            stopTextActionModeWithPreservingSelection();
            startSelectionActionModeAsync(false);
        } else
        {
            mTextActionMode.invalidateContentRect();
        }
_L4:
        mRestartActionModeOnNextRefresh = false;
        return;
_L2:
        if(insertionpointcursorcontroller == null || insertionpointcursorcontroller.isActive() ^ true)
            stopTextActionMode();
        else
        if(mTextActionMode != null)
            mTextActionMode.invalidateContentRect();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void replace()
    {
        int i = (mTextView.getSelectionStart() + mTextView.getSelectionEnd()) / 2;
        stopSelectionActionMode();
        Selection.setSelection((Spannable)mTextView.getText(), i);
        showSuggestions();
    }

    boolean reportExtractedText()
    {
        InputMethodState inputmethodstate = mInputMethodState;
        if(inputmethodstate != null)
        {
            boolean flag = inputmethodstate.mContentChanged;
            if(flag || inputmethodstate.mSelectionModeChanged)
            {
                inputmethodstate.mContentChanged = false;
                inputmethodstate.mSelectionModeChanged = false;
                ExtractedTextRequest extractedtextrequest = inputmethodstate.mExtractedTextRequest;
                if(extractedtextrequest != null)
                {
                    InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
                    if(inputmethodmanager != null)
                    {
                        if(inputmethodstate.mChangedStart < 0 && flag ^ true)
                            inputmethodstate.mChangedStart = -2;
                        if(extractTextInternal(extractedtextrequest, inputmethodstate.mChangedStart, inputmethodstate.mChangedEnd, inputmethodstate.mChangedDelta, inputmethodstate.mExtractedText))
                        {
                            inputmethodmanager.updateExtractedText(mTextView, extractedtextrequest.token, inputmethodstate.mExtractedText);
                            inputmethodstate.mChangedStart = -1;
                            inputmethodstate.mChangedEnd = -1;
                            inputmethodstate.mChangedDelta = 0;
                            inputmethodstate.mContentChanged = false;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    void restoreInstanceState(ParcelableParcel parcelableparcel)
    {
        Parcel parcel = parcelableparcel.getParcel();
        mUndoManager.restoreInstanceState(parcel, parcelableparcel.getClassLoader());
        mUndoInputFilter.restoreInstanceState(parcel);
        mUndoOwner = mUndoManager.getOwner("Editor", this);
    }

    ParcelableParcel saveInstanceState()
    {
        ParcelableParcel parcelableparcel = new ParcelableParcel(getClass().getClassLoader());
        Parcel parcel = parcelableparcel.getParcel();
        mUndoManager.saveInstanceState(parcel);
        mUndoInputFilter.saveInstanceState(parcel);
        return parcelableparcel;
    }

    boolean selectCurrentWord()
    {
        int i;
        int j;
        URLSpan aurlspan[];
        if(!canSelectText())
            return false;
        if(needsToSelectAllToSelectWordOrParagraph())
            return mTextView.selectAllText();
        long l = getLastTouchOffsets();
        i = TextUtils.unpackRangeStartFromLong(l);
        j = TextUtils.unpackRangeEndFromLong(l);
        if(i < 0 || i > mTextView.getText().length())
            return false;
        if(j < 0 || j > mTextView.getText().length())
            return false;
        aurlspan = (URLSpan[])((Spanned)mTextView.getText()).getSpans(i, j, android/text/style/URLSpan);
        if(aurlspan.length < 1) goto _L2; else goto _L1
_L1:
        int k;
        URLSpan urlspan = aurlspan[0];
        k = ((Spanned)mTextView.getText()).getSpanStart(urlspan);
        j = ((Spanned)mTextView.getText()).getSpanEnd(urlspan);
_L5:
        setSelectionTranslation(k, j);
        Selection.setSelection((Spannable)mTextView.getText(), k, j);
        long l1;
        WordIterator worditerator;
        boolean flag;
        int i1;
        int j1;
        if(j > k)
            flag = true;
        else
            flag = false;
        return flag;
_L2:
        worditerator = getWordIterator();
        worditerator.setCharSequence(mTextView.getText(), i, j);
        i1 = worditerator.getBeginning(i);
        j1 = worditerator.getEnd(j);
        if(i1 != -1 && j1 != -1) goto _L4; else goto _L3
_L3:
        l1 = getCharClusterRange(i);
        k = TextUtils.unpackRangeStartFromLong(l1);
        j = TextUtils.unpackRangeEndFromLong(l1);
        break; /* Loop/switch isn't completed */
_L4:
        j = j1;
        k = i1;
        if(i1 != j1) goto _L5; else goto _L3
    }

    void sendOnTextChanged(int i, int j, int k)
    {
        getSelectionActionModeHelper().onTextChanged(i, i + j);
        updateSpellCheckSpans(i, i + k, false);
        mUpdateWordIteratorText = true;
        hideCursorControllers();
        if(mSelectionModifierCursorController != null)
            mSelectionModifierCursorController.resetTouchOffsets();
        stopTextActionMode();
    }

    void setContextMenuAnchor(float f, float f1)
    {
        mContextMenuAnchorX = f;
        mContextMenuAnchorY = f1;
    }

    public void setError(CharSequence charsequence, Drawable drawable)
    {
        mError = TextUtils.stringOrSpannedString(charsequence);
        mErrorWasChanged = true;
        if(mError != null) goto _L2; else goto _L1
_L1:
        setErrorIcon(null);
        if(mErrorPopup != null)
        {
            if(mErrorPopup.isShowing())
                mErrorPopup.dismiss();
            mErrorPopup = null;
        }
_L4:
        return;
_L2:
        setErrorIcon(drawable);
        if(mTextView.isFocused())
            showError();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setFrame()
    {
        if(mErrorPopup != null)
        {
            TextView textview = (TextView)mErrorPopup.getContentView();
            chooseSize(mErrorPopup, mError, textview);
            mErrorPopup.update(mTextView, getErrorX(), getErrorY(), mErrorPopup.getWidth(), mErrorPopup.getHeight());
        }
    }

    void setRestartActionModeOnNextRefresh(boolean flag)
    {
        mRestartActionModeOnNextRefresh = flag;
    }

    void showSuggestions()
    {
        if(mSuggestionsPopupWindow == null)
            mSuggestionsPopupWindow = new SuggestionsPopupWindow();
        hideCursorAndSpanControllers();
        mSuggestionsPopupWindow.show();
    }

    void startInsertionActionMode()
    {
    }

    boolean startSelectionActionMode()
    {
        boolean flag = startSelectionActionModeInternal();
        if(!flag) goto _L2; else goto _L1
_L1:
        getSelectionController().show();
_L4:
        return flag;
_L2:
        if(getInsertionController() != null)
            getInsertionController().show();
        if(true) goto _L4; else goto _L3
_L3:
    }

    void startSelectionActionModeAsync(boolean flag)
    {
        getSelectionActionModeHelper().startActionModeAsync(flag);
    }

    boolean startSelectionActionModeInternal()
    {
        if(mTextActionMode != null)
        {
            mTextActionMode.invalidate();
            return false;
        }
        if(!checkFieldAndSelectCurrentWord())
            return false;
        if(!extractedTextModeWillBeStarted() && mCustomSelectionActionModeCallback != null && mCustomSelectionActionModeCallback.toString().contains("Mock for Callback"))
        {
            mAllowToStartActionMode = mCustomSelectionActionModeCallback.onCreateActionMode(mMockActionMode, new ActionMenu(mTextView.getContext()));
            if(!mAllowToStartActionMode)
            {
                Selection.setSelection((Spannable)mTextView.getText(), mTextView.getSelectionEnd());
                return false;
            }
        }
        if(!mTextView.isTextSelectable() && mShowSoftInputOnFocus)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
                inputmethodmanager.showSoftInput(mTextView, 0, null);
        }
        return true;
    }

    protected void stopSelectionActionMode()
    {
        if(mSelectionModifierCursorController != null)
            mSelectionModifierCursorController.hide();
        if(!mCustomSelectionActionModeCallbackDestroyed && mCustomSelectionActionModeCallback != null && mAllowToStartActionMode)
        {
            mCustomSelectionActionModeCallback.onDestroyActionMode(mMockActionMode);
            mCustomSelectionActionModeCallbackDestroyed = true;
        }
    }

    protected void stopTextActionMode()
    {
        if(mTextActionMode != null)
            mTextActionMode.finish();
        stopSelectionActionMode();
    }

    void undo()
    {
        if(!mAllowUndo)
        {
            return;
        } else
        {
            UndoOwner undoowner = mUndoOwner;
            mUndoManager.undo(new UndoOwner[] {
                undoowner
            }, 1);
            return;
        }
    }

    void updateCursorsPositions()
    {
        if(mTextView.mCursorDrawableRes == 0)
        {
            mCursorCount = 0;
            return;
        }
        Layout layout = getActiveLayout();
        int i = mTextView.getSelectionStart();
        int j = layout.getLineForOffset(i);
        int k = layout.getLineTop(j);
        int l = layout.getLineTop(j + 1);
        int i1;
        boolean flag;
        if(layout.isLevelBoundary(i))
            i1 = 2;
        else
            i1 = 1;
        mCursorCount = i1;
        i1 = l;
        if(mCursorCount == 2)
            i1 = k + l >> 1;
        flag = layout.shouldClampCursor(j);
        updateCursorPosition(0, k, i1, layout.getPrimaryHorizontal(i, flag));
        if(mCursorCount == 2)
            updateCursorPosition(1, i1, l, layout.getSecondaryHorizontal(i, flag));
    }

    static final int BLINK = 500;
    private static final boolean DEBUG_UNDO = false;
    private static int DRAG_SHADOW_MAX_TEXT_LENGTH = 0;
    static final int EXTRACT_NOTHING = -2;
    static final int EXTRACT_UNKNOWN = -1;
    private static final float LINE_SLOP_MULTIPLIER_FOR_HANDLEVIEWS = 0.5F;
    private static final int MENU_ITEM_ORDER_COPY = 4;
    private static final int MENU_ITEM_ORDER_CUT = 3;
    private static final int MENU_ITEM_ORDER_PASTE = 5;
    private static final int MENU_ITEM_ORDER_PASTE_AS_PLAIN_TEXT = 6;
    private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 10;
    private static final int MENU_ITEM_ORDER_REDO = 2;
    private static final int MENU_ITEM_ORDER_REPLACE = 9;
    private static final int MENU_ITEM_ORDER_SELECT_ALL = 8;
    private static final int MENU_ITEM_ORDER_SHARE = 7;
    private static final int MENU_ITEM_ORDER_UNDO = 1;
    private static final String MOCK_CALLBACK_NAME = "Mock for Callback";
    private static final String TAG = "Editor";
    private static final int TAP_STATE_DOUBLE_TAP = 2;
    private static final int TAP_STATE_FIRST_TAP = 1;
    private static final int TAP_STATE_INITIAL = 0;
    private static final int TAP_STATE_TRIPLE_CLICK = 3;
    private static final float TEMP_POSITION[] = new float[2];
    private static final String UNDO_OWNER_TAG = "Editor";
    private static final int UNSET_LINE = -1;
    private static final ActionMode mMockActionMode = new ActionMode() {

        public void finish()
        {
        }

        public View getCustomView()
        {
            return null;
        }

        public Menu getMenu()
        {
            return null;
        }

        public MenuInflater getMenuInflater()
        {
            return null;
        }

        public CharSequence getSubtitle()
        {
            return null;
        }

        public CharSequence getTitle()
        {
            return null;
        }

        public void invalidate()
        {
        }

        public void setCustomView(View view)
        {
        }

        public void setSubtitle(int i)
        {
        }

        public void setSubtitle(CharSequence charsequence)
        {
        }

        public void setTitle(int i)
        {
        }

        public void setTitle(CharSequence charsequence)
        {
        }

    }
;
    private boolean mAllowToStartActionMode;
    boolean mAllowUndo;
    Blink mBlink;
    private float mContextMenuAnchorX;
    private float mContextMenuAnchorY;
    CorrectionHighlighter mCorrectionHighlighter;
    boolean mCreatedWithASelection;
    int mCursorCount;
    final Drawable mCursorDrawable[] = new Drawable[2];
    boolean mCursorVisible;
    android.view.ActionMode.Callback mCustomInsertionActionModeCallback;
    android.view.ActionMode.Callback mCustomSelectionActionModeCallback;
    private boolean mCustomSelectionActionModeCallbackDestroyed;
    boolean mDiscardNextActionUp;
    CharSequence mError;
    ErrorPopup mErrorPopup;
    boolean mErrorWasChanged;
    boolean mFirstTouchUp;
    boolean mFrozenWithFocus;
    boolean mIgnoreActionUpEvent;
    boolean mInBatchEditControllers;
    InputContentType mInputContentType;
    InputMethodState mInputMethodState;
    int mInputType;
    private Runnable mInsertionActionModeRunnable;
    boolean mInsertionControllerEnabled;
    InsertionPointCursorController mInsertionPointCursorController;
    boolean mIsBeingLongClicked;
    boolean mIsInsertionActionModeStartPending;
    KeyListener mKeyListener;
    private int mLastButtonState;
    float mLastDownPositionX;
    float mLastDownPositionY;
    private long mLastTouchUpTime;
    private PositionListener mPositionListener;
    private boolean mPreserveSelection;
    final ProcessTextIntentActionsHandler mProcessTextIntentActionsHandler = new ProcessTextIntentActionsHandler(this, null);
    private boolean mRestartActionModeOnNextRefresh;
    boolean mSelectAllOnFocus;
    private Drawable mSelectHandleCenter;
    private Drawable mSelectHandleLeft;
    private Drawable mSelectHandleRight;
    private SelectionActionModeHelper mSelectionActionModeHelper;
    boolean mSelectionControllerEnabled;
    SelectionModifierCursorController mSelectionModifierCursorController;
    boolean mSelectionMoved;
    long mShowCursor;
    boolean mShowErrorAfterAttach;
    boolean mShowSoftInputOnFocus;
    Runnable mShowSuggestionRunnable;
    private SpanController mSpanController;
    SpellChecker mSpellChecker;
    SuggestionRangeSpan mSuggestionRangeSpan;
    SuggestionsPopupWindow mSuggestionsPopupWindow;
    private int mTapState;
    private Rect mTempRect;
    ActionMode mTextActionMode;
    boolean mTextIsSelectable;
    TextRenderNode mTextRenderNodes[];
    private TextView mTextView;
    boolean mTouchFocusSelected;
    final UndoInputFilter mUndoInputFilter = new UndoInputFilter(this);
    private final UndoManager mUndoManager = new UndoManager();
    private UndoOwner mUndoOwner;
    private boolean mUpdateWordIteratorText;
    WordIterator mWordIterator;
    private WordIterator mWordIteratorWithText;

    static 
    {
        DRAG_SHADOW_MAX_TEXT_LENGTH = 20;
    }

    // Unreferenced inner class android/widget/Editor$ActionPinnedPopupWindow$1

/* anonymous class */
    class ActionPinnedPopupWindow._cls1
        implements Runnable
    {

        public void run()
        {
            if(Editor._2D_wrap4(_fld0, Editor._2D_get6(_fld0).getSelectionStart()) || Editor._2D_wrap4(_fld0, Editor._2D_get6(_fld0).getSelectionEnd()) || ActionPinnedPopupWindow._2D_wrap0(ActionPinnedPopupWindow.this))
            {
                show();
                if(ActionPinnedPopupWindow._2D_get0(ActionPinnedPopupWindow.this) instanceof InsertionHandleView)
                    InsertionHandleView._2D_wrap0((InsertionHandleView)ActionPinnedPopupWindow._2D_get0(ActionPinnedPopupWindow.this));
            }
        }

        final ActionPinnedPopupWindow this$1;

            
            {
                this$1 = ActionPinnedPopupWindow.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/Editor$ActionPinnedPopupWindow$2

/* anonymous class */
    class ActionPinnedPopupWindow._cls2
        implements android.view.ViewTreeObserver.OnComputeInternalInsetsListener
    {

        public void onComputeInternalInsets(android.view.ViewTreeObserver.InternalInsetsInfo internalinsetsinfo)
        {
            internalinsetsinfo.contentInsets.setEmpty();
            internalinsetsinfo.visibleInsets.setEmpty();
            internalinsetsinfo.touchableRegion.set(ActionPinnedPopupWindow._2D_get1(ActionPinnedPopupWindow.this));
            internalinsetsinfo.setTouchableInsets(3);
        }

        final ActionPinnedPopupWindow this$1;

            
            {
                this$1 = ActionPinnedPopupWindow.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/Editor$ActionPopupWindow$1

/* anonymous class */
    class ActionPopupWindow._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 45
        //                       1 67;
               goto _L1 _L2 _L3
_L1:
            Log.e("Editor", "Unrecognised message received.");
_L5:
            setContentAreaAsTouchableSurface(true);
            return;
_L2:
            message = (TranslationResult)message.obj;
            ActionPopupWindow._2D_get0(ActionPopupWindow.this).updatePanel(message);
            continue; /* Loop/switch isn't completed */
_L3:
            ActionPopupWindow._2D_get0(ActionPopupWindow.this).updatePanel(null);
            if(true) goto _L5; else goto _L4
_L4:
        }

        final ActionPopupWindow this$1;

            
            {
                this$1 = ActionPopupWindow.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/Editor$HandleView$1

/* anonymous class */
    class HandleView._cls1
        implements PopupWindow.OnDismissListener
    {

        public void onDismiss()
        {
            onDetached();
        }

        final HandleView this$1;

            
            {
                this$1 = HandleView.this;
                super();
            }
    }

}
