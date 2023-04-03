<!-- Settings -->
<template>
    <div class="Settings">
        <div class="main" v-if="hasSchool">
            <div class="schoolhead">
                <img
                    class="icon"
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.5d61f749f94162bbe886fe0db3c58b16?rik=iqZNXpwMs5GL8w&riu=http%3a%2f%2fi9.qhimg.com%2ft01916e9d3c0cc53c0e.jpg&ehk=3ocM5tD5vMh4rqIubPEYdYioEmlLvAYk32NUb4EkqpI%3d&risl=&pid=ImgRaw&r=0"
                    alt=""
                />
                <span class="schoolName">{{ school.name }}</span>
                <el-button
                    style="margin-left: 40px; position: relative; top: -4px"
                    @click="edit"
                    >编辑</el-button
                >
            </div>

            <div style="margin-top: 40px">
                <div style="text-indent: 2em">
                    {{ school.descs }}
                </div>
            </div>
            <div style="margin-top: 20px">
                <div></div>
                <img class="image" :src="'api' + school.background" alt="" />
            </div>
        </div>

        <el-empty
            v-if="!hasSchool"
            :image-size="200"
            description="没有创建学校"
        >
            <el-button @click="createSchool()"> 点击创建学校</el-button>
        </el-empty>

        <!--  抽屉 -->
        <el-drawer
            :title="title"
            :visible.sync="drawer"
            direction="rtl"
            :before-close="handleClose"
        >
            <div style="width: 90%">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="名称">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>

                    <el-form-item label="图标">
                        <el-upload
                            class="upload-icon"
                            drag
                            action="https://jsonplaceholder.typicode.com/posts/"
                            multiple
                        >
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">
                                <em>点击上传</em>或拖拽
                            </div>
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="描述">
                        <el-input
                            type="textarea"
                            rows="10"
                            v-model="form.descs"
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

                    <el-form-item style="float: right">
                        <el-button @click="drawer = false">取消</el-button>
                        <el-button type="primary" @click="onSubmit"
                            >保存</el-button
                        >
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
    name: "Settings",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            title: "编辑学校信息",
            drawer: false,
            form: {
                name: "",
                descs: "",
                background: "",
            },
        };
    },
    //监听属性 类似于data概念
    computed: {
        school() {
            return this.$store.state.school;
        },
        hasSchool() {
            return this.$store.state.school.hasSchool;
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        edit() {
            this.title = "编辑管理的学校";
            this.form = this.school;
            this.drawer = true;
        },
        // 创建学校
        createSchool() {
            this.title = "添加管理的学校";
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
        // 提交保存
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
.Settings {
    width: 100%;
    height: 100%;
    background: #fff;
    padding: 20px;
    text-align: left;
}
.main {
    width: 800px;
    margin: auto;
}
.icon {
    height: 100px;
}
.label {
    display: inline-block;
    font-size: 20px;
    margin-right: 10px;
}
.schoolName {
    line-height: 100px;
    margin-left: 20px;
    font-size: 26px;
}
.image {
    width: 100%;
}

// 设置drawer的
.upload-icon,
/deep/ .upload-icon > .el-upload,
/deep/ .upload-icon > .el-upload > .el-upload-dragger {
    height: 160px !important;
    width: 160px !important;
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
