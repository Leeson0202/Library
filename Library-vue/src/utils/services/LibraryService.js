import http from '../http';

export default {
      // 获取图书馆
      queryLibraries: (schoolId) => http.get("api/library", { schoolId }),
      queryLibrariesByToken: () => http.get("api/library"),
      queryLibrary: (libraryId) => http.get("api/library/id/" + libraryId),
      insertLibrary: (data) => http.post("api/library", data),
      updateLibrary: (data) => http.put("api/library", data),
      deleteLibrary: (libraryId) => http.delete("api/library", { libraryId }),
}