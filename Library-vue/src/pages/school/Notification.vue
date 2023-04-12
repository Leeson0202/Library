<!-- Notification -->
<template>
    <div class="Notification">
        <div class="operate">
            <el-button type="danger" size="mini" style="margin-left: 10px"
                >删除
            </el-button>

            <el-button
                size="mini"
                style="margin-left: 16px"
                @click="editNotification(-1)"
                >添加
            </el-button>
        </div>

        <el-table
            :data="tableData"
            :stripe="true"
            style="width: calc(100% - 40px); margin: 0 20px"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column fixed label="日期" width="230">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">
                        {{
                            scope.row.date.slice(0, 10) +
                            " " +
                            scope.row.date.slice(10, 19)
                        }}
                    </span>
                </template>
            </el-table-column>
            <el-table-column prop="nickName" label="管理员" width="120">
            </el-table-column>

            <el-table-column label="标题" width="380">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>{{ scope.row.context }}</p>
                        <div slot="reference" class="name-wrapper">
                            {{ scope.row.title }}
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column
                label="已读"
                prop="view"
                width="100"
            ></el-table-column>
            <el-table-column align="left" style="align-items: right">
                <template slot="header">
                    <el-input
                        v-model="searchValue"
                        size="mini"
                        placeholder="输入关键字搜索"
                        style="max-width: 300px; float: left"
                    />
                </template>
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="editNotification(scope.$index, scope.row)"
                        >编辑</el-button
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

        <!-- 对话框 -->

        <el-dialog title="编辑" :visible.sync="dialogFormVisible">
            <el-form :model="form" style="width: 90%">
                <el-form-item label="描述" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.title"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input
                        type="textarea"
                        rows="5"
                        v-model="form.context"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-form>
            <!-- 下方确定按钮 -->
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="onSubmit">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import api from "@/utils/api";
import { Message } from "element-ui";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "Notification",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            size: 10,
            totalElements: 0,
            searchValue: "",
            idx: -1,
            dialogFormVisible: false,
            // 对话框数据
            form: {},
            formLabelWidth: "80px",
            tableData: [],
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        submitSuccess(data) {
            Message.success("提交成功");
            this.dialogFormVisible = false;
            this.queryNotifications(this.$store.state.school.schoolId, 0);
        },
        // 提交
        onSubmit() {
            if (this.idx == -1) {
                api.insertNotification(this.form).then((data) => {
                    this.submitSuccess(data);
                });
            } else {
                api.updateNotification(this.form).then((data) => {
                    this.submitSuccess(data);
                });
            }
        },
        // 查找
        queryNotifications(schoolId, page) {
            let form = { schoolId, page, size: this.size };
            api.queryNotifications(form).then((data) => {
                this.totalElements = data.data.totalElements;
                data.data.content.forEach((el) => {
                    el.date = new Date(el.date).toLocaleString();
                });
                this.tableData = data.data.content;
            });
        },
        // 编辑
        editNotification(idx, row) {
            this.idx = idx;
            if (idx == -1)
                this.form = {
                    schoolId: this.$store.state.school.schoolId,
                    userId: this.$store.state.user.userInfo.userId,
                    title: "",
                    context: "",
                };
            else this.form = { ...row };
            this.form.date = null;
            this.dialogFormVisible = true;
        },
        search(e) {},
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },

        handleDelete(index, row) {
            this.$confirm("确定删除？")
                .then((_) => {
                    api.deleteNotification(row.notificationId).then((data) => {
                        Message.success("删除成功");
                        this.queryNotifications(
                            this.$store.state.school.schoolId,
                            0
                        );
                    });
                    done();
                })
                .catch((_) => {});
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        this.queryNotifications(this.$store.state.school.schoolId, 0);
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
* {
    user-select: text;
    line-height: 20px;
}
.Notification {
    padding: 20px 0;
    height: 100%;
    background-color: #fff;

    .operate {
        height: 50px;
        padding-left: 10px;
        * {
            float: left;
        }
    }
}
</style>
