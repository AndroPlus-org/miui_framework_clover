// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.io.PrintStream;

// Referenced classes of package android.os:
//            Message, Handler

public class Broadcaster
{
    private class Registration
    {

        Registration next;
        Registration prev;
        int senderWhat;
        int targetWhats[];
        Handler targets[];
        final Broadcaster this$0;

        private Registration()
        {
            this$0 = Broadcaster.this;
            super();
        }

        Registration(Registration registration)
        {
            this();
        }
    }


    public Broadcaster()
    {
    }

    public void broadcast(Message message)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mReg;
        if(obj != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        int i;
        Object obj1;
        i = message.what;
        obj1 = mReg;
        obj = obj1;
_L7:
        if(((Registration) (obj)).senderWhat < i) goto _L2; else goto _L1
_L1:
        Handler ahandler[];
        int ai[];
        int j;
        if(((Registration) (obj)).senderWhat != i)
            break; /* Loop/switch isn't completed */
        ahandler = ((Registration) (obj)).targets;
        ai = ((Registration) (obj)).targetWhats;
        j = ahandler.length;
        i = 0;
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = ahandler[i];
        obj1 = Message.obtain();
        ((Message) (obj1)).copyFrom(message);
        obj1.what = ai[i];
        ((Handler) (obj)).sendMessage(((Message) (obj1)));
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        break; /* Loop/switch isn't completed */
_L2:
        Registration registration = ((Registration) (obj)).next;
        obj = registration;
        if(registration == obj1) goto _L1; else goto _L5
_L5:
        obj = registration;
        if(true) goto _L7; else goto _L6
_L6:
        return;
        message;
        throw message;
    }

    public void cancelRequest(int i, Handler handler, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Registration registration = mReg;
        Registration registration1;
        registration1 = registration;
        if(registration == null)
            return;
          goto _L1
_L3:
        Registration registration2 = registration1.next;
        registration1 = registration2;
        if(registration2 == registration)
            break; /* Loop/switch isn't completed */
        registration1 = registration2;
_L1:
        if(registration1.senderWhat < i) goto _L3; else goto _L2
_L2:
        if(registration1.senderWhat != i) goto _L5; else goto _L4
_L4:
        Handler ahandler[];
        int ai[];
        int k;
        ahandler = registration1.targets;
        ai = registration1.targetWhats;
        k = ahandler.length;
        i = 0;
_L11:
        if(i >= k) goto _L5; else goto _L6
_L6:
        if(ahandler[i] != handler || ai[i] != j) goto _L8; else goto _L7
_L7:
        registration1.targets = new Handler[k - 1];
        registration1.targetWhats = new int[k - 1];
        if(i <= 0)
            break MISSING_BLOCK_LABEL_156;
        System.arraycopy(ahandler, 0, registration1.targets, 0, i);
        System.arraycopy(ai, 0, registration1.targetWhats, 0, i);
        j = k - i - 1;
        if(j == 0) goto _L5; else goto _L9
_L9:
        System.arraycopy(ahandler, i + 1, registration1.targets, i, j);
        System.arraycopy(ai, i + 1, registration1.targetWhats, i, j);
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L8:
        i++;
        if(true) goto _L11; else goto _L10
_L10:
        handler;
        throw handler;
    }

    public void dumpRegistrations()
    {
        this;
        JVM INSTR monitorenter ;
        Registration registration;
        registration = mReg;
        PrintStream printstream = System.out;
        StringBuilder stringbuilder = JVM INSTR new #69  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printstream.println(stringbuilder.append("Broadcaster ").append(this).append(" {").toString());
        if(registration == null) goto _L2; else goto _L1
_L1:
        Registration registration2 = registration;
_L5:
        int i;
        PrintStream printstream1 = System.out;
        StringBuilder stringbuilder1 = JVM INSTR new #69  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        printstream1.println(stringbuilder1.append("    senderWhat=").append(registration2.senderWhat).toString());
        i = registration2.targets.length;
        int j = 0;
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        PrintStream printstream2 = System.out;
        StringBuilder stringbuilder2 = JVM INSTR new #69  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        printstream2.println(stringbuilder2.append("        [").append(registration2.targetWhats[j]).append("] ").append(registration2.targets[j]).toString());
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        Registration registration1 = registration2.next;
        registration2 = registration1;
        if(registration1 != registration) goto _L5; else goto _L2
_L2:
        System.out.println("}");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void request(int i, Handler handler, int j)
    {
        this;
        JVM INSTR monitorenter ;
        if(mReg != null) goto _L2; else goto _L1
_L1:
        Registration registration;
        registration = JVM INSTR new #6   <Class Broadcaster$Registration>;
        registration.this. Registration(null);
        registration.senderWhat = i;
        registration.targets = new Handler[1];
        registration.targetWhats = new int[1];
        registration.targets[0] = handler;
        registration.targetWhats[0] = j;
        mReg = registration;
        registration.next = registration;
        registration.prev = registration;
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Registration registration1 = mReg;
        registration = registration1;
_L9:
        if(registration.senderWhat < i) goto _L4; else goto _L3
_L3:
        if(registration.senderWhat == i) goto _L6; else goto _L5
_L5:
        Registration registration2;
        registration2 = JVM INSTR new #6   <Class Broadcaster$Registration>;
        registration2.this. Registration(null);
        registration2.senderWhat = i;
        registration2.targets = new Handler[1];
        registration2.targetWhats = new int[1];
        registration2.next = registration;
        registration2.prev = registration.prev;
        registration.prev.next = registration2;
        registration.prev = registration2;
        if(registration == mReg && registration.senderWhat > registration2.senderWhat)
            mReg = registration2;
        registration = registration2;
        i = 0;
_L10:
        registration.targets[i] = handler;
        registration.targetWhats[i] = j;
          goto _L7
        handler;
_L11:
        this;
        JVM INSTR monitorexit ;
        throw handler;
_L4:
        registration2 = registration.next;
        registration = registration2;
        if(registration2 == registration1) goto _L3; else goto _L8
_L8:
        registration = registration2;
          goto _L9
_L6:
        Handler ahandler[];
        int ai[];
        int k;
        k = registration.targets.length;
        ahandler = registration.targets;
        ai = registration.targetWhats;
        for(i = 0; i < k; i++)
        {
            if(ahandler[i] != handler)
                continue;
            int l = ai[i];
            if(l != j)
                continue;
            return;
        }

        registration.targets = new Handler[k + 1];
        System.arraycopy(ahandler, 0, registration.targets, 0, k);
        registration.targetWhats = new int[k + 1];
        System.arraycopy(ai, 0, registration.targetWhats, 0, k);
        i = k;
          goto _L10
        handler;
          goto _L11
    }

    private Registration mReg;
}
