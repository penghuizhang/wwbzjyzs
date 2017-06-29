// login/welcome/welcome.js
Page({
  data: {},
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  //事件处理函数
  bindViewTap: function () {
    wx.switchTab({
      url: '/library/libraryhome/libraryhome',
    })
  }
})