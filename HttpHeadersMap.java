import java.util.HashMap;

public class HttpHeadersMap 
{ /** Creating an outline for initializing HTTP headers */
    public void HeaderMap()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Host", "httpbin.org");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        map.put("Accept", "text/html");
        map.put("Accept-Language", "en-us, en; q-0.5");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Charset", "ISO-8859-1, UTF-8, q=0.7, *; q=0.7");
        map.put("Keep-Alive", "300");
        map.put("Connection", "keep-alive");
    }
}
