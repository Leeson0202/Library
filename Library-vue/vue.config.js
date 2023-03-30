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
  // 添加全局less变量
  devServer: {
    proxy: {
      "/api": {
        // 匹配所有以 '/api1'开头的请求路径
        target: "https://library.leeson.cool", // 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: { "^/api": "" }, //代理服务器将请求地址转给真实服务器时会将 /api1 去掉
      },
    },
  },
});
