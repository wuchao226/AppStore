package com.wuchao.store.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppDetailBean {

    private String name ;
    private String intro ;
    private String icoUrl ;
    private String stars ;
    private List<LabelName> labelNameList ;
    private List<SafeLabel> safeLabelList ;
    private List<String> tabInfoList ;
    private String size ;
    private String downloadUrl ;

    public AppDetailBean(String name, String intro, String icoUrl, String stars, List<LabelName> labelNameList, List<SafeLabel> safeLabelList, List<String> tabInfoList, String size, String downloadUrl) {
        this.name = name;
        this.intro = intro;
        this.icoUrl = icoUrl;
        this.stars = stars;
        this.labelNameList = labelNameList;
        this.safeLabelList = safeLabelList;
        this.tabInfoList = tabInfoList;
        this.size = size;
        this.downloadUrl = downloadUrl;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public String getStars() {
        return stars;
    }

    public List<LabelName> getLabelNameList() {
        return labelNameList;
    }

    public List<SafeLabel> getSafeLabelList() {
        return safeLabelList;
    }

    public List<String> getTabInfoList() {
        return tabInfoList;
    }

    public String getSize() {
        return size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public static class LabelName {
        private String name ;
        private int type ;

        public LabelName(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }
    }

    public static class SafeLabel{
        private String detectorDesc ;
        private String detectorName ;
        private String name ;
        private String url ;

        public SafeLabel(String detectorDesc, String detectorName, String name, String url) {
            this.detectorDesc = detectorDesc;
            this.detectorName = detectorName;
            this.name = name;
            this.url = url;
        }

        public String getDetectorDesc() {
            return detectorDesc;
        }

        public String getDetectorName() {
            return detectorName;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
