<!-- FirstRight -->
<template>
    <div class="FirstRight card">
        <div v-for="(user, idx) in users" :key="idx">
            <div class="top1" v-if="idx == 0">
                <div style="height: 70px">
                    <img class="avatarImg" :src="user.avatarUrl" alt="" />
                </div>
                <div style="display: block; height: 60px; margin: auto">
                    <div class="user-name">{{ user.name }}</div>
                    <div class="user-desc">{{ user.descs }}</div>
                </div>
            </div>
            <div class="next" v-else>
                <img class="avatarImg" :src="user.avatarUrl" alt="" />
                <div
                    style="
                        display: inline-block;
                        height: 48px;
                        margin-left: 10px;
                        width: calc(100% - 80px);
                    "
                >
                    <div style="float: left">
                        <div class="user-name">{{ user.name }}</div>
                        <div class="user-desc">{{ user.descs }}</div>
                    </div>
                    <div class="time-num">{{ user.minute }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "FirstRight",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            users: [
                {
                    id: "",
                    name: "Leeson",
                    avatarUrl:
                        "https://tse3-mm.cn.bing.net/th/id/OIP-C.XpnLCXvNjh1PjkvAr-t6nAAAAA?w=215&h=215&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "诗与远方",
                    minute: 300,
                },
                {
                    id: "",
                    name: "无所谓",
                    avatarUrl:
                        "https://tse1-mm.cn.bing.net/th/id/OIP-C.g4VMocET-A-JEvZrRMalBgAAAA?w=215&h=215&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "意义",
                    minute: 300,
                },
                {
                    id: "",
                    name: "左盼",
                    avatarUrl:
                        "https://tse1-mm.cn.bing.net/th/id/OIP-C.7h9wcySsOrfNHTk1wJV2JQAAAA?w=215&h=215&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "每天开心",
                    minute: 300,
                },
                {
                    id: "",
                    name: "小猪佩奇",
                    avatarUrl:
                        "https://tse4-mm.cn.bing.net/th/id/OIP-C.q7VFtf5fSePQqf5MbJEY5QAAAA?w=199&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "诗与远方不达标",
                    minute: 300,
                },
                {
                    id: "",
                    name: "再见只是陌生人",
                    avatarUrl:
                        "https://tse2-mm.cn.bing.net/th/id/OIP-C.yLK1zLu33aujN7QnOMqDwQAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "2048",
                    minute: 290,
                },
                {
                    id: "",
                    name: "读书",
                    avatarUrl:
                        "https://tse1-mm.cn.bing.net/th/id/OIP-C.UzMtNg2KaGmUlx5uP8XZ_AAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "阅万卷书，行万里路",
                    minute: 280,
                },
                {
                    id: "",
                    name: "悄悄地卷",
                    avatarUrl:
                        "https://tse3-mm.cn.bing.net/th/id/OIP-C.j1DqlOxg1HOiKCuXJ5LhvgAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "安静地。",
                    minute: 180,
                },
                {
                    id: "",
                    name: "这个表情有点帅",
                    avatarUrl:
                        "https://tse3-mm.cn.bing.net/th/id/OIP-C.dESxYPLsFhStiSYxbhAo5wAAAA?w=198&h=198&c=7&r=0&o=5&dpr=2&pid=1.7",
                    descs: "你发现不了我这里写的啥",
                    minute: 168,
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
.FirstRight {
    width: 100%;
    height: 100%;
    background-color: #fff;
    padding: 10px;
}
.next {
    padding-left: 10px;
}
.avatarImg {
    display: inline-block;
    width: 40px;
    border-radius: 50%;
}
.user-name {
    font-size: 13px;
    font-weight: 500;
}
.user-desc {
    color: #666;
    font-size: 12px;
}
.time-num {
    line-height: 40px;
    font-size: 14px;
    font-weight: 500;
    float: right;
    color: rgb(243, 151, 151);
}
.top1 {
    text-align: center;
    width: 100%;
    // height: 120px;
    .avatarImg {
        display: block;
        margin: auto;
        width: 70px;
    }
    .user-name {
        font-size: 18px;
        font-weight: 500;
    }
    .user-desc {
        color: #666;
        font-size: 12px;
    }
}
</style>
