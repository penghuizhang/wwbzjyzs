// library/searchshow/searchshow.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var key = options.key;
    var that = this;
      
      wx.request({
        url: app.globalData.ServerUrl + 'getBooksBySearchKey',
        data: {
          key: key,
        },
        method: 'GET',
        // header: {}, // 设置请求的 header
        success: function (res) {
          // success
          var booklist = res.data.booklist;
          if(booklist.length === 0){
            that.setData({
              books: "",
              isFull: false,
              isEmpty: true
            })
          }else{
            that.setData({
              booklist: booklist,
              isFull: true,
              isEmpty: false
            })
          }
        }
    })
  },
  onTap: function (event) {
    var bookid = event.currentTarget.dataset.id;
    console.log(bookid);
    wx.navigateTo({
      url: '/library/bookinformation/bookInformation?bookid=' + bookid,
    })
  }
})