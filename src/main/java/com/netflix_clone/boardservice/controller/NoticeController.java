package com.netflix_clone.boardservice.controller;

import com.netflix_clone.boardservice.component.exception.CommonException;
import com.netflix_clone.boardservice.repository.dto.reference.NoticeDto;
import com.netflix_clone.boardservice.repository.dto.reference.PageableRequest;
import com.netflix_clone.boardservice.repository.dto.request.SaveNoticeRequest;
import com.netflix_clone.boardservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService service;

    @GetMapping(value = "/")
    public ResponseEntity<PageImpl> notices(@ModelAttribute PageableRequest pageRequest) {
        return new ResponseEntity<>(service.notices(pageRequest), HttpStatus.OK);
    }
    @GetMapping(value = "/{noticeNo:[\\d]+}")
    public ResponseEntity<NoticeDto> notice(@PathVariable Long noticeNo) throws CommonException {
        return new ResponseEntity<>(service.notice(noticeNo), HttpStatus.OK);
    }
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> save(@ModelAttribute SaveNoticeRequest request){
        return new ResponseEntity<>(service.save(request), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{noticeNo:[\\d]+}")
    public ResponseEntity<Boolean> remove(@PathVariable Long noticeNo) {
        return new ResponseEntity<>(service.remove(noticeNo), HttpStatus.OK);
    }
}
