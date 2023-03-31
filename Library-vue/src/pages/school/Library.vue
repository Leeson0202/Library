<!-- Library -->
<template>
    <div class="Library" id="library" v-resize="resize">
        <el-card
            class="library-card"
            :body-style="{ padding: '0px' }"
            v-for="(item, idx) in libraries"
            :key="idx"
            :style="{ width: detailWidth - 40 + 'px' }"
        >
            <div
                class="card-img"
                @click="openDrawer(idx)"
                :style="{
                    'background-image': 'url(' + item.background + ')',
                }"
            ></div>
            <div class="library-info">
                <div class="library-title">{{ item.name }}</div>
                <div class="simple">{{ item.descs }}</div>
            </div>
        </el-card>
        <!--  抽屉 -->
        <el-card
            class="library-card"
            :style="{ width: detailWidth - 40 + 'px' }"
        >
            <div @click="openDrawer(-1)">
                <i
                    class="el-icon-plus"
                    style="font-size: 60px; color: #666; margin-top: 50px"
                ></i>
                <div style="color: #666">添加图书馆</div>
            </div>
        </el-card>

        <el-drawer
            title="编辑图书馆"
            :visible.sync="drawer"
            direction="rtl"
            :before-close="handleClose"
        >
            <div style="width: 90%">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="名称">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input
                            type="textarea"
                            v-model="form.desc"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="背景">
                        <el-upload
                            class="upload-demo"
                            drag
                            action="https://jsonplaceholder.typicode.com/posts/"
                            multiple
                        >
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">
                                将文件拖到此处，或<em>点击上传</em>
                            </div>
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="开放规则">
                        <el-select
                            v-model="form.rule"
                            placeholder="请选择活动区域"
                            style="width: 100%"
                        >
                            <el-option
                                label="工作日（一周五天）"
                                value="shanghai"
                            ></el-option>
                            <el-option
                                label="非节假日（一周七天）"
                                value="beijing"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="开放时间">
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.time1"
                                :picker-options="{
                                    start: '07:00',
                                    step: '00:30',
                                    end: '17:30',
                                }"
                                placeholder="选择时间"
                                style="width: 130px"
                            >
                            </el-time-select>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.time2"
                                :picker-options="{
                                    start: '09:00',
                                    step: '00:30',
                                    end: '22:00',
                                }"
                                placeholder="选择时间"
                                style="width: 130px"
                            ></el-time-select>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="时段长度">
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.time3"
                                :picker-options="{
                                    start: '00:30',
                                    step: '00:30',
                                    end: '4:00',
                                }"
                                placeholder="选择时间段"
                                style="width: 180px"
                            >
                            </el-time-select>
                        </el-col>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="onSubmit"
                            >保存</el-button
                        >
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-drawer>
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
            detailWidth: 380,
            //抽屉
            drawer: false,
            drawerIdx: 0,
            libraries: [
                {
                    id: "jsabahadv",
                    name: "老图书馆",
                    descs: "老图书馆",
                    background:
                        "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
                },
                {
                    id: "jsabaqrfsahadv",
                    name: "数字图书馆",
                    descs: "新图书馆",
                    background:
                        "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
                },
            ],
            form: {
                name: "",
                desc: "",
                background: "",
                rule: "",
                time1: "",
                time2: "",
                time3: "",
            },
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
        // 打开 drawer
        openDrawer(idx) {
            this.drawerIdx = idx;
            this.drawer = true;
        },
        // 关闭抽屉
        handleClose(done) {
            this.$confirm("保存并关闭？")
                .then((_) => {
                    done();
                })
                .catch((_) => {});
        },
        onSubmit() {},
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
        .library-info {
            padding: 6px 16px;
            text-align: left;
        }
        .library-title {
            font-size: 18px;
            font-weight: 500;
        }
        .simple {
            text-indent: 2em;
            line-height: 12px;
        }
    }
}
.upload-demo,
/deep/ .upload-demo > .el-upload,
/deep/ .upload-demo > .el-upload > .el-upload-dragger {
    width: 100% !important;
}
</style>
