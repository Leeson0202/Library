<!-- FirstLeftEnd -->
<template>
    <div class="FirstLeftEnd">
        <div class="card table fle1">
            <div class="fle-title">实时图书馆数据</div>
            <div id="fle1"></div>
        </div>
        <div class="card table fle2">
            <div class="fle-title">今日就坐情况</div>
            <div id="fle2"></div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;
import * as echarts from "echarts";
import $ from "jquery";

// let myChart = echarts.init(document.getElementById("fle1"));
// let fle2 = echarts.init(document.getElementById("fle2"));

export default {
    name: "FirstLeftEnd",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            opt1Data: [],
            optDataLength: 4,
            fle1: null,
            fle2: null,
            opt1: {
                grid: {
                    left: "0%",
                    right: "8%",
                    top: "8%",
                    bottom: "4%",
                    containLabel: true,
                },
                xAxis: {
                    max: "dataMax",
                },
                yAxis: {
                    type: "category",
                    data: [
                        "数图\n一楼阅览室",
                        "数图\n二楼阅览室",
                        "老图\n一楼电脑室",
                        "老图\n二楼阅览室",
                    ],
                    inverse: true,
                    animationDuration: 300,
                    animationDurationUpdate: 300,
                    // max: 2, // only the largest 3 bars will be displayed
                    max: 3, // only the largest 3 bars will be displayed
                },
                series: [
                    {
                        realtimeSort: true,
                        name: "X",
                        type: "bar",
                        data: [],
                        label: {
                            show: true,
                            position: "right",
                            valueAnimation: true,
                        },
                    },
                ],
                animationDuration: 0,
                animationDurationUpdate: 3000,
                animationEasing: "linear",
                animationEasingUpdate: "linear",
            },
            opt2: {
                tooltip: {
                    trigger: "axis",
                },
                legend: {
                    top: "bottom",
                    textStyle: {
                        //标题内容的样式
                        // color: "#666", //京东红
                        fontStyle: "normal", //lic主标题文字字体风格，默认normal，有italic(斜体),oblique(斜体)
                        fontWeight: "500", //可选normal(正常)，bold(加粗)，bolder(加粗)，lighter(变细)，100|200|300|400|500...
                        fontFamily: "san-serif", //主题文字字体，默认微软雅黑
                        fontSize: 10, //主题文字字体大小，默认为18px
                    },
                },
                grid: {
                    left: "3%",
                    right: "4%",
                    top: "3%",
                    bottom: "22%",
                    containLabel: true,
                },
                xAxis: {
                    type: "category",
                    boundaryGap: false,
                    data: ["8", "10", "12", "14", "16", "18", "20"],
                },
                yAxis: {
                    type: "value",
                },
                series: [
                    {
                        name: "数图一楼阅览室",
                        type: "line",
                        data: [10, 20, 51, 44, 90, 120, 10],
                    },
                    {
                        name: "数图二楼阅览室",
                        type: "line",
                        data: [20, 30, 80, 48, 89, 99, 20],
                    },
                    {
                        name: "老图一楼电脑室",
                        type: "line",
                        data: [13, 40, 61, 74, 28, 101, 19],
                    },
                    {
                        name: "老图二楼电脑室",
                        type: "line",
                        data: [16, 10, 91, 90, 75, 120, 5],
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
    methods: {
        // 初始化
        init() {
            let that = this;
            this.fle1 = echarts.init(document.getElementById("fle1"));
            this.fle2 = echarts.init(document.getElementById("fle2"));
            this.fle1.setOption(this.opt1);
            this.fle2.setOption(this.opt2);


            setTimeout(this.run, 0);
            setInterval(function () {
                that.run();
            }, 10000);
        },

        // fle1 刷新
        run() {
            let data = [];
            for (var i = 0; i < this.optDataLength; ++i) {
                if (Math.random() > 0.9) {
                    data[i] = Math.round(Math.random() * 2000);
                } else {
                    data[i] = Math.round(Math.random() * 200);
                }
            }
            this.fle1.setOption({
                series: [
                    {
                        type: "bar",
                        data,
                    },
                ],
            });
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        this.init();
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
.FirstLeftEnd {
    padding: 0 20px;
    width: 100%;
    height: 100%;
    .card {
        display: inline-block;
        background-color: #fff;
        padding: 10px 20px;
    }
    .fle-title {
        line-height: 30px;
        font-size: 18px;
        font-weight: 500;
        color: #666;
    }
    .fle1 {
        height: 100%;
        width: calc((100% - 30px) * 0.6);
        margin-right: 30px;
        vertical-align: top;
    }
    .fle2 {
        width: calc((100% - 30px) * 0.4);
        height: 100%;
    }
    #fle1 {
        height: calc(100% - 30px);
        width: 100%;
    }
    #fle2 {
        height: calc(100% - 30px);
        width: 100%;
    }
}
</style>
