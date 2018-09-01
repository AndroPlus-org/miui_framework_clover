// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.os.AsyncTask;
import android.os.LocaleList;
import android.text.*;
import android.util.Log;
import android.view.ActionMode;
import android.view.textclassifier.*;
import android.view.textclassifier.logging.SmartSelectionEventTracker;
import com.android.internal.util.Preconditions;
import java.text.BreakIterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.widget:
//            Editor, TextView

final class SelectionActionModeHelper
{
    private static final class SelectionMetricsLogger
    {

        private int countWordsBackward(int i)
        {
            boolean flag;
            int j;
            if(i >= mStartIndex)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag);
            j = 0;
            for(int k = i; k > mStartIndex;)
            {
                int l = mWordIterator.preceding(k);
                i = j;
                if(!isWhitespace(l, k))
                    i = j + 1;
                k = l;
                j = i;
            }

            return j;
        }

        private int countWordsForward(int i)
        {
            boolean flag;
            int j;
            int k;
            if(i <= mStartIndex)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag);
            j = 0;
            k = i;
            for(i = j; k < mStartIndex; i = j)
            {
                int l = mWordIterator.following(k);
                j = i;
                if(!isWhitespace(k, l))
                    j = i + 1;
                k = l;
            }

            return i;
        }

        private int[] getWordDelta(int i, int j)
        {
            int ai[] = new int[2];
            if(i == mStartIndex)
                ai[0] = 0;
            else
            if(i < mStartIndex)
            {
                ai[0] = -countWordsForward(i);
            } else
            {
                ai[0] = countWordsBackward(i);
                if(!mWordIterator.isBoundary(i) && isWhitespace(mWordIterator.preceding(i), mWordIterator.following(i)) ^ true)
                    ai[0] = ai[0] - 1;
            }
            if(j == mStartIndex)
                ai[1] = 0;
            else
            if(j < mStartIndex)
                ai[1] = -countWordsForward(j);
            else
                ai[1] = countWordsBackward(j);
            return ai;
        }

        private boolean isWhitespace(int i, int j)
        {
            return PATTERN_WHITESPACE.matcher(mText.substring(i, j)).matches();
        }

        public boolean isEditTextLogger()
        {
            return mEditTextLogger;
        }

        public void logSelectionAction(int i, int j, int k, TextClassification textclassification)
        {
            int ai[];
            Preconditions.checkArgumentInRange(i, 0, mText.length(), "start");
            Preconditions.checkArgumentInRange(j, i, mText.length(), "end");
            ai = getWordDelta(i, j);
            if(textclassification == null)
                break MISSING_BLOCK_LABEL_65;
            mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionAction(ai[0], ai[1], k, textclassification));
_L1:
            return;
            try
            {
                mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionAction(ai[0], ai[1], k));
            }
            // Misplaced declaration of an exception variable
            catch(TextClassification textclassification)
            {
                Log.d("SelectionMetricsLogger", textclassification.getMessage());
            }
              goto _L1
        }

        public void logSelectionModified(int i, int j, TextClassification textclassification, TextSelection textselection)
        {
            int ai[];
            Preconditions.checkArgumentInRange(i, 0, mText.length(), "start");
            Preconditions.checkArgumentInRange(j, i, mText.length(), "end");
            ai = getWordDelta(i, j);
            if(textselection == null) goto _L2; else goto _L1
_L1:
            mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionModified(ai[0], ai[1], textselection));
_L3:
            return;
_L2:
label0:
            {
                if(textclassification == null)
                    break label0;
                try
                {
                    mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionModified(ai[0], ai[1], textclassification));
                }
                // Misplaced declaration of an exception variable
                catch(TextClassification textclassification)
                {
                    Log.d("SelectionMetricsLogger", textclassification.getMessage());
                }
            }
              goto _L3
            mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionModified(ai[0], ai[1]));
              goto _L3
        }

        public void logSelectionStarted(CharSequence charsequence, int i)
        {
            Preconditions.checkNotNull(charsequence);
            Preconditions.checkArgumentInRange(i, 0, charsequence.length(), "index");
            if(mText == null || mText.contentEquals(charsequence) ^ true)
                mText = charsequence.toString();
            mWordIterator.setText(mText);
            mStartIndex = i;
            mDelegate.logEvent(android.view.textclassifier.logging.SmartSelectionEventTracker.SelectionEvent.selectionStarted(0));
_L1:
            return;
            charsequence;
            Log.d("SelectionMetricsLogger", charsequence.getMessage());
              goto _L1
        }

        private static final String LOG_TAG = "SelectionMetricsLogger";
        private static final Pattern PATTERN_WHITESPACE = Pattern.compile("\\s+");
        private final SmartSelectionEventTracker mDelegate;
        private final boolean mEditTextLogger;
        private int mStartIndex;
        private String mText;
        private final BreakIterator mWordIterator;


        SelectionMetricsLogger(TextView textview)
        {
            Preconditions.checkNotNull(textview);
            byte byte0;
            if(textview.isTextEditable())
                byte0 = 3;
            else
                byte0 = 1;
            mDelegate = new SmartSelectionEventTracker(textview.getContext(), byte0);
            mEditTextLogger = textview.isTextEditable();
            mWordIterator = BreakIterator.getWordInstance(textview.getTextLocale());
        }
    }

    private static final class SelectionResult
    {

        static TextClassification _2D_get0(SelectionResult selectionresult)
        {
            return selectionresult.mClassification;
        }

        static int _2D_get1(SelectionResult selectionresult)
        {
            return selectionresult.mEnd;
        }

        static TextSelection _2D_get2(SelectionResult selectionresult)
        {
            return selectionresult.mSelection;
        }

        static int _2D_get3(SelectionResult selectionresult)
        {
            return selectionresult.mStart;
        }

        private final TextClassification mClassification;
        private final int mEnd;
        private final TextSelection mSelection;
        private final int mStart;

        SelectionResult(int i, int j, TextClassification textclassification, TextSelection textselection)
        {
            mStart = i;
            mEnd = j;
            mClassification = (TextClassification)Preconditions.checkNotNull(textclassification);
            mSelection = textselection;
        }
    }

    private static final class SelectionTracker
    {

        static SelectionMetricsLogger _2D_get0(SelectionTracker selectiontracker)
        {
            return selectiontracker.mLogger;
        }

        static int _2D_get1(SelectionTracker selectiontracker)
        {
            return selectiontracker.mSelectionEnd;
        }

        static int _2D_get2(SelectionTracker selectiontracker)
        {
            return selectiontracker.mSelectionStart;
        }

        static TextView _2D_get3(SelectionTracker selectiontracker)
        {
            return selectiontracker.mTextView;
        }

        static int _2D_set0(SelectionTracker selectiontracker, int i)
        {
            selectiontracker.mSelectionEnd = i;
            return i;
        }

        static int _2D_set1(SelectionTracker selectiontracker, int i)
        {
            selectiontracker.mSelectionStart = i;
            return i;
        }

        private boolean isSelectionStarted()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mSelectionStart >= 0)
            {
                flag1 = flag;
                if(mSelectionEnd >= 0)
                {
                    flag1 = flag;
                    if(mSelectionStart != mSelectionEnd)
                        flag1 = true;
                }
            }
            return flag1;
        }

        private void maybeInvalidateLogger()
        {
            if(mLogger.isEditTextLogger() != mTextView.isTextEditable())
                mLogger = new SelectionMetricsLogger(mTextView);
        }

        public void onOriginalSelection(CharSequence charsequence, int i, int j)
        {
            mDelayedLogAbandon.flush();
            mSelectionStart = i;
            mOriginalStart = i;
            mSelectionEnd = j;
            mOriginalEnd = j;
            mAllowReset = false;
            maybeInvalidateLogger();
            mLogger.logSelectionStarted(charsequence, i);
        }

        public void onSelectionAction(int i, int j, int k, TextClassification textclassification)
        {
            if(isSelectionStarted())
            {
                mAllowReset = false;
                mLogger.logSelectionAction(i, j, k, textclassification);
            }
        }

        public void onSelectionDestroyed()
        {
            mAllowReset = false;
            mDelayedLogAbandon.schedule(100);
        }

        public void onSelectionUpdated(int i, int j, TextClassification textclassification)
        {
            if(isSelectionStarted())
            {
                mSelectionStart = i;
                mSelectionEnd = j;
                mAllowReset = false;
                mLogger.logSelectionModified(i, j, textclassification, null);
            }
        }

        public void onSmartSelection(SelectionResult selectionresult)
        {
            boolean flag = true;
            if(isSelectionStarted())
            {
                mSelectionStart = SelectionResult._2D_get3(selectionresult);
                mSelectionEnd = SelectionResult._2D_get1(selectionresult);
                boolean flag1 = flag;
                if(mSelectionStart == mOriginalStart)
                    if(mSelectionEnd != mOriginalEnd)
                        flag1 = flag;
                    else
                        flag1 = false;
                mAllowReset = flag1;
                mLogger.logSelectionModified(SelectionResult._2D_get3(selectionresult), SelectionResult._2D_get1(selectionresult), SelectionResult._2D_get0(selectionresult), SelectionResult._2D_get2(selectionresult));
            }
        }

        public void onTextChanged(int i, int j, TextClassification textclassification)
        {
            if(isSelectionStarted() && i == mSelectionStart && j == mSelectionEnd)
                onSelectionAction(i, j, 100, textclassification);
        }

        public boolean resetSelection(int i, Editor editor)
        {
            TextView textview = editor.getTextView();
            if(isSelectionStarted() && mAllowReset && i >= mSelectionStart && i <= mSelectionEnd && (SelectionActionModeHelper._2D_wrap0(textview) instanceof Spannable))
            {
                mAllowReset = false;
                boolean flag = editor.selectCurrentWord();
                if(flag)
                {
                    mSelectionStart = editor.getTextView().getSelectionStart();
                    mSelectionEnd = editor.getTextView().getSelectionEnd();
                    mLogger.logSelectionAction(textview.getSelectionStart(), textview.getSelectionEnd(), 201, null);
                }
                return flag;
            } else
            {
                return false;
            }
        }

        private boolean mAllowReset;
        private final LogAbandonRunnable mDelayedLogAbandon = new LogAbandonRunnable(null);
        private SelectionMetricsLogger mLogger;
        private int mOriginalEnd;
        private int mOriginalStart;
        private int mSelectionEnd;
        private int mSelectionStart;
        private final TextView mTextView;

        SelectionTracker(TextView textview)
        {
            mTextView = (TextView)Preconditions.checkNotNull(textview);
            mLogger = new SelectionMetricsLogger(textview);
        }
    }

    private final class SelectionTracker.LogAbandonRunnable
        implements Runnable
    {

        void flush()
        {
            SelectionTracker._2D_get3(SelectionTracker.this).removeCallbacks(this);
            run();
        }

        public void run()
        {
            if(mIsPending)
            {
                SelectionTracker._2D_get0(SelectionTracker.this).logSelectionAction(SelectionTracker._2D_get2(SelectionTracker.this), SelectionTracker._2D_get1(SelectionTracker.this), 107, null);
                SelectionTracker._2D_set1(SelectionTracker.this, SelectionTracker._2D_set0(SelectionTracker.this, -1));
                mIsPending = false;
            }
        }

        void schedule(int i)
        {
            if(mIsPending)
            {
                Log.e("SelectActionModeHelper", "Force flushing abandon due to new scheduling request");
                flush();
            }
            mIsPending = true;
            SelectionTracker._2D_get3(SelectionTracker.this).postDelayed(this, i);
        }

        private boolean mIsPending;
        final SelectionTracker this$1;

        private SelectionTracker.LogAbandonRunnable()
        {
            this$1 = SelectionTracker.this;
            super();
        }

        SelectionTracker.LogAbandonRunnable(SelectionTracker.LogAbandonRunnable logabandonrunnable)
        {
            this();
        }
    }

    private static final class TextClassificationAsyncTask extends AsyncTask
    {

        private void onTimeOut()
        {
            if(getStatus() == android.os.AsyncTask.Status.RUNNING)
                onPostExecute(((SelectionResult) (null)));
            cancel(true);
        }

        void _2D_android_widget_SelectionActionModeHelper$TextClassificationAsyncTask_2D_mthref_2D_0()
        {
            onTimeOut();
        }

        protected transient SelectionResult doInBackground(Void avoid[])
        {
            avoid = new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)6, this);
            mTextView.postDelayed(avoid, mTimeOutDuration);
            SelectionResult selectionresult = (SelectionResult)mSelectionResultSupplier.get();
            mTextView.removeCallbacks(avoid);
            return selectionresult;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected void onPostExecute(SelectionResult selectionresult)
        {
            if(!TextUtils.equals(mOriginalText, SelectionActionModeHelper._2D_wrap0(mTextView)))
                selectionresult = null;
            mSelectionResultCallback.accept(selectionresult);
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((SelectionResult)obj);
        }

        private final String mOriginalText;
        private final Consumer mSelectionResultCallback;
        private final Supplier mSelectionResultSupplier;
        private final TextView mTextView;
        private final long mTimeOutDuration;

        TextClassificationAsyncTask(TextView textview, long l, Supplier supplier, Consumer consumer)
        {
            android.os.Handler handler = null;
            if(textview != null)
                handler = textview.getHandler();
            super(handler);
            mTextView = (TextView)Preconditions.checkNotNull(textview);
            mTimeOutDuration = l;
            mSelectionResultSupplier = (Supplier)Preconditions.checkNotNull(supplier);
            mSelectionResultCallback = (Consumer)Preconditions.checkNotNull(consumer);
            mOriginalText = SelectionActionModeHelper._2D_wrap0(mTextView).toString();
        }
    }

    private static final class TextClassificationHelper
    {

        private SelectionResult performClassification(TextSelection textselection)
        {
            if(!Objects.equals(mText, mLastClassificationText) || mSelectionStart != mLastClassificationSelectionStart || mSelectionEnd != mLastClassificationSelectionEnd || Objects.equals(mLocales, mLastClassificationLocales) ^ true)
            {
                mLastClassificationText = mText;
                mLastClassificationSelectionStart = mSelectionStart;
                mLastClassificationSelectionEnd = mSelectionEnd;
                mLastClassificationLocales = mLocales;
                trimText();
                mLastClassificationResult = new SelectionResult(mSelectionStart, mSelectionEnd, mTextClassifier.classifyText(mTrimmedText, mRelativeStart, mRelativeEnd, mLocales), textselection);
            }
            return mLastClassificationResult;
        }

        private void trimText()
        {
            mTrimStart = Math.max(0, mSelectionStart - 120);
            int i = Math.min(mText.length(), mSelectionEnd + 120);
            mTrimmedText = mText.subSequence(mTrimStart, i);
            mRelativeStart = mSelectionStart - mTrimStart;
            mRelativeEnd = mSelectionEnd - mTrimStart;
        }

        public SelectionResult classifyText()
        {
            mHot = true;
            return performClassification(null);
        }

        public long getTimeoutDuration()
        {
            return !mHot ? 500L : 200L;
        }

        public void init(TextClassifier textclassifier, CharSequence charsequence, int i, int j, LocaleList localelist)
        {
            mTextClassifier = (TextClassifier)Preconditions.checkNotNull(textclassifier);
            mText = ((CharSequence)Preconditions.checkNotNull(charsequence)).toString();
            mLastClassificationText = null;
            boolean flag;
            if(j > i)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag);
            mSelectionStart = i;
            mSelectionEnd = j;
            mLocales = localelist;
        }

        public SelectionResult suggestSelection()
        {
            mHot = true;
            trimText();
            TextSelection textselection = mTextClassifier.suggestSelection(mTrimmedText, mRelativeStart, mRelativeEnd, mLocales);
            if(!mTextClassifier.getSettings().isDarkLaunch())
            {
                mSelectionStart = Math.max(0, textselection.getSelectionStartIndex() + mTrimStart);
                mSelectionEnd = Math.min(mText.length(), textselection.getSelectionEndIndex() + mTrimStart);
            }
            return performClassification(textselection);
        }

        private static final int TRIM_DELTA = 120;
        private boolean mHot;
        private LocaleList mLastClassificationLocales;
        private SelectionResult mLastClassificationResult;
        private int mLastClassificationSelectionEnd;
        private int mLastClassificationSelectionStart;
        private CharSequence mLastClassificationText;
        private LocaleList mLocales;
        private int mRelativeEnd;
        private int mRelativeStart;
        private int mSelectionEnd;
        private int mSelectionStart;
        private String mText;
        private TextClassifier mTextClassifier;
        private int mTrimStart;
        private CharSequence mTrimmedText;

        TextClassificationHelper(TextClassifier textclassifier, CharSequence charsequence, int i, int j, LocaleList localelist)
        {
            init(textclassifier, charsequence, i, j, localelist);
        }
    }


    static SelectionResult _2D_android_widget_SelectionActionModeHelper_2D_mthref_2D_0(TextClassificationHelper textclassificationhelper)
    {
        return textclassificationhelper.suggestSelection();
    }

    static SelectionResult _2D_android_widget_SelectionActionModeHelper_2D_mthref_2D_1(TextClassificationHelper textclassificationhelper)
    {
        return textclassificationhelper.classifyText();
    }

    static SelectionResult _2D_android_widget_SelectionActionModeHelper_2D_mthref_2D_3(TextClassificationHelper textclassificationhelper)
    {
        return textclassificationhelper.classifyText();
    }

    static CharSequence _2D_wrap0(TextView textview)
    {
        return getText(textview);
    }

    SelectionActionModeHelper(Editor editor)
    {
        mEditor = (Editor)Preconditions.checkNotNull(editor);
        mTextView = mEditor.getTextView();
        mTextClassificationHelper = new TextClassificationHelper(mTextView.getTextClassifier(), getText(mTextView), 0, 1, mTextView.getTextLocales());
        mSelectionTracker = new SelectionTracker(mTextView);
    }

    private void cancelAsyncTask()
    {
        if(mTextClassificationAsyncTask != null)
        {
            mTextClassificationAsyncTask.cancel(true);
            mTextClassificationAsyncTask = null;
        }
        mTextClassification = null;
    }

    private static int getActionType(int i)
    {
        switch(i)
        {
        default:
            return 108;

        case 16908319: 
            return 200;

        case 16908320: 
            return 103;

        case 16908321: 
            return 101;

        case 16908322: 
        case 16908337: 
            return 102;

        case 16908341: 
            return 104;

        case 16908353: 
            return 105;
        }
    }

    private static CharSequence getText(TextView textview)
    {
        textview = textview.getText();
        if(textview != null)
            return textview;
        else
            return "";
    }

    private void invalidateActionMode(SelectionResult selectionresult)
    {
        if(selectionresult != null)
            selectionresult = SelectionResult._2D_get0(selectionresult);
        else
            selectionresult = null;
        mTextClassification = selectionresult;
        selectionresult = mEditor.getTextActionMode();
        if(selectionresult != null)
            selectionresult.invalidate();
        mSelectionTracker.onSelectionUpdated(mTextView.getSelectionStart(), mTextView.getSelectionEnd(), mTextClassification);
        mTextClassificationAsyncTask = null;
    }

    private void resetTextClassificationHelper()
    {
        mTextClassificationHelper.init(mTextView.getTextClassifier(), getText(mTextView), mTextView.getSelectionStart(), mTextView.getSelectionEnd(), mTextView.getTextLocales());
    }

    private boolean skipTextClassification()
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(mTextView.getTextClassifier() == TextClassifier.NO_OP)
            flag = true;
        else
            flag = false;
        if(mTextView.getSelectionEnd() == mTextView.getSelectionStart())
            flag1 = true;
        else
            flag1 = false;
        if(!mTextView.hasPasswordTransformationMethod())
            flag2 = TextView.isPasswordInputType(mTextView.getInputType());
        else
            flag2 = true;
        if(flag || flag1)
            flag2 = true;
        return flag2;
    }

    private void startActionMode(SelectionResult selectionresult)
    {
        CharSequence charsequence = getText(mTextView);
        if(selectionresult != null && (charsequence instanceof Spannable))
        {
            if(!mTextView.getTextClassifier().getSettings().isDarkLaunch())
                Selection.setSelection((Spannable)charsequence, SelectionResult._2D_get3(selectionresult), SelectionResult._2D_get1(selectionresult));
            mTextClassification = SelectionResult._2D_get0(selectionresult);
        } else
        {
            mTextClassification = null;
        }
        if(mEditor.startSelectionActionModeInternal())
        {
            Editor.SelectionModifierCursorController selectionmodifiercursorcontroller = mEditor.getSelectionController();
            if(selectionmodifiercursorcontroller != null)
                selectionmodifiercursorcontroller.show();
            if(selectionresult != null)
                mSelectionTracker.onSmartSelection(selectionresult);
        }
        mEditor.setRestartActionModeOnNextRefresh(false);
        mTextClassificationAsyncTask = null;
    }

    void _2D_android_widget_SelectionActionModeHelper_2D_mthref_2D_2(SelectionResult selectionresult)
    {
        startActionMode(selectionresult);
    }

    void _2D_android_widget_SelectionActionModeHelper_2D_mthref_2D_4(SelectionResult selectionresult)
    {
        invalidateActionMode(selectionresult);
    }

    public TextClassification getTextClassification()
    {
        return mTextClassification;
    }

    public void invalidateActionModeAsync()
    {
        cancelAsyncTask();
        if(skipTextClassification())
        {
            invalidateActionMode(null);
        } else
        {
            resetTextClassificationHelper();
            TextView textview = mTextView;
            long l = mTextClassificationHelper.getTimeoutDuration();
            TextClassificationHelper textclassificationhelper = mTextClassificationHelper;
            textclassificationhelper.getClass();
            mTextClassificationAsyncTask = (new TextClassificationAsyncTask(textview, l, new _.Lambda.tTszxdFZ0V9nXhnBpPsqeBMO0fw._cls1((byte)0, textclassificationhelper), new _.Lambda.tTszxdFZ0V9nXhnBpPsqeBMO0fw((byte)0, this))).execute(new Void[0]);
        }
    }

    public void onDestroyActionMode()
    {
        mSelectionTracker.onSelectionDestroyed();
        cancelAsyncTask();
    }

    public void onSelectionAction(int i)
    {
        mSelectionTracker.onSelectionAction(mTextView.getSelectionStart(), mTextView.getSelectionEnd(), getActionType(i), mTextClassification);
    }

    public void onSelectionDrag()
    {
        mSelectionTracker.onSelectionAction(mTextView.getSelectionStart(), mTextView.getSelectionEnd(), 106, mTextClassification);
    }

    public void onTextChanged(int i, int j)
    {
        mSelectionTracker.onTextChanged(i, j, mTextClassification);
    }

    public boolean resetSelection(int i)
    {
        if(mSelectionTracker.resetSelection(i, mEditor))
        {
            invalidateActionModeAsync();
            return true;
        } else
        {
            return false;
        }
    }

    public void startActionModeAsync(boolean flag)
    {
        boolean flag1;
        if(mTextView.isTextEditable())
            flag1 = mTextView.getTextClassifier().getSettings().isSuggestSelectionEnabledForEditableText();
        else
            flag1 = true;
        mSelectionTracker.onOriginalSelection(getText(mTextView), mTextView.getSelectionStart(), mTextView.getSelectionEnd());
        cancelAsyncTask();
        if(skipTextClassification())
        {
            startActionMode(null);
        } else
        {
            resetTextClassificationHelper();
            TextView textview = mTextView;
            long l = mTextClassificationHelper.getTimeoutDuration();
            Object obj;
            if(flag & flag1)
            {
                obj = mTextClassificationHelper;
                obj.getClass();
                obj = new _.Lambda.tTszxdFZ0V9nXhnBpPsqeBMO0fw._cls1((byte)1, obj);
            } else
            {
                obj = mTextClassificationHelper;
                obj.getClass();
                obj = new _.Lambda.tTszxdFZ0V9nXhnBpPsqeBMO0fw._cls1((byte)2, obj);
            }
            mTextClassificationAsyncTask = (new TextClassificationAsyncTask(textview, l, ((Supplier) (obj)), new _.Lambda.tTszxdFZ0V9nXhnBpPsqeBMO0fw((byte)1, this))).execute(new Void[0]);
        }
    }

    private static final String LOG_TAG = "SelectActionModeHelper";
    private final Editor mEditor;
    private final SelectionTracker mSelectionTracker;
    private TextClassification mTextClassification;
    private AsyncTask mTextClassificationAsyncTask;
    private final TextClassificationHelper mTextClassificationHelper;
    private final TextView mTextView;
}
