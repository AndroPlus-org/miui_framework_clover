// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;
import java.nio.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.util:
//            Log

public class EventLog
{
    public static final class Event
    {

        private Object decodeObject()
        {
            int i = mBuffer.get();
            byte byte0;
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown entry type: ").append(i).toString());

            case 0: // '\0'
                return Integer.valueOf(mBuffer.getInt());

            case 1: // '\001'
                return Long.valueOf(mBuffer.getLong());

            case 4: // '\004'
                return Float.valueOf(mBuffer.getFloat());

            case 2: // '\002'
                String s;
                try
                {
                    i = mBuffer.getInt();
                    int j = mBuffer.position();
                    mBuffer.position(j + i);
                    s = new String(mBuffer.array(), j, i, "UTF-8");
                }
                catch(UnsupportedEncodingException unsupportedencodingexception)
                {
                    Log.wtf("EventLog", "UTF-8 is not supported", unsupportedencodingexception);
                    mLastWtf = unsupportedencodingexception;
                    return null;
                }
                return s;

            case 3: // '\003'
                byte0 = mBuffer.get();
                i = byte0;
                break;
            }
            if(byte0 < 0)
                i = byte0 + 256;
            Object aobj[] = new Object[i];
            for(int k = 0; k < i; k++)
                aobj[k] = decodeObject();

            return ((Object) (aobj));
        }

        public static Event fromBytes(byte abyte0[])
        {
            return new Event(abyte0);
        }

        public void clearError()
        {
            mLastWtf = null;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
            {
                return false;
            } else
            {
                obj = (Event)obj;
                return Arrays.equals(mBuffer.array(), ((Event) (obj)).mBuffer.array());
            }
        }

        public byte[] getBytes()
        {
            byte abyte0[] = mBuffer.array();
            return Arrays.copyOf(abyte0, abyte0.length);
        }

        public Object getData()
        {
            this;
            JVM INSTR monitorenter ;
            int i = mBuffer.getShort(2);
            short word0;
            word0 = i;
            if(i == 0)
                word0 = 20;
            mBuffer.limit(mBuffer.getShort(0) + word0);
            i = mBuffer.limit();
            if(word0 + 4 < i)
                break MISSING_BLOCK_LABEL_57;
            this;
            JVM INSTR monitorexit ;
            return null;
            Object obj;
            mBuffer.position(word0 + 4);
            obj = decodeObject();
            this;
            JVM INSTR monitorexit ;
            return obj;
            Object obj1;
            obj1;
            StringBuilder stringbuilder1 = JVM INSTR new #73  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.wtf("EventLog", stringbuilder1.append("Truncated entry payload: tag=").append(getTag()).toString(), ((Throwable) (obj1)));
            mLastWtf = ((Exception) (obj1));
            this;
            JVM INSTR monitorexit ;
            return null;
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            StringBuilder stringbuilder = JVM INSTR new #73  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.wtf("EventLog", stringbuilder.append("Illegal entry payload: tag=").append(getTag()).toString(), illegalargumentexception);
            mLastWtf = illegalargumentexception;
            this;
            JVM INSTR monitorexit ;
            return null;
            stringbuilder;
            throw stringbuilder;
        }

        public Exception getLastError()
        {
            return mLastWtf;
        }

        public int getProcessId()
        {
            return mBuffer.getInt(4);
        }

        public int getTag()
        {
            short word0 = mBuffer.getShort(2);
            short word1 = word0;
            if(word0 == 0)
                word1 = 20;
            return mBuffer.getInt(word1);
        }

        public int getThreadId()
        {
            return mBuffer.getInt(8);
        }

        public long getTimeNanos()
        {
            return (long)mBuffer.getInt(12) * 0x3b9aca00L + (long)mBuffer.getInt(16);
        }

        public int getUid()
        {
            int i;
            try
            {
                i = mBuffer.getInt(24);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return -1;
            }
            return i;
        }

        public int hashCode()
        {
            return Arrays.hashCode(mBuffer.array());
        }

        private static final int DATA_OFFSET = 4;
        private static final byte FLOAT_TYPE = 4;
        private static final int HEADER_SIZE_OFFSET = 2;
        private static final byte INT_TYPE = 0;
        private static final int LENGTH_OFFSET = 0;
        private static final byte LIST_TYPE = 3;
        private static final byte LONG_TYPE = 1;
        private static final int NANOSECONDS_OFFSET = 16;
        private static final int PROCESS_OFFSET = 4;
        private static final int SECONDS_OFFSET = 12;
        private static final byte STRING_TYPE = 2;
        private static final int THREAD_OFFSET = 8;
        private static final int UID_OFFSET = 24;
        private static final int V1_PAYLOAD_START = 20;
        private final ByteBuffer mBuffer;
        private Exception mLastWtf;

        Event(byte abyte0[])
        {
            mBuffer = ByteBuffer.wrap(abyte0);
            mBuffer.order(ByteOrder.nativeOrder());
        }
    }


    public EventLog()
    {
    }

    public static int getTagCode(String s)
    {
        readTagsFile();
        s = (Integer)sTagCodes.get(s);
        int i;
        if(s != null)
            i = s.intValue();
        else
            i = -1;
        return i;
    }

    public static String getTagName(int i)
    {
        readTagsFile();
        return (String)sTagNames.get(Integer.valueOf(i));
    }

    public static native void readEvents(int ai[], Collection collection)
        throws IOException;

    public static native void readEventsOnWrapping(int ai[], long l, Collection collection)
        throws IOException;

    private static void readTagsFile()
    {
        android/util/EventLog;
        JVM INSTR monitorenter ;
        Object obj;
        if(sTagCodes == null)
            break MISSING_BLOCK_LABEL_21;
        obj = sTagNames;
        if(obj == null)
            break MISSING_BLOCK_LABEL_21;
        android/util/EventLog;
        JVM INSTR monitorexit ;
        return;
        Pattern pattern;
        Pattern pattern1;
        obj = JVM INSTR new #42  <Class HashMap>;
        ((HashMap) (obj)).HashMap();
        sTagCodes = ((HashMap) (obj));
        obj = JVM INSTR new #42  <Class HashMap>;
        ((HashMap) (obj)).HashMap();
        sTagNames = ((HashMap) (obj));
        pattern = Pattern.compile("^\\s*(#.*)?$");
        pattern1 = Pattern.compile("^\\s*(\\d+)\\s+(\\w+)\\s*(\\(.*\\))?\\s*$");
        Object obj1;
        NumberFormatException numberformatexception;
        obj1 = null;
        numberformatexception = null;
        obj = obj1;
        Object obj2 = JVM INSTR new #77  <Class BufferedReader>;
        obj = obj1;
        FileReader filereader = JVM INSTR new #79  <Class FileReader>;
        obj = obj1;
        filereader.FileReader("/system/etc/event-log-tags");
        obj = obj1;
        ((BufferedReader) (obj2)).BufferedReader(filereader, 256);
_L4:
        obj = ((BufferedReader) (obj2)).readLine();
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(pattern.matcher(((CharSequence) (obj))).matches()) goto _L4; else goto _L3
_L3:
        obj1 = pattern1.matcher(((CharSequence) (obj)));
        if(((Matcher) (obj1)).matches()) goto _L6; else goto _L5
_L5:
        obj1 = JVM INSTR new #101 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.wtf("EventLog", ((StringBuilder) (obj1)).append("Bad entry in /system/etc/event-log-tags: ").append(((String) (obj))).toString());
          goto _L4
        obj1;
_L9:
        obj = obj2;
        Log.wtf("EventLog", "Error reading /system/etc/event-log-tags", ((Throwable) (obj1)));
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_184;
        try
        {
            ((BufferedReader) (obj2)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        android/util/EventLog;
        JVM INSTR monitorexit ;
        return;
_L6:
        int i = Integer.parseInt(((Matcher) (obj1)).group(1));
        obj1 = ((Matcher) (obj1)).group(2);
        sTagCodes.put(obj1, Integer.valueOf(i));
        sTagNames.put(Integer.valueOf(i), obj1);
          goto _L4
        numberformatexception;
        obj1 = JVM INSTR new #101 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.wtf("EventLog", ((StringBuilder) (obj1)).append("Error in /system/etc/event-log-tags: ").append(((String) (obj))).toString(), numberformatexception);
          goto _L4
        obj;
        obj1 = obj2;
_L8:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_279;
        try
        {
            ((BufferedReader) (obj1)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2) { }
        throw obj;
        obj;
        android/util/EventLog;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_184;
        try
        {
            ((BufferedReader) (obj2)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        break MISSING_BLOCK_LABEL_184;
        obj2;
        obj1 = obj;
        obj = obj2;
        if(true) goto _L8; else goto _L7
_L7:
        obj1;
        obj2 = numberformatexception;
          goto _L9
    }

    public static native int writeEvent(int i, float f);

    public static native int writeEvent(int i, int j);

    public static native int writeEvent(int i, long l);

    public static native int writeEvent(int i, String s);

    public static transient native int writeEvent(int i, Object aobj[]);

    private static final String COMMENT_PATTERN = "^\\s*(#.*)?$";
    private static final String TAG = "EventLog";
    private static final String TAGS_FILE = "/system/etc/event-log-tags";
    private static final String TAG_PATTERN = "^\\s*(\\d+)\\s+(\\w+)\\s*(\\(.*\\))?\\s*$";
    private static HashMap sTagCodes = null;
    private static HashMap sTagNames = null;

}
