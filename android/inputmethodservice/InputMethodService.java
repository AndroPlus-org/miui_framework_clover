// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.Region;
import android.net.Uri;
import android.os.*;
import android.text.Editable;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.util.*;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.*;
import android.widget.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.inputmethodservice:
//            AbstractInputMethodService, ExtractEditText, SoftInputWindow

public class InputMethodService extends AbstractInputMethodService
{
    public class InputMethodImpl extends AbstractInputMethodService.AbstractInputMethodImpl
    {

        public void attachToken(IBinder ibinder)
        {
            if(mToken == null)
            {
                mToken = ibinder;
                mWindow.setToken(ibinder);
            }
        }

        public void bindInput(InputBinding inputbinding)
        {
            mInputBinding = inputbinding;
            mInputConnection = inputbinding.getConnection();
            if(mImm != null && mToken != null)
                mImm.reportFullscreenMode(mToken, mIsFullscreen);
            initialize();
            onBindInput();
        }

        public void changeInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
        {
            onCurrentInputMethodSubtypeChanged(inputmethodsubtype);
        }

        public void dispatchStartInputWithToken(InputConnection inputconnection, EditorInfo editorinfo, boolean flag, IBinder ibinder)
        {
            InputMethodService._2D_set0(InputMethodService.this, ibinder);
            super.dispatchStartInputWithToken(inputconnection, editorinfo, flag, ibinder);
        }

        public void hideSoftInput(int i, ResultReceiver resultreceiver)
        {
            boolean flag;
            i = 0;
            flag = isInputViewShown();
            mShowInputFlags = 0;
            mShowInputRequested = false;
            InputMethodService._2D_wrap2(InputMethodService.this);
            InputMethodService._2D_wrap1(InputMethodService.this);
            if(resultreceiver == null) goto _L2; else goto _L1
_L1:
            if(flag == isInputViewShown()) goto _L4; else goto _L3
_L3:
            i = 3;
_L6:
            resultreceiver.send(i, null);
_L2:
            return;
_L4:
            if(!flag)
                i = 1;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public void restartInput(InputConnection inputconnection, EditorInfo editorinfo)
        {
            doStartInput(inputconnection, editorinfo, true);
        }

        public void showSoftInput(int i, ResultReceiver resultreceiver)
        {
            byte byte0 = 2;
            boolean flag = isInputViewShown();
            boolean flag1;
            InputMethodManager inputmethodmanager;
            IBinder ibinder;
            IBinder ibinder1;
            if(InputMethodService._2D_wrap0(InputMethodService.this, i, false))
                try
                {
                    showWindow(true);
                }
                catch(android.view.WindowManager.BadTokenException badtokenexception) { }
            InputMethodService._2D_wrap1(InputMethodService.this);
            flag1 = isInputViewShown();
            inputmethodmanager = mImm;
            ibinder = mToken;
            ibinder1 = InputMethodService._2D_get0(InputMethodService.this);
            if(flag1)
                i = 2;
            else
                i = 0;
            inputmethodmanager.setImeWindowStatus(ibinder, ibinder1, i | 1, mBackDisposition);
            if(resultreceiver != null)
            {
                if(flag != isInputViewShown())
                    i = byte0;
                else
                if(flag)
                    i = 0;
                else
                    i = 1;
                resultreceiver.send(i, null);
            }
        }

        public void startInput(InputConnection inputconnection, EditorInfo editorinfo)
        {
            doStartInput(inputconnection, editorinfo, false);
        }

        public void unbindInput()
        {
            onUnbindInput();
            mInputBinding = null;
            mInputConnection = null;
        }

        final InputMethodService this$0;

        public InputMethodImpl()
        {
            this$0 = InputMethodService.this;
            super(InputMethodService.this);
        }
    }

    public class InputMethodSessionImpl extends AbstractInputMethodService.AbstractInputMethodSessionImpl
    {

        public void appPrivateCommand(String s, Bundle bundle)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onAppPrivateCommand(s, bundle);
                return;
            }
        }

        public void displayCompletions(CompletionInfo acompletioninfo[])
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                mCurCompletions = acompletioninfo;
                onDisplayCompletions(acompletioninfo);
                return;
            }
        }

        public void finishInput()
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                doFinishInput();
                return;
            }
        }

        public void toggleSoftInput(int i, int j)
        {
            InputMethodService._2D_wrap3(InputMethodService.this, i, j);
        }

        public void updateCursor(Rect rect)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onUpdateCursor(rect);
                return;
            }
        }

        public void updateCursorAnchorInfo(CursorAnchorInfo cursoranchorinfo)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onUpdateCursorAnchorInfo(cursoranchorinfo);
                return;
            }
        }

        public void updateExtractedText(int i, ExtractedText extractedtext)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onUpdateExtractedText(i, extractedtext);
                return;
            }
        }

        public void updateSelection(int i, int j, int k, int l, int i1, int j1)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onUpdateSelection(i, j, k, l, i1, j1);
                return;
            }
        }

        public void viewClicked(boolean flag)
        {
            if(!isEnabled())
            {
                return;
            } else
            {
                onViewClicked(flag);
                return;
            }
        }

        final InputMethodService this$0;

        public InputMethodSessionImpl()
        {
            this$0 = InputMethodService.this;
            super(InputMethodService.this);
        }
    }

    public static final class Insets
    {

        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_REGION = 3;
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;
        public int contentTopInsets;
        public int touchableInsets;
        public final Region touchableRegion = new Region();
        public int visibleTopInsets;

        public Insets()
        {
        }
    }

    private static final class SettingsObserver extends ContentObserver
    {

        static boolean _2D_wrap0(SettingsObserver settingsobserver)
        {
            return settingsobserver.shouldShowImeWithHardKeyboard();
        }

        public static SettingsObserver createAndRegister(InputMethodService inputmethodservice)
        {
            SettingsObserver settingsobserver = new SettingsObserver(inputmethodservice);
            inputmethodservice.getContentResolver().registerContentObserver(android.provider.Settings.Secure.getUriFor("show_ime_with_hard_keyboard"), false, settingsobserver);
            return settingsobserver;
        }

        private boolean shouldShowImeWithHardKeyboard()
        {
            if(mShowImeWithHardKeyboard == 0)
            {
                int i;
                if(android.provider.Settings.Secure.getInt(mService.getContentResolver(), "show_ime_with_hard_keyboard", 0) != 0)
                    i = 2;
                else
                    i = 1;
                mShowImeWithHardKeyboard = i;
            }
            switch(mShowImeWithHardKeyboard)
            {
            default:
                Log.e("InputMethodService", (new StringBuilder()).append("Unexpected mShowImeWithHardKeyboard=").append(mShowImeWithHardKeyboard).toString());
                return false;

            case 2: // '\002'
                return true;

            case 1: // '\001'
                return false;
            }
        }

        public void onChange(boolean flag, Uri uri)
        {
            if(android.provider.Settings.Secure.getUriFor("show_ime_with_hard_keyboard").equals(uri))
            {
                int i;
                if(android.provider.Settings.Secure.getInt(mService.getContentResolver(), "show_ime_with_hard_keyboard", 0) != 0)
                    i = 2;
                else
                    i = 1;
                mShowImeWithHardKeyboard = i;
                InputMethodService._2D_wrap4(mService);
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("SettingsObserver{mShowImeWithHardKeyboard=").append(mShowImeWithHardKeyboard).append("}").toString();
        }

        void unregister()
        {
            mService.getContentResolver().unregisterContentObserver(this);
        }

        private final InputMethodService mService;
        private int mShowImeWithHardKeyboard;

        private SettingsObserver(InputMethodService inputmethodservice)
        {
            super(new Handler(inputmethodservice.getMainLooper()));
            mShowImeWithHardKeyboard = 0;
            mService = inputmethodservice;
        }
    }


    static IBinder _2D_get0(InputMethodService inputmethodservice)
    {
        return inputmethodservice.mStartInputToken;
    }

    static IBinder _2D_set0(InputMethodService inputmethodservice, IBinder ibinder)
    {
        inputmethodservice.mStartInputToken = ibinder;
        return ibinder;
    }

    static boolean _2D_wrap0(InputMethodService inputmethodservice, int i, boolean flag)
    {
        return inputmethodservice.dispatchOnShowInputRequested(i, flag);
    }

    static void _2D_wrap1(InputMethodService inputmethodservice)
    {
        inputmethodservice.clearInsetOfPreviousIme();
    }

    static void _2D_wrap2(InputMethodService inputmethodservice)
    {
        inputmethodservice.doHideWindow();
    }

    static void _2D_wrap3(InputMethodService inputmethodservice, int i, int j)
    {
        inputmethodservice.onToggleSoftInput(i, j);
    }

    static void _2D_wrap4(InputMethodService inputmethodservice)
    {
        inputmethodservice.resetStateForNewConfiguration();
    }

    public InputMethodService()
    {
        mTheme = 0;
    }

    private void clearInsetOfPreviousIme()
    {
        if(!mShouldClearInsetOfPreviousIme)
        {
            return;
        } else
        {
            mImm.clearLastInputMethodWindowForTransition(mToken);
            mShouldClearInsetOfPreviousIme = false;
            return;
        }
    }

    private boolean dispatchOnShowInputRequested(int i, boolean flag)
    {
        flag = onShowInputRequested(i, flag);
        if(flag)
            mShowInputFlags = i;
        else
            mShowInputFlags = 0;
        return flag;
    }

    private void doHideWindow()
    {
        mImm.setImeWindowStatus(mToken, mStartInputToken, 0, mBackDisposition);
        hideWindow();
    }

    private void finishViews()
    {
        if(!mInputViewStarted) goto _L2; else goto _L1
_L1:
        onFinishInputView(false);
_L4:
        mInputViewStarted = false;
        mCandidatesViewStarted = false;
        return;
_L2:
        if(mCandidatesViewStarted)
            onFinishCandidatesView(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private ExtractEditText getExtractEditTextIfVisible()
    {
        if(!isExtractViewShown() || isInputViewShown() ^ true)
            return null;
        else
            return mExtractEditText;
    }

    private int getIconForImeAction(int i)
    {
        switch(i & 0xff)
        {
        default:
            return 0x1080377;

        case 2: // '\002'
            return 0x1080374;

        case 3: // '\003'
            return 0x1080378;

        case 4: // '\004'
            return 0x1080379;

        case 5: // '\005'
            return 0x1080375;

        case 6: // '\006'
            return 0x1080373;

        case 7: // '\007'
            return 0x1080376;
        }
    }

    private boolean handleBack(boolean flag)
    {
        if(mShowInputRequested)
        {
            if(flag)
                requestHideSelf(0);
            return true;
        }
        if(!mWindowVisible) goto _L2; else goto _L1
_L1:
        if(mCandidatesVisibility != 0) goto _L4; else goto _L3
_L3:
        if(flag)
            setCandidatesViewShown(false);
_L5:
        return true;
_L4:
        if(flag)
            doHideWindow();
        if(true) goto _L5; else goto _L2
_L2:
        return false;
    }

    private void onToggleSoftInput(int i, int j)
    {
        if(isInputViewShown())
            requestHideSelf(j);
        else
            requestShowSelf(i);
    }

    private void requestShowSelf(int i)
    {
        mImm.showSoftInputFromInputMethod(mToken, i);
    }

    private void resetStateForNewConfiguration()
    {
        byte byte0 = 0;
        boolean flag = mWindowVisible;
        int i = mShowInputFlags;
        boolean flag1 = mShowInputRequested;
        CompletionInfo acompletioninfo[] = mCurCompletions;
        initViews();
        mInputViewStarted = false;
        mCandidatesViewStarted = false;
        if(mInputStarted)
            doStartInput(getCurrentInputConnection(), getCurrentInputEditorInfo(), true);
        if(flag)
        {
            IBinder ibinder;
            InputMethodManager inputmethodmanager;
            IBinder ibinder1;
            if(flag1)
            {
                if(dispatchOnShowInputRequested(i, true))
                {
                    showWindow(true);
                    if(acompletioninfo != null)
                    {
                        mCurCompletions = acompletioninfo;
                        onDisplayCompletions(acompletioninfo);
                    }
                } else
                {
                    doHideWindow();
                }
            } else
            if(mCandidatesVisibility == 0)
                showWindow(false);
            else
                doHideWindow();
            flag1 = onEvaluateInputViewShown();
            inputmethodmanager = mImm;
            ibinder = mToken;
            ibinder1 = mStartInputToken;
            if(flag1)
                byte0 = 2;
            inputmethodmanager.setImeWindowStatus(ibinder, ibinder1, byte0 | 1, mBackDisposition);
        }
    }

    void doFinishInput()
    {
        if(!mInputViewStarted) goto _L2; else goto _L1
_L1:
        onFinishInputView(true);
_L4:
        mInputViewStarted = false;
        mCandidatesViewStarted = false;
        if(mInputStarted)
            onFinishInput();
        mInputStarted = false;
        mStartedInputConnection = null;
        mCurCompletions = null;
        return;
_L2:
        if(mCandidatesViewStarted)
            onFinishCandidatesView(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean doMovementKey(int i, KeyEvent keyevent, int j)
    {
        ExtractEditText extractedittext = getExtractEditTextIfVisible();
        if(extractedittext == null) goto _L2; else goto _L1
_L1:
        MovementMethod movementmethod = extractedittext.getMovementMethod();
        android.text.Layout layout = extractedittext.getLayout();
        if(movementmethod != null && layout != null)
            if(j == -1)
            {
                if(movementmethod.onKeyDown(extractedittext, extractedittext.getText(), i, keyevent))
                {
                    reportExtractedMovement(i, 1);
                    return true;
                }
            } else
            if(j == -2)
            {
                if(movementmethod.onKeyUp(extractedittext, extractedittext.getText(), i, keyevent))
                    return true;
            } else
            if(movementmethod.onKeyOther(extractedittext, extractedittext.getText(), keyevent))
            {
                reportExtractedMovement(i, j);
            } else
            {
                KeyEvent keyevent1 = KeyEvent.changeAction(keyevent, 0);
                if(movementmethod.onKeyDown(extractedittext, extractedittext.getText(), i, keyevent1))
                {
                    keyevent = KeyEvent.changeAction(keyevent, 1);
                    movementmethod.onKeyUp(extractedittext, extractedittext.getText(), i, keyevent);
                    while(--j > 0) 
                    {
                        movementmethod.onKeyDown(extractedittext, extractedittext.getText(), i, keyevent1);
                        movementmethod.onKeyUp(extractedittext, extractedittext.getText(), i, keyevent);
                    }
                    reportExtractedMovement(i, j);
                }
            }
        i;
        JVM INSTR tableswitch 19 22: default 148
    //                   19 254
    //                   20 254
    //                   21 254
    //                   22 254;
           goto _L2 _L3 _L3 _L3 _L3
_L2:
        return false;
_L3:
        return true;
    }

    void doStartInput(InputConnection inputconnection, EditorInfo editorinfo, boolean flag)
    {
        if(!flag)
            doFinishInput();
        mInputStarted = true;
        mStartedInputConnection = inputconnection;
        mInputEditorInfo = editorinfo;
        initialize();
        onStartInput(editorinfo, flag);
        if(!mWindowVisible) goto _L2; else goto _L1
_L1:
        if(!mShowInputRequested) goto _L4; else goto _L3
_L3:
        mInputViewStarted = true;
        onStartInputView(mInputEditorInfo, flag);
        startExtractingText(true);
_L2:
        return;
_L4:
        if(mCandidatesVisibility == 0)
        {
            mCandidatesViewStarted = true;
            onStartCandidatesView(mInputEditorInfo, flag);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        filedescriptor = new PrintWriterPrinter(printwriter);
        filedescriptor.println((new StringBuilder()).append("Input method service state for ").append(this).append(":").toString());
        filedescriptor.println((new StringBuilder()).append("  mWindowCreated=").append(mWindowCreated).append(" mWindowAdded=").append(mWindowAdded).toString());
        filedescriptor.println((new StringBuilder()).append("  mWindowVisible=").append(mWindowVisible).append(" mWindowWasVisible=").append(mWindowWasVisible).append(" mInShowWindow=").append(mInShowWindow).toString());
        filedescriptor.println((new StringBuilder()).append("  Configuration=").append(getResources().getConfiguration()).toString());
        filedescriptor.println((new StringBuilder()).append("  mToken=").append(mToken).toString());
        filedescriptor.println((new StringBuilder()).append("  mInputBinding=").append(mInputBinding).toString());
        filedescriptor.println((new StringBuilder()).append("  mInputConnection=").append(mInputConnection).toString());
        filedescriptor.println((new StringBuilder()).append("  mStartedInputConnection=").append(mStartedInputConnection).toString());
        filedescriptor.println((new StringBuilder()).append("  mInputStarted=").append(mInputStarted).append(" mInputViewStarted=").append(mInputViewStarted).append(" mCandidatesViewStarted=").append(mCandidatesViewStarted).toString());
        filedescriptor.println((new StringBuilder()).append("  mStartInputToken=").append(mStartInputToken).toString());
        if(mInputEditorInfo != null)
        {
            filedescriptor.println("  mInputEditorInfo:");
            mInputEditorInfo.dump(filedescriptor, "    ");
        } else
        {
            filedescriptor.println("  mInputEditorInfo: null");
        }
        filedescriptor.println((new StringBuilder()).append("  mShowInputRequested=").append(mShowInputRequested).append(" mLastShowInputRequested=").append(mLastShowInputRequested).append(" mShowInputFlags=0x").append(Integer.toHexString(mShowInputFlags)).toString());
        filedescriptor.println((new StringBuilder()).append("  mCandidatesVisibility=").append(mCandidatesVisibility).append(" mFullscreenApplied=").append(mFullscreenApplied).append(" mIsFullscreen=").append(mIsFullscreen).append(" mExtractViewHidden=").append(mExtractViewHidden).toString());
        if(mExtractedText != null)
        {
            filedescriptor.println("  mExtractedText:");
            filedescriptor.println((new StringBuilder()).append("    text=").append(mExtractedText.text.length()).append(" chars").append(" startOffset=").append(mExtractedText.startOffset).toString());
            filedescriptor.println((new StringBuilder()).append("    selectionStart=").append(mExtractedText.selectionStart).append(" selectionEnd=").append(mExtractedText.selectionEnd).append(" flags=0x").append(Integer.toHexString(mExtractedText.flags)).toString());
        } else
        {
            filedescriptor.println("  mExtractedText: null");
        }
        filedescriptor.println((new StringBuilder()).append("  mExtractedToken=").append(mExtractedToken).toString());
        filedescriptor.println((new StringBuilder()).append("  mIsInputViewShown=").append(mIsInputViewShown).append(" mStatusIcon=").append(mStatusIcon).toString());
        filedescriptor.println("Last computed insets:");
        filedescriptor.println((new StringBuilder()).append("  contentTopInsets=").append(mTmpInsets.contentTopInsets).append(" visibleTopInsets=").append(mTmpInsets.visibleTopInsets).append(" touchableInsets=").append(mTmpInsets.touchableInsets).append(" touchableRegion=").append(mTmpInsets.touchableRegion).toString());
        filedescriptor.println((new StringBuilder()).append(" mShouldClearInsetOfPreviousIme=").append(mShouldClearInsetOfPreviousIme).toString());
        filedescriptor.println((new StringBuilder()).append(" mSettingsObserver=").append(mSettingsObserver).toString());
    }

    public boolean enableHardwareAcceleration()
    {
        if(mWindow != null)
            throw new IllegalStateException("Must be called before onCreate()");
        else
            return ActivityManager.isHighEndGfx();
    }

    public final void exposeContent(InputContentInfo inputcontentinfo, InputConnection inputconnection)
    {
        if(inputconnection == null)
            return;
        if(getCurrentInputConnection() != inputconnection)
        {
            return;
        } else
        {
            mImm.exposeContent(mToken, inputcontentinfo, getCurrentInputEditorInfo());
            return;
        }
    }

    public int getBackDisposition()
    {
        return mBackDisposition;
    }

    public int getCandidatesHiddenVisibility()
    {
        byte byte0;
        if(isExtractViewShown())
            byte0 = 8;
        else
            byte0 = 4;
        return byte0;
    }

    public InputBinding getCurrentInputBinding()
    {
        return mInputBinding;
    }

    public InputConnection getCurrentInputConnection()
    {
        InputConnection inputconnection = mStartedInputConnection;
        if(inputconnection != null)
            return inputconnection;
        else
            return mInputConnection;
    }

    public EditorInfo getCurrentInputEditorInfo()
    {
        return mInputEditorInfo;
    }

    public boolean getCurrentInputStarted()
    {
        return mInputStarted;
    }

    public int getInputMethodWindowRecommendedHeight()
    {
        return mImm.getInputMethodWindowVisibleHeight();
    }

    public LayoutInflater getLayoutInflater()
    {
        return mInflater;
    }

    public int getMaxWidth()
    {
        return ((WindowManager)getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public CharSequence getTextForImeAction(int i)
    {
        switch(i & 0xff)
        {
        default:
            return getText(0x1040289);

        case 1: // '\001'
            return null;

        case 2: // '\002'
            return getText(0x104028b);

        case 3: // '\003'
            return getText(0x104028e);

        case 4: // '\004'
            return getText(0x104028f);

        case 5: // '\005'
            return getText(0x104028c);

        case 6: // '\006'
            return getText(0x104028a);

        case 7: // '\007'
            return getText(0x104028d);
        }
    }

    public Dialog getWindow()
    {
        return mWindow;
    }

    public void hideStatusIcon()
    {
        mStatusIcon = 0;
        mImm.hideStatusIcon(mToken);
    }

    public void hideWindow()
    {
        finishViews();
        if(mWindowVisible)
        {
            mWindow.hide();
            mWindowVisible = false;
            onWindowHidden();
            mWindowWasVisible = false;
        }
        updateFullscreenMode();
    }

    void initViews()
    {
        mInitialized = false;
        mWindowCreated = false;
        mShowInputRequested = false;
        mShowInputFlags = 0;
        mThemeAttrs = obtainStyledAttributes(android.R.styleable.InputMethodService);
        mRootView = mInflater.inflate(0x109006d, null);
        mRootView.setSystemUiVisibility(768);
        mWindow.setContentView(mRootView);
        mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(mInsetsComputer);
        mRootView.getViewTreeObserver().addOnComputeInternalInsetsListener(mInsetsComputer);
        if(android.provider.Settings.Global.getInt(getContentResolver(), "fancy_ime_animations", 0) != 0)
            mWindow.getWindow().setWindowAnimations(0x10302f3);
        mFullscreenArea = (ViewGroup)mRootView.findViewById(0x1020274);
        mExtractViewHidden = false;
        mExtractFrame = (FrameLayout)mRootView.findViewById(0x102001c);
        mExtractView = null;
        mExtractEditText = null;
        mExtractAccessories = null;
        mExtractAction = null;
        mFullscreenApplied = false;
        mCandidatesFrame = (FrameLayout)mRootView.findViewById(0x102001d);
        mInputFrame = (FrameLayout)mRootView.findViewById(0x102001e);
        mInputView = null;
        mIsInputViewShown = false;
        mExtractFrame.setVisibility(8);
        mCandidatesVisibility = getCandidatesHiddenVisibility();
        mCandidatesFrame.setVisibility(mCandidatesVisibility);
        mInputFrame.setVisibility(8);
    }

    void initialize()
    {
        if(!mInitialized)
        {
            mInitialized = true;
            onInitializeInterface();
        }
    }

    public boolean isExtractViewShown()
    {
        boolean flag;
        if(mIsFullscreen)
            flag = mExtractViewHidden ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean isFullscreenMode()
    {
        return mIsFullscreen;
    }

    public boolean isInputViewShown()
    {
        boolean flag;
        if(mIsInputViewShown)
            flag = mWindowVisible;
        else
            flag = false;
        return flag;
    }

    public boolean isShowInputRequested()
    {
        return mShowInputRequested;
    }

    public void onAppPrivateCommand(String s, Bundle bundle)
    {
    }

    public void onBindInput()
    {
    }

    public void onComputeInsets(Insets insets)
    {
        int ai[] = mTmpLocation;
        if(mInputFrame.getVisibility() == 0)
            mInputFrame.getLocationInWindow(ai);
        else
            ai[1] = getWindow().getWindow().getDecorView().getHeight();
        if(isFullscreenMode())
            insets.contentTopInsets = getWindow().getWindow().getDecorView().getHeight();
        else
            insets.contentTopInsets = ai[1];
        if(mCandidatesFrame.getVisibility() == 0)
            mCandidatesFrame.getLocationInWindow(ai);
        insets.visibleTopInsets = ai[1];
        insets.touchableInsets = 2;
        insets.touchableRegion.setEmpty();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        resetStateForNewConfiguration();
    }

    public void onConfigureWindow(Window window, boolean flag, boolean flag1)
    {
        int i = mWindow.getWindow().getAttributes().height;
        if(flag)
            i = -1;
        else
            i = -2;
        if(!mIsInputViewShown);
        mWindow.getWindow().setLayout(-1, i);
    }

    public void onCreate()
    {
        mTheme = Resources.selectSystemTheme(mTheme, getApplicationInfo().targetSdkVersion, 0x1030054, 0x103007f, 0x103013e, 0x103013e);
        super.setTheme(mTheme);
        super.onCreate();
        mImm = (InputMethodManager)getSystemService("input_method");
        mSettingsObserver = SettingsObserver.createAndRegister(this);
        boolean flag;
        if(mImm.getInputMethodWindowVisibleHeight() > 0)
            flag = true;
        else
            flag = false;
        mShouldClearInsetOfPreviousIme = flag;
        mInflater = (LayoutInflater)getSystemService("layout_inflater");
        mWindow = new SoftInputWindow(this, "InputMethod", mTheme, null, null, mDispatcherState, 2011, 80, false);
        initViews();
        mWindow.getWindow().setLayout(-1, -2);
    }

    public View onCreateCandidatesView()
    {
        return null;
    }

    public View onCreateExtractTextView()
    {
        return mInflater.inflate(0x109006e, null);
    }

    public AbstractInputMethodService.AbstractInputMethodImpl onCreateInputMethodInterface()
    {
        return new InputMethodImpl();
    }

    public AbstractInputMethodService.AbstractInputMethodSessionImpl onCreateInputMethodSessionInterface()
    {
        return new InputMethodSessionImpl();
    }

    public View onCreateInputView()
    {
        return null;
    }

    protected void onCurrentInputMethodSubtypeChanged(InputMethodSubtype inputmethodsubtype)
    {
    }

    public void onDestroy()
    {
        super.onDestroy();
        mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(mInsetsComputer);
        doFinishInput();
        if(mWindowAdded)
        {
            mWindow.getWindow().setWindowAnimations(0);
            mWindow.dismiss();
        }
        if(mSettingsObserver != null)
        {
            mSettingsObserver.unregister();
            mSettingsObserver = null;
        }
    }

    public void onDisplayCompletions(CompletionInfo acompletioninfo[])
    {
    }

    public boolean onEvaluateFullscreenMode()
    {
        if(getResources().getConfiguration().orientation != 2)
            return false;
        return mInputEditorInfo == null || (mInputEditorInfo.imeOptions & 0x2000000) == 0;
    }

    public boolean onEvaluateInputViewShown()
    {
        boolean flag = true;
        if(mSettingsObserver == null)
        {
            Log.w("InputMethodService", "onEvaluateInputViewShown: mSettingsObserver must not be null here.");
            return false;
        }
        if(SettingsObserver._2D_wrap0(mSettingsObserver))
            return true;
        Configuration configuration = getResources().getConfiguration();
        boolean flag1 = flag;
        if(configuration.keyboard != 1)
            if(configuration.hardKeyboardHidden == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean onExtractTextContextMenuItem(int i)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
            inputconnection.performContextMenuAction(i);
        return true;
    }

    public void onExtractedCursorMovement(int i, int j)
    {
        if(mExtractEditText == null || j == 0)
            return;
        if(mExtractEditText.hasVerticalScrollBar())
            setCandidatesViewShown(false);
    }

    public void onExtractedDeleteText(int i, int j)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
        {
            inputconnection.finishComposingText();
            inputconnection.setSelection(i, i);
            inputconnection.deleteSurroundingText(0, j - i);
        }
    }

    public void onExtractedReplaceText(int i, int j, CharSequence charsequence)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
        {
            inputconnection.setComposingRegion(i, j);
            inputconnection.commitText(charsequence, 1);
        }
    }

    public void onExtractedSelectionChanged(int i, int j)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
            inputconnection.setSelection(i, j);
    }

    public void onExtractedSetSpan(Object obj, int i, int j, int k)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
        {
            if(!inputconnection.setSelection(i, j))
                return;
            CharSequence charsequence = inputconnection.getSelectedText(1);
            if(charsequence instanceof Spannable)
            {
                ((Spannable)charsequence).setSpan(obj, 0, charsequence.length(), k);
                inputconnection.setComposingRegion(i, j);
                inputconnection.commitText(charsequence, 1);
            }
        }
    }

    public void onExtractedTextClicked()
    {
        if(mExtractEditText == null)
            return;
        if(mExtractEditText.hasVerticalScrollBar())
            setCandidatesViewShown(false);
    }

    public void onExtractingInputChanged(EditorInfo editorinfo)
    {
        if(editorinfo.inputType == 0)
            requestHideSelf(2);
    }

    public void onFinishCandidatesView(boolean flag)
    {
        if(!flag)
        {
            InputConnection inputconnection = getCurrentInputConnection();
            if(inputconnection != null)
                inputconnection.finishComposingText();
        }
    }

    public void onFinishInput()
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
            inputconnection.finishComposingText();
    }

    public void onFinishInputView(boolean flag)
    {
        if(!flag)
        {
            InputConnection inputconnection = getCurrentInputConnection();
            if(inputconnection != null)
                inputconnection.finishComposingText();
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void onInitializeInterface()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 4)
        {
            ExtractEditText extractedittext = getExtractEditTextIfVisible();
            if(extractedittext != null && extractedittext.handleBackInTextActionModeIfNeeded(keyevent))
                return true;
            if(handleBack(false))
            {
                keyevent.startTracking();
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return doMovementKey(i, keyevent, -1);
        }
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return doMovementKey(i, keyevent, j);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 4)
        {
            ExtractEditText extractedittext = getExtractEditTextIfVisible();
            if(extractedittext != null && extractedittext.handleBackInTextActionModeIfNeeded(keyevent))
                return true;
            if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                return handleBack(true);
        }
        return doMovementKey(i, keyevent, -2);
    }

    public boolean onShowInputRequested(int i, boolean flag)
    {
        if(!onEvaluateInputViewShown())
            return false;
        if((i & 1) == 0)
        {
            if(!flag && onEvaluateFullscreenMode())
                return false;
            if(!SettingsObserver._2D_wrap0(mSettingsObserver) && getResources().getConfiguration().keyboard != 1)
                return false;
        }
        return true;
    }

    public void onStartCandidatesView(EditorInfo editorinfo, boolean flag)
    {
    }

    public void onStartInput(EditorInfo editorinfo, boolean flag)
    {
    }

    public void onStartInputView(EditorInfo editorinfo, boolean flag)
    {
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void onUnbindInput()
    {
    }

    public void onUpdateCursor(Rect rect)
    {
    }

    public void onUpdateCursorAnchorInfo(CursorAnchorInfo cursoranchorinfo)
    {
    }

    public void onUpdateExtractedText(int i, ExtractedText extractedtext)
    {
        if(mExtractedToken != i)
            return;
        if(extractedtext != null && mExtractEditText != null)
        {
            mExtractedText = extractedtext;
            mExtractEditText.setExtractedText(extractedtext);
        }
    }

    public void onUpdateExtractingViews(EditorInfo editorinfo)
    {
        if(!isExtractViewShown())
            return;
        if(mExtractAccessories == null)
            return;
        boolean flag;
        if(editorinfo.actionLabel == null)
        {
            if((editorinfo.imeOptions & 0xff) != 1 && (editorinfo.imeOptions & 0x20000000) == 0)
            {
                if(editorinfo.inputType != 0)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = true;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        mExtractAccessories.setVisibility(0);
        if(mExtractAction != null)
        {
            if(mExtractAction instanceof ImageButton)
            {
                ((ImageButton)mExtractAction).setImageResource(getIconForImeAction(editorinfo.imeOptions));
                if(editorinfo.actionLabel != null)
                    mExtractAction.setContentDescription(editorinfo.actionLabel);
                else
                    mExtractAction.setContentDescription(getTextForImeAction(editorinfo.imeOptions));
            } else
            if(editorinfo.actionLabel != null)
                ((TextView)mExtractAction).setText(editorinfo.actionLabel);
            else
                ((TextView)mExtractAction).setText(getTextForImeAction(editorinfo.imeOptions));
            mExtractAction.setOnClickListener(mActionClickListener);
        }
_L4:
        return;
_L2:
        mExtractAccessories.setVisibility(8);
        if(mExtractAction != null)
            mExtractAction.setOnClickListener(null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onUpdateExtractingVisibility(EditorInfo editorinfo)
    {
        if(editorinfo.inputType == 0 || (editorinfo.imeOptions & 0x10000000) != 0)
        {
            setExtractViewShown(false);
            return;
        } else
        {
            setExtractViewShown(true);
            return;
        }
    }

    public void onUpdateSelection(int i, int j, int k, int l, int i1, int j1)
    {
        ExtractEditText extractedittext = mExtractEditText;
        if(extractedittext != null && isFullscreenMode() && mExtractedText != null)
        {
            i = mExtractedText.startOffset;
            extractedittext.startInternalChanges();
            j = k - i;
            l -= i;
            k = extractedittext.getText().length();
            if(j < 0)
            {
                i = 0;
            } else
            {
                i = j;
                if(j > k)
                    i = k;
            }
            if(l < 0)
            {
                j = 0;
            } else
            {
                j = l;
                if(l > k)
                    j = k;
            }
            extractedittext.setSelection(i, j);
            extractedittext.finishInternalChanges();
        }
    }

    public void onViewClicked(boolean flag)
    {
    }

    public void onWindowHidden()
    {
    }

    public void onWindowShown()
    {
    }

    void reportExtractedMovement(int i, int j)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        i;
        JVM INSTR tableswitch 19 22: default 36
    //                   19 65
    //                   20 73
    //                   21 48
    //                   22 57;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        j = ((flag1) ? 1 : 0);
        i = ((flag) ? 1 : 0);
_L7:
        onExtractedCursorMovement(i, j);
        return;
_L4:
        i = -j;
        j = ((flag1) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L5:
        i = j;
        j = ((flag1) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L2:
        j = -j;
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L3:
        i = ((flag) ? 1 : 0);
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void requestHideSelf(int i)
    {
        mImm.hideSoftInputFromInputMethod(mToken, i);
    }

    public boolean sendDefaultEditorAction(boolean flag)
    {
        EditorInfo editorinfo = getCurrentInputEditorInfo();
        if(editorinfo != null && (!flag || (editorinfo.imeOptions & 0x40000000) == 0) && (editorinfo.imeOptions & 0xff) != 1)
        {
            InputConnection inputconnection = getCurrentInputConnection();
            if(inputconnection != null)
                inputconnection.performEditorAction(editorinfo.imeOptions & 0xff);
            return true;
        } else
        {
            return false;
        }
    }

    public void sendDownUpKeyEvents(int i)
    {
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection == null)
        {
            return;
        } else
        {
            long l = SystemClock.uptimeMillis();
            inputconnection.sendKeyEvent(new KeyEvent(l, l, 0, i, 0, 0, -1, 0, 6));
            inputconnection.sendKeyEvent(new KeyEvent(l, SystemClock.uptimeMillis(), 1, i, 0, 0, -1, 0, 6));
            return;
        }
    }

    public void sendKeyChar(char c)
    {
        c;
        JVM INSTR tableswitch 10 10: default 20
    //                   10 44;
           goto _L1 _L2
_L1:
        if(c < '0' || c > '9') goto _L4; else goto _L3
_L3:
        sendDownUpKeyEvents((c - 48) + 7);
_L6:
        return;
_L2:
        if(!sendDefaultEditorAction(true))
            sendDownUpKeyEvents(66);
        continue; /* Loop/switch isn't completed */
_L4:
        InputConnection inputconnection = getCurrentInputConnection();
        if(inputconnection != null)
            inputconnection.commitText(String.valueOf(c), 1);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setBackDisposition(int i)
    {
        mBackDisposition = i;
    }

    public void setCandidatesView(View view)
    {
        mCandidatesFrame.removeAllViews();
        mCandidatesFrame.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -2));
    }

    public void setCandidatesViewShown(boolean flag)
    {
        updateCandidatesVisibility(flag);
        if(!mShowInputRequested && mWindowVisible != flag)
            if(flag)
                showWindow(false);
            else
                doHideWindow();
    }

    public void setExtractView(View view)
    {
        mExtractFrame.removeAllViews();
        mExtractFrame.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -1));
        mExtractView = view;
        if(view != null)
        {
            mExtractEditText = (ExtractEditText)view.findViewById(0x1020025);
            mExtractEditText.setIME(this);
            mExtractAction = view.findViewById(0x10202ad);
            if(mExtractAction != null)
                mExtractAccessories = (ViewGroup)view.findViewById(0x10202ac);
            startExtractingText(false);
        } else
        {
            mExtractEditText = null;
            mExtractAccessories = null;
            mExtractAction = null;
        }
    }

    public void setExtractViewShown(boolean flag)
    {
        if(mExtractViewHidden == flag)
        {
            mExtractViewHidden = flag ^ true;
            updateExtractFrameVisibility();
        }
    }

    public void setInputView(View view)
    {
        mInputFrame.removeAllViews();
        mInputFrame.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -2));
        mInputView = view;
    }

    public void setTheme(int i)
    {
        if(mWindow != null)
        {
            throw new IllegalStateException("Must be called before onCreate()");
        } else
        {
            mTheme = i;
            return;
        }
    }

    public void showStatusIcon(int i)
    {
        mStatusIcon = i;
        mImm.showStatusIcon(mToken, getPackageName(), i);
    }

    public void showWindow(boolean flag)
    {
        if(mInShowWindow)
        {
            Log.w("InputMethodService", "Re-entrance in to showWindow");
            return;
        }
        mWindowWasVisible = mWindowVisible;
        mInShowWindow = true;
        showWindowInner(flag);
        mWindowWasVisible = true;
        mInShowWindow = false;
        return;
        Object obj;
        obj;
        mWindowVisible = false;
        mWindowAdded = false;
        throw obj;
        obj;
        mWindowWasVisible = true;
        mInShowWindow = false;
        throw obj;
    }

    void showWindowInner(boolean flag)
    {
        byte byte0 = 2;
        boolean flag1 = false;
        int i;
        int j;
        if(mWindowVisible)
            i = 1;
        else
            i = 0;
        if(isInputViewShown())
            j = 2;
        else
            j = 0;
        j = i | j;
        mWindowVisible = true;
        i = ((flag1) ? 1 : 0);
        if(!mShowInputRequested)
        {
            i = ((flag1) ? 1 : 0);
            if(mInputStarted)
            {
                i = ((flag1) ? 1 : 0);
                if(flag)
                {
                    i = 1;
                    mShowInputRequested = true;
                }
            }
        }
        initialize();
        updateFullscreenMode();
        updateInputViewShown();
        if(!mWindowAdded || mWindowCreated ^ true)
        {
            mWindowAdded = true;
            mWindowCreated = true;
            initialize();
            View view = onCreateCandidatesView();
            if(view != null)
                setCandidatesView(view);
        }
        if(mShowInputRequested)
        {
            if(!mInputViewStarted)
            {
                mInputViewStarted = true;
                onStartInputView(mInputEditorInfo, false);
            }
        } else
        if(!mCandidatesViewStarted)
        {
            mCandidatesViewStarted = true;
            onStartCandidatesView(mInputEditorInfo, false);
        }
        if(i != 0)
            startExtractingText(false);
        if(isInputViewShown())
            i = byte0;
        else
            i = 0;
        i |= 1;
        if(j != i)
            mImm.setImeWindowStatus(mToken, mStartInputToken, i, mBackDisposition);
        if((j & 1) == 0)
        {
            onWindowShown();
            mWindow.show();
            mShouldClearInsetOfPreviousIme = false;
        }
    }

    void startExtractingText(boolean flag)
    {
        Object obj;
        ExtractEditText extractedittext;
        obj = null;
        extractedittext = mExtractEditText;
        if(extractedittext == null || !getCurrentInputStarted() || !isFullscreenMode()) goto _L2; else goto _L1
_L1:
        mExtractedToken = mExtractedToken + 1;
        ExtractedTextRequest extractedtextrequest = new ExtractedTextRequest();
        extractedtextrequest.token = mExtractedToken;
        extractedtextrequest.flags = 1;
        extractedtextrequest.hintMaxLines = 10;
        extractedtextrequest.hintMaxChars = 10000;
        InputConnection inputconnection = getCurrentInputConnection();
        int i;
        int j;
        if(inputconnection != null)
            obj = inputconnection.getExtractedText(extractedtextrequest, 1);
        mExtractedText = ((ExtractedText) (obj));
        if(mExtractedText == null || inputconnection == null)
            Log.e("InputMethodService", (new StringBuilder()).append("Unexpected null in startExtractingText : mExtractedText = ").append(mExtractedText).append(", input connection = ").append(inputconnection).toString());
        obj = getCurrentInputEditorInfo();
        extractedittext.startInternalChanges();
        onUpdateExtractingVisibility(((EditorInfo) (obj)));
        onUpdateExtractingViews(((EditorInfo) (obj)));
        i = ((EditorInfo) (obj)).inputType;
        j = i;
        if((i & 0xf) == 1)
        {
            j = i;
            if((0x40000 & i) != 0)
                j = i | 0x20000;
        }
        extractedittext.setInputType(j);
        extractedittext.setHint(((EditorInfo) (obj)).hintText);
        if(mExtractedText == null) goto _L4; else goto _L3
_L3:
        extractedittext.setEnabled(true);
        extractedittext.setExtractedText(mExtractedText);
_L6:
        extractedittext.finishInternalChanges();
        if(flag)
            onExtractingInputChanged(((EditorInfo) (obj)));
_L2:
        return;
_L4:
        extractedittext.setEnabled(false);
        extractedittext.setText("");
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        extractedittext.finishInternalChanges();
        throw exception;
    }

    public void switchInputMethod(String s)
    {
        mImm.setInputMethod(mToken, s);
    }

    void updateCandidatesVisibility(boolean flag)
    {
        int i;
        if(flag)
            i = 0;
        else
            i = getCandidatesHiddenVisibility();
        if(mCandidatesVisibility != i)
        {
            mCandidatesFrame.setVisibility(i);
            mCandidatesVisibility = i;
        }
    }

    void updateExtractFrameVisibility()
    {
        int i = 1;
        byte byte0;
        boolean flag;
        if(isFullscreenMode())
        {
            TypedArray typedarray;
            if(mExtractViewHidden)
                byte0 = 4;
            else
                byte0 = 0;
            mExtractFrame.setVisibility(byte0);
        } else
        {
            byte0 = 0;
            mExtractFrame.setVisibility(8);
        }
        if(mCandidatesVisibility == 0)
            flag = true;
        else
            flag = false;
        updateCandidatesVisibility(flag);
        if(mWindowWasVisible && mFullscreenArea.getVisibility() != byte0)
        {
            typedarray = mThemeAttrs;
            if(byte0 != 0)
                i = 2;
            i = typedarray.getResourceId(i, 0);
            if(i != 0)
                mFullscreenArea.startAnimation(AnimationUtils.loadAnimation(this, i));
        }
        mFullscreenArea.setVisibility(byte0);
    }

    public void updateFullscreenMode()
    {
        boolean flag;
        boolean flag1;
        if(mShowInputRequested)
            flag = onEvaluateFullscreenMode();
        else
            flag = false;
        if(mLastShowInputRequested != mShowInputRequested)
            flag1 = true;
        else
            flag1 = false;
        if(mIsFullscreen != flag || mFullscreenApplied ^ true)
        {
            flag1 = true;
            mIsFullscreen = flag;
            if(mImm != null && mToken != null)
                mImm.reportFullscreenMode(mToken, mIsFullscreen);
            mFullscreenApplied = true;
            initialize();
            Object obj = (android.widget.LinearLayout.LayoutParams)mFullscreenArea.getLayoutParams();
            if(flag)
            {
                mFullscreenArea.setBackgroundDrawable(mThemeAttrs.getDrawable(0));
                obj.height = 0;
                obj.weight = 1.0F;
            } else
            {
                mFullscreenArea.setBackgroundDrawable(null);
                obj.height = -2;
                obj.weight = 0.0F;
            }
            ((ViewGroup)mFullscreenArea.getParent()).updateViewLayout(mFullscreenArea, ((android.view.ViewGroup.LayoutParams) (obj)));
            if(flag)
            {
                if(mExtractView == null)
                {
                    obj = onCreateExtractTextView();
                    if(obj != null)
                        setExtractView(((View) (obj)));
                }
                startExtractingText(false);
            }
            updateExtractFrameVisibility();
        }
        if(flag1)
        {
            onConfigureWindow(mWindow.getWindow(), flag, mShowInputRequested ^ true);
            mLastShowInputRequested = mShowInputRequested;
        }
    }

    public void updateInputViewShown()
    {
        boolean flag;
        if(mShowInputRequested)
            flag = onEvaluateInputViewShown();
        else
            flag = false;
        if(mIsInputViewShown != flag && mWindowVisible)
        {
            mIsInputViewShown = flag;
            FrameLayout framelayout = mInputFrame;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            framelayout.setVisibility(i);
            if(mInputView == null)
            {
                initialize();
                View view = onCreateInputView();
                if(view != null)
                    setInputView(view);
            }
        }
    }

    public static final int BACK_DISPOSITION_DEFAULT = 0;
    public static final int BACK_DISPOSITION_WILL_DISMISS = 2;
    public static final int BACK_DISPOSITION_WILL_NOT_DISMISS = 1;
    static final boolean DEBUG = false;
    public static final int IME_ACTIVE = 1;
    public static final int IME_VISIBLE = 2;
    static final int MOVEMENT_DOWN = -1;
    static final int MOVEMENT_UP = -2;
    static final String TAG = "InputMethodService";
    final android.view.View.OnClickListener mActionClickListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            EditorInfo editorinfo;
            editorinfo = getCurrentInputEditorInfo();
            view = getCurrentInputConnection();
            if(editorinfo == null || view == null) goto _L2; else goto _L1
_L1:
            if(editorinfo.actionId == 0) goto _L4; else goto _L3
_L3:
            view.performEditorAction(editorinfo.actionId);
_L2:
            return;
_L4:
            if((editorinfo.imeOptions & 0xff) != 1)
                view.performEditorAction(editorinfo.imeOptions & 0xff);
            if(true) goto _L2; else goto _L5
_L5:
        }

        final InputMethodService this$0;

            
            {
                this$0 = InputMethodService.this;
                super();
            }
    }
;
    int mBackDisposition;
    FrameLayout mCandidatesFrame;
    boolean mCandidatesViewStarted;
    int mCandidatesVisibility;
    CompletionInfo mCurCompletions[];
    ViewGroup mExtractAccessories;
    View mExtractAction;
    ExtractEditText mExtractEditText;
    FrameLayout mExtractFrame;
    View mExtractView;
    boolean mExtractViewHidden;
    ExtractedText mExtractedText;
    int mExtractedToken;
    boolean mFullscreenApplied;
    ViewGroup mFullscreenArea;
    InputMethodManager mImm;
    boolean mInShowWindow;
    LayoutInflater mInflater;
    boolean mInitialized;
    InputBinding mInputBinding;
    InputConnection mInputConnection;
    EditorInfo mInputEditorInfo;
    FrameLayout mInputFrame;
    boolean mInputStarted;
    View mInputView;
    boolean mInputViewStarted;
    final android.view.ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new android.view.ViewTreeObserver.OnComputeInternalInsetsListener() {

        public void onComputeInternalInsets(android.view.ViewTreeObserver.InternalInsetsInfo internalinsetsinfo)
        {
            if(isExtractViewShown())
            {
                View view = getWindow().getWindow().getDecorView();
                Rect rect = internalinsetsinfo.contentInsets;
                int i = view.getHeight();
                internalinsetsinfo.visibleInsets.top = i;
                rect.top = i;
                internalinsetsinfo.touchableRegion.setEmpty();
                internalinsetsinfo.setTouchableInsets(0);
            } else
            {
                onComputeInsets(mTmpInsets);
                internalinsetsinfo.contentInsets.top = mTmpInsets.contentTopInsets;
                internalinsetsinfo.visibleInsets.top = mTmpInsets.visibleTopInsets;
                internalinsetsinfo.touchableRegion.set(mTmpInsets.touchableRegion);
                internalinsetsinfo.setTouchableInsets(mTmpInsets.touchableInsets);
            }
        }

        final InputMethodService this$0;

            
            {
                this$0 = InputMethodService.this;
                super();
            }
    }
;
    boolean mIsFullscreen;
    boolean mIsInputViewShown;
    boolean mLastShowInputRequested;
    View mRootView;
    private SettingsObserver mSettingsObserver;
    boolean mShouldClearInsetOfPreviousIme;
    int mShowInputFlags;
    boolean mShowInputRequested;
    private IBinder mStartInputToken;
    InputConnection mStartedInputConnection;
    int mStatusIcon;
    int mTheme;
    TypedArray mThemeAttrs;
    final Insets mTmpInsets = new Insets();
    final int mTmpLocation[] = new int[2];
    IBinder mToken;
    SoftInputWindow mWindow;
    boolean mWindowAdded;
    boolean mWindowCreated;
    boolean mWindowVisible;
    boolean mWindowWasVisible;
}
