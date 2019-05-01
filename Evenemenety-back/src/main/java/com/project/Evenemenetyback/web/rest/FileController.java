package com.project.Evenemenetyback.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.service.util.FileService;
import com.project.Evenemenetyback.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/api/files")
    @Timed
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> handleFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
        fileService.storeFile(file);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("FileStorage : Creation", file.getName())).build();
    }
}
