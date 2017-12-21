
    $("#navtoggle_btn").mouseenter(function(e){
            $(".nav").stop(true,true);
            $(".nav").slideToggle(200,function(){
            });
            $(".layui-icon").toggleClass("navtoggle_btn_toggleClass");
    });



