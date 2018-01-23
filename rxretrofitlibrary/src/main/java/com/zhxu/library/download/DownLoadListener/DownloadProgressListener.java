package com.zhxu.library.download.DownLoadListener;


/**
 * 成功回调处理
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
