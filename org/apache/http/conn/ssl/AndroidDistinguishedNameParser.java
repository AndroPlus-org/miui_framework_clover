// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.conn.ssl;

import java.util.*;
import javax.security.auth.x500.X500Principal;

final class AndroidDistinguishedNameParser
{

    public AndroidDistinguishedNameParser(X500Principal x500principal)
    {
        dn = x500principal.getName("RFC2253");
        length = dn.length();
    }

    private String escapedAV()
    {
        beg = pos;
        end = pos;
        do
        {
label0:
            do
            {
                if(pos >= length)
                    return new String(chars, beg, end - beg);
                switch(chars[pos])
                {
                default:
                    char ac[] = chars;
                    int i = end;
                    end = i + 1;
                    ac[i] = chars[pos];
                    pos = pos + 1;
                    break;

                case 43: // '+'
                case 44: // ','
                case 59: // ';'
                    return new String(chars, beg, end - beg);

                case 92: // '\\'
                    char ac1[] = chars;
                    int j = end;
                    end = j + 1;
                    ac1[j] = getEscaped();
                    pos = pos + 1;
                    break;

                case 32: // ' '
                    cur = end;
                    pos = pos + 1;
                    char ac2[] = chars;
                    int k = end;
                    end = k + 1;
                    ac2[k] = (char)32;
                    break label0;
                }
            } while(true);
            for(; pos < length && chars[pos] == ' '; pos = pos + 1)
            {
                char ac3[] = chars;
                int l = end;
                end = l + 1;
                ac3[l] = (char)32;
            }

            while(pos == length || chars[pos] == ',' || chars[pos] == '+' || chars[pos] == ';') 
                return new String(chars, beg, cur - beg);
        } while(true);
    }

    private int getByte(int i)
    {
        if(i + 1 >= length)
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        int j = chars[i];
        if(j >= 48 && j <= 57)
            j -= 48;
        else
        if(j >= 97 && j <= 102)
            j -= 87;
        else
        if(j >= 65 && j <= 70)
            j -= 55;
        else
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
_L6:
        i = chars[i + 1];
        if(i < 48 || i > 57) goto _L2; else goto _L1
_L1:
        i -= 48;
_L4:
        return (j << 4) + i;
_L2:
        if(i >= 97 && i <= 102)
        {
            i -= 87;
            continue; /* Loop/switch isn't completed */
        }
        if(i < 65 || i > 70)
            break; /* Loop/switch isn't completed */
        i -= 55;
        if(true) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        if(true) goto _L6; else goto _L5
_L5:
    }

    private char getEscaped()
    {
        pos = pos + 1;
        if(pos == length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        switch(chars[pos])
        {
        default:
            return getUTF8();

        case 32: // ' '
        case 34: // '"'
        case 35: // '#'
        case 37: // '%'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 92: // '\\'
        case 95: // '_'
            return chars[pos];
        }
    }

    private char getUTF8()
    {
        int i = getByte(pos);
        pos = pos + 1;
        if(i < 128)
            return (char)i;
        if(i >= 192 && i <= 247)
        {
            int j;
            int k;
            if(i <= 223)
            {
                j = 1;
                i &= 0x1f;
            } else
            if(i <= 239)
            {
                j = 2;
                i &= 0xf;
            } else
            {
                j = 3;
                i &= 7;
            }
            for(k = 0; k < j; k++)
            {
                pos = pos + 1;
                if(pos == length || chars[pos] != '\\')
                    return '?';
                pos = pos + 1;
                int l = getByte(pos);
                pos = pos + 1;
                if((l & 0xc0) != 128)
                    return '?';
                i = (i << 6) + (l & 0x3f);
            }

            return (char)i;
        } else
        {
            return '?';
        }
    }

    private String hexAV()
    {
        if(pos + 4 >= length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        beg = pos;
        pos = pos + 1;
        break MISSING_BLOCK_LABEL_61;
_L2:
        int i;
        if(pos == length || chars[pos] == '+' || chars[pos] == ',' || chars[pos] == ';')
        {
            end = pos;
        } else
        {
label0:
            {
                if(chars[pos] != ' ')
                    break label0;
                end = pos;
                pos = pos + 1;
                while(pos < length && chars[pos] == ' ') 
                    pos = pos + 1;
            }
        }
        i = end - beg;
        if(i < 5 || (i & 1) == 0)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        break; /* Loop/switch isn't completed */
        if(chars[pos] >= 'A' && chars[pos] <= 'F')
        {
            char ac[] = chars;
            int j = pos;
            ac[j] = (char)(ac[j] + 32);
        }
        pos = pos + 1;
        if(true) goto _L2; else goto _L1
_L1:
        byte abyte0[] = new byte[i / 2];
        int l = 0;
        int k = beg + 1;
        for(; l < abyte0.length; l++)
        {
            abyte0[l] = (byte)getByte(k);
            k += 2;
        }

        return new String(chars, beg, i);
    }

    private String nextAT()
    {
        for(; pos < length && chars[pos] == ' '; pos = pos + 1);
        if(pos == length)
            return null;
        beg = pos;
        for(pos = pos + 1; pos < length && chars[pos] != '=' && chars[pos] != ' '; pos = pos + 1);
        if(pos >= length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        end = pos;
        if(chars[pos] == ' ')
        {
            for(; pos < length && chars[pos] != '=' && chars[pos] == ' '; pos = pos + 1);
            if(chars[pos] != '=' || pos == length)
                throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        }
        for(pos = pos + 1; pos < length && chars[pos] == ' '; pos = pos + 1);
        if(end - beg > 4 && chars[beg + 3] == '.' && (chars[beg] == 'O' || chars[beg] == 'o') && (chars[beg + 1] == 'I' || chars[beg + 1] == 'i') && (chars[beg + 2] == 'D' || chars[beg + 2] == 'd'))
            beg = beg + 4;
        return new String(chars, beg, end - beg);
    }

    private String quotedAV()
    {
        pos = pos + 1;
        beg = pos;
        end = beg;
        do
        {
            if(pos == length)
                throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
            if(chars[pos] == '"')
            {
                for(pos = pos + 1; pos < length && chars[pos] == ' '; pos = pos + 1);
                break;
            }
            if(chars[pos] == '\\')
                chars[end] = getEscaped();
            else
                chars[end] = chars[pos];
            pos = pos + 1;
            end = end + 1;
        } while(true);
        return new String(chars, beg, end - beg);
    }

    public String findMostSpecific(String s)
    {
        String s4;
        pos = 0;
        beg = 0;
        end = 0;
        cur = 0;
        chars = dn.toCharArray();
        String s1 = nextAT();
        s4 = s1;
        if(s1 == null)
            return null;
_L8:
        String s2;
        s2 = "";
        if(pos == length)
            return null;
        chars[pos];
        JVM INSTR lookupswitch 5: default 120
    //                   34: 135
    //                   35: 143
    //                   43: 125
    //                   44: 125
    //                   59: 125;
           goto _L1 _L2 _L3 _L4 _L4 _L4
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        s2 = escapedAV();
_L6:
        if(s.equalsIgnoreCase(s4))
            return s2;
        break; /* Loop/switch isn't completed */
_L2:
        s2 = quotedAV();
        continue; /* Loop/switch isn't completed */
_L3:
        s2 = hexAV();
        if(true) goto _L6; else goto _L5
_L5:
        if(pos >= length)
            return null;
          goto _L7
_L10:
        pos = pos + 1;
        String s3 = nextAT();
        s4 = s3;
        if(s3 == null)
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        if(true) goto _L8; else goto _L7
_L7:
        if(chars[pos] == ',' || chars[pos] == ';' || chars[pos] == '+') goto _L10; else goto _L9
_L9:
        throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
    }

    public List getAllMostSpecificFirst(String s)
    {
        String s4;
        Object obj1;
        pos = 0;
        beg = 0;
        end = 0;
        cur = 0;
        chars = dn.toCharArray();
        List list = Collections.emptyList();
        String s1 = nextAT();
        s4 = s1;
        obj1 = list;
        if(s1 == null)
            return list;
_L9:
        Object obj = obj1;
        if(pos >= length) goto _L2; else goto _L1
_L1:
        String s2 = "";
        chars[pos];
        JVM INSTR lookupswitch 5: default 128
    //                   34: 187
    //                   35: 195
    //                   43: 133
    //                   44: 133
    //                   59: 133;
           goto _L3 _L4 _L5 _L6 _L6 _L6
_L6:
        break; /* Loop/switch isn't completed */
_L3:
        s2 = escapedAV();
_L8:
        obj = obj1;
        if(s.equalsIgnoreCase(s4))
        {
            obj = obj1;
            if(((List) (obj1)).isEmpty())
                obj = new ArrayList();
            ((List) (obj)).add(s2);
        }
        if(pos < length)
            break; /* Loop/switch isn't completed */
_L2:
        return ((List) (obj));
_L4:
        s2 = quotedAV();
        continue; /* Loop/switch isn't completed */
_L5:
        s2 = hexAV();
        if(true) goto _L8; else goto _L7
_L11:
        pos = pos + 1;
        String s3 = nextAT();
        s4 = s3;
        obj1 = obj;
        if(s3 == null)
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        if(true) goto _L9; else goto _L7
_L7:
        if(chars[pos] == ',' || chars[pos] == ';' || chars[pos] == '+') goto _L11; else goto _L10
_L10:
        throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
    }

    private int beg;
    private char chars[];
    private int cur;
    private final String dn;
    private int end;
    private final int length;
    private int pos;
}
