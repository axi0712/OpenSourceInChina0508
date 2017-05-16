package com.example.administrator.opensourceinchina0508.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 * 作者 Administrator
 * 日期 2017/5/12 0012
 * 内容${CONTENT}
 * /**
 * /**
 * <p>
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */


public class ZongHe_HotBean {

    private String catalog;
    private String pagesize;
    private String newsCount;
    private List<NewsBean> newslist;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewsBean {
        private String id;
        private String title;
        private String body;
        private String commentCount;
        private String author;
        private String authorid;
        private String pubDate;
        private String url;
        private NewstypeBean newstype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public NewstypeBean getNewstype() {
            return newstype;
        }

        public void setNewstype(NewstypeBean newstype) {
            this.newstype = newstype;
        }

        public static class NewstypeBean {
            private String type;
            private String authoruid2;
            private String eventurl;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAuthoruid2() {
                return authoruid2;
            }

            public void setAuthoruid2(String authoruid2) {
                this.authoruid2 = authoruid2;
            }

            public String getEventurl() {
                return eventurl;
            }

            public void setEventurl(String eventurl) {
                this.eventurl = eventurl;
            }
        }
    }
}
