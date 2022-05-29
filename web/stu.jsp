<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <!-- 引入 layui.css -->
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="static/cascader/cascader.css"/>
    <!-- 引入 layui.js -->
    <script src="static/layui/layui.js"></script>
    <script src="static/cascader/cascader.js"></script>
    <script src="static/js/index.js"></script>
</head>
<body>


<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md10 layui-col-md-offset1">
            <script type="text/html" id="barDemo">
                <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
                <a class="layui-btn layui-btn-xs" lay-event="more">更多 <i class="layui-icon layui-icon-down"></i></a>
            </script>
            <table id="stuTable" lay-filter="test"></table>
        </div>
    </div>

    <div id="stuinfo_from_wrapper" action="" style="display: none;  margin-left: 15px;">
        <%--method="post" action="http://localhost:8080/ysj/stu/add"--%>
        <form class="layui-form layui-form-pane" id="stuinfo_form" lay-filter="stuinfo_form">
            <div class="layui-form-item">
                <input type="hidden" name="id" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" autocomplete="off" lay-verify="required" placeholder="请输入姓名"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="gender" value="男" title="男" checked="">
                    <input type="radio" name="gender" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">家庭住址</label>
                <div class="layui-input-inline">
                    <input id="address" type="text" name="address" lay-verify="required" placeholder="请输入"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">生日</label>
                    <div class="layui-input-block">
                        <input type="text" name="birthday" id="birthday" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <%--<div class="layui-form-item">--%>
            <%--    <button type="button" class="layui-btn" lay-submit="" lay-filter="add_form">提交</button>--%>
            <%--</div>--%>
        </form>
    </div>

</div>


<script>

    var options = [];

    layui.use(['table', 'form', 'jquery', 'layCascader', 'laydate', 'dropdown'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;
        var layCascader = layui.layCascader;
        var laydate = layui.laydate;
        var dropdown = layui.dropdown //下拉菜单

        //第一个实例
        table.render({
            elem: '#stuTable',
            height: 500,
            url: 'http://localhost:8080/ysj/stuList',
            toolbar: 'default',
            // page: true,
            cols: [[
                {field: 'id', title: 'ID', sort: true, fixed: 'left'},
                {field: 'name', title: '用户名'},
                {field: 'gender', title: '性别', sort: true},
                {field: 'address', title: '出生地'},
                {field: 'birthday', title: '生日'},
                {fixed: 'right', width: 150, align: 'center', toolbar: '#barDemo'}
            ]]
        });

        laydate.render({
            elem: '#birthday',
        });


        $.get({
            url: "http://localhost:8080/ysj/cityList",
            success: function (res) {
                // const r = JSON.parse(res);
                const r = res;

                // pid, list, pidFiled, labelFiled, valueFiled
                options = handleTree(r.data, "code", "pcode")


                layCascader({
                    elem: '#address',
                    props: {
                        value: 'code',
                        label: 'name'
                    },
                    options: options
                });
            }
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'add':
                    $("#stuinfo_form")[0].reset();
                    layui.form.render();
                    // $("stuinfo_from").css({"display": "block"})
                    layer.open({
                        type: 1 //此处以iframe举例
                        , title: '添加信息'
                        // , area: ['600px', '400px']
                        , content: $("#stuinfo_from_wrapper")
                        , btn: ['确定']
                        , yes: function (index, layero) {
                            const formData = layui.form.val("stuinfo_form");
                            console.log(formData)
                            $.post({
                                url: 'http://localhost:8080/ysj/stu/add',
                                data: formData,
                                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                                success: (res) => {
                                    layer.msg(res.msg);
                                    layer.close(index);
                                }
                            });

                        }
                    })
                    break;
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        form.val("stuinfo_form", data)
                        layer.open({
                            type: 1 //此处以iframe举例
                            , title: '添加信息'
                            // , area: ['600px', '400px']
                            , content: $("#stuinfo_from_wrapper")
                            , btn: ['确定']
                            , yes: function (index, layero) {
                                const formData = layui.form.val("stuinfo_form");
                                console.log(formData)
                                $.post({
                                    url: 'http://localhost:8080/ysj/stu/add',
                                    data: formData,
                                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                                    success: (res) => {
                                        layer.msg(res.msg);
                                        layer.close(index);
                                    }
                                });

                            }
                        })
                    }
                    break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'more') {
                //下拉菜单
                dropdown.render({
                    elem: this //触发事件的 DOM 对象
                    , show: true //外部事件触发即显示
                    , data: [{
                        title: '编辑'
                        , id: 'edit'
                    }, {
                        title: '删除'
                        , id: 'del'
                    }]
                    , click: function (menudata) {
                        if (menudata.id === 'del') {
                            layer.confirm('确认删除吗？', function (index) {
                                obj.del(); //删除对应行（tr）的DOM结构
                                layer.close(index);
                                //向服务端发送删除指令
                                $.ajax({
                                    type: "POST",
                                    url: "http://localhost:8080/ysj/stu/delete",
                                    data: {stuId: index},
                                    dataType: 'json',
                                    success: function (res) {
                                    }
                                });
                            });
                        } else if (menudata.id === 'edit') {

                            data.address = parseInt(data.address)

                            console.log(data)
                            form.val("stuinfo_form", data)
                            layer.open({
                                type: 1 //此处以iframe举例
                                , title: '编辑信息'
                                // , area: ['600px', '400px']
                                , content: $("#stuinfo_from_wrapper")
                                , btn: ['确定']
                                , yes: function (index, layero) {
                                    const formData = layui.form.val("stuinfo_form");
                                    console.log(formData)
                                    $.post({
                                        url: 'http://localhost:8080/ysj/stu/update',
                                        data: formData,
                                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                                        success: (res) => {
                                            layer.msg(res.msg);
                                            layer.close(index);
                                        }
                                    });

                                }
                            })
                        }
                    }
                    , align: 'right' //右对齐弹出（v2.6.8 新增）
                    , style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' //设置额外样式
                })
            }
        })

        // form.on('submit(add_form)', function (data) {
        //     var maintenancePlace = JSON.stringify(data.field);
        //     $.ajax({
        //         url: 'http://localhost:8080/ysj/stu/add',
        //         type: 'post',
        //         contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        //         data: data.field,
        //         success: function (res) {
        //             layer.msg(res.msg)
        //             $("stuinfo_from").css({"display": "none"})
        //         }
        //     })
        //     return false;
        // })

    });
</script>
</body>
</html>
