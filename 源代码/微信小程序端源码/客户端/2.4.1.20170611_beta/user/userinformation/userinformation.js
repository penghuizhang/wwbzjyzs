// user/userinformation/userinformation.js
var app = getApp();
var utils = require('../../utils/Utils.js')
Page({
  data: {},
  onLoad: function (options) {
    var that = this;
    if (options == null) {
      var timestamp = wx.getStorageSync('uid')
    } else {
      var timestamp = options.timestamp;
      console.log(timestamp);
    }
    wx.request({
      url: app.globalData.ServerUrl + 'getUserByUid',
      data: {
        uid: timestamp
      },
      method: 'GET',
      header: {
        'content-type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var user = {};
        user = res.data.user
        that.setData({
          user: user
        })
      },
    })
  },

})