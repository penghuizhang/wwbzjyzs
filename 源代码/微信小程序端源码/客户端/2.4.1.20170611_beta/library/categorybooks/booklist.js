// library/categorybooks/booklist.js
var app = getApp();
Page({
  data: {
    begin: 0,
    booklist: []
  },

  /**
   * 页面加载
   */
  onLoad: function (options) {
    var category = options.category;
    this.setData({
      category: category
    })
    var that = this;
    var begin = this.data.begin;
    wx.showNavigationBarLoading();
    wx.request({
      url: app.globalData.ServerUrl + "getBooksByCategory",
      data: {
        category: category,
        begin: begin
      },
      method: 'GET',
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        that.setData({
          booklist: res.data.booklist
        })
      },
    })
   wx.hideNavigationBarLoading();
  },

  /**
   * 下拉加载
   */
  onPullDownRefresh: function (event) {
    console.log("下拉执行")
    var category = this.data.category;
    wx.request({
      url: app.globalData.ServerUrl + "getBooksByCategory",
      data: {
        category: category,
        begin: 0
      },
      method: 'GET',
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        that.setData({
          booklist: res.data.booklist
        })
      },
    })
    wx.showNavigationBarLoading();
    setTimeout(function () { wx.hideNavigationBarLoading(); }, 1000);
  },
/**
 * 页面到达底部
 */
  onScrollLower: function (event) {
    wx.showNavigationBarLoading();
    setTimeout(function () { wx.hideNavigationBarLoading(); }, 500);
    var that = this;
    var booklist = this.data.booklist;
    var begin = this.data.begin + 20;
    this.setData({
      begin: begin
    })
    var category = this.data.category;
    wx.request({
      url: app.globalData.ServerUrl + "getBooksByCategory",
      data: {
        category: category,
        begin: begin
      },
      method: 'GET',
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        booklist = booklist.concat(res.data.booklist);
        console.log(booklist)
        that.setData({
          booklist: booklist
        })
      },
    })
  },
  /**
   * 书籍点击
   */
  onTap: function (event) {
    var bookid = event.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/library/bookinformation/bookInformation?bookid=' + bookid,
    })
  }
})