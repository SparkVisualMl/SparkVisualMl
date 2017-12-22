//header 部分动画 动态 js
    $("#navtoggle_btn").mouseenter(function(e){
            $(".nav").stop(true,true);
            $(".nav").slideToggle(200,function(){
            });
            $(".layui-icon").toggleClass("navtoggle_btn_toggleClass");
    });

    $("#login_btn").click(function(e){
          //弹出一个层

            layer.open({
              type: 1,
              title: '登录页面',
              skin: 'layui-layer-molv', //样式类名
              maxmin: true,
              shadeClose: true, //点击遮罩关闭层
              area : ['500px' , '420px'],
              content: '<div style="padding:50px;">这是一个非常普通的页面层，传入了自定义的html</div>'
            });
    });

    $("#reg_btn").click(function(e){

          //弹出一个层

            layer.open({
              type: 1,
              title: '注册页面',
              maxmin: true,
              shadeClose: true, //点击遮罩关闭层
              area : ['500px' , '420px'],
              content: '<div style="padding:50px;">这是一个非常普通的页面层，传入了自定义的html</div>'
            });
    });



