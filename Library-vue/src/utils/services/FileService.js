import http from '../http';

export default class FileService {

    static fileUpload(data) {
        return http.upload('api/upload', data);
    }

    static fileDownload(fileName) {
        return http.get(`api/download/${fileName}`);
    }
}