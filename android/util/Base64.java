// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.UnsupportedEncodingException;

public class Base64
{
    static abstract class Coder
    {

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte abyte0[], int i, int j, boolean flag);

        public int op;
        public byte output[];

        Coder()
        {
        }
    }

    static class Decoder extends Coder
    {

        public int maxOutputSize(int i)
        {
            return (i * 3) / 4 + 10;
        }

        public boolean process(byte abyte0[], int i, int j, boolean flag)
        {
            int k;
            int l;
            int i1;
            int j1;
            byte abyte1[];
            int ai[];
            if(state == 6)
                return false;
            k = i;
            l = j + i;
            i1 = state;
            j = value;
            j1 = 0;
            abyte1 = output;
            ai = alphabet;
_L12:
            int k1;
            int l1;
            int i2;
            if(k >= l)
                break MISSING_BLOCK_LABEL_816;
            k1 = j1;
            l1 = k;
            i2 = j;
            if(i1 != 0) goto _L2; else goto _L1
_L1:
            i = j;
            do
            {
                if(k + 4 > l)
                    break;
                j = ai[abyte0[k] & 0xff] << 18 | ai[abyte0[k + 1] & 0xff] << 12 | ai[abyte0[k + 2] & 0xff] << 6 | ai[abyte0[k + 3] & 0xff];
                i = j;
                if(j < 0)
                    break;
                abyte1[j1 + 2] = (byte)j;
                abyte1[j1 + 1] = (byte)(j >> 8);
                abyte1[j1] = (byte)(j >> 16);
                j1 += 3;
                k += 4;
                i = j;
            } while(true);
            k1 = j1;
            l1 = k;
            i2 = i;
            if(k < l) goto _L2; else goto _L3
_L3:
            j = i;
            i = j1;
_L25:
            if(!flag)
            {
                state = i1;
                value = j;
                op = i;
                return true;
            }
              goto _L4
_L2:
            k = ai[abyte0[l1] & 0xff];
            i1;
            JVM INSTR tableswitch 0 5: default 292
        //                       0 314
        //                       1 358
        //                       2 408
        //                       3 490
        //                       4 619
        //                       5 665;
               goto _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L5:
            j = i2;
            i = i1;
            j1 = k1;
_L13:
            k = l1 + 1;
            i1 = i;
              goto _L12
_L6:
label0:
            {
                if(k < 0)
                    break label0;
                j = k;
                i = i1 + 1;
                j1 = k1;
            }
              goto _L13
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L14
_L14:
            state = 6;
            return false;
_L7:
label1:
            {
                if(k < 0)
                    break label1;
                j = i2 << 6 | k;
                i = i1 + 1;
                j1 = k1;
            }
              goto _L13
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L15
_L15:
            state = 6;
            return false;
_L8:
            if(k >= 0)
            {
                j = i2 << 6 | k;
                i = i1 + 1;
                j1 = k1;
            } else
            {
label2:
                {
                    if(k != -2)
                        break label2;
                    abyte1[k1] = (byte)(i2 >> 4);
                    i = 4;
                    j1 = k1 + 1;
                    j = i2;
                }
            }
              goto _L13
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L16
_L16:
            state = 6;
            return false;
_L9:
            if(k >= 0)
            {
                j = i2 << 6 | k;
                abyte1[k1 + 2] = (byte)j;
                abyte1[k1 + 1] = (byte)(j >> 8);
                abyte1[k1] = (byte)(j >> 16);
                j1 = k1 + 3;
                i = 0;
            } else
            {
label3:
                {
                    if(k != -2)
                        break label3;
                    abyte1[k1 + 1] = (byte)(i2 >> 2);
                    abyte1[k1] = (byte)(i2 >> 10);
                    j1 = k1 + 2;
                    i = 5;
                    j = i2;
                }
            }
              goto _L13
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L17
_L17:
            state = 6;
            return false;
_L10:
label4:
            {
                if(k != -2)
                    break label4;
                i = i1 + 1;
                j1 = k1;
                j = i2;
            }
              goto _L13
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L18
_L18:
            state = 6;
            return false;
_L11:
            j1 = k1;
            i = i1;
            j = i2;
            if(k == -1) goto _L13; else goto _L19
_L19:
            state = 6;
            return false;
_L4:
            i1;
            JVM INSTR tableswitch 0 5: default 728
        //                       0 741
        //                       1 744
        //                       2 752
        //                       3 772
        //                       4 805
        //                       5 813;
               goto _L20 _L20 _L21 _L22 _L23 _L24 _L20
_L20:
            state = i1;
            op = i;
            return true;
_L21:
            state = 6;
            return false;
_L22:
            j1 = i + 1;
            abyte1[i] = (byte)(j >> 4);
            i = j1;
            continue; /* Loop/switch isn't completed */
_L23:
            j1 = i + 1;
            abyte1[i] = (byte)(j >> 10);
            abyte1[j1] = (byte)(j >> 2);
            i = j1 + 1;
            if(true) goto _L20; else goto _L24
_L24:
            state = 6;
            return false;
            i = j1;
              goto _L25
        }

        private static final int DECODE[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1, 
            -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
            25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
            49, 50, 51, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1
        };
        private static final int DECODE_WEBSAFE[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1, 
            -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
            25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 
            29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
            39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
            49, 50, 51, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
            -1, -1, -1, -1, -1, -1
        };
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int alphabet[];
        private int state;
        private int value;


        public Decoder(int i, byte abyte0[])
        {
            output = abyte0;
            if((i & 8) == 0)
                abyte0 = DECODE;
            else
                abyte0 = DECODE_WEBSAFE;
            alphabet = abyte0;
            state = 0;
            value = 0;
        }
    }

    static class Encoder extends Coder
    {

        public int maxOutputSize(int i)
        {
            return (i * 8) / 5 + 10;
        }

        public boolean process(byte abyte0[], int i, int j, boolean flag)
        {
            byte abyte1[];
            byte abyte4[];
            int k;
            int j1;
            int j2;
            int k2;
            byte byte1;
            int l2;
            abyte1 = alphabet;
            abyte4 = output;
            k = 0;
            j1 = count;
            j2 = i;
            k2 = j + i;
            byte1 = -1;
            j = j2;
            l2 = byte1;
            tailLen;
            JVM INSTR tableswitch 0 2: default 68
        //                       0 75
        //                       1 409
        //                       2 477;
               goto _L1 _L2 _L3 _L4
_L2:
            break MISSING_BLOCK_LABEL_75;
_L1:
            l2 = byte1;
            j = j2;
_L10:
            i = j1;
            j2 = k;
            k = j;
            if(l2 == -1) goto _L6; else goto _L5
_L5:
            abyte4[0] = abyte1[l2 >> 18 & 0x3f];
            i = 1 + 1;
            abyte4[1] = abyte1[l2 >> 12 & 0x3f];
            j2 = i + 1;
            abyte4[i] = abyte1[l2 >> 6 & 0x3f];
            i = j2 + 1;
            abyte4[j2] = abyte1[l2 & 0x3f];
            l2 = j1 - 1;
            if(l2 == 0)
            {
                if(do_cr)
                {
                    j2 = i + 1;
                    abyte4[i] = (byte)13;
                    i = j2;
                }
                j2 = i + 1;
                abyte4[i] = (byte)10;
                l2 = 19;
                i = j2;
            }
_L8:
            if(j + 3 > k2)
                break; /* Loop/switch isn't completed */
            j2 = (abyte0[j] & 0xff) << 16 | (abyte0[j + 1] & 0xff) << 8 | abyte0[j + 2] & 0xff;
            abyte4[i] = abyte1[j2 >> 18 & 0x3f];
            abyte4[i + 1] = abyte1[j2 >> 12 & 0x3f];
            abyte4[i + 2] = abyte1[j2 >> 6 & 0x3f];
            abyte4[i + 3] = abyte1[j2 & 0x3f];
            int k1 = j + 3;
            j = i + 4;
            i = --l2;
            j2 = j;
            k = k1;
            if(l2 == 0)
            {
                i = j;
                if(do_cr)
                {
                    abyte4[j] = (byte)13;
                    i = j + 1;
                }
                abyte4[i] = (byte)10;
                j = 19;
                j2 = i + 1;
                k = k1;
                i = j;
            }
_L6:
            j = k;
            l2 = i;
            i = j2;
            if(true) goto _L8; else goto _L7
_L7:
            break; /* Loop/switch isn't completed */
_L3:
            j = j2;
            l2 = byte1;
            if(i + 2 <= k2)
            {
                j2 = tail[0];
                j = i + 1;
                l2 = (j2 & 0xff) << 16 | (abyte0[i] & 0xff) << 8 | abyte0[j] & 0xff;
                tailLen = 0;
                j++;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            j = j2;
            l2 = byte1;
            if(i + 1 <= k2)
            {
                l2 = tail[0];
                j2 = tail[1];
                j = i + 1;
                l2 = (l2 & 0xff) << 16 | (j2 & 0xff) << 8 | abyte0[i] & 0xff;
                tailLen = 0;
            }
            if(true) goto _L10; else goto _L9
_L9:
            if(!flag) goto _L12; else goto _L11
_L11:
            if(j - tailLen != k2 - 1) goto _L14; else goto _L13
_L13:
            j2 = 0;
            int l;
            int l1;
            if(tailLen > 0)
            {
                abyte0 = tail;
                boolean flag1 = true;
                j2 = abyte0[0];
                l = j;
                j = ((flag1) ? 1 : 0);
            } else
            {
                l = j + 1;
                byte byte0 = abyte0[j];
                j = j2;
                j2 = byte0;
            }
            l1 = (j2 & 0xff) << 4;
            tailLen = tailLen - j;
            j = i + 1;
            abyte4[i] = abyte1[l1 >> 6 & 0x3f];
            j2 = j + 1;
            abyte4[j] = abyte1[l1 & 0x3f];
            if(do_padding)
            {
                i = j2 + 1;
                abyte4[j2] = (byte)61;
                abyte4[i] = (byte)61;
                j2 = i + 1;
            }
            i = j2;
            j = l;
            if(do_newline)
            {
                i = j2;
                if(do_cr)
                {
                    abyte4[j2] = (byte)13;
                    i = j2 + 1;
                }
                abyte4[i] = (byte)10;
                i++;
                j = l;
            }
_L16:
            if(!_2D_assertionsDisabled && tailLen != 0)
                throw new AssertionError();
            break; /* Loop/switch isn't completed */
_L14:
            if(j - tailLen == k2 - 2)
            {
                int i2 = 0;
                int i1;
                if(tailLen > 1)
                {
                    byte abyte5[] = tail;
                    i2 = 1;
                    j2 = abyte5[0];
                    i1 = j;
                    j = i2;
                } else
                {
                    i1 = j + 1;
                    j2 = abyte0[j];
                    j = i2;
                }
                if(tailLen > 0)
                {
                    i2 = tail[j];
                    j++;
                } else
                {
                    i2 = abyte0[i1];
                    i1++;
                }
                j2 = (j2 & 0xff) << 10 | (i2 & 0xff) << 2;
                tailLen = tailLen - j;
                j = i + 1;
                abyte4[i] = abyte1[j2 >> 12 & 0x3f];
                i2 = j + 1;
                abyte4[j] = abyte1[j2 >> 6 & 0x3f];
                i = i2 + 1;
                abyte4[i2] = abyte1[j2 & 0x3f];
                j2 = i;
                if(do_padding)
                {
                    abyte4[i] = (byte)61;
                    j2 = i + 1;
                }
                i = j2;
                j = i1;
                if(do_newline)
                {
                    i = j2;
                    if(do_cr)
                    {
                        abyte4[j2] = (byte)13;
                        i = j2 + 1;
                    }
                    abyte4[i] = (byte)10;
                    i++;
                    j = i1;
                }
            } else
            if(do_newline && i > 0 && l2 != 19)
            {
                if(do_cr)
                {
                    j2 = i + 1;
                    abyte4[i] = (byte)13;
                    i = j2;
                }
                abyte4[i] = (byte)10;
                i++;
            }
            if(true) goto _L16; else goto _L15
_L15:
            j2 = i;
            if(!_2D_assertionsDisabled)
            {
                j2 = i;
                if(j != k2)
                    throw new AssertionError();
            }
              goto _L17
_L12:
            if(j == k2 - 1)
            {
                byte abyte2[] = tail;
                j2 = tailLen;
                tailLen = j2 + 1;
                abyte2[j2] = abyte0[j];
                j2 = i;
            } else
            if(j == k2 - 2)
            {
                byte abyte3[] = tail;
                j2 = tailLen;
                tailLen = j2 + 1;
                abyte3[j2] = abyte0[j];
                abyte3 = tail;
                j2 = tailLen;
                tailLen = j2 + 1;
                abyte3[j2] = abyte0[j + 1];
                j2 = i;
            } else
            {
                j2 = i;
            }
_L17:
            op = j2;
            count = l2;
            return true;
        }

        static final boolean _2D_assertionsDisabled = android/util/Base64$Encoder.desiredAssertionStatus() ^ true;
        private static final byte ENCODE[] = {
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
            85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
            121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
            56, 57, 43, 47
        };
        private static final byte ENCODE_WEBSAFE[] = {
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
            85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
            121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
            56, 57, 45, 95
        };
        public static final int LINE_GROUPS = 19;
        private final byte alphabet[];
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte tail[] = new byte[2];
        int tailLen;


        public Encoder(int i, byte abyte0[])
        {
            boolean flag = true;
            super();
            output = abyte0;
            boolean flag1;
            if((i & 1) == 0)
                flag1 = true;
            else
                flag1 = false;
            do_padding = flag1;
            if((i & 2) == 0)
                flag1 = true;
            else
                flag1 = false;
            do_newline = flag1;
            if((i & 4) != 0)
                flag1 = flag;
            else
                flag1 = false;
            do_cr = flag1;
            if((i & 8) == 0)
                abyte0 = ENCODE;
            else
                abyte0 = ENCODE_WEBSAFE;
            alphabet = abyte0;
            tailLen = 0;
            if(do_newline)
                i = 19;
            else
                i = -1;
            count = i;
        }
    }


    private Base64()
    {
    }

    public static byte[] decode(String s, int i)
    {
        return decode(s.getBytes(), i);
    }

    public static byte[] decode(byte abyte0[], int i)
    {
        return decode(abyte0, 0, abyte0.length, i);
    }

    public static byte[] decode(byte abyte0[], int i, int j, int k)
    {
        Decoder decoder = new Decoder(k, new byte[(j * 3) / 4]);
        if(!decoder.process(abyte0, i, j, true))
            throw new IllegalArgumentException("bad base-64");
        if(decoder.op == decoder.output.length)
        {
            return decoder.output;
        } else
        {
            abyte0 = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, abyte0, 0, decoder.op);
            return abyte0;
        }
    }

    public static byte[] encode(byte abyte0[], int i)
    {
        return encode(abyte0, 0, abyte0.length, i);
    }

    public static byte[] encode(byte abyte0[], int i, int j, int k)
    {
        Encoder encoder;
        int l;
        encoder = new Encoder(k, null);
        l = (j / 3) * 4;
        if(!encoder.do_padding) goto _L2; else goto _L1
_L1:
        k = l;
        if(j % 3 > 0)
            k = l + 4;
_L4:
        l = k;
        if(encoder.do_newline)
        {
            l = k;
            if(j > 0)
            {
                int i1 = (j - 1) / 57;
                if(encoder.do_cr)
                    l = 2;
                else
                    l = 1;
                l = k + l * (i1 + 1);
            }
        }
        encoder.output = new byte[l];
        encoder.process(abyte0, i, j, true);
        if(!_2D_assertionsDisabled && encoder.op != l)
            throw new AssertionError();
        else
            return encoder.output;
_L2:
        k = l;
        switch(j % 3)
        {
        default:
            k = l;
            break;

        case 1: // '\001'
            k = l + 2;
            break;

        case 2: // '\002'
            k = l + 3;
            break;

        case 0: // '\0'
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String encodeToString(byte abyte0[], int i)
    {
        try
        {
            abyte0 = new String(encode(abyte0, i), "US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    public static String encodeToString(byte abyte0[], int i, int j, int k)
    {
        try
        {
            abyte0 = new String(encode(abyte0, i, j, k), "US-ASCII");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    static final boolean _2D_assertionsDisabled = android/util/Base64.desiredAssertionStatus() ^ true;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

}
