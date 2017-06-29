// user/borrorcar/borrorcar.js
// var borrowcarData=require("../../data/homedata.js")
var app = getApp();
Page({
  data:{},
  onLoad:function(options){
    var uid = wx.getStorageSync("uid");
    var that = this;
    /**后台获取数据 */
    wx.request({
      url: app.globalData.ServerUrl + 'getShopCarOrderByUid',
      data: {
        uid: uid
      },
      method: 'GET',
      header: {
        'content-type': 'application/json;charset=UTF-8'
      }, // 设置请求的 header
      success: function (res) {
        var booklist = res.data.list;
        console.log(booklist)
        that.setData({
          booklist:booklist
        })
      },
    })
  },

  /**复选框改变触发事件 */
  checkboxChange:function(event){
    var ids = event.detail.value;
    console.log(ids);
    this.setData({
      ids:ids
    })
  },
  
  /**生成二维码 */
  borrow:function(event){
    var ids = this.data.ids;
    if(ids.length>2){
      wx.showToast({
        title: '一次最多借阅两本！',
        icon: 'mask',
        duration: 2000
      })
      return;
    }
    var ids = ids.join(",");
    //组装要借阅的图书信息
    var data = 'borrow,'+ids;
    wx.navigateTo({
      url: '/user/qrcode/qrcode?data='+data,
    })
  },
  /**删除选中的书籍 */
  delete:function(event){
    var Ids = this.data.ids;
    var orderIds = Ids.join(",");
    wx.request({
      url: app.globalData.ServerUrl + 'deleteShopCarOrderByOrderId',
      data: {
        orderIds: orderIds
      },
      method: 'GET',
      header: {
        'content-type': 'application/json;charset=UTF-8'
      }, // 设置请求的 header
      success: function (res) {
       wx.showToast({
         title: '删除成功！',
       })
       wx.redirectTo({
         url: '/user/borrorcar/borrorcar',
       })
      },
    })
  }
})