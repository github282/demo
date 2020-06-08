package com.cloudmusic.dao;

import com.cloudmusic.domian.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDao extends JpaRepository<Code, Integer> {
    public Code findByUsername(String username);
}
