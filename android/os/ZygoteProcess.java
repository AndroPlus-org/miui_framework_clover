// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;
import android.util.Slog;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Referenced classes of package android.os:
//            ZygoteStartFailedEx

public class ZygoteProcess
{
    public static class ZygoteState
    {

        public static ZygoteState connect(String s)
            throws IOException
        {
            LocalSocket localsocket = new LocalSocket();
            Object obj;
            obj = JVM INSTR new #43  <Class LocalSocketAddress>;
            ((LocalSocketAddress) (obj)).LocalSocketAddress(s, android.net.LocalSocketAddress.Namespace.RESERVED);
            localsocket.connect(((LocalSocketAddress) (obj)));
            obj = JVM INSTR new #57  <Class DataInputStream>;
            ((DataInputStream) (obj)).DataInputStream(localsocket.getInputStream());
            Object obj1;
            obj1 = JVM INSTR new #66  <Class OutputStreamWriter>;
            ((OutputStreamWriter) (obj1)).OutputStreamWriter(localsocket.getOutputStream());
            obj1 = new BufferedWriter(((java.io.Writer) (obj1)), 256);
            String s1 = ZygoteProcess._2D_wrap0(((BufferedWriter) (obj1)), ((DataInputStream) (obj)));
            Log.i("Zygote", (new StringBuilder()).append("Process: zygote socket ").append(s).append(" opened, supported ABIS: ").append(s1).toString());
            return new ZygoteState(localsocket, ((DataInputStream) (obj)), ((BufferedWriter) (obj1)), Arrays.asList(s1.split(",")));
            s;
_L2:
            try
            {
                localsocket.close();
            }
            catch(IOException ioexception) { }
            throw s;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void close()
        {
            try
            {
                socket.close();
            }
            catch(IOException ioexception)
            {
                Log.e("ZygoteProcess", "I/O exception on routine close", ioexception);
            }
            mClosed = true;
        }

        boolean isClosed()
        {
            return mClosed;
        }

        boolean matches(String s)
        {
            return abiList.contains(s);
        }

        final List abiList;
        final DataInputStream inputStream;
        boolean mClosed;
        final LocalSocket socket;
        final BufferedWriter writer;

        private ZygoteState(LocalSocket localsocket, DataInputStream datainputstream, BufferedWriter bufferedwriter, List list)
        {
            socket = localsocket;
            inputStream = datainputstream;
            writer = bufferedwriter;
            abiList = list;
        }
    }


    static String _2D_wrap0(BufferedWriter bufferedwriter, DataInputStream datainputstream)
    {
        return getAbiList(bufferedwriter, datainputstream);
    }

    public ZygoteProcess(String s, String s1)
    {
        mSocket = s;
        mSecondarySocket = s1;
    }

    private static String getAbiList(BufferedWriter bufferedwriter, DataInputStream datainputstream)
        throws IOException
    {
        bufferedwriter.write("1");
        bufferedwriter.newLine();
        bufferedwriter.write("--query-abi-list");
        bufferedwriter.newLine();
        bufferedwriter.flush();
        bufferedwriter = new byte[datainputstream.readInt()];
        datainputstream.readFully(bufferedwriter);
        return new String(bufferedwriter, StandardCharsets.US_ASCII);
    }

    private ZygoteState openZygoteSocketIfNeeded(String s)
        throws ZygoteStartFailedEx
    {
        Preconditions.checkState(Thread.holdsLock(mLock), "ZygoteProcess lock not held");
        if(primaryZygoteState == null || primaryZygoteState.isClosed())
            try
            {
                primaryZygoteState = ZygoteState.connect(mSocket);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new ZygoteStartFailedEx("Error connecting to primary zygote", s);
            }
        if(primaryZygoteState.matches(s))
            return primaryZygoteState;
        if(secondaryZygoteState == null || secondaryZygoteState.isClosed())
            try
            {
                secondaryZygoteState = ZygoteState.connect(mSecondarySocket);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new ZygoteStartFailedEx("Error connecting to secondary zygote", s);
            }
        if(secondaryZygoteState.matches(s))
            return secondaryZygoteState;
        else
            throw new ZygoteStartFailedEx((new StringBuilder()).append("Unsupported zygote ABI: ").append(s).toString());
    }

    private Process.ProcessStartResult startViaZygote(String s, String s1, int i, int j, int ai[], int k, int l, 
            int i1, String s2, String s3, String s4, String s5, String s6, String as[])
        throws ZygoteStartFailedEx
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        arraylist.add("--runtime-args");
        arraylist.add((new StringBuilder()).append("--setuid=").append(i).toString());
        arraylist.add((new StringBuilder()).append("--setgid=").append(j).toString());
        if((k & 0x10) != 0)
            arraylist.add("--enable-jni-logging");
        if((k & 8) != 0)
            arraylist.add("--enable-safemode");
        if((k & 1) != 0)
            arraylist.add("--enable-jdwp");
        if((k & 2) != 0)
            arraylist.add("--enable-checkjni");
        if((k & 0x20) != 0)
            arraylist.add("--generate-debug-info");
        if((k & 0x40) != 0)
            arraylist.add("--always-jit");
        if((k & 0x80) != 0)
            arraylist.add("--native-debuggable");
        if((k & 0x100) != 0)
            arraylist.add("--java-debuggable");
        if((k & 4) != 0)
            arraylist.add("--enable-assert");
        if(l == 1)
            arraylist.add("--mount-external-default");
        else
        if(l == 2)
            arraylist.add("--mount-external-read");
        else
        if(l == 3)
            arraylist.add("--mount-external-write");
        arraylist.add((new StringBuilder()).append("--target-sdk-version=").append(i1).toString());
        if(ai != null && ai.length > 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("--setgroups=");
            j = ai.length;
            for(i = 0; i < j; i++)
            {
                if(i != 0)
                    stringbuilder.append(',');
                stringbuilder.append(ai[i]);
            }

            arraylist.add(stringbuilder.toString());
        }
        if(s1 != null)
            arraylist.add((new StringBuilder()).append("--nice-name=").append(s1).toString());
        if(s2 != null)
            arraylist.add((new StringBuilder()).append("--seinfo=").append(s2).toString());
        if(s4 != null)
            arraylist.add((new StringBuilder()).append("--instruction-set=").append(s4).toString());
        if(s5 != null)
            arraylist.add((new StringBuilder()).append("--app-data-dir=").append(s5).toString());
        if(s6 != null)
        {
            arraylist.add("--invoke-with");
            arraylist.add(s6);
        }
        arraylist.add(s);
        if(as != null)
        {
            i = 0;
            for(j = as.length; i < j; i++)
                arraylist.add(as[i]);

        }
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
        s1 = zygoteSendArgsAndGetResult(openZygoteSocketIfNeeded(s3), arraylist);
        s;
        JVM INSTR monitorexit ;
        return s1;
        s1;
        throw s1;
    }

    public static void waitForConnectionToZygote(String s)
    {
        int i = 20;
        while(i >= 0) 
        {
            try
            {
                ZygoteState.connect(s).close();
                return;
            }
            catch(IOException ioexception)
            {
                Log.w("ZygoteProcess", (new StringBuilder()).append("Got error connecting to zygote, retrying. msg= ").append(ioexception.getMessage()).toString());
            }
            try
            {
                Thread.sleep(1000L);
            }
            catch(InterruptedException interruptedexception) { }
            i--;
        }
        Slog.wtf("ZygoteProcess", (new StringBuilder()).append("Failed to connect to Zygote through socket ").append(s).toString());
    }

    private static Process.ProcessStartResult zygoteSendArgsAndGetResult(ZygoteState zygotestate, ArrayList arraylist)
        throws ZygoteStartFailedEx
    {
        int i;
        int j;
        try
        {
            i = arraylist.size();
        }
        // Misplaced declaration of an exception variable
        catch(ArrayList arraylist)
        {
            zygotestate.close();
            throw new ZygoteStartFailedEx(arraylist);
        }
        j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        if(((String)arraylist.get(j)).indexOf('\n') >= 0)
        {
            arraylist = JVM INSTR new #85  <Class ZygoteStartFailedEx>;
            arraylist.ZygoteStartFailedEx("embedded newlines not allowed");
            throw arraylist;
        }
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        BufferedWriter bufferedwriter;
        DataInputStream datainputstream;
        bufferedwriter = zygotestate.writer;
        datainputstream = zygotestate.inputStream;
        bufferedwriter.write(Integer.toString(arraylist.size()));
        bufferedwriter.newLine();
        j = 0;
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        bufferedwriter.write((String)arraylist.get(j));
        bufferedwriter.newLine();
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        bufferedwriter.flush();
        arraylist = JVM INSTR new #261 <Class Process$ProcessStartResult>;
        arraylist.Process.ProcessStartResult();
        arraylist.pid = datainputstream.readInt();
        arraylist.usingWrapper = datainputstream.readBoolean();
        if(((Process.ProcessStartResult) (arraylist)).pid < 0)
        {
            arraylist = JVM INSTR new #85  <Class ZygoteStartFailedEx>;
            arraylist.ZygoteStartFailedEx("fork() failed");
            throw arraylist;
        }
        return arraylist;
    }

    public void establishZygoteConnectionForAbi(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        openZygoteSocketIfNeeded(s);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        ZygoteStartFailedEx zygotestartfailedex;
        zygotestartfailedex;
        throw new RuntimeException((new StringBuilder()).append("Unable to connect to zygote for abi: ").append(s).toString(), zygotestartfailedex);
    }

    public boolean preloadDefault(String s)
        throws ZygoteStartFailedEx, IOException
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        s = openZygoteSocketIfNeeded(s);
        ((ZygoteState) (s)).writer.write("1");
        ((ZygoteState) (s)).writer.newLine();
        ((ZygoteState) (s)).writer.write("--preload-default");
        ((ZygoteState) (s)).writer.newLine();
        ((ZygoteState) (s)).writer.flush();
        i = ((ZygoteState) (s)).inputStream.readInt();
        if(i == 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public boolean preloadPackageForAbi(String s, String s1, String s2, String s3)
        throws ZygoteStartFailedEx, IOException
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        s3 = openZygoteSocketIfNeeded(s3);
        ((ZygoteState) (s3)).writer.write("4");
        ((ZygoteState) (s3)).writer.newLine();
        ((ZygoteState) (s3)).writer.write("--preload-package");
        ((ZygoteState) (s3)).writer.newLine();
        ((ZygoteState) (s3)).writer.write(s);
        ((ZygoteState) (s3)).writer.newLine();
        ((ZygoteState) (s3)).writer.write(s1);
        ((ZygoteState) (s3)).writer.newLine();
        ((ZygoteState) (s3)).writer.write(s2);
        ((ZygoteState) (s3)).writer.newLine();
        ((ZygoteState) (s3)).writer.flush();
        i = ((ZygoteState) (s3)).inputStream.readInt();
        if(i == 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public final Process.ProcessStartResult start(String s, String s1, int i, int j, int ai[], int k, int l, 
            int i1, String s2, String s3, String s4, String s5, String s6, String as[])
    {
        try
        {
            s = startViaZygote(s, s1, i, j, ai, k, l, i1, s2, s3, s4, s5, s6, as);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("ZygoteProcess", "Starting VM process through Zygote failed");
            throw new RuntimeException("Starting VM process through Zygote failed", s);
        }
        return s;
    }

    private static final String LOG_TAG = "ZygoteProcess";
    static final int ZYGOTE_RETRY_MILLIS = 500;
    private final Object mLock = new Object();
    private final String mSecondarySocket;
    private final String mSocket;
    private ZygoteState primaryZygoteState;
    private ZygoteState secondaryZygoteState;
}
