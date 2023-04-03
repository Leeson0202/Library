<!-- Seat -->
<template>
    <div class="Seat">
        <!-- menu -->
        <div class="menu-block">
            <template>
                <el-select v-model="libraryId" clearable placeholder="请选择">
                    <el-option
                        v-for="item in libraryList"
                        size="mini"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    >
                    </el-option>
                </el-select>
                <el-button style="margin-left: 16px" @click="addLibrary"
                    >添加
                </el-button>
            </template>
        </div>
        <!-- main -->
        <div class="main-block">
            <el-empty v-if="libraryId == ''" :image-size="200"> </el-empty>
            <div v-else>
                <el-table
                    :data="rooms[libraryId]"
                    :stripe="true"
                    style="width: calc(100% - 30px)"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="name"
                        label="房间名"
                        width="160"
                    >
                    </el-table-column>

                    <el-table-column prop="descs" label="描述" width="350">
                    </el-table-column>

                    <el-table-column
                        prop="seatNum"
                        label="座位数量"
                        align="center"
                        width="80"
                    >
                    </el-table-column>

                    <el-table-column
                        label="剩余位置"
                        prop="left"
                        width="80"
                        align="center"
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
                                @click="dialogFormVisible = true"
                                >编辑</el-button
                            >
                            <el-button
                                size="mini"
                                type="primary"
                                @click="handleDelete(scope.$index, scope.row)"
                                >编辑座位</el-button
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

        <el-dialog title="编辑" :visible.sync="dialogFormVisible">
            <el-form :model="form" style="width: 90%;">
                <el-form-item label="图书馆" :label-width="formLabelWidth">
                    <el-select v-model="form.region" placeholder="请选择图书馆">
                        <el-option
                            label="老图书馆"
                            value="shanghai"
                        ></el-option>
                        <el-option
                            label="数字图书馆"
                            value="beijing"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="房间名" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.descs"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item label="座位数量" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.seatNum"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-form>
            <!-- 下方确定按钮 -->
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false"
                    >保 存</el-button
                >
            </div>
        </el-dialog>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "Seat",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            searchValue: "",
            libraryList: [
                {
                    value: "avajhgvwab",
                    label: "老图书馆",
                },
                {
                    value: "wajhvcgwavc",
                    label: "数字图书馆",
                },
            ],
            libraryId: "wajhvcgwavc",
            rooms: {
                avajhgvwab: [
                    {
                        roomId: "ajvgah",
                        name: "一楼电脑室",
                        descs: "老图书馆一楼电脑室",
                        seatNum: 100,
                        left: 20,
                        weekend: true,
                        beginTime: "",
                        endTime: "",
                    },
                    {
                        roomId: "ajvgah",
                        name: "二楼电脑室",
                        descs: "老图书馆二楼电脑室",
                        seatNum: 80,
                        left: 20,
                        weekend: true,
                        beginTime: "",
                        endTime: "",
                    },
                ],
                wajhvcgwavc: [
                    {
                        roomId: "ajvgah",
                        name: "一楼阅览室",
                        descs: "数字图书馆一楼阅览室",
                        seatNum: 100,
                        left: 20,
                        weekend: true,
                        beginTime: "",
                        endTime: "",
                    },
                    {
                        roomId: "ajvgah",
                        name: "二楼阅览室",
                        descs: "数字图书馆二楼阅览室",
                        seatNum: 80,
                        left: 20,
                        weekend: true,
                        beginTime: "",
                        endTime: "",
                    },
                ],
            },
            // 对话框
            dialogFormVisible: false,
            // 对话框数据
            form: {
                roomId: "ajvgah",
                name: "一楼电脑室",
                descs: "老图书馆一楼电脑室",
                seatNum: 100,
                left: 20,
                weekend: true,
                beginTime: "",
                endTime: "",
            },
            formLabelWidth: "80px",
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        toggleSelection(rows) {
            if (rows) {
                rows.forEach((row) => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        clearFilter(e, c) {},
        search(e) {},
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
        addLibrary() {},
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
.Seat {
    padding: 20px;
    height: 100%;
    background-color: #fff;
    overflow-y: scroll;
}
.menu-block {
    display: block;
    height: 50px;
    width: 100%;
    text-align: left;
}
.main-block {
    width: 100%;
}
</style>
