<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "App",
    components: {},
    data() {
        return {};
    },
    computed: {},
    methods: {

    },
    mounted() {
        this.$store.dispatch("Launch");
    },
    created() {},
};
</script>

<style scoped>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /* text-align: center; */
    color: #2c3e50;
    margin: auto;
    font-size: 16px;
    height: 100%;
    width: 100%;
    background-color: #f4f4f4;
}

</style>
