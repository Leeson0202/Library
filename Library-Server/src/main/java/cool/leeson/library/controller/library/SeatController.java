package cool.leeson.library.controller.library;

import cool.leeson.library.service.library.LibraryRoomService;
import cool.leeson.library.service.library.LibrarySeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class SeatController {
    @Resource
    private HttpServletRequest request;
    /**
     * 服务对象
     */
    @Resource
    private LibrarySeatService librarySeatService;


}
