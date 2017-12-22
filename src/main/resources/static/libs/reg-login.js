//Demo
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(formSubmit)', function(data){
    alert(JSON.stringify(data.field));
    alert(444);
//    layer.msg(JSON.stringify(data.field));

  });
});