// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.system.*;
import android.util.Log;
import android.util.Slog;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.os:
//            ZygoteConnection

class ZygoteServer
{

    ZygoteServer()
    {
    }

    private ZygoteConnection acceptCommandPeer(String s)
    {
        try
        {
            s = createNewConnection(mServerSocket.accept(), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("IOException during accept()", s);
        }
        return s;
    }

    void closeServerSocket()
    {
        FileDescriptor filedescriptor;
        if(mServerSocket == null)
            break MISSING_BLOCK_LABEL_30;
        filedescriptor = mServerSocket.getFileDescriptor();
        mServerSocket.close();
        if(filedescriptor != null)
            try
            {
                Os.close(filedescriptor);
            }
            catch(IOException ioexception)
            {
                Log.e("ZygoteServer", "Zygote:  error closing sockets", ioexception);
            }
            catch(ErrnoException errnoexception)
            {
                Log.e("ZygoteServer", "Zygote:  error closing descriptor", errnoexception);
            }
        mServerSocket = null;
        return;
    }

    protected ZygoteConnection createNewConnection(LocalSocket localsocket, String s)
        throws IOException
    {
        return new ZygoteConnection(localsocket, s);
    }

    FileDescriptor getServerSocketFileDescriptor()
    {
        return mServerSocket.getFileDescriptor();
    }

    void registerServerSocket(String s)
    {
        int i;
        if(mServerSocket != null)
            break MISSING_BLOCK_LABEL_62;
        s = (new StringBuilder()).append("ANDROID_SOCKET_").append(s).toString();
        LocalServerSocket localserversocket;
        try
        {
            i = Integer.parseInt(System.getenv(s));
        }
        catch(RuntimeException runtimeexception)
        {
            throw new RuntimeException((new StringBuilder()).append(s).append(" unset or invalid").toString(), runtimeexception);
        }
        s = JVM INSTR new #102 <Class FileDescriptor>;
        s.FileDescriptor();
        s.setInt$(i);
        localserversocket = JVM INSTR new #28  <Class LocalServerSocket>;
        localserversocket.LocalServerSocket(s);
        mServerSocket = localserversocket;
        return;
        s;
        throw new RuntimeException((new StringBuilder()).append("Error binding to local socket '").append(i).append("'").toString(), s);
    }

    Runnable runSelectLoop(String s)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist.add(mServerSocket.getFileDescriptor());
        arraylist1.add(null);
_L2:
        int j;
        StructPollfd astructpollfd[] = new StructPollfd[arraylist.size()];
        for(int i = 0; i < astructpollfd.length; i++)
        {
            astructpollfd[i] = new StructPollfd();
            astructpollfd[i].fd = (FileDescriptor)arraylist.get(i);
            astructpollfd[i].events = (short)OsConstants.POLLIN;
        }

        try
        {
            Os.poll(astructpollfd, -1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("poll failed", s);
        }
        j = astructpollfd.length - 1;
_L3:
        if(j < 0) goto _L2; else goto _L1
_L1:
        if((astructpollfd[j].revents & OsConstants.POLLIN) != 0)
        {
label0:
            {
                if(j != 0)
                    break label0;
                ZygoteConnection zygoteconnection = acceptCommandPeer(s);
                arraylist1.add(zygoteconnection);
                arraylist.add(zygoteconnection.getFileDesciptor());
            }
        }
_L4:
        j--;
          goto _L3
          goto _L2
        Object obj;
        ZygoteConnection zygoteconnection1;
        zygoteconnection1 = (ZygoteConnection)arraylist1.get(j);
        obj = zygoteconnection1.processOneCommand(this);
        if(!mIsForkChild)
            break MISSING_BLOCK_LABEL_276;
        if(obj == null)
        {
            try
            {
                obj = JVM INSTR new #176 <Class IllegalStateException>;
                ((IllegalStateException) (obj)).IllegalStateException("command == null");
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
            if(!mIsForkChild)
            {
                Slog.e("ZygoteServer", "Exception executing zygote command: ", ((Throwable) (obj)));
                ((ZygoteConnection)arraylist1.remove(j)).closeSocket();
                arraylist.remove(j);
            } else
            {
                Log.e("ZygoteServer", "Caught post-fork exception in child process.", ((Throwable) (obj)));
                throw obj;
            }
        } else
        {
            return ((Runnable) (obj));
        }
          goto _L4
        if(obj == null)
            break MISSING_BLOCK_LABEL_296;
        obj = JVM INSTR new #176 <Class IllegalStateException>;
        ((IllegalStateException) (obj)).IllegalStateException("command != null");
        throw obj;
        if(zygoteconnection1.isClosedByPeer())
        {
            zygoteconnection1.closeSocket();
            arraylist1.remove(j);
            arraylist.remove(j);
        }
          goto _L4
    }

    void setForkChild()
    {
        mIsForkChild = true;
    }

    private static final String ANDROID_SOCKET_PREFIX = "ANDROID_SOCKET_";
    public static final String TAG = "ZygoteServer";
    private boolean mIsForkChild;
    private LocalServerSocket mServerSocket;
}
