package com.shop.entity;



import javax.persistence.*;
import java.util.Date;


@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CommentId;
    private String Content;
    private Date CommentDate;
    private Long UserId;
    private Long ProductId;


    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)

    private CommentDetail commentDetail;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_productId")
    private Products product;


    public Comment() {
    }

    public Comment(Long commentId, String content, Date commentDate, Long userId, Long productId) {
        CommentId = commentId;
        Content = content;
        CommentDate = commentDate;
        UserId = userId;
        ProductId = productId;
    }

    public Long getCommentId() {
        return CommentId;
    }

    public void setCommentId(Long commentId) {
        CommentId = commentId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(Date commentDate) {
        CommentDate = commentDate;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }
}
