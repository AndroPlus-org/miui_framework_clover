// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.*;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.ParceledListSlice;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.*;
import android.util.*;
import com.android.internal.os.SomeArgs;
import java.util.*;

// Referenced classes of package android.service.notification:
//            StatusBarNotification, NotificationRankingUpdate, IStatusBarNotificationHolder

public abstract class NotificationListenerService extends Service
{
    private final class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(!NotificationListenerService._2D_get1(NotificationListenerService.this))
                return;
            message.what;
            JVM INSTR tableswitch 1 8: default 60
        //                       1 61
        //                       2 101
        //                       3 155
        //                       4 165
        //                       5 184
        //                       6 202
        //                       7 220
        //                       8 281;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            StatusBarNotification statusbarnotification = (StatusBarNotification)((SomeArgs) (message)).arg1;
            RankingMap rankingmap = (RankingMap)((SomeArgs) (message)).arg2;
            message.recycle();
            onNotificationPosted(statusbarnotification, rankingmap);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (SomeArgs)message.obj;
            StatusBarNotification statusbarnotification1 = (StatusBarNotification)((SomeArgs) (message)).arg1;
            RankingMap rankingmap1 = (RankingMap)((SomeArgs) (message)).arg2;
            int i = ((Integer)((SomeArgs) (message)).arg3).intValue();
            message.recycle();
            onNotificationRemoved(statusbarnotification1, rankingmap1, i);
            continue; /* Loop/switch isn't completed */
_L4:
            onListenerConnected();
            continue; /* Loop/switch isn't completed */
_L5:
            message = (RankingMap)message.obj;
            onNotificationRankingUpdate(message);
            continue; /* Loop/switch isn't completed */
_L6:
            int j = message.arg1;
            onListenerHintsChanged(j);
            continue; /* Loop/switch isn't completed */
_L7:
            int k = message.arg1;
            onInterruptionFilterChanged(k);
            continue; /* Loop/switch isn't completed */
_L8:
            SomeArgs someargs = (SomeArgs)message.obj;
            String s = (String)someargs.arg1;
            message = (UserHandle)someargs.arg2;
            NotificationChannel notificationchannel = (NotificationChannel)someargs.arg3;
            int l = ((Integer)someargs.arg4).intValue();
            onNotificationChannelModified(s, message, notificationchannel, l);
            continue; /* Loop/switch isn't completed */
_L9:
            SomeArgs someargs1 = (SomeArgs)message.obj;
            message = (String)someargs1.arg1;
            UserHandle userhandle = (UserHandle)someargs1.arg2;
            NotificationChannelGroup notificationchannelgroup = (NotificationChannelGroup)someargs1.arg3;
            int i1 = ((Integer)someargs1.arg4).intValue();
            onNotificationChannelGroupModified(message, userhandle, notificationchannelgroup, i1);
            if(true) goto _L1; else goto _L10
_L10:
        }

        public static final int MSG_ON_INTERRUPTION_FILTER_CHANGED = 6;
        public static final int MSG_ON_LISTENER_CONNECTED = 3;
        public static final int MSG_ON_LISTENER_HINTS_CHANGED = 5;
        public static final int MSG_ON_NOTIFICATION_CHANNEL_GROUP_MODIFIED = 8;
        public static final int MSG_ON_NOTIFICATION_CHANNEL_MODIFIED = 7;
        public static final int MSG_ON_NOTIFICATION_POSTED = 1;
        public static final int MSG_ON_NOTIFICATION_RANKING_UPDATE = 4;
        public static final int MSG_ON_NOTIFICATION_REMOVED = 2;
        final NotificationListenerService this$0;

        public MyHandler(Looper looper)
        {
            this$0 = NotificationListenerService.this;
            super(looper, null, false);
        }
    }

    protected class NotificationListenerWrapper extends INotificationListener.Stub
    {

        public void onInterruptionFilterChanged(int i)
            throws RemoteException
        {
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(6, i, 0).sendToTarget();
        }

        public void onListenerConnected(NotificationRankingUpdate notificationrankingupdate)
        {
            Object obj = NotificationListenerService._2D_get3(NotificationListenerService.this);
            obj;
            JVM INSTR monitorenter ;
            applyUpdateLocked(notificationrankingupdate);
            obj;
            JVM INSTR monitorexit ;
            NotificationListenerService._2D_set0(NotificationListenerService.this, true);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(3).sendToTarget();
            return;
            notificationrankingupdate;
            throw notificationrankingupdate;
        }

        public void onListenerHintsChanged(int i)
            throws RemoteException
        {
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(5, i, 0).sendToTarget();
        }

        public void onNotificationChannelGroupModification(String s, UserHandle userhandle, NotificationChannelGroup notificationchannelgroup, int i)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = userhandle;
            someargs.arg3 = notificationchannelgroup;
            someargs.arg4 = Integer.valueOf(i);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(8, someargs).sendToTarget();
        }

        public void onNotificationChannelModification(String s, UserHandle userhandle, NotificationChannel notificationchannel, int i)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = userhandle;
            someargs.arg3 = notificationchannel;
            someargs.arg4 = Integer.valueOf(i);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(7, someargs).sendToTarget();
        }

        public void onNotificationEnqueued(IStatusBarNotificationHolder istatusbarnotificationholder)
            throws RemoteException
        {
        }

        public void onNotificationPosted(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate)
        {
            Object obj;
            try
            {
                istatusbarnotificationholder = istatusbarnotificationholder.get();
            }
            // Misplaced declaration of an exception variable
            catch(IStatusBarNotificationHolder istatusbarnotificationholder)
            {
                Log.w(NotificationListenerService._2D_get0(NotificationListenerService.this), "onNotificationPosted: Error receiving StatusBarNotification", istatusbarnotificationholder);
                return;
            }
            try
            {
                NotificationListenerService._2D_wrap0(NotificationListenerService.this, istatusbarnotificationholder.getNotification());
                NotificationListenerService._2D_wrap1(NotificationListenerService.this, istatusbarnotificationholder.getNotification());
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w(NotificationListenerService._2D_get0(NotificationListenerService.this), (new StringBuilder()).append("onNotificationPosted: can't rebuild notification from ").append(istatusbarnotificationholder.getPackageName()).toString());
                istatusbarnotificationholder = null;
            }
            obj = NotificationListenerService._2D_get3(NotificationListenerService.this);
            obj;
            JVM INSTR monitorenter ;
            applyUpdateLocked(notificationrankingupdate);
            if(istatusbarnotificationholder == null)
                break MISSING_BLOCK_LABEL_144;
            notificationrankingupdate = SomeArgs.obtain();
            notificationrankingupdate.arg1 = istatusbarnotificationholder;
            notificationrankingupdate.arg2 = NotificationListenerService._2D_get4(NotificationListenerService.this);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(1, notificationrankingupdate).sendToTarget();
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(4, NotificationListenerService._2D_get4(NotificationListenerService.this)).sendToTarget();
              goto _L1
            istatusbarnotificationholder;
            throw istatusbarnotificationholder;
        }

        public void onNotificationRankingUpdate(NotificationRankingUpdate notificationrankingupdate)
            throws RemoteException
        {
            Object obj = NotificationListenerService._2D_get3(NotificationListenerService.this);
            obj;
            JVM INSTR monitorenter ;
            applyUpdateLocked(notificationrankingupdate);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(4, NotificationListenerService._2D_get4(NotificationListenerService.this)).sendToTarget();
            obj;
            JVM INSTR monitorexit ;
            return;
            notificationrankingupdate;
            throw notificationrankingupdate;
        }

        public void onNotificationRemoved(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate, int i)
        {
            StatusBarNotification statusbarnotification;
            try
            {
                statusbarnotification = istatusbarnotificationholder.get();
            }
            // Misplaced declaration of an exception variable
            catch(IStatusBarNotificationHolder istatusbarnotificationholder)
            {
                Log.w(NotificationListenerService._2D_get0(NotificationListenerService.this), "onNotificationRemoved: Error receiving StatusBarNotification", istatusbarnotificationholder);
                return;
            }
            istatusbarnotificationholder = ((IStatusBarNotificationHolder) (NotificationListenerService._2D_get3(NotificationListenerService.this)));
            istatusbarnotificationholder;
            JVM INSTR monitorenter ;
            applyUpdateLocked(notificationrankingupdate);
            notificationrankingupdate = SomeArgs.obtain();
            notificationrankingupdate.arg1 = statusbarnotification;
            notificationrankingupdate.arg2 = NotificationListenerService._2D_get4(NotificationListenerService.this);
            notificationrankingupdate.arg3 = Integer.valueOf(i);
            NotificationListenerService._2D_get2(NotificationListenerService.this).obtainMessage(2, notificationrankingupdate).sendToTarget();
            istatusbarnotificationholder;
            JVM INSTR monitorexit ;
            return;
            notificationrankingupdate;
            throw notificationrankingupdate;
        }

        public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder istatusbarnotificationholder, String s)
            throws RemoteException
        {
        }

        final NotificationListenerService this$0;

        protected NotificationListenerWrapper()
        {
            this$0 = NotificationListenerService.this;
            super();
        }
    }

    public static class Ranking
    {

        static void _2D_wrap0(Ranking ranking, String s, int i, boolean flag, int j, int k, int l, CharSequence charsequence, 
                String s1, NotificationChannel notificationchannel, ArrayList arraylist, ArrayList arraylist1, boolean flag1)
        {
            ranking.populate(s, i, flag, j, k, l, charsequence, s1, notificationchannel, arraylist, arraylist1, flag1);
        }

        public static String importanceToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("UNKNOWN(").append(String.valueOf(i)).append(")").toString();

            case -1000: 
                return "UNSPECIFIED";

            case 0: // '\0'
                return "NONE";

            case 1: // '\001'
                return "MIN";

            case 2: // '\002'
                return "LOW";

            case 3: // '\003'
                return "DEFAULT";

            case 4: // '\004'
            case 5: // '\005'
                return "HIGH";
            }
        }

        private void populate(String s, int i, boolean flag, int j, int k, int l, CharSequence charsequence, 
                String s1, NotificationChannel notificationchannel, ArrayList arraylist, ArrayList arraylist1, boolean flag1)
        {
            mKey = s;
            mRank = i;
            boolean flag2;
            if(l < 2)
                flag2 = true;
            else
                flag2 = false;
            mIsAmbient = flag2;
            mMatchesInterruptionFilter = flag;
            mVisibilityOverride = j;
            mSuppressedVisualEffects = k;
            mImportance = l;
            mImportanceExplanation = charsequence;
            mOverrideGroupKey = s1;
            mChannel = notificationchannel;
            mOverridePeople = arraylist;
            mSnoozeCriteria = arraylist1;
            mShowBadge = flag1;
        }

        public boolean canShowBadge()
        {
            return mShowBadge;
        }

        public List getAdditionalPeople()
        {
            return mOverridePeople;
        }

        public NotificationChannel getChannel()
        {
            return mChannel;
        }

        public int getImportance()
        {
            return mImportance;
        }

        public CharSequence getImportanceExplanation()
        {
            return mImportanceExplanation;
        }

        public String getKey()
        {
            return mKey;
        }

        public String getOverrideGroupKey()
        {
            return mOverrideGroupKey;
        }

        public int getRank()
        {
            return mRank;
        }

        public List getSnoozeCriteria()
        {
            return mSnoozeCriteria;
        }

        public int getSuppressedVisualEffects()
        {
            return mSuppressedVisualEffects;
        }

        public int getVisibilityOverride()
        {
            return mVisibilityOverride;
        }

        public boolean isAmbient()
        {
            return mIsAmbient;
        }

        public boolean matchesInterruptionFilter()
        {
            return mMatchesInterruptionFilter;
        }

        public static final int VISIBILITY_NO_OVERRIDE = -1000;
        private NotificationChannel mChannel;
        private int mImportance;
        private CharSequence mImportanceExplanation;
        private boolean mIsAmbient;
        private String mKey;
        private boolean mMatchesInterruptionFilter;
        private String mOverrideGroupKey;
        private ArrayList mOverridePeople;
        private int mRank;
        private boolean mShowBadge;
        private ArrayList mSnoozeCriteria;
        private int mSuppressedVisualEffects;
        private int mVisibilityOverride;

        public Ranking()
        {
            mRank = -1;
        }
    }

    public static class RankingMap
        implements Parcelable
    {

        private void buildChannelsLocked()
        {
            Bundle bundle = mRankingUpdate.getChannels();
            mChannels = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mChannels.put(s, (NotificationChannel)bundle.getParcelable(s)))
                s = (String)iterator.next();

        }

        private void buildImportanceExplanationLocked()
        {
            Bundle bundle = mRankingUpdate.getImportanceExplanation();
            mImportanceExplanation = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mImportanceExplanation.put(s, bundle.getString(s)))
                s = (String)iterator.next();

        }

        private void buildImportanceLocked()
        {
            String as[] = mRankingUpdate.getOrderedKeys();
            int ai[] = mRankingUpdate.getImportance();
            mImportance = new ArrayMap(as.length);
            for(int i = 0; i < as.length; i++)
            {
                String s = as[i];
                mImportance.put(s, Integer.valueOf(ai[i]));
            }

        }

        private void buildInterceptedSetLocked()
        {
            String as[] = mRankingUpdate.getInterceptedKeys();
            mIntercepted = new ArraySet(as.length);
            Collections.addAll(mIntercepted, as);
        }

        private void buildOverrideGroupKeys()
        {
            Bundle bundle = mRankingUpdate.getOverrideGroupKeys();
            mOverrideGroupKeys = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mOverrideGroupKeys.put(s, bundle.getString(s)))
                s = (String)iterator.next();

        }

        private void buildOverridePeopleLocked()
        {
            Bundle bundle = mRankingUpdate.getOverridePeople();
            mOverridePeople = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mOverridePeople.put(s, bundle.getStringArrayList(s)))
                s = (String)iterator.next();

        }

        private void buildRanksLocked()
        {
            String as[] = mRankingUpdate.getOrderedKeys();
            mRanks = new ArrayMap(as.length);
            for(int i = 0; i < as.length; i++)
            {
                String s = as[i];
                mRanks.put(s, Integer.valueOf(i));
            }

        }

        private void buildShowBadgeLocked()
        {
            Bundle bundle = mRankingUpdate.getShowBadge();
            mShowBadge = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mShowBadge.put(s, Boolean.valueOf(bundle.getBoolean(s))))
                s = (String)iterator.next();

        }

        private void buildSnoozeCriteriaLocked()
        {
            Bundle bundle = mRankingUpdate.getSnoozeCriteria();
            mSnoozeCriteria = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mSnoozeCriteria.put(s, bundle.getParcelableArrayList(s)))
                s = (String)iterator.next();

        }

        private void buildSuppressedVisualEffectsLocked()
        {
            Bundle bundle = mRankingUpdate.getSuppressedVisualEffects();
            mSuppressedVisualEffects = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mSuppressedVisualEffects.put(s, Integer.valueOf(bundle.getInt(s))))
                s = (String)iterator.next();

        }

        private void buildVisibilityOverridesLocked()
        {
            Bundle bundle = mRankingUpdate.getVisibilityOverrides();
            mVisibilityOverrides = new ArrayMap(bundle.size());
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); mVisibilityOverrides.put(s, Integer.valueOf(bundle.getInt(s))))
                s = (String)iterator.next();

        }

        private NotificationChannel getChannel(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mChannels == null)
                buildChannelsLocked();
            this;
            JVM INSTR monitorexit ;
            return (NotificationChannel)mChannels.get(s);
            s;
            throw s;
        }

        private int getImportance(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mImportance == null)
                buildImportanceLocked();
            this;
            JVM INSTR monitorexit ;
            s = (Integer)mImportance.get(s);
            if(s == null)
                return 3;
            else
                return s.intValue();
            s;
            throw s;
        }

        private String getImportanceExplanation(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mImportanceExplanation == null)
                buildImportanceExplanationLocked();
            this;
            JVM INSTR monitorexit ;
            return (String)mImportanceExplanation.get(s);
            s;
            throw s;
        }

        private String getOverrideGroupKey(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mOverrideGroupKeys == null)
                buildOverrideGroupKeys();
            this;
            JVM INSTR monitorexit ;
            return (String)mOverrideGroupKeys.get(s);
            s;
            throw s;
        }

        private ArrayList getOverridePeople(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mOverridePeople == null)
                buildOverridePeopleLocked();
            this;
            JVM INSTR monitorexit ;
            return (ArrayList)mOverridePeople.get(s);
            s;
            throw s;
        }

        private int getRank(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mRanks == null)
                buildRanksLocked();
            this;
            JVM INSTR monitorexit ;
            s = (Integer)mRanks.get(s);
            int i;
            if(s != null)
                i = s.intValue();
            else
                i = -1;
            return i;
            s;
            throw s;
        }

        private boolean getShowBadge(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mShowBadge == null)
                buildShowBadgeLocked();
            this;
            JVM INSTR monitorexit ;
            s = (Boolean)mShowBadge.get(s);
            boolean flag;
            if(s == null)
                flag = false;
            else
                flag = s.booleanValue();
            return flag;
            s;
            throw s;
        }

        private ArrayList getSnoozeCriteria(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mSnoozeCriteria == null)
                buildSnoozeCriteriaLocked();
            this;
            JVM INSTR monitorexit ;
            return (ArrayList)mSnoozeCriteria.get(s);
            s;
            throw s;
        }

        private int getSuppressedVisualEffects(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mSuppressedVisualEffects == null)
                buildSuppressedVisualEffectsLocked();
            this;
            JVM INSTR monitorexit ;
            s = (Integer)mSuppressedVisualEffects.get(s);
            if(s == null)
                return 0;
            else
                return s.intValue();
            s;
            throw s;
        }

        private int getVisibilityOverride(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mVisibilityOverrides == null)
                buildVisibilityOverridesLocked();
            this;
            JVM INSTR monitorexit ;
            s = (Integer)mVisibilityOverrides.get(s);
            if(s == null)
                return -1000;
            else
                return s.intValue();
            s;
            throw s;
        }

        private boolean isIntercepted(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if(mIntercepted == null)
                buildInterceptedSetLocked();
            this;
            JVM INSTR monitorexit ;
            return mIntercepted.contains(s);
            s;
            throw s;
        }

        public int describeContents()
        {
            return 0;
        }

        public String[] getOrderedKeys()
        {
            return mRankingUpdate.getOrderedKeys();
        }

        public boolean getRanking(String s, Ranking ranking)
        {
            int i = getRank(s);
            Ranking._2D_wrap0(ranking, s, i, isIntercepted(s) ^ true, getVisibilityOverride(s), getSuppressedVisualEffects(s), getImportance(s), getImportanceExplanation(s), getOverrideGroupKey(s), getChannel(s), getOverridePeople(s), getSnoozeCriteria(s), getShowBadge(s));
            boolean flag;
            if(i >= 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeParcelable(mRankingUpdate, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RankingMap createFromParcel(Parcel parcel)
            {
                return new RankingMap((NotificationRankingUpdate)parcel.readParcelable(null), null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RankingMap[] newArray(int i)
            {
                return new RankingMap[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private ArrayMap mChannels;
        private ArrayMap mImportance;
        private ArrayMap mImportanceExplanation;
        private ArraySet mIntercepted;
        private ArrayMap mOverrideGroupKeys;
        private ArrayMap mOverridePeople;
        private final NotificationRankingUpdate mRankingUpdate;
        private ArrayMap mRanks;
        private ArrayMap mShowBadge;
        private ArrayMap mSnoozeCriteria;
        private ArrayMap mSuppressedVisualEffects;
        private ArrayMap mVisibilityOverrides;


        private RankingMap(NotificationRankingUpdate notificationrankingupdate)
        {
            mRankingUpdate = notificationrankingupdate;
        }

        RankingMap(NotificationRankingUpdate notificationrankingupdate, RankingMap rankingmap)
        {
            this(notificationrankingupdate);
        }
    }


    static String _2D_get0(NotificationListenerService notificationlistenerservice)
    {
        return notificationlistenerservice.TAG;
    }

    static boolean _2D_get1(NotificationListenerService notificationlistenerservice)
    {
        return notificationlistenerservice.isConnected;
    }

    static Handler _2D_get2(NotificationListenerService notificationlistenerservice)
    {
        return notificationlistenerservice.mHandler;
    }

    static Object _2D_get3(NotificationListenerService notificationlistenerservice)
    {
        return notificationlistenerservice.mLock;
    }

    static RankingMap _2D_get4(NotificationListenerService notificationlistenerservice)
    {
        return notificationlistenerservice.mRankingMap;
    }

    static boolean _2D_set0(NotificationListenerService notificationlistenerservice, boolean flag)
    {
        notificationlistenerservice.isConnected = flag;
        return flag;
    }

    static void _2D_wrap0(NotificationListenerService notificationlistenerservice, Notification notification)
    {
        notificationlistenerservice.createLegacyIconExtras(notification);
    }

    static void _2D_wrap1(NotificationListenerService notificationlistenerservice, Notification notification)
    {
        notificationlistenerservice.maybePopulateRemoteViews(notification);
    }

    public NotificationListenerService()
    {
        mWrapper = null;
        isConnected = false;
    }

    private StatusBarNotification[] cleanUpNotificationList(ParceledListSlice parceledlistslice)
    {
        if(parceledlistslice == null)
            return null;
        List list = parceledlistslice.getList();
        parceledlistslice = null;
        int i = list.size();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            StatusBarNotification statusbarnotification = (StatusBarNotification)list.get(j);
            Notification notification = statusbarnotification.getNotification();
            try
            {
                createLegacyIconExtras(notification);
                maybePopulateRemoteViews(notification);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Object obj = parceledlistslice;
                if(parceledlistslice == null)
                    obj = new ArrayList(i);
                ((ArrayList) (obj)).add(statusbarnotification);
                Log.w(TAG, (new StringBuilder()).append("get(Active/Snoozed)Notifications: can't rebuild notification from ").append(statusbarnotification.getPackageName()).toString());
                parceledlistslice = ((ParceledListSlice) (obj));
            }
            j++;
        } while(true);
        if(parceledlistslice != null)
            list.removeAll(parceledlistslice);
        return (StatusBarNotification[])list.toArray(new StatusBarNotification[list.size()]);
    }

    private void createLegacyIconExtras(Notification notification)
    {
        Icon icon = notification.getSmallIcon();
        Icon icon1 = notification.getLargeIcon();
        if(icon != null && icon.getType() == 2)
        {
            notification.extras.putInt("android.icon", icon.getResId());
            notification.icon = icon.getResId();
        }
        if(icon1 != null)
        {
            Object obj = icon1.loadDrawable(getContext());
            if(obj != null && (obj instanceof BitmapDrawable))
            {
                obj = ((BitmapDrawable)obj).getBitmap();
                notification.extras.putParcelable("android.largeIcon", ((Parcelable) (obj)));
                notification.largeIcon = ((android.graphics.Bitmap) (obj));
            }
        }
    }

    private void maybePopulateRemoteViews(Notification notification)
    {
        if(getContext().getApplicationInfo().targetSdkVersion < 24)
        {
            Object obj = android.app.Notification.Builder.recoverBuilder(getContext(), notification);
            android.widget.RemoteViews remoteviews = ((android.app.Notification.Builder) (obj)).createContentView();
            android.widget.RemoteViews remoteviews1 = ((android.app.Notification.Builder) (obj)).createBigContentView();
            obj = ((android.app.Notification.Builder) (obj)).createHeadsUpContentView();
            notification.contentView = remoteviews;
            notification.bigContentView = remoteviews1;
            notification.headsUpContentView = ((android.widget.RemoteViews) (obj));
        }
    }

    public static void requestRebind(ComponentName componentname)
    {
        INotificationManager inotificationmanager = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        try
        {
            inotificationmanager.requestBindListener(componentname);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public final void applyUpdateLocked(NotificationRankingUpdate notificationrankingupdate)
    {
        mRankingMap = new RankingMap(notificationrankingupdate, null);
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new MyHandler(getMainLooper());
    }

    public final void cancelAllNotifications()
    {
        cancelNotifications(null);
    }

    public final void cancelNotification(String s)
    {
        if(!isBound())
            return;
        getNotificationInterface().cancelNotificationsFromListener(mWrapper, new String[] {
            s
        });
_L1:
        return;
        s;
        Log.v(TAG, "Unable to contact notification manager", s);
          goto _L1
    }

    public final void cancelNotification(String s, String s1, int i)
    {
        if(!isBound())
            return;
        getNotificationInterface().cancelNotificationFromListener(mWrapper, s, s1, i);
_L1:
        return;
        s;
        Log.v(TAG, "Unable to contact notification manager", s);
          goto _L1
    }

    public final void cancelNotifications(String as[])
    {
        if(!isBound())
            return;
        getNotificationInterface().cancelNotificationsFromListener(mWrapper, as);
_L1:
        return;
        as;
        Log.v(TAG, "Unable to contact notification manager", as);
          goto _L1
    }

    public StatusBarNotification[] getActiveNotifications()
    {
        return getActiveNotifications(null, 0);
    }

    public StatusBarNotification[] getActiveNotifications(int i)
    {
        return getActiveNotifications(null, i);
    }

    public StatusBarNotification[] getActiveNotifications(String as[])
    {
        return getActiveNotifications(as, 0);
    }

    public StatusBarNotification[] getActiveNotifications(String as[], int i)
    {
        if(!isBound())
            return null;
        try
        {
            as = cleanUpNotificationList(getNotificationInterface().getActiveNotificationsFromListener(mWrapper, as, i));
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            Log.v(TAG, "Unable to contact notification manager", as);
            return null;
        }
        return as;
    }

    protected Context getContext()
    {
        if(mSystemContext != null)
            return mSystemContext;
        else
            return this;
    }

    public final int getCurrentInterruptionFilter()
    {
        if(!isBound())
            return 0;
        int i;
        try
        {
            i = getNotificationInterface().getInterruptionFilterFromListener(mWrapper);
        }
        catch(RemoteException remoteexception)
        {
            Log.v(TAG, "Unable to contact notification manager", remoteexception);
            return 0;
        }
        return i;
    }

    public final int getCurrentListenerHints()
    {
        if(!isBound())
            return 0;
        int i;
        try
        {
            i = getNotificationInterface().getHintsFromListener(mWrapper);
        }
        catch(RemoteException remoteexception)
        {
            Log.v(TAG, "Unable to contact notification manager", remoteexception);
            return 0;
        }
        return i;
    }

    public RankingMap getCurrentRanking()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        RankingMap rankingmap = mRankingMap;
        obj;
        JVM INSTR monitorexit ;
        return rankingmap;
        Exception exception;
        exception;
        throw exception;
    }

    public final List getNotificationChannelGroups(String s, UserHandle userhandle)
    {
        if(!isBound())
            return null;
        try
        {
            s = getNotificationInterface().getNotificationChannelGroupsFromPrivilegedListener(mWrapper, s, userhandle).getList();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.v(TAG, "Unable to contact notification manager", s);
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public final List getNotificationChannels(String s, UserHandle userhandle)
    {
        if(!isBound())
            return null;
        try
        {
            s = getNotificationInterface().getNotificationChannelsFromPrivilegedListener(mWrapper, s, userhandle).getList();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.v(TAG, "Unable to contact notification manager", s);
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    protected final INotificationManager getNotificationInterface()
    {
        if(mNoMan == null)
            mNoMan = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        return mNoMan;
    }

    public final StatusBarNotification[] getSnoozedNotifications()
    {
        StatusBarNotification astatusbarnotification[];
        try
        {
            astatusbarnotification = cleanUpNotificationList(getNotificationInterface().getSnoozedNotificationsFromListener(mWrapper, 0));
        }
        catch(RemoteException remoteexception)
        {
            Log.v(TAG, "Unable to contact notification manager", remoteexception);
            return null;
        }
        return astatusbarnotification;
    }

    protected boolean isBound()
    {
        if(mWrapper == null)
        {
            Log.w(TAG, "Notification listener service not yet bound.");
            return false;
        } else
        {
            return true;
        }
    }

    public IBinder onBind(Intent intent)
    {
        if(mWrapper == null)
            mWrapper = new NotificationListenerWrapper();
        return mWrapper;
    }

    public void onDestroy()
    {
        onListenerDisconnected();
        super.onDestroy();
    }

    public void onInterruptionFilterChanged(int i)
    {
    }

    public void onListenerConnected()
    {
    }

    public void onListenerDisconnected()
    {
    }

    public void onListenerHintsChanged(int i)
    {
    }

    public void onNotificationChannelGroupModified(String s, UserHandle userhandle, NotificationChannelGroup notificationchannelgroup, int i)
    {
    }

    public void onNotificationChannelModified(String s, UserHandle userhandle, NotificationChannel notificationchannel, int i)
    {
    }

    public void onNotificationPosted(StatusBarNotification statusbarnotification)
    {
    }

    public void onNotificationPosted(StatusBarNotification statusbarnotification, RankingMap rankingmap)
    {
        onNotificationPosted(statusbarnotification);
    }

    public void onNotificationRankingUpdate(RankingMap rankingmap)
    {
    }

    public void onNotificationRemoved(StatusBarNotification statusbarnotification)
    {
    }

    public void onNotificationRemoved(StatusBarNotification statusbarnotification, RankingMap rankingmap)
    {
        onNotificationRemoved(statusbarnotification);
    }

    public void onNotificationRemoved(StatusBarNotification statusbarnotification, RankingMap rankingmap, int i)
    {
        onNotificationRemoved(statusbarnotification, rankingmap);
    }

    public void registerAsSystemService(Context context, ComponentName componentname, int i)
        throws RemoteException
    {
        if(mWrapper == null)
            mWrapper = new NotificationListenerWrapper();
        mSystemContext = context;
        INotificationManager inotificationmanager = getNotificationInterface();
        mHandler = new MyHandler(context.getMainLooper());
        mCurrentUser = i;
        inotificationmanager.registerListener(mWrapper, componentname, i);
    }

    public final void requestInterruptionFilter(int i)
    {
        if(!isBound())
            return;
        getNotificationInterface().requestInterruptionFilterFromListener(mWrapper, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.v(TAG, "Unable to contact notification manager", remoteexception);
          goto _L1
    }

    public final void requestListenerHints(int i)
    {
        if(!isBound())
            return;
        getNotificationInterface().requestHintsFromListener(mWrapper, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.v(TAG, "Unable to contact notification manager", remoteexception);
          goto _L1
    }

    public final void requestUnbind()
    {
        INotificationManager inotificationmanager;
        if(mWrapper == null)
            break MISSING_BLOCK_LABEL_27;
        inotificationmanager = getNotificationInterface();
        inotificationmanager.requestUnbindListener(mWrapper);
        isConnected = false;
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public final void setNotificationsShown(String as[])
    {
        if(!isBound())
            return;
        getNotificationInterface().setNotificationsShownFromListener(mWrapper, as);
_L1:
        return;
        as;
        Log.v(TAG, "Unable to contact notification manager", as);
          goto _L1
    }

    public final void setOnNotificationPostedTrim(int i)
    {
        if(!isBound())
            return;
        getNotificationInterface().setOnNotificationPostedTrimFromListener(mWrapper, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.v(TAG, "Unable to contact notification manager", remoteexception);
          goto _L1
    }

    public final void snoozeNotification(String s, long l)
    {
        if(!isBound())
            return;
        getNotificationInterface().snoozeNotificationUntilFromListener(mWrapper, s, l);
_L1:
        return;
        s;
        Log.v(TAG, "Unable to contact notification manager", s);
          goto _L1
    }

    public final void snoozeNotification(String s, String s1)
    {
        if(!isBound())
            return;
        getNotificationInterface().snoozeNotificationUntilContextFromListener(mWrapper, s, s1);
_L1:
        return;
        s;
        Log.v(TAG, "Unable to contact notification manager", s);
          goto _L1
    }

    public void unregisterAsSystemService()
        throws RemoteException
    {
        if(mWrapper != null)
            getNotificationInterface().unregisterListener(mWrapper, mCurrentUser);
    }

    public final void updateNotificationChannel(String s, UserHandle userhandle, NotificationChannel notificationchannel)
    {
        if(!isBound())
            return;
        try
        {
            getNotificationInterface().updateNotificationChannelFromPrivilegedListener(mWrapper, s, userhandle, notificationchannel);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.v(TAG, "Unable to contact notification manager", s);
        }
        throw s.rethrowFromSystemServer();
    }

    public static final int HINT_HOST_DISABLE_CALL_EFFECTS = 4;
    public static final int HINT_HOST_DISABLE_EFFECTS = 1;
    public static final int HINT_HOST_DISABLE_NOTIFICATION_EFFECTS = 2;
    public static final int INTERRUPTION_FILTER_ALARMS = 4;
    public static final int INTERRUPTION_FILTER_ALL = 1;
    public static final int INTERRUPTION_FILTER_NONE = 3;
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_ADDED = 1;
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_DELETED = 3;
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_UPDATED = 2;
    public static final int REASON_APP_CANCEL = 8;
    public static final int REASON_APP_CANCEL_ALL = 9;
    public static final int REASON_CANCEL = 2;
    public static final int REASON_CANCEL_ALL = 3;
    public static final int REASON_CHANNEL_BANNED = 17;
    public static final int REASON_CLICK = 1;
    public static final int REASON_ERROR = 4;
    public static final int REASON_GROUP_OPTIMIZATION = 13;
    public static final int REASON_GROUP_SUMMARY_CANCELED = 12;
    public static final int REASON_LISTENER_CANCEL = 10;
    public static final int REASON_LISTENER_CANCEL_ALL = 11;
    public static final int REASON_PACKAGE_BANNED = 7;
    public static final int REASON_PACKAGE_CHANGED = 5;
    public static final int REASON_PACKAGE_SUSPENDED = 14;
    public static final int REASON_PROFILE_TURNED_OFF = 15;
    public static final int REASON_SNOOZED = 18;
    public static final int REASON_TIMEOUT = 19;
    public static final int REASON_UNAUTOBUNDLED = 16;
    public static final int REASON_USER_STOPPED = 6;
    public static final String SERVICE_INTERFACE = "android.service.notification.NotificationListenerService";
    public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;
    public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;
    public static final int TRIM_FULL = 0;
    public static final int TRIM_LIGHT = 1;
    private final String TAG = getClass().getSimpleName();
    private boolean isConnected;
    protected int mCurrentUser;
    private Handler mHandler;
    private final Object mLock = new Object();
    private INotificationManager mNoMan;
    private RankingMap mRankingMap;
    protected Context mSystemContext;
    protected NotificationListenerWrapper mWrapper;
}
