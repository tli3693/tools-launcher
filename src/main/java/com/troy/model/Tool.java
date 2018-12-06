package com.troy.model;

public class Tool {

	private String name;
	private String path;
	private String source;
	private String fileName;

	public Tool() {
	}

	public Tool(String name, String path, String source, String fileName) {
		super();
		this.name = name;
		this.path = path;
		this.source = source;
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
