package com.eunbi.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

	public String getAccessToken(String code) throws IOException;

}
