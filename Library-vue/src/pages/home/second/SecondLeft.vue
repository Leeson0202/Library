<!-- SecondLeft -->
<template>
    <div class="SecondLeft">
        <div class="card">
            <div class="title">违规人员</div>
            <div class="list">
                <div class="item" v-for="(user, idx) in users" :key="idx">
                    <div class="avatar-block">
                        <img class="avatar" :src="user.avatarUrl" alt="" />
                    </div>
                    <div class="name">
                        {{ user.name }}
                    </div>
                    <div class="xueyuan">
                        {{ user.xueyuan }}
                    </div>
                    <div class="weigui">
                        {{ user.weigui }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "SecondLeft",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            users: [
                {
                    id: "akhja",
                    name: "我很讨厌吗？",
                    avatarUrl:
                        "https://tse1-mm.cn.bing.net/th/id/OIP-C.5VKhXHzPE19RBWEliYRJvgAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    xueyuan: "摆烂学院",
                    weigui: "迟到20分钟，信用-5",
                },
                {
                    id: "akhja",
                    name: "悄悄地卷",
                    avatarUrl:
                        "https://tse3-mm.cn.bing.net/th/id/OIP-C.BxBNXt0A-U6q099vh6X4AAAAAA?w=200&h=200&c=7&r=0&o=5&dpr=2&pid=1.7",
                    xueyuan: "淘汰学院",
                    weigui: "早离座位20分钟，信用-5",
                },
                {
                    id: "akhja",
                    name: "一杯苦咖啡",
                    avatarUrl:
                        "https://tse4-mm.cn.bing.net/th/id/OIP-C.mfN47fQjxPzHz9WMxXIr-QAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    xueyuan: "成功学院",
                    weigui: "占座，信用-5",
                },
                {
                    id: "akhja",
                    name: "你什么档次",
                    avatarUrl:
                        "https://tse3-mm.cn.bing.net/th/id/OIP-C.E2J-rMDr5CtTG3nLC2q8dQAAAA?w=191&h=191&c=7&r=0&o=5&dpr=2&pid=1.7",
                    xueyuan: "摆烂学院",
                    weigui: "迟到20分钟，信用-5",
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
.SecondLeft {
    width: 100%;
    height: 100%;
    padding: 20px 20px 10px;
    text-align: left;
}
.card {
    padding: 20px 20px;
    background-color: #fff;
    vertical-align: top;
    overflow: scroll;
}
.title {
    font-size: 18px;
    font-weight: 500;
    height: 20px;
    color: #666;
    margin-bottom: 16px;
}
.list {
    .item {
        margin-top: 4px;
        line-height: 40px;
        .avatar-block {
            float: left;
            .avatar {
                height: 40px;
                border-radius: 50%;
            }
        }
        .name {
            float: left;
            width: 160px;
            text-align: left;
            margin-left: 10px;
        }
        .xueyuan {
            float: left;
            width: calc((100% - 240px) * 0.4);
            text-align: left;
        }
    }
}
</style>
