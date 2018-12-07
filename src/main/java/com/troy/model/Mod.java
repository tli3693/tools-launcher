package com.troy.model;

import java.util.Objects;

public class Mod {

	private String name;
	private String path;
	private String source;
	private String fileName;
	private boolean selected;

	public Mod(String name, String path, String source, String fileName) {
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Mod mod = (Mod) o;
		return Objects.equals(name, mod.name) &&
				Objects.equals(path, mod.path) &&
				Objects.equals(source, mod.source) &&
				Objects.equals(fileName, mod.fileName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, path, source, fileName);
	}
}
