// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier.logging;

import android.content.Context;
import android.metrics.LogMaker;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextSelection;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.Preconditions;
import java.util.Objects;
import java.util.UUID;

public final class SmartSelectionEventTracker
{
    public static final class SelectionEvent
    {

        static int _2D_get0(SelectionEvent selectionevent)
        {
            return selectionevent.mEnd;
        }

        static String _2D_get1(SelectionEvent selectionevent)
        {
            return selectionevent.mEntityType;
        }

        static int _2D_get2(SelectionEvent selectionevent)
        {
            return selectionevent.mEventType;
        }

        static int _2D_get3(SelectionEvent selectionevent)
        {
            return selectionevent.mStart;
        }

        static String _2D_get4(SelectionEvent selectionevent)
        {
            return selectionevent.mVersionTag;
        }

        static boolean _2D_wrap0(SelectionEvent selectionevent)
        {
            return selectionevent.isTerminal();
        }

        private boolean isActionType()
        {
            switch(mEventType)
            {
            default:
                return false;

            case 100: // 'd'
            case 101: // 'e'
            case 102: // 'f'
            case 103: // 'g'
            case 104: // 'h'
            case 105: // 'i'
            case 106: // 'j'
            case 107: // 'k'
            case 200: 
            case 201: 
                return true;
            }
        }

        private boolean isTerminal()
        {
            switch(mEventType)
            {
            default:
                return false;

            case 100: // 'd'
            case 101: // 'e'
            case 102: // 'f'
            case 103: // 'g'
            case 104: // 'h'
            case 105: // 'i'
            case 106: // 'j'
            case 107: // 'k'
            case 108: // 'l'
                return true;
            }
        }

        public static SelectionEvent selectionAction(int i, int j, int k)
        {
            return new SelectionEvent(i, j, k, "", "");
        }

        public static SelectionEvent selectionAction(int i, int j, int k, TextClassification textclassification)
        {
            String s;
            if(textclassification.getEntityCount() > 0)
                s = textclassification.getEntity(0);
            else
                s = "";
            return new SelectionEvent(i, j, k, s, textclassification.getVersionInfo());
        }

        public static SelectionEvent selectionModified(int i, int j)
        {
            return new SelectionEvent(i, j, 2, "", "");
        }

        public static SelectionEvent selectionModified(int i, int j, TextClassification textclassification)
        {
            String s;
            if(textclassification.getEntityCount() > 0)
                s = textclassification.getEntity(0);
            else
                s = "";
            return new SelectionEvent(i, j, 2, s, textclassification.getVersionInfo());
        }

        public static SelectionEvent selectionModified(int i, int j, TextSelection textselection)
        {
            byte byte0;
            String s;
            if(textselection.getSourceClassifier().equals("TextClassifierImpl"))
            {
                if(j - i > 1)
                    byte0 = 4;
                else
                    byte0 = 3;
            } else
            {
                byte0 = 5;
            }
            if(textselection.getEntityCount() > 0)
                s = textselection.getEntity(0);
            else
                s = "";
            return new SelectionEvent(i, j, byte0, s, textselection.getVersionInfo());
        }

        public static SelectionEvent selectionStarted(int i)
        {
            return new SelectionEvent(i, i + 1, 1, "", "");
        }

        private static final String NO_VERSION_TAG = "";
        public static final int OUT_OF_BOUNDS = 32767;
        public static final int OUT_OF_BOUNDS_NEGATIVE = -32768;
        private final int mEnd;
        private final String mEntityType;
        private int mEventType;
        private final int mStart;
        private final String mVersionTag;

        private SelectionEvent(int i, int j, int k, String s, String s1)
        {
            boolean flag;
            if(j >= i)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "end cannot be less than start");
            mStart = i;
            mEnd = j;
            mEventType = k;
            mEntityType = (String)Preconditions.checkNotNull(s);
            mVersionTag = (String)Preconditions.checkNotNull(s1);
        }
    }


    public SmartSelectionEventTracker(Context context, int i)
    {
        mWidgetType = i;
        mContext = (Context)Preconditions.checkNotNull(context);
    }

    private static int clamp(int i)
    {
        return Math.max(Math.min(i, 32767), -32768);
    }

    private static String createSessionId()
    {
        return UUID.randomUUID().toString();
    }

    private static void debugLog(LogMaker logmaker)
    {
    }

    private void endSession()
    {
        mOrigStart = 0;
        int ai[] = mSmartIndices;
        mSmartIndices[1] = 0;
        ai[0] = 0;
        ai = mPrevIndices;
        mPrevIndices[1] = 0;
        ai[0] = 0;
        mIndex = 0;
        mSessionStartTime = 0L;
        mLastEventTime = 0L;
        mSmartSelectionTriggered = false;
        mVersionTag = getVersionTag(null);
        mSessionId = null;
    }

    private int getEventDelta(SelectionEvent selectionevent)
    {
        return clamp(SelectionEvent._2D_get3(selectionevent) - mOrigStart) << 16 | clamp(SelectionEvent._2D_get0(selectionevent) - mOrigStart) & 0xffff;
    }

    private static int getLogSubType(SelectionEvent selectionevent)
    {
        selectionevent = SelectionEvent._2D_get1(selectionevent);
        if(selectionevent.equals("other"))
            return 2;
        if(selectionevent.equals("email"))
            return 3;
        if(selectionevent.equals("phone"))
            return 4;
        if(selectionevent.equals("address"))
            return 5;
        return !selectionevent.equals("url") ? 1 : 6;
    }

    private static String getLogSubTypeString(int i)
    {
        switch(i)
        {
        default:
            return "";

        case 2: // '\002'
            return "other";

        case 3: // '\003'
            return "email";

        case 4: // '\004'
            return "phone";

        case 5: // '\005'
            return "address";

        case 6: // '\006'
            return "url";
        }
    }

    private static int getLogType(SelectionEvent selectionevent)
    {
        switch(SelectionEvent._2D_get2(selectionevent))
        {
        default:
            return 0;

        case 100: // 'd'
            return 1108;

        case 101: // 'e'
            return 1109;

        case 102: // 'f'
            return 1110;

        case 103: // 'g'
            return 1111;

        case 104: // 'h'
            return 1112;

        case 105: // 'i'
            return 1113;

        case 106: // 'j'
            return 1114;

        case 107: // 'k'
            return 1115;

        case 108: // 'l'
            return 1116;

        case 200: 
            return 1103;

        case 201: 
            return 1104;

        case 1: // '\001'
            return 1101;

        case 2: // '\002'
            return 1102;

        case 3: // '\003'
            return 1105;

        case 4: // '\004'
            return 1106;

        case 5: // '\005'
            return 1107;
        }
    }

    private static String getLogTypeString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 1108: 
            return "OVERTYPE";

        case 1109: 
            return "COPY";

        case 1110: 
            return "PASTE";

        case 1111: 
            return "CUT";

        case 1112: 
            return "SHARE";

        case 1113: 
            return "SMART_SHARE";

        case 1114: 
            return "DRAG";

        case 1115: 
            return "ABANDON";

        case 1116: 
            return "OTHER";

        case 1103: 
            return "SELECT_ALL";

        case 1104: 
            return "RESET";

        case 1101: 
            return "SELECTION_STARTED";

        case 1102: 
            return "SELECTION_MODIFIED";

        case 1105: 
            return "SMART_SELECTION_SINGLE";

        case 1106: 
            return "SMART_SELECTION_MULTI";

        case 1107: 
            return "AUTO_SELECTION";
        }
    }

    private int getSmartDelta()
    {
        if(mSmartSelectionTriggered)
            return clamp(mSmartIndices[0] - mOrigStart) << 16 | clamp(mSmartIndices[1] - mOrigStart) & 0xffff;
        else
            return 0;
    }

    private String getVersionTag(SelectionEvent selectionevent)
    {
        mWidgetType;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 64
    //                   2 70
    //                   3 76
    //                   4 82;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_82;
_L1:
        String s = "unknown";
_L6:
        if(selectionevent == null)
            selectionevent = "";
        else
            selectionevent = Objects.toString(SelectionEvent._2D_get4(selectionevent), "");
        return String.format("%s/%s", new Object[] {
            s, selectionevent
        });
_L2:
        s = "textview";
          goto _L6
_L3:
        s = "webview";
          goto _L6
_L4:
        s = "edittext";
          goto _L6
        s = "edit-webview";
          goto _L6
    }

    private String startNewSession()
    {
        endSession();
        mSessionId = createSessionId();
        return mSessionId;
    }

    private void writeEvent(SelectionEvent selectionevent, long l)
    {
        long l1;
        LogMaker logmaker;
        if(mLastEventTime == 0L)
            l1 = 0L;
        else
            l1 = l - mLastEventTime;
        logmaker = (new LogMaker(1100)).setType(getLogType(selectionevent)).setSubtype(getLogSubType(selectionevent)).setPackageName(mContext.getPackageName()).setTimestamp(l).addTaggedData(1117, Long.valueOf(l - mSessionStartTime)).addTaggedData(1118, Long.valueOf(l1)).addTaggedData(1120, Integer.valueOf(mIndex)).addTaggedData(1121, mVersionTag).addTaggedData(1123, Integer.valueOf(getSmartDelta())).addTaggedData(1122, Integer.valueOf(getEventDelta(selectionevent))).addTaggedData(1119, mSessionId);
        mMetricsLogger.write(logmaker);
        debugLog(logmaker);
        mLastEventTime = l;
        mPrevIndices[0] = SelectionEvent._2D_get3(selectionevent);
        mPrevIndices[1] = SelectionEvent._2D_get0(selectionevent);
        mIndex = mIndex + 1;
    }

    public void logEvent(SelectionEvent selectionevent)
    {
        boolean flag;
        long l;
        flag = true;
        Preconditions.checkNotNull(selectionevent);
        String s;
        if(SelectionEvent._2D_get2(selectionevent) != 1)
            s = mSessionId;
        l = System.currentTimeMillis();
        SelectionEvent._2D_get2(selectionevent);
        JVM INSTR tableswitch 1 5: default 64
    //                   1 83
    //                   2 167
    //                   3 130
    //                   4 130
    //                   5 167;
           goto _L1 _L2 _L3 _L4 _L4 _L3
_L1:
        writeEvent(selectionevent, l);
        if(SelectionEvent._2D_wrap0(selectionevent))
            endSession();
        return;
_L2:
        mSessionId = startNewSession();
        if(SelectionEvent._2D_get0(selectionevent) != SelectionEvent._2D_get3(selectionevent) + 1)
            flag = false;
        Preconditions.checkArgument(flag);
        mOrigStart = SelectionEvent._2D_get3(selectionevent);
        mSessionStartTime = l;
        continue; /* Loop/switch isn't completed */
_L4:
        mSmartSelectionTriggered = true;
        mVersionTag = getVersionTag(selectionevent);
        mSmartIndices[0] = SelectionEvent._2D_get3(selectionevent);
        mSmartIndices[1] = SelectionEvent._2D_get0(selectionevent);
        if(true) goto _L1; else goto _L3
_L3:
        if(mPrevIndices[0] == SelectionEvent._2D_get3(selectionevent) && mPrevIndices[1] == SelectionEvent._2D_get0(selectionevent))
            return;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static final boolean DEBUG_LOG_ENABLED = false;
    private static final String EDITTEXT = "edittext";
    private static final String EDIT_WEBVIEW = "edit-webview";
    private static final int EVENT_INDICES = 1122;
    private static final int INDEX = 1120;
    private static final String LOG_TAG = "SmartSelectEventTracker";
    private static final int PREV_EVENT_DELTA = 1118;
    private static final int SESSION_ID = 1119;
    private static final int SMART_INDICES = 1123;
    private static final int START_EVENT_DELTA = 1117;
    private static final String TEXTVIEW = "textview";
    private static final String UNKNOWN = "unknown";
    private static final int VERSION_TAG = 1121;
    private static final String WEBVIEW = "webview";
    private static final String ZERO = "0";
    private final Context mContext;
    private int mIndex;
    private long mLastEventTime;
    private final MetricsLogger mMetricsLogger = new MetricsLogger();
    private int mOrigStart;
    private final int mPrevIndices[] = new int[2];
    private String mSessionId;
    private long mSessionStartTime;
    private final int mSmartIndices[] = new int[2];
    private boolean mSmartSelectionTriggered;
    private String mVersionTag;
    private final int mWidgetType;
}
