var app = getApp();
Page({
  data: {
    uid: "",
    user: {}
  },
  onLoad: function (options) {
    var uid = wx.getStorageSync('uid');
    var userInfo = wx.getStorageSync("userInfo");
    this.setData({
      userInfo:userInfo
    })
    if (!uid) {
      wx.redirectTo({
        url: '/login/login/login',
      })
    }
  },
  
  onReserve: function () {
    wx.navigateTo({
      url: '/user/reserve/reserve',
    })
  },

  onBorrow: function () {
    wx.navigateTo({
      url: '/user/borroredlist/borroredlist',
    })
  },
  onFavority: function () {
    wx.navigateTo({
      url: '/user/collection/collection',
    })
  },
  onRead: function () {
    wx.navigateTo({
      url: '/user/readhistory/readhistory',
    })
  },
  onborrowcar: function () {
    wx.navigateTo({
      url: '/user/borrorcar/borrorcar',
    })
  },
  logout: function(){
    wx.setStorageSync("uid", "");
    wx.redirectTo({
      url: '/login/login/login',
    })
  }
})