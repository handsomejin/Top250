package me.aaron.top250.model.bean;

import java.util.List;

/**
 * Created by aaron on 17-9-19.
 * 这个Bean可以理解为是单独ItemBean的合集，主要还是为了解决解析数据过于麻烦的问题
 */

public class ItemsBean {

    /**
     * count : 20
     * start : 25
     * total : 250
     * subjects : []
     * title : 豆瓣电影Top250
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<ItemBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<ItemBean> itemBeen) {
        this.subjects = subjects;
    }

    public class ItemBean{

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * rating : {"max":10,"average":9.4,"stars":"50","min":0}
         * genres : ["剧情"]
         * title : 十二怒汉
         * casts : [{"alt":"https://movie.douban.com/celebrity/1048150/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1537.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1537.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1537.jpg"},"name":"亨利·方达","id":"1048150"},{"alt":"https://movie.douban.com/celebrity/1041188/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1427201867.36.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1427201867.36.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1427201867.36.jpg"},"name":"马丁·鲍尔萨姆","id":"1041188"},{"alt":"https://movie.douban.com/celebrity/1007076/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/40857.jpg","large":"http://img3.doubanio.com/img/celebrity/large/40857.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/40857.jpg"},"name":"约翰·菲德勒","id":"1007076"}]
         * collect_count : 209757
         * original_title : 12 Angry Men
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1010627/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/1309.jpg","large":"http://img3.doubanio.com/img/celebrity/large/1309.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/1309.jpg"},"name":"西德尼·吕美特","id":"1010627"}]
         * year : 1957
         * images : {"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2173577632.webp","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2173577632.webp","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2173577632.webp"}
         * alt : https://movie.douban.com/subject/1293182/
         * id : 1293182
         */

        private RatingBean rating;
        private String title;

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        private String year;
        private ImagesBean images;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        private List<String> genres;

        public class RatingBean {
            /**
             * max : 10
             * average : 9.4
             * stars : 50
             * min : 0
             */

            private double average;

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }
        }

        public class ImagesBean {
            /**
             * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2173577632.webp
             * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2173577632.webp
             * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2173577632.webp
             */

            private String medium;

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
