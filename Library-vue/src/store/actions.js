import { AuthService, UserService, SchoolService, LibraryService, ReservationService, RoomService, RuleService, NotificationService, FileService, TableService, SeatService } from "../utils/services/index";
export default {
    // 初始化
    Launch(context, value) {
        // 更新token
        AuthService.loginUpdate().then((data) => {
            if (data == undefined || data.code == 401 || data.code == 402) {
                window.localStorage.clear();
                this.commit("launch");
                return false;
            } else if (data.code == 200) {
                window.localStorage.setItem("token", data.token);
                this.dispatch("QueryUserInfo");
                this.dispatch("QuerySchool");
                this.commit("updateLogin", true);
                return true;
            }
        });
    },
    // 获取用户信息
    QueryUserInfo(context, value) {
        UserService.queryUserInfo().then((data) => {
            context.commit("updateUserInfo", data.userInfo);
        });
    },
    // 获取学校信息
    QuerySchool(context, value) {
        SchoolService.querySchool().then((data) => {
            context.dispatch("QuerySchoolSimple", data.data.schoolId);
            context.commit("updateSchool", data.data);
        });
    },
    // 获取学校Simple信息
    QuerySchoolSimple(context, value) {
        SchoolService.querSchoolSimple(value).then((data) => {
            let root = [];
            let res = data.data.librarySimpleList;
            res.forEach((el1, idx) => {
                let t = {};
                t.value = el1.libraryId;
                t.label = el1.name;
                t.children = [];
                el1.roomSimpleList.forEach((el) => {
                    let tt = {};
                    tt.value = el.roomId;
                    tt.label = el.name;
                    t.children.push(tt);
                });
                root.push(t);
            });
            context.commit("updateOptions", root);
        });
    },
};
