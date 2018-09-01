// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Locale;

public class WifiSsid
    implements Parcelable
{

    private WifiSsid()
    {
        octets = new ByteArrayOutputStream(32);
    }

    WifiSsid(WifiSsid wifissid)
    {
        this();
    }

    private void convertToBytes(String s)
    {
        int i = 0;
        do
        {
            if(i >= s.length())
                break;
            char c = s.charAt(i);
            switch(c)
            {
            default:
                octets.write(c);
                i++;
                break;

            case 92: // '\\'
                i++;
                switch(s.charAt(i))
                {
                default:
                    break;

                case 34: // '"'
                    octets.write(34);
                    i++;
                    break;

                case 92: // '\\'
                    octets.write(92);
                    i++;
                    break;

                case 110: // 'n'
                    octets.write(10);
                    i++;
                    break;

                case 114: // 'r'
                    octets.write(13);
                    i++;
                    break;

                case 116: // 't'
                    octets.write(9);
                    i++;
                    break;

                case 101: // 'e'
                    octets.write(27);
                    i++;
                    break;

                case 120: // 'x'
                    int j = i + 1;
                    try
                    {
                        i = Integer.parseInt(s.substring(j, j + 2), 16);
                    }
                    catch(NumberFormatException numberformatexception)
                    {
                        i = -1;
                    }
                    if(i < 0)
                    {
                        int l = Character.digit(s.charAt(j), 16);
                        i = j;
                        if(l >= 0)
                        {
                            octets.write(l);
                            i = j + 1;
                        }
                    } else
                    {
                        octets.write(i);
                        i = j + 2;
                    }
                    break;

                case 48: // '0'
                case 49: // '1'
                case 50: // '2'
                case 51: // '3'
                case 52: // '4'
                case 53: // '5'
                case 54: // '6'
                case 55: // '7'
                    int i1 = s.charAt(i) - 48;
                    int j1 = i + 1;
                    int k = j1;
                    i = i1;
                    if(s.charAt(j1) >= '0')
                    {
                        k = j1;
                        i = i1;
                        if(s.charAt(j1) <= '7')
                        {
                            i = (i1 * 8 + s.charAt(j1)) - 48;
                            k = j1 + 1;
                        }
                    }
                    i1 = k;
                    j1 = i;
                    if(s.charAt(k) >= '0')
                    {
                        i1 = k;
                        j1 = i;
                        if(s.charAt(k) <= '7')
                        {
                            j1 = (i * 8 + s.charAt(k)) - 48;
                            i1 = k + 1;
                        }
                    }
                    octets.write(j1);
                    i = i1;
                    break;
                }
                break;
            }
        } while(true);
    }

    public static WifiSsid createFromAsciiEncoded(String s)
    {
        WifiSsid wifissid = new WifiSsid();
        wifissid.convertToBytes(s);
        return wifissid;
    }

    public static WifiSsid createFromByteArray(byte abyte0[])
    {
        WifiSsid wifissid = new WifiSsid();
        if(abyte0 != null)
            wifissid.octets.write(abyte0, 0, abyte0.length);
        return wifissid;
    }

    public static WifiSsid createFromHex(String s)
    {
        WifiSsid wifissid;
        String s1;
label0:
        {
            wifissid = new WifiSsid();
            if(s == null)
                return wifissid;
            if(!s.startsWith("0x"))
            {
                s1 = s;
                if(!s.startsWith("0X"))
                    break label0;
            }
            s1 = s.substring(2);
        }
        int i = 0;
        while(i < s1.length() - 1) 
        {
            int j;
            try
            {
                j = Integer.parseInt(s1.substring(i, i + 2), 16);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                j = 0;
            }
            wifissid.octets.write(j);
            i += 2;
        }
        return wifissid;
    }

    private boolean isArrayAllZeroes(byte abyte0[])
    {
        for(int i = 0; i < abyte0.length; i++)
            if(abyte0[i] != 0)
                return false;

        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof WifiSsid))
        {
            return false;
        } else
        {
            obj = (WifiSsid)obj;
            return Arrays.equals(octets.toByteArray(), ((WifiSsid) (obj)).octets.toByteArray());
        }
    }

    public String getHexString()
    {
        if(octets.size() == 0)
            return "<unknown ssid>";
        String s = "0x";
        byte abyte0[] = getOctets();
        for(int i = 0; i < octets.size(); i++)
            s = (new StringBuilder()).append(s).append(String.format(Locale.US, "%02x", new Object[] {
                Byte.valueOf(abyte0[i])
            })).toString();

        if(octets.size() <= 0)
            s = null;
        return s;
    }

    public byte[] getOctets()
    {
        return octets.toByteArray();
    }

    public int hashCode()
    {
        return Arrays.hashCode(octets.toByteArray());
    }

    public boolean isHidden()
    {
        return isArrayAllZeroes(octets.toByteArray());
    }

    public String toString()
    {
        byte abyte0[] = octets.toByteArray();
        if(octets.size() <= 0 || isArrayAllZeroes(abyte0))
            return "";
        Object obj = Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        CharBuffer charbuffer = CharBuffer.allocate(32);
        obj = ((CharsetDecoder) (obj)).decode(ByteBuffer.wrap(abyte0), charbuffer, true);
        charbuffer.flip();
        if(((CoderResult) (obj)).isError())
            return "<unknown ssid>";
        else
            return charbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(octets.size());
        parcel.writeByteArray(octets.toByteArray());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiSsid createFromParcel(Parcel parcel)
        {
            WifiSsid wifissid = new WifiSsid(null);
            int i = parcel.readInt();
            byte abyte0[] = new byte[i];
            parcel.readByteArray(abyte0);
            wifissid.octets.write(abyte0, 0, i);
            return wifissid;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiSsid[] newArray(int i)
        {
            return new WifiSsid[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int HEX_RADIX = 16;
    public static final String NONE = "<unknown ssid>";
    private static final String TAG = "WifiSsid";
    public final ByteArrayOutputStream octets;

}
