package com.zimplyshop.app.baseobjects;

import java.util.List;

/**
 * Created by praveen goel on 10/8/2015.
 */
public class ZProductDescriptionProductRatingObject {

    RatingHeader headerData;
    List<ProductRating> ratings;

    public class RatingHeader {
        String name;
        int boughtBy;
        int savedBy;
        float rating;
        int numberOfRatings;

        public String getName() {
            return name;
        }

        public int getBoughtBy() {
            return boughtBy;
        }

        public int getSavedBy() {
            return savedBy;
        }

        public float getRating() {
            return rating;
        }

        public int getNumberOfRatings() {
            return numberOfRatings;
        }
    }

    public class ProductRating {
        String imageUrl;
        String name;
        String time;
        String comment;
        int rating;

        public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public String getComment() {
            return comment;
        }

        public int getRating() {
            return rating;
        }
    }
}
