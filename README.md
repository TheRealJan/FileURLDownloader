# URLFileDownloader

A simple module to download a file from an url

Download: 

Examples:

```java
URLDownloader downloader = new URLDownloader();

//Set the user agent
downloader.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:79.0) Gecko/20100101 Firefox/79.0");

//Download file using normal downloader.
downloader.download("", new File("C:\\Downloads\\README.md"));

//Download file using async downloader.
downloader.asyncDownload("https://speed.hetzner.de/100MB.bin", new File("C:\\Downloads\\README.md"), new DownloadListener() {
        @Override
        public void onDownloading(String percentComplete) {
            System.out.println("Downloading file... " + percentComplete + "% complete");
        }

        @Override
        public void onCompletion(File file) {
            System.out.println("The file was successfully downloaded!");
        }

        @Override
        public void onError(IOException e) {
        e.printStackTrace();
        }
    });
```
