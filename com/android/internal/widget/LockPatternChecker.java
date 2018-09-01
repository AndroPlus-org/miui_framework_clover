// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.internal.widget:
//            LockPatternUtils

public final class LockPatternChecker
{
    public static interface OnCheckCallback
    {

        public void onCancelled()
        {
        }

        public abstract void onChecked(boolean flag, int i);

        public void onEarlyMatched()
        {
        }
    }

    public static interface OnVerifyCallback
    {

        public abstract void onVerified(byte abyte0[], int i);
    }


    public LockPatternChecker()
    {
    }

    public static AsyncTask checkPassword(LockPatternUtils lockpatternutils, String s, int i, OnCheckCallback oncheckcallback)
    {
        lockpatternutils = new AsyncTask(lockpatternutils, s, i, oncheckcallback) {

            static void _2D_com_android_internal_widget_LockPatternChecker$5_2D_mthref_2D_0(OnCheckCallback oncheckcallback1)
            {
                oncheckcallback1.onEarlyMatched();
            }

            protected transient Boolean doInBackground(Void avoid[])
            {
                boolean flag;
                try
                {
                    LockPatternUtils lockpatternutils1 = utils;
                    String s1 = password;
                    int j = userId;
                    OnCheckCallback oncheckcallback1 = callback;
                    oncheckcallback1.getClass();
                    avoid = JVM INSTR new #52  <Class _$Lambda$E2sSlgjiM2w1MdavtCJi6YeQRgk>;
                    avoid._.Lambda.E2sSlgjiM2w1MdavtCJi6YeQRgk((byte)1, oncheckcallback1);
                    flag = lockpatternutils1.checkPassword(s1, j, avoid);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mThrottleTimeout = avoid.getTimeoutMs();
                    return Boolean.valueOf(false);
                }
                return Boolean.valueOf(flag);
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected void onCancelled()
            {
                callback.onCancelled();
            }

            protected void onPostExecute(Boolean boolean1)
            {
                callback.onChecked(boolean1.booleanValue(), mThrottleTimeout);
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Boolean)obj);
            }

            private int mThrottleTimeout;
            final OnCheckCallback val$callback;
            final String val$password;
            final int val$userId;
            final LockPatternUtils val$utils;

            
            {
                utils = lockpatternutils;
                password = s;
                userId = i;
                callback = oncheckcallback;
                super();
            }
        }
;
        lockpatternutils.execute(new Void[0]);
        return lockpatternutils;
    }

    public static AsyncTask checkPattern(LockPatternUtils lockpatternutils, List list, int i, OnCheckCallback oncheckcallback)
    {
        lockpatternutils = new AsyncTask(list, lockpatternutils, i, oncheckcallback) {

            static void _2D_com_android_internal_widget_LockPatternChecker$2_2D_mthref_2D_0(OnCheckCallback oncheckcallback1)
            {
                oncheckcallback1.onEarlyMatched();
            }

            protected transient Boolean doInBackground(Void avoid[])
            {
                boolean flag;
                try
                {
                    LockPatternUtils lockpatternutils1 = utils;
                    List list1 = patternCopy;
                    int j = userId;
                    avoid = callback;
                    avoid.getClass();
                    _.Lambda.E2sSlgjiM2w1MdavtCJi6YeQRgk e2sslgjim2w1mdavtcji6yeqrgk = JVM INSTR new #56  <Class _$Lambda$E2sSlgjiM2w1MdavtCJi6YeQRgk>;
                    e2sslgjim2w1mdavtcji6yeqrgk._.Lambda.E2sSlgjiM2w1MdavtCJi6YeQRgk((byte)0, avoid);
                    flag = lockpatternutils1.checkPattern(list1, j, e2sslgjim2w1mdavtcji6yeqrgk);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mThrottleTimeout = avoid.getTimeoutMs();
                    return Boolean.valueOf(false);
                }
                return Boolean.valueOf(flag);
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected void onCancelled()
            {
                callback.onCancelled();
            }

            protected void onPostExecute(Boolean boolean1)
            {
                callback.onChecked(boolean1.booleanValue(), mThrottleTimeout);
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Boolean)obj);
            }

            protected void onPreExecute()
            {
                patternCopy = new ArrayList(pattern);
            }

            private int mThrottleTimeout;
            private List patternCopy;
            final OnCheckCallback val$callback;
            final List val$pattern;
            final int val$userId;
            final LockPatternUtils val$utils;

            
            {
                pattern = list;
                utils = lockpatternutils;
                userId = i;
                callback = oncheckcallback;
                super();
            }
        }
;
        lockpatternutils.execute(new Void[0]);
        return lockpatternutils;
    }

    public static AsyncTask verifyPassword(LockPatternUtils lockpatternutils, String s, long l, int i, OnVerifyCallback onverifycallback)
    {
        lockpatternutils = new AsyncTask(lockpatternutils, s, l, i, onverifycallback) {

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected transient byte[] doInBackground(Void avoid[])
            {
                try
                {
                    avoid = utils.verifyPassword(password, challenge, userId);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mThrottleTimeout = avoid.getTimeoutMs();
                    return null;
                }
                return avoid;
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((byte[])obj);
            }

            protected void onPostExecute(byte abyte0[])
            {
                callback.onVerified(abyte0, mThrottleTimeout);
            }

            private int mThrottleTimeout;
            final OnVerifyCallback val$callback;
            final long val$challenge;
            final String val$password;
            final int val$userId;
            final LockPatternUtils val$utils;

            
            {
                utils = lockpatternutils;
                password = s;
                challenge = l;
                userId = i;
                callback = onverifycallback;
                super();
            }
        }
;
        lockpatternutils.execute(new Void[0]);
        return lockpatternutils;
    }

    public static AsyncTask verifyPattern(LockPatternUtils lockpatternutils, List list, long l, int i, OnVerifyCallback onverifycallback)
    {
        lockpatternutils = new AsyncTask(list, lockpatternutils, l, i, onverifycallback) {

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected transient byte[] doInBackground(Void avoid[])
            {
                try
                {
                    avoid = utils.verifyPattern(patternCopy, challenge, userId);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mThrottleTimeout = avoid.getTimeoutMs();
                    return null;
                }
                return avoid;
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((byte[])obj);
            }

            protected void onPostExecute(byte abyte0[])
            {
                callback.onVerified(abyte0, mThrottleTimeout);
            }

            protected void onPreExecute()
            {
                patternCopy = new ArrayList(pattern);
            }

            private int mThrottleTimeout;
            private List patternCopy;
            final OnVerifyCallback val$callback;
            final long val$challenge;
            final List val$pattern;
            final int val$userId;
            final LockPatternUtils val$utils;

            
            {
                pattern = list;
                utils = lockpatternutils;
                challenge = l;
                userId = i;
                callback = onverifycallback;
                super();
            }
        }
;
        lockpatternutils.execute(new Void[0]);
        return lockpatternutils;
    }

    public static AsyncTask verifyTiedProfileChallenge(LockPatternUtils lockpatternutils, String s, boolean flag, long l, int i, OnVerifyCallback onverifycallback)
    {
        lockpatternutils = new AsyncTask(lockpatternutils, s, flag, l, i, onverifycallback) {

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected transient byte[] doInBackground(Void avoid[])
            {
                try
                {
                    avoid = utils.verifyTiedProfileChallenge(password, isPattern, challenge, userId);
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    mThrottleTimeout = avoid.getTimeoutMs();
                    return null;
                }
                return avoid;
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((byte[])obj);
            }

            protected void onPostExecute(byte abyte0[])
            {
                callback.onVerified(abyte0, mThrottleTimeout);
            }

            private int mThrottleTimeout;
            final OnVerifyCallback val$callback;
            final long val$challenge;
            final boolean val$isPattern;
            final String val$password;
            final int val$userId;
            final LockPatternUtils val$utils;

            
            {
                utils = lockpatternutils;
                password = s;
                isPattern = flag;
                challenge = l;
                userId = i;
                callback = onverifycallback;
                super();
            }
        }
;
        lockpatternutils.execute(new Void[0]);
        return lockpatternutils;
    }
}
