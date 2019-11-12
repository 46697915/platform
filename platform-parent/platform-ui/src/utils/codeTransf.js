
exports.install = (Vue, options) => {
    Vue.prototype.codeTransf = {
        lockedTransf(val) {
            //window.console.log("begin locked .")
            if(val == '0'){
                return '未锁定';
            }
            if(val == '1'){
                return '已锁定';
            }
            return val ;
        }
    }
    Vue.prototype.available = {
        available(val) {
            //window.console.log("begin locked .")
            if(val == '1'){
                return '有效';
            }
            if(val == '0'){
                return '无效';
            }
            return val ;
        }
    }
}