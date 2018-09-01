// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import libcore.internal.StringPool;

// Referenced classes of package android.util:
//            JsonScope, JsonToken, MalformedJsonException

public final class JsonReader
    implements Closeable
{

    private static int[] _2D_getandroid_2D_util_2D_JsonScopeSwitchesValues()
    {
        if(_2D_android_2D_util_2D_JsonScopeSwitchesValues != null)
            return _2D_android_2D_util_2D_JsonScopeSwitchesValues;
        int ai[] = new int[JsonScope.values().length];
        try
        {
            ai[JsonScope.CLOSED.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[JsonScope.DANGLING_NAME.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[JsonScope.EMPTY_ARRAY.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[JsonScope.EMPTY_DOCUMENT.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[JsonScope.EMPTY_OBJECT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[JsonScope.NONEMPTY_ARRAY.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[JsonScope.NONEMPTY_OBJECT.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_util_2D_JsonScopeSwitchesValues = ai;
        return ai;
    }

    public JsonReader(Reader reader)
    {
        lenient = false;
        pos = 0;
        limit = 0;
        bufferStartLine = 1;
        bufferStartColumn = 1;
        push(JsonScope.EMPTY_DOCUMENT);
        skipping = false;
        if(reader == null)
        {
            throw new NullPointerException("in == null");
        } else
        {
            in = reader;
            return;
        }
    }

    private JsonToken advance()
        throws IOException
    {
        peek();
        JsonToken jsontoken = token;
        token = null;
        value = null;
        name = null;
        return jsontoken;
    }

    private void checkLenient()
        throws IOException
    {
        if(!lenient)
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        else
            return;
    }

    private JsonToken decodeLiteral()
        throws IOException
    {
        if(valuePos == -1)
            return JsonToken.STRING;
        if(valueLength == 4 && ('n' == buffer[valuePos] || 'N' == buffer[valuePos]) && ('u' == buffer[valuePos + 1] || 'U' == buffer[valuePos + 1]) && ('l' == buffer[valuePos + 2] || 'L' == buffer[valuePos + 2]) && ('l' == buffer[valuePos + 3] || 'L' == buffer[valuePos + 3]))
        {
            value = "null";
            return JsonToken.NULL;
        }
        if(valueLength == 4 && ('t' == buffer[valuePos] || 'T' == buffer[valuePos]) && ('r' == buffer[valuePos + 1] || 'R' == buffer[valuePos + 1]) && ('u' == buffer[valuePos + 2] || 'U' == buffer[valuePos + 2]) && ('e' == buffer[valuePos + 3] || 'E' == buffer[valuePos + 3]))
        {
            value = "true";
            return JsonToken.BOOLEAN;
        }
        if(valueLength == 5 && ('f' == buffer[valuePos] || 'F' == buffer[valuePos]) && ('a' == buffer[valuePos + 1] || 'A' == buffer[valuePos + 1]) && ('l' == buffer[valuePos + 2] || 'L' == buffer[valuePos + 2]) && ('s' == buffer[valuePos + 3] || 'S' == buffer[valuePos + 3]) && ('e' == buffer[valuePos + 4] || 'E' == buffer[valuePos + 4]))
        {
            value = "false";
            return JsonToken.BOOLEAN;
        } else
        {
            value = stringPool.get(buffer, valuePos, valueLength);
            return decodeNumber(buffer, valuePos, valueLength);
        }
    }

    private JsonToken decodeNumber(char ac[], int i, int j)
    {
        int k;
        int j1;
        k = i;
        char c1 = ac[i];
        j1 = c1;
        if(c1 == '-')
        {
            k = i + 1;
            j1 = ac[k];
        }
        if(j1 != 48) goto _L2; else goto _L1
_L1:
        int l;
        j1 = k + 1;
        l = ac[j1];
_L4:
        int l1;
        l1 = l;
        k = j1;
        if(l == '.')
        {
            l = j1 + 1;
            j1 = ac[l];
            do
            {
                l1 = j1;
                k = l;
                if(j1 < 48)
                    break;
                l1 = j1;
                k = l;
                if(j1 > 57)
                    break;
                l++;
                j1 = ac[l];
            } while(true);
        }
        break MISSING_BLOCK_LABEL_190;
_L2:
        if(j1 < 49 || j1 > 57)
            break MISSING_BLOCK_LABEL_186;
        l1 = k + 1;
        k = ac[l1];
_L6:
        l = k;
        j1 = l1;
        if(k < 48) goto _L4; else goto _L3
_L3:
        l = k;
        j1 = l1;
        if(k > 57) goto _L4; else goto _L5
_L5:
        l1++;
        k = ac[l1];
          goto _L6
        return JsonToken.STRING;
        int i1;
label0:
        {
            int k1;
label1:
            {
                if(l1 != 101)
                {
                    i1 = k;
                    if(l1 != 69)
                        break label0;
                }
                i1 = k + 1;
                l1 = ac[i1];
                if(l1 != '+')
                {
                    k = l1;
                    k1 = i1;
                    if(l1 != '-')
                        break label1;
                }
                k1 = i1 + 1;
                k = ac[k1];
            }
            if(k >= '0' && k <= '9')
            {
                k1++;
                char c = ac[k1];
                do
                {
                    i1 = k1;
                    if(c < '0')
                        break;
                    i1 = k1;
                    if(c > '9')
                        break;
                    k1++;
                    c = ac[k1];
                } while(true);
            } else
            {
                return JsonToken.STRING;
            }
        }
        if(i1 == i + j)
            return JsonToken.NUMBER;
        else
            return JsonToken.STRING;
    }

    private void expect(JsonToken jsontoken)
        throws IOException
    {
        peek();
        if(token != jsontoken)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected ").append(jsontoken).append(" but was ").append(peek()).toString());
        } else
        {
            advance();
            return;
        }
    }

    private boolean fillBuffer(int i)
        throws IOException
    {
        int j = 0;
        while(j < pos) 
        {
            if(buffer[j] == '\n')
            {
                bufferStartLine = bufferStartLine + 1;
                bufferStartColumn = 1;
            } else
            {
                bufferStartColumn = bufferStartColumn + 1;
            }
            j++;
        }
        if(limit != pos)
        {
            limit = limit - pos;
            System.arraycopy(buffer, pos, buffer, 0, limit);
        } else
        {
            limit = 0;
        }
        pos = 0;
        do
        {
            int k = in.read(buffer, limit, buffer.length - limit);
            if(k != -1)
            {
                limit = limit + k;
                if(bufferStartLine == 1 && bufferStartColumn == 1 && limit > 0 && buffer[0] == '\uFEFF')
                {
                    pos = pos + 1;
                    bufferStartColumn = bufferStartColumn - 1;
                }
                if(limit >= i)
                    return true;
            } else
            {
                return false;
            }
        } while(true);
    }

    private int getColumnNumber()
    {
        int i = bufferStartColumn;
        int j = 0;
        while(j < pos) 
        {
            if(buffer[j] == '\n')
                i = 1;
            else
                i++;
            j++;
        }
        return i;
    }

    private int getLineNumber()
    {
        int i = bufferStartLine;
        for(int j = 0; j < pos;)
        {
            int k = i;
            if(buffer[j] == '\n')
                k = i + 1;
            j++;
            i = k;
        }

        return i;
    }

    private CharSequence getSnippet()
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = Math.min(pos, 20);
        stringbuilder.append(buffer, pos - i, i);
        i = Math.min(limit - pos, 20);
        stringbuilder.append(buffer, pos, i);
        return stringbuilder;
    }

    private JsonToken nextInArray(boolean flag)
        throws IOException
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        replaceTop(JsonScope.NONEMPTY_ARRAY);
_L4:
        switch(nextNonWhitespace())
        {
        default:
            pos = pos - 1;
            return nextValue();

        case 93: // ']'
            if(flag)
            {
                pop();
                JsonToken jsontoken1 = JsonToken.END_ARRAY;
                token = jsontoken1;
                return jsontoken1;
            }
            // fall through

        case 44: // ','
        case 59: // ';'
            checkLenient();
            pos = pos - 1;
            value = "null";
            JsonToken jsontoken2 = JsonToken.NULL;
            token = jsontoken2;
            return jsontoken2;
        }
_L2:
        switch(nextNonWhitespace())
        {
        default:
            throw syntaxError("Unterminated array");

        case 93: // ']'
            pop();
            JsonToken jsontoken = JsonToken.END_ARRAY;
            token = jsontoken;
            return jsontoken;

        case 59: // ';'
            checkLenient();
            break;

        case 44: // ','
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private JsonToken nextInObject(boolean flag)
        throws IOException
    {
        if(flag)
        {
            switch(nextNonWhitespace())
            {
            default:
                pos = pos - 1;
                do
                {
                    int i = nextNonWhitespace();
                    JsonToken jsontoken;
                    switch(i)
                    {
                    default:
                        checkLenient();
                        pos = pos - 1;
                        name = nextLiteral(false);
                        if(name.isEmpty())
                            throw syntaxError("Expected name");
                        break;

                    case 39: // '\''
                        checkLenient();
                        // fall through

                    case 34: // '"'
                        name = nextString((char)i);
                        break;
                    }
                    replaceTop(JsonScope.DANGLING_NAME);
                    jsontoken = JsonToken.NAME;
                    token = jsontoken;
                    return jsontoken;
                } while(true);

            case 125: // '}'
                pop();
                jsontoken = JsonToken.END_OBJECT;
                token = jsontoken;
                return jsontoken;
            }
        } else
        {
            switch(nextNonWhitespace())
            {
            default:
                throw syntaxError("Unterminated object");

            case 125: // '}'
                pop();
                jsontoken = JsonToken.END_OBJECT;
                token = jsontoken;
                return jsontoken;

            case 44: // ','
            case 59: // ';'
                break;
            }
            continue;
        }
    }

    private String nextLiteral(boolean flag)
        throws IOException
    {
        Object obj;
        int i;
        obj = null;
        valuePos = -1;
        valueLength = 0;
        i = 0;
_L6:
        if(pos + i >= limit) goto _L2; else goto _L1
_L1:
        Object obj1;
        int j;
        obj1 = obj;
        j = i;
        buffer[pos + i];
        JVM INSTR lookupswitch 16: default 184
    //                   9: 200
    //                   10: 200
    //                   12: 200
    //                   13: 200
    //                   32: 200
    //                   35: 190
    //                   44: 200
    //                   47: 190
    //                   58: 200
    //                   59: 190
    //                   61: 190
    //                   91: 200
    //                   92: 190
    //                   93: 200
    //                   123: 200
    //                   125: 200;
           goto _L3 _L4 _L4 _L4 _L4 _L4 _L5 _L4 _L5 _L4 _L5 _L5 _L4 _L5 _L4 _L4 _L4
_L3:
        i++;
        break; /* Loop/switch isn't completed */
_L5:
        checkLenient();
        j = i;
        obj1 = obj;
          goto _L4
        if(true) goto _L6; else goto _L2
_L4:
        if(flag && obj1 == null)
        {
            valuePos = pos;
            obj = null;
        } else
        if(skipping)
            obj = "skipped!";
        else
        if(obj1 == null)
        {
            obj = stringPool.get(buffer, pos, j);
        } else
        {
            ((StringBuilder) (obj1)).append(buffer, pos, j);
            obj = ((StringBuilder) (obj1)).toString();
        }
        valueLength = valueLength + j;
        pos = pos + j;
        return ((String) (obj));
_L2:
label0:
        {
            if(i >= buffer.length)
                break label0;
            if(fillBuffer(i + 1))
                break; /* Loop/switch isn't completed */
            buffer[limit] = (char)0;
            obj1 = obj;
            j = i;
        }
          goto _L4
        obj1 = obj;
        if(obj == null)
            obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(buffer, pos, i);
        valueLength = valueLength + i;
        pos = pos + i;
        j = 0;
        i = 0;
        obj = obj1;
        if(fillBuffer(1)) goto _L6; else goto _L4
    }

    private int nextNonWhitespace()
        throws IOException
    {
        do
            if(pos < limit || fillBuffer(1))
            {
                char ac[] = buffer;
                int i = pos;
                pos = i + 1;
                i = ac[i];
                switch(i)
                {
                default:
                    return i;

                case 9: // '\t'
                case 10: // '\n'
                case 13: // '\r'
                case 32: // ' '
                    break;

                case 47: // '/'
                    if(pos == limit && fillBuffer(1) ^ true)
                        return i;
                    checkLenient();
                    switch(buffer[pos])
                    {
                    default:
                        return i;

                    case 42: // '*'
                        pos = pos + 1;
                        if(!skipTo("*/"))
                            throw syntaxError("Unterminated comment");
                        pos = pos + 2;
                        break;

                    case 47: // '/'
                        pos = pos + 1;
                        skipToEndOfLine();
                        break;
                    }
                    break;

                case 35: // '#'
                    checkLenient();
                    skipToEndOfLine();
                    break;
                }
            } else
            {
                throw new EOFException("End of input");
            }
        while(true);
    }

    private String nextString(char c)
        throws IOException
    {
        StringBuilder stringbuilder = null;
        do
        {
            int i = pos;
            do
            {
                if(pos >= limit)
                    break;
                char ac[] = buffer;
                int j = pos;
                pos = j + 1;
                j = ac[j];
                if(j == c)
                {
                    if(skipping)
                        return "skipped!";
                    if(stringbuilder == null)
                    {
                        return stringPool.get(buffer, i, pos - i - 1);
                    } else
                    {
                        stringbuilder.append(buffer, i, pos - i - 1);
                        return stringbuilder.toString();
                    }
                }
                if(j == '\\')
                {
                    StringBuilder stringbuilder1 = stringbuilder;
                    if(stringbuilder == null)
                        stringbuilder1 = new StringBuilder();
                    stringbuilder1.append(buffer, i, pos - i - 1);
                    stringbuilder1.append(readEscapeCharacter());
                    i = pos;
                    stringbuilder = stringbuilder1;
                }
            } while(true);
            StringBuilder stringbuilder2 = stringbuilder;
            if(stringbuilder == null)
                stringbuilder2 = new StringBuilder();
            stringbuilder2.append(buffer, i, pos - i);
            stringbuilder = stringbuilder2;
        } while(fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private JsonToken nextValue()
        throws IOException
    {
        int i = nextNonWhitespace();
        switch(i)
        {
        default:
            pos = pos - 1;
            return readLiteral();

        case 123: // '{'
            push(JsonScope.EMPTY_OBJECT);
            JsonToken jsontoken = JsonToken.BEGIN_OBJECT;
            token = jsontoken;
            return jsontoken;

        case 91: // '['
            push(JsonScope.EMPTY_ARRAY);
            JsonToken jsontoken1 = JsonToken.BEGIN_ARRAY;
            token = jsontoken1;
            return jsontoken1;

        case 39: // '\''
            checkLenient();
            // fall through

        case 34: // '"'
            value = nextString((char)i);
            JsonToken jsontoken2 = JsonToken.STRING;
            token = jsontoken2;
            return jsontoken2;
        }
    }

    private JsonToken objectValue()
        throws IOException
    {
        switch(nextNonWhitespace())
        {
        case 59: // ';'
        case 60: // '<'
        default:
            throw syntaxError("Expected ':'");

        case 61: // '='
            checkLenient();
            if((pos < limit || fillBuffer(1)) && buffer[pos] == '>')
                pos = pos + 1;
            // fall through

        case 58: // ':'
            replaceTop(JsonScope.NONEMPTY_OBJECT);
            return nextValue();
        }
    }

    private JsonScope peekStack()
    {
        return (JsonScope)stack.get(stack.size() - 1);
    }

    private JsonScope pop()
    {
        return (JsonScope)stack.remove(stack.size() - 1);
    }

    private void push(JsonScope jsonscope)
    {
        stack.add(jsonscope);
    }

    private char readEscapeCharacter()
        throws IOException
    {
        if(pos == limit && fillBuffer(1) ^ true)
            throw syntaxError("Unterminated escape sequence");
        char ac[] = buffer;
        int i = pos;
        pos = i + 1;
        char c = ac[i];
        switch(c)
        {
        default:
            return c;

        case 117: // 'u'
            if(pos + 4 > limit && fillBuffer(4) ^ true)
            {
                throw syntaxError("Unterminated escape sequence");
            } else
            {
                String s = stringPool.get(buffer, pos, 4);
                pos = pos + 4;
                return (char)Integer.parseInt(s, 16);
            }

        case 116: // 't'
            return '\t';

        case 98: // 'b'
            return '\b';

        case 110: // 'n'
            return '\n';

        case 114: // 'r'
            return '\r';

        case 102: // 'f'
            return '\f';
        }
    }

    private JsonToken readLiteral()
        throws IOException
    {
        value = nextLiteral(true);
        if(valueLength == 0)
            throw syntaxError("Expected literal value");
        token = decodeLiteral();
        if(token == JsonToken.STRING)
            checkLenient();
        return token;
    }

    private void replaceTop(JsonScope jsonscope)
    {
        stack.set(stack.size() - 1, jsonscope);
    }

    private boolean skipTo(String s)
        throws IOException
    {
label0:
        do
        {
            if(pos + s.length() <= limit || fillBuffer(s.length()))
            {
                int i = 0;
                do
                {
                    if(i >= s.length())
                        break;
                    if(buffer[pos + i] != s.charAt(i))
                    {
                        pos = pos + 1;
                        continue label0;
                    }
                    i++;
                } while(true);
                return true;
            }
            return false;
        } while(true);
    }

    private void skipToEndOfLine()
        throws IOException
    {
        int i;
        do
        {
            if(pos >= limit && !fillBuffer(1))
                break;
            char ac[] = buffer;
            i = pos;
            pos = i + 1;
            i = ac[i];
        } while(i != '\r' && i != '\n');
    }

    private IOException syntaxError(String s)
        throws IOException
    {
        throw new MalformedJsonException((new StringBuilder()).append(s).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
    }

    public void beginArray()
        throws IOException
    {
        expect(JsonToken.BEGIN_ARRAY);
    }

    public void beginObject()
        throws IOException
    {
        expect(JsonToken.BEGIN_OBJECT);
    }

    public void close()
        throws IOException
    {
        value = null;
        token = null;
        stack.clear();
        stack.add(JsonScope.CLOSED);
        in.close();
    }

    public void endArray()
        throws IOException
    {
        expect(JsonToken.END_ARRAY);
    }

    public void endObject()
        throws IOException
    {
        expect(JsonToken.END_OBJECT);
    }

    public boolean hasNext()
        throws IOException
    {
        boolean flag = false;
        peek();
        boolean flag1 = flag;
        if(token != JsonToken.END_OBJECT)
        {
            flag1 = flag;
            if(token != JsonToken.END_ARRAY)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isLenient()
    {
        return lenient;
    }

    public boolean nextBoolean()
        throws IOException
    {
        peek();
        if(token != JsonToken.BOOLEAN)
            throw new IllegalStateException((new StringBuilder()).append("Expected a boolean but was ").append(token).toString());
        boolean flag;
        if(value == "true")
            flag = true;
        else
            flag = false;
        advance();
        return flag;
    }

    public double nextDouble()
        throws IOException
    {
        peek();
        if(token != JsonToken.STRING && token != JsonToken.NUMBER)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected a double but was ").append(token).toString());
        } else
        {
            double d = Double.parseDouble(value);
            advance();
            return d;
        }
    }

    public int nextInt()
        throws IOException
    {
        peek();
        if(token != JsonToken.STRING && token != JsonToken.NUMBER)
            throw new IllegalStateException((new StringBuilder()).append("Expected an int but was ").append(token).toString());
        int i;
        try
        {
            i = Integer.parseInt(value);
        }
        catch(NumberFormatException numberformatexception)
        {
            double d = Double.parseDouble(value);
            int j = (int)d;
            i = j;
            if((double)j != d)
                throw new NumberFormatException(value);
        }
        advance();
        return i;
    }

    public long nextLong()
        throws IOException
    {
        peek();
        if(token != JsonToken.STRING && token != JsonToken.NUMBER)
            throw new IllegalStateException((new StringBuilder()).append("Expected a long but was ").append(token).toString());
        long l;
        try
        {
            l = Long.parseLong(value);
        }
        catch(NumberFormatException numberformatexception)
        {
            double d = Double.parseDouble(value);
            long l1 = (long)d;
            l = l1;
            if((double)l1 != d)
                throw new NumberFormatException(value);
        }
        advance();
        return l;
    }

    public String nextName()
        throws IOException
    {
        peek();
        if(token != JsonToken.NAME)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected a name but was ").append(peek()).toString());
        } else
        {
            String s = name;
            advance();
            return s;
        }
    }

    public void nextNull()
        throws IOException
    {
        peek();
        if(token != JsonToken.NULL)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected null but was ").append(token).toString());
        } else
        {
            advance();
            return;
        }
    }

    public String nextString()
        throws IOException
    {
        peek();
        if(token != JsonToken.STRING && token != JsonToken.NUMBER)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected a string but was ").append(peek()).toString());
        } else
        {
            String s = value;
            advance();
            return s;
        }
    }

    public JsonToken peek()
        throws IOException
    {
        if(token != null)
            return token;
        _2D_getandroid_2D_util_2D_JsonScopeSwitchesValues()[peekStack().ordinal()];
        JVM INSTR tableswitch 1 8: default 68
    //                   1 211
    //                   2 166
    //                   3 148
    //                   4 76
    //                   5 160
    //                   6 154
    //                   7 177
    //                   8 171;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        throw new AssertionError();
_L5:
        replaceTop(JsonScope.NONEMPTY_DOCUMENT);
        JsonToken jsontoken = nextValue();
        if(!lenient && token != JsonToken.BEGIN_ARRAY && token != JsonToken.BEGIN_OBJECT)
            throw new IOException((new StringBuilder()).append("Expected JSON document to start with '[' or '{' but was ").append(token).toString());
        else
            return jsontoken;
_L4:
        return nextInArray(true);
_L7:
        return nextInArray(false);
_L6:
        return nextInObject(true);
_L3:
        return objectValue();
_L9:
        return nextInObject(false);
_L8:
        JsonToken jsontoken1 = nextValue();
        if(lenient)
            return jsontoken1;
        try
        {
            throw syntaxError("Expected EOF");
        }
        catch(EOFException eofexception)
        {
            eofexception = JsonToken.END_DOCUMENT;
        }
        token = eofexception;
        return eofexception;
_L2:
        throw new IllegalStateException("JsonReader is closed");
    }

    public void setLenient(boolean flag)
    {
        lenient = flag;
    }

    public void skipValue()
        throws IOException
    {
        skipping = true;
        if(!hasNext() || peek() == JsonToken.END_DOCUMENT)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #176 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("No element left to skip");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_43;
        Exception exception;
        exception;
        skipping = false;
        throw exception;
        int i = 0;
_L5:
        JsonToken jsontoken = advance();
        if(jsontoken == JsonToken.BEGIN_ARRAY) goto _L2; else goto _L1
_L1:
        JsonToken jsontoken1 = JsonToken.BEGIN_OBJECT;
        if(jsontoken != jsontoken1) goto _L3; else goto _L2
_L2:
        int j = i + 1;
_L8:
        i = j;
        if(j != 0) goto _L5; else goto _L4
_L4:
        skipping = false;
        return;
_L3:
        if(jsontoken == JsonToken.END_ARRAY) goto _L7; else goto _L6
_L6:
        jsontoken1 = JsonToken.END_OBJECT;
        j = i;
        if(jsontoken != jsontoken1) goto _L8; else goto _L7
_L7:
        j = i - 1;
          goto _L8
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(" near ").append(getSnippet()).toString();
    }

    private static final int _2D_android_2D_util_2D_JsonScopeSwitchesValues[];
    private static final String FALSE = "false";
    private static final String TRUE = "true";
    private final char buffer[] = new char[1024];
    private int bufferStartColumn;
    private int bufferStartLine;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private String name;
    private int pos;
    private boolean skipping;
    private final List stack = new ArrayList();
    private final StringPool stringPool = new StringPool();
    private JsonToken token;
    private String value;
    private int valueLength;
    private int valuePos;
}
