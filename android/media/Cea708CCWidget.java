// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.graphics.*;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.CaptioningManager;
import android.widget.RelativeLayout;
import com.android.internal.widget.SubtitleView;
import java.nio.charset.Charset;
import java.util.*;

// Referenced classes of package android.media:
//            ClosedCaptionWidget

class Cea708CCWidget extends ClosedCaptionWidget
    implements Cea708CCParser.DisplayListener
{
    static class CCHandler
        implements android.os.Handler.Callback
    {

        private void clearWindows(int i)
        {
            if(i == 0)
                return;
            for(Iterator iterator = getWindowsFromBitmap(i).iterator(); iterator.hasNext(); ((CCWindowLayout)iterator.next()).clear());
        }

        private void defineWindow(Cea708CCParser.CaptionWindow captionwindow)
        {
            if(captionwindow == null)
                return;
            int i = captionwindow.id;
            if(i < 0 || i >= mCaptionWindowLayouts.length)
                return;
            CCWindowLayout ccwindowlayout = mCaptionWindowLayouts[i];
            CCWindowLayout ccwindowlayout1 = ccwindowlayout;
            if(ccwindowlayout == null)
                ccwindowlayout1 = new CCWindowLayout(mCCLayout.getContext());
            ccwindowlayout1.initWindow(mCCLayout, captionwindow);
            mCaptionWindowLayouts[i] = ccwindowlayout1;
            mCurrentWindowLayout = ccwindowlayout1;
        }

        private void delay(int i)
        {
            if(i < 0 || i > 255)
            {
                return;
            } else
            {
                mIsDelayed = true;
                mHandler.sendMessageDelayed(mHandler.obtainMessage(1), i * 100);
                return;
            }
        }

        private void delayCancel()
        {
            mIsDelayed = false;
            processPendingBuffer();
        }

        private void deleteWindows(int i)
        {
            if(i == 0)
                return;
            for(Iterator iterator = getWindowsFromBitmap(i).iterator(); iterator.hasNext();)
            {
                CCWindowLayout ccwindowlayout = (CCWindowLayout)iterator.next();
                ccwindowlayout.removeFromCaptionView();
                mCaptionWindowLayouts[ccwindowlayout.getCaptionWindowId()] = null;
            }

        }

        private void displayWindows(int i)
        {
            if(i == 0)
                return;
            for(Iterator iterator = getWindowsFromBitmap(i).iterator(); iterator.hasNext(); ((CCWindowLayout)iterator.next()).show());
        }

        private ArrayList getWindowsFromBitmap(int i)
        {
            ArrayList arraylist = new ArrayList();
            for(int j = 0; j < 8; j++)
            {
                if((1 << j & i) == 0)
                    continue;
                CCWindowLayout ccwindowlayout = mCaptionWindowLayouts[j];
                if(ccwindowlayout != null)
                    arraylist.add(ccwindowlayout);
            }

            return arraylist;
        }

        private void hideWindows(int i)
        {
            if(i == 0)
                return;
            for(Iterator iterator = getWindowsFromBitmap(i).iterator(); iterator.hasNext(); ((CCWindowLayout)iterator.next()).hide());
        }

        private void processPendingBuffer()
        {
            for(Iterator iterator = mPendingCaptionEvents.iterator(); iterator.hasNext(); processCaptionEvent((Cea708CCParser.CaptionEvent)iterator.next()));
            mPendingCaptionEvents.clear();
        }

        private void sendBufferToCurrentWindow(String s)
        {
            if(mCurrentWindowLayout != null)
            {
                mCurrentWindowLayout.sendBuffer(s);
                mHandler.removeMessages(2);
                mHandler.sendMessageDelayed(mHandler.obtainMessage(2), 60000L);
            }
        }

        private void sendControlToCurrentWindow(char c)
        {
            if(mCurrentWindowLayout != null)
                mCurrentWindowLayout.sendControl(c);
        }

        private void setCurrentWindowLayout(int i)
        {
            if(i < 0 || i >= mCaptionWindowLayouts.length)
                return;
            CCWindowLayout ccwindowlayout = mCaptionWindowLayouts[i];
            if(ccwindowlayout == null)
            {
                return;
            } else
            {
                mCurrentWindowLayout = ccwindowlayout;
                return;
            }
        }

        private void setPenAttr(Cea708CCParser.CaptionPenAttr captionpenattr)
        {
            if(mCurrentWindowLayout != null)
                mCurrentWindowLayout.setPenAttr(captionpenattr);
        }

        private void setPenColor(Cea708CCParser.CaptionPenColor captionpencolor)
        {
            if(mCurrentWindowLayout != null)
                mCurrentWindowLayout.setPenColor(captionpencolor);
        }

        private void setPenLocation(Cea708CCParser.CaptionPenLocation captionpenlocation)
        {
            if(mCurrentWindowLayout != null)
                mCurrentWindowLayout.setPenLocation(captionpenlocation.row, captionpenlocation.column);
        }

        private void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionwindowattr)
        {
            if(mCurrentWindowLayout != null)
                mCurrentWindowLayout.setWindowAttr(captionwindowattr);
        }

        private void toggleWindows(int i)
        {
            if(i == 0)
                return;
            for(Iterator iterator = getWindowsFromBitmap(i).iterator(); iterator.hasNext();)
            {
                CCWindowLayout ccwindowlayout = (CCWindowLayout)iterator.next();
                if(ccwindowlayout.isShown())
                    ccwindowlayout.hide();
                else
                    ccwindowlayout.show();
            }

        }

        public boolean handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                return false;

            case 1: // '\001'
                delayCancel();
                return true;

            case 2: // '\002'
                clearWindows(255);
                break;
            }
            return true;
        }

        public void processCaptionEvent(Cea708CCParser.CaptionEvent captionevent)
        {
            if(mIsDelayed)
            {
                mPendingCaptionEvents.add(captionevent);
                return;
            }
            captionevent.type;
            JVM INSTR tableswitch 1 16: default 100
        //                       1 101
        //                       2 115
        //                       3 132
        //                       4 149
        //                       5 166
        //                       6 183
        //                       7 200
        //                       8 217
        //                       9 234
        //                       10 251
        //                       11 258
        //                       12 265
        //                       13 279
        //                       14 293
        //                       15 307
        //                       16 321;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
            return;
_L2:
            sendBufferToCurrentWindow((String)captionevent.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            sendControlToCurrentWindow(((Character)captionevent.obj).charValue());
            continue; /* Loop/switch isn't completed */
_L4:
            setCurrentWindowLayout(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L5:
            clearWindows(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L6:
            displayWindows(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L7:
            hideWindows(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L8:
            toggleWindows(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L9:
            deleteWindows(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L10:
            delay(((Integer)captionevent.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L11:
            delayCancel();
            continue; /* Loop/switch isn't completed */
_L12:
            reset();
            continue; /* Loop/switch isn't completed */
_L13:
            setPenAttr((Cea708CCParser.CaptionPenAttr)captionevent.obj);
            continue; /* Loop/switch isn't completed */
_L14:
            setPenColor((Cea708CCParser.CaptionPenColor)captionevent.obj);
            continue; /* Loop/switch isn't completed */
_L15:
            setPenLocation((Cea708CCParser.CaptionPenLocation)captionevent.obj);
            continue; /* Loop/switch isn't completed */
_L16:
            setWindowAttr((Cea708CCParser.CaptionWindowAttr)captionevent.obj);
            continue; /* Loop/switch isn't completed */
_L17:
            defineWindow((Cea708CCParser.CaptionWindow)captionevent.obj);
            if(true) goto _L1; else goto _L18
_L18:
        }

        public void reset()
        {
            mCurrentWindowLayout = null;
            mIsDelayed = false;
            mPendingCaptionEvents.clear();
            for(int i = 0; i < 8; i++)
            {
                if(mCaptionWindowLayouts[i] != null)
                    mCaptionWindowLayouts[i].removeFromCaptionView();
                mCaptionWindowLayouts[i] = null;
            }

            mCCLayout.setVisibility(4);
            mHandler.removeMessages(2);
        }

        private static final int CAPTION_ALL_WINDOWS_BITMAP = 255;
        private static final long CAPTION_CLEAR_INTERVAL_MS = 60000L;
        private static final int CAPTION_WINDOWS_MAX = 8;
        private static final boolean DEBUG = false;
        private static final int MSG_CAPTION_CLEAR = 2;
        private static final int MSG_DELAY_CANCEL = 1;
        private static final String TAG = "CCHandler";
        private static final int TENTHS_OF_SECOND_IN_MILLIS = 100;
        private final CCLayout mCCLayout;
        private final CCWindowLayout mCaptionWindowLayouts[] = new CCWindowLayout[8];
        private CCWindowLayout mCurrentWindowLayout;
        private final Handler mHandler = new Handler(this);
        private boolean mIsDelayed;
        private final ArrayList mPendingCaptionEvents = new ArrayList();

        public CCHandler(CCLayout cclayout)
        {
            mIsDelayed = false;
            mCCLayout = cclayout;
        }
    }

    static class CCLayout extends ScaledLayout
        implements ClosedCaptionWidget.ClosedCaptionLayout
    {

        public void addOrUpdateViewToSafeTitleArea(CCWindowLayout ccwindowlayout, ScaledLayout.ScaledLayoutParams scaledlayoutparams)
        {
            if(mSafeTitleAreaLayout.indexOfChild(ccwindowlayout) < 0)
            {
                mSafeTitleAreaLayout.addView(ccwindowlayout, scaledlayoutparams);
                return;
            } else
            {
                mSafeTitleAreaLayout.updateViewLayout(ccwindowlayout, scaledlayoutparams);
                return;
            }
        }

        public void removeViewFromSafeTitleArea(CCWindowLayout ccwindowlayout)
        {
            mSafeTitleAreaLayout.removeView(ccwindowlayout);
        }

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            int i = mSafeTitleAreaLayout.getChildCount();
            for(int j = 0; j < i; j++)
                ((CCWindowLayout)mSafeTitleAreaLayout.getChildAt(j)).setCaptionStyle(captionstyle);

        }

        public void setFontScale(float f)
        {
            int i = mSafeTitleAreaLayout.getChildCount();
            for(int j = 0; j < i; j++)
                ((CCWindowLayout)mSafeTitleAreaLayout.getChildAt(j)).setFontScale(f);

        }

        private static final float SAFE_TITLE_AREA_SCALE_END_X = 0.9F;
        private static final float SAFE_TITLE_AREA_SCALE_END_Y = 0.9F;
        private static final float SAFE_TITLE_AREA_SCALE_START_X = 0.1F;
        private static final float SAFE_TITLE_AREA_SCALE_START_Y = 0.1F;
        private final ScaledLayout mSafeTitleAreaLayout;

        public CCLayout(Context context)
        {
            super(context);
            mSafeTitleAreaLayout = new ScaledLayout(context);
            addView(mSafeTitleAreaLayout, new ScaledLayout.ScaledLayoutParams(0.1F, 0.9F, 0.1F, 0.9F));
        }
    }

    static class CCView extends SubtitleView
    {

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            int i;
            if(captionstyle.hasForegroundColor())
                i = captionstyle.foregroundColor;
            else
                i = DEFAULT_CAPTION_STYLE.foregroundColor;
            setForegroundColor(i);
            if(captionstyle.hasBackgroundColor())
                i = captionstyle.backgroundColor;
            else
                i = DEFAULT_CAPTION_STYLE.backgroundColor;
            setBackgroundColor(i);
            if(captionstyle.hasEdgeType())
                i = captionstyle.edgeType;
            else
                i = DEFAULT_CAPTION_STYLE.edgeType;
            setEdgeType(i);
            if(captionstyle.hasEdgeColor())
                i = captionstyle.edgeColor;
            else
                i = DEFAULT_CAPTION_STYLE.edgeColor;
            setEdgeColor(i);
            setTypeface(captionstyle.getTypeface());
        }

        private static final android.view.accessibility.CaptioningManager.CaptionStyle DEFAULT_CAPTION_STYLE;

        static 
        {
            DEFAULT_CAPTION_STYLE = android.view.accessibility.CaptioningManager.CaptionStyle.DEFAULT;
        }

        public CCView(Context context)
        {
            this(context, null);
        }

        public CCView(Context context, AttributeSet attributeset)
        {
            this(context, attributeset, 0);
        }

        public CCView(Context context, AttributeSet attributeset, int i)
        {
            this(context, attributeset, i, 0);
        }

        public CCView(Context context, AttributeSet attributeset, int i, int j)
        {
            super(context, attributeset, i, j);
        }
    }

    static class CCWindowLayout extends RelativeLayout
        implements android.view.View.OnLayoutChangeListener
    {

        private int getScreenColumnCount()
        {
            return 42;
        }

        private void updateText(String s, boolean flag)
        {
            if(!flag)
                mBuilder.clear();
            if(s != null && s.length() > 0)
            {
                int i = mBuilder.length();
                mBuilder.append(s);
                CharacterStyle characterstyle;
                for(s = mCharacterStyles.iterator(); s.hasNext(); mBuilder.setSpan(characterstyle, i, mBuilder.length(), 33))
                    characterstyle = (CharacterStyle)s.next();

            }
            s = TextUtils.split(mBuilder.toString(), "\n");
            s = TextUtils.join("\n", Arrays.copyOfRange(s, Math.max(0, s.length - (mRowLimit + 1)), s.length));
            mBuilder.delete(0, mBuilder.length() - s.length());
            int k = 0;
            int l = mBuilder.length() - 1;
            int j = l;
            int i1;
            do
            {
                i1 = j;
                if(k > l)
                    break;
                i1 = j;
                if(mBuilder.charAt(k) > ' ')
                    break;
                k++;
            } while(true);
            for(; i1 >= k && mBuilder.charAt(i1) <= ' '; i1--);
            if(k == 0 && i1 == l)
            {
                mCCView.setText(mBuilder);
            } else
            {
                s = new SpannableStringBuilder();
                s.append(mBuilder);
                if(i1 < l)
                    s.delete(i1 + 1, l + 1);
                if(k > 0)
                    s.delete(0, k);
                mCCView.setText(s);
            }
        }

        private void updateTextSize()
        {
            if(mCCLayout == null)
                return;
            Object obj = new StringBuilder();
            int i = getScreenColumnCount();
            for(int j = 0; j < i; j++)
                ((StringBuilder) (obj)).append(mWidestChar);

            obj = ((StringBuilder) (obj)).toString();
            Paint paint = new Paint();
            paint.setTypeface(mCaptionStyle.getTypeface());
            float f = 0.0F;
            float f1;
            for(f1 = 255F; f < f1;)
            {
                float f2 = (f + f1) / 2.0F;
                paint.setTextSize(f2);
                float f3 = paint.measureText(((String) (obj)));
                if((float)mCCLayout.getWidth() * 0.8F > f3)
                    f = f2 + 0.01F;
                else
                    f1 = f2 - 0.01F;
            }

            mTextSize = mFontScale * f1;
            mCCView.setTextSize(mTextSize);
        }

        private void updateWidestChar()
        {
            Paint paint = new Paint();
            paint.setTypeface(mCaptionStyle.getTypeface());
            Charset charset = Charset.forName("ISO-8859-1");
            float f = 0.0F;
            for(int i = 0; i < 256;)
            {
                String s = new String(new byte[] {
                    (byte)i
                }, charset);
                float f1 = paint.measureText(s);
                float f2 = f;
                if(f < f1)
                {
                    f2 = f1;
                    mWidestChar = s;
                }
                i++;
                f = f2;
            }

            updateTextSize();
        }

        public void appendText(String s)
        {
            updateText(s, true);
        }

        public void clear()
        {
            clearText();
            hide();
        }

        public void clearText()
        {
            mBuilder.clear();
            mCCView.setText("");
        }

        public int getCaptionWindowId()
        {
            return mCaptionWindowId;
        }

        public void hide()
        {
            setVisibility(4);
            requestLayout();
        }

        public void initWindow(CCLayout cclayout, Cea708CCParser.CaptionWindow captionwindow)
        {
            float f;
            int i;
            float f1;
            float f2;
            float f3;
            float f4;
            float f5;
label0:
            {
                if(mCCLayout != cclayout)
                {
                    if(mCCLayout != null)
                        mCCLayout.removeOnLayoutChangeListener(this);
                    mCCLayout = cclayout;
                    mCCLayout.addOnLayoutChangeListener(this);
                    updateWidestChar();
                }
                f = captionwindow.anchorVertical;
                int j;
                int l;
                if(captionwindow.relativePositioning)
                    i = 99;
                else
                    i = 74;
                f1 = f / (float)i;
                f = captionwindow.anchorHorizontal;
                if(captionwindow.relativePositioning)
                    i = 99;
                else
                    i = 209;
                f2 = f / (float)i;
                if(f1 >= 0.0F)
                {
                    f = f1;
                    if(f1 <= 1.0F)
                        break label0;
                }
                Log.i("CCWindowLayout", (new StringBuilder()).append("The vertical position of the anchor point should be at the range of 0 and 1 but ").append(f1).toString());
                f = Math.max(0.0F, Math.min(f1, 1.0F));
            }
label1:
            {
                if(f2 >= 0.0F)
                {
                    f1 = f2;
                    if(f2 <= 1.0F)
                        break label1;
                }
                Log.i("CCWindowLayout", (new StringBuilder()).append("The horizontal position of the anchor point should be at the range of 0 and 1 but ").append(f2).toString());
                f1 = Math.max(0.0F, Math.min(f2, 1.0F));
            }
            i = 17;
            j = captionwindow.anchorId;
            l = captionwindow.anchorId / 3;
            f3 = 0.0F;
            f4 = 1.0F;
            f2 = 0.0F;
            f5 = 1.0F;
            j % 3;
            JVM INSTR tableswitch 0 2: default 260
        //                       0 366
        //                       1 390
        //                       2 599;
               goto _L1 _L2 _L3 _L4
_L1:
            f1 = f5;
_L9:
            l;
            JVM INSTR tableswitch 0 2: default 292
        //                       0 615
        //                       1 631
        //                       2 661;
               goto _L5 _L6 _L7 _L8
_L5:
            f = f4;
_L10:
            mCCLayout.addOrUpdateViewToSafeTitleArea(this, new ScaledLayout.ScaledLayoutParams(f3, f, f2, f1));
            setCaptionWindowId(captionwindow.id);
            setRowLimit(captionwindow.rowCount);
            setGravity(i);
            int k;
            StringBuilder stringbuilder;
            if(captionwindow.visible)
                show();
            else
                hide();
            return;
_L2:
            i = 3;
            mCCView.setAlignment(android.text.Layout.Alignment.ALIGN_NORMAL);
            f2 = f1;
            f1 = f5;
              goto _L9
_L3:
            f5 = Math.min(1.0F - f1, f1);
            i = captionwindow.columnCount;
            k = Math.min(getScreenColumnCount(), i + 1);
            stringbuilder = new StringBuilder();
            for(i = 0; i < k; i++)
                stringbuilder.append(mWidestChar);

            cclayout = new Paint();
            cclayout.setTypeface(mCaptionStyle.getTypeface());
            cclayout.setTextSize(mTextSize);
            f2 = cclayout.measureText(stringbuilder.toString());
            if(mCCLayout.getWidth() > 0)
                f2 = f2 / 2.0F / ((float)mCCLayout.getWidth() * 0.8F);
            else
                f2 = 0.0F;
            if(f2 > 0.0F && f2 < f1)
            {
                i = 3;
                mCCView.setAlignment(android.text.Layout.Alignment.ALIGN_NORMAL);
                f2 = f1 - f2;
                f1 = 1.0F;
            } else
            {
                i = 1;
                mCCView.setAlignment(android.text.Layout.Alignment.ALIGN_CENTER);
                f2 = f1 - f5;
                f1 += f5;
            }
              goto _L9
_L4:
            i = 5;
            mCCView.setAlignment(android.text.Layout.Alignment.ALIGN_RIGHT);
              goto _L9
_L6:
            i |= 0x30;
            f3 = f;
            f = f4;
              goto _L10
_L7:
            i |= 0x10;
            f4 = Math.min(1.0F - f, f);
            f3 = f - f4;
            f += f4;
              goto _L10
_L8:
            i |= 0x50;
              goto _L10
        }

        public void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            i = k - i;
            j = l - j;
            if(i != mLastCaptionLayoutWidth || j != mLastCaptionLayoutHeight)
            {
                mLastCaptionLayoutWidth = i;
                mLastCaptionLayoutHeight = j;
                updateTextSize();
            }
        }

        public void removeFromCaptionView()
        {
            if(mCCLayout != null)
            {
                mCCLayout.removeViewFromSafeTitleArea(this);
                mCCLayout.removeOnLayoutChangeListener(this);
                mCCLayout = null;
            }
        }

        public void sendBuffer(String s)
        {
            appendText(s);
        }

        public void sendControl(char c)
        {
        }

        public void setCaptionStyle(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            mCaptionStyle = captionstyle;
            mCCView.setCaptionStyle(captionstyle);
        }

        public void setCaptionWindowId(int i)
        {
            mCaptionWindowId = i;
        }

        public void setFontScale(float f)
        {
            mFontScale = f;
            updateTextSize();
        }

        public void setPenAttr(Cea708CCParser.CaptionPenAttr captionpenattr)
        {
            mCharacterStyles.clear();
            if(captionpenattr.italic)
                mCharacterStyles.add(new StyleSpan(2));
            if(captionpenattr.underline)
                mCharacterStyles.add(new UnderlineSpan());
            captionpenattr.penSize;
            JVM INSTR tableswitch 0 2: default 88
        //                       0 121
        //                       1 88
        //                       2 143;
               goto _L1 _L2 _L1 _L3
_L1:
            captionpenattr.penOffset;
            JVM INSTR tableswitch 0 2: default 120
        //                       0 165
        //                       1 120
        //                       2 185;
               goto _L4 _L5 _L4 _L6
_L4:
            return;
_L2:
            mCharacterStyles.add(new RelativeSizeSpan(0.75F));
              goto _L1
_L3:
            mCharacterStyles.add(new RelativeSizeSpan(1.25F));
              goto _L1
_L5:
            mCharacterStyles.add(new SubscriptSpan());
              goto _L4
_L6:
            mCharacterStyles.add(new SuperscriptSpan());
              goto _L4
        }

        public void setPenColor(Cea708CCParser.CaptionPenColor captionpencolor)
        {
        }

        public void setPenLocation(int i, int j)
        {
            if(mRow >= 0)
                for(j = mRow; j < i; j++)
                    appendText("\n");

            mRow = i;
        }

        public void setRowLimit(int i)
        {
            if(i < 0)
            {
                throw new IllegalArgumentException("A rowLimit should have a positive number");
            } else
            {
                mRowLimit = i;
                return;
            }
        }

        public void setText(String s)
        {
            updateText(s, false);
        }

        public void setWindowAttr(Cea708CCParser.CaptionWindowAttr captionwindowattr)
        {
        }

        public void show()
        {
            setVisibility(0);
            requestLayout();
        }

        private static final int ANCHOR_HORIZONTAL_16_9_MAX = 209;
        private static final int ANCHOR_HORIZONTAL_MODE_CENTER = 1;
        private static final int ANCHOR_HORIZONTAL_MODE_LEFT = 0;
        private static final int ANCHOR_HORIZONTAL_MODE_RIGHT = 2;
        private static final int ANCHOR_MODE_DIVIDER = 3;
        private static final int ANCHOR_RELATIVE_POSITIONING_MAX = 99;
        private static final int ANCHOR_VERTICAL_MAX = 74;
        private static final int ANCHOR_VERTICAL_MODE_BOTTOM = 2;
        private static final int ANCHOR_VERTICAL_MODE_CENTER = 1;
        private static final int ANCHOR_VERTICAL_MODE_TOP = 0;
        private static final int MAX_COLUMN_COUNT_16_9 = 42;
        private static final float PROPORTION_PEN_SIZE_LARGE = 1.25F;
        private static final float PROPORTION_PEN_SIZE_SMALL = 0.75F;
        private static final String TAG = "CCWindowLayout";
        private final SpannableStringBuilder mBuilder;
        private CCLayout mCCLayout;
        private CCView mCCView;
        private android.view.accessibility.CaptioningManager.CaptionStyle mCaptionStyle;
        private int mCaptionWindowId;
        private final List mCharacterStyles;
        private float mFontScale;
        private int mLastCaptionLayoutHeight;
        private int mLastCaptionLayoutWidth;
        private int mRow;
        private int mRowLimit;
        private float mTextSize;
        private String mWidestChar;

        public CCWindowLayout(Context context)
        {
            this(context, null);
        }

        public CCWindowLayout(Context context, AttributeSet attributeset)
        {
            this(context, attributeset, 0);
        }

        public CCWindowLayout(Context context, AttributeSet attributeset, int i)
        {
            this(context, attributeset, i, 0);
        }

        public CCWindowLayout(Context context, AttributeSet attributeset, int i, int j)
        {
            super(context, attributeset, i, j);
            mRowLimit = 0;
            mBuilder = new SpannableStringBuilder();
            mCharacterStyles = new ArrayList();
            mRow = -1;
            mCCView = new CCView(context);
            attributeset = new android.widget.RelativeLayout.LayoutParams(-2, -2);
            addView(mCCView, attributeset);
            context = (CaptioningManager)context.getSystemService("captioning");
            mFontScale = context.getFontScale();
            setCaptionStyle(context.getUserStyle());
            mCCView.setText("");
            updateWidestChar();
        }
    }

    static class ScaledLayout extends ViewGroup
    {

        protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            return layoutparams instanceof ScaledLayoutParams;
        }

        public void dispatchDraw(Canvas canvas)
        {
            int i = getPaddingLeft();
            int j = getPaddingTop();
            int k = getChildCount();
            int l = 0;
            do
            {
label0:
                {
                    View view;
label1:
                    {
                        if(l < k)
                        {
                            view = getChildAt(l);
                            if(view.getVisibility() == 8)
                                break label0;
                            if(l < mRectArray.length)
                                break label1;
                        }
                        return;
                    }
                    int i1 = mRectArray[l].left;
                    int j1 = mRectArray[l].top;
                    int k1 = canvas.save();
                    canvas.translate(i + i1, j + j1);
                    view.draw(canvas);
                    canvas.restoreToCount(k1);
                }
                l++;
            } while(true);
        }

        public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
        {
            return new ScaledLayoutParams(getContext(), attributeset);
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            k = getPaddingLeft();
            l = getPaddingTop();
            j = getChildCount();
            for(i = 0; i < j; i++)
            {
                View view = getChildAt(i);
                if(view.getVisibility() != 8)
                {
                    int i1 = mRectArray[i].left;
                    int j1 = mRectArray[i].top;
                    int k1 = mRectArray[i].bottom;
                    view.layout(k + i1, l + j1, l + mRectArray[i].right, k + k1);
                }
            }

        }

        protected void onMeasure(int i, int j)
        {
            int k = android.view.View.MeasureSpec.getSize(i);
            int l = android.view.View.MeasureSpec.getSize(j);
            j = k - getPaddingLeft() - getPaddingRight();
            int i1 = l - getPaddingTop() - getPaddingBottom();
            int j1 = getChildCount();
            mRectArray = new Rect[j1];
            for(i = 0; i < j1; i++)
            {
                View view = getChildAt(i);
                android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
                if(!(layoutparams instanceof ScaledLayoutParams))
                    throw new RuntimeException("A child of ScaledLayout cannot have the UNSPECIFIED scale factors");
                float f = ((ScaledLayoutParams)layoutparams).scaleStartRow;
                float f1 = ((ScaledLayoutParams)layoutparams).scaleEndRow;
                float f2 = ((ScaledLayoutParams)layoutparams).scaleStartCol;
                float f3 = ((ScaledLayoutParams)layoutparams).scaleEndCol;
                if(f < 0.0F || f > 1.0F)
                    throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartRow between 0 and 1");
                if(f1 < f || f > 1.0F)
                    throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndRow between scaleStartRow and 1");
                if(f3 < 0.0F || f3 > 1.0F)
                    throw new RuntimeException("A child of ScaledLayout should have a range of scaleStartCol between 0 and 1");
                if(f3 < f2 || f3 > 1.0F)
                    throw new RuntimeException("A child of ScaledLayout should have a range of scaleEndCol between scaleStartCol and 1");
                mRectArray[i] = new Rect((int)((float)j * f2), (int)((float)i1 * f), (int)((float)j * f3), (int)((float)i1 * f1));
                int k1 = android.view.View.MeasureSpec.makeMeasureSpec((int)((float)j * (f3 - f2)), 0x40000000);
                view.measure(k1, android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
                if(view.getMeasuredHeight() > mRectArray[i].height())
                {
                    int l1 = ((view.getMeasuredHeight() - mRectArray[i].height()) + 1) / 2;
                    Rect rect = mRectArray[i];
                    rect.bottom = rect.bottom + l1;
                    rect = mRectArray[i];
                    rect.top = rect.top - l1;
                    if(mRectArray[i].top < 0)
                    {
                        Rect rect1 = mRectArray[i];
                        rect1.bottom = rect1.bottom - mRectArray[i].top;
                        mRectArray[i].top = 0;
                    }
                    if(mRectArray[i].bottom > i1)
                    {
                        Rect rect2 = mRectArray[i];
                        rect2.top = rect2.top - (mRectArray[i].bottom - i1);
                        mRectArray[i].bottom = i1;
                    }
                }
                view.measure(k1, android.view.View.MeasureSpec.makeMeasureSpec((int)((float)i1 * (f1 - f)), 0x40000000));
            }

            i = 0;
            int ai[] = new int[j1];
            Rect arect[] = new Rect[j1];
            for(int i2 = 0; i2 < j1;)
            {
                j = i;
                if(getChildAt(i2).getVisibility() == 0)
                {
                    ai[i] = i;
                    arect[i] = mRectArray[i2];
                    j = i + 1;
                }
                i2++;
                i = j;
            }

            Arrays.sort(arect, 0, i, mRectTopLeftSorter);
            for(j = 0; j < i - 1; j++)
            {
                for(int j2 = j + 1; j2 < i; j2++)
                    if(Rect.intersects(arect[j], arect[j2]))
                    {
                        ai[j2] = ai[j];
                        arect[j2].set(arect[j2].left, arect[j].bottom, arect[j2].right, arect[j].bottom + arect[j2].height());
                    }

            }

            for(i--; i >= 0; i--)
            {
                if(arect[i].bottom <= i1)
                    continue;
                int k2 = arect[i].bottom - i1;
                for(j = 0; j <= i; j++)
                    if(ai[i] == ai[j])
                        arect[j].set(arect[j].left, arect[j].top - k2, arect[j].right, arect[j].bottom - k2);

            }

            setMeasuredDimension(k, l);
        }

        private static final boolean DEBUG = false;
        private static final String TAG = "ScaledLayout";
        private static final Comparator mRectTopLeftSorter = new Comparator() {

            public int compare(Rect rect, Rect rect1)
            {
                if(rect.top != rect1.top)
                    return rect.top - rect1.top;
                else
                    return rect.left - rect1.left;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((Rect)obj, (Rect)obj1);
            }

        }
;
        private Rect mRectArray[];


        public ScaledLayout(Context context)
        {
            super(context);
        }
    }

    static class ScaledLayout.ScaledLayoutParams extends android.view.ViewGroup.LayoutParams
    {

        public static final float SCALE_UNSPECIFIED = -1F;
        public float scaleEndCol;
        public float scaleEndRow;
        public float scaleStartCol;
        public float scaleStartRow;

        public ScaledLayout.ScaledLayoutParams(float f, float f1, float f2, float f3)
        {
            super(-1, -1);
            scaleStartRow = f;
            scaleEndRow = f1;
            scaleStartCol = f2;
            scaleEndCol = f3;
        }

        public ScaledLayout.ScaledLayoutParams(Context context, AttributeSet attributeset)
        {
            super(-1, -1);
        }
    }


    public Cea708CCWidget(Context context)
    {
        this(context, null);
    }

    public Cea708CCWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public Cea708CCWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Cea708CCWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mCCHandler = new CCHandler((CCLayout)mClosedCaptionLayout);
    }

    public ClosedCaptionWidget.ClosedCaptionLayout createCaptionLayout(Context context)
    {
        return new CCLayout(context);
    }

    public void emitEvent(Cea708CCParser.CaptionEvent captionevent)
    {
        mCCHandler.processCaptionEvent(captionevent);
        setSize(getWidth(), getHeight());
        if(mListener != null)
            mListener.onChanged(this);
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        ((ViewGroup)mClosedCaptionLayout).draw(canvas);
    }

    private final CCHandler mCCHandler;
}
