// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMessageParser
{
    public static class Acronym extends Token
    {

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getRawText());
            list.add(getValue());
            return list;
        }

        public String getValue()
        {
            return value;
        }

        public boolean isHtml()
        {
            return false;
        }

        private String value;

        public Acronym(String s, String s1)
        {
            super(Token.Type.ACRONYM, s);
            value = s1;
        }
    }

    public static class FlickrPhoto extends Token
    {

        public static String getPhotoURL(String s, String s1)
        {
            return (new StringBuilder()).append("http://flickr.com/photos/").append(s).append("/").append(s1).toString();
        }

        public static String getRssUrl(String s)
        {
            return null;
        }

        public static String getTagsURL(String s)
        {
            return (new StringBuilder()).append("http://flickr.com/photos/tags/").append(s).toString();
        }

        public static String getUserSetsURL(String s, String s1)
        {
            return (new StringBuilder()).append("http://flickr.com/photos/").append(s).append("/sets/").append(s1).toString();
        }

        public static String getUserTagsURL(String s, String s1)
        {
            return (new StringBuilder()).append("http://flickr.com/photos/").append(s).append("/tags/").append(s1).toString();
        }

        public static String getUserURL(String s)
        {
            return (new StringBuilder()).append("http://flickr.com/photos/").append(s).toString();
        }

        public static FlickrPhoto matchURL(String s, String s1)
        {
            Matcher matcher = GROUPING_PATTERN.matcher(s);
            if(matcher.matches())
                return new FlickrPhoto(matcher.group(1), null, matcher.group(2), matcher.group(3), s1);
            s = URL_PATTERN.matcher(s);
            if(s.matches())
                return new FlickrPhoto(s.group(1), s.group(2), null, null, s1);
            else
                return null;
        }

        public String getGrouping()
        {
            return grouping;
        }

        public String getGroupingId()
        {
            return groupingId;
        }

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getUrl());
            String s;
            if(getUser() != null)
                s = getUser();
            else
                s = "";
            list.add(s);
            if(getPhoto() != null)
                s = getPhoto();
            else
                s = "";
            list.add(s);
            if(getGrouping() != null)
                s = getGrouping();
            else
                s = "";
            list.add(s);
            if(getGroupingId() != null)
                s = getGroupingId();
            else
                s = "";
            list.add(s);
            return list;
        }

        public String getPhoto()
        {
            return photo;
        }

        public String getUrl()
        {
            if("sets".equals(grouping))
                return getUserSetsURL(user, groupingId);
            if("tags".equals(grouping))
                if(user != null)
                    return getUserTagsURL(user, groupingId);
                else
                    return getTagsURL(groupingId);
            if(photo != null)
                return getPhotoURL(user, photo);
            else
                return getUserURL(user);
        }

        public String getUser()
        {
            return user;
        }

        public boolean isHtml()
        {
            return false;
        }

        public boolean isMedia()
        {
            return true;
        }

        private static final Pattern GROUPING_PATTERN = Pattern.compile("http://(?:www.)?flickr.com/photos/([^/?#&]+)/(tags|sets)/([^/?#&]+)/?");
        private static final String SETS = "sets";
        private static final String TAGS = "tags";
        private static final Pattern URL_PATTERN = Pattern.compile("http://(?:www.)?flickr.com/photos/([^/?#&]+)/?([^/?#&]+)?/?.*");
        private String grouping;
        private String groupingId;
        private String photo;
        private String user;


        public FlickrPhoto(String s, String s1, String s2, String s3, String s4)
        {
            super(Token.Type.FLICKR, s4);
            if(!"tags".equals(s))
            {
                user = s;
                if("show".equals(s1))
                    s1 = null;
                photo = s1;
                grouping = s2;
                groupingId = s3;
            } else
            {
                user = null;
                photo = null;
                grouping = "tags";
                groupingId = s1;
            }
        }
    }

    public static class Format extends Token
    {

        private String getFormatEnd(char c)
        {
            switch(c)
            {
            default:
                throw new AssertionError((new StringBuilder()).append("unknown format '").append(c).append("'").toString());

            case 42: // '*'
                return "</b>";

            case 95: // '_'
                return "</i>";

            case 94: // '^'
                return "</font></b>";

            case 34: // '"'
                return "\u201D</font>";
            }
        }

        private String getFormatStart(char c)
        {
            switch(c)
            {
            default:
                throw new AssertionError((new StringBuilder()).append("unknown format '").append(c).append("'").toString());

            case 42: // '*'
                return "<b>";

            case 95: // '_'
                return "<i>";

            case 94: // '^'
                return "<b><font color=\"#005FFF\">";

            case 34: // '"'
                return "<font color=\"#999999\">\u201C";
            }
        }

        public boolean controlCaps()
        {
            boolean flag;
            if(ch == '^')
                flag = true;
            else
                flag = false;
            return flag;
        }

        public List getInfo()
        {
            throw new UnsupportedOperationException();
        }

        public boolean isHtml()
        {
            return true;
        }

        public boolean setCaps()
        {
            return start;
        }

        public void setMatched(boolean flag)
        {
            matched = flag;
        }

        public String toHtml(boolean flag)
        {
            if(matched)
            {
                String s;
                if(start)
                    s = getFormatStart(ch);
                else
                    s = getFormatEnd(ch);
                return s;
            }
            String s1;
            if(ch == '"')
                s1 = "&quot;";
            else
                s1 = String.valueOf(ch);
            return s1;
        }

        private char ch;
        private boolean matched;
        private boolean start;

        public Format(char c, boolean flag)
        {
            super(Token.Type.FORMAT, String.valueOf(c));
            ch = c;
            start = flag;
        }
    }

    public static class Html extends Token
    {

        private static String trimLeadingWhitespace(String s)
        {
            int i;
            for(i = 0; i < s.length() && Character.isWhitespace(s.charAt(i)); i++);
            return s.substring(i);
        }

        public static String trimTrailingWhitespace(String s)
        {
            int i;
            for(i = s.length(); i > 0 && Character.isWhitespace(s.charAt(i - 1)); i--);
            return s.substring(0, i);
        }

        public List getInfo()
        {
            throw new UnsupportedOperationException();
        }

        public boolean isHtml()
        {
            return true;
        }

        public String toHtml(boolean flag)
        {
            String s;
            if(flag)
                s = html.toUpperCase();
            else
                s = html;
            return s;
        }

        public void trimLeadingWhitespace()
        {
            text = trimLeadingWhitespace(text);
            html = trimLeadingWhitespace(html);
        }

        public void trimTrailingWhitespace()
        {
            text = trimTrailingWhitespace(text);
            html = trimTrailingWhitespace(html);
        }

        private String html;

        public Html(String s, String s1)
        {
            super(Token.Type.HTML, s);
            html = s1;
        }
    }

    public static class Link extends Token
    {

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getURL());
            list.add(getRawText());
            return list;
        }

        public String getURL()
        {
            return url;
        }

        public boolean isHtml()
        {
            return false;
        }

        private String url;

        public Link(String s, String s1)
        {
            super(Token.Type.LINK, s1);
            url = s;
        }
    }

    public static class MusicTrack extends Token
    {

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getTrack());
            return list;
        }

        public String getTrack()
        {
            return track;
        }

        public boolean isHtml()
        {
            return false;
        }

        private String track;

        public MusicTrack(String s)
        {
            super(Token.Type.MUSIC, s);
            track = s;
        }
    }

    public static class Part
    {

        private String getPartType()
        {
            if(isMedia())
                return "d";
            if(meText != null)
                return "m";
            else
                return "";
        }

        public void add(Token token)
        {
            if(isMedia())
            {
                throw new AssertionError("media ");
            } else
            {
                tokens.add(token);
                return;
            }
        }

        public Token getMediaToken()
        {
            if(isMedia())
                return (Token)tokens.get(0);
            else
                return null;
        }

        public String getRawText()
        {
            StringBuilder stringbuilder = new StringBuilder();
            if(meText != null)
                stringbuilder.append(meText);
            for(int i = 0; i < tokens.size(); i++)
                stringbuilder.append(((Token)tokens.get(i)).getRawText());

            return stringbuilder.toString();
        }

        public ArrayList getTokens()
        {
            return tokens;
        }

        public String getType(boolean flag)
        {
            StringBuilder stringbuilder = new StringBuilder();
            String s;
            if(flag)
                s = "s";
            else
                s = "r";
            return stringbuilder.append(s).append(getPartType()).toString();
        }

        public boolean isMedia()
        {
            boolean flag = false;
            if(tokens.size() == 1)
                flag = ((Token)tokens.get(0)).isMedia();
            return flag;
        }

        public void setMeText(String s)
        {
            meText = s;
        }

        private String meText;
        private ArrayList tokens;

        public Part()
        {
            tokens = new ArrayList();
        }
    }

    public static class Photo extends Token
    {

        public static String getAlbumURL(String s, String s1)
        {
            return (new StringBuilder()).append("http://picasaweb.google.com/").append(s).append("/").append(s1).toString();
        }

        public static String getPhotoURL(String s, String s1, String s2)
        {
            return (new StringBuilder()).append("http://picasaweb.google.com/").append(s).append("/").append(s1).append("/photo#").append(s2).toString();
        }

        public static String getRssUrl(String s)
        {
            return (new StringBuilder()).append("http://picasaweb.google.com/data/feed/api/user/").append(s).append("?category=album&alt=rss").toString();
        }

        public static Photo matchURL(String s, String s1)
        {
            s = URL_PATTERN.matcher(s);
            if(s.matches())
                return new Photo(s.group(1), s.group(2), s.group(3), s1);
            else
                return null;
        }

        public String getAlbum()
        {
            return album;
        }

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getRssUrl(getUser()));
            list.add(getAlbumURL(getUser(), getAlbum()));
            if(getPhoto() != null)
                list.add(getPhotoURL(getUser(), getAlbum(), getPhoto()));
            else
                list.add((String)null);
            return list;
        }

        public String getPhoto()
        {
            return photo;
        }

        public String getUser()
        {
            return user;
        }

        public boolean isHtml()
        {
            return false;
        }

        public boolean isMedia()
        {
            return true;
        }

        private static final Pattern URL_PATTERN = Pattern.compile("http://picasaweb.google.com/([^/?#&]+)/+((?!searchbrowse)[^/?#&]+)(?:/|/photo)?(?:\\?[^#]*)?(?:#(.*))?");
        private String album;
        private String photo;
        private String user;


        public Photo(String s, String s1, String s2, String s3)
        {
            super(Token.Type.PHOTO, s3);
            user = s;
            album = s1;
            photo = s2;
        }
    }

    public static interface Resources
    {

        public abstract TrieNode getAcronyms();

        public abstract TrieNode getDomainSuffixes();

        public abstract Set getSchemes();

        public abstract TrieNode getSmileys();
    }

    public static class Smiley extends Token
    {

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getRawText());
            return list;
        }

        public boolean isHtml()
        {
            return false;
        }

        public Smiley(String s)
        {
            super(Token.Type.SMILEY, s);
        }
    }

    public static abstract class Token
    {

        public boolean controlCaps()
        {
            return false;
        }

        public List getInfo()
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(getType().toString());
            return arraylist;
        }

        public String getRawText()
        {
            return text;
        }

        public Type getType()
        {
            return type;
        }

        public boolean isArray()
        {
            return isHtml() ^ true;
        }

        public abstract boolean isHtml();

        public boolean isMedia()
        {
            return false;
        }

        public boolean setCaps()
        {
            return false;
        }

        public String toHtml(boolean flag)
        {
            throw new AssertionError("not html");
        }

        protected String text;
        protected Type type;

        protected Token(Type type1, String s)
        {
            type = type1;
            text = s;
        }
    }

    public static final class Token.Type extends Enum
    {

        public static Token.Type valueOf(String s)
        {
            return (Token.Type)Enum.valueOf(com/google/android/util/AbstractMessageParser$Token$Type, s);
        }

        public static Token.Type[] values()
        {
            return $VALUES;
        }

        public String toString()
        {
            return stringRep;
        }

        private static final Token.Type $VALUES[];
        public static final Token.Type ACRONYM;
        public static final Token.Type FLICKR;
        public static final Token.Type FORMAT;
        public static final Token.Type GOOGLE_VIDEO;
        public static final Token.Type HTML;
        public static final Token.Type LINK;
        public static final Token.Type MUSIC;
        public static final Token.Type PHOTO;
        public static final Token.Type SMILEY;
        public static final Token.Type YOUTUBE_VIDEO;
        private String stringRep;

        static 
        {
            HTML = new Token.Type("HTML", 0, "html");
            FORMAT = new Token.Type("FORMAT", 1, "format");
            LINK = new Token.Type("LINK", 2, "l");
            SMILEY = new Token.Type("SMILEY", 3, "e");
            ACRONYM = new Token.Type("ACRONYM", 4, "a");
            MUSIC = new Token.Type("MUSIC", 5, "m");
            GOOGLE_VIDEO = new Token.Type("GOOGLE_VIDEO", 6, "v");
            YOUTUBE_VIDEO = new Token.Type("YOUTUBE_VIDEO", 7, "yt");
            PHOTO = new Token.Type("PHOTO", 8, "p");
            FLICKR = new Token.Type("FLICKR", 9, "f");
            $VALUES = (new Token.Type[] {
                HTML, FORMAT, LINK, SMILEY, ACRONYM, MUSIC, GOOGLE_VIDEO, YOUTUBE_VIDEO, PHOTO, FLICKR
            });
        }

        private Token.Type(String s, int i, String s1)
        {
            super(s, i);
            stringRep = s1;
        }
    }

    public static class TrieNode
    {

        public static void addToTrie(TrieNode trienode, String s, String s1)
        {
            for(int i = 0; i < s.length(); i++)
                trienode = trienode.getOrCreateChild(s.charAt(i));

            trienode.setValue(s1);
        }

        public final boolean exists()
        {
            boolean flag;
            if(value != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public TrieNode getChild(char c)
        {
            return (TrieNode)children.get(Character.valueOf(c));
        }

        public TrieNode getOrCreateChild(char c)
        {
            Character character = Character.valueOf(c);
            TrieNode trienode = (TrieNode)children.get(character);
            TrieNode trienode1 = trienode;
            if(trienode == null)
            {
                trienode1 = new TrieNode((new StringBuilder()).append(text).append(String.valueOf(c)).toString());
                children.put(character, trienode1);
            }
            return trienode1;
        }

        public final String getText()
        {
            return text;
        }

        public final String getValue()
        {
            return value;
        }

        public void setValue(String s)
        {
            value = s;
        }

        private final HashMap children;
        private String text;
        private String value;

        public TrieNode()
        {
            this("");
        }

        public TrieNode(String s)
        {
            children = new HashMap();
            text = s;
        }
    }

    public static class Video extends Token
    {

        public static String getRssUrl(String s)
        {
            return (new StringBuilder()).append("http://video.google.com/videofeed?type=docid&output=rss&sourceid=gtalk&docid=").append(s).toString();
        }

        public static String getURL(String s)
        {
            return getURL(s, null);
        }

        public static String getURL(String s, String s1)
        {
            if(s1 != null) goto _L2; else goto _L1
_L1:
            String s2 = "";
_L4:
            return (new StringBuilder()).append("http://video.google.com/videoplay?").append(s2).append("docid=").append(s).toString();
_L2:
            s2 = s1;
            if(s1.length() > 0)
                s2 = (new StringBuilder()).append(s1).append("&").toString();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static Video matchURL(String s, String s1)
        {
            s = URL_PATTERN.matcher(s);
            if(s.matches())
                return new Video(s.group(1), s1);
            else
                return null;
        }

        public String getDocID()
        {
            return docid;
        }

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getRssUrl(docid));
            list.add(getURL(docid));
            return list;
        }

        public boolean isHtml()
        {
            return false;
        }

        public boolean isMedia()
        {
            return true;
        }

        private static final Pattern URL_PATTERN = Pattern.compile("(?i)http://video\\.google\\.[a-z0-9]+(?:\\.[a-z0-9]+)?/videoplay\\?.*?\\bdocid=(-?\\d+).*");
        private String docid;


        public Video(String s, String s1)
        {
            super(Token.Type.GOOGLE_VIDEO, s1);
            docid = s;
        }
    }

    public static class YouTubeVideo extends Token
    {

        public static String getPrefixedURL(boolean flag, String s, String s1, String s2)
        {
            String s3;
            String s4;
            s3 = "";
            if(flag)
                s3 = "http://";
            s4 = s;
            if(s == null)
                s4 = "";
            if(s2 != null) goto _L2; else goto _L1
_L1:
            s = "";
_L4:
            return (new StringBuilder()).append(s3).append(s4).append("youtube.com/watch?").append(s).append("v=").append(s1).toString();
_L2:
            s = s2;
            if(s2.length() > 0)
                s = (new StringBuilder()).append(s2).append("&").toString();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static String getRssUrl(String s)
        {
            return (new StringBuilder()).append("http://youtube.com/watch?v=").append(s).toString();
        }

        public static String getURL(String s)
        {
            return getURL(s, null);
        }

        public static String getURL(String s, String s1)
        {
            if(s1 != null) goto _L2; else goto _L1
_L1:
            String s2 = "";
_L4:
            return (new StringBuilder()).append("http://youtube.com/watch?").append(s2).append("v=").append(s).toString();
_L2:
            s2 = s1;
            if(s1.length() > 0)
                s2 = (new StringBuilder()).append(s1).append("&").toString();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static YouTubeVideo matchURL(String s, String s1)
        {
            s = URL_PATTERN.matcher(s);
            if(s.matches())
                return new YouTubeVideo(s.group(1), s1);
            else
                return null;
        }

        public String getDocID()
        {
            return docid;
        }

        public List getInfo()
        {
            List list = super.getInfo();
            list.add(getRssUrl(docid));
            list.add(getURL(docid));
            return list;
        }

        public boolean isHtml()
        {
            return false;
        }

        public boolean isMedia()
        {
            return true;
        }

        private static final Pattern URL_PATTERN = Pattern.compile("(?i)http://(?:[a-z0-9]+\\.)?youtube\\.[a-z0-9]+(?:\\.[a-z0-9]+)?/watch\\?.*\\bv=([-_a-zA-Z0-9=]+).*");
        private String docid;


        public YouTubeVideo(String s, String s1)
        {
            super(Token.Type.YOUTUBE_VIDEO, s1);
            docid = s;
        }
    }


    private static int[] _2D_getcom_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues()
    {
        if(_2D_com_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues != null)
            return _2D_com_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues;
        int ai[] = new int[Token.Type.values().length];
        try
        {
            ai[Token.Type.ACRONYM.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[Token.Type.FLICKR.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[Token.Type.FORMAT.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[Token.Type.GOOGLE_VIDEO.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[Token.Type.HTML.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Token.Type.LINK.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Token.Type.MUSIC.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Token.Type.PHOTO.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Token.Type.SMILEY.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Token.Type.YOUTUBE_VIDEO.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_com_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues = ai;
        return ai;
    }

    public AbstractMessageParser(String s)
    {
        this(s, true, true, true, true, true, true);
    }

    public AbstractMessageParser(String s, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5)
    {
        text = s;
        nextChar = 0;
        nextClass = 10;
        parts = new ArrayList();
        tokens = new ArrayList();
        formatStart = new HashMap();
        parseSmilies = flag;
        parseAcronyms = flag1;
        parseFormatting = flag2;
        parseUrls = flag3;
        parseMusic = flag4;
        parseMeText = flag5;
    }

    private void addToken(Token token)
    {
        tokens.add(token);
    }

    private void addURLToken(String s, String s1)
    {
        addToken(tokenForUrl(s, s1));
    }

    private void buildParts(String s)
    {
        int i = 0;
_L2:
        Token token;
        if(i >= tokens.size())
            break MISSING_BLOCK_LABEL_84;
        token = (Token)tokens.get(i);
        break MISSING_BLOCK_LABEL_25;
        if(token.isMedia() || parts.size() == 0 || lastPart().isMedia())
            parts.add(new Part());
        lastPart().add(token);
        i++;
        continue; /* Loop/switch isn't completed */
        if(parts.size() > 0)
            ((Part)parts.get(0)).setMeText(s);
        return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private int getCharClass(int i)
    {
        if(i < 0 || text.length() <= i)
            return 0;
        char c = text.charAt(i);
        if(Character.isWhitespace(c))
            return 1;
        if(Character.isLetter(c))
            return 2;
        if(Character.isDigit(c))
            return 3;
        if(isPunctuation(c))
        {
            i = nextClass + 1;
            nextClass = i;
            return i;
        } else
        {
            return 4;
        }
    }

    private boolean isDomainChar(char c)
    {
        boolean flag;
        if(c != '-' && !Character.isLetter(c))
            flag = Character.isDigit(c);
        else
            flag = true;
        return flag;
    }

    private static boolean isFormatChar(char c)
    {
        switch(c)
        {
        default:
            return false;

        case 42: // '*'
        case 94: // '^'
        case 95: // '_'
            return true;
        }
    }

    private static boolean isPunctuation(char c)
    {
        switch(c)
        {
        default:
            return false;

        case 33: // '!'
        case 34: // '"'
        case 40: // '('
        case 41: // ')'
        case 44: // ','
        case 46: // '.'
        case 58: // ':'
        case 59: // ';'
        case 63: // '?'
            return true;
        }
    }

    private static boolean isSmileyBreak(char c, char c1)
    {
        switch(c)
        {
        case 36: // '$'
        case 38: // '&'
        case 42: // '*'
        case 43: // '+'
        case 45: // '-'
        case 47: // '/'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 64: // '@'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 124: // '|'
        case 125: // '}'
        case 126: // '~'
            switch(c1)
            {
            case 35: // '#'
            case 36: // '$'
            case 37: // '%'
            case 42: // '*'
            case 47: // '/'
            case 60: // '<'
            case 61: // '='
            case 62: // '>'
            case 64: // '@'
            case 91: // '['
            case 92: // '\\'
            case 94: // '^'
            case 126: // '~'
                return true;
            }
            break;
        }
        while(true) 
            return false;
    }

    private boolean isSmileyBreak(int i)
    {
        return i > 0 && i < text.length() && isSmileyBreak(text.charAt(i - 1), text.charAt(i));
    }

    private boolean isURLBreak(int i)
    {
        switch(getCharClass(i - 1))
        {
        default:
            return true;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return false;
        }
    }

    private boolean isValidDomain(String s)
    {
        return matches(getResources().getDomainSuffixes(), reverse(s));
    }

    private boolean isWordBreak(int i)
    {
        boolean flag;
        if(getCharClass(i - 1) != getCharClass(i))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private Part lastPart()
    {
        return (Part)parts.get(parts.size() - 1);
    }

    private static TrieNode longestMatch(TrieNode trienode, AbstractMessageParser abstractmessageparser, int i)
    {
        return longestMatch(trienode, abstractmessageparser, i, false);
    }

    private static TrieNode longestMatch(TrieNode trienode, AbstractMessageParser abstractmessageparser, int i, boolean flag)
    {
        TrieNode trienode1 = null;
_L2:
        TrieNode trienode2;
        int j;
label0:
        {
            if(i < abstractmessageparser.getRawText().length())
            {
                String s = abstractmessageparser.getRawText();
                j = i + 1;
                trienode = trienode.getChild(s.charAt(i));
                if(trienode != null)
                    break label0;
            }
            return trienode1;
        }
        trienode2 = trienode1;
        if(trienode.exists())
        {
            if(!abstractmessageparser.isWordBreak(j))
                break; /* Loop/switch isn't completed */
            trienode2 = trienode;
        }
_L3:
        i = j;
        trienode1 = trienode2;
        if(true) goto _L2; else goto _L1
_L1:
        trienode2 = trienode1;
        if(flag)
        {
            trienode2 = trienode1;
            if(abstractmessageparser.isSmileyBreak(j))
                trienode2 = trienode;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private static boolean matches(TrieNode trienode, String s)
    {
        int i = 0;
        do
        {
label0:
            {
                if(i < s.length())
                {
                    trienode = trienode.getChild(s.charAt(i));
                    if(trienode != null)
                        break label0;
                }
                return false;
            }
            if(trienode.exists())
                return true;
            i++;
        } while(true);
    }

    private boolean parseAcronym()
    {
        if(!parseAcronyms)
            return false;
        TrieNode trienode = longestMatch(getResources().getAcronyms(), this, nextChar);
        if(trienode == null)
        {
            return false;
        } else
        {
            addToken(new Acronym(trienode.getText(), trienode.getValue()));
            nextChar = nextChar + trienode.getText().length();
            return true;
        }
    }

    private boolean parseFormatting()
    {
        if(!parseFormatting)
            return false;
        int i;
        for(i = nextChar; i < text.length() && isFormatChar(text.charAt(i)); i++);
        if(i == nextChar || isWordBreak(i) ^ true)
            return false;
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        int j = nextChar;
        while(j < i) 
        {
            char c = text.charAt(j);
            Character character = Character.valueOf(c);
            if(linkedhashmap.containsKey(character))
            {
                addToken(new Format(c, false));
            } else
            {
                Format format = (Format)formatStart.get(character);
                if(format != null)
                {
                    format.setMatched(true);
                    formatStart.remove(character);
                    linkedhashmap.put(character, Boolean.TRUE);
                } else
                {
                    Format format1 = new Format(c, true);
                    formatStart.put(character, format1);
                    addToken(format1);
                    linkedhashmap.put(character, Boolean.FALSE);
                }
            }
            j++;
        }
        Iterator iterator = linkedhashmap.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj = (Character)iterator.next();
            if(linkedhashmap.get(obj) == Boolean.TRUE)
            {
                obj = new Format(((Character) (obj)).charValue(), false);
                ((Format) (obj)).setMatched(true);
                addToken(((Token) (obj)));
            }
        } while(true);
        nextChar = i;
        return true;
    }

    private boolean parseMusicTrack()
    {
        if(parseMusic && text.startsWith("\u266B "))
        {
            addToken(new MusicTrack(text.substring("\u266B ".length())));
            nextChar = text.length();
            return true;
        } else
        {
            return false;
        }
    }

    private boolean parseSmiley()
    {
        if(!parseSmilies)
            return false;
        TrieNode trienode = longestMatch(getResources().getSmileys(), this, nextChar, true);
        if(trienode == null)
            return false;
        int i = getCharClass(nextChar - 1);
        int j = getCharClass(nextChar + trienode.getText().length());
        if((i == 2 || i == 3) && (j == 2 || j == 3))
        {
            return false;
        } else
        {
            addToken(new Smiley(trienode.getText()));
            nextChar = nextChar + trienode.getText().length();
            return true;
        }
    }

    private void parseText()
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder();
        i = nextChar;
_L9:
        char c;
        String s = text;
        int j = nextChar;
        nextChar = j + 1;
        c = s.charAt(j);
        c;
        JVM INSTR lookupswitch 6: default 100
    //                   10: 201
    //                   34: 179
    //                   38: 168
    //                   39: 190
    //                   60: 146
    //                   62: 157;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L2:
        break MISSING_BLOCK_LABEL_201;
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append(c);
_L10:
        if(isWordBreak(nextChar))
        {
            addToken(new Html(text.substring(i, nextChar), stringbuilder.toString()));
            return;
        }
        if(true) goto _L9; else goto _L8
_L8:
        stringbuilder.append("&lt;");
          goto _L10
_L7:
        stringbuilder.append("&gt;");
          goto _L10
_L4:
        stringbuilder.append("&amp;");
          goto _L10
_L3:
        stringbuilder.append("&quot;");
          goto _L10
_L5:
        stringbuilder.append("&apos;");
          goto _L10
        stringbuilder.append("<br>");
          goto _L10
    }

    private boolean parseURL()
    {
        int i;
        int j;
        String s;
        int k;
        int l;
        if(!parseUrls || isURLBreak(nextChar) ^ true)
            return false;
        i = nextChar;
        for(j = i; j < text.length() && isDomainChar(text.charAt(j)); j++);
        s = "";
        k = 0;
        l = 0;
        if(j == text.length())
            return false;
        if(text.charAt(j) != ':') goto _L2; else goto _L1
_L1:
        String s1 = text.substring(nextChar, j);
        if(!getResources().getSchemes().contains(s1))
            return false;
          goto _L3
_L2:
        if(text.charAt(j) != '.')
            break MISSING_BLOCK_LABEL_504;
        k = j;
label0:
        do
        {
label1:
            {
                if(k < text.length())
                {
                    char c = text.charAt(k);
                    if(c == '.' || !(isDomainChar(c) ^ true))
                        break label1;
                }
                if(!isValidDomain(text.substring(nextChar, k)))
                    return false;
                break label0;
            }
            k++;
        } while(true);
        j = k;
        if(k + 1 < text.length())
        {
            j = k;
            if(text.charAt(k) == ':')
            {
                j = k;
                if(Character.isDigit(text.charAt(k + 1)))
                {
                    k++;
                    do
                    {
                        j = k;
                        if(k >= text.length())
                            break;
                        j = k;
                        if(!Character.isDigit(text.charAt(k)))
                            break;
                        k++;
                    } while(true);
                }
            }
        }
        if(j != text.length()) goto _L5; else goto _L4
_L4:
        k = 1;
_L7:
        s = "http://";
_L3:
        l = j;
        if(k == 0)
            do
            {
                l = j;
                if(j >= text.length())
                    break;
                l = j;
                if(!(Character.isWhitespace(text.charAt(j)) ^ true))
                    break;
                j++;
            } while(true);
        break MISSING_BLOCK_LABEL_506;
_L5:
        char c1;
        c1 = text.charAt(j);
        if(c1 == '?')
        {
            if(j + 1 == text.length())
            {
                k = 1;
                continue; /* Loop/switch isn't completed */
            }
            c1 = text.charAt(j + 1);
            if(!Character.isWhitespace(c1))
            {
                k = l;
                if(!isPunctuation(c1))
                    continue; /* Loop/switch isn't completed */
            }
            k = 1;
            continue; /* Loop/switch isn't completed */
        }
        if(isPunctuation(c1))
        {
            k = 1;
            continue; /* Loop/switch isn't completed */
        }
        if(!Character.isWhitespace(c1))
            break; /* Loop/switch isn't completed */
        k = 1;
        if(true) goto _L7; else goto _L6
_L6:
        k = l;
        if(c1 == '/') goto _L7; else goto _L8
_L8:
        k = l;
        if(c1 == '#') goto _L7; else goto _L9
_L9:
        return false;
        return false;
        String s2 = text.substring(i, l);
        addURLToken((new StringBuilder()).append(s).append(s2).toString(), s2);
        nextChar = l;
        return true;
    }

    protected static String reverse(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--)
            stringbuilder.append(s.charAt(i));

        return stringbuilder.toString();
    }

    public static Token tokenForUrl(String s, String s1)
    {
        if(s == null)
            return null;
        Object obj = Video.matchURL(s, s1);
        if(obj != null)
            return ((Token) (obj));
        obj = YouTubeVideo.matchURL(s, s1);
        if(obj != null)
            return ((Token) (obj));
        obj = Photo.matchURL(s, s1);
        if(obj != null)
            return ((Token) (obj));
        obj = FlickrPhoto.matchURL(s, s1);
        if(obj != null)
            return ((Token) (obj));
        else
            return new Link(s, s1);
    }

    public final Part getPart(int i)
    {
        return (Part)parts.get(i);
    }

    public final int getPartCount()
    {
        return parts.size();
    }

    public final List getParts()
    {
        return parts;
    }

    public final String getRawText()
    {
        return text;
    }

    protected abstract Resources getResources();

    public void parse()
    {
        if(parseMusicTrack())
        {
            buildParts(null);
            return;
        }
        Object obj = null;
        String s = obj;
        if(parseMeText)
        {
            s = obj;
            if(text.startsWith("/me"))
            {
                s = obj;
                if(text.length() > 3)
                {
                    s = obj;
                    if(Character.isWhitespace(text.charAt(3)))
                    {
                        s = text.substring(0, 4);
                        text = text.substring(4);
                    }
                }
            }
        }
        boolean flag = false;
        do
        {
            if(nextChar >= text.length())
                break;
            if(!isWordBreak(nextChar) && (!flag || isSmileyBreak(nextChar) ^ true))
                throw new AssertionError("last chunk did not end at word break");
            if(parseSmiley())
            {
                flag = true;
            } else
            {
                boolean flag1 = false;
                flag = flag1;
                if(!parseAcronym())
                {
                    flag = flag1;
                    if(parseURL() ^ true)
                    {
                        flag = flag1;
                        if(parseFormatting() ^ true)
                        {
                            parseText();
                            flag = flag1;
                        }
                    }
                }
            }
        } while(true);
        for(int i = 0; i < tokens.size(); i++)
        {
            if(!((Token)tokens.get(i)).isMedia())
                continue;
            if(i > 0 && (tokens.get(i - 1) instanceof Html))
                ((Html)tokens.get(i - 1)).trimLeadingWhitespace();
            if(i + 1 < tokens.size() && (tokens.get(i + 1) instanceof Html))
                ((Html)tokens.get(i + 1)).trimTrailingWhitespace();
        }

        int k;
        for(int j = 0; j < tokens.size(); j = k + 1)
        {
            k = j;
            if(!((Token)tokens.get(j)).isHtml())
                continue;
            k = j;
            if(((Token)tokens.get(j)).toHtml(true).length() == 0)
            {
                tokens.remove(j);
                k = j - 1;
            }
        }

        buildParts(s);
    }

    public String toHtml()
    {
        StringBuilder stringbuilder;
        Iterator iterator;
        stringbuilder = new StringBuilder();
        iterator = parts.iterator();
_L6:
        Object obj;
        boolean flag;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (Part)iterator.next();
        flag = false;
        stringbuilder.append("<p>");
        obj = ((Part) (obj)).getTokens().iterator();
_L2:
        Token token;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_538;
        token = (Token)((Iterator) (obj)).next();
        if(!token.isHtml())
            break; /* Loop/switch isn't completed */
        stringbuilder.append(token.toHtml(flag));
_L4:
        if(token.controlCaps())
            flag = token.setCaps();
        if(true) goto _L2; else goto _L1
_L1:
        switch(_2D_getcom_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues()[token.getType().ordinal()])
        {
        default:
            throw new AssertionError((new StringBuilder()).append("unknown token type: ").append(token.getType()).toString());

        case 4: // '\004'
            stringbuilder.append("<a href=\"");
            stringbuilder.append(((Link)token).getURL());
            stringbuilder.append("\">");
            stringbuilder.append(token.getRawText());
            stringbuilder.append("</a>");
            break;

        case 7: // '\007'
            stringbuilder.append(token.getRawText());
            break;

        case 1: // '\001'
            stringbuilder.append(token.getRawText());
            break;

        case 5: // '\005'
            stringbuilder.append(((MusicTrack)token).getTrack());
            break;

        case 3: // '\003'
            stringbuilder.append("<a href=\"");
            Video video = (Video)token;
            stringbuilder.append(Video.getURL(((Video)token).getDocID()));
            stringbuilder.append("\">");
            stringbuilder.append(token.getRawText());
            stringbuilder.append("</a>");
            break;

        case 8: // '\b'
            stringbuilder.append("<a href=\"");
            YouTubeVideo youtubevideo = (YouTubeVideo)token;
            stringbuilder.append(YouTubeVideo.getURL(((YouTubeVideo)token).getDocID()));
            stringbuilder.append("\">");
            stringbuilder.append(token.getRawText());
            stringbuilder.append("</a>");
            break;

        case 6: // '\006'
            stringbuilder.append("<a href=\"");
            stringbuilder.append(Photo.getAlbumURL(((Photo)token).getUser(), ((Photo)token).getAlbum()));
            stringbuilder.append("\">");
            stringbuilder.append(token.getRawText());
            stringbuilder.append("</a>");
            break;

        case 2: // '\002'
            Photo photo = (Photo)token;
            stringbuilder.append("<a href=\"");
            stringbuilder.append(((FlickrPhoto)token).getUrl());
            stringbuilder.append("\">");
            stringbuilder.append(token.getRawText());
            stringbuilder.append("</a>");
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
        stringbuilder.append("</p>\n");
        if(true) goto _L6; else goto _L5
_L5:
        return stringbuilder.toString();
    }

    private static final int _2D_com_2D_google_2D_android_2D_util_2D_AbstractMessageParser$Token$TypeSwitchesValues[];
    public static final String musicNote = "\u266B ";
    private HashMap formatStart;
    private int nextChar;
    private int nextClass;
    private boolean parseAcronyms;
    private boolean parseFormatting;
    private boolean parseMeText;
    private boolean parseMusic;
    private boolean parseSmilies;
    private boolean parseUrls;
    private ArrayList parts;
    private String text;
    private ArrayList tokens;
}
