package de.Jan.FileURLDownloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public final class URLDownloader {

    /**
     * Download a file from an url
     * @param url
     * @param output
     * @return
     */
    public File download(String url, File output) {
        try {
            URL URL = new URL(url);
            HttpURLConnection http = (HttpURLConnection) URL.openConnection();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(output);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
            }
            bout.close();
            in.close();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Download a file from an url with a listener
     * @param url
     * @param output
     * @param listener
     * @return
     */
    public File asyncDownload(String url, File output, DownloadListener listener) {
        try {
            URL URL = new URL(url);
            HttpURLConnection http = (HttpURLConnection) URL.openConnection();
            double fileSize = (double) http.getContentLengthLong();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(output);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00D;
            int read;
            double percentDownloaded;
            while ((read = in.read(buffer, 0, 1014)) >= 0) {
                bout.write(buffer, 0, read);
                downloaded += read;
                percentDownloaded = (downloaded * 100) / fileSize;
                String percent = String.format("%.4f", percentDownloaded);
                listener.onDownloading(percent);
            }
            bout.close();
            in.close();
            listener.onCompletion(output);
            return output;
        } catch (IOException e) {
            listener.onError(e);
        }
        return null;
    }

    /**
     * Set the user agent
     * @param a
     */
    public void setUserAgent(String a) {
        System.setProperty("http.agent", a);
    }
}
