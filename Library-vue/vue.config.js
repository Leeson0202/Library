const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: true,
    /* 代码保存时进行eslint检测 */
    lintOnSave: false,

    /* 输出文件目录：在npm run build时，生成文件的目录名称 */
    outputDir: "dist",
    /* 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录 */
    assetsDir: "assets",
    /* 是否在构建生产包时生成 sourceMap 文件，false将提高构建速度 */
    productionSourceMap: false,
    /* 默认情况下，生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存，你可以通过将这个选项设为 false 来关闭文件名哈希。(false的时候就是让原来的文件名不改变) */
    filenameHashing: false,

    devServer: {
        // port: 8070,
        proxy: {
            "/api": {
                //这里最好有一个 /
                target: "https://api.library.leeson.cool", // 后台接口域名
                // target: "http://localhost:8080", // 后台接口域名
                changeOrigin: true, //是否跨域
                pathRewrite: {
                    "^/api": "",
                },
            },
        },
    },
});
