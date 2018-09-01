// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.os.StrictMode;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Slog;
import dalvik.system.SocketTagger;
import java.io.FileDescriptor;
import java.net.SocketException;

public final class NetworkManagementSocketTagger extends SocketTagger
{
    public static class SocketTags
    {

        public int statsTag;
        public int statsUid;

        public SocketTags()
        {
            statsTag = -1;
            statsUid = -1;
        }
    }


    public NetworkManagementSocketTagger()
    {
    }

    public static int getThreadSocketStatsTag()
    {
        return ((SocketTags)threadSocketTags.get()).statsTag;
    }

    public static void install()
    {
        SocketTagger.set(new NetworkManagementSocketTagger());
    }

    public static int kernelToTag(String s)
    {
        int i = s.length();
        if(i > 10)
            return Long.decode(s.substring(0, i - 8)).intValue();
        else
            return 0;
    }

    private static native int native_deleteTagData(int i, int j);

    private static native int native_setCounterSet(int i, int j);

    private static native int native_tagSocketFd(FileDescriptor filedescriptor, int i, int j);

    private static native int native_untagSocketFd(FileDescriptor filedescriptor);

    public static void resetKernelUidStats(int i)
    {
        if(SystemProperties.getBoolean("net.qtaguid_enabled", false))
        {
            int j = native_deleteTagData(0, i);
            if(j < 0)
                Slog.w("NetworkManagementSocketTagger", (new StringBuilder()).append("problem clearing counters for uid ").append(i).append(" : errno ").append(j).toString());
        }
    }

    public static void setKernelCounterSet(int i, int j)
    {
        if(SystemProperties.getBoolean("net.qtaguid_enabled", false))
        {
            int k = native_setCounterSet(j, i);
            if(k < 0)
                Log.w("NetworkManagementSocketTagger", (new StringBuilder()).append("setKernelCountSet(").append(i).append(", ").append(j).append(") failed with errno ").append(k).toString());
        }
    }

    public static int setThreadSocketStatsTag(int i)
    {
        int j = ((SocketTags)threadSocketTags.get()).statsTag;
        ((SocketTags)threadSocketTags.get()).statsTag = i;
        return j;
    }

    public static int setThreadSocketStatsUid(int i)
    {
        int j = ((SocketTags)threadSocketTags.get()).statsUid;
        ((SocketTags)threadSocketTags.get()).statsUid = i;
        return j;
    }

    private void tagSocketFd(FileDescriptor filedescriptor, int i, int j)
    {
        if(i == -1 && j == -1)
            return;
        if(SystemProperties.getBoolean("net.qtaguid_enabled", false))
        {
            int k = native_tagSocketFd(filedescriptor, i, j);
            if(k < 0)
                Log.i("NetworkManagementSocketTagger", (new StringBuilder()).append("tagSocketFd(").append(filedescriptor.getInt$()).append(", ").append(i).append(", ").append(j).append(") failed with errno").append(k).toString());
        }
    }

    private void unTagSocketFd(FileDescriptor filedescriptor)
    {
        SocketTags sockettags = (SocketTags)threadSocketTags.get();
        if(sockettags.statsTag == -1 && sockettags.statsUid == -1)
            return;
        if(SystemProperties.getBoolean("net.qtaguid_enabled", false))
        {
            int i = native_untagSocketFd(filedescriptor);
            if(i < 0)
                Log.w("NetworkManagementSocketTagger", (new StringBuilder()).append("untagSocket(").append(filedescriptor.getInt$()).append(") failed with errno ").append(i).toString());
        }
    }

    public void tag(FileDescriptor filedescriptor)
        throws SocketException
    {
        SocketTags sockettags = (SocketTags)threadSocketTags.get();
        if(sockettags.statsTag == -1 && StrictMode.vmUntaggedSocketEnabled())
            StrictMode.onUntaggedSocket();
        tagSocketFd(filedescriptor, sockettags.statsTag, sockettags.statsUid);
    }

    public void untag(FileDescriptor filedescriptor)
        throws SocketException
    {
        unTagSocketFd(filedescriptor);
    }

    private static final boolean LOGD = false;
    public static final String PROP_QTAGUID_ENABLED = "net.qtaguid_enabled";
    private static final String TAG = "NetworkManagementSocketTagger";
    private static ThreadLocal threadSocketTags = new ThreadLocal() {

        protected SocketTags initialValue()
        {
            return new SocketTags();
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;

}
