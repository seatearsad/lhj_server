package com.wolfroc.slots.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-11-18 ����04:35:09
 * @Description 
 */
@SuppressWarnings("serial")
public abstract class NestedCheckedException extends Exception {

	private Throwable cause;
	public NestedCheckedException(String msg) {
		super(msg);
	}
	public NestedCheckedException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}
	public Throwable getCause() {
		return (this.cause == this ? null : this.cause);
	}
	public String getMessage() {
		if (this.cause == null || this.cause == this) {
			return super.getMessage();
		}
		else {
			return super.getMessage() + "; nested exception is " + this.cause.getClass().getName() +
					": " + this.cause.getMessage();
		}
	}
	
	public void printStackTrace() {
		if (this.cause == null || this.cause == this) {
			super.printStackTrace();
		}
		else {
			System.err.println(this);
			this.cause.printStackTrace();
		}
	}
	public void printStackTrace(PrintStream ps) {
		if (this.cause == null || this.cause == this) {
			super.printStackTrace(ps);
		}
		else {
			ps.println(this);
			this.cause.printStackTrace(ps);
		}
	}
	public void printStackTrace(PrintWriter pw) {
		if (this.cause == null || this.cause == this) {
			super.printStackTrace(pw);
		}
		else {
			pw.println(this);
			this.cause.printStackTrace(pw);
		}
	}
	public boolean contains(Class<?> exClass) {
		if (exClass == null) {
			return false;
		}
		Throwable ex = this;
		while (ex != null) {
			if (exClass.isInstance(ex)) {
				return true;
			}
			if (ex instanceof NestedCheckedException) {
				ex = ((NestedCheckedException) ex).getCause();
			}
			else {
				ex = null;
			}
		}
		return false;
	}
}
