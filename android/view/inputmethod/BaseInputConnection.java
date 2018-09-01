// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.*;
import android.text.*;
import android.text.method.MetaKeyKeyListener;
import android.view.*;

// Referenced classes of package android.view.inputmethod:
//            InputConnection, ComposingText, InputMethodManager, CompletionInfo, 
//            InputContentInfo, CorrectionInfo, ExtractedTextRequest, ExtractedText

public class BaseInputConnection
    implements InputConnection
{

    public BaseInputConnection(View view, boolean flag)
    {
        mIMM = (InputMethodManager)view.getContext().getSystemService("input_method");
        mTargetView = view;
        mDummyMode = flag ^ true;
    }

    BaseInputConnection(InputMethodManager inputmethodmanager, boolean flag)
    {
        mIMM = inputmethodmanager;
        mTargetView = null;
        mDummyMode = flag ^ true;
    }

    private void ensureDefaultComposingSpans()
    {
        if(mDefaultComposingSpans == null)
        {
            Object obj;
            if(mTargetView != null)
                obj = mTargetView.getContext();
            else
            if(mIMM.mServedView != null)
                obj = mIMM.mServedView.getContext();
            else
                obj = null;
            if(obj != null)
            {
                obj = ((Context) (obj)).getTheme().obtainStyledAttributes(new int[] {
                    0x1010230
                });
                CharSequence charsequence = ((TypedArray) (obj)).getText(0);
                ((TypedArray) (obj)).recycle();
                if(charsequence != null && (charsequence instanceof Spanned))
                    mDefaultComposingSpans = ((Spanned)charsequence).getSpans(0, charsequence.length(), java/lang/Object);
            }
        }
    }

    private static int findIndexBackward(CharSequence charsequence, int i, int j)
    {
        int k = i;
        boolean flag = false;
        int l = charsequence.length();
        if(i < 0 || l < i)
            return INVALID_INDEX;
        if(j < 0)
            return INVALID_INDEX;
        i = j;
        j = ((flag) ? 1 : 0);
        do
        {
            if(i == 0)
                return k;
            if(--k < 0)
                if(j != 0)
                    return INVALID_INDEX;
                else
                    return 0;
            char c = charsequence.charAt(k);
            if(j != 0)
            {
                if(!Character.isHighSurrogate(c))
                    return INVALID_INDEX;
                j = 0;
                i--;
            } else
            if(!Character.isSurrogate(c))
            {
                i--;
            } else
            {
                if(Character.isHighSurrogate(c))
                    return INVALID_INDEX;
                j = 1;
            }
        } while(true);
    }

    private static int findIndexForward(CharSequence charsequence, int i, int j)
    {
        int k = i;
        boolean flag = false;
        int l = charsequence.length();
        if(i < 0 || l < i)
            return INVALID_INDEX;
        if(j < 0)
            return INVALID_INDEX;
        int i1 = j;
        j = ((flag) ? 1 : 0);
        i = k;
        do
        {
            if(i1 == 0)
                return i;
            if(i >= l)
                if(j != 0)
                    return INVALID_INDEX;
                else
                    return l;
            char c = charsequence.charAt(i);
            if(j != 0)
            {
                if(!Character.isLowSurrogate(c))
                    return INVALID_INDEX;
                i1--;
                j = 0;
                i++;
            } else
            if(!Character.isSurrogate(c))
            {
                i1--;
                i++;
            } else
            {
                if(Character.isLowSurrogate(c))
                    return INVALID_INDEX;
                j = 1;
                i++;
            }
        } while(true);
    }

    public static int getComposingSpanEnd(Spannable spannable)
    {
        return spannable.getSpanEnd(COMPOSING);
    }

    public static int getComposingSpanStart(Spannable spannable)
    {
        return spannable.getSpanStart(COMPOSING);
    }

    public static final void removeComposingSpans(Spannable spannable)
    {
        spannable.removeSpan(COMPOSING);
        Object aobj[] = spannable.getSpans(0, spannable.length(), java/lang/Object);
        if(aobj != null)
        {
            for(int i = aobj.length - 1; i >= 0; i--)
            {
                Object obj = aobj[i];
                if((spannable.getSpanFlags(obj) & 0x100) != 0)
                    spannable.removeSpan(obj);
            }

        }
    }

    private void replaceText(CharSequence charsequence, int i, boolean flag)
    {
        Editable editable = getEditable();
        if(editable == null)
            return;
        beginBatchEdit();
        int j = getComposingSpanStart(editable);
        int k = getComposingSpanEnd(editable);
        int i1 = j;
        int j1 = k;
        if(k < j)
        {
            i1 = k;
            j1 = j;
        }
        Object obj;
        if(i1 != -1 && j1 != -1)
        {
            removeComposingSpans(editable);
        } else
        {
            int l = Selection.getSelectionStart(editable);
            i1 = Selection.getSelectionEnd(editable);
            j = l;
            if(l < 0)
                j = 0;
            l = i1;
            if(i1 < 0)
                l = 0;
            i1 = j;
            j1 = l;
            if(l < j)
            {
                i1 = l;
                j1 = j;
            }
        }
        obj = charsequence;
        if(flag)
        {
            Object obj1;
            if(!(charsequence instanceof Spannable))
            {
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(charsequence);
                obj = spannablestringbuilder;
                ensureDefaultComposingSpans();
                obj1 = spannablestringbuilder;
                charsequence = ((CharSequence) (obj));
                if(mDefaultComposingSpans != null)
                {
                    j = 0;
                    do
                    {
                        obj1 = spannablestringbuilder;
                        charsequence = ((CharSequence) (obj));
                        if(j >= mDefaultComposingSpans.length)
                            break;
                        spannablestringbuilder.setSpan(mDefaultComposingSpans[j], 0, spannablestringbuilder.length(), 289);
                        j++;
                    } while(true);
                }
            } else
            {
                obj1 = (Spannable)charsequence;
            }
            setComposingSpans(((Spannable) (obj1)));
            obj = charsequence;
        }
        if(i > 0)
            j = i + (j1 - 1);
        else
            j = i + i1;
        i = j;
        if(j < 0)
            i = 0;
        j = i;
        if(i > editable.length())
            j = editable.length();
        Selection.setSelection(editable, j);
        editable.replace(i1, j1, ((CharSequence) (obj)));
        endBatchEdit();
    }

    private void sendCurrentText()
    {
        if(!mDummyMode)
            return;
        Editable editable = getEditable();
        if(editable != null)
        {
            int i = editable.length();
            if(i == 0)
                return;
            if(i == 1)
            {
                if(mKeyCharacterMap == null)
                    mKeyCharacterMap = KeyCharacterMap.load(-1);
                Object aobj[] = new char[1];
                editable.getChars(0, 1, ((char []) (aobj)), 0);
                aobj = mKeyCharacterMap.getEvents(((char []) (aobj)));
                if(aobj != null)
                {
                    for(int j = 0; j < aobj.length; j++)
                        sendKeyEvent(aobj[j]);

                    editable.clear();
                    return;
                }
            }
            sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), editable.toString(), -1, 0));
            editable.clear();
        }
    }

    public static void setComposingSpans(Spannable spannable)
    {
        setComposingSpans(spannable, 0, spannable.length());
    }

    public static void setComposingSpans(Spannable spannable, int i, int j)
    {
        Object aobj[] = spannable.getSpans(i, j, java/lang/Object);
        if(aobj != null)
        {
            int k = aobj.length - 1;
            while(k >= 0) 
            {
                Object obj = aobj[k];
                if(obj == COMPOSING)
                {
                    spannable.removeSpan(obj);
                } else
                {
                    int l = spannable.getSpanFlags(obj);
                    if((l & 0x133) != 289)
                        spannable.setSpan(obj, spannable.getSpanStart(obj), spannable.getSpanEnd(obj), l & 0xffffffcc | 0x100 | 0x21);
                }
                k--;
            }
        }
        spannable.setSpan(COMPOSING, i, j, 289);
    }

    public boolean beginBatchEdit()
    {
        return false;
    }

    public boolean clearMetaKeyStates(int i)
    {
        Editable editable = getEditable();
        if(editable == null)
        {
            return false;
        } else
        {
            MetaKeyKeyListener.clearMetaKeyState(editable, i);
            return true;
        }
    }

    public void closeConnection()
    {
        finishComposingText();
    }

    public boolean commitCompletion(CompletionInfo completioninfo)
    {
        return false;
    }

    public boolean commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle)
    {
        return false;
    }

    public boolean commitCorrection(CorrectionInfo correctioninfo)
    {
        return false;
    }

    public boolean commitText(CharSequence charsequence, int i)
    {
        replaceText(charsequence, i, false);
        sendCurrentText();
        return true;
    }

    public boolean deleteSurroundingText(int i, int j)
    {
        Editable editable = getEditable();
        if(editable == null)
            return false;
        beginBatchEdit();
        int k = Selection.getSelectionStart(editable);
        int l = Selection.getSelectionEnd(editable);
        int i1 = k;
        int j1 = l;
        if(k > l)
        {
            i1 = l;
            j1 = k;
        }
        int k1 = getComposingSpanStart(editable);
        int i2 = getComposingSpanEnd(editable);
        l = k1;
        k = i2;
        if(i2 < k1)
        {
            l = i2;
            k = k1;
        }
        int j2 = i1;
        i2 = j1;
        if(l != -1)
        {
            j2 = i1;
            i2 = j1;
            if(k != -1)
            {
                int l1 = i1;
                if(l < i1)
                    l1 = l;
                j2 = l1;
                i2 = j1;
                if(k > j1)
                {
                    i2 = k;
                    j2 = l1;
                }
            }
        }
        i1 = 0;
        if(i > 0)
        {
            i1 = j2 - i;
            i = i1;
            if(i1 < 0)
                i = 0;
            editable.delete(i, j2);
            i1 = j2 - i;
        }
        if(j > 0)
        {
            i1 = i2 - i1;
            j = i1 + j;
            i = j;
            if(j > editable.length())
                i = editable.length();
            editable.delete(i1, i);
        }
        endBatchEdit();
        return true;
    }

    public boolean deleteSurroundingTextInCodePoints(int i, int j)
    {
        Editable editable = getEditable();
        if(editable == null)
            return false;
        beginBatchEdit();
        int k = Selection.getSelectionStart(editable);
        int l = Selection.getSelectionEnd(editable);
        int i1 = k;
        int k1 = l;
        if(k > l)
        {
            i1 = l;
            k1 = k;
        }
        int l1 = getComposingSpanStart(editable);
        int j2 = getComposingSpanEnd(editable);
        l = l1;
        k = j2;
        if(j2 < l1)
        {
            l = j2;
            k = l1;
        }
        int k2 = i1;
        j2 = k1;
        if(l != -1)
        {
            k2 = i1;
            j2 = k1;
            if(k != -1)
            {
                int i2 = i1;
                if(l < i1)
                    i2 = l;
                k2 = i2;
                j2 = k1;
                if(k > k1)
                {
                    j2 = k;
                    k2 = i2;
                }
            }
        }
        if(k2 >= 0 && j2 >= 0)
        {
            i = findIndexBackward(editable, k2, Math.max(i, 0));
            if(i != INVALID_INDEX)
            {
                int j1 = findIndexForward(editable, j2, Math.max(j, 0));
                if(j1 != INVALID_INDEX)
                {
                    j = k2 - i;
                    if(j > 0)
                        editable.delete(i, k2);
                    if(j1 - j2 > 0)
                        editable.delete(j2 - j, j1 - j);
                }
            }
        }
        endBatchEdit();
        return true;
    }

    public boolean endBatchEdit()
    {
        return false;
    }

    public boolean finishComposingText()
    {
        Editable editable = getEditable();
        if(editable != null)
        {
            beginBatchEdit();
            removeComposingSpans(editable);
            sendCurrentText();
            endBatchEdit();
        }
        return true;
    }

    public int getCursorCapsMode(int i)
    {
        if(mDummyMode)
            return 0;
        Editable editable = getEditable();
        if(editable == null)
            return 0;
        int j = Selection.getSelectionStart(editable);
        int k = Selection.getSelectionEnd(editable);
        int l = j;
        if(j > k)
            l = k;
        return TextUtils.getCapsMode(editable, l, i);
    }

    public Editable getEditable()
    {
        if(mEditable == null)
        {
            mEditable = android.text.Editable.Factory.getInstance().newEditable("");
            Selection.setSelection(mEditable, 0);
        }
        return mEditable;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i)
    {
        return null;
    }

    public Handler getHandler()
    {
        return null;
    }

    public CharSequence getSelectedText(int i)
    {
        Editable editable = getEditable();
        if(editable == null)
            return null;
        int j = Selection.getSelectionStart(editable);
        int k = Selection.getSelectionEnd(editable);
        int l = j;
        int i1 = k;
        if(j > k)
        {
            i1 = j;
            l = k;
        }
        if(l == i1)
            return null;
        if((i & 1) != 0)
            return editable.subSequence(l, i1);
        else
            return TextUtils.substring(editable, l, i1);
    }

    public CharSequence getTextAfterCursor(int i, int j)
    {
        Editable editable = getEditable();
        if(editable == null)
            return null;
        int k = Selection.getSelectionStart(editable);
        int l = Selection.getSelectionEnd(editable);
        int i1 = l;
        if(k > l)
            i1 = k;
        k = i1;
        if(i1 < 0)
            k = 0;
        i1 = i;
        if(k + i > editable.length())
            i1 = editable.length() - k;
        if((j & 1) != 0)
            return editable.subSequence(k, k + i1);
        else
            return TextUtils.substring(editable, k, k + i1);
    }

    public CharSequence getTextBeforeCursor(int i, int j)
    {
        Editable editable = getEditable();
        if(editable == null)
            return null;
        int k = Selection.getSelectionStart(editable);
        int l = Selection.getSelectionEnd(editable);
        int i1 = k;
        if(k > l)
            i1 = l;
        if(i1 <= 0)
            return "";
        l = i;
        if(i > i1)
            l = i1;
        if((j & 1) != 0)
            return editable.subSequence(i1 - l, i1);
        else
            return TextUtils.substring(editable, i1 - l, i1);
    }

    public boolean performContextMenuAction(int i)
    {
        return false;
    }

    public boolean performEditorAction(int i)
    {
        long l = SystemClock.uptimeMillis();
        sendKeyEvent(new KeyEvent(l, l, 0, 66, 0, 0, -1, 0, 22));
        sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), l, 1, 66, 0, 0, -1, 0, 22));
        return true;
    }

    public boolean performPrivateCommand(String s, Bundle bundle)
    {
        return false;
    }

    public boolean reportFullscreenMode(boolean flag)
    {
        return true;
    }

    public boolean requestCursorUpdates(int i)
    {
        return false;
    }

    public boolean sendKeyEvent(KeyEvent keyevent)
    {
        mIMM.dispatchKeyEventFromInputMethod(mTargetView, keyevent);
        return false;
    }

    public boolean setComposingRegion(int i, int j)
    {
        Editable editable = getEditable();
        if(editable != null)
        {
            beginBatchEdit();
            removeComposingSpans(editable);
            int k = i;
            int l = j;
            if(i > j)
            {
                l = i;
                k = j;
            }
            int i1 = editable.length();
            i = k;
            if(k < 0)
                i = 0;
            j = l;
            if(l < 0)
                j = 0;
            l = i;
            if(i > i1)
                l = i1;
            i = j;
            if(j > i1)
                i = i1;
            ensureDefaultComposingSpans();
            if(mDefaultComposingSpans != null)
                for(j = 0; j < mDefaultComposingSpans.length; j++)
                    editable.setSpan(mDefaultComposingSpans[j], l, i, 289);

            editable.setSpan(COMPOSING, l, i, 289);
            sendCurrentText();
            endBatchEdit();
        }
        return true;
    }

    public boolean setComposingText(CharSequence charsequence, int i)
    {
        replaceText(charsequence, i, true);
        return true;
    }

    public boolean setSelection(int i, int j)
    {
        Editable editable = getEditable();
        if(editable == null)
            return false;
        for(int k = editable.length(); i > k || j > k || i < 0 || j < 0;)
            return true;

        if(i == j && MetaKeyKeyListener.getMetaState(editable, 2048) != 0)
            Selection.extendSelection(editable, i);
        else
            Selection.setSelection(editable, i, j);
        return true;
    }

    static final Object COMPOSING = new ComposingText();
    private static final boolean DEBUG = false;
    private static int INVALID_INDEX = 0;
    private static final String TAG = "BaseInputConnection";
    private Object mDefaultComposingSpans[];
    final boolean mDummyMode;
    Editable mEditable;
    protected final InputMethodManager mIMM;
    KeyCharacterMap mKeyCharacterMap;
    final View mTargetView;

    static 
    {
        INVALID_INDEX = -1;
    }
}
