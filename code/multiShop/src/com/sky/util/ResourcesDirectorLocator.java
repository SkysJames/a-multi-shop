package com.sky.util;

import org.springframework.util.StringUtils;

public class ResourcesDirectorLocator {
	private String root;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public String mergePath(String relation) {
		return StringUtils.applyRelativePath(StringUtils.cleanPath(root), StringUtils.cleanPath(relation));
	}
}
