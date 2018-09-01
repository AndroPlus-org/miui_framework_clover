// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.text.*;
import android.view.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.text.method:
//            BaseKeyListener, QwertyKeyListener, MultiTapKeyListener, KeyListener

public class TextKeyListener extends BaseKeyListener
    implements SpanWatcher
{
    public static final class Capitalize extends Enum
    {

        public static Capitalize valueOf(String s)
        {
            return (Capitalize)Enum.valueOf(android/text/method/TextKeyListener$Capitalize, s);
        }

        public static Capitalize[] values()
        {
            return $VALUES;
        }

        private static final Capitalize $VALUES[];
        public static final Capitalize CHARACTERS;
        public static final Capitalize NONE;
        public static final Capitalize SENTENCES;
        public static final Capitalize WORDS;

        static 
        {
            NONE = new Capitalize("NONE", 0);
            SENTENCES = new Capitalize("SENTENCES", 1);
            WORDS = new Capitalize("WORDS", 2);
            CHARACTERS = new Capitalize("CHARACTERS", 3);
            $VALUES = (new Capitalize[] {
                NONE, SENTENCES, WORDS, CHARACTERS
            });
        }

        private Capitalize(String s, int i)
        {
            super(s, i);
        }
    }

    private static class NullKeyListener
        implements KeyListener
    {

        public static NullKeyListener getInstance()
        {
            if(sInstance != null)
            {
                return sInstance;
            } else
            {
                sInstance = new NullKeyListener();
                return sInstance;
            }
        }

        public void clearMetaKeyState(View view, Editable editable, int i)
        {
        }

        public int getInputType()
        {
            return 0;
        }

        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
        {
            return false;
        }

        public boolean onKeyOther(View view, Editable editable, KeyEvent keyevent)
        {
            return false;
        }

        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyevent)
        {
            return false;
        }

        private static NullKeyListener sInstance;

        private NullKeyListener()
        {
        }
    }

    private class SettingsObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            if(TextKeyListener._2D_get0(TextKeyListener.this) != null)
            {
                ContentResolver contentresolver = (ContentResolver)TextKeyListener._2D_get0(TextKeyListener.this).get();
                if(contentresolver == null)
                    TextKeyListener._2D_set0(TextKeyListener.this, false);
                else
                    TextKeyListener._2D_wrap0(TextKeyListener.this, contentresolver);
            } else
            {
                TextKeyListener._2D_set0(TextKeyListener.this, false);
            }
        }

        final TextKeyListener this$0;

        public SettingsObserver()
        {
            this$0 = TextKeyListener.this;
            super(new Handler());
        }
    }


    static WeakReference _2D_get0(TextKeyListener textkeylistener)
    {
        return textkeylistener.mResolver;
    }

    static boolean _2D_set0(TextKeyListener textkeylistener, boolean flag)
    {
        textkeylistener.mPrefsInited = flag;
        return flag;
    }

    static void _2D_wrap0(TextKeyListener textkeylistener, ContentResolver contentresolver)
    {
        textkeylistener.updatePrefs(contentresolver);
    }

    public TextKeyListener(Capitalize capitalize, boolean flag)
    {
        mAutoCap = capitalize;
        mAutoText = flag;
    }

    public static void clear(Editable editable)
    {
        editable.clear();
        editable.removeSpan(ACTIVE);
        editable.removeSpan(CAPPED);
        editable.removeSpan(INHIBIT_REPLACEMENT);
        editable.removeSpan(LAST_TYPED);
        QwertyKeyListener.Replaced areplaced[] = (QwertyKeyListener.Replaced[])editable.getSpans(0, editable.length(), android/text/method/QwertyKeyListener$Replaced);
        int i = areplaced.length;
        for(int j = 0; j < i; j++)
            editable.removeSpan(areplaced[j]);

    }

    public static TextKeyListener getInstance()
    {
        return getInstance(false, Capitalize.NONE);
    }

    public static TextKeyListener getInstance(boolean flag, Capitalize capitalize)
    {
        int i = capitalize.ordinal();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        j = i * 2 + j;
        if(sInstance[j] == null)
            sInstance[j] = new TextKeyListener(capitalize, flag);
        return sInstance[j];
    }

    private KeyListener getKeyListener(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCharacterMap().getKeyboardType();
        if(i == 3)
            return QwertyKeyListener.getInstance(mAutoText, mAutoCap);
        if(i == 1)
            return MultiTapKeyListener.getInstance(mAutoText, mAutoCap);
        if(i == 4 || i == 5)
            return QwertyKeyListener.getInstanceForFullKeyboard();
        else
            return NullKeyListener.getInstance();
    }

    private void initPrefs(Context context)
    {
        context = context.getContentResolver();
        mResolver = new WeakReference(context);
        if(mObserver == null)
        {
            mObserver = new SettingsObserver();
            context.registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mObserver);
        }
        updatePrefs(context);
        mPrefsInited = true;
    }

    public static boolean shouldCap(Capitalize capitalize, CharSequence charsequence, int i)
    {
        if(capitalize == Capitalize.NONE)
            return false;
        if(capitalize == Capitalize.CHARACTERS)
            return true;
        char c;
        boolean flag;
        if(capitalize == Capitalize.WORDS)
            c = '\u2000';
        else
            c = '\u4000';
        if(TextUtils.getCapsMode(charsequence, i, c) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void updatePrefs(ContentResolver contentresolver)
    {
        byte byte0 = 0;
        boolean flag;
        byte byte1;
        byte byte2;
        boolean flag1;
        if(android.provider.Settings.System.getInt(contentresolver, "auto_caps", 1) > 0)
            flag = true;
        else
            flag = false;
        if(android.provider.Settings.System.getInt(contentresolver, "auto_replace", 1) > 0)
            byte1 = 1;
        else
            byte1 = 0;
        if(android.provider.Settings.System.getInt(contentresolver, "auto_punctuate", 1) > 0)
            byte2 = 1;
        else
            byte2 = 0;
        if(android.provider.Settings.System.getInt(contentresolver, "show_password", 1) > 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            flag = true;
        else
            flag = false;
        if(byte1 != 0)
            byte1 = 2;
        else
            byte1 = 0;
        if(byte2 != 0)
            byte2 = 4;
        else
            byte2 = 0;
        if(flag1)
            byte0 = 8;
        mPrefs = byte0 | (byte2 | (flag | byte1));
    }

    public int getInputType()
    {
        return makeTextContentType(mAutoCap, mAutoText);
    }

    int getPrefs(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if(!mPrefsInited || mResolver.get() == null)
            initPrefs(context);
        this;
        JVM INSTR monitorexit ;
        return mPrefs;
        context;
        throw context;
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyevent)
    {
        return getKeyListener(keyevent).onKeyDown(view, editable, i, keyevent);
    }

    public boolean onKeyOther(View view, Editable editable, KeyEvent keyevent)
    {
        return getKeyListener(keyevent).onKeyOther(view, editable, keyevent);
    }

    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyevent)
    {
        return getKeyListener(keyevent).onKeyUp(view, editable, i, keyevent);
    }

    public void onSpanAdded(Spannable spannable, Object obj, int i, int j)
    {
    }

    public void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
    {
        if(obj == Selection.SELECTION_END)
            spannable.removeSpan(ACTIVE);
    }

    public void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
    {
    }

    public void release()
    {
        if(mResolver != null)
        {
            ContentResolver contentresolver = (ContentResolver)mResolver.get();
            if(contentresolver != null)
            {
                contentresolver.unregisterContentObserver(mObserver);
                mResolver.clear();
            }
            mObserver = null;
            mResolver = null;
            mPrefsInited = false;
        }
    }

    static final Object ACTIVE = new android.text.NoCopySpan.Concrete();
    static final int AUTO_CAP = 1;
    static final int AUTO_PERIOD = 4;
    static final int AUTO_TEXT = 2;
    static final Object CAPPED = new android.text.NoCopySpan.Concrete();
    static final Object INHIBIT_REPLACEMENT = new android.text.NoCopySpan.Concrete();
    static final Object LAST_TYPED = new android.text.NoCopySpan.Concrete();
    static final int SHOW_PASSWORD = 8;
    private static TextKeyListener sInstance[] = new TextKeyListener[Capitalize.values().length * 2];
    private Capitalize mAutoCap;
    private boolean mAutoText;
    private SettingsObserver mObserver;
    private int mPrefs;
    private boolean mPrefsInited;
    private WeakReference mResolver;

}
