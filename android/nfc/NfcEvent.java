// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;


// Referenced classes of package android.nfc:
//            NfcAdapter

public final class NfcEvent
{

    NfcEvent(NfcAdapter nfcadapter, byte byte0)
    {
        nfcAdapter = nfcadapter;
        peerLlcpMajorVersion = (byte0 & 0xf0) >> 4;
        peerLlcpMinorVersion = byte0 & 0xf;
    }

    public final NfcAdapter nfcAdapter;
    public final int peerLlcpMajorVersion;
    public final int peerLlcpMinorVersion;
}
