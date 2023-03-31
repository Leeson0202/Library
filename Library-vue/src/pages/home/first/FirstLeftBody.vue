<!-- FirstLeftBody -->
<template>
    <div class="FirstLeftBody">
        <div class="card table t1" id="t1"></div>
        <div class="card table t2" id="t2"></div>
        <div class="card table t3" id="t3"></div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;
import * as echarts from "echarts";

export default {
    name: "FirstLeftBody",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            opt1: {
                title: {
                    text: "图书馆一周人数统计",
                    textStyle: {
                        //标题内容的样式
                        color: "#666", //京东红
                        fontStyle: "normal", //lic主标题文字字体风格，默认normal，有italic(斜体),oblique(斜体)
                        fontWeight: "500", //可选normal(正常)，bold(加粗)，bolder(加粗)，lighter(变细)，100|200|300|400|500...
                        fontFamily: "san-serif", //主题文字字体，默认微软雅黑
                        fontSize: 16, //主题文字字体大小，默认为18px
                    },
                    left: 6,
                    top: 6,
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        type: "shadow",
                    },
                },
                grid: {
                    left: "-8%",
                    right: "5%",
                    top: "8%",
                    bottom: "4%",
                    containLabel: true,
                },
                xAxis: [
                    {
                        type: "category",
                        data: ["M", "T", "W", "T", "F", "S", "S"],
                    },
                ],
                yAxis: [
                    {
                        show: false,
                    },
                ],
                series: [
                    {
                        name: "老图书馆",
                        type: "bar",
                        stack: "all",
                        emphasis: {
                            focus: "series",
                        },
                        data: [250, 244, 252, 228, 290, 360, 330],
                    },
                    {
                        name: "数字图书馆",
                        type: "bar",
                        stack: "all",
                        emphasis: {
                            focus: "series",
                        },
                        data: [340, 314, 292, 368, 580, 560, 530],
                    },
                ],
            },
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {},
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        let t1 = echarts.init(document.getElementById("t1"));
        let t2 = echarts.init(document.getElementById("t2"));
        let t3 = echarts.init(document.getElementById("t3"));
        t1.setOption(this.opt1);
        // t2.setOption();
        // t3.setOption();
    },
    beforeCreate() {}, //生命周期 - 创建之前
    beforeMount() {}, //生命周期 - 挂载之前
    beforeUpdate() {}, //生命周期 - 更新之前
    updated() {}, //生命周期 - 更新之后
    beforeDestroy() {}, //生命周期 - 销毁之前
    destroyed() {}, //生命周期 - 销毁完成
    activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang="less" scoped>
.FirstLeftBody {
    width: 100%;
    height: 100%;
    padding: 0 20px;
}
.table {
    float: left;
    height: calc(100% - 40px);
    width: calc((100% - 80px) / 3);
    margin: 20px 16px;
    background-color: #fff;
}
.table:first-child {
    margin-left: 0;
}
.table:last-child {
    margin-right: 0;
}
</style>
