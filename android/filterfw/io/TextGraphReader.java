// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.io;

import android.filterfw.core.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

// Referenced classes of package android.filterfw.io:
//            GraphReader, GraphIOException, PatternScanner

public class TextGraphReader extends GraphReader
{
    private class AddLibraryCommand
        implements Command
    {

        public void execute(TextGraphReader textgraphreader)
        {
            TextGraphReader._2D_get2(textgraphreader);
            FilterFactory.addFilterLibrary(mLibraryName);
        }

        private String mLibraryName;
        final TextGraphReader this$0;

        public AddLibraryCommand(String s)
        {
            this$0 = TextGraphReader.this;
            super();
            mLibraryName = s;
        }
    }

    private class AllocateFilterCommand
        implements Command
    {

        public void execute(TextGraphReader textgraphreader)
            throws GraphIOException
        {
            Filter filter;
            try
            {
                filter = TextGraphReader._2D_get2(textgraphreader).createFilterByClassName(mClassName, mFilterName);
            }
            // Misplaced declaration of an exception variable
            catch(TextGraphReader textgraphreader)
            {
                throw new GraphIOException(textgraphreader.getMessage());
            }
            TextGraphReader._2D_set0(textgraphreader, filter);
        }

        private String mClassName;
        private String mFilterName;
        final TextGraphReader this$0;

        public AllocateFilterCommand(String s, String s1)
        {
            this$0 = TextGraphReader.this;
            super();
            mClassName = s;
            mFilterName = s1;
        }
    }

    private static interface Command
    {

        public abstract void execute(TextGraphReader textgraphreader)
            throws GraphIOException;
    }

    private class ConnectCommand
        implements Command
    {

        public void execute(TextGraphReader textgraphreader)
        {
            TextGraphReader._2D_get1(textgraphreader).connect(mSourceFilter, mSourcePort, mTargetFilter, mTargetName);
        }

        private String mSourceFilter;
        private String mSourcePort;
        private String mTargetFilter;
        private String mTargetName;
        final TextGraphReader this$0;

        public ConnectCommand(String s, String s1, String s2, String s3)
        {
            this$0 = TextGraphReader.this;
            super();
            mSourceFilter = s;
            mSourcePort = s1;
            mTargetFilter = s2;
            mTargetName = s3;
        }
    }

    private class ImportPackageCommand
        implements Command
    {

        public void execute(TextGraphReader textgraphreader)
            throws GraphIOException
        {
            try
            {
                TextGraphReader._2D_get2(textgraphreader).addPackage(mPackageName);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(TextGraphReader textgraphreader)
            {
                throw new GraphIOException(textgraphreader.getMessage());
            }
        }

        private String mPackageName;
        final TextGraphReader this$0;

        public ImportPackageCommand(String s)
        {
            this$0 = TextGraphReader.this;
            super();
            mPackageName = s;
        }
    }

    private class InitFilterCommand
        implements Command
    {

        public void execute(TextGraphReader textgraphreader)
            throws GraphIOException
        {
            Filter filter = TextGraphReader._2D_get0(textgraphreader);
            try
            {
                filter.initWithValueMap(mParams);
            }
            // Misplaced declaration of an exception variable
            catch(TextGraphReader textgraphreader)
            {
                throw new GraphIOException(textgraphreader.getMessage());
            }
            TextGraphReader._2D_get1(textgraphreader).addFilter(TextGraphReader._2D_get0(TextGraphReader.this));
        }

        private KeyValueMap mParams;
        final TextGraphReader this$0;

        public InitFilterCommand(KeyValueMap keyvaluemap)
        {
            this$0 = TextGraphReader.this;
            super();
            mParams = keyvaluemap;
        }
    }


    static Filter _2D_get0(TextGraphReader textgraphreader)
    {
        return textgraphreader.mCurrentFilter;
    }

    static FilterGraph _2D_get1(TextGraphReader textgraphreader)
    {
        return textgraphreader.mCurrentGraph;
    }

    static FilterFactory _2D_get2(TextGraphReader textgraphreader)
    {
        return textgraphreader.mFactory;
    }

    static Filter _2D_set0(TextGraphReader textgraphreader, Filter filter)
    {
        textgraphreader.mCurrentFilter = filter;
        return filter;
    }

    public TextGraphReader()
    {
        mCommands = new ArrayList();
    }

    private void applySettings()
        throws GraphIOException
    {
        for(Iterator iterator = mSettings.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            Object obj = mSettings.get(s);
            if(s.equals("autoBranch"))
            {
                expectSettingClass(s, obj, java/lang/String);
                if(obj.equals("synced"))
                    mCurrentGraph.setAutoBranchMode(1);
                else
                if(obj.equals("unsynced"))
                    mCurrentGraph.setAutoBranchMode(2);
                else
                if(obj.equals("off"))
                    mCurrentGraph.setAutoBranchMode(0);
                else
                    throw new GraphIOException((new StringBuilder()).append("Unknown autobranch setting: ").append(obj).append("!").toString());
            } else
            if(s.equals("discardUnconnectedOutputs"))
            {
                expectSettingClass(s, obj, java/lang/Boolean);
                mCurrentGraph.setDiscardUnconnectedOutputs(((Boolean)obj).booleanValue());
            } else
            {
                throw new GraphIOException((new StringBuilder()).append("Unknown @setting '").append(s).append("'!").toString());
            }
        }

    }

    private void bindExternal(String s)
        throws GraphIOException
    {
        if(mReferences.containsKey(s))
        {
            Object obj = mReferences.get(s);
            mBoundReferences.put(s, obj);
            return;
        } else
        {
            throw new GraphIOException((new StringBuilder()).append("Unknown external variable '").append(s).append("'! ").append("You must add a reference to this external in the host program using ").append("addReference(...)!").toString());
        }
    }

    private void checkReferences()
        throws GraphIOException
    {
        for(Iterator iterator = mReferences.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(!mBoundReferences.containsKey(s))
                throw new GraphIOException((new StringBuilder()).append("Host program specifies reference to '").append(s).append("', which is not ").append("declared @external in graph file!").toString());
        }

    }

    private void executeCommands()
        throws GraphIOException
    {
        for(Iterator iterator = mCommands.iterator(); iterator.hasNext(); ((Command)iterator.next()).execute(this));
    }

    private void expectSettingClass(String s, Object obj, Class class1)
        throws GraphIOException
    {
        if(obj.getClass() != class1)
            throw new GraphIOException((new StringBuilder()).append("Setting '").append(s).append("' must have a value of type ").append(class1.getSimpleName()).append(", but found a value of type ").append(obj.getClass().getSimpleName()).append("!").toString());
        else
            return;
    }

    private void parseString(String s)
        throws GraphIOException
    {
        Pattern pattern = Pattern.compile("@[a-zA-Z]+");
        Pattern pattern1 = Pattern.compile("\\}");
        Pattern pattern2 = Pattern.compile("\\{");
        Object obj = Pattern.compile("(\\s+|//[^\\n]*\\n)+");
        Pattern pattern3 = Pattern.compile("[a-zA-Z\\.]+");
        Pattern pattern4 = Pattern.compile("[a-zA-Z\\./:]+");
        Pattern pattern5 = Pattern.compile("\\[[a-zA-Z0-9\\-_]+\\]");
        Pattern pattern6 = Pattern.compile("=>");
        Pattern pattern7 = Pattern.compile(";");
        Pattern pattern8 = Pattern.compile("[a-zA-Z0-9\\-_]+");
        int i = 0;
        PatternScanner patternscanner = new PatternScanner(s, ((Pattern) (obj)));
        s = null;
        String s1 = null;
        String s2 = null;
        obj = null;
        do
        {
            if(!patternscanner.atEnd())
            {
                switch(i)
                {
                case 0: // '\0'
                    String s3 = patternscanner.eat(pattern, "<command>");
                    if(s3.equals("@import"))
                        i = 1;
                    else
                    if(s3.equals("@library"))
                        i = 2;
                    else
                    if(s3.equals("@filter"))
                        i = 3;
                    else
                    if(s3.equals("@connect"))
                        i = 8;
                    else
                    if(s3.equals("@set"))
                        i = 13;
                    else
                    if(s3.equals("@external"))
                        i = 14;
                    else
                    if(s3.equals("@setting"))
                        i = 15;
                    else
                        throw new GraphIOException((new StringBuilder()).append("Unknown command '").append(s3).append("'!").toString());
                    break;

                case 1: // '\001'
                    String s4 = patternscanner.eat(pattern3, "<package-name>");
                    mCommands.add(new ImportPackageCommand(s4));
                    i = 16;
                    break;

                case 2: // '\002'
                    String s5 = patternscanner.eat(pattern4, "<library-name>");
                    mCommands.add(new AddLibraryCommand(s5));
                    i = 16;
                    break;

                case 3: // '\003'
                    s = patternscanner.eat(pattern8, "<class-name>");
                    i = 4;
                    break;

                case 4: // '\004'
                    String s6 = patternscanner.eat(pattern8, "<filter-name>");
                    mCommands.add(new AllocateFilterCommand(s, s6));
                    i = 5;
                    break;

                case 5: // '\005'
                    patternscanner.eat(pattern2, "{");
                    i = 6;
                    break;

                case 6: // '\006'
                    KeyValueMap keyvaluemap = readKeyValueAssignments(patternscanner, pattern1);
                    mCommands.add(new InitFilterCommand(keyvaluemap));
                    i = 7;
                    break;

                case 7: // '\007'
                    patternscanner.eat(pattern1, "}");
                    i = 0;
                    break;

                case 8: // '\b'
                    s1 = patternscanner.eat(pattern8, "<source-filter-name>");
                    i = 9;
                    break;

                case 9: // '\t'
                    s2 = patternscanner.eat(pattern5, "[<source-port-name>]");
                    s2 = s2.substring(1, s2.length() - 1);
                    i = 10;
                    break;

                case 10: // '\n'
                    patternscanner.eat(pattern6, "=>");
                    i = 11;
                    break;

                case 11: // '\013'
                    obj = patternscanner.eat(pattern8, "<target-filter-name>");
                    i = 12;
                    break;

                case 12: // '\f'
                    String s7 = patternscanner.eat(pattern5, "[<target-port-name>]");
                    s7 = s7.substring(1, s7.length() - 1);
                    mCommands.add(new ConnectCommand(s1, s2, ((String) (obj)), s7));
                    i = 16;
                    break;

                case 13: // '\r'
                    KeyValueMap keyvaluemap1 = readKeyValueAssignments(patternscanner, pattern7);
                    mBoundReferences.putAll(keyvaluemap1);
                    i = 16;
                    break;

                case 14: // '\016'
                    bindExternal(patternscanner.eat(pattern8, "<external-identifier>"));
                    i = 16;
                    break;

                case 15: // '\017'
                    KeyValueMap keyvaluemap2 = readKeyValueAssignments(patternscanner, pattern7);
                    mSettings.putAll(keyvaluemap2);
                    i = 16;
                    break;

                case 16: // '\020'
                    patternscanner.eat(pattern7, ";");
                    i = 0;
                    break;
                }
                continue;
            }
            if(i != 16 && i != 0)
                throw new GraphIOException("Unexpected end of input!");
            break;
        } while(true);
    }

    private KeyValueMap readKeyValueAssignments(PatternScanner patternscanner, Pattern pattern)
        throws GraphIOException
    {
        Pattern pattern1 = Pattern.compile("=");
        Pattern pattern2 = Pattern.compile(";");
        Pattern pattern3 = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9]*");
        Pattern pattern4 = Pattern.compile("'[^']*'|\\\"[^\\\"]*\\\"");
        Pattern pattern5 = Pattern.compile("[0-9]+");
        Pattern pattern6 = Pattern.compile("[0-9]*\\.[0-9]+f?");
        Pattern pattern7 = Pattern.compile("\\$[a-zA-Z]+[a-zA-Z0-9]");
        Pattern pattern8 = Pattern.compile("true|false");
        int i = 0;
        KeyValueMap keyvaluemap = new KeyValueMap();
        String s = null;
        do
        {
            if(!patternscanner.atEnd())
            {
                boolean flag;
                if(pattern != null)
                    flag = patternscanner.peek(pattern);
                else
                    flag = false;
                if(flag ^ true)
                {
                    switch(i)
                    {
                    case 0: // '\0'
                        s = patternscanner.eat(pattern3, "<identifier>");
                        i = 1;
                        break;

                    case 1: // '\001'
                        patternscanner.eat(pattern1, "=");
                        i = 2;
                        break;

                    case 2: // '\002'
                        String s1 = patternscanner.tryEat(pattern4);
                        if(s1 != null)
                        {
                            keyvaluemap.put(s, s1.substring(1, s1.length() - 1));
                        } else
                        {
                            Object obj = patternscanner.tryEat(pattern7);
                            if(obj != null)
                            {
                                String s5 = ((String) (obj)).substring(1, ((String) (obj)).length());
                                if(mBoundReferences != null)
                                    obj = mBoundReferences.get(s5);
                                else
                                    obj = null;
                                if(obj == null)
                                    throw new GraphIOException((new StringBuilder()).append("Unknown object reference to '").append(s5).append("'!").toString());
                                keyvaluemap.put(s, obj);
                            } else
                            {
                                String s2 = patternscanner.tryEat(pattern8);
                                if(s2 != null)
                                {
                                    keyvaluemap.put(s, Boolean.valueOf(Boolean.parseBoolean(s2)));
                                } else
                                {
                                    String s3 = patternscanner.tryEat(pattern6);
                                    if(s3 != null)
                                    {
                                        keyvaluemap.put(s, Float.valueOf(Float.parseFloat(s3)));
                                    } else
                                    {
                                        String s4 = patternscanner.tryEat(pattern5);
                                        if(s4 != null)
                                            keyvaluemap.put(s, Integer.valueOf(Integer.parseInt(s4)));
                                        else
                                            throw new GraphIOException(patternscanner.unexpectedTokenMessage("<value>"));
                                    }
                                }
                            }
                        }
                        i = 3;
                        break;

                    case 3: // '\003'
                        patternscanner.eat(pattern2, ";");
                        i = 0;
                        break;
                    }
                    continue;
                }
            }
            if(i != 0 && i != 3)
                throw new GraphIOException((new StringBuilder()).append("Unexpected end of assignments on line ").append(patternscanner.lineNo()).append("!").toString());
            else
                return keyvaluemap;
        } while(true);
    }

    private void reset()
    {
        mCurrentGraph = null;
        mCurrentFilter = null;
        mCommands.clear();
        mBoundReferences = new KeyValueMap();
        mSettings = new KeyValueMap();
        mFactory = new FilterFactory();
    }

    public FilterGraph readGraphString(String s)
        throws GraphIOException
    {
        FilterGraph filtergraph = new FilterGraph();
        reset();
        mCurrentGraph = filtergraph;
        parseString(s);
        applySettings();
        executeCommands();
        reset();
        return filtergraph;
    }

    public KeyValueMap readKeyValueAssignments(String s)
        throws GraphIOException
    {
        return readKeyValueAssignments(new PatternScanner(s, Pattern.compile("\\s+")), null);
    }

    private KeyValueMap mBoundReferences;
    private ArrayList mCommands;
    private Filter mCurrentFilter;
    private FilterGraph mCurrentGraph;
    private FilterFactory mFactory;
    private KeyValueMap mSettings;
}
