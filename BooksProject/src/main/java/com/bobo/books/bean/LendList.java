package com.bobo.books.bean;

import java.util.Date;

/**
 * 借书记录的Bean
 */
public class LendList {

    private Integer id; // 借阅记录ID
    private Integer bookId; // 书籍ID
    private String cardId; // 借阅卡ID
    private Date lendDate; // 借阅时间
    private Date backDate; // 归还时间

    @Override
    public String toString() {
        return "LendList{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", cardId='" + cardId + '\'' +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }
}
