// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.net.Credentials;
import android.net.LocalSocket;
import android.os.*;
import android.system.*;
import android.util.Log;
import dalvik.system.VMRuntime;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import libcore.io.IoUtils;

// Referenced classes of package com.android.internal.os:
//            RoSystemProperties, ZygoteSecurityException, WrapperInit, ZygoteInit, 
//            ZygoteServer, Zygote

class ZygoteConnection
{
    static class Arguments
    {

        private void parseArgs(String as[])
            throws IllegalArgumentException
        {
            int i;
            boolean flag;
            i = 0;
            flag = false;
_L11:
            int j = i;
            if(i >= as.length) goto _L2; else goto _L1
_L1:
            Object obj = as[i];
            if(!((String) (obj)).equals("--")) goto _L4; else goto _L3
_L3:
            j = i + 1;
_L2:
            boolean flag1;
            int ai[];
            int k;
            if(abiListQuery)
            {
                if(as.length - j > 0)
                    throw new IllegalArgumentException("Unexpected arguments after --query-abi-list.");
            } else
            if(preloadPackage != null)
            {
                if(as.length - j > 0)
                    throw new IllegalArgumentException("Unexpected arguments after --preload-package.");
            } else
            if(!preloadDefault)
            {
                if(!flag)
                    throw new IllegalArgumentException((new StringBuilder()).append("Unexpected argument : ").append(as[j]).toString());
                remainingArgs = new String[as.length - j];
                System.arraycopy(as, j, remainingArgs, 0, remainingArgs.length);
            }
            break MISSING_BLOCK_LABEL_1397;
_L4:
            if(!((String) (obj)).startsWith("--setuid=")) goto _L6; else goto _L5
_L5:
            if(uidSpecified)
                throw new IllegalArgumentException("Duplicate arg specified");
            uidSpecified = true;
            uid = Integer.parseInt(((String) (obj)).substring(((String) (obj)).indexOf('=') + 1));
            flag1 = flag;
            j = i;
_L8:
            i = j + 1;
            flag = flag1;
            continue; /* Loop/switch isn't completed */
_L6:
            if(((String) (obj)).startsWith("--setgid="))
            {
                if(gidSpecified)
                    throw new IllegalArgumentException("Duplicate arg specified");
                gidSpecified = true;
                gid = Integer.parseInt(((String) (obj)).substring(((String) (obj)).indexOf('=') + 1));
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--target-sdk-version="))
            {
                if(targetSdkVersionSpecified)
                    throw new IllegalArgumentException("Duplicate target-sdk-version specified");
                targetSdkVersionSpecified = true;
                targetSdkVersion = Integer.parseInt(((String) (obj)).substring(((String) (obj)).indexOf('=') + 1));
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--enable-jdwp"))
            {
                debugFlags = debugFlags | 1;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--enable-safemode"))
            {
                debugFlags = debugFlags | 8;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--enable-checkjni"))
            {
                debugFlags = debugFlags | 2;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--generate-debug-info"))
            {
                debugFlags = debugFlags | 0x20;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--always-jit"))
            {
                debugFlags = debugFlags | 0x40;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--native-debuggable"))
            {
                debugFlags = debugFlags | 0x80;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--java-debuggable"))
            {
                debugFlags = debugFlags | 0x100;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--enable-jni-logging"))
            {
                debugFlags = debugFlags | 0x10;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--enable-assert"))
            {
                debugFlags = debugFlags | 4;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--runtime-args"))
            {
                flag1 = true;
                j = i;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--seinfo="))
            {
                if(seInfoSpecified)
                    throw new IllegalArgumentException("Duplicate arg specified");
                seInfoSpecified = true;
                seInfo = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1);
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--capabilities="))
            {
                if(capabilitiesSpecified)
                    throw new IllegalArgumentException("Duplicate arg specified");
                capabilitiesSpecified = true;
                obj = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1).split(",", 2);
                if(obj.length == 1)
                {
                    effectiveCapabilities = Long.decode(obj[0]).longValue();
                    permittedCapabilities = effectiveCapabilities;
                    j = i;
                    flag1 = flag;
                } else
                {
                    permittedCapabilities = Long.decode(obj[0]).longValue();
                    effectiveCapabilities = Long.decode(obj[1]).longValue();
                    j = i;
                    flag1 = flag;
                }
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--rlimit="))
            {
                obj = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1).split(",");
                if(obj.length != 3)
                    throw new IllegalArgumentException("--rlimit= should have 3 comma-delimited ints");
                ai = new int[obj.length];
                for(j = 0; j < obj.length; j++)
                    ai[j] = Integer.parseInt(obj[j]);

                if(rlimits == null)
                    rlimits = new ArrayList();
                rlimits.add(ai);
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--setgroups="))
            {
                if(gids != null)
                    throw new IllegalArgumentException("Duplicate arg specified");
                obj = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1).split(",");
                gids = new int[obj.length];
                k = obj.length - 1;
                do
                {
                    j = i;
                    flag1 = flag;
                    if(k < 0)
                        continue; /* Loop/switch isn't completed */
                    gids[k] = Integer.parseInt(obj[k]);
                    k--;
                } while(true);
            }
            if(((String) (obj)).equals("--invoke-with"))
            {
                if(invokeWith != null)
                    throw new IllegalArgumentException("Duplicate arg specified");
                j = i + 1;
                try
                {
                    invokeWith = as[j];
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    throw new IllegalArgumentException("--invoke-with requires argument");
                }
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--nice-name="))
            {
                if(niceName != null)
                    throw new IllegalArgumentException("Duplicate arg specified");
                niceName = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1);
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--mount-external-default"))
            {
                mountExternal = 1;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--mount-external-read"))
            {
                mountExternal = 2;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--mount-external-write"))
            {
                mountExternal = 3;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--query-abi-list"))
            {
                abiListQuery = true;
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--instruction-set="))
            {
                instructionSet = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1);
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).startsWith("--app-data-dir="))
            {
                appDataDir = ((String) (obj)).substring(((String) (obj)).indexOf('=') + 1);
                j = i;
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            if(((String) (obj)).equals("--preload-package"))
            {
                i++;
                preloadPackage = as[i];
                i++;
                preloadPackageLibs = as[i];
                j = i + 1;
                preloadPackageCacheKey = as[j];
                flag1 = flag;
                continue; /* Loop/switch isn't completed */
            }
            j = i;
            if(!((String) (obj)).equals("--preload-default"))
                break; /* Loop/switch isn't completed */
            preloadDefault = true;
            j = i;
            flag1 = flag;
            if(true) goto _L8; else goto _L7
_L7:
            if(true) goto _L2; else goto _L9
_L9:
            return;
            if(true) goto _L11; else goto _L10
_L10:
        }

        boolean abiListQuery;
        String appDataDir;
        boolean capabilitiesSpecified;
        int debugFlags;
        long effectiveCapabilities;
        int gid;
        boolean gidSpecified;
        int gids[];
        String instructionSet;
        String invokeWith;
        int mountExternal;
        String niceName;
        long permittedCapabilities;
        boolean preloadDefault;
        String preloadPackage;
        String preloadPackageCacheKey;
        String preloadPackageLibs;
        String remainingArgs[];
        ArrayList rlimits;
        String seInfo;
        boolean seInfoSpecified;
        int targetSdkVersion;
        boolean targetSdkVersionSpecified;
        int uid;
        boolean uidSpecified;

        Arguments(String as[])
            throws IllegalArgumentException
        {
            uid = 0;
            gid = 0;
            mountExternal = 0;
            parseArgs(as);
        }
    }


    ZygoteConnection(LocalSocket localsocket, String s)
        throws IOException
    {
        mSocket = localsocket;
        abiList = s;
        mSocketOutStream = new DataOutputStream(localsocket.getOutputStream());
        mSocketReader = new BufferedReader(new InputStreamReader(localsocket.getInputStream()), 256);
        mSocket.setSoTimeout(1000);
        try
        {
            peer = mSocket.getPeerCredentials();
        }
        // Misplaced declaration of an exception variable
        catch(LocalSocket localsocket)
        {
            Log.e("Zygote", "Cannot read peer credentials", localsocket);
            throw localsocket;
        }
        isEof = false;
    }

    public static void applyDebuggerSystemProperty(Arguments arguments)
    {
        if(RoSystemProperties.DEBUGGABLE)
            arguments.debugFlags = arguments.debugFlags | 1;
    }

    private static void applyInvokeWithSecurityPolicy(Arguments arguments, Credentials credentials)
        throws ZygoteSecurityException
    {
        int i = credentials.getUid();
        if(arguments.invokeWith != null && i != 0 && (arguments.debugFlags & 1) == 0)
            throw new ZygoteSecurityException("Peer is permitted to specify anexplicit invoke-with wrapper command only for debuggableapplications.");
        else
            return;
    }

    public static void applyInvokeWithSystemProperty(Arguments arguments)
    {
        if(arguments.invokeWith == null && arguments.niceName != null)
        {
            arguments.invokeWith = SystemProperties.get((new StringBuilder()).append("wrap.").append(arguments.niceName).toString());
            if(arguments.invokeWith != null && arguments.invokeWith.length() == 0)
                arguments.invokeWith = null;
        }
    }

    private static void applyUidSecurityPolicy(Arguments arguments, Credentials credentials)
        throws ZygoteSecurityException
    {
        if(credentials.getUid() == 1000)
        {
            boolean flag;
            if(FactoryTest.getMode() == 0)
                flag = true;
            else
                flag = false;
            if(flag && arguments.uidSpecified && arguments.uid < 1000)
                throw new ZygoteSecurityException("System UID may not launch process with UID < 1000");
        }
        if(!arguments.uidSpecified)
        {
            arguments.uid = credentials.getUid();
            arguments.uidSpecified = true;
        }
        if(!arguments.gidSpecified)
        {
            arguments.gid = credentials.getGid();
            arguments.gidSpecified = true;
        }
    }

    private void handleAbiListQuery()
    {
        try
        {
            byte abyte0[] = abiList.getBytes(StandardCharsets.US_ASCII);
            mSocketOutStream.writeInt(abyte0.length);
            mSocketOutStream.write(abyte0);
            return;
        }
        catch(IOException ioexception)
        {
            throw new IllegalStateException("Error writing to command socket", ioexception);
        }
    }

    private Runnable handleChildProc(Arguments arguments, FileDescriptor afiledescriptor[], FileDescriptor filedescriptor)
    {
        int i;
        i = 0;
        closeSocket();
        if(afiledescriptor == null) goto _L2; else goto _L1
_L1:
        int j;
        Os.dup2(afiledescriptor[0], OsConstants.STDIN_FILENO);
        Os.dup2(afiledescriptor[1], OsConstants.STDOUT_FILENO);
        Os.dup2(afiledescriptor[2], OsConstants.STDERR_FILENO);
        j = afiledescriptor.length;
_L3:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        IoUtils.closeQuietly(afiledescriptor[i]);
        i++;
        if(true) goto _L3; else goto _L2
        afiledescriptor;
        Log.e("Zygote", "Error reopening stdio", afiledescriptor);
_L2:
        if(arguments.niceName != null)
            Process.setArgV0(arguments.niceName);
        Trace.traceEnd(64L);
        if(arguments.invokeWith != null)
        {
            WrapperInit.execApplication(arguments.invokeWith, arguments.niceName, arguments.targetSdkVersion, VMRuntime.getCurrentInstructionSet(), filedescriptor, arguments.remainingArgs);
            throw new IllegalStateException("WrapperInit.execApplication unexpectedly returned");
        } else
        {
            return ZygoteInit.zygoteInit(arguments.targetSdkVersion, arguments.remainingArgs, null);
        }
    }

    private void handleParentProc(int i, FileDescriptor afiledescriptor[], FileDescriptor filedescriptor)
    {
        int i1;
        boolean flag;
        boolean flag1;
        if(i > 0)
            setChildPgid(i);
        if(afiledescriptor != null)
        {
            int j = 0;
            for(int l = afiledescriptor.length; j < l; j++)
                IoUtils.closeQuietly(afiledescriptor[j]);

        }
        flag = false;
        flag1 = flag;
        i1 = i;
        if(filedescriptor == null) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        i1 = i;
        if(i <= 0) goto _L2; else goto _L3
_L3:
        int k;
        byte byte0;
        byte0 = -1;
        k = byte0;
        StructPollfd astructpollfd[] = new StructPollfd[1];
        k = byte0;
        afiledescriptor = JVM INSTR new #276 <Class StructPollfd>;
        k = byte0;
        afiledescriptor.StructPollfd();
        astructpollfd[0] = afiledescriptor;
        k = byte0;
        afiledescriptor = new byte[4];
        int j1;
        i1 = 30000;
        j1 = 0;
        k = byte0;
        long l1 = System.nanoTime();
_L14:
        k = byte0;
        if(j1 >= afiledescriptor.length || i1 <= 0) goto _L5; else goto _L4
_L4:
        k = byte0;
        astructpollfd[0].fd = filedescriptor;
        k = byte0;
        astructpollfd[0].events = (short)OsConstants.POLLIN;
        k = byte0;
        astructpollfd[0].revents = (short)0;
        k = byte0;
        astructpollfd[0].userData = null;
        k = byte0;
        int k1 = Os.poll(astructpollfd, i1);
        k = byte0;
        int i2 = 30000 - (int)((System.nanoTime() - l1) / 0xf4240L);
        if(k1 <= 0) goto _L7; else goto _L6
_L6:
        k = byte0;
        if((astructpollfd[0].revents & OsConstants.POLLIN) == 0) goto _L5; else goto _L8
_L8:
        k = byte0;
        i1 = Os.read(filedescriptor, afiledescriptor, j1, 1);
        if(i1 >= 0) goto _L10; else goto _L9
_L9:
        k = byte0;
        afiledescriptor = JVM INSTR new #313 <Class RuntimeException>;
        k = byte0;
        afiledescriptor.RuntimeException("Some error");
        k = byte0;
        try
        {
            throw afiledescriptor;
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor afiledescriptor[])
        {
            Log.w("Zygote", "Error reading pid from wrapped process, child may have died", afiledescriptor);
        }
_L12:
        flag1 = flag;
        i1 = i;
        if(k > 0)
        {
            ByteArrayInputStream bytearrayinputstream;
            for(i1 = k; i1 > 0 && i1 != i; i1 = Process.getParentPid(i1));
            if(i1 > 0)
            {
                Log.i("Zygote", (new StringBuilder()).append("Wrapped process has pid ").append(k).toString());
                flag1 = true;
                i1 = k;
            } else
            {
                Log.w("Zygote", (new StringBuilder()).append("Wrapped process reported a pid that is not a child of the process that we forked: childPid=").append(i).append(" innerPid=").append(k).toString());
                flag1 = flag;
                i1 = i;
            }
        }
          goto _L2
_L10:
        j1 += i1;
        i1 = i2;
        continue; /* Loop/switch isn't completed */
_L7:
        i1 = i2;
        if(k1 != 0)
            continue; /* Loop/switch isn't completed */
        k = byte0;
        Log.w("Zygote", "Timed out waiting for child.");
        i1 = i2;
        continue; /* Loop/switch isn't completed */
_L5:
        k = byte0;
        i1 = byte0;
        if(j1 != afiledescriptor.length)
            break MISSING_BLOCK_LABEL_447;
        k = byte0;
        filedescriptor = JVM INSTR new #332 <Class DataInputStream>;
        k = byte0;
        bytearrayinputstream = JVM INSTR new #334 <Class ByteArrayInputStream>;
        k = byte0;
        bytearrayinputstream.ByteArrayInputStream(afiledescriptor);
        k = byte0;
        filedescriptor.DataInputStream(bytearrayinputstream);
        k = byte0;
        i1 = filedescriptor.readInt();
        k = i1;
        if(i1 != -1) goto _L12; else goto _L11
_L11:
        k = i1;
        Log.w("Zygote", "Error reading pid from wrapped process, child may have died");
        k = i1;
          goto _L12
_L2:
        try
        {
            mSocketOutStream.writeInt(i1);
            mSocketOutStream.writeBoolean(flag1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor afiledescriptor[])
        {
            throw new IllegalStateException("Error writing to command socket", afiledescriptor);
        }
        if(true) goto _L14; else goto _L13
_L13:
    }

    private void handlePreload()
    {
        if(!isPreloadComplete())
            break MISSING_BLOCK_LABEL_16;
        mSocketOutStream.writeInt(1);
_L1:
        return;
        try
        {
            preload();
            mSocketOutStream.writeInt(0);
        }
        catch(IOException ioexception)
        {
            throw new IllegalStateException("Error writing to command socket", ioexception);
        }
          goto _L1
    }

    private String[] readArgumentList()
        throws IOException
    {
        int i;
        String s;
        try
        {
            s = mSocketReader.readLine();
        }
        catch(NumberFormatException numberformatexception)
        {
            Log.e("Zygote", "invalid Zygote wire format: non-int at argc");
            throw new IOException("invalid wire format");
        }
        if(s == null)
            return null;
        i = Integer.parseInt(s);
        if(i > 1024)
            throw new IOException("max arg count exceeded");
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
        {
            as[j] = mSocketReader.readLine();
            if(as[j] == null)
                throw new IOException("truncated request");
        }

        return as;
    }

    private void setChildPgid(int i)
    {
        Os.setpgid(i, Os.getpgid(peer.getPid()));
_L1:
        return;
        ErrnoException errnoexception;
        errnoexception;
        Log.i("Zygote", "Zygote: setpgid failed. This is normal if peer is not in our session");
          goto _L1
    }

    void closeSocket()
    {
        mSocket.close();
_L1:
        return;
        IOException ioexception;
        ioexception;
        Log.e("Zygote", "Exception while closing command socket in parent", ioexception);
          goto _L1
    }

    FileDescriptor getFileDesciptor()
    {
        return mSocket.getFileDescriptor();
    }

    protected DataOutputStream getSocketOutputStream()
    {
        return mSocketOutStream;
    }

    protected void handlePreloadPackage(String s, String s1, String s2)
    {
        throw new RuntimeException("Zyogte does not support package preloading");
    }

    boolean isClosedByPeer()
    {
        return isEof;
    }

    protected boolean isPreloadComplete()
    {
        return ZygoteInit.isPreloadComplete();
    }

    protected void preload()
    {
        ZygoteInit.lazyPreload();
    }

    Runnable processOneCommand(ZygoteServer zygoteserver)
    {
        Object obj;
        FileDescriptor afiledescriptor[];
        FileDescriptor filedescriptor;
        Object obj1;
        Object obj2;
        int i;
        try
        {
            obj = readArgumentList();
            afiledescriptor = mSocket.getAncillaryFileDescriptors();
        }
        // Misplaced declaration of an exception variable
        catch(ZygoteServer zygoteserver)
        {
            throw new IllegalStateException("IOException on command socket", zygoteserver);
        }
        if(obj == null)
        {
            isEof = true;
            return null;
        }
        filedescriptor = null;
        obj1 = null;
        Arguments arguments = new Arguments(((String []) (obj)));
        if(arguments.abiListQuery)
        {
            handleAbiListQuery();
            return null;
        }
        if(arguments.preloadDefault)
        {
            handlePreload();
            return null;
        }
        if(arguments.preloadPackage != null)
        {
            handlePreloadPackage(arguments.preloadPackage, arguments.preloadPackageLibs, arguments.preloadPackageCacheKey);
            return null;
        }
        if(arguments.permittedCapabilities != 0L || arguments.effectiveCapabilities != 0L)
            throw new ZygoteSecurityException((new StringBuilder()).append("Client may not specify capabilities: permitted=0x").append(Long.toHexString(arguments.permittedCapabilities)).append(", effective=0x").append(Long.toHexString(arguments.effectiveCapabilities)).toString());
        applyUidSecurityPolicy(arguments, peer);
        applyInvokeWithSecurityPolicy(arguments, peer);
        applyDebuggerSystemProperty(arguments);
        applyInvokeWithSystemProperty(arguments);
        obj = null;
        if(arguments.rlimits != null)
            obj = (int[][])arguments.rlimits.toArray(intArray2d);
        int ai[] = null;
        if(arguments.invokeWith == null)
            break MISSING_BLOCK_LABEL_299;
        int ai1[];
        FileDescriptor filedescriptor1;
        try
        {
            obj1 = Os.pipe2(OsConstants.O_CLOEXEC);
        }
        // Misplaced declaration of an exception variable
        catch(ZygoteServer zygoteserver)
        {
            throw new IllegalStateException("Unable to set up pipe for invoke-with", zygoteserver);
        }
        filedescriptor = obj1[1];
        obj1 = obj1[0];
        Os.fcntlInt(filedescriptor, OsConstants.F_SETFD, 0);
        ai = new int[2];
        ai[0] = filedescriptor.getInt$();
        ai[1] = ((FileDescriptor) (obj1)).getInt$();
        ai1 = new int[2];
        ai1[0] = -1;
        ai1[1] = -1;
        filedescriptor1 = mSocket.getFileDescriptor();
        if(filedescriptor1 != null)
            ai1[0] = filedescriptor1.getInt$();
        filedescriptor1 = zygoteserver.getServerSocketFileDescriptor();
        if(filedescriptor1 != null)
            ai1[1] = filedescriptor1.getInt$();
        i = Zygote.forkAndSpecialize(arguments.uid, arguments.gid, arguments.gids, arguments.debugFlags, ((int [][]) (obj)), arguments.mountExternal, arguments.seInfo, arguments.niceName, ai1, ai, arguments.instructionSet, arguments.appDataDir);
        if(i != 0)
            break MISSING_BLOCK_LABEL_491;
        obj = filedescriptor;
        obj2 = obj1;
        zygoteserver.setForkChild();
        obj = filedescriptor;
        obj2 = obj1;
        zygoteserver.closeServerSocket();
        obj = filedescriptor;
        obj2 = obj1;
        IoUtils.closeQuietly(((FileDescriptor) (obj1)));
        obj2 = null;
        obj = filedescriptor;
        zygoteserver = handleChildProc(arguments, afiledescriptor, filedescriptor);
        IoUtils.closeQuietly(filedescriptor);
        IoUtils.closeQuietly(null);
        return zygoteserver;
        obj = filedescriptor;
        obj2 = obj1;
        IoUtils.closeQuietly(filedescriptor);
        obj = null;
        obj2 = obj1;
        handleParentProc(i, afiledescriptor, ((FileDescriptor) (obj1)));
        IoUtils.closeQuietly(null);
        IoUtils.closeQuietly(((FileDescriptor) (obj1)));
        return null;
        zygoteserver;
        IoUtils.closeQuietly(((FileDescriptor) (obj)));
        IoUtils.closeQuietly(((FileDescriptor) (obj2)));
        throw zygoteserver;
    }

    private static final String TAG = "Zygote";
    private static final int intArray2d[][] = new int[0][0];
    private final String abiList;
    private boolean isEof;
    private final LocalSocket mSocket;
    private final DataOutputStream mSocketOutStream;
    private final BufferedReader mSocketReader;
    private final Credentials peer;

}
