import http from '../http';

export default {
    // 获取整个学校信息
    querSchoolSimple: (schoolId) => http.get("api/school/simple", { schoolId }),
    // 获取学校信息-token
    querySchool: () => http.get("api/school"),
    // 修改学校
    updateSchool: (data) => http.put("api/school", data),
    // 添加学校
    insertSchool: (data) => http.post("api/school", data),
    // 删除学校
    deleteSchool: (schoolId) => http.delete("api/school", { schoolId }),

}