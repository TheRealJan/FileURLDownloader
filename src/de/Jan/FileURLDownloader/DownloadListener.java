package de.Jan.FileURLDownloader;

import java.io.File;
import java.io.IOException;

public abstract class DownloadListener {
    public abstract void onDownloading(String percentComplete);

    public abstract void onCompletion(File file);

    public abstract void onError(IOException e);
}
