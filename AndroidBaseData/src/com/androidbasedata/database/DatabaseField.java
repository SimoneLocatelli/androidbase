package com.androidbasedata.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.androidbase.dotnet.alias.SU;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DatabaseField {

	boolean primaryKey() default false;

	boolean autoincrement() default false;

	String name();

	ColumnTypes type();

	boolean nullable() default false;

	boolean unique() default false;

	String check() default SU.Empty;

	String deafultValue() default SU.Empty;
}
