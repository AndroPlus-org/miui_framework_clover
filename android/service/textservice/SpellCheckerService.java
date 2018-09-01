// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.textservice;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.text.TextUtils;
import android.text.method.WordIterator;
import android.view.textservice.*;
import com.android.internal.textservice.ISpellCheckerServiceCallback;
import com.android.internal.textservice.ISpellCheckerSessionListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

public abstract class SpellCheckerService extends Service
{
    private static class InternalISpellCheckerSession extends com.android.internal.textservice.ISpellCheckerSession.Stub
    {

        public Bundle getBundle()
        {
            return mBundle;
        }

        public String getLocale()
        {
            return mLocale;
        }

        public void onCancel()
        {
            int i = Process.getThreadPriority(Process.myTid());
            Process.setThreadPriority(10);
            mSession.onCancel();
            Process.setThreadPriority(i);
            return;
            Exception exception;
            exception;
            Process.setThreadPriority(i);
            throw exception;
        }

        public void onClose()
        {
            int i = Process.getThreadPriority(Process.myTid());
            Process.setThreadPriority(10);
            mSession.onClose();
            Process.setThreadPriority(i);
            mListener = null;
            return;
            Exception exception;
            exception;
            Process.setThreadPriority(i);
            mListener = null;
            throw exception;
        }

        public void onGetSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
        {
            mListener.onGetSentenceSuggestions(mSession.onGetSentenceSuggestionsMultiple(atextinfo, i));
_L2:
            return;
            atextinfo;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void onGetSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
        {
            int j = Process.getThreadPriority(Process.myTid());
            Process.setThreadPriority(10);
            mListener.onGetSuggestions(mSession.onGetSuggestionsMultiple(atextinfo, i, flag));
            Process.setThreadPriority(j);
_L2:
            return;
            atextinfo;
            Process.setThreadPriority(j);
            if(true) goto _L2; else goto _L1
_L1:
            atextinfo;
            Process.setThreadPriority(j);
            throw atextinfo;
        }

        private final Bundle mBundle;
        private ISpellCheckerSessionListener mListener;
        private final String mLocale;
        private final Session mSession;

        public InternalISpellCheckerSession(String s, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle, Session session)
        {
            mListener = ispellcheckersessionlistener;
            mSession = session;
            mLocale = s;
            mBundle = bundle;
            session.setInternalISpellCheckerSession(this);
        }
    }

    private static class SentenceLevelAdapter
    {

        static SentenceTextInfoParams _2D_wrap0(SentenceLevelAdapter sentenceleveladapter, TextInfo textinfo)
        {
            return sentenceleveladapter.getSplitWords(textinfo);
        }

        private SentenceTextInfoParams getSplitWords(TextInfo textinfo)
        {
            WordIterator worditerator = mWordIterator;
            String s = textinfo.getText();
            int i = textinfo.getCookie();
            int j = s.length();
            ArrayList arraylist = new ArrayList();
            worditerator.setCharSequence(s, 0, s.length());
            int k = worditerator.following(0);
            int l = worditerator.getBeginning(k);
            do
            {
label0:
                {
                    if(l <= j && k != -1 && l != -1)
                    {
                        if(k >= 0 && k > l)
                        {
                            CharSequence charsequence = s.subSequence(l, k);
                            arraylist.add(new SentenceWordItem(new TextInfo(charsequence, 0, charsequence.length(), i, charsequence.hashCode()), l, k));
                        }
                        k = worditerator.following(k);
                        if(k != -1)
                            break label0;
                    }
                    return new SentenceTextInfoParams(textinfo, arraylist);
                }
                l = worditerator.getBeginning(k);
            } while(true);
        }

        public static SentenceSuggestionsInfo reconstructSuggestions(SentenceTextInfoParams sentencetextinfoparams, SuggestionsInfo asuggestionsinfo[])
        {
            if(asuggestionsinfo == null || asuggestionsinfo.length == 0)
                return null;
            if(sentencetextinfoparams == null)
                return null;
            int i = sentencetextinfoparams.mOriginalTextInfo.getCookie();
            int j = sentencetextinfoparams.mOriginalTextInfo.getSequence();
            int k = sentencetextinfoparams.mSize;
            int ai[] = new int[k];
            int ai1[] = new int[k];
            SuggestionsInfo asuggestionsinfo1[] = new SuggestionsInfo[k];
            int l = 0;
label0:
            do
            {
                if(l < k)
                {
                    SentenceWordItem sentenceworditem = (SentenceWordItem)sentencetextinfoparams.mItems.get(l);
                    Object obj = null;
                    int i1 = 0;
                    do
                    {
label1:
                        {
                            SuggestionsInfo suggestionsinfo = obj;
                            if(i1 < asuggestionsinfo.length)
                            {
                                SuggestionsInfo suggestionsinfo1 = asuggestionsinfo[i1];
                                if(suggestionsinfo1 == null || suggestionsinfo1.getSequence() != sentenceworditem.mTextInfo.getSequence())
                                    break label1;
                                suggestionsinfo = suggestionsinfo1;
                                suggestionsinfo1.setCookieAndSequence(i, j);
                            }
                            ai[l] = sentenceworditem.mStart;
                            ai1[l] = sentenceworditem.mLength;
                            if(suggestionsinfo == null)
                                suggestionsinfo = EMPTY_SUGGESTIONS_INFO;
                            asuggestionsinfo1[l] = suggestionsinfo;
                            l++;
                            continue label0;
                        }
                        i1++;
                    } while(true);
                }
                return new SentenceSuggestionsInfo(asuggestionsinfo1, ai, ai1);
            } while(true);
        }

        public static final SentenceSuggestionsInfo EMPTY_SENTENCE_SUGGESTIONS_INFOS[] = new SentenceSuggestionsInfo[0];
        private static final SuggestionsInfo EMPTY_SUGGESTIONS_INFO = new SuggestionsInfo(0, null);
        private final WordIterator mWordIterator;


        public SentenceLevelAdapter(Locale locale)
        {
            mWordIterator = new WordIterator(locale);
        }
    }

    public static class SentenceLevelAdapter.SentenceTextInfoParams
    {

        final ArrayList mItems;
        final TextInfo mOriginalTextInfo;
        final int mSize;

        public SentenceLevelAdapter.SentenceTextInfoParams(TextInfo textinfo, ArrayList arraylist)
        {
            mOriginalTextInfo = textinfo;
            mItems = arraylist;
            mSize = arraylist.size();
        }
    }

    public static class SentenceLevelAdapter.SentenceWordItem
    {

        public final int mLength;
        public final int mStart;
        public final TextInfo mTextInfo;

        public SentenceLevelAdapter.SentenceWordItem(TextInfo textinfo, int i, int j)
        {
            mTextInfo = textinfo;
            mStart = i;
            mLength = j - i;
        }
    }

    public static abstract class Session
    {

        public Bundle getBundle()
        {
            return mInternalSession.getBundle();
        }

        public String getLocale()
        {
            return mInternalSession.getLocale();
        }

        public void onCancel()
        {
        }

        public void onClose()
        {
        }

        public abstract void onCreate();

        public SentenceSuggestionsInfo[] onGetSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
        {
            if(atextinfo == null || atextinfo.length == 0)
                return SentenceLevelAdapter.EMPTY_SENTENCE_SUGGESTIONS_INFOS;
            if(mSentenceLevelAdapter != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorenter ;
            if(mSentenceLevelAdapter == null)
            {
                String s = getLocale();
                if(!TextUtils.isEmpty(s))
                {
                    SentenceLevelAdapter sentenceleveladapter = JVM INSTR new #35  <Class SpellCheckerService$SentenceLevelAdapter>;
                    Locale locale = JVM INSTR new #50  <Class Locale>;
                    locale.Locale(s);
                    sentenceleveladapter.SentenceLevelAdapter(locale);
                    mSentenceLevelAdapter = sentenceleveladapter;
                }
            }
            this;
            JVM INSTR monitorexit ;
_L2:
            if(mSentenceLevelAdapter == null)
                return SentenceLevelAdapter.EMPTY_SENTENCE_SUGGESTIONS_INFOS;
            break MISSING_BLOCK_LABEL_88;
            atextinfo;
            throw atextinfo;
            int j = atextinfo.length;
            SentenceSuggestionsInfo asentencesuggestionsinfo[] = new SentenceSuggestionsInfo[j];
            for(int k = 0; k < j; k++)
            {
                SentenceLevelAdapter.SentenceTextInfoParams sentencetextinfoparams = SentenceLevelAdapter._2D_wrap0(mSentenceLevelAdapter, atextinfo[k]);
                ArrayList arraylist = sentencetextinfoparams.mItems;
                int l = arraylist.size();
                TextInfo atextinfo1[] = new TextInfo[l];
                for(int i1 = 0; i1 < l; i1++)
                    atextinfo1[i1] = ((SentenceLevelAdapter.SentenceWordItem)arraylist.get(i1)).mTextInfo;

                asentencesuggestionsinfo[k] = SentenceLevelAdapter.reconstructSuggestions(sentencetextinfoparams, onGetSuggestionsMultiple(atextinfo1, i, true));
            }

            return asentencesuggestionsinfo;
        }

        public abstract SuggestionsInfo onGetSuggestions(TextInfo textinfo, int i);

        public SuggestionsInfo[] onGetSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
        {
            int j = atextinfo.length;
            SuggestionsInfo asuggestionsinfo[] = new SuggestionsInfo[j];
            for(int k = 0; k < j; k++)
            {
                asuggestionsinfo[k] = onGetSuggestions(atextinfo[k], i);
                asuggestionsinfo[k].setCookieAndSequence(atextinfo[k].getCookie(), atextinfo[k].getSequence());
            }

            return asuggestionsinfo;
        }

        public final void setInternalISpellCheckerSession(InternalISpellCheckerSession internalispellcheckersession)
        {
            mInternalSession = internalispellcheckersession;
        }

        private InternalISpellCheckerSession mInternalSession;
        private volatile SentenceLevelAdapter mSentenceLevelAdapter;

        public Session()
        {
        }
    }

    private static class SpellCheckerServiceBinder extends com.android.internal.textservice.ISpellCheckerService.Stub
    {

        public void getISpellCheckerSession(String s, ISpellCheckerSessionListener ispellcheckersessionlistener, Bundle bundle, ISpellCheckerServiceCallback ispellcheckerservicecallback)
        {
            Object obj = (SpellCheckerService)mInternalServiceRef.get();
            if(obj == null)
            {
                s = null;
            } else
            {
                obj = ((SpellCheckerService) (obj)).createSession();
                s = new InternalISpellCheckerSession(s, ispellcheckersessionlistener, bundle, ((Session) (obj)));
                ((Session) (obj)).onCreate();
            }
            ispellcheckerservicecallback.onSessionCreated(s);
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final WeakReference mInternalServiceRef;

        public SpellCheckerServiceBinder(SpellCheckerService spellcheckerservice)
        {
            mInternalServiceRef = new WeakReference(spellcheckerservice);
        }
    }


    public SpellCheckerService()
    {
    }

    public abstract Session createSession();

    public final IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    private static final boolean DBG = false;
    public static final String SERVICE_INTERFACE = "android.service.textservice.SpellCheckerService";
    private static final String TAG = android/service/textservice/SpellCheckerService.getSimpleName();
    private final SpellCheckerServiceBinder mBinder = new SpellCheckerServiceBinder(this);

}
