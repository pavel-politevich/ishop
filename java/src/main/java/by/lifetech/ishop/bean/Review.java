package by.lifetech.ishop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Review implements Serializable {
    public static final long serialVersionUID = 9094335382261950240L;

    private Date reviewDate;
    private String userName;
    private byte rate;
    private String comment;

    public Review(Date reviewDate, String userName, byte rate, String comment) {
        this.reviewDate = reviewDate;
        this.userName = userName;
        this.rate = rate;
        this.comment = comment;
    }

    public Review() {}

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getUserName() {
        return userName;
    }

    public byte getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rate == review.rate &&
                Objects.equals(reviewDate, review.reviewDate) &&
                Objects.equals(userName, review.userName) &&
                Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewDate, userName, rate, comment);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewDate=" + reviewDate +
                ", userName='" + userName + '\'' +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
