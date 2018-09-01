// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Intent;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import libcore.net.UriCodec;

public abstract class Uri
    implements Parcelable, Comparable
{
    private static abstract class AbstractHierarchicalUri extends Uri
    {

        private Part getUserInfoPart()
        {
            Part part;
            if(userInfo == null)
            {
                part = Part.fromEncoded(parseUserInfo());
                userInfo = part;
            } else
            {
                part = userInfo;
            }
            return part;
        }

        private String parseHost()
        {
            String s = getEncodedAuthority();
            if(s == null)
                return null;
            int i = s.lastIndexOf('@');
            int j = s.indexOf(':', i);
            if(j == -1)
                s = s.substring(i + 1);
            else
                s = s.substring(i + 1, j);
            return decode(s);
        }

        private int parsePort()
        {
            String s = getEncodedAuthority();
            if(s == null)
                return -1;
            int i = s.indexOf(':', s.lastIndexOf('@'));
            if(i == -1)
                return -1;
            s = decode(s.substring(i + 1));
            try
            {
                i = Integer.parseInt(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                Log.w(Uri._2D_get0(), "Error parsing port string.", numberformatexception);
                return -1;
            }
            return i;
        }

        private String parseUserInfo()
        {
            String s = null;
            String s1 = getEncodedAuthority();
            if(s1 == null)
                return null;
            int i = s1.lastIndexOf('@');
            if(i != -1)
                s = s1.substring(0, i);
            return s;
        }

        public final String getEncodedUserInfo()
        {
            return getUserInfoPart().getEncoded();
        }

        public String getHost()
        {
            boolean flag;
            String s;
            if(host != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = host;
            } else
            {
                s = parseHost();
                host = s;
            }
            return s;
        }

        public String getLastPathSegment()
        {
            List list = getPathSegments();
            int i = list.size();
            if(i == 0)
                return null;
            else
                return (String)list.get(i - 1);
        }

        public int getPort()
        {
            int i;
            if(port == -2)
            {
                i = parsePort();
                port = i;
            } else
            {
                i = port;
            }
            return i;
        }

        public String getUserInfo()
        {
            return getUserInfoPart().getDecoded();
        }

        private volatile String host;
        private volatile int port;
        private Part userInfo;

        private AbstractHierarchicalUri()
        {
            super(null);
            host = Uri._2D_get1();
            port = -2;
        }

        AbstractHierarchicalUri(AbstractHierarchicalUri abstracthierarchicaluri)
        {
            this();
        }
    }

    static abstract class AbstractPart
    {

        final String getDecoded()
        {
            boolean flag;
            String s;
            if(decoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = decoded;
            } else
            {
                s = Uri.decode(encoded);
                decoded = s;
            }
            return s;
        }

        abstract String getEncoded();

        final void writeTo(Parcel parcel)
        {
            boolean flag;
            boolean flag1;
            if(encoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(decoded != Uri._2D_get1())
                flag1 = true;
            else
                flag1 = false;
            if(flag && flag1)
            {
                parcel.writeInt(0);
                parcel.writeString(encoded);
                parcel.writeString(decoded);
            } else
            if(flag)
            {
                parcel.writeInt(1);
                parcel.writeString(encoded);
            } else
            if(flag1)
            {
                parcel.writeInt(2);
                parcel.writeString(decoded);
            } else
            {
                throw new IllegalArgumentException("Neither encoded nor decoded");
            }
        }

        volatile String decoded;
        volatile String encoded;

        AbstractPart(String s, String s1)
        {
            encoded = s;
            decoded = s1;
        }
    }

    static class AbstractPart.Representation
    {

        static final int BOTH = 0;
        static final int DECODED = 2;
        static final int ENCODED = 1;

        AbstractPart.Representation()
        {
        }
    }

    public static final class Builder
    {

        private boolean hasSchemeOrAuthority()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(scheme == null)
                if(authority != null && authority != Part.NULL)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public Builder appendEncodedPath(String s)
        {
            return path(PathPart.appendEncodedSegment(path, s));
        }

        public Builder appendPath(String s)
        {
            return path(PathPart.appendDecodedSegment(path, s));
        }

        public Builder appendQueryParameter(String s, String s1)
        {
            opaquePart = null;
            s = (new StringBuilder()).append(Uri.encode(s, null)).append("=").append(Uri.encode(s1, null)).toString();
            if(query == null)
            {
                query = Part.fromEncoded(s);
                return this;
            }
            s1 = query.getEncoded();
            if(s1 == null || s1.length() == 0)
                query = Part.fromEncoded(s);
            else
                query = Part.fromEncoded((new StringBuilder()).append(s1).append("&").append(s).toString());
            return this;
        }

        Builder authority(Part part)
        {
            opaquePart = null;
            authority = part;
            return this;
        }

        public Builder authority(String s)
        {
            return authority(Part.fromDecoded(s));
        }

        public Uri build()
        {
            PathPart pathpart;
            if(opaquePart != null)
                if(scheme == null)
                    throw new UnsupportedOperationException("An opaque URI must have a scheme.");
                else
                    return new OpaqueUri(scheme, opaquePart, fragment, null);
            pathpart = path;
            if(pathpart != null && pathpart != PathPart.NULL) goto _L2; else goto _L1
_L1:
            PathPart pathpart1 = PathPart.EMPTY;
_L4:
            return new HierarchicalUri(scheme, authority, pathpart1, query, fragment, null);
_L2:
            pathpart1 = pathpart;
            if(hasSchemeOrAuthority())
                pathpart1 = PathPart.makeAbsolute(pathpart);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Builder clearQuery()
        {
            return query((Part)null);
        }

        public Builder encodedAuthority(String s)
        {
            return authority(Part.fromEncoded(s));
        }

        public Builder encodedFragment(String s)
        {
            return fragment(Part.fromEncoded(s));
        }

        public Builder encodedOpaquePart(String s)
        {
            return opaquePart(Part.fromEncoded(s));
        }

        public Builder encodedPath(String s)
        {
            return path(PathPart.fromEncoded(s));
        }

        public Builder encodedQuery(String s)
        {
            return query(Part.fromEncoded(s));
        }

        Builder fragment(Part part)
        {
            fragment = part;
            return this;
        }

        public Builder fragment(String s)
        {
            return fragment(Part.fromDecoded(s));
        }

        Builder opaquePart(Part part)
        {
            opaquePart = part;
            return this;
        }

        public Builder opaquePart(String s)
        {
            return opaquePart(Part.fromDecoded(s));
        }

        Builder path(PathPart pathpart)
        {
            opaquePart = null;
            path = pathpart;
            return this;
        }

        public Builder path(String s)
        {
            return path(PathPart.fromDecoded(s));
        }

        Builder query(Part part)
        {
            opaquePart = null;
            query = part;
            return this;
        }

        public Builder query(String s)
        {
            return query(Part.fromDecoded(s));
        }

        public Builder scheme(String s)
        {
            scheme = s;
            return this;
        }

        public String toString()
        {
            return build().toString();
        }

        private Part authority;
        private Part fragment;
        private Part opaquePart;
        private PathPart path;
        private Part query;
        private String scheme;

        public Builder()
        {
        }
    }

    private static class HierarchicalUri extends AbstractHierarchicalUri
    {

        private void appendSspTo(StringBuilder stringbuilder)
        {
            String s = authority.getEncoded();
            if(s != null)
                stringbuilder.append("//").append(s);
            s = path.getEncoded();
            if(s != null)
                stringbuilder.append(s);
            if(!query.isEmpty())
                stringbuilder.append('?').append(query.getEncoded());
        }

        private Part getSsp()
        {
            Part part;
            if(ssp == null)
            {
                part = Part.fromEncoded(makeSchemeSpecificPart());
                ssp = part;
            } else
            {
                part = ssp;
            }
            return part;
        }

        private String makeSchemeSpecificPart()
        {
            StringBuilder stringbuilder = new StringBuilder();
            appendSspTo(stringbuilder);
            return stringbuilder.toString();
        }

        private String makeUriString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            if(scheme != null)
                stringbuilder.append(scheme).append(':');
            appendSspTo(stringbuilder);
            if(!fragment.isEmpty())
                stringbuilder.append('#').append(fragment.getEncoded());
            return stringbuilder.toString();
        }

        static Uri readFrom(Parcel parcel)
        {
            return new HierarchicalUri(parcel.readString(), Part.readFrom(parcel), PathPart.readFrom(parcel), Part.readFrom(parcel), Part.readFrom(parcel));
        }

        public Builder buildUpon()
        {
            return (new Builder()).scheme(scheme).authority(authority).path(path).query(query).fragment(fragment);
        }

        public int describeContents()
        {
            return 0;
        }

        public String getAuthority()
        {
            return authority.getDecoded();
        }

        public String getEncodedAuthority()
        {
            return authority.getEncoded();
        }

        public String getEncodedFragment()
        {
            return fragment.getEncoded();
        }

        public String getEncodedPath()
        {
            return path.getEncoded();
        }

        public String getEncodedQuery()
        {
            return query.getEncoded();
        }

        public String getEncodedSchemeSpecificPart()
        {
            return getSsp().getEncoded();
        }

        public String getFragment()
        {
            return fragment.getDecoded();
        }

        public String getPath()
        {
            return path.getDecoded();
        }

        public List getPathSegments()
        {
            return path.getPathSegments();
        }

        public String getQuery()
        {
            return query.getDecoded();
        }

        public String getScheme()
        {
            return scheme;
        }

        public String getSchemeSpecificPart()
        {
            return getSsp().getDecoded();
        }

        public boolean isHierarchical()
        {
            return true;
        }

        public boolean isRelative()
        {
            boolean flag;
            if(scheme == null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            boolean flag;
            String s;
            if(uriString != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = uriString;
            } else
            {
                s = makeUriString();
                uriString = s;
            }
            return s;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(3);
            parcel.writeString(scheme);
            authority.writeTo(parcel);
            path.writeTo(parcel);
            query.writeTo(parcel);
            fragment.writeTo(parcel);
        }

        static final int TYPE_ID = 3;
        private final Part authority;
        private final Part fragment;
        private final PathPart path;
        private final Part query;
        private final String scheme;
        private Part ssp;
        private volatile String uriString;

        private HierarchicalUri(String s, Part part, PathPart pathpart, Part part1, Part part2)
        {
            super(null);
            uriString = Uri._2D_get1();
            scheme = s;
            authority = Part.nonNull(part);
            s = pathpart;
            if(pathpart == null)
                s = PathPart.NULL;
            path = s;
            query = Part.nonNull(part1);
            fragment = Part.nonNull(part2);
        }

        HierarchicalUri(String s, Part part, PathPart pathpart, Part part1, Part part2, HierarchicalUri hierarchicaluri)
        {
            this(s, part, pathpart, part1, part2);
        }
    }

    private static class OpaqueUri extends Uri
    {

        static Uri readFrom(Parcel parcel)
        {
            return new OpaqueUri(parcel.readString(), Part.readFrom(parcel), Part.readFrom(parcel));
        }

        public Builder buildUpon()
        {
            return (new Builder()).scheme(scheme).opaquePart(ssp).fragment(fragment);
        }

        public int describeContents()
        {
            return 0;
        }

        public String getAuthority()
        {
            return null;
        }

        public String getEncodedAuthority()
        {
            return null;
        }

        public String getEncodedFragment()
        {
            return fragment.getEncoded();
        }

        public String getEncodedPath()
        {
            return null;
        }

        public String getEncodedQuery()
        {
            return null;
        }

        public String getEncodedSchemeSpecificPart()
        {
            return ssp.getEncoded();
        }

        public String getEncodedUserInfo()
        {
            return null;
        }

        public String getFragment()
        {
            return fragment.getDecoded();
        }

        public String getHost()
        {
            return null;
        }

        public String getLastPathSegment()
        {
            return null;
        }

        public String getPath()
        {
            return null;
        }

        public List getPathSegments()
        {
            return Collections.emptyList();
        }

        public int getPort()
        {
            return -1;
        }

        public String getQuery()
        {
            return null;
        }

        public String getScheme()
        {
            return scheme;
        }

        public String getSchemeSpecificPart()
        {
            return ssp.getDecoded();
        }

        public String getUserInfo()
        {
            return null;
        }

        public boolean isHierarchical()
        {
            return false;
        }

        public boolean isRelative()
        {
            boolean flag;
            if(scheme == null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            boolean flag;
            if(cachedString != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
                return cachedString;
            Object obj = new StringBuilder();
            ((StringBuilder) (obj)).append(scheme).append(':');
            ((StringBuilder) (obj)).append(getEncodedSchemeSpecificPart());
            if(!fragment.isEmpty())
                ((StringBuilder) (obj)).append('#').append(fragment.getEncoded());
            obj = ((StringBuilder) (obj)).toString();
            cachedString = ((String) (obj));
            return ((String) (obj));
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(2);
            parcel.writeString(scheme);
            ssp.writeTo(parcel);
            fragment.writeTo(parcel);
        }

        static final int TYPE_ID = 2;
        private volatile String cachedString;
        private final Part fragment;
        private final String scheme;
        private final Part ssp;

        private OpaqueUri(String s, Part part, Part part1)
        {
            super(null);
            cachedString = Uri._2D_get1();
            scheme = s;
            ssp = part;
            s = part1;
            if(part1 == null)
                s = Part.NULL;
            fragment = s;
        }

        OpaqueUri(String s, Part part, Part part1, OpaqueUri opaqueuri)
        {
            this(s, part, part1);
        }
    }

    static class Part extends AbstractPart
    {

        static Part from(String s, String s1)
        {
            if(s == null)
                return NULL;
            if(s.length() == 0)
                return EMPTY;
            if(s1 == null)
                return NULL;
            if(s1.length() == 0)
                return EMPTY;
            else
                return new Part(s, s1);
        }

        static Part fromDecoded(String s)
        {
            return from(Uri._2D_get1(), s);
        }

        static Part fromEncoded(String s)
        {
            return from(s, Uri._2D_get1());
        }

        static Part nonNull(Part part)
        {
            Part part1 = part;
            if(part == null)
                part1 = NULL;
            return part1;
        }

        static Part readFrom(Parcel parcel)
        {
            int i = parcel.readInt();
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown representation: ").append(i).toString());

            case 0: // '\0'
                return from(parcel.readString(), parcel.readString());

            case 1: // '\001'
                return fromEncoded(parcel.readString());

            case 2: // '\002'
                return fromDecoded(parcel.readString());
            }
        }

        String getEncoded()
        {
            boolean flag;
            String s;
            if(encoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = encoded;
            } else
            {
                s = Uri.encode(decoded);
                encoded = s;
            }
            return s;
        }

        boolean isEmpty()
        {
            return false;
        }

        static final Part EMPTY = new EmptyPart("");
        static final Part NULL = new EmptyPart(null);


        private Part(String s, String s1)
        {
            super(s, s1);
        }

        Part(String s, String s1, Part part)
        {
            this(s, s1);
        }
    }

    private static class Part.EmptyPart extends Part
    {

        boolean isEmpty()
        {
            return true;
        }

        public Part.EmptyPart(String s)
        {
            super(s, s, null);
        }
    }

    static class PathPart extends AbstractPart
    {

        static PathPart appendDecodedSegment(PathPart pathpart, String s)
        {
            return appendEncodedSegment(pathpart, Uri.encode(s));
        }

        static PathPart appendEncodedSegment(PathPart pathpart, String s)
        {
            if(pathpart == null)
                return fromEncoded((new StringBuilder()).append("/").append(s).toString());
            String s1 = pathpart.getEncoded();
            pathpart = s1;
            if(s1 == null)
                pathpart = "";
            int i = pathpart.length();
            if(i == 0)
                pathpart = (new StringBuilder()).append("/").append(s).toString();
            else
            if(pathpart.charAt(i - 1) == '/')
                pathpart = (new StringBuilder()).append(pathpart).append(s).toString();
            else
                pathpart = (new StringBuilder()).append(pathpart).append("/").append(s).toString();
            return fromEncoded(pathpart);
        }

        static PathPart from(String s, String s1)
        {
            if(s == null)
                return NULL;
            if(s.length() == 0)
                return EMPTY;
            else
                return new PathPart(s, s1);
        }

        static PathPart fromDecoded(String s)
        {
            return from(Uri._2D_get1(), s);
        }

        static PathPart fromEncoded(String s)
        {
            return from(s, Uri._2D_get1());
        }

        static PathPart makeAbsolute(PathPart pathpart)
        {
            boolean flag;
            String s;
            if(pathpart.encoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
                s = pathpart.encoded;
            else
                s = pathpart.decoded;
            while(s == null || s.length() == 0 || s.startsWith("/")) 
                return pathpart;
            if(flag)
                s = (new StringBuilder()).append("/").append(pathpart.encoded).toString();
            else
                s = Uri._2D_get1();
            if(pathpart.decoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
                pathpart = (new StringBuilder()).append("/").append(pathpart.decoded).toString();
            else
                pathpart = Uri._2D_get1();
            return new PathPart(s, pathpart);
        }

        static PathPart readFrom(Parcel parcel)
        {
            int i = parcel.readInt();
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad representation: ").append(i).toString());

            case 0: // '\0'
                return from(parcel.readString(), parcel.readString());

            case 1: // '\001'
                return fromEncoded(parcel.readString());

            case 2: // '\002'
                return fromDecoded(parcel.readString());
            }
        }

        String getEncoded()
        {
            boolean flag;
            String s;
            if(encoded != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = encoded;
            } else
            {
                s = Uri.encode(decoded, "/");
                encoded = s;
            }
            return s;
        }

        PathSegments getPathSegments()
        {
            if(pathSegments != null)
                return pathSegments;
            String s = getEncoded();
            if(s == null)
            {
                PathSegments pathsegments = PathSegments.EMPTY;
                pathSegments = pathsegments;
                return pathsegments;
            }
            Object obj = new PathSegmentsBuilder();
            int i = 0;
            do
            {
                int j = s.indexOf('/', i);
                if(j <= -1)
                    break;
                if(i < j)
                    ((PathSegmentsBuilder) (obj)).add(Uri.decode(s.substring(i, j)));
                i = j + 1;
            } while(true);
            if(i < s.length())
                ((PathSegmentsBuilder) (obj)).add(Uri.decode(s.substring(i)));
            obj = ((PathSegmentsBuilder) (obj)).build();
            pathSegments = ((PathSegments) (obj));
            return ((PathSegments) (obj));
        }

        static final PathPart EMPTY = new PathPart("", "");
        static final PathPart NULL = new PathPart(null, null);
        private PathSegments pathSegments;


        private PathPart(String s, String s1)
        {
            super(s, s1);
        }
    }

    static class PathSegments extends AbstractList
        implements RandomAccess
    {

        public volatile Object get(int i)
        {
            return get(i);
        }

        public String get(int i)
        {
            if(i >= size)
                throw new IndexOutOfBoundsException();
            else
                return segments[i];
        }

        public int size()
        {
            return size;
        }

        static final PathSegments EMPTY = new PathSegments(null, 0);
        final String segments[];
        final int size;


        PathSegments(String as[], int i)
        {
            segments = as;
            size = i;
        }
    }

    static class PathSegmentsBuilder
    {

        void add(String s)
        {
            if(segments != null) goto _L2; else goto _L1
_L1:
            segments = new String[4];
_L4:
            String as[] = segments;
            int i = size;
            size = i + 1;
            as[i] = s;
            return;
_L2:
            if(size + 1 == segments.length)
            {
                String as1[] = new String[segments.length * 2];
                System.arraycopy(segments, 0, as1, 0, segments.length);
                segments = as1;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        PathSegments build()
        {
            if(segments == null)
                return PathSegments.EMPTY;
            PathSegments pathsegments = new PathSegments(segments, size);
            segments = null;
            return pathsegments;
            Exception exception;
            exception;
            segments = null;
            throw exception;
        }

        String segments[];
        int size;

        PathSegmentsBuilder()
        {
            size = 0;
        }
    }

    private static class StringUri extends AbstractHierarchicalUri
    {

        private int findFragmentSeparator()
        {
            int i;
            if(cachedFsi == -2)
            {
                i = uriString.indexOf('#', findSchemeSeparator());
                cachedFsi = i;
            } else
            {
                i = cachedFsi;
            }
            return i;
        }

        private int findSchemeSeparator()
        {
            int i;
            if(cachedSsi == -2)
            {
                i = uriString.indexOf(':');
                cachedSsi = i;
            } else
            {
                i = cachedSsi;
            }
            return i;
        }

        private Part getAuthorityPart()
        {
            if(authority == null)
            {
                Part part = Part.fromEncoded(parseAuthority(uriString, findSchemeSeparator()));
                authority = part;
                return part;
            } else
            {
                return authority;
            }
        }

        private Part getFragmentPart()
        {
            Part part;
            if(fragment == null)
            {
                part = Part.fromEncoded(parseFragment());
                fragment = part;
            } else
            {
                part = fragment;
            }
            return part;
        }

        private PathPart getPathPart()
        {
            PathPart pathpart;
            if(path == null)
            {
                pathpart = PathPart.fromEncoded(parsePath());
                path = pathpart;
            } else
            {
                pathpart = path;
            }
            return pathpart;
        }

        private Part getQueryPart()
        {
            Part part;
            if(query == null)
            {
                part = Part.fromEncoded(parseQuery());
                query = part;
            } else
            {
                part = query;
            }
            return part;
        }

        private Part getSsp()
        {
            Part part;
            if(ssp == null)
            {
                part = Part.fromEncoded(parseSsp());
                ssp = part;
            } else
            {
                part = ssp;
            }
            return part;
        }

        static String parseAuthority(String s, int i)
        {
            int j = s.length();
            if(j > i + 2 && s.charAt(i + 1) == '/' && s.charAt(i + 2) == '/')
            {
                int k = i + 3;
label0:
                do
                {
                    if(k >= j)
                        break;
                    switch(s.charAt(k))
                    {
                    default:
                        k++;
                        break;

                    case 35: // '#'
                    case 47: // '/'
                    case 63: // '?'
                    case 92: // '\\'
                        break label0;
                    }
                } while(true);
                return s.substring(i + 3, k);
            } else
            {
                return null;
            }
        }

        private String parseFragment()
        {
            int i = findFragmentSeparator();
            String s;
            if(i == -1)
                s = null;
            else
                s = uriString.substring(i + 1);
            return s;
        }

        private String parsePath()
        {
            String s = uriString;
            int i = findSchemeSeparator();
            if(i > -1)
            {
                boolean flag;
                if(i + 1 == s.length())
                    flag = true;
                else
                    flag = false;
                if(flag)
                    return null;
                if(s.charAt(i + 1) != '/')
                    return null;
            }
            return parsePath(s, i);
        }

        static String parsePath(String s, int i)
        {
            int j = s.length();
            if(j <= i + 2 || s.charAt(i + 1) != '/' || s.charAt(i + 2) != '/') goto _L2; else goto _L1
_L1:
            int k = i + 3;
_L4:
            i = k;
            if(k >= j)
                break; /* Loop/switch isn't completed */
            i = k;
            switch(s.charAt(k))
            {
            default:
                k++;
                break;

            case 47: // '/'
            case 92: // '\\'
                break; /* Loop/switch isn't completed */

            case 35: // '#'
            case 63: // '?'
                return "";
            }
            continue; /* Loop/switch isn't completed */
_L2:
            i++;
            break; /* Loop/switch isn't completed */
            if(true) goto _L4; else goto _L3
_L3:
            int l = i;
label0:
            do
            {
                if(l >= j)
                    break;
                switch(s.charAt(l))
                {
                default:
                    l++;
                    break;

                case 35: // '#'
                case 63: // '?'
                    break label0;
                }
            } while(true);
            return s.substring(i, l);
        }

        private String parseQuery()
        {
            int i = uriString.indexOf('?', findSchemeSeparator());
            if(i == -1)
                return null;
            int j = findFragmentSeparator();
            if(j == -1)
                return uriString.substring(i + 1);
            if(j < i)
                return null;
            else
                return uriString.substring(i + 1, j);
        }

        private String parseScheme()
        {
            int i = findSchemeSeparator();
            String s;
            if(i == -1)
                s = null;
            else
                s = uriString.substring(0, i);
            return s;
        }

        private String parseSsp()
        {
            int i = findSchemeSeparator();
            int j = findFragmentSeparator();
            String s;
            if(j == -1)
                s = uriString.substring(i + 1);
            else
                s = uriString.substring(i + 1, j);
            return s;
        }

        static Uri readFrom(Parcel parcel)
        {
            return new StringUri(parcel.readString());
        }

        public Builder buildUpon()
        {
            if(isHierarchical())
                return (new Builder()).scheme(getScheme()).authority(getAuthorityPart()).path(getPathPart()).query(getQueryPart()).fragment(getFragmentPart());
            else
                return (new Builder()).scheme(getScheme()).opaquePart(getSsp()).fragment(getFragmentPart());
        }

        public int describeContents()
        {
            return 0;
        }

        public String getAuthority()
        {
            return getAuthorityPart().getDecoded();
        }

        public String getEncodedAuthority()
        {
            return getAuthorityPart().getEncoded();
        }

        public String getEncodedFragment()
        {
            return getFragmentPart().getEncoded();
        }

        public String getEncodedPath()
        {
            return getPathPart().getEncoded();
        }

        public String getEncodedQuery()
        {
            return getQueryPart().getEncoded();
        }

        public String getEncodedSchemeSpecificPart()
        {
            return getSsp().getEncoded();
        }

        public String getFragment()
        {
            return getFragmentPart().getDecoded();
        }

        public String getPath()
        {
            return getPathPart().getDecoded();
        }

        public List getPathSegments()
        {
            return getPathPart().getPathSegments();
        }

        public String getQuery()
        {
            return getQueryPart().getDecoded();
        }

        public String getScheme()
        {
            boolean flag;
            String s;
            if(scheme != Uri._2D_get1())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                s = scheme;
            } else
            {
                s = parseScheme();
                scheme = s;
            }
            return s;
        }

        public String getSchemeSpecificPart()
        {
            return getSsp().getDecoded();
        }

        public boolean isHierarchical()
        {
            boolean flag = true;
            int i = findSchemeSeparator();
            if(i == -1)
                return true;
            if(uriString.length() == i + 1)
                return false;
            if(uriString.charAt(i + 1) != '/')
                flag = false;
            return flag;
        }

        public boolean isRelative()
        {
            boolean flag;
            if(findSchemeSeparator() == -1)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return uriString;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(1);
            parcel.writeString(uriString);
        }

        static final int TYPE_ID = 1;
        private Part authority;
        private volatile int cachedFsi;
        private volatile int cachedSsi;
        private Part fragment;
        private PathPart path;
        private Part query;
        private volatile String scheme;
        private Part ssp;
        private final String uriString;

        private StringUri(String s)
        {
            super(null);
            cachedSsi = -2;
            cachedFsi = -2;
            scheme = Uri._2D_get1();
            if(s == null)
            {
                throw new NullPointerException("uriString");
            } else
            {
                uriString = s;
                return;
            }
        }

        StringUri(String s, StringUri stringuri)
        {
            this(s);
        }
    }


    static String _2D_get0()
    {
        return LOG;
    }

    static String _2D_get1()
    {
        return NOT_CACHED;
    }

    private Uri()
    {
    }

    Uri(Uri uri)
    {
        this();
    }

    public static String decode(String s)
    {
        if(s == null)
            return null;
        else
            return UriCodec.decode(s, false, StandardCharsets.UTF_8, false);
    }

    public static String encode(String s)
    {
        return encode(s, null);
    }

    public static String encode(String s, String s1)
    {
        Object obj;
        int i;
        int j;
        if(s == null)
            return null;
        obj = null;
        i = s.length();
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Object obj1;
        int k;
        for(k = j; k < i && isAllowed(s.charAt(k), s1); k++);
        if(k == i)
            if(j == 0)
            {
                return s;
            } else
            {
                ((StringBuilder) (obj)).append(s, j, i);
                return ((StringBuilder) (obj)).toString();
            }
        obj1 = obj;
        if(obj == null)
            obj1 = new StringBuilder();
        if(k > j)
            ((StringBuilder) (obj1)).append(s, j, k);
        for(j = k + 1; j < i && isAllowed(s.charAt(j), s1) ^ true; j++);
        obj = s.substring(k, j);
        int l;
        try
        {
            obj = ((String) (obj)).getBytes("UTF-8");
            l = obj.length;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new AssertionError(s);
        }
        k = 0;
        if(k >= l)
            continue; /* Loop/switch isn't completed */
        ((StringBuilder) (obj1)).append('%');
        ((StringBuilder) (obj1)).append(HEX_DIGITS[(obj[k] & 0xf0) >> 4]);
        ((StringBuilder) (obj1)).append(HEX_DIGITS[obj[k] & 0xf]);
        k++;
        if(true)
            break MISSING_BLOCK_LABEL_168;
        obj = obj1;
          goto _L3
_L2:
        if(obj != null)
            s = ((StringBuilder) (obj)).toString();
        return s;
    }

    public static Uri fromFile(File file)
    {
        if(file == null)
        {
            throw new NullPointerException("file");
        } else
        {
            file = PathPart.fromDecoded(file.getAbsolutePath());
            return new HierarchicalUri("file", Part.EMPTY, file, Part.NULL, Part.NULL, null);
        }
    }

    public static Uri fromParts(String s, String s1, String s2)
    {
        if(s == null)
            throw new NullPointerException("scheme");
        if(s1 == null)
            throw new NullPointerException("ssp");
        else
            return new OpaqueUri(s, Part.fromDecoded(s1), Part.fromDecoded(s2), null);
    }

    private static boolean isAllowed(char c, String s)
    {
        boolean flag = true;
        if(c < 'A' || c > 'Z') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'z')
                continue; /* Loop/switch isn't completed */
        }
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if("_-!.~'()*".indexOf(c) != -1)
            continue; /* Loop/switch isn't completed */
        if(s != null)
        {
            flag1 = flag;
            if(s.indexOf(c) != -1)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Uri parse(String s)
    {
        return new StringUri(s, null);
    }

    public static Uri withAppendedPath(Uri uri, String s)
    {
        return uri.buildUpon().appendEncodedPath(s).build();
    }

    public static void writeToParcel(Parcel parcel, Uri uri)
    {
        if(uri == null)
            parcel.writeInt(0);
        else
            uri.writeToParcel(parcel, 0);
    }

    public abstract Builder buildUpon();

    public void checkContentUriWithoutPermission(String s, int i)
    {
        if("content".equals(getScheme()) && Intent.isAccessUriMode(i) ^ true)
            StrictMode.onContentUriWithoutPermission(this, s);
    }

    public void checkFileUriExposed(String s)
    {
        if("file".equals(getScheme()) && getPath() != null && getPath().startsWith("/system/") ^ true)
            StrictMode.onFileUriExposed(this, s);
    }

    public int compareTo(Uri uri)
    {
        return toString().compareTo(uri.toString());
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Uri)obj);
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Uri))
        {
            return false;
        } else
        {
            obj = (Uri)obj;
            return toString().equals(((Uri) (obj)).toString());
        }
    }

    public abstract String getAuthority();

    public boolean getBooleanQueryParameter(String s, boolean flag)
    {
        s = getQueryParameter(s);
        if(s == null)
            return flag;
        s = s.toLowerCase(Locale.ROOT);
        if(!"false".equals(s))
            flag = "0".equals(s) ^ true;
        else
            flag = false;
        return flag;
    }

    public Uri getCanonicalUri()
    {
        if("file".equals(getScheme()))
        {
            String s1;
            try
            {
                File file = JVM INSTR new #186 <Class File>;
                file.File(getPath());
                s1 = file.getCanonicalPath();
            }
            catch(IOException ioexception)
            {
                return this;
            }
            if(Environment.isExternalStorageEmulated())
            {
                String s = Environment.getLegacyExternalStorageDirectory().toString();
                if(s1.startsWith(s))
                    return fromFile(new File(Environment.getExternalStorageDirectory().toString(), s1.substring(s.length() + 1)));
            }
            return fromFile(new File(s1));
        } else
        {
            return this;
        }
    }

    public abstract String getEncodedAuthority();

    public abstract String getEncodedFragment();

    public abstract String getEncodedPath();

    public abstract String getEncodedQuery();

    public abstract String getEncodedSchemeSpecificPart();

    public abstract String getEncodedUserInfo();

    public abstract String getFragment();

    public abstract String getHost();

    public abstract String getLastPathSegment();

    public abstract String getPath();

    public abstract List getPathSegments();

    public abstract int getPort();

    public abstract String getQuery();

    public String getQueryParameter(String s)
    {
        if(isOpaque())
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        if(s == null)
            throw new NullPointerException("key");
        String s1 = getEncodedQuery();
        if(s1 == null)
            return null;
        s = encode(s, null);
        int i = s1.length();
        int j = 0;
        do
        {
            int k;
            int l;
            int j1;
label0:
            {
                k = s1.indexOf('&', j);
                int i1;
                if(k != -1)
                    l = k;
                else
                    l = i;
                i1 = s1.indexOf('=', j);
                if(i1 <= l)
                {
                    j1 = i1;
                    if(i1 != -1)
                        break label0;
                }
                j1 = l;
            }
            if(j1 - j == s.length() && s1.regionMatches(j, s, 0, s.length()))
                if(j1 == l)
                    return "";
                else
                    return UriCodec.decode(s1.substring(j1 + 1, l), true, StandardCharsets.UTF_8, false);
            if(k != -1)
                j = k + 1;
            else
                return null;
        } while(true);
    }

    public Set getQueryParameterNames()
    {
        if(isOpaque())
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        String s = getEncodedQuery();
        if(s == null)
            return Collections.emptySet();
        LinkedHashSet linkedhashset = new LinkedHashSet();
        int i = 0;
        do
        {
            int j;
            int l;
label0:
            {
                j = s.indexOf('&', i);
                if(j == -1)
                    j = s.length();
                int k = s.indexOf('=', i);
                if(k <= j)
                {
                    l = k;
                    if(k != -1)
                        break label0;
                }
                l = j;
            }
            linkedhashset.add(decode(s.substring(i, l)));
            i = ++j;
            if(j >= s.length())
                return Collections.unmodifiableSet(linkedhashset);
        } while(true);
    }

    public List getQueryParameters(String s)
    {
        if(isOpaque())
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        if(s == null)
            throw new NullPointerException("key");
        String s1 = getEncodedQuery();
        if(s1 == null)
            return Collections.emptyList();
        String s2;
        int i;
        try
        {
            s2 = URLEncoder.encode(s, "UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new AssertionError(s);
        }
        s = new ArrayList();
        i = 0;
        do
        {
            int k;
            int i1;
label0:
            {
                int j = s1.indexOf('&', i);
                int l;
                if(j != -1)
                    k = j;
                else
                    k = s1.length();
                l = s1.indexOf('=', i);
                if(l <= k)
                {
                    i1 = l;
                    if(l != -1)
                        break label0;
                }
                i1 = k;
            }
            if(i1 - i == s2.length() && s1.regionMatches(i, s2, 0, s2.length()))
                if(i1 == k)
                    s.add("");
                else
                    s.add(decode(s1.substring(i1 + 1, k)));
            if(j != -1)
                i = j + 1;
            else
                return Collections.unmodifiableList(s);
        } while(true);
    }

    public abstract String getScheme();

    public abstract String getSchemeSpecificPart();

    public abstract String getUserInfo();

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean isAbsolute()
    {
        return isRelative() ^ true;
    }

    public abstract boolean isHierarchical();

    public boolean isOpaque()
    {
        return isHierarchical() ^ true;
    }

    public boolean isPathPrefixMatch(Uri uri)
    {
        if(!Objects.equals(getScheme(), uri.getScheme()))
            return false;
        if(!Objects.equals(getAuthority(), uri.getAuthority()))
            return false;
        List list = getPathSegments();
        uri = uri.getPathSegments();
        int i = uri.size();
        if(list.size() < i)
            return false;
        for(int j = 0; j < i; j++)
            if(!Objects.equals(list.get(j), uri.get(j)))
                return false;

        return true;
    }

    public abstract boolean isRelative();

    public Uri normalizeScheme()
    {
        String s = getScheme();
        if(s == null)
            return this;
        String s1 = s.toLowerCase(Locale.ROOT);
        if(s.equals(s1))
            return this;
        else
            return buildUpon().scheme(s1).build();
    }

    public String toSafeString()
    {
label0:
        {
            String s;
            Object obj;
            Object obj1;
label1:
            {
                s = getScheme();
                obj = getSchemeSpecificPart();
                obj1 = obj;
                if(s == null)
                    break label0;
                if(!s.equalsIgnoreCase("tel") && !s.equalsIgnoreCase("sip") && !s.equalsIgnoreCase("sms") && !s.equalsIgnoreCase("smsto") && !s.equalsIgnoreCase("mailto"))
                    break label1;
                obj1 = new StringBuilder(64);
                ((StringBuilder) (obj1)).append(s);
                ((StringBuilder) (obj1)).append(':');
                if(obj != null)
                {
                    int i = 0;
                    do
                    {
                        if(i >= ((String) (obj)).length())
                            break;
                        char c = ((String) (obj)).charAt(i);
                        if(c == '-' || c == '@' || c == '.')
                            ((StringBuilder) (obj1)).append(c);
                        else
                            ((StringBuilder) (obj1)).append('x');
                        i++;
                    } while(true);
                }
                return ((StringBuilder) (obj1)).toString();
            }
            if(!s.equalsIgnoreCase("http") && !s.equalsIgnoreCase("https"))
            {
                obj1 = obj;
                if(!s.equalsIgnoreCase("ftp"))
                    break label0;
            }
            obj = (new StringBuilder()).append("//");
            if(getHost() != null)
                obj1 = getHost();
            else
                obj1 = "";
            obj = ((StringBuilder) (obj)).append(((String) (obj1)));
            if(getPort() != -1)
                obj1 = (new StringBuilder()).append(":").append(getPort()).toString();
            else
                obj1 = "";
            obj1 = ((StringBuilder) (obj)).append(((String) (obj1))).append("/...").toString();
        }
        obj = new StringBuilder(64);
        if(s != null)
        {
            ((StringBuilder) (obj)).append(s);
            ((StringBuilder) (obj)).append(':');
        }
        if(obj1 != null)
            ((StringBuilder) (obj)).append(((String) (obj1)));
        return ((StringBuilder) (obj)).toString();
    }

    public abstract String toString();

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Uri createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI type: ").append(i).toString());

            case 0: // '\0'
                return null;

            case 1: // '\001'
                return StringUri.readFrom(parcel);

            case 2: // '\002'
                return OpaqueUri.readFrom(parcel);

            case 3: // '\003'
                return HierarchicalUri.readFrom(parcel);
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Uri[] newArray(int i)
        {
            return new Uri[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String DEFAULT_ENCODING = "UTF-8";
    public static final Uri EMPTY;
    private static final char HEX_DIGITS[] = "0123456789ABCDEF".toCharArray();
    private static final String LOG = android/net/Uri.getSimpleName();
    private static final String NOT_CACHED = new String("NOT CACHED");
    private static final int NOT_CALCULATED = -2;
    private static final int NOT_FOUND = -1;
    private static final String NOT_HIERARCHICAL = "This isn't a hierarchical URI.";
    private static final int NULL_TYPE_ID = 0;

    static 
    {
        EMPTY = new HierarchicalUri(null, Part.NULL, PathPart.EMPTY, Part.NULL, Part.NULL, null);
    }
}
