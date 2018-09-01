// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.app.ActivityManager;
import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.*;
import android.telephony.*;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package com.android.internal.telephony:
//            CallerInfo

public class CallerInfoAsyncQuery
{
    private class CallerInfoAsyncQueryHandler extends AsyncQueryHandler
    {

        static CallerInfo _2D_get0(CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler)
        {
            return callerinfoasyncqueryhandler.mCallerInfo;
        }

        static Context _2D_get1(CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler)
        {
            return callerinfoasyncqueryhandler.mContext;
        }

        static CallerInfo _2D_set0(CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler, CallerInfo callerinfo)
        {
            callerinfoasyncqueryhandler.mCallerInfo = callerinfo;
            return callerinfo;
        }

        static Context _2D_set1(CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler, Context context)
        {
            callerinfoasyncqueryhandler.mContext = context;
            return context;
        }

        static Uri _2D_set2(CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler, Uri uri)
        {
            callerinfoasyncqueryhandler.mQueryUri = uri;
            return uri;
        }

        protected Handler createHandler(Looper looper)
        {
            return new CallerInfoWorkerHandler(looper);
        }

        protected void onQueryComplete(int i, final Object cw, Cursor cursor)
        {
            Rlog.d("CallerInfoAsyncQuery", (new StringBuilder()).append("##### onQueryComplete() #####   query complete for token: ").append(i).toString());
            cw = (CookieWrapper)cw;
            if(cw == null)
            {
                Rlog.i("CallerInfoAsyncQuery", "Cookie is null, ignoring onQueryComplete() request.");
                if(cursor != null)
                    cursor.close();
                return;
            }
            if(((CookieWrapper) (cw)).event == 3)
            {
                for(cw = mPendingListenerCallbacks.iterator(); ((Iterator) (cw)).hasNext(); ((Runnable)((Iterator) (cw)).next()).run());
                mPendingListenerCallbacks.clear();
                CallerInfoAsyncQuery._2D_wrap1(CallerInfoAsyncQuery.this);
                if(cursor != null)
                    cursor.close();
                return;
            }
            if(((CookieWrapper) (cw)).event == 6)
            {
                if(mCallerInfo != null)
                    mCallerInfo.geoDescription = ((CookieWrapper) (cw)).geoDescription;
                CookieWrapper cookiewrapper = new CookieWrapper(null);
                cookiewrapper.event = 3;
                startQuery(i, cookiewrapper, null, null, null, null, null);
            }
            if(mCallerInfo != null) goto _L2; else goto _L1
_L1:
            if(mContext == null || mQueryUri == null)
                throw new QueryPoolException("Bad context or query uri, or CallerInfoAsyncQuery already released.");
            if(((CookieWrapper) (cw)).event != 4) goto _L4; else goto _L3
_L3:
            mCallerInfo = (new CallerInfo()).markAsEmergency(mContext);
_L6:
            CookieWrapper cookiewrapper1 = new CookieWrapper(null);
            cookiewrapper1.event = 3;
            startQuery(i, cookiewrapper1, null, null, null, null, null);
_L2:
            CallerInfo callerinfo;
            if(((CookieWrapper) (cw)).listener != null)
                mPendingListenerCallbacks.add(i. new Runnable() {

                    public void run()
                    {
                        cw.listener.onQueryComplete(token, cw.cookie, CallerInfoAsyncQueryHandler._2D_get0(CallerInfoAsyncQueryHandler.this));
                    }

                    final CallerInfoAsyncQueryHandler this$1;
                    final CookieWrapper val$cw;
                    final int val$token;

            
            {
                this$1 = final_callerinfoasyncqueryhandler;
                cw = cookiewrapper;
                token = I.this;
                super();
            }
                }
);
            else
                Rlog.w("CallerInfoAsyncQuery", "There is no listener to notify for this query.");
            if(cursor != null)
                cursor.close();
            return;
_L4:
            if(((CookieWrapper) (cw)).event != 5)
                break; /* Loop/switch isn't completed */
            mCallerInfo = (new CallerInfo()).markAsVoiceMail(((CookieWrapper) (cw)).subId);
            if(true) goto _L6; else goto _L5
_L5:
            mCallerInfo = CallerInfo.getCallerInfo(mContext, mQueryUri, cursor);
            callerinfo = CallerInfo.doSecondaryLookupIfNecessary(mContext, ((CookieWrapper) (cw)).number, mCallerInfo);
            if(callerinfo != mCallerInfo)
                mCallerInfo = callerinfo;
            if(!TextUtils.isEmpty(((CookieWrapper) (cw)).number))
                mCallerInfo.phoneNumber = PhoneNumberUtils.formatNumber(((CookieWrapper) (cw)).number, mCallerInfo.normalizedNumber, CallerInfo.getCurrentCountryIso(mContext));
            if(TextUtils.isEmpty(mCallerInfo.name))
            {
                cw.event = 6;
                startQuery(i, cw, null, null, null, null, null);
                return;
            }
            if(true) goto _L6; else goto _L7
_L7:
        }

        private CallerInfo mCallerInfo;
        private Context mContext;
        private List mPendingListenerCallbacks;
        private Uri mQueryUri;
        final CallerInfoAsyncQuery this$0;

        private CallerInfoAsyncQueryHandler(Context context)
        {
            this$0 = CallerInfoAsyncQuery.this;
            super(CallerInfoAsyncQuery.getCurrentProfileContentResolver(context));
            mPendingListenerCallbacks = new ArrayList();
            mContext = context;
        }

        CallerInfoAsyncQueryHandler(Context context, CallerInfoAsyncQueryHandler callerinfoasyncqueryhandler)
        {
            this(context);
        }
    }

    protected class CallerInfoAsyncQueryHandler.CallerInfoWorkerHandler extends android.content.AsyncQueryHandler.WorkerHandler
    {

        private void handleGeoDescription(Message message)
        {
            android.content.AsyncQueryHandler.WorkerArgs workerargs = (android.content.AsyncQueryHandler.WorkerArgs)message.obj;
            Object obj = (CookieWrapper)workerargs.cookie;
            if(!TextUtils.isEmpty(((CookieWrapper) (obj)).number) && ((CookieWrapper) (obj)).cookie != null && CallerInfoAsyncQueryHandler._2D_get1(CallerInfoAsyncQueryHandler.this) != null)
            {
                SystemClock.elapsedRealtime();
                obj.geoDescription = CallerInfo.getGeoDescription(CallerInfoAsyncQueryHandler._2D_get1(CallerInfoAsyncQueryHandler.this), ((CookieWrapper) (obj)).number);
                SystemClock.elapsedRealtime();
            }
            obj = workerargs.handler.obtainMessage(message.what);
            obj.obj = workerargs;
            obj.arg1 = message.arg1;
            ((Message) (obj)).sendToTarget();
        }

        public void handleMessage(Message message)
        {
            android.content.AsyncQueryHandler.WorkerArgs workerargs;
            CookieWrapper cookiewrapper;
            workerargs = (android.content.AsyncQueryHandler.WorkerArgs)message.obj;
            cookiewrapper = (CookieWrapper)workerargs.cookie;
            if(cookiewrapper != null) goto _L2; else goto _L1
_L1:
            Rlog.i("CallerInfoAsyncQuery", (new StringBuilder()).append("Unexpected command (CookieWrapper is null): ").append(message.what).append(" ignored by CallerInfoWorkerHandler, passing onto parent.").toString());
            super.handleMessage(message);
_L4:
            return;
_L2:
            Rlog.d("CallerInfoAsyncQuery", (new StringBuilder()).append("Processing event: ").append(cookiewrapper.event).append(" token (arg1): ").append(message.arg1).append(" command: ").append(message.what).append(" query URI: ").append(CallerInfoAsyncQuery._2D_wrap0(workerargs.uri)).toString());
            switch(cookiewrapper.event)
            {
            case 1: // '\001'
                super.handleMessage(message);
                break;

            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
                Message message1 = workerargs.handler.obtainMessage(message.what);
                message1.obj = workerargs;
                message1.arg1 = message.arg1;
                message1.sendToTarget();
                break;

            case 6: // '\006'
                handleGeoDescription(message);
                break;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final CallerInfoAsyncQueryHandler this$1;

        public CallerInfoAsyncQueryHandler.CallerInfoWorkerHandler(Looper looper)
        {
            this$1 = CallerInfoAsyncQueryHandler.this;
            super(CallerInfoAsyncQueryHandler.this, looper);
        }
    }

    private static final class CookieWrapper
    {

        public Object cookie;
        public int event;
        public String geoDescription;
        public OnQueryCompleteListener listener;
        public String number;
        public int subId;

        private CookieWrapper()
        {
        }

        CookieWrapper(CookieWrapper cookiewrapper)
        {
            this();
        }
    }

    public static interface OnQueryCompleteListener
    {

        public abstract void onQueryComplete(int i, Object obj, CallerInfo callerinfo);
    }

    public static class QueryPoolException extends SQLException
    {

        public QueryPoolException(String s)
        {
            super(s);
        }
    }


    static String _2D_wrap0(Uri uri)
    {
        return sanitizeUriToString(uri);
    }

    static void _2D_wrap1(CallerInfoAsyncQuery callerinfoasyncquery)
    {
        callerinfoasyncquery.release();
    }

    private CallerInfoAsyncQuery()
    {
    }

    private void allocate(Context context, Uri uri)
    {
        if(context == null || uri == null)
        {
            throw new QueryPoolException("Bad context or query uri.");
        } else
        {
            mHandler = new CallerInfoAsyncQueryHandler(context, null);
            CallerInfoAsyncQueryHandler._2D_set2(mHandler, uri);
            return;
        }
    }

    static ContentResolver getCurrentProfileContentResolver(Context context)
    {
        int i;
        i = ActivityManager.getCurrentUser();
        if(UserManager.get(context).getUserHandle() == i)
            break MISSING_BLOCK_LABEL_52;
        Object obj;
        String s = context.getPackageName();
        obj = JVM INSTR new #102 <Class UserHandle>;
        ((UserHandle) (obj)).UserHandle(i);
        obj = context.createPackageContextAsUser(s, 0, ((UserHandle) (obj))).getContentResolver();
        return ((ContentResolver) (obj));
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        Rlog.e("CallerInfoAsyncQuery", "Can't find self package", namenotfoundexception);
        return context.getContentResolver();
    }

    private void release()
    {
        CallerInfoAsyncQueryHandler._2D_set1(mHandler, null);
        CallerInfoAsyncQueryHandler._2D_set2(mHandler, null);
        CallerInfoAsyncQueryHandler._2D_set0(mHandler, null);
        mHandler = null;
    }

    private static String sanitizeUriToString(Uri uri)
    {
        if(uri != null)
        {
            uri = uri.toString();
            int i = uri.lastIndexOf('/');
            if(i > 0)
                return (new StringBuilder()).append(uri.substring(0, i)).append("/xxxxxxx").toString();
            else
                return uri;
        } else
        {
            return "";
        }
    }

    public static CallerInfoAsyncQuery startQuery(int i, Context context, Uri uri, OnQueryCompleteListener onquerycompletelistener, Object obj)
    {
        CallerInfoAsyncQuery callerinfoasyncquery = new CallerInfoAsyncQuery();
        callerinfoasyncquery.allocate(context, uri);
        context = new CookieWrapper(null);
        context.listener = onquerycompletelistener;
        context.cookie = obj;
        context.event = 1;
        callerinfoasyncquery.mHandler.startQuery(i, context, uri, null, null, null, null);
        return callerinfoasyncquery;
    }

    public static CallerInfoAsyncQuery startQuery(int i, Context context, String s, OnQueryCompleteListener onquerycompletelistener, Object obj)
    {
        return startQuery(i, context, s, onquerycompletelistener, obj, SubscriptionManager.getDefaultSubscriptionId());
    }

    public static CallerInfoAsyncQuery startQuery(int i, Context context, String s, OnQueryCompleteListener onquerycompletelistener, Object obj, int j)
    {
        Uri uri = android.provider.ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI.buildUpon().appendPath(s).appendQueryParameter("sip", String.valueOf(PhoneNumberUtils.isUriNumber(s))).build();
        CallerInfoAsyncQuery callerinfoasyncquery = new CallerInfoAsyncQuery();
        callerinfoasyncquery.allocate(context, uri);
        CookieWrapper cookiewrapper = new CookieWrapper(null);
        cookiewrapper.listener = onquerycompletelistener;
        cookiewrapper.cookie = obj;
        cookiewrapper.number = s;
        cookiewrapper.subId = j;
        if(PhoneNumberUtils.isLocalEmergencyNumber(context, s))
            cookiewrapper.event = 4;
        else
        if(PhoneNumberUtils.isVoiceMailNumber(context, j, s))
            cookiewrapper.event = 5;
        else
            cookiewrapper.event = 1;
        callerinfoasyncquery.mHandler.startQuery(i, cookiewrapper, uri, null, null, null, null);
        return callerinfoasyncquery;
    }

    public void addQueryListener(int i, OnQueryCompleteListener onquerycompletelistener, Object obj)
    {
        CookieWrapper cookiewrapper = new CookieWrapper(null);
        cookiewrapper.listener = onquerycompletelistener;
        cookiewrapper.cookie = obj;
        cookiewrapper.event = 2;
        mHandler.startQuery(i, cookiewrapper, null, null, null, null, null);
    }

    private static final boolean DBG = false;
    private static final boolean ENABLE_UNKNOWN_NUMBER_GEO_DESCRIPTION = true;
    private static final int EVENT_ADD_LISTENER = 2;
    private static final int EVENT_EMERGENCY_NUMBER = 4;
    private static final int EVENT_END_OF_QUEUE = 3;
    private static final int EVENT_GET_GEO_DESCRIPTION = 6;
    private static final int EVENT_NEW_QUERY = 1;
    private static final int EVENT_VOICEMAIL_NUMBER = 5;
    private static final String LOG_TAG = "CallerInfoAsyncQuery";
    private CallerInfoAsyncQueryHandler mHandler;
}
