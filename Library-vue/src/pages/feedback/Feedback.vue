<!-- FeedBack -->
<template>
    <div class="FeedBack">
        <div class="menu-block">
            <el-input
                class="input"
                v-model="input"
                placeholder="请输入用户名"
                clearable
            ></el-input>
            <el-button
                icon="el-icon-search"
                style="float: left; margin-left: 20px"
            ></el-button>
        </div>
        <!-- 数组展示 -->
        <div class="feedback-show">
            <el-empty v-if="input == ''" :image-size="200"></el-empty>
            <div v-else>
                <el-table
                    :data="feedbacks"
                    :stripe="true"
                    style="width: calc(100% - 30px)"
                    :default-sort="{ prop: 'date', order: 'descending' }"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column fixed prop="name" label="姓名" width="120">
                    </el-table-column>

                    <el-table-column
                        prop="date"
                        label="时间"
                        sortable
                        width="200"
                    >
                    </el-table-column>

                    <el-table-column prop="title" label="标题" width="300">
                    </el-table-column>

                    <el-table-column label="状态" width="100" align="center">
                        <template slot-scope="scope">
                            {{
                                scope.row.status == 0
                                    ? "未查阅"
                                    : scope.row.status == 1
                                    ? "已查阅"
                                    : "已解决"
                            }}
                        </template>
                    </el-table-column>
                    <el-table-column
                        align="left"
                        label="操作"
                        style="align-items: right"
                    >
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                @click="showForm(scope.$index)"
                                >查阅</el-button
                            >
                            <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)"
                                >删除</el-button
                            >
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <!-- 对话框 -->
        <el-dialog :title="form.title" :visible.sync="dialogFormVisible">
            <div
                style="
                    text-align: left;
                    margin: 0px 20px 10px;
                    font-size: 18px;
                    font-weight: 500;
                "
            >
                反馈内容：
            </div>
            <div class="feedback-context">{{ form.context }}</div>
            <div
                style="
                    text-align: left;
                    margin: 30px 20px 10px;
                    font-size: 18px;
                    font-weight: 500;
                "
            >
                请输入回复信息：
            </div>
            <el-input
                class="feedback-input"
                type="textarea"
                :rows="8"
                placeholder="请输入内容"
                v-model="textarea"
            >
            </el-input>

            <!-- 下方确定按钮 -->
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button  @click="dialogFormVisible = false">
                    未阅读
                </el-button>
                <el-button type="primary" @click="dialogFormVisible = false"
                    >已阅读
                </el-button>
                <el-button type="primary" @click="dialogFormVisible = false"
                    >已解决
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "FeedBack",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            feedbacks: [
                {
                    id: "aabwwa",
                    name: "Leeson",
                    date: "2023-03-30 20:00:00",
                    title: "首页数据问题",
                    context: "首页数据不能展示",
                    status: 0,
                    feedback: "正在努力修改bug，敬请期待",
                },
                {
                    id: "aabwwa",
                    name: "Leeson",
                    date: "2023-03-30 08:22:00",
                    title: "首页数据问题",
                    context: "首页数据不能展示",
                    status: 1,
                    feedback: "正在努力修改bug，敬请期待",
                },
                {
                    id: "aabwwa",
                    name: "Leeson",
                    date: "2023-03-29 10:33:00",
                    title: "首页数据问题",
                    context: "首页数据不能展示",
                    status: 2,
                    feedback: "正在努力修改bug，敬请期待",
                },
            ],
            dialogFormVisible: false,
            form: {
                id: "aabwwa",
                name: "Leeson",
                date: "2023-03-30 20:00:00",
                title: "首页数据问题",
                context: "首页数据不能展示",
                status: 0,
                feedback: "正在努力修改bug，敬请期待",
            },
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        showForm(row) {
            console.log(this.feedbacks[row]);
            this.form = this.feedbacks[row];
            this.dialogFormVisible = true;
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
.FeedBack {
    width: 100%;
    height: 100%;
    background-color: #fff;
    padding: 20px;
}
.menu-block {
    height: 60px;
    padding-left: 10px;
    .input {
        float: left;
        width: 40%;
        min-width: 300px;
    }
    .setting {
        float: right;
        margin-right: 20px;
        font-size: 20px;
        height: 30px;
        top: calc(50% - 15px);
        cursor: pointer;
    }
}
.feedback-context {
    width: 90%;
    margin: auto;
    max-height: 200px;
    text-align: left;
    text-indent: 2em;
    overflow: scroll;
    margin-bottom: 20px;
    // background-color: #eee;
}
.feedback-input {
    width: 90%;
}
</style>
