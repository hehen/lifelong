package com.cit.lifelong.dto;


import com.cit.lifelong.model.User;

public class QuestionDTO {

  private long id;
  private String title;
  private String content;
  private long read;
  private long comment;
  private long gmtCreate;
  private long gmtModified;
  private String tag;
  private String type;
  private int creator;

  private User user;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getRead() {
    return read;
  }

  public void setRead(long read) {
    this.read = read;
  }


  public long getComment() {
    return comment;
  }

  public void setComment(long comment) {
    this.comment = comment;
  }


  public long getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(long gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public long getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(long gmtModified) {
    this.gmtModified = gmtModified;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getCreator() {
    return creator;
  }

  public void setCreator(int creator) {
    this.creator = creator;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
