//添加节点
function addNode(parentId, nodeId, nodeLable, position) {
  var panel = d3.select("#" + parentId);
  panel.append('div').style('width','120px').style('height','50px')
    .style('position','absolute')
    .style('top',position.y).style('left',position.x)
    .style('border','2px #9DFFCA solid').attr('align','center')
    .attr('id',nodeId).classed('node',true)
    .text(nodeLable);

  return jsPlumb.getSelector('#' + nodeId)[0];
}
//添加节点的端点
function addPorts(instance, node, ports, type) {
  //Assume horizental layout 假设水平布局
  var number_of_ports = ports.length;
  var i = 0;
  var height = $(node).height();  //Note, jquery does not include border for height
  var y_offset = 1 / ( number_of_ports + 1);
  var y = 0;

  for ( ; i < number_of_ports; i++ ) {
    var anchor = [0,0,0,0];
    var paintStyle = { radius:5, fillStyle:'#FF8891' };
    var isSource = false, isTarget = false;
    if ( type === 'output' ) {
      anchor[0] = 1;
      paintStyle.fillStyle = '#D4FFD6';
      isSource = true;
    } else {
      isTarget =true;
    }

    anchor[1] = y + y_offset;
    y = anchor[1];

    instance.addEndpoint(node, {
      uuid:node.getAttribute("id") + "-" + ports[i],
      paintStyle: paintStyle,
      anchor:anchor,
      maxConnections:-1,
      isSource:isSource,
      isTarget:isTarget
    });
  }
}
//连接节点之间的端点
function connectPorts(instance, node1, port1, node2 , port2) {
  // declare some common values:
  var color = "gray";
  var arrowCommon = { foldback:0.8, fillStyle:color, width:5 },
  // use three-arg spec to create two different arrows with the common values:
  overlays = [
    [ "Arrow", { location:0.8 }, arrowCommon ],
    [ "Arrow", { location:0.2, direction:-1 }, arrowCommon ]
  ];

  var uuid_source = node1.getAttribute("id") + "-" + port1;
  var uuid_target = node2.getAttribute("id") + "-" + port2;

  instance.connect({uuids:[uuid_source, uuid_target]});
}
//节点树
function getTreeData() {
  var tree = [
    {
        text: "examples",
        nodes: [
          {
            text: "WordCount",
          }
        ]
      },
    {
      text: "数据源",
      nodes: [
        {
          text: "HDFS",
        },
        {
          text: "MySQL"
        },
        {
          text:"Kafka"
        }
      ]
    },
    {
          text: "数据预处理",
          nodes: [
            {
              text: "缺失值填充",
            },
            {
              text: "归一化"
            }
          ]
      },
     {
               text: "特征工程",
               nodes: [
                 {
                   text: "全表统计",
                 },
                 {
                   text: "PCA"
                 }
               ]
             },
          {
                    text: "模型评估",
                    nodes: [
                      {
                        text: "AUC曲线",
                      },
                      {
                        text: "ROC曲线"
                      }
                    ]
                  }
  ]; 

  return tree;
}

jsPlumb.ready(function() {

    console.log("jsPlumb is ready to use MAIN start");

    //初始化  JsPlumb

    var color = "#E8C870";//连线的颜色
    var instance = jsPlumb.getInstance({
      // notice the 'curviness' argument to this Bezier curve.  the curves on this page are far smoother
      // than the curves on the first demo, which use the default curviness value.      
      Connector : [ "Bezier", { curviness:50 } ],
      DragOptions : { cursor: "pointer", zIndex:2000 },
      PaintStyle : { strokeStyle:color, lineWidth:2 },
      EndpointStyle : { radius:5, fillStyle:color },
      HoverPaintStyle : {strokeStyle:"#7073EB" },
      EndpointHoverStyle : {fillStyle:"#7073EB" },
      Container:"flow-panel"//拖拽空间容器
    });
    connectCallBack({"instance":instance});
    connectCancelCallBack({"instance":instance});
    connectClick({"instance":instance});
    //Initialize Control Tree View
    $('#control-panel').treeview({data: getTreeData()});

    
    //Handle drag and drop
    $('.list-group-item').attr('draggable','true').on('dragstart', function(ev){
      //ev.dataTransfer.setData("text", ev.target.id);
      ev.originalEvent.dataTransfer.setData('text',ev.target.textContent);
      console.log('drag start');
    });

    $('#flow-panel').on('drop', function(ev){
      
      //avoid event conlict for jsPlumb
      if (ev.target.className.indexOf('_jsPlumb') >= 0 ) {
        return;
      }

      ev.preventDefault();
      var mx = '' + ev.originalEvent.offsetX + 'px';
      var my = '' + ev.originalEvent.offsetY + 'px';

      console.log('on drop : ' + ev.originalEvent.dataTransfer.getData('text'));
      var nodeName = ev.originalEvent.dataTransfer.getData('text');

      console.log(nodeName+"---------------------")
      var uid = new Date().getTime();

      var node = addNode('flow-panel','node' + uid, nodeName, {x:mx,y:my});


      //给添加节点赋右键点击事件，先屏蔽右键，再给右键赋点击事件
      $("#node" + uid).bind("contextmenu", function(){
          return false;
      })
      $("#node" + uid).mousedown(function(e) {
          if(e.which==1){
            console.log("点击了左键");
          }

          //右键为3
          if (3 == e.which) {
              console.log("点击了右键");
              rightBtnConfig({"nodeName":nodeName,"event":e,"nodeId":"#node" + uid});
          }
      })
      addPorts(instance, node, ['out','out1','out2'],'output');
      addPorts(instance, node, ['in1','in2'],'input');
      instance.draggable($(node));
    }).on('dragover', function(ev){
      ev.preventDefault();
      console.log('on drag over');
    });

    //右键配置事件
    function rightBtnConfig(args){
        var nodeName = args.nodeName;
        var event = args.event;
        var nodeId = args.nodeId;
        console.log("进入rightBtnConfig nodeName 为"+nodeName);
        if("WordCount"==nodeName){
            console.log("进入WordCount 的rightBtnConfig");
            console.log("x"+event.clientX);//相对于DAG拖拽面板
            console.log("y"+event.clientY);//相对于DAG拖拽面板
            console.log(event);
            //之前要清除掉以前追加的
            $(".config_box").remove();
            $(nodeId).append(
                '<div class="config_box">'+
                      '<span class="delete">X</span>'+
                      '<ul>'+
                        '<li>'+
                            '<span class="config_title">本地文件地址(绝对路径)</span>'+
                            '<input type="text" id="fileAddress" placeholder="请输入wordcount的文件地址"/>'+
                        '</li>'+
                        '<li>'+
                          '<span class="config_title">本地目录(绝对路径)</span>'+
                          '<input type="text" id="dirAddress" placeholder="请输入wordcount的文件地址"/>'+
                        '</li>'+
                      '</ul>'+
                      '<div id="wc_submit_btn">提交</div>'+
                  '</div>'
            );



            deleteConfigBtn({"nodeId":nodeId});
            //提交wordcount读取的本地文件路径
            submitWcBtn({"nodeId":nodeId});
            $(nodeId).css("position","absolute");
            $(".config_box").css({"left":130,"top":0});


        }
    }

    function deleteConfigBtn(args){
        $(args.nodeId).find(".delete").click(function(e){
            $(args.nodeId).find(".config_box").fadeOut(400);
        });
    }

    function submitWcBtn(args){
        var nodeId = args.nodeId;
        var divdom = $(nodeId);

        $("#wc_submit_btn").click(function(){
                var fileAddress = divdom.find("#fileAddress").val();
                var dirAddress = divdom.find("#dirAddress").val();
                console.log("WC 文件地址"+fileAddress);
                console.log("WC 目录地址"+dirAddress);
                console.log("提交WC 请求");
                var fileAddress = JSON.stringify(fileAddress);

                //ajax 提交请求
                submitByAjax({"fileAddress":fileAddress,"dirAddress":dirAddress});
        });

    }

    function submitByAjax(args){
        //判断取文件还是目录
        var fileAddress=args.fileAddress.toString();
        fileSplits = fileAddress.split("\\")
        console.log(fileSplits)
        console.log(fileAddress);
        var len = fileSplits.length
        var pathParams = ""
        for(var i=0;i<len;i++){
            var str = fileSplits[i]
            var str = str.replace(":","")
            pathParams=pathParams+str
            if(i<len-1){
                pathParams=pathParams+","
            }
        }
        url = "http://127.0.0.1:8081/algorithm/wordCountInFile?fileAddress="+pathParams
        $.ajax({
            type:"GET",
            url:url,
            processData:false,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType:"json",
            success:function(data) {
                console.log(data);
                 jQuery.each(data,function(key,values){
                    console.log(values.name+"-------"+values.value);
                 });
            },
            error:function(){

            }
        });
    }
  
    instance.doWhileSuspended(function() {

      // declare some common values:
      var arrowCommon = { foldback:0.8, fillStyle:color, width:5 },
      // use three-arg spec to create two different arrows with the common values:
      overlays = [
        [ "Arrow", { location:0.8 }, arrowCommon ],
        [ "Arrow", { location:0.2, direction:-1 }, arrowCommon ]
      ];

      var node1 = addNode('flow-panel','HDFS', 'HDFS', {x:'10px',y:'10px'});
      var node2 = addNode('flow-panel','MySQL', 'MySQL', {x:'80px',y:'80px'});

      addPorts(instance, node1, ['out1','out2','out3'],'output');
      addPorts(instance, node2, ['in','in1','in2'],'input');

      connectPorts(instance, node1, 'out2', node2, 'in2');
      instance.draggable($('.node'));
    });

    jsPlumb.fire("jsFlowLoaded", instance);
    function connectCallBack(args){
          var instance = args.instance;
          //自己连接自己事件
          console.log("自己连接自己事件");
          conn = instance.getAllConnections();
          console.log(conn);
          instance.bind("connection", function (connInfo, originalEvent) {

              if (connInfo.connection.sourceId == connInfo.connection.targetId) {
                  jsPlumb.detach(connInfo);
                  console.log("不能连接自己！");
              }else{
                 console.log("连接"+connInfo.connection.sourceId+"==="+connInfo.connection.targetId);
              }
           });

     }

    function connectCancelCallBack(args){
             //连接取消事件
             var instance = args.instance;

             instance.bind("connectionDetached", function (conn, originalEvent) {
                         if (conn.sourceId == conn.targetId) {
                             //自己连接自己时会自动取消连接
                         }else{
                                 console.log("删除连接从" + conn.sourceId + "到" + conn.targetId + "！");
                         }
              });
     }

     function connectClick(args){
        var instance = args.instance;

        instance.bind("click", function (conn, originalEvent) {
            if (confirm("Delete connection from " + conn.sourceId + " to " + conn.targetId + "?"))
                instance.detach(conn);
        });

     }
     //移除所有的连接
     function removeConnection(args){
        args.instance.removeAllEndpoints("window");
     }

    
});