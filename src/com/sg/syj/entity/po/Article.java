package com.sg.syj.entity.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * 说明:文章实体
 *
 */
@Entity
public class Article {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 40)
	private String id;

	// 标题
	private String title;
	// 最后一次修改的时间
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	//作者
    private String author;
    //出处
    private String source;
	// 发表时间
    @Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date issuedDate;
	//封面图片
    private String coverImgUrl;
	// 内容
    @Column(columnDefinition="TEXT")
	private String content;
    //摘要
    @Column(columnDefinition="TEXT")
    private String summary;
    
	// 栏目
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name = "menubar_Id")
	private Menubar menubar;
 
	//二维码名片地址
	private String qrCodeUrl;

    //点击率
    private Integer clickRate=0;
    
    //文章的唯一路径标识
    private Integer pathNumber;
 
    
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

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Menubar getMenubar() {
		return menubar;
	}

	public void setMenubar(Menubar menubar) {
		this.menubar = menubar;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public Integer getPathNumber() {
		return pathNumber;
	}

	public void setPathNumber(Integer pathNumber) {
		this.pathNumber = pathNumber;
	}

}
