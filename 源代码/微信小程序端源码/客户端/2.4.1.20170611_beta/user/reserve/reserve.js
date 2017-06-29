// user/reserve/reserve.js

var app = getApp();
Page({
  data: {},
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var uid = wx.getStorageSync("uid");
    var that = this;
    console.log(uid);
    wx.request({
      url: app.globalData.ServerUrl + 'getReserveByUid',
      data: {
        uid: uid
      },
      method: 'GET',
      header: {
        'content-type': 'application/json;charset=UTF-8'
      }, // 设置请求的 header
      success: function (res) {
        var booklist = res.data.reservelist;
        console.log(booklist);
        that.setData({
          booklist: booklist
        })
      }
    })
  },

})