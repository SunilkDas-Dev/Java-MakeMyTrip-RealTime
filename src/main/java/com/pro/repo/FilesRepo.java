package com.pro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.entity.Files;

public interface FilesRepo extends JpaRepository<Files, Long> {

}
