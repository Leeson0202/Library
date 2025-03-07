import http from '../http';

export default {

    fileUpload: (data) => http.upload("api/upload", data),
    fileDownload: (fileName) => http.get("api/download/" + fileName),
}