// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.text.*;
import android.text.method.WordIterator;
import android.text.style.SpellCheckSpan;
import android.text.style.SuggestionSpan;
import android.util.Log;
import android.util.LruCache;
import android.view.textservice.*;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.util.Locale;

// Referenced classes of package android.widget:
//            TextView

public class SpellChecker
    implements android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
{
    private class SpellParser
    {

        private void removeRangeSpan(Editable editable)
        {
            editable.removeSpan(mRange);
        }

        private void removeSpansAt(Editable editable, int i, Object aobj[])
        {
            int j = aobj.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                Object obj = aobj[k];
                if(editable.getSpanStart(obj) <= i && editable.getSpanEnd(obj) >= i)
                    editable.removeSpan(obj);
                k++;
            } while(true);
        }

        private void setRangeSpan(Editable editable, int i, int j)
        {
            editable.setSpan(mRange, i, j, 33);
        }

        public boolean isFinished()
        {
            boolean flag;
            if(((Editable)SpellChecker._2D_get6(SpellChecker.this).getText()).getSpanStart(mRange) < 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void parse()
        {
            Editable editable;
            int i;
            int j;
            int k;
            int l;
            int j1;
            int k1;
            SpellCheckSpan aspellcheckspan[];
            Object obj;
            int l1;
            boolean flag;
            int i2;
            int j2;
            editable = (Editable)SpellChecker._2D_get6(SpellChecker.this).getText();
            if(SpellChecker._2D_get2(SpellChecker.this))
                i = Math.max(0, editable.getSpanStart(mRange) - 50);
            else
                i = editable.getSpanStart(mRange);
            j = editable.getSpanEnd(mRange);
            k = Math.min(j, i + 350);
            SpellChecker._2D_get7(SpellChecker.this).setCharSequence(editable, i, k);
            l = SpellChecker._2D_get7(SpellChecker.this).preceding(i);
            if(l == -1)
            {
                int i1 = SpellChecker._2D_get7(SpellChecker.this).following(i);
                k1 = i1;
                if(i1 != -1)
                {
                    l = SpellChecker._2D_get7(SpellChecker.this).getBeginning(i1);
                    k1 = i1;
                }
            } else
            {
                k1 = SpellChecker._2D_get7(SpellChecker.this).getEnd(l);
            }
            if(k1 == -1)
            {
                removeRangeSpan(editable);
                return;
            }
            aspellcheckspan = (SpellCheckSpan[])editable.getSpans(i - 1, j + 1, android/text/style/SpellCheckSpan);
            obj = (SuggestionSpan[])editable.getSpans(i - 1, j + 1, android/text/style/SuggestionSpan);
            l1 = 0;
            flag = false;
            j1 = 0;
            i2 = k1;
            j2 = k;
            k1 = l;
            if(!SpellChecker._2D_get2(SpellChecker.this)) goto _L2; else goto _L1
_L1:
            k1 = j1;
            if(k < j)
                k1 = 1;
            j2 = SpellChecker._2D_get7(SpellChecker.this).preceding(k);
            if(j2 != -1)
                i2 = 1;
            else
                i2 = 0;
            j1 = i2;
            k = j2;
            if(i2 != 0)
            {
                k = SpellChecker._2D_get7(SpellChecker.this).getEnd(j2);
                if(k != -1)
                    j1 = 1;
                else
                    j1 = 0;
            }
            if(j1 == 0)
            {
                removeRangeSpan(editable);
                return;
            }
            l1 = 1;
            i2 = 0;
            j1 = l;
            l = k;
            k = i2;
_L9:
            i2 = l1;
            if(k >= SpellChecker._2D_get3(SpellChecker.this)) goto _L4; else goto _L3
_L3:
            obj = SpellChecker._2D_get4(SpellChecker.this)[k];
            i2 = l;
            j2 = j1;
            if(SpellChecker._2D_get1(SpellChecker.this)[k] < 0) goto _L6; else goto _L5
_L5:
            if(!((SpellCheckSpan) (obj)).isSpellCheckInProgress()) goto _L8; else goto _L7
_L7:
            j2 = j1;
            i2 = l;
_L6:
            k++;
            l = i2;
            j1 = j2;
              goto _L9
_L8:
            int k2;
            int l2;
            k2 = editable.getSpanStart(obj);
            l2 = editable.getSpanEnd(obj);
            i2 = l;
            j2 = j1;
            if(l2 < j1) goto _L6; else goto _L10
_L10:
            i2 = l;
            j2 = j1;
            if(l < k2) goto _L6; else goto _L11
_L11:
            if(k2 > j1 || l > l2) goto _L13; else goto _L12
_L12:
            i2 = 0;
_L4:
            if(l >= i)
                if(l <= j1)
                    Log.w(SpellChecker._2D_get0(), (new StringBuilder()).append("Trying to spellcheck invalid region, from ").append(i).append(" to ").append(j).toString());
                else
                if(i2 != 0)
                    SpellChecker._2D_wrap0(SpellChecker.this, editable, j1, l);
            j1 = l;
            k = k1;
_L15:
            if(k != 0 && j1 != -1 && j1 <= j)
                setRangeSpan(editable, j1, j);
            else
                removeRangeSpan(editable);
            SpellChecker._2D_wrap1(SpellChecker.this);
            return;
_L13:
            editable.removeSpan(obj);
            j2 = Math.min(k2, j1);
            i2 = Math.max(l2, l);
              goto _L6
_L2:
            k = ((flag) ? 1 : 0);
            j1 = k1;
            if(k1 > j) goto _L15; else goto _L14
_L14:
            l2 = l1;
            if(i2 < i) goto _L17; else goto _L16
_L16:
            l2 = l1;
            if(i2 <= k1) goto _L17; else goto _L18
_L18:
label0:
            {
                if(l1 < 50)
                    break label0;
                k = 1;
                j1 = k1;
            }
              goto _L15
            if(k1 < i && i2 > i)
            {
                removeSpansAt(editable, i, aspellcheckspan);
                removeSpansAt(editable, i, ((Object []) (obj)));
            }
            if(k1 < j && i2 > j)
            {
                removeSpansAt(editable, j, aspellcheckspan);
                removeSpansAt(editable, j, ((Object []) (obj)));
            }
            j1 = 1;
            l = j1;
            if(i2 != i) goto _L20; else goto _L19
_L19:
            k = 0;
_L28:
            l = j1;
            if(k >= aspellcheckspan.length) goto _L20; else goto _L21
_L21:
            if(editable.getSpanEnd(aspellcheckspan[k]) != i) goto _L23; else goto _L22
_L22:
            l = 0;
_L20:
            j1 = l;
            if(k1 != j) goto _L25; else goto _L24
_L24:
            k = 0;
_L29:
            j1 = l;
            if(k < aspellcheckspan.length)
            {
                if(editable.getSpanStart(aspellcheckspan[k]) != j)
                    break MISSING_BLOCK_LABEL_1023;
                j1 = 0;
            }
_L25:
            if(j1 != 0)
                SpellChecker._2D_wrap0(SpellChecker.this, editable, k1, i2);
            l2 = l1 + 1;
_L17:
label1:
            {
                k = SpellChecker._2D_get7(SpellChecker.this).following(i2);
                l = k;
                k2 = j2;
                if(j2 >= j)
                    break label1;
                if(k != -1)
                {
                    l = k;
                    k2 = j2;
                    if(k < j2)
                        break label1;
                }
                k2 = Math.min(j, i2 + 350);
                SpellChecker._2D_get7(SpellChecker.this).setCharSequence(editable, i2, k2);
                l = SpellChecker._2D_get7(SpellChecker.this).following(i2);
            }
            k = ((flag) ? 1 : 0);
            j1 = k1;
            if(l == -1) goto _L15; else goto _L26
_L26:
            j1 = SpellChecker._2D_get7(SpellChecker.this).getBeginning(l);
            l1 = l2;
            i2 = l;
            j2 = k2;
            k1 = j1;
            if(j1 != -1) goto _L2; else goto _L27
_L27:
            k = ((flag) ? 1 : 0);
              goto _L15
_L23:
            k++;
              goto _L28
            k++;
              goto _L29
        }

        public void parse(int i, int j)
        {
            int k = SpellChecker._2D_get6(SpellChecker.this).length();
            if(j > k)
            {
                Log.w(SpellChecker._2D_get0(), (new StringBuilder()).append("Parse invalid region, from ").append(i).append(" to ").append(j).toString());
                j = k;
            }
            if(j > i)
            {
                setRangeSpan((Editable)SpellChecker._2D_get6(SpellChecker.this).getText(), i, j);
                parse();
            }
        }

        public void stop()
        {
            removeRangeSpan((Editable)SpellChecker._2D_get6(SpellChecker.this).getText());
        }

        private Object mRange;
        final SpellChecker this$0;

        private SpellParser()
        {
            this$0 = SpellChecker.this;
            super();
            mRange = new Object();
        }

        SpellParser(SpellParser spellparser)
        {
            this();
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static int[] _2D_get1(SpellChecker spellchecker)
    {
        return spellchecker.mIds;
    }

    static boolean _2D_get2(SpellChecker spellchecker)
    {
        return spellchecker.mIsSentenceSpellCheckSupported;
    }

    static int _2D_get3(SpellChecker spellchecker)
    {
        return spellchecker.mLength;
    }

    static SpellCheckSpan[] _2D_get4(SpellChecker spellchecker)
    {
        return spellchecker.mSpellCheckSpans;
    }

    static SpellParser[] _2D_get5(SpellChecker spellchecker)
    {
        return spellchecker.mSpellParsers;
    }

    static TextView _2D_get6(SpellChecker spellchecker)
    {
        return spellchecker.mTextView;
    }

    static WordIterator _2D_get7(SpellChecker spellchecker)
    {
        return spellchecker.mWordIterator;
    }

    static void _2D_wrap0(SpellChecker spellchecker, Editable editable, int i, int j)
    {
        spellchecker.addSpellCheckSpan(editable, i, j);
    }

    static void _2D_wrap1(SpellChecker spellchecker)
    {
        spellchecker.spellCheck();
    }

    public SpellChecker(TextView textview)
    {
        mSpellParsers = new SpellParser[0];
        mSpanSequenceCounter = 0;
        mTextView = textview;
        mIds = ArrayUtils.newUnpaddedIntArray(1);
        mSpellCheckSpans = new SpellCheckSpan[mIds.length];
        setLocale(mTextView.getSpellCheckerLocale());
    }

    private void addSpellCheckSpan(Editable editable, int i, int j)
    {
        int k = nextSpellCheckSpanIndex();
        SpellCheckSpan spellcheckspan = mSpellCheckSpans[k];
        editable.setSpan(spellcheckspan, i, j, 33);
        spellcheckspan.setSpellCheckInProgress(false);
        editable = mIds;
        i = mSpanSequenceCounter;
        mSpanSequenceCounter = i + 1;
        editable[k] = i;
    }

    private void createMisspelledSuggestionSpan(Editable editable, SuggestionsInfo suggestionsinfo, SpellCheckSpan spellcheckspan, int i, int j)
    {
        int k = editable.getSpanStart(spellcheckspan);
        int l = editable.getSpanEnd(spellcheckspan);
        if(k < 0 || l <= k)
            return;
        if(i != -1 && j != -1)
        {
            l = k + i;
            i = l + j;
            j = l;
        } else
        {
            j = k;
            i = l;
        }
        k = suggestionsinfo.getSuggestionsCount();
        if(k > 0)
        {
            String as[] = new String[k];
            l = 0;
            do
            {
                spellcheckspan = as;
                if(l >= k)
                    break;
                as[l] = suggestionsinfo.getSuggestionAt(l);
                l++;
            } while(true);
        } else
        {
            spellcheckspan = (String[])ArrayUtils.emptyArray(java/lang/String);
        }
        SuggestionSpan suggestionspan = new SuggestionSpan(mTextView.getContext(), spellcheckspan, 3);
        if(mIsSentenceSpellCheckSupported)
        {
            spellcheckspan = Long.valueOf(TextUtils.packRangeInLong(j, i));
            suggestionsinfo = (SuggestionSpan)mSuggestionSpanCache.get(spellcheckspan);
            if(suggestionsinfo != null)
                editable.removeSpan(suggestionsinfo);
            mSuggestionSpanCache.put(spellcheckspan, suggestionspan);
        }
        editable.setSpan(suggestionspan, j, i, 33);
        mTextView.invalidateRegion(j, i, false);
    }

    public static boolean haveWordBoundariesChanged(Editable editable, int i, int j, int k, int l)
    {
        boolean flag;
        if(l != i && k != j)
            flag = true;
        else
        if(l == i && i < editable.length())
            flag = Character.isLetterOrDigit(Character.codePointAt(editable, i));
        else
        if(k == j && j > 0)
            flag = Character.isLetterOrDigit(Character.codePointBefore(editable, j));
        else
            flag = false;
        return flag;
    }

    private boolean isSessionActive()
    {
        boolean flag;
        if(mSpellCheckerSession != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int nextSpellCheckSpanIndex()
    {
        for(int i = 0; i < mLength; i++)
            if(mIds[i] < 0)
                return i;

        mIds = GrowingArrayUtils.append(mIds, mLength, 0);
        mSpellCheckSpans = (SpellCheckSpan[])GrowingArrayUtils.append(mSpellCheckSpans, mLength, new SpellCheckSpan());
        mLength = mLength + 1;
        return mLength - 1;
    }

    private SpellCheckSpan onGetSuggestionsInternal(SuggestionsInfo suggestionsinfo, int i, int j)
    {
        Editable editable;
        int k;
        int i1;
        if(suggestionsinfo == null || suggestionsinfo.getCookie() != mCookie)
            return null;
        editable = (Editable)mTextView.getText();
        k = suggestionsinfo.getSequence();
        i1 = 0;
_L7:
        if(i1 >= mLength) goto _L2; else goto _L1
_L1:
        SpellCheckSpan spellcheckspan;
        if(k != mIds[i1])
            continue; /* Loop/switch isn't completed */
        int j1 = suggestionsinfo.getSuggestionsAttributes();
        boolean flag;
        if((j1 & 1) > 0)
            flag = true;
        else
            flag = false;
        if((j1 & 2) > 0)
            j1 = 1;
        else
            j1 = 0;
        spellcheckspan = mSpellCheckSpans[i1];
        if(flag || !j1) goto _L4; else goto _L3
_L3:
        createMisspelledSuggestionSpan(editable, suggestionsinfo, spellcheckspan, i, j);
_L6:
        return spellcheckspan;
_L4:
        if(mIsSentenceSpellCheckSupported)
        {
            int l = editable.getSpanStart(spellcheckspan);
            i1 = editable.getSpanEnd(spellcheckspan);
            if(i != -1 && j != -1)
            {
                i = l + i;
                j = i + j;
            } else
            {
                i = l;
                j = i1;
            }
            if(l >= 0 && i1 > l && j > i)
            {
                Long long1 = Long.valueOf(TextUtils.packRangeInLong(i, j));
                suggestionsinfo = (SuggestionSpan)mSuggestionSpanCache.get(long1);
                if(suggestionsinfo != null)
                {
                    editable.removeSpan(suggestionsinfo);
                    mSuggestionSpanCache.remove(long1);
                }
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
        i1++;
          goto _L7
_L2:
        return null;
    }

    private void resetSession()
    {
        closeSession();
        mTextServicesManager = (TextServicesManager)mTextView.getContext().getSystemService("textservices");
        break MISSING_BLOCK_LABEL_24;
        int i;
        if(!mTextServicesManager.isSpellCheckerEnabled() || mCurrentLocale == null || mTextServicesManager.getCurrentSpellCheckerSubtype(true) == null)
        {
            mSpellCheckerSession = null;
        } else
        {
            mSpellCheckerSession = mTextServicesManager.newSpellCheckerSession(null, mCurrentLocale, this, false);
            mIsSentenceSpellCheckSupported = true;
        }
        for(i = 0; i < mLength; i++)
            mIds[i] = -1;

        break MISSING_BLOCK_LABEL_106;
        mLength = 0;
        mTextView.removeMisspelledSpans((Editable)mTextView.getText());
        mSuggestionSpanCache.evictAll();
        return;
    }

    private void scheduleNewSpellCheck()
    {
        if(mSpellRunnable == null)
            mSpellRunnable = new Runnable() {

                public void run()
                {
                    int i = SpellChecker._2D_get5(SpellChecker.this).length;
                    int j = 0;
                    do
                    {
label0:
                        {
                            if(j < i)
                            {
                                SpellParser spellparser = SpellChecker._2D_get5(SpellChecker.this)[j];
                                if(spellparser.isFinished())
                                    break label0;
                                spellparser.parse();
                            }
                            return;
                        }
                        j++;
                    } while(true);
                }

                final SpellChecker this$0;

            
            {
                this$0 = SpellChecker.this;
                super();
            }
            }
;
        else
            mTextView.removeCallbacks(mSpellRunnable);
        mTextView.postDelayed(mSpellRunnable, 400L);
    }

    private void setLocale(Locale locale)
    {
        mCurrentLocale = locale;
        resetSession();
        if(locale != null)
            mWordIterator = new WordIterator(locale);
        mTextView.onLocaleChanged();
    }

    private void spellCheck()
    {
        if(mSpellCheckerSession == null)
            return;
        Editable editable = (Editable)mTextView.getText();
        int i = Selection.getSelectionStart(editable);
        int j = Selection.getSelectionEnd(editable);
        TextInfo atextinfo1[] = new TextInfo[mLength];
        int k = 0;
        int l = 0;
        while(l < mLength) 
        {
            SpellCheckSpan spellcheckspan = mSpellCheckSpans[l];
            int i1 = k;
            if(mIds[l] >= 0)
                if(spellcheckspan.isSpellCheckInProgress())
                {
                    i1 = k;
                } else
                {
                    int j1 = editable.getSpanStart(spellcheckspan);
                    int k1 = editable.getSpanEnd(spellcheckspan);
                    boolean flag;
                    if(i == k1 + 1 && WordIterator.isMidWordPunctuation(mCurrentLocale, Character.codePointBefore(editable, k1 + 1)))
                        flag = false;
                    else
                    if(mIsSentenceSpellCheckSupported)
                    {
                        if(j <= j1 || i > k1)
                            flag = true;
                        else
                            flag = false;
                    } else
                    if(j < j1 || i > k1)
                        flag = true;
                    else
                        flag = false;
                    i1 = k;
                    if(j1 >= 0)
                    {
                        i1 = k;
                        if(k1 > j1)
                        {
                            i1 = k;
                            if(flag)
                            {
                                spellcheckspan.setSpellCheckInProgress(true);
                                atextinfo1[k] = new TextInfo(editable, j1, k1, mCookie, mIds[l]);
                                i1 = k + 1;
                            }
                        }
                    }
                }
            l++;
            k = i1;
        }
        if(k > 0)
        {
            TextInfo atextinfo[] = atextinfo1;
            if(k < atextinfo1.length)
            {
                atextinfo = new TextInfo[k];
                System.arraycopy(atextinfo1, 0, atextinfo, 0, k);
            }
            if(mIsSentenceSpellCheckSupported)
                mSpellCheckerSession.getSentenceSuggestions(atextinfo, 5);
            else
                mSpellCheckerSession.getSuggestions(atextinfo, 5, false);
        }
    }

    public void closeSession()
    {
        if(mSpellCheckerSession != null)
            mSpellCheckerSession.close();
        int i = mSpellParsers.length;
        for(int j = 0; j < i; j++)
            mSpellParsers[j].stop();

        if(mSpellRunnable != null)
            mTextView.removeCallbacks(mSpellRunnable);
    }

    public void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[])
    {
        Editable editable = (Editable)mTextView.getText();
        int i = 0;
        while(i < asentencesuggestionsinfo.length) 
        {
            SentenceSuggestionsInfo sentencesuggestionsinfo = asentencesuggestionsinfo[i];
            if(sentencesuggestionsinfo != null)
            {
                SuggestionsInfo suggestionsinfo = null;
                int j = 0;
                while(j < sentencesuggestionsinfo.getSuggestionsCount()) 
                {
                    Object obj = sentencesuggestionsinfo.getSuggestionsInfoAt(j);
                    if(obj == null)
                    {
                        obj = suggestionsinfo;
                    } else
                    {
                        SpellCheckSpan spellcheckspan = onGetSuggestionsInternal(((SuggestionsInfo) (obj)), sentencesuggestionsinfo.getOffsetAt(j), sentencesuggestionsinfo.getLengthAt(j));
                        obj = suggestionsinfo;
                        if(suggestionsinfo == null)
                        {
                            obj = suggestionsinfo;
                            if(spellcheckspan != null)
                                obj = spellcheckspan;
                        }
                    }
                    j++;
                    suggestionsinfo = ((SuggestionsInfo) (obj));
                }
                if(suggestionsinfo != null)
                    editable.removeSpan(suggestionsinfo);
            }
            i++;
        }
        scheduleNewSpellCheck();
    }

    public void onGetSuggestions(SuggestionsInfo asuggestionsinfo[])
    {
        Editable editable = (Editable)mTextView.getText();
        for(int i = 0; i < asuggestionsinfo.length; i++)
        {
            SpellCheckSpan spellcheckspan = onGetSuggestionsInternal(asuggestionsinfo[i], -1, -1);
            if(spellcheckspan != null)
                editable.removeSpan(spellcheckspan);
        }

        scheduleNewSpellCheck();
    }

    public void onSelectionChanged()
    {
        spellCheck();
    }

    public void onSpellCheckSpanRemoved(SpellCheckSpan spellcheckspan)
    {
        for(int i = 0; i < mLength; i++)
            if(mSpellCheckSpans[i] == spellcheckspan)
            {
                mIds[i] = -1;
                return;
            }

    }

    public void spellCheck(int i, int j)
    {
        Locale locale;
        boolean flag;
        locale = mTextView.getSpellCheckerLocale();
        flag = isSessionActive();
        break MISSING_BLOCK_LABEL_14;
        int k;
        int l;
        if(locale == null || mCurrentLocale == null || mCurrentLocale.equals(locale) ^ true)
        {
            setLocale(locale);
            k = 0;
            l = mTextView.getText().length();
        } else
        {
            k = i;
            l = j;
            if(flag != mTextServicesManager.isSpellCheckerEnabled())
            {
                resetSession();
                k = i;
                l = j;
            }
        }
        if(!flag)
            return;
        break MISSING_BLOCK_LABEL_97;
        j = mSpellParsers.length;
        for(i = 0; i < j; i++)
        {
            SpellParser spellparser = mSpellParsers[i];
            if(spellparser.isFinished())
            {
                spellparser.parse(k, l);
                return;
            }
        }

        SpellParser aspellparser[] = new SpellParser[j + 1];
        System.arraycopy(mSpellParsers, 0, aspellparser, 0, j);
        mSpellParsers = aspellparser;
        SpellParser spellparser1 = new SpellParser(null);
        mSpellParsers[j] = spellparser1;
        spellparser1.parse(k, l);
        return;
    }

    public static final int AVERAGE_WORD_LENGTH = 7;
    private static final boolean DBG = false;
    public static final int MAX_NUMBER_OF_WORDS = 50;
    private static final int MIN_SENTENCE_LENGTH = 50;
    private static final int SPELL_PAUSE_DURATION = 400;
    private static final int SUGGESTION_SPAN_CACHE_SIZE = 10;
    private static final String TAG = android/widget/SpellChecker.getSimpleName();
    private static final int USE_SPAN_RANGE = -1;
    public static final int WORD_ITERATOR_INTERVAL = 350;
    final int mCookie = hashCode();
    private Locale mCurrentLocale;
    private int mIds[];
    private boolean mIsSentenceSpellCheckSupported;
    private int mLength;
    private int mSpanSequenceCounter;
    private SpellCheckSpan mSpellCheckSpans[];
    SpellCheckerSession mSpellCheckerSession;
    private SpellParser mSpellParsers[];
    private Runnable mSpellRunnable;
    private final LruCache mSuggestionSpanCache = new LruCache(10);
    private TextServicesManager mTextServicesManager;
    private final TextView mTextView;
    private WordIterator mWordIterator;

}
