package com.cloudmusic.dao;

import com.cloudmusic.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDao extends JpaRepository<Code, Integer> {
    public Code findByUsername(String username);
}
