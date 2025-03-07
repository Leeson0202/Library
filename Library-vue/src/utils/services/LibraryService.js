import http from '../http';

export default class LibraryService {
  static queryLibraries(schoolId) {
    return http.get('api/library', { schoolId });
  }

  static queryLibrariesByToken() {
    return http.get('api/library');
  }

  static queryLibrary(libraryId) {
    return http.get(`api/library/id/${libraryId}`);
  }

  static insertLibrary(data) {
    return http.post('api/library', data);
  }

  static updateLibrary(data) {
    return http.put('api/library', data);
  }

  static deleteLibrary(libraryId) {
    return http.delete('api/library', { libraryId });
  }
}