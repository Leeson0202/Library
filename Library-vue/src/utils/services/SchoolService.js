import http from '../http';

export default class SchoolService {
  static querSchoolSimple(schoolId) {
    return http.get('api/school/simple', { schoolId });
  }

  static querySchool() {
    return http.get('api/school');
  }

  static updateSchool(data) {
    return http.put('api/school', data);
  }

  static insertSchool(data) {
    return http.post('api/school', data);
  }

  static deleteSchool(schoolId) {
    return http.delete('api/school', { schoolId });
  }
}