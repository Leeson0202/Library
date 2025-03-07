import http from '../http';

export default {
    queryRule: (schoolId) => http.get("api/rule/id/" + schoolId),
    insertRule: (data) => http.post("api/rule", { data }),
    updateRule: (data) => http.put("api/rule", data),
    deleteRule: (ruleId) => http.delete("api/rule", { ruleId }),
}