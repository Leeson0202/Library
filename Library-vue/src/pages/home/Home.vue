<!-- Home -->
<template>
    <div class="Home" id="home">
        <!-- 上半部分 -->
        <div class="first-content">
            <!-- 上左 -->
            <div class="first-left">
                <div class="first-left-title"><FirstLeftTitle /></div>
                <div class="first-left-body"><FirstLeftBody /></div>
                <div class="first-left-end"><FirstLeftEnd /></div>
            </div>
            <!-- 上右 -->
            <div class="first-right"></div>
        </div>

        <!-- 下半部分 -->
        <div class="second-block" style="width: 100%">
            <div class="rate-bin">圆饼图</div>
            <div class="lines">
                <div
                    id="manwoman"
                    class="card"
                    style="height: 300px; width: 100%"
                ></div>
                <div
                    id="manwomanbin"
                    class="card"
                    style="
                        height: 200px;
                        width: 30%;
                        margin-top: 20px;
                        display: inline-block;
                    "
                ></div>
                <div
                    id="labelbin"
                    class="card"
                    style="
                        height: 200px;
                        width: 40%;
                        margin-top: 20px;
                        display: inline-block;
                    "
                ></div>
            </div>
            <div class="tops">排行榜</div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import * as echarts from 'echarts';
import FirstLeftTitle from "./first/FirstLeftTitle.vue";
import FirstLeftBody from "./first/FirstLeftBody.vue";
import FirstLeftEnd from "./first/FirstLeftEnd.vue";

let option = {
    tooltip: {
        trigger: "axis",
        axisPointer: {
            type: "shadow",
        },
    },
    legend: {},
    grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true,
    },
    xAxis: [
        {
            type: "category",
            data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
    ],
    yAxis: [
        {
            type: "value",
        },
    ],
    series: [
        {
            name: "总人数-男生",
            type: "bar",
            stack: "all",
            emphasis: {
                focus: "series",
            },
            data: [250, 244, 252, 228, 290, 360, 330],
        },
        {
            name: "总人数-女生",
            type: "bar",
            stack: "all",
            emphasis: {
                focus: "series",
            },
            data: [340, 314, 292, 368, 580, 560, 530],
        },
        {
            name: "老图-男生",
            type: "bar",
            stack: "first",
            emphasis: {
                focus: "series",
            },
            data: [120, 132, 101, 134, 90, 230, 210],
        },
        {
            name: "老图女生",
            type: "bar",
            stack: "first",
            emphasis: {
                focus: "series",
            },
            data: [220, 182, 191, 234, 290, 330, 310],
        },
        {
            name: "数图-男生",
            type: "bar",
            stack: "second",
            emphasis: {
                focus: "series",
            },
            data: [130, 112, 151, 94, 200, 130, 120],
        },
        {
            name: "数图-女生",
            type: "bar",
            stack: "second",
            emphasis: {
                focus: "series",
            },
            data: [120, 132, 101, 134, 290, 230, 220],
        },
    ],
};
let option1 = {
    tooltip: {
        trigger: "item",
    },
    legend: {
        top: "5%",
        left: "center",
    },
    series: [
        {
            name: "Access From",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 5,
                borderWidth: 4,
            },
            label: {
                show: false,
                position: "center",
            },
            emphasis: {
                label: {
                    show: false,
                    fontSize: 30,
                    fontWeight: "nomal",
                },
            },
            labelLine: {
                show: true,
            },
            data: [
                { value: 484, name: "男生" },
                { value: 300, name: "女生" },
            ],
        },
    ],
};
let option2 = {
    tooltip: {
        trigger: "item",
    },
    legend: {
        top: "3%",
        left: "center",
    },
    series: [
        {
            name: "Access From",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 5,
                borderWidth: 3,
            },
            label: {
                show: false,
                position: "center",
            },
            emphasis: {
                label: {
                    show: false,
                    fontSize: 30,
                    fontWeight: "nomal",
                },
            },
            labelLine: {
                show: true,
            },
            data: [
                { value: 484, name: "老师" },
                { value: 300, name: "学生" },
                { value: 300, name: "其他" },
            ],
        },
    ],
};

export default {
    name: "Home",
    components: { FirstLeftTitle, FirstLeftBody, FirstLeftEnd },
    props: [],
    data() {
        //这里存放数据
        return {};
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
        // 接下来的使用就跟之前一样，初始化图表，设置配置项
        var manwoman = echarts.init(document.getElementById("manwoman"));
        var manwomanbin = echarts.init(document.getElementById("manwomanbin"));
        var labelbin = echarts.init(document.getElementById("labelbin"));
        manwoman.setOption(option);
        manwomanbin.setOption(option1);
        labelbin.setOption(option2);
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
.Home {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
.rate-bin,
.lines,
.tops {
    float: left;
    line-height: 20px;
    height: 100%;
}
.rate-bin {
    width: 30%;
}
.lines {
    width: 40%;
    // background-color: #ccc;
}
.tops {
    width: 30%;
}
.card {
    background-color: #fff;
}

.first-content {
    width: 100%;
    height: 64%;
    text-align: left;
    .first-left {
        height: 100%;
        width: calc(100% - 300px);
        float: left;
        .first-left-title {
            width: 100%;
            height: 16%;
            // background-color: #fff;
        }
        .first-left-body {
            width: 100%;
            height: 50%;
            // background-color: #fff;
        }
        .first-left-end {
            width: 100%;
            height: 34%;
            background-color: #eee;
        }
    }
    .first-right {
        height: 100%;
        width: 300px;
        float: left;
        background-color: #fff;
    }
}
.second-content {
    width: 100%;
    height: 36%;
}
</style>
