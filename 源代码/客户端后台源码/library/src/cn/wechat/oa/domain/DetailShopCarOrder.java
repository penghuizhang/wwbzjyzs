package cn.wechat.oa.domain;

public class DetailShopCarOrder {
	ShopCarOrder shopCarOrder;
	Book book;
	public ShopCarOrder getShopCarOrder() {
		return shopCarOrder;
	}
	public void setShopCarOrder(ShopCarOrder shopCarOrder) {
		this.shopCarOrder = shopCarOrder;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
