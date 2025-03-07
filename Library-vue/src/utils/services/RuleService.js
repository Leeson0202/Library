import http from '../http';

export default class RuleService {
  static queryRule(schoolId) {
    return http.get(`api/rule/id/${schoolId}`);
  }

  static insertRule(data) {
    return http.post('api/rule', data);
  }

  static updateRule(data) {
    return http.put('api/rule', data);
  }

  static deleteRule(ruleId) {
    return http.delete('api/rule', { ruleId });
  }
}