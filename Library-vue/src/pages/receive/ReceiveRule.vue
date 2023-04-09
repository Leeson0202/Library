<!-- ReceiveRule -->
<template>
    <div class="ReceiveRule">
        <div class="rule-show" v-if="!editTag">
            <div v-if="t.length > 0">
                <div class="title">
                    预约规则
                    <div class="edit-btu" @click="toEdit">编辑</div>
                </div>
                <div v-for="(item, index) in t" :key="index" class="context">
                    {{ item }}
                </div>
            </div>
            <div v-if="t.length == 0">
                <el-empty :image-size="300"> </el-empty>
                <el-button @click="toEdit">添加规则</el-button>
            </div>
        </div>
        <div class="rule-edit" v-if="editTag">
            <div class="title">编辑预约规则</div>
            <div>
                <div class="rule-input">
                    <el-input
                        type="textarea"
                        :rows="26"
                        placeholder="请输入内容"
                        v-model="eContext"
                        style="line-height: 30px"
                    >
                    </el-input>
                </div>
                <div style="margin-top: 40px">
                    <el-button @click="editTag = false">取消</el-button
                    ><el-button type="primary" @click="success">保存</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import api from "@/utils/api";

export default {
    name: "ReceiveRule",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            rule: {
                context: "",
            },
            editTag: false,
            eContext: "",
        };
    },
    //监听属性 类似于data概念
    computed: {
        ruleContext() {
            return this.$store.state.school.schoolRule.context;
        },
        t() {
            return this.$store.state.school.schoolRule.context.split("\n");
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        toEdit() {
            this.editTag = true;
            this.eContext = this.ruleContext;
        },
        success() {
            let that = this;
            api.updateRule({
                schoolId: this.$store.state.school.schoolId,
                context: this.eContext,
            }).then((data) => {
                this.editTag = false;
                this.$store.dispatch("QuerySchool");
            });
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        // this.t = this.ruleContext.split("\n");
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
.ReceiveRule {
    width: 100%;
    height: 100%;
    padding: 20px 100px;
    background-color: #fff;
    overflow-y: scroll;
}
.title {
    font-size: 20px;
    line-height: 40px;
    margin-bottom: 40px;
}
.rule-show {
    padding-bottom: 80px;
    .title {
        .edit-btu {
            margin-right: 40px;
            float: right;
            color: rgb(131, 160, 240);
            cursor: pointer;
        }
    }
    .context {
        text-align: left;
        text-indent: 2em;
        line-height: 30px;
    }
}

.rule-edit {
    .rule-input {
        min-height: 400px;
    }
}
</style>
