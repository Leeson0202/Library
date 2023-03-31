import $ from 'jquery';

export default {
    install(Vue,) {
        //全局过滤器
        // Vue.filter('mySlice', function (value) {
        //     return value.slice(0, 4)
        // })

        //定义全局指令
        Vue.directive('resize', {
            bind(el, binding) { // el为绑定的元素，binding为绑定给指令的对象
                let width = '', height = '';

                function isReize() {
                    const style = document.defaultView.getComputedStyle(el);
                    if (width !== style.width || height !== style.height) {
                        binding.value();  // 关键
                    }
                    width = style.width;
                    height = style.height;
                }

                el.__vueSetInterval__ = setInterval(isReize, 300);
            },
            unbind(el) {
                clearInterval(el.__vueSetInterval__);
            }

        })

        //定义混入
        // Vue.mixin({
        //     data() {
        //         return {
        //             x: 100,
        //             y: 200
        //         }
        //     },
        // })


        //给Vue原型上添加一个方法（vm和vc就都能用了）
        Vue.prototype.sleep = (millTime) => {
            let start = Date.now();
            while (true) {
                let nowTime = Date.now();
                let offset = nowTime - start;
                if (offset >= millTime)
                    break;
            }
        }


        // 格式化时间
        Vue.prototype.timeFormate = (time) => {
            if (time === Infinity || isNaN(time)) {
                return '00:00'
            }
            let minite = Math.floor(time / 60)
            minite = minite > 9.5 ? minite : "0" + minite;
            let secend = Math.floor(time % 60)
            secend = secend > 9.5 ? secend : "0" + secend
            return minite + ":" + secend
        }

        // 解析 Lyric 文本为 歌词数组
        Vue.prototype.parseLyric = (text) => {
            //将文本分隔成一行一行，存入数组
            var lines = text.split('\n'),
                //用于匹配时间的正则表达式，匹配的结果类似[xx:xx.xx]
                pattern = /\[\d{2}:\d{2}.\d{2}\]/g,
                //保存最终结果的数组
                result = [];
            //去掉不含时间的行
            while (!pattern.test(lines[0])) {
                lines = lines.slice(1);
            }
            ;
            //上面用'\n'生成生成数组时，结果中最后一个为空元素，这里将去掉
            lines[lines.length - 1].length === 0 && lines.pop();
            lines.forEach(function (v /*数组元素值*/, i /*元素索引*/, a /*数组本身*/) {
                //提取出时间[xx:xx.xx]
                var time = v.match(pattern),
                    //提取歌词
                    value = v.replace(pattern, '');
                //因为一行里面可能有多个时间，所以time有可能是[xx:xx.xx][xx:xx.xx][xx:xx.xx]的形式，需要进一步分隔
                time.forEach(function (v1, i1, a1) {
                    //去掉时间里的中括号得到xx:xx.xx
                    var t = v1.slice(1, -1).split(':');
                    //将结果压入最终数组
                    result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value]);
                });
            });
            //最后将结果数组中的元素按时间大小排序，以便保存之后正常显示歌词
            result.sort(function (a, b) {
                return a[0] - b[0];
            });
            return result;
        }

        // ajax同步请求 获取数据
        Vue.prototype.AjaxAncy = (url) => {
            $.ajax({
                url: '..............',
                cache: false,
                dataType: 'json',
                type: 'post',
                async: false,
                success: function (data) {
                    return data;
                },
                error: function (data, errormsg, e) {
                    console.log(data);
                }
            });
        }


    }
}