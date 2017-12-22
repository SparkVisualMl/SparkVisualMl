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
              content: '<form class="layui-form" action="">'+
                              '<div class="layui-form-item">'+
                                '<br/><br/>'+
                                '<label class="layui-form-label">用户名</label>'+
                                '<div class="layui-input-block">'+
                                  '<input type="text" name="title" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" style="width:80%;" />'+
                                '</div>'+
                              '</div>'+
                              '<div class="layui-form-item">'+
                                '<label class="layui-form-label">密码框</label>'+
                                '<div class="layui-input-block">'+
                                  '<input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="on" class="layui-input" style="width:80%;"/>'+
                                '</div>'+

                              '</div>'+

                              '<div class="layui-form-item">'+
                                '<div class="layui-input-block">'+
                                  '<button class="layui-btn" lay-filter="formSubmit">登录</button>'+
                                  '<button type="reset" class="layui-btn layui-btn-primary">重置</button>'+
                                '</div>'+
                              '</div>'+
                            '</form>'
            });
    });

    $("#reg_btn").click(function(e){

          //弹出一个层

            layer.open({
              type: 1,
              title: '注册页面',
              skin: 'layui-layer-lan',
              maxmin: true,
              shadeClose: true, //点击遮罩关闭层
              area : ['500px' , '420px'],
              content: '<form class="layui-form" action="">'+
                                                     '<div class="layui-form-item">'+
                                                       '<br/><br/>'+
                                                       '<label class="layui-form-label">用户名</label>'+
                                                       '<div class="layui-input-block">'+
                                                         '<input type="text" name="title" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" style="width:80%;" />'+
                                                       '</div>'+
                                                     '</div>'+
                                                     '<div class="layui-form-item">'+
                                                       '<label class="layui-form-label">密码框</label>'+
                                                       '<div class="layui-input-block">'+
                                                         '<input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="on" class="layui-input" style="width:80%;"/>'+
                                                       '</div>'+
                                                     '<div class="layui-form-item">'+
                                                        '<label class="layui-form-label">验证码</label>'+
                                                        '<div class="layui-input-block">'+
                                                          '<input type="password" name="password" lay-verify="required" placeholder="请输入验证码" autocomplete="on" class="layui-input" style="width:80%;"/>'+
                                                        '</div>'+

                                                     '</div>'+

                                                     '<div class="layui-form-item">'+
                                                       '<div class="layui-input-block">'+
                                                         '<button class="layui-btn" lay-filter="formSubmit">登录</button>'+
                                                         '<button type="reset" class="layui-btn layui-btn-primary">重置</button>'+
                                                       '</div>'+
                                                     '</div>'+
                                                   '</form>'
            });
    });



