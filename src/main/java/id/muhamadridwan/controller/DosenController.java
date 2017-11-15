package id.muhamadridwan.controller;

import id.muhamadridwan.model.Dosen;
import id.muhamadridwan.dto.DosenCustom;
import id.muhamadridwan.service.DosenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mridwan on 14/11/2017.
 */
@Controller
public class DosenController {

    @Autowired
    private DosenService service;

    @GetMapping("/dosens")
    @ResponseBody
    public List<Dosen> getDosenAll() {
        return service.getDosenAll();
    }

    @GetMapping("/dosens-custom")
    @ResponseBody
    public List<DosenCustom> getDosenAll2() {
        return service.getDosenCustomAll();
    }


}
