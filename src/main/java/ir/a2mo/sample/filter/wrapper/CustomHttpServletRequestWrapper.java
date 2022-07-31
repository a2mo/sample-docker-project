package ir.a2mo.sample.filter.wrapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private ByteArrayOutputStream cachedBytes;

        public CustomHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (cachedBytes == null)
                cacheInputStream();
            return new CachedServletInputStream();
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        private void cacheInputStream() throws IOException {
            cachedBytes = new ByteArrayOutputStream();
            IOUtils.copy(super.getInputStream(), cachedBytes);
        }

        public class CachedServletInputStream extends ServletInputStream {
            private ByteArrayInputStream input;

            public CachedServletInputStream() {
                input = new ByteArrayInputStream(cachedBytes.toByteArray());
            }

            @Override
            public int read() throws IOException {
                return input.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        }
    }