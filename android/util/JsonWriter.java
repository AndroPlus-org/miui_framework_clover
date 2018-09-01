// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.util:
//            JsonScope

public final class JsonWriter
    implements Closeable
{

    private static int[] _2D_getandroid_2D_util_2D_JsonScopeSwitchesValues()
    {
        if(_2D_android_2D_util_2D_JsonScopeSwitchesValues != null)
            return _2D_android_2D_util_2D_JsonScopeSwitchesValues;
        int ai[] = new int[JsonScope.values().length];
        try
        {
            ai[JsonScope.CLOSED.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[JsonScope.DANGLING_NAME.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[JsonScope.EMPTY_ARRAY.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[JsonScope.EMPTY_DOCUMENT.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[JsonScope.EMPTY_OBJECT.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[JsonScope.NONEMPTY_ARRAY.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 5;
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

    public JsonWriter(Writer writer)
    {
        stack.add(JsonScope.EMPTY_DOCUMENT);
        separator = ":";
        if(writer == null)
        {
            throw new NullPointerException("out == null");
        } else
        {
            out = writer;
            return;
        }
    }

    private void beforeName()
        throws IOException
    {
        JsonScope jsonscope = peek();
        if(jsonscope == JsonScope.NONEMPTY_OBJECT)
            out.write(44);
        else
        if(jsonscope != JsonScope.EMPTY_OBJECT)
            throw new IllegalStateException((new StringBuilder()).append("Nesting problem: ").append(stack).toString());
        newline();
        replaceTop(JsonScope.DANGLING_NAME);
    }

    private void beforeValue(boolean flag)
        throws IOException
    {
        _2D_getandroid_2D_util_2D_JsonScopeSwitchesValues()[peek().ordinal()];
        JVM INSTR tableswitch 1 5: default 44
    //                   1 136
    //                   2 105
    //                   3 74
    //                   4 119
    //                   5 158;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Nesting problem: ").append(stack).toString());
_L4:
        if(!lenient && flag ^ true)
            throw new IllegalStateException("JSON must start with an array or an object.");
        replaceTop(JsonScope.NONEMPTY_DOCUMENT);
_L7:
        return;
_L3:
        replaceTop(JsonScope.NONEMPTY_ARRAY);
        newline();
        continue; /* Loop/switch isn't completed */
_L5:
        out.append(',');
        newline();
        continue; /* Loop/switch isn't completed */
_L2:
        out.append(separator);
        replaceTop(JsonScope.NONEMPTY_OBJECT);
        if(true) goto _L7; else goto _L6
_L6:
        throw new IllegalStateException("JSON must have only one top-level value.");
    }

    private JsonWriter close(JsonScope jsonscope, JsonScope jsonscope1, String s)
        throws IOException
    {
        JsonScope jsonscope2 = peek();
        if(jsonscope2 != jsonscope1 && jsonscope2 != jsonscope)
            throw new IllegalStateException((new StringBuilder()).append("Nesting problem: ").append(stack).toString());
        stack.remove(stack.size() - 1);
        if(jsonscope2 == jsonscope1)
            newline();
        out.write(s);
        return this;
    }

    private void newline()
        throws IOException
    {
        if(indent == null)
            return;
        out.write("\n");
        for(int i = 1; i < stack.size(); i++)
            out.write(indent);

    }

    private JsonWriter open(JsonScope jsonscope, String s)
        throws IOException
    {
        beforeValue(true);
        stack.add(jsonscope);
        out.write(s);
        return this;
    }

    private JsonScope peek()
    {
        return (JsonScope)stack.get(stack.size() - 1);
    }

    private void replaceTop(JsonScope jsonscope)
    {
        stack.set(stack.size() - 1, jsonscope);
    }

    private void string(String s)
        throws IOException
    {
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
label4:
                        {
label5:
                            {
label6:
                                {
                                    out.write("\"");
                                    int i = 0;
                                    int j = s.length();
label7:
                                    do
                                    {
                                        {
                                            if(i >= j)
                                                break label0;
                                            char c = s.charAt(i);
                                            switch(c)
                                            {
                                            default:
                                                if(c <= '\037')
                                                    out.write(String.format("\\u%04x", new Object[] {
                                                        Integer.valueOf(c)
                                                    }));
                                                else
                                                    out.write(c);
                                                break;

                                            case 8: // '\b'
                                                break label5;

                                            case 9: // '\t'
                                                break label6;

                                            case 10: // '\n'
                                                break label4;

                                            case 12: // '\f'
                                                break label2;

                                            case 13: // '\r'
                                                break label3;

                                            case 34: // '"'
                                            case 92: // '\\'
                                                break label7;

                                            case 8232: 
                                            case 8233: 
                                                break label1;
                                            }
                                        }
                                        i++;
                                    } while(true);
                                    out.write(92);
                                    out.write(c);
                                    break MISSING_BLOCK_LABEL_143;
                                }
                                out.write("\\t");
                                break MISSING_BLOCK_LABEL_143;
                            }
                            out.write("\\b");
                            break MISSING_BLOCK_LABEL_143;
                        }
                        out.write("\\n");
                        break MISSING_BLOCK_LABEL_143;
                    }
                    out.write("\\r");
                    break MISSING_BLOCK_LABEL_143;
                }
                out.write("\\f");
                break MISSING_BLOCK_LABEL_143;
            }
            out.write(String.format("\\u%04x", new Object[] {
                Integer.valueOf(c)
            }));
            break MISSING_BLOCK_LABEL_143;
        }
        out.write("\"");
    }

    public JsonWriter beginArray()
        throws IOException
    {
        return open(JsonScope.EMPTY_ARRAY, "[");
    }

    public JsonWriter beginObject()
        throws IOException
    {
        return open(JsonScope.EMPTY_OBJECT, "{");
    }

    public void close()
        throws IOException
    {
        out.close();
        if(peek() != JsonScope.NONEMPTY_DOCUMENT)
            throw new IOException("Incomplete document");
        else
            return;
    }

    public JsonWriter endArray()
        throws IOException
    {
        return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
    }

    public JsonWriter endObject()
        throws IOException
    {
        return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
    }

    public void flush()
        throws IOException
    {
        out.flush();
    }

    public boolean isLenient()
    {
        return lenient;
    }

    public JsonWriter name(String s)
        throws IOException
    {
        if(s == null)
        {
            throw new NullPointerException("name == null");
        } else
        {
            beforeName();
            string(s);
            return this;
        }
    }

    public JsonWriter nullValue()
        throws IOException
    {
        beforeValue(false);
        out.write("null");
        return this;
    }

    public void setIndent(String s)
    {
        if(s.isEmpty())
        {
            indent = null;
            separator = ":";
        } else
        {
            indent = s;
            separator = ": ";
        }
    }

    public void setLenient(boolean flag)
    {
        lenient = flag;
    }

    public JsonWriter value(double d)
        throws IOException
    {
        if(!lenient && (Double.isNaN(d) || Double.isInfinite(d)))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Numeric values must be finite, but was ").append(d).toString());
        } else
        {
            beforeValue(false);
            out.append(Double.toString(d));
            return this;
        }
    }

    public JsonWriter value(long l)
        throws IOException
    {
        beforeValue(false);
        out.write(Long.toString(l));
        return this;
    }

    public JsonWriter value(Number number)
        throws IOException
    {
        if(number == null)
            return nullValue();
        String s = number.toString();
        if(!lenient && (s.equals("-Infinity") || s.equals("Infinity") || s.equals("NaN")))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Numeric values must be finite, but was ").append(number).toString());
        } else
        {
            beforeValue(false);
            out.append(s);
            return this;
        }
    }

    public JsonWriter value(String s)
        throws IOException
    {
        if(s == null)
        {
            return nullValue();
        } else
        {
            beforeValue(false);
            string(s);
            return this;
        }
    }

    public JsonWriter value(boolean flag)
        throws IOException
    {
        beforeValue(false);
        Writer writer = out;
        String s;
        if(flag)
            s = "true";
        else
            s = "false";
        writer.write(s);
        return this;
    }

    private static final int _2D_android_2D_util_2D_JsonScopeSwitchesValues[];
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private final List stack = new ArrayList();
}
