// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


public class ConsoleMessage
{
    public static final class MessageLevel extends Enum
    {

        public static MessageLevel valueOf(String s)
        {
            return (MessageLevel)Enum.valueOf(android/webkit/ConsoleMessage$MessageLevel, s);
        }

        public static MessageLevel[] values()
        {
            return $VALUES;
        }

        private static final MessageLevel $VALUES[];
        public static final MessageLevel DEBUG;
        public static final MessageLevel ERROR;
        public static final MessageLevel LOG;
        public static final MessageLevel TIP;
        public static final MessageLevel WARNING;

        static 
        {
            TIP = new MessageLevel("TIP", 0);
            LOG = new MessageLevel("LOG", 1);
            WARNING = new MessageLevel("WARNING", 2);
            ERROR = new MessageLevel("ERROR", 3);
            DEBUG = new MessageLevel("DEBUG", 4);
            $VALUES = (new MessageLevel[] {
                TIP, LOG, WARNING, ERROR, DEBUG
            });
        }

        private MessageLevel(String s, int i)
        {
            super(s, i);
        }
    }


    public ConsoleMessage(String s, String s1, int i, MessageLevel messagelevel)
    {
        mMessage = s;
        mSourceId = s1;
        mLineNumber = i;
        mLevel = messagelevel;
    }

    public int lineNumber()
    {
        return mLineNumber;
    }

    public String message()
    {
        return mMessage;
    }

    public MessageLevel messageLevel()
    {
        return mLevel;
    }

    public String sourceId()
    {
        return mSourceId;
    }

    private MessageLevel mLevel;
    private int mLineNumber;
    private String mMessage;
    private String mSourceId;
}
