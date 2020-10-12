/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface CrudService<T, ID> {

	Page<T> findAll(String[] columnSortValue, String sortDirection);

	Page<T> findAllWithPaging(Integer page, Integer size, String[] columnSortValue, String sortDirection);

	T add(T t);

	T edit(T t);

	Boolean deleteById(ID id);

	Boolean delete(T t);

	Optional<T> findById(ID id);

	List<T> findAll();

	Boolean isExistById(ID id);
	
	Pageable createPageRequest(Integer pageIndex, Integer size, String[] columnSort, String sortDirection) ;
}
