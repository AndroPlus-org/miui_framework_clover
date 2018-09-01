// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;


// Referenced classes of package android.media.midi:
//            MidiReceiver

public abstract class MidiSender
{

    public MidiSender()
    {
    }

    public void connect(MidiReceiver midireceiver)
    {
        if(midireceiver == null)
        {
            throw new NullPointerException("receiver null in MidiSender.connect");
        } else
        {
            onConnect(midireceiver);
            return;
        }
    }

    public void disconnect(MidiReceiver midireceiver)
    {
        if(midireceiver == null)
        {
            throw new NullPointerException("receiver null in MidiSender.disconnect");
        } else
        {
            onDisconnect(midireceiver);
            return;
        }
    }

    public abstract void onConnect(MidiReceiver midireceiver);

    public abstract void onDisconnect(MidiReceiver midireceiver);
}
