<!-- Credit -->
<template>
    <div class="Credit">
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
            <div class="setting">
                <img
                    style="height: 100%"
                    :src="require('@/assets/img/setting.svg')"
                />
            </div>
        </div>
        <div class="credit-show">
            <el-empty v-if="input == ''" :image-size="200"></el-empty>
            <div v-else>
                <el-table
                    :data="users"
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
                        prop="credit"
                        label="信用"
                        width="120"
                        align="center"
                    >
                    </el-table-column>
                    <el-table-column prop="date" label="时间" sortable width="200">
                    </el-table-column>
                    <el-table-column
                        prop="num"
                        label="扣分"
                        width="60"
                        align="center"
                    >
                    </el-table-column>
                    <el-table-column prop="msg" label="记录" width="300">
                    </el-table-column>

                    <el-table-column align="left" label="操作" style="align-items: right">
                        
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                @click="dialogFormVisible = true"
                                >修改</el-button
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
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "Credit",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            input: "Leeson",
            users: [
                {
                    id: "aisfygewuh",
                    name: "Leeson",
                    credit: 95,
                    date: "2023-02-20 14:15:00",
                    msg: "迟到15分钟",
                    num: "-5",
                },
                {
                    id: "aisfygewuh",
                    name: "cquab",
                    credit: 95,
                    date: "2023-02-20 16:15:00",
                    msg: "迟到15分钟",
                    num: "-5",
                },
                {
                    id: "aisfygewuh",
                    name: "cquab",
                    credit: 95,
                    date: "2023-02-19 16:15:00",
                    msg: "迟到15分钟",
                    num: "-5",
                },
            ],
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
.Credit {
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
</style>
