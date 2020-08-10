package com.pizzafinder.service.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface DownloadApi {

    @RequestMapping(value = "/download",
            method = RequestMethod.GET)
    ModelAndView excelExport(HttpServletRequest request, HttpServletResponse response);
}
