<!-- Library -->
<template>
    <div class="Library" id="library" 
            v-resize="resize">
        <el-card
            class="library-card"
            :body-style="{ padding: '0px' }"
            v-for="(itemjh, idx) in 13"
            :key="idx"
            :style="{ width: detailWidth - 40 + 'px' }"
        >
            <div
                class="card-img"
                :style="{
                    'background-image':
                        'url(' +
                        'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg' +
                        ')',
                }"
            ></div>
            <div>数字图书馆</div>
        </el-card>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "Library",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            detailWidth: 0,
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        resize() {
            // console.log("size is change");
            // console.log(document.getElementById("showTable").getBoundingClientRect().width)
            // 调整宽度
            var showTable = document.getElementById("library");
            if (showTable == null) return;
            let Allwidth = document
                .getElementById("library")
                .getBoundingClientRect().width;
            let n = Allwidth / 330;
            this.detailWidth = Allwidth / Math.floor(n);
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {},
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
.Library {
    padding: 20px;
    height: 100%;
    background-color: #fff;
    line-height: 30px;
    overflow-y: scroll;

    .library-card {
        display: inline-block;
        margin: 10px;
        height: 240px;
        float: left;
        .card-img {
            height: 160px;
            overflow: hidden;
            background-size: 100%;
            background-position: center;
        }
    }
}
</style>
