// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

public class BlockedNumberContract
{
    public static class BlockedNumbers
    {

        public static final String COLUMN_E164_NUMBER = "e164_number";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_ORIGINAL_NUMBER = "original_number";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/blocked_number";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/blocked_number";
        public static final Uri CONTENT_URI;

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(BlockedNumberContract.AUTHORITY_URI, "blocked");
        }

        private BlockedNumbers()
        {
        }
    }

    public static class SystemContract
    {

        public static void endBlockSuppression(Context context)
        {
            context.getContentResolver().call(BlockedNumberContract.AUTHORITY_URI, "end_block_suppression", null, null);
        }

        public static BlockSuppressionStatus getBlockSuppressionStatus(Context context)
        {
            context = context.getContentResolver().call(BlockedNumberContract.AUTHORITY_URI, "get_block_suppression_status", null, null);
            return new BlockSuppressionStatus(context.getBoolean("blocking_suppressed", false), context.getLong("blocking_suppressed_until_timestamp", 0L));
        }

        public static void notifyEmergencyContact(Context context)
        {
            context.getContentResolver().call(BlockedNumberContract.AUTHORITY_URI, "notify_emergency_contact", null, null);
        }

        public static boolean shouldSystemBlockNumber(Context context, String s)
        {
            boolean flag = false;
            context = context.getContentResolver().call(BlockedNumberContract.AUTHORITY_URI, "should_system_block_number", s, null);
            if(context != null)
                flag = context.getBoolean("blocked", false);
            return flag;
        }

        public static final String ACTION_BLOCK_SUPPRESSION_STATE_CHANGED = "android.provider.action.BLOCK_SUPPRESSION_STATE_CHANGED";
        public static final String METHOD_END_BLOCK_SUPPRESSION = "end_block_suppression";
        public static final String METHOD_GET_BLOCK_SUPPRESSION_STATUS = "get_block_suppression_status";
        public static final String METHOD_NOTIFY_EMERGENCY_CONTACT = "notify_emergency_contact";
        public static final String METHOD_SHOULD_SYSTEM_BLOCK_NUMBER = "should_system_block_number";
        public static final String RES_BLOCKING_SUPPRESSED_UNTIL_TIMESTAMP = "blocking_suppressed_until_timestamp";
        public static final String RES_IS_BLOCKING_SUPPRESSED = "blocking_suppressed";

        public SystemContract()
        {
        }
    }

    public static class SystemContract.BlockSuppressionStatus
    {

        public final boolean isSuppressed;
        public final long untilTimestampMillis;

        public SystemContract.BlockSuppressionStatus(boolean flag, long l)
        {
            isSuppressed = flag;
            untilTimestampMillis = l;
        }
    }


    private BlockedNumberContract()
    {
    }

    public static boolean canCurrentUserBlockNumbers(Context context)
    {
        boolean flag = false;
        context = context.getContentResolver().call(AUTHORITY_URI, "can_current_user_block_numbers", null, null);
        if(context != null)
            flag = context.getBoolean("can_block", false);
        return flag;
    }

    public static boolean isBlocked(Context context, String s)
    {
        boolean flag = false;
        context = context.getContentResolver().call(AUTHORITY_URI, "is_blocked", s, null);
        if(context != null)
            flag = context.getBoolean("blocked", false);
        return flag;
    }

    public static int unblock(Context context, String s)
    {
        return context.getContentResolver().call(AUTHORITY_URI, "unblock", s, null).getInt("num_deleted", 0);
    }

    public static final String AUTHORITY = "com.android.blockednumber";
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.android.blockednumber");
    public static final String METHOD_CAN_CURRENT_USER_BLOCK_NUMBERS = "can_current_user_block_numbers";
    public static final String METHOD_IS_BLOCKED = "is_blocked";
    public static final String METHOD_UNBLOCK = "unblock";
    public static final String RES_CAN_BLOCK_NUMBERS = "can_block";
    public static final String RES_NUMBER_IS_BLOCKED = "blocked";
    public static final String RES_NUM_ROWS_DELETED = "num_deleted";

}
