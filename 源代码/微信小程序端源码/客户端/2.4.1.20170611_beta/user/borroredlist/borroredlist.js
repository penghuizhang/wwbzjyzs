// user/borroredlist/borroredlist.js
var app = getApp();
Page({
  data:{},
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var uid = wx.getStorageSync("uid");
    var that = this;
    console.log(uid);
    
    wx.request({
      url: app.globalData.ServerUrl + 'getBorrowBooksByUid',
      data: {
        uid: uid
      },
      method: 'GET',
      header: {
        'content-type': 'application/json;charset=UTF-8'
      }, // 设置请求的 header
      success: function (res) {
        console.log(res)
        var booklist = res.data.list;
        that.setData({
          booklist: booklist
        })
      },
    })
  },
  /**复选框改变触发事件 */
  checkboxChange: function (event) {
    var ids = event.detail.value;
    console.log(ids);
    this.setData({
      ids: ids
    })
  },

  /**生成二维码 */
  createQRcode: function (event) {
    var ids = this.data.ids;
    if (ids.length > 2) {
      wx.showModal({
        title: '提示',
        content: '最多一次借阅两本！',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
      return;
    }
    var ids = ids.join(",");
    console.log(ids);
    var data = "return,"+ ids;
    wx.navigateTo({
      url: '/user/qrcode/qrcode?data=' + data,
    })
  },
})