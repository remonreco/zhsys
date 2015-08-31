package com.cbice.distribute.mgr.service;

import net.sf.json.JSONArray;

import org.springframework.web.multipart.MultipartFile;

public interface ImportAllUserService {
	

	JSONArray importAllUser(MultipartFile messageFile);

}
