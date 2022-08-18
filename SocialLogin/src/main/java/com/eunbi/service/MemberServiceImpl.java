package com.eunbi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eunbi.persistence.MemberDAO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDAO dao;

	@Override
	public String getAccessToken(String code) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
