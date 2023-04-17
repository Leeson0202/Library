<!-- Library -->
<template>
    <div class="Library" id="library" v-resize="resize">
        <!-- 图书馆列表 -->
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
                    'background-image': 'url(api/' + item.background + ')',
                }"
            >
                <div
                    style="
                        width: 100%;
                        height: 100%;
                        background-color: rgba(255, 255, 255, 0.2);
                    "
                ></div>
            </div>
            <div class="library-info">
                <div class="library-title">{{ item.name }}</div>
                <div class="simple">{{ item.descs }}</div>
            </div>
        </el-card>
        <!-- 添加Card -->
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

        <!--  抽屉 -->
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
                            rows="5"
                            v-model="form.descs"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="背景">
                        <el-upload
                            class="upload-demo"
                            drag
                            action="none"
                            auto-upload
                            :http-request="uploadFile"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload"
                        >
                            <img
                                v-if="imageUrl"
                                :src="imageUrl"
                                style="width: 100%; height: 100%"
                            />
                            <div else>
                                <i class="el-icon-upload"></i>
                                <div class="el-upload__text">
                                    将文件拖到此处，或<em>点击上传</em>
                                </div>
                            </div>
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="开放规则">
                        <el-select
                            v-model="form.weekend"
                            placeholder="请选择活动区域"
                            style="width: 100%"
                        >
                            <el-option
                                label="工作日（一周五天）"
                                :value="false"
                            ></el-option>
                            <el-option
                                label="非节假日（一周七天）"
                                :value="true"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="开放时间">
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.beginTime"
                                :picker-options="{
                                    start: '07:00',
                                    step: '00:30',
                                    end: '17:30',
                                }"
                                placeholder="选择时间"
                                style="width: 100%"
                            >
                            </el-time-select>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.endTime"
                                :picker-options="{
                                    start: '09:00',
                                    step: '00:30',
                                    end: '22:00',
                                }"
                                placeholder="选择时间"
                                style="width: 100%"
                            ></el-time-select>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="时段长度">
                        <el-col :span="11">
                            <el-time-select
                                v-model="form.tt"
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

                    <el-form-item style="float: right; width: 100%">
                        <el-button
                            v-if="drawerIdx != -1"
                            type="danger"
                            @click="rmLibrary(form.libraryId)"
                            style="float: left; position: relative; left: -65px"
                            >删 除</el-button
                        >
                        <div style="float: right">
                            <el-button @click="drawer = false">取 消</el-button>
                            <el-button type="primary" @click="onSubmit">
                                保 存
                            </el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </el-drawer>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import api from "@/utils/api";
import { Message } from "element-ui";

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
            imageUrl: "",
            form: {
                name: "",
                desc: "",
                background: "",
                rule: "",
                beginTime: "",
                endTime: "",
                tt: "",
            },
        };
    },
    //监听属性 类似于data概念
    computed: {
        libraries() {
            return this.$store.state.school.libraries;
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        // 上传成功
        handleAvatarSuccess(res, file) {
            console.log(res, file);
            this.imageUrl = URL.createObjectURL(file.raw);
            this.form.background = URL.createObjectURL(file.raw);
        },
        // 上传
        uploadFile(file) {
            console.log(file);
            let form = { file: file.file };
            api.fileUpload(form).then((data) => {
                this.form.background = data.data;
                this.imageUrl = "api/" + data.data;
            });
        },
        // 上传前
        beforeAvatarUpload(file) {
            const isJPG = file.type === "image/jpeg";
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error("上传头像图片只能是 JPG 格式!");
            }
            if (!isLt2M) {
                this.$message.error("上传头像图片大小不能超过 2MB!");
            }
            return isJPG && isLt2M;
        },
        // 打开 drawer
        openDrawer(idx) {
            if (idx == -1) {
                this.form = {
                    schoolId: this.$store.state.school.schoolId,
                    name: "",
                    desc: "",
                    background: "",
                    rule: "",
                    beginTime: "",
                    endTime: "",
                    tt: "",
                };
                this.imageUrl = this.form.background;
            } else {
                this.form = { ...this.libraries[idx] };
                this.imageUrl = this.form.background;
                if (this.form.beginTime.length > 5) {
                    this.form.beginTime = this.form.beginTime.slice(0, 5);
                }
                if (this.form.endTime.length > 5) {
                    this.form.endTime = this.form.endTime.slice(0, 5);
                }
                if (this.form.tt.length > 5) {
                    this.form.tt = this.form.tt.slice(0, 5);
                }
            }
            this.drawerIdx = idx;

            this.drawer = true;
        },
        // 关闭抽屉
        handleClose(done) {
            this.$confirm("保存并关闭？")
                .then((_) => {
                    this.onSubmit();
                })
                .catch((_) => {});
        },
        // 保存按钮
        onSubmit() {
            if (this.drawerIdx == -1) {
                // 新的
                // console.log("new", this.form);

                api.insertLibrary(this.form).then((data) => {
                    // console.log(data);
                    if (data.code == 200) {
                        Message.success("添加成功");
                        this.$store.dispatch("QuerySchool");
                    }
                });
            } else {
                //更新
                api.updateLibrary(this.form).then((data) => {
                    console.log(data);
                    if (data.code == 200) {
                        Message.success("修改成功");
                        this.$store.dispatch("QuerySchool");
                    }
                });
            }
            console.log("submit");
            this.drawer = false;
        },
        // 删除图书馆
        rmLibrary(libraryId) {
            this.$confirm("确定删除图书馆？")
                .then((_) => {
                    api.deleteLibrary(libraryId).then((data) => {
                        this.drawer = false;
                        Message.success("删除成功");
                        this.$store.dispatch("QuerySchool");
                    });
                })
                .catch((_) => {});
        },
        // 监听宽度
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
    mounted() {
        //  更新学校和图书馆列表
        setTimeout(() => {
            this.$store.dispatch("QuerySchool");
        }, 300);
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
            margin-bottom: 6px;
        }
        .simple {
            text-indent: 2em;
            line-height: 20px;
            height: 20px;
            width: 100%;
            overflow: hidden;
        }
    }
}
.upload-demo,
/deep/ .upload-demo > .el-upload,
/deep/ .upload-demo > .el-upload > .el-upload-dragger {
    width: 100% !important;
}
/deep/ .el-drawer {
    min-width: 400px;
}
</style>
