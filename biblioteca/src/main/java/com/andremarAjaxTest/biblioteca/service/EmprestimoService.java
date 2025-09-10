package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.EmprestimoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.EmprestimoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmprestimoService {
    List<EmprestimoResponse> getAll();
    EmprestimoResponse create(EmprestimoRequest request);
    EmprestimoResponse update(Long id, EmprestimoRequest request);
    void delete(Long id);
    EmprestimoResponse getById(Long id);

}
