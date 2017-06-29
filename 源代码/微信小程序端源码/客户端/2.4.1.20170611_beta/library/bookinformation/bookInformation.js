// library/bookdetailinformation/detailinformation.js

var app = getApp();
Page({
  data: {},
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var id = options.bookid;
    var that = this;
    wx.request({
      url: app.globalData.ServerUrl + "getBookById",
      data: {
        id:id,
      },
      method: 'GET', 
      header: {
         'content-type': 'application/json'
      }, // 设置请求的 header
      success: function(res){
        // success
        var book = res.data.book;
        console.log(book)
        that.setData({
          book:book,
        })
      },
      fail: function(res) {
        // fail
      },
      complete: function(res) {
        // complete
      }
    })
  },
/**
 * 加入购物车
 */
  addshopcar: function (event) {
    var uid = wx.getStorageSync('uid');
    if(!uid){
      wx.redirectTo({
        url: '/login/login/login',
      })
      return;
    }
    var id = event.currentTarget.dataset.id;
    wx.request({
      url: app.globalData.ServerUrl + "addShopCarOrder",
      data: {
        uid:uid,
        id:id,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      }, // 设置请求的 header
      success: function (res) {
        // success
        wx.showToast({
          title: '加入成功',
          icon: 'success',
          duration: 2000
        })
      },
      fail: function (res) {
        // fail
        wx.showToast({
          title: '加入失败',
          icon: 'warn',
          duration: 2000
        })
      },
      complete: function (res) {
        // complete
      }
    })
   
  },

  /**
   * 预定图书
   */
  reserve: function (event) {
    var uid = wx.getStorageSync('uid');
    if (!uid) {
      wx.redirectTo({
        url: '/login/login/login',
      })
      return;
    }
    var id = event.currentTarget.dataset.id;
    wx.request({
      url: app.globalData.ServerUrl + "addReserve",
      data: {
        uid: uid,
        id: id,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      }, // 设置请求的 header
      success: function (res) {
        // success
        wx.showToast({
          title: '成功预定',
          icon: 'success',
          duration: 2000
        })
      },
    })
  }
})